package com.harshit.blogapi.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.harshit.blogapi.payloads.ApiResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionhandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> map = new HashMap<>();
//		String message = ex.getMessage();
//		ApiResponse apiResponse = new ApiResponse(message,false);
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			map.put(fieldName, message);
			
					
		});
		
		return new ResponseEntity<Map<String, String>>(map,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, String>> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
		Map<String, String> map = new HashMap<>();
//		String message = ex.getMessage();
//		ApiResponse apiResponse = new ApiResponse(message,false);
//		ex.getBindingResult().getAllErrors().forEach((error)->{
//			String fieldName = ((FieldError)error).getField();
//			String message = error.getDefaultMessage();
//			map.put(fieldName, message);
//			
//					
//		});
		map.put("error_message", "Must pass a category");
		map.put("success", "false");
		
		return new ResponseEntity<Map<String, String>>(map,HttpStatus.BAD_REQUEST);
		
	}

}

