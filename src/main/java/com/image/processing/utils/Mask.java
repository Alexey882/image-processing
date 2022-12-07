package com.image.processing.utils;
public class Mask {
    private boolean[][] mask;
    public Mask(){
        generateRhombusMaskSize3();
    }

    public void setMask(boolean[][] mask) {
        this.mask = mask;
    }
    public int width(){return mask[0].length;}
    public int height(){
        return mask.length;
    }
    public boolean getElemIfMainElemInCenter(int i , int j){
        return mask[i+1][j+1];
    }
    public boolean getElemIfMainElemInBegin(int i , int j){
        return mask[i][j];
    }
    private void generateRectangleMaskSize3(){
        mask = new boolean[][]{
                {true, true, true},
                {true, true, true},
                {true, true, true}};
    }
    private void generateRhombusMaskSize3(){
        mask = new boolean[][]{
                {false, true, false},
                {true, false, true},
                {false, true, false}};
    }
    private void generateCrossMaskSize3(){
        mask = new boolean[][]{
                {false, true, false},
                {true, true, true},
                {false, true, false}};
    }
    private void generateAnyFormMask(){
        mask = new boolean[][]{
                {false, true, false},
                {true, true, true},
                {false, true, false}};
    }
}
