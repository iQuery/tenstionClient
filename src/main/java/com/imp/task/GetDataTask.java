package com.imp.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imp.model.ReportModel1;
import com.imp.model.SynRecordModel;
import com.imp.model.TensionDetailJsonModel;
import com.imp.model.TensionJsonModel;
import com.imp.service.ReportService;
import com.imp.service.SQLiteReportService;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class GetDataTask extends TimerTask{
    Properties prop = new Properties();
    InputStreamReader in = null;
    private String machineId;
    private String keyword;
    @Override
    public void run() {
        try {
            System.out.println("同步");
            in = new InputStreamReader(new FileInputStream("config.properties"), "UTF-8");
            prop.load(in);
            machineId  = prop.getProperty("machineId").trim();
            keyword = prop.getProperty("keyword").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }



        ReportService reportService = new ReportService();
        SQLiteReportService sqLiteReportService = new SQLiteReportService();
        List<SynRecordModel> recordModelList = sqLiteReportService.selectLastRecord();
        Integer lastId = 0;
        if (recordModelList != null && recordModelList.size() > 0){
            lastId = recordModelList.get(0).getMaxTensionId();
        }
        List<Object> reportModelList = reportService.selectByLastId(lastId);
        Map<String, TensionJsonModel> resultMap = deal(reportModelList);
        if (resultMap != null){
            for (String key: resultMap.keySet()){
                TensionJsonModel tensionJsonModel = resultMap.get(key);
                SynRecordModel synRecordModel = new SynRecordModel();
                synRecordModel.setMaxTensionId(tensionJsonModel.getId());
                tensionJsonModel.setId(null);
                synRecordModel.setBeanNO(tensionJsonModel.getBeanNO());
                synRecordModel.setJson(JSONObject.toJSONString(tensionJsonModel));
                sqLiteReportService.saveOrUpdate(synRecordModel);
            }
        }

    }

    private Map<String, TensionJsonModel> deal(List<Object> reportModelList){
        if (reportModelList == null || reportModelList.isEmpty()){
            return null;
        }
        Map<String, TensionJsonModel> map = new HashMap<>();
        TensionJsonModel tensionJsonModel = null;

        for (Object object : reportModelList){
            ReportModel1 reportModel = JSON.parseObject(JSONObject.toJSONString(object), ReportModel1.class);
            if(reportModel.getJackNo() == null || reportModel.getJackNo().equals("")){
                //过滤掉未知钢束
                continue;
            }
            try {
                reportModel.setExtenError(String.valueOf(Float.parseFloat(reportModel.getExtenError())/100));
            }catch (Exception e){
                reportModel.setExtenError("0");
            }
            //beanNO梁号
            String key = reportModel.getBeanNO();
            if (!map.containsKey(key)){
                tensionJsonModel = reportModel;
                tensionJsonModel.setOrgName(keyword);
                tensionJsonModel.setMachineId(machineId);
                tensionJsonModel.setTensionDetailList(new ArrayList<TensionDetailJsonModel>());
                map.put(key, tensionJsonModel);

            }else{
                tensionJsonModel = map.get(key);
                if (reportModel.getId() > map.get(key).getId()){
                    map.get(key).setId(reportModel.getId());
                }
            }
            tensionJsonModel.getTensionDetailList().add(report2Detail(reportModel));
        }
        if (tensionJsonModel != null){
            return map;
        }
        return null;
    }
    private TensionDetailJsonModel report2Detail(ReportModel1 reportModel){
        if (reportModel == null){
            return null;
        }
        Field[] fields = reportModel.getClass().getDeclaredFields();
        TensionDetailJsonModel  tensionDetailJsonModel = new TensionDetailJsonModel();
        Field[] detailFields = TensionDetailJsonModel.class.getDeclaredFields();
        for (Field detailField : detailFields){
            for (Field field : fields){
                if (detailField.getName().equals(field.getName())){
                    try {
                        field.setAccessible(true);
                        detailField.setAccessible(true);
                        detailField.set(tensionDetailJsonModel,field.get(reportModel));
                        continue;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return tensionDetailJsonModel;
    }



}
