package MapBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ObjectBuilder.ColorTest;



public class BuilderWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7879429050579315307L;
	
	private int WIDTH, HEIGHT;
	public static BufferedImage selectedImage;
	public static int currentMouseX, currentMouseY, currentImageWidth = 40, currentImageHeight = 40;
	public static boolean interactable;
	public static boolean showBG = false, showINT = false;
	public static boolean specialBuildCondition = false;
	public static String assetsPath;
	public static String levelsPath;
	
	
	private TilePanel tilePanel;
	private DrawPanel drawPanel;
	private JPanel controlPanel;
	private JPanel buildPanel;
	private JCheckBox interactableButton;
	
	private DrawPanel drawInteractablePanel;
	private JLayeredPane layeredPane;
	
	private JButton leftScroll, rightScroll;
	
	private ColorTest colorPanel;
	private JPanel sliders;
	private JSlider r, g, b;
	
	private JButton clear, build, undo;
	private SliderListener sl;
	
	private int red, green, blue;
	
	private JLabel layerLabel;
	private JCheckBox bgLayerButton, intLayerButton;
	
	private JPanel layerPanel;
	
	
	private String[] config;
	
	private JTextField levelNameField;
	



	public BuilderWindow() {
		
		
		config = new String[4];
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("./Configuration Files/MapBuilderConfig.txt")));
			for(int i = 0; i < 4; i++) {
				config[i] = br.readLine();
			}
			br.close();
		}
		catch (IOException exc) {
			exc.printStackTrace();
		}
		
		int width = Integer.parseInt(config[0].substring(config[0].indexOf('=')+1));
		int height = Integer.parseInt(config[1].substring(config[1].indexOf('=')+1));
		
		assetsPath = config[2].substring(config[2].indexOf('=')+1);
		levelsPath = config[3].substring(config[3].indexOf('=')+1);
		
		
		this.WIDTH = width; this.HEIGHT = height;
		this.setLayout(new BorderLayout());
		
		this.setTitle("Map Builder");
		
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		MouseListener ml = new MouseListener();
		ButtonListener bl = new ButtonListener();
		
		
		int numberOfTiles = 12;
		int tileHeight = 80;
		tilePanel = new TilePanel();
		tilePanel.setLayout(null);
		tilePanel.setPreferredSize(new Dimension(width, tileHeight));
		tilePanel.setMinimumSize(new Dimension(width, tileHeight));
		tilePanel.setMaximumSize(new Dimension(width, tileHeight));
		tilePanel.addMouseListener(ml);
		tilePanel.addMouseWheelListener(ml);
//		tilePanel.setBounds(0, 0, width, tileHeight);
		
		
		
		
		leftScroll = new JButton("LS");
		rightScroll = new JButton("RS");
		
		leftScroll.setActionCommand("leftScroll");
		rightScroll.setActionCommand("rightScroll");
		leftScroll.addActionListener(bl);
		rightScroll.addActionListener(bl);
		tilePanel.add(leftScroll);
		tilePanel.add(rightScroll);
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
		
		this.add(tilePanel, BorderLayout.NORTH);	
		
		layeredPane = new JLayeredPane();
//		layeredPane.setLayout(new BorderLayout());
//		layeredPane.setLayout(new FlowLayout());
//		layeredPane.setBounds(0, 0, width, height-180);
		layeredPane.setPreferredSize(new Dimension(width, height - 180));
		layeredPane.setMinimumSize(new Dimension(width, height - 180));
		layeredPane.setMaximumSize(new Dimension(width, height - 180));
//		layeredPane.addMouseListener(ml);
//		layeredPane.addMouseMotionListener(ml);
//		layeredPane.addMouseWheelListener(ml);
		
		
		
		
		drawPanel = new DrawPanel();
		drawPanel.setBounds(0, 0, width, height-180);
//		drawPanel.setPreferredSize(new Dimension(width, height-180));
//		drawPanel.setMinimumSize(new Dimension(width, height-180));
//		drawPanel.setMaximumSize(new Dimension(width, height-180));
		drawPanel.addMouseListener(ml);
		drawPanel.addMouseWheelListener(ml);
		drawPanel.addMouseMotionListener(ml);
//		drawPanel.setBounds(0, tileHeight+50, width, height-80);
		
		layeredPane.add(drawPanel, new Integer(1));
		
		drawInteractablePanel = new DrawPanel();
		drawInteractablePanel.setBounds(0, 0, width, height-180);
