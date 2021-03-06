package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test0"));
    }
  }

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType()); //List<ContactData>.class
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    Groups groups = app.db().groups();
    ContactData newContact = contact.inGroup(groups.iterator().next());
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.contact().goToAddNewPage();
    app.contact().fillContactForm(newContact,true);
    app.contact().submitContactCreation();
    app.contact().returnToHomePage();
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

/*  @Test
  public void testContactCreationWithPhoto (ContactData contact) throws Exception{
    app.goTo().goToAddNewPage();
    File photo = new File("src/test/resources/stru.png");
    app.contact().fillContactForm(new ContactData().withName("test1").withSurname("test2").
            withAddress("test3").withEmail("test@test.com").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withGroup("test1").withPhoto(photo), true);
    app.contact().submitContactCreation();
    app.contact().returnToHomePage();}*/


/*  @Test
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.print(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.print(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }*/
}

   // app.getSessionHelper().logout();


