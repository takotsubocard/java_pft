package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
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
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void initContactModification() {
    click(By.xpath("(//img[@alt='Edit'])[2]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("(//input[@value='Delete'])"));
    wd.switchTo().alert().accept();

  }

  public void selectContact() {click(By.name("selected[]"));
  }
  {
  }
  public void goToAddNewPage() {

    click(By.linkText("add new"));
  }
  public void returnToHomePage() {
    click(By.linkText("home"));
  }


    public void createContact (ContactData contact,boolean b){
      goToAddNewPage();
      fillContactForm(new ContactData("test1", "test2", "test3", "test@test.com", "111", "test_group"), true);
      submitContactCreation();
      returnToHomePage();


    }


  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}
