/**
 * Class Circle
 * Author: Gabor Nemeth
 * Date: 2012-07-09
 * 
 * 
 * Implementation of a 2D circle class.
 * 
 */ 
 package model; 

 public class Circle extends DrawingElement {
	
	
	private float cx;
	private float cy;
	private float radius;
	
	/**
	 * Constructor.
	 * 
	 * @param x is the x coordinate of the center of circle
	 * @param y is the y coordinate of the center of circle
	 * @param r is the radius of circle
	 */ 	
	public Circle( float x, float y, float r ) {
		super();
		this.cx = x;
		this.cy = y;
		this.radius = r;
	}
	
	/**
	 * Constructor.
	 * @param p is the center of circle
	 * @param r is the radius of circle
	 */ 	
	public Circle( Point p, float r ) {
		super();
		this.cx = p.getX();
		this.cy = p.getY();
		this.radius = r;
	}
	
	/**
	 * Set (or update) the center point of the circle.
	 * @param x is the new x coordinate of the center point.
	 * @param y is the new y coordinate of the center point.
	 */ 
	public void setCenter( float x, float y ) {
		this.cx = x;
		this.cy = y;
	}
	
	/**
	 * Set (or update) the center point of the circle.
	 * @param p is the new the center point.
	 */ 
	public void setCenter( Point p ) {
		this.cx = p.getX();
		this.cy = p.getY();
	}
	
	/**
	 * Set (or update) the radius the circle.
	 * @param r is the new radius.
	 */ 
	public void setRadius( float r ) {
		this.radius = r;
	}
	
	
	/* *
	 * Return with the coordinates center point contained in an array.
	 * @return the coordinatex of the center points
	 * 
	public float[] getCenter() {
		float[] c = new float[2];
		c[0] = this.cx;
		c[1] = this.cy;
		
		return c;
	}
	*/ 
	
	/**
	 * Return with the center point of the circle.
	 * @return the new the center point.
	 */ 
	public Point getCenter() {
		return new Point(this.cx, this.cy);
	}
	
	/**
	 * Return with the radius the circle.
	 * @return r is the new radius.
	 */ 
	public float getRadius() {
		return this.radius;
	}
	
	
 }
 
