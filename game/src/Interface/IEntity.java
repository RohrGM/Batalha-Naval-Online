package Interface;

import java.awt.Graphics2D;

import component.EntityManager;
import util.Body;
import util.Rect2;
import util.Vector2;

public interface IEntity {

	public void draw(Graphics2D graphics2d);

	public void takeDamage(int damage);

	public Vector2 getPosition();

	public boolean isAlive();

	public Rect2 getRect();

	public void update();

	public void setPosition(Vector2 position);

	public void setManager(EntityManager manager);
	
	public Body getBody();

	public IEntity clone();
}
