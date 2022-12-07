package com.image.processing.utils;

import javafx.scene.image.PixelReader;

public interface Utility {
   public static int clamp(int value , int max, int min){
       if(value > max) return max;
       else if(value<min) return min;
       return value;
   }
    public static byte[][] getBinaryMatrix(PixelReader pixelReader, int width , int height){
        byte[][] binaryImage = new byte[width][height];
        for(int i = 0 ; i < width ; i++){
            for(int j = 0; j < height; j++){
                Pixel pixel = new Pixel(pixelReader.getArgb(i,j));
                binaryImage[i][j] = (byte) pixel.getBinaryPixel();
            }
        }
        return binaryImage;
    }
}
