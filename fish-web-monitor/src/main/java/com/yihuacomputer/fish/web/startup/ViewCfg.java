package com.yihuacomputer.fish.web.startup;

import java.io.File;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.fish.api.version.VersionCfg;

public class ViewCfg extends FishCfg {

	public static void initDirs() {
		FishCfg.initDirs();
		File atmParamDir = new File(VersionCfg.getAtmParamDir());
		if (!atmParamDir.isDirectory()) {
			atmParamDir.mkdir();
		}

		File advertDir = new File(VersionCfg.getAdvertDir());
		if (!advertDir.isDirectory()) {
			advertDir.mkdir();
		}

	}
}
