package com.yihuacomputer.fish.monitor.entity.software;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.yihuacomputer.fish.api.monitor.software.IOS;

/**
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2011-12-26 上午10:30:21
 * @version 操作系统
 */
@Embeddable
public class OS implements IOS {
	
	@Column(name = "OS_ARCH",length=20)
	private String arch;
	
	@Column(name = "OS_DESCRIPTION",length=30)
	private String description;
	
	@Column(name = "OS_TYPE",length=15)
	private String type;
	
	@Column(name = "SYS_PATCH_LEVEL",length=20)
	private String patchLevel;
	
	@Column(name = "OS_VENDOR",length=30)
	private String vendor;
	
	@Column(name = "OS_VENDOR_NAME",length=50)
	private String vendorName;
	
	@Column(name = "SYS_VERSION",length=30)
	private String version;
	
	@Column(name = "SYS_PATCH_VERSION",length=30)
	private String osPath;

	@Override
	public void setOsPath(String osPath) {
		this.osPath = osPath;
	}

	@Override
	public String getOsPath() {
		return this.osPath;
	}

	@Override
	public void setArch(String arch) {
		this.arch = arch;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void setPatchLevel(String patchLevel) {
		this.patchLevel = patchLevel;
	}

	@Override
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	@Override
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String getArch() {
		return this.arch;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getPatchLevel() {
		return this.patchLevel;
	}

	@Override
	public String getVendor() {
		return this.vendor;
	}

	@Override
	public String getVendorName() {
		return this.vendorName;
	}

	@Override
	public String getVersion() {
		return this.version;
	}

}
