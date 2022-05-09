/**
 * Class MainFrame
 * Author: Gabor Nemeth
 * Date: 2012-07-09
 * 
 * 
 * The main frame of the 2D CAD application.
 * 
 */ 
 package view;
 
 import javax.swing.*;
 import javax.swing.event.*;
 import java.awt.event.*;
 import java.util.*;
 import java.awt.*;
 
 import control.*;
 import view.event.*;
 
 public class MainFrame extends JFrame implements MouseMotionListener, ActionListener {
	 
	 public static final String EXIT_MENU_ITEM = "Exit";
	 public static final String NEW_MENU_ITEM = "New";
	 public static final String OPEN_MENU_ITEM = "Open";
	 public static final String SAVE_MENU_ITEM = "Save";
	 public static final String SAVEAS_MENU_ITEM = "SaveAs";
	 public static final String PRINT_MENU_ITEM = "Print";
	 
	 public static final String ZOOM_IN_MENU_ITEM = "ZoomIn";
	 public static final String ZOOM_OUT_MENU_ITEM = "ZoomOut";
	 public static final String FULL_SHEET_MENU_ITEM = "FullSheet";
	 public static final String NO_ZOOM_MENU_ITEM = "NoZoom";
	 public static final String DEFINE_TYPES_MENU_ITEM = "DefineTypes"; //Viktor
	 public static final String DEFINE_PARAMETERS_MENU_ITEM = "DefineParameters"; //Viktor
	 public static final String CREATE_CATALOG_MENU_ITEM = "CreateCatalog"; //Viktor
	 public static final String SAVE_DATABASE_MENU_ITEM="SaveDataBase";//Viktor
	 
	 
	 private JMenuBar menubar;
	 private JToolBar maintoolbar;
	 private JPanel mainPanel;
	 private JPanel infoPanel;
	 private JPanel toolPanel;
	 private JPanel attribPanel;
	 private JPanel mainToolPanel;
	 private JPanel subToolPanel;
	 
	 private JMenu fileMenu;
	 private JMenuItem exitMenuItem;
	 private JMenuItem newMenuItem;
	 private JMenuItem openMenuItem;
	 private JMenuItem saveMenuItem;
	 private JMenuItem saveasMenuItem;
	 private JMenuItem printMenuItem;
	 
	 private JMenu viewMenu;
	 private JMenuItem zoomInMenuItem;
	 private JMenuItem zoomOutMenuItem;
	 private JMenuItem noZoomMenuItem;
	 private JMenuItem fullSheetMenuItem;
	 
	 //Viktor
	 private IconButtonGroup iconButtonGroup;
	 private JMenu unitsMenu;
	 private JMenuItem defineType;
	 private JMenuItem defineParameter;
	 private JMenuItem createCatalog;
	 private JMenuItem saveDatabase;
	 DataBaseController db_ctrl;
	 IconButton iconButton1=new IconButton("icon/line.png");
	 IconButton iconButton2 = new IconButton("icon/cursor.png");
	 TypeFrame type_panel;
	 ParameterFrame par_frame;
	 CatalogFrame ctframe;
	 private SelectPopUpMenu selectPopUpMenu;
	 private SetTypeFrame setTypeFrame;
	 private SetTypeParameterFrame setTypeParameterFrame;
	 //Viktor end
	 
	 private DrawingCanvas canvas;
	 private ScrollPane scrollWin;
	
	 //private DrawingController drawingController;
	 
	 
	 private ArrayList<OptionEventListener> optionListeners;
	 
	 private JLabel mousePositionLabel;
	 private JLabel infoLabel;
	 private JLabel zoomLabel;
	 
	 
	 public MainFrame( DataBaseController db_ctrl) {
		 super("2D CAD");
		 this.setSize( 1280,768 );
		 this.setLocation( 20,20 );
		 this.db_ctrl = db_ctrl;
		 /* building the GUI */
		 this.createMenuBar();
		 this.buildGUI();	 
		 this.prepareActions();	 
		 this.createFrames();
		 selectPopUpMenu = new SelectPopUpMenu(setTypeFrame,setTypeParameterFrame);
	 }
	 
	 private void createFrames() {
		// TODO Auto-generated method stub
		 par_frame = new ParameterFrame();
		 type_panel = new TypeFrame();
		 ctframe = new CatalogFrame();
		 setTypeFrame = new SetTypeFrame();
		 setTypeParameterFrame = new SetTypeParameterFrame();
	}

	private void createMenuBar() {
		 menubar = new JMenuBar();
		 fileMenu = new JMenu( "File" );
		 viewMenu = new JMenu( "View" );
		 unitsMenu = new JMenu( "Units" );
		 
		 newMenuItem = new JMenuItem( "New ..." );
		 openMenuItem = new JMenuItem( "Open ..." );
		 saveMenuItem = new JMenuItem( "Save" );
		 saveasMenuItem = new JMenuItem( "Save As ..." );
		 printMenuItem = new JMenuItem( "Print ..." );
		 exitMenuItem = new JMenuItem( "Exit" );
		 
		 zoomInMenuItem = new JMenuItem( "Zoom In" );
		 zoomOutMenuItem = new JMenuItem( "Zoom Out" );
		 fullSheetMenuItem = new JMenuItem( "Full sheet" );
		 noZoomMenuItem = new JMenuItem( "100% Zoom" );
		 
		 defineType = new JMenuItem("Define Types");//Viktor
		 defineParameter = new JMenuItem("Define Parameter");//Viktor
		 createCatalog = new JMenuItem("Create Catalog");
		 saveDatabase = new JMenuItem("Save DataBase");
		 
		 newMenuItem.setEnabled(false);
		 openMenuItem.setEnabled(false);
		 saveMenuItem.setEnabled(false);
		 printMenuItem.setEnabled(false);
		 saveasMenuItem.setEnabled(false);
		 
		 //zoomInMenuItem.setEnabled(false);
		 //zoomOutMenuItem.setEnabled(false);
		 //fullSheetMenuItem.setEnabled(false);
		 //noZoomMenuItem.setEnabled(false);
		 
		 
		 fileMenu.add(newMenuItem);
		 fileMenu.add(openMenuItem);
		 fileMenu.add(saveMenuItem);
		 fileMenu.add(saveasMenuItem);
		 fileMenu.addSeparator();
		 fileMenu.add(printMenuItem);
		 fileMenu.addSeparator();
		 fileMenu.add(exitMenuItem);
		 
		 viewMenu.add(zoomInMenuItem);
		 viewMenu.add(zoomOutMenuItem);
		 viewMenu.add(fullSheetMenuItem);
		 viewMenu.add(noZoomMenuItem);
		 
		 unitsMenu.add(defineType);//Viktor
		 unitsMenu.add(defineParameter);//Viktor
		 unitsMenu.add(createCatalog);//Viktor
		 unitsMenu.add(saveDatabase);//Viktor

		 menubar.add(fileMenu);
		 menubar.add(viewMenu);
		 menubar.add(unitsMenu);//Viktor
		 
	
		 this.setJMenuBar(menubar);
		 
	 }
	 
	 private void buildGUI() {
		 //this.getContentPane().setLayout(new BorderLayout());
		 
		 mainPanel = new JPanel(new BorderLayout());
		 maintoolbar = new JToolBar();
		 infoPanel = new JPanel(new GridLayout(1,4));
		 toolPanel = new JPanel(new GridLayout(2,1));
		 attribPanel = new JPanel();
		 
		 mainToolPanel = new JPanel(new GridLayout(2,3));
		 subToolPanel = new JPanel(new GridLayout(3,3));
		 //mainToolPanel.setPreferredSize(new Dimension(90,60));
		 //mainToolPanel.setMaximumSize(new Dimension(90,60));
		 //subToolPanel.setPreferredSize(new Dimension(90,90));
		 //subToolPanel.setMaximumSize(new Dimension(90,90));
		 
		 
		
		 toolPanel.setPreferredSize(new Dimension(90,30));
		 toolPanel.setMaximumSize(new Dimension(90,30));
		 
		 toolPanel.add(mainToolPanel);
		 toolPanel.add(subToolPanel);
		 
		 this.getContentPane().add(mainPanel);
		 this.mainPanel.add(maintoolbar, BorderLayout.NORTH);
		 
		 this.mousePositionLabel = new JLabel("(0,0) mm");
		 this.infoLabel = new JLabel("Application started");
		 this.zoomLabel = new JLabel("Zoom: 100%");
		 
		 this.infoPanel.add(this.infoLabel);
		 this.infoPanel.add(this.mousePositionLabel);
		 this.infoPanel.add(this.zoomLabel);
		 this.mainPanel.add(infoPanel, BorderLayout.SOUTH);
		 this.mainPanel.add(toolPanel, BorderLayout.WEST);
		 
		 
		 this.canvas = new DrawingCanvas();
		 JPanel canvasPanel = new JPanel();
		 canvasPanel.add(canvas);
		 //JScrollPane scrollWin = new JScrollPane(canvasPanel);
		 scrollWin = new ScrollPane();
		 scrollWin.add(canvasPanel);
		 
		 mainPanel.add(scrollWin, BorderLayout.CENTER);
		 this.canvas.repaint();	
		 
		 iconButtonGroup = new IconButtonGroup(); //Viktor
		 //iconButton1 = new IconButton("icon/line.png");
		 iconButtonGroup.add(iconButton1);
		 iconButtonGroup.add(iconButton2);
		 mainToolPanel.add(iconButton1);
		 mainToolPanel.add(iconButton2);
		 mainToolPanel.add(Box.createHorizontalGlue());
		 mainToolPanel.add(Box.createHorizontalGlue());		 
	 }
	 public IconButton getDrawIconButton(){
		 return this.iconButton1;
	 }
	 public IconButton getSelectIconButton(){
		 return this.iconButton2;
	 }
	 
	 public void prepareActions() {
		 
		 
		 this.newMenuItem.setActionCommand(MainFrame.NEW_MENU_ITEM);
		 this.openMenuItem.setActionCommand(MainFrame.OPEN_MENU_ITEM);
		 this.saveMenuItem.setActionCommand(MainFrame.SAVE_MENU_ITEM);
		 this.saveasMenuItem.setActionCommand(MainFrame.SAVEAS_MENU_ITEM);
		 this.printMenuItem.setActionCommand(MainFrame.PRINT_MENU_ITEM);
		 this.exitMenuItem.addActionListener(this);
		 this.exitMenuItem.setActionCommand(MainFrame.EXIT_MENU_ITEM);
		 
		 this.zoomInMenuItem.addActionListener(this);
		 this.zoomInMenuItem.setActionCommand(MainFrame.ZOOM_IN_MENU_ITEM);
		 this.zoomOutMenuItem.addActionListener(this);
		 this.zoomOutMenuItem.setActionCommand(MainFrame.ZOOM_OUT_MENU_ITEM);
		 this.noZoomMenuItem.addActionListener(this);
		 this.noZoomMenuItem.setActionCommand(MainFrame.NO_ZOOM_MENU_ITEM);
		 this.fullSheetMenuItem.addActionListener(this);
		 this.fullSheetMenuItem.setActionCommand(MainFrame.FULL_SHEET_MENU_ITEM);
		 this.defineType.addActionListener(this);
		 this.defineType.setActionCommand(MainFrame.DEFINE_TYPES_MENU_ITEM);
		 this.defineParameter.addActionListener(this);
		 this.defineParameter.setActionCommand(MainFrame.DEFINE_PARAMETERS_MENU_ITEM);
		 this.createCatalog.addActionListener(this);
		 this.createCatalog.setActionCommand(MainFrame.CREATE_CATALOG_MENU_ITEM);
		 this.saveDatabase.addActionListener(this);
		 this.saveDatabase.setActionCommand(MainFrame.SAVE_DATABASE_MENU_ITEM);
		 this.canvas.addMouseMotionListener(this);
		 //this.canvas.addMouseListener(drawingController);
		 
	 }
	 
	 public DrawingCanvas getDrawingCanvas() {
		 return this.canvas;
	 }
	 
	 
	 /* ************************************ */
	 /* mouse motion listener implementation */
	 /* ************************************ */
	 
	 public void mouseDragged(MouseEvent e) {
	 }
	 
	 public void mouseMoved(MouseEvent e) {
		
		 //int x = Float.valueOf(e.getX() / (this.canvas.getResolutionMM() * this.canvas.getZoom()) + 0.5F ).intValue();
		 //int y = Float.valueOf(e.getY() / (this.canvas.getResolutionMM() * this.canvas.getZoom()) + 0.5F ).intValue();
		 
		 int x =Math.round( e.getX() / (this.canvas.getResolutionMM() * this.canvas.getZoom()));
		 int y =Math.round( e.getY() / (this.canvas.getResolutionMM() * this.canvas.getZoom()));
		 
		 this.mousePositionLabel.setText( "(" + x + "," + y + ") mm" );
		 this.mousePositionLabel.repaint();
	 }
	 
	 
	 /* ************************************ */
	 /*    action listener implementation    */
	 /* ************************************ */
	 public void actionPerformed(ActionEvent e) {
		 if ( e.getActionCommand() == MainFrame.EXIT_MENU_ITEM ) {
			 System.exit(0);
		 } else if ( e.getActionCommand() == MainFrame.ZOOM_IN_MENU_ITEM ) {
			 java.awt.Point scrollPos = this.scrollWin.getScrollPosition();
			 double scrollX = scrollPos.getX();
			 double scrollY = scrollPos.getY();
			 this.scrollWin.remove(this.canvas);
			 this.canvas.zoomIn();
			 this.scrollWin.add(this.canvas);
			 int zoomScrollX = Double.valueOf( this.canvas.getZoom() * scrollX ).intValue();
			 int zoomScrollY = Double.valueOf( this.canvas.getZoom() * scrollY ).intValue();
			 this.scrollWin.setScrollPosition(zoomScrollX, zoomScrollY);
			 this.scrollWin.repaint();
			 this.zoomLabel.setText("Zoom: "+ (int) (this.canvas.getZoom() * 100) + "%");
			 
		 } else if ( e.getActionCommand() == MainFrame.ZOOM_OUT_MENU_ITEM ) {
			 java.awt.Point scrollPos = this.scrollWin.getScrollPosition();
			 double scrollX = scrollPos.getX();
			 double scrollY = scrollPos.getY();
			 this.scrollWin.remove(this.canvas);
			 this.canvas.zoomOut();
			 this.scrollWin.add(this.canvas);
			 int zoomScrollX = Double.valueOf( this.canvas.getZoom() * scrollX ).intValue();
			 int zoomScrollY = Double.valueOf( this.canvas.getZoom() * scrollY ).intValue();
			 this.scrollWin.setScrollPosition(zoomScrollX, zoomScrollY);
			 this.scrollWin.repaint();
			 this.zoomLabel.setText("Zoom: "+ (int) (this.canvas.getZoom() * 100) + "%");
			 
		 } else if ( e.getActionCommand() == MainFrame.FULL_SHEET_MENU_ITEM ) {
			 java.awt.Point scrollPos = this.scrollWin.getScrollPosition();
			 double scrollX = scrollPos.getX();
			 double scrollY = scrollPos.getY();
			 this.canvas.fullSheetZoom();
			 this.scrollWin.remove(this.canvas);
			 this.scrollWin.add(this.canvas);
			 //int zoomScrollX = Double.valueOf( this.canvas.getZoom() * scrollX ).intValue();
			 //int zoomScrollY = Double.valueOf( this.canvas.getZoom() * scrollY ).intValue();
			 this.scrollWin.setScrollPosition(0, 0);
			 this.zoomLabel.setText("Zoom: "+ (int) (this.canvas.getZoom() * 100) + "%");
			 
			 
		 } else if ( e.getActionCommand() == MainFrame.NO_ZOOM_MENU_ITEM ) {
			 java.awt.Point scrollPos = this.scrollWin.getScrollPosition();
			 double scrollX = scrollPos.getX();
			 double scrollY = scrollPos.getY();
			 this.scrollWin.remove(this.canvas);
			 this.canvas.noZoom();
			 this.scrollWin.add(this.canvas);
			 int zoomScrollX = Double.valueOf( this.canvas.getZoom() * scrollX ).intValue();
			 int zoomScrollY = Double.valueOf( this.canvas.getZoom() * scrollY ).intValue();
			 this.scrollWin.setScrollPosition(zoomScrollX, zoomScrollY);
			 this.zoomLabel.setText("Zoom: "+ (int) (this.canvas.getZoom() * 100) + "%");
		 }else if ( e.getActionCommand() == MainFrame.DEFINE_TYPES_MENU_ITEM ) {
			 type_panel.createAndShowGui();
		 }else if ( e.getActionCommand() == MainFrame.DEFINE_PARAMETERS_MENU_ITEM ) {
			 par_frame.createAndShowGui(); 
		 }else if( e.getActionCommand() == MainFrame.CREATE_CATALOG_MENU_ITEM){
			 ctframe.createAndShowGui();
		 }else if( e.getActionCommand() == MainFrame.SAVE_DATABASE_MENU_ITEM){
			 String path = null;
			 db_ctrl.SaveDataBase(path);
		 }
	 }
	 
	 public void addOptionEventListener(OptionEventListener listener) {
		this.optionListeners.add(listener);
	}
	
	public void fireOptionEvent(OptionEvent e) {
		int nlisteners = this.optionListeners.size();
		for ( int i = 0; i < nlisteners; i++ ) {
			optionListeners.get(i).optionChanged(e);
		}
	}

	public TypeFrame getType_panel() {
		return type_panel;
	}


	public ParameterFrame getPar_frame() {
		return par_frame;
	}

	public CatalogFrame getCtframe() {
		return ctframe;
	}

	public SelectPopUpMenu getSelectPopUpMenu() {
		return selectPopUpMenu;
	}

	public SetTypeFrame getSetTypeFrame() {
		return setTypeFrame;
	}

	public SetTypeParameterFrame getSetTypeParameterFrame() {
		return setTypeParameterFrame;
	}
	 	
 }
