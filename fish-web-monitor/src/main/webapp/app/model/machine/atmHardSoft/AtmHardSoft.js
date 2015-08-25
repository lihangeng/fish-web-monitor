Ext.define('Eway.model.machine.atmHardSoft.AtmHardSoft', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'termId',
		mapping : 'hardware.termId'
	}, {
		name : 'diskMem',
		mapping : 'hardware.diskMem'
	}, {
		name : 'biosVersion',
		mapping : 'hardware.bios.biosVersion'
	}, {
		name : 'biosVendor',
		mapping : 'hardware.bios.biosVendor'
	}, {
		name : 'biosReleaseDate',
		mapping : 'hardware.bios.biosReleaseDate'
	}, {
		name : 'memorySize',
		mapping : 'hardware.memory.memorySize'
	}, {
		name : 'used',
		mapping : 'hardware.memory.used'
	}, {
		name : 'free',
		mapping : 'hardware.memory.free'
	}, {
		name : 'usedPercent',
		mapping : 'hardware.memory.usedPercent'
	}, {
		name : 'cpu',
		mapping : 'hardware.cpu'
	}, {
		name : 'hardDisk',
		mapping : 'hardware.hardDisk'
	}, {
		name : 'arch',
		mapping : 'software.arch'
	}, {
		name : 'description',
		mapping : 'software.description'
	}, {
		name : 'type',
		mapping : 'software.type'
	}, {
		name : 'patchLevel',
		mapping : 'software.patchLevel'
	}, {
		name : 'vendor',
		mapping : 'software.vendor'
	}, {
		name : 'vendorName',
		mapping : 'software.vendorName'
	}, {
		name : 'version',
		mapping : 'software.version'
	}, {
		name : 'antiName',
		mapping : 'software.antiName'
	}, {
		name : 'antiVer',
		mapping : 'software.antiVer'
	}, {
		name : 'atmcVer',
		mapping : 'software.atmcVer'
	}, {
		name : 'chkCashData',
		mapping : 'software.chkCashData'
	}, {
		name : 'spVersion',
		mapping : 'software.spVersion'
	}, {
		name : 'spPatch',
		mapping : 'software.spPatch'
	}, {
		name : 'spDate',
		mapping : 'software.spDate'
	} ],
	proxy : {
		type : 'rest',
		url : 'api/machine/device/softhardware/info',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});
