package util;

public final class SizePattern {

	private static int widht = 608;
	private static int height = 800;
	private static int xOffSet = -8;
	private static int yOffSet = -32;
	
	public static int getXCenterPosition(int xParent, int xItem) {
		return (xParent / 2) - (xItem /2);		
	}
	
	public static int getWidht() {
		return widht;
	}
	
	public static int getHeight() {
		return height;
	}

	public static int getxOffSet() {
		return xOffSet;
	}

	public static int getyOffSet() {
		return yOffSet;
	}		
}
