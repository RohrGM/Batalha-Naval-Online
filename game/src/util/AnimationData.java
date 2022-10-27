package util;

import java.util.HashMap;
import java.util.Map;

public class AnimationData {
	
	public final static Map<String, Animation> animations = new HashMap<String, Animation>() {
		private static final long serialVersionUID = -2861268004883474665L;
	{
		put("sentinel1Idle", new Animation(null, 0, true, 7, 3));
		put("sentinel1Shoot", new Animation(null, 0,  false, 3, 3));
		put("attacker1Run", new Animation(null, 0,  true, 5, 4));
		put("attacker1Attack", new Animation(null, 0,  false, 5, 4));
	}};	
}
