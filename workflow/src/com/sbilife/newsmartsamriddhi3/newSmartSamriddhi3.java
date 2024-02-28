package com.sbilife.newsmartsamriddhi3;



import com.sbilife.common.CommonForAllProd;

public class newSmartSamriddhi3 {
	newSmartSamriddhiProperties prop;
	CommonForAllProd commonForAllProd;
	StringBuilder bussIll=null; 
	StringBuilder retVal=null;
	double premiumAmt=0;
	public String CalculatePrem(String age,String gender,String premiumFreq,String premium,String state,String policyTerm)
	{   
		prop= new newSmartSamriddhiProperties();
		commonForAllProd =  new CommonForAllProd();
		
		newSmartSamriddhiBean bean = new newSmartSamriddhiBean();
		try {
		bean.setAge(Integer.parseInt(age));
		bean.setGender(gender);			
		bean.setPremium(Double.parseDouble(premium));
		bean.setPremiumfreq(premiumFreq);
		bean.setPolicyTerm(Integer.parseInt(policyTerm));
		bean.setState(Boolean.parseBoolean(state));
		}
		catch(Exception e)
		{
			
		}
		return showSmartSamriddhiOutput(bean);

	}
	public String showSmartSamriddhiOutput(newSmartSamriddhiBean bean)
	{
		retVal= new StringBuilder();
		try
		{
			String[] outputArr = getOutput("BI_of_New_Smart_Samriddhi",bean);
		retVal.append("<?xml version='1.0' encoding='utf-8' ?><NewSmartSamriddhi>");
		retVal.append("<errCode>0</errCode>");
		retVal.append("<basicPrem>" + outputArr[0] + "</basicPrem>");
		retVal.append("<installmntPrem>" + outputArr[0] + "</installmntPrem>");//Parivartan
		retVal.append("<firtYrServTax>" + outputArr[1] + "</firtYrServTax>"+ "<FYServTax>" + outputArr[1] + "</FYServTax>");
		retVal.append("<firtYrPremWithTax>" + outputArr[2] + "</firtYrPremWithTax>");
		retVal.append("<FYPrem>" + outputArr[2] + "</FYPrem>");
		retVal.append("<secYrServTax>" + outputArr[3] + "</secYrServTax>");
		retVal.append("<SYServTax>" + outputArr[3] + "</SYServTax>");//Parivartan
		
		retVal.append("<secYrPremWithTax>" + outputArr[4] + "</secYrPremWithTax>");
		retVal.append("<SYPrem>" + outputArr[4] + "</SYPrem>");//Parivartan
		retVal.append("<sumAssured>" + outputArr[5] + "</sumAssured>");
		
		retVal.append("<GuarntdMatBeneft>" + outputArr[6] + "</GuarntdMatBeneft>");	//Parivartan
		
		
		retVal.append("<SAMF>" + outputArr[7] + "</SAMF>" +
			
		"<basicServiceTax>"  + outputArr[8]  + "</basicServiceTax>" +
		"<SBCServiceTax>"  + outputArr[9]  + "</SBCServiceTax>" +
		"<KKCServiceTax>"  + outputArr[10]  + "</KKCServiceTax>" +

		"<basicServiceTaxSecondYear>"  + outputArr[11]  + "</basicServiceTaxSecondYear>" +
		"<SBCServiceTaxSecondYear>"  + outputArr[12]  + "</SBCServiceTaxSecondYear>" +
		"<KKCServiceTaxSecondYear>"  + outputArr[13]  + "</KKCServiceTaxSecondYear>" +
	    "<KeralaCessServiceTax>"  + outputArr[14]  + "</KeralaCessServiceTax>" +
	    "<KeralaCessServiceTaxSecondYear>"  + outputArr[15]  + "</KeralaCessServiceTaxSecondYear>" +
		
		
		/*** modified by Akshaya on 20-MAY-16 end **/
				
				bussIll.toString());
		retVal.append("</NewSmartSamriddhi>");
	}
	catch (Exception e) {
		// TODO: handle exception
		
		retVal.append("<?xml version='1.0' encoding='utf-8' ?><SmartSamriddhi>" 
				+ "<errCode>1</errCode>" +
				"<errorMessage>" + e.getMessage() + "</errorMessage></NewSmartSamriddhi>");
	}
	return retVal.toString();
	}
	public String[] getOutput(String sheetName,
			newSmartSamriddhiBean bean) {
		newSmartSamriddhiLogic log= new newSmartSamriddhiLogic();
		bussIll = new StringBuilder();
		
		int _year_F = 0;

		int year_F = 0;

		double yearlyBasePremiumPaid = 0, GuaranteedDeathBenefit = 0,GuaranteedSurvivalBenefit=0,

		GuaranteedMaturityBenefit = 0, cummulativePremium = 0,SurvivalBenefit=0,sumoftotalpremium=0,totalpremium=0, GuaranteedAddition = 0, GuaranteedSurrenderValue = 0,SpecialSurrenderValue=0, cummulativeGuaranteedAddition = 0;
		double TotalPremium=0,GetValue=0,SumOfGetValue=0 ;
		int premiumPayingTerm=0;
		
//=============================================================================================
		int age = bean.getAge();
		int policyterm =bean.getPolicyTerm();
		
		premiumPayingTerm = log.getPremiumPayingTerm(policyterm);
		 premiumAmt = log.getPremium(bean.getPremiumfreq(),bean.getPremium());
		String premiumAmtRound = commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(premiumAmt));
		
