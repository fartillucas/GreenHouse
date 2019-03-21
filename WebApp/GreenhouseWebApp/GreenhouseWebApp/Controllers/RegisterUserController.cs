using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;


namespace GreenhouseWebApp.Controllers
{
    public class RegisterUserController : Controller
    {

        public ActionResult Index()
        {
            return View();
        }
        public ActionResult RegisterUser()
        {   
           
            return View();
        }
        [HttpPost]
       public ActionResult RegisterUser(string username,string password,string confirmPassword, string email ){
            if (password != confirmPassword)
            {
                ViewBag.Message = "Passwords must be equal";
                return View(); 
            }
           
             string hashedPassword = SecurePasswordHasher.Hash(password);

            //TODO
            //SavedToDatabase(username,hashedPassword,email);
            ViewBag.HashedPassword = hashedPassword;
            return View();
            
        }
        
    }
}