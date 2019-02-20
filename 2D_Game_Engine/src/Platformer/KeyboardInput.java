package Platformer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import GameGraphics.Vector;
import Core.GameObject;
import Core.ObjectHandler;

public class KeyboardInput extends KeyAdapter {
	
	///basic player movement WSAD
	private ObjectHandler handler;
	private static boolean testCond = true;
	public static boolean attackTriggered = false;
	public static boolean skeleDead = false;
	public KeyboardInput(ObjectHandler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i < handler.getObjectList().size(); i++) {
			GameObject tempObject = handler.getObjectList().get(i);
			if(tempObject.getId().equals("playerTest1")) {
				float step = 5.0f;
				float deg = 0.1f;
				AffineTransform at = new AffineTransform();
				if(key == KeyEvent.VK_W) {
					tempObject.setR(tempObject.getR().sub(new Vector(0, step)));
					at.setToTranslation(0, -step);
					tempObject.transform(at);
					
					tempObject.getStructure().getStruct().get(0).setY(tempObject.getStructure().getStruct().get(0).getShape().getBounds().y);
					tempObject.getStructure().getStruct().get(0).setX(tempObject.getStructure().getStruct().get(0).getShape().getBounds().x);
				} 
				else if(key == KeyEvent.VK_S) {
					tempObject.setR(tempObject.getR().add(new Vector(0, step)));
					at.setToTranslation(0, step);
					tempObject.transform(at);
					
					tempObject.getStructure().getStruct().get(0).setY(tempObject.getStructure().getStruct().get(0).getShape().getBounds().y);
					tempObject.getStructure().getStruct().get(0).setX(tempObject.getStructure().getStruct().get(0).getShape().getBounds().x);
				} 
				else if(key == KeyEvent.VK_A) {
					tempObject.setR(tempObject.getR().sub(new Vector(step, 0)));
					at.setToTranslation(-step, 0);
					tempObject.transform(at);
					tempObject.getStructure().getStruct().get(0).setY(tempObject.getStructure().getStruct().get(0).getShape().getBounds().y);
					tempObject.getStructure().getStruct().get(0).setX(tempObject.getStructure().getStruct().get(0).getShape().getBounds().x);
					String ip = tempObject.getStructure().getStruct().get(0).getImgPath();
					if(ip.length() < 13) {
						ip = "fadventurer-run3-00.png";
					}
					if(ip.charAt(0) != 'f') {
						ip = "f" + ip;
					}
					char ch = ip.charAt(ip.length() - 5);
					String temp = ch + "";
					System.out.println(ip);
					if(Integer.parseInt(temp) < 5) {
						
						ip = ip.substring(0, ip.length() - 5).concat((Integer.parseInt(temp) + 1) + ".png");
					} else {
						ip = ip.substring(0, ip.length() - 5).concat((0) + ".png");
					}
					tempObject.getStructure().getStruct().get(0).setImgPath(ip);
				} 
				else if(key == KeyEvent.VK_D) {					
					System.out.println("Center of mass: " + tempObject.getR().toString());
					tempObject.setR(tempObject.getR().add(new Vector(step, 0)));
					at.setToTranslation(step, 0);
					tempObject.transform(at);
					tempObject.getStructure().getStruct().get(0).setY(tempObject.getStructure().getStruct().get(0).getShape().getBounds().y);
					tempObject.getStructure().getStruct().get(0).setX(tempObject.getStructure().getStruct().get(0).getShape().getBounds().x);
					String ip = tempObject.getStructure().getStruct().get(0).getImgPath();
					if(ip.length() < 13) {
						ip = "adventurer-run3-00.png";
					}
					if(ip.charAt(0) == 'f') {
						ip = ip.substring(1);
					}
					char ch = ip.charAt(ip.length() - 5);
					String temp = ch + "";
					if(Integer.parseInt(temp) < 5) {
						
						ip = ip.substring(0, ip.length() - 5).concat((Integer.parseInt(temp) + 1) + ".png");
					} else {
						ip = ip.substring(0, ip.length() - 5).concat((0) + ".png");
					}
					tempObject.getStructure().getStruct().get(0).setImgPath(ip);
					if(tempObject.getStructure().getStruct().get(0).getX() > 170 && tempObject.getStructure().getStruct().get(0).getY() > 500) {
						if(testCond == true) {
							//	tempObject.getStructure().getStruct().get(0).setX(750);
							//	tempObject.getStructure().getStruct().get(0).setY(40);
								tempObject.setR(tempObject.getR().add(new Vector(540, -490)));
								at.setToTranslation(840, -490);
								tempObject.transform(at);
								String ips = "fadventurer-run3-00.png";
								tempObject.getStructure().getStruct().get(0).setImgPath(ips);
								testCond = false;
								tempObject.getStructure().getStruct().get(0).setX(tempObject.getStructure().getStruct().get(0).getShape().getBounds().y);
								tempObject.getStructure().getStruct().get(0).setY(tempObject.getStructure().getStruct().get(0).getShape().getBounds().x);								
						}										
					} 
				
				
				}
				else if(key == KeyEvent.VK_Q) {
					Vector r = tempObject.getR();
					at.setToRotation(-deg, r.getX(), r.getY());
					tempObject.transform(at);
				} 
				else if(key == KeyEvent.VK_E) {
					Vector r = tempObject.getR();
					at.setToRotation(deg, r.getX(), r.getY());
					tempObject.transform(at);
				} else if(key == KeyEvent.VK_SPACE) {
					for(int cnt = 0; cnt < 10; cnt ++) {
						tempObject.setR(tempObject.getR().sub(new Vector(0, step)));
						at.setToTranslation(0, -step);
						tempObject.transform(at);
						
						tempObject.getStructure().getStruct().get(0).setY(tempObject.getStructure().getStruct().get(0).getShape().getBounds().y);
						tempObject.getStructure().getStruct().get(0).setX(tempObject.getStructure().getStruct().get(0).getShape().getBounds().x);
						
						handler.update();
					}
//					for(int cnt = 0; cnt < 10; cnt ++) {
//						tempObject.setR(tempObject.getR().add(new Vector(0, step)));
//						at.setToTranslation(0, step);
//						tempObject.transform(at);
//						
//						tempObject.getStructure().getStruct().get(0).setY(tempObject.getStructure().getStruct().get(0).getShape().getBounds().y);
//						tempObject.getStructure().getStruct().get(0).setX(tempObject.getStructure().getStruct().get(0).getShape().getBounds().x);
//						handler.update();
//					}
				
				}else if(key == KeyEvent.VK_T) {
					String ip = tempObject.getStructure().getStruct().get(0).getImgPath();
					attackTriggered = !attackTriggered;
					tempObject.getStructure().getStruct().get(0).setImgPath(ip);
					
					
				} else if(key == KeyEvent.VK_X) {
					skeleDead = true;
				}
			}
		}
		
	}
	
}