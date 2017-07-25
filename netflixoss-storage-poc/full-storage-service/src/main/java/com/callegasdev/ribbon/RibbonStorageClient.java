package com.callegasdev.ribbon;

import com.google.inject.Injector;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.governator.guice.LifecycleInjector;
import com.netflix.governator.guice.LifecycleInjectorBuilder;
import com.netflix.ribbon.ClientOptions;
import com.netflix.ribbon.Ribbon;
import com.netflix.ribbon.RibbonResponse;
import com.netflix.ribbon.http.HttpRequestTemplate;
import com.netflix.ribbon.http.HttpResourceGroup;
import io.netty.buffer.ByteBuf;
import rx.Observable;

import java.nio.charset.Charset;

@SuppressWarnings("unchecked")
public class RibbonStorageClient {
	
	private EurekaClient eurekaClient;
	
	private EurekaClient getEurekaClient(){
		if (eurekaClient==null){
			LifecycleInjectorBuilder builder = LifecycleInjector.builder();
			Injector injector = builder.build().createInjector();
			eurekaClient = injector.getInstance(EurekaClient.class);
		}
		return eurekaClient;
	}
	
	private String getServerIP(String microservice){
		try{
			InstanceInfo info = getEurekaClient().getApplication(microservice.toUpperCase()).getInstances().get(0);
			String serverPort =  "http://" + info.getVIPAddress() + ":" + info.getPort();
			return serverPort;
		}catch(Exception e){
			throw new RuntimeException("Could not get Microservice IP:PORT. EX: " + e);
		}
	}
	
	private String callService(String microservice,String path){

		HttpResourceGroup httpRG = Ribbon.createHttpResourceGroup("apiGroup",
	            ClientOptions.create()
                .withMaxAutoRetriesNextServer(1)
                .withConfigurationBasedServerList(getServerIP(microservice)));
		
		HttpRequestTemplate<ByteBuf> apiTemplate = httpRG.newTemplateBuilder("apiCall",ByteBuf.class)
		            .withMethod("GET")
		            .withUriTemplate(path)
		            .withFallbackProvider(new DefaultFallback())
		            .withResponseValidator(new SimpleResponseValidator())
		            .build();
		
		RibbonResponse<ByteBuf> result = apiTemplate.requestBuilder()
									 .withHeader("client", "full-storage-service")
					                 .build()
					                 .withMetadata().execute();
		ByteBuf buf = result.content();
		String json = buf.toString(Charset.forName("UTF-8" ));
		return json;
	}
	
	public Observable<String> book(){
		return Observable.just(new String(callService("book-storage-service","/book")));
	}
	
	public Observable<String> vinyl(){
		return Observable.just(new String(callService("vinyl-disc-storage-service", "/vinyl")));
	}

}
