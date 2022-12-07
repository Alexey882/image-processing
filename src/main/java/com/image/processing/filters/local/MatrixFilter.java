package com.image.processing.filters.local;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Pixel;
import com.image.processing.utils.Utility;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.Arrays;

public abstract class MatrixFilter implements Filter {
    public double[][] kernel = new double[0][];
      int a = 1;
    public int width , height;
    public MatrixFilter(){

    }
    public MatrixFilter(double[][] kernel){
        this.kernel = kernel;
    }

    public MatrixFilter(double[][] kernel, int width, int height) {
        this.kernel = kernel;
        this.width = width;
        this.height = height;
    }

    public MatrixFilter(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public WritableImage processImage(Image image) {
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        this.width= (int) writableImage.getWidth();
        this.height= (int) writableImage.getHeight();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for(int i = 0 ; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixelWriter.setColor(i,j, calculatePixelColor(pixelReader,i,j));
            }
        }
        return writableImage;
    }

    @Override
    public String toString() {
        return "kernel=" + Arrays.deepToString(kernel) + '}';
    }
    @Override
    public Color calculatePixelColor(PixelReader pixelReader,int x , int y) {
        int radiusX = kernel[0].length/2;
        int radiusY = kernel[1].length/2;
        float R = 0, G = 0, B = 0 ;
        for(int i = -radiusX ; i <= radiusX ; i++){
            for(int j = -radiusY ; j <= radiusY ; j++){
                int X = Utility.clamp(x+j ,width-1,0);
                int Y =  Utility.clamp(y+i,height-1,0);
                int pixelNeighbor = pixelReader.getArgb(X,Y);
                Pixel pixel = new Pixel(pixelNeighbor);
                R+=pixel.getR()*kernel[i + radiusX][j + radiusY];
                G+=pixel.getG()*kernel[i + radiusX][j + radiusY];
                B+=pixel.getB()*kernel[i + radiusX][j + radiusY];
            }
        }
        return Pixel.getColor(R,G,B);
    }

}
