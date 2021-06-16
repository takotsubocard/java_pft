package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase{


  @BeforeMethod
  public void ensureContactIsPresent() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withName("ContactToModify"), true);
    }
  }
  @BeforeMethod
  private ContactData selectContactToAdd() {
    Contacts contactsList = app.db().contacts();
    Groups groupsList = app.db().groups();
    for (ContactData contact : contactsList) {
      if (contact.getGroups().size() < groupsList.size()) {
        return contact;
      }
    }
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("GroupToModify"));
    return contactsList.iterator().next();
  }

  @Test
  //(enabled = false)
  public void testAddContactToGroup() {
    ContactData contactToAdd = selectContactToAdd();
    Groups before = contactToAdd.getGroups();
    GroupData groupToBeModified = selectGroupToModify(contactToAdd);
    app.contact().addToGroup(contactToAdd, groupToBeModified);
    app.goTo().homePage();
    ContactData addContactAfter = selectContactById(contactToAdd);
    Groups after = addContactAfter.getGroups();
    assertThat(after, equalTo(before.withAdded(groupToBeModified)));
  }

  private ContactData selectContactById(ContactData addContact) {
    Contacts contactsById = app.db().contacts();
    return contactsById.iterator().next().withId(addContact.getId());
  }

  private GroupData selectGroupToModify(ContactData contact) {
      Groups allGroups = app.db().groups();
      Groups contactsInGroups = contact.getGroups();
      Collection<GroupData> contactGroups = new HashSet<>(contactsInGroups);
      Collection<GroupData> availableGroups = new HashSet<>(allGroups);
      availableGroups.removeAll(contactGroups);
      return availableGroups.iterator().next();
  }
}


