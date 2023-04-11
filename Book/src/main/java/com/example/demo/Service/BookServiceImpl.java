package com.example.demo.Service;

import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.BookLibrary;
import com.example.demo.Exceptions.BookNotFoundException;
import com.example.demo.Exceptions.DuplicateBookException;
import com.example.demo.Repository.BookLibraryRepository;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	public BookLibraryRepository bookLibraryRepository;

	org.slf4j.Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Override
	public String addNewBook(BookLibrary bookLibrary) {
		java.util.Optional<BookLibrary> byId = bookLibraryRepository.findById(bookLibrary.getBookId());

		byId.ifPresent(book -> {
			throw new DuplicateBookException("Book with same id present");
		});

		if (!byId.isPresent()) {
			logger.info("No Duplicates found");
			bookLibraryRepository.save(bookLibrary);
			logger.info("Book Saved Successfully");
		}

//          BookLibrary bookById=bookLibraryRepository.findById(bookLibrary.getBookId()).orElse(null);
//          if(bookById==null) {
//        	  bookLibraryRepository.save(bookLibrary);
//        	  return "Customer added Successfully";
//          }
//          else
//        	  throw new DuplicateBookException("Book already exists");

		return null;
	}

	@Override
	public List<BookLibrary> getAll() {
		// TODO Auto-generated method stub
		return this.bookLibraryRepository.findAll();
	}

	@Override
	public BookLibrary updateById(Integer id,BookLibrary bookLibrary) {
		BookLibrary bookById=bookLibraryRepository.findById(id).orElse(null);
		if(bookById==null) {
			throw new BookNotFoundException("Book with this ID is not present");
		}
		else {
			bookById.setBookName(bookLibrary.getBookName());
			bookById.setAuthor(bookLibrary.getAuthor());
			bookById.setPublisher(bookLibrary.getPublisher());
			bookLibraryRepository.save(bookById);
			logger.info("Book Updated Successfully");
		}
		return bookById;

	}

	@Override
	public void deleteById(Integer bookId) {
		bookLibraryRepository.deleteById(bookId);
		logger.info("book with "+ bookId +"id deleted");
		
	}

}
