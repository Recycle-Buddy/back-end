package com.recycle.buddy.service;


import com.google.api.client.util.Base64;
import com.google.cloud.automl.v1beta1.*;

import com.google.protobuf.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class ImageRecognitionService {

    private static final Logger LOG = LoggerFactory.getLogger(ImageRecognitionService.class);

    /*model parametrs*/
    //Prashanth's model
    private static final String GCP_PROJECT_ID = "recycle-buddy";
    private static final String GCP_LOCATION = "us-central1";
    private static final String AUTOML_MODEL_ID = "ICN3866551369314271958"; //ICN5772469721644251948

    //Chloe's model
    //private static final String GCP_PROJECT = "canvas-hybrid-212616";
    //private static final String GCP_LOCATION = "us-central1";
    //private static final String AUTOML_MODEL_ID = "ICN1265489133416904487";

    public String recognize(final String imageBase64) {
        LOG.info(imageBase64);
        PredictResponse response = PredictResponse.getDefaultInstance();
        try {
            response = predictResponse(GCP_PROJECT_ID,GCP_LOCATION,AUTOML_MODEL_ID, imageBase64);
        } catch (Throwable t) {
            System.out.println(t);
        }
        return response.toString();
    }



    public static PredictResponse predictResponse(String projectId, String computeRegion, String modelId, String imageBase64) throws IOException {
        PredictionServiceClient predictionServiceClient = PredictionServiceClient.create();
        try {
            predictionServiceClient = PredictionServiceClient.create();
            ModelName name = ModelName.of(projectId, computeRegion, modelId);

            ByteString content = ByteString.copyFrom(Base64.decodeBase64(imageBase64));
            Image image = Image.newBuilder().setImageBytes(content).build();

            ExamplePayload payload = ExamplePayload.newBuilder().setImage(image).build();
            Map<String, String> params = new HashMap<>();

            PredictResponse response = predictionServiceClient.predict(name, payload, params);

            System.out.println(response.toString());
            return response;

        }catch (Throwable t){
          System.out.println(t);
        }
        return null;
    }
}
