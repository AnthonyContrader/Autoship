using Autoship.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Autoship.DTO
{
    public class UserDTO
    {
        public int? Id { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public Usertype Usertype { get; set; }

        public User ConvertTo()
        {
            return new User
            {
                Id = this.Id,
                Username = this.Username,
                Password = this.Password,
                Usertype = this.Usertype

            };
        }

        public static UserDTO ConvertFrom(User user)
        {
            return new UserDTO
            {
                Id = user.Id,
                Username = user.Username,
                Password = user.Password,
                Usertype = user.Usertype
            };
        }
    }
}
