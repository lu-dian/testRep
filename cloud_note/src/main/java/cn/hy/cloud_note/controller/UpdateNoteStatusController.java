package cn.hy.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hy.cloud_note.service.NoteService;
import cn.hy.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class UpdateNoteStatusController {
	
	@Resource
	private NoteService service;
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId){
		NoteResult<Object> result = service.updateNoteStatus(noteId);
		
		return result;
	}
}
