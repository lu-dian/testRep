package cn.hy.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hy.cloud_note.entity.Note;
import cn.hy.cloud_note.service.NoteService;
import cn.hy.cloud_note.util.NoteResult;


@Controller
@RequestMapping("/note") //∆•≈‰«Î«Û¬∑æ∂
public class LoadNoteController {
	
	@Resource
	private NoteService service;
	
	@RequestMapping("/loadnote.do")
	@ResponseBody
	public NoteResult<Note> execute(String noteId){
		NoteResult<Note> result = service.loadNote(noteId);
		return result;
	}
}
