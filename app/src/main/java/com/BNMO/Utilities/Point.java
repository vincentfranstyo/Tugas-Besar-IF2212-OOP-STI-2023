package com.BNMO.Utilities;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point A) {
        return x == (A.getX()) && (y == A.getY());
    }

    @Override
    public String toString() {
        return "Location: (" + x + "," + y + ")";
    }
}
