/**
 *
 */
package com.yihuacomputer.fish.api.version;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.version.job.task.ITask;

/**
 * 版本相关操作的服务
 *
 * @author xuxigang
 *
 */
public interface IVersionService {
    public IVersion make();

    public IVersion getById(long id);

    public IVersion add(IVersion version);

    /**
     * 如果更新之前，压缩包有调整，需要自行修改压缩包的MD5值
     */
    public void update(IVersion version);

    public void delete(IVersion version);

    public void delete(long id);

    public List<IVersion> list(IFilter filter);

    public IPageResult<IVersion> page(int offset, int limit, IFilter filter);

    /**
     * 分页显示卡表信息
     *
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IVersion> pageCardTable(int offset, int limit, IFilter filter);

    /**
     * 收集设备当前最新版本信息
     *
     * @param termialId
     * @param versionNo
     * @param versionName
     */
    public void collectCurrentVersionInfo(String termialId, String versionNo, String versionName);

    /**
     * 收集版本下发更新报告结果
     *
     * @param taskId
     *            任务编号
     * @param ret
     *            下发结果
     */
    public void collectUpdateReport(long taskId, String ret, String ip);

    /**
     * 计算需要自动下发的版本
     *
     * @param typeName
     *            版本类型
     * @param versionNo
     *            版本号
     * @return
     */
    public IVersion autoUpdate(String typeName, String versionNo);

    /**
     * 得到下一个版本号
     *
     * @return
     */
    public String getNextVersionNo(long versionTypeId);

    /**
     * 由版本类型和版本号唯一确定一个版本信息
     *
     * @param typeName
     * @param versionNo
     * @return 没有找到返回NotFoundException异常
     */
    public IVersion getVersion(String typeName, String versionNo);

    /**
     * 由版本类型和版本号唯一确定一个版本信息
     *
     * @param typeName
     * @param versionNo
     * @return 没有找到返回返回null
     */
    public IVersion findVersion(String typeName, String versionNo);

    /**
     * 根据版本类型IDversionType[long]获取版本分布信息
     * 
     * @param filter
     * @return
     */
    public Map<Long, VersionDistribute> getVersionDistribute(IFilter filter);

    /**
     * 根据版本号，查看当前版本下发状态情况
     * 
     * @param filter
     * @return
     */
    public List<VersionStatusDistribute> getVersionStatusDistribute(IFilter filter);

    /**
     * 查看某个版本的历史状态详情
     * 
     * @param start
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<VersionDistributeDetail> getVersionStatusDistributeDetail(int start, int limit, IFilter filter);

    /**
     * 更新版本下发次数
     * 
     * @param versionId
     * @return
     */
    public IVersion updateDownLoadCounter(IVersion version);

    /**
     * 收集版本下发后的更新报告结果
     * 
     * @param taskId
     * @param ret
     * @param size
     * @return
     */
    public ITask collectUpdateReport(long taskId, String ret, double size, String startTime, String endTime);
}
