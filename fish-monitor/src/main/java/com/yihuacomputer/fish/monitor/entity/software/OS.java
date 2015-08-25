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

	public void setOsPath(String osPath) {
		this.osPath = osPath;
	}

	public String getOsPath() {
		return this.osPath;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPatchLevel(String patchLevel) {
		this.patchLevel = patchLevel;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getArch() {
		return this.arch;
	}

	public String getDescription() {
		return this.description;
	}

	public String getType() {
		return this.type;
	}

	public String getPatchLevel() {
		return this.patchLevel;
	}

	public String getVendor() {
		return this.vendor;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public String getVersion() {
		return this.version;
	}

}
