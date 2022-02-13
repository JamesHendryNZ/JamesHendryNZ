# Test script for Tweeting and ReTweeting
# log on to jmh1Test1
# tweet "This is jmhTest1" from excel file
# reload page to look for "This is jmhTest1"
# logg off
# logg back on as jmh2Test and go to jmhTest1
# 

import time
import datetime

import traceback

import pandas as pd
from pandas import ExcelWriter
from pandas import ExcelFile
from pandas import DataFrame

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains

##########################################################################################
expectedResults = []
testStrings = []
testResults = []
testReport = ""

#adds a dashed line as long as it's needed
def addDashLine( amountOfDashes , string2Add2):
    for i in range(0 , amountOfDashes ):
        string2Add2 = string2Add2 + "-"
    #
    string2Add2 = string2Add2 + "\n"
#--------------------------------------------------------
def setUpTestData():
    ##This function MUST BE CALLED FIRST##
    global testReport
    #loading the excel file with pandas
    ecxelFilePath = 'TwitterTestData.xlsx'
    xlFile = pd.read_excel( ecxelFilePath )
    expectedResults = xlFile.get_values()


    #for i in xlFile:
        #expectedResults.append( xlFile['Expected Results'][i])
    #expectedResults = xlFile['Expected Headings']

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

def logIn2Twitter( userName, password ):
    ##log on to twitter (do sparinly to avoid getting locked out of account)
    global testReport
    driver.get("https://twitter.com/")
    assert "Twitter. It's what's happening." in driver.title
    #driver.save_screenshot( 'zeldaDungeonHomePage.png' )
    #driver.save_screenshot('zeldaDungeonHomePage.png');
    driver.implicitly_wait( 5 )
    elem = driver.find_element_by_xpath('//*[@id="doc"]/div/div[1]/div[1]/div[2]/div[2]/div/a[2]')
    elem.click()

    #assert "Login on Twitter" in driver.title
    driver.implicitly_wait( 5 )
    elem = driver.find_element_by_xpath('/html/body/div[1]/div[2]/div/div/div[1]/form/fieldset/div[1]/input')
    elem.click()
    elem.send_keys( userName )
    driver.implicitly_wait( 5 )
    
    elem = driver.find_element_by_xpath('/html/body/div[1]/div[2]/div/div/div[1]/form/fieldset/div[2]/input')
    elem.click()#turns out this is all i had to do
    elem.send_keys( password )

#this is not the time for skipping commands
def clickTheLoginButton( userName ):
    global testReport
    driver.implicitly_wait( 10 )
    elem = driver.find_element_by_xpath('/html/body/div[1]/div[2]/div/div/div[1]/form/div[2]/button')
    elem.click()
  #  print("i've clicked the bloody login button too ealry")
    driver.implicitly_wait( 5 )
    Elem = driver.find_element_by_xpath('/html/body/div[2]/div[2]/div/div[1]/div[1]/div/div[2]/div/a')
    #assert userName in Elem.text
    testReport = userName + " has succesufly logged into twitter\n"
    driver.save_screenshot( userName +'LoggedOn.png' )
    addDashLine( 32 , testReport )

#-------------------------------------------------------------------------------------------------------------------

def logOutOfTwitter( userName ):
    global testReport
    #log out of twitter(do sparinly to avoid getting locked out of account)
    elem = driver.find_element_by_xpath('//*[@id="user-dropdown-toggle"]')
    elem.click()
    driver.implicitly_wait( 5 )
    elem = driver.find_element_by_xpath('/html/body/div[2]/div[1]/div[2]/div/div/div[3]/ul/li[1]/div/ul/li[13]/button')
    elem.click()
    driver.implicitly_wait( 8 )
    assert "Login on Twitter" in driver.title
    testReport = testReport + userName + " has succesufly logged into twitter\n"
    addDashLine( 32 , testReport )
    driver.save_screenshot( 'LoggedOff.png' )

#--------------------------------------------------------------------------------------------------------------------

