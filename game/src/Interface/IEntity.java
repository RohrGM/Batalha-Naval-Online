package Interface;

import java.awt.Graphics2D;
import util.Vector2;

public interface IEntity {
	
	public void update();
	public void draw(Graphics2D graphics2d);
	public Vector2 getPosition();
	public void setPosition(Vector2 position);
}
