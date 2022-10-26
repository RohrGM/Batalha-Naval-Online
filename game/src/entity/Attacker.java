package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Interface.IEntity;
import asset.ImageData;
import component.EntityManager;
import util.Body;
import util.Collision;
import util.Rect2;
import util.SizePattern;
import util.Vector2;

public class Attacker implements IEntity, Serializable, Cloneable {

	private Rect2 rect = new Rect2(SizePattern.tileSize * 2, SizePattern.tileSize * 2);
	private static final long serialVersionUID = 1347943847248129981L;
	private ImageData imageData = new ImageData();
	private boolean isAlive = true;
	private EntityManager manager;
	private Vector2 position;
	private Body body;
	private int damage;
	private int life;

	private final int XOFFSET = -16;
	private final int YOFFSET = -64;
	private final int SPEED = 1;

	public Attacker(EntityManager manager, Vector2 position) {
		this.position = new Vector2(position.x + XOFFSET, position.y + YOFFSET);
		this.body = new Body(this.position, 8, 0, 15, 27);
		this.manager = manager;
		this.damage = 10;
		this.life = 50;
	}

	private void onBodyCollision() {
		List<IEntity> entities = this.manager.getEntities();
		boolean isColliding = false;
		for (IEntity entity : new ArrayList<>(entities)) {
			if (entity.getClass() == Sentinel.class && Collision.is_colliding(this.body, entity.getBody())) {
				isColliding = true;
			}
		}

		if (isColliding == false) {
			this.position.x -= this.SPEED;
		}
	}

	@Override
	public void update() {
		onBodyCollision();
		if (this.position.x <= -100) {
			this.isAlive = false;
		}
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.setColor(Color.BLUE);
		graphics2d.fillRect(this.body.getX(), this.body.getY(), this.body.getW(), this.body.getH());
		try {
			graphics2d.drawImage(ImageIO.read(getClass().getResourceAsStream(this.imageData.zombie1)), this.position.x,
					this.position.y, SizePattern.tileSize * 2, SizePattern.tileSize * 2, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Vector2 getPosition() {
		return this.position;
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

	public Attacker clone() {
		try {
			return (Attacker) super.clone();
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
