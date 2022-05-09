/**
 * Class Sheet
 * Author: Gabor Nemeth
 * Date: 2012-07-09
 * 
 * 
 * The sheet. This is the model of the blueprint. It contains the drawing primitives. 
 * 
 * 
 */ 
 package model;
 
 import java.util.*;
 
 public class Sheet {
	 
	public static boolean PAGE_HORIZONTAL;
	public static boolean PAGE_VERTICAL;
	public static float INCH_TO_MM = 25.4F;
	
	
	
	private ArrayList<DrawingElement> elements; /* a container for geometric primitives, the elements are sorted by their ID */
	
	private int pageWidthMM;
	private int pageHeightMM;
	private int borderMM;
	
	public Sheet() {
		this.pageWidthMM = 297;
		this.pageHeightMM = 210;
		this.borderMM = 5;
		
		elements = new ArrayList<DrawingElement>();
	}
	
	public void setSize(int widthMM, int heightMM) {
		this.pageWidthMM = widthMM;
		this.pageHeightMM = heightMM;
	}
	
	public int getSheetWidth() {
		return this.pageWidthMM;
	}
	
	public int getSheetHeight() {
		return this.pageHeightMM;
	}
	
	public void setBorder(int border) {
		this.borderMM = border;
	}
	
	public int getBorder() {
		return this.borderMM;
	}
	
	public ArrayList<DrawingElement> getDrawingElements() {
		return this.elements;
	}
	
	public void addDrawingElement(DrawingElement e) {
		System.out.println("e = " + e);
		System.out.println("elements = " + this.elements);
		this.elements.add(e);
	}
	
	
 }
