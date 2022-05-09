package control;

import model.Sheet;
import view.MainFrame;
/**
 * 
 * @author Godó Viktor
 * 
 */
public class DataBaseModuleFramesController {
	DataBaseController db_ctrl;
	MainFrame frame;
	TypeController tctrl;
	ParameterController parctrl;
	CatalogController ctrl;
	SelectController sctrl;
	SetTypeFrameController stctrl;
	public DataBaseModuleFramesController(DataBaseController dbctrl, MainFrame frame,SelectController sctrl, Sheet testSheet){
		ParameterController parctrl =new ParameterController(dbctrl,frame);
		TypeController tctrl = new TypeController(dbctrl,frame,testSheet);
		CatalogController catctrl = new CatalogController(dbctrl,frame);
		SetTypeFrameController stctrl= new SetTypeFrameController(dbctrl,frame,sctrl);
		SetTypeParameterController stpctrl= new SetTypeParameterController(dbctrl,frame,sctrl);
	}
}