#This test will tweet what ever is in the parameter then reload the page and then test to see if it had been
#    tweeted
def tweetTest( tweetContent ):
    global testReport
    testReport = testReport + 'Testing Tweet function, will twweet "' + tweetContent + '" and test for its existance \n\n'
    elem = driver.find_element_by_xpath('//*[@id="global-new-tweet-button"]')
    elem.click()
    driver.implicitly_wait( 5 )
    elem = driver.find_element_by_xpath('/html/body/div[34]/div[2]/div[2]/div[2]/div[3]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]')
    testReport = testReport + 'Will now enter in "' + tweetContent + '" and press the tweet box \n'
    driver.save_screenshot( tweetContent+'TweetBoxBeforeTweet.png' )
    elem.send_keys( tweetContent )
    driver.save_screenshot( tweetContent+'TweetBoxwithTweetIn.png' )
    elem = driver.find_element_by_xpath('/html/body/div[34]/div[2]/div[2]/div[2]/div[3]/div[2]/div[2]/div[2]/span/button[2]')
    
    elem.click()
    driver.implicitly_wait( 5 )
    driver.get("https://twitter.com/?lang=en")
    driver.implicitly_wait( 5 )
    testReport = testReport + 'Will now test for tweets existance\n'
    driver.save_screenshot( tweetContent+'TweetBoxwithTweetIn.png' )

    testExistantance( tweetContent )
    addDashLine( 32 , testReport )
    
    #1052389025267556354
    #1054567847937040384
    #TTTFFFFFFFFFFFFFFFT

#------------------------------------------------------------------------------------------------------
def testExistantance( textToTest , twitterUser , isRetweet ):
    #test that this is the right class
    global testReport
    #driver = webdriver.Firefox()
    driver.get("https://twitter.com/"+ twitterUser )
    
    #driver.get("https://twitter.com/?lang=en")
    driver.implicitly_wait( 5 )

    try:
        notFound = True
        tweetElems = []
        tweetElems.append( driver.find_elements_by_class_name( 'TweetTextSize TweetTextSize--normal js-tweet-text tweet-text') )
        for i in tweetElems:
            if( tweetElems[i] is None ):
                testReport = testReport + "Plain Text tweets are not present on the page\n"
            if( tweetElems[i].text is textToTest and isRetweet is False ):
                testReport = testReport + 'The created tweet "'+ textToText +'" is found confirming the creation of the tweet\n'
                notFound = False
                driver.save_screenshot( textToText+'TweetExistance.png' )
            elif( tweetElems[i].text is textToTest and isRetweet is True ):
                testReport = testReport + 'The retweet "'+ textToText +'" is found confirming the creation of the retweet\n'
                notFound = False
                driver.save_screenshot( textToText+'RetweetExistance.png' )
    except Exception as e:
        traceback.print_exc()
    finally:
        if ( notFound is True and isRetweet is False ):
            testReport = testReport + "The speficic tweet as not been found\n"
            driver.save_screenshot( 'TweetExistance.png' )
        elif( notFound is True and isRetweet is True ):
            testReport = testReport + "The speficic retweet as not been found\n"
            driver.save_screenshot( 'TweetExistance.png' )
        addDashLine( 32 , testReport )
        
    #if(testToTest == elem.text):
    #    print('Found tweet text in tweet text class\n')
   # else: 
    #    print('Could not find tweet text class or tweet text\n')
    #if( tweetElems[0] is not None):
     #   print('Found da tweet class')
   # else: //*[@id="stream-item-tweet-1051278423002251265"]/div[1]/div[2]/div[4]/div[2]/div[2]/button[1]/div/span[1]
    #    print("it's not there")
##
## OVERLOADED METHOD FOR TESTING TWEETS AFTER THEY HAVE BEEN MADE
def testExistantance( textToTest ):
    #test that this is the right class
    global testReport
    #driver = webdriver.Firefox()
    driver.get("https://twitter.com/")
    
    #driver.get("https://twitter.com/?lang=en")
    driver.implicitly_wait( 5 )

    notFound = True
    try:
        
        tweetElems = []
        tweetElems.append( driver.find_elements_by_class_name( 'TweetTextSize TweetTextSize--normal js-tweet-text tweet-text') )
        for i in range(len(tweetElems)):
            if( tweetElems[i] is None ):
                testReport = testReport + "Plain Text tweets are not present on the page\n"
            if( tweetElems[i].text is textToTest  ):
                testReport = testReport + 'The created tweet "'+ textToText +'" is found confirming the creation of the tweet\n'
                notFound = False
                driver.save_screenshot( textToText+'TweetExistance.png' )
    except Exception as e:
        traceback.print_exc()
    finally:
        if ( notFound is True ):
            testReport = testReport + "The speficic tweet as not been found\n"
            driver.save_screenshot( 'TweetExistance.png' )
        addDashLine( 32 , testReport )

