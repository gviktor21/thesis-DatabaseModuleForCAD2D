package control;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.lang.model.element.Element;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import model.Circle;
import model.DrawingElement;
import model.Line;
import model.LineSelector;
import model.Point;
import model.SelectingRectangle;
import model.Sheet;
import view.DrawingCanvas;
import view.MainFrame;
import view.SelectPopUpMenu;
import view.SetTypeFrame;
/**
 * 
 * @author Godó Viktor
 * A rajzelem kiválasztását kezeli.
 *A kiválasztás kétféle állapotban állhat az iniciális STATE_DEAFULT, ekkor nincs semmi kijelölve vagy STATE_SELECTED állapotban, ekkor van kijelölt rajzelem a rajzlapon. 
 */
public class SelectController extends CanvasController implements Snapping { 
	public static final int OPTION_LINE_SELECT = 1; /** option for selecting lines **/
	public static final int OPTION_CIRCLE_SELECT = 2; /** option for selecting circles **/
	public static final int OPTION_ARC_SELECT = 3; /** option for selecting arc **/
	public static final int OPTION_POLYGON_SELECT = 4; /** option for selecting polygon **/
	public static final int OPTION_TEXT_SELECT = 5; /** option for selecting text **/
	
	
	public static final int STATE_SELECTED=1;
	public static final int STATE_DEFAULT=0;
	private SelectingRectangle selecting_rect;
	private int current_State;
	protected int mouseX;
	protected int mouseY;
	protected float x1;
	protected float y1;
	protected int snap;
	protected Color color_select; 
	private boolean isHaveType;
	DataBaseController databaseController;
	private LineSelector lineSelector;
	private SelectPopUpMenu menu;
	int refreshtimer;
	public SelectController(DrawingCanvas canvas,DataBaseController databaseController, SelectPopUpMenu selectPopUpMenu) {
		super(canvas);	
		MouseListener mouseListener = new MouseListener();
		// TODO Auto-generated constructor stubt
		this.canvas.addMouseListener(mouseListener);
		this.canvas.addMouseMotionListener(mouseListener);
		current_State=STATE_DEFAULT;
		this.color_select = Color.BLUE;
		isHaveType = true;
		this.selecting_rect  =new SelectingRectangle();
		selecting_rect.setColor(Color.GRAY);
		lineSelector = new LineSelector(LineSelector.LineSelectMode.SELECT_THE_NEWEST);
		refreshtimer=0;
		menu = selectPopUpMenu;
	}
	
	//Calculate the Selected color of the object from Native color or Select_Color
	protected void setSelectedObjectColor(DrawingElement element){
		element.setColor(color_select);
	} 
	/**
	 * Visszaállítja a rajzelemeket az eredeti színükre
	 * @param element
	 */
	protected void ereaseSelectedObjectColor(DrawingElement element){
		element.setColor(Color.BLACK);
	}
	/**
	 * Visszaadja a kiválasztás színét.
	 * @return
	 */
	public Color getColor_select() {
		return color_select;
	}
	/**
	 * vizsgálja, hogy a kijelölt rajzelemek mindegyike rendelkezik-e már típussal
	 */
	private void typeTest(){
		Iterator it = inputs.iterator();
		while ( it.hasNext() ) {
			DrawingElement element = (DrawingElement) it.next();
			if(element.getType() == null){
				isHaveType = false;
				menu.disableParameters();
			}
		}
	}
	
