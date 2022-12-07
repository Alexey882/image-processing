package com.image.processing.filters.morphology;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Mask;
import com.image.processing.utils.Pixel;
import com.image.processing.utils.Utility;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class ClosingFilter implements Filter {
    @Override
    public WritableImage processImage(Image image){
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        Filter filterDilation = new DilationFilter();
        Image dilationImage = filterDilation.processImage(image);
        Filter filterErosion = new ErosionFilter();
        return filterErosion.processImage(dilationImage);
    }
}
