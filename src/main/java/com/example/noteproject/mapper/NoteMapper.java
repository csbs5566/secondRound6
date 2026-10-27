package com.example.noteproject.mapper;

import com.example.noteproject.entity.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface NoteMapper {


    @Insert("INSERT INTO note (content) VALUES (#{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Note note);


    @Select("SELECT id, content, created_at FROM note " +
            "ORDER BY created_at DESC, id DESC LIMIT #{offset}, #{size}")
    List<Note> selectPage(@Param("offset") int offset, @Param("size") int size);


    @Select("SELECT COUNT(*) FROM note")
    long count();
}
