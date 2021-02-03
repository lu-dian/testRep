package cn.hy.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hy.cloud_note.dao.UserDao;
import cn.hy.cloud_note.entity.User;
import cn.hy.cloud_note.util.NoteResult;
import cn.hy.cloud_note.util.NoteUtil;

//ɨ�赽spring����
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	public NoteResult<User> checkLogin(String name, String password) {
		//���ս������
		NoteResult<User> result = new NoteResult<>();
		
		//������name��ѯ���ݿ�
		User user = userDao.findByName(name);
		//����û���
		if(user == null) {
			result.setStatus(1);
			result.setMsg("�û�������");
			return result;
		}
		//�������
		//����
		String md5Password = NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5Password)) {
			result.setStatus(2);
			result.setMsg("�������");
			return result;
		}
		//�û��������붼��ȷ
		result.setStatus(0);
		result.setMsg("��½�ɹ�");
		result.setData(user);
		return result;
	}

	public NoteResult<Object> addUser(String name,String password,String nick) {
		NoteResult<Object> result = new NoteResult<>();
		//����û�
		User hasUser = userDao.findByName(name);
		if(hasUser != null) {
			//�˺��Ѿ�����
			result.setStatus(1);
			result.setMsg("�˺��Ѵ���");
			return result;
		}
		//����û�
		User user = new User();
		//�����û���
		user.setCn_user_name(name);
		//��������
		user.setCn_user_password(NoteUtil.md5(password));
		//�����û�ID
		user.setCn_user_id(NoteUtil.createId());
		//�����û��ǳ�
		user.setCn_user_desc(nick);
		userDao.saveUser(user);
		//�������ؽ��
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
		return result;
	}
}
