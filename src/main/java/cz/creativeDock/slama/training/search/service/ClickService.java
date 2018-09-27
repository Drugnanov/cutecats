package cz.creativeDock.slama.training.search.service;

import cz.creativeDock.slama.training.search.model.Click;

import java.util.List;

public interface ClickService {
    Click persistNewClick(String category);

    List<String> getAllClicks();
}
