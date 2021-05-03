package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
   int before = app.getGroupHelper().getGroupCount();
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData ("test1", null, null));
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before + 1);
    app.getSessionHelper().logout();
  }


}

