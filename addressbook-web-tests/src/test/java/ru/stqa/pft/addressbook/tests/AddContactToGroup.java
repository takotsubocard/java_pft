package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase{
  @BeforeMethod
  public void ensureContactIsPresent(){
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withName("test1"), true);
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
    Contacts before = app.db().contacts();
    ContactData contactAddedToGroup = before.iterator().next();
    app.goTo().homePage();
    ContactData contact = new ContactData().withId(contactAddedToGroup.getId()).withName("name 0").
            withSurname("surname 0").withAddress("address 0");
    app.contact().initAddingToGroup(contact);
    app.contact().submitAddingToGroup();
    Contacts after = app.db().contacts();
    Assert.assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(contactAddedToGroup).withAdded(contact)));
    // app.getSessionHelper().logout();
  }

}


