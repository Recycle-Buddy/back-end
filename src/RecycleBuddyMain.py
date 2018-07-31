import sys
import datetime

if __name__ == '__main__':
    '''
    This function takes an image URL as the input and generates two json files which are texts and objects been recognized by the RBA object
    '''

    # if len(sys.argv) != 2:
    #     raise ValueError('Please check your parameter')

    # imageURL = str(sys.argv[1])

    imageURL = 'https://s3.us-east-2.amazonaws.com/recycle.buddy/IMG_3862.jpg'
    model = RBA.RecycleBuddyAnnotator()
    starttime = datetime.datetime.now()
    texts_returned = model.textExtraction(imageURL)
    endtime = datetime.datetime.now()
    interval = endtime-starttime
    print('Text recognition takes {} seconds'.format(interval.seconds))
    starttime = datetime.datetime.now()
    labels_returned = model.objectExtraction(imageURL)
    endtime = datetime.datetime.now()
    interval = endtime - starttime
    print('Object recognition takes {} seconds'.format(interval.seconds))






