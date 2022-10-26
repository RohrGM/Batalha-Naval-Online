package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import Interface.IEntity;
import asset.ImageData;
import component.EntityManager;
import panel.GameDefend;
import util.AnimatedSprite;
import util.Animation;
import util.Body;
import util.Rect2;
import util.SizePattern;
import util.Vector2;

public class Sentinel implements IEntity, Serializable, Cloneable {

	private Rect2 rect = new Rect2(SizePattern.tileSize * 2, SizePattern.tileSize * 2);
	private static final long serialVersionUID = 1347943847248129981L;
	private static final Rect2 rectOffSet = new Rect2(-16, -64);
	private long frameStart = GameDefend.getFrameCount();
	private ImageData imageData = new ImageData();
	private EntityManager manager;
	private Vector2 position;
	private Animation anim;
	private Body body;
	

	public Sentinel(Vector2 position, EntityManager manager) {
		this.anim = new Animation(new AnimatedSprite(), imageData.sentinel1, true, 7, 3);
		this.position = new Vector2(position.x + rectOffSet.w, position.y + rectOffSet.h);
		this.body = new Body(position, 0, -14, 15, 27);
		this.manager = manager;
		this.anim.start(GameDefend.getFrameCount());
	}

	private void shoot() {
		this.manager.addEntity(new Bullet(this.manager, position.sum(new Vector2(96, 56))));
	}

	@Override
	public void update() {
		System.out.println(this.anim.getIndex(GameDefend.getFrameCount())+"");
		if ((GameDefend.getFrameCount() + this.frameStart) % 90 == 0) {
			this.shoot();
		}
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.setColor(Color.BLUE);
		graphics2d.fillRect(this.body.getX(), this.body.getY(), this.body.getW(), this.body.getH());
		System.out.println(this.anim.getImgPath()+"Idle"+this.anim.getIndex(GameDefend.getFrameCount())+".png");
		
		try {
			graphics2d.drawImage(ImageIO.read(getClass().getResourceAsStream(this.anim.getImgPath()+"Idle"+this.anim.getIndex(GameDefend.getFrameCount())+".png")), this.position.x,
					this.position.y, SizePattern.tileSize * 2, SizePattern.tileSize * 2, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		return true;
	}

	@Override
	public Rect2 getRect() {
		return this.rect;
	}

	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub

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
}
