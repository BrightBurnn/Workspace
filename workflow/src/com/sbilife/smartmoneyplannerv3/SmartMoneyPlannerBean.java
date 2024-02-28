package com.sbilife.smartmoneyplannerv3;

public class SmartMoneyPlannerBean {
	
	 //Bean Variable Declaration
   private int age=0,plan=0,PF=0,premiumPayingTerm=0,policyTerm_Basic=0;
   private String gender=null,premFreqMode=null;
   private boolean isStaffDisc=false,isJKResident=false;
   private double premiumAmount=0,effectivePremium=0,sumAssured=0;


   //Bean Getter Setter Methods
   public String getGender()
   {return gender;}
   public void setGender(String gender)
   {this.gender=gender;}
   
   public String getPremFreqMode()
   {return premFreqMode;}
   public void setPremFreqMode(String premFreqMode)
   {this.premFreqMode=premFreqMode;}

   public int getPolicyTerm_Basic()
   {return policyTerm_Basic;}
   public void setPolicyTerm_Basic(int policyTerm_Basic)
   {this.policyTerm_Basic=policyTerm_Basic;}

   public int getPF()
   {return PF;}
   public void setPF(int PF)
   {this.PF=PF;}

   public double getsumAssured()
   {return sumAssured;}
   public void setsumAssured(double sumAssured)
   {this.sumAssured=sumAssured;}

   public boolean getIsForStaffOrNot()
   {return isStaffDisc;}
   public void setIsForStaffOrNot(boolean isStaffDisc)
   {this.isStaffDisc=isStaffDisc;}

   public boolean getIsJKResidentDiscOrNot()
   {return isJKResident;}
   public void setIsJKResidentDiscOrNot(boolean isJKResident)
   {this.isJKResident=isJKResident;}

   public int getAge()
   {return age;}
   public void setAge(int age)
   {this.age=age;}

   public int getPlan()
   {return plan;}
   public void setPlan(int plan)
   {this.plan=plan;}

   public int getPremiumPayingTerm()
   {return premiumPayingTerm;}
   public void setPremiumPayingTerm(int premiumPayingTerm)
   {this.premiumPayingTerm=premiumPayingTerm;}


   public double getEffectivePremium()
   {return effectivePremium;}
   public void setEffectivePremium(double effectivePremium)
   {this.effectivePremium=effectivePremium;}

   public double getPremiumAmount()
   {return premiumAmount;}
   public void setPremiumAmount(double premiumAmount)
   {this.premiumAmount=premiumAmount;}

}
