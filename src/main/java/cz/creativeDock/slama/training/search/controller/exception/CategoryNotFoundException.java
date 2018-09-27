package cz.creativeDock.slama.training.search.controller.exception;

public class CategoryNotFoundException extends RuntimeException {

	public CategoryNotFoundException(String category) {
		super("Could not find category " + category);
	}
}
