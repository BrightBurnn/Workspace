package com.sbilife.sampoorncancersuraksha;

public class SampoorncancersurakshaBean {
	
		private int age = 0;
		private int policyterm = 0;
		private int premiumpayingterm = 0;
		private double sumassured = 0 ;
		
		
		private String plantype = null ;
		private String premfreq = null ;
		private String gender = null ;
		
		private boolean isStaffDisc=false;
		private boolean isjammuresident=false;
		
		
		
		
		public void setAge(int age)
	    {this.age=age;}
	    public int getAge()
	    {return age;}
	    
	    
	    public void setPolicyterm(int policyterm)
	    {this.policyterm=policyterm;}
	    public int getPolicyterm()
	    {return policyterm;}
	    
	    
	    
	    public void setSumAssured(double sumassured)
	    {this.sumassured=sumassured;}
	    public double getSumAssured()
	    {return sumassured;}
	    
	    
	    
	    
	    public void setPlanType(String plantype)
	    {this.plantype=plantype;}
	    public String getPlantype()
	    {return plantype;}
	    
	    
	    
	    public void setGender(String gender)
	    {this.gender=gender;}
	    public String getGender()
	    {return gender;}
	    
	    
	    public void setPremiumFrequency(String premfreq)
	    {this.premfreq=premfreq;}
	    public String getPremiumFrequency()
	    {return premfreq;}
	    
	    
	    
	    public void setIsForStaffOrNot(boolean isStaffDisc)
	    {this.isStaffDisc=isStaffDisc;}
	     public boolean getIsForStaffOrNot()
	    {return isStaffDisc;}
	     
	     
	     
	     public void setIsJammuResident(boolean jammu)
	     {this.isjammuresident=jammu;}
	      public boolean getIsJammuResident()
	     {return isjammuresident;}

	}


