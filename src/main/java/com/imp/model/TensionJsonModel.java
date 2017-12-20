package com.imp.model;

import com.imp.system.Column;
import com.imp.system.Table;

import java.math.BigDecimal;
import java.util.List;
@Table("张拉报表库")
public class TensionJsonModel {
	
    private Integer count;                  //
	@Column("ID")
    private Integer id;                     //ID                              
    private  Integer orgId;                 //标段ID                            
    private  String orgName;                //标段名称                            
    private  String machineId;              //设备ID
	@Column("manufacturer")
	private String manufacturer = "";           //设备厂家  ---河南省裕源达预应力设备有限公司
	@Column("27编号")
	private String tensionNo = "";              //张拉编号
	private String bridgeName = "";             //桥梁名称
	@Column("梁型")
	private String beamType = "1";               //梁型
	@Column("梁号")
	private String beanNO = "";                 //梁号、构件编号
	@Column("8浇筑日期")
	private String pouringTime = "";            //浇筑时间
	@Column("1张拉时间")
	private String tensionTime = "";            //张拉时间
	private BigDecimal designStrength = BigDecimal.ZERO;     //砼设计张拉强度(Mpa)
	private BigDecimal strength = BigDecimal.ZERO;           //砼实测强度(Mpa)
	@Column("10钢绞线规格")
	private String strandNorm = "";            //钢绞线规格
	private  BigDecimal strandDiameter;     //钢绞线直径(mm)
	private String elasticModulus = "0";       //钢绞线弹性模量
	private BigDecimal tensileStrength = BigDecimal.ZERO;    //钢绞线抗拉强度(MPa)

	private  String jackType;               //千斤顶型号
    private  String mainPump = "1";               //主泵站编号
    private  String pump = "2";                   //副泵站编号
    private  BigDecimal holdTime;           //中间持荷时间(S)                       
    private  BigDecimal finalHoldTime;      //终点持荷时间(S)                       
    private  BigDecimal retraction;         //力筋回缩量设定值Ns(mm)
	@Column("55张拉类型")
    private  String tensionType;            //张拉方式                            
    private  Long longarch;                     //实测起拱度(mm)
	@Column("56回缩值")
    private  BigDecimal singleRet;          //单端锚固回缩量                         
	private String tenTemp = "0";                //张拉时温度
	private  String anchorage;              //锚具类别
    private  String data1;              //备用字段 1           
    private  String data2;              //备用字段 2           
    private  String data3;              //备用字段3            
    private  String data4;              //备用字段4            
    private  String data5;              //备用字段5            
    
    private  String steel;                  //钢束类别
    
    private List<TensionDetailJsonModel> tensionDetailList;
    
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getTensionNo() {
		return tensionNo;
	}

	public void setTensionNo(String tensionNo) {
		this.tensionNo = tensionNo;
	}

	public String getBridgeName() {
		return bridgeName;
	}

	public void setBridgeName(String bridgeName) {
		this.bridgeName = bridgeName;
	}

	public String getBeamType() {
		return beamType;
	}

	public void setBeamType(String beamType) {
		this.beamType = beamType;
	}

	public String getBeanNO() {
		return beanNO;
	}

	public void setBeanNO(String beanNO) {
		this.beanNO = beanNO;
	}

	public String getPouringTime() {
		return pouringTime;
	}

	public void setPouringTime(String pouringTime) {
		this.pouringTime = pouringTime;
	}

	public String getTensionTime() {
		return tensionTime;
	}

	public void setTensionTime(String tensionTime) {
		this.tensionTime = tensionTime;
	}

	public BigDecimal getDesignStrength() {
		return designStrength;
	}

	public void setDesignStrength(BigDecimal designStrength) {
		this.designStrength = designStrength;
	}

	public BigDecimal getStrength() {
		return strength;
	}

	public void setStrength(BigDecimal strength) {
		this.strength = strength;
	}

	public String getStrandNorm() {
		return strandNorm;
	}

	public void setStrandNorm(String strandNorm) {
		this.strandNorm = strandNorm;
	}

	public BigDecimal getStrandDiameter() {
		return strandDiameter;
	}

	public void setStrandDiameter(BigDecimal strandDiameter) {
		this.strandDiameter = strandDiameter;
	}

	public String getElasticModulus() {
		return elasticModulus;
	}

	public void setElasticModulus(String elasticModulus) {
		this.elasticModulus = elasticModulus;
	}

	public BigDecimal getTensileStrength() {
		return tensileStrength;
	}

	public void setTensileStrength(BigDecimal tensileStrength) {
		this.tensileStrength = tensileStrength;
	}

	public String getJackType() {
		return jackType;
	}

	public void setJackType(String jackType) {
		this.jackType = jackType;
	}

	public String getMainPump() {
		return mainPump;
	}

	public void setMainPump(String mainPump) {
		this.mainPump = mainPump;
	}

	public String getPump() {
		return pump;
	}

	public void setPump(String pump) {
		this.pump = pump;
	}

	public BigDecimal getHoldTime() {
		return holdTime;
	}

	public void setHoldTime(BigDecimal holdTime) {
		this.holdTime = holdTime;
	}

	public BigDecimal getFinalHoldTime() {
		return finalHoldTime;
	}

	public void setFinalHoldTime(BigDecimal finalHoldTime) {
		this.finalHoldTime = finalHoldTime;
	}

	public BigDecimal getRetraction() {
		return retraction;
	}

	public void setRetraction(BigDecimal retraction) {
		this.retraction = retraction;
	}

	public String getTensionType() {
		return tensionType;
	}

	public void setTensionType(String tensionType) {
		this.tensionType = tensionType;
	}

	public BigDecimal getSingleRet() {
		return singleRet;
	}

	public void setSingleRet(BigDecimal singleRet) {
		this.singleRet = singleRet;
	}

	public String getTenTemp() {
		return tenTemp;
	}

	public void setTenTemp(String tenTemp) {
		this.tenTemp = tenTemp;
	}

	public String getSteel() {
		return steel;
	}

	public void setSteel(String steel) {
		this.steel = steel;
	}

	public Long getLongarch() {
		return longarch;
	}

	public void setLongarch(Long longarch) {
		this.longarch = longarch;
	}

	public String getAnchorage() {
		return anchorage;
	}

	public void setAnchorage(String anchorage) {
		this.anchorage = anchorage;
	}

	public List<TensionDetailJsonModel> getTensionDetailList() {
		return tensionDetailList;
	}

	public void setTensionDetailList(List<TensionDetailJsonModel> tensionDetailList) {
		this.tensionDetailList = tensionDetailList;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getData3() {
		return data3;
	}

	public void setData3(String data3) {
		this.data3 = data3;
	}

	public String getData4() {
		return data4;
	}

	public void setData4(String data4) {
		this.data4 = data4;
	}

	public String getData5() {
		return data5;
	}

	public void setData5(String data5) {
		this.data5 = data5;
	}




    
    
}