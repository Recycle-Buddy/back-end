package com.recycle.buddy.model.input;

import com.recycle.buddy.model.input.Image;

public class RecognizeRequest {

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    private Image image;
}
