package Core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

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
	
	public TestClass() {
		new Window(WIDTH, HEIGHT, "TestClass", this);
	}


	public static void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}
	
	@Override
	public void update() {
		System.out.println("Test " + this.getFPS());
	}
	

	public static void main(String[] args) {
		new TestClass();
	}

}
