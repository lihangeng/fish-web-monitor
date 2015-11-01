package com.yihuacomputer.fish.api.device;

import java.util.Date;

public interface ITempDevInfo extends IDevice{

	//设备生效日期
		public Date getEffectiveDate();

		public void setEffectiveDate(Date effectiveDate);	
		
		public long getDeviceId(); 
		
		public void setDeviceId(long deviceId);
		
		
}
