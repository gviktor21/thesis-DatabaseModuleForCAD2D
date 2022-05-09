/**
 * Class LineDrawer
 * Author: Gabor Nemeth
 * Date: 2012-07-09
 * 
 * 
 * Line drawer is a class to create lines in different ways. Methods are 
 * static that need the input parameters. Constant variables are to 
 * set the option of drawing.
 * 
 * 
 */ 
 package model;


public class LineDrawer {
	
	
	public static final int METHOD_LINE_TWO_POINT = 1;
	
	public static Line createLineTwoPoints(Point p1, Point p2) {
		return new Line(p1, p2);
	}
}

