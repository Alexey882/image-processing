package com.image.processing.filters.local;

import com.image.processing.filters.Filter;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class SharpnessFilter extends MatrixFilter {

    public SharpnessFilter() {
        createGaussKernel();
    }

    public void createGaussKernel() {
        kernel = new double[][]{{0,-1,0},
                                {-1,5,-1},
                                {0,-1,0}};
    }
}
