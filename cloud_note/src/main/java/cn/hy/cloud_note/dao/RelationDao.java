package cn.hy.cloud_note.dao;

import java.util.List;

import cn.hy.cloud_note.entity.Book;
import cn.hy.cloud_note.entity.User;

public interface RelationDao {
	//关联多个对象
	public User findUserAndBooks(String userId);
	public User findUserAndBooks1(String userId);
	
	//关联单个对象
	public List<Book> findBookAndUser();
	public List<Book> findBookAndUser1();
}
