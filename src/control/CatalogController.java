package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFileChooser;

import model.DrawingElement;
import model.Parameter;
import model.Type;
import view.CatalogFrame;
import view.MainFrame;
/**
 * 
 * @author Godó Viktor
 * Az alkatrész katalógust elkészítését, HTML fájlba elmentését és a CatalogFrame-et kezeli.
 */
public class CatalogController implements ActionListener,WindowListener {
	private final static String LINE_SEPARATOR="\n";
	//private final static String BLOCK_SEPARATOR=LINE_SEPARATOR+"========================================================="+LINE_SEPARATOR ;
	DataBaseController db_ctrl;
	CatalogFrame catalogView;
	Map<Integer, Type> types ; 
	Map<Integer, DrawingElement> drawElements;
	File catalog_HtmlFile;
	File catalog_CssFile;
	int currenterrors;
	static String STR_ERROR_ACCESS_DENIED="Save operation failed.Please choose an another directory.";
	static String STR_ERROR_LOAD_FAIL="Could not load the catalog file.Please use export.";
	CatalogController(DataBaseController dbctrl, MainFrame view){
		currenterrors=0;
		catalog_HtmlFile = new File("output.html");
		catalog_CssFile = new File("output.css");
		this.db_ctrl=dbctrl;
		catalogView = view.getCtframe();
		catalogView.addCatalogFrameActionListener(this);
		catalogView.addWindowListener(this);
		types = new HashMap<Integer,Type>();
		drawElements = new HashMap<Integer,DrawingElement>();
	}
	
	private void saveToFile() throws IOException{
		saveStyle();
		File outputFile =catalog_HtmlFile;
		types=db_ctrl.getTypes();
		drawElements = db_ctrl.getDrawingElements();
		try {
			FileOutputStream fos = new FileOutputStream(outputFile);
			BufferedWriter bwr = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
			bwr.write("<html>");
			bwr.newLine();
			bwr.write("<head>");
			bwr.write("<h1>Alkatrészkatalógus</h1>");
			bwr.newLine();
			bwr.write("<meta http-equiv="+"\""+"Content-Type "+ "\""+"content="+"\""+"text/html; charset=utf-8"+"\"" +"/>");
			bwr.write("<link href="+"\""+"output.css"+"\""+"rel="+"\""+"stylesheet"+"\"" +" type=" +"\""+"text/css"+"\"");
			bwr.write("</head>");
			bwr.newLine();
			bwr.write("<body>");
			bwr.newLine();
			Iterator it = types.values().iterator();
			while(it.hasNext()){
				model.Type t = (model.Type) it.next();
				bwr.write("<h2>"+"Típus: "+t.getname()+"</h2>");
				bwr.write("<table border=0  cellspacing='0' align="+"\""+"center"+"\""+">");
				bwr.newLine();
				//1.sor
				bwr.write("<tr>");
				bwr.write("<th>"+t.getID()+"</th>");
				bwr.write("<th>"+t.getname()+"</th>");
				t=db_ctrl.getTypeParameters(t);
				Map<Integer,Parameter> Parameterk = t.getParameters();
				Iterator it2 = Parameterk.values().iterator();
				while(it2.hasNext()){
					Parameter j = (Parameter)it2.next();
					bwr.write("<th>"+j.getname()+"("+j.getmeasure()+")</th>");
				}
				bwr.write("</tr>");
				//1-n-ik sor
				Iterator it3 = drawElements.values().iterator();
				int itemcount=0;
				while(it3.hasNext()){
					DrawingElement rajz= (DrawingElement) it3.next();
					model.Type rajzType = rajz.getType();
					if(rajzType !=null && rajzType.getID()==t.getID()){
						bwr.write("<tr>");
						bwr.write("<td>"+rajz.getID()+"</td>");
						bwr.write("<td>"+rajz.getname()+"</td>");
						
						DrawingElement temp =db_ctrl.getDrawingElementParameters(rajz);
						Iterator it4 = temp.getParameters().values().iterator();
						itemcount++;
						while(it4.hasNext()){
							Parameter j = (Parameter)it4.next();
							bwr.write("<td>"+j.getvalue()+"</td>");
						}
						bwr.write("</tr>");
						
					}

				}
				bwr.write("<tr>");
				bwr.write("<td>"+"Number of elements:"+itemcount+"</td>");
				bwr.write("</tr>");
				bwr.write("</table>");
			}
			bwr.newLine();
			bwr.write("</body>");
			bwr.newLine();

			bwr.write("</html>");
			bwr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IOException(e);
		}
		
	}
	
