#explict and implicit wait
#

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait


driver = webdriver.Firefox()
driver.get("http://timetable.weltec.ac.nz/2018")
elem = driver.find_element_by_id("LinkBtn_locations").click()
elem = driver.find_element_by_id("dlObject")

driver.get_screenshot_as_file( '/tmp/b107ThisWeekSelectioned.png' )
driver.save_screenshot('/tmp/b107TimeTableSelect.png')

elem = driver.find_element_by_xpath('//*[@id="dlObject"]/option[47]')
elem.click()

elem = driver.find_element_by_xpath('//*[@id="lbWeeks"]/option[3]')
elem.click()

elem = driver.find_element_by_xpath('//*[@id="lbWeeks"]/option[1]')
elem.click()

driver.get_screenshot_as_file( '/tmp/b107ThisWeekSelectioned.png' )
driver.save_screenshot('/tmp/b107TimeTable.png');

elem = driver.find_element_by_id("bGetTimetable")
elem.click()

driver.implicitly_wait( 5 )
driver.get_screenshot_as_file( '/tmp/b107TimeTable.png' )
driver.save_screenshot('/tmp/b107TimeTable.png')

