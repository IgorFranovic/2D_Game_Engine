package ObjectBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BuilderWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7879429050579315307L;
	
	private ImageIcon circle0, circle1, ellipse0, ellipse1, square0, square1, rectangle0, rectangle1, polygon0, polygon1;
	
	private JRadioButton circle, ellipse, square, rectangle, polygon;
	private ButtonGroup group;
	private JLabel l1;
	private JTextField name;
	private JButton open;
	
	private ColorTest ct;
	private JPanel sliders;
	private JSlider r, g, b;
	
	private JButton undo, clear, build;
	private JPanel top, topLeft, topRight, bottom;
	private DrawPanel dp;
	
	private SliderListener sl;
	private RadioButtonListener rbl;
	private ButtonListener bl;
	private MouseListener ml;
	
	private final int x0, y0;
	
	private int red, green, blue;
	private String selectedType;
	private String [] config;
	private String path;
	
	private int shapeCount;
	private LinkedList<String> shapeList;

	public void switchIcons() {
		if(circle.isSelected()) {
			circle.setIcon(circle1);
		}
		else {
			circle.setIcon(circle0);
		}
		if(ellipse.isSelected()) {
			ellipse.setIcon(ellipse1);
		}
		else {
			ellipse.setIcon(ellipse0);
		}
		if(square.isSelected()) {
			square.setIcon(square1);
		}
		else {
			square.setIcon(square0);
		}
		if(rectangle.isSelected()) {
			rectangle.setIcon(rectangle1);
		}
		else {
			rectangle.setIcon(rectangle0);
		}
		if(polygon.isSelected()) {
			polygon.setIcon(polygon1);
		}
		else {
			polygon.setIcon(polygon0);
		}
	}
	
	private String getShapeList(){
		if(this.shapeList.isEmpty()) {
			return "0";
		}
		else {
			String temp = Integer.toString(shapeCount) + System.lineSeparator();
			for(int i = 0; i < this.shapeList.size()-1; i++) {
				temp += (this.shapeList.get(i) + System.lineSeparator());
			}
			temp += this.shapeList.get(this.shapeList.size()-1);
			return temp;
		}
	}

	public BuilderWindow() {		
		config = new String[3];
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("./2D_Game_Engine/Configuration Files/ObjectBuilderConfig.txt")));
			for(int i = 0; i < 3; i++) {
				config[i] = br.readLine();
			}
			br.close();
		}
		catch (IOException exc) {
			exc.printStackTrace();
		}
		
		int width = Integer.parseInt(config[0].substring(config[0].indexOf('=')+1));
		int height = Integer.parseInt(config[1].substring(config[1].indexOf('=')+1));
		
		path = config[2].substring(config[2].indexOf('=')+1);
		
		this.setTitle("Builder Window");
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		rbl = new RadioButtonListener();
		ml = new MouseListener();
		sl = new SliderListener();
		bl = new ButtonListener();
		
		top = new JPanel();
		top.setLayout(new GridLayout(1, 8));
		top.setPreferredSize(new Dimension(width, 50));
		top.setMinimumSize(new Dimension(width, 50));
		top.setMaximumSize(new Dimension(width, 50));
		
		l1 = new JLabel("Object name: ");
		top.add(l1);
		
		name = new JTextField(20);
		name.setText("object");
		top.add(name);
		
		open = new JButton("Open");
		open.setActionCommand("open");
		open.addActionListener(bl);
		top.add(open);
		
		circle0 = new ImageIcon("./2D_Game_Engine/Assets/ButtonIcons/circle_unselected.png");
		circle1 = new ImageIcon("./2D_Game_Engine/Assets/ButtonIcons/circle_selected.png");
		ellipse0 = new ImageIcon("./2D_Game_Engine/Assets/ButtonIcons/ellipse_unselected.png");
		ellipse1 = new ImageIcon("./2D_Game_Engine/Assets/ButtonIcons/ellipse_selected.png");
		square0 = new ImageIcon("./2D_Game_Engine/Assets/ButtonIcons/square_unselected.png");
		square1 = new ImageIcon("./2D_Game_Engine/Assets/ButtonIcons/square_selected.png");
		rectangle0 = new ImageIcon("./2D_Game_Engine/Assets/ButtonIcons/rectangle_unselected.png");
		rectangle1 = new ImageIcon("./2D_Game_Engine/Assets/ButtonIcons/rectangle_selected.png");
		polygon0 = new ImageIcon("./2D_Game_Engine/Assets/ButtonIcons/polygon_unselected.png");
		polygon1 = new ImageIcon("./2D_Game_Engine/Assets/ButtonIcons/polygon_selected.png");
		
		circle = new JRadioButton(circle1);
		circle.setActionCommand("circle");
		circle.addActionListener(rbl);
		circle.setSelected(true);
		selectedType = "circle";
		top.add(circle);
		
		ellipse = new JRadioButton(ellipse0);
		ellipse.setActionCommand("ellipse");
		ellipse.addActionListener(rbl);
		top.add(ellipse);
		
		square = new JRadioButton(square0);
		square.setActionCommand("square");
		square.addActionListener(rbl);
		top.add(square);
		
		rectangle = new JRadioButton(rectangle0);
		rectangle.setActionCommand("rectangle");
		rectangle.addActionListener(rbl);
		top.add(rectangle);
		
		polygon = new JRadioButton(polygon0);
		polygon.setActionCommand("polygon");
		polygon.addActionListener(rbl);
		top.add(polygon);
		
		group = new ButtonGroup();
		group.add(circle);
		group.add(ellipse);
		group.add(square);
		group.add(rectangle);
		group.add(polygon);
		
		this.add(top, BorderLayout.NORTH);
		
		dp = new DrawPanel();
		dp.setPreferredSize(new Dimension(width, height-110));
		dp.setMinimumSize(new Dimension(width, height-110));
		dp.setMaximumSize(new Dimension(width, height-110));
		dp.addMouseListener(ml);
		dp.addMouseMotionListener(ml);
		dp.addMouseWheelListener(ml);
		this.add(dp, BorderLayout.CENTER);
		
		// questionable
		this.x0 = width/2 - 7;
		this.y0 = height/2 - 55 - 20;
		
		bottom = new JPanel();
		bottom.setLayout(new GridLayout(1, 5));
		bottom.setPreferredSize(new Dimension(width, 60));
		bottom.setMinimumSize(new Dimension(width, 60));
		bottom.setMaximumSize(new Dimension(width, 60));
		
		ct = new ColorTest(Color.BLACK);
		red = green = blue = 0;
		bottom.add(ct);
		
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
		
		bottom.add(sliders);
		
		undo = new JButton("Undo");
		undo.setActionCommand("undo");
		undo.addActionListener(bl);
		bottom.add(undo);
		
		clear = new JButton("Clear");
		clear.setActionCommand("clear");
		clear.addActionListener(bl);
		bottom.add(clear);
		
		build = new JButton("Build");
		build.setActionCommand("build");
		build.addActionListener(bl);
		bottom.add(build);
		
		this.add(bottom, BorderLayout.SOUTH);
		
		shapeList = new LinkedList<String>();
		shapeCount = 0;
		
		this.pack();
		this.setVisible(true);
	}
	
	private class RadioButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedType = e.getActionCommand();
			switchIcons();
		}
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("open")) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(new File(path + name.getText() + ".dat")));
					int cnt = Integer.parseInt(br.readLine());
					shapeList.clear();
					dp.clear();
					shapeCount = cnt;
					for(int i = 0; i < cnt; i++) {
						String line = br.readLine();
						shapeList.addLast(line);
						String [] temp = line.split(" ");
						if(temp[0].equals("Polygon")) {
							String [] coords = temp[1].split(";");
							int [] xp = new int[coords.length];
							int [] yp = new int[coords.length];
							for(int j = 0; j < coords.length; j++) {
								String [] xy = coords[j].split(",");
								xp[j] = Integer.parseInt(xy[0]) + x0;
								yp[j] = Integer.parseInt(xy[1]) + y0;
							}
							Shape shape = new Polygon(xp, yp, coords.length);
							String [] rgb = temp[2].split(",");
							int r = Integer.parseInt(rgb[0]);
							int g = Integer.parseInt(rgb[1]);
							int b = Integer.parseInt(rgb[2]);
							Color color = new Color(r, g, b);
							dp.add(shape, color);
						}
						else {
							String [] coords = temp[1].split(",");
							int x = Integer.parseInt(coords[0]) + x0;
							int y = Integer.parseInt(coords[1]) + y0;
							int w = Integer.parseInt(coords[2]);
							int h = Integer.parseInt(coords[3]);
							Shape shape;
							if(temp[0].equals("Circle") || temp[0].equals("Ellipse")) {
								shape = new Ellipse2D.Float(x, y, w, h);
							}
							else {
								shape = new Rectangle2D.Float(x, y, w, h);
							}
							String [] rgb = temp[2].split(",");
							int r = Integer.parseInt(rgb[0]);
							int g = Integer.parseInt(rgb[1]);
							int b = Integer.parseInt(rgb[2]);
							Color color = new Color(r, g, b);
							dp.add(shape, color);
						}
					}
					dp.repaint();
					br.close();
				}
				catch (IOException exc) {
					System.out.println("Object " + name.getText() + " doesn't exist in the current workspace.");
					//exc.printStackTrace();
				}
			}
			else if(e.getActionCommand().equals("undo") && shapeCount > 0) {
				shapeList.removeLast();
				shapeCount--;
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path + name.getText() + ".dat")));
					bw.write(getShapeList());
					bw.close();
				}
				catch (IOException exc) {
					exc.printStackTrace();
				}
				dp.remove();
				dp.repaint();
			}
			else if(e.getActionCommand().equals("clear")) {
				shapeList.clear();;
				shapeCount = 0;
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path + name.getText() + ".dat")));
					bw.write("0");
					bw.close();
				}
				catch (IOException exc) {
					exc.printStackTrace();
				}
				dp.clear();
				dp.repaint();
			}
			else if(e.getActionCommand().equals("build")) {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path + name.getText() + ".dat")));
					bw.write(getShapeList());
					bw.close();
				}
				catch (IOException exc) {
					exc.printStackTrace();
				}
			}
		}
	}
	
	private class SliderListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			int value = source.getValue();
			if(source == r) {
				red = value;
			}
			else if(source == g) {
				green = value;
			}
			else {
				blue = value;
			}
			ct.setColor(new Color(red, green, blue));
			ct.repaint();
		}
	}
	
	private class MouseListener extends MouseAdapter {
		private int x1, y1, x2, y2;
		private LinkedList<Integer> xp, yp;
		
		public MouseListener() {
			this.x1 = this.y1 = this.x2 = this.y2 = -1;
			this.xp = new LinkedList<Integer>();
			this.yp = new LinkedList<Integer>();
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println((e.getX()-x0) + " " + (e.getY()-y0));
			String type = selectedType;
			if(type.equals("circle") || type.equals("ellipse") || type.equals("square") || type.equals("rectangle")) {
				this.x1 = e.getX();
				this.y1 = e.getY();
			}
			else if(type.equals("polygon")) {
				this.xp.add(e.getX());
				this.yp.add(e.getY());
				int [] currX = new int[this.xp.size()];
				int [] currY = new int[this.yp.size()];
				for(int i = 0; i < currX.length; i++) {
					currX[i] = this.xp.get(i);
					currY[i] = this.yp.get(i);
				}
				dp.setCurrentShape(new Polygon(currX, currY, currX.length));
				dp.repaint();
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			String type = selectedType;
			if(type.equals("circle")) {
				this.x2 = e.getX();
				this.y2 = e.getY();
				int r = (int)Math.sqrt(Math.pow(this.x2 - this.x1, 2) + Math.pow(this.y2 - this.y1, 2));
				int x = this.x1 - r;
				int y = this.y1 - r;
				dp.setCurrentShape(new Ellipse2D.Float(x, y, 2*r, 2*r));
				dp.repaint();
			}
			else if(type.equals("ellipse")) {
				this.x2 = e.getX();
				this.y2 = e.getY();
				int x = Math.min(this.x1, this.x2);
				int y = Math.min(this.y1, this.y2);
				int width = Math.abs(this.x2 - this.x1);
				int height = Math.abs(this.y2 - this.y1);
				dp.setCurrentShape(new Ellipse2D.Float(x, y, width, height));
				dp.repaint();
			}
			else if(type.equals("square")) {
				this.x2 = e.getX();
				this.y2 = e.getY();
				int r = (int)Math.sqrt(Math.pow(this.x2 - this.x1, 2) + Math.pow(this.y2 - this.y1, 2));
				int x = this.x1 - r;
				int y = this.y1 - r;
				dp.setCurrentShape(new Rectangle2D.Float(x, y, 2*r, 2*r));
				dp.repaint();
			}
			else if(type.equals("rectangle")) {
				this.x2 = e.getX();
				this.y2 = e.getY();
				int x = Math.min(this.x1, this.x2);
				int y = Math.min(this.y1, this.y2);
				int width = Math.abs(this.x2 - this.x1);
				int height = Math.abs(this.y2 - this.y1);
				dp.setCurrentShape(new Rectangle2D.Float(x, y, width, height));
				dp.repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			String type = selectedType;
			if(type.equals("circle")) {
				int r = (int)Math.sqrt(Math.pow(this.x2 - this.x1, 2) + Math.pow(this.y2 - this.y1, 2));
				int x = this.x1 - r;
				int y = this.y1 - r;
				Color color = new Color(red, green, blue);
				dp.add(new Ellipse2D.Float(x, y, 2*r, 2*r), color);
				shapeList.addLast(String.format("Circle %d,%d,%d,%d %d,%d,%d", x-x0, y-y0, 2*r, 2*r, color.getRed(), color.getGreen(), color.getBlue()));
				shapeCount++;
				this.x1 = this.y1 = this.x2 = this.y2 = -1;
				dp.setCurrentShape(null);
				dp.repaint();
			}
			else if(type.equals("ellipse")) {
				int x = Math.min(this.x1, this.x2);
				int y = Math.min(this.y1, this.y2);
				int width = Math.abs(this.x2 - this.x1);
				int height = Math.abs(this.y2 - this.y1);
				Color color = new Color(red, green, blue);
				dp.add(new Ellipse2D.Float(x, y, width, height), color);
				shapeList.addLast(String.format("Ellipse %d,%d,%d,%d %d,%d,%d", x-x0, y-y0, width, height, color.getRed(), color.getGreen(), color.getBlue()));
				shapeCount++;
				this.x1 = this.y1 = this.x2 = this.y2 = -1;
				dp.setCurrentShape(null);
				dp.repaint();
			}
			else if(type.equals("square")) {
				int r = (int)Math.sqrt(Math.pow(this.x2 - this.x1, 2) + Math.pow(this.y2 - this.y1, 2));
				int x = this.x1 - r;
				int y = this.y1 - r;
				Color color = new Color(red, green, blue);
				dp.add(new Rectangle2D.Float(x, y, 2*r, 2*r), color);
				shapeList.addLast(String.format("Square %d,%d,%d,%d %d,%d,%d", x-x0, y-y0, 2*r, 2*r, color.getRed(), color.getGreen(), color.getBlue()));
				shapeCount++;
				this.x1 = this.y1 = this.x2 = this.y2 = -1;
				dp.setCurrentShape(null);
				dp.repaint();
			}
			else if(type.equals("rectangle")) {
				int x = Math.min(this.x1, this.x2);
				int y = Math.min(this.y1, this.y2);
				int width = Math.abs(this.x2 - this.x1);
				int height = Math.abs(this.y2 - this.y1);
				Color color = new Color(red, green, blue);
				dp.add(new Rectangle2D.Float(x, y, width, height), color);
				shapeList.addLast(String.format("Rectangle %d,%d,%d,%d %d,%d,%d", x-x0, y-y0, width, height, color.getRed(), color.getGreen(), color.getBlue()));
				shapeCount++;
				this.x1 = this.y1 = this.x2 = this.y2 = -1;
				dp.setCurrentShape(null);
				dp.repaint();
			}
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			String type = selectedType;
			if(type.equals("polygon") && this.xp.size() > 2) {
				Color color = new Color(red, green, blue);
				dp.add(dp.getCurrentShape(), color);
				String temp = "Polygon ";
				for(int i = 0; i < this.xp.size()-1; i++) {
					temp += String.format("%d,%d;", this.xp.get(i)-x0, this.yp.get(i)-y0);
				}
				temp += String.format("%d,%d", this.xp.get(this.xp.size()-1)-x0, this.yp.get(this.xp.size()-1)-y0);
				temp += String.format(" %d,%d,%d", color.getRed(), color.getGreen(), color.getBlue());
				shapeList.addLast(temp);
				shapeCount++;
				this.xp.clear();
				this.yp.clear();
				dp.setCurrentShape(null);
				dp.repaint();
			}
		}
		
	}

}
