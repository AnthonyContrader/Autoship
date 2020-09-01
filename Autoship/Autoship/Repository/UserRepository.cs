using Autoship.DBContexts;
using Autoship.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Autoship.Repository
{
    public class UserRepository : BaseRepository<User>
    {
        public UserRepository(AutoshipContext context) : base(context) {}

        public User GetByLogin(string username, string password)
        {
            var users = GetAll().Where(a => a.Username == username && a.Password == password);
            if (users.Count() > 1)
                throw new Exception("One one user with login and password");

            return users.FirstOrDefault();
        }
    }
}