//		drawInteractablePanel.setPreferredSize(new Dimension(width, height-180));
//		drawInteractablePanel.setMinimumSize(new Dimension(width, height-180));
//		drawInteractablePanel.setMaximumSize(new Dimension(width, height-180));
		drawInteractablePanel.addMouseListener(ml);
		drawInteractablePanel.addMouseWheelListener(ml);
		drawInteractablePanel.addMouseMotionListener(ml);
		layeredPane.add(drawInteractablePanel, new Integer(0));
		
		
		
		this.add(layeredPane, BorderLayout.CENTER);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(1, 4));
		controlPanel.setPreferredSize(new Dimension(width, 80));
		controlPanel.setMinimumSize(new Dimension(width, 80));
		controlPanel.setMaximumSize(new Dimension(width, 80));
		
		
		colorPanel = new ColorTest(Color.BLACK);
		red = green = blue = 0;
		controlPanel.add(colorPanel);
		
		sl = new SliderListener();
		
		sliders = new JPanel();
		sliders.setLayout(new GridLayout(3, 1));
		r = new JSlider(0, 255, 0);
		r.addChangeListener(sl);
		sliders.add(r);
		g = new JSlider(0, 255, 0);
		g.addChangeListener(sl);
		sliders.add(g);
		b = new JSlider(0, 255, 0);
		b.addChangeListener(sl);
		sliders.add(b);
		
		controlPanel.add(sliders);
		
		bl = new ButtonListener();
		
		buildPanel = new JPanel();
		
		buildPanel.setPreferredSize(new Dimension(width/2, 80));
		buildPanel.setMaximumSize(new Dimension(width/2, 80));
		buildPanel.setMinimumSize(new Dimension(width/2, 80));
		
		
		levelNameField = new JTextField("Level_Name");
		buildPanel.add(levelNameField);
		
		clear = new JButton("Clear");
		clear.setActionCommand("clear");
		clear.addActionListener(bl);
		buildPanel.add(clear);
		
		build = new JButton("Build");
		build.setActionCommand("build");
		build.addActionListener(bl);
		buildPanel.add(build);
		
		undo = new JButton("Undo");
		undo.setActionCommand("undo");
		undo.addActionListener(bl);
		buildPanel.add(undo);
		
		CheckBoxListener cbl = new CheckBoxListener();
		
		interactableButton = new JCheckBox("Interactable");
		interactableButton.setToolTipText("Check if the object you are drawing is interactable.");
		interactableButton.setActionCommand("interactableButton");
		interactableButton.addActionListener(cbl);
		buildPanel.add(interactableButton);
		
		
		layerLabel = new JLabel("Layers");
		
		
		bgLayerButton = new JCheckBox("BG");
		bgLayerButton.setToolTipText("Check to show the uninteractable objects.");
		bgLayerButton.setActionCommand("bgLayerButton");
		bgLayerButton.addActionListener(cbl);
		buildPanel.add(bgLayerButton);
		
		intLayerButton = new JCheckBox("INT");
		intLayerButton.setToolTipText("Check to show the interactable objects.");
		intLayerButton.setActionCommand("intLayerButton");
		intLayerButton.addActionListener(cbl);
		buildPanel.add(intLayerButton);
		
		
		layerPanel = new JPanel();
		
		
		layerPanel.add(layerLabel);
		layerPanel.add(bgLayerButton);
		layerPanel.add(intLayerButton);
		
		bgLayerButton.doClick();
		intLayerButton.doClick();
		buildPanel.add(layerPanel);
		
		
		controlPanel.add(buildPanel);
		
	
	
		
		this.add(controlPanel, BorderLayout.SOUTH);
		

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
						tilePanel.repaint();
					}
	
				} break;
				
				case "rightScroll" : {
	//				System.out.println("RS CLICKED");
					if(TilePanel.firstTileNumber+TilePanel.numberOfShownTiles < TilePanel.listSize) {
						TilePanel.firstTileNumber++;
						tilePanel.repaint();
					}
						
				} break;
				
				case "clear" : {
					currentMouseX = -50;
					currentMouseY = -50;
					if(interactable == true) {
						drawInteractablePanel.clearList();
						drawInteractablePanel.repaint();
					} else {
						drawPanel.clearList();
						drawPanel.repaint();
					}
					
					
					
				} break;
				
				case "undo" : {
					System.out.println("undo pressed");
					currentMouseX = -50;
					currentMouseY = -50;
					if(interactable == true) {
						drawInteractablePanel.removeLast();
						drawInteractablePanel.repaint();
					} else {
						drawPanel.removeLast();
						drawPanel.repaint();
					}
				} break;
				
				case "build" : {
					System.out.println("Building...");
		//			Rectangle r = new Rectangle(0, 0, drawPanel.getWidth(), drawPanel.getHeight());
					BufferedImage bi;
					String levelName = levelNameField.getText();
//						bi = ScreenImage.createImage(r);
					String levelDirectory = levelsPath + "" + levelName + "/";
					System.out.println("levels  path: " + levelsPath);
					System.out.println("level dir: " + levelDirectory);
					
					new File(levelsPath + "" + levelName).mkdirs();
					
					try {
						///type object info into file
						BufferedWriter bw = new BufferedWriter(new FileWriter(new File(levelDirectory + "" + levelName + ".dat")));
						int xCoord, yCoord, w, h;
						String img = ""; //implement this later
						for(int i = 0; i < drawPanel.interactableObjects.size(); i++) {
							xCoord = drawPanel.interactableObjects.get(i).getXCoord();
							yCoord = drawPanel.interactableObjects.get(i).getYCoord();
							w = drawPanel.interactableObjects.get(i).getWidthDim();
							h = drawPanel.interactableObjects.get(i).getHeightDim();
							bw.write("Object_" + i + "= x:" + xCoord + "; y:" + yCoord + "; width:" + w + "; height:" + h + "; imagePath:" + img + ";");
							bw.write(System.lineSeparator());
						}
						
						bw.close();
					}
					catch (IOException eception) {
						eception.printStackTrace();
					}
					
					
					LinkedList<Object> tempList = new LinkedList<Object>();
					tempList = DrawPanel.interactableObjects;
					
					interactable = true;
					
					drawPanel.clearList();
					interactable = false;
					
					undo.doClick();
					
					bi = ScreenImage.createImage(drawPanel);
					try {
						ScreenImage.writeImage(bi, levelDirectory + "" + levelName + "NI.jpg");
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					
					drawPanel.interactableObjects = tempList;
					specialBuildCondition = true;
					drawPanel.repaint();
					specialBuildCondition = false;

				}
			
			}
		}
	}
	
	private class CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "interactableButton") {
				System.out.println("Checked / unchecked");
				interactable = !interactable;
				if(interactable == true) {
					layeredPane.setLayer(drawInteractablePanel, new Integer(1));
					layeredPane.setLayer(drawPanel, new Integer(0));
				
					drawInteractablePanel.repaint();
				} else if(interactable == false) {
					layeredPane.setLayer(drawPanel, new Integer(1));
					layeredPane.setLayer(drawInteractablePanel, new Integer(0));
					
					drawPanel.repaint();
				}
				System.out.println(interactable);
			} else if(e.getActionCommand() == "bgLayerButton") {
				showBG = !showBG;
				drawPanel.repaint();
			} else if(e.getActionCommand() == "intLayerButton") {
				showINT = !showINT;
				drawPanel.repaint();
			}
			
		}
		
	}
	
	private class MouseListener extends MouseAdapter {
		
		
		@Override
		public void mousePressed(MouseEvent e) {
		
			if(e.getSource().equals(tilePanel)) {
				System.out.println("Input type c");
				int x = e.getX();
				int y = e.getY();
				if(y < tilePanel.getHeight()) {
					System.out.println("TCLICKED" + (TilePanel.firstTileNumber + x/(WIDTH/TilePanel.numberOfShownTiles)));
					selectedImage = TilePanel.tiles.get(TilePanel.firstTileNumber + x/(WIDTH/TilePanel.numberOfShownTiles));	
				}
			
			} else if(e.getSource().equals(drawPanel)) {
				System.out.println("drawPanel c");
				currentMouseX = e.getX() - 20;
				currentMouseY = e.getY() - 20;
				System.out.println("Mouse clicked in drawPanel at : " + currentMouseX + ", " + currentMouseY);
				
				drawPanel.repaint();

			} else if(e.getSource().equals(drawInteractablePanel)) {
				
				//drawInteractablePanel.requestFocus();
				System.out.println("drawInteractablePanel c");
				currentMouseX = e.getX() - 20;
				currentMouseY = e.getY() - 20;
				System.out.println("Mouse clicked in drawInteractablePanel at : " + currentMouseX + ", " + currentMouseY);
				
				drawInteractablePanel.repaint();
			} else if(e.getSource().equals(layeredPane)) {
				if(interactable == true) {
					drawInteractablePanel.repaint();
				} else {
					drawPanel.repaint();
				}
			}
			
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if(e.getSource().equals(drawPanel)) {
				System.out.println("drawPanel dragged");
				currentMouseX = e.getX() - 20;
				currentMouseY = e.getY() - 20;
				System.out.println("Mouse clicked in drawPanel at : " + currentMouseX + ", " + currentMouseY);
				
				drawPanel.repaint();
				
			} else if(e.getSource().equals(drawInteractablePanel)) {
				System.out.println("drawInteractablePanel dragged");
				currentMouseX = e.getX() - 20;
				currentMouseY = e.getY() - 20;
				System.out.println("Mouse clicked in drawInteractablePanel at : " + currentMouseX + ", " + currentMouseY);
				
				drawInteractablePanel.repaint();
				
			}
		}
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			
			if(e.getWheelRotation() > 0) {
				System.out.println("Rotated downwards");
				currentImageWidth -= 3;
				currentImageHeight -= 2;
				
			} else if(e.getWheelRotation() < 0) {
				System.out.println("Rotated upwards");
				currentImageWidth += 3;
				currentImageHeight += 2;
			}
		}
		
		
	}
	
	private class SliderListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			int value = source.getValue();
			if(source == r) {
				setRed(value);
			}
			else if(source == g) {
				setGreen(value);
			}
			else {
				setBlue(value);
			}
			setColor();
			colorPanel.repaint();
		}
	}
	
	
	public void setRed(int red) {
		this.red = red;
	}
	public void setGreen(int green) {
		this.green = green;
	}
	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	public Color getColor() {
		return new Color(this.red, this.green, this.blue);
	}
	
	public void setColor() {
		this.colorPanel.setColor(new Color(this.red, this.green, this.blue));
	}
//	public String getAssetsPath() {
//		return this.assetsPath;
//	}

}
