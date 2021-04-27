package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() {
  app.getNavigationHelper().returnToHomePage();
  if (! app.getContactHelper().isThereAContact()) {
    app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "test@test.com", "111", "test_group"), true);
  }
  app.getContactHelper().selectContact();
  app.getContactHelper().deleteSelectedContact();
  app.getNavigationHelper().returnToHomePage();
  app.getSessionHelper().logout();
  }

}
