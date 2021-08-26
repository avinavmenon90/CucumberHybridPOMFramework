#Demo of Excel utility (read values from an external Excel file
#https://www.youtube.com/watch?v=Oly5QGDgFW8&list=PLFGoYjJG_fqo1gsSFl5WInw_fPkk_MQu7&index=7
Feature: Contact Us Feature

Scenario Outline: Contact Us scenario with different set of data
Given user navigates to Contact Us page
When user fillers the form from given sheetname "<SheetName>" and rownumber <RowNumber>
And user clicks on send button
Then it shows a successful message "Your message has been successfully sent to our team."

Examples:
|SheetName|RowNumber|
|contactus|0|
|contactus|1|