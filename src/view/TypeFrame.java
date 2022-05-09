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
	 * A listában kiválasztott típust adja vissza.
	 * @return
	 */
	public String getSelectedType() {
		return typeList.getSelectedType();
	}
	/**
	 * A típus jellemzõit tartalmazó táblázat kiürítése.
	 */
	public void clearParametertable(){
		table.cleartable();
	}
	/**
	 * Típus törlése a listából.
	 * @param typename
	 */
	public void removeType(String typename){
		this.typeList.remove(typename);
	}
	/**
	 * A grafikus felülethez tartozó  eseménykiváltó komponensekhez a paraméterként átadott objektumot hozzáadjuk az eseményeik figyelésére.  
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
	 * Hozzáadunk a listához egy típust.
	 * @param name
	 */
	public void addType(String name){
		this.typeList.add(name);
	}
	/**
	 * A grafikus felület gyártása.
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
	 * A felhasználói felület megjelenítése.
	 */
	public void createAndShowGui(){
		setVisible(true);
	}
	/**
	 * Hozzáadunk a táblázathoz egy jellemzõt.
	 * @param row
	 */
	public void addParamter(String[] row){
	    table.addRow(row);
	}
	/**
	 * A felsõ panel létrehozása.
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
	 * Az alsó panel létrehozása.
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
	 * Adott hibaüzenet kiíratása dialógusablakban.
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
	 * Egy szöveges bekérési dialógusablak kirajzolása.
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
	 * Egy szelekciós dialógusablak kirajzolása. 
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
	 * Visszaadja a táblázat sorait karakterláncok tömbjeként.
	 * @return
	 */
	public String[] getTableLines(){
		String [] lines =this.table.getSelectedLines();
		return lines;
	}
	/**
	 * Beállítja a kiválasztott típust.	
	 * @param s
	 */
	public void setselectedType (String s){
		this.selectedType=s;
	}
	/**
	 * Beállítja a listában kiválasztott elemet.
	 * @param itemId
	 */
	public void select(int itemId){
		typeList.select(itemId);
	}
	/**
	 * Visszaadja a jellemzõ nevét.	
	 * @return
	 */
	public String getParname() {
		return parname;
	}
	/**
	 * Beállítja a jellemzõ nevét.
	 * @param parname
	 */
	public void setParname(String parname) {
		this.parname = parname;
	}
	/**
	 * Visszaadja a szöveges bekérõablakba beírt szöveget
	 * @return
	 */

	public String getInputString() {
		return inputString;
	}
	/**
	 * Beállítja a szöveges bekérõablakba beírt szöveget
	 * @param inputString
	 */
	public void setInputString(String inputString) {
		this.inputString = inputString;
	}
	
}