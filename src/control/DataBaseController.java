package control;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.Parameter;
import model.DrawingElement;
import model.Type;
import model.Dao;
/**
 * 
 * @author Godó Viktor
 * DataBaseController osztály grafikus felület vezérlõi   adatigényléseit és adatrögzítéseit  továbbítja a Dao-hoz.
 *
 */
public class DataBaseController{
	Dao dao= new Dao();
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DataBaseControllerler tctrl = nDataBaseControllerler();
			MainView main = new MainView(tctrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	*/
	
	public DataBaseController() throws ClassNotFoundException, SQLException{
		//dao.getTypeParameterk(1);
		//dao.AddType(2, "acï¿½ldï¿½bel");
		//System.out.println("Parameterk");
		//dao.AddParameter(5,"szakï¿½toszilï¿½rdsï¿½g",null);
		//dao.Parameterk();
	}
	public void createDataBase(){
		dao.createDataBase();
	}
	public Map<Integer,Type> getTypes(){
		try {
			return dao.getTypes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public DrawingElement addDrawingElement(DrawingElement element){
		try{
			element= dao.AddDrawingElement(element);
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return element;
	}
	public Type addType(Type newType){
		
		try {
			return dao.AddType(newType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public Map<Integer,Parameter> getParameters(){
		try {
			return dao.getParameters();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public Map<Integer,DrawingElement> getDrawingElements(){
		try {
			return dao.getDrawingElements();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String[][] getParameterData(){
		Map<Integer, Parameter>Parameterk = new HashMap<>();
			try {
				Parameterk=dao.getParameters();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Iterator it = Parameterk.values().iterator();
			int i=0;
			String[][] data = new String[Parameterk.size()][];
			while (it.hasNext()) {
				//System.out.println(i);
			    Parameter je = (Parameter) it.next();
			    //System.out.println(t);
			    data[i] = je.getDataLine();
			    i++;
			}
			return data;
		
	} 
	public Type getTypeParameters(Type t){
		Map<Integer, Parameter>Parameterk = new HashMap<>();
		try {
			Parameterk=dao.getTypeParameters(t.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException nie){
			
		}
		Iterator it = Parameterk.values().iterator();
		while(it.hasNext()){
			Parameter j = (Parameter) it.next();
			t.addParameter(j);
		}
		return t;
	}
	
	public   Map<Integer,Parameter> getNotTypeParameters(int i){
		Map<Integer, Parameter>parameters = new HashMap<>();
		try {
			parameters=dao.getNotTypeParameters(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(parameters.isEmpty()){
			return null;
		}else{
			return parameters;	
		}	
	}
	
	
	public boolean deleteType(Type toDelete){
		try {
			if(dao.DeleteType(toDelete)){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	public boolean modifyDrawingElementType(DrawingElement element){
		 try {
			if(dao.ModifyDrawingElementType(element)){
				 return true;
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		 return false;
	}
	public boolean setNullDrawingElementType(DrawingElement element){
		 try {
			if(dao.SetNullDrawingElementType(element)){
				 return true;
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		 return false;
	}
	public String[][] getTypeokData(){
		Map<Integer,Type>Typeok = new HashMap<>();
		try {
			Typeok = dao.getTypes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator it = Typeok.values().iterator();
		int i=0,j=0;
		String[][] data = new String[Typeok.size()][];
		while (it.hasNext()) {
			//System.out.println(i);
		    Type t = (Type) it.next();
		    //System.out.println(t);
		    data[i] = t.getDataLine();
		    i++;
		}
		/*
		for(i = 0;i<data.length; i++){
			for(j=0;j<data[i].length;j++){
				System.out.println(data[i][j]);
			}
		}
		*/
		return data;
	}
	public Parameter addParameter(Parameter j){
		try {
			return dao.AddParameter(j.getname(),j.getmeasure());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	
	public int DeleteParameter(Parameter torlendo) {
		try {
			if(dao.DeleteParameter(torlendo)){
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return -1;
		
	}
	public boolean DeleteTypeParameter(Type t, Parameter par){
		try{
			if(dao.DeleteTypeParameter(t, par)){
				return true;
			}
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean AddTypeParameter(Type t, Parameter j) {
		// TODO Auto-generated method stub
		try {
			return dao.AddTypeParameter(t,j);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public void setDrawingElementParameters(DrawingElement element){
		if(dao.setDrawingElementParameters(element)){
			
		}else{
			System.out.println("Valami hiba tÃ¶rtÃ©nt DrawingElementhez paramÃ©terrendelÃ©skor");
		}
		
	}
	public DrawingElement getDrawingElementParameters(DrawingElement element){
		try {
			dao.getDrawingElementParameters(element);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return element;

	
	}
	public boolean deleteDrawingParameter(DrawingElement element, Parameter parameter){
		try {
			dao.DeleteDrawingElementParameter(element,parameter);
			return true;
		} catch (SQLException e) {
			return false;
			// TODO Auto-generated catch block
			
		}
		
	}
	public boolean addDrawingElementParameter(DrawingElement draw_element, Parameter par){
		try {
			dao.AddDrawingElementParameter(draw_element ,  par);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public void SaveDataBase(String path) {
		// TODO Auto-generated method stub
		dao.saveDataBase(path);
	}
}
