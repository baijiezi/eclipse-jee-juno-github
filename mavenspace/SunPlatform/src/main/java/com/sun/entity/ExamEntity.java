package com.sun.entity;

/**
 * ����:caolei
 * ���ڣ�2014-09-08
 * ����:�ն˷����ƿ���¼��ϸʵ��
 * @author Administrator
 *
 */
public class ExamEntity {
	public int id;//����ID
	public String hospitalId;//ҽԺID
	public String hospitalName;//ҽԺ����
	public String patientName;//��������
	public String patientSex;//�����Ա�
	public String idCard;//�������֤���
	public String birthDay;//��������
	public String address;//��ַ
	public String mobile;//�绰
	public String medicalCardNo;//����
	public String password;//����
	public String operId;//�ն�ID
	public String createTime;//��������
	private String frontOrderNo;
	public String getFrontOrderNo() {
		return frontOrderNo;
	}
	public void setFrontOrderNo(String frontOrderNo) {
		this.frontOrderNo = frontOrderNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMedicalCardNo() {
		return medicalCardNo;
	}
	public void setMedicalCardNo(String medicalCardNo) {
		this.medicalCardNo = medicalCardNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
