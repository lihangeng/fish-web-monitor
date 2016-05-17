package com.yihuacomputer.fish.web.daily.form;
import com.yihuacomputer.fish.api.monitor.hardware.IHardware;
import com.yihuacomputer.fish.api.monitor.software.ISoftware;
import com.yihuacomputer.fish.web.atm.format.BiosMsg;
import com.yihuacomputer.fish.web.atm.format.CpuMsg;
import com.yihuacomputer.fish.web.atm.format.DiskMsg;
import com.yihuacomputer.fish.web.atm.format.HardwareMsg;
import com.yihuacomputer.fish.web.atm.format.MemoryMsg;
import com.yihuacomputer.fish.web.atm.format.SoftwareForm;

/**
 * 设备软件、硬件信息
 * 
 * @author dell
 * 
 */
public class SoftAndHardwareInfoForm
{
    private HardwareMsg hardware;

    private SoftwareForm software;

    public SoftAndHardwareInfoForm()
    {
    }

    public SoftAndHardwareInfoForm(IHardware hardware, ISoftware software)
    {
        toHardware(hardware);
        toSoftware(software);
    }

    /**
     * 将IHardware接口信息转换为HardwareMsg
     * 
     * @param hardware
     *            接口
     */
    public void toHardware(IHardware iHardware)
    {
        hardware = new HardwareMsg();

        // 取出bios信息
        hardware.setBios(iHardware.getBios() == null ? new BiosMsg()
                : new BiosMsg(iHardware.getBios()));

        // CpuMsg cpuMsg = new CpuMsg();
        // cpuMsg.setCacheSize(111L);
        // cpuMsg.setCombined("xxxx");
        // cpuMsg.setFrequency(111);
        // cpuMsg.setIdle("xxxx");
        // cpuMsg.setSys("xxxx");
        // cpuMsg.setTotalCores(12);
        // cpuMsg.setUser("xxxx");
        // cpuMsg.setVendor("xxxxx");
        //
        // List<CpuMsg> cpuMsgList = new ArrayList<CpuMsg>();
        // cpuMsgList.add(cpuMsg);
        // hardware.setCpu(cpuMsgList);

        // 取出cpu信息
        hardware.setCpu(iHardware.getCpu() == null ? null : CpuMsg
                .convert(iHardware.getCpu()));

        hardware.setDiskMem(iHardware.getDiskMem());

        // hardware.setFrimware(new FrimwareMsg());

        // DiskMsg diskMsg = new DiskMsg();
        // diskMsg.setFileSys("xxxx");
        // diskMsg.setFreeSize(111L);
        // diskMsg.setLabel("xxxx");
        // diskMsg.setLabelAndname("xxxx");
        // diskMsg.setMemo("xxxx");
        // diskMsg.setName("xxxx");
        // diskMsg.setTotalSize(12L);
        // List<DiskMsg> diskMsgList = new ArrayList<DiskMsg>();
        // diskMsgList.add(diskMsg);
        // hardware.setHardDisk(diskMsgList);
        // 取出磁盘信息
        hardware.setHardDisk(iHardware.getHardDisk() == null ? null : DiskMsg
                .convert(iHardware.getHardDisk()));

        hardware.setTermId(iHardware.getTerminalId());

        hardware.setMemory(iHardware.getMemory() == null ? new MemoryMsg()
                : new MemoryMsg(iHardware.getMemory()));
    }

    /**
     * 将ISoftware接口信息转换为SoftwareForm
     * 
     * @param software
     */
    public void toSoftware(ISoftware iSoftware)
    {
        software = new SoftwareForm();
        software.setAtmcVer(iSoftware.getAtmcVer());// os描述
        software.setChkCashData(iSoftware.getChkCashData());// 验钞数据版本

        if (iSoftware.getAnti() != null)
        {
            software.setAntiName(iSoftware.getAnti().getAntiName());// 病毒软件名称
            software.setAntiVer(iSoftware.getAnti().getAntiVer());// 病毒软件版本
        }

        if (iSoftware.getOS() != null)
        {
            software.setArch(iSoftware.getOS().getArch());// C端应用版本
            software.setDescription(iSoftware.getOS().getDescription());// 操作系统说明
            software.setPatchLevel(iSoftware.getOS().getPatchLevel()); // 操作系统补丁级别
            software.setType(iSoftware.getOS().getType());// 操作系统类型
            software.setVendor(iSoftware.getOS().getVendor());// 操作系统供应商
            software.setVendorName(iSoftware.getOS().getVendorName());// 供应商名称
            software.setVersion(iSoftware.getOS().getVersion());// 系统版本号
        }

        if (iSoftware.getSP() != null)
        {
            software.setSpDate(iSoftware.getSP().getSpDate()); // sp版本日期
            software.setSpPatch(iSoftware.getSP().getSpPatch());// sp补丁信息
            software.setSpVersion(iSoftware.getSP().getSpVersion());// sp版本号
        }
    }

    public HardwareMsg getHardware()
    {
        return hardware;
    }

    public void setHardware(HardwareMsg hardware)
    {
        this.hardware = hardware;
    }

    public SoftwareForm getSoftware()
    {
        return software;
    }

    public void setSoftware(SoftwareForm software)
    {
        this.software = software;
    }
}
