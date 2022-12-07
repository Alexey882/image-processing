package com.image.processing.filters.point;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Pixel;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.image.*;


public class InversionFilter implements Filter {
    @Override
    public Color calculatePixelColor(PixelReader pixelReader, int x, int y) {
        int color = pixelReader.getArgb(x , y);
        Pixel pixel = new Pixel(color);
        return Color.rgb( 255 - pixel.getR(), 255 - pixel.getG() , 255 - pixel.getB());
    }
}
