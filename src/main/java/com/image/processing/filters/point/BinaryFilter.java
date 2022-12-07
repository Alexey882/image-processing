package com.image.processing.filters.point;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Pixel;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class BinaryFilter implements Filter {
    @Override
    public Color calculatePixelColor(PixelReader pixelReader,int x , int y) {
        int color = pixelReader.getArgb(x , y);
        Pixel pixel = new Pixel(color);
        int average = (pixel.getR()+pixel.getG()+pixel.getB())/3;
        int border = 128;
        if(average<border){
            return Color.rgb(0,0,0);
        }
        return Color.rgb(255,255,255);

    }
}
