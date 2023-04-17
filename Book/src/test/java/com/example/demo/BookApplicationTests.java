package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entity.BookLibrary;
import com.example.demo.Repository.BookLibraryRepository;
import com.example.demo.Service.BookServiceImpl;

@SpringBootTest
class BookApplicationTests {
	
	@Mock
	BookLibraryRepository bookLibraryRepository;
	
	@InjectMocks
	BookServiceImpl bookServiceImpl;
	
	@Test
	public void test_getAllBooks() {
		List<BookLibrary> bookLibraries=new ArrayList<>();
		bookLibraries.add(new BookLibrary(1, "ABC", "EFG", "XYZ"));
		bookLibraries.add(new BookLibrary(2, "AAA", "BBB", "CCC"));
		
		when(bookLibraryRepository.findAll()).thenReturn(bookLibraries);
		assertEquals(2, bookServiceImpl.getAll().size());
	
	}
	
	@Test
	public void test_addBooks() {
	
		List<BookLibrary> bookToAdd=new ArrayList<>();
		bookToAdd.add(new BookLibrary(1, "ABC", "EFG", "XYZ"));
		bookToAdd.add(new BookLibrary(2, "AAA", "BBB", "CCC"));
		
		List<BookLibrary> expectedSavedBooks=new ArrayList<>();
		expectedSavedBooks.add(new BookLibrary(1, "ABC", "EFG", "XYZ"));
		expectedSavedBooks.add(new BookLibrary(2, "AAA", "BBB", "CCC"));
		
		when(bookLibraryRepository.save(ArgumentMatchers.any(BookLibrary.class))).thenReturn(expectedSavedBooks.get(0),expectedSavedBooks.get(1));
		List<BookLibrary> savedBooks=bookServiceImpl.addBooks(bookToAdd);
		assertEquals(expectedSavedBooks, savedBooks);
		
	
	}

	

	@Test
	void contextLoads() {
	}

	
}
