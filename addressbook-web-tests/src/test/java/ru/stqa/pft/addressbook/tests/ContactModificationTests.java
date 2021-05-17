package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withName("test1").withSurname("test2").withAddress("test3").
              withEmail("test@test.com").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withGroup("test1"), true);
    }
  }

  @Test
          //(enabled = false)
  public void testContactModification(){

    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).withName("test1").withSurname("test2").withAddress("test3").withEmail("test@test.com").
            withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withGroup(null);
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
   // app.getSessionHelper().logout();
  }

}
