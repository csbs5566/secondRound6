package com.example.noteproject.service;

import com.example.noteproject.dto.NoteCreateDTO;
import com.example.noteproject.vo.NoteVO;
import com.example.noteproject.vo.PageResult;


public interface NoteService {

    /* 保存一条笔记，返回新数据的 id */
    Long create(NoteCreateDTO dto);

    /* 分页查询 */
    PageResult<NoteVO> page(int page, int size);
}
