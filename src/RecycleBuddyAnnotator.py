import io
import os
import pandas as pd
import numpy as np
import json

import cv2
from PIL import Image

from google.cloud import vision
from google.cloud.vision import types


class RecycleBuddyAnnotator:
    '''
    The class preprocesses an image when needed, recognize texts and objects contained.
    It reads in an image URL and returns a json file with texts, label of objects that have been recognized
    '''

    def __init__(self):

        self.client = vision.ImageAnnotatorClient()
        print('Google client successfully initiated')
        self.image = vision.types.Image()

    def preprocessingImage(self, imageURL):
        self.image.source.image_uri = imageURL
        pass


    def textExtraction(self, imageURL):
        self.image.source.image_uri = imageURL
        response = self.client.text_detection(image=self.image)
        text_info = response.text_annotations
        return_dict = {}
        print('The image has the following texts:\n')
        for text in text_info:
            return_dict[text.description] = []
            print('{}\n'.format(text.description))

        return_json = json.dumps(return_dict)
        return return_json


    def objectExtraction(self, imageURL):
        self.image.source.image_uri = imageURL
        response = self.client.label_detection(image=self.image)
        labels_info = response.label_annotations
        return_dict = {}
        print('The image contains the following objects with corresponding score:\n')
        for label in labels_info:
            return_dict[label.description] = round(label.score,4)
            print('Lable:{} | score:{}\n'.format(label.description,round(label.score,4)))
        return_json = json.dumps(return_dict)
        return return_json
