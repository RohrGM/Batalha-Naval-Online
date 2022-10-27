package util;

import java.io.Serializable;

public class Animation implements Serializable{
	private static final long serialVersionUID = 1273622894861106080L;
	private AnimatedSprite animatedSprite;
	private boolean loop;
	private int speed;
	private int frames;
	private long frameStart;
	
	public Animation(AnimatedSprite animatedSprite, long frameStart, boolean loop, int speed, int frames) {
		this.animatedSprite = animatedSprite;
		this.loop = loop;
		this.speed = speed;
		this.frames = frames;
		this.frameStart = frameStart;
	}
	
	public int getIndex(long frameCount) {
		int index = (int) ((frameCount / this.speed) % this.frames) ;
		if (this.loop == false) {
			int aux = (int) ((frameCount - this.frameStart) / this.speed);
			if(aux >= this.frames -1) {
				this.animatedSprite.endLoopAnim();
			}
		}		
		return index;
	}

	public void setAnimatedSprite(AnimatedSprite animatedSprite) {
		this.animatedSprite = animatedSprite;
	}

	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	public boolean isLoop() {
		return loop;
	}

	public int getSpeed() {
		return speed;
	}
}
