package Core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public abstract class Run extends Canvas implements Runnable {
	private Thread thread;
	private boolean running = false;
	private int FPS;
	
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		//game loop
		this.requestFocus();
		 long lastTime = System.nanoTime();
		 double amountOfupdates = 60.0;
		 double ns = 1000000000 / amountOfupdates;
		 double delta = 0;
		 long timer = System.currentTimeMillis();
		 FPS = 0;
		 while(running) {
			 long now = System.nanoTime();
			 delta += (now - lastTime) / ns;
			 lastTime = now;
			 while(delta >= 1) {
				 this.update();
				 delta--;
			 }
			 if(running)
				 this.render();
			 FPS++;
			 
			 if(System.currentTimeMillis() - timer > 1000) {
				 timer += 1000;
				 FPS = 0;
				 
			 }
		 }
		 stop();
		
	}
	
	public void update() {
	
	}
	public void render() {
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bufferStrategy.getDrawGraphics();
		/*This part will be reworked to make it easier for the user*/
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		TestClass.draw(g);
		g.dispose();
		bufferStrategy.show();
		
	}
	public int getFPS() {
		return this.FPS; 
		
	}

}
