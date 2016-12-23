/**
 *
 */
package com.yihuacomputer.fish.api.advert;

/**
 * 广告包生成器
 * @author xuxiang
 *
 */
public interface IAdvertZipGenerator {
	/**
	 * 根据广告生成广告压缩包
	 * @param advert
	 * @return
	 */
	public void generateZipFile(IAdvert advert);
}
