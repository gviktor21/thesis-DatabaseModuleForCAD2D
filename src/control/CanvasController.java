/**
 * Basic controller
 * Authors: Gabor Nemeth
 * Date: 2012-07-09
 * 
 * 
 * Drawing controller.
 * 
 */ 
package control;

import model.*;
import view.*;
import java.util.*;

public class CanvasController {
	
	
	public static final int STATE_INPUT = 1;  /** if we are waiting for inputs (getting input in progress) **/
	public static final int STATE_READY  = 0;  /** if we are ready **/
	public static final int STATE_INACTIVE = -1; /** if the controller is not used **/
	
	
	protected DrawingCanvas canvas;
	protected Sheet sheet;
	protected ArrayList<Object> inputs;
	protected int state;  /* this is the state of the controller */
	protected Boolean isActive;//viktor
	
	
	public CanvasController(DrawingCanvas canvas) {
		this.sheet = null;
		this.canvas = canvas;
		this.inputs = new ArrayList<Object>();
		this.state = CanvasController.STATE_INACTIVE;
		isActive=false;
	}
	
	
	public ArrayList<Object> getInputs() {
		return inputs;
	}


	public void setInputs(ArrayList<Object> inputs) {
		this.inputs = inputs;
	}


	/**
	 * The canvas must be refresh if a sheet content changed.
	 * 
	 */ 					
	public void refreshCanvas() {
		if ( this.sheet != null ) {
			//this.sheet.addDrawingElement(this.primitive);
			this.canvas.repaint();
		}
	}
	
	
	/**
	 *  Setting the state of the Controller. 
	 *  It may be READY, INACTIVE, and INPUT.
	 **/
	public void setState(int state) {
		this.state = state;
	}
	
	/**
	 *  Return the state of the Controller. 
	 *  It may be READY, INACTIVE, and INPUT.
	 **/
	public int getState() {
		return this.state;
	}
	
	/**
	 * Set the sheet for the controller. Basically, there is no sheet 
	 * set in it. If a sheet will be open, then this method has to be call. 
	 * 
	 * @param s is the sheet to be set.
	 */ 
	public void setSheet( Sheet s) {
		this.sheet = s;
		this.canvas.setSheet(this.sheet);
	}
	
	/**
	 * Return the sheet which is set for the controller. 
	 * @return the sheet which is set for the controller. 
	 */
	public Sheet getSheet() {
		return this.sheet;
	}
	
	public void addInput(Object input) {
		this.inputs.add(input);
	}
	//viktor
	public void disableController(){
		this.isActive=false;
	}
	public void enableController(){
		this.isActive=true;
	}
}

