package com.recycle.buddy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ImageRecognitionService {

    private static final Logger LOG = LoggerFactory.getLogger(ImageRecognitionService.class);

    public String recognize(final String image) {
        // TODO: call to machine learning service
        LOG.info(image);
        return "Hello World!";
    }
}
