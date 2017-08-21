package cn.itcast.mapper;

import java.util.List;

import cn.itcast.pojo.User;

public interface UserMapper2 {
	
	// 根据id查询用户信息
	public User findUserById(Integer id) throws Exception;
	
	// 根据用户模糊查询用户信息
	public List<User> findUserByUserName(String userName) throws Exception;

}
