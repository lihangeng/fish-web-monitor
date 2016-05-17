package com.yihuacomputer.fish.web.daily.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.StopType;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNotice;

public class QuittingNoticeForm
{
    private long id;
    private String deviceCode;
    private Date stopTime;
    private Date openTime;
    private String stopType;
    private String stopReason; 
    private Date setTime;
    private String responsibilityName;
    private DevStatus devStatus;
    
    public QuittingNoticeForm(){};
    
    public QuittingNoticeForm(IQuittingNotice quittingNotice) {
        setId(quittingNotice.getId());
        setDeviceCode(quittingNotice.getDeviceCode());
        this.openTime = quittingNotice.getOpenTime();
        this.setTime = quittingNotice.getSetTime();
        this.stopTime = quittingNotice.getStopTime();
        setResponsibilityName(quittingNotice.getResponsibilityName());
        setStopReason(quittingNotice.getStopReason());
        setStopType(Integer.toString(quittingNotice.getStopType().getId()));
        setDevStatus(quittingNotice.getDevStatus());
    }
    
    public void translate(IQuittingNotice quittingNotice) {
        quittingNotice.setId(getId());
        quittingNotice.setDeviceCode(getDeviceCode());
        quittingNotice.setOpenTime(this.openTime);
        quittingNotice.setResponsiblilityName(getResponsibilityName());
        quittingNotice.setSetTime(this.setTime);
        quittingNotice.setStopReason(getStopReason());
        quittingNotice.setStopTime(this.stopTime);
        quittingNotice.setStopType(StopType.getById((quittingNotice.getStopType().getId())));
    }

    public static List<QuittingNoticeForm> convert(List<IQuittingNotice> list) {
        List<QuittingNoticeForm> result = new ArrayList<QuittingNoticeForm>();
        for(IQuittingNotice item : list) {
            result.add(new QuittingNoticeForm(item));
        }
        return result;
    }
    

    public DevStatus getDevStatus() {
		return devStatus;
	}

	public void setDevStatus(DevStatus devStatus) {
		this.devStatus = devStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id)
    {
        this.id = id;
    }
    public String getDeviceCode()
    {
        return deviceCode;
    }
    public void setDeviceCode(String deviceCode)
    {
        this.deviceCode = deviceCode;
    }
     public String getStopType()
    {
        return stopType;
    }
    public void setStopType(String stopType)
    {
        this.stopType = stopType;
    }
    public String getStopReason()
    {
        return stopReason;
    }
    public void setStopReason(String stopReason)
    {
        this.stopReason = stopReason;
    }
    public String getResponsibilityName()
    {
        return responsibilityName;
    }
    public void setResponsibilityName(String responsibilityName)
    {
        this.responsibilityName = responsibilityName;
    }

    public Date getStopTime()
    {
        return stopTime;
    }

    public void setStopTime(Date stopTime)
    {
        this.stopTime = stopTime;
    }

    public Date getOpenTime()
    {
        return openTime;
    }

    public void setOpenTime(Date openTime)
    {
        this.openTime = openTime;
    }

    public Date getSetTime()
    {
        return setTime;
    }

    public void setSetTime(Date setTime)
    {
        this.setTime = setTime;
    }
    
    
    
}
