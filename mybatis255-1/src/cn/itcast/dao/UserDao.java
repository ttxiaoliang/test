package cn.itcast.dao;

import java.util.List;

import cn.itcast.pojo.User;

public interface UserDao {
	
	// 根据id查询用户信息
	public User findUserById(Integer id) throws Exception;
	
	// 根据用户模糊查询用户信息
	public List<User> findUserByUserName(String userName) throws Exception;

}
