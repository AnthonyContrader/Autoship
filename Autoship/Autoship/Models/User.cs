using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace Autoship.Models
{
    public enum Usertype
    {
        SUPERUTENTE,
        AMMINISTRATORE,
        CORRIERE,
        UTENTE
    }

    public class User : BaseModel
    {
        public string Username { get; set; }

        public string Password { get; set; }

        [EnumDataType(typeof(Usertype))]
        public Usertype Usertype { get; set; }
    }
}
