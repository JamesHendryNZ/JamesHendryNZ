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

#loading the excel file with 
ecxelFilePath = 'testData.xlsx'
xlFile = pd.read_excel( excelFilePath , sheetname = 'sheet1' )
expectedResults = xlFile['Expected Headings']


driver = webdriver.Firefox()
driver.get("https://zeldadungeon.net")
driver.getScreenShot_as_file( '/tmp/zeldaDungeonHomePage' )
driver.save_screenShot('/tmp/zeldaDungeonHomePage');

hoverElem = driver.find_element_by_xpath('//*[@id="sitenav-games"]/nav/ul/li[3]/a')
hover = ActionChains(driver).move_to_element(hoverElem)
hover.perform()
elem = WebDriverWait( driver,2 ).until( EC.presence_of_element_located((By.PARTIAL_LINK, "Tower of Hera")))

elem = driver.find_element_by_xpath('//*[@id="sitenav-games"]/nav/ul/li[3]/div/div[2]/ul/li[5]/a')
elem.click()

elem = driver.find_element_by_visibilty
#this is where expected results from a spreed sheet will be
#loaded and compared







