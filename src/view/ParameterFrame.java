package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import model.Parameter;
import model.Type;
import control.DataBaseController;

/**
 * 
 * @author God� Viktor
 * ParameterFrame grafikus fel�letet biztos�t a jellemz�k l�trehoz�s�ra.
 */
public class ParameterFrame  extends JFrame{
	JTextField parname_text;
	JTextField measure_text;
	JLabel parname_label;
	JLabel measure_label;
	JButton add_Button;
	JButton cancel_Button;
	JPanel upper_panel;
	JPanel lower_panel;
	public static final String ACTION_ADD= "Add";
	public static final String ACTION_CANCEL = "Cancel";
	
	/**
	 * Konstruktor
	 */
	public ParameterFrame(){
		super("Parameters");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridLayout frame_Layout = new GridLayout(2,1,10,10);
		setLayout(frame_Layout);
		setSize(300,300);
		setupUpperAndLowerJPanel();
	}
	/**
	 * Megjelen�ti a grafikus fel�letet.
	 */
	public void createAndShowGui(){
		setVisible(true);
	}
	/**
	 *  Legy�rtja a grafikus fel�let komponenseit.
	 */
	private void setupUpperAndLowerJPanel(){
		upper_panel = new JPanel();
		FlowLayout upper_Layout= new FlowLayout(FlowLayout.CENTER,2,20);
		upper_panel.setLayout(upper_Layout);
		parname_text = new JTextField(10);
		parname_text.setPreferredSize(new Dimension(120,20));
		measure_text = new JTextField(10);
		measure_text.setPreferredSize(new Dimension(120,20));
		parname_label = new JLabel("Parameter(Name)");
		parname_label.setPreferredSize(new Dimension(120,30));
		measure_label = new JLabel("Measure");
		parname_label.setPreferredSize(new Dimension(120,30));
		upper_panel.add(parname_label);
		upper_panel.add(parname_text);
		upper_panel.add(measure_label);
		upper_panel.add(measure_text);
		
		lower_panel = new JPanel();
		FlowLayout lower_Layout = new FlowLayout(30,50,30);
		lower_panel .setLayout(lower_Layout);
		add_Button = new JButton(ACTION_ADD);
		cancel_Button = new JButton(ACTION_CANCEL);
		lower_panel.add(add_Button);
		lower_panel.add(cancel_Button);
		add(upper_panel);
		add(lower_panel);
	}
	/**
	 * A sz�vegmez�k tartalm�t t�rli.
	 */
	public void clearText(){
		parname_text.setText("");
		measure_text.setText("");
	}
	/**
	 * A param�ternek megfelel� hiba�zenetet �rja ki dial�gusablakban.
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
	 * Visszaadja a jellemz� nev�t tartalmaz�  sz�veget.
	 * @return
	 */
	public String getParameterName(){
		return this.parname_text.getText();
	}
	/**
	 * Visszaadja a m�rt�kegys�g sz�vegmez� tartalm�t.
	 * @return
	 */
	public String getParameterMeasure(){
		return this.measure_text.getText();
	}
	/**
	 * A param�terben adott objektumot hozz�adja a ParameterFrame esem�nyeinek figyel�je(i)-hez. 
	 * @param par_action_listener
	 */
	public void addParameterActionListener(ActionListener par_action_listener){
		add_Button.addActionListener(par_action_listener);
		cancel_Button.addActionListener(par_action_listener);
	}
	

}
