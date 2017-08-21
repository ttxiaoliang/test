package cn.itcast.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.pojo.User;

public class MyTest {
	
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void init() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("MyBatisCofig.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void envTest() throws Exception {
		// 1. 加载核心配置文件
		InputStream inputStream = Resources.getResourceAsStream("MyBatisCofig.xml");
		
		// 2. 根据核心配置文件创建会话工程
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		// 3. 根据会话工厂创建会话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		System.out.println(sqlSession);
	}
	
	// 测试根据id查询用户信息
	@Test
	public void test1() throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			User user = sqlSession.selectOne("user.selectUserById", 1001);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			sqlSession.close();
		}
	}
	// 测试插入用户信息
	@Test
	public void test2() throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			User user = new User();
			user.setAddress("北京");
			user.setAge(18);
			user.setMobile("13500099000");
			user.setName("张三");
			user.setSex("男");
			int count = sqlSession.insert("user.insertUser", user);
			System.out.println(count);
			System.out.println(user.getUserId());
			sqlSession.commit();
		} catch(Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}
	
	// 测试根据id更新用户信息
	@Test
	public void test3() throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			User user = new User();
			user.setUserId(1012);
			user.setAddress("北京");
			user.setAge(18);
			user.setMobile("13500099000");
			user.setName("李四");
			user.setSex("男");
			int count = sqlSession.update("user.updateUserById", user);
			System.out.println(count);
			sqlSession.commit();
		} catch(Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}
	// 测试根据id删除用户信息
	@Test
	public void test4() throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			int count = sqlSession.delete("user.deleteUserById", 1012);
			System.out.println(count);
			sqlSession.commit();
		} catch(Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
	}
	// 测试根据用户名模糊查询用户信息
	@Test
	public void test5() throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
//			List<User> userList = sqlSession.selectList("user.selectUserByUserName", "%王%");
//			List<User> userList = sqlSession.selectList("user.selectUserByUserName2", "王");
//			List<User> userInfoList = sqlSession.selectList("user.selectUserByUserName3", "' OR 1=1 OR 1='");
			List<User> userInfoList = sqlSession.selectList("user.selectUserByUserName4", "' OR 1=1 OR 1='");
			System.out.println(userInfoList);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			sqlSession.close();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
