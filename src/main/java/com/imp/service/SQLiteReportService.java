package com.imp.service;

import com.dbquery.util.DatetimeUtil;
import com.dbquery.util.Model;
import com.imp.mapper.SQLiteAbstractMapper;
import com.imp.model.SynRecordModel;

import java.util.*;


public class SQLiteReportService extends SQLiteAbstractMapper {
	
	public List<SynRecordModel> selectByProjectId(Integer projectId){
		String sql ="select * from tbl_syn_record";
		return selectBySql(sql,new Object[]{});
	}

	public List<SynRecordModel> selectBySql(String sql, Object[] objects){
		System.out.println(sql);
		System.out.println(objects);
		List<Map<String, Object>> maps = this.select(sql, objects);
		List<SynRecordModel> models = new ArrayList<>();
		if (maps != null) {
			for (Map<String,Object> map : maps) {
				models.add((SynRecordModel) Model.parseObject(map, SynRecordModel.class));
			}
		}
		return models;
	}

	public List<SynRecordModel> selectLastRecord(){
		String sql ="select * from tbl_syn_record order by maxTensionId desc LIMIT 1";
		return selectBySql(sql,new Object[]{});
	}

	public boolean saveOrUpdate(SynRecordModel synRecordModel){
		SynRecordModel synModel = new SynRecordModel();
		synModel.setBeanNO(synRecordModel.getBeanNO());
		Set<String> set = new HashSet<>();
		set.add("beanNO");
		List<Object> list = selectByModel(synModel);
		if (list != null && list.size() > 0){
			synModel = (SynRecordModel)list.get(0);
			synModel.setUpdateTime(DatetimeUtil.getCurDateString());
			synModel.setJson(synRecordModel.getJson());
			synModel.setFlag(0);
			super.updateById(synModel);
		}else{
			String timetemp = DatetimeUtil.getCurDateString();
			synRecordModel.setUpdateTime(timetemp);
			synRecordModel.setCreateTime(timetemp);
			synRecordModel.setFlag(0);
			super.insertModel(synRecordModel);
		}
		return true;
	}
	


}
