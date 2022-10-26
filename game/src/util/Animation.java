package util;

import java.io.Serializable;

public class Animation implements Serializable{
	private static final long serialVersionUID = 1273622894861106080L;
	private AnimatedSprite animatedSprite;
	private String imgPath;
	private boolean loop;
	private int speed;
	private int frames;
	private long frameStart;
	
	public Animation(AnimatedSprite animatedSprite, String imgPath, boolean loop, int speed, int frames) {
		super();
		this.animatedSprite = animatedSprite;
		this.imgPath = imgPath;
		this.loop = loop;
		this.speed = speed;
		this.frames = frames;
	}
	
	public void start(long frameStart) {
		this.frameStart = frameStart;
	}
	
	public int getIndex(long frameCount) {
		int index = (int) ((frameCount / this.speed) % this.frames) ;
		if (this.loop == false) {
			int aux = (int) ((frameCount - this.frameStart) / this.speed);
			if(aux >= this.frames -1) {

			}
		}		
		return index;
	}
	
	public String getImgPath() {
		return this.imgPath;
	}
}
