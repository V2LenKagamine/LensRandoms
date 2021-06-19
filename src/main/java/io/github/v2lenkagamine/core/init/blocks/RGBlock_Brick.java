package io.github.v2lenkagamine.core.init.blocks;

import java.awt.Color;

public class RGBlock_Brick extends RGBlock{
	
	public RGBlock_Brick() {
		super();
	}
    public static int getColorAsInt(Color color) {
        if (color == null) {
            return 0;
        }
        return ((color.getRed() & 0xFF) << 16) | ((color.getGreen() & 0xFF) << 8) | (color.getBlue() & 0xFF);
    }
}
