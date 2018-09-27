package cz.creativeDock.slama.training.search.controller;

import cz.creativeDock.slama.training.search.controller.exception.CategoryNotFoundException;
import cz.creativeDock.slama.training.search.model.Click;
import cz.creativeDock.slama.training.search.repository.ClickRepository;
import cz.creativeDock.slama.training.search.repository.FaqRepository;
import cz.creativeDock.slama.training.search.service.ClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
class ClickController {

    private final ClickService clickService;

    @Autowired
    ClickController(ClickService clickService) {
        this.clickService = clickService;
    }

    @GetMapping("/clicks")
    public List<String> getAllClicks() {
        return clickService.getAllClicks();
    }

    //ToDo check category exists in Faq  - diff data structure?
    @PostMapping("/clicks/{category}")
    public Click updateClick(@PathVariable String category) {
        return clickService.persistNewClick(category);
    }


}