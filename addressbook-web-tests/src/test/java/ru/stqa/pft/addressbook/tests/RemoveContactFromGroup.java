package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class RemoveContactFromGroup  extends TestBase{
  private int contactId;
  private int groupId;


  @BeforeMethod
  public void createContactWithGroup() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("group 1"));
    }
    Groups groups = app.db().groups();
    for (GroupData group : groups) {
      if (group.getId() > groupId) {
        groupId = group.getId();
      }
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withName("contact 1"), true);
      app.goTo().homePage();
    }
    Contacts contactList = app.db().contacts();
    for ( ContactData contact : contactList) {
      if (contact.getId() > contactId) {
        contactId = contact.getId();
      }
    }
    app.contact().selectContactById(contactId);
    app.contact().selectGroupOnContactPage((String.valueOf(groupId)));
    app.contact().addContactToGroup();
  }

  @Test
  public void testRemoveContactToGroup(){
    app.goTo().homePage();
    app.contact().filterContactsByGroup(String.valueOf(groupId));
    app.contact().selectContactById(contactId);
    app.contact().removeContactFromGroup();
    ContactData modifiedContact = app.db().contactById(contactId).iterator().next();
    int groupsQuantity = modifiedContact.getGroups().size();
    Assert.assertTrue(groupsQuantity == 0);
  }
}



