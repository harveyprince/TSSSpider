package cn.edu.nju.tss.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		return null;
	}

	@Override
	public Course getCourseByCode(String code) {
		// TODO Auto-generated method stub
		Session session = baseDao.getNewSession();
		try{
			String sql = "select * from Course where code = ?";
			Query query = session.createSQLQuery(sql).addEntity(Course.class);
			query.setParameter(0, code);
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

}
