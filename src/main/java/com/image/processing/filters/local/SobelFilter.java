package com.image.processing.filters.local;

import com.image.processing.utils.Utility;
import com.image.processing.utils.Pixel;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class SobelFilter extends MatrixFilter{
    public WritableImage processImage(Image image) {
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        this.width= (int) writableImage.getWidth();
        this.height= (int) writableImage.getHeight();
        Image imageX = processImageX(image , width , height);
        Image imageY = processImageY(image, width , height);
        PixelReader pixelReaderX = imageX.getPixelReader();
        PixelReader pixelReaderY = imageY.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for(int i = 1 ; i < width-1; i++) {
            for (int j = 1; j < height-1; j++) {
                int pixelX = pixelReaderX.getArgb(i,j);
                int pixelY = pixelReaderY.getArgb(i,j);
                Pixel pX = new Pixel(pixelX);
                Pixel pY = new Pixel(pixelY);
                int red = (int) Math.sqrt(Math.pow(pX.getR(),2) + Math.pow(pY.getR(),2));
                int green = (int) Math.sqrt(Math.pow(pX.getG(),2) + Math.pow(pY.getG(),2));
                int blue = (int) Math.sqrt(Math.pow(pX.getB(),2) + Math.pow(pY.getB(),2));
                pixelWriter.setColor(i,j, Color.rgb(
                        Utility.clamp(red,255,0),
                        Utility.clamp(green,255,0),
                        Utility.clamp(blue,255,0)));
            }
        }
        return writableImage;
    }

    public WritableImage processImageY(Image image, int width, int height) {
        WritableImage writableImage = new WritableImage(width , height);
        this.width=width;
        this.height=height;
        createSobelKernelY();
      //  System.out.println(Arrays.deepToString(kernel)+" X");
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for(int i = 1 ; i < width-1; i++) {
            for (int j = 1; j < height-1; j++) {
                pixelWriter.setColor(i,j, calculatePixelColor(pixelReader,i,j));
            }
        }
        return writableImage;
    }

    public WritableImage processImageX(Image image, int width, int height) {
        WritableImage writableImage = new WritableImage(width , height);
        this.width=width;
        this.height=height;
        createSobelKernelX();
        System.out.println(Arrays.deepToString(kernel)+" Y");
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for(int i = 0 ; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixelWriter.setColor(i,j, calculatePixelColor(pixelReader,i,j));
            }
        }
        return writableImage;
    }

    private void createSobelKernelY() {
        int size = 3;
        kernel = new double[size][size];
        List<Double> values = List.of(-1.,-2.,-1.);
        for(int i = 0 ; i<size; i++){
            kernel[0][i] = values.get(i);
            kernel[2][i] = -1*values.get(i);
        }
    }
    private void createSobelKernelX() {
        int size = 3;
        kernel = new double[size][size];
        List<Double> values = List.of(-1.,-2.,-1.);
        for(int i = 0 ; i < size; i++){
            kernel[i][0] = values.get(i);
            kernel[i][2] = -1*values.get(i);
        }
    }
}
