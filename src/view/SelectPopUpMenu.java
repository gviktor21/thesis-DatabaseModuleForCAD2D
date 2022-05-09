package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import control.DataBaseController;
/**
 * 
 * @author Godó Viktor
 * Egy lenyíló menüt valóst meg.
 */
public class SelectPopUpMenu extends JPopupMenu implements ActionListener {
    JMenuItem defineType_Item;
    JMenuItem setParameters_Item;
    public static final String ACTION_DEFINE_TYPE_MENU_ITEM = "Set Type";
    public static final String ACTION_SET_PARAMETERS_MENU_ITEM = "SetParmeters";
    protected ArrayList<Object> inputs;
    DataBaseController db_ctrl;
    SetTypeFrame setTypeFrame;
    SetTypeParameterFrame setTypeParameterFrame;
    public SelectPopUpMenu(ArrayList<Object> inputs,DataBaseController databaseController){
    	try {
			this.db_ctrl=new DataBaseController();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.inputs=inputs;
    	defineType_Item = new JMenuItem("Define Type");
    	setParameters_Item = new JMenuItem("Set Parameters");
        add(defineType_Item);
        add(setParameters_Item);
        defineType_Item.addActionListener(this);
        setParameters_Item.addActionListener(this);
        defineType_Item.setActionCommand(ACTION_DEFINE_TYPE_MENU_ITEM);
        setParameters_Item.setActionCommand(ACTION_SET_PARAMETERS_MENU_ITEM);
        setParameters_Item.setEnabled(false);
    }
    public SelectPopUpMenu(SetTypeFrame setTypeFrame,SetTypeParameterFrame setTypeParameterFrame) {
		// TODO Auto-generated constructor stub
    	defineType_Item = new JMenuItem("Define Type");
    	setParameters_Item = new JMenuItem("Set Parameters");
        add(defineType_Item);
        add(setParameters_Item);
        defineType_Item.addActionListener(this);
        setParameters_Item.addActionListener(this);
        defineType_Item.setActionCommand(ACTION_DEFINE_TYPE_MENU_ITEM);
        setParameters_Item.setActionCommand(ACTION_SET_PARAMETERS_MENU_ITEM);
        setParameters_Item.setEnabled(false);
        this.setTypeFrame =setTypeFrame;
        this.setTypeParameterFrame=setTypeParameterFrame;
	}
	public void enableParameters(){
        setParameters_Item.setEnabled(true);
    }
	public void disableParameters(){
        setParameters_Item.setEnabled(false);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 if ( e.getActionCommand() == SelectPopUpMenu.ACTION_DEFINE_TYPE_MENU_ITEM ) {
			 setTypeFrame.createAndShowGui();
		 }else if(e.getActionCommand() == SelectPopUpMenu.ACTION_SET_PARAMETERS_MENU_ITEM){
			 setTypeParameterFrame.createAndShowGui();
		 }
	}
}