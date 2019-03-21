using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace GreenhouseWebApp.Models
{
    public class RegisterForm
    {
        [Required]
        public String Username { get; set; }

        [Required]
        public String Password { get; set; }

        [Required]
        public String ConfirmPassword { get; set; }

        [Required]
        public String Email  { get; set; }


    }
}