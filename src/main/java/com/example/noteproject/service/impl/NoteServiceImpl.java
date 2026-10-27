package com.example.noteproject.service.impl;

import com.example.noteproject.dto.NoteCreateDTO;
import com.example.noteproject.entity.Note;
import com.example.noteproject.mapper.NoteMapper;
import com.example.noteproject.service.NoteService;
import com.example.noteproject.vo.NoteVO;
import com.example.noteproject.vo.PageResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NoteServiceImpl implements NoteService {

    private final NoteMapper noteMapper;


    public NoteServiceImpl(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    @Override
    public Long create(NoteCreateDTO dto) {
        // 1. DTO -> Entity
        Note note = new Note();
        note.setContent(dto.getContent());

        // 2. 交给 Mapper 存进数据库
        noteMapper.insert(note);

        // 3. 插入后返回ID
        return note.getId();
    }

    @Override
    public PageResult<NoteVO> page(int page, int size) {
     //至少一页
        if (page < 1) {
            page = 1;
        }
        if (size < 1) {
            size = 10;
        }
        if (size > 100) {
            // 最多一页 100 条
            size = 100;
        }

        // ===== 核心公式：第 page 页要跳过前面 (page - 1) * size 条 =====
        int offset = (page - 1) * size;

        List<Note> notes = noteMapper.selectPage(offset, size);
        long total = noteMapper.count();

        // ===== Entity -> VO =====
        List<NoteVO> list = new ArrayList<>();
        for (Note note : notes) {
            NoteVO vo = new NoteVO();
            vo.setId(note.getId());
            vo.setContent(note.getContent());
            vo.setCreatedAt(note.getCreatedAt());
            list.add(vo);
        }

        return new PageResult<>(page, size, total, list);
    }
}
