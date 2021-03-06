package Core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import Controls.KeyboardInput;
import GameGraphics.Animation;
import GameGraphics.Structure;
import GameGraphics.Vector;
import MapBuilder.BuilderWindow;
import MapBuilder.DrawPanel;
import MapBuilder.MapObject;
import MapBuilder.TilePanel;

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
	
	public static final int WIDTH = 1280, HEIGHT = 720;
	private static ObjectHandler handler;
	
	public TestClass() {
		
		new Window(WIDTH, HEIGHT, "TestClass", this);
		handler = new ObjectHandler();
		
		this.addKeyListener(new KeyboardInput(handler));
//		
		Player p1 = new Player("player1", new Structure("./Objects/avatar.dat", 400, 300), 
				new Vector(400,300), new Vector(0,0), new Vector(0,0), 0, 0, 5, handler);
//		
		handler.addObject(p1);
		
		//handler.addObject(new GameObject("wall1", new Structure("./Objects/wall1.dat", 300, 300), 
		//		new Vector(300,300), handler));
		/*
		handler.addObject(new GameObject("top", new Structure("./Objects/wallH.dat", 400, 40), 
				new Vector(400,40), handler));
		handler.addObject(new GameObject("bottom", new Structure("./Objects/wallH.dat", 400, 550), 
				new Vector(400,550), handler));
		handler.addObject(new GameObject("left", new Structure("./Objects/wallV.dat", 40, 300), 
				new Vector(40,300), handler));
		handler.addObject(new GameObject("right", new Structure("./Objects/wallV.dat", 750, 300), 
				new Vector(750,300), handler));
		
		handler.addObject(new GameObject("windmill", new Structure("./Objects/windmill1.dat", 400, 300), 
				new Vector(400,300), 0, 0, 5f, handler));
		
		handler.addObject(new GameObject("obj21", new Structure("./Objects/obj2.dat", 200, 450), 
				new Vector(200,450), new Vector(15,-5), new Vector(0,0), 0, 0, 5, handler));
		handler.addObject(new GameObject("obj22", new Structure("./Objects/obj2.dat", 600, 200), 
				new Vector(600,200), new Vector(-9, 15), new Vector(0,0), 0, 0, 5, handler));
		
		*/
		//handler.addObject(new GameObject("obj23", new Structure("./Objects/obj2.dat", 300, 300), 
		//		new Vector(300,300), new Vector(0, 20), new Vector(0,0), 0, 0, 5, handler));
		
		
		
		
		
		try {
			///level_ft1 is made for testing interactions
			String loadPathName = "level_test1";
			String levelsPath = "./LevelTest/";
			BufferedReader br = new BufferedReader(new FileReader(new File(levelsPath + "" + loadPathName + "/" + loadPathName + "_IN.dat")));
			String line;
			int xCoord, yCoord, w, h;
			String imgPath;
			int i = 100;
			while((line = br.readLine()) != null) {
				String[] objectInfo = line.split(":");
//				System.out.println("Ob info  |" + objectInfo[1]);
				xCoord = Integer.parseInt(objectInfo[1].substring(0, objectInfo[1].indexOf(';')));
				yCoord = Integer.parseInt(objectInfo[2].substring(0, objectInfo[2].indexOf(';')));
				w = Integer.parseInt(objectInfo[3].substring(0, objectInfo[3].indexOf(';')));
				h = Integer.parseInt(objectInfo[4].substring(0, objectInfo[4].indexOf(';')));
				imgPath = objectInfo[5].substring(0, objectInfo[5].indexOf(';'));
//				System.out.println("image path:" + imgPath);
//				System.out.println(xCoord + "  " + yCoord + "  " + w + "  " + h + "  " + imgPath);
//				MapObject newObject = new MapObject(xCoord, yCoord, w, h, imgPath, true);
//				try {
//					BufferedImage newImage = ImageIO.read(new File("Assets/LandTiles/bush1.png"));
//				} catch(Exception ex1) {
//					System.out.println("Image exc");
//					
//				}
				
				//new Rectangle(xCoord, yCoord, w, h), newImage
				GameObject newGameObject = new GameObject("obj" + i, new Structure(new Rectangle(xCoord, yCoord, w, h), imgPath), new Vector(w, h), new Vector(0,0), new Vector(0,0), 0, 0, 5, handler);
				handler.addObject(newGameObject);
				Structure [] newFrames = new Structure[1];
//				newFrames[0] = new Structure("./Animations/spelltest" + (i-99)+".dat", 400, 300);
//				
//				newGameObject.setAnimation(new Animation(newFrames, 5, true));
				
				
			
				i++;	//break;
				
			}
			br.close();
		}
		catch (IOException exc) {
			System.out.println("IOEXC");
			exc.printStackTrace();
		}
		
		
		
		
		
		
		
/*		
		Structure [] frames = new Structure[4];
		frames[0] = new Structure("./Animations/spell1.dat", 400, 300);
		frames[1] = new Structure("./Animations/spell2.dat", 400, 300);
		frames[2] = new Structure("./Animations/spell3.dat", 400, 300);
		frames[3] = new Structure("./Animations/spell4.dat", 400, 300);
		p1.setAnimation(new Animation(frames, 5, true));*/
	}


	public static void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		///DRAW BACKGROUND OBJECTS HERE
		try {
			BufferedImage bgImage = ImageIO.read(new File("LevelTest/Level_test1/Level_test1NI.jpg"));
			g.drawImage(bgImage, 0, 0, WIDTH, HEIGHT, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
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