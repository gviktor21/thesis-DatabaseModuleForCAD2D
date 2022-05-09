package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author Godó Viktor
 *Az osztály arra szolgál megoldási módokat, kiválasztáskor a vonal (Line) rajzelemre kattintva, ha egyszerre több vonalat érint a kiválasztás, akkor mely vonal kerüljön bele a kijelölt rajzelemek közé. 
 */
public class LineSelector {
	public enum LineSelectMode{SELECT_THE_NEAREST,SELECT_THE_NEWEST}
	private LineSelectMode mode;
	private float distance;
	public LineSelectMode getMode() {
		return mode;
	}
	/**
	 * Vonal kiválasztási módot állítja be.
	 * @param mode
	 */
	public void setMode(LineSelectMode mode) {
		this.mode = mode;
	}
	/**
	 * Konstruktor
	 * @param mode Kiválasztási mód.
	 */
	public LineSelector(LineSelectMode mode){
		distance=999.0f;
		this.mode=mode;
	}
	/**
	 * A  paraméterben kapott vonalak közül a legközelebbi kerül kiválasztásra a (px,py) koordinátához képest.
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
	 *Beállítja az alaptávolságot. 
	 * @param distance
	 */
	public void setDistance(float distance) {
		this.distance = distance;
	}
	/**
	 * A vonalak közül a legújabb kerül kiválasztásra.
	 * @param lines
	 */
	private void selectNewest(ArrayList<Line> lines){
		Line last = lines.get(lines.size()-1);
		lines.clear();
		lines.add(last);
	}
	/**
	 * Meghívja a  beállított módnak megfelelõ vonalkiválasztási algoritmust
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
