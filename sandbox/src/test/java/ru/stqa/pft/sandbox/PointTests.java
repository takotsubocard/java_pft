package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Square;

public class PointTests {
  @Test
  public void testDistance() {
    Point p1 = new Point(1, 1);
    Point p2 = new Point(1,  5);
    Assert.assertEquals(p1.distance(p2), 4.0);

    Point p3 = new Point(1000, 1);
    Point p4 = new Point(1,  1);
    Assert.assertEquals(p3.distance(p4), 999.0);
  }
}
