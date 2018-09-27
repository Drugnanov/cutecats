package cz.creativeDock.slama.training.search.controller;

import cz.creativeDock.slama.training.search.model.Faq;
import cz.creativeDock.slama.training.search.repository.FaqRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class TopicController {

    private final FaqRepository repository;

    TopicController(FaqRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/topics")
    public List<String> getAllTopics() {
        return repository.findDistinctCategory();
    }

    //ToDo String kontrola
    //ToDo case sensitive?
    //ToDo proc to nefunguje???????????????????????????????
    @GetMapping("/topics/{category}")
    public List<Faq> getTopic(@PathVariable String category) {
        List<Faq> faqs = repository.findByCategory(category);
        return faqs;
    }

    @GetMapping("/topics/search/{search}")
    public List<String> getFilteredTopic(@PathVariable String search) {
        List<String> values = repository.filterTopicsByText(search);
        return values;
    }
}