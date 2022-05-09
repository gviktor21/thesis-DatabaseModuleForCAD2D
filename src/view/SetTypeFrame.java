package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.DrawingElement;
import model.Type;
import control.DataBaseController;
/**
 * 
 * @author Godó Viktor
 *  A SetTypeFrame grafikus felületet biztosít a rajzelemekhez típus definiálásához
 */
public class SetTypeFrame extends JFrame {
	private TypeListPanel typeList; 
	private JPanel lowerPanel;
	ArrayList<Object> inputs;
	JButton b_ok;
	JButton b_cancel;
    public static final String ACTION_OK= "Ok";
    public static final String ACTION_CANCEL = "Cancel";

	public SetTypeFrame() {
		// TODO Auto-generated constructor stub
		super("SetTypes");
		BorderLayout SetTypeFrame_Layout = new BorderLayout();
		setLayout(SetTypeFrame_Layout);
		setupUpperAndLowerPanel();
		setSize(300,300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void createAndShowGui(){
		setVisible(true);
	}
	public void clearlist(){
		this.typeList.clear();
	}
	private void setupUpperAndLowerPanel(){
		typeList = new TypeListPanel();
		lowerPanel  =  new JPanel(); 
		FlowLayout alsoLayout = new FlowLayout(30,50,30);
		lowerPanel.setLayout(alsoLayout);
		b_cancel = new JButton(ACTION_CANCEL);
		b_ok = new JButton(ACTION_OK);
		lowerPanel.add(b_ok);
		lowerPanel.add(b_cancel);
		this.add(typeList,BorderLayout.CENTER);
		this.add(lowerPanel,BorderLayout.SOUTH);
	}
	public void addType(String name){
		this.typeList.add(name);
	}
	public void enableNull(){
		this.typeList.enableNull();
	}
	
	public String getSelectedType() {
		return typeList.getSelectedType();
	}
	
	public void showErrorMessage(String errorText){
		JFrame frame = (JFrame)SwingUtilities.getRoot(this);
		JOptionPane.showMessageDialog(frame,
				errorText,
			    "Input error",
			    JOptionPane.ERROR_MESSAGE);
	}
	public void addSetTypeFrameActionListener(ActionListener actionListener){
		b_cancel.addActionListener(actionListener);
		b_ok.addActionListener(actionListener);	
	}
	
}
