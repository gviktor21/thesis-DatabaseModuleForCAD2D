package model;

/**
 * 
 * @author God� Viktor
 *Ez az oszt�ly �rja le a jellemz�t. A rajzelem �s a t�pus jellemz�it is.
 */
public class Parameter {
	int ID;
	String name;
	String measure;
	String defaultValue; 
	String value; 
	public Parameter(){
		
	}
	/**
	 * Konstruktor
	 * @param azon a jellemz� azonos�t�ja
	 * @param nev a jellemz� elnevez�se
	 * @param measure a jellemz� m�rt�kegys�ge
	 */
	public Parameter( int azon, String nev, String measure){
		ID =azon ;
		name =nev ;
		this.measure =measure;
	}
	/**
	 * 
	 * @param azon a jellemz� azonos�t�ja
	 * @param nev  a jellemz� elnevez�se
	 * @param measure a jellemz� m�rt�kegys�ge
	 * @param defaultValue a jellemz� alap�rt�ke (t�pusokn�l)
	 */
	public Parameter( int azon, String nev, String measure, String defaultValue){
		ID =azon ;
		name =nev ;
		this.measure =measure;
		this.defaultValue=defaultValue;
	}
	/**
	 * 
	 * @param nev a jellemz� elnevez�se
	 * @param measure a jellemz� m�rt�kegys�ge
	 */
	public Parameter(  String nev, String measure){
		name =nev ;
		this.measure =measure;
	}
	/**
	 * 
	 * @param azon a jellemz� azonos�t�ja
	 * @param nev a jellemz� neve
	 */
	public Parameter(int azon, String nev) {
		// TODO Auto-generated constructor stub
		ID =azon ;
		name =nev ;
	}
	/**
	 * Visszaadja a jellemz� azonos�t�j�t.
	 * @return
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Be�ll�tja a jellemz� azonos�t�j�t.
	 * @param ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
	/**
	 * Visszaadja a az adott jellemz� megnevez�s�t.
	 * @return
	 */
	public String getname() {
		return name;
	}
	/**
	 * Be�ll�tja a az adott jellemz� megnevez�s�t.
	 * @param name
	 */
	public void setname(String name) {
		this.name = name;
	}
	/**
	 * Visszaadja az adott jellemz� m�rt�kegys�g�t.
	 * @return
	 */
	public String getmeasure() {
		return measure;
	}
	/**
	 * Be�ll�tja a az adott jellemz� m�rt�kegys�g�t.
	 * @param measure
	 */
	public void setmeasure(String measure) {
		this.measure = measure;
	}
	/**
	 * Visszaadja a az adott jellemz� alap�rt�k�t.
	 * @return
	 */
	public String getdefaultValue() {
		return defaultValue;
	}
	/**
	 * Be�ll�tja a az adott jellemz� alap�rt�k�t.
	 * @param defaultValue
	 */
	public void setdefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	/**
	 * Visszaadja  az adott jellemz� �rt�k�t.
	 * @return
	 */
	public String getvalue() {
		return value;
	}
	/**
	 * Be�ll�tja a az adott jellemz� �rt�k�t.
	 * @param value
	 */
	public void setvalue(String value) {
		this.value = value;
	}
	/**
	 * Visszaad egy karakterl�ncokb�l �ll� t�mb�t a (t�pus)jellemz� adatair�l.
	 * @return
	 */
	public String[] getDataLine(){
		 String[] dataline = {Integer.toString(ID),name,measure,defaultValue};
		 return dataline;
	}
	
	public String toString(){
		//return " parname:"+this.name+" id:"+ this.ID +" measure: "+this.measure;
		return  this.name+"("+this.measure+")";
	}
	
}
