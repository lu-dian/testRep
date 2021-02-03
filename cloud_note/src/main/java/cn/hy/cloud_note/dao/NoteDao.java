package cn.hy.cloud_note.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.hy.cloud_note.entity.Note;


@Repository("noteDao")
public interface NoteDao {
	public List<Map> findByBookId(String bookId);
	public Note findByNoteId(String noteId);
	public int updateNote(Note note);
	public int save(Note note);
	public int updateNoteStatus(String noteId);
}
