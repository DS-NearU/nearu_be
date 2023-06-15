package com.nearu.nearu.controller;

import com.nearu.nearu.OriginObject;
import com.nearu.nearu.SessionRequest;
import com.nearu.nearu.request.CommentDto;
import com.nearu.nearu.request.QaCountsResponse;
import com.nearu.nearu.request.QaDto;
import com.nearu.nearu.services.QaService;
import com.nearu.nearu.entity.Comment;
import com.nearu.nearu.entity.Qa;
import com.nearu.nearu.request.QaReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class QaController extends OriginObject {
    private final QaService qaService;

    @PostMapping("/qa")
    public void post(SessionRequest request){
        QaDto map = map(request.getParam(), QaDto.class);
        qaService.post(map);
    }

    @PutMapping("/qa")
    public void edit(SessionRequest request){
        QaDto map = map(request.getParam(), QaDto.class);
        qaService.update(map);
    }

    @DeleteMapping("/qa")
    public void delete(SessionRequest request){
        QaDto map = map(request.getParam(), QaDto.class);
        qaService.delete(map.getQaNo());
    }

    @PostMapping("/comment")
    public void commentPost(SessionRequest request) {
        CommentDto map = map(request.getParam(), CommentDto.class);
        qaService.commentPost(map);
    }

    @PutMapping("/comment")
    public void commentEdit(SessionRequest request) {
        CommentDto map = map(request.getParam(), CommentDto.class);
        qaService.commentUpdate(map);
    }

    @DeleteMapping("/comment")
    public void commentDelete(SessionRequest request) {
        CommentDto map = map(request.getParam(), CommentDto.class);
        qaService.commentDelete(map.getCommentNo());
    }

    @GetMapping("/qa")
    public QaReadResponse read (SessionRequest request) {
        QaDto map = map(request.getParam(), QaDto.class);
        return qaService.fetchDetails(map.getQaNo());
    }

    @GetMapping("/qaAll")
    public ArrayList<QaCountsResponse> readAll (SessionRequest request) {
        QaDto map = map(request.getParam(), QaDto.class);
        return qaService.fetchAll(map);
    }
}