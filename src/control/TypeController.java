package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.DrawingElement;
import model.Parameter;
import model.Sheet;
import model.Type;
import view.MainFrame;
import view.ParameterFrame;
import view.TypeFrame;
/**
 * 
 * @author God� Viktor
 * A t�pus defini�l�s�nak esem�nyeit, menet�t vez�rli. Ezek az esem�nyek a: T�pusok l�trehoz�sa, t�rl�se. Jellemz�k hozz�ad�sa t�pushoz �s azok t�rl�se
 *
 */
public class TypeController  implements ActionListener {
	DataBaseController dbctrl;
	TypeFrame typeView;
	ParameterFrame parameterView;
	String selectedType;
	Map<Integer,Type>types;
	Sheet sheet;
	
	static String STR_ERROR_NO_NAME="Type name can't be empty";
	static String STR_ERROR_TOO_LONG_DATA="Given expression is too long.Parameter name(1-40),measure(1-20)";
	static String STR_ERROR_TOO_LONG_STRING="Given expression is too long.(>40 character)";
	static String STR_ERROR_NO_TYPENAME_GIVEN ="No name given to the type";
	static String STR_ERROR_NO_DELETE ="Invalid action";
	static String STR_ERROR_NO_TYPE_SELECTED = "No Type Selected";
	static String STR_ERROR_TYPE_ALREADY_USED = "Type is in use by an Drawing Element";
	static String STR_ERROR_NO_PARAMETER_SELECTED = "No Parameter Selected";
	static String STR_INPUT_TYPENAME ="Please, enter the Type name";
	static String STR_INPUT_DEFAULT_VALUE ="Define Default value or just CLICK ok.";
	static String STR_ERROR_TYPE_ALREADY_EXIST ="Typename must be unique.";
	
	public final static  String TYPE_NULL ="(null)";
	
	public TypeController(DataBaseController dbctrl, MainFrame frame, Sheet testSheet) {
		// TODO Auto-generated constructor stub
		this.sheet=testSheet;
		this.typeView = frame.getType_panel();
		parameterView  = frame.getPar_frame();
		this.dbctrl=dbctrl;
		selectedType=new String("");
		typeView.addTypeFrameActionListener(this);
		initTypes();
	}
	/**
	 * A param�terben �tadott nev� t�pust adja vissza, ha ki van jel�lve.
	 * @param name
	 * @return
	 */
	private Parameter lookupParameterbyName(String name){
		Map<Integer,Parameter>Parameterk = new HashMap<>();
		Parameterk = dbctrl.getParameters();
		Iterator it = Parameterk.values().iterator();
		while(it.hasNext()){
			Parameter j = (Parameter) it.next();
			if(j.getname().equals(name)){
					return j;
			}
		}
		return null;
	}
	/**
	 * A param�terben �tadott nev� t�pust adja vissza, ha ki van jel�lve.
	 * @param name
	 * @param selectedType
	 * @return
	 */
	private Parameter lookupParameterbyName(String name, Type selectedType){
		Map<Integer,Parameter> selectedTypeParameters=selectedType.getParameters();
		System.out.println(selectedTypeParameters.size());
		
		Map<Integer,Parameter>Parameterk = new HashMap<>();
		Parameterk = dbctrl.getParameters();
		Iterator it = Parameterk.values().iterator();
		while(it.hasNext()){
			Parameter j = (Parameter) it.next();
			if(j.getname().equals(name)){
					if(!(selectedTypeParameters.containsKey(j.getID()))){
						return j;
					}else{
						System.out.println("Kulcseggyez�s");
					}
			}
		}
		return null;
	}
	/**
	 * Visszadja a kiv�lasztott t�pusn�vnek megfelel� t�pus obkektumot.
	 * @param selectedType
	 * @return
	 */
	public Type getSelectedTypeObject(String selectedType) {
		if(selectedType== null || selectedType.equals("")  || selectedType.equals(TYPE_NULL)  ){
			return null;
		}
		Type Type = lookupTypeByName(selectedType);
		return Type;
	}
	/**
	 * Visszadja a kiv�lasztott t�pusn�vnek megfelel� t�pus obkektumot.
	 * @return
	 */
	public Type getSelectedTypeObject() {
		if(selectedType== null || selectedType.equals("")  || selectedType.equals(TYPE_NULL)  ){
			return null;
		}
		Type Type = lookupTypeByName(selectedType);
		return Type;
	}
	/**
	 * A param�terben �tadott nev� t�pust adja vissza, ha ki van jel�lve.
	 * @param name
	 * @return
	 */
	public Type lookupTypeByName(String name){
		
		Map<Integer,Type>types = new HashMap<>();
		types = dbctrl.getTypes();
		Iterator it = types.values().iterator();
		while(it.hasNext()){
			Type t = (Type) it.next();
			if(t.getname().equals(name)){
				return t;
			}
		}
		return null;
	
	}

