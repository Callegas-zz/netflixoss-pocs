package com.callegasdev.msa;

import com.callegasdev.rest.StorageService;
import com.callegasdev.ribbon.RibbonStorageClient;
import com.google.inject.AbstractModule;

public class GuiceBindingsModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(RibbonStorageClient.class);
    	bind(StorageService.class);
	}
	
}
