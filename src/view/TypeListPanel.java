package view;

import java.awt.BorderLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;

import control.DataBaseController;
import model.Type;

public class TypeListPanel extends JPanel implements ActionListener,MouseListener {
	private String selectedType;
	List typelist=new List();
	public final static  String TYPE_NULL ="(null)";
	private boolean enable_null;
	/**
	 * Konstruktor
	 */
	public TypeListPanel(){
		BorderLayout TypeListPanelLayout = new BorderLayout();
		enable_null = false;
		typelist.addActionListener(this);
		typelist.addMouseListener(this);
		setLayout(TypeListPanelLayout);
		add(typelist,BorderLayout.CENTER);
		selectedType=new String("");
		addNullType();
	}
	/**
	 * Engedélyezi, hogy a típus legyen nullolható. A rajzelem típushozzárendeléskor fontos.
	 */
	public void enableNull(){
		this.enable_null=true;
	}
	/**
	 * Megtiltja, hogy a típus legyen nullolható
	 */
	public void disableNull(){
		this.enable_null=true;
	}
	/**
	 * Hozzáadunk egy „(null)”-t a listához.
	 */
	public void addNullType(){
		typelist.add(TYPE_NULL);
	}
	/**
	 * Töröljük a lista tartalmát, kivéve a „(null)”-t.	
	 */
	public void clear(){
		this.typelist.removeAll();
		addNullType();
	}
	/**
	 * Visszaadja a listán kiválasztott elemet. Ami a típus neve
	 * @return
	 */
	public String getSelectedType() {
		if(selectedType== null ||selectedType.equals(TYPE_NULL) && !enable_null){
			return null;
		}else{
			return selectedType;
		}
	}
	/**
	 * Beállítja a kiválaztott típus nevét.
	 * @param selectedType
	 */
	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Frissíti a kijelölt elemet az egér interakciónak megfelelõen.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(typelist)){
			this.selectedType=typelist.getSelectedItem();	
		}	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Elem törlése a listából
	 * @param selectedType
	 */
	public void remove(String selectedType) {
		// TODO Auto-generated method stub
		typelist.remove(selectedType);
	}
	/**
	 * Elem hozzáadása a listához
	 * @param name
	 */
	public void add(String name){
		typelist.add(name);
	}
	/**
	 *  A megadott indexnek megfelelõen állítja be a kiválasztott elemet a listában.
	 * @param num
	 */
	public void select(int num){
		typelist.select(num);
	}
}
