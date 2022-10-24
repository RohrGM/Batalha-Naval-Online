package entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import Interface.IEntity;
import Interface.IPlayerInput;
import asset.ImageData;
import component.EntityManager;
import service.PlayerInput;
import util.Rect2;
import util.SizePattern;
import util.Vector2;

public class PlayerAttack implements IEntity, Serializable, IPlayerInput, Cloneable {

	private static final long serialVersionUID = -9085298154653380600L;
	private PlayerInput keyHandler = new PlayerInput(this);
	private ImageData imageData = new ImageData();
	private Vector2 position = new Vector2(1216, 256);
	private EntityManager manager;
	private Rect2 rect = new Rect2(SizePattern.tileSize, SizePattern.tileSize);

	final private Vector2 LEFTLIMIT = new Vector2(1216, 256);
	final private Vector2 RIGHTLIMIT = new Vector2(1216, 640);
	final private int SPEED = 128;

	public PlayerAttack(EntityManager manager) {
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
			this.spawAttacker(position);
			break;
		}
	}

	private void spawAttacker(Vector2 position) {
		this.manager.addEntity(new Attacker(this.manager, position));
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

	public PlayerAttack clone() {
		try {
			return (PlayerAttack) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

}
