/**
 * Class Point
 * Author: Gabor Nemeth
 * Date: 2012-07-09
 * 
 * 
 * Implementation of a 2D point class. 
 */ 
package model;
public class Point {
	
	private float x;
	private float y;
	
	
	/**
	 * Constructor. One must set the x and the y coordinate to create a point.
	 * @param x is the x coordinate of the point.
	 * @param y is the y coordinate of the point.
	 */ 
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Setting the x coordinate of the point.
	 * @param x is the x coordinate of the point.
	 */ 
	public void setX(float x) {
		this.x = x;
	}
	
	
	/**
	 * Setting the y coordinate of the point.
	 * @param y is the y coordinate of the point.
	 */ 
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Return the x coordinate of the point.
	 * @return the x coordinate of the point.
	 */ 
	public float getX() {
		return this.x;
	}
	
	
	/**
	 * Return the y coordinate of the point.
	 * @return the y coordinate of the point.
	 */ 
	public float getY() {
		return this.y;
	}
	
}
