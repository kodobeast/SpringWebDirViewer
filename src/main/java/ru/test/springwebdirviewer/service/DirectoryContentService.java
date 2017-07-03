package ru.test.springwebdirviewer.service;

import java.util.List;
import java.util.Set;

public interface DirectoryContentService {
    List<String> getContentInto(String rootFolder, boolean directory);
}
