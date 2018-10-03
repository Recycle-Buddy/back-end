package com.recycle.buddy.service;

import com.google.cloud.automl.v1beta1.PredictResponse;
import com.google.common.io.Resources;

import com.google.protobuf.ByteString;

import org.junit.Test;
import sun.misc.BASE64Encoder;
import sun.misc.Resource;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class ImageRecognitionServiceTest {
    private static final String GCP_PROJECT_ID = "recycle-buddy";
    private static final String GCP_LOCATION = "us-central1";
    private static final String AUTOML_MODEL_ID = "ICN3866551369314271958";
    private static final String IMAGE = "testImage.jpg";

    private static final String RESPONSE = "rightResponse.txt";

    @Test
    public void predictResponseTest() throws Exception{
        byte[] encoded = Resources.toByteArray(Resources.getResource(RESPONSE));
        Charset charset = Charset.defaultCharset();
        String rightResponse = new String(encoded, charset);

        BASE64Encoder encoder = new BASE64Encoder();
        String imageBase64 = encoder.encode(Resources.toByteArray(Resources.getResource(IMAGE)));

       // PredictResponse response = ImageRecognitionService.predictResponse(GCP_PROJECT_ID,GCP_LOCATION,AUTOML_MODEL_ID,imageBase64);
       // assertEquals(rightResponse,response.toString());
    }
}
