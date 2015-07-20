package cn.edu.nju.tss.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.nju.tss.conf.PageConfig;
import cn.edu.nju.tss.dao.BaseDao;
import cn.edu.nju.tss.dao.CourseDao;
import cn.edu.nju.tss.model.Course;

@Repository
public class CourseDaoImpl implements CourseDao {
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public List<Course> getCourseList() {
		// TODO Auto-generated method stub
		Session session = baseDao.getNewSession();
		try{
			String sql = "select * from Course";
			Query query = session.createSQLQuery(sql).addEntity(Course.class);
			@SuppressWarnings("unchecked")
			List<Course> list = query.list();
			if(list!=null){
				if(list.size()<=0){
					return null;
				}
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	@Override
	public Course getCourseByCode(String code) {
		// TODO Auto-generated method stub
		Session session = baseDao.getNewSession();
		try{
			String sql = "select * from Course where code = ?";
			Query query = session.createSQLQuery(sql).addEntity(Course.class);
			query.setString(0, code);
			@SuppressWarnings("unchecked")
			List<Course> list = query.list();
			if(list!=null){
				if(list.size()>0){
					return list.get(0);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	@Override
	public List<Course> getCourseByPage(int page) {
		// TODO Auto-generated method stub
		Session session = baseDao.getNewSession();
		try{
			String sql = "select * from Course limit ?,?";
			Query query = session.createSQLQuery(sql).addEntity(Course.class);
			query.setInteger(0, page*PageConfig.COURSE_PAGE);
			query.setInteger(1, PageConfig.COURSE_PAGE);
			@SuppressWarnings("unchecked")
			List<Course> list = query.list();
			if(list!=null){
				if(list.size()<=0){
					return null;
				}
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	@Override
	public List<Course> getUnfollowedCourseByPage(String address, int page) {
		// TODO Auto-generated method stub
		Session session = baseDao.getNewSession();
		try{
			String sql = "select * from Course where code not in (select code from FollowCourse where address='?') limit ?,?";
			Query query = session.createSQLQuery(sql).addEntity(Course.class);
			query.setString(0, address);
			query.setInteger(1, page*PageConfig.COURSE_PAGE);
			query.setInteger(2, PageConfig.COURSE_PAGE);
			@SuppressWarnings("unchecked")
			List<Course> list = query.list();
			if(list!=null){
				if(list.size()<=0){
					return null;
				}
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

}
