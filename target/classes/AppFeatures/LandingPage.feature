Feature: Landing page feature

Scenario: Landing page title
Given User is on Landing Page "https://www.lenskart.com/"
Then Page title should contain "Lenskart.com"

Scenario: Landing page url
Given User is on Landing Page "https://www.lenskart.com/"
Then Page url should contain "Lenskart.com"

Scenario: Login with correct credentials
Given User is on Landing Page "https://www.lenskart.com/"
When User sign in with valid username and password
		| UserName | Password |
		| automationpractice20@gmail.com | Automationpractice20@gmail.com |
Then User is on the dashboard with the username "Vipul"
