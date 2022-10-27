package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

import Interface.IAnimatedEntity;
import asset.ImageData;
import component.EntityManager;
import util.AnimatedSprite;
import util.Body;
import util.Rect2;
import util.SizePattern;
import util.Vector2;

public class Sentinel implements IAnimatedEntity, Serializable, Cloneable {

	private Rect2 rect = new Rect2(SizePattern.tileSize * 2, SizePattern.tileSize * 2);
	private static final long serialVersionUID = 1347943847248129981L;
	private static final Rect2 rectOffSet = new Rect2(-16, -64);
	private long frameCount = 0;
	private AnimatedSprite animatedSprite;
	private boolean isAlive = true;
	private EntityManager manager;
	private Vector2 position;
	private Body body;
	private int life;
	

	public Sentinel(Vector2 position, EntityManager manager) {
		this.position = new Vector2(position.x + rectOffSet.w, position.y + rectOffSet.h);
		this.body = new Body(position, 0, -14, 15, 27);
		this.manager = manager;
		this.life = 30;
		this.animatedSprite = new AnimatedSprite(this, this.position, "Idle", ImageData.sentinel1, this.frameCount);
	}

	private void shoot() {
		this.manager.addEntity(new Bullet(this.manager, position.sum(new Vector2(96, 56))));
		this.animatedSprite.setCurrentAnim("Shoot", this.frameCount);
	}

	@Override
	public void update() {
		this.frameCount++;
		if (this.frameCount % 90 == 0) {
			this.shoot();
		}
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		/*graphics2d.setColor(Color.BLUE);
		graphics2d.fillRect(this.body.getX(), this.body.getY(), this.body.getW(), this.body.getH());*/
		
		animatedSprite.draw(graphics2d, this.frameCount);
	}

	@Override
	public Vector2 getPosition() {
		return this.position;
	}

	public static Rect2 getRectoffset() {
		return rectOffSet;
	}

	@Override
	public boolean isAlive() {
		return this.isAlive;
	}

	@Override
	public Rect2 getRect() {
		return this.rect;
	}

	@Override
	public void takeDamage(int damage) {
		this.life -= damage;

		if (life <= 0) {
			this.isAlive = false;
		}
	}

	@Override
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Sentinel clone() {
		try {
			return (Sentinel) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Body getBody() {
		return this.body;
	}

	@Override
	public void endLoopAnim(String anim) {
		if(anim.equals("Shoot")) {
			this.animatedSprite.setCurrentAnim("Idle", this.frameCount);
		}
		
	}
}
