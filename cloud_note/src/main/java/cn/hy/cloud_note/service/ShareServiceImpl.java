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
		//����note_id,title,body
		Note note = noteDao.findByNoteId(noteId);
		
		String shareId = NoteUtil.createId();
		share.setCn_share_id(shareId);
		String shareTitle = note.getCn_note_title();
		share.setCn_share_title(shareTitle);		
		String shareBody = note.getCn_note_body();
		share.setCn_share_body(shareBody);
		share.setCn_note_id(noteId);
		
		//�������
		shareDao.save(share);
		//����cn_note��
		
		
		//ģ���쳣--��ָ���쳣
		String str = null;
		str.length();
		
		//����result
		NoteResult<Object> result = new NoteResult<>();
		result.setStatus(0);
		result.setMsg("����ʼǳɹ�");
		return result;
	}
	@Override
	public NoteResult<List<Share>> searchNote(String keyword ,int page) {
		//����result
		NoteResult<List<Share>> result = new NoteResult<>();
		String title = "%" + keyword +"%";
		
		//����ץȡ��¼�����
		int begin = (page-1)*3;	
		//��������Map
		Map<String,Object> params = new HashMap<>();
		params.put("title", title);
		params.put("begin", begin);
		//ħ����ѯ
		List<Share> list = shareDao.findLikeTitle(params);
		
		result.setStatus(0);
		result.setMsg("��ѯ���");
		result.setData(list);
		return result;
	}

}
