package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  @Test (enabled = false)
  public void testContactDeletion() {
  app.getNavigationHelper().returnToHomePage();
  if (! app.getContactHelper().isThereAContact()) {
    app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "test@test.com", "111", "test_group"), true);
  }
    List<ContactData> before = app.getContactHelper().getContactList();
  app.getContactHelper().selectContact(before.size()-1);
  app.getContactHelper().deleteSelectedContact();
  app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
  Assert.assertEquals(after.size(), before.size() - 1);
  before.remove(before.size()-1);

    Assert.assertEquals(before, after);
 // app.getSessionHelper().logout();
  }
}
