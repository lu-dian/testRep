package cn.hy.cloud_note.service;


import cn.hy.cloud_note.entity.User;
import cn.hy.cloud_note.util.NoteResult;


public interface UserService {
	public NoteResult<User> checkLogin(String name,String password);
	public NoteResult<Object> addUser(String name,String password,String nick);
}
