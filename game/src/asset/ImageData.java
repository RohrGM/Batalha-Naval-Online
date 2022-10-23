package asset;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageData {

	public BufferedImage background;	
	public BufferedImage playerCell;
	public BufferedImage ober1;
	
	public ImageData() {
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/asset/image/background.png"));
			playerCell = ImageIO.read(getClass().getResourceAsStream("/asset/image/playerCell.png"));
			ober1 = ImageIO.read(getClass().getResourceAsStream("/asset/image/ober1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
