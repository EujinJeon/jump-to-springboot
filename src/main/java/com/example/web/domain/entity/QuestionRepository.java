package com.example.web.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    // 제목으로 조회하는 메서드
    Question findBySubject(String subject);
    // 제목과 내용으로 조회하는 메서드
    Question findBySubjectAndContent(String subject, String content);
    // 일부 문구는 포함하는 제목으로 조회하는 메서드
    List<Question> findBySubjectLike(String subject);
}
