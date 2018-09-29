package com.recycle.buddy.service;

import com.google.cloud.automl.v1beta1.Image;
import com.google.cloud.automl.v1beta1.PredictResponse;
import com.google.protobuf.ByteString;
import org.junit.Assert;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;



public class ImageRecognitionServiceTest {
    private static final String GCP_PROJECT_ID = "recycle-buddy";
    private static final String GCP_LOCATION = "us-central1";
    private static final String AUTOML_MODEL_ID = "ICN3866551369314271958";
    private static final String IMAGE_PATH = "E:\\Masha\\RecycleBuddy\\images\\testImage.jpg";
    private static final String RESPONSE_PATH = "E:\\Masha\\RecycleBuddy\\jsons\\rightResponse.json";

    @Test
    public void predictResponseTest(){

        ByteString content = null;
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(RESPONSE_PATH));
            Charset charset = Charset.defaultCharset();
            String rightResponse = new String(encoded, charset);

            BASE64Encoder encoder = new BASE64Encoder();
            String imageBase64 = encoder.encode(Files.readAllBytes(Paths.get(IMAGE_PATH)));
            PredictResponse response = ImageRecognitionService.predictResponse(GCP_PROJECT_ID,GCP_LOCATION,AUTOML_MODEL_ID,imageBase64);

            Assert.assertEquals(rightResponse,response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
