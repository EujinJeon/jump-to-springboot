package com.example.web.domain.service;

import com.example.web.Exception.DataNotFoundException;
import com.example.web.domain.entity.Question;
import com.example.web.domain.entity.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    public List<Question> getList(){
        return this.questionRepository.findAll();
    }

    public void create(String subject, String content){
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public Question getQuestion(Integer id){
        Optional<Question> oq = this.questionRepository.findById(id);
        if(oq.isPresent()){
            return oq.get();
        }
        else {
            throw new DataNotFoundException("question not found");
        }
    }

}
