package model;

public class Point {
	
	private int x;
	private int y;
	
	//Constructor
	public Point() {
		super();
		this.x = 0;
		this.y = 0;
	}
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	//Getters & Setters
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public Point clone() {
		return new Point(x,y);
	}
}