	public void addSelectedItem(Object item){
		inputs.add(item);
	}
	public void addSelectedItems(ArrayList<Object> items){
		inputs.add(items);
	}
	/***
	 * Kikapcsolja a vezérlõt a rajzlap eseményeinek figyelésérõl.
	 */
	public void disableController(){
		this.isActive=false;
		deselect();
	}
	/**
	 * Beállítja a kiválasztás színét
	 * @param color_select
	 */
	public void setColor_select(Color color_select) {
		this.color_select = color_select;
	}
	/**
	 * Egy lenyíló menüt hoz le.
	 * @param e
	 */
	private void doPop(MouseEvent e){
		if(isHaveType == true){
			menu.enableParameters();
		}
        menu.show(e.getComponent(), e.getX(), e.getY());   
    }
	//MÃ¡sra hasznÃ¡lom mint a Drawing elementnÃ©l
	public void receiveCursorPoint() {
		//float resMM = this.canvas.getResolutionMM();
		//float xMM = (float) mouseX / resMM;
		//float yMM = (float) mouseY / resMM;
		////this.inputs.add(new model.Point(xMM, yMM));
		//this.addInput(new model.Point(xMM, yMM));
		float resMM = this.canvas.getResolutionMM();
		this.x1 = (float) mouseX / resMM /(this.canvas.getZoom()*this.canvas.getZoom());
		this.y1 = (float) mouseY / resMM /(this.canvas.getZoom()*this.canvas.getZoom());
		//this.inputs.add(new model.Point(xMM, yMM));		
	}
	/**
	 * eddigi kiválasztási halmazt üri ki.
	 */
	public void deselect(){
		Iterator it = inputs.iterator();
		while ( it.hasNext() ) {
			DrawingElement element = (DrawingElement) it.next();
			ereaseSelectedObjectColor(element);
		}
		inputs.clear();
		current_State=STATE_DEFAULT;
		this.canvas.repaint();
	}
	@Override
	public void receiveEndpoint() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void receiveCenter() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void receiveMidpoint() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void receiveAbsolutePoint() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void receiveRelativePoint() {
		// TODO Auto-generated method stub
		
	}



