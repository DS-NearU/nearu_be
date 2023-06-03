package com.nearu.nearu.controller;

import com.nearu.nearu.services.CommentService;
import com.nearu.nearu.services.QaService;
import com.nearu.nearu.entity.Comment;
import com.nearu.nearu.entity.Qa;
import com.nearu.nearu.request.QaReadResponse;

import java.time.LocalDateTime;
import java.util.*;

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

    public QaReadResponse read (Integer qaNo) {
        QaReadResponse response =  new QaReadResponse();
        response.setQuestion(qaService.fetch(qaNo));
        ArrayList<Comment> comments = commentService.fetchAllByQa(qaNo);
        response.setComments(comments);
        return response;
    }

    public ArrayList<Qa> readAll () {
        return qaService.readAll();
    }
}