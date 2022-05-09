package model;

/**
 * @author Godó Viktor
 *A a többszörös kiválasztásnál kirajzolandó téglalapot  valósítja meg, ami a kiválasztási területet jelzi. 
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
	 * @param x1 egyik sarokpont x koordinátája
	 * @param y1 az egyik sarokpont y koordinátája
	 * @param x2 a másik sarokpont x koordinátája
	 * @param y2 a másik sarokpont y koordinátája
	 */
	public SelectingRectangle(float x1, float y1, float x2, float y2){
		super();
		startpoint = new Point(Math.min(x1, x2),Math.min(y1, y2));
		endpoint =new Point(Math.max(x1, x2),Math.max(y1, y2));
		this.width =endpoint.getX()-startpoint.getX();
		this.height =endpoint.getY()-startpoint.getY();
	}
	/**
	 * Visszaadja az egyik sarokpont x koordinátáját.
	 * @return egyik sarokpont x koordinátája
	 */
	public float getX1() {
		return x1;
	}
	/**
	 * Beállítja a kezdõ(bal felsõ) és a végpontot(jobb alsó)
	 */
	public void setStartAndEndpoint(){
		startpoint.setX(Math.min(x1, x2));
		startpoint.setY(Math.min(y1, y2));
		endpoint.setX(Math.max(x1, x2));
		endpoint.setY(Math.max(y1, y2));
	}
	/**
	 * Beállítja az egyik sarokpont x koordinátáját.
	 * @param x1 egyik sarokpont  új x koordinátája
	 */
	public void setX1(float x1) {
		this.x1 = x1;
	}
	/**
	 *  Visszaadja az egyik sarokpont y koordinátáját.
	 * @return sarokpont y koordinátája
	 */
	public float getY1() {
		return y1;
	}
	/**
	 * (): Beállítja az egyik sarokpont y koordinátáját
	 * @param y1 sarokpont  új y koordinátáját
	 */
	public void setY1(float y1) {
		this.y1 = y1;
	}
	/**
	 * Visszaadja a másik sarokpont x koordinátáját.
	 * @return másik sarokpont x koordinátája
	 */ 
	public float getX2() {
		return x2;
	}
	/**
	 * Beállítja a másik sarokpont x koordinátáját.
	 * @param x2 másik sarokpont új x koordinátáját.
	 */
	public void setX2(float x2) {
		this.x2 = x2;
	}
	/**
	 * Visszaadja a másik sarokpont y koordinátáját.
	 * @return  sarokpont y koordinátája
	 */
	public float getY2() {
		return y2;
	}
	/**
	 * Beállítja a másik sarokpont y koordinátáját.
	 * @param y2 sarokpont y koordinátája
	 */
	public void setY2(float y2) {
		this.y2 = y2;
	}
	/**
	 * Visszaadja a téglalap szélességét.
	 * @return
	 */
	public float getWidth() {
		return width;
	}
	/**
	 * Beállítja a téglalap szélességét.
	 * @param width
	 */
	public void setWidth(float width) {
		this.width = width;
	}
	/**
	 * Visszaadja a téglalap magasságát.
	 * @return
	 */
	public float getHeight() {
		return height;
	}
	/**
	 * Beállítja a téglalap magasságát.
	 * @param height
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	/**
	 * Visszaadja a kijelölõ téglalap bal felsõ sarokpontját.
	 * @return
	 */
	public Point getStartpoint() {
		startpoint.setX(Math.min(x1, x2));
		startpoint.setY(Math.min(y1, y2));
		return startpoint;
	}
	/**
	 * Beállítja a kijelölõ téglalap bal felsõ sarokpontját.
	 * @param startpoint
	 */
	public void setStartpoint(Point startpoint) {
		this.startpoint = startpoint;
	}
	/**
	 * Visszaadja a kijelölõ téglalap jobb alsó sarokpontját.
	 * @return
	 */
	public Point getEndpoint() {
		endpoint.setX(Math.max(x1, x2));
		endpoint.setY(Math.max(y1, y2));
		return endpoint;
	}
	/**
	 * Beállítja a kijelölõ téglalap bal felsõ sarokpontját.
	 * @param endpoint
	 */
	public void setEndpoint(Point endpoint) {
		this.endpoint = endpoint;
	}
	/**
	 * Visszaállítja a kijelölõ téglalapot a kiinduló helyzetébe. Eltünteti a rajzlapról.
	 */
	public void setToDefault(){
		startpoint = new Point(0,0);
		endpoint = new Point(0,0);
		x1 =y1=x2=y2=0;
	}
	
}
