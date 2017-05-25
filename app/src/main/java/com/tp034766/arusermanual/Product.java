package com.tp034766.arusermanual;

import java.io.Serializable;

/**
 * Created by User on 5/25/2017.
 */
@SuppressWarnings("serial")
public class Product implements Serializable {
    String name;
    String haarClassifierName;
    int haarClassifier;

    public Product(String name, String haarClassifierName, int haarClassifier) {
        this.name = name;
        this.haarClassifierName = haarClassifierName;
        this.haarClassifier = haarClassifier;
    }
}
