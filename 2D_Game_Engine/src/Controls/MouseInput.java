package Controls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	public void mousePressed(MouseEvent e) {
		System.out.println("("+e.getX() + ", "+ e.getY()+")");
	}

}
