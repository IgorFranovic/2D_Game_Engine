package SpaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		handler = new Handler();
		handler.addObject(new GameObject("upper bound", new Structure(new Rectangle2D.Float(0, -100, WIDTH, 100), Color.BLACK), 
				new Vector(0,-1), handler));
		handler.addObject(new GameObject("lower bound", new Structure(new Rectangle2D.Float(0, HEIGHT-35, WIDTH, 100), Color.BLACK), 
				new Vector(0,-1), handler));
		handler.addObject(new GameObject("left bound", new Structure(new Rectangle2D.Float(-100, 0, 100, HEIGHT), Color.BLACK), 
				new Vector(0,-1), handler));
		handler.addObject(new GameObject("right bound", new Structure(new Rectangle2D.Float(WIDTH-10, 0, 100, HEIGHT), Color.BLACK), 
				new Vector(0,-1), handler));
		handler.addObject(new Asteroid("big asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/big_asteroid.dat", 500, 200),
				new Vector(500,200), new Vector(0, 10), new Vector(0,0), 0, 0, 55, handler));
		handler.addObject(new Asteroid("big asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/big_asteroid.dat", 600, 500),
				new Vector(600,500), new Vector(0, -10), new Vector(0,0), 0, 0, 55, handler));
		handler.addObject(new Asteroid("medium asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/medium_asteroid.dat", 800, 200),
				new Vector(800,300), new Vector(9, 4), new Vector(0,0), 0, 0, 35, handler));
		handler.addObject(new Asteroid("small asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/small_asteroid.dat", 800, 600),
				new Vector(800,600), new Vector(8, -6), new Vector(0,0), 0, 0, 15, handler));
		Spacecraft spacecraft = new Spacecraft("player", new Structure("./2D_Game_Engine/src/SpaceShooter/spacecraft.dat", 200, 200),
				new Vector(200,200), handler);
		this.addKeyListener(new KeyboardListener(spacecraft, handler));
		handler.addObject(spacecraft);
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
		try {
			BufferedImage bgImage = ImageIO.read(new File("./2D_Game_Engine/src/SpaceShooter/stars_universe_space_118205_1280x720.jpg"));
			g.drawImage(bgImage, 0, 0, WIDTH, HEIGHT, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handler.render(g);
		g.dispose();
		bufferStrategy.show();
	}
	
}
