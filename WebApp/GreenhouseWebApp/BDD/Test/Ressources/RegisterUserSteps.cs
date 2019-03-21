using System;
using TechTalk.SpecFlow;

namespace BDD
{
    [Binding]
    public class RegisterUserSteps
    {
        [Given(@"acceptable user credidentials")]
        public void GivenAcceptableUserCredidentials()
        {
            ScenarioContext.Current.Pending();
        }
        
        [When(@"register is clicked")]
        public void WhenRegisterIsClicked()
        {
            ScenarioContext.Current.Pending();
        }
        
        [Then(@"the user is saved in the database")]
        public void ThenTheUserIsSavedInTheDatabase()
        {
            ScenarioContext.Current.Pending();
        }
        
        [Then(@"user is redirected to login screen")]
        public void ThenUserIsRedirectedToLoginScreen()
        {
            ScenarioContext.Current.Pending();
        }
    }
}
