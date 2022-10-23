package util;

public final class SizePattern {

	final private static int originalTileSize = 16;	
	final private static int scale = 4;
	final public static int tileSize = scale * originalTileSize;
	final private static int maxScreenCol = 20;
	final private static int maxScreenRow = 12;
	final public static int screenWidth = tileSize * maxScreenCol;
	final public static int screenHeight = tileSize * maxScreenRow;
	
	
	public static int getXCenterPosition(int xReference, int xItem) {
		return (xReference / 2) - (xItem /2);		
	}
}
