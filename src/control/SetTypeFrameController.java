package control;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.DrawingElement;
import model.Parameter;
import view.MainFrame;
import view.SetTypeFrame;
/**
 * 
 * @author Godó Viktor
 * A SetTypeFrame párbeszédablak eseményet kezeli.
 */
public class SetTypeFrameController implements WindowListener,ActionListener{
	DataBaseController db_ctrl;
	SetTypeFrame setTypeView;
	SelectController sctrl;
	String selectedType;
	static String STR_ERROR_NO_TYPE_SELECTED = "No Type Selected";
	static String STR_ERROR_NO_ELEMENT_SELECTED = "No Drawing element Selected.";
	static String STR_ERROR_OPERATION_FAILED = "Operation failed";
	public final static  String TYPE_NULL ="(null)";

	/**
	 * Konstruktor
	 * @param dbctrl Adatbázis vezérlõ
	 * @param view Fõablak
	 * @param sctrl Kiválasztás vezérlõ
	 */
	public SetTypeFrameController(DataBaseController dbctrl, MainFrame view, SelectController sctrl){
		this.db_ctrl = dbctrl;
		this.sctrl=sctrl;
		setTypeView = view.getSetTypeFrame();
		setTypeView.addWindowListener(this);
		setTypeView.addSetTypeFrameActionListener(this);
		selectedType=new String("");
		setTypeView.enableNull();
	}
	/**
	 * Frissíti a típus kiválasztó listát
	 */
	public void updateView(){
		setTypeView.clearlist();
		Map<Integer, model.Type>types;
		types = db_ctrl.getTypes();
		Iterator it = types.values().iterator();
		while(it.hasNext()){
			model.Type t = (model.Type) it.next();
			setTypeView.addType(t.getname());
		}
	}
public model.Type lookupTypeByName(String name){
		
		Map<Integer, model.Type>types = new HashMap<>();
		types = db_ctrl.getTypes();
		Iterator it = types.values().iterator();
		while(it.hasNext()){
			model.Type t = (model.Type) it.next();
			if(t.getname().equals(name)){
				return t;
			}
		}
		return null;
	
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		updateView();
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}
	/**
	 * Törli a régí típusjellemzõket
	 * @param element
	 */
	private void clearDrawingElement(DrawingElement element){
		Map<Integer,Parameter>parameters =element.getParameters();
		Iterator it3 = parameters.values().iterator();
		while(it3.hasNext()){
			Parameter j = (Parameter) it3.next();
			this.db_ctrl.deleteDrawingParameter(element, j);
		}
		element.getParameters().clear();
	}
	/**új rajzelem felvitele az adatbázisba
	 * 
	 * @param element
	 */
	public void addNewElement(DrawingElement element){
		DrawingElement temp;
		temp=db_ctrl.addDrawingElement(element);
		if(temp !=null){
			element=temp;
		}else{
			setTypeView.showErrorMessage(STR_ERROR_NO_TYPE_SELECTED);
		} 
	}
	private void action_cancel(){
		setTypeView.dispose();
	} 
	/**
	 * Az ACTION_OK akció kezeli. 
	 */
	private void action_ok(){
		ArrayList<Object> inputs = sctrl.getInputs();
		if(  !inputs.isEmpty()){ 
			selectedType=setTypeView.getSelectedType();
			if(this.selectedType==null || this.selectedType.equals("")){
				this.setTypeView.showErrorMessage(STR_ERROR_NO_TYPE_SELECTED);
				this.selectedType="";
			}else if (this.selectedType!="(null)"){
				Iterator it = inputs.iterator();
				while ( it.hasNext() ) {
					DrawingElement element = (DrawingElement) it.next();
					model.Type type = lookupTypeByName(selectedType);
					if(element.getType()!= null){
						element= db_ctrl.getDrawingElementParameters(element);
					}
					if(element.getType() != null && element.getParameters()!=null && element.getParameters().size()>0 && type.getID() != element.getType().getID()){ 
						//already had a type and params setted
						clearDrawingElement(element);
						element.setType(type);
						if(!db_ctrl.modifyDrawingElementType(element)){
							this.setTypeView.showErrorMessage(STR_ERROR_NO_TYPE_SELECTED);
						}
					}else if(element.getType() != null && type.getID() == element.getType().getID()){
						//do nothing to prevent loose data
					}else if(element.getType() != null){
						//Modify the type of the drawing element
						element.setType(type);
						element.setname(type.getname());
						if(!db_ctrl.modifyDrawingElementType(element)){
							this.setTypeView.showErrorMessage(STR_ERROR_NO_TYPE_SELECTED);
						}
						element.setname(selectedType);
					}else{
						element.setType(type);
						addNewElement(element);
					}	
				}
				
			}else if(this.selectedType.equals(TYPE_NULL)){
				System.out.println("Params setted now clearing");
				Iterator it = inputs.iterator();
				while ( it.hasNext() ) {
					DrawingElement element = (DrawingElement) it.next();
					if(element.getType()!= null){
						element= db_ctrl.getDrawingElementParameters(element);
					}
					if(element.getType() != null && element.getParameters()!=null && element.getParameters().size()>0){ 
						//already had a type and params setted
						System.out.println("Params setted now clearing");
						clearDrawingElement(element);
						element.setType(null);
						element.setname(null);
						if(!db_ctrl.setNullDrawingElementType(element)){this.setTypeView.showErrorMessage(STR_ERROR_OPERATION_FAILED);}
					}else if(element.getType() != null){
						//Modify the type of the drawing element
						element.setType(null);
						element.setname(null);
						if(!db_ctrl.setNullDrawingElementType(element)){
							this.setTypeView.showErrorMessage(STR_ERROR_OPERATION_FAILED);
						}
					}
				}
			}
		}else{
			this.setTypeView.showErrorMessage(STR_ERROR_NO_ELEMENT_SELECTED);
		}
		setTypeView.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals((SetTypeFrame.ACTION_OK))){
			action_ok();
		}else if(e.getActionCommand().equals((SetTypeFrame.ACTION_CANCEL))){
			action_cancel();
		}
	}
}
