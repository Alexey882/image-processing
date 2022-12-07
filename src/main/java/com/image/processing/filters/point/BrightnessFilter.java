package com.image.processing.filters.point;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Pixel;
import com.image.processing.utils.Utility;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class BrightnessFilter implements Filter {
    private final int ADDITIVE_BRIGHTNESS = 17;
    @Override
    public Color calculatePixelColor(PixelReader pixelReader, int x, int y) {
        int color = pixelReader.getArgb(x,y);
        Pixel pixel = new Pixel(color);
        return Pixel.getColor(
                pixel.getR()+ADDITIVE_BRIGHTNESS ,
                pixel.getG()+ADDITIVE_BRIGHTNESS,
                pixel.getB()+ADDITIVE_BRIGHTNESS);

    }

}
