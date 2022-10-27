package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Interface.IAnimatedEntity;
import Interface.IEntity;
import asset.ImageData;
import component.EntityManager;
import util.AnimatedSprite;
import util.Body;
import util.Collision;
import util.Rect2;
import util.SizePattern;
import util.Vector2;

public class Attacker implements IAnimatedEntity, Serializable, Cloneable {

	private Rect2 rect = new Rect2(SizePattern.tileSize * 2, SizePattern.tileSize * 2);
	private static final long serialVersionUID = 1347943847248129981L;
	private long frameCount = 0;
	private AnimatedSprite animatedSprite;
	private boolean isColliding = false;
	private boolean isAlive = true;
	private EntityManager manager;
	private Vector2 position;
	private int damage;
	private Body body;
	private int life;

	private final int XOFFSET = -16;
	private final int YOFFSET = -64;
	private final int SPEED = 2;

	public Attacker(EntityManager manager, Vector2 position) {
		this.position = new Vector2(position.x + XOFFSET, position.y + YOFFSET);
		this.animatedSprite = new AnimatedSprite(this, this.position, "Run", ImageData.attacker1, this.frameCount);
		this.body = new Body(this.position, 8, 0, 15, 27);
		this.manager = manager;
		this.damage = 10;
		this.life = 50;
	}

	private IEntity onBodyCollision() {
		List<IEntity> entities = this.manager.getEntities();
		for (IEntity entity : new ArrayList<>(entities)) {
			if (entity.getClass() == Sentinel.class && Collision.is_colliding(this.body, entity.getBody())) {
				this.isColliding = true;
				return entity;
			}
		}
		this.isColliding = false;
		return null;
	}

	@Override
	public void update() {
		this.frameCount++;
		IEntity entityCollider = onBodyCollision();
		if (this.isColliding) {
			if (this.frameCount % 30 == 0 && entityCollider != null) {
				this.animatedSprite.setCurrentAnim("Attack", this.frameCount);
				entityCollider.takeDamage(damage);
			}
		} else {
			this.position.x -= this.SPEED;
		}
		if (this.position.x <= -100) {
			this.isAlive = false;
		}
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		/*raphics2d.setColor(Color.BLUE);
		graphics2d.fillRect(this.body.getX(), this.body.getY(), this.body.getW(), this.body.getH());*/
		this.animatedSprite.draw(graphics2d, this.frameCount);
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

	@Override
	public void endLoopAnim(String anim) {
		if (anim == "Attack") {
			if (this.isColliding) {
				this.animatedSprite.setCurrentAnim("Attack", this.frameCount);
			} else {
				this.animatedSprite.setCurrentAnim("Run", this.frameCount);
			}
		}

	}
}
