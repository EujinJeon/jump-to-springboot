package com.example.web.controller;

import ch.qos.logback.core.model.Model;
import com.example.web.domain.entity.Question;
import com.example.web.domain.service.AnswerService;
import com.example.web.domain.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id,
                               @RequestParam String content){
        Question question = this.questionService.getQuestion(id);
        //답변을 저장하는 코드
        this.answerService.create(question, content);
        return String.format("redirect:/question/detail/%s", id);
    }
}
