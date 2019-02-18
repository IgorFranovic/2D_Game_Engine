package SpaceShooter;

import java.util.LinkedList;

import Core.GameObject;
import Core.ObjectHandler;
import GameGraphics.Structure;
import GameGraphics.Vector;
import Physics.CollisionHandler;
import Physics.CollisionInfo;

public class Handler extends ObjectHandler {

	@Override
	public void update() {
		for(int i = 0; i < this.objectList.size(); i++) {
			this.objectList.get(i).update();
		}
		LinkedList<GameObject> toRemove = new LinkedList<GameObject>();
		LinkedList<GameObject> toAdd = new LinkedList<GameObject>();
		for(int i = 0; i < this.objectList.size()-1; i++) {
			for(int j = i+1; j < this.objectList.size(); j++) {
				GameObject o1 = this.objectList.get(i);
				GameObject o2 = this.objectList.get(j);
				CollisionInfo c12 = o1.getCollisionInfo(o2);
				if(c12 != null) {
					if(o1.id.matches("^.*asteroid$")) {
						if(o2.id.equals("player")) {
							Spacecraft s = (Spacecraft)o2;
							s.health -= 10;
							CollisionHandler.handleCollision(o1, o2, c12);
						}
						else if(o2.id.equals("missile")) {
							toRemove.add(o1);
							toRemove.add(o2);
							toAdd.add(new Explosion("explosion", new Structure(), c12.P, this));
							
							float tempX = (float)(2*Math.random() - 1);
							float tempY = (float)(Math.sqrt(1 - tempX*tempX));
							if(Math.random() < 0.5) {
								tempY *= -1;
							}
							Vector temp = new Vector(tempX, tempY);
							if(o1.id.equals("big asteroid")) {
								float x1 = o1.r.x + 50*temp.x;
								float y1 = o1.r.y + 50*temp.y;
								toAdd.add(new Asteroid("medium asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/medium_asteroid.dat", x1, y1),
										new Vector(x1,y1), new Vector(8*tempX, 8*tempY), new Vector(0,0), (float)(Math.random()-0.5), 0, 35, this));
								temp = temp.R((float)(2*Math.PI/3));
								x1 = o1.r.x + 50*temp.x;
								y1 = o1.r.y + 50*temp.y;
								toAdd.add(new Asteroid("medium asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/medium_asteroid.dat", x1, y1),
										new Vector(x1,y1), new Vector(8*tempX, 8*tempY), new Vector(0,0), (float)(Math.random()-0.5), 0, 35, this));
								temp = temp.R((float)(2*Math.PI/3));
								x1 = o1.r.x + 50*temp.x;
								y1 = o1.r.y + 50*temp.y;
								toAdd.add(new Asteroid("small asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/small_asteroid.dat", x1, y1),
										new Vector(x1,y1), new Vector(12*tempX, 12*tempY), new Vector(0,0), (float)(Math.random()-0.5), 0, 35, this));
							}
							else if(o1.id.equals("medium asteroid")) {
								float x1 = o1.r.x + 50*temp.x;
								float y1 = o1.r.y + 50*temp.y;
								toAdd.add(new Asteroid("small asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/small_asteroid.dat", x1, y1),
										new Vector(x1,y1), new Vector(10*tempX, 10*tempY), new Vector(0,0), (float)(Math.random()-0.5), 0, 35, this));
								temp = temp.R((float)(Math.PI));
								x1 = o1.r.x + 50*temp.x;
								y1 = o1.r.y + 50*temp.y;
								toAdd.add(new Asteroid("small asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/small_asteroid.dat", x1, y1),
										new Vector(x1,y1), new Vector(10*tempX, 10*tempY), new Vector(0,0), (float)(Math.random()-0.5), 0, 35, this));
							}
						}
						else {
							CollisionHandler.handleCollision(o1, o2, c12);
						}
					}
					else if(o2.id.matches("^.*asteroid$")) {
						if(o1.id.equals("player")) {
							Spacecraft s = (Spacecraft)o1;
							s.health -= 10;
							CollisionHandler.handleCollision(o1, o2, c12);
						}
						else if(o1.id.equals("missile")) {
							toRemove.add(o1);
							toRemove.add(o2);
							toAdd.add(new Explosion("explosion", new Structure(), c12.P, this));
							
							float tempX = (float)(2*Math.random() - 1);
							float tempY = (float)(Math.sqrt(1 - tempX*tempX));
							if(Math.random() < 0.5) {
								tempY *= -1;
							}
							Vector temp = new Vector(tempX, tempY);
							if(o2.id.equals("big asteroid")) {
								float x1 = o2.r.x + 50*temp.x;
								float y1 = o2.r.y + 50*temp.y;
								toAdd.add(new Asteroid("medium asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/medium_asteroid.dat", x1, y1),
										new Vector(x1,y1), new Vector(8*tempX, 8*tempY), new Vector(0,0), (float)(Math.random()-0.5), 0, 35, this));
								temp = temp.R((float)(2*Math.PI/3));
								x1 = o2.r.x + 50*temp.x;
								y1 = o2.r.y + 50*temp.y;
								toAdd.add(new Asteroid("medium asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/medium_asteroid.dat", x1, y1),
										new Vector(x1,y1), new Vector(8*tempX, 8*tempY), new Vector(0,0), (float)(Math.random()-0.5), 0, 35, this));
								temp = temp.R((float)(2*Math.PI/3));
								x1 = o2.r.x + 50*temp.x;
								y1 = o2.r.y + 50*temp.y;
								toAdd.add(new Asteroid("small asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/small_asteroid.dat", x1, y1),
										new Vector(x1,y1), new Vector(12*tempX, 12*tempY), new Vector(0,0), (float)(Math.random()-0.5), 0, 35, this));
							}
							else if(o2.id.equals("medium asteroid")) {
								float x1 = o2.r.x + 50*temp.x;
								float y1 = o2.r.y + 50*temp.y;
								toAdd.add(new Asteroid("small asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/small_asteroid.dat", x1, y1),
										new Vector(x1,y1), new Vector(10*tempX, 10*tempY), new Vector(0,0), (float)(Math.random()-0.5), 0, 35, this));
								temp = temp.R((float)(Math.PI));
								x1 = o2.r.x + 50*temp.x;
								y1 = o2.r.y + 50*temp.y;
								toAdd.add(new Asteroid("small asteroid", new Structure("./2D_Game_Engine/src/SpaceShooter/small_asteroid.dat", x1, y1),
										new Vector(x1,y1), new Vector(10*tempX, 10*tempY), new Vector(0,0), (float)(Math.random()-0.5), 0, 35, this));
							}
						}
						else {
							CollisionHandler.handleCollision(o1, o2, c12);
						}
					}
					else if(o1.id.equals("missile") && o2.id.matches("^.*bound$")) {
						toRemove.add(o1);
						toAdd.add(new Explosion("explosion", new Structure(), c12.P, this));
					}
					else if(o1.id.matches("^.*bound$") && o2.id.equals("missile")) {
						toRemove.add(o2);
						toAdd.add(new Explosion("explosion", new Structure(), c12.P, this));
					}
					else {
						CollisionHandler.handleCollision(o1, o2, c12);
					}
				}
			}
		}
		this.objectList.removeAll(toRemove);
		this.objectList.addAll(toAdd);
	}
	
}
