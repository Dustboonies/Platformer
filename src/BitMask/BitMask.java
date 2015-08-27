package BitMask;

import java.awt.image.BufferedImage;

public class BitMask {

	public boolean[][] Solid;
	public int width, height;
	
	public BitMask(BufferedImage Image, int width, int height){
		this.width = width; this.height = height;
		Solid = new boolean[width][height];
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				int pixel = Image.getRGB(i, j);
				int red = (pixel >> 16) & 0xFF;
				int green = (pixel >> 8) & 0xFF;
				int blue = (pixel) & 0xFF;
				int alpha = (pixel >> 24) & 0xFF;
				if((red == green && green == blue && blue == 255) || alpha == 0){
					Solid[i][j] = false;
				} else {
					Solid[i][j] = true;
				}
			}
		}
	}
	
	
	
}
