package ru.test.springwebdirviewer.controller;

import ru.test.springwebdirviewer.service.DirectoryContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DirectoryContentController {

    @Autowired
    private DirectoryContentService directoryContentService;

    @RequestMapping(value = "/content/{directory}", method = RequestMethod.GET)
    public String getAppContent(Model model, @PathVariable("directory") String directory) {

        List<String> directories = directoryContentService.getContentInto(directory, true);
        List<String> files = directoryContentService.getContentInto(directory, false);

        if (directories == null & files == null) {
            model.addAttribute("error", "Content not found");
        }

        if (directories != null && !directories.isEmpty()) {
            model.addAttribute("directories", directories);
        }
        if(files != null && !files.isEmpty()) {
            model.addAttribute("files", files);
        }
        return "welcome";
    }
}
