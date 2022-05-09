package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;

import model.Parameter;
import model.DrawingElement;
import model.Type;
import control.DataBaseController;
/**
 * A katalógus megjelenítésére, és exportálására szolgáltat grafikus gelületet.
 * @author Godó Viktor
 *
 */
public class CatalogFrame  extends JFrame{
	private JEditorPane editorPane;
	DataBaseController db_ctrl;
	Map<Integer, model.Type> types ; 
	Map<Integer, DrawingElement> drawElements;
	JButton b_save;
	File catalog_HtmlFile;
	File catalog_CssFile;
	JScrollPane scroll;
	JFileChooser fileChooser;
	FileFilter html_filter;
	public final static String LINE_SEPARATOR="\n";
	public final static String BLOCK_SEPARATOR=LINE_SEPARATOR+"========================================================="+LINE_SEPARATOR ;
	public final static String ACTION_EXPORT="Export";

	public JFileChooser getFileChooser() {
		return fileChooser;
	}
	
	public CatalogFrame(){
		super("PartsCatalog");
		setLayout( new BorderLayout() );
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize( new Dimension(500,500) );
		b_save = new JButton("Export");
		b_save.setActionCommand(ACTION_EXPORT);
        //addTypes();
       // saveToFile();
        //setEditorPane();
		fileChooser = new JFileChooser();
		html_filter = new FileNameExtensionFilter(".html","Html files");
		fileChooser.setFileFilter(html_filter);
		
		editorPane = new JEditorPane();
		editorPane.setContentType("text/html;charset=UTF-8");
		editorPane.setEditable(false);
        scroll = new JScrollPane(editorPane);
        this.add( scroll, BorderLayout.CENTER );
        this.add(b_save, BorderLayout.SOUTH);
	}	

	public void createAndShowGui(){
		setVisible(true);
	}
	
	public void setEditorPane(File file){
		System.out.println("Create Catalog");
        try {
        	Document doc = editorPane.getDocument();
        	doc.putProperty(Document.StreamDescriptionProperty, null);
        	editorPane.setPage(file.toURI().toURL());
        } catch (IOException e) {
        	//showErrorMessage("Attempted to read a bad URL: ");
        }
        scroll.revalidate();
        scroll.repaint();
        editorPane.revalidate();
        editorPane.repaint();
	}
	
	public void showErrorMessage(String errorText){
		JFrame frame = (JFrame)SwingUtilities.getRoot(this);
		JOptionPane.showMessageDialog(frame,
				errorText,
			    "Input error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public void addCatalogFrameActionListener(ActionListener action_listener){
		b_save.addActionListener(action_listener);
	}
}
