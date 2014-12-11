package com.sun309.frontend.db.model;

import java.util.Date;
import java.util.Map;

/**
 * 作用:用户评价实体
 * 日期:2014-10-11
 * 作者:caolei
 * @author Administrator
 *
 */
public class FrontendMannerModel {
	private Integer id;	//主键ID
	private String userName;//用户名称
	private String medicalCardNo;//诊疗卡号
	private Integer mannerDoctId;//医生态度ID
	private String mannerDoctRemark;//医生态度备注
	private Integer mannerNursetId;//护士态度ID
	private String mannerNursetRemark;//护士态度备注
	private Integer mannerCheckId;//检查态度ID
	private String mannerCheckRemark;//检查态度备注
	private Integer mannerPhamacyId;//药房态度ID
	private String mannerPhamacyRemark;//药房态度备注
	private Integer mannerChargeId;//收费态度ID
	private String mannerChargeRemark;//收费态度备注
	private Integer mannerVisitId;//环境评价ID
	private String mannerVisitRemark;//环境评价备注
	private Date mannerTime;//评价日期
	private String mobile;//用户手机号码
	private Integer isSyn;//数据同步状态
	public Integer getIsSyn() {
		return isSyn;
	}
	public void setIsSyn(Integer isSyn) {
		this.isSyn = isSyn;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	private String patientName;//患者姓名
	
	public FrontendMannerModel(){}
	public FrontendMannerModel(Map<String, Object> map){
		this.setUserName(map.get("userName").toString());
		this.setMedicalCardNo(map.get("medicalCardNo").toString());
		this.setMannerDoctId(Integer.parseInt(map.get("mannerDoctId").toString()));
		this.setMannerDoctRemark(map.get("mannerDoctRemark").toString());
		this.setMannerNursetId(Integer.parseInt(map.get("mannerNursetId").toString()));
		this.setMannerNursetRemark(map.get("mannerNursetRemark").toString());
		this.setMannerChargeId(Integer.parseInt(map.get("mannerCheckId").toString()));
		this.setMannerCheckRemark(map.get("mannerCheckRemark").toString());
		this.setMannerPhamacyRemark(map.get("mannerPhamacyRemark").toString());
		this.setMannerChargeId(Integer.parseInt(map.get("mannerChargeId").toString()));
		this.setMannerChargeRemark(map.get("mannerChargeRemark").toString());
		this.setMannerVisitId(Integer.parseInt(map.get("mannerVisitId").toString()));
		this.setMannerVisitRemark(map.get("mannerVisitRemark").toString());
		//map.get("mannerTime");
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMedicalCardNo() {
		return medicalCardNo;
	}
	public void setMedicalCardNo(String medicalCardNo) {
		this.medicalCardNo = medicalCardNo;
	}
	public Integer getMannerDoctId() {
		return mannerDoctId;
	}
	public void setMannerDoctId(Integer mannerDoctId) {
		this.mannerDoctId = mannerDoctId;
	}
	public String getMannerDoctRemark() {
		return mannerDoctRemark;
	}
	public void setMannerDoctRemark(String mannerDoctRemark) {
		this.mannerDoctRemark = mannerDoctRemark;
	}
	public Integer getMannerNursetId() {
		return mannerNursetId;
	}
	public void setMannerNursetId(Integer mannerNursetId) {
		this.mannerNursetId = mannerNursetId;
	}
	public String getMannerNursetRemark() {
		return mannerNursetRemark;
	}
	public void setMannerNursetRemark(String mannerNursetRemark) {
		this.mannerNursetRemark = mannerNursetRemark;
	}
	public Integer getMannerCheckId() {
		return mannerCheckId;
	}
	public void setMannerCheckId(Integer mannerCheckId) {
		this.mannerCheckId = mannerCheckId;
	}
	public String getMannerCheckRemark() {
		return mannerCheckRemark;
	}
	public void setMannerCheckRemark(String mannerCheckRemark) {
		this.mannerCheckRemark = mannerCheckRemark;
	}
	public Integer getMannerPhamacyId() {
		return mannerPhamacyId;
	}
	public void setMannerPhamacyId(Integer mannerPhamacyId) {
		this.mannerPhamacyId = mannerPhamacyId;
	}
	public String getMannerPhamacyRemark() {
		return mannerPhamacyRemark;
	}
	public void setMannerPhamacyRemark(String mannerPhamacyRemark) {
		this.mannerPhamacyRemark = mannerPhamacyRemark;
	}
	public Integer getMannerChargeId() {
		return mannerChargeId;
	}
	public void setMannerChargeId(Integer mannerChargeId) {
		this.mannerChargeId = mannerChargeId;
	}
	public String getMannerChargeRemark() {
		return mannerChargeRemark;
	}
	public void setMannerChargeRemark(String mannerChargeRemark) {
		this.mannerChargeRemark = mannerChargeRemark;
	}
	public Integer getMannerVisitId() {
		return mannerVisitId;
	}
	public void setMannerVisitId(Integer mannerVisitId) {
		this.mannerVisitId = mannerVisitId;
	}
	public String getMannerVisitRemark() {
		return mannerVisitRemark;
	}
	public void setMannerVisitRemark(String mannerVisitRemark) {
		this.mannerVisitRemark = mannerVisitRemark;
	}
	public Date getMannerTime() {
		return mannerTime;
	}
	public void setMannerTime(Date mannerTime) {
		this.mannerTime = mannerTime;
	}
}
