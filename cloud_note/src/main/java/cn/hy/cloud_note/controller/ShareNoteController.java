package cn.hy.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hy.cloud_note.service.ShareService;
import cn.hy.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/share")
public class ShareNoteController {

	@Resource
	private ShareService service;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId){
		NoteResult<Object> result = service.shareNote(noteId);
		
		return result;
	}
}
