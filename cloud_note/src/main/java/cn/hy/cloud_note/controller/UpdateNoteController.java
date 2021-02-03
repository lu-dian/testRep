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
public class UpdateNoteController {

	@Resource
	private NoteService service;
	
	@RequestMapping("/update.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId,String noteTitle,String noteBody){
		NoteResult<Object> result = service.updateNote(noteId, noteTitle, noteBody);
		return result;
	}
}
