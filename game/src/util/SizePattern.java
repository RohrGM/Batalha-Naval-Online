package util;

public final class SizePattern {
	
	private static int widht = 608;
	private static int height = 800;
	
	public static int getXCenterPosition(int xParent, int xItem) {
		return (xParent / 2) - (xItem /2);		
	}
	
	
	public static int getWidht() {
		return widht;
	}
	
	public static int getHeight() {
		return height;
	}
		
}
