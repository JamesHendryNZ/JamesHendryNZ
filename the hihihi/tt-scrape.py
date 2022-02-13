import sys
import time

from selenium import webdriver
#from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support.ui import Select 



gStreams =  [\
    [ "BIT", "1B1"]] 

def make_trimester_selection( driver, trimester):
    pass
    # Select the trimser
    if trimester == 1:
        pass
    elif trimester == 2:
        pass
    elif trimester == 3:
        pass
    else:
        raise AssertionError( "Invalid tremester specified")

    # Select Monday-Friday.

    
    # Select the report format.


    # Click report button.




def make_selections_BIT( driver, semester, streamName):
        # Select the School of Information Technology
        try:
            programComboBox = Select( driver.find_element_by_id( "dlFilter"))
            programComboBox.select_by_visible_text( "Bach. Information Technology")
        except:
            raise AssertionError( "Unable to set 'Program' combobox value")
        time.sleep(1)

        # Iterate through the options in the streams list, and save the
        # of the option that contains the stream name we are looking for.
        try:
            program_full_name = ""

            streamList = Select( driver.find_element_by_id( "dlObject"))
            streamOptions = streamList.options
            for index in range(0, len(streamOptions)-1):
                option = streamOptions[index]
                if "(" + streamName + ")" in option.text:
                    program_full_name = option.text
                    break

            if program_full_name == "":
                raise AssertionError( "Program not found")

            streamList.select_by_visible_text( program_full_name)
            time.sleep(1)            
        except:
            raise AssertionError( "Unable to set 'Program' combobox value")

        make_trimester_selection( driver, semester)    




def load_web_page( driver, year, semester, program, streamName):
    try:
        # Load the timetable site.
        driver.get( "http://timetable.weltec.ac.nz/" + year + "/")
        # No further waits needed here. Current page does not seem to have any 'onLoad' processing.
        if "Timetable " + year not in driver.title:
            raise AssertionError( "Invalid driver.Title")


        # Select the "Programmes of Study" link.
        link = driver.find_element_by_link_text("Programmes of Study")
        link.click()

        # Wait for the page to rearrange.
        wait = WebDriverWait( driver, 10)
        wait.until( EC.presence_of_element_located( (By.ID, "dlFilter2")))
        
        # Select the School of Information Technology
        schoolComboBox = Select( driver.find_element_by_id( "dlFilter2"))
        schoolComboBox.select_by_visible_text( "School of Information Technology")
        time.sleep(1)

        if program == "BIT":
            make_selections_BIT( driver, semester, streamName)
        else:
            raise AssertionError( "Invalid 'program' was specified")
        time.sleep(1)

    except:
        raise

def main():


    driver = webdriver.Firefox()
    try:
        try:
            for stream in gStreams:
                load_web_page( driver, "2018", "2", stream[0], stream[1])
            print( "OK")
        except:
            print( "Failed: ")
    finally:
        pass
        #driver.close()


if __name__ == "__main__":
    main()