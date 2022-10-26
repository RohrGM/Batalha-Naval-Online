package util;

import java.io.Serializable;

public class Body implements Serializable {
	private static final long serialVersionUID = 7841511573299684551L;
	private Vector2 position;
	private int x;
	private int y;
	private int w;
	private int h;

	public Body(Vector2 position, int x, int y, int w, int h) {
		super();
		this.position = position;
		this.x = x * SizePattern.scale;
		this.y = y * SizePattern.scale;
		this.w = w * SizePattern.scale;
		this.h = h * SizePattern.scale;
	}

	public boolean is_colliding(Body body) {
		if (this.equals(body) == false 
				&& this.x + this.w > body.getX()
				&& body.getX() + this.y > this.x
				&& this.y + this.h > body.getY()
				&& body.getY() + body.getH() > this.y) {
			return true;
		}
		return false;
	}

	public int getX() {
		return this.position.x + this.x;
	}

	public int getY() {
		return this.position.y + y;
	}

	public int getW() {
		return this.w;
	}

	public int getH() {
		return this.h;
	}
}
