package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BookLibrary {
	@Id
	private Integer bookId;
	private String bookName;
	private String author;
	private String Publisher;

	public BookLibrary(Integer bookId, String bookName, String author, String publisher) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		Publisher = publisher;
	}

	public BookLibrary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return Publisher;
	}

	public void setPublisher(String publisher) {
		Publisher = publisher;
	}

	@Override
	public String toString() {
		return "BookLibrary [bookId=" + bookId + ", bookName=" + bookName + ", author=" + author + ", Publisher="
				+ Publisher + "]";
	}
}
