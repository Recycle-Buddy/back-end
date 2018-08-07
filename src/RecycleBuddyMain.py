import pickle
import RecycleBuddyAnnotator as RBA
import sys
import datetime

if __name__ == '__main__':
    '''
    This function takes an image URL as the input and generates two json files which are texts and objects been recognized by the RBA object
    '''

    # if len(sys.argv) != 2:
    #     raise ValueError('Please check your parameter')

    # imageURL = str(sys.argv[1])
    image_URL = 'https://s3.us-east-2.amazonaws.com/recycle.buddy/IMG_3862.jpg'
    model = RBA.RecycleBuddyAnnotator()
    starttime = datetime.datetime.now()
    texts_returned = model.text_extraction(image_URL)
    endtime = datetime.datetime.now()
    interval = endtime-starttime
    print('Text recognition takes {} seconds'.format(interval.seconds))
    starttime = datetime.datetime.now()
    labels_returned = model.object_extraction(image_URL)
    endtime = datetime.datetime.now()
    interval = endtime - starttime
    print('Object recognition takes {} seconds'.format(interval.seconds))

    # examples for using encoder and decoder in object detection
    image_base64_encoded = model.base64_encoder(image_URL)
    image_base64_decoded = model.base64_decoder(image_base64_encoded)
    labels_returned_base64 = model.object_extraction_base64(image_base64_encoded)








