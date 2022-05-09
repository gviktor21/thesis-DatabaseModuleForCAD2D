/**
 * Main application
 * Author: Gabor Nemeth
 * Date: 2012-07-09
 * Contact: gnemeth@inf.u-szeged.hu
 *
 * The main program.
 * 
 */ 

import model.*;
import model.Point;
import view.*;
import control.*;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;


public class CAD2D {
	
	public static void main(String args[]) {
		try {
			Sheet testSheet = new Sheet();
			DataBaseController databaseController =new DataBaseController();
			databaseController.createDataBase();
			MainFrame frame = new MainFrame(databaseController);

			CanvasModeController canvasModeController =new CanvasModeController(frame.getDrawingCanvas(),frame,testSheet,databaseController);
			DataBaseModuleFramesController mf_control = new DataBaseModuleFramesController(databaseController,frame,canvasModeController.getSelectController(),testSheet);
			/*DrawingController drawingController = new DrawingController(frame.getDrawingCanvas() );
			 **** EZ CSAK A TESZTELÉS MIATT VAN   *****                                
			drawingController.setSheet(testSheet);
			drawingController.setOption(DrawingController.OPTION_LINE_DRAW);
			drawingController.setMethod(LineDrawer.METHOD_LINE_TWO_POINT);
			drawingController.setSnap(Snapping.SNAP_CURSOR);*/
			/* **** EZ CSAK A TESZTELÉS MIATT VAN  --- TESZSELÉS VÉGE  *****            */
			
			WindowListener wl = new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			};
			frame.addWindowListener(wl);
			
			frame.setVisible(true);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//ArrayList<Sheet> sheetList = new ArrayList<Sheet>(); 
		
		
	}
	
	
}
