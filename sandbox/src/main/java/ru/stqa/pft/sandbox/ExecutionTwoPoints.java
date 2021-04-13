package ru.stqa.pft.sandbox;

public class ExecutionTwoPoints {
  public static void main(String[] args) {

    TwoPoints p = new TwoPoints(1, 2, 3, 4);


    System.out.println("Расстояние между точкой p1 c параметрами (" + p.x1 + ", " + p.x2 + ") и точкой p2 c параметрами (" + p.x3 + ", " + p.x4 + ") равно " + p.distance2());
  }


}