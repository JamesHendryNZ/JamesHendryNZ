# Test script for Tweeting and ReTweeting
# log on to jmh1Test1
# tweet "This is jmhTest1" from excel file
# reload page to look for "This is jmhTest1"
# logg off
# logg back on as jmh2Test and go to jmhTest1
# 


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

def setUpTestData():
    ##This function MUST BE CALLED FIRST##
    
    #loading the excel file with pandas
    ecxelFilePath = 'TwitterTestData.xlsx'
    xlFile = pd.read_excel( ecxelFilePath )
    expectedResults = xlFile.get_values()


    #for i in xlFile:
        #expectedResults.append( xlFile['Expected Results'][i])
    #expectedResults = xlFile['Expected Headings']

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

def logIn2Twitter( userName, password , accountName ):
    
    driver = webdriver.Firefox()
    driver.get("https://twitter.com/")
    assert "Twitter. It's what's happening." in driver.title
    #driver.get_screenshot_as_file( 'zeldaDungeonHomePage.png' )
    #driver.save_screenshot('zeldaDungeonHomePage.png');

    elem = driver.find_element_by_xpath('//*[@id="doc"]/div/div[1]/div[1]/div[2]/div[2]/div/a[2]')
    elem.click()

    assert "Login on Twitter" in driver.title

    elem = driver.find_element_by_xpath('//*[@id="page-container"]/div/div[1]/form/fieldset/div[1]/input')
    elem.send_keys( userName )
    elem = driver.find_element_by_xpath('//*[@id="page-container"]/div/div[1]/form/fieldset/div[2]/input')
    elem.send_keys( password )

    elem = driver.find_element_by_xpath('//*[@id="page-container"]/div/div[1]/form/div[2]/button')
    elem.click()

    accountElem = dirver.find_element_by_xpath('//*[@id="page-container"]/div[1]/div[1]/div/div[2]/div/a')
    assert userName in accountElem
    testReport = userName + " has succesufly logged into twitter\n"
    driver.get_screenshot_as_file( userName +'LoggedOn.png' )

#-------------------------------------------------------------------------------------------------------------------

def logOutOfTwitter( userName ):
    
    elem = driver.find_element_by_xpath('//*[@id="user-dropdown-toggle"]')
    elem.click()

    elem = driver.find_element_by_xpath('//*[@id="signout-button"]/button')
    elem.click()
    driver.implicitly_wait( 8 )
    assert "Login on Twitter" in driver.title
    testReport = userName + " has succesufly logged into twitter\n"
    driver.get_screenshot_as_file( 'LoggedOff.png' )

#--------------------------------------------------------------------------------------------------------------------

#This test will tweet what ever is in the parameter then reload the page and then test to see if it had been
#    tweeted
def tweetTest( tweetContent ):
    elem = driver.find_element_by_xpath('//*[@id="global-new-tweet-button"]/span')
    elem.click()
    elem = driver.find_element_by_xpath('//*[@id="Tweetstorm-tweet-box-0"]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div')
    elem.send_keys( tweetContent )
    elem = driver.find_element_by_xpath('//*[@id="Tweetstorm-tweet-box-0"]/div[2]/div[2]/div[2]/span/button[2]/span')

    driver.get("https://twitter.com/?lang=en")
    driver.implicitly_wait( 5 )
    
    elem.find_element_by_class_name( 'TweetTextSize  js-tweet-text tweet-text')
    testResults.append(elem.text)
    #1052389025267556354
    #1054567847937040384
    #TTTFFFFFFFFFFFFFFFT

#------------------------------------------------------------------------------------------------------
def testExistantance(textToTest):
    #test that this is the right class

    driver = webdriver.Firefox()
    driver.get("https://twitter.com/jmh_test1")
    
    #driver.get("https://twitter.com/?lang=en")
    driver.implicitly_wait( 5 )
    
    tweetElems = []
    tweetElems.append( driver.find_elements_by_class_name( 'TweetTextSize TweetTextSize--normal js-tweet-text tweet-text') )
    #if(testToTest == elem.text):
    #    print('Found tweet text in tweet text class\n')
   # else: 
    #    print('Could not find tweet text class or tweet text\n')
    if( tweetElems[0] is not None):
        print('Found da tweet class')
    else:
        print("it's not there")


def testReTweet():
    #will retweet a specfic tweet then checks for it's exisitance
    elem = driver.find_element_by_xpath('//*[@id="stream-item-tweet-1054567847937040384"]/div[1]/div[2]/div[3]/div[2]/div[2]/button[1]/div/span[1]')
    elem.click()
    testText = elem.text();
    elem = driver.find_element_by_xpath('//*[@id="retweet-tweet-dialog-dialog"]/div[2]/form/div[2]/div[3]/button/span[1]')
    elem.click()
    
    testExistance( testText )

#---------------------------------------------------------------------------------------------------------------------

## functions/methods conserning the testing of the block function

