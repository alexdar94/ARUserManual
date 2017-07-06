package com.tp034766.arusermanual;

import java.io.Serializable;

/**
 * Created by User on 7/6/2017.
 */

public class AugmentedRealityInstruction implements Serializable {
    int stepNo;
    String textInstruction;
    String haarClassifierName;
    int x;
    int y;
    double scaleFactor;

    public AugmentedRealityInstruction(int stepNo, String textInstruction, String haarClassifierName, double scaleFactor, int x, int y) {
        this.stepNo = stepNo;
        this.textInstruction = textInstruction;
        this.haarClassifierName = haarClassifierName;
        this.x = x;
        this.y = y;
        this.scaleFactor = scaleFactor;
    }
}
