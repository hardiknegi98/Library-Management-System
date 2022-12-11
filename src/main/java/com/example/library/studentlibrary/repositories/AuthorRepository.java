package com.example.library.studentlibrary.repositories;

import com.example.library.studentlibrary.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query(value = "select a.name from author a where a.id = :id" , nativeQuery = true)
    Author getAuthorByIdCustom(int id);

}
