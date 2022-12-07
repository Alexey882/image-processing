package com.image.processing.filters.local;

import com.image.processing.utils.Pixel;
import com.image.processing.utils.Utility;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.*;

public class MedianFilter extends MatrixFilter{

    public MedianFilter() {
        createMedianKernel(1);
    }

    public void createMedianKernel(int radius) {
        int size = radius * 2 + 1;
        kernel = new double[size][size];
    }

    @Override
    public Color calculatePixelColor(PixelReader pixelReader, int x , int y) {
        int radiusX = kernel[0].length/2;
        int radiusY = kernel[1].length/2;
        List<Integer> R = new ArrayList<>();
        List<Integer> G = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        for(int i = -radiusX ; i <= radiusX ; i++){
            for(int j = -radiusY ; j <= radiusY ; j++){
                int X = Utility.clamp(x+i ,width-1,0);
                int Y = Utility.clamp(y+j,height-1,0);
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
        int medianNumber = R.size()/2;
        if(medianNumber%2!=0)
            return Pixel.getColor(R.get(medianNumber),G.get(medianNumber),B.get(medianNumber));

        return Pixel.getColor(
                (R.get(medianNumber)+R.get(medianNumber-1))/2,
                (G.get(medianNumber)+G.get(medianNumber-1))/2,
                (B.get(medianNumber)+B.get(medianNumber-1))/2);
    }
    }

