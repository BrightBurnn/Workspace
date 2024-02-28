package com.sbilife.NewsmartsamriddhiVer3;
//package com.sbilife.NewsmartsamriddhiVer3;

public class NewSmartSamriddhiBean {
	
	
	 //Bean Variable Declaration
	   private int age=0,policyTerm=0,premPayingTerm=0;;
	   private String gender=null,premfreq=null;	  
	   private double premiumAmount=0,effectivePremium=0,sumAssured=0;
	   private boolean state=false;

	   public String getGender()
	   {return gender;}
	   public void setGender(String gender)
	   {this.gender=gender;}
	   
	   public String getPremfreq()
	   {return premfreq;}
	   public void setPremfreq(String premfreq)
	   {this.premfreq=premfreq;}
	   
	   public double getPremiumAmount()
	   {return premiumAmount;}
	   public void setPremiumAmt(double premiumAmount)
	   {this.premiumAmount=premiumAmount;}
	   
	   public int getAge()
	   {return age;}
	   public void setAge(int age)
	   {this.age=age;}
	   
	// Added By Saurabh Jain on 16/05/2019 Start
	   public boolean getState()
	 	   {return state;}
	 	   public void setState(boolean state)
	 	   {this.state=state;}
	 	   
	 	  public int getPolicyTerm()
		   {return policyTerm;}
		   public void setPolicyTerm(int policyTerm)
		   {this.policyTerm =policyTerm;}
		   
		   public int getPremPayingTerm()
		   {return premPayingTerm;}
		   public void setPremPayingTerm(int premPayingTerm)
		   {this.premPayingTerm =premPayingTerm;}
		   
	 // Added By Saurabh Jain on 16/05/2019 End
	 	   
	 	  //added by sujata on 26-02-2020
//	 	  public double getsumAssured()
//		   {return sumAssured;}
//		   public void setsumAssured(double sumAssured)
//		   {this.sumAssured=sumAssured;}
		   //end
	 	   
}
