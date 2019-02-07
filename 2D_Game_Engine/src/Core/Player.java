package Core;

import GameGraphics.Structure;
import GameGraphics.Vector;

public class Player extends GameObject {
	///player id has to be given in the following format: 'player1', 'player2', ... 
	public Player(String id, Structure structure, Vector r, ObjectHandler handler) {
		super(id, structure, r, handler);
	}

	@Override
	public void update() {

//		int x1 = this.structure.getStruct().get(0).getShape().getBounds().x;
//		System.out.println("Player coords: (" + this.getX() + ", " + this.getY() + ") " + x1);
		
	}

}