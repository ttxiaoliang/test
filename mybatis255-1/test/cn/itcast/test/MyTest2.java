package cn.itcast.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.dao.UserDao2;
import cn.itcast.dao.UserDaoImpl;
import cn.itcast.pojo.User;

public class MyTest2 {
	
	private SqlSessionFactory sqlSessionFactory;
	private UserDaoImpl userDao;
	
	@Before
	public void init() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("MyBatisCofig.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		userDao = new UserDaoImpl();
		userDao.setSqlSessionFactory(sqlSessionFactory);
		
	}
	
	// 测试根据id查询用户信息
	@Test
	public void test1() throws Exception {
		User user = userDao.findUserById(1001);
		System.out.println(user);
	}
	
	// 测试根据用户名模糊查询用户信息
	@Test
	public void test2() throws Exception {
		List<User> userList = userDao.findUserByUserName("%王%");
		System.out.println(userList);
	}
	
	// 测试根据用户名模糊查询用户信息
	@Test
	public void test3() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDao2 userDao2 = sqlSession.getMapper(UserDao2.class);
		User user = userDao2.findUserById(1001);
		System.out.println(user);
	}

	// 测试根据用户名模糊查询用户信息
	@Test
	public void test4() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDao2 userDao2 = sqlSession.getMapper(UserDao2.class);
		List<User> userList= userDao2.findUserByUserName("%王%");
		System.out.println(userList);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
