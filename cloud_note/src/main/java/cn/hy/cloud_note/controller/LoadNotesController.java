package cn.hy.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hy.cloud_note.service.NoteService;
import cn.hy.cloud_note.util.NoteResult;


@Controller
@RequestMapping("/note") //∆•≈‰«Î«Û¬∑æ∂
public class LoadNotesController {
	
	@Resource
	private NoteService service;
	
	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public NoteResult<List<Map>> execute(String bookId){
		NoteResult<List<Map>> result = service.loadBookNotes(bookId);
		return result;
	}
}
