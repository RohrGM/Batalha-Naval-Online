package entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Interface.IEntity;
import Interface.IPlayerInput;
import asset.ImageData;
import component.EntityManager;
import service.PlayerInput;
import util.Body;
import util.Rect2;
import util.SizePattern;
import util.Vector2;

public class PlayerDefend implements IEntity, Serializable, IPlayerInput, Cloneable {

	private Rect2 rect = new Rect2(SizePattern.tileSize, SizePattern.tileSize);
	private PlayerInput keyHandler = new PlayerInput(this);
	private Vector2 position = new Vector2(SizePattern.tileSize, SizePattern.tileSize * 4);
	private ImageData imageData = new ImageData();
	private EntityManager manager;

	private static final long serialVersionUID = -9085298154653380600L;
	private final Vector2 RIGHTLIMIT = new Vector2(SizePattern.tileSize * 9, SizePattern.tileSize * 10);
	private final Vector2 LEFTLIMIT = new Vector2(SizePattern.tileSize, SizePattern.tileSize * 4);
	private final int SPEED = SizePattern.tileSize * 2;

	public PlayerDefend(EntityManager manager) {
		this.manager = manager;
	}

	public PlayerInput getKeyHandler() {
		return keyHandler;
	}

	public void input(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
		case KeyEvent.VK_W:
			this.position.y = (this.position.y - SPEED < LEFTLIMIT.y) ? this.position.y : this.position.y - SPEED;
			break;
		case KeyEvent.VK_S:
			this.position.y = (this.position.y + SPEED > RIGHTLIMIT.y) ? this.position.y : this.position.y + SPEED;
			break;
		case KeyEvent.VK_A:
			this.position.x = (this.position.x - SPEED < LEFTLIMIT.x) ? this.position.x : this.position.x - SPEED;
			break;
		case KeyEvent.VK_D:
			this.position.x = (this.position.x + SPEED > RIGHTLIMIT.x) ? this.position.x : this.position.x + SPEED;
			break;
		case KeyEvent.VK_SPACE:
			this.spawSentinel(position);
			break;
		}
	}

	private void spawSentinel(Vector2 position) {
		if (isSpaceAvailable(position)) {
			this.manager.addEntity(new Sentinel(this.position.clone(), this.manager));
		}
	}

	private boolean isSpaceAvailable(Vector2 position) {
		List<IEntity> entities = new ArrayList<>(this.manager.getEntities());
		for (IEntity entity : entities) {
			if (entity.getClass() == Sentinel.class) {

				if (entity.getPosition().compare(position.withOffSet(Sentinel.getRectoffset()))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		try {
			graphics2d.drawImage(ImageIO.read(getClass().getResourceAsStream(this.imageData.playerCell)),
					this.position.x, this.position.y, this.rect.w, this.rect.h, null);
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

	public PlayerDefend clone() {
		try {
			return (PlayerDefend) super.clone();
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
		return null;
	}

}
