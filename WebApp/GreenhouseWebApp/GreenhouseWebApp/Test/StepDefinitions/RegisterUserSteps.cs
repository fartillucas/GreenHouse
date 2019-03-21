using System;
using TechTalk.SpecFlow;
using GreenhouseWebApp.Controllers;
using GreenhouseWebApp;

namespace GreenhouseWebApp
{
    [Binding]
    public class RegisterUserSteps
    {
        string username;
        string password;
        string email;
        string hashedPassword;

        [Given(@"acceptable user credidentials")]
        public void GivenAcceptableUserCredidentials()
        {
            username = "test";
            password = "test";
            hashedPassword = SecurePasswordHasher.Hash("test");
            email = "Test@test.com";
               
        }
        
        [When(@"register is clicked")]
        public void WhenRegisterIsClicked()
        {
            RegisterUserController registerUserController = new RegisterUserController();
            registerUserController.RegisterUser(username, password, password, email);
        }
        
        [Then(@"the user is saved in the database")]
        public void ThenTheUserIsSavedInTheDatabase()
        {
            //TODO Check for registered user in database
            ScenarioContext.Current.Pending();
            //TODO After user is registered remove again
        }
        
        [Then(@"user is redirected to login screen")]
        public void ThenUserIsRedirectedToLoginScreen()
        {
           
            ScenarioContext.Current.Pending();
        }
    }
}
