using Project2.IServices;
using Shared.Model;
using Shared.Services;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace Project1WPF.ViewModel
{
    class OggettoWindowViewModel : BaseViewModel
    {
        #region fields
        private ObservableCollection<Oggetto> _oggetti = new ObservableCollection<Oggetto>();

        public ObservableCollection<Oggetto> Oggetti
        {
            get { return _oggetti; }
            set { _oggetti = value; OnPropertyChange(nameof(Oggetto)); }
        }
        #endregion

        #region methods
        public async Task LoadOggetti()
        {
            List<Oggetto> oggetti = null;
            using (HttpClient client = new HttpClient())
            {
                client.BaseAddress = new Uri(Shared.Define.CommonDefine.SERVICE1_BASEURL);
                IProject1Service service = new Project1Service(client);
                oggetti = await service.GetOggettiAsync();
            }

            _oggetti.Clear();
            foreach (var oggetto in oggetti)
            {
                _oggetti.Add(oggetto);
            }

            Oggetti = _oggetti;
        }

        #endregion
    }
}
