package ObjectBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

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
	
	private JRadioButton ellipse, rectangle, polygon;
	private ButtonGroup group;
	private JLabel l1;
	private JTextField color;
	private JButton clear, build;
	private JPanel inputType, controls;
	private DrawPanel dp;
	
	private RadioButtonListener rbl;
	private ButtonListener bl;
	
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
		inputType.setLayout(new GridLayout(1, 3));
		inputType.setPreferredSize(new Dimension(width, 40));
		inputType.setMinimumSize(new Dimension(width, 40));
		inputType.setMaximumSize(new Dimension(width, 40));
		
		rbl = new RadioButtonListener();
		
		ellipse = new JRadioButton("Ellipse");
		ellipse.setActionCommand("ellipse");
		ellipse.addActionListener(rbl);
		ellipse.setSelected(true);
		selectedType = "ellipse";
		inputType.add(ellipse);
		
		rectangle = new JRadioButton("Rectangle");
		rectangle.setActionCommand("rectangle");
		rectangle.addActionListener(rbl);
		inputType.add(rectangle);
		
		polygon = new JRadioButton("Polygon");
		polygon.setActionCommand("polygon");
		polygon.addActionListener(rbl);
		inputType.add(polygon);
		
		group = new ButtonGroup();
		group.add(ellipse);
		group.add(rectangle);
		group.add(polygon);
		
		this.add(inputType, BorderLayout.NORTH);
		
		MouseListener ml = new MouseListener();
		
		dp = new DrawPanel();
		dp.setPreferredSize(new Dimension(width, height-80));
		dp.setMinimumSize(new Dimension(width, height-80));
		dp.setMaximumSize(new Dimension(width, height-80));
		dp.addMouseListener(ml);
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
				
			}
			else if(e.getActionCommand().equals("build")) {
				System.out.println(getCode());
			}
		}
	}
	
	private class MouseListener extends MouseAdapter {
		private int x, y, width, height;
		
		public MouseListener() {
			this.x = this.y = this.width = this.height = -1;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			String type = getSelectedType();
			if(type.equals("ellipse")) {
				if(this.x < 0) {
					this.x = e.getX();
					this.y = e.getY();
				}
				else {
					this.width = e.getX() - this.x;
					this.height = e.getY() - this.y;
					Color color = getColor();
					dp.add(new Ellipse2D.Float(this.x, this.y, this.width, this.height), color);
					setCode(getCode() + String.format("Ellipse (%d,%d,%d,%d) (%d,%d,%d)\n", this.x, this.y, this.width, this.height,
																					color.getRed(), color.getGreen(), color.getBlue()));
					this.x = this.y = this.width = this.height = -1;
				}
			}
			else if(type.equals("rectangle")) {
				if(this.x < 0) {
					this.x = e.getX();
					this.y = e.getY();
				}
				else {
					this.width = e.getX() - this.x;
					this.height = e.getY() - this.y;
					Color color = getColor();
					dp.add(new Rectangle2D.Float(this.x, this.y, this.width, this.height), color);
					setCode(getCode() + String.format("Rectangle (%d,%d,%d,%d) (%d,%d,%d)\n", this.x, this.y, this.width, this.height,
																					color.getRed(), color.getGreen(), color.getBlue()));
					this.x = this.y = this.width = this.height = -1;
				}
			}
			else if(type.equals("polygon")) {
				//...
			}
		}
	}

}
