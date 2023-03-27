package nju.sentistrength.project.web;

import nju.sentistrength.project.core.Result;
import nju.sentistrength.project.service.TextService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;

@RestController
@RequestMapping("/text")
public class TextController {
    @Resource
    private TextService textService;

    @PostMapping("/basic")
    public Result<String> analyzeText(@RequestBody String text) {
        return textService.analyzeText(text);
    }

    @PostMapping("/file")
    public Result<String> analyzeFile(@RequestBody File file){
        return textService.analyzeFile(file);
    }
}
