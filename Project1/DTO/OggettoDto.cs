using Project1.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Project1.DTO
{
    public class OggettoDto
    {
        public int? Id { get; set; }
        public string Nome { get; set; }
        public int Dimensione { get; set; }

        public Oggetto ConvertTo()
        {
            return new Oggetto
            {
                Id = this.Id,
                Nome = this.Nome,
                Dimensione = this.Dimensione
            };
        }

        public static OggettoDto ConvertFrom(Oggetto oggetto)
        {
            return new OggettoDto
            {
                Id = oggetto.Id,
                Nome = oggetto.Nome,
                Dimensione = oggetto.Dimensione
            };
        }
    }
}
