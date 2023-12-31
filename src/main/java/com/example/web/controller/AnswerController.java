package com.example.web.controller;

import com.example.web.domain.entity.Question;
import com.example.web.domain.service.AnswerService;
import com.example.web.domain.service.QuestionService;
import com.example.web.validation.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
                               @Valid AnswerForm answerForm, BindingResult bindingResult){
        Question question = this.questionService.getQuestion(id);
        //답변을 저장하는 코드
        if (bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "question_detail";
        }
        this.answerService.create(question, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }
}
