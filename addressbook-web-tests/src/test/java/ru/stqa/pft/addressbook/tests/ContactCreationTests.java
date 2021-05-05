package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().returnToHomePage();
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().goToAddNewPage();
    app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "test@test.com", "111", "test1"), true);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
    app.getSessionHelper().logout();
  }


}
