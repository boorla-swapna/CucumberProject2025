Feature: Login feature
 Background:
       Given user launched login Page
Scenario: LoginErrorMessage_TC01
Then verify applicationPageOpened
When  user entered valid username
Then verify userenteredUsername
When  the password field is empty
Then verifyPasswordFieldisEmpty
When  user clicks login button 
Then password error message Is Displayed

      
  Scenario: SuccessfullLogin
  
  When user entered valid username,user entered valid password,user clicks the login button
  Then app home page should be displayed


    
    
    
   
  
