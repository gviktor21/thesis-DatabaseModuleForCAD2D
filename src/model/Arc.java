/**
 * Class Arc
 * Author: Gabor Nemeth
 * Date: 2012-07-09
 * 
 * 
 * Implementation of a 2D arc class.
 * 
 */ 
package model;

public class Arc extends DrawingElement {
	
	/* angles are given in degrees */
	
	private float cx;
	private float cy;
	private float radius;
	private float startAngle;
	private float endAngle;
	private Point end1;  /* endpoint at the starting angle */
	private Point end2;  /* endpoint at the endAngle */
	private Point midpoint;    /* the midpoint (it is not the center!!!) */
	
	
	/**
	 * Constructor.
	 * 
	 * The parameters startAngle and endAngle must be given in degrees and they will be converted between 0-360 degrees. 
	 * 
	 * @param x is the x coordinate of the center of arc
	 * @param y is the y coordinate of the center of arc
	 * @param r is the radius of arc
	 * @param startAngle is the angle where the arc is started to draw.
	 * @param endAngle is the angle where the is end to draw. 
	 */
	public Arc( float x, float y, float r, float startAngle, float endAngle ) {
		super();
		this.cx = x;
		this.cy = y;
		this.radius = r;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		
		this.setEndpoints();
		
	} 
	
	
	/**
	 * Constructor.
	 * 
	 * The parameters startAngle and endAngle must be given in degrees and they will be converted between 0-360 degrees. 
	 * 
	 * @param p is the center point of the arc
	 * @param r is the radius of arc
	 * @param startAngle is the angle where the arc is started to draw.
	 * @param endAngle is the angle where the is end to draw. 
	 */
	public Arc( Point p, float r, float startAngle, float endAngle ) {
		this(p.getX(), p.getY(), r, startAngle, endAngle);
	}
	
	/**
	 * Set (or update) the center point of the arc.
	 * @param x is the new x coordinate of the center point.
	 * @param y is the new y coordinate of the center point.
	 */ 
	public void setCenter( float x, float y ) {
		this.cx = x;
		this.cy = y;
	}
	
	/**
	 * Set (or update) the center point of the arc.
	 * @param p is the new the center point.
	 */ 
	public void setCenter( Point p ) {
		this.cx = p.getX();
		this.cy = p.getY();
	}
	
	
	/* *
	 * Return with the coordinates center point contained in an array.
	 * @return the coordinatex of the center points
	 */
	/*  
	public float[] getCenter() {
		float[] c = new float[2];
		c[0] = this.cx;
		c[1] = this.cy;
		
		return c;
	}
	*/ 
	
	/**
	 * Return with the center point of the arc.
	 * @return the new the center point.
	 */ 
	public Point getCenter() {
		return new Point( this.cx, this.cy );
	}
	
	/**
	 * Set (or update) the radius.
	 * @param r is the new value of the radius
	 */ 
	public void setRadius(float r) {
		this.radius = r;
		this.setEndpoints();
	}
	
	
	/**
	 * Return with the radius.
	 * @return the radius.
	 */ 
	public float getRadius() {
		return this.radius;
	}
	
	/**
	 * Return with the starting point.
	 * @return the starting point.
	 */ 
	public Point getStartingPoint() {
		return this.end1;
	}
	
	/**
	 * Return with the ending point.
	 * @return the ending point.
	 */ 
	public Point getEndingPoint() {
		return this.end2;
	}
	
	/**
	 * Return with the midpoint. The midpoint is on the curve and it not equal with the center point.
	 * @return the midpoint.
	 */ 
	public Point getMidpoint() {
		return this.midpoint;
	}
	
	
	/**
	 * Set (or update) the starting angle.
	 * @param angle is the new value of starting angle
	 */ 
	public void setStartAngle(float angle) {
		this.startAngle = angle;
		this.setEndpoints();
	}
	
	/**
	 * Set (or update) the ending angle.
	 * @param angle is the new value of ending angle
	 */ 
	public void setEndAngle(float angle) {
		this.endAngle = angle;
		this.setEndpoints();
	}
	
	/**
	 * Return with the value of the starting angle.
	 * @return the starting angle
	 */ 
	public float getStartAngle() {
		return this.startAngle;
	}
	
	/**
	 * Return with the value of the ending angle.
	 * @return the ending angle
	 */ 
	public float getEndAngle() {
		return this.endAngle;
	}
	
	
	
	/**
	 * Calculate and initialize the endpoints and the midpoint.
	 */ 
	private void setEndpoints() {
		
		while ( 0.0 > this.startAngle ) {
			this.startAngle += 360.0F;
		}
		while ( this.startAngle > 360.0 ) {
			this.startAngle -= 360.0F;
		}
		while ( 0.0 > this.endAngle ) {
			this.endAngle += 360.0F;
		}
		while ( this.endAngle > 360.0 ) {
			this.endAngle -= 360.0F;
		}
		
		while ( this.endAngle < this.startAngle ) {
			this.endAngle += 360.0F;
		} 
		
		
		double startAngleRad = (double) (this.startAngle * Math.PI / 180.0);
		double endAngleRad = (double) (this.endAngle * Math.PI / 180.0);
		
		double cosSAR = Math.cos(startAngleRad);
		double sinSAR = Math.sin(startAngleRad);
		double cosEAR = Math.cos(endAngleRad);
		double sinEAR = Math.sin(endAngleRad);
		
		this.end1 = new Point( Double.valueOf(this.radius * cosSAR).floatValue() + this.cx, Double.valueOf(this.radius * sinSAR).floatValue() + this.cy );
		this.end2 = new Point( Double.valueOf(this.radius * cosEAR).floatValue() + this.cx, Double.valueOf(this.radius * sinEAR).floatValue() + this.cy );
		
		double midAngleRad = (startAngleRad + endAngleRad) / 2.0;
		double cosMAR = Math.cos(midAngleRad);
		double sinMAR = Math.sin(midAngleRad);
		this.midpoint = new Point( Double.valueOf(this.radius * cosMAR).floatValue() + this.cx , Double.valueOf(this.radius * sinMAR).floatValue() + this.cy );
		
	}
}
