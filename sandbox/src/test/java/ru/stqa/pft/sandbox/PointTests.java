package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Square;

public class PointTests {
  @Test
  public void testDistance2() {
    TwoPoints p = new TwoPoints(1, 1, 2, 4);
    TwoPoints p1 = new TwoPoints(1, 1, 2, 5);
    TwoPoints p2 = new TwoPoints(1, 100, 100, 100);
    Assert.assertEquals(p.distance2(), 2.0);
    Assert.assertEquals(p1.distance2(), 3.0);
    Assert.assertEquals(p2.distance2(), 99.0);
  }
}
