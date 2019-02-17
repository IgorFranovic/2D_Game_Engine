package SpaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;

import Core.GameObject;
import Core.ObjectHandler;
import Core.Run;
import GameGraphics.Structure;
import GameGraphics.Vector;

public class Game extends Run {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4808304689488472220L;
	public static final int WIDTH = 1280, HEIGHT = 720;
	private ObjectHandler handler;
	
	public Game() {
		new Window(WIDTH, HEIGHT, "TestClass", this);
		handler = new ObjectHandler();
		Spacecraft spacecraft = new Spacecraft("player", new Structure("./2D_Game_Engine/src/SpaceShooter/spacecraft.dat", 200, 200),
				new Vector(200,200), handler);
		this.addKeyListener(new KeyboardListener(spacecraft, handler));
		handler.addObject(spacecraft);
		handler.addObject(new GameObject("upper bound", new Structure(new Rectangle2D.Float(0, -100, WIDTH, 100), Color.BLACK), 
				new Vector(0,-1), handler));
		handler.addObject(new GameObject("lower bound", new Structure(new Rectangle2D.Float(0, HEIGHT, WIDTH, 100), Color.BLACK), 
				new Vector(0,-1), handler));
		handler.addObject(new GameObject("left bound", new Structure(new Rectangle2D.Float(-100, 0, 100, HEIGHT), Color.BLACK), 
				new Vector(0,-1), handler));
		handler.addObject(new GameObject("right bound", new Structure(new Rectangle2D.Float(WIDTH, 0, 100, HEIGHT), Color.BLACK), 
				new Vector(0,-1), handler));
	}
	
	@Override
	public void update() {
		handler.update();
	}

	@Override
	public void render() {
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bufferStrategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		g.dispose();
		bufferStrategy.show();
	}
	
}
