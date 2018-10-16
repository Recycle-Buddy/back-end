package com.recycle.buddy.service;


import com.google.api.client.util.Base64;
import com.google.cloud.automl.v1beta1.*;
import com.google.protobuf.ByteString;
import com.recycle.buddy.catalog.WasteClassifier;
import com.recycle.buddy.model.input.RecognizeRequest;
import com.recycle.buddy.model.output.RecognitionResult;
import com.recycle.buddy.model.output.RecognizeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ImageRecognitionService {

    private static final String GOOGLE_CREDENTIOLS = "/chloe-recycle-buddy-77e46e35c520.json";

    private static final Logger LOG = LoggerFactory.getLogger(ImageRecognitionService.class);

    private final WasteClassifier wasteClassifier;

    @Autowired
    public ImageRecognitionService(WasteClassifier wasteClassifier) {
        this.wasteClassifier = wasteClassifier;
    }

    private static final String GCP_PROJECT_ID = "chloe-recycle-buddy";
    private static final String GCP_LOCATION = "us-central1";
    private static final String AUTOML_MODEL_ID = "ICN2373703841484853385";

    public RecognizeResponse recognize(final RecognizeRequest recognizeRequest) {
        LOG.info(recognizeRequest.toString());
        PredictResponse response = predictResponse(GCP_PROJECT_ID,GCP_LOCATION,AUTOML_MODEL_ID, recognizeRequest.getImage().getImageBytes());
        List<RecognitionResult> resultList = wasteClassifier.classify(response);

        return createResponse(resultList);
    }
  
    public PredictResponse predictResponse(String projectId, String computeRegion, String modelId, String imageBase64) {

        try {

            PredictionServiceClient predictionServiceClient = PredictionServiceClient.create();
            ModelName name = ModelName.of(projectId, computeRegion, modelId);

            ByteString content = ByteString.copyFrom(Base64.decodeBase64(imageBase64));
            Image image = Image.newBuilder().setImageBytes(content).build();

            ExamplePayload payload = ExamplePayload.newBuilder().setImage(image).build();
            Map<String, String> params = new HashMap<>();

            PredictResponse response = predictionServiceClient.predict(name, payload, params);

            return response;
        } catch (Exception e) {
            LOG.error("AutoML model invocation error ",e );
            throw new RuntimeException(e);
        }
    }

    private RecognizeResponse createResponse(List<RecognitionResult> resultList){
        RecognizeResponse recognizeResponse = new RecognizeResponse();
        recognizeResponse.setResult(resultList);
        return recognizeResponse;
    }
}
