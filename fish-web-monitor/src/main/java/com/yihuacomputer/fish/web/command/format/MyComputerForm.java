package com.yihuacomputer.fish.web.command.format;

import java.util.List;

/**
 * 远程浏览我的电脑的实体类.
 * @author huxiaobao
 *
 */
public class MyComputerForm
{
    private List<DiskForm> myComputerList;

    public List<DiskForm> getMyComputerList()
    {
        return myComputerList;
    }

    public void setMyComputerList(List<DiskForm> myComputerList)
    {
        this.myComputerList = myComputerList;
    }

   
    
}