##

def testReTweet( twitterUser , tweetText , startedUser ):
    #will retweet a specfic tweet then checks for it's exisitance
    testReport = testReport + "Now testing the retweet function\n\n"
    #global testReport
    driver.get("https://twitter.com/"+ twitterUser )

    elem = driver.find_element_by_xpath('//*[@id="search-query"]')
    elem.send_keys( "Sparta" )
    elem.send_keys( Keys.RETURN )
    
    driver.implicitly_wait( 5 )
    
    elem = driver.find_element_by_xpath('/html/body/div[2]/div[2]/div/div[2]/div/div/div[2]/div/div/div/div/div[2]/ol[1]/li[14]/div/div[2]/div[4]/div[2]/div[2]/button[1]')
    elem.click()
    testText = elem.text();
    elem = driver.find_element_by_xpath('/html/body/div[24]/div/div[2]/form/div[2]/div[3]/button')
    elem.click()
    
    
    testExistance( testText , startedUser , True)

#---------------------------------------------------------------------------------------------------------------------

## functions/methods conserning the testing of the block function
## mus be called from the user who is doing the blocking
def testBlock( twitterUser ):
    global testReport
    testReport = testReport + "Testing the block feture\n\n"
    driver.get("https://twitter.com/"+twitterUser+"?lang=en")
    driver.implicitly_wait( 5 )

    elem = driver.find_element_by_xpath('/html/body/div[2]/div[2]/div/div[1]/div/div[2]/div/div/div[2]/div/div/ul/li[3]/div/div/div/button')
    elem.click()
    driver.save_screenshot( 'menu.png' )
    driver.implicitly_wait( 5 )
    driver.save_screenshot( 'blockMenu.png' )
    elem = driver.find_element_by_xpath('/html/body/div[2]/div[2]/div/div[1]/div/div[2]/div/div/div[2]/div/div/ul/li[3]/div/div/div/div/ul/li[7]/button')
    elem.click()
    driver.implicitly_wait( 5 )
    driver.save_screenshot( 'blockbox.png' )
    elem = driver.find_element_by_xpath('/html/body/div[25]/div/div[2]/div[3]/button[1]')
    #testReport = testReport + elem.text
    driver.save_screenshot( 'Tweetblock.png' )
    elem.click()
    #testBlockEffects( twitterUser )

##mustbeDoneBytheoneBeingBloicked
def testBlockEffects( twitterUser ):
    global testReport
    try:
        driver.get('https://twitter.com/'+ twitterUser +'?lang=en')
        driver.implicitly_wait( 5 )
        elem = driver.find_element_by_xpath('/html/body/div[2]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/div/p')
        if( elem.text == expectedResults[3][2]):
            testReport = testReport + "The Block Feature works"
        else:
            testReport = testReport + "The Block Feature Does Not work/n Instead it shows ::" + elem.text
    except Exception as e:
        traceback.print_exc()
    finally:    
        addDashLine( 32 , testReport )

#ran out of time to fully implement
def testUnBlockEffects():
    global testReport
    driver.get('https://twitter.com/')
    testReport = testReport + "Reversing the Block Feature works"
    elem = driver.find_element_by_xpath('//*[@id="user-dropdown-toggle"]')
    elem.click()
    elem = driver.find_element_by_xpath('//*[@id="user-dropdown"]/div/ul/li[10]/a')
    elem.click()
    elem = driver.find_element_by_xpath('//*[@id="settings_nav"]/ul/li[11]/a')
    elem.click()
    elem = driver.find_element_by_xpath('//*[@id="stream-item-user-1054550192509247488"]/div/div[1]/div/div/div/div/span[2]/button[5]/span[1]')
    elem.click()
    

