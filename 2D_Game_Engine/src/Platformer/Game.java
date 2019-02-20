package Platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



import GameGraphics.Structure;
import GameGraphics.Vector;

import Core.GameObject;
import Core.ObjectHandler;


public class Game extends Run {
	
	private static final long serialVersionUID = 785759791619045725L;
	public static final int WIDTH = 1280, HEIGHT = 720;
	private static ObjectHandler handler;
	
	private GameObject enemyTest;
	private int enemyIdleCounter = 1;
	private static int dummyCounter = 0;
	
	private static int portalCounter = 1;
	private static boolean deathAnime = true;
	private static int deathAnimeCounter = 1;

	public Game() {
		new Window(WIDTH, HEIGHT, "Platformer", this);
		handler = new ObjectHandler();
		
		this.addKeyListener(new KeyboardInput(handler));
		
		
		/*
		handler.addObject(new GameObject("top", new Structure("./Objects/wallH.dat", 400, 40), 
				new Vector(400,40), handler));
		handler.addObject(new GameObject("bottom", new Structure("./Objects/wallH.dat", 400, 550), 
						new Vector(400,550), handler));
					handler.addObject(new GameObject("left", new Structure("./Objects/wallV.dat", 40, 200), 
							new Vector(40,200), handler));
					handler.addObject(new GameObject("right", new Structure("./Objects/wallV.dat", 750, 200), 
							new Vector(750,200), handler));
		handler.addObject(new GameObject("obj21", new Structure("./Objects/obj2.dat", 200, 450), 
				new Vector(200,450), new Vector(15,-5), new Vector(0,0), 0, 0, 5, handler));
		handler.addObject(new GameObject("obj22", new Structure("./Objects/obj2.dat", 600, 200), 
				new Vector(600,200), new Vector(-9, 15), new Vector(0,0), 0, 0, 5, handler));
		*/
//		Player p1 = new Player("playerTest1", new Structure("./Objects/avatar.dat", 400, 300), 
//				new Vector(400,300), new Vector(0,0), new Vector(0,0), 0, 0, 5, handler);
//		handler.addObject(p1);
		
		String playerImgPath = "adventurer-run3-00.png";
		int pw = 100, ph = 74;
		GameObject playerTest = new GameObject("playerTest1", new Structure(new Rectangle(100, 550, pw - 20, ph + 7), playerImgPath),
				new Vector(100 + pw/2, 300 + ph/2), new Vector(0, 0), new Vector(0,0), 0, 0, 3, handler);
		handler.addObject(playerTest);
		
		String enemyImgPath = "skelidletile000 (1).png";
		int ew = 80, eh = 80;
		this.enemyTest = new GameObject("enemyTest1", new Structure(new Rectangle(850, 52, ew - 20, eh + 7), enemyImgPath),
				new Vector(100 + ew/2, 300 + eh/2), new Vector(0,0), new Vector(0,0), 0, 0, 1, handler);
		handler.addObject(enemyTest);
		
		
		
		
		
		
		try {
			///level_ft1 is made for testing interactions
			String loadPathName = "Level_real1";
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
				GameObject newGameObject = new GameObject("obj" + i, new Structure(new Rectangle(xCoord, yCoord, w, h), imgPath), new Vector(xCoord + w/2, yCoord + h/2), new Vector(0,0), new Vector(0,0), 0, 0, 5, handler);
				handler.addObject(newGameObject);
		//		Structure [] newFrames = new Structure[1];
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
		
	
		
	}
	
	
	public static void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		///DRAW BACKGROUND OBJECTS HERE
		try {
			BufferedImage bgImage = ImageIO.read(new File("LevelTest/Level_real1/Level_real1NI.jpg"));
			BufferedImage bgImage2 = ImageIO.read(new File("Assets/LandTiles/CloudsBack.png"));
			BufferedImage bgImage3 = ImageIO.read(new File("Assets/LandTiles/CloudsFront.png"));
			BufferedImage bgImage4 = ImageIO.read(new File("Assets/LandTiles/BGFront.png"));
			g.drawImage(bgImage, 0, 0, WIDTH, HEIGHT, null);
			g.drawImage(bgImage2,0, 0, WIDTH, HEIGHT, null);
			g.drawImage(bgImage3,0, 0, WIDTH, HEIGHT, null);
			g.drawImage(bgImage4,0, 0, WIDTH, HEIGHT, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		
		
			BufferedImage portalImage;
			String tempstr = "portaltile015 (";
			if(portalCounter <= 16) {
				try {
					portalImage = ImageIO.read(new File("Assets/LandTiles/" + tempstr + portalCounter + ").png"));
				
						g.drawImage(portalImage, 200, 560, 60, 60, null);
						g.drawImage(portalImage, 1050, 30, 80, 60, null);
				} catch (IOException e) {
					// 
					e.printStackTrace();
				}
			}
		//	portalCounter = (portalCounter == 16) ? 1 : portalCounter++;
		//	if(dummyCounter % 1 == 0) {
				if(portalCounter < 16) {
					portalCounter++;
				} else {
					portalCounter = 1;
				}
		//	}
			
		if(KeyboardInput.attackTriggered == true) {
			handler.getObjectList().get(0).getStructure().getStruct().get(0).setImgPath("fattack" + (dummyCounter % 6 + 1) + ".png");
			if(KeyboardInput.skeleDead == false) {
				handler.getObjectList().get(1).getStructure().getStruct().get(0).setImgPath("skelattack (" + (dummyCounter % 19 + 1) + ").png");	
			} 
				
			
		}
		
			if(KeyboardInput.skeleDead == true && deathAnime == true) {
				handler.getObjectList().get(1).getStructure().getStruct().get(0).setImgPath("skeldead (" + (deathAnimeCounter) + ").png");
				
				if(deathAnimeCounter == 15) {
					deathAnime = false;
				} else {
					deathAnimeCounter++;
				}
			}
	
		
		handler.render(g);
		
	}

	@Override
	public void update() {
		handler.update();
		dummyCounter++;
		
		
		String enemyIdle = "";
		if(KeyboardInput.skeleDead == false && dummyCounter % 6 == 0) {
			if(enemyIdleCounter < 9) {
				enemyIdle = "skelidletile000 (" + enemyIdleCounter + ").png";
				enemyIdleCounter ++;
			}else {
				enemyIdleCounter = 1;
				enemyIdle = "skelidletile000 (1).png";
			}
			
			this.enemyTest.getStructure().getStruct().get(0).setImgPath(enemyIdle);
		}
		
	}
	

	public static void main(String[] args) {
		new Game();
	}

}
