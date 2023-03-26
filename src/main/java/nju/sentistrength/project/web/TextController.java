package nju.sentistrength.project.web;

import nju.sentistrength.project.core.Result;
import nju.sentistrength.project.service.TextService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/text")
public class TextController {
    @Resource
    private TextService textService;

    @PostMapping("/basic")
    public Result<String> analizeText(@RequestBody String text) {
        return textService.analizeText(text);
    }
}
