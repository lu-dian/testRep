package cn.hy.cloud_note.dao;

import org.springframework.stereotype.Repository;

import cn.hy.cloud_note.entity.User;

@Repository
public interface UserDao {
	public User findByName(String name);
	public void saveUser(User user);
}
