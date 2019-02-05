package MapBuilder;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	
	public static LinkedList<MapObject> backgroundObjects = new LinkedList<MapObject>();;
	public static LinkedList<MapObject> interactableObjects = new LinkedList<MapObject>();
//	private LinkedList<Shape> shapes;
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3862371684457001135L;

	@Override
	protected void paintComponent(Graphics g) {
		
		System.out.println("BG: " + BuilderWindow.showBG + " INT: " + BuilderWindow.showINT);
		Graphics2D G = (Graphics2D)g;
		G.clearRect(0, 0, getWidth(), getHeight());
		
		
		if(BuilderWindow.interactable == true) {
			
			if(BuilderWindow.selectedImagePath != "" && BuilderWindow.currentMouseX > 0 && BuilderWindow.currentMouseY > 0) {
				MapObject newObject = new MapObject(BuilderWindow.currentMouseX, BuilderWindow.currentMouseY, BuilderWindow.currentImageWidth, BuilderWindow.currentImageHeight, BuilderWindow.selectedImagePath, BuilderWindow.interactable);
				interactableObjects.add(newObject);
//				G.drawImage(BuilderWindow.selectedImage, BuilderWindow.currentMouseX, BuilderWindow.currentMouseY, BuilderWindow.currentImageWidth, BuilderWindow.currentImageHeight, this);
			}
			
		} else {
			
			if(BuilderWindow.selectedImagePath != "" && BuilderWindow.currentMouseX > 0 && BuilderWindow.currentMouseY > 0) {
				MapObject newObject = new MapObject(BuilderWindow.currentMouseX, BuilderWindow.currentMouseY, BuilderWindow.currentImageWidth, BuilderWindow.currentImageHeight, BuilderWindow.selectedImagePath, BuilderWindow.interactable);
				backgroundObjects.add(newObject);
		//		System.out.println("Image added:" + BuilderWindow.selectedImagePath);
//				G.drawImage(BuilderWindow.selectedImage, BuilderWindow.currentMouseX, BuilderWindow.currentMouseY, BuilderWindow.currentImageWidth, BuilderWindow.currentImageHeight, this);
			}
			
		}
		
		if(BuilderWindow.showINT == true) {
			
			for(int i = 0; i < interactableObjects.size(); i++) {
	//			G.drawImage(backgroundObjects.get(i).getImage(), backgroundObjects.get(i).x, backgroundObjects.get(i).y, backgroundObjects.get(i).width, backgroundObjects.get(i).height, this);
	//			if(backgroundObjects.size()>0 && backgroundObjects.getLast().x != BuilderWindow.currentMouseX && backgroundObjects.getLast().y != BuilderWindow.currentMouseY)
					G.drawImage(interactableObjects.get(i).getImage(), interactableObjects.get(i).x, interactableObjects.get(i).y, interactableObjects.get(i).width, interactableObjects.get(i).height, this);
					
			}
		}
		if(BuilderWindow.showBG == true) {
			
			for(int i = 0; i < backgroundObjects.size(); i++) {
//	/			if(interactableObjects.size()>0 && interactableObjects.getLast().x != BuilderWindow.currentMouseX && interactableObjects.getLast().y != BuilderWindow.currentMouseY)
					G.drawImage(backgroundObjects.get(i).getImage(), backgroundObjects.get(i).x, backgroundObjects.get(i).y, backgroundObjects.get(i).width, backgroundObjects.get(i).height, this);
		//		G.drawImage(interactableObjects.get(i).getImage(), interactableObjects.get(i).x, interactableObjects.get(i).y, interactableObjects.get(i).width, interactableObjects.get(i).height, this);
			}
		}
//		if(BuilderWindow.currentMouseX > 0)
//		G.drawImage(BuilderWindow.selectedImage, BuilderWindow.currentMouseX, BuilderWindow.currentMouseY, BuilderWindow.currentImageWidth, BuilderWindow.currentImageHeight, this);
		
		
		
		
		
		
		
	
	}
	
	
	public void clearList() {
		if(BuilderWindow.interactable == true) {
			int listSize = interactableObjects.size(); 
			for(int i = 0; i < listSize; i++) {
				interactableObjects.removeFirst();
			}
		
			System.out.println("list size; "+interactableObjects.size());
		} else {
			int listSize = backgroundObjects.size(); 
			for(int i = 0; i < listSize; i++) {
				backgroundObjects.removeFirst();
			}
		
			System.out.println("list size; "+backgroundObjects.size());
		}
		
	}
	public void clearList(boolean interactable) {
		if(interactable == true) {
			int listSize = interactableObjects.size(); 
			for(int i = 0; i < listSize; i++) {
				interactableObjects.removeFirst();
			}
		
			System.out.println("list size; "+interactableObjects.size());
		} else {
			int listSize = backgroundObjects.size(); 
			for(int i = 0; i < listSize; i++) {
				backgroundObjects.removeFirst();
			}
		
			System.out.println("list size; "+backgroundObjects.size());
		}
		
	}
	
	public void removeLast() {
		if(BuilderWindow.interactable == true) {
			if(interactableObjects.size() > 0) {
				interactableObjects.removeLast();
				System.out.println("Object removed ls: " + interactableObjects.size());
			}
		} else {
			if(backgroundObjects.size() > 0) {
				backgroundObjects.removeLast();
				System.out.println("Object removed ls: " + backgroundObjects.size());
			}
		}
		
			
	}
}