def testBlock():
    driver.get("https://twitter.com/?lang=en")
    driver.implicitly_wait( 5 )

    elem = driver.find_element_by_xpath('//*[@id="menu-0"]/span')
    elem.click()

    elem = driver.find_element_by_xpath('//*[@id="page-container"]/div[1]/div/div[2]/div/div/div[2]/div/div/ul/li[2]/div/div/div/div/ul/li[7]/button')
    elem.click()

    elem = driver.find_element_by_xpath('//*[@id="block-dialog-dialog"]/div[2]/div[3]/button[1]')
    elem.click()

    elem = driver.find_element_by_xpath('//*[@id="page-container"]/div[1]/div/div[2]/div/div/div[2]/div/div/ul/li[2]/div/div/span[2]/button[5]/span[1]')
    testResults.append( elem.text )

def testBlockEffects():

    driver.get('https://twitter.com/jmh_Test1?lang=en')
    elem = driver.find_element_by_xpath('//*[@id="page-container"]/div[2]/div/div/div[2]/div/div[1]/div/div/p')
    if( elem.text == expectedResults[3][2]):
        testReport = testReport + "The Block Feature works"
    else:
        testReport = testReport + "The Block Feature Does Not work/n Instead it shows ::" + elem.text

def testUnBlockEffects():
    driver.get('https://twitter.com/')
    elem = driver.find_element_by_xpath('//*[@id="user-dropdown-toggle"]')
    elem.click()
    elem = driver.find_element_by_xpath('//*[@id="user-dropdown"]/div/ul/li[10]/a')
    elem.click()
    elem = driver.find_element_by_xpath('//*[@id="settings_nav"]/ul/li[11]/a')
    elem.click()
    elem = driver.find_element_by_xpath('//*[@id="stream-item-user-1054550192509247488"]/div/div[1]/div/div/div/div/span[2]/button[5]/span[1]')

#def confirmUnBlockEffects():
    
######
    
    ###//*[@id="stream-item-user-1054550192509247488"]/div/div[1]/div/div/div/div/span[2]/button[5]/span[1]
    

def testSearch( searchTerm ):
    elem = driver.find_element_by_xpath('//*[@id="search-query"]')
    elem.send_keys( searchTerm )
    elem.send_keys( Keys.RETURN )

    #//*[@id="stream-item-tweet-1052378663973339136"]/div/div[2]/div[1]/a/img
    #////*[@id="stream-item-tweet-1052389025267556354"]/div/div[2]/div[1]/a/img
    #//*[@id="stream-item-tweet-1052378182907658240"]/div/div[2]/div[1]/a/img

    #//*[@id="stream-item-tweet-1052389025267556354"]/div/div[2]/div[1]/a/span[1]/strong
    #//*[@id="stream-item-tweet-1034007929576247296"]/div/div[2]/div[1]/a/span[1]/strong
    #                           TFFFFFFFFFFFFFFFFFF
    #//*[@id="account-form"]/div[7]/div/label

#sets up the test account settings
def get2SettingsPage():
    elem = driver.find_element_by_xpath('//*[@id="user-dropdown-toggle"]')
    elem.click()
    elem = driver.find_element_by_xpath('//*[@id="user-dropdown"]/div/ul/li[10]/a')
    elem.click()
    
def setUpTestAccountSettings( accountOptionMin , accountOptionMax , selectionXpath , settingTitle):

    elem = driver.find_element_by_xpath( selectionXpath )
    elem.click()

    testReport = "--------------------------------------------------------------------------/n"

    driver.get_screenshot_as_file( 'TwitterAccountSettingsBefore'+str(selectionXpath)+'.png' )
    try:
        for i in range( accountOptionMin , accountOptionMax ):
            driver.get_screenshot_as_file( 'TwitterAccountSettingsBefore'+'1'+'.png' )
            elem = driver.find_element_by_xpath( '//*[@id="' + expectedResults[5][i] +'"]' )
            elem.click()
    except Exception as e:
        traceback.print_exc()
    finally:
        print(expectedResults[7][selectionTitle] + "Has been selected")
    #        
    driver.get_screenshot_as_file( 'TwitterAccountSettingsAfter'+str(selectionXpath)+'.png' )
    ##Account options
    #settingElem = driver.find_element_by_xpath('//*[@id="autoplay_disabled"]')
    #settingElem.click()
    #settingElem = driver.find_element_by_xpath('//*[@id="personalize_timeline"]')
    #settingElem.click()

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
def testTestAccountSettings( accountOptionMin , accountOptionMax , optionRange , selectionTitle):
    elem = driver.find_element_by_xpath( selectionXpath )
    elem.click()
    try:
        for i in range( accountOptionMin , accountOptionMax ):
            elem = driver.find_element_by_xpath( '//*[@id="' + expectedResults[5][i] +'"]' )

            isTrueRight = false;
            if( expectedResults[8][i] == '1'):
                isTrueRight = true
            
            if( (elem.is_selected == true & isTrueRight ) | ( elem.is_selected == false & isTrueRight == false )):
                testReport = "The selection of " + expectedResults[9][selectionTitle] + " is correct\n"
    except Exception as e:
        traceback.print_exc()
    finally:
        print(expectedResults[7][selectionTitle] + "Has passed the test")    

############################################################################################


setUpTestData()
testExistantance("lolol")


        

    

    
    
    

