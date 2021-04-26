package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToAddNewPage();
    app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3", "test@test.com", "111"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
    app.getSessionHelper().logout();
  }


}
