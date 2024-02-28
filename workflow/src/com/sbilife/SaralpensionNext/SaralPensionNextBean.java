package com.sbilife.SaralpensionNext;

public class SaralPensionNextBean {
	
	private boolean isStaff = false, state=false;
	private int fAnnuitantAge = 0, sAnnuitantAge=0;	
	private String fAnnuitantGender = null, sAnnuitantGender = null,ChannelD= null, payoutMode = null,
			annuityOption="", SouceofBuss="";
	private double SumAssured = 0,AdditionalAmt=0;
	
	public void setisStaff(boolean isStaff) {
		this.isStaff = isStaff;
	}
	public boolean getisStaff() {
		return isStaff;
	}
	
    public boolean getState() {
	    return state;
	}
    public void setState(boolean state) {
	    this.state=state;
    }
	    
	public void setfAnnuitantAge(int fAnnuitantAge) {
		this.fAnnuitantAge = fAnnuitantAge;
	}
	public int getfAnnuitantAge() {
		return fAnnuitantAge;
	}
	
	public void setsAnnuitantAge(int sAnnuitantAge) {
		this.sAnnuitantAge = sAnnuitantAge;
	}

	public int getsAnnuitantAge() {
		return sAnnuitantAge;
	}
	
	public void setfAnnuitantGender(String fAnnuitantGender) {
		this.fAnnuitantGender = fAnnuitantGender;
	}
	public String getfAnnuitantGender() {
		return fAnnuitantGender;
	}
	
	public void setChannelD(String ChannelD) {
		this.ChannelD = ChannelD;
	}
	public String getChannelD() {
		return ChannelD;
	}
	
	public void setsAnnuitantGender(String sAnnuitantGender) {
		this.sAnnuitantGender = sAnnuitantGender;
	}
	public String getsAnnuitantGender() {
		return sAnnuitantGender;
	}
	
	public void setpayoutMode(String payoutMode) {
		this.payoutMode = payoutMode;
	}
	public String getpayoutMode() {
		return payoutMode;
	}
	
	public String getAnnuityOption() {
	    return annuityOption;
	}
	public void setAnnuityOption(String annuityOption) {
	    this.annuityOption = annuityOption;
	}
	
	public String getSouceofBuss() {
		return SouceofBuss;
	}
	public void setSouceofBuss(String SouceofBuss) { 
		this.SouceofBuss = SouceofBuss;
	}
	
	public void setSumAssured(double SumAssured) {
		this.SumAssured = SumAssured;
	}

	public double getSumAssured() {
		return SumAssured;
	}
	
	public void setAdditionalAmt(double AdditionalAmt) {
		this.AdditionalAmt = AdditionalAmt;
	}

	public double getAdditionalAmt() {
		return AdditionalAmt;
	}

}
