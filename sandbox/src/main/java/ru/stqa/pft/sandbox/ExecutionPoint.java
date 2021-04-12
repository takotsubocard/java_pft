package ru.stqa.pft.sandbox;

public class ExecutionPoint {
  public static void main(String[] args) {

    Point p1 = new Point();
    p1.x1 = 1;
    p1.x2 = 2;

    Point p2 = new Point();
    p2.x1 = 3;
    p2.x2 = 4;
    System.out.println("Расстояние между точкой p1 c параметрами (" + p1.x1 + ", " + p1.x2 + ") и точкой p1 c параметрами (" + p2.x1 + ", " + p2.x2 + ") равно " + distance(p1, p2));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p1.x1 - p1.x2) * (p1.x1 - p1.x2) + (p2.x1 - p2.x2) * (p2.x1 - p2.x2));
  }
}