	/**
	 * Ki�ratja a kijel�lt t�pus jellemz�it a t�bl�zatba.
	 */
	private void showPreferences(){
		typeView.clearParametertable();
		String name = typeView.getSelectedType();
		Type t = getSelectedTypeObject(name);
		if(t!= null){
			t=dbctrl.getTypeParameters(t);
			Map<Integer,Parameter> t_ParameteriMap = t.getParameters();
			Iterator it = t_ParameteriMap.values().iterator();
			while (it.hasNext()) {
			    Parameter je = (Parameter) it.next();
			    String[] row = je.getDataLine();
			    typeView.addParamter(row);
			}	
		}
	}
	/**
	 * T�rli a t�bl�zatban kijel�lt param�tert a kijel�lt t�pusnak.
	 * @param lines
	 */
	private void deletePreferences(String[] lines){
		Type selectedType =getSelectedTypeObject();
		for(int i = 0;i<lines.length;i++){
			Parameter par = lookupParameterbyName(lines[i]);
			if(this.dbctrl.DeleteTypeParameter(selectedType, par)){
				selectedType.deleteParameter( par);
				updateDrawingElements(TypeFrame.ACTION_DELETE_PARAMETER,par);
			}
		}
		
	}
	/**
	 * A t�pus l�trehoz�s interakci�j�t kezeli.
	 */
	public void addType(){
		Boolean isAlreadyExist;
		typeView.clearParametertable();
		typeView.showInputMassage(STR_INPUT_TYPENAME,"Add a Type");
		String name;
		name = typeView.getInputString();
		isAlreadyExist = this.isTypeAlreadyExist(name);
		if ((name != null) && (name.length() > 0) && (name.length() < 40)&& !isAlreadyExist && !name.equals("(null)")) {
			Type newType = new Type(name);
			newType = dbctrl.addType(newType);
			types.put(newType.getID(), newType);
			
			typeView.addType(name);
		}else if(isAlreadyExist){
			typeView.showErrorMessage(STR_ERROR_TYPE_ALREADY_EXIST);
		}else if(name==null){
			typeView.showErrorMessage(STR_ERROR_NO_TYPENAME_GIVEN);
		}else if(name.equals("(null)")){
			typeView.showErrorMessage(STR_ERROR_TYPE_ALREADY_EXIST);
		}else{
			typeView.showErrorMessage(STR_ERROR_TOO_LONG_DATA);
		}
		typeView.select(0);
	}
	/**
	 * M�dos�tja a rajzelemeket a t�puson v�gzett m�dos�t�sok alapj�n.
	 * @param action
	 * @param parameter
	 */
	public void updateDrawingElements(String action, Parameter parameter){
		Type  type = lookupTypeByName(selectedType);
		if(action.equals(TypeFrame.ACTION_ADD_PARAMETER)){
			ArrayList coll = sheet.getDrawingElements();
			Iterator it = coll.iterator();
			while ( it.hasNext() ) {
				DrawingElement element = (DrawingElement) it.next();
				if(element.getType() !=null && element.getType().getID()==type.getID()){
					element.addParameter(parameter);
					if(!dbctrl.addDrawingElementParameter(element, parameter)){
						System.out.println("hiba t�rt�nt");
					}
				}
			}
			
		}else if(action.equals(TypeFrame.ACTION_DELETE_PARAMETER)){
			ArrayList coll = sheet.getDrawingElements();
			Iterator it = coll.iterator();
			while ( it.hasNext() ) {
				DrawingElement element = (DrawingElement) it.next();
				if(element.getType() !=null && element.getType().getID()==type.getID()){
					if(element.getParameters().containsKey(parameter.getID())){
						element.deleteParameter(parameter);
					}
				}
			}
			
		}
		
	}
	/**
	 * Megmondja, hogy az adott t�pushoz van-e m�r rajzelem.
	 * @return
	 */
	public boolean isTypeinUse(){
		Type  type = lookupTypeByName(selectedType);
		ArrayList coll = sheet.getDrawingElements();
		Iterator it = coll.iterator();
		while ( it.hasNext() ) {
			DrawingElement element = (DrawingElement) it.next();
			if(element.getType() !=null && element.getType().getID()==type.getID()){
				return true;
			}
		}
		return false;
	}
	/**
	 * A t�pus t�rl�s interakci�j�t kezeli.
	 */
	public void deleteType(){
		typeView.clearParametertable();
		selectedType=typeView.getSelectedType();
		if(selectedType==null){
			typeView.showErrorMessage(STR_ERROR_NO_DELETE);
		}else if(selectedType.equals("(null)")){
			//typeView.showErrorMessage(STR_ERROR_NO_DELETE);
		}else if(isTypeinUse()){
			typeView.showErrorMessage(STR_ERROR_TYPE_ALREADY_USED);
		}else{
			Type  type = lookupTypeByName(selectedType);
			//types.remove(type.getID());
			if(dbctrl.deleteType(type)){
				//System.out.println("sikeres t�rl�s");
			}else{
				//System.out.println("sikertelen t�rl�s");
			}
			typeView.select(0);
			typeView.removeType(selectedType);
		}
	}
	/**
	 * Kikeresi a t�pusokat �s hozz�adja a list�hoz.
	 */
	public void initTypes(){
		types = new HashMap<>();
		types = dbctrl.getTypes();
		Iterator it = types.values().iterator();
		while(it.hasNext()){
			Type t = (Type) it.next();
			typeView.addType(t.getname());
		}
	}
	/**
	 * A t�pus jellemz�inek mutatat�sa esem�nyt kezeli.
	 */
	public void showParameters(){
		 this.selectedType=typeView.getSelectedType();
		if(selectedType==null){
			typeView.showErrorMessage(STR_ERROR_NO_TYPE_SELECTED);
			this.selectedType="";
		}else{
			showPreferences();
		}
	}
	/**
	 * A t�pushoz jellemz� hozz�rendel�s interakci�j�t kezeli.
	 */
	public void addParameter(){
		selectedType =typeView.getSelectedType();
		if(this.selectedType!=null && !selectedType.equals("") && !selectedType.equals("(null)")){
			showPreferences();
			this.selectedType= typeView.getSelectedType();
			Type selectedType = getSelectedTypeObject();
			//debug
			Map<Integer, Parameter> notTypeprefs = dbctrl.getNotTypeParameters(selectedType.getID());
				if(notTypeprefs !=null){
					System.out.println("not type prefs: "+notTypeprefs.size());
					//There is addable parameter in the database. 
					Iterator it = notTypeprefs.values().iterator();
					String[] parameternames;
					parameternames = new String[notTypeprefs.size()];
					int k=0;
					while (it.hasNext()) {
						Parameter j = (Parameter) it.next();
						parameternames[k]= j.getname();
						k++;
					}
					
					typeView.showParameterSelectDialog(parameternames);
					String parname = typeView.getParname();
					
					if ((parname != null) && (parname.length() > 0)) {						
						Parameter j = lookupParameterbyName(parname,selectedType);
						typeView.showInputMassage(STR_INPUT_DEFAULT_VALUE,"Add default Value");
						String defaultValue = typeView.getInputString();
						if(defaultValue!=null && defaultValue.length() < 40){
							j.setdefaultValue(defaultValue);
						}else if(defaultValue!=null && defaultValue.length() > 40 ){
							typeView.showErrorMessage(STR_ERROR_TOO_LONG_STRING);
						}	
						selectedType.addParameter(j);
						dbctrl.AddTypeParameter(selectedType,j);
						updateDrawingElements(TypeFrame.ACTION_ADD_PARAMETER,j);
				}
			}else{
				//There's no parameter to add in the Database , so you have to create one
				parameterView.createAndShowGui();				
			}
		}
		showPreferences();
	}
	/**
	 * Vizsg�lja, hogy az adott t�pusn�v foglalt-e.
	 * @param typename
	 * @return
	 */
	private Boolean isTypeAlreadyExist(String typename){
		Map<Integer,Type> types=this.dbctrl.getTypes();
		Iterator it = types.values().iterator();
		while (it.hasNext()) {
			Type t = (Type) it.next();
			if(t.getname().equals(typename)){
				return true;
			}
		}
		return false;
	}
	/**
	 * A TypeFrame grafikus fel�let akci�it kezeli.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Delete a Type")){
			deleteType();
		}else if(arg0.getActionCommand().equals("Add a Type")){
			addType();
		}else if(arg0.getActionCommand().equals("Show Parameters")){
			showParameters();
		}else if(arg0.getActionCommand().equals("Add Parameter")){
			addParameter();
		}else if(arg0.getActionCommand().equals("Delete Parameter")){
			selectedType = typeView.getSelectedType();
			if(selectedType==null || selectedType.equals("")){
				//typeView.showErrorMessage(STR_ERROR_NO_TYPE_SELECTED);
			}else{
				try{
					String [] lines =typeView.getTableLines();
					deletePreferences(lines);
				}catch(NullPointerException ne){
					typeView.showErrorMessage(STR_ERROR_NO_PARAMETER_SELECTED);
					//ne.printStackTrace();
				}
				showPreferences();
			}
		}
	}

}

