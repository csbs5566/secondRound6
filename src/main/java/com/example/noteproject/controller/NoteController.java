package com.example.noteproject.controller;

import com.example.noteproject.common.Result;
import com.example.noteproject.dto.NoteCreateDTO;
import com.example.noteproject.service.NoteService;
import com.example.noteproject.vo.NoteVO;
import com.example.noteproject.vo.PageResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping
    public Result<Long> create(@RequestBody NoteCreateDTO dto) {

        if (dto == null || dto.getContent() == null || dto.getContent().isBlank()) {
            return Result.error("content 不能为空");
        }
        return Result.success(noteService.create(dto));
    }

    @GetMapping
    public Result<PageResult<NoteVO>> page(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Result.success(noteService.page(page, size));
    }
}
