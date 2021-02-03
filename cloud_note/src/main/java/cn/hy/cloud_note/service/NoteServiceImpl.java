package cn.hy.cloud_note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hy.cloud_note.dao.NoteDao;
import cn.hy.cloud_note.entity.Note;
import cn.hy.cloud_note.util.NoteResult;
import cn.hy.cloud_note.util.NoteUtil;

@Service("noteService")
public class NoteServiceImpl implements NoteService{

	@Resource
	private NoteDao dao;
	
	public NoteResult<List<Map>> loadBookNotes(String bookId) {
		NoteResult<List<Map>> result = new NoteResult<>();
		List<Map> list = dao.findByBookId(bookId);
		//判断是否有笔记
		if(list.size() == 0) {
			result.setStatus(1);
			result.setMsg("没有笔记");
			return result;
		}
		//有笔记
		result.setStatus(0);
		result.setMsg("笔记已找到");
		result.setData(list);
		return result;
	}

	@Override
	public NoteResult<Note> loadNote(String noteId) {
		NoteResult<Note> result = new NoteResult<>();
		Note note = dao.findByNoteId(noteId);
		if(note == null) {
			result.setStatus(1);
			result.setMsg("没有该笔记");
			return result;
		}
		result.setStatus(0);
		result.setMsg("笔记已找到");
		result.setData(note);
		return result;
	}

	@Override
	public NoteResult<Object> updateNote(String noteId, String noteTitle, String noteBody) {
		NoteResult<Object> result = new NoteResult<>();
		
		Note note = new Note();
		note.setCn_note_title(noteTitle);
		note.setCn_note_id(noteId);
		note.setCn_note_body(noteBody);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		//更新
		int rows = dao.updateNote(note);
		if(rows != 1) {
			result.setStatus(1);
			result.setMsg("修改失败");
		}
		result.setStatus(0);
		result.setMsg("修改成功");
		return result;
	}

	@Override
	public NoteResult<Note> addNote(String userId, String bookId, String noteName) {
		NoteResult<Note> result = new NoteResult<>();
		Note note = new Note();
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		//状态 1-normal 2-delete
		note.setCn_note_status_id("1");
		note.setCn_note_title(noteName);
		Long time = System.currentTimeMillis();
		note.setCn_note_create_time(time);
		note.setCn_note_last_modify_time(time);
		int rows = dao.save(note);
		if(rows != 1) {
			result.setStatus(1);
			result.setMsg("创建笔记本失败");
			return result;
		}
		result.setStatus(0);
		result.setMsg("笔记创建成功");
		result.setData(note);
		return result;
		
	}

	@Override
	public NoteResult<Object> updateNoteStatus(String noteId) {
		NoteResult<Object> result = new NoteResult<>();
		int rows = dao.updateNoteStatus(noteId);
		if(rows != 1) {
			result.setStatus(1);
			result.setMsg("删除失败");
			return result;
		}
		result.setStatus(0);
		result.setMsg("删除成功");
		return result;
	}

}
