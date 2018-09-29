package com.recycle.buddy.controller;

import com.recycle.buddy.model.Image;
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
    public String recognize(@RequestBody final Image image) {
        return imageRecognitionService.recognize(image.getImageBytes());
    }
}
