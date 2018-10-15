package com.recycle.buddy.catalog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.automl.v1beta1.AnnotationPayload;
import com.google.cloud.automl.v1beta1.PredictResponse;
import com.google.common.io.Resources;
import com.recycle.buddy.model.output.RecognitionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class WasteClassifier {

    private static final Logger LOG = LoggerFactory.getLogger(WasteClassifier.class);
    private static final String TREE = "recycleTree.json";

    private final JsonNode root;

    public WasteClassifier() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            root = mapper.readTree(Resources.getResource(TREE));
            //printTree();
        } catch (IOException e) {
            LOG.error("Reading tree file error",e );
            throw new RuntimeException(e);
        }
    }

    public JsonNode getRoot() {

        return root;
    }

    public List<RecognitionResult> classify(PredictResponse response) {
        List<RecognitionResult> results = new ArrayList<>();

        for (AnnotationPayload annotationPayload : response.getPayloadList()) {
            RecognitionResult result = new RecognitionResult();
            result.setLabel(matchLabel(annotationPayload.getDisplayName()));
            result.setProbability(annotationPayload.getClassification().getScore());
            results.add(result);
        }
        return results;
    }

    //for demo presentation
    private String matchLabel (String displayName){
        switch (displayName) {
            case "glass": return "GlassBottlesJars";
            case "paper": return "PaperTowels";
            case "metal": return "BeverageCans";
            case "plastic": return "PlasticBottles";
            case "trash": return "ChipBags";
            case "cardboard": return "CardboardBoxes";
        }
        return "no label matches";
    }


    private void printTree(){
        //JsonNode root = wasteClassifier.getRoot();

        LinkedList<JsonNode> q = new LinkedList<>();
        q.add(root.get("root"));
        int k = q.size();

        while (!q.isEmpty()){
            k = q.size();

            for(int i = 0; i < k; i++){
                JsonNode node = q.removeFirst();
                System.out.println(node.toString());
                q.addAll(node.findValues("children"));

                System.out.println("");

            }
        }
    }
}
