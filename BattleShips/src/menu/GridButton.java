package menu;

import java.awt.Point;

import javax.swing.JButton;

public class GridButton extends JButton{
	
	private Point returnPoint;
	
	public GridButton(int x, int y){
		
		returnPoint = new Point(x,y);
		
	}
	
	public Point getCoordinates(){
		return returnPoint;
	}
	

}