	private void saveStyle() throws IOException{
		File outputFile = catalog_CssFile;
		try {
			FileOutputStream fos = new FileOutputStream(outputFile);
			BufferedWriter bwr = new BufferedWriter(new OutputStreamWriter(fos));
			bwr.write("h1{margin: 0px; margin-bottom: 10px;text-align:center;font-size:75px;}");
			bwr.newLine();
			bwr.write("h2{margin: 0px;font-size:35px;}");
			bwr.newLine();
			bwr.write("table a:link {color: #666;font-weight: bold;text-decoration:none;}");
			bwr.newLine();
			bwr.write("table a:link {color: #666;font-weight: bold;text-decoration:none;}");
			bwr.newLine();
			bwr.write("table a:visited {color: #999999;font-weight:bold;text-decoration:none;}");
			bwr.newLine();
			bwr.write("table a:active,");
			bwr.newLine();
			bwr.write("table a:hover {color: #bd5a35;text-decoration:underline;}");
			bwr.newLine();
			bwr.write("table {font-family:Arial, Helvetica, sans-serif;color:#666;font-size:12px;text-shadow: 1px 1px 0px #fff;background:#eaebec;margin-bottom: 30px;border:#ccc 1px solid;");
			bwr.write("-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;-moz-box-shadow: 0 1px 2px #d1d1d1;-webkit-box-shadow: 0 1px 2px #d1d1d1;box-shadow: 0 1px 2px #d1d1d1;}");
			bwr.newLine();
			bwr.write("table th {padding:21px 25px 22px 25px;border-top:1px solid #fafafa;border-bottom:1bwr.newLine();px solid #e0e0e0;background: #ededed;background: -webkit-gradient(linear, left top, left bottom, from(#ededed), to(#ebebeb));background: -moz-linear-gradient(top,  #ededed,  #ebebeb);}");
			bwr.newLine();
			bwr.write("table th:first-child{text-align: left;padding-left:20px;}");
			bwr.newLine();
			bwr.write("table tr:first-child th:first-child{-moz-border-radius-topleft:3px;-webkit-border-top-left-radius:3px;border-top-left-radius:3px;}");
			bwr.newLine();
			bwr.write("table tr:first-child th:last-child{-moz-border-radius-topright:3px;-webkit-border-top-right-radius:3px;border-top-right-radius:3px;}");
			bwr.newLine();
			bwr.write("table tr{text-align: center;padding-left:20px;}");
			bwr.newLine();
			bwr.write("table tr td:first-child{text-align: left;padding-left:20px;border-left: 0;}");
			bwr.newLine();
			bwr.write("table tr td {padding:18px;border-top: 1px solid #ffffff;border-bottom:1px solid #e0e0e0;border-left: 1px solid #e0e0e0;background: #fafafa;background: -webkit-gradient(linear, left top, left bottom, from(#fbfbfb), to(#fafafa));background: -moz-linear-gradient(top,  #fbfbfb,  #fafafa);}");
			bwr.newLine();
			bwr.write("table tr.even td{background: #f6f6f6;background: -webkit-gradient(linear, left top, left bottom, from(#f8f8f8), to(#f6f6f6));background: -moz-linear-gradient(top,  #f8f8f8,  #f6f6f6);}");
			bwr.newLine();
			bwr.write("table tr:last-child td{border-bottom:0;}");
			bwr.newLine();
			bwr.write("table tr:last-child td:first-child{-moz-border-radius-bottomleft:3px;-webkit-border-bottom-left-radius:3px;border-bottom-left-radius:3px;}");
			bwr.newLine();
			bwr.write("table tr:last-child td:last-child{-moz-border-radius-bottomright:3px;-webkit-border-bottom-right-radius:3px;border-bottom-right-radius:3px;}");
			bwr.newLine();
			bwr.write("table tr:hover td{background: #f2f2f2;background: -webkit-gradient(linear, left top, left bottom, from(#f2f2f2), to(#f0f0f0));background: -moz-linear-gradient(top,  #f2f2f2,  #f0f0f0);}");
			bwr.write("</style>");
			bwr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IOException(e);
		}
	}

	public void action_export(){
		JFileChooser fileChooser = catalogView.getFileChooser();
		if (fileChooser.showSaveDialog(catalogView.getComponent(0)) == JFileChooser.APPROVE_OPTION) {
			this.catalog_HtmlFile= fileChooser.getSelectedFile();
			if(!catalog_HtmlFile.getName().endsWith(".html")){
				File temp = new File(catalog_HtmlFile.getAbsolutePath()+".html");
				catalog_HtmlFile=temp;
			}
		  // save to file
			this.catalog_CssFile =  new File(catalog_HtmlFile.getParent() + File.separator+"output.css");
			try{
				saveToFile();
				currenterrors=0;
			}catch(IOException ioe){
				catalogView.showErrorMessage(STR_ERROR_LOAD_FAIL);
				currenterrors++;
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(CatalogFrame.ACTION_EXPORT)){
			action_export();
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		try {
			saveToFile();
			currenterrors=0;
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			if(currenterrors==0)catalogView.showErrorMessage(STR_ERROR_ACCESS_DENIED);
			currenterrors++;
		}
		catalogView.setEditorPane(catalog_HtmlFile);
		catalogView.repaint();
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
