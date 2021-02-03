package cn.hy.cloud_note.service;

import java.util.List;
import java.util.Map;

import cn.hy.cloud_note.entity.Note;
import cn.hy.cloud_note.util.NoteResult;

public interface NoteService {
	public NoteResult<List<Map>> loadBookNotes(String bookId);
	public NoteResult<Note> loadNote(String noteId);
	public NoteResult<Object> updateNote(String noteId,String noteTitle,String noteBody);
	public NoteResult<Note> addNote(String userId,String bookId,String noteName);
	public NoteResult<Object> updateNoteStatus(String noteId);
}
