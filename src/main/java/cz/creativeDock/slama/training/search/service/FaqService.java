package cz.creativeDock.slama.training.search.service;

import cz.creativeDock.slama.training.search.model.Click;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FaqService {
    void handleFileUpload(MultipartFile file) throws IOException;

    List<String> getAllfaqs();
}
