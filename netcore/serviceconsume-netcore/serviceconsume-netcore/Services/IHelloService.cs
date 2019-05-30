using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace serviceconsume_netcore.Services
{
    public interface IHelloService
    {
        Task<string> SayHello(string message);
        IList<string> GetAllService();
    }
}
