
//package com.sbilife.NewsmartsamriddhiVer3;
//
//public class NewSmartSamriddhiBusinessLogic {
//
//}

package com.sbilife.NewsmartsamriddhiVer3;

import com.sbilife.common.CommonForAllProd;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewSmartSamriddhiBusinessLogic {

	public CommonForAllProd cfap = null;
	public NewSmartSamriddhiProperties sgspProp = null;
	public NewSmartSamriddhiBean smartsdbean=null;
	double TabularSAMFRate = 0;
	double GSVFactor = 0, TermToMaturity = 0;
	double PremiumAmount = 0;
	double monthvalue=0;
	public NewSmartSamriddhiBusinessLogic(
			NewSmartSamriddhiBean SGSPbean) {
		// TODO Auto-generated constructor stub

		cfap = new CommonForAllProd();
		sgspProp = new NewSmartSamriddhiProperties();
		smartsdbean = new NewSmartSamriddhiBean();
	}


	public double getPremiumAmount(String premfreq,Double PremAmount )
	{
		double PremiumAmount = 0;
		if(premfreq.equalsIgnoreCase("Yearly"))
		{
			PremiumAmount = PremAmount;
		}
		else
		{
			PremiumAmount = Double.parseDouble(cfap.getRoundOffLevel2New(cfap.getStringWithout_E(PremAmount * 0.085)));
		}
		
		return PremiumAmount;
	}
	
	/*** modified by Akshaya on 20-MAY-16 start **/
	public double getServiceTax(double premiumWithoutST, String type) {
		if (type.equals("basic")) {
			return Double.parseDouble(cfap.getRoundOffLevel2(String
					.valueOf(premiumWithoutST * (sgspProp.serviceTax + 1))))
					- premiumWithoutST;
		} else if (type.equals("SBC")) {
			return Double.parseDouble(cfap.getRoundOffLevel2(String
					.valueOf(premiumWithoutST * (sgspProp.SBCServiceTax + 1))))
					- premiumWithoutST;
		} 
	//  Added By Saurabh Jain on 16/05/2019 Start
		else if (type.equals("KERALA")) {
			return Double.parseDouble(cfap.getRoundOffLevel2(String
					.valueOf(premiumWithoutST * (sgspProp.kerlaServiceTax + 1))))
					- premiumWithoutST;
		}
	//  Added By Saurabh Jain on 16/05/2019 End
		else // KKC
		{
			return Double.parseDouble(cfap.getRoundOffLevel2(String
					.valueOf(premiumWithoutST * (sgspProp.KKCServiceTax + 1))))
					- premiumWithoutST;
		}

	}

	public double getServiceTaxSecondYear(double premiumWithoutST, String type) {
		if (type.equals("basic")) {
			return Double.parseDouble(cfap.getRoundOffLevel2(String
					.valueOf(premiumWithoutST
							* (sgspProp.serviceTaxSecondYear + 1))))
					- premiumWithoutST;
		} else if (type.equals("SBC")) {
			return Double.parseDouble(cfap.getRoundOffLevel2(String
					.valueOf(premiumWithoutST
							* (sgspProp.SBCServiceTaxSecondYear + 1))))
					- premiumWithoutST;
		}
	//  Added By Saurabh Jain on 16/05/2019 Start
		else if (type.equals("KERALA")) {
			return Double.parseDouble(cfap.getRoundOffLevel2(String
					.valueOf(premiumWithoutST
							* (sgspProp.kerlaServiceTaxSecondYear + 1))))
					- premiumWithoutST;
		} 
	//  Added By Saurabh Jain on 16/05/2019 End
		else // KKC
		{
			return Double.parseDouble(cfap.getRoundOffLevel2(String
					.valueOf(premiumWithoutST
							* (sgspProp.KKCServiceTaxSecondYear + 1))))
					- premiumWithoutST;
		}

	}

	/*** modified by Akshaya on 20-MAY-16 end **/

	public double setYearlyBasePremiumPaid(int year_F, double premiumAmt,int ppt) {
//		if (year_F > sgspProp.premiumPayingTerm)
		if (year_F > ppt)
			return 0;
		else
			return premiumAmt;
	}
	//========================================================================

	public double setGuaranteedAddition(double premiumAmt,
			double cummulativePremium) {
		double a = 0;
		if (premiumAmt >= 30000)
			a = 0.06;
		else
			a = 0.055;
		return (a * cummulativePremium);

	}


	public double setSumAssured(double premiumAmt, int age)  //not use
	{
		NewSmartSamriddhiDB sgspDB = new NewSmartSamriddhiDB();
		double[] samfArr = sgspDB.getSAMFRates();
		int position = 0;
		double  TabularSAMFRate =0 ;
		for (int ageCount = 18; ageCount <= 60; ageCount++) {
			if (ageCount == age) {
				TabularSAMFRate = samfArr[position];
				break;
			}
			position++;
		}
		System.out.println(" TabularSAMFRate " +TabularSAMFRate);
		return (premiumAmt * TabularSAMFRate);
	}

//	public double setGuaranteedDeathBenefit(double sumAssured,
//			double premiumAmt, double cummulativeGuaranteedAddition,
//			double cummulativePremium) {
//		double max = Math.max(10 * premiumAmt, sumAssured);
//		max = Math.max(max, 1.05 * cummulativePremium);
//		return (cummulativeGuaranteedAddition + max);
//	}
	
	public double setGuaranteedDeathBenefit(double sumAssured,
			double premiumAmt, double cummulativeGuaranteedAddition,
			double cummulativePremium,int year_F,int policyTerm) {
		double aa =0;
				
		if(year_F <= policyTerm){
		double max = Math.max(10 * premiumAmt, sumAssured);
		max = Math.max(max, 1.05 * cummulativePremium);
		aa =(cummulativeGuaranteedAddition + max);
		}else{
			
			aa=0;
		}
		return aa;
	}
	

	public double setGuaranteedMaturityBenefit(double sumAssured,
			double cummulativeGuaranteedAddition, int year_F,int policyTerm) {
		
		double aa =0;
		if (year_F == policyTerm){
			aa =(sumAssured + cummulativeGuaranteedAddition);
		}
		else{
			return aa =0;
		}
		return aa;
	}

//	public double setSAMFRate(int age,int policyTerm) {
//		NewSmartSamriddhiDB sgspDB = new NewSmartSamriddhiDB();
//		double[] samfArr = sgspDB.getSAMFRates();
//		double[] samfArr1 = sgspDB.getSAMFRatesFor7();
//		int position = 0;
//		double TabularSAMFRate = 0;
//		if(policyTerm ==12){
//		for (int ageCount =3; ageCount <=50; ageCount++) {
//			if (ageCount == age) {
//				TabularSAMFRate = samfArr[position];
//				break;
//			}
//			position++;
//		}
//		}else{
////			for (int ageCount = 18; ageCount <= 60; ageCount++) {
//			for (int ageCount = 3; ageCount <= 50; ageCount++) {
//				if (ageCount == age) {
//					TabularSAMFRate = samfArr1[position];
//					break;
//				}
//				position++;
//			}
//		}

////		return TabularSAMFRate;
//		
////		return Double.parseDouble(cfap.getRoundOffLevel2New(cfap.getStringWithout_E(1000/TabularSAMFRate)));
//		return Double.parseDouble(cfap.getRoundOffLevel2(cfap.getStringWithout_E(1000/TabularSAMFRate)));
////		return Double.parseDouble(cfap.getRound(cfap.getStringWithout_E(1000/TabularSAMFRate)));
//	}
	
	public double setSAMFRate(int age, int policyTerm) {
		NewSmartSamriddhiDB sgspDB = new NewSmartSamriddhiDB();
		double[] samfArr = sgspDB.getSAMFRates();
		double[] samfArr1 = sgspDB.getSAMFRatesFor7();
		double[] samfArr2 = sgspDB.getSAMFRatesFor10();
		int position = 0;
		double TabularSAMFRate = 0;
		if(policyTerm ==12){
//		for (int ageCount =3; ageCount <=50; ageCount++) {
			for (int ageCount =0; ageCount <=50; ageCount++) {
			if (ageCount == age) {
				TabularSAMFRate = samfArr[position];
				break;
			}
			position++;
		}
		}else if(policyTerm ==15){
//			for (int ageCount = 18; ageCount <= 60; ageCount++) {
			for (int ageCount = 0; ageCount <= 50; ageCount++) {
				if (ageCount == age) {
					TabularSAMFRate = samfArr1[position];
					break;
				}
				position++;
			}
		}
		
		else{
//			for (int ageCount = 18; ageCount <= 60; ageCount++) {
			for (int ageCount = 0; ageCount <= 50; ageCount++) {
				if (ageCount == age) {
					TabularSAMFRate = samfArr2[position];
					break;
				}
				position++;
			}
		}

		return TabularSAMFRate;
	}

	public double setSumAssured1(double premiumAmt, double SAMFRate) {
		// SmartGuaranteedSavingsPlanDB sgspDB=new
		// SmartGuaranteedSavingsPlanDB();
		// double [] samfArr=sgspDB.getSAMFRates();
		// int position=0;
		// TabularSAMFRate=0;
		// for (int ageCount=18; ageCount<=60; ageCount++)
		// {
		// if(ageCount==age)
		// {
		// TabularSAMFRate=samfArr[position];
		// break;
		// }
		// position++;
		// }
//		return (premiumAmt * SAMFRate);
		double aa =Double.parseDouble(cfap.getRoundOffLevel2(cfap.getStringWithout_E(1000/SAMFRate)));
		return (premiumAmt * aa);
	}

	
	public double settotalpremium(int Year_F) //not use
	{
		double totalprem=0;
		if (Year_F <= sgspProp.premiumPayingTerm)
		{
			totalprem=29000;
		}
		else
		{
			return 0;
		}
		return totalprem;
	}
	

	//added by sujata 09/10/2020
	/*public double setGuaranteedSurrenderValue(
			double cummulativeGuaranteedAddition, double cummulativePremium,
			int year_F,double sumoftotalpremium ) {
		SmartSamriddhiDB smartsamriddhiDB = new SmartSamriddhiDB();
		double[] gsvFactor = smartsamriddhiDB.getGSVFactors();
		double[] termMaturity = smartsamriddhiDB.getTermToMaturity();
		double temp1;
		int position = 0;
		GSVFactor = 0;
		TermToMaturity = 0;
		for (int policyYear = 1; policyYear <= 15; policyYear++) {
			if (policyYear == (sgspProp.policyTerm - year_F + 1)) {
				TermToMaturity = termMaturity[position];
				break;
			}
			position++;
		}

		position = 0;

		for (int policyYear = 1; policyYear <= 15; policyYear++) {
			if (policyYear == year_F) {
				GSVFactor = gsvFactor[position];
				break;
			}
			position++;
		}

//		temp1 = (GSVFactor * cummulativePremium) + (TermToMaturity / 100)
//				* cummulativeGuaranteedAddition;
//
//		return (GSVFactor * cummulativePremium + (TermToMaturity / 100)
//				* cummulativeGuaranteedAddition);
//		System.out.println("GSVFactor"+GSVFactor);
		System.out.println("\ncummulativePremium "+cummulativePremium);

		temp1 = (GSVFactor * cummulativePremium);
	//	temp1 = (GSVFactor * sumoftotalpremium);
		
	//	System.out.println("\nGSVFactor "+GSVFactor);
	//	System.out.println("totalpremium "+sumoftotalpremium);


		//return (GSVFactor * sumoftotalpremium );
		return (GSVFactor * cummulativePremium);
	}*/
	////Added By Akash///////////////////
	public double minimumguarrenteedSurrenderValue(double cumulativepremium,int year_F,int ppt)
	{
//		NewSmartSamriddhiDB smartsamriddhiDB = new NewSmartSamriddhiDB();
//		double[] gsvFactor = smartsamriddhiDB.getGSVFactors(policyTerm);
//		int position = 0;
//		double temp1=0;
//		GSVFactor = 0;
//		for (int policyYear = 1; policyYear <= 20; policyYear++) {
//			if (policyYear == year_F) {
//				GSVFactor = gsvFactor[position];
//				break;
//			}
//			position++;
//		}
//
//		if(year_F<=sgspProp.policyTerm) {
//			temp1=GSVFactor*cumulativepremium;
//		}
//		
//		//System.out.println("temp1   "+temp1);
//		
//		return (GSVFactor*cumulativepremium);
		NewSmartSamriddhiDB smartsamriddhiDB = new NewSmartSamriddhiDB();
//		double[] gsvFactor = smartsamriddhiDB.getGSVFactors(policyTerm);
		double[] gsvFactor = smartsamriddhiDB.getGSVFactors(ppt);
		int position = 0;
		double temp1=0;
		GSVFactor = 0;
		for (int policyYear = 1; policyYear <= 20; policyYear++) {
			if (policyYear == year_F) {
				GSVFactor = gsvFactor[position];
				break;
			}
			position++;
		}

		if(year_F<=sgspProp.policyTerm) {
			temp1=GSVFactor*cumulativepremium;
		}
		
		//System.out.println("temp1   "+temp1);
		
		return (GSVFactor*cumulativepremium);
	}
	
	///Added by Akash
	
	public double getTotalPremium(int year_f,double instalmentPremium,int ppt) {
		double totalPremium=0;
		if(year_f<=ppt) {
			totalPremium=instalmentPremium*monthvalue;
		}else {
			totalPremium=0;
		}
		//System.out.println("totalPremium  "+totalPremium);
		return totalPremium;
	}
	//Added By Akash///
	public double getvalue(String premfreq)
	{
		
		if(premfreq.equals("Monthly")) {
			monthvalue=12;
		}else {
			monthvalue=1;
		}
		return monthvalue;
	}
	/************ Added By tushar kotian on 3/8/2017 **************/
	
	 public double getBackDateInterest(double grossPremium,String bkDate)
		{
			
			try
			{
			
//			double indigoRate=7.5;
				/**
				 * Modified by Akshaya. Rate Change from 1-APR-2015
				 */
				
				/*** modified by Akshaya on 20-MAY-16 start **/
//				double indigoRate=10;
				double indigoRate=8.81;
				double ServiceTaxValue= ((sgspProp.serviceTax+sgspProp.SBCServiceTax+sgspProp.KKCServiceTax)+1)*100;
				
				/*** modified by Akshaya on 20-MAY-16 start **/

		       int compoundFreq=2;
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat dateformat1 = new SimpleDateFormat("dd-MM-yyyy");
			
			Date dtBackdate =  dateformat1.parse(bkDate);		
			String strBackDate=dateFormat.format(dtBackdate);
			
			
		    Calendar cal= Calendar.getInstance();
		    String date=dateFormat.format(cal.getTime());
		    
		    double netPremWithoutST=Math.round((grossPremium*100)/ServiceTaxValue);
	       int days= cfap.setDate(date, strBackDate);
	       double monthsBetween=cfap.roundDown((double)days/365*12,0);
	       
	       double interest=getCompoundAmount(monthsBetween, netPremWithoutST, indigoRate, compoundFreq);
	       return interest;
			}
			
			catch(Exception e)
			{
				return 0;
			}
		}

	/********Smart Guranteed saving end ******************/

		public double getCompoundAmount(double monthsBetween,
				double netPremWithoutST, double indigoRate, int compoundFreq) {
			double compoundAmount = netPremWithoutST
					* Math.pow((1 + (indigoRate / (100 * compoundFreq))),
							(compoundFreq * (monthsBetween / 12)))
					- netPremWithoutST;
			return compoundAmount;
			// System.out.println("compoundAmount "+compoundAmount);
		}

		
		public double getMinesOccuInterest(double SumAssured_Basic) {
			return (SumAssured_Basic / 1000) * 2;
		}

		/************ Added By sujata on 26-02-2020 **************/
		
//		//added by sujata on 28-11-2019
//				public double getSpecialSurrender(int Year_F,double sumAssured,double premiumAmt,double cummulativeGuaranteedAddition)
//				{
//					double a=0,b=0,c=0,ssvFactor=0,specialSurrenderVal=0;
//					NewSmartSamriddhiDB smartSamriddhiDB = new NewSmartSamriddhiDB();
//					double ppt = sgspProp.premiumPayingTerm;
//					
//					if (Year_F > sgspProp.premiumPayingTerm)
//					{
//						a=1;
//					}
//					else
//					{
//						a= Year_F/ppt;
//					}
//					//System.out.println("\nYear "+Year_F);
//					//System.out.println(" a "+a);
//					
//					b=(a*sumAssured);
//					
//					//System.out.println("b "+b);
//					//System.out.println("sumAssured "+sumAssured);
//				   // System.out.println("cummulativeGuaranteedAddition "+cummulativeGuaranteedAddition);
//					double[] SSVFactor = smartSamriddhiDB.getSSVFactors();
//					ssvFactor = SSVFactor[Year_F-1];
//				//	System.out.println("single temp "+ ssvFactor);	
//					int pos1=0;
//						for(int i= 1;i<=Year_F;i++ )
//						{
//							if(i == Year_F)
//							{
//								ssvFactor = SSVFactor[pos1];
//								//System.out.println("ssvFactor "+ssvFactor);
//								break;
//							}
//							pos1++;
//						}
//						
//					specialSurrenderVal= (b + cummulativeGuaranteedAddition) * ssvFactor;
//				//	System.out.println("\n specialSurrenderVal "+specialSurrenderVal);
//					return (specialSurrenderVal);
//					
//				}	
					
			
			

					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
			/*		double val, val2 , a ,specialSurrenderVal,ssvFactorVal;
					if(policyTerm == 12)
					{
						a =2;
					}
					else{
						a =3;
					}
					
					if(Year_F > premPayingTerm)
					{
						val= 1;
					}
					else
					{
						val = Year_F/premPayingTerm;
					}
//					System.out.println("\nYear_F "+Year_F);
//					System.out.println("premPayingTerm "+premPayingTerm);
//					System.out.println("val "+val);
//					System.out.println("SumAssuredBasic" + SumAssured_Basic);
					val2 = (val * SumAssured_Basic);
//					System.out.println("val2 "+val2);
					
					if(policyTerm == 12)
					{
					double[] singleSSV12 = db.getSSVFactor12();
					ssvFactor = singleSSV12[Year_F-1];
				//	System.out.println("single temp "+ ssvFactor);	
					int pos1=0;
						for(int i=1 ;i<=Year_F;i++ )
						{
							if(i == Year_F)
							{
								ssvFactor = singleSSV12[pos1];
								
								break;
							}
							pos1++;
						}	
						
					}
					else
					{
						double[] singleSSV15 = db.getSSVFactor15();
						ssvFactor = singleSSV15[Year_F-1];
					//	System.out.println("single temp "+ ssvFactor);	
						int pos1=0;
							for(int i= 1;i<=Year_F;i++ )
							{
								if(i == Year_F)
								{
									ssvFactor = singleSSV15[pos1];
									break;
								}
								pos1++;
							}
							
							
					}
					
//					System.out.println("");
//					System.out.println("val2 "+val2);
//					System.out.println("ssvFactor "+ssvFactor);
					specialSurrenderVal = (val2 + sumGuaranteedAddition)*ssvFactor;
//					System.out.println("specialSurrenderVal "+specialSurrenderVal);
					return specialSurrenderVal;
					
					} */
		
		
		public double getSpecialSurrender(int Year_F,double sumAssured,double premiumAmt,double cummulativeGuaranteedAddition,int policyTerm)
		{
			double a=0,b=0,c=0,ssvFactor=0,specialSurrenderVal=0,ssvFactor1=0,ssvFactor2=0,aa=0;
			NewSmartSamriddhiDB smartSamriddhiDB = new NewSmartSamriddhiDB();
			double ppt =0;
			
			if(policyTerm ==12){
				
				ppt = 6;
			}else if(policyTerm ==15){
				
				ppt = 7;
			}else {
				
				ppt = 10;
			}
			
//			if (Year_F > sgspProp.premiumPayingTerm)
			if (Year_F > ppt)
			{
				a=1;
			}
			else
			{
				a= Year_F/ppt;
			}
			//System.out.println("\nYear "+Year_F);
			//System.out.println(" a "+a);
			
			b=(a*sumAssured);
			
			//System.out.println("b "+b);
			//System.out.println("sumAssured "+sumAssured);
		   // System.out.println("cummulativeGuaranteedAddition "+cummulativeGuaranteedAddition);
			double[] SSVFactor = smartSamriddhiDB.getSSVFactors();
			ssvFactor = SSVFactor[Year_F-1];
			double[] SSVFactor1 = smartSamriddhiDB.getSSVFactors1();
			ssvFactor1 = SSVFactor1[Year_F-1];
			double[] SSVFactor2 = smartSamriddhiDB.getSSVFactors2();
			ssvFactor2 = SSVFactor2[Year_F-1];
		//	System.out.println("single temp "+ ssvFactor);	
			int pos1=0;
			if(ppt ==6){
				for(int i= 1;i<=Year_F;i++ )
				{
					if(i == Year_F)
					{
						ssvFactor = SSVFactor[pos1];
						aa =ssvFactor;
						//System.out.println("ssvFactor "+ssvFactor);
						break;
					}
					pos1++;
				}
			}else if(ppt ==7){
				
				for(int i= 1;i<=Year_F;i++ )
				{
					if(i == Year_F)
					{
						ssvFactor1 = SSVFactor1[pos1];
						aa =ssvFactor1;
						//System.out.println("ssvFactor "+ssvFactor);
						break;
					}
					pos1++;
				}
			}else{
				
				for(int i= 1;i<=Year_F;i++ )
				{
					if(i == Year_F)
					{
						ssvFactor2 = SSVFactor2[pos1];
						aa =ssvFactor2;
						//System.out.println("ssvFactor "+ssvFactor);
						break;
					}
					pos1++;
				}
			}
//			specialSurrenderVal= (b + cummulativeGuaranteedAddition) * ssvFactor;
			specialSurrenderVal= (b + cummulativeGuaranteedAddition) * aa;
		//	System.out.println("\n specialSurrenderVal "+specialSurrenderVal);
			return (specialSurrenderVal);
			
		}
		
}

