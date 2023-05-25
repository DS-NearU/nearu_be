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
        qaService.update(question);
    }

    public void delete(Integer qaNo){
        commentService.deleteAll(qaNo);
        qaService.delete(qaNo);
    }

    public void commentPost(Integer userNo, Integer qaNo, String content) {
        Comment com = new Comment (userNo, qaNo, content, LocalDateTime.now());
        commentService.save(com);
    }

    public void commentEdit(Integer commentNo, String content) {
        Comment com = commentService.fetch(commentNo);
        com.setContent(content);
        com.setUpdatedAt(LocalDateTime.now());
        commentService.update(com);
    }

    public void commentDelete(Integer commentNo) {
        commentService.delete(commentNo);
    }
}