package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])"));
  }

  public void fillContactForm(ContactData contact, boolean creation) {
    type(By.name("firstname"), contact.getName());
    type(By.name("lastname"), contact.getSurname());
    type(By.name("address"), contact.getAddress());
    type(By.name("email"), contact.getEmail());
    type(By.name("email2"), contact.getEmail());
    type(By.name("email3"), contact.getEmail());
    type(By.name("home"), contact.getHomePhone());
    type(By.name("mobile"), contact.getMobilePhone());
    type(By.name("work"), contact.getWorkPhone());
    // attach(By.name("photo"), contact.getPhoto());

    /*  if (creation) {
    if (contact.getGroups().size() > 0) {
      Assert.assertTrue(contact.getGroups().size() == 1);
    }
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroups().iterator().next().getName());
  } else

  {
    Assert.assertFalse(isElementPresent(By.name("new_group")));
  }*/

}

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])"));
  }

public void initContactModificationById(int id) {
    wd.findElement(By.xpath("//a[contains(@href, 'edit.php?id=" + id + "')]")).click();

//  WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id));
//    WebElement row = checkbox.findElement(By.xpath("./../.."));
//    List<WebElement> cells = row.findElements(By.tagName("td"));
//    cells.get(7).findElement(By.tagName("a")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("(//input[@value='Delete'])"));
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));

  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public void goToAddNewPage() {
    click(By.linkText("add new"));
  }
  public void returnToHomePage() {
    click(By.linkText("home"));
  }

   public void create(ContactData contact, boolean b){
      goToAddNewPage();
      fillContactForm(new ContactData().withName("test1").withSurname("test2").withAddress("test3").withEmail("test@test.com").
              withHomePhone("111").withMobilePhone("222").withWorkPhone("333"), true);
      submitContactCreation();
      returnToHomePage();
    }
  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
  }

  public void selectGroupOnContactPage(String groupId) {
    Select groupsFromDropDown =
            new Select(wd.findElement(By.name("to_group")));
    groupsFromDropDown.selectByValue(groupId);
  }

  public void addContactToGroup() {
    click(By.name("add"));
  }


  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
   return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String surname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
      String allEmails = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
      String address = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
      // String[] phones = element.findElement(By.cssSelector("td:nth-child(6)")).getText().split("\n");
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withName(name).withSurname(surname).
                withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);

      //        withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]);
      contacts.add(contact);
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String surname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withName(name).withSurname(surname).
            withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).
            withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
  }
  public void filterContactsByGroup(String id) {
    Select groupsDropDown =
            new Select(wd.findElement(By.cssSelector("#right [name='group']")));
    groupsDropDown.selectByValue(id);
  }
  public void removeContactFromGroup() {
    click(By.name("remove"));
  }


}