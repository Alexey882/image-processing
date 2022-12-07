package com.image.processing.filters.point;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Utility;
import com.image.processing.utils.Pixel;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class SepiaFilter implements Filter {
    @Override
    public Color calculatePixelColor(PixelReader pixelReader, int x, int y) {
        int color = pixelReader.getArgb(x , y);
        Pixel pixel = new Pixel(color);
        int intensity  = (int) (0.36*pixel.getG() + 0.53*pixel.getG() + 0.11*pixel.getB());
        return Color.rgb(
                Utility.clamp((int) (intensity+1.05*2),255,0),
                Utility.clamp((int) (intensity+0.5*1.05),255,0),
                Utility.clamp((int) (intensity-1.05),255,0));
    }
}
