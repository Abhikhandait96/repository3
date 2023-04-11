package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.BookLibrary;

@Service
public interface BookService {
	public List<BookLibrary> addBooks(List<BookLibrary> bookLibrary);
	public List<BookLibrary> getAll();
	public BookLibrary updateById(Integer id,BookLibrary bookLibrary);
	public void deleteById(Integer bookId);
}
