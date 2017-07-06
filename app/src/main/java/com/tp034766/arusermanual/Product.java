package com.tp034766.arusermanual;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 5/25/2017.
 */
@SuppressWarnings("serial")
public class Product implements Serializable {
    String name;
    String productImgUrl;
    String brandName;
    String modelCode;
    String onlineTutorialLink;
    String textBasedUserManual;
    String videoTutorialLink;
    List<AugmentedRealityInstruction> instructions = new ArrayList<>();

    public Product(String name, String productImgUrl, String brandName, String modelCode, String onlineTutorialLink
            , String textBasedUserManual, String videoTutorialLink, List<AugmentedRealityInstruction> instructions) {
        this.name = name;
        this.productImgUrl = productImgUrl;
        this.brandName = brandName;
        this.modelCode = modelCode;
        this.onlineTutorialLink = onlineTutorialLink;
        this.textBasedUserManual = textBasedUserManual;
        this.videoTutorialLink = videoTutorialLink;
        this.instructions = instructions;
    }
}
