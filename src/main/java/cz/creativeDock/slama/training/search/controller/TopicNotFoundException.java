package cz.creativeDock.slama.training.search.controller;

class TopicNotFoundException extends RuntimeException {

	TopicNotFoundException(Long id) {
		super("Could not find student " + id);
	}
}
