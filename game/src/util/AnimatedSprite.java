package util;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.imageio.ImageIO;

import Interface.IAnimatedEntity;

public class AnimatedSprite implements Serializable {
	private static final long serialVersionUID = 7183961236837973628L;
	private IAnimatedEntity animatedEntity;
	private Vector2 position;
	private Animation anim;
	private String currentAnim;
	private String imgPath;
	private String entityName;

	public AnimatedSprite(IAnimatedEntity animatedEntity, Vector2 position, String startAnim, String imgPath, long frameStart) {
		this.animatedEntity = animatedEntity;
		this.position = position;
		this.currentAnim = startAnim;
		this.imgPath = imgPath;
		this.entityName = formatName();
		this.setCurrentAnim(startAnim, frameStart);
	}

	public void setCurrentAnim(String animName, long frameStart) {
		Animation aux = AnimationData.animations.get(this.entityName + animName);
		this.currentAnim = animName;
		this.anim = new Animation(this,frameStart, aux.isLoop(), aux.getSpeed(), aux.getFrames());
		this.anim.setAnimatedSprite(this);
	}

	public void draw(Graphics2D graphics2d, long frameCount) {
		try {
			graphics2d.drawImage(
					ImageIO.read(getClass().getResourceAsStream(
							this.imgPath + this.currentAnim + this.anim.getIndex(frameCount) + ".png")),
					this.position.x, this.position.y, SizePattern.tileSize * 2, SizePattern.tileSize * 2, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void endLoopAnim() {
		this.animatedEntity.endLoopAnim(this.currentAnim);
	}

	private String formatName() {
		String[] aux = this.imgPath.split("/");

		return aux[aux.length - 1];
	}

}
