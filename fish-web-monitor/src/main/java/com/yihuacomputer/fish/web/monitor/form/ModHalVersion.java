package com.yihuacomputer.fish.web.monitor.form;
import java.util.List;

public class ModHalVersion {
    // /**
    // * 现金模块
    // */
    // private HalVersion cashVersion;
    //
    // /**
    // * 键盘模块
    // */
    // private HalVersion padVersion;
    //
    // /**
    // * 打印机模块
    // */
    // private HalVersion printerVersion;
    //
    // /**
    // * 读卡器模块
    // */
    // private HalVersion readerVersion;

    private List<HalVersion> listHal;

    public List<HalVersion> getListHal() {
        return listHal;
    }

    public void setListHal(List<HalVersion> listHal) {
        this.listHal = listHal;
    }
}
