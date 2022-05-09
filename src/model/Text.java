/**
 * Class Text
 * Author: Gabor Nemeth
 * Date: 2012-07-09
 * 
 * 
 * Implementation of a 2D text class.
 * 
 */ 
package model;


public class Text extends DrawingElement {
	
	private String text;
	private String font_family;
	private int size;
	private Point startPoint;
	
	/**
	 * Constructor.
	 * 
	 * @param text is the content 
	 * @param font is the font family
	 * @param size is the font size
	 */ 
	public Text(String text, String font, int size) {
		super();
		this.text = text; 
		this.font_family = font;
		this.size = size;
		
	}
	
	
	/**
	 * Returns with the text content.
	 * @return the text content
	 */ 
	public String getText() {
		return this.text;
	}
	
	
	/**
	 * Returns with the font family.
	 * @return the font family
	 */ 
	public String getFontFamily() {
		return this.font_family;
	}
	
	/**
	 * Returns with the font size.
	 * @return the font size
	 */ 
	public int getFontSize() {
		return this.size;
	}
	
	
	/**
	 * Set the start point of the baseline. 
	 * @param p is the left endpoint of the baseline.
	 **/
	public void setStartPoint(Point p) {
		this.startPoint = p;
	}
	
	/**
	 * Returns the start point of the baseline. 
	 * @return is the left endpoint of the baseline.
	 **/
	public Point getStartPoint() {
		return this.startPoint;
	}
	
}
