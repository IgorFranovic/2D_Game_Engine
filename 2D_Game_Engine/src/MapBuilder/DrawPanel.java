package MapBuilder;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	
	public static LinkedList<Object> backgroundObjects = new LinkedList<Object>();;
	public static LinkedList<Object> interactableObjects = new LinkedList<Object>();
//	private LinkedList<Shape> shapes;
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3862371684457001135L;

	@Override
	protected void paintComponent(Graphics g) {
		
		System.out.println("BG: " + BuilderWindow.showBG + " INT: " + BuilderWindow.showINT);
		Graphics2D G = (Graphics2D)g;
		G.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		
		if(BuilderWindow.interactable == true) {
			
			if(BuilderWindow.selectedImage != null && BuilderWindow.currentMouseX > 0 && BuilderWindow.currentMouseY > 0) {
				Object newObject = new Object(BuilderWindow.currentMouseX, BuilderWindow.currentMouseY, BuilderWindow.currentImageWidth, BuilderWindow.currentImageHeight, BuilderWindow.selectedImage, BuilderWindow.interactable);
				interactableObjects.add(newObject);
//				G.drawImage(BuilderWindow.selectedImage, BuilderWindow.currentMouseX, BuilderWindow.currentMouseY, BuilderWindow.currentImageWidth, BuilderWindow.currentImageHeight, this);
			}
			
		} else {
			
			if(BuilderWindow.selectedImage != null && BuilderWindow.currentMouseX > 0 && BuilderWindow.currentMouseY > 0) {
				Object newObject = new Object(BuilderWindow.currentMouseX, BuilderWindow.currentMouseY, BuilderWindow.currentImageWidth, BuilderWindow.currentImageHeight, BuilderWindow.selectedImage, BuilderWindow.interactable);
				backgroundObjects.add(newObject);
//				G.drawImage(BuilderWindow.selectedImage, BuilderWindow.currentMouseX, BuilderWindow.currentMouseY, BuilderWindow.currentImageWidth, BuilderWindow.currentImageHeight, this);
			}
			
		}
		
		if(BuilderWindow.showINT == true) {
			
			for(int i = 0; i < this.interactableObjects.size(); i++) {
	//			G.drawImage(backgroundObjects.get(i).getImage(), backgroundObjects.get(i).x, backgroundObjects.get(i).y, backgroundObjects.get(i).width, backgroundObjects.get(i).height, this);
	//			if(backgroundObjects.size()>0 && backgroundObjects.getLast().x != BuilderWindow.currentMouseX && backgroundObjects.getLast().y != BuilderWindow.currentMouseY)
					G.drawImage(interactableObjects.get(i).getImage(), interactableObjects.get(i).x, interactableObjects.get(i).y, interactableObjects.get(i).width, interactableObjects.get(i).height, this);
				
			}
		}
		if(BuilderWindow.showBG == true) {
			
			for(int i = 0; i < this.backgroundObjects.size(); i++) {
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
			int listSize = this.interactableObjects.size(); 
			for(int i = 0; i < listSize; i++) {
				this.interactableObjects.removeFirst();
			}
		
			System.out.println("list size; "+this.interactableObjects.size());
		} else {
			int listSize = this.backgroundObjects.size(); 
			for(int i = 0; i < listSize; i++) {
				this.backgroundObjects.removeFirst();
			}
		
			System.out.println("list size; "+this.backgroundObjects.size());
		}
		
	}
	
	public void removeLast() {
		if(BuilderWindow.interactable == true) {
			if(this.interactableObjects.size() > 0) {
				this.interactableObjects.removeLast();
				System.out.println("Object removed ls: " + this.interactableObjects.size());
			}
		} else {
			if(this.backgroundObjects.size() > 0) {
				this.backgroundObjects.removeLast();
				System.out.println("Object removed ls: " + this.backgroundObjects.size());
			}
		}
		
			
	}
}
