package com.sbilife.newsmartsamriddhi3;

import com.sbilife.NewsmartsamriddhiVer3.NewSmartSamriddhiDB;
import com.sbilife.common.CommonForAllProd;

public class newSmartSamriddhiLogic {

	newSmartSamriddhiBean bean = new newSmartSamriddhiBean();
	newSmartSamriddhiDb db= new  newSmartSamriddhiDb();
	CommonForAllProd comm = new CommonForAllProd();
	newSmartSamriddhiProperties prop = new newSmartSamriddhiProperties();
	double SAMFRate = 0;
	double GSVFactor = 0, TermToMaturity = 0;
	double PremiumAmount = 0;
	double monthvalue=0;
	double premiumPayingTerm=0;
	public double getPremium(String premFreq , double premium ) {
		 double premiumAmnt =0;
		 if (premFreq.equalsIgnoreCase("monthly"))
		 {
			 premiumAmnt = Double.parseDouble(comm.getRoundOffLevel2New(comm.getStringWithout_E(premium*0.085)));
		 }
		 else
			 premiumAmnt = premium;
		 
		 return premiumAmnt;
	}
	
	public int getPremiumPayingTerm(int policyTerm)
	{  int premiumPayingTerm=0;
	if(policyTerm==15)
	{
		premiumPayingTerm =7;
	}
	else if(policyTerm ==20)
	{
		premiumPayingTerm=10;
	}
	else
		premiumPayingTerm =6;
	 return premiumPayingTerm;	
	}
	
	public double getServiceTaxFirstYear(double premiumWithoutST, String type) {
		if (type.equalsIgnoreCase("basic")) {
			return Double.parseDouble(comm.getRoundOffLevel2(String
					.valueOf(premiumWithoutST * (prop.serviceTax + 1))))
					- premiumWithoutST;
		} else if (type.equalsIgnoreCase("SBC")) {
			return Double.parseDouble(comm.getRoundOffLevel2(String
					.valueOf(premiumWithoutST * (prop.SBCServiceTax + 1))))
					- premiumWithoutST;
		} 
		else if (type.equalsIgnoreCase("KERALA")) {
			return Double.parseDouble(comm.getRoundOffLevel2(String
					.valueOf(premiumWithoutST * (prop.kerlaServiceTax + 1))))
					- premiumWithoutST;
		}
	
		else // KKC
		{
			return Double.parseDouble(comm.getRoundOffLevel2(String
					.valueOf(premiumWithoutST * (prop.KKCServiceTax + 1))))
					- premiumWithoutST;
		}
	}
	public double getServiceTaxSecondYear(double premiumWithoutST, String type) {
		if (type.equalsIgnoreCase("basic")) {
			return Double.parseDouble(comm.getRoundOffLevel2(String
					.valueOf(premiumWithoutST
							* (prop.serviceTaxSecondYear + 1))))
					- premiumWithoutST;
		} else if (type.equalsIgnoreCase("SBC")) {
			return Double.parseDouble(comm.getRoundOffLevel2(String
					.valueOf(premiumWithoutST
							* (prop.SBCServiceTaxSecondYear + 1))))
					- premiumWithoutST;
		}
		else if (type.equals("KERALA")) {
			return Double.parseDouble(comm.getRoundOffLevel2(String
					.valueOf(premiumWithoutST
							* (prop.kerlaServiceTaxSecondYear + 1))))
					- premiumWithoutST;
		} 
	    else // KKC
		{
			return Double.parseDouble(comm.getRoundOffLevel2(String
					.valueOf(premiumWithoutST
							* (prop.KKCServiceTaxSecondYear + 1))))
					- premiumWithoutST;
		}

	}
	
	public double setAnnualBasedPremium(double year , double premium, int policyPayingTerm)
	{
		if(year>policyPayingTerm)
			return 0;
		else
			return premium;
	}
	
	
	public double setGuaranteedAddition(double premium,
			double cummulativePremium) {
		double per= 0;
		if (premium >= 30000)
			per = 0.06;
		else
			per = 0.055;
		return (per * cummulativePremium);
	}
	
	
	public double setGuaranteedDeathBenefit(double sumAssured,
			double premium, double cummulativeGuaranteedAddition,
			double cummulativePremium,int year,int policyTerm) {
		double aa =0;
				
		if(year <= policyTerm){
		double max = Math.max(10 * premium, sumAssured);
		max = Math.max(max, 1.05 * cummulativePremium);
		aa =(cummulativeGuaranteedAddition + max);
		}else{
			
			aa=0;
		}
		return aa;
	}
	
