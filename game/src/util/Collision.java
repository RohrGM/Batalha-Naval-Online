package util;

public class Collision {

	public static boolean is_colliding(Body b1, Body b2) {
		if(b1.equals(b2) == false && 
				b1.getX() + b1.getW() > b2.getX() &&
				b2.getX() + b2.getW() > b1.getX() &&
				b1.getY() + b1.getH() > b2.getY() &&
				b2.getY() + b2.getH() > b1.getY()) {
			return true;
		}
		return false;
	}
}