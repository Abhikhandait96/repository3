package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DuplicateBookException extends RuntimeException {
	public DuplicateBookException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
