package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author God� Viktor
 * Ez az oszt�ly �rja le a t�pust.
 */
public class Type {
	int ID;
	String name;
	Map<Integer, Parameter> Parameterk;
	public Type(){
		
	}
	/**
	 * Konstruktor
	 * @param ident A t�pus adatb�zis b�li azonos�t�ja
	 * @param name A t�pus neve
	 */
	public Type( int ident, String name){
		this.ID =ident ;
		this.name =name ;
		Parameterk = new HashMap<>();
	}
	/**
	 * Konstruktor
	 * @param name A t�pus neve
	 */
	public Type( String name){
		this.name =name ;
		Parameterk = new HashMap<>();
	}
	/**
	 * 
	 * @return Visszaadja a t�pus jellemz�inek halmaz�t.
	 */
	public Map<Integer, Parameter> getParameters() {
		return Parameterk;
	}
	/**
	 *  Lecser�li a t�pus jellemz�inek halmaz�t egy a param�terben �tadott m�sik halmazra.
	 * @param Parameterk jellemz�k  egy halmaza
	 */
	public void setParameters(Map<Integer, Parameter> Parameterk) {
		this.Parameterk = Parameterk;
	}
	/**
	 * Hozz�ad egy jellemz�t a t�pus jellemz�inek a halmaz�hoz.
	 * @param Parameter jellemz� objektum
	 */
	public void addParameter(Parameter Parameter){
		Parameterk.put(Parameter.ID, Parameter);
		//todo: hibakiiratás ha már benne van
	}
	/**
	 * T�rli a megadott jellemz�t a halmazb�l.
	 * @param Parameter t�rlend� jellemz�
	 */
	public void deleteParameter(Parameter Parameter){
		Parameterk.remove(Parameter.ID);
		//todo:ürességi feltétel ha szükséges, csak benne lévő Parameter elemre.
	}
	/**
	 * Visszaadja a t�pus azonos�t�j�t.
	 * @return t�pus adatb�zis b�li azonos�t�ja
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Be�ll�tja a t�pus azonos�t�j�t.
	 * @param ID t�pus adatb�zis b�li azonos�t�ja
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
	/**
	 * Visszaadja a az adott t�pus elnevez�s�t.
	 * @return t�pus neve
	 */
	public String getname() {
		return name;
	}
	/**
	 * Be�ll�tja a t�pus nev�t.
	 * @param name t�pus �j neve
	 */
	public void setname(String name) {
		this.name = name;
	}
	/**
	 * Ki�rja a t�pus param�tereit
	 */
	public void print_Parameterk(){
		Iterator it = Parameterk.values().iterator();

		while (it.hasNext()) {
		    System.out.println(it.next());
		}
	}
	/**
	 * Visszaad egy karakterl�ncot a t�pus jellemz�ivel
	 * @return karakterl�nc a t�pus jellemz�ivel
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
