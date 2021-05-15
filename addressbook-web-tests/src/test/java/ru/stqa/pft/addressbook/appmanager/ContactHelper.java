package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getSurname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("home"), contactData.getPhone());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])"));
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.xpath("//a[contains(@href, 'edit.php?id=" + id + "')]")).click();
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
      fillContactForm(new ContactData().withName("test1").withSurname("test2").withAddress("test3").withEmail("test@test.com").withPhone("111").withGroup("test1"), true);
      submitContactCreation();
      returnToHomePage();
    }
  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
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
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withName(name).withSurname(surname).withAddress(null).withEmail(null).withPhone(null).withGroup(null);
      contacts.add(contact);
    }
    return contacts;
  }

}
