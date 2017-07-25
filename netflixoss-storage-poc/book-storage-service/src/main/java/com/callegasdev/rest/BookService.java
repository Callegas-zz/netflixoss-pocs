package com.callegasdev.rest;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private List<Book> books = new ArrayList<>();

    public String addBook(String bookName, String bookAuthor) {
        books.add(new Book(bookName, bookAuthor));
        return bookName + " has added!";
    }

    public List<Book> getAll(){
        return books;
    }
}
