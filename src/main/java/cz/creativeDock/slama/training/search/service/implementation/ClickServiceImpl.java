package cz.creativeDock.slama.training.search.service.implementation;

import cz.creativeDock.slama.training.search.controller.exception.CategoryNotFoundException;
import cz.creativeDock.slama.training.search.model.Click;
import cz.creativeDock.slama.training.search.repository.ClickRepository;
import cz.creativeDock.slama.training.search.repository.FaqRepository;
import cz.creativeDock.slama.training.search.service.ClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClickServiceImpl implements ClickService {

    private final ClickRepository clickRepository;
    private final FaqRepository faqRepository;

    @Autowired
    ClickServiceImpl(ClickRepository clickRepository, FaqRepository faqRepository) {
        this.clickRepository = clickRepository;
        this.faqRepository = faqRepository;
    }

    @Override
    public Click persistNewClick(String category) {
        if (!faqRepository.existsByCategory(category)) {
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

    @Override
    public List<String> getAllClicks() {
        return clickRepository.findAll().stream().map(Object::toString).collect(Collectors.toList());
    }
}

