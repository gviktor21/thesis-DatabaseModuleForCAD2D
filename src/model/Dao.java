package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import view.Structures;

/**
 * 
 * @author Godó Viktor
 *Ez az osztály tartalmazza, szolgáltatja azokat a metódusokat, eljárásokat amik közvetlenül írnak az adatbázisba vagy adatokat olvasnak ki belõle.
 */
public class Dao{
	private static Connection connection;
	Map<Integer, Type> tipusok = new HashMap<Integer, Type>();
	Map<Integer, Parameter> jellemzok = new HashMap<Integer, Parameter>();
	Map<Integer, DrawingElement> DrawingElementek = new HashMap<Integer, DrawingElement>();
	
	/**
	 * Konstruktor
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Dao() throws SQLException, ClassNotFoundException{
		Class.forName("org.hsqldb.jdbcDriver");
	}
	
	/**
	 * Az adatbázis létrehozása a memóriában.
	 */
	public void createDataBase(){
		try {
			Statement statement = null;
			Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
			statement = c.createStatement();
		    String sql = "CREATE TABLE Tipus(tipus_id NUMERIC(8) PRIMARY KEY NOT NULL,megnevezes VARCHAR(40))";
		    statement.executeUpdate(sql);
		    sql = "CREATE SEQUENCE s_tipus START WITH 1 INCREMENT BY 1 NO MAXVALUE";
		    statement.executeUpdate(sql);
		    sql = "CREATE TABLE Jellemzo(jellemzo_id NUMERIC(8) PRIMARY KEY NOT NULL,megnevezes VARCHAR(40),mertekegyseg VARCHAR(20))";
		    statement.executeUpdate(sql);
		    sql = "CREATE SEQUENCE s_jellemzo START WITH 1 INCREMENT BY 1 NO MAXVALUE";
		    statement.executeUpdate(sql);
		    sql = "CREATE TABLE Rajzelem(rajzelem_id NUMERIC(8) PRIMARY KEY NOT NULL,tipus_id NUMERIC(8) REFERENCES Tipus(tipus_id) ON UPDATE CASCADE ON DELETE SET NULL,rajzelem_megnevezes VARCHAR(40))";
		    statement.executeUpdate(sql);
		    sql = "CREATE SEQUENCE s_rajzelem START WITH 1 INCREMENT BY 1 NO MAXVALUE";
		    statement.executeUpdate(sql);
		    sql = "CREATE TABLE TipusJellemzoi(tipus_id NUMERIC(8) NOT NULL REFERENCES Tipus(tipus_id ) ON UPDATE CASCADE ON DELETE CASCADE,jellemzo_id  NUMERIC(8) NOT NULL REFERENCES Jellemzo(jellemzo_id)ON UPDATE CASCADE ON DELETE CASCADE ,alapertek VARCHAR(40),PRIMARY KEY(tipus_id,jellemzo_id))";
		    statement.executeUpdate(sql);
		    sql = "CREATE TABLE RajzelemJellemzoi(jellemzo_id NUMERIC(8) NOT NULL REFERENCES Jellemzo(jellemzo_id) ON UPDATE CASCADE ON DELETE CASCADE,rajzelem_id NUMERIC(8) NOT NULL REFERENCES Rajzelem(rajzelem_id) ON UPDATE CASCADE ON DELETE CASCADE,ertek VARCHAR(40) NOT NULL,PRIMARY KEY(rajzelem_id,jellemzo_id))";
		    statement.executeUpdate(sql);
		    //System.out.println("DataBase Created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Az összes típus lekérése az adatbázisból.
	 * @return
	 * @throws SQLException
	 */
	public Map<Integer, Type> getTypes() throws SQLException{
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		tipusok.clear();
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
		Type a;
		String query = "Select * from Tipus";
		Statement statement = null;
        ResultSet resultSet = null;
        statement = connection.createStatement();
        resultSet =statement.executeQuery(query);
        while( resultSet.next()){
        	//System.out.println(resultSet.getInt("tipus_id") +" "+resultSet.getString("megnevezes"));
        	a = new Type(resultSet.getInt(Structures.str_Type[0]),resultSet.getString(Structures.str_Type[1]));
        	tipusok.put(a.getID(), a);
        }
        resultSet.close();
    	statement.close();
    	connection.close();
    	return tipusok;
	}
	/**
	 * Az adott azonosítóval rendelkezõ típus lekérése az adatbázisból. 
	 * @param tipus_id
	 * @return
	 * @throws SQLException
	 */
	public Type getType(int tipus_id) throws SQLException{
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");

		Type t;
		String query = "Select * from Tipus Where Tipus.tipus_id ="+tipus_id;
		Statement statement = null;
        ResultSet resultSet = null;
        statement = connection.createStatement();
        resultSet =statement.executeQuery(query);
        if(resultSet.next()){
        	t = new Type(resultSet.getInt("tipus_id"),resultSet.getString("megnevezes"));
        	 return t;
        }
        return null;
	}
	/**
	 * Az összes jellemzõ lekérése az adatbázisból.
	 * @return
	 * @throws SQLException
	 */
	public Map<Integer, Parameter> getParameters() throws SQLException{
		jellemzok.clear();
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
		String query = "Select * from Jellemzo";
		Statement statement = null;
        ResultSet resultSet = null;
        statement = connection.createStatement();
        resultSet =statement.executeQuery(query);
        while( resultSet.next()){
        	//System.out.println(resultSet.getInt("jellemzo_id") +" "+resultSet.getString("megnevezes")+" "+resultSet.getString("mertekegyseg"));
        	Parameter a = new Parameter(resultSet.getInt(Structures.str_Parameter[0]),resultSet.getString(Structures.str_Parameter[1]),resultSet.getString(Structures.str_Parameter[2]));
        	jellemzok.put(a.getID(), a);
        }
        resultSet.close();
    	statement.close();
    	connection.close();
    	return jellemzok;
	}
	/**
	 * Adott rajzelemhez tartozó jellemzõk lekérése az adatbázisból.
	 * @param element
	 * @return
	 * @throws SQLException
	 */
	public DrawingElement getDrawingElementParameters(DrawingElement element) throws SQLException{
		jellemzok.clear();
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
		String query = "Select jellemzo_id,megnevezes,ertek from RajzelemJellemzoi,Jellemzo WHERE jellemzo_id = RajzelemJellemzoi.jellemzo_id and rajzelem_id ="+element.getID();
		Statement statement = null;
        ResultSet resultSet = null;
        statement = connection.createStatement();
        resultSet =statement.executeQuery(query);
        while( resultSet.next()){
        	Parameter a = new Parameter(resultSet.getInt(Structures.str_Parameter[0]),resultSet.getString(Structures.str_Parameter[1]));
        	a.setvalue(resultSet.getString(Structures.str_DrawingELementParameters[2]));
        	jellemzok.put(a.getID(), a);
        }
        resultSet.close();
    	statement.close();
    	connection.close();
    	element.setParameters(jellemzok);
    	return element;
	}
	/**
	 * Az összes  adatbázisban lévõ rajzelem lekérése az adatbázisból.
	 * @return
	 * @throws SQLException
	 */
	public Map<Integer, DrawingElement> getDrawingElements() throws SQLException{
		DrawingElementek.clear();
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");

		String query = "Select * from Rajzelem";
		Statement statement = null;
        ResultSet resultSet = null;
        statement = connection.createStatement();
        resultSet =statement.executeQuery(query);
        while( resultSet.next()){
        	int tipus_id = resultSet.getInt(Structures.str_Type[0]);
        	if(tipus_id != 0){
        		Type  t;
        		if(tipusok.containsKey(tipus_id)){
        			t = tipusok.get(tipus_id);
        		}else{
        			//KÃ¼lÃ¶nben az adatbÃ¡zisbÃ³l kell kivenni
        			 t = getType(tipus_id);
        		}
        		DrawingElement a = new DrawingElement(resultSet.getInt(Structures.str_DrawingElement[0]),resultSet.getString(Structures.str_DrawingElement[2]),t);
            	DrawingElementek.put(Integer.parseInt(a.getID()), a);
        	}else{
        		DrawingElement a = new DrawingElement(resultSet.getInt(Structures.str_DrawingElement[0]),resultSet.getString(Structures.str_DrawingElement[0]));
            	DrawingElementek.put(Integer.parseInt(a.getID()), a);
        	}
        	
        }
        resultSet.close();
    	statement.close();
    	connection.close();
    	return DrawingElementek;
	}
	
	/**
	 * Adott típus törlése az adatbázisból.
	 * @param toDelete
	 * @return
	 * @throws SQLException
	 */
	public boolean DeleteType(Type toDelete) throws SQLException{
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
		String updatestatement="DELETE FROM Tipus WHERE tipus_id ="+toDelete.getID();
		//System.out.println(updatestatement);
		Statement statement = null;
		statement = connection.createStatement();
		try{
		statement.executeUpdate(updatestatement);
		}catch (SQLIntegrityConstraintViolationException sicve){
			//System.out.println("Kulcseggyezï¿½s");
			return false;
		}catch (SQLDataException sde){
			//System.out.println("Szoveg tï¿½pus hiba");
			return false;
		}
    	statement.close();
    	connection.close();
    	return true;
		
	}
	/**
	 * Az adott típushoz hozzátartozó paraméterek lekérdezése.
	 */
	public Map<Integer, Parameter> getTypeParameters(int tipusId) throws SQLException{
		jellemzok.clear();
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
		String query = "SELECT Jellemzo_id,jellemzo.megnevezes,alapertek,mertekegyseg FROM Tipus,TipusJellemzoi,Jellemzo WHERE Tipus.tipus_id="+tipusId+" and Tipus.tipus_id= TipusJellemzoi.tipus_id and TipusJellemzoi.jellemzo_id =Jellemzo.jellemzo_id";
		Statement statement = null;
        ResultSet resultSet = null;
        statement = connection.createStatement();
        resultSet =statement.executeQuery(query);
        while( resultSet.next()){
        	//System.out.println(resultSet.getInt("Jellemzo_id") +" "+resultSet.getString("jellemzo.megnevezes")+" "+resultSet.getString("alapertek")+" "+resultSet.getString("jellemzo.mertekegyseg"));
        	Parameter a = new Parameter(resultSet.getInt("Jellemzo_id"),resultSet.getString("jellemzo.megnevezes"),resultSet.getString("jellemzo.mertekegyseg"),resultSet.getString("alapertek"));
        	jellemzok.put(a.getID(), a);
        }
        resultSet.close();
    	statement.close();
    	connection.close();
    	return jellemzok;
		
	}
	/**
	 * Az adott típushoz nem hozzátartozó paraméterek lekérdezése.
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Map<Integer, Parameter> getNotTypeParameters(int id) throws SQLException{
		jellemzok.clear();
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");

		String query = "(SELECT * From Jellemzo)EXCEPT (SELECT Jellemzo_id,jellemzo.megnevezes,mertekegyseg FROM Tipus,TipusJellemzoi,Jellemzo WHERE Tipus.tipus_id="+id+" and Tipus.tipus_id= TipusJellemzoi.tipus_id and TipusJellemzoi.jellemzo_id =Jellemzo.jellemzo_id)";
		Statement statement = null;
        ResultSet resultSet = null;
        statement = connection.createStatement();
        resultSet =statement.executeQuery(query);
        while( resultSet.next()){
        	Parameter a = new Parameter(resultSet.getInt("Jellemzo_id"),resultSet.getString("jellemzo.megnevezes"),resultSet.getString("jellemzo.mertekegyseg"));
        	jellemzok.put(a.getID(), a);
        }
        resultSet.close();
    	statement.close();
    	connection.close();
    	return jellemzok;
	}
	/**
	 * Új típus felvétele az adatbázisba.
	 * @param newType
	 * @return
	 * @throws SQLException
	 */
	public Type AddType(Type newType ) throws SQLException{
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");

		String str_statement="INSERT INTO Tipus(tipus_id,megnevezes) VALUES(NEXT VALUE FOR s_tipus, '"+newType.getname()+"')";
		Statement statement = null;
		statement = connection.createStatement();
        ResultSet resultSet = null;
		try{
			statement.executeUpdate(str_statement);
			str_statement = "select max(Tipus_id) as maxtipusid FROM Tipus";
			statement = null;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(str_statement);
			if(resultSet.next()){
					newType.setID(resultSet.getInt("maxtipusid"));
			}else{
				//vmi hiba
			}
		}catch (SQLIntegrityConstraintViolationException sicve){
			//System.out.println("Kulcseggyezï¿½s");
		}catch (SQLDataException sde){
			//System.out.println("Szoveg tï¿½pus hiba");
		}
    	statement.close();
    	connection.close();
		return newType;
	}
	/**
	 * Új jellemzõ felvitele az adatbázisba.
	 * @param jellemzonev
	 * @param mertekegyseg
	 * @return
	 * @throws SQLException
	 */
	public Parameter AddParameter(String jellemzonev,String mertekegyseg) throws SQLException{
		//todo return a jellemzo
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
		String str_statement="INSERT INTO Jellemzo(jellemzo_id,megnevezes,mertekegyseg) VALUES(NEXT VALUE FOR s_jellemzo ,'"+jellemzonev+"', '"+ mertekegyseg+"')";
		//System.out.println(str_statement);
		Statement statement = null;
		ResultSet resultSet = null;
		statement = connection.createStatement();
		Parameter newJellemzo = new Parameter(jellemzonev,mertekegyseg);
		try{
		statement.executeUpdate(str_statement);
		str_statement = "select max(Jellemzo_id) as maxjellemzoid FROM Jellemzo";
		statement = null;
		statement = connection.createStatement();
		resultSet = statement.executeQuery(str_statement);
		if(resultSet.next()){
			newJellemzo.setID(resultSet.getInt("maxjellemzoid"));
		}else{
			//vmi hiba
		}
		}catch (SQLIntegrityConstraintViolationException sicve){
			//System.out.println("Kulcseggyezï¿½s");
		}catch (SQLDataException sde){
			//System.out.println("Szoveg tï¿½pus hiba");
		}
    	statement.close();
    	connection.close();
    	return newJellemzo;
	}
	/**
	 * Adott típushoz adott jellemzõ hozzávételezése az adatbázisban.
	 * @param t
	 * @param j
	 * @return
	 * @throws SQLException
	 */
	public boolean  AddTypeParameter(Type t , Parameter j) throws SQLException{
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");

		String updatestatement="INSERT INTO TipusJellemzoi(tipus_id,jellemzo_id,alapertek) VALUES("+t.getID()+", '"+j.getID()+"', '"+ j.getdefaultValue() +"')";
		//System.out.println(updatestatement);
		Statement statement = null;
		statement = connection.createStatement();
		try{
		statement.executeUpdate(updatestatement);
		}catch (SQLIntegrityConstraintViolationException sicve){
			//System.out.println("Kulcseggyezï¿½s");
			return false;
		}catch (SQLDataException sde){
			//System.out.println("Szoveg tï¿½pus hiba");
			return false;
		}
    	statement.close();
    	connection.close();
		return true;
		
	}
	/**
	 * Adott típushoz tartozó jellemzõ törlése az adatbázisból.
	 * @param tipus
	 * @param jellemzo
	 * @return
	 * @throws SQLException
	 */
	public boolean DeleteTypeParameter(Type tipus, Parameter jellemzo) throws SQLException{
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
		String updatestatement="DELETE FROM TipusJellemzoi WHERE tipus_id ="+tipus.getID()+" and jellemzo_id="+jellemzo.getID();
		//System.out.println(updatestatement);
		Statement statement = null;
		statement = connection.createStatement();
		try{
		statement.executeUpdate(updatestatement);
		}catch (SQLIntegrityConstraintViolationException sicve){
			//System.out.println("Kulcseggyezï¿½s");
			return false;
		}catch (SQLDataException sde){
			//System.out.println("Szoveg tï¿½pus hiba");
			return false;
		}
    	statement.close();
    	connection.close();
    	return true;
		
	}
	/**
	 * Új rajzelem felvitele az adatbázisba.
	 * @param newElement
	 * @return
	 * @throws SQLException
	 */
	public DrawingElement AddDrawingElement(DrawingElement newElement) throws SQLException{
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
		String str_statement="INSERT INTO Rajzelem(rajzelem_id,tipus_id,rajzelem_megnevezes)  VALUES(NEXT VALUE FOR s_rajzelem, "+newElement.getType().getID()+", '"+ newElement.getname()+"')";
		System.out.println("newName: "+newElement.getname());
		Statement statement = null;
		statement = connection.createStatement();
		ResultSet resultSet = null;
		try{
			statement.executeUpdate(str_statement);
			str_statement = "select max("+Structures.str_DrawingElement[0]+") as maxrajzelemid FROM Rajzelem";
			statement = null;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(str_statement);
			if(resultSet.next()){
				newElement.setID(resultSet.getInt("maxrajzelemid")+"");
			}else{
				//vmi hiba
			}
			String elnevezes = resultSet.getInt("maxrajzelemid")+"_"+newElement.getType().getname();
			//System.out.println("Elnevezes: "+elnevezes);
			str_statement = "UPDATE Rajzelem SET "+Structures.str_DrawingElement[2] +"= '"+elnevezes+"' WHERE "+Structures.str_DrawingElement[0] +"="+resultSet.getInt("maxrajzelemid");
			newElement.setname(elnevezes);
			statement = connection.createStatement();
			statement.executeUpdate(str_statement);
			
		}catch (SQLIntegrityConstraintViolationException sicve){
			//System.out.println("Kulcseggyezï¿½s");
			return null;
		}catch (SQLDataException sde){
			//System.out.println("Szoveg tï¿½pus hiba");
			return null;
		}
    	statement.close();
    	connection.close();
    	return newElement;
		
	}
	
	/**
	 * Adott jellemzõ törlése az adatbázisból.
	 * @param toDelete
	 * @return
	 * @throws SQLException
	 */
	public boolean DeleteParameter(Parameter toDelete) throws SQLException{
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
		String updatestatement="DELETE FROM Jellemzo WHERE jellemzo_id ="+toDelete.getID();
		//System.out.println(updatestatement);
		Statement statement = null;
		statement = connection.createStatement();
		try{
			statement.executeUpdate(updatestatement);
		}catch (SQLIntegrityConstraintViolationException sicve){
			//System.out.println("Kulcseggyezï¿½s");
			return false;
		}catch (SQLDataException sde){
			//System.out.println("Szoveg tï¿½pus hiba");
			return false;
		}
    	statement.close();
    	connection.close();
    	return true;
		
	}
	/**
	 * Adott rajzelemhez  tartozó jellemzõ eltávolítása az adatbázisból.
	 * @param toDelete
	 * @return
	 * @throws SQLException
	 */
	public boolean DeleteDrawingElement(DrawingElement toDelete) throws SQLException{
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");

		String updatestatement="DELETE FROM Rajzelem WHERE rajzelem_id ="+toDelete.getID();
		//System.out.println(updatestatement);
		Statement statement = null;
		statement = connection.createStatement();
		try{
			statement.executeUpdate(updatestatement);
		}catch (SQLIntegrityConstraintViolationException sicve){
			System.out.println("Kulcseggyezï¿½s");
			sicve.printStackTrace();
			return false;
		}catch (SQLDataException sde){
			//System.out.println("Szoveg tï¿½pus hiba");
			sde.printStackTrace();
			return false;
		}
    	statement.close();
    	connection.close();
    	return true;
		
	}
	/**
	 * Adott rajzelemhez jellemzõ hozzáadása az adatbázisban.
	 * @param DrawingElement
	 * @param jellemzo
	 * @throws SQLException
	 */
	public void AddDrawingElementParameter(DrawingElement DrawingElement , Parameter jellemzo) throws SQLException{
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");

		String updatestatement="INSERT INTO RajzelemJellemzoi(rajzelem_id,jellemzo_id,ertek) VALUES("+DrawingElement.getID()+", '"+jellemzo.getID()+"', "+ jellemzo.getvalue() +")";
		//System.out.println(updatestatement);
		Statement statement = null;
		statement = connection.createStatement();
		try{
			statement.executeUpdate(updatestatement);
		}catch (SQLIntegrityConstraintViolationException sicve){
			//System.out.println("Kulcseggyezï¿½s");
		}catch (SQLDataException sde){
			//System.out.println("Szoveg tï¿½pus hiba");
		}
    	statement.close();
    	connection.close();	
	}
	/**
	 * Adott rajzelemhez  tartozó jellemzõ eltávolítása az adatbázisból.
	 * @param DrawingElement
	 * @param jellemzo
	 * @return
	 * @throws SQLException
	 */
	public boolean DeleteDrawingElementParameter(DrawingElement DrawingElement, Parameter jellemzo) throws SQLException{
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
		String updatestatement="DELETE FROM RajzelemJellemzoi WHERE rajzelem_id = "+DrawingElement.getID()+" AND jellemzo_id = "+jellemzo.getID();
		//System.out.println(updatestatement);
		Statement statement = null;
		statement = connection.createStatement();
		try{
		statement.executeUpdate(updatestatement);
		}catch (SQLIntegrityConstraintViolationException sicve){
			System.out.println("Kulcseggyezï¿½s");
			return false;
		}catch (SQLDataException sde){
			System.out.println("Szoveg tï¿½pus hiba");
			return false;
		}
    	statement.close();
    	connection.close();
    	return true;
		
	}
	/**
	 * Adott rajzelemhez tartozó jellemzõk felvitele az adatbázisba.
	 * @param drawElement
	 * @return
	 */
	public boolean setDrawingElementParameters(DrawingElement drawElement){
		Map<Integer,Parameter> parameters =drawElement.getParameters();
		Iterator it = parameters.values().iterator();
		try{
			//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
			connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
			Statement statement = null;
			while(it.hasNext()){
				Parameter parameter = (Parameter)it.next();
				String updatestatement="INSERT INTO RajzelemJellemzoi(Rajzelem_id,jellemzo_id,ertek) VALUES("+drawElement.getID()+", "+parameter.getID()+", '"+ parameter.getvalue() +"')";
				try{
					//System.out.println(updatestatement);
					statement = connection.createStatement();
					statement.executeUpdate(updatestatement);
				}catch (SQLIntegrityConstraintViolationException sicve){
					//System.out.println("Kulcseggyezï¿½s");
					updatestatement="UPDATE RajzelemJellemzoi SET "+Structures.str_DrawingELementParameters[2]+"= '"+parameter.getvalue()+"' WHERE "+Structures.str_DrawingELementParameters[0]+"="+parameter.getID()+" AND "+Structures.str_DrawingELementParameters[1]+"="+drawElement.getID();
					//System.out.println(updatestatement);
					statement = connection.createStatement();
					statement.executeUpdate(updatestatement);
				}	
			}
	    	statement.close();
	    	connection.close();
		}catch (SQLDataException sde){
			//System.out.println("Szoveg tï¿½pus hiba");
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		return true;
	
	}
	/**
	 * Beállítja az adott rajzelem típusát aza adatbázisban.
	 * @param drawingElement
	 * @return
	 * @throws SQLException
	 */
	public boolean ModifyDrawingElementType(DrawingElement drawingElement) throws SQLException{
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
		String reszsztring= " , "+Structures.str_DrawingElement[2]+"= '"+drawingElement.getname()+"' ";
		String updatestatement="UPDATE Rajzelem SET "+Structures.str_DrawingElement[1]+" = "+drawingElement.getType().getID()+reszsztring+" WHERE "+Structures.str_DrawingElement[0]+" = "+drawingElement.getID();
		//System.out.println(updatestatement);
		Statement statement = null;
		statement = connection.createStatement();
		try{
			statement.executeUpdate(updatestatement);
		}catch (SQLIntegrityConstraintViolationException sicve){
			System.out.println("Kulcseggyezï¿½s");
			return false;
		}catch (SQLDataException sde){
			System.out.println("Szoveg tï¿½pus hiba");
			return false;
		}
    	statement.close();
    	connection.close();
    	return true;
		
	}
	/**
	 * Beállítja null-ra az adott rajzelem típusát az adatbázisban.
	 * @param drawingElement
	 * @return
	 * @throws SQLException
	 */
	public boolean SetNullDrawingElementType(DrawingElement drawingElement) throws SQLException{
		//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
		String reszsztring= " , "+Structures.str_DrawingElement[2]+"= '"+drawingElement.getname()+"' ";
		String updatestatement="UPDATE Rajzelem SET "+Structures.str_DrawingElement[1]+" = "+" null "+reszsztring+" WHERE "+Structures.str_DrawingElement[0]+" = "+drawingElement.getID();
		//System.out.println(updatestatement);
		Statement statement = null;
		statement = connection.createStatement();
		try{
			statement.executeUpdate(updatestatement);
		}catch (SQLIntegrityConstraintViolationException sicve){
			System.out.println("Kulcseggyezï¿½s");
			return false;
		}catch (SQLDataException sde){
			System.out.println("Szoveg tï¿½pus hiba");
			return false;
		}
    	statement.close();
    	connection.close();
    	return true;
		
	}
	
	/**
	 * Kimenti az adatbázist (a HSQLDB álltal használt fájlformátumokba)
	 * @param path
	 */
	public void saveDataBase(String path) {
		try{
			//connection  = DriverManager.getConnection("jdbc:hsqldb:"+db,"gviktor","376462Di");
			connection = DriverManager.getConnection("jdbc:hsqldb:mem:cadDataBase", "SA", "");
			String stmt ="BACKUP DATABASE TO '/home/gviktor/Szakdolg/Db_cad/ViktorBackup/' BLOCKING";
			Statement statement = null;
			statement = connection.createStatement();
			statement.execute(stmt);
			statement.close();
	    	connection.close();		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
