package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;

import model.Parameter;
import view.MainFrame;
import view.ParameterFrame;
/**
 * 
 * @author Godó Viktor
 * ParameterController vezérlõ osztály, ami figyeli és vezérli a ParameterFrame grafikus felület eseményeit.
 */
public class ParameterController implements ActionListener{
	DataBaseController db_ctrl;
	ParameterFrame parameterView;
	static String STR_ERROR_NO_NAME="Parameter name can't be empty";
	static String STR_ERROR_PARNAME_ALREADY_EXIST="Parametername  must be unique";
	static String STR_ERROR_TOO_LONG_DATA="Given expression is too long.Parameter name(1-40),measure(1-20)";
	static String STR_ERROR_OPERATION_FAILED ="Operation failed";
	
	/**
	 * Konstruktor
	 * @param dbctrl
	 * @param view
	 */
	public ParameterController(DataBaseController dbctrl, MainFrame view){
		this.db_ctrl = dbctrl;
		parameterView = view.getPar_frame();
		parameterView.addParameterActionListener(this);
	}
	/**
	 * Vizsgálja, hogy az adott jellemzõ létezik-e már(névegyezés alapján).
	 * @param parname
	 * @return
	 */
	private Boolean isParameterAlreadyExist(String parname){
		Map<Integer,Parameter> parameters=this.db_ctrl.getParameters();
		//System.out.println("Debug1: "+selectedTypeParameters1.size());
		Iterator it = parameters.values().iterator();
		while (it.hasNext()) {
			Parameter j = (Parameter) it.next();
			if(j.getname().equals(parname)){
				return true;
			}
		}
		return false;
	}
	/**
	 * A jellemzõ hozzáadás interakcióját kezeli. 
	 */
	public void action_add(){
		String parname,measure;
		try{
			parname = parameterView.getParameterName();
			measure = parameterView.getParameterMeasure();
			Boolean alreadyexist = isParameterAlreadyExist(parname);
			if(parname == null || parname.equals("")){
				parameterView.showErrorMessage(STR_ERROR_NO_NAME);
			}else if(parname.length()>40 || measure.length()>20){
				parameterView.showErrorMessage(STR_ERROR_TOO_LONG_DATA);
			}else if(alreadyexist){
				parameterView.showErrorMessage(STR_ERROR_PARNAME_ALREADY_EXIST);
			}else{
				Parameter j = new Parameter(parname,measure);
				db_ctrl.addParameter(j);
				parameterView.clearText();
			}	
		}catch(Exception exc){
			parameterView.showErrorMessage(STR_ERROR_OPERATION_FAILED);
		}
	}
	/**
	 * Bezárja a ParameterFrame ablakát.
	 */
	public void action_cancel(){
		parameterView.dispose();
	}
	/**
	 * A ParameterFrame grafikus felület eseményeit figyeli.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals((parameterView.ACTION_ADD))){
			action_add();
		}else if(e.getActionCommand().equals((parameterView.ACTION_CANCEL))){
			action_cancel();
		}
		
	}
}
