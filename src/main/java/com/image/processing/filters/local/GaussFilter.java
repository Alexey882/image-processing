package com.image.processing.filters.local;
import javafx.scene.image.*;

public class GaussFilter extends MatrixFilter {

    public GaussFilter() {
        createGaussKernel(3, 2);
    }

    public void createGaussKernel(int radius, int sigma) {
        int size = radius * 2 + 1;
        kernel = new double[size][size];
        double norm = 0;
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                kernel[i + radius][j + radius] = Math.exp(-(i * i + j * j) / (sigma * sigma));
                norm += kernel[i + radius][j + radius];
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                kernel[i][j] /= norm;
            }
        }
    }
}
