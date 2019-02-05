package Core;

import GameGraphics.Structure;

public class Player extends GameObject {
	///player id has to be given in the following format: 'player1', 'player2', ... 
	public Player(String id, Structure structure, float x, float y, ObjectHandler handler) {
		super(id, structure, x, y);
		
	}

	@Override
	public void update() {

//		int x1 = this.structure.getStruct().get(0).getShape().getBounds().x;
//		System.out.println("Player coords: (" + this.getX() + ", " + this.getY() + ") " + x1);
		
	}

}
