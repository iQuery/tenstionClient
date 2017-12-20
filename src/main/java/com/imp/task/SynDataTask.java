package com.imp.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dbquery.util.DatetimeUtil;
import com.imp.model.SynRecordModel;
import com.imp.service.SQLiteReportService;
import com.imp.utils.HttpHelper;
import com.imp.utils.HttpUtil;
import com.imp.utils.OApiException;

import java.io.*;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

public class SynDataTask extends TimerTask{
    static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SynDataTask.class);
    Properties prop = new Properties();
    InputStreamReader in = null;
    String url = null;
    String keyword = null;
    String bridgeName = null;
    @Override
    public void run() {
        try {

            in = new InputStreamReader(new FileInputStream("config.properties"), "UTF-8");
            prop.load(in);
            url  = prop.getProperty("webService.url").trim();
            keyword  = prop.getProperty("keyword").trim();
            bridgeName  = prop.getProperty("bridgeName").trim();
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
        SQLiteReportService sqLiteReportService = new SQLiteReportService();
        SynRecordModel model = new SynRecordModel();
        List<Object> objects = sqLiteReportService.selectByModel(model);
        if (objects != null && objects.size() > 0){
            for (Object object : objects){
                SynRecordModel recordModel = (SynRecordModel) object;
                try {
                    //tihuan keyword
                    JSONObject myJsonObject = JSONObject.parseObject(recordModel.getJson());
                    //梁号修改，数据重新发送
                    if(myJsonObject.get("orgName") != null && keyword != null && !myJsonObject.get("orgName").equals(keyword)){
                        myJsonObject.put("orgName",keyword);
                        recordModel.setJson(myJsonObject.toJSONString());
                        recordModel.setFlag(0);
                    }
                    //修改大桥名称，数据重新发送
                    if(myJsonObject.get("bridgeName") != null && bridgeName != null && !myJsonObject.get("bridgeName").equals(bridgeName)){
                        myJsonObject.put("bridgeName",bridgeName);
                        recordModel.setJson(myJsonObject.toJSONString());
                        recordModel.setFlag(0);
                    }

                    if (recordModel.getFlag() == 0){
                        String resultStr = HttpHelper.httpPost(url, recordModel.getJson());
                        recordModel.setId(recordModel.getId());
                        recordModel.setUpdateTime(DatetimeUtil.getCurDateString());
                        recordModel.setFlag(0);
                        if (resultStr != null){
                            JSONObject result = JSON.parseObject(resultStr);
                            String rst = result.getString("result");
                            String err = result.getString("message");
                            if (rst != null && rst.equals("success")){
                                logger.info("数据上传成功！ " + resultStr);
                                recordModel.setSynTime(DatetimeUtil.getCurDateString());
                                recordModel.setFlag(1);
                            }else{
                                logger.error("数据上传异常！ " + resultStr);
                            }
                            recordModel.setResult(resultStr);
                        }else{
                            logger.error("数据上传异常！ " + resultStr);
                        }
                        sqLiteReportService.updateById(recordModel);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
