package cn.edu.nju.tss.service;

import java.util.List;

import cn.edu.nju.tss.model.Course;

public interface TSSService {
	public List<Course> getCourseListFromNet();
	public List<Course> compareCourseList(List<Course> info);
}
