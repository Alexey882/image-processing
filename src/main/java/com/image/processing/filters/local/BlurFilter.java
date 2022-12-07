package com.image.processing.filters.local;
import javafx.scene.image.*;

import java.util.Arrays;

public class BlurFilter extends MatrixFilter{

    public BlurFilter() {
        createBlurKernel(3);
    }
    public BlurFilter(int kernelSize) {
        createBlurKernel(kernelSize);
    }

    public void createBlurKernel(int size) {
        kernel = new double[size][size];
        double norm = size*size;
        for (int i = 0; i < size; i++) {
            for (int j = 0 ; j < size ; j++) {
                kernel[i][j] = 1.0/norm;
            }
        }
    }
}
