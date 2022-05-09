package model;

/**
 * 
 * @author Godó Viktor
 *Ez az osztály írja le a jellemzõt. A rajzelem és a típus jellemzõit is.
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
	 * @param azon a jellemzõ azonosítója
	 * @param nev a jellemzõ elnevezése
	 * @param measure a jellemzõ mértékegysége
	 */
	public Parameter( int azon, String nev, String measure){
		ID =azon ;
		name =nev ;
		this.measure =measure;
	}
	/**
	 * 
	 * @param azon a jellemzõ azonosítója
	 * @param nev  a jellemzõ elnevezése
	 * @param measure a jellemzõ mértékegysége
	 * @param defaultValue a jellemzõ alapértéke (típusoknál)
	 */
	public Parameter( int azon, String nev, String measure, String defaultValue){
		ID =azon ;
		name =nev ;
		this.measure =measure;
		this.defaultValue=defaultValue;
	}
	/**
	 * 
	 * @param nev a jellemzõ elnevezése
	 * @param measure a jellemzõ mértékegysége
	 */
	public Parameter(  String nev, String measure){
		name =nev ;
		this.measure =measure;
	}
	/**
	 * 
	 * @param azon a jellemzõ azonosítója
	 * @param nev a jellemzõ neve
	 */
	public Parameter(int azon, String nev) {
		// TODO Auto-generated constructor stub
		ID =azon ;
		name =nev ;
	}
	/**
	 * Visszaadja a jellemzõ azonosítóját.
	 * @return
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Beállítja a jellemzõ azonosítóját.
	 * @param ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
	/**
	 * Visszaadja a az adott jellemzõ megnevezését.
	 * @return
	 */
	public String getname() {
		return name;
	}
	/**
	 * Beállítja a az adott jellemzõ megnevezését.
	 * @param name
	 */
	public void setname(String name) {
		this.name = name;
	}
	/**
	 * Visszaadja az adott jellemzõ mértékegységét.
	 * @return
	 */
	public String getmeasure() {
		return measure;
	}
	/**
	 * Beállítja a az adott jellemzõ mértékegységét.
	 * @param measure
	 */
	public void setmeasure(String measure) {
		this.measure = measure;
	}
	/**
	 * Visszaadja a az adott jellemzõ alapértékét.
	 * @return
	 */
	public String getdefaultValue() {
		return defaultValue;
	}
	/**
	 * Beállítja a az adott jellemzõ alapértékét.
	 * @param defaultValue
	 */
	public void setdefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	/**
	 * Visszaadja  az adott jellemzõ értékét.
	 * @return
	 */
	public String getvalue() {
		return value;
	}
	/**
	 * Beállítja a az adott jellemzõ értékét.
	 * @param value
	 */
	public void setvalue(String value) {
		this.value = value;
	}
	/**
	 * Visszaad egy karakterláncokból álló tömböt a (típus)jellemzõ adatairól.
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
