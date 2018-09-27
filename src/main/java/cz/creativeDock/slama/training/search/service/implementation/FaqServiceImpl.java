package cz.creativeDock.slama.training.search.service.implementation;

import com.google.common.base.Strings;
import cz.creativeDock.slama.training.search.model.Faq;
import cz.creativeDock.slama.training.search.repository.FaqRepository;
import cz.creativeDock.slama.training.search.service.ClickService;
import cz.creativeDock.slama.training.search.service.FaqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;
    private static final String CSV_VALUE_SEPARATOR = ",";

    @Autowired
    FaqServiceImpl(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public void handleFileUpload(MultipartFile file) throws IOException {
        List<Faq> result = new ArrayList<>();
        List<Long> ignoredRows = new ArrayList<>();
        InputStream is = file.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        long rowNumber = 0;
        String lastCategory = "Empty";
        String line;
        while ((line = br.readLine()) != null) {
            rowNumber++;
            if (Strings.isNullOrEmpty(line) || rowNumber == 1) { //skip header
                ignoredRows.add(rowNumber);
                continue;
            }
            String[] values = line.split(CSV_VALUE_SEPARATOR);
            if (values.length < 4) {
                ignoredRows.add(rowNumber);
                continue;
            }
            String category = values[1];
            String question = values[2];
            String answer = values[3];
            if (Strings.isNullOrEmpty(question) || Strings.isNullOrEmpty(answer)) {
                ignoredRows.add(rowNumber);
                continue;
            }
            if (Strings.isNullOrEmpty(category)) {
                category = lastCategory;
            } else {
                lastCategory = category;
            }
            result.add(new Faq(rowNumber, category, question, answer));
        }
        saveFaqs(result);
        deleteIgnoredRows(ignoredRows);
        //ToDo sql problems
//        log.debug("File processing ignored rows these rows " + ignoredRows.stream().map(Object::toString)
//                .collect(Collectors.joining(",")));
    }

    private void deleteIgnoredRows(List<Long> ignoredRows) {
        faqRepository.deleteFaqsWithIds(ignoredRows);
    }

    private void saveFaqs(List<Faq> faqs) {
        for (Faq faq : faqs) {
            faqRepository.save(faq);
        }
    }

    @Override
    public List<String> getAllfaqs() {
        return faqRepository.findAll().stream().map(Object::toString).collect(Collectors.toList());
    }
}

