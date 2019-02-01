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
import java.util.LinkedList;

import javax.swing.ButtonGroup;
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
	
	private JRadioButton circle, ellipse, square, rectangle, polygon;
	private ButtonGroup group;
	private JLabel l1;
	private JTextField color;
	private JButton clear, build;
	private JPanel inputType, controls;
	private DrawPanel dp;
	
	private RadioButtonListener rbl;
	private ButtonListener bl;
	private MouseListener ml;
	
	private String selectedType;
	private String code;

	public Color getColor() {
		String [] rgb = color.getText().split(",");
		return new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
	}
	
	public String getSelectedType() {
		return selectedType;
	}

	public String getCode() {
		return code;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BuilderWindow(int width, int height) {
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
		
		controls = new JPanel();
		controls.setLayout(new GridLayout(1, 4));
		controls.setPreferredSize(new Dimension(width, 40));
		controls.setMinimumSize(new Dimension(width, 40));
		controls.setMaximumSize(new Dimension(width, 40));
		
		l1 = new JLabel("Color: R,G,B");
		controls.add(l1);
		
		color = new JTextField();
		color.setText("0,0,0");
		controls.add(color);
		
		bl = new ButtonListener();
		
		clear= new JButton("Clear");
		clear.setActionCommand("clear");
		clear.addActionListener(bl);
		controls.add(clear);
		
		build = new JButton("Build");
		build.setActionCommand("build");
		build.addActionListener(bl);
		controls.add(build);
		
		this.add(controls, BorderLayout.SOUTH);
		
		code = "\n";
		
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
			if(e.getActionCommand().equals("clear")) {
				setCode("\n");
				dp.clear();
				dp.repaint();
			}
			else if(e.getActionCommand().equals("build")) {
				System.out.println(getCode());
			}
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
				setCode(getCode() + String.format("Circle (%d,%d,%d,%d) (%d,%d,%d)\n", x, y, 2*r, 2*r,
																					color.getRed(), color.getGreen(), color.getBlue()));
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
				setCode(getCode() + String.format("Ellipse (%d,%d,%d,%d) (%d,%d,%d)\n", x, y, width, height,
																					color.getRed(), color.getGreen(), color.getBlue()));
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
				setCode(getCode() + String.format("Square (%d,%d,%d,%d) (%d,%d,%d)\n", x, y, 2*r, 2*r,
																					color.getRed(), color.getGreen(), color.getBlue()));
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
				setCode(getCode() + String.format("Rectangle (%d,%d,%d,%d) (%d,%d,%d)\n", x, y, width, height,
																					color.getRed(), color.getGreen(), color.getBlue()));
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
				for(int i = 0; i < this.xp.size(); i++) {
					temp += String.format("(%d,%d),", this.xp.get(i), this.yp.get(i));
				}
				temp += String.format(" (%d,%d,%d)\n", color.getRed(), color.getGreen(), color.getBlue());
				setCode(getCode() + temp);
				this.xp.clear();
				this.yp.clear();
				dp.setCurrentShape(null);
				dp.repaint();
			}
		}
		
	}

}
