package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import model.Parameter;
/**
 * 
 * @author Godó Viktor
 *	A rajzelemekhez biztosít jellemzõbeállító párbeszédablakot.
 */
public class SetTypeParameterFrame extends JFrame {	 
	 JPanel mainPanel;
	 JPanel buttonPanel;
	 
	 protected ArrayList<JLabel> parname_labels;
	 protected ArrayList<JTextField> parameterValue_texts;
	 JButton b_ok;
	 JButton b_cancel;
	 JScrollPane scrollWin;
	 
	 public static final String ACTION_OK= "Ok";
	 public static final String ACTION_CANCEL = "Cancel";
	 Map<Integer,Parameter> typeparameters;
	 SetTypeParameterLayout mainPanelLayout;
	 
	 
	 /**
	  *Visszaad egy kollekciót ami a szövegmezõket tartalmazza. 
	  * @return
	  */
	public ArrayList<JTextField> getParameterValue_texts() {
		return parameterValue_texts;
	}
	/**
	 * Konstruktor
	 */
	public SetTypeParameterFrame() {
		super("DrawElementParameters");
		// TODO Auto-generated constructor stub
		BorderLayout fram_layout = new BorderLayout();
		this.setLayout(fram_layout);
		initComponents();
		
		parname_labels = new  ArrayList<JLabel>();
		parameterValue_texts = new  ArrayList<JTextField>();
		setSize(500,500);
		add(scrollWin,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.SOUTH);
	}
	/**
	 * Legyártja a felhasználói felület komponenseit.
	 */
	private void initComponents(){
		//todo Ã©rtelmesen Ã¡trendezni		
		b_ok = new JButton(ACTION_OK);
		b_cancel = new JButton(ACTION_CANCEL);
		
		mainPanel = new JPanel();
		scrollWin = new JScrollPane(mainPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanelLayout = new SetTypeParameterLayout(FlowLayout.CENTER,30,30);
		mainPanel.setLayout(mainPanelLayout);
		buttonPanel = new JPanel();
		FlowLayout buttonPanelLayout = new FlowLayout(FlowLayout.CENTER,50,30);
		buttonPanel.setLayout(buttonPanelLayout);
		buttonPanel.add(b_ok);
		buttonPanel.add(b_cancel);
	}
	/**
	 * Törli a fõ panel tartalmát és a komponenseket tartalmazó
kollekciókat.
	 */
	public void removeAllField(){
		parname_labels.clear();
		parameterValue_texts.clear();
		mainPanel.removeAll();
	}
	/**
	 * Megjeleníti a grafikus felületet.
	 */
	public void createAndShowGui(){
		setVisible(true);
	}
	/**
	 * Újrarajzolja a GUIt
	 */
	public void repaintMainPanel(){
		mainPanel.updateUI(); 
	}
	/**
	 * Hozzáad egy címke objektumot a grafikus felülethez.
	 * @param parname
	 */
	public void addParnameLabel(String parname){
		JLabel parname_label = new JLabel(parname);
		parname_label.setPreferredSize(new Dimension(120,20));
		parname_labels.add(parname_label);
		mainPanel.add(parname_label);
	}
	/**
	 * Hozzáad egy szövegmezõt a grafikus felülethez.
	 * @param defaultValue
	 */
	public void addParValueTextField(String defaultValue){
		JTextField parameterValue_text = new JTextField(20);
		if(defaultValue!=null && !defaultValue.equals("null")){
			parameterValue_text.setText(defaultValue);
		}
		parameterValue_texts.add(parameterValue_text);
		mainPanel.add(parameterValue_text);
	}
	
	/**
	 * Visszaad egy kollekciót, ami a címkéket tartalmazza.
	 * @return
	 */
	public ArrayList<JLabel> getParname_labels() {
		return parname_labels;
	}
	public void showErrorMessage(String errorText){
		JFrame frame = (JFrame)SwingUtilities.getRoot(this);
		JOptionPane.showMessageDialog(frame,
				errorText,
			    "Input error",
			    JOptionPane.ERROR_MESSAGE);
	}
	public void addSetTypeParameterFrameActionListener( ActionListener actionListener) {
		// TODO Auto-generated method stub
		b_ok.addActionListener(actionListener);
		b_cancel.addActionListener(actionListener);
	}
}
