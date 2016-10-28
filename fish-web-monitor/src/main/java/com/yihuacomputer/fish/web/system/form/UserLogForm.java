package com.yihuacomputer.fish.web.system.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.person.IUserLog;

/**
 * 用户日志信息：
 * 
 * @author shixiaolong
 * 
 */
public class UserLogForm {
    private Long id;

    private String operContent;

    private String operTime;

    private String operCode;

    private String operName;

    private String operResult;
    
    private String serverIp;
    
    private String clientIP;
    
    private long times;

    public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public long getTimes() {
		return times;
	}

	public void setTimes(long times) {
		this.times = times;
	}

	public UserLogForm() {
    }

    public UserLogForm(IUserLog userLog) {
        this.id = userLog.getId();
        this.operContent = userLog.getOperContent();
        SimpleDateFormat timeFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        this.operTime = timeFormat.format(userLog.getOperTime());
        this.operCode = userLog.getOperCode();
        this.operName = userLog.getOperName();
        this.operResult = userLog.getOperResult();
        this.clientIP = userLog.getClientIP();
        this.serverIp = userLog.getServerIp();
        this.times = userLog.getTimes();

    }

    /**
     * 
     * 方法描述 : 转换
     * 
     * @param resources
     * @return List
     */

    public static List<UserLogForm> convert(List<IUserLog> resources) {
        List<UserLogForm> result = new ArrayList<UserLogForm>();
        for (IUserLog resource : resources) {
            result.add(new UserLogForm(resource));
        }
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperContent() {
        return this.operContent;
    }

    public void setOperContent(String operContent) {
        this.operContent = operContent;
    }

    public String getOperTime() {
        return this.operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public String getOperCode() {
        return this.operCode;
    }

    public void setOperCode(String operCode) {
        this.operCode = operCode;
    }

    public String getOperName() {
        return this.operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getOperResult() {
        return this.operResult;
    }

    public void setOperResult(String operResult) {
        this.operResult = operResult;
    }

}
