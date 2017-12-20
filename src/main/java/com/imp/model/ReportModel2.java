package com.imp.model;

import com.imp.system.Column;
import com.imp.system.Table;

import java.math.BigDecimal;

@Table("张拉报表库")
public class ReportModel2 extends TensionJsonModel {

    private Integer    tensionId;       //张拉主表ID
    @Column("孔位1")
    private String     steelNo;   //钢束编号
    private String     steelType = "0"; //钢束型号
    private String     anchorage = "0";  //锚具型号
    private BigDecimal steelLength = BigDecimal.ZERO;     //钢束长度(cm)
    @Column("29张拉仪2编号")
    private String     jackNo;          //张拉千斤顶编号
    @Column("29张拉仪2编号")
    private String     oilPressureNo;   //油压表编号
    @Column("61张拉控制力")
    private BigDecimal designTension;   //设计张拉力(KN)
    @Column("10张拉力")
    private BigDecimal tension10;       //张拉力10%(KN)
    @Column("33a210油压")
    private BigDecimal oilPressure10;   //油压  10%(Mpa)
    @Column("32a210伸长量")
    private BigDecimal extenLength10;   //伸长量10%(mm)
    @Column("20张拉力")
    private BigDecimal tension20;       //张拉力20%(KN)
    @Column("35a220油压")
    private BigDecimal oilPressure20;   //油压  20%(Mpa)
    @Column("34a220伸长量")
    private BigDecimal extenLength20;   //伸长量20%(mm)
    @Column("50张拉力")
    private BigDecimal tension50;       //张拉力50%(KN)
    @Column("50A2油压")
    private BigDecimal oilPressure50;   //油压  50%(Mpa)
    @Column("68主1-50伸长量")
    private BigDecimal extenLength50;   //伸长量50%(mm)
    @Column("100张拉力")
    private BigDecimal tension100;      //张拉力100%(KN)

    @Column("37a2100油压")
    private BigDecimal oilPressure100;  //油压  100%(Mpa)
    @Column("36a2100伸长量")
    private BigDecimal extenLength100;  //伸长量100%(mm)
    private BigDecimal tension103;      //张拉力103%(KN)
    private BigDecimal oilPressure103;  //油压  103%(Mpa)
    private BigDecimal extenLength103;  //伸长量103%(mm)
    @Column("11单端锚固回缩量")
    private BigDecimal singleRet = BigDecimal.ZERO;       //单端回缩量(mm)
    private BigDecimal fanalExten = BigDecimal.ZERO;      //锚固后伸长量(mm)
    @Column("62实际伸长量2")
    private BigDecimal exten;           //实际伸长量(mm)
    @Column("18理论伸长量")
    private BigDecimal theoryExten;     //理论伸长量(mm)
    @Column("50伸长量误差B")
    private String     extenError;      //延伸误差(%)
    private String     isBroken;        //断丝情况

    public String getSteelNo() {
        return steelNo;
    }

    public void setSteelNo(String steelNo) {
        this.steelNo = steelNo;
    }

    public String getSteelType() {
        return steelType;
    }

    public void setSteelType(String steelType) {
        this.steelType = steelType;
    }

    @Override
    public String getAnchorage() {
        return anchorage;
    }

    @Override
    public void setAnchorage(String anchorage) {
        this.anchorage = anchorage;
    }

    public BigDecimal getSteelLength() {
        return steelLength;
    }

    public void setSteelLength(BigDecimal steelLength) {
        this.steelLength = steelLength;
    }

    public String getJackNo() {
        return jackNo;
    }

    public void setJackNo(String jackNo) {
        this.jackNo = jackNo;
    }

    public String getOilPressureNo() {
        return oilPressureNo;
    }

    public void setOilPressureNo(String oilPressureNo) {
        this.oilPressureNo = oilPressureNo;
    }

    public BigDecimal getDesignTension() {
        return designTension;
    }

