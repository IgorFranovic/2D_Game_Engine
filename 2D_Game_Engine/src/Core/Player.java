package Core;

import GameGraphics.Structure;

public class Player extends GameObject {
	///player id has to be given in the following format: 'player1', 'player2', ... 
	public Player(String id, float x, float y, float dx, float dy, float theta, Structure structure, ObjectHandler handler) {
		super(id , x, y, dx, dy, theta, structure);
		
	}

	@Override
	public void update() {

//		int x1 = this.structure.getStruct().get(0).getShape().getBounds().x;
//		System.out.println("Player coords: (" + this.getX() + ", " + this.getY() + ") " + x1);
		
	}

}
