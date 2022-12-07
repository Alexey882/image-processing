package com.image.processing.filters.geometry;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Pixel;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class WaveFilter implements Filter {
    private int width ;
    @Override
    public WritableImage processImage(Image image) {
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        this.width = (int) writableImage.getWidth();
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
        int newX = (int) (x + 20*Math.sin(2*Math.PI*y/60));
        if(newX>width-1 || newX<0) return Color.rgb(0,0,0);
        int color = pixelReader.getArgb(newX,y);
        Pixel pixel = new Pixel(color);
        return Color.rgb(pixel.getR(),pixel.getG(),pixel.getB());

    }
}
