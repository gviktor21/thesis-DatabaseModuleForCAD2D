/**
 * A special Button implementation for icon buttons. This is extended 
 * from JButton, hence it can be handled as a JButton. However, the 
 * look-and-feel is changed. It can not contain any text, but it 
 * holds images. 
 * 
 * Author: Gabor Nemeth (gnemeth@inf.u-szeged.hu)
 * Contact: gnemeth@inf.u-szeged.hu
 * 
 **/ 
package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class IconButton extends JButton implements MouseListener {

	 //Átállítottam publicra
	public final int ACTIVE = 1;
	public final int SELECTED = 2;
	public final int DEFAULT = 0;

	private int state;
	
	private Color activeColor;
	private Color selectedColor;
	private Color defaultColor;
	private BufferedImage img;
	private BufferedImage invImg;
	private int imgWidth;
	private int imgHeight;
	
	
	public IconButton(String iconFilename) {
		super();
		
		try {
			this.img = ImageIO.read(new File(iconFilename));
			this.invImg = ImageIO.read(new File(iconFilename));
			
			this.imgWidth = img.getWidth();
			this.imgHeight = img.getHeight();
			
			
			
			//this.setBounds(0,0,imgWidth+4, imgHeight+4);
			this.repaint();
			this.state = DEFAULT;
			this.addMouseListener(this);
			
			// invert the original image
			
			int[] rgbdata = new int[imgWidth*imgHeight];
			rgbdata = invImg.getRGB(0,0,this.imgWidth,this.imgHeight,rgbdata,0,this.imgWidth);
			for (int i = 0; i < rgbdata.length; i++) {
				int pixel = rgbdata[i];
				int alpha = pixel & 0xFF000000;
				int red = pixel   & 0x00FF0000;
				int green = pixel & 0x0000FF00;
				int blue = pixel  & 0x000000FF;
				
				if ( alpha == 0x00000000 ) {
					rgbdata[i] = 0x00000000;
				} else {
					rgbdata[i] = 0xFFFFFFFF;
				}
			}
			invImg.setRGB(0,0,this.imgWidth,this.imgHeight,rgbdata,0,this.imgWidth);
			
			
			defaultColor = Color.lightGray;
			activeColor = Color.gray;
			selectedColor = Color.darkGray;
			this.setVisible(true);
			
			this.setSize(imgWidth+4, imgHeight+4);
			this.setMinimumSize(new Dimension(imgWidth+4,imgHeight+4));
			this.setPreferredSize(new Dimension(imgWidth+4,imgHeight+4));
			this.setMaximumSize(new Dimension(imgWidth+4,imgHeight+4));
			this.setBounds(new Rectangle(imgWidth+4,imgHeight+4));
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void select() {
		this.state = this.SELECTED;
		this.repaint();
	}
	
	public void deselect() {
		this.state = this.DEFAULT;
		this.repaint();
	}
	
	public boolean selected() {
		if ( this.state == this.SELECTED) {
			return true;
		}
		return false;
	}
	

	
	public void mouseExited(MouseEvent e) {
		if (this.state != this.SELECTED) {
			this.state = this.DEFAULT;
			this.repaint();
		}
	}
	
	public void mouseEntered(MouseEvent e) {
		if (this.state != this.SELECTED) {
			this.state = this.ACTIVE;
			this.repaint();
		}
	}
	
	public void mouseClicked(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
		if (this.state != this.SELECTED) {
			this.state = this.SELECTED;
		} else {
			this.state = this.ACTIVE;
		}
		this.repaint();
	}
	
	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public void mouseReleased(MouseEvent e) {
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (state == this.DEFAULT) { // the button is selected 
			this.setBackground(defaultColor);
			g.drawImage(this.img, 2,2,this.imgWidth, this.imgHeight, this);
		}
		if (state == this.ACTIVE) { // the button is selected 
			this.setBackground(activeColor);
			g.drawImage(this.img, 2,2,this.imgWidth, this.imgHeight, this);
		}
		if (state == this.SELECTED) { // the button is selected 
			this.setBackground(selectedColor);
			g.drawImage(this.invImg, 2,2,this.imgWidth, this.imgHeight, this);
		}
		
		
	}
	

}
