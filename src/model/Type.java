package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Godó Viktor
 * Ez az osztály írja le a típust.
 */
public class Type {
	int ID;
	String name;
	Map<Integer, Parameter> Parameterk;
	public Type(){
		
	}
	/**
	 * Konstruktor
	 * @param ident A típus adatbázis béli azonosítója
	 * @param name A típus neve
	 */
	public Type( int ident, String name){
		this.ID =ident ;
		this.name =name ;
		Parameterk = new HashMap<>();
	}
	/**
	 * Konstruktor
	 * @param name A típus neve
	 */
	public Type( String name){
		this.name =name ;
		Parameterk = new HashMap<>();
	}
	/**
	 * 
	 * @return Visszaadja a típus jellemzõinek halmazát.
	 */
	public Map<Integer, Parameter> getParameters() {
		return Parameterk;
	}
	/**
	 *  Lecseréli a típus jellemzõinek halmazát egy a paraméterben átadott másik halmazra.
	 * @param Parameterk jellemzõk  egy halmaza
	 */
	public void setParameters(Map<Integer, Parameter> Parameterk) {
		this.Parameterk = Parameterk;
	}
	/**
	 * Hozzáad egy jellemzõt a típus jellemzõinek a halmazához.
	 * @param Parameter jellemzõ objektum
	 */
	public void addParameter(Parameter Parameter){
		Parameterk.put(Parameter.ID, Parameter);
		//todo: hibakiiratÃ¡s ha mÃ¡r benne van
	}
	/**
	 * Törli a megadott jellemzõt a halmazból.
	 * @param Parameter törlendõ jellemzõ
	 */
	public void deleteParameter(Parameter Parameter){
		Parameterk.remove(Parameter.ID);
		//todo:Ã¼ressÃ©gi feltÃ©tel ha szÃ¼ksÃ©ges, csak benne lÃ©vÅ‘ Parameter elemre.
	}
	/**
	 * Visszaadja a típus azonosítóját.
	 * @return típus adatbázis béli azonosítója
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Beállítja a típus azonosítóját.
	 * @param ID típus adatbázis béli azonosítója
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
	/**
	 * Visszaadja a az adott típus elnevezését.
	 * @return típus neve
	 */
	public String getname() {
		return name;
	}
	/**
	 * Beállítja a típus nevét.
	 * @param name típus új neve
	 */
	public void setname(String name) {
		this.name = name;
	}
	/**
	 * Kiírja a típus paramétereit
	 */
	public void print_Parameterk(){
		Iterator it = Parameterk.values().iterator();

		while (it.hasNext()) {
		    System.out.println(it.next());
		}
	}
	/**
	 * Visszaad egy karakterláncot a típus jellemzõivel
	 * @return karakterlánc a típus jellemzõivel
	 */
	public String getParameterkString(){
		Iterator it = Parameterk.values().iterator();
		String Parameterk = new String();
		while (it.hasNext()) 
		{
			Parameter jel = (Parameter) it.next();
			String jelstring = jel.toString();
			Parameterk = Parameterk+jelstring+", ";
		}
		return Parameterk;
	}
	public String toString(){
		return " TYPE :"+this.name+" id:"+ this.ID;
		
	}
	public String[] getDataLine(){
		 String[] dataline = {Integer.toString(ID),name};
		 return dataline;
	}
	
}