		double premiumRoundlevel2= Double.parseDouble(commonForAllProd.getRoundOffLevel2((commonForAllProd.getStringWithout_E(premiumAmt))));
		double premiumAmtRoundfinal= Double.parseDouble(premiumAmtRound);
	
		
		
		/*** modified by Akshaya on 20-MAY-16 start **/
	//  Added By Saurabh Jain on 16/05/2019 Start
		
		boolean state =bean.isState();
		double basicServiceTax =0;
		double SBCServiceTax =0;
		double KKCServiceTax =0;
		double KerlaServiceTax =0;
		double KeralaCessServiceTax =0;
		
		if(state){
			 basicServiceTax = log.getServiceTaxFirstYear(premiumAmt,"basic");
			 KerlaServiceTax = log.getServiceTaxFirstYear(premiumAmt,"KERALA");
			 KeralaCessServiceTax =KerlaServiceTax-basicServiceTax;
		}else{
			 basicServiceTax = log.getServiceTaxFirstYear(premiumAmt,"basic");
			 SBCServiceTax = log.getServiceTaxFirstYear(premiumAmt,"SBC");
			 KKCServiceTax = log.getServiceTaxFirstYear(premiumAmt,"KKC");
		}
		
		String fyServiceTax = commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(basicServiceTax+SBCServiceTax+KKCServiceTax+KerlaServiceTax));
		
	double fyPremiumWithST = premiumAmtRoundfinal + (premiumAmtRoundfinal*0.0225)+(premiumAmtRoundfinal*0.0225);
		
		String fyPremiumWithSTRound = commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(fyPremiumWithST));
		double fyPremiumWithSTFinal = Double.parseDouble(fyPremiumWithSTRound);
		double fyPremiumWithSTFinallevel2 = Double.parseDouble(commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(fyPremiumWithST)));
           double basicServiceTaxSecondYear =0;
		double SBCServiceTaxSecondYear = 0;
		double KKCServiceTaxSecondYear =0;
		double kerlaServiceTaxSecondYear=0;
		double KeralaCessServiceTaxSecondYear =0;
		
		if(state){
			 basicServiceTaxSecondYear = log.getServiceTaxSecondYear(premiumAmt,"basic");
			 kerlaServiceTaxSecondYear = log.getServiceTaxSecondYear(premiumAmt,"KERALA");
			 KeralaCessServiceTaxSecondYear =kerlaServiceTaxSecondYear-basicServiceTaxSecondYear;
		}else{
			 basicServiceTaxSecondYear = log.getServiceTaxSecondYear(premiumAmt,"basic");
			 SBCServiceTaxSecondYear = log.getServiceTaxSecondYear(premiumAmt,"SBC");
			 KKCServiceTaxSecondYear = log.getServiceTaxSecondYear(premiumAmt,"KKC");
		}
		
		
		double syServiceTax = basicServiceTaxSecondYear+SBCServiceTaxSecondYear+KKCServiceTaxSecondYear+kerlaServiceTaxSecondYear;
		
	 
		
		//double syPremiumWithST = premiumAmtRoundfinal+syServiceTax;
		double syPremiumWithST = premiumAmtRoundfinal + (premiumAmtRoundfinal*0.01125)+(premiumAmtRoundfinal*0.01125);
		
		String syPremiumWithSTRound = commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(syPremiumWithST));
		double syPremiumWithSTFinal = Double.parseDouble(syPremiumWithSTRound);
		double syPremiumWithSTFinallevel2 = Double.parseDouble(commonForAllProd.getRound(commonForAllProd.getStringWithout_E(syPremiumWithST)));
		

