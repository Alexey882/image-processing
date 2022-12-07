package com.image.processing.filters.geometry;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Pixel;
import com.image.processing.utils.Utility;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.Random;

public class GlassEffectFilter implements Filter {
    private int width ,height;
    @Override
    public WritableImage processImage(Image image) {
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        this.width = (int) writableImage.getWidth();
        this.height = (int) writableImage.getHeight();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for(int i = 0 ; i < width; i++) {
            for (int j = 0; j < writableImage.getHeight(); j++) {
                pixelWriter.setColor(i,j, calculatePixelColor(pixelReader,i,j));
            }
        }
        return writableImage;
    }

    @Override
    public Color calculatePixelColor(PixelReader pixelReader, int x, int y) {
        int newX = (int) (x + (Math.random()-0.5)*10);
        int newY = (int) (y + (Math.random()-0.5)*10);
        int color = pixelReader.getArgb(Utility.clamp(newX,width-1,0), Utility.clamp(newY,height-1,0));
        Pixel pixel = new Pixel(color);
        return Color.rgb(pixel.getR(),pixel.getG(),pixel.getB());

    }
}
