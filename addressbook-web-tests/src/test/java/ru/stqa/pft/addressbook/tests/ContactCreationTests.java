package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactCreationTests extends TestBase {

  @Test
          //(enabled = false)
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withName("test1").withSurname("test2").withAddress("test3").withEmail("test@test.com").
            withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withGroup("test1");
    app.goTo().goToAddNewPage();
    app.contact().create(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

   // app.getSessionHelper().logout();
  }


}
