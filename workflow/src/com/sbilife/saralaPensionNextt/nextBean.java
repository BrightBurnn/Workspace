package com.sbilife.saralaPensionNextt;

public class nextBean {
	private boolean isStaff = false;
	private int firstAnnuitantAge = 0, secondAnnuitantAge=0;	
	private String firstAnnuitantGender = null, secondAnnuitantGender = null,ChannelDetails= null, payoutMode = null,state=null,
			annuityOption="", SouceofBuss="";
	private double SumAssured = 0,AdditionalAmt=0;
	public boolean isStaff() {
		return isStaff;
	}
	public void setStaff(boolean isStaff) {
		this.isStaff = isStaff;
	}

	public int getFirstAnnuitantAge() {
		return firstAnnuitantAge;
	}
	public void setFirstAnnuitantAge(int firstAnnuitantAge) {
		this.firstAnnuitantAge = firstAnnuitantAge;
	}
	public int getSecondAnnuitantAge() {
		return secondAnnuitantAge;
	}
	public void setSecondAnnuitantAge(int secondAnnuitantAge) {
		this.secondAnnuitantAge = secondAnnuitantAge;
	}
	public String getFirstAnnuitantGender() {
		return firstAnnuitantGender;
	}
	public void setFirstAnnuitantGender(String firstAnnuitantGender) {
		this.firstAnnuitantGender = firstAnnuitantGender;
	}
	public String getSecondAnnuitantGender() {
		return secondAnnuitantGender;
	}
	public void setSecondAnnuitantGender(String secondAnnuitantGender) {
		this.secondAnnuitantGender = secondAnnuitantGender;
	}
	public String getChannelDetails() {
		return ChannelDetails;
	}
	public void setChannelDetails(String channelDetails) {
		ChannelDetails = channelDetails;
	}
	public String getPayoutMode() {
		return payoutMode;
	}
	public void setPayoutMode(String payoutMode) {
		this.payoutMode = payoutMode;
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
	public void setSouceofBuss(String souceofBuss) {
		SouceofBuss = souceofBuss;
	}
	
	public double getAdditionalAmt() {
		return AdditionalAmt;
	}
	public void setAdditionalAmt(double additionalAmt) {
		AdditionalAmt = additionalAmt;
	}
	public double getSumAssured() {
		return SumAssured;
	}
	public void setSumAssured(double SumAssured) {
		this.SumAssured = SumAssured;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
