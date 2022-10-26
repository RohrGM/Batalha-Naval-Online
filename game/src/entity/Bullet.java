package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Interface.IEntity;
import component.EntityManager;
import util.Body;
import util.Rect2;
import util.SizePattern;
import util.Vector2;

public class Bullet implements IEntity, Serializable, Cloneable {

	private static final long serialVersionUID = 3953297426000767944L;
	private boolean isAlive = true;
	private EntityManager manager;
	private Vector2 position;
	private Rect2 rect;
	private int damage;
	private Body body;

	private final int SPEED = 24;

	public Bullet(EntityManager manager, Vector2 position) {
		this.damage = 10;
		this.position = new Vector2(position.x, position.y);
		this.rect = new Rect2(32, 4);
		this.manager = manager;
		this.body = new Body(this.position, -16, -1, 32, 3);
	}

	private void onBodyCollision() {
		List<IEntity> entities = this.manager.getEntities();
		for (IEntity entity : new ArrayList<>(entities)) {
			if (entity.getClass() == Attacker.class && this.body.is_colliding(entity.getBody())) {
				entity.takeDamage(damage);
				System.out.println("Bati: " + entity);
				this.isAlive = false;
			}
		}
	}

	@Override
	public void update() {
		this.position.x += this.SPEED;
		this.onBodyCollision();

		if (this.position.x > SizePattern.screenWidth - 100) {
			this.isAlive = false;
		}
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.setColor(Color.BLUE);
		graphics2d.fillRect(this.body.getX(), this.body.getY(), this.body.getW(), this.body.getH());
		
		graphics2d.setColor(Color.YELLOW);
		graphics2d.fillRect(this.position.x, this.position.y, this.rect.w, this.rect.h);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Bullet clone() {
		try {
			return (Bullet) super.clone();
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
