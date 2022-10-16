package util;

public class SizePattern {
	
	private int widht = 608;
	private int height = 800;
	
	public int getXCenterPosition(int xParent, int xItem) {
		return (xParent / 2) - (xItem /2);		
	}
	
	
	public int getWidht() {
		return widht;
	}
	
	public int getHeight() {
		return height;
	}
		
}
