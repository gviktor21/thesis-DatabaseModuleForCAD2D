package model;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class DrawingElement
 * Author: Gabor Nemeth
 * Date: 2012-07-09
 * 
 * All of the drawing primitives are extended from this class. It contains all
 * the property of the drawing primitives. 
 * 
 */ 

public class DrawingElement {

	private String id;
	
	
	/* line attributes and styles */
	private int lineEnd;
	private int lineJoin;
	private float lineWidth;
	private float[] dash;
	private float dashPhase;
	private Color color;
	private int layerID;
	//viktor
	private int db_ID;
	private String elnevezes;
	private Map<Integer, Parameter> Parameterk;
	private Type Type;
	//Viktor end
	/**
	 * Constructor. Set the default values of the drawing properties.
	 * 
	 */ 
	public DrawingElement() {
		lineEnd = java.awt.BasicStroke.CAP_BUTT;
		lineJoin = java.awt.BasicStroke.JOIN_MITER;
		lineWidth = 2.0F;
		dash = new float[1];
		dash[0] = 1;
		dashPhase = 0;
		color = Color.BLACK;
		this.layerID = 0;
		Type = null; //Viktor
		Parameterk = new HashMap<>();
	}
	/**
	 * Konstruktor
	 * @param id A rajzelem adatbázisbéli azonosítója
	 * @param name A rajzelem elnevezése
	 * @param t A rajzelem típusa
	 */
	public DrawingElement(int id, String name, Type t) {
		// TODO Auto-generated constructor stub
		this.id=id+"";
		this.elnevezes=name;
		this.Type=t;
		Parameterk = new HashMap<>();
	}

	/**
	 * Konstruktor
	 * @param id A rajzelem adatbázisbéli azonosítója
	 * @param name A rajzelem elnevezése
	 */
	public DrawingElement(int id, String name) {
		// TODO Auto-generated constructor stub
		this.id=id+"";
		this.elnevezes=name;
		Parameterk = new HashMap<>();
	}

	/**
	 * Returns with the line-end style of the drawing element.
	 * @return lineEnd
	 */
	public int getLineEnd() {
		return lineEnd;
	}
	
	
	/**
	 * Returns with the line-join style of the drawing element.
	 * @return lineJoin
	 */
	public int getLineJoin() {
		return lineJoin;
	}
	
	
	/**
	 * Returns with the linewidth of the drawing element.
	 * @return lineWidth
	 */
	public float getLineWidth() {
		return lineWidth;
	}
	
	
	/**
	 * Returns with the dashing pattern of the drawing element.
	 * @return dash
	 */
	public float[] getDash() {
		return dash;
	}
	
	
	/**
	 * Returns with the offset of the dashing pattern.
	 * @return dashPhase
	 */
	public float getDashPhase() {
		return dashPhase;
	}

	
	/**
	 * Returns with the color of the drawing element.
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Returns with the ID of the drawing element. Each drawing element has a unique ID.
	 * @return id
	 */
	public String getID() {
		return id;
	}

	/**
	 * Set the end type of the lines.
	 * @param endType is the given end type;
	 */ 
	public void setLineEnd(int endType) {
		this.lineEnd = endType;
	}
	
	/**
	 * Set the join type of the lines.
	 * @param joinType is the given end type;
	 */ 
	public void setLineJoin(int joinType) {
		this.lineJoin = joinType;
	}
	
	
	/**
	 * Set the line width.
	 * @param lineWidth is the given line width;
	 */ 
	public void setLineWidth(float lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	
	/**
	 * Set the dashing patterns. In the dashing patterns alternate the opaque and the transparent segments. The pattern starts with an opaqu segment.
	 * @param dash is the array of the dashing pattern.;
	 * 
	 */ 
	public void setDash(float[] dash) {
		this.dash = dash;
	}
	
	/**
	 * Set the dash phase of the dashing patterns. Although the dashing phase is given, we can tell an offset, where have to start to draw the 
	 * dashing pattern.
	 * @param dashPhase is the offset of the dashing pattern.;
	 * 
	 */ 
	public void setDashPhase(float dashPhase) {
		this.dashPhase = dashPhase;
	}

	/**
	 * Set the line color. The default color is black.
	 * @param new_color is the new color of the line.;
	 */ 
	public void setColor(Color new_color) {
		this.color = new_color;
	}
	
	
	/**
	 * Set the ID of the drawing element.
	 * @param id is the unique identifier of the drawing element;
	 */ 
	public void setID(String id) {
		this.id = id;
	}
	
	
	/**
	 * Set the layer ID. 
	 * @param lid is the layer ID.
	 */
	public void setLayerID(int lid) {
		this.layerID = lid;
	}
	
	/**
	 * Returns the layer ID. 
	 * @return the layer ID.
	 */
	public int getLayerID() {
		return this.layerID;
	}
	
	
	//Viktor
	/**
	 * Hozzáad egy jellemzõt a rajzelem jellemzõinek a halmazához.
	 * @param Parameter
	 */
	public void addParameter(Parameter Parameter){
		Parameterk.put(Parameter.ID, Parameter);
	}
	/**
	 * Töröl egy jellemzõt a rajzelem jellemzõinek a halmazából.
	 * @param Parameter
	 */
	public void deleteParameter(Parameter Parameter){
		Parameterk.remove(Parameter.ID);
	}
	public int getdb_ID() {
		return db_ID;
	}
	public void setdb_ID(int ID) {
		this.db_ID = ID;
	}
	/**
	 * Visszaadja a az adott rajzelem elnevezését.
	 * @return
	 */
	public String getname() {
		return elnevezes;
	}
	/**
	 * Beállítja a típus nevét.
	 * @param elnevezes
	 */
	public void setname(String elnevezes) {
		this.elnevezes =this.getID()+"_"+elnevezes;
	}
	/**
	 * Visszaadja a rajzelem jellemzõinek halmazát.
	 * @return
	 */
	public Map<Integer, Parameter> getParameters() {
		return Parameterk;
	}
	/**
	 * Lecseréli a jellemzõk halmazát egy a paraméterben átadott másik halmazra.
	 * @param Parameterk
	 */
	public void setParameters(Map<Integer, Parameter> Parameterk) {
		this.Parameterk = Parameterk;
	}
	/**
	 * Visszaadja a rajzelem típusát.
	 * @return
	 */
	public Type getType() {
		return Type;
	}
	/**
	 * Beállítja a rajzelem típusát.
	 * @param Type
	 */
	public void setType(Type Type) {
		this.Type = Type;
	}
	public String toString(){
		return elnevezes;
		
	}
	/**
	 * Visszad egy karakterláncot a jellemzõ paramétereivel.
	 * @return
	 */
	public String getParameterkString(){
		Iterator it = Parameterk.values().iterator();
		String Parameterk = new String();
		while (it.hasNext()) 
		{
			Parameter jel = (Parameter) it.next();
			String jelstring = jel.toString()+ "ertek:"+jel.getvalue() ;
			Parameterk = Parameterk+jelstring+", ";
		}
		return Parameterk;
	}

}
