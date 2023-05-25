package com.nearu.nearu;

import java.time.LocalDateTime;

public class QaController {
    private final QaService qaService = new QaService();
    private final CommentService commentService = new CommentService();

    public void post(Integer userNo, Boolean anonymous, String title, String content){
        Qa question = new Qa(anonymous, LocalDateTime.now(), title, content);
        question.setUserNo(userNo);
        qaService.save(question);
    }
    public void edit(Integer qaNo, Boolean anonymous, String title, String content){
        Qa question = qaService.fetch(qaNo);
        question.setAnonymous(anonymous);
        question.setUpdatedDt(LocalDateTime.now());
        question.setTitle(title);
        question.setQuestion(content);
    }

    public void delete(Integer qaNo){
        commentService.deleteAll(qaNo);
        qaService.delete(qaNo);
    }

}
