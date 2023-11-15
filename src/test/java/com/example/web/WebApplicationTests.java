package com.example.web;

import com.example.web.domain.entity.Question;
import com.example.web.domain.entity.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WebApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    // 데이터 베이스에 데이터를 저장하는 테스트 코드
    @Test
    void testJPA() {
        
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb가 무엇인지 알고싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);
        
        Question q2 = new Question();
        q2.setSubject("스프링부트 질문입니다.");
        q2.setContent("이거는 어떻게 해야합니까?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);
    }

    // 모든 데이터를 조회하여 데이터가 올바르게 저장됐는지 확인하는 테스트 코드
    // assertEquals는 asserEquals(기대값, 실제값)인 메서드로 기대값과 실제값이 동일한지 조사한다.
    @Test
    void findAll(){
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    // ID값으로 데이터를 조회하는 테스트 코드
    @Test
    void findById(){
        Optional<Question> oq = this.questionRepository.findById(1);
        if(oq.isPresent()){
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }

    // 제목으로 데이터를 조회하는 테스트 코드
    @Test
    void findBySubject(){
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, q.getId());
    }

    // 제목과 내용으로 조회하는 테스트 코드
    /*
        And -> 여러 칼럼을 and로 검색
        Or -> 여러 칼럼을 or로 검색
        Between -> 컬럼을 between으로 검색
        LessThan -> 작은 항목 검색 ex) findByLessThan(Integer Id) 아이디 값이 작은항목
        GreaterThanEqual -> 크거나 같은 항목 검색 ex) findByGreaterThanEqual(Integer Id)
        Like -> like 검색
        In -> 여러 값중에 하나인 항목
        OrderBy -> 검색 결과를 정렬하여 전달
    */
    @Test
    void findBySubjectAndContent(){
        Question q = this.questionRepository
                .findBySubjectAndContent("sbb가 무엇인가요?", "sbb가 무엇인지 알고싶습니다.");
        assertEquals(1, q.getId());
    }

    // 일부 문구를 포함한 제목을 조회하는 테스트 코드
    /*
    * sbb% -> "sbb"로 시작하는 문자열
    * %sbb -> "sbb"로 끝나는 문자열
    * %sbb% -> "sbb"를 포함하는 문자열
    * */
    @Test
    void findBySubjectLike(){
        List<Question> qList = this.questionRepository.finsBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    // 데이터 수정 테스트 코드
    @Test
    void modifySubject(){
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
    }

    //데이터 삭제 테스트 코드
    @Test
    void deleteData(){
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }
}
