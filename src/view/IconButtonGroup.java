/**
 * This class is to encapsulate the IconButtons. 
 * If a button is selected in the group, then the other 
 * ones are not selected. This class is just a collection 
 * of buttons.
 * 
 * 
 * Author: Gabor Nemeth (gnemeth@inf.u-szeged.hu)
 * Contact: gnemeth@inf.u-szeged.hu
 * 
 * Date: 2012-09-25
 * 
 **/
 package view;
 
 import java.util.*;
 import java.awt.event.*;
 import javax.swing.*;
 public class IconButtonGroup implements ActionListener {
	 
	 private ArrayList<IconButton> buttonList;
	 
	 
	 public IconButtonGroup() {
		 this.buttonList = new ArrayList<IconButton>();
		 
	 }
	 
	 public void add(IconButton button) {
		 this.buttonList.add(button);
		 button.addActionListener(this);
	 }
	 
	 public Object remove(int index) {
		 return this.buttonList.remove(index);
	 }
	 
	 public void actionPerformed(ActionEvent e) {
		 IconButton actionButton = (IconButton) e.getSource();
		 int size = this.buttonList.size();
		 for (int i=0; i < size; i++) {
			 IconButton b = this.buttonList.get(i);
			 if ( b.equals(actionButton) == false ) {
				 b.deselect();
			 }
		 }
		 
	 }
 }
  