	public void receivePointBySnap(int snap) {
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
	@Override
	public void receivePointBySnap() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSheet( Sheet s) {
		this.sheet = s;
		this.canvas.setSheet(this.sheet);
		sheet.addDrawingElement(selecting_rect);
	}
	/**
	 *  A kiválasztás egéreseményeit kezeli.
	 * @author Godó Viktor
	 *
	 */
	protected class MouseListener extends MouseInputAdapter{
		Rectangle rectToDraw;
		Rectangle currentRect;
		public MouseListener(){
		}
		
		
		public void mousePressed(MouseEvent e) {
			isHaveType = true;
			refreshtimer=0;
			if(isActive == true && SwingUtilities.isLeftMouseButton(e)){
				mouseX = e.getX() * (int) (canvas.getZoom() + 0.5F);
				mouseY = e.getY() * (int) (canvas.getZoom() + 0.5F);
				receiveCursorPoint();
				selecting_rect.setX1(x1);selecting_rect.setY1(y1);
				selecting_rect.setX2(x1);selecting_rect.setY2(y1);
				//currentRect = new Rectangle(mouseX, mouseY, 0, 0);
				if(  !inputs.isEmpty()){ 
					//Something has already been selected and not click right button.
					//In other words deselect action or new select Action
					//System.out.println("DESELECT");	
					deselect();
				}
				//System.out.println("koords: "+mouseX+", "+mouseY);
				if (sheet != null && sheet.getDrawingElements().size() > 1) {
					ArrayList lines =new ArrayList<Line>();
					ArrayList circles =new ArrayList<Circle>();		
					ArrayList coll = sheet.getDrawingElements();
					
					Iterator it = coll.iterator();
					while ( it.hasNext() ) {
						DrawingElement element = (DrawingElement) it.next();
						if ( element instanceof Line ) {
							receivePointBySnap();
							Line line = (Line)element;	
							receiveCursorPoint();
							if(line.isOnLine(x1, y1)){
								current_State=STATE_SELECTED;
								//inputs.add(element);
								lines.add(line);
							}
						}else if(element instanceof Circle){
							
						}
					}
					if( lines.size()>0){
						//System.out.println("linesSelected: "+lines.size());
						if(lines.size()>1){lineSelector.selectLine(lines,x1,y1);}
						inputs.add(lines.get(lines.size()-1));
					}else if(circles.size()>1){
						
					}
				}
			}
		}
		public void mouseDragged(MouseEvent e) {
			if(isActive == true  && SwingUtilities.isLeftMouseButton(e)){
				mouseX = e.getX() * (int) (canvas.getZoom() + 0.5F);
				mouseY = e.getY() * (int) (canvas.getZoom() + 0.5F);
				receiveCursorPoint();
				//System.out.println("koords: "+mouseX+", "+mouseY);
				float dragEndpositionX=x1;
				float dragEndpositionY=y1;
				float dragStartPositionX =selecting_rect.getX1();
				float dragStartPositionY = selecting_rect.getY1();
				float distance = (float) Math.sqrt((dragEndpositionX - dragStartPositionX)*(dragEndpositionX - dragStartPositionX) + (dragEndpositionY-dragStartPositionY)*(dragEndpositionY-dragStartPositionY)) ;
				if(  distance  > 1 && sheet.getDrawingElements().size() > 1){
					refreshSelectRectangle(x1,y1);
				}
			}
			
		}
		/**
		 * Frissíti a kijelölõ téglalapot.
		 * @param dragEndpositionX
		 * @param dragEndpositionY
		 */
		private void refreshSelectRectangle(float dragEndpositionX,float dragEndpositionY){
			refreshtimer++;
			selecting_rect.setX2(dragEndpositionX);
			selecting_rect.setY2(dragEndpositionY);
			selecting_rect.setStartAndEndpoint();
			if (refreshtimer%15==0){
				//To avoid flicker because there is no BufferStrategy
				canvas.repaint();
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if(isActive){
				if(SwingUtilities.isRightMouseButton(e) && current_State==STATE_SELECTED){
					typeTest();
					doPop(e);
				}else{
					mouseX = e.getX() * (int) (canvas.getZoom() + 0.5F);
					mouseY = e.getY() * (int) (canvas.getZoom() + 0.5F);
 					receiveCursorPoint();
					//System.out.println("koords: "+mouseX+", "+mouseY);
					float dragEndpositionX=x1;
					float dragEndpositionY=y1;
					float dragStartPositionX =selecting_rect.getX1();
					float dragStartPositionY = selecting_rect.getY1();
					
					float distance = (float) Math.sqrt((dragEndpositionX - dragStartPositionX)*(dragEndpositionX - dragStartPositionX) + (dragEndpositionY-dragStartPositionY)*(dragEndpositionY-dragStartPositionY)) ;
					if(!inputs.isEmpty() && distance <= 1){
						//There's something to select. To repaint(get selected color).
						Iterator it = inputs.iterator();
						while ( it.hasNext() ) {
							DrawingElement element = (DrawingElement) it.next();
							setSelectedObjectColor(element);
						}
					}else if(distance > 1 && SwingUtilities.isLeftMouseButton(e)){
						//drag select
						//setSelecting_rect
						//System.out.println("DragSelect");
						if (sheet != null && sheet.getDrawingElements().size() > 1) {
							ArrayList coll = sheet.getDrawingElements();
							Iterator it = coll.iterator();
							while ( it.hasNext() ) {
								DrawingElement element = (DrawingElement) it.next();
								if ( element instanceof Line ) {
									Line vonal = (Line)element;
									float l_x1 = vonal.getEndpoint1().getX();
									float l_y1 =vonal.getEndpoint1().getY();
									float l_x2 = vonal.getEndpoint2().getX();
									float l_y2=vonal.getEndpoint2().getY();
									Point startpoint = selecting_rect.getStartpoint();
									Point endpoint = selecting_rect.getEndpoint();
									if(l_x1>= startpoint.getX() && l_x1 <= endpoint.getX() &&  l_y1 >= startpoint.getY() &&  l_y1 <= endpoint.getY() && l_x2>= startpoint.getX() && l_x2 <= endpoint.getX() &&  l_y2 >= startpoint.getY() &&  l_y2 <= endpoint.getY()){
										inputs.add(element);
										setSelectedObjectColor(element);
										current_State=STATE_SELECTED;
									}
								}
							}
						}
						selecting_rect.setToDefault();
					}
				}
			}
			selecting_rect.setToDefault();

			canvas.repaint();
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}


}
