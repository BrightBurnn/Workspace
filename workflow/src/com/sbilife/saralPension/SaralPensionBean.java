package com.sbilife.saralPension;

public class SaralPensionBean {

	private boolean staffDisc = false, jkResident = false, PTR_Status = false,state=false;
	private int age = 0, policyTerm = 0, PTRTerm = 0,vestingAge =0,deferredPeroid=0;
	private String gender = null, premiumFreq = null,annuityOption="";
	private double sumAssured = 0, PTR_sumAssured = 0;

	public void setStaffDisc(boolean staffDisc) {
		this.staffDisc = staffDisc;
	}

	public boolean getStaffDisc() {
		return staffDisc;
	}

	public void setJkResident(boolean jkResident) {
		this.jkResident = jkResident;
	}

	public boolean getJkResident() {
		return jkResident;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setPolicyTerm(int policyTerm) {
		this.policyTerm = policyTerm;
	}

	public int getPolicyTerm() {
		return policyTerm;
	}

	public void setPTRTerm(int PTRTerm) {
		this.PTRTerm = PTRTerm;
	}

	public int getPTRTerm() {
		return PTRTerm;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setPremiumFreq(String premiumFreq) {
		this.premiumFreq = premiumFreq;
	}

	public String getPremiumFreq() {
		return premiumFreq;
	}

	public void setSumAssured(double sumAssured) {
		this.sumAssured = sumAssured;
	}

	public double getSumAssured() {
		return sumAssured;
	}

	public void setPTR_sumAssured(double PTR_sumAssured) {
		this.PTR_sumAssured = PTR_sumAssured;
	}

	public double getPTR_sumAssured() {
		return PTR_sumAssured;
	}

	public void setPTR_Status(boolean PTR_Status) {
		this.PTR_Status = PTR_Status;
	}

	public boolean getPTR_Status() {
		return PTR_Status;
	}
	
//  Added By Saurabh Jain on 14/05/2019 Start
   public boolean getState()
	   {return state;}
	   public void setState(boolean state)
	   {this.state=state;}
//  Added By Saurabh Jain on 14/05/2019 End
	   
	   public String getAnnuityOption()
		{return annuityOption;}
		public void setAnnuityOption(String annuityOption)
		{this.annuityOption = annuityOption;}
		
		public void setVestingAge(int vestingAge) {
			this.vestingAge = vestingAge;
		}

		public int getVestingAge() {
			return vestingAge;
		}
		public int getDeferredPeroid()
		{return deferredPeroid;}
		public void setDeferredPeroid(int deferredPeroid)
		{this.deferredPeroid = deferredPeroid;}
}
