package cz.creativeDock.slama.training.search.controller;

import cz.creativeDock.slama.training.search.controller.exception.CategoryNotFoundException;
import cz.creativeDock.slama.training.search.model.Click;
import cz.creativeDock.slama.training.search.repository.ClickRepository;
import cz.creativeDock.slama.training.search.repository.FaqRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
class ClickController {

    private final ClickRepository clickRepository;
    private final FaqRepository faqRepository;

    ClickController(ClickRepository clickRepository, FaqRepository faqRepository) {
        this.clickRepository = clickRepository;
        this.faqRepository = faqRepository;
    }

    @GetMapping("/clicks")
    public List<String> getAllClicks() {
        return clickRepository.findAll().stream().map(Object::toString).collect(Collectors.toList());
    }

    //ToDo check category exists in Faq  - diff data structure?
    @PostMapping("/clicks/{category}")
    public Click updateClick(@PathVariable String category) {
        if (!faqRepository.existsByCategory(category)){
            throw new CategoryNotFoundException(category);
        }
        Optional<Click> clickPersisted = clickRepository.findByCategory(category);
        return clickPersisted
                .map(click -> {
                    click.setCount(click.getCount() + 1);
                    return clickRepository.save(click);
                })
                .orElseGet(() -> {
                    return clickRepository.save(new Click(category, 1L));
                });
    }
}