package com.example.demo.Controller;

import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.BookLibrary;
import com.example.demo.Service.BookServiceImpl;

@RestController
public class BookLibraryController {

	@Autowired
	public BookServiceImpl bookServiceImpl;
	
	@GetMapping("/books")
	@ResponseStatus(code = HttpStatus.OK)
    public List<BookLibrary> getBooks()
    {
        return bookServiceImpl.getAll();
    }

	@PostMapping("/books")  
	@ResponseStatus(code = HttpStatus.CREATED)
	public List<BookLibrary> saveBook(@RequestBody List<BookLibrary> bookLibrary)   
	{  
	List<BookLibrary> books = bookServiceImpl.addBooks(bookLibrary);  
	return books;  
	}
	
	@PutMapping("/updateBook/{bookid}")
	@ResponseStatus(code=HttpStatus.OK)
	public BookLibrary update(@PathVariable("bookid") Integer bookId,@RequestBody BookLibrary bookLibrary)   
	{  
	 return bookServiceImpl.updateById(bookId, bookLibrary);  
	 
	}
	
	@DeleteMapping("/deleteBook/{bookid}")  
	private void deleteBook(@PathVariable("bookid") int bookid)   
	{  
	bookServiceImpl.deleteById(bookid);  
	}

}
