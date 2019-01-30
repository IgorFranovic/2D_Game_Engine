package Core;

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
 *  The render() method contains method calls related to drawing
 *  graphics.
 * */

public class TestClass extends Run {
	
	public TestClass() {
		this.start();
	}

	public static void main(String[] args) {
		new TestClass();
	}
	
	@Override
	public void update() {
		System.out.println("Test " + this.getFPS());
	}
	@Override
	public void render() {
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bufferStrategy.getDrawGraphics();
		/*This part will be reworked to make it easier for the user*/
	}


}
