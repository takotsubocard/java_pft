package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String surname;
  private final String address;
  private final String email;
  private final String phone;
  private final String group;

  public ContactData(String name, String surname, String address, String email, String phone, String group) {
    this.name = name;
    this.surname = surname;
    this.address = address;
    this.email = email;
    this.phone = phone;
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getGroup() {
    return group;
  }
}
