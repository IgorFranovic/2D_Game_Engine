package GameGraphics;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

public class Animation {

	private Structure [] frames;
	private int delay;
	private int frameCount;
	private boolean loop;
	
	public Animation(Structure[] frames, int delay, boolean loop) {
		this.frames = frames;
		this.delay = delay;
		this.frameCount = 0;
		this.loop = loop;
	}
	
	public boolean isRunning() {
		return frameCount/delay < frames.length;
	}
	
	public void update() {
		frameCount++;
		if(frameCount/delay == frames.length && loop) {
			frameCount = 0;
		}
	}
	
	public void render(Graphics g) {
		frames[frameCount/delay].render(g);
	}
	
	public void transform(AffineTransform at) {
		for(int i = 0; i < frames.length; i++) {
			frames[i].transform(at);
		}
	}

	public int getDelay() {
		return delay;
	}

	public int getFrameCount() {
		return frameCount;
	}

	public boolean isLooped() {
		return loop;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}
	
	
}
