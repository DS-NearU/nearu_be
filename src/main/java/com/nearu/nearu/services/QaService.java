package com.nearu.nearu.services;
import com.nearu.nearu.entity.Comment;
import com.nearu.nearu.entity.Qa;
import com.nearu.nearu.repository.CommentRepository;
import com.nearu.nearu.repository.QaRepository;
import com.nearu.nearu.request.CommentDto;
import com.nearu.nearu.request.QaCountsResponse;
import com.nearu.nearu.request.QaDto;
import com.nearu.nearu.request.QaReadResponse;

import java.time.LocalDateTime;
import java.util.*;
public class QaService {

    private QaRepository qaRepository;
    private CommentRepository commentRepository;

    // comment update, comment delete
    public void post (QaDto q){
        Qa qa = new Qa();

        qa.setAnonymous(q.getAnonymous());
        qa.setUserNo(q.getUserNo());
        qa.setTitle(q.getTitle());
        qa.setQuestion(q.getQuestion());
        qa.setCreatedDt(LocalDateTime.now());

        qaRepository.save(qa);
    }

    public ArrayList<QaCountsResponse> fetchAll (QaDto q){
        ArrayList<Qa> list = (ArrayList<Qa>) qaRepository.findAll();
        ArrayList<QaCountsResponse> arrList = new ArrayList<QaCountsResponse>();

        for(int i=0; i<list.size(); i++) {
            QaCountsResponse res = new QaCountsResponse();
            res.setQuestion(list.get(i));
            res.setCountComments(commentRepository.countAllByQaNo(list.get(i).getQaNo()));
            arrList.add(res);
        }
        return arrList;
    }

    public QaReadResponse fetchDetails (Integer qaNo) {
        QaReadResponse readResponse = new QaReadResponse();
        readResponse.setQuestion(qaRepository.findByQaNo(qaNo));
        readResponse.setComments(commentRepository.findAllByQaNo(qaNo));

        return readResponse;
    }

    public void update(QaDto q) {
        Qa qa = qaRepository.findByQaNo(q.getQaNo());
        qa.setAnonymous(q.getAnonymous());
        qa.setUpdatedDt(LocalDateTime.now());
        qa.setTitle(q.getTitle());
        qa.setQuestion(q.getQuestion());

        qaRepository.save(qa);
    }

    public void delete(Integer qaNo){
        commentRepository.deleteAllByQaNo(qaNo);
        qaRepository.deleteByQaNo(qaNo);
    }

    public void commentUpdate(CommentDto c) {
        Comment com = commentRepository.findByCommentNo(c.getCommentNo());
        com.setContent(c.getContent());
        com.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(com);
    }

    public void commentDelete (Integer commentNo) {
        commentRepository.deleteByCommentNo(commentNo);
    }

    public void commentPost (CommentDto c) {
        Comment com = new Comment();

        com.setUserNo(c.getUserNo());
        com.setQaNo(c.getQaNo());
        com.setContent(c.getContent());
        com.setCreatedAt(LocalDateTime.now());

        commentRepository.save(com);
    }



}
