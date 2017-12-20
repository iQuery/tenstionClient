package com.imp.task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class MyTimerTask {
	InputStreamReader in = null;
	Properties prop = new Properties();
	private Integer interval = 60 * 5;
	private ScheduledExecutorService newScheduledThreadPool;

	private static MyTimerTask myTimerTask = null;
	
	public static MyTimerTask getInstance(){
		if (myTimerTask == null) {
			myTimerTask = new MyTimerTask();
			
		}
		return myTimerTask;
	}
	
	public MyTimerTask() {
		try {
			in = new InputStreamReader(new FileInputStream("config.properties"), "UTF-8");
			prop.load(in);
			interval  = Integer.parseInt(prop.getProperty("interval").trim().replaceAll("\r|\n", ""));
			if (interval < 6){
				interval = 5;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SynDataTask synDataTask = new SynDataTask();
		GetDataTask getDataTask = new GetDataTask();
		newScheduledThreadPool = Executors.newScheduledThreadPool(2);
		newScheduledThreadPool.scheduleAtFixedRate(synDataTask,interval * 500, interval * 1000, TimeUnit.MILLISECONDS);
		newScheduledThreadPool.scheduleAtFixedRate(getDataTask,3000, interval * 1000, TimeUnit.MILLISECONDS);
	}

}
