/**
 * Class Polygon
 * Author: Gabor Nemeth
 * Date: 2012-07-09
 * 
 * 
 * Implementation of a 2D polygon class.
 * 
 */ 
 package model;

 public class Polygon extends DrawingElement {
	 
	 private int n; /* number of vertices */
	 private float cx; /* center of the outer circle */
	 private float cy; /* center of the outer circle */
	 private float radius;  /* the outer circle radius */
	 private Point center;
	 
	 private Line[] edges;
	 private Point[] vertices;
	 
	 /**
	  * Constructor. 
	  * 
	  * @param n is the number of vertices
	  * @param x is the x coordinate of the center point
	  * @param y is the y coordinate of the center point
	  * @param r is the radius of the outer circle
	  */ 
	 public Polygon( int n, float x, float y, float r ) {
		 super();
		 this.n = n;
		 this.cx = x;
		 this.cy = y;
		 this.radius = r;
		 this.center = new Point(this.cx, this.cy);
		 
		 this.vertices = new Point[n];
		 this.edges = new Line[n];
		 
		 this.setVerticesAndLines();
		 
	 }
	 
	 
	 /**
	  * Calculate the location of the vertices and set the lines. 
	  * It will be called if the polygon is updated or created. 
	  */ 
	 private void setVerticesAndLines() {
		 double alpha = 360.0 / this.n;
		 double alpharad = alpha * Math.PI / 180.0;
		 double pi_per_2 = Math.PI / 2.0;
		 
		 for (int i=0; i < this.n; i++) {
			 float px = this.cx + this.radius * Double.valueOf(Math.cos(i * alpharad + pi_per_2)).floatValue();
			 float py = this.cy + this.radius * Double.valueOf(Math.sin(i * alpharad + pi_per_2)).floatValue();
			 
			 vertices[i] = new Point(px,py);
		 }
		 for (int i=1; i < this.n; i++) {
			 edges[i-1] = new Line(vertices[i-1], vertices[i]);
		 } 
		 edges[this.n-1] = new Line(vertices[this.n-1], vertices[0]);
	 }
	 
	 
	 /**
	  * Set the center of the polygon.
	  * @param x is the x coordinate of the new center
	  * @param y is the y coordinate of the new center
	  * 
	  */ 
	 public void setCenter( float x, float y ) {
		 this.cx = x;
		 this.cy = y;
		 this.center.setX(x);
		 this.center.setY(y);
		 
		 this.setVerticesAndLines();
	 }
	 
	 /**
	  * Set the radius of the polygon.
	  * @param r is the new value of the radius.
	  * 
	  */ 
	 public void setRadius( float r) {
		 this.radius = r;
		 
		 this.setVerticesAndLines();
	 }
	 
	 /**
	  * Returns with the center point of the polygon.
	  * @return the center point
	  */ 
	  public Point getCenter() {
		  return this.center;
	  }
	  
	  /* *
	  * Returns with the center point of the polygon.
	  * @return the an array of the center point coordinates
	  */ 
	  /*
	  public float[] getCenter() {
		  float c[] = new float[2];
		  c[0] = this.cx;
		  c[1] = this.cy;
		  return c;
	  }
	  */ 
	 
	  /**
	  * Returns with the radius of the polygon.
	  * @return the radius
	  */ 
	  public float getRadius() {
		  return this.radius;
	  }
	 
	  /**
	  * Returns with the vertices array of the polygon.
	  * @return the vertices
	  */ 
	  public Point[] getVertices() {
		  return this.vertices;
	  }
	  
	  /**
	  * Returns with the edges array of the polygon.
	  * @return the edges
	  */ 
	  public Line[] getEdges() {
		  return this.edges;
	  }
	 
 }
