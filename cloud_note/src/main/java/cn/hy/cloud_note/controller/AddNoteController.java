package cn.hy.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hy.cloud_note.entity.Note;
import cn.hy.cloud_note.service.NoteService;
import cn.hy.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class AddNoteController {

	@Resource
	private NoteService service;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Note> execute(String userId,String bookId,String noteName){
		NoteResult<Note> result = service.addNote(userId, bookId, noteName);
		return result;
	}
}
