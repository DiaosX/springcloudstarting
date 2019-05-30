using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using serviceconsume_netcore.Models;
using serviceconsume_netcore.Services;

namespace serviceconsume_netcore.Controllers
{
    [Route("Home")]
    public class HomeController : Controller
    {
        private IHelloService helloService;
        public HomeController(IHelloService helloService)
        {
            this.helloService = helloService;
        }

        [HttpGet]
        [Route("SayHello")]
        public Task<string> SayHello(string message)
        {
            return this.helloService.SayHello(message);
        }


        [HttpGet]
        [Route("GetAllServive")]
        public IList<string> GetAllServive()
        {
            return this.helloService.GetAllService();
        }

        [HttpGet]
        [Route("GetValues")]
        public string GetValues()
        {
            using (var client = new HttpClient())
            {
                return client.GetStringAsync("http://localhost:60001/api/values/5").Result;
            }
        }
    }
}
