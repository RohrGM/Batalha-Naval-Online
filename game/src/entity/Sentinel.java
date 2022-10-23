package entity;

import java.awt.Graphics2D;

import Interface.IEntity;
import asset.ImageData;
import util.SizePattern;
import util.Vector2;

public class Sentinel implements IEntity{
	
	private ImageData imageData = new ImageData();
	private Vector2 position;
	
	private final int XOFFSET = -16;
	private final int YOFFSET = -64;
	
	public Sentinel(Vector2 position) {
		this.position = new Vector2(position.x + XOFFSET, position.y + YOFFSET);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.drawImage(this.imageData.ober1, this.position.x, this.position.y,
				SizePattern.tileSize *2 , SizePattern.tileSize * 2, null);
		
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
