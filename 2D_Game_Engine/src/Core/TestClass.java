package Core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import Controls.KeyboardInput;
import GameGraphics.Piece;
import GameGraphics.Structure;

/*
 * The game maker has to extend the class 'Run' and override the
 * update() and render() methods as well as call the start() method 
 * in the class constructor. This is done in the class containing
 *  the main() method.
 *  
 *  
 *  The start() method is called once on the start of execution.
 *  
 *  The update() method is called once every frame.
 *  
 *  The draw() method contains method calls related to drawing
 *  graphics.
 * */

public class TestClass extends Run {
	
	public static final int WIDTH = 1200, HEIGHT = 600;
	private static ObjectHandler handler;
	
	public TestClass() {
		
		new Window(WIDTH, HEIGHT, "TestClass", this);
		handler = new ObjectHandler();
		
		this.addKeyListener(new KeyboardInput(handler));
		
		LinkedList<Piece> struct = new LinkedList<Piece>();
		Structure structure = new Structure(struct);
		Rectangle shape1 = new Rectangle(30, 30, 40, 40);
		Piece piece = new Piece(shape1, 100, 100, 100);
		
		structure.add(piece);
		
		
		handler.addObject(new Player("player1", 100, 100, 0, 0, 0, structure, handler));
		
	}


	public static void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		
	}
	
	@Override
	public void update() {
//		System.out.println("Test " + this.getFPS());
		for(GameObject obj : handler.getObjectList()) {
			obj.update();
		}
	}
	

	public static void main(String[] args) {
		new TestClass();
	}

}
