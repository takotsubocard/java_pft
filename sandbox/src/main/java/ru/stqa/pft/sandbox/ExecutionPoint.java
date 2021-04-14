package ru.stqa.pft.sandbox;

public class ExecutionPoint {
  public static void main(String[] args) {

    Point p1 = new Point(1, 2);
    Point p2 = new Point(4, 2);


    System.out.println("Расстояние между точкой p1 с координатами (" + p1.x + ", " + p1.y + ") и точкой p2 с координатами (" +
            p2.x + ", " + p2.y + ") равно " + p1.distance(p2));

  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));

  }
}


