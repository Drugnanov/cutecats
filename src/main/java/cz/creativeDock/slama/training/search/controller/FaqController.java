package cz.creativeDock.slama.training.search.controller;

import cz.creativeDock.slama.training.search.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

//ToDo Security? // upload should be accessible only for autentificated users
//ToDo fix Slf4j - cannot fing symbol log??
@Controller
class FaqController {

    private final FaqService faqService;

    @Autowired
    FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping("/faqs/upload")
    public String listUploadedForm() {
        return "uploadForm";
    }

    @GetMapping("/faqs")
    public String getAllfaqs(Model model) {
        List<String> faqsString = faqService.getAllfaqs();
        model.addAttribute("faqs", faqsString);
        return "uploadForm";
    }

    //ToDo refactor
    @PostMapping("/faqs")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            return "uploadForm";
        }
        try {
            faqService.handleFileUpload(file);
        } catch (IOException e) {
//            log.error("There was a problem with file processing..." + e.getMessage());
            redirectAttributes.addFlashAttribute("message",
                    "There was a problem with file processing... " + e.getMessage() + "!");
        }
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/faqs";
    }

}