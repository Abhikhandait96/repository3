package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.Entity.BookLibrary;
import com.example.demo.Service.BookServiceImpl;
@Controller
public class BookLibraryController {

	@Autowired
	public BookServiceImpl bookServiceImpl;
	
	@PostMapping("/addBook")  
	@ResponseStatus(code = HttpStatus.CREATED)
	public int saveBook(@RequestBody BookLibrary bookLibrary)   
	{  
	bookServiceImpl.addNewBook(bookLibrary);  
	return bookLibrary.getBookId();  
	}  
	
	@GetMapping("/getBooks")
	@ResponseStatus(code = HttpStatus.OK)
    public List<BookLibrary> getBooks()
    {
        return bookServiceImpl.getAll();
    }
	
	@PutMapping("/updateBook/{bookid}")
	@ResponseStatus(code=HttpStatus.OK)
	public BookLibrary update(@PathVariable("bookid") Integer bookId,BookLibrary bookLibrary)   
	{  
	 return bookServiceImpl.updateById(bookId, bookLibrary);  
	 
	}
	
	@DeleteMapping("/deleteBook/{bookid}")  
	private void deleteBook(@PathVariable("bookid") int bookid)   
	{  
	bookServiceImpl.deleteById(bookid);  
	}
}
