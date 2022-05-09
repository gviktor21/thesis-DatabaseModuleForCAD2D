/**
 * Drawing controller
 * Authors: Gabor Nemeth
 * 		   Zsuzsanna Magyar
 * Date: 2012-07-09
 * 
 * 
 * Drawing controller.
 * 
 */ 
package control;

import model.*;
import view.*;
import view.event.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.*;


/**
 * DrawingController is a basic class to draw primitives in different ways to the canvas. 
 * A DrawingController has three states: 
 * 	1.) Inactive: if the controller is not used
 *  2.) Ready: if the we want to draw
 *  3.) Input: if we want to point to some input in the elements or snapping point in the canvas, and we collect these input parameters to draw
 * 
 * If all of the necessary input to draw is given, then we can create a new instance of a DrawingElement (geometric primitive) and add to 
 * the canvas. 
 * 
 * We can determine what we want to draw by options. 
 * 
 */ 
public class DrawingController extends CanvasController implements Snapping, MouseListener, ActionListener, OptionEventListener {
	
	
	public static final int OPTION_LINE_DRAW = 1; /** option for drawing lines **/
	public static final int OPTION_CIRCLE_DRAW = 2; /** option for drawing circles **/
	public static final int OPTION_ARC_DRAW = 3; /** option for drawing arc **/
	public static final int OPTION_POLYGON_DRAW = 4; /** option for drawing polygon **/
	public static final int OPTION_TEXT_DRAW = 5; /** option for drawing text **/
	
	
	
	protected DrawingElement primitive;
	
	
	protected LineDrawer lineDrawer;
	
	
	protected int option; /* this is the drawing option */
	protected int method; /* this is show how to draw */
	protected int snap;   /* hold the snapping option */
	
	
	
	protected int mouseX;
	protected int mouseY;
	protected float absX;
	protected float absY;
	protected float relX;
	protected float relY;
	
	/**
	 * Constructor. 
	 * 
	 * @param canvas is the canvas, where we will draw.
	 * 
	 **/ 
	public DrawingController(DrawingCanvas canvas) {
		super(canvas);
		//this.canvas = canvas;
		//this.sheet = null;
		this.lineDrawer = new LineDrawer();
		//this.inputs = new ArrayList<Object>();
		//this.state = DrawingController.STATE_READY;
		this.canvas.addMouseListener(this);
	}
	//Viktor

		
	
	

	/**
	 *  Setting the option of the Controller. 
	 *  It may be LINE_DRAW, CIRCLE_DRAW, ARC_DRAW, POLYGON_DRAW, and TEXT_DRAW.
	 **/
	public void setOption(int option) {
		this.option = option;
		this.state = CanvasController.STATE_READY; /** if the option is changed, then the state must be READY */
	}
	
	/**
	 *  Return the option of the Controller. 
	 *  It may be LINE_DRAW, CIRCLE_DRAW, ARC_DRAW, POLYGON_DRAW, and TEXT_DRAW.
	 **/
	public int getOption() {
		return this.option;
	}
	
	/**
	 *  Setting the method of the drawing. 
	 *  
	 **/
	public void setMethod(int method) {
		this.method = method;
		//this.state = CanvasController.STATE_READY; /** if the option is changed, then the state must be READY */
	}
	/**
	 *  Return the method of the drawing. 
	 * 
	 **/
	public int getMethod() {
		return this.method;
	}
	
	/**
	 *  Setting the snapping option. 
	 *  
	 **/
	public void setSnap(int snap) {
		this.snap = snap;
		
	}
	
	/**
	 *  Returns the snapping option. 
	 * 
	 **/
	public int getSnap() {
		return this.snap;
	}
	
	 
	 /**************/
	/**
	 * Implementing the Snapping interface method.
	 */
	public void receiveCursorPoint() {
		//float resMM = this.canvas.getResolutionMM();
		//float xMM = (float) mouseX / resMM;
		//float yMM = (float) mouseY / resMM;
		////this.inputs.add(new model.Point(xMM, yMM));
		//this.addInput(new model.Point(xMM, yMM));
		
		float resMM = this.canvas.getResolutionMM();
		float xMM = (float) mouseX / resMM /(this.canvas.getZoom()*this.canvas.getZoom());
		float yMM = (float) mouseY / resMM /(this.canvas.getZoom()*this.canvas.getZoom());
		//this.inputs.add(new model.Point(xMM, yMM));
		this.addInput(new model.Point(xMM, yMM));
		
		
	}
	
	/**
	 * Implementing the Snapping interface method.
	 */
	public void receiveEndpoint() {
		/* * INPLEMENTÁLNI KELL * */
		/* * Magyar Zsuzsanna * */
	}
	
	/**
	 * Implementing the Snapping interface method.
	 */
	public void receiveCenter() {
		/* * INPLEMENTÁLNI KELL * */
		/* * Magyar Zsuzsanna * */
	}
	
