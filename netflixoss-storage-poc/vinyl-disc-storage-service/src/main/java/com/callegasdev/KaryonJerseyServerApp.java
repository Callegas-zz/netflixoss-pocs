package com.callegasdev;

import com.callegasdev.KaryonJerseyServerApp.KaryonJerseyModuleImpl;
import com.callegasdev.rest.VinylService;
import com.netflix.governator.annotations.Modules;
import netflix.adminresources.resources.KaryonWebAdminModule;
import netflix.karyon.KaryonBootstrap;
import netflix.karyon.archaius.ArchaiusBootstrap;
import netflix.karyon.eureka.KaryonEurekaModule;
import netflix.karyon.jersey.blocking.KaryonJerseyModule;
import netflix.karyon.servo.KaryonServoModule;

@ArchaiusBootstrap
@KaryonBootstrap(name = "vinyl-disc-storage-service", healthcheck = HealthcheckResource.class)
@Modules(include = {
        ShutdownModule.class,
        KaryonWebAdminModule.class,
        KaryonServoModule.class,
        KaryonJerseyModuleImpl.class,
        KaryonEurekaModule.class
})
public interface KaryonJerseyServerApp {
    class KaryonJerseyModuleImpl extends KaryonJerseyModule {
        @Override
        protected void configureServer() {
            bind(VinylService.class).asEagerSingleton();

            bind(AuthenticationService.class).to(AuthenticationServiceImpl.class);
            interceptorSupport().forUri("/*").intercept(LoggingInterceptor.class);
            interceptorSupport().forUri("/vinyl").interceptIn(AuthInterceptor.class);
            server().port(6002).threadPoolSize(400);
        }
    }
}
