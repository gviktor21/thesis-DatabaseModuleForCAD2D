/**
 * Class Line
 * Author: Gabor Nemeth
 * Date: 2012-07-09
 * 
 * 
 * Implementation of a 2D line class.
 * 
 */ 
package model;


public class Line extends DrawingElement {
	
	/*
	 * The points (x1,y1) and (x2,y2) are the two endpoints of the line.
	 * There is no ordering relation between (x1,y1) and (x2,y2) points.
	 * The point (cx,cy) is the midpoint of the line. It will be useful 
	 * for snapping.
	 */
	
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	private float cx;
	private float cy;
	private  Point midpoint;
	private Point end1;
	private Point end2;
	
	/**
	 * Constructor.
	 * @param x1_ is the x coordinate of the first endpoint.
	 * @param y1_ is the y coordinate of the first endpoint.
	 * @param x2_ is the x coordinate of the second endpoint.
	 * @param y2_ is the y coordinate of the second endpoint.
	 */ 
	public Line( float x1_, float y1_, float x2_, float y2_ ) {
		super();
		this.x1 = x1_;
		this.y1 = y1_;
		this.x2 = x2_;
		this.y2 = y2_;
		
		this.end1 = new Point(this.x1, this.y1);
		this.end2 = new Point(this.x2, this.y2);
		
		this.cx = (this.x1 + this.x2) / 2.0F;
		this.cy = (this.y1 + this.y2) / 2.0F;
		this.midpoint = new Point(cx,cy);
		
	}
	
	/**
	 * Constructor.
	 * @param p1 is the first endpoint.
	 * @param p2 is the second endpoint.
	 */ 
	public Line( Point p1, Point p2 ) {
		super();
		this.x1 = p1.getX();
		this.y1 = p1.getY();
		this.x2 = p2.getX();
		this.y2 = p2.getY();
		
		this.end1 = new Point(this.x1, this.y1);
		this.end2 = new Point(this.x2, this.y2);
		
		this.cx = (this.x1 + this.x2) / 2.0F;
		this.cy = (this.y1 + this.y2) / 2.0F;
		this.midpoint = new Point(cx,cy);
	}

	/**
	 * Set (or update) the first endpoint of the line.
	 * @param x is the x coordinate of the first endpoint.
	 * @param x is the y coordinate of the first endpoint.
	 */ 
	public void setEndpoint1(float x, float y) {
		this.x1 = x;
		this.y1 = y;
		
		this.end1.setX(x);
		this.end1.setY(y);
		
		this.cx = (this.x1 + this.x2) / 2.0F;
		this.cy = (this.y1 + this.y2) / 2.0F;
		this.midpoint.setX(cx);
		this.midpoint.setY(cy);
	}
	
	/**
	 * Set (or update) the second endpoint of the line.
	 * @param x is the x coordinate of the second endpoint.
	 * @param x is the y coordinate of the second endpoint.
	 */ 
	public void setEndpoint2(float x, float y) {
		this.x2 = x;
		this.y2 = y;
		
		this.end2.setX(x);
		this.end2.setY(y);
		
		this.cx = (this.x1 + this.x2) / 2.0F;
		this.cy = (this.y1 + this.y2) / 2.0F;
		this.midpoint.setX(cx);
		this.midpoint.setY(cy);
	}
	
	
	/**
	 * Set (or update) the first endpoint of the line.
	 * @param p is the point corresponding to the first endpoint.
	 * 
	 */ 
	public void setEndpoint1( Point p ) {
		this.x1 = p.getX();
		this.y1 = p.getY();
		
		this.end1.setX(this.x1);
		this.end1.setY(this.y1);
		
		this.cx = (this.x1 + this.x2) / 2.0F;
		this.cy = (this.y1 + this.y2) / 2.0F;
		this.midpoint.setX(cx);
		this.midpoint.setY(cy);
	}
	
	/**
	 * Set (or update) the second endpoint of the line.
	 * @param  p is the point corresponding to the second endpoint.
	 * 
	 */ 
	public void setEndpoint2( Point p ) {
		this.x2 = p.getX();
		this.y2 = p.getY();
		
		this.end1.setX(this.x2);
		this.end1.setY(this.y2);
		
		this.cx = (this.x1 + this.x2) / 2.0F;
		this.cy = (this.y1 + this.y2) / 2.0F;
		this.midpoint.setX(cx);
		this.midpoint.setY(cy);
	}
	
	public Boolean isOnLine(float px, float py){ //Viktor
		float crossproduct = (py - y1) * (x2 - x1) - (px - x1) * (y2- y1);
		//System.out.println(Math.abs(crossproduct)+"Crossproduct: "+crossproduct);
		if (Math.abs(crossproduct) >= 3)  return false;
		
		float dotproduct =   (px - x1) * (x2 - x1) + (py - y1)*(y2 -y1);
		//System.out.println("dotproduct: "+dotproduct);
		if (dotproduct < 0)  return false;
		 
		float squaredlengthba =  (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1);
		//System.out.println(squaredlengthba+"squaredlengthba: "+squaredlengthba);
		if (dotproduct > squaredlengthba) return false;
		
		 return true;	
	}
	
	/*  * 
	 * Return an array of two elements. The first element is the x coordinate of the 
	 * first endpoint, while the second element in the array is corresponding to the
	 * y coordinate of the first endpoint.
	 * @return an array of the x and y coordinates of the first endpoint.
	 */
	/* 
	public float[] getEndpoint1() {
		float[] e = new float[2];
		e[0] = this.x1;
		e[1] = this.y1;
		
		return e;
	}
	*/
	/* *
	 * Return an array of two elements. The first element is the x coordinate of the 
	 * second endpoint, while the second element in the array is corresponding to the
	 * y coordinate of the second endpoint.
	 * @return an array of the x and y coordinates of the second endpoint.
	 */
	/* 
	public float[] getEndpoint2() {
		float[] e = new float[2];
		e[0] = this.x2;
		e[1] = this.y2;
		
		return e;
	}
	*/
	
	/**
	 * Return an instance of Point as the first endpoint of the line.
	 * @return it returns with a Point object
	 */
	public Point getEndpoint1() {
		return this.end1;
	}
	
	/**
	 * Return an instance of Point as the second endpoint of the line.
	 * @return it returns with a Point object
	 */
	public Point getEndpoint2() {
		return this.end2;
	}
	
	
	/**
	 * Return an instance of Point as the midpoint of the line.
	 * @return it returns with a Point object
	 */
	public Point getMidpoint() {
		return this.midpoint;
	}
	
	/* *
	 * Return an array of two elements. The first element is the x coordinate of the 
	 * midpoint, while the second element in the array is corresponding to the
	 * y coordinate of the midpoint.
	 * @return an array of the x and y coordinates of the midpoint.
	 */
	/*  
	public float[] getMidpoint() {
		float[] e = new float[2];
		e[0] = this.cx;
		e[1] = this.cy;
		
		return e;
	}
	*/ 
	
		
}
