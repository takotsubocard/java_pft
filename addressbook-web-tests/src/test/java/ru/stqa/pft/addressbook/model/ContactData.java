package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String id;
  private final String name;
  private final String surname;
  private final String address;
  private final String email;

  public String getId() {
    return id;
  }

  private final String phone;
  private final String group;

  public ContactData(String name, String surname, String address, String email, String phone, String group) {
    this.id = null;
    this.name = name;
    this.surname = surname;
    this.address = address;
    this.email = email;
    this.phone = phone;
    this.group = group;
  }
  public ContactData(String id, String name, String surname, String address, String email, String phone, String group) {
    this.id = id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, surname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            '}';
  }

}
