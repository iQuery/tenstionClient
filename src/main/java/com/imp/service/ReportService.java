package com.imp.service;

import com.dbquery.util.Model;
import com.imp.mapper.AbstractMapper;
import com.imp.model.CarModel;
import com.imp.model.ReportModel1;
import com.imp.model.ReportModel2;
import com.imp.model.TensionJsonModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ReportService extends AbstractMapper {
	
	public List<Object> selectByLastId(Integer lastId){
		String sql ="select a.*, b.[50A1压力] as 50A1油压,  b.[50A2压力] as 50A2油压, 0.1*[61张拉控制力] as 10张拉力, 0.2*[61张拉控制力] as 20张拉力, 0.5*[61张拉控制力] as 50张拉力, [61张拉控制力] as 100张拉力,'河南裕源达' as manufacturer from 张拉报表库 a ,张拉行程表 b where a.[梁型] = b.[梁号] and a.[ID] > ?";
		if (lastId == null){
			lastId = 0;
		}
		return selectBySql(sql,new Object[]{lastId});
	}

	public List<Object> selectBySql(String sql, Object[] objects){
		System.out.println(sql);
		System.out.println(objects);
		List<Map<String, Object>> maps = this.select(sql, objects);
		List<Object> models = new ArrayList<>();
		if (maps != null) {
			for (Map<String,Object> map : maps) {
				models.add((ReportModel1) Model.parseObject(map, ReportModel1.class));
				models.add((ReportModel2) Model.parseObject(map, ReportModel2.class));
			}
		}
		return models;
	}
	
	public List<CarModel> selectByModel(CarModel model){
		
		List<Object>  list = super.selectByModel(model);
		List<CarModel>  carModels = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for(Object object : list){
				carModels.add((CarModel) object);
			}
		}
		return carModels;
	}

}