#def confirmUnBlockEffects():
    
######
    
    ###//*[@id="stream-item-user-1054550192509247488"]/div/div[1]/div/div/div/div/span[2]/button[5]/span[1]
    

def testSearch( searchTerm ):
    global testReport
    elem = driver.find_element_by_xpath('//*[@id="search-query"]')
    elem.send_keys( searchTerm )
    elem.send_keys( Keys.RETURN )
    driver.implicitly_wait( 5 )

    notFound = True
    try:
        
        seachElems = []
        seachElems.append( driver.find_elements_by_class_name( 'TweetTextSize TweetTextSize--normal js-tweet-text tweet-text') )
        for i in tweetElems:
            if( seachElems[i] is None ):
                testReport = testReport + "Plain Text tweets are not present on the page\n"
            if( seachElems[i].text.find(searchTerm) is not -1 ):
                testReport = testReport + 'The search Term "'+ search +'" is found confirming the function of the search term\n'
                notFound = False
                driver.save_screenshot( searchTerm+'SerachResults.png' )
    except Exception as e:
        traceback.print_exc()
    finally:
        if ( notFound is True ):
            testReport = testReport + "The speficic search term has not been found\n"
            driver.save_screenshot( search + 'NoSerachResults.png' )

        addDashLine( 32 , testReport )

    #//*[@id="stream-item-tweet-1052378663973339136"]/div/div[2]/div[1]/a/img
    #////*[@id="stream-item-tweet-1052389025267556354"]/div/div[2]/div[1]/a/img
    #//*[@id="stream-item-tweet-1052378182907658240"]/div/div[2]/div[1]/a/img

    #//*[@id="stream-item-tweet-1052389025267556354"]/div/div[2]/div[1]/a/span[1]/strong
    #//*[@id="stream-item-tweet-1034007929576247296"]/div/div[2]/div[1]/a/span[1]/strong
    #                           TFFFFFFFFFFFFFFFFFF
    #//*[@id="account-form"]/div[7]/div/label

#sets up the test account settings
def get2SettingsPage():
    global testReport
    testReport = testReport + "Testing account setting configuration\n"
    elem = driver.find_element_by_xpath('//*[@id="user-dropdown-toggle"]')
    elem.click()
    driver.implicitly_wait( 5 )
    elem = driver.find_element_by_xpath('/html/body/div[2]/div[1]/div[2]/div/div/div[3]/ul/li[1]/div/ul/li[10]/a')
    elem.click()
    driver.implicitly_wait( 5 )
    
    
def setUpTestAccountSettings( accountOptionMin , accountOptionMax , selectionXpath , settingTitle):
    global testReport
    elem = driver.find_element_by_xpath( selectionXpath )
    elem.click()
    driver.implicitly_wait( 5 )

    driver.save_screenshot( 'TwitterAccountSettingsBefore'+expectedResults[7][settingTitle]+'.png' )
    try:
        for i in range( accountOptionMin , accountOptionMax ):
            driver.save_screenshot( 'TwitterAccountSettingsBefore'+expectedResults[9][i]+'.png' )
            testReport = testReport + "Configuring setting :"+ expectedResults[9][i]+":\n"
            elem = driver.find_element_by_xpath( '//*[@id="' + expectedResults[5][i] +'"]' )
            elem.click()
    except Exception as e:
        traceback.print_exc()
    finally:
        print(expectedResults[7][selectionTitle] + "Has been selected")
    #           
        driver.save_screenshot( 'TwitterAccountSettingsAfter'+str(selectionXpath)+'.png' )
        elem = driver.find_element_by_xpath( '//*[@id="settings_save"]' )
        elem.click()
        testReport = testReport + ""+ expectedResults[7][settingTitle]+": has been set up\n"
        driver.implicitly_wait( 5 )
    ##Account options
    #settingElem = driver.find_element_by_xpath('//*[@id="autoplay_disabled"]')
    #settingElem.click()
    #settingElem = driver.find_element_by_xpath('//*[@id="personalize_timeline"]')
    #settingElem.click()

    #//*[@id="settings_save"]
    
    #settingElem = driver.find_element_by_xpath('//*[@id="settings_save"]')
    #settingElem.click()

    ##Privacy and saftety options
    #settingElem = driver.find_elemnt_by_xpath('//*[@id="settings_nav"]/ul/li[2]/a')
    #settingElem.click()

    #settingElem = driver.find_element_by_xpath('//*[@id="user_protected"]')
    #settingElem.click()
    #settingElem = driver.find_element_by_xpath('//*[@id="allow_media_tagging_following"]')
    #settingElem.click()
    
    #//*[@id="user_discoverable_by_email"]
    #//*[@id="user_mobile_discoverable"]
    #

