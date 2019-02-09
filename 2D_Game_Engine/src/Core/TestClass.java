package Core;

import java.awt.Color;
import java.awt.Graphics;

import Controls.KeyboardInput;
import GameGraphics.Animation;
import GameGraphics.Structure;
import GameGraphics.Vector;

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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2047332610043354456L;
	
	public static final int WIDTH = 1200, HEIGHT = 650;
	private static ObjectHandler handler;
	
	public TestClass() {
		
		new Window(WIDTH, HEIGHT, "TestClass", this);
		handler = new ObjectHandler();
		
		this.addKeyListener(new KeyboardInput(handler));
		
		Player p1 = new Player("player1", new Structure("./2D_Game_Engine/Objects/avatar.dat", 400, 300), 
				new Vector(400,300), new Vector(0,0), new Vector(0,0), 0, 0, 5, handler);
		
		handler.addObject(p1);
		
		//handler.addObject(new GameObject("wall1", new Structure("./2D_Game_Engine/Objects/wall1.dat", 300, 300), 
		//		new Vector(300,300), handler));
		
		/*handler.addObject(new GameObject("top", new Structure("./2D_Game_Engine/Objects/wallH.dat", 400, 40), 
				new Vector(400,40), handler));
		handler.addObject(new GameObject("bottom", new Structure("./2D_Game_Engine/Objects/wallH.dat", 400, 550), 
				new Vector(400,550), handler));
		handler.addObject(new GameObject("left", new Structure("./2D_Game_Engine/Objects/wallV.dat", 40, 300), 
				new Vector(40,300), handler));
		handler.addObject(new GameObject("right", new Structure("./2D_Game_Engine/Objects/wallV.dat", 750, 300), 
				new Vector(750,300), handler));*/
		
		/*handler.addObject(new GameObject("windmill", new Structure("./2D_Game_Engine/Objects/windmill1.dat", 400, 300), 
				new Vector(400,300), 0, 0, 5f, handler));
		
		handler.addObject(new GameObject("obj21", new Structure("./2D_Game_Engine/Objects/obj2.dat", 200, 450), 
				new Vector(200,450), new Vector(15,-5), new Vector(0,0), 0, 0, 5, handler));
		handler.addObject(new GameObject("obj22", new Structure("./2D_Game_Engine/Objects/obj2.dat", 600, 200), 
				new Vector(600,200), new Vector(-9, 15), new Vector(0,0), 0, 0, 5, handler));*/
		//handler.addObject(new GameObject("obj23", new Structure("./2D_Game_Engine/Objects/obj2.dat", 300, 300), 
		//		new Vector(300,300), new Vector(0, 20), new Vector(0,0), 0, 0, 5, handler));
		
		Structure [] frames = new Structure[4];
		frames[0] = new Structure("./2D_Game_Engine/Animations/spell1.dat", 400, 300);
		frames[1] = new Structure("./2D_Game_Engine/Animations/spell2.dat", 400, 300);
		frames[2] = new Structure("./2D_Game_Engine/Animations/spell3.dat", 400, 300);
		frames[3] = new Structure("./2D_Game_Engine/Animations/spell4.dat", 400, 300);
		p1.setAnimation(new Animation(frames, 5, true));
	}


	public static void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		
	}
	
	@Override
	public void update() {
//		System.out.println("Test " + this.getFPS());
		handler.update();
	}
	

	public static void main(String[] args) {
		new TestClass();
	}

}