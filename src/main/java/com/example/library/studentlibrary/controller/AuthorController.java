package com.example.library.studentlibrary.controller;

import com.example.library.studentlibrary.models.Author;
import com.example.library.studentlibrary.models.Student;
import com.example.library.studentlibrary.repositories.AuthorRepository;
import com.example.library.studentlibrary.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("/getAuthorById")
    public ResponseEntity getAuthorById(@RequestParam int id){
        return new ResponseEntity<>(authorRepository.getAuthorByIdCustom(id) , HttpStatus.OK);
    }


    @PostMapping("/createAuthor")
    public ResponseEntity createAuthor(@RequestBody Author author){

        authorService.create(author);
        return new ResponseEntity<>("the author is successfully added to the system", HttpStatus.CREATED);
    }
}
