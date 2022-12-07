package com.image.processing.filters.morphology;

import com.image.processing.filters.Filter;
import javafx.scene.image.*;

public class OpeningFilter implements Filter {
    @Override
    public WritableImage processImage(Image image){
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        Filter filterErosion = new ErosionFilter();
        Image erosionImage = filterErosion.processImage(image);
        Filter filterDilation = new DilationFilter();
        return filterDilation.processImage(erosionImage);
    }
}
