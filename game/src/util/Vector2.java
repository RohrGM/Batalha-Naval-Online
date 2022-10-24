package util;

import java.io.Serializable;

public class Vector2 implements Serializable, Cloneable {

	private static final long serialVersionUID = 1200463141913352706L;
	public int x;
	public int y;

	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector2 sum(Vector2 v) {
		return new Vector2(x + v.x, y + v.y);
	}

	public boolean compare(Vector2 v) {
		return x == v.x && y == v.y ? true : false;
	}

	public Vector2 withOffSet(Rect2 r) {
		return new Vector2(x + r.w, y + r.h);
	}

	public Vector2 clone() {
		try {
			return (Vector2) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
