package com.image.processing.filters.point;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Pixel;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class GrayScaleFilter implements Filter {
    @Override
    public Color calculatePixelColor(PixelReader pixelReader, int x, int y) {
        int color = pixelReader.getArgb(x,y);
        Pixel pixel = new Pixel(color);
        int intensity  = (int) (0.36*pixel.getR() + 0.53*pixel.getG() + 0.11*pixel.getB());
        return Color.rgb(intensity,intensity,intensity);

    }

}
