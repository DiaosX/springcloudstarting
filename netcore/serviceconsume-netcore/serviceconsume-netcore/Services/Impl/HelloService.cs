using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.Extensions.Logging;
using Steeltoe.Common.Discovery;

namespace serviceconsume_netcore.Services.Impl
{
    public class HelloService : IHelloService
    {
        private const string REQUEST_URL = "https://serviceprovider/hello/sayHello";
        private ILogger<HelloService> _logger;
        private readonly HttpClient _httpClient;
        private readonly IDiscoveryClient discoveryClient;

        public HelloService(IDiscoveryClient discoveryClient, HttpClient httpClient, ILoggerFactory logFactory = null)
        {
            _httpClient = httpClient;
            _logger = logFactory?.CreateLogger<HelloService>();
            this.discoveryClient = discoveryClient;
        }
        public async Task<string> SayHello(string message)
        {
            var result = await _httpClient.GetStringAsync(REQUEST_URL + "?message=" + message);
            return result;
        }
        public IList<string> GetAllService()
        {
            return this.discoveryClient.Services;
        }
    }
}