#tests to make sure account settings are saved
def testTestAccountSettings( accountOptionMin , accountOptionMax , selectionXpath , selectionTitle):
    global testReport
    elem = driver.find_element_by_xpath( selectionXpath )
    elem.click()
    driver.implicitly_wait( 5 )
    testReport = testReport + "Testing confimation of settings" + expectedResults[7][selectionTitle] + "\n"
    try:
        for i in range( accountOptionMin , accountOptionMax ):
            elem = driver.find_element_by_xpath( '//*[@id="' + expectedResults[5][i] +'"]' )
            driver.save_screenshot( 'TwitterAccountSettingsChecked'+expectedResults[7][settingTitle]+'.png' )  
            isTrueRight = False;
            if( expectedResults[8][i] is '1'):
                isTrueRight = True
            
            if( (elem.is_selected is True and isTrueRight is True) | ( elem.is_selected is False and isTrueRight is False )):
                testReport = "The selection of " + expectedResults[9][selectionTitle] + " is correct\n"
    except Exception as e:
        traceback.print_exc()
    finally:
         testReport = testReport + expectedResults[7][selectionTitle] + "confimation has finished\n"
         driver.implicitly_wait( 5 )
         addDashLine( 32 , testReport )
     

############################################################################################
def writeTestReport():
    global testReport
    locationPath = "TestReport" + datetime.datetime.now() +".txt" 
    file = (locationPath , w )
    file.write(testReport)
    file.close()

###############################################################################################

setUpTestData()
driver = webdriver.Firefox()
##The order of tests
##minizing logging in and out to avoid being locked
logIn2Twitter( "jmh_test1", "YouShallNotPass" )
clickTheLoginButton( "jmh_test1" )
#logged in as jmh_test1
tweetTest("This is jmh_Test1")
testBlock("jmh_test2")
#going to test effects of settings
get2SettingsPage()
setUpTestAccountSettings( 1 , 3 , expectedResults[6][1] , 1)
setUpTestAccountSettings( 3 , 10 , expectedResults[6][2] , 2)
setUpTestAccountSettings( 10 , 20 , expectedResults[6][3] , 3)
setUpTestAccountSettings( 20 , 21 , expectedResults[6][4] , 4)
setUpTestAccountSettings( 20 , 21 , expectedResults[6][4] , 5)
setUpTestAccountSettings( 21 , 22 , expectedResults[6][5] , 6)
##confirming changes
testTestAccountSettings( 1 , 3 , expectedResults[6][1] , 1)
testTestAccountSettings( 3 , 10 , expectedResults[6][2] , 2)
testTestAccountSettings( 10 , 20 , expectedResults[6][3] , 3)
testTestAccountSettings( 20 , 21 , expectedResults[6][4] , 4)
testTestAccountSettings( 20 , 21 , expectedResults[6][4] , 5)
testTestAccountSettings( 21 , 22 , expectedResults[6][5] , 6)
##testseach
testSearch( "Xerxesian Chronicles" )
##will log out inorder to go to jmh_test2 to test block effects
logOutOfTwitter("jmh_test1")
driver.implicitly_wait( 20 )
logIn2Twitter( "jmh_test2", "FlyYouFools!!!" )
clickTheLoginButton( "jmh_test2" )
testBlockEffects( "jmh_test1" )
testReTweet( "jmh_test2" , 'I "was" the worst Sparta Remix guy? What is I now then? ', "jmh_test2" )
##final loggout and write report to file
logOutOfTwitter("jmh_test2")
writeTestReport()
#testExistantance("lolol")


        

    

    
    
    

