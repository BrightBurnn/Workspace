package com.sbilife.saralaPensionNextt;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.sbilife.common.CommonForAllProd;

public class nextBusinessLogic {
	private CommonForAllProd comm= null;
	private nextBean bean=null;
	private nextProperties prop= null;
	private nextDb  db = new nextDb();
	private static final int minAge=40;
	private static final int maxAge=80;
	
	public  nextBusinessLogic(nextBean bean)
	{
		comm = new CommonForAllProd();
		this.bean= bean;
		prop = new nextProperties();
		db = new nextDb();
		
	}
	
	
	public double getSingleAnnutyRate() {
		double rate = 0;
		double[] AnnuityRatesForSingleLife = db.getAnnuityRatesForSingleLife();

		rate = AnnuityRatesForSingleLife[bean.getFirstAnnuitantAge() -minAge];
		return rate;
	}
	
	public double getJoinAnnunityRate() {  
		double res = 0;
		int rowNo = bean.getFirstAnnuitantAge() -(minAge);
		int colNo = bean.getSecondAnnuitantAge() -(minAge);
		int min = (rowNo * (maxAge-minAge))+ rowNo;
		double[] getAnnuityRatesForJoinLife = db.getAnnuityRatesForJoinLife();
		res = getAnnuityRatesForJoinLife[min+colNo];
	return res;
		
	}
	public int getFreqModeFactor() {
		if(bean.getPayoutMode().equalsIgnoreCase("Annual"))
			return 1;	
		else if(bean.getPayoutMode().equalsIgnoreCase("Half yearly"))
			return 2;	
		else if(bean.getPayoutMode().equalsIgnoreCase("Quarterly"))
			return 4;	
		else  
			return 12;	
	}
	
	public double getFreqModalFactor() {
		if(bean.getPayoutMode().equalsIgnoreCase("Annual") )
			return 1.035;	
		else if(bean.getPayoutMode().equalsIgnoreCase("Half yearly") )
			return 1.0175;	
		else if(bean.getPayoutMode().equalsIgnoreCase("Quarterly") )
			return 1.0075;	
		else  
			return 0;
	}
	
	public double getPremiumRate() {
		if (bean.getAnnuityOption().equalsIgnoreCase("Option1"))
			return getSingleAnnutyRate();
		else 
			return getJoinAnnunityRate();
	}
	
	public double getPremiumModelfactor()
	{
		if(bean.getPayoutMode().equalsIgnoreCase("monthly"))
			return getPremiumRate();
		else
			return getPremiumRate()* getFreqModalFactor();
	}
	
	 public String getVestingAmt() {
	    	
	    	if(bean.getSouceofBuss().equalsIgnoreCase("Vesting/Death/ Surrender of existing SBI Life's pension policy")) 
	    		return  Double.toString(bean.getSumAssured());
	    	else
	    		return "-";

	    }
	 public double getAdditionalAmt() {
	    	if(bean.getSouceofBuss().equalsIgnoreCase("New Proposal")|| bean.getSouceofBuss().equalsIgnoreCase("Open Market Option (Any other Life Insurance Company pension policy)") )
	    		return 0;
	    	else 
	    		return bean.getAdditionalAmt();
			
		}
	 public double getTotalAmt() {
	    	double totalAmt=0;
	    	totalAmt=(bean.getSumAssured() + getAdditionalAmt());
	    	return totalAmt;
	    }
	 
	 public double getServiceTax() {
	   double serviceTax =0;
	       if(bean.getState().equalsIgnoreCase("keral"))
		 serviceTax= bean.getSumAssured()-(bean.getSumAssured()/(1+ prop.KKCServiceTax));
		 else
			 serviceTax= bean.getSumAssured()-(bean.getSumAssured()/(1+ prop.serviceTax));
			 
			 return Math.round(serviceTax);
		 }
	 
	 public double getAmountAfterServiceTax()
	 {     return getTotalAmt() - getServiceTax();
		 
	 }
	 public double getHighrPurchesPrice() {
			if (bean.getSumAssured() >=0 && bean.getSumAssured()<=500000)
				return 0;
			else if (bean.getSumAssured()>500000 && bean.getSumAssured()<=1000000)
				return 2.75;
			else if (bean.getSumAssured()>1000000 && bean.getSumAssured()<=2500000)
				return 3.75;
			else if (bean.getSumAssured()>2500000)
				return 4.75;
			else 
				return 0;
			
		}
	 
	 public double getAnnuityAmt() {
	    	double annuityAmt = 0;
	    if(bean.getSouceofBuss().equalsIgnoreCase("Vesting/Death/ Surrender of existing SBI Life's pension policy" )
	    			|| bean.getChannelDetails().equalsIgnoreCase("Direct")) 
	    		annuityAmt =((getPremiumModelfactor()+getHighrPurchesPrice())/getFreqModeFactor())/980*getAmountAfterServiceTax()	;	
	        else if(bean.isStaff()== true)     	   
	        	annuityAmt= ((getPremiumModelfactor()+getHighrPurchesPrice())/getFreqModeFactor())/980*getAmountAfterServiceTax();	
	    	 else 
	        	annuityAmt= ((getPremiumModelfactor()+getHighrPurchesPrice())/getFreqModeFactor())/1000*getAmountAfterServiceTax();	
	    	
	    	return Math.round(annuityAmt);	
	    }
	 
	 public String getSingleOrAnnualizedPrem(int policyYear) {
	    	if((policyYear-1)<=(99-bean.getFirstAnnuitantAge()))
	    		return "0";
	    	else
	    		return "-";
	    }
	 
	 public double getSurvivalBenefit( ){
	    	double survivalB=0;
	    	
	    	if(bean.getPayoutMode().equalsIgnoreCase("Half yearly"))
	    		survivalB=(getAnnuityAmt()*2);
	    	
	    	else if(bean.getPayoutMode().equalsIgnoreCase("Quarterly"))
	    		survivalB= (getAnnuityAmt()*4);
	    	
	    	else if(bean.getPayoutMode().equalsIgnoreCase("Monthly"))
	    		survivalB= (getAnnuityAmt()*12);
	    	else
	    		survivalB= getAnnuityAmt();
	    	return survivalB;
	    }
	 public String getOtherBenefit(int policyYear) {
	    	if(policyYear<=(99-bean.getFirstAnnuitantAge()))
	    		return "0";
	    	else
	    		return "-";
	    }
	 
	  public String getDeathBenefit() {
	    	DecimalFormat df = new DecimalFormat("#");
	    	df.setRoundingMode(RoundingMode.FLOOR);
	    	if(bean.getAnnuityOption().equalsIgnoreCase("Option2"))
	    		return "Rs. "+String.valueOf(getSurvivalBenefit())+
	    				", Till death of 2nd Annuitant and Rs. "+ String.valueOf(getAmountAfterServiceTax())+
	    				" on death of 2nd Annuitant";
	    	else 
	    		return df.format(getAmountAfterServiceTax());	
	    }
	  public double getminGuaranteedSurrValue() {
		    
	    	DecimalFormat df = new DecimalFormat("#");
	    	double minGuaranteedSurr=0;
	    	minGuaranteedSurr=(getAmountAfterServiceTax()*0.95);
	    	return Double.parseDouble(df.format(minGuaranteedSurr));
	    }
	 
}
