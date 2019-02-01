package MapBuilder;

import javax.swing.SwingUtilities;

public class Main {

	public static final int WIDTH = 1280, HEIGHT = 720;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new BuilderWindow(WIDTH, HEIGHT);
			}
		});
	}

}
