#select drop down boxes from
#https://zeldadungeon.net
#has images
#has a scearch
#has predictable resluts
#test case select a link to the past (wait for)
#heras tower then click to open
#then look for the presense of a screenshot
#all assume data is to be stored in a spred sheet.
#look for menu and dropdown box and screenshot visablity

# //*[@id="sitenav-games"]/nav/ul/li[3]/a
# //*[@id="sitenav-games"]/nav/ul/li[3]/div/div[2]/ul/li[5]/a

import pandas as pd
from pandas import ExcelWriter
from pandas import ExcelFile

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains

expectedResults = []

loading the excel file with 
ecxelFilePath = 'testData.xlsx'
xlFile = pd.read_excel( excelFilePath , sheetname = 'sheet1' )
for i in xlFile:
    expectedResults.append( xlFile['Expected Results'][i])

#expectedResults = xlFile['Expected Headings']


driver = webdriver.Firefox()
driver.get("https://zeldadungeon.net")
driver.get_screenshot_as_file( '/tmp/zeldaDungeonHomePage.png' )
driver.save_screenshot('/tmp/zeldaDungeonHomePage.png');

try:
    hoverElem = WebDriverWait(driver , 10).until(EC.presence_of_element_located((By.XPATH , '//*[@id="sitenav-games"]/nav/ul/li[3]/a')))
    #hoverElem = driver.find_element_by_xpath('//*[@id="sitenav-games"]/nav/ul/li[3]/a')    
    hover = ActionChains(driver).move_to_element(hoverElem)
    hover.perform()
    elem = WebDriverWait(driver , 10).until( EC.presence_of_element_located((By.XPATH, "/html/body/div[4]/div[2]/nav/ul/li[3]/div/div[2]/ul/li[5]/a")))
    elem = driver.find_element_by_xpath('//*[@id="sitenav-games"]/nav/ul/li[3]/div/div[2]/ul/li[5]/a')
    elem.click()
finally:
    print('yo') #tempary will be change later
#elem = driver.find_element_by_visibilty
#this is where expected results from a spreed sheet will be
#compared to those in the spred sheet
for i in expectedResults:
    
    assert 








