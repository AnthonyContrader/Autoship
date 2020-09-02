using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Project1.DTO;
using Project1.Infrastructure;
using Project1.Model;
using Project1.Repository;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;

namespace Project1.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class OggettoController : ControllerBase
    {
        private readonly Project1Context _context;

        public OggettoController(Project1Context context)
        {
            _context = context;
        }

        [Route("/api/v1/[controller]/create")]
        [HttpPost]
        public UserDto Create([FromBody] dynamic oggetto)
        {
            OggettoDTO oggettoDto = JsonConvert.DeserializeObject<OggettoDTO>(oggetto.ToString());

            Oggetto oggettoItem = oggettoDto.ConvertTo();
            OggettoRepository oggettoRepository = new OggettoRepository(_context);
            oggettoItem = oggettoRepository.Insert(oggettoItem);
            return OggettoDto.ConvertFrom(oggettoItem);           
        }

        //[Authorize(Roles ="Admin")]
        [Route("/api/v1/[controller]/getoggetti")]
        [HttpGet]
        public List<OggettoDto> GetOggetti()
        {
            OggettoRepository oggettoRepository = new OggettoRepository(_context);
            var oggetti = oggettoRepository.GetAll().AsNoTracking();
            List<OggettoDto> oggettiDto = new List<OggettoDto>();
            foreach (var oggetto in oggetti)
            {
                oggettiDto.Add(OggettoDto.ConvertFrom(oggetto));
            }

            return OggettoDto;
        }

        [Route("/api/v1/[controller]/deleteOggetto")]
        [HttpDelete]
        public HttpResponseMessage DeleteOggetto(int id)
        {
            OggettoRepository oggettoRepository = new OggettoRepository(_context);
            oggettoRepository.Delete(id);

            return new HttpResponseMessage(System.Net.HttpStatusCode.OK);
        }

      
    }
}
