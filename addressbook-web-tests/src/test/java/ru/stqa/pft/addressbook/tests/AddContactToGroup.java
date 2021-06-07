package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class AddContactToGroup extends TestBase{
  private int maxId;


  @BeforeMethod
  public void createContactWithoutGroup(){
    app.goTo().homePage();
    app.contact().goToAddNewPage();
    ContactData contactData = new ContactData().withName("test1").withSurname("test2").withAddress("test3");
    app.contact().fillContactForm(contactData, true);
    app.contact().submitContactCreation();
    Contacts result = app.db().contacts();
    for ( ContactData contact : result) {
      if (contact.getId() > maxId) {
        maxId = contact.getId();
      }
    }
  }

  @BeforeMethod
  public void ensureGroupIsPresent() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 0"));
    }
  }

  @Test
  //(enabled = false)
  public void testAddContactToGroup(){
    app.goTo().homePage();
    app.contact().selectContactById(maxId);
    app.contact().addContactToGroup();
    ContactData modifiedContact = app.db().contactById(maxId).iterator().next();
    int groupsQuantity = modifiedContact.getGroups().size();
    Assert.assertTrue(groupsQuantity > 0);
  }

}


