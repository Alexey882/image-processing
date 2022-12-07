package com.image.processing.filters.local;

import com.image.processing.utils.Pixel;
import com.image.processing.utils.Utility;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaximumFilter extends MatrixFilter{
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

    public MaximumFilter() {
        createMedianKernel(1);
    }

    public void createMedianKernel(int radius) {
        int size = radius * 2 + 1;
        kernel = new double[size][size];
    }

    @Override
    public Color calculatePixelColor(PixelReader pixelReader, int x, int y) {
        int radiusX = kernel[0].length/2;
        int radiusY = kernel[1].length/2;
        List<Integer> R = new ArrayList<>();
        List<Integer> G = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        for(int i = -radiusX ; i <= radiusX ; i++){
            for(int j = -radiusY ; j <= radiusY ; j++){
                int X = Utility.clamp(x+i ,width-1,0);
                int Y =  Utility.clamp(y+j,height-1,0);
                int pixelNeighbor = pixelReader.getArgb(X,Y);
                Pixel pixel = new Pixel(pixelNeighbor);
                R.add(pixel.getR());
                G.add(pixel.getG());
                B.add(pixel.getB());
            }
        }
        Collections.sort(R);
        Collections.sort(G);
        Collections.sort(B);
        int size = R.size();
        return Pixel.getColor(R.get(size-1),G.get(size-1),B.get(size-1));
    }
}

