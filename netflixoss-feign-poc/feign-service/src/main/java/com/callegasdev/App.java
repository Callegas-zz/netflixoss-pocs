package com.callegasdev;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;

import java.util.Arrays;
import java.util.List;

/**
 * Created by callegas on 13/07/17.
 */
public class App {

    public static void main(String[] args) {

        LibraryBookApi fLibraryBookApi = Feign.builder()
                .contract(new JAXRSContract())
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(LibraryBookApi.class, "http://localhost:8080/books");


        List<BookRestModel> bookList = Arrays.asList(
                new BookRestModel(1L, "Blood Donator", "Josh Winston", 2007),
                new BookRestModel(2L, "It's high noon", "James Mccree", 1997)
        );

        bookList.stream().forEach(
                book -> fLibraryBookApi.add(book)
        );

        fLibraryBookApi.all().stream().forEach(
                book -> System.out.println(book.getName())
        );

        System.out.println(fLibraryBookApi.getById(2L).getAuthor());
    }

}
