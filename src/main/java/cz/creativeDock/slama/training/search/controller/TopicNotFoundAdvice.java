package cz.creativeDock.slama.training.search.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class TopicNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(TopicNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(TopicNotFoundException ex) {
		return ex.getMessage();
	}
}
