package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification(){
  app.getContactHelper().returnToHomePage();
  app.getContactHelper().initContactModification();
  app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3", "test@test.com", "111", null), false);
  app.getContactHelper().submitContactModification();
  app.getContactHelper().returnToHomePage();
  app.getSessionHelper().logout();
  }

}
