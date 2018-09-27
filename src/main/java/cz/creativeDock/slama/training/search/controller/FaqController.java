package cz.creativeDock.slama.training.search.controller;

import com.google.common.base.Strings;
import cz.creativeDock.slama.training.search.model.Faq;
import cz.creativeDock.slama.training.search.repository.FaqRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//ToDo Security? // upload should be accessible only for autentificated users
//ToDo fix Slf4j - cannot fing symbol log??
@Controller
@Slf4j
class FaqController {

    private static final String CSV_VALUE_SEPARATOR = ",";

    private final FaqRepository repository;

    FaqController(FaqRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/faqs/upload")
    public String listUploadedForm() {
        return "uploadForm";
    }

    @GetMapping("/faqs")
    public String getAllfaqs(Model model) {
        List<String> faqsString = repository.findAll().stream().map(Object::toString).collect(Collectors.toList());
        model.addAttribute("faqs", faqsString);
        return "uploadForm";
    }

    //ToDo refactor
    @PostMapping("/faqs")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            return "uploadForm";
        }
        //check its correct file
        BufferedReader br;
        List<Faq> result = new ArrayList<>();
        List<Long> ignoredRows = new ArrayList<>();
        try {
            String line;
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            long rowNumber = 0;
            String lastCategory = "Empty";
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
                if (Strings.isNullOrEmpty(category)){
                    category = lastCategory;
                }else{
                    lastCategory = category;
                }
                result.add(new Faq(rowNumber, category, question, answer));
            }
        } catch (IOException e) {
//            log.error("Where was a problem with file processing..." + e.getMessage());
            redirectAttributes.addFlashAttribute("message",
                    "There was a problem with file processing... " + e.getMessage() + "!");
        }
        saveFaqs(result);
        //ToDo sql problems
//        log.debug("File processing ignored rows these rows " + ignoredRows.stream().map(Object::toString)
//                .collect(Collectors.joining(",")));
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/faqs";
    }

    @Transactional
    void saveFaqs(List<Faq> faqs) {
        for (Faq faq : faqs) {
            repository.save(faq);
        }
    }

}