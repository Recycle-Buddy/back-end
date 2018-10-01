package com.recycle.buddy.model.input;
/*
 Class for keeping data from JSON file with base64 encoded image, that is received from Front-end.
*/

public class Image {

    private String imageBase64;

    public String getImageBytes() {
        return imageBase64;
    }

    public void setImageBytes(final String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
