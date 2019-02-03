package ObjectBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Polygon;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BuilderWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7879429050579315307L;
	
	private JRadioButton circle, ellipse, square, rectangle, polygon;
	private ButtonGroup group;
	
	private ColorTest ct;
	private JPanel sliders;
	private JSlider r, g, b;
	
	private JButton undo, clear, build;
	private JPanel inputType, controls;
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
	
	public String getSelectedType() {
		return selectedType;
	}

	public int getShapeCount() {
		return shapeCount;
	}
	
	private String getShapeList(){
		if(this.shapeList.isEmpty()) {
			return "0";
		}
		else {
			String temp = Integer.toString(getShapeCount()) + System.lineSeparator();
			for(int i = 0; i < this.shapeList.size()-1; i++) {
				temp += (this.shapeList.get(i) + System.lineSeparator());
			}
			temp += this.shapeList.get(this.shapeList.size()-1);
			return temp;
		}
	}
	
	public String getPath() {
		return path;
	}
	
	public void setColor() {
		this.ct.setColor(new Color(this.red, this.green, this.blue));
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	public void setShapeCount(int cnt) {
		this.shapeCount = cnt;
	}
	
	public void pushShape(String shape) {
		this.shapeList.addLast(shape);
	}
	
	public String popShape() {
		String shape = this.shapeList.getLast();
		this.shapeList.removeLast();
		return shape;
	}
	
	public void clearShapeList() {
		this.shapeList.clear();
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
		
		inputType = new JPanel();
		inputType.setLayout(new GridLayout(1, 5));
		inputType.setPreferredSize(new Dimension(width, 40));
		inputType.setMinimumSize(new Dimension(width, 40));
		inputType.setMaximumSize(new Dimension(width, 40));
		
		rbl = new RadioButtonListener();
		
		circle = new JRadioButton("Circle");
		circle.setActionCommand("circle");
		circle.addActionListener(rbl);
		circle.setSelected(true);
		selectedType = "circle";
		inputType.add(circle);
		
		ellipse = new JRadioButton("Ellipse");
		ellipse.setActionCommand("ellipse");
		ellipse.addActionListener(rbl);
		inputType.add(ellipse);
		
		square = new JRadioButton("Square");
		square.setActionCommand("square");
		square.addActionListener(rbl);
		inputType.add(square);
		
		rectangle = new JRadioButton("Rectangle");
		rectangle.setActionCommand("rectangle");
		rectangle.addActionListener(rbl);
		inputType.add(rectangle);
		
		polygon = new JRadioButton("Polygon");
		polygon.setActionCommand("polygon");
		polygon.addActionListener(rbl);
		inputType.add(polygon);
		
		group = new ButtonGroup();
		group.add(circle);
		group.add(ellipse);
		group.add(square);
		group.add(rectangle);
		group.add(polygon);
		
		this.add(inputType, BorderLayout.NORTH);
		
		ml = new MouseListener();
		
		dp = new DrawPanel();
		dp.setPreferredSize(new Dimension(width, height-80));
		dp.setMinimumSize(new Dimension(width, height-80));
		dp.setMaximumSize(new Dimension(width, height-80));
		dp.addMouseListener(ml);
		dp.addMouseMotionListener(ml);
		dp.addMouseWheelListener(ml);
		this.add(dp, BorderLayout.CENTER);
		
		// questionable
		this.x0 = width/2 - 7;
		this.y0 = height/2 - 40 - 29;
		
		controls = new JPanel();
		controls.setLayout(new GridLayout(1, 5));
		controls.setPreferredSize(new Dimension(width, 60));
		controls.setMinimumSize(new Dimension(width, 60));
		controls.setMaximumSize(new Dimension(width, 60));
		
		ct = new ColorTest(Color.BLACK);
		red = green = blue = 0;
		controls.add(ct);
		
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
		
		controls.add(sliders);
		
		bl = new ButtonListener();
		
		undo = new JButton("Undo");
		undo.setActionCommand("undo");
		undo.addActionListener(bl);
		controls.add(undo);
		
		clear = new JButton("Clear");
		clear.setActionCommand("clear");
		clear.addActionListener(bl);
		controls.add(clear);
		
		build = new JButton("Build");
		build.setActionCommand("build");
		build.addActionListener(bl);
		controls.add(build);
		
		this.add(controls, BorderLayout.SOUTH);
		
		shapeList = new LinkedList<String>();
		shapeCount = 0;
		
		this.pack();
		this.setVisible(true);
	}
	
	private class RadioButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setSelectedType(e.getActionCommand());
		}
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("undo") && getShapeCount() > 0) {
				popShape();
				setShapeCount(getShapeCount() - 1);
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path + "object.dat")));
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
				clearShapeList();
				setShapeCount(0);
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path + "object.dat")));
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
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path + "object.dat")));
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
				setRed(value);
			}
			else if(source == g) {
				setGreen(value);
			}
			else {
				setBlue(value);
			}
			setColor();
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
			String type = getSelectedType();
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
			String type = getSelectedType();
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
			String type = getSelectedType();
			if(type.equals("circle")) {
				int r = (int)Math.sqrt(Math.pow(this.x2 - this.x1, 2) + Math.pow(this.y2 - this.y1, 2));
				int x = this.x1 - r;
				int y = this.y1 - r;
				Color color = getColor();
				dp.add(new Ellipse2D.Float(x, y, 2*r, 2*r), color);
				pushShape(String.format("Circle %d,%d,%d,%d %d,%d,%d\n", x-x0, y-y0, 2*r, 2*r, color.getRed(), color.getGreen(), color.getBlue()));
				setShapeCount(getShapeCount() + 1);
				this.x1 = this.y1 = this.x2 = this.y2 = -1;
				dp.setCurrentShape(null);
				dp.repaint();
			}
			else if(type.equals("ellipse")) {
				int x = Math.min(this.x1, this.x2);
				int y = Math.min(this.y1, this.y2);
				int width = Math.abs(this.x2 - this.x1);
				int height = Math.abs(this.y2 - this.y1);
				Color color = getColor();
				dp.add(new Ellipse2D.Float(x, y, width, height), color);
				pushShape(String.format("Ellipse %d,%d,%d,%d %d,%d,%d\n", x-x0, y-y0, width, height, color.getRed(), color.getGreen(), color.getBlue()));
				setShapeCount(getShapeCount() + 1);
				this.x1 = this.y1 = this.x2 = this.y2 = -1;
				dp.setCurrentShape(null);
				dp.repaint();
			}
			else if(type.equals("square")) {
				int r = (int)Math.sqrt(Math.pow(this.x2 - this.x1, 2) + Math.pow(this.y2 - this.y1, 2));
				int x = this.x1 - r;
				int y = this.y1 - r;
				Color color = getColor();
				dp.add(new Rectangle2D.Float(x, y, 2*r, 2*r), color);
				pushShape(String.format("Square %d,%d,%d,%d %d,%d,%d\n", x-x0, y-y0, 2*r, 2*r, color.getRed(), color.getGreen(), color.getBlue()));
				setShapeCount(getShapeCount() + 1);
				this.x1 = this.y1 = this.x2 = this.y2 = -1;
				dp.setCurrentShape(null);
				dp.repaint();
			}
			else if(type.equals("rectangle")) {
				int x = Math.min(this.x1, this.x2);
				int y = Math.min(this.y1, this.y2);
				int width = Math.abs(this.x2 - this.x1);
				int height = Math.abs(this.y2 - this.y1);
				Color color = getColor();
				dp.add(new Rectangle2D.Float(x, y, width, height), color);
				pushShape(String.format("Rectangle %d,%d,%d,%d %d,%d,%d\n", x-x0, y-y0, width, height, color.getRed(), color.getGreen(), color.getBlue()));
				setShapeCount(getShapeCount() + 1);
				this.x1 = this.y1 = this.x2 = this.y2 = -1;
				dp.setCurrentShape(null);
				dp.repaint();
			}
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			String type = getSelectedType();
			if(type.equals("polygon") && this.xp.size() > 2) {
				Color color = getColor();
				dp.add(dp.getCurrentShape(), color);
				String temp = "Polygon ";
				for(int i = 0; i < this.xp.size()-1; i++) {
					temp += String.format("%d,%d;", this.xp.get(i)-x0, this.yp.get(i)-y0);
				}
				temp += String.format("%d,%d", this.xp.get(this.xp.size()-1)-x0, this.yp.get(this.xp.size()-1)-y0);
				temp += String.format(" %d,%d,%d\n", color.getRed(), color.getGreen(), color.getBlue());
				pushShape(temp);
				setShapeCount(getShapeCount() + 1);
				this.xp.clear();
				this.yp.clear();
				dp.setCurrentShape(null);
				dp.repaint();
			}
		}
		
	}

}
