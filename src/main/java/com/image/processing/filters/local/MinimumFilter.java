package com.image.processing.filters.local;

import com.image.processing.utils.Pixel;
import com.image.processing.utils.Utility;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumFilter extends MatrixFilter{

    public MinimumFilter() {
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

        return Pixel.getColor(R.get(0),G.get(0),B.get(0));
    }
}

