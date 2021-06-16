package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup  extends TestBase{

  @BeforeMethod
  public void ensureContactAndGroupArePresent() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("GroupToModify"));
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withName("ContactToModify"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testRemoveContactFromGroup(){
    app.goTo().homePage();
    ContactData contact = selectContact();
    Groups before = contact.getGroups();
    GroupData groupToRemoveFrom = selectGroup(contact);
    app.goTo().homePage();
    app.contact().selectGroupToRemoveFrom(groupToRemoveFrom.getId());
    app.contact().selectContactById(contact.getId());
    app.contact().removeContactFromGroup(contact);
    ContactData contactsAfter = listContactById(contact);
    Groups after = contactsAfter.getGroups();
    assertThat(after, equalTo(before.without(groupToRemoveFrom)));
  }

  private ContactData listContactById(ContactData contact) {
    Contacts contactsById = app.db().contacts();
    return contactsById.iterator().next().withId(contact.getId());
  }

  private GroupData selectGroup(ContactData removeContact) {
    ContactData contact = listContactById(removeContact);
    Groups removedContact = contact.getGroups();
    return removedContact.iterator().next();
  }

  private ContactData selectContact() {
    Contacts allContacts = app.db().contacts();
    for (ContactData contact : allContacts) {
      if (contact.getGroups().size() > 0) {
        return contact;
      }
    }
    ContactData addedContact = app.db().contacts().iterator().next();
    app.contact().addToGroup(addedContact, app.db().groups().iterator().next());
    return addedContact;
  }
}


