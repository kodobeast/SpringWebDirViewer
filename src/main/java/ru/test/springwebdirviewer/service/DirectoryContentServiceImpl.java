package ru.test.springwebdirviewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class DirectoryContentServiceImpl implements DirectoryContentService {

    @Autowired
    ReloadableResourceBundleMessageSource messageSource;

    File[] contentArray = null;

    @Override
    public List<String> getContentInto(String searchDirectory, boolean directories) {

        //TODO: при rootDeployFolder == default_path кинуть ошибку
        String rootDeployDirectory = messageSource.getMessage("root.deploy.folder", null, "default_path", new Locale("en"));
        List<String> content = new ArrayList<>();

        contentFinder(rootDeployDirectory, searchDirectory);

        if (contentArray != null) {
            for (int i = 0; i < contentArray.length; i++) {

                if (contentArray[i].isDirectory() && directories) {
                    content.add(contentArray[i].getName());
                }
                if (contentArray[i].isFile() && !directories) {
                    content.add(contentArray[i].getName());
                }
            }
        } else {
            return null;
        }
        return content;
    }

    private void contentFinder(String path, String search) {
        File root = new File(path);
        File[] list = root.listFiles();
        if (list == null) return;

        for (File f : list) {
            if (f.isDirectory() && search.equals(f.getName())) {
                contentArray = f.listFiles();
                return;
            } else {
                contentFinder(f.getAbsolutePath(), search);
            }
        }
    }
}
