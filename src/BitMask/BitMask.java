package BitMask;

import java.awt.image.BufferedImage;

public class BitMask {

	public static boolean[][] Solid;
	
	public BitMask(BufferedImage Image){
		Solid = new boolean[800][600];
		for(int i = 0; i < 800; i++){
			for(int j = 0; j < 600; j++){
				int pixel = Image.getRGB(i, j);
				int red = (pixel >> 16) & 0xFF;
				int green = (pixel >> 8) & 0xFF;
				int blue = (pixel) & 0xFF;
				if(red == green && green == blue && blue == 255){
					Solid[i][j] = false;
				} else {
					Solid[i][j] = true;
				}
			}
		}
	}
	
}
