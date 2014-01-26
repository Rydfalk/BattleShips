package menu;

import game.Game;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SetupGridButton extends JButton implements ActionListener{
	
	private Point returnPoint;

	
	public SetupGridButton(int x, int y){
		super();
	
		returnPoint = new Point(x,y);
		
	}
	
	public Point getCoordinates(){
		return returnPoint;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	

}