	/**
	 * Implementing the Snapping interface method.
	 */
	public void receiveMidpoint() {
		/* * INPLEMENTÁLNI KELL * */
		/* * Magyar Zsuzsanna * */
	}
	
	/**
	 * Implementing the Snapping interface method.
	 */
	public void receiveAbsolutePoint() {
		/* * INPLEMENTÁLNI KELL **/
		/* * használni kell az absX és absY változókat (az actionPerformed()-ben kell értéket adni neki) * */
		/* * Magyar Zsuzsanna * */
	}
	
	/**
	 * Implementing the Snapping interface method.
	 */
	public void receiveRelativePoint() {
		/* * INPLEMENTÁLNI KELL * */
		/* * használni kell az relX és relY változókat (az actionPerformed()-ben kell értéket adni neki) * */
		/* * Magyar Zsuzsanna * */
	}
	
	
	
	/**
	 * Implementing the Snapping interface method.
	 */
	public void receivePointBySnap() {
		switch (snap) {
			case Snapping.SNAP_CURSOR : this.receiveCursorPoint();
			break;
			case Snapping.SNAP_ENDPOINT : this.receiveEndpoint();
			break;
			case Snapping.SNAP_CENTER : this.receiveCenter();
			break;
			case Snapping.SNAP_MIDPOINT : this.receiveMidpoint();
			break;
			case Snapping.SNAP_ABSOLUTE : this.receiveAbsolutePoint();
			break;
			case Snapping.SNAP_RELATIVE : this.receiveRelativePoint();
			break;
		}
	}
	
	/***************/
	/** Implementing mouse listener method **/
	public void mouseClicked(MouseEvent e) {
		/* * IMPLEMENTÁLNI KELL (ha szükséges) **/
		/* * Magyar Zsuzsanna **/
	}
	
	/** Implementing mouse listener method **/
	public void mouseEntered(MouseEvent e) {
		/* * IMPLEMENTÁLNI KELL (ha szükséges) * */
		/* * Magyar Zsuzsanna * */
	}
	
	/** Implementing mouse listener method **/
	public void mouseExited(MouseEvent e) {
		/* * IMPLEMENTÁLNI KELL (ha szükséges) * */
		/* * Magyar Zsuzsanna * */
	}
	
	/** Implementing mouse listener method **/
	public void mousePressed(MouseEvent e) {
		/* * IMPLEMENTÁLNI KELL (ha szükséges) * */
		/* * Magyar Zsuzsanna * */
	}
	
	/** Implementing mouse listener method **/
	public void mouseReleased(MouseEvent e) {
		if(isActive == true){
			/* * IMPLEMENTÁLNI KELL (ha szükséges) * */
			/* * Magyar Zsuzsanna * */
			this.mouseX = e.getX() * (int) (this.canvas.getZoom() + 0.5F);
			this.mouseY = e.getY() * (int) (this.canvas.getZoom() + 0.5F);
			
			if ( this.option == DrawingController.OPTION_LINE_DRAW ) {
				if ( method == LineDrawer.METHOD_LINE_TWO_POINT ) { 
					if ( this.state == CanvasController.STATE_READY ) {
						this.receivePointBySnap();
						this.state = CanvasController.STATE_INPUT;
						System.out.println("STATE INPUT");
					} else if ( this.state == CanvasController.STATE_INPUT ) {
						this.receivePointBySnap();
						
						
						if (this.sheet != null) {
							Line line = LineDrawer.createLineTwoPoints((model.Point) inputs.get(0), (model.Point) inputs.get(1));
							this.sheet.addDrawingElement(line);
						}
						this.canvas.repaint();
						this.inputs.clear();
						
						this.state = CanvasController.STATE_READY;
						System.out.println("STATE READY");
					}				
				}
				
				/* ***************************************** */
				/*  IDE KELL A TÖBBI VONALRAJZOLÁST MEGÍRNI  */
				/*           Magyar Zsuzsanna                */
				/* ***************************************** */
			}
			
			/* ********************************************* */
			/*  IDE KELL A TÖBBI PRIMITÍV RAJZOLÁST MEGÍRNI  */
			/*               Magyar Zsuzsanna                */
			/* ********************************************* */
		}
	}
	
	
	/****************/
	/** Implementing action listener **/
	public void actionPerformed(ActionEvent e) {
		/** this may be useful if the we want to change the option **/
		/** IMPLEMENTÁLNI KELL **/
		/** Magyar Zsuzsanna **/
	}
	
	
	/**
	 * Implementing the OptionEventListener 
	 **/ 
	public void optionChanged(OptionEvent e) {
		if ( e.getType() == OptionEvent.EVENT_TYPE_OPTION ) {
			this.setOption( e.getOption() );
		}
		if ( e.getType() == OptionEvent.EVENT_TYPE_METHOD ) {
			this.setMethod( e.getOption() );
		}
	}
	
}
