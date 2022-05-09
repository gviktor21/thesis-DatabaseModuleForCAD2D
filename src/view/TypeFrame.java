package view;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;

import model.Parameter;
import model.Type;
import control.DataBaseController;
public class TypeFrame extends JFrame{
	private static final long serialVersionUID = -6669119145443449187L;
	private String selectedType;
	JPanel alsoPanel;
	JPanel felsoPanel;
	TypeListPanel typeList;
	TablePanel table;
	JButton b_AddParameter;
	JButton b_DeleteParameter;
	JButton b_add= new JButton("Add a Type");
	JButton b_delete= new JButton("Delete a Type");
	JButton b_show= new JButton("Show Parameters");
	JPanel bal=new JPanel();
	String inputString;
	String parname;
	public final static String ACTION_ADD_TYPE="Add a Type";
	public final static String ACTION_DELETE_TYPE="Delete a Type";
	public final static String ACTION_SHOW_PARAMETERS="Show Parameters of the Type";
	public final static String ACTION_ADD_PARAMETER = "Add a Parameter of the selected Type ";
	public final static String ACTION_DELETE_PARAMETER = "Delete one Type Parameter";
	/**
	 *Konstruktor 
	 */
	public TypeFrame(){
		super("Types");
		initGUI();
	}
	/**
	 * A list�ban kiv�lasztott t�pust adja vissza.
	 * @return
	 */
	public String getSelectedType() {
		return typeList.getSelectedType();
	}
	/**
	 * A t�pus jellemz�it tartalmaz� t�bl�zat ki�r�t�se.
	 */
	public void clearParametertable(){
		table.cleartable();
	}
	/**
	 * T�pus t�rl�se a list�b�l.
	 * @param typename
	 */
	public void removeType(String typename){
		this.typeList.remove(typename);
	}
	/**
	 * A grafikus fel�lethez tartoz�  esem�nykiv�lt� komponensekhez a param�terk�nt �tadott objektumot hozz�adjuk az esem�nyeik figyel�s�re.  
	 * @param action_listener
	 */
	public void addTypeFrameActionListener(ActionListener action_listener){
		b_AddParameter.addActionListener(action_listener);
		b_DeleteParameter.addActionListener(action_listener);
		b_DeleteParameter.addActionListener(action_listener);
		b_add.addActionListener(action_listener);
		b_delete.addActionListener(action_listener);
		b_show.addActionListener(action_listener);
	}
	/**
	 * Hozz�adunk a list�hoz egy t�pust.
	 * @param name
	 */
	public void addType(String name){
		this.typeList.add(name);
	}
	/**
	 * A grafikus fel�let gy�rt�sa.
	 */
	public void initGUI(){
		typeList = new TypeListPanel();		
		this.selectedType=new String("");
		GridLayout main = new GridLayout(2,1,5,5);
		setLayout(main);
		setupUpperPanel();
		setupLowerPanel();
		add(felsoPanel);
		add(alsoPanel);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	/**
	 * A felhaszn�l�i fel�let megjelen�t�se.
	 */
	public void createAndShowGui(){
		setVisible(true);
	}
	/**
	 * Hozz�adunk a t�bl�zathoz egy jellemz�t.
	 * @param row
	 */
	public void addParamter(String[] row){
	    table.addRow(row);
	}
	/**
	 * A fels� panel l�trehoz�sa.
	 */
	public void setupUpperPanel(){
		felsoPanel = new JPanel();
		GridLayout felsoLayout = new GridLayout(1,2,5,5);
		felsoPanel.setLayout(felsoLayout);
		FlowLayout balrendez=new FlowLayout(FlowLayout.CENTER,25,25);
		bal.setLayout(balrendez);
		bal.add(b_add);
		bal.add(b_delete);
		bal.add(b_show);
		felsoPanel.add(bal);
		felsoPanel.add(typeList);
	}
	/**
	 * Az als� panel l�trehoz�sa.
	 */
	private void setupLowerPanel() {
		alsoPanel = new JPanel();
		GridLayout alsoLayout = new GridLayout(1,2,5,5);
		alsoPanel.setLayout(alsoLayout);
		JPanel bal = new JPanel();
		GridLayout balLayout = new GridLayout(1,1,5,5);
		bal.setLayout(balLayout);
		table = new TablePanel(Structures.str_ParameterwithDefaultValue);
		bal.add(table);
		FlowLayout jobbrendez=new FlowLayout(FlowLayout.CENTER,30,30);
		JPanel jobb = new JPanel();
		b_AddParameter = new JButton("Add Parameter");
		b_DeleteParameter = new JButton("Delete Parameter");
		jobb.setLayout(jobbrendez);
		jobb.add(b_AddParameter);
		jobb.add(b_DeleteParameter);
		alsoPanel.add(bal);
		alsoPanel.add(jobb);
	}
	/**
	 * Adott hiba�zenet ki�rat�sa dial�gusablakban.
	 * @param errorText
	 */
	public void showErrorMessage(String errorText){
		JFrame frame = (JFrame)SwingUtilities.getRoot(this);
		JOptionPane.showMessageDialog(frame,
				errorText,
			    "Input error",
			    JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Egy sz�veges bek�r�si dial�gusablak kirajzol�sa.
	 * @param inputText
	 * @param q_text
	 */
	public void showInputMassage(String inputText,String q_text){
		JFrame frame = (JFrame)SwingUtilities.getRoot(this);
		this.inputString = "";
		String s = (String)JOptionPane.showInputDialog(
                frame,
                inputText,
                q_text,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
		this.inputString=s;
	}
	/**
	 * Egy szelekci�s dial�gusablak kirajzol�sa. 
	 * @param parameternames
	 */
	public void showParameterSelectDialog(String[] parameternames){
		JFrame frame = (JFrame)SwingUtilities.getRoot(this);
		parname = (String)JOptionPane.showInputDialog(
                frame,
                "Select a parameter to add",
                "AddParameterDialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                parameternames,
                "");
	}
	/**
	 * Visszaadja a t�bl�zat sorait karakterl�ncok t�mbjek�nt.
	 * @return
	 */
	public String[] getTableLines(){
		String [] lines =this.table.getSelectedLines();
		return lines;
	}
	/**
	 * Be�ll�tja a kiv�lasztott t�pust.	
	 * @param s
	 */
	public void setselectedType (String s){
		this.selectedType=s;
	}
	/**
	 * Be�ll�tja a list�ban kiv�lasztott elemet.
	 * @param itemId
	 */
	public void select(int itemId){
		typeList.select(itemId);
	}
	/**
	 * Visszaadja a jellemz� nev�t.	
	 * @return
	 */
	public String getParname() {
		return parname;
	}
	/**
	 * Be�ll�tja a jellemz� nev�t.
	 * @param parname
	 */
	public void setParname(String parname) {
		this.parname = parname;
	}
	/**
	 * Visszaadja a sz�veges bek�r�ablakba be�rt sz�veget
	 * @return
	 */

	public String getInputString() {
		return inputString;
	}
	/**
	 * Be�ll�tja a sz�veges bek�r�ablakba be�rt sz�veget
	 * @param inputString
	 */
	public void setInputString(String inputString) {
		this.inputString = inputString;
	}
	
}