package util;

import Interface.IEntity;

public class Collision {

	public static boolean is_colliding(IEntity b1, IEntity b2) {
		if(b1.equals(b2) == false && 
				b1.getPosition().x + b1.getRect().w > b2.getPosition().x &&
				b2.getPosition().x + b2.getRect().w > b1.getPosition().x &&
				b1.getPosition().y + b1.getRect().h > b2.getPosition().y &&
				b2.getPosition().y + b2.getRect().h > b1.getPosition().y) {
			return true;
		}
		return false;
	}
}
