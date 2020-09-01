using Autoship.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Autoship.Repository
{
    public abstract class BaseRepository<T> where T : BaseModel
    {
        private readonly DbContext _context;

        public BaseRepository(DbContext context)
        {
            _context = context;
        }

        public T Insert(T item)
        {
            _context.Add(item);
            _context.SaveChanges();

            return item;
        }

        public IQueryable<T> GetAll()
        {
            return _context.Set<T>();
        }

        public void Delete(int id)
        {
            _context.Set<T>().Remove(_context.Set<T>().First(a => a.Id == id));
            _context.SaveChanges();
        }
    }
}
