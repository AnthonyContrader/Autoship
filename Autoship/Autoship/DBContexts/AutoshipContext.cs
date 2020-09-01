using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Autoship.Models;

namespace Autoship.DBContexts
{
    public class AutoshipContext : DbContext
    {
        public AutoshipContext(DbContextOptions<AutoshipContext> options) : base(options)
        {
        }

        public DbSet<User> Users { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<User>().HasData(
                new User
                {
                    Id = 1,
                    Username = "superuser",
                    Password = "superuser",
                    Usertype = Usertype.SUPERUTENTE,

                },
                new User
                {
                    Id = 2,
                    Username = "admin",
                    Password = "admin",
                    Usertype = Usertype.AMMINISTRATORE,

                },
                new User
                {
                    Id = 3,
                    Username = "corriere",
                    Password = "corriere",
                    Usertype = Usertype.CORRIERE,

                },
                new User
                {
                    Id = 4,
                    Username = "user",
                    Password = "user",
                    Usertype = Usertype.UTENTE,

                }
            );

        }
    }
}

