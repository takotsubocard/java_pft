package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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

  public void initContactModification(int index) {
   wd.findElements(By.xpath("(//img[@alt='Edit'])")).get(index).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("(//input[@value='Delete'])"));
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));

  }

  public void selectContact(int index) {
  wd.findElements(By.name("selected[]")).get(index).click();
  }
  {
  }
  public void goToAddNewPage() {

    click(By.linkText("add new"));
  }
  public void returnToHomePage() {
    click(By.linkText("home"));
  }

    public void create(ContactData contact, boolean b){
      goToAddNewPage();
      fillContactForm(new ContactData(0,"test1", "test2", "test3", "test@test.com", "111", "test1"), true);
      submitContactCreation();
      returnToHomePage();
    }
  public void modify(int index, ContactData contact) {
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
   return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
  List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String surname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData (id, name, surname, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
