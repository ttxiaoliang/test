package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import cn.itcast.pojo.User;

public class UserDaoImpl implements UserDao {
	
	private SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory(SqlSessionFactory ssf) {
		this.sqlSessionFactory = ssf;
	}

	@Override
	public User findUserById(Integer id) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.selectOne("user.selectUserById", id);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public List<User> findUserByUserName(String userName) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.selectList("user.selectUserByUserName", userName);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			sqlSession.close();
		}
	}

}
