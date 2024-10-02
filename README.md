#Entrata Selenium Tests  d y D

This Repository contains Selenium WebDriver tests in Java using ""TestNG"" and ""Maven"" to validate the functionality of [Entrata.com]

## --- Table of Content ---
# Prerequisites
# Project-Setup
# Test Cases
# Running The Test ases

## Prerequisites
- **Java development Kit (JDK) or higher.
- **Maven
- **IDE : Eclipse, VSCode
- **WebDriver -- Project uses WebDriverManager for automatic Driver management
- **TestNG -- Dependancy
- **WebDriverManager -- Dependancy

## Project-Setup
* clone the repository
    git clone 
    cd entrata-selenium-test
* Install Dependancies -- Insure maven is installed then run
--- mvn clean install

* To Configure WebDriver no any manual setup require . As project uses WebDriverManager.

## Test Cases
1. TestNavigation.java
    TC1: Validate navigation to the homepage
            Validation: Entrata HomePage title (Assertion)
            Function: url navigation

    TC2: Validate navigation to the Studentpage
            Validation: Entrata Studentpage url (Assertion)
            Functions : Mouse hovring using Action class

    TC3: Valiate navigation to the Summitpage
            Validation: Entrata SummitPage url & Logo (Assertion)
            Functions/feature : Window Handling

    TC4: Validate navigation to the Accounting Page
            Validation: Entrata AccountingPage title (Assertion)
            Functions/feature : JavaScriptExecutor

2. Intereaction.java
    TC5: Validate Watch Demo form with valid details 
            Validation: title, Message on form, Watch Demo Button on form(Assertion)
            Function: Select Class- DropDown Selection, JavaScriptExecutor 

    TC6: Validate Watch Demo form with Invalid email address
            Validation: Demo page url, Email error message (Assertion)
            Functions : JavaScriptExecutor. Input box filling 

    TC7: Validate bahaviour of Watch Demo form without selecting dropdown and submitting form
            Validation: dropdown error message (Assertion)
            Functions : JavaScriptExecutor. Input box filling, Select 

3. careerFeature.java
    TC8: Validate Career page by selecting all option from dropdown
            Validation: job/career page url, Team headers (Assertion)
            Function:  Window Handling, JavaScriptExecutor 

    TC9: Validate Job for Test Engineer Team showing all right details
            Validation: career page url, total job role count, Team & Job role heaers (Soft Assertion)
            Functions : JavaScriptExecutor, Window Handling

    TC10: Validate SDET role details and resume file uploadation
            Validation: submit form application title, status after file uploading (Assertion)
            Functions : JavaScriptExecutor. Window Handling

## Running The Test ases
-- Clone repo to local
        git clone 
        cd entrata-selenium-test

-- Install epenenices 
        mvn clean install

-- Execute the tests
        mvn test

--Note: Running Tezt cases is also possible through IDE:
        1. @using TestNG.xml
        2. @using Each Test class file