package entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Interface.IEntity;
import asset.ImageData;
import component.EntityManager;
import service.PlayerDefendKeyHandler;
import util.SizePattern;
import util.Vector2;
import asset.ImageData;

public class PlayerDefend implements IEntity{
	private PlayerDefendKeyHandler keyHandler =  new PlayerDefendKeyHandler(this);
	private ImageData imageData = new ImageData();
	private Vector2 position = new Vector2(64, 256);
	private EntityManager manager;
	
	final private Vector2 LEFTLIMIT = new Vector2(64, 256);
	final private Vector2 RIGHTLIMIT = new Vector2(576, 640);
	final private int SPEED = 128;
	
	public PlayerDefend(EntityManager manager) {
		this.manager = manager;
	}
	
	public PlayerDefendKeyHandler getKeyHandler() {
		return keyHandler;
	}	
	
	public void input(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
		case KeyEvent.VK_W:
			this.position.y = (this.position.y - SPEED < LEFTLIMIT.y)? this.position.y : this.position.y - SPEED;
			break;
		case KeyEvent.VK_S:
			this.position.y = (this.position.y + SPEED > RIGHTLIMIT.y)? this.position.y : this.position.y + SPEED;
			break;
		case KeyEvent.VK_A:
			this.position.x = (this.position.x - SPEED < LEFTLIMIT.x)? this.position.x : this.position.x - SPEED;
			break;
		case KeyEvent.VK_D:
			this.position.x = (this.position.x + SPEED > RIGHTLIMIT.x)? this.position.x : this.position.x + SPEED;
			break;
		case KeyEvent.VK_SPACE:
			this.spawSentinel(position);
			break;
		}
	}
	
	private void spawSentinel(Vector2 position) {
		this.manager.addEntity(new Sentinel(position));
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.drawImage(this.imageData.playerCell, this.position.x, this.position.y,
				SizePattern.tileSize, SizePattern.tileSize, null);
		
	}

	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(Vector2 position) {
		// TODO Auto-generated method stub
		
	}
	
}
