package com.recycle.buddy.controller;

import com.recycle.buddy.model.input.RecognizeRequest;
import com.recycle.buddy.model.output.RecognizeResponse;
import com.recycle.buddy.service.ImageRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images/v1")
public class RecycleBuddyController {

    private final ImageRecognitionService imageRecognitionService;

    @Autowired
    public RecycleBuddyController(final ImageRecognitionService imageRecognitionService) {
        this.imageRecognitionService = imageRecognitionService;
    }

    @RequestMapping(value = "/recognize", method = RequestMethod.POST)
    public RecognizeResponse recognize(@RequestBody final RecognizeRequest recognizeRequest) {
        return imageRecognitionService.recognize(recognizeRequest);
    }
}
