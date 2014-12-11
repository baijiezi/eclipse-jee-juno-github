package com.sun.entity;

/**
 * 作用:药品查询实体
 * 日期:2014-10-22
 * 作者:caolei
 * @author Administrator
 *
 */
public class DrugsEntity {
	public int id; //主键ID
	public String medicineCode;//药品编号
	public String medicineName;//药品名称
	public String specifiCation;//药品规格
	public String outpatienTunit;//药品单位
	public String price;//药品价格
	public String pfPrice;//批发价
	public String medicineType;//药品类型
	public String state;//药品状态
	public String ybType;//医保类型
	public String wbCode;//五笔编码
	public String pyCode;//拼音编码
	public String createTime;//创建日期
	public String typeCode;//类别ID
	public String hospitalName;//医院名称
	public String name;//名称
	public String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMedicineCode() {
		return medicineCode;
	}
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getSpecifiCation() {
		return specifiCation;
	}
	public void setSpecifiCation(String specifiCation) {
		this.specifiCation = specifiCation;
	}
	public String getOutpatienTunit() {
		return outpatienTunit;
	}
	public void setOutpatienTunit(String outpatienTunit) {
		this.outpatienTunit = outpatienTunit;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPfPrice() {
		return pfPrice;
	}
	public void setPfPrice(String pfPrice) {
		this.pfPrice = pfPrice;
	}
	public String getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getYbType() {
		return ybType;
	}
	public void setYbType(String ybType) {
		this.ybType = ybType;
	}
	public String getWbCode() {
		return wbCode;
	}
	public void setWbCode(String wbCode) {
		this.wbCode = wbCode;
	}
	public String getPyCode() {
		return pyCode;
	}
	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
}
