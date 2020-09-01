using Autoship.DBContexts;
using Autoship.DTO;
using Autoship.Models;
using Autoship.Repository;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Autoship.Controllers
{
    public class UserController : ControllerBase
    {
        private readonly AutoshipContext _context;

        public UserController(AutoshipContext context)
        {
            _context = context;
        }

        [Route("/api/v1/[controller]/getuserbylogin")]
        [HttpPost]
        public UserDTO GetUserByLogin([FromBody] dynamic loginData)
        {
            dynamic data = loginData;

            string username = data.Username;
            string password = data.Password;

            UserRepository userRepository = new UserRepository(_context);
            User user = userRepository.GetByLogin(username, password);

            UserDTO userDto = null;
            if (user != null)
            {
                userDto = UserDTO.ConvertFrom(user);
            }

            return userDto;
        }
    }
}
