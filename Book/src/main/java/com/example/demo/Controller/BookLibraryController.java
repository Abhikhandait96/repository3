package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.BookLibrary;
import com.example.demo.Service.BookServiceImpl;

@RestController
@RequestMapping("/books")
public class BookLibraryController {

	@Autowired
	public BookServiceImpl bookServiceImpl;
	
	@GetMapping("/getAll")
	@ResponseStatus(code = HttpStatus.FOUND)
    public List<BookLibrary> getBooks()
    {
        return bookServiceImpl.getAll();
    }

	@PostMapping("/add")  
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
	@ResponseStatus(code = HttpStatus.OK)
	private void deleteBook(@PathVariable("bookid") Integer bookid)   
	{  
	bookServiceImpl.deleteById(bookid);  
	}

}
