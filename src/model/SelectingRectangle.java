package model;

/**
 * @author God� Viktor
 *A a t�bbsz�r�s kiv�laszt�sn�l kirajzoland� t�glalapot  val�s�tja meg, ami a kiv�laszt�si ter�letet jelzi. 
 */

public class SelectingRectangle extends DrawingElement {
	private float x1,y1,x2,y2;
	private float width,height;
	private Point startpoint; //left upper
	private Point endpoint; // right lower
	
	/**
	 * Konstruktor
	 */
	public SelectingRectangle(){
		super();
		startpoint = new Point(0,0);
		endpoint = new Point(0,0);
	}
	/**
	 * Konstruktor
	 * @param x1 egyik sarokpont x koordin�t�ja
	 * @param y1 az egyik sarokpont y koordin�t�ja
	 * @param x2 a m�sik sarokpont x koordin�t�ja
	 * @param y2 a m�sik sarokpont y koordin�t�ja
	 */
	public SelectingRectangle(float x1, float y1, float x2, float y2){
		super();
		startpoint = new Point(Math.min(x1, x2),Math.min(y1, y2));
		endpoint =new Point(Math.max(x1, x2),Math.max(y1, y2));
		this.width =endpoint.getX()-startpoint.getX();
		this.height =endpoint.getY()-startpoint.getY();
	}
	/**
	 * Visszaadja az egyik sarokpont x koordin�t�j�t.
	 * @return egyik sarokpont x koordin�t�ja
	 */
	public float getX1() {
		return x1;
	}
	/**
	 * Be�ll�tja a kezd�(bal fels�) �s a v�gpontot(jobb als�)
	 */
	public void setStartAndEndpoint(){
		startpoint.setX(Math.min(x1, x2));
		startpoint.setY(Math.min(y1, y2));
		endpoint.setX(Math.max(x1, x2));
		endpoint.setY(Math.max(y1, y2));
	}
	/**
	 * Be�ll�tja az egyik sarokpont x koordin�t�j�t.
	 * @param x1 egyik sarokpont  �j x koordin�t�ja
	 */
	public void setX1(float x1) {
		this.x1 = x1;
	}
	/**
	 *  Visszaadja az egyik sarokpont y koordin�t�j�t.
	 * @return sarokpont y koordin�t�ja
	 */
	public float getY1() {
		return y1;
	}
	/**
	 * (): Be�ll�tja az egyik sarokpont y koordin�t�j�t
	 * @param y1 sarokpont  �j y koordin�t�j�t
	 */
	public void setY1(float y1) {
		this.y1 = y1;
	}
	/**
	 * Visszaadja a m�sik sarokpont x koordin�t�j�t.
	 * @return m�sik sarokpont x koordin�t�ja
	 */ 
	public float getX2() {
		return x2;
	}
	/**
	 * Be�ll�tja a m�sik sarokpont x koordin�t�j�t.
	 * @param x2 m�sik sarokpont �j x koordin�t�j�t.
	 */
	public void setX2(float x2) {
		this.x2 = x2;
	}
	/**
	 * Visszaadja a m�sik sarokpont y koordin�t�j�t.
	 * @return  sarokpont y koordin�t�ja
	 */
	public float getY2() {
		return y2;
	}
	/**
	 * Be�ll�tja a m�sik sarokpont y koordin�t�j�t.
	 * @param y2 sarokpont y koordin�t�ja
	 */
	public void setY2(float y2) {
		this.y2 = y2;
	}
	/**
	 * Visszaadja a t�glalap sz�less�g�t.
	 * @return
	 */
	public float getWidth() {
		return width;
	}
	/**
	 * Be�ll�tja a t�glalap sz�less�g�t.
	 * @param width
	 */
	public void setWidth(float width) {
		this.width = width;
	}
	/**
	 * Visszaadja a t�glalap magass�g�t.
	 * @return
	 */
	public float getHeight() {
		return height;
	}
	/**
	 * Be�ll�tja a t�glalap magass�g�t.
	 * @param height
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	/**
	 * Visszaadja a kijel�l� t�glalap bal fels� sarokpontj�t.
	 * @return
	 */
	public Point getStartpoint() {
		startpoint.setX(Math.min(x1, x2));
		startpoint.setY(Math.min(y1, y2));
		return startpoint;
	}
	/**
	 * Be�ll�tja a kijel�l� t�glalap bal fels� sarokpontj�t.
	 * @param startpoint
	 */
	public void setStartpoint(Point startpoint) {
		this.startpoint = startpoint;
	}
	/**
	 * Visszaadja a kijel�l� t�glalap jobb als� sarokpontj�t.
	 * @return
	 */
	public Point getEndpoint() {
		endpoint.setX(Math.max(x1, x2));
		endpoint.setY(Math.max(y1, y2));
		return endpoint;
	}
	/**
	 * Be�ll�tja a kijel�l� t�glalap bal fels� sarokpontj�t.
	 * @param endpoint
	 */
	public void setEndpoint(Point endpoint) {
		this.endpoint = endpoint;
	}
	/**
	 * Vissza�ll�tja a kijel�l� t�glalapot a kiindul� helyzet�be. Elt�nteti a rajzlapr�l.
	 */
	public void setToDefault(){
		startpoint = new Point(0,0);
		endpoint = new Point(0,0);
		x1 =y1=x2=y2=0;
	}
	
}
