package com.sbilife.saralswadhanplus;

public class SaralSwadhanPlusBean {

	  //bean variable declaration
    private int     ageAtEntry=0,
                    policy_Term=0;

    private String  gender=null,
                    premFreqMode=null;

    private double  premiumAmt=0;
    
    private boolean state=false;
    
    //bean getter setter method
    public void setAge(int ageAtEntry)
    {this.ageAtEntry=ageAtEntry;}
    public int getAge()
    {return ageAtEntry;}

    public void setPolicy_Term(int policy_Term)
    {this.policy_Term=policy_Term;}
    public int getPolicy_Term()
    {return policy_Term;}
	
    public void setGender(String gender)
    {this.gender=gender;}
    public String getGender()
    {return gender;}
    
    public void setPremFreqMode(String premFreqMode)
    {this.premFreqMode=premFreqMode;}
    public String getPremFreqMode()
    {return premFreqMode;}
    
    public void setPremiumAmt(double premiumAmt)
    {this.premiumAmt=premiumAmt;}
    public double getPremiumAmt()
    {return premiumAmt;}
    
    public boolean getState()
	   {return state;}
	   public void setState(boolean state)
	   {this.state=state;}

}
