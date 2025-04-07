package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Exceptions.BookNotFoundException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    //GET
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    //POST
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    //PUT
    public void updateBook(Long id, Book updatedBook) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setBook(updatedBook.getBook());
        book.setAuthor(updatedBook.getAuthor());
        bookRepo.save(book);
    }

    //DELETE
    public Book deleteBook(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        bookRepo.deleteById(id);
        return book;
    }
}
