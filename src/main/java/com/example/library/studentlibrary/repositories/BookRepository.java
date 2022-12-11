package com.example.library.studentlibrary.repositories;

import com.example.library.studentlibrary.models.Book;
import com.example.library.studentlibrary.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {


    @Query("select b from Book b where b.available =:availability and b.author in (select a from Author a where a.name =:author_name)")
   /* @Query(value = "select b.name, a.name\n" +
            "from book as b  \n" +
            "join author as a \n" +
            "where  b.author_id = (\n" +
            "select a2.id \n" +
            "from author as a2\n" +
            "where a2.name = :author_name)\n" +
            "and \n" +
            "a.name = :author_name\n" +
            "and \n" +
            "b.available = :availability" , nativeQuery = true)*/
    List<Book> findBooksByAuthor(String author_name, boolean availability);

    @Query("select b from Book b where b.genre =:genre and b.available =:available")
    //@Query(value = "select * from book b where b.genre =:genre and b.available =:available" , nativeQuery = true)
    List<Book> findBooksByGenre(Genre genre, boolean available);

    @Query("select b from Book b where b.available =:available and b.genre =:genre and b.author in (select a from Author a where a.name =:author)")
   /* @Query(value = "select b.name, a.name\n" +
            "from library.book as b  \n" +
            "join library.author as a \n" +
            "where  b.author_id = ( select a2.id from author as a2 where a2.name = :author)\n" +
            "and \n" +
            "a.name = :author\n" +
            "and \n" +
            "b.available = :available\n" +
            "and \n" +
            "b.genre = :genre", nativeQuery = true)*/
    List<Book> findBooksByGenreAuthor(Genre genre, String author, boolean available);


    @Query(value = "select * from book b where b.available =:availabilty", nativeQuery = true)
    List<Book> findByAvailability(boolean availabilty);


    @Modifying
    @Transactional
    @Query("update Book b set b.available =:#{#book.available}, b.card =:#{#book.card} where b.id =:#{#book.id}")
    int updateBook(Book book);

}
