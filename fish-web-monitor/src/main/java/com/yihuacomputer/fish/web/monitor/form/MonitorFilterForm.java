package com.yihuacomputer.fish.web.monitor.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmGroup;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;
import com.yihuacomputer.fish.api.person.IOrganization;

public class MonitorFilterForm {

    /**
     * ID
     */
    public long id;

    /**
     *订阅条件名称 
     */
    public String filterName;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 机构ID
     */
    private String orgId;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 品牌
     */
    private String brandItem;

    /**
     * 品牌名称
     */
    private String brandItemName;

    /**
     * 型号
     */
    private String classifyItem;

    /**
     * 型号名称
     */
    private String classifyItemName;

    /**
     * 在行离行标志
     */
    private String ingItem;

    /**
     * 经营方式
     */
    private String sellItem;

    /**
     * 分组
     */
    private String atmGroup;

    /**
     * 分组名称
     */
    private String atmGroupName;

    /**
     * 运行状态条件
     */
    private RunStatusFilterForm runStatusFilterForm;

    /**
     * 网络状态条件
     */
    private NetStatusFilterForm netStatusFilterForm;

    /**
     * 模块状态条件
     */
    private ModStatusFilterForm modStatusFilterForm;

    /**
     * 钞箱状态条件
     */
    private BoxStatusFilterForm boxStatusFilterForm;

    public MonitorFilterForm() {
    }

    /**
     * @param statusFilter
     */
    public MonitorFilterForm(IStatusFilter statusFilter) {
        this.userId = statusFilter.getUserId();
        this.orgId = statusFilter.getOrgId();
        this.brandItem = String.valueOf(statusFilter.getDevVendor());
        this.classifyItem = String.valueOf(statusFilter.getDevType());
        this.ingItem = String.valueOf(statusFilter.getAwayFlag());
        this.sellItem = String.valueOf(statusFilter.getWorkType());
        this.atmGroup = String.valueOf(statusFilter.getAtmGroup());

        this.id = statusFilter.getId();
        this.filterName = statusFilter.getFilterName();

        runStatusFilterForm = new RunStatusFilterForm(statusFilter.getRunStatusFilter());
        netStatusFilterForm = new NetStatusFilterForm(statusFilter.getNetStatusFilter());
        modStatusFilterForm = new ModStatusFilterForm(statusFilter.getModStatusFilter());
        boxStatusFilterForm = new BoxStatusFilterForm(statusFilter.getBoxStatusFilter());

    }

    /**
     * @param statusFilter
     * @param org
     * @param type
     * @param group
     */
    public MonitorFilterForm(IStatusFilter statusFilter, IOrganization org, IAtmType type, IAtmGroup group) {
        this.userId = statusFilter.getUserId();
        this.orgId = statusFilter.getOrgId();
        this.brandItem = String.valueOf(statusFilter.getDevVendor());
        this.classifyItem = String.valueOf(statusFilter.getDevType());
        this.ingItem = String.valueOf(statusFilter.getAwayFlag());
        this.sellItem = String.valueOf(statusFilter.getWorkType());
        this.atmGroup = String.valueOf(statusFilter.getAtmGroup());

        this.id = statusFilter.getId();
        this.filterName = statusFilter.getFilterName();

        runStatusFilterForm = new RunStatusFilterForm(statusFilter.getRunStatusFilter());
        netStatusFilterForm = new NetStatusFilterForm(statusFilter.getNetStatusFilter());
        modStatusFilterForm = new ModStatusFilterForm(statusFilter.getModStatusFilter());
        boxStatusFilterForm = new BoxStatusFilterForm(statusFilter.getBoxStatusFilter());
        
        
        

    }

    /**
     * @param list
     * @return
     */
    public static List<MonitorFilterForm> convert(List<IStatusFilter> list) {
        List<MonitorFilterForm> result = new ArrayList<MonitorFilterForm>();
        for (IStatusFilter filter : list) {
            result.add(new MonitorFilterForm(filter));
        }
        return result;
    }

    /**
     * @param list
     * @return
     */
    public static List<MonitorFilterForm> convertObj(List<Object> list) {
        List<MonitorFilterForm> result = new ArrayList<MonitorFilterForm>();
        for (Object obj : list) {

            Object[] objs = (Object[]) obj;

            IStatusFilter statusFilter = (IStatusFilter) objs[0];
            IOrganization organization = (IOrganization) objs[1];
            IAtmType type = (IAtmType) objs[2];
            IAtmGroup group = (IAtmGroup) objs[3];

            result.add(new MonitorFilterForm(statusFilter, organization, type, group));
        }
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getBrandItem() {
        return brandItem;
    }

    public void setBrandItem(String brandItem) {
        this.brandItem = brandItem;
    }

    public String getBrandItemName() {
        return brandItemName;
    }

    public void setBrandItemName(String brandItemName) {
        this.brandItemName = brandItemName;
    }

    public String getClassifyItem() {
        return classifyItem;
    }

    public void setClassifyItem(String classifyItem) {
        this.classifyItem = classifyItem;
    }

    public String getClassifyItemName() {
        return classifyItemName;
    }

    public void setClassifyItemName(String classifyItemName) {
        this.classifyItemName = classifyItemName;
    }

    public String getIngItem() {
        return ingItem;
    }

    public void setIngItem(String ingItem) {
        this.ingItem = ingItem;
    }

    public String getSellItem() {
        return sellItem;
    }

    public void setSellItem(String sellItem) {
        this.sellItem = sellItem;
    }

    public String getAtmGroup() {
        return atmGroup;
    }

    public void setAtmGroup(String atmGroup) {
        this.atmGroup = atmGroup;
    }

    public String getAtmGroupName() {
        return atmGroupName;
    }

    public void setAtmGroupName(String atmGroupName) {
        this.atmGroupName = atmGroupName;
    }

    public RunStatusFilterForm getRunStatusFilterForm() {
        return runStatusFilterForm;
    }

    public void setRunStatusFilterForm(RunStatusFilterForm runStatusFilterForm) {
        this.runStatusFilterForm = runStatusFilterForm;
    }

    public NetStatusFilterForm getNetStatusFilterForm() {
        return netStatusFilterForm;
    }

    public void setNetStatusFilterForm(NetStatusFilterForm netStatusFilterForm) {
        this.netStatusFilterForm = netStatusFilterForm;
    }

    public ModStatusFilterForm getModStatusFilterForm() {
        return modStatusFilterForm;
    }

    public void setModStatusFilterForm(ModStatusFilterForm modStatusFilterForm) {
        this.modStatusFilterForm = modStatusFilterForm;
    }

    public BoxStatusFilterForm getBoxStatusFilterForm() {
        return boxStatusFilterForm;
    }

    public void setBoxStatusFilterForm(BoxStatusFilterForm boxStatusFilterForm) {
        this.boxStatusFilterForm = boxStatusFilterForm;
    }
}
