package cn.hy.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hy.cloud_note.dao.NoteDao;
import cn.hy.cloud_note.dao.ShareDao;
import cn.hy.cloud_note.entity.Note;
import cn.hy.cloud_note.entity.Share;
import cn.hy.cloud_note.util.NoteResult;
import cn.hy.cloud_note.util.NoteUtil;

@Service("shareService")
@Transactional
public class ShareServiceImpl implements ShareService{

	@Resource
	private ShareDao shareDao;
	@Resource
	private NoteDao noteDao;
	public NoteResult<Object> shareNote(String noteId) {
		Share share = new Share();
		//查找note_id,title,body
		Note note = noteDao.findByNoteId(noteId);
		
		String shareId = NoteUtil.createId();
		share.setCn_share_id(shareId);
		String shareTitle = note.getCn_note_title();
		share.setCn_share_title(shareTitle);		
		String shareBody = note.getCn_note_body();
		share.setCn_share_body(shareBody);
		share.setCn_note_id(noteId);
		
		//保存分享
		shareDao.save(share);
		//更新cn_note表
		
		
		//模拟异常--空指针异常
		String str = null;
		str.length();
		
		//构建result
		NoteResult<Object> result = new NoteResult<>();
		result.setStatus(0);
		result.setMsg("分享笔记成功");
		return result;
	}
	@Override
	public NoteResult<List<Share>> searchNote(String keyword ,int page) {
		//构建result
		NoteResult<List<Share>> result = new NoteResult<>();
		String title = "%" + keyword +"%";
		
		//计算抓取记录的起点
		int begin = (page-1)*3;	
		//构建参数Map
		Map<String,Object> params = new HashMap<>();
		params.put("title", title);
		params.put("begin", begin);
		//魔化查询
		List<Share> list = shareDao.findLikeTitle(params);
		
		result.setStatus(0);
		result.setMsg("查询完毕");
		result.setData(list);
		return result;
	}

}
