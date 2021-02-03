package cn.hy.cloud_note.service;

import java.util.List;

import cn.hy.cloud_note.entity.Share;
import cn.hy.cloud_note.util.NoteResult;

public interface ShareService {
	public NoteResult<Object> shareNote(String noteId);
	public NoteResult<List<Share>> searchNote(String keyword,int page);
}