    public void setDesignTension(BigDecimal designTension) {
        this.designTension = designTension;
    }

    public BigDecimal getTension10() {
        return tension10;
    }

    public void setTension10(BigDecimal tension10) {
        this.tension10 = tension10;
    }

    public BigDecimal getOilPressure10() {
        return oilPressure10;
    }

    public void setOilPressure10(BigDecimal oilPressure10) {
        this.oilPressure10 = oilPressure10;
    }

    public BigDecimal getExtenLength10() {
        return extenLength10;
    }

    public void setExtenLength10(BigDecimal extenLength10) {
        this.extenLength10 = extenLength10;
    }

    public BigDecimal getTension20() {
        return tension20;
    }

    public void setTension20(BigDecimal tension20) {
        this.tension20 = tension20;
    }

    public BigDecimal getOilPressure20() {
        return oilPressure20;
    }

    public void setOilPressure20(BigDecimal oilPressure20) {
        this.oilPressure20 = oilPressure20;
    }

    public BigDecimal getExtenLength20() {
        return extenLength20;
    }

    public void setExtenLength20(BigDecimal extenLength20) {
        this.extenLength20 = extenLength20;
    }

    public BigDecimal getTension50() {
        return tension50;
    }

    public void setTension50(BigDecimal tension50) {
        this.tension50 = tension50;
    }

    public BigDecimal getOilPressure50() {
        return oilPressure50;
    }

    public void setOilPressure50(BigDecimal oilPressure50) {
        this.oilPressure50 = oilPressure50;
    }

    public BigDecimal getExtenLength50() {
        return extenLength50;
    }

    public void setExtenLength50(BigDecimal extenLength50) {
        this.extenLength50 = extenLength50;
    }

    public BigDecimal getTension100() {
        return tension100;
    }

    public void setTension100(BigDecimal tension100) {
        this.tension100 = tension100;
    }

    public BigDecimal getOilPressure100() {
        return oilPressure100;
    }

    public void setOilPressure100(BigDecimal oilPressure100) {
        this.oilPressure100 = oilPressure100;
    }

    public BigDecimal getExtenLength100() {
        return extenLength100;
    }

    public void setExtenLength100(BigDecimal extenLength100) {
        this.extenLength100 = extenLength100;
    }

    public BigDecimal getTension103() {
        return tension103;
    }

    public void setTension103(BigDecimal tension103) {
        this.tension103 = tension103;
    }

    public BigDecimal getOilPressure103() {
        return oilPressure103;
    }

    public void setOilPressure103(BigDecimal oilPressure103) {
        this.oilPressure103 = oilPressure103;
    }

    public BigDecimal getExtenLength103() {
        return extenLength103;
    }

    public void setExtenLength103(BigDecimal extenLength103) {
        this.extenLength103 = extenLength103;
    }

    @Override
    public BigDecimal getSingleRet() {
        return singleRet;
    }

    @Override
    public void setSingleRet(BigDecimal singleRet) {
        this.singleRet = singleRet;
    }

    public BigDecimal getFanalExten() {
        return fanalExten;
    }

    public void setFanalExten(BigDecimal fanalExten) {
        this.fanalExten = fanalExten;
    }

    public BigDecimal getExten() {
        return exten;
    }

    public void setExten(BigDecimal exten) {
        this.exten = exten;
    }

    public BigDecimal getTheoryExten() {
        return theoryExten;
    }

    public void setTheoryExten(BigDecimal theoryExten) {
        this.theoryExten = theoryExten;
    }

    public String getExtenError() {
        return extenError;
    }

    public void setExtenError(String extenError) {
        this.extenError = extenError;
    }

    public String getIsBroken() {
        return isBroken;
    }

    public void setIsBroken(String isBroken) {
        this.isBroken = isBroken;
    }

    public Integer getTensionId() {
        return tensionId;
    }

    public void setTensionId(Integer tensionId) {
        this.tensionId = tensionId;
    }
}



