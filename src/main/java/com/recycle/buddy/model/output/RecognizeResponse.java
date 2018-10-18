package com.recycle.buddy.model.output;

import java.util.List;

public class RecognizeResponse {
    private Error error;
    private List<RecognitionResult> result;

    public Error getError() {
        return error;
    }

    public List<RecognitionResult> getResult() {
        return result;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public void setResult(List<RecognitionResult> result) {
        this.result = result;
    }
}
