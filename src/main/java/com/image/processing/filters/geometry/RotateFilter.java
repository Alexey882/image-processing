package com.image.processing.filters.geometry;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Pixel;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class RotateFilter implements Filter {
    private int width , height ;
    @Override
    public WritableImage processImage(Image image) {
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        this.width = (int) writableImage.getWidth();
        this.height = (int) writableImage.getHeight();
        for(int i = 0 ; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixelWriter.setColor(i,j, calculatePixelColor(pixelReader,i,j));
            }
        }
        return writableImage;
    }

    @Override
    public Color calculatePixelColor(PixelReader pixelReader, int x, int y) {
        int centerRotateX = width/2;
        int centerRotateY = height/2;
        int angle = 175;
        int newX = (int) ((x-centerRotateX)*Math.cos(angle) - (y - centerRotateY)*Math.sin(angle) + centerRotateX);
        int newY = (int) ((x-centerRotateX)*Math.sin(angle) + (y - centerRotateY)*Math.cos(angle) + centerRotateY);
        if(newX>width-1 || newY>height-1 || newX<0 || newY<0)  return Color.rgb(0,0,0);
        int color = pixelReader.getArgb(newX,newY);
        Pixel pixel = new Pixel(color);
        return Color.rgb(pixel.getR(),pixel.getG(),pixel.getB());

    }
}
