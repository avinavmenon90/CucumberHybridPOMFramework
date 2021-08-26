#Youtube related video: https://www.youtube.com/watch?v=NhiIAZRoc0g&list=PLFGoYjJG_fqo1gsSFl5WInw_fPkk_MQu7&index=2
Feature: Login page feature

Scenario: Login page title
Given User is on login page
When user gets the title of the page
Then page title should be "Login - My Store"

Scenario: Forgot Password Link
Given User is on login page
Then forgot password link should be displayed


Scenario: Login with correct credentials
Given User is on login page
When User enters username "avinavmenon@outlook.com"
And User enters password "test@123"
And User clicks on login button
Then user gets the title of the page
And page title should be "My account - My Store"