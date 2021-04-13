package ru.stqa.pft.sandbox;

public class TwoPoints {

  public double x1;
  public double x2;
  public double x3;
  public double x4;



  public TwoPoints(double x1, double x2, double x3, double x4) {
    this.x1 = x1;
    this.x2 = x2;
    this.x3 = x3;
    this.x4 = x4;
  }


  public double distance2() {
    return Math.sqrt((this.x1 - this.x2) * (this.x1 - this.x2) + (this.x3 - this.x4) * (this.x3 - this.x4));
  }
}

