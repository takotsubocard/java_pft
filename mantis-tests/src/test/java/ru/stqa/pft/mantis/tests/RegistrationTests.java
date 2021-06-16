package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.RegistrationHelper;

public class RegistrationTests extends TestBase{

  @Test
  public void testRegistration(){
    app.registration().start("user1", "user@localhost.localdomain");
  }
}
