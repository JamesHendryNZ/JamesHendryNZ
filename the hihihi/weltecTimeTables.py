#explict and implicit wait
#

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait


driver = webdriver.Firefox()
driver.get("http://timetable.weltec.ac.nz/2018")
elem = driver.find_element_by_id("LinkBtn_locations").click()
elem = driver.find_element_by_id("dlObject")

driver.getScreenShot_as_file( '/tmp/b107ThisWeekSelectioned' )
driver.save_screenShot('/tmp/b107TimeTable');

elem = driver.find_element_by_xpath('//*[@id="dlObject"]/option[47]')
elem.click()

elem = driver.find_element_by_xpath('//*[@id="lbWeeks"]/option[3]')
elem.click()

elem = driver.find_element_by_xpath('//*[@id="lbWeeks"]/option[1]')
elem.click()

driver.getScreenShot_as_file( '/tmp/b107ThisWeekSelectioned' )
driver.save_screenShot('/tmp/b107TimeTable');

elem = driver.find_element_by_id("bGetTimetable")
elem.click()

driver.implicity_wait( 5 )
driver.getScreenShot_as_file( '/tmp/b107TimeTable' )
driver.save_screenShot('/tmp/b107TimeTable');
