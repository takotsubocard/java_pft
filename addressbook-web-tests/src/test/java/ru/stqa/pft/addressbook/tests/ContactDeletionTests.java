package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() {
  app.getNavigationHelper().returnToHomePage();
  int before = app.getContactHelper().getContactCount();
  if (! app.getContactHelper().isThereAContact()) {
    app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "test@test.com", "111", "test_group"), true);
  }
  app.getContactHelper().selectContact();
  app.getContactHelper().deleteSelectedContact();
  app.getContactHelper().returnToHomePage();
  int after = app.getContactHelper().getContactCount();
  Assert.assertEquals(after, before - 1);
  app.getSessionHelper().logout();
  }

}
