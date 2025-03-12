Feature: Login Functionality
  As a user
  I want to login to the application
  So that I can access my account

  Background:
    Given the app is launched

  Scenario: Successful login with valid credentials
    When I enter username "testuser"
    And I enter password "password123"
    And I tap on the login button
    Then I should be logged in successfully
    And I should see the home screen