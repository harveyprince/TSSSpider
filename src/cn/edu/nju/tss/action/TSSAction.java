package cn.edu.nju.tss.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.nju.tss.service.TSSService;

@Repository
public class TSSAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7861690796993971267L;
	
	@Autowired
	private TSSService tssService;
	
	
}
