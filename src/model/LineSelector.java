package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author God� Viktor
 *Az oszt�ly arra szolg�l megold�si m�dokat, kiv�laszt�skor a vonal (Line) rajzelemre kattintva, ha egyszerre t�bb vonalat �rint a kiv�laszt�s, akkor mely vonal ker�lj�n bele a kijel�lt rajzelemek k�z�. 
 */
public class LineSelector {
	public enum LineSelectMode{SELECT_THE_NEAREST,SELECT_THE_NEWEST}
	private LineSelectMode mode;
	private float distance;
	public LineSelectMode getMode() {
		return mode;
	}
	/**
	 * Vonal kiv�laszt�si m�dot �ll�tja be.
	 * @param mode
	 */
	public void setMode(LineSelectMode mode) {
		this.mode = mode;
	}
	/**
	 * Konstruktor
	 * @param mode Kiv�laszt�si m�d.
	 */
	public LineSelector(LineSelectMode mode){
		distance=999.0f;
		this.mode=mode;
	}
	/**
	 * A  param�terben kapott vonalak k�z�l a legk�zelebbi ker�l kiv�laszt�sra a (px,py) koordin�t�hoz k�pest.
	 * @param lines
	 * @param px
	 * @param py
	 */
	private void selectNearest(ArrayList<Line> lines, float px, float py){
		Iterator it = lines.iterator();
		Line nearest=lines.get(lines.size()-1);
		float dist=distance;
		while ( it.hasNext() ) {
			Line line = (Line) it.next();
			float y1=line.getEndpoint1().getY();
			float y2=line.getEndpoint2().getY();
			float x1=line.getEndpoint1().getX();
			float x2=line.getEndpoint2().getX();
			float crossproduct = Math.abs((py - y1) * (x2 - x1) - (px - x1) * (y2- y1));
			if(crossproduct<dist){
				nearest = line;
				dist = crossproduct;
			}
		}
		lines.clear();
		lines.add(nearest);
		
	}
	/**
	 *Be�ll�tja az alapt�vols�got. 
	 * @param distance
	 */
	public void setDistance(float distance) {
		this.distance = distance;
	}
	/**
	 * A vonalak k�z�l a leg�jabb ker�l kiv�laszt�sra.
	 * @param lines
	 */
	private void selectNewest(ArrayList<Line> lines){
		Line last = lines.get(lines.size()-1);
		lines.clear();
		lines.add(last);
	}
	/**
	 * Megh�vja a  be�ll�tott m�dnak megfelel� vonalkiv�laszt�si algoritmust
	 * @param lines
	 * @param px
	 * @param py
	 */
	public void selectLine(ArrayList<Line> lines,float px,float py){
		switch(mode){
		case SELECT_THE_NEAREST:
			selectNearest(lines,px,py);
			break;
		case SELECT_THE_NEWEST:
			selectNewest(lines);
			break;
		default:
			selectNearest(lines,px,py);
			break;
		}
	}
}
