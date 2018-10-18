package com.recycle.buddy.model.output;

public class RecognitionResult {
    private String label;
    private double probability;

    public String getLabel() {
        return label;
    }

    public double getProbability() {
        return probability;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
