Feature: Home Page Test

	Background:
		Given User is on Landing Page "https://www.lenskart.com/"
		And User sign in with valid username "automationpractice20@gmail.com" and password "Automationpractice20@gmail.com"

  Scenario: Login panel item count
    When User gets the count of login panel items
    Then Login panel item count should be "5"   
    
  
