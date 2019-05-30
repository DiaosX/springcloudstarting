using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;

namespace serviceconsume_netcore
{
    public class Program
    {
        public static void Main(string[] args)
        {
            BuildWebHost(args).Run();
        }

        public static IWebHost BuildWebHost(string[] args) =>
     WebHost.CreateDefaultBuilder(args)
         .UseStartup<Startup>()
         .UseKestrel(options =>
         {
             options.Limits.MaxConcurrentConnections = 100000;
             options.Limits.MaxConcurrentUpgradedConnections = 100000;
         })
         .UseUrls("http://*:60000")
         .Build();
    }
}
