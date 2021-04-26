package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() {
  app.getNavigationHelper().goToGroupPage();
  app.getGroupHelper().selectGroup();
  app.getGroupHelper().deleteSelectedGroups();
  app.getNavigationHelper().goToGroupPage();
  app.getSessionHelper().logout(); }

}
