package control;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.DrawingElement;
import model.Parameter;
import view.MainFrame;
import view.SetTypeParameterFrame;
/**
 * 
 * @author Godó Viktor
 *A SetTypeParameterController vezérlõ osztály figyeli a SetTypeParameterFrame eseményeit és reagál rájuk.
 *Segítségével lehet a rajzelemekhez jellemzõértékeket rendelni.
 */
public class SetTypeParameterController implements WindowListener,ActionListener{
	DataBaseController db_ctrl;
	SetTypeParameterFrame setTypeParameterView;
	SelectController sctrl;
	
	private boolean isSameType;
	private boolean isSameContent;
	private boolean isEmpty;
	private int number_of_Parameters;
	
	model.Type prototype;
	Map<Integer,Parameter> typeparameters;
	SetTypeParameterContent previous_content;
	
	static String STR_ERROR_TYPE_MISMATCH = "Type mismatch: Draw elements must be the same type";
	static String STR_ERROR_TOO_LONG_DATA = "Given expression is too long.";
	static String STR_ERROR_NO_ELEMENT_SELECTED = "No Drawing element Selected.";
	/**
	 * Konstruktor
	 * @param dbctrl Adatbáziskezelõ vezérlõ.
	 * @param view Fõablak
	 * @param sctrl Kiválaztásvezérlõ.
	 */
	public SetTypeParameterController(DataBaseController dbctrl, MainFrame view, SelectController sctrl){
		this.db_ctrl = dbctrl;
		this.sctrl=sctrl;
		setTypeParameterView = view.getSetTypeParameterFrame();
		setTypeParameterView.addWindowListener(this);
		setTypeParameterView.addSetTypeParameterFrameActionListener(this);
		previous_content = new SetTypeParameterContent();
	}
	/**
	 * Vizsgálja azt, hogy a kiválasztott rajzelemek ugyanolyan típusúak-e
	 */
	private void typeTest(){
		isSameType=true;
		isEmpty=true;
		ArrayList<Object> inputs = sctrl.getInputs();
		if(!inputs.isEmpty()){
			Iterator it = inputs.iterator();
			DrawingElement  first_element = ((DrawingElement) inputs.get(0));
			prototype = first_element.getType();
			prototype =db_ctrl.getTypeParameters(prototype);
			while ( it.hasNext() ) {
				DrawingElement element = (DrawingElement) it.next();
				model.Type Type = element.getType();
				if(Type.getID()!=prototype.getID()){
					isSameType = false;
				}
			}
		}else{
			isEmpty=false;	
		}
	}
	/**
	 * Azt ivzsgálja, hogy az elõzõ kiválasztáshoz képest változott-e a kiválasztott rajzelemek vagy azok típusa.
	 */
	private void contentTest(){
		isSameContent=false;
		SetTypeParameterContent current_content= new SetTypeParameterContent(sctrl.getInputs(),prototype);
		System.out.print("previous: "+this.previous_content.toString());
		System.out.print("new: "+current_content.toString());
		if(current_content.equals(previous_content)){
			System.out.println("Content is the same");
			isSameContent=true;
		}else{
			isSameContent=false;
			System.out.println("Content changed");
			//previous_content = current_content;
		}
		previous_content = current_content;
	}
	/**
	 *  Visszaadja a jellemzõnévhez tartozó a párbeszédablak-ba beirt jellemzõértéket
	 * @param parname
	 * @return
	 */
	private String searchValue(String parname){
		ArrayList<JTextField> parameterValue_texts=setTypeParameterView.getParameterValue_texts();
		ArrayList<JLabel> parameterLabels=setTypeParameterView.getParname_labels();
		Iterator it = parameterLabels.iterator();
		while(it.hasNext()){
			JLabel jlabel = (JLabel) it.next();
			String labeltextWithoutMeasure =getpureParnameLabeltext(jlabel.getText());
			if(labeltextWithoutMeasure.equals(parname)){
				int index =parameterLabels.indexOf(jlabel);
				return parameterValue_texts.get(index).getText();
			}
		}
		return new String("");
	}
	/**
	 * A cimke komponensek szövegébõl lenyesi a jellemzõ mértékegyégét 
	 */
	private String getpureParnameLabeltext(String labeltext){
		String text = null;
		int firstchar =labeltext.lastIndexOf("(");
		text =labeltext.substring(0, firstchar);
		return text;
	}
	/**
	 * 
	 */
	private void action_ok(){
		Iterator it =typeparameters.values().iterator();
		while(it.hasNext()){
			Parameter par = (Parameter)it.next();
			String value = searchValue(par.getname());
			if(value.length()>40){
				this.setTypeParameterView.showErrorMessage(STR_ERROR_TOO_LONG_DATA);
				return;
			}
			par.setvalue(value);
		}
		ArrayList<Object> inputs = sctrl.getInputs();
		it = inputs.iterator();
		while(it.hasNext()){
			DrawingElement element = (DrawingElement) it.next();
			element.setParameters(typeparameters);
			db_ctrl.setDrawingElementParameters(element);
		}
		setTypeParameterView.dispose();
	}
	/**
	 * 
	 */
	private void action_cancel(){
		setTypeParameterView.dispose();
	}
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == SetTypeParameterFrame.ACTION_OK){
			action_ok();
		}else if(e.getActionCommand() == SetTypeParameterFrame.ACTION_CANCEL){
			action_cancel();
		}
	}
	/**
	 * 
	 */
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		typeTest();
		contentTest();
		if(isSameType && isEmpty){
			if(!isSameContent){
				setTypeParameterView.removeAllField();
				updateView();
			}
		}else if(!isSameType){
			setTypeParameterView.showErrorMessage(STR_ERROR_TYPE_MISMATCH);
			setTypeParameterView.dispose();
		}else if(!isEmpty){
			setTypeParameterView.showErrorMessage(STR_ERROR_NO_ELEMENT_SELECTED);
			setTypeParameterView.dispose();
		}
	}
	/**
	 * 
	 */
	public void updateView(){
		prototype =db_ctrl.getTypeParameters(prototype);
		typeparameters = prototype.getParameters();
		Iterator it = typeparameters.values().iterator();
		while(it.hasNext()){
			Parameter par = (Parameter)it.next();
			String defaultvalue=par.getdefaultValue();
			String parMeasure= new String();
			parMeasure=par.getmeasure();
			if(parMeasure == null){
				parMeasure="";
			}
			setTypeParameterView.addParnameLabel(par.getname()+"("+parMeasure+")");
			setTypeParameterView.addParValueTextField(defaultvalue);
		}
		setTypeParameterView.repaintMainPanel();
		setTypeParameterView.repaint();
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
		//setTypeParameterView.removeAllField();
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
	 * 
	 * @author Godó Viktor
	 *A SetTypeParameterContent osztály írja az adott rajzelem kiválasztást.
	 * Más szóval, mely rajzelemek kerültek kiválasztásra, azok milyen típusúak, és az adott típushoz milyen jellemzõk tartoznak
	 */
	private class SetTypeParameterContent{
		private int numOfdrawElements;
		private int numOfTypeParameters;
		model.Type type;
		private ArrayList<Integer> drawElementIds;
		private ArrayList<Integer> typeParameterIds;
		
		SetTypeParameterContent(){
			this.drawElementIds = new  ArrayList<Integer>();
			this.typeParameterIds = new  ArrayList<Integer>();
			numOfdrawElements=0;
			numOfTypeParameters=0;
			this.type = new model.Type(-1,"null");
			drawElementIds.add(-1);
			typeParameterIds.add(-1);
		}
		
		SetTypeParameterContent(ArrayList<Object> drawElements,model.Type type){
			this.drawElementIds = new  ArrayList<Integer>();
			this.typeParameterIds = new  ArrayList<Integer>();
			numOfdrawElements = drawElements.size();
			numOfTypeParameters =type.getParameters().size();
			this.type = type;
			
			Iterator it = drawElements.iterator();
			while ( it.hasNext() ) {
				DrawingElement element = (DrawingElement) it.next();
				int id = Integer.parseInt(element.getID());
				this.drawElementIds.add(id);
			}
			
			Map<Integer, Parameter> parameters=type.getParameters();
			it = parameters.values().iterator();
			while ( it.hasNext() ) {
				Parameter parameter = (Parameter) it.next();
				int id = parameter.getID();
				typeParameterIds.add(id);
			}
			Collections.sort(this.drawElementIds);
			Collections.sort(this.typeParameterIds);
		}
		
		public int getNumOfdrawElements() {
			return numOfdrawElements;
		}
		public int getNumOfTypeParameters() {
			return numOfTypeParameters;
		}
		public model.Type getType() {
			return type;
		}
		
		public ArrayList<Integer> getDrawElementIds() {
			return drawElementIds;
		}

		public ArrayList<Integer> getTypeParameterIds() {
			return typeParameterIds;
		}
		public String toString(){
			return type.getname()+" "+this.numOfdrawElements+" "+this.numOfTypeParameters+" ";
			
		}
		public boolean equals(SetTypeParameterContent otther){
			if(numOfdrawElements == otther.getNumOfdrawElements() && numOfTypeParameters == otther.getNumOfTypeParameters() && type.getID() == otther.getType().getID()
					&& drawElementIds.equals(otther.getDrawElementIds()) && typeParameterIds.equals(otther.getTypeParameterIds()) ){
				return true;
			}else{
				return false;
			}
		}
	}

}
