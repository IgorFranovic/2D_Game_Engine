package MapBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class BuilderWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7879429050579315307L;
	
	private static int WIDTH, HEIGHT;
	public static BufferedImage selectedImage;
	public static int currentMouseX, currentMouseY;
	
	
	
	private JPanel inputType;
	private DrawPanel dp;
	
	private JButton leftScroll, rightScroll;
	
	
	private ButtonListener bl;
	




	public BuilderWindow(int width, int height) {
		
		WIDTH = width; HEIGHT = height;
		
		this.setTitle("Map Builder");
		this.setLayout(null);
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		MouseListener ml = new MouseListener();
		
		
		
		int numberOfTiles = 12;
		int tileHeight = 100;
		inputType = new TilePanel();
		inputType.setLayout(null);
		inputType.setPreferredSize(new Dimension(width, tileHeight));
		inputType.setMinimumSize(new Dimension(width, tileHeight));
		inputType.setMaximumSize(new Dimension(width, tileHeight));
		inputType.addMouseListener(ml);
		inputType.setBounds(0, 0, width, tileHeight);
		
		
		
		
		leftScroll = new JButton("LS");
		rightScroll = new JButton("RS");
		
		leftScroll.setActionCommand("leftScroll");
		rightScroll.setActionCommand("rightScroll");
		leftScroll.addActionListener(new ButtonListener());
		rightScroll.addActionListener(new ButtonListener());
		inputType.add(leftScroll);
		inputType.add(rightScroll);
		leftScroll.setBounds(25,5, 40, 40);
		rightScroll.setBounds(width-80,5, 40, 40);
	
		leftScroll.setBorderPainted(false);
		leftScroll.setContentAreaFilled(false);
		rightScroll.setBorderPainted(false);
		rightScroll.setContentAreaFilled(false);
		
		try {
			leftScroll.setIcon(new ImageIcon(ImageIO.read(new File("Assets/ButtonIcons/leftScroll.png"))));
			rightScroll.setIcon(new ImageIcon(ImageIO.read(new File("Assets/ButtonIcons/rightScroll.png"))));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		this.add(inputType);	
		
		
		
		
		dp = new DrawPanel();
		dp.setPreferredSize(new Dimension(width, height-80));
		dp.setMinimumSize(new Dimension(width, height-80));
		dp.setMaximumSize(new Dimension(width, height-80));
		dp.addMouseListener(ml);
		dp.setBounds(0, tileHeight+50, width, height-80);
		this.add(dp);
		
		
		
		

		this.setVisible(true);
	}
	
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String AC = e.getActionCommand();
			switch(AC) {
				case "leftScroll" : {
//					System.out.println("LS CLICKED");
					if(TilePanel.firstTileNumber > 0) {
						TilePanel.firstTileNumber--;
						inputType.repaint();
					}
	
				} break;
				
				case "rightScroll" : {
	//				System.out.println("RS CLICKED");
					if(TilePanel.firstTileNumber < TilePanel.listSize-1) {
						TilePanel.firstTileNumber++;
						inputType.repaint();
					}
						
				} break;
				
			
			}
		}
	}
	
	private class MouseListener extends MouseAdapter {
		
		
		@Override
		public void mousePressed(MouseEvent e) {
			
			if(e.getSource().equals(inputType)) {
				System.out.println("Input type c");
				int x = e.getX();
				int y = e.getY();
				if(y < inputType.getHeight()) {
					System.out.println("TCLICKED" + (TilePanel.firstTileNumber + x/(WIDTH/TilePanel.numberOfShownTiles)));
					selectedImage = TilePanel.tiles.get(TilePanel.firstTileNumber + x/(WIDTH/TilePanel.numberOfShownTiles));	
				}
			
			} else if(e.getSource().equals(dp)) {
				System.out.println("dp c");
				currentMouseX = e.getX();
				currentMouseY = e.getY();
				System.out.println("Mouse clicked in dp at : " + currentMouseX + ", " + currentMouseY);
				dp.repaint();
				
			}
			
		}
		
		
		
	}

}
