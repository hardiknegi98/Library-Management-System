package com.example.library.studentlibrary.controller;

import com.example.library.studentlibrary.models.Author;
import com.example.library.studentlibrary.models.Book;
import com.example.library.studentlibrary.models.Genre;
import com.example.library.studentlibrary.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Enumerated;
import java.util.List;

@RestController
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @PostMapping("/createBook")
    public ResponseEntity createBook(@RequestBody Book book){
        logger.info("books author id - "+ book.getAuthor());
        bookService.createBook(book);
        return new ResponseEntity<>("the book is added successfully", HttpStatus.CREATED);
    }


    @GetMapping("/getBooks")
    public ResponseEntity getBooks(@RequestParam(value = "genre", required = false)  Genre genre,
                                   @RequestParam(value = "available", required = false, defaultValue = "true") boolean available,
                                   @RequestParam(value = "author", required = false) String author){

        // genre + author
        // genre
        // author

        List<Book> bookList = bookService.getBooks(genre, available, author);

        return new ResponseEntity<>(bookList, HttpStatus.OK);

    }
}
