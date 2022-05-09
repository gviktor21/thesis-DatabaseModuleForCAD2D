package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * @author God� Viktor
 * Egy panel, ami felhaszn�l�i fel�letet biztos�t a t�pus jellemz�inek ki�rat�s�hoz, kiv�laszt�s�hoz, t�rl�s�hez t�bl�zatos form�ban.
 *
 */
public class TablePanel extends JPanel {
	 private JTable tabla;
	 private JScrollPane scrollablak;
	 private DefaultTableModel tablemodel;
	 public Vector<String> oszlopnevek;
	 /**
	  * Konstruktor
	  * @param oszlopok
	  * @param data
	  */
	public TablePanel(String[] oszlopok,String data[][]){
		GridLayout layout=new GridLayout(1,1);
		setLayout(layout);
		oszlopnevek=new Vector<String>();
		for(int i=0;i<oszlopok.length;++i){
			oszlopnevek.add(oszlopok[i]);
		}
		tablemodel=new DefaultTableModel(oszlopnevek,0);
		
		for(int i = 0;i<data.length; i++){
			tablemodel.addRow(data[i]);
		}
		tabla=new JTable(tablemodel);
		scrollablak = new JScrollPane(tabla);
		setSize(300,300);
		add(scrollablak);
	}
	/**
	 * Konstruktor
	 * @param oszlopok
	 */
	public TablePanel(String[] oszlopok){
		GridLayout layout=new GridLayout(1,1);
		setLayout(layout);
		oszlopnevek=new Vector<String>();
		for(int i=0;i<oszlopok.length;++i){
			oszlopnevek.add(oszlopok[i]);
		}
		tablemodel=new DefaultTableModel(oszlopnevek,0){
			@Override
			public boolean isCellEditable(int row, int column) {
				if (0 == column)
					return false;
				return super.isCellEditable(row, column);
			}
		};
		tabla=new JTable(tablemodel);
		scrollablak = new JScrollPane(tabla);
		add(scrollablak);	
	}
	/**
	 * T�bb sort add hozz� a t�bl�zathoz
	 * @param data
	 */
	public void  setData(String data[][]){
		for(int i = 0;i<data.length; i++){
			tablemodel.addRow(data[i]);
		};
	}
	/**
	 * Hozz�ad egy sort a t�bl�zathoz
	 * @param row
	 */
	public void addRow( String row[]){
		tablemodel.addRow(row);
	}
	/**
	 * T�rli az �sszes sort a t�bl�zatb�l.
	 */
	public void cleartable( ){
		if (tablemodel.getRowCount() > 0) {
		    for (int i = tablemodel.getRowCount() - 1; i > -1; i--) {
		    	tablemodel.removeRow(i);
		    }
		}
	}
	/**
	 * Visszaadja a kijel�lt sorokat a t�bl�zatb�l. 
	 * @return
	 */
	public String[] getSelectedLines(){
		int rowcount =tabla.getSelectedRowCount();
		int[] linenums = tabla.getSelectedRows();
		String[] lines =  new String[rowcount];
		if(rowcount != -1){
			for(int i = 0;i<rowcount;i++){
				lines[i] = (String) tabla.getModel().getValueAt(linenums[i], 1);
				System.out.println(lines[i]);
			}
		}
		return lines;
	}
}