	public double setGuaranteedMaturityBenefit(double sumAssured,
			double cummulativeGuaranteedAddition, int year,int policyTerm) {
		
		double res =0;
		if (year == policyTerm){
			res =(sumAssured + cummulativeGuaranteedAddition);
		}
		else{
			return res =0;
		}
		return res;
	}
	//===================================================================
	public double setSAMFRate(int age, int policyTerm) {
		newSmartSamriddhiDb db = new newSmartSamriddhiDb();
		double[] samfArr6 = db.getSAMFRatesFor6();
		double[] samfArr7 = db.getSAMFRatesFor7();
		double[] samfArr10 = db.getSAMFRatesFor10();
		int position = 0;
		double SAMFRate = 0;
		if(policyTerm ==12){
		for (int ageCount =0; ageCount <=50; ageCount++) {
			if (ageCount == age) {
				SAMFRate = samfArr6[position];
				break;
			}
			position++;
		}
		}else if(policyTerm ==15){
		for (int ageCount = 0; ageCount <= 50; ageCount++) {
				if (ageCount == age) {
					SAMFRate = samfArr7[position];
					break;
				}
				position++;
			}
		}
		
		else{
		for (int ageCount = 0; ageCount <= 50; ageCount++) {
				if (ageCount == age) {
					SAMFRate = samfArr10[position];
					break;
				}
				position++;
			}
		}

		return SAMFRate;
	}
	public double setSumAssure(double premium, double SAMFRate)
	{
		double res =Double.parseDouble(comm.getRoundOffLevel2(comm.getStringWithout_E(1000/SAMFRate)));
	
		return (premium * res);
		
	}
	
	
	public double getValue(String premFreq)
	{
		
		if(premFreq.equalsIgnoreCase("Monthly")) {
			monthvalue=12;
		}else {
			monthvalue=1;
		}
		return monthvalue;
	}
	
	public double getTotalPremium(int year,double AnnualBasedPremium,int premiumPayingTerm) {
		double totalPremium=0;
		if(year<=premiumPayingTerm) {
			totalPremium=AnnualBasedPremium*monthvalue;
		}else {
			totalPremium=0;
		}
		return totalPremium;
	}
	
	public double minimumguarrenteedSurrenderValue(double cumulativepremium,int year,int premiumPayingTerm)
	{

		newSmartSamriddhiDb db = new newSmartSamriddhiDb();
      double[] gsvFactor = db.getGSVFactors(premiumPayingTerm);
		int position = 0;
		
		GSVFactor = 0;
		for (int policyYear = 1; policyYear <= 20; policyYear++) {
			if (policyYear == year) {
				GSVFactor = gsvFactor[position];
				break;
			}
			position++;
		}		
		
		return GSVFactor*cumulativepremium;
	}
	
	
	public double getSpecialSurrenderValue(int Year,double sumAssured,double premiumAmt,double cummulativeGuaranteedAddition,int policyTerm)
	{
		double a=0,b=0,c=0,ssvFactor6=0,specialSurrenderVal=0,ssvFactor7=0,ssvFactor10=0,res=0;
		newSmartSamriddhiDb db= new newSmartSamriddhiDb();
		double premiumPayingTerm =0;
		if(policyTerm ==12){
			
			premiumPayingTerm = 6;
		}else if(policyTerm ==15){
			
			premiumPayingTerm = 7;
		}else {
			
			premiumPayingTerm = 10;
		}
		

		if (Year > premiumPayingTerm)
		{
			a=1;
		}
		else
		{
			a= Year/premiumPayingTerm;
		}
		
		b=(a*sumAssured);
		
	
		double[] SSVFactor6 = db.getSSVFactorsFor6();
		ssvFactor6 = SSVFactor6[Year-1];
		double[] SSVFactor7 = db.getSSVFactorsFor7();
		ssvFactor7 = SSVFactor7[Year-1];
		double[] SSVFactor10 = db.getSSVFactorsFor10();
		ssvFactor10 = SSVFactor10[Year-1];
	
		int pos1=0;
		if(premiumPayingTerm ==6){
			for(int i= 1;i<=Year;i++ )
			{
				if(i == Year)
				{
					ssvFactor6 = SSVFactor6[pos1];
					res =ssvFactor6;
					
					break;
				}
				pos1++;
			}
		}else if(premiumPayingTerm ==7){
			
			for(int i= 1;i<=Year;i++ )
			{
				if(i == Year)
				{
					ssvFactor7 = SSVFactor7[pos1];
					res =ssvFactor7;
					
					break;
				}
				pos1++;
			}
		}else{
			
			for(int i= 1;i<=Year;i++ )
			{
				if(i == Year)
				{
					ssvFactor10 = SSVFactor10[pos1];
					res=ssvFactor10;
					break;
				}
				pos1++;
			}
		}
	specialSurrenderVal= (b + cummulativeGuaranteedAddition) * res;
		return specialSurrenderVal;
		
	}
	
	
	
	
	
	
	
	

	
	
	

	
	
}
