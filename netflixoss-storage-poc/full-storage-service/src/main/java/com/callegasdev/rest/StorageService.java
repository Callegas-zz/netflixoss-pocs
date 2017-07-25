package com.callegasdev.rest;

import com.callegasdev.ribbon.RibbonStorageClient;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StorageService {

    private RibbonStorageClient client;

    @Inject
    public StorageService(RibbonStorageClient client) {
        this.client = client;
    }

    public String show(String product) {
        if (product.equalsIgnoreCase("book")) {
            return client.book().toBlocking().first();
        } else if (product.equalsIgnoreCase("vinyl")) {
            return client.vinyl().toBlocking().first();
        } else {
            return "error";
        }
    }

}
