package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification(){
  app.getNavigationHelper().returnToHomePage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "test@test.com", "111", "test1"), true);
    }
  app.getContactHelper().initContactModification();
  app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3", "test@test.com", "111", null), false);
  app.getContactHelper().submitContactModification();
  app.getNavigationHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  app.getSessionHelper().logout();
  }

}
