package com.image.processing.filters.global;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Pixel;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class LinearStretchingFilter implements Filter {
    private int width ,height;
    List<Integer> maxBrightness;
    List<Integer> minBrightness ;
    @Override
    public WritableImage processImage(Image image) {
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        this.width= (int) writableImage.getWidth();
        this.height= (int) writableImage.getHeight();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        this.maxBrightness = searchMaxBrightness(pixelReader);
        this.minBrightness = searchMinBrightness(pixelReader);
        for(int i = 0 ; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixelWriter.setColor(i,j, calculatePixelColor(pixelReader,i,j));
            }
        }
        return writableImage;
    }
    public List<Integer> searchMaxBrightness(PixelReader pixelReader) {
        int maxR = 0, maxG = 0, maxB = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Pixel pixel = new Pixel(pixelReader.getArgb(i, j));
                if (pixel.getR() > maxR) maxR = pixel.getR();
                if (pixel.getG() > maxG) maxG = pixel.getG();
                if (pixel.getB() > maxB) maxB = pixel.getB();
            }
        }
        return new ArrayList<>(List.of(maxR ,maxG, maxB));
    }
        public List<Integer> searchMinBrightness(PixelReader pixelReader) {

            int minR = 255, minG = 255, minB = 255;
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Pixel pixel = new Pixel(pixelReader.getArgb(i, j));
                    if (pixel.getR() < minR) minR = pixel.getR();
                    if (pixel.getG() < minG) minG = pixel.getG();
                    if (pixel.getB() < minB) minB = pixel.getB();
                }
            }
            return new ArrayList<>(List.of(minR ,minG, minB));
        }

        public int linearStretch(int brightness , int minBrightness , int maxBrightness){
           int correctionBrightness = (brightness - minBrightness)*255/(maxBrightness - minBrightness);
           return correctionBrightness;
        }
    @Override
    public Color calculatePixelColor(PixelReader pixelReader, int x, int y) {
        Pixel pixel = new Pixel(pixelReader.getArgb(x,y));
        return Color.rgb(
                linearStretch(pixel.getR(), minBrightness.get(0),maxBrightness.get(0)),
                linearStretch(pixel.getG(), minBrightness.get(1),maxBrightness.get(1)),
                linearStretch(pixel.getB(), minBrightness.get(2),maxBrightness.get(2)));
    }
}
