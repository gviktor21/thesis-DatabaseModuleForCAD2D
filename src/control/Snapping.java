/**
 * Interface Snapping
 * Authors: Gabor Nemeth
 * Date: 2012-07-09
 * 
 * 
 * Snapping interface.
 * 
 */ 
 package control;

public interface Snapping {
	
	public static final int SNAP_CURSOR = 1;
	public static final int SNAP_ENDPOINT = 2;
	public static final int SNAP_CENTER = 3;
	public static final int SNAP_MIDPOINT = 4;
	public static final int SNAP_ABSOLUTE = 5;
	public static final int SNAP_RELATIVE = 6;
	
	
	/**
	 * It handles the most easiest snapping when the user 
	 * clicked to a point in the canvas. The received cursor coordinates
	 * will be registered.
	 */
	public void receiveCursorPoint();
	
	/**
	 * It handles the snapping when the user 
	 * clicked to a point in the canvas and it is near to an endpoint. 
	 * One has to find the nearest endpoint to the received coordinates.
	 */
	public void receiveEndpoint();
	
	
	/**
	 * It handles the snapping when the user 
	 * clicked to a point in the canvas and it is near to a center point.
	 * Only circle and arc objects have center point. 
	 * One has to find the nearest center point to the received coordinates.
	 */
	public void receiveCenter();
	
	
	/**
	 * It handles the snapping when the user 
	 * clicked to a point in the canvas and it is near to a midpoint.
	 * Only line and arc objects have midpoints. In the case of arcs, the midpoint does not equal to the 
	 * center point. Midpoint is always located in the curve. 
	 * One has to find the nearest center point to the received coordinates.
	 */
	public void receiveMidpoint();
	
	/**
	 * In this case of snapping the user give a point by its absolute 
	 * coordinates. The given point has to be registered.  
	 */
	public void receiveAbsolutePoint();
	
	
	/**
	 * In this case of snapping the user give a point by its coordinates 
	 * relative to a point. The given point has to be registered.  
	 */
	public void receiveRelativePoint();
	
	/**
	 * Here will be called the specific snapping method. 
	 */ 
	public void receivePointBySnap();
	
}
