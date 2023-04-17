package com.example.demo;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import com.example.demo.Entity.BookLibrary;
import com.example.demo.Exceptions.BookNotFoundException;
import com.example.demo.Repository.BookLibraryRepository;
import com.example.demo.Service.BookServiceImpl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookLibraryRepository bookLibraryRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private BookServiceImpl bookService;

    private BookLibrary book1, book2;

    @BeforeEach
    void setUp() {
        book1 = new BookLibrary(1, "Book 1", "Author 1", "Publisher 1");
        book2 = new BookLibrary(2, "Book 2", "Author 2", "Publisher 2");
    }

    @Test
    @DisplayName("Test getAll() method")
    void testGetAll() {
        // given
        List<BookLibrary> expectedBooks = Arrays.asList(book1, book2);
        when(bookLibraryRepository.findAll()).thenReturn(expectedBooks);

        // when
        List<BookLibrary> actualBooks = bookService.getAll();

        // then
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    @DisplayName("Test updateById() method")
    void testUpdateById() {
        // given
        Integer id = 1;
        BookLibrary updatedBook = new BookLibrary(1, "Updated Book", "Updated Author", "Updated Publisher");
        when(bookLibraryRepository.findById(id)).thenReturn(Optional.of(book1));
        when(bookLibraryRepository.save(book1)).thenReturn(updatedBook);

        // when
        BookLibrary actualBook = bookService.updateById(id, updatedBook);

        // then
        assertEquals(updatedBook.getBookId(), actualBook.getBookId());
        verify(logger).info("Book Updated Successfully");
    }

    @Test
    @DisplayName("Test updateById() method with invalid id")
    void testUpdateByIdWithInvalidId() {
        // given
        Integer id = 3;
        BookLibrary updatedBook = new BookLibrary(3, "New Book", "New Author", "New Publisher");
        when(bookLibraryRepository.findById(id)).thenReturn(Optional.empty());

        // then
        assertThrows(BookNotFoundException.class, () -> bookService.updateById(id, updatedBook));
    }

    @Test
    @DisplayName("Test deleteById() method")
    void testDeleteById() {
        // given
        Integer id = 1;

        // when
        bookService.deleteById(id);

        // then
        verify(bookLibraryRepository).deleteById(id);
        verify(logger).info("book with id "+id +" deleted");
     }


    @Test
    @DisplayName("Test addBooks() method")
    void testAddBooks() {
        // given
        List<BookLibrary> booksToAdd = Arrays.asList(book1, book2);
        when(bookLibraryRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        List<BookLibrary> actualBooks = bookService.addBooks(booksToAdd);

        // then
        assertEquals(booksToAdd, actualBooks);
        verify(bookLibraryRepository, times(2)).save(any());
    }
}


