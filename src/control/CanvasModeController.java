package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.LineDrawer;
import model.Sheet;
import view.DrawingCanvas;
import view.IconButton;
import view.MainFrame;
/**
 * 
 * @author Godó Viktor
 *A rajzlap vezérlõit kezeli és hangolja össze, más szóval meghatározza milyen események történhetnek a rajzlapon
 *Melyik vezérlõ kezeli õket. A rajzlapon háromféle módban mûködhet(jelenleg)  ami lehet: iniciális,kiválasztás, rajzolás.
 Azaz MODE_DEFAULT, MODE_SELECT, MODE_DRAW.
 */
public class CanvasModeController extends CanvasController implements MouseListener {


	IconButton icon1;
	IconButton icon2;
	DrawingController drawingController;
	SelectController selectController;
	Sheet testSheet;
	public static final int MODE_DEFAULT=0;
	public static final int MODE_DRAW=1;
	public static final int MODE_SELECT=2;
	private  int current_mode;
	public CanvasModeController(DrawingCanvas canvas,MainFrame frame,Sheet testSheet,DataBaseController databaseController) {
		super(canvas);	
		this.testSheet = testSheet;
		drawingController = new DrawingController(canvas);
		selectController = new SelectController(canvas, databaseController,frame.getSelectPopUpMenu());
		selectController.setSheet(testSheet);
		drawingController.setSheet(testSheet);
		drawingController.setOption(DrawingController.OPTION_LINE_DRAW);
		drawingController.setMethod(LineDrawer.METHOD_LINE_TWO_POINT);
		drawingController.setSnap(Snapping.SNAP_CURSOR);
		this.icon1=frame.getDrawIconButton();
		this.icon2=frame.getSelectIconButton();
		this.icon1.addMouseListener(this);
		this.icon2.addMouseListener(this);
		current_mode=MODE_DEFAULT;
	}
	
	public SelectController getSelectController() {
		return selectController;
	}

	public void setSelectController(SelectController selectController) {
		this.selectController = selectController;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int state1 =icon1.getState();
		int state2 =icon2.getState();
		if(state2==icon2.SELECTED){
			setSelectMode();
		}else if(state1==icon1.SELECTED){
			setDrawMode();
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void setDrawMode(){
		selectController.disableController();
		drawingController.enableController();
		current_mode=MODE_DRAW;
	}
	public void setSelectMode(){
		drawingController.disableController();
		selectController.enableController();
		current_mode=MODE_SELECT;
	}
	public void setDefaultMode(){
		drawingController.disableController();
		selectController.disableController();
		current_mode=MODE_DEFAULT;
		icon1.deselect();
		icon2.deselect();
	}
	public int getCurrent_mode() {
		return current_mode;
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
