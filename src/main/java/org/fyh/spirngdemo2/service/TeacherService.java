package org.fyh.spirngdemo2.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.fyh.spirngdemo2.commons.OpenPage;
import org.fyh.spirngdemo2.entity.Teacher;
import org.fyh.spirngdemo2.mapper.TeacherMapper;
import org.fyh.spirngdemo2.util.UUIDUtil;
import org.fyh.spirngdemo2.vo.TeacherVo;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class TeacherService {
	
	@Resource
	TeacherMapper teacherMapper;
	
	/**
	 * 测试使用mybitas的参数，不用之前的对象作为参数查询
	 * @param name
	 * @return
	 */
	public List<Teacher> searchByName(String name){
		return teacherMapper.searchByName(name);
	}
	
	/**
	 * 测试批量插入
	 * @return
	 */
	public int insertBatch(){
		List<Teacher> list = new ArrayList<Teacher>();
		Teacher t1 = new Teacher();
		t1.setCreateTime(new Date());
		t1.setId(UUIDUtil.getUUID());
		t1.setName("批量录入"+new Date().getTime());
		
		Teacher t2 = new Teacher();
		t2.setCreateTime(new Date());
		t2.setId(UUIDUtil.getUUID());
		t2.setName("批量录入"+new Date().getTime());
		
		list.add(t1);
		list.add(t2);
		
		return teacherMapper.insertBatch(list);
	}
	
	/**
	 * 测试批量删除，用的是mybatis的foreach:数据是数组
	 * @param ids
	 * @return
	 */
	public int deleteBatch(String ids){
		String[] split = ids.split(",");
		return teacherMapper.deleteBatch(split);
	}
	
	/**
	 * 一对多关联查询（运用mybatis的collection关键字）
	 * @return
	 */
	public List<TeacherVo> queryWithRelevanceDetails(){
		return teacherMapper.queryDetails();
	}

	public PageInfo<Teacher> pageQuery(Integer pageNum,Integer pageSize) {
		//获取第1页，10条内容，默认查询总数count
	    PageHelper.startPage(pageNum, pageSize);

	    //紧跟着的第一个select方法会被分页
	    List<Teacher> list = teacherMapper.pageQuery();
	    return new PageInfo<Teacher>(list);
	}
	
	
	
}
