package nju.sentistrength.project.service;

import nju.sentistrength.project.core.Result;

import java.io.File;

public interface TextService {
    Result analyzeText(String text);

    Result analyzeFile(File file);
}
