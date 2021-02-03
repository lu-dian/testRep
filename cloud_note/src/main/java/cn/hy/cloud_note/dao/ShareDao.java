package cn.hy.cloud_note.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.hy.cloud_note.entity.Share;

@Repository("shareDao")
public interface ShareDao {
	public void save(Share share);
	public List<Share> findLikeTitle(Map params);
}
