package nju.sentistrength.project.service;

import nju.sentistrength.project.core.Result;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.io.File;

public interface TextService {
    Result analyzeText(String text);

    Result analyzeFile(File file);
}
