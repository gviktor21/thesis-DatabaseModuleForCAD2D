/**
 * Class DrawingPanel
 * Author: Gabor Nemeth
 * 		   Julia Boroka Nemeth
 * Date: 2012-07-09
 * 
 * 
 * The drawing panel.
 * 
 */ 
 package view;


 import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferStrategy;

import model.*;
import control.*;

public class DrawingCanvas extends Canvas {
	
	

	public static final String MODE_DRAWING="MODE_DRAWING";
	public static final String MODE_SELECTION="MODE_SELECTION";
	public static final String MODE_EDITING = "MODE_EDITING";
	
	//private TreeMap<String,DrawingElement> elements; /* a container for drawing elements, the elements are sorted by their ID */
	
	private float zoom;
	private int offsetX;
	private int offsetY;
	private float resolution;


	private int pageWidthMM;
	private int pageHeightMM;
	
	private float resolutionMM;
	
	private Color backgroundColor;
	
	private int canvasWidth;
	private int canvasHeight;
	private int borderMM;
	private int borderPixel;
	private Sheet sheet;	
		
	private Graphics2D g2d;
	
	public DrawingCanvas() {
		this.zoom = 1.0F;
		this.offsetX = 0;
		this.offsetY = 0;
		this.resolution = 600; /* the default resolution is 600 dpi */
		this.resolutionMM = this.resolution / Sheet.INCH_TO_MM;
		
		this.pageHeightMM = 210;  /* default is an A4 page */
		this.pageWidthMM = 297;
		
		this.borderMM = 5;
		this.borderPixel = Float.valueOf(this.borderMM * this.resolutionMM).intValue();
		
		this.backgroundColor = Color.WHITE;
		
		this.canvasWidth = Float.valueOf(this.pageWidthMM * this.resolutionMM).intValue();
		this.canvasHeight = Float.valueOf(this.pageHeightMM * this.resolutionMM).intValue();
		
		
		this.setSize( this.canvasWidth, this.canvasHeight );
		
		this.sheet = null;
		
		//elements = new TreeMap<String,DrawingElement>();
	}
	
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
		this.pageWidthMM = this.sheet.getSheetWidth();
		this.pageHeightMM = this.sheet.getSheetHeight();
		
	}
	
	public void paint(Graphics g) {
		this.g2d = (Graphics2D) g;
		/* clear the background */ 
		this.g2d.setColor(this.backgroundColor);
		this.g2d.fillRect( 0,0, this.canvasWidth, this.canvasHeight );
		
		/* drawing border */
		this.g2d.setColor(Color.BLACK);
		this.g2d.setStroke(new BasicStroke(this.zoom * 0.5F * this.resolutionMM));
		
		//System.out.println( "Border pixel = "+borderPixel );
		this.g2d.drawRect(this.borderPixel, this.borderPixel, this.canvasWidth-(2*this.borderPixel), this.canvasHeight-(2*this.borderPixel));
		if (this.sheet != null) {
			if (this.sheet.getDrawingElements().size() > 0) {
				ArrayList coll = this.sheet.getDrawingElements();
				
				Iterator it = coll.iterator();
				
				while ( it.hasNext() ) {
					DrawingElement element = (DrawingElement) it.next();
					/* setting drawing attributes  */
					setDrawingAttributes(element);

					/* drawing primitives */
					drawingPrimitives(element,g2d);
				}
			}
		}
	
	}
	public void setDrawingAttributes(DrawingElement element) {
		/*** IMPLEMENTĂ�LNI KELL ***/
		/*** NĂ©meth JĂşlia   ***/
	}
	
	public void drawingPrimitives(DrawingElement element, Graphics2D g2d2) {
		
		float resMMZoom = resolutionMM * this.zoom;
		g2d2.setColor(element.getColor());
		if ( element instanceof Line ) {
			//int px1 = Float.valueOf( ((Line) element).getEndpoint1().getX() * resMMZoom ).intValue();
			//int py1 = Float.valueOf( ((Line) element).getEndpoint1().getY() * resMMZoom ).intValue();
			//int px2 = Float.valueOf( ((Line) element).getEndpoint2().getX() * resMMZoom ).intValue();
			//int py2 = Float.valueOf( ((Line) element).getEndpoint2().getY() * resMMZoom ).intValue();
			
			int px1 = Math.round( ((Line) element).getEndpoint1().getX() * resMMZoom );
			int py1 = Math.round( ((Line) element).getEndpoint1().getY() * resMMZoom );
			int px2 = Math.round( ((Line) element).getEndpoint2().getX() * resMMZoom );
			int py2 = Math.round( ((Line) element).getEndpoint2().getY() * resMMZoom );
			
			
			this.g2d.drawLine(px1, py1, px2, py2);
		} else if ( element instanceof Circle ) {
			int cx = Float.valueOf( ((Circle) element).getCenter().getX() * resMMZoom ).intValue();
			int cy = Float.valueOf( ((Circle) element).getCenter().getY() * resMMZoom ).intValue();
			int r = Float.valueOf( ((Circle) element).getRadius() * resMMZoom ).intValue();
			this.g2d.drawOval( cx-r, cy-r, 2*r, 2*r );
		} else if ( element instanceof Arc ) {
			int cx = Float.valueOf( ((Circle) element).getCenter().getX() * resMMZoom ).intValue();
			int cy = Float.valueOf( ((Circle) element).getCenter().getY() * resMMZoom ).intValue();
			int r = Float.valueOf( ((Circle) element).getRadius() * resMMZoom ).intValue();
			int startAngle = Float.valueOf( ((Arc) element).getStartAngle() ).intValue();
			int endAngle = Float.valueOf( ((Arc) element).getEndAngle() ).intValue();
			this.g2d.drawArc(cx-r, cy-r, 2*r, 2*r, startAngle, endAngle-startAngle);
		} else if ( element instanceof model.Polygon ) {
			Line[] lines = ((model.Polygon) element).getEdges();
			for ( int i = 0; i < lines.length; i++ ) {
				int px1 = Float.valueOf( lines[i].getEndpoint1().getX() * resMMZoom ).intValue();
				int py1 = Float.valueOf( lines[i].getEndpoint1().getY() * resMMZoom ).intValue();
				int px2 = Float.valueOf( lines[i].getEndpoint2().getX() * resMMZoom ).intValue();
				int py2 = Float.valueOf( lines[i].getEndpoint2().getY() * resMMZoom ).intValue();
				this.g2d.drawLine(px1, py1, px2, py2);
			}
		} else if ( element instanceof SelectingRectangle ){

			int px1 = Math.round( ((SelectingRectangle) element).getStartpoint().getX() * resMMZoom );
			int py1 = Math.round( ((SelectingRectangle) element).getStartpoint().getY() * resMMZoom );
			int px2 = Math.round( ((SelectingRectangle) element).getEndpoint().getX() * resMMZoom );
			int py2 = Math.round( ((SelectingRectangle) element).getEndpoint().getY() * resMMZoom );
			this.g2d.drawRect(px1,py1, px2-px1, py2-py1);
		}
		
	}
	
	public float getResolutionMM() {
		return this.resolutionMM;
	}
	
	
	private void updateCanvas() {
		this.canvasWidth = Float.valueOf(this.zoom * this.pageWidthMM * this.resolutionMM).intValue();
		this.canvasHeight = Float.valueOf(this.zoom * this.pageHeightMM * this.resolutionMM).intValue();
		this.setSize( this.canvasWidth, this.canvasHeight );
		this.borderPixel = Float.valueOf(this.zoom * this.borderMM * this.resolutionMM).intValue();
	}
	
	public void zoomIn() {
		this.zoom *= 2.0F;
		this.updateCanvas();
		this.repaint();
	}
	
	public void zoomOut() {
		this.zoom /=2.0F;
		this.updateCanvas();
		this.repaint();
	}
	
	public void fullSheetZoom() {
		if ( this.sheet.getSheetWidth() > this.sheet.getSheetHeight() ) {
			this.zoom = (float) (((ScrollPane) this.getParent()).getViewportSize().getWidth() / this.canvasWidth);
		} else {
			this.zoom = (float) (((ScrollPane) this.getParent()).getViewportSize().getHeight() / this.canvasHeight);
		}
		this.updateCanvas();
		this.repaint();
	}
	
	public void noZoom() {
		this.zoom = 1.0F;
		this.updateCanvas();
		this.repaint();
	}
	
	public float getZoom() {
		return this.zoom;
	}
	class PopUpMenu extends JPopupMenu {
	    JMenuItem drawing_element_Item;
	    public PopUpMenu(){
	    	drawing_element_Item = new JMenuItem("Define Drawing element");
	        add(drawing_element_Item);
	    }
	}
	
}
