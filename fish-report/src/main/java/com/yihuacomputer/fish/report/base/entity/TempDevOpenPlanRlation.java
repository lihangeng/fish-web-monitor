package com.yihuacomputer.fish.report.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.openplan.ITempDevRelation;


@Entity
@Table(name="TEMP_DEV_OPENPLANRELATION")
public class TempDevOpenPlanRlation implements ITempDevRelation{
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_PLAN_RELATION")
	    @SequenceGenerator(name = "SEQ_DEV_PLAN_RELATION", sequenceName = "SEQ_DEV_PLAN_RELATION")
	    @Column(name = "ID")
	    private Long id;

	    @Column(name = "TEMP_DEVID")
	    private Long tempDeviceId;

	    @Column(name = "TEMP_PLAN_ID")
	    private Long tempPlanId;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getTempPlanId() {
			return tempPlanId;
		}

		public void setTempPlanId(Long tempPlanId) {
			this.tempPlanId = tempPlanId;
		}

		public Long getTempDeviceId() {
			return tempDeviceId;
		}

		public void setTempDeviceId(Long tempDeviceId) {
			this.tempDeviceId = tempDeviceId;
		}

		public static TempDevOpenPlanRlation make(Long planId, Long deviceId){
			TempDevOpenPlanRlation obj = new TempDevOpenPlanRlation();
			obj.setTempDeviceId(deviceId);
			obj.setTempPlanId(planId);
			return obj;
		}

}
