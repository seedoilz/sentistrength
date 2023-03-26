package nju.sentistrength.project.service.impl;

import nju.sentistrength.project.core.AbstractService;
import nju.sentistrength.project.core.Result;
import nju.sentistrength.project.core.ResultGenerator;
import nju.sentistrength.project.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.wlv.sentistrength.Corpus;
import uk.ac.wlv.sentistrength.SentiStrengthWeb;

@Service
@Transactional
public class TextServiceImpl implements TextService {

    private Corpus corpus = new Corpus();

    @Override
    public Result analizeText(String text) {
        corpus.initialise();
        return ResultGenerator.genSuccessResult("success", SentiStrengthWeb.analyzeText(corpus, text));
//        return ResultGenerator.genSuccessResult("success", System.getProperty("user.dir"));
    }
}
