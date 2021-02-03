package cn.hy.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hy.cloud_note.entity.Share;
import cn.hy.cloud_note.service.ShareService;
import cn.hy.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/share")
public class ShareSearchController {
	
	@Resource
	private ShareService service;
	
	@RequestMapping("/search.do")
	@ResponseBody
	public NoteResult<List<Share>> execute(String keyword,int page){
		NoteResult<List<Share>> result = service.searchNote(keyword ,page);
		
		return result;
	}
}
