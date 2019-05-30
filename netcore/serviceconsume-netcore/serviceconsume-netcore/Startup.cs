using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using serviceconsume_netcore.Services;
using serviceconsume_netcore.Services.Impl;
using SkyApm.Utilities.DependencyInjection;
using Steeltoe.Common.Http.Discovery;
using Steeltoe.Discovery.Client;
using Swashbuckle.AspNetCore.Swagger;

namespace serviceconsume_netcore
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddMvc().SetCompatibilityVersion(CompatibilityVersion.Version_2_1);
            //添加Swagger.
            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new Info { Title = "serviceconsume_netcore", Version = "v1" });
                //Set the comments path for the swagger json and ui.
                var basePath = AppDomain.CurrentDomain.BaseDirectory;
                var xmlPath = Path.Combine(basePath, "serviceconsume-netcore.xml");
                c.IncludeXmlComments(xmlPath);
            });

            //启用服务发现
            services.AddDiscoveryClient(Configuration);
            // Add Steeltoe handler to container
            //services.AddTransient<DiscoveryHttpMessageHandler>();
            // Configure a HttpClient
            services.AddHttpClient("client-api-values", c =>
            {
                c.BaseAddress = new Uri(Configuration["Services:Client-Service:Url"]);
            })
            .AddHttpMessageHandler<DiscoveryHttpMessageHandler>()
            .AddTypedClient<IHelloService, HelloService>();

        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, Microsoft.AspNetCore.Hosting.IHostingEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseExceptionHandler("/Home/Error");
            }
            //app.UseHttpsRedirection();
            app.UseStaticFiles();

            //配置Swagger
            app.UseSwagger();
            app.UseSwaggerUI(c =>
            {
                c.SwaggerEndpoint("/swagger/v1/swagger.json", "serviceconsume_netcore V1");
            });

            // Add Steeltoe Discovery Client service
            app.UseDiscoveryClient();
            app.UseMvc();
        }
    }
}
