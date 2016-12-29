package com.yihuacomputer.fish.web.monitor.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.business.IHostRet;

/**
 * @author YiHua
 *
 */
public class HostRetForm {

    private long id;

    private String hostRet;

    private String name;


    public HostRetForm(){

    }

    /**
     * @param hostRet
     */
    public HostRetForm(IHostRet hostRet){
        this.id = hostRet.getId();
        this.hostRet = hostRet.getCode();
        this.name = hostRet.getName();
    }

    /**
     * @param list
     * @return
     */
    public static List<HostRetForm> convert(List<IHostRet> list) {
        List<HostRetForm> result = new ArrayList<HostRetForm>();
        for (IHostRet item : list) {
            result.add(new HostRetForm(item));
        }
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHostRet() {
        return hostRet;
    }

    public void setHostRet(String hostRet) {
        this.hostRet = hostRet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
