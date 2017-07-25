package com.callegasdev.rest;

import java.util.ArrayList;
import java.util.List;

public class VinylService {

    private List<Vinyl> vinyls = new ArrayList<>();

    public String addBook(String vinylName, String vinylAuthor) {
        vinyls.add(new Vinyl(vinylName, vinylAuthor));
        return vinylName + " has added!";
    }

    public List<Vinyl> getAll(){
        return vinyls;
    }
}
