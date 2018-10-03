package com.recycle.buddy.catalog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.automl.v1beta1.PredictResponse;
import com.google.common.io.Resources;
import com.recycle.buddy.model.output.RecognitionResult;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WasteClassifier {

    private static final Logger LOG = LoggerFactory.getLogger(WasteClassifier.class);
    private static final String TREE = "recycleTree.json";

    private final JsonNode root;

    public WasteClassifier() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            root = mapper.readTree(Resources.getResource(TREE));
            printTree();
        } catch (IOException e) {
            LOG.error("Reading tree file error",e );
            throw new RuntimeException(e);
        }
    }

    public JsonNode getRoot() {
        return root;
    }

    public List<RecognitionResult> classify(PredictResponse response) {
        return null;
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