package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
      if (app.db().contacts().size() == 0) {
        app.goTo().homePage();
        app.contact().create(new ContactData().withName("test1").withSurname("test2").withAddress("test3").
                withEmail("test@test.com").withHomePhone("111").withMobilePhone("222").withWorkPhone("333"), true);
      }
    }


  @Test
          //(enabled = false)
  public void testContactModification(){

    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    app.goTo().homePage();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).withName("test1").withSurname("test2").withAddress("test3").withEmail("test@test.com").
            withHomePhone("111").withMobilePhone("222").withWorkPhone("333");
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    Assert.assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
   // app.getSessionHelper().logout();
  }

}