double SAMFRate = log.setSAMFRate(age,policyterm);
		
		double sumAssured = Double.valueOf(log.setSumAssure(
				premiumAmt, SAMFRate));
	// + " "+sumAssured);
		int rowNumber = 0, j = 0;
		try {
//			
			for (int i = 0; i < policyterm; i++) {	
				rowNumber++;

				year_F = rowNumber;
				_year_F = year_F;
				 //System.out.println("1. year_F "+year_F);
				bussIll.append("<policyYr" + _year_F + ">" + _year_F
						+ "</policyYr" + _year_F + ">");

				yearlyBasePremiumPaid = log
						.setAnnualBasedPremium(year_F, premiumAmt,premiumPayingTerm);
				bussIll.append("<Annulizedpremium" + _year_F + ">"
						+ yearlyBasePremiumPaid + "</Annulizedpremium" + _year_F
						+ ">");

				
				 
				bussIll.append("<GuaranteedSurvivalBenefit" + _year_F + ">"
						+ GuaranteedSurvivalBenefit + "</GuaranteedSurvivalBenefit" + _year_F
						+ ">");
				
				
				
				cummulativePremium = cummulativePremium + yearlyBasePremiumPaid;
			
				bussIll.append("<cummulatvePrem" + _year_F + ">"
						+ cummulativePremium+ "</cummulatvePrem" + _year_F
						+ ">");
				
				
				totalpremium=log.getTotalPremium(year_F, yearlyBasePremiumPaid, premiumPayingTerm);
				sumoftotalpremium=sumoftotalpremium+totalpremium;
				
				GuaranteedAddition =log.setGuaranteedAddition(premiumAmt, cummulativePremium);
				cummulativeGuaranteedAddition = cummulativeGuaranteedAddition
						+ GuaranteedAddition;
				
			
				 
				bussIll.append("<guarntdAddtn" + _year_F + ">"
						+ GuaranteedAddition + "</guarntdAddtn" + _year_F + ">");

			
				GuaranteedDeathBenefit = log
						.setGuaranteedDeathBenefit(sumAssured, premiumAmt,
								cummulativeGuaranteedAddition,
								cummulativePremium,year_F,policyterm);
				
				bussIll.append("<guarntdDeathBen" + _year_F + ">"
						+ GuaranteedDeathBenefit + "</guarntdDeathBen"
						+ _year_F + ">");

				GuaranteedMaturityBenefit = log
						.setGuaranteedMaturityBenefit(sumAssured,
								cummulativeGuaranteedAddition, year_F,policyterm);
				
				bussIll.append("<GuaranteedMaturityBenefit" + _year_F + ">"
						+ GuaranteedMaturityBenefit + "</GuaranteedMaturityBenefit"
						+ _year_F + ">");

				
				GetValue=log.getValue(bean.getPremiumfreq());
				
				
				TotalPremium=log.getTotalPremium(_year_F, premiumAmt,premiumPayingTerm);
				SumOfGetValue=SumOfGetValue+TotalPremium;
				
                  GuaranteedSurrenderValue = Math.round(log
						.minimumguarrenteedSurrenderValue(SumOfGetValue,
								 year_F,premiumPayingTerm));
				
				
				bussIll.append("<guarntdSurrndrVal" + _year_F + ">"
						+ GuaranteedSurrenderValue + "</guarntdSurrndrVal"
						+ _year_F + ">");
				
				SpecialSurrenderValue = Math.round( log.getSpecialSurrenderValue(year_F, sumAssured, premiumAmt, cummulativeGuaranteedAddition, policyterm));
				
			bussIll.append("<SpecialSurrenderValue" + _year_F + ">"
						+ SpecialSurrenderValue + "</SpecialSurrenderValue"
						+ _year_F + ">");
			}


			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/****** modified by Akshaya on 20-MAY-16 start ****/
		return new String[] {
				(commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E((premiumAmt)))),
				(fyServiceTax),
				commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(fyPremiumWithSTFinal)),
				commonForAllProd.getStringWithout_E(syServiceTax),
				commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(syPremiumWithSTFinal)),
				commonForAllProd.getStringWithout_E(sumAssured),
				commonForAllProd.getStringWithout_E(GuaranteedMaturityBenefit),
				commonForAllProd.getStringWithout_E(SAMFRate),
				commonForAllProd.getStringWithout_E(basicServiceTax),
				commonForAllProd.getStringWithout_E(SBCServiceTax),
				commonForAllProd.getStringWithout_E(KKCServiceTax),
				commonForAllProd.getStringWithout_E(basicServiceTaxSecondYear),
				commonForAllProd.getStringWithout_E(SBCServiceTaxSecondYear),
				commonForAllProd.getStringWithout_E(KKCServiceTaxSecondYear),
				commonForAllProd.getStringWithout_E(KeralaCessServiceTax),
				commonForAllProd.getStringWithout_E(KeralaCessServiceTaxSecondYear)};

	}

	
}