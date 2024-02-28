package com.sbilife.NewsmartsamriddhiVer3;

import com.sbilife.common.CommonForAllProd;

public class NewSmartSamriddhiVer3 
{
	NewSmartSamriddhiProperties prop;
	CommonForAllProd commonForAllProd;
	StringBuilder bussIll=null; 
	String BackDateinterest="0";
	StringBuilder retVal=null;
	String Isbackdate="";
	String backdate="";
	String Ismines="";
	double premiumAmt=0;
	public String CalculatePrem(String age,String gender,String premfreq,String premAmt,String IsBackdate ,String Backdate,String IsMines,String KFC,String policyTerm)
	{
		prop= new NewSmartSamriddhiProperties();
		commonForAllProd =  new CommonForAllProd();
		NewSmartSamriddhiBean smartsamriddhibean=new NewSmartSamriddhiBean();
		Isbackdate=IsBackdate;
		backdate=Backdate;
		Ismines=IsMines;
		try
		{
		
		smartsamriddhibean.setAge(Integer.parseInt(age));
		smartsamriddhibean.setGender(gender);			
		smartsamriddhibean.setPremiumAmt(Double.parseDouble(premAmt));
		smartsamriddhibean.setPremfreq(premfreq);
		smartsamriddhibean.setPolicyTerm(Integer.parseInt(policyTerm));
	//	smartsamriddhibean.setsumAssured(Double.parseDouble(sumAssured));
		//Show Smasmartsamriddhibeanrt Scholar Output Screen
		
		if((smartsamriddhibean.getPolicyTerm())==12){   
			smartsamriddhibean.setPremPayingTerm(6);
			}else if((smartsamriddhibean.getPolicyTerm())==15){
				smartsamriddhibean.setPremPayingTerm(7);
			}else{
				smartsamriddhibean.setPremPayingTerm(10);
			}
		
	//  Added By Saurabh Jain on 16/05/2019 Start
		
		smartsamriddhibean.setState(Boolean.parseBoolean(KFC));
		
	//  Added By Saurabh Jain on 16/05/2019 End
		
		}
		catch (Exception e) {
			// TODO: handle exception
			
			
		}
		
		return showSmartMoneyPlannerOutputPg(smartsamriddhibean);
		
	}
	
	public String showSmartMoneyPlannerOutputPg(NewSmartSamriddhiBean smartsamriddhibean)
	{
		//return getOutput("BI_of_Smart_Guaranteed_Savings_Plan",smartsamriddhibean);
		retVal=  new StringBuilder();
		//System.out.println(" retVal " + retVal);
		try
		{
			String[] outputArr = getOutput("BI_of_New_Smart_Samriddhi",smartsamriddhibean);
			
			
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
			retVal.append("<guratnedMatBen>" + outputArr[6] + "</guratnedMatBen>");	
			retVal.append("<GuarntdMatBeneft>" + outputArr[6] + "</GuarntdMatBeneft>");	//Parivartan
			
			
			retVal.append("<SAMF>" + outputArr[7] + "</SAMF>" +

					/******************* Modified by Akshaya on 03-MAR-2015 start **********/

					/**
					 * <backdateInt> tag value will be stored in
					 * nbd_backdate_int and Backdating date will be stored in
					 * nbd_backdating_date of T_new_business_detail. send Y or N
					 * for NBD_WISH_TO_BACKDATE.
					 */

					"<BackDateinterest>" + outputArr[8] + "</BackDateinterest>" +
					"<backdateInt>" + outputArr[8] + "</backdateInt>" +//Parivartan
					

					/******************* Modified by Akshaya on 03-MAR-2015 end **********/

			/*** modified by Akshaya on 20-MAY-16 start **/

			"<basicServiceTax>"  + outputArr[9]  + "</basicServiceTax>" +
    		"<SBCServiceTax>"  + outputArr[10]  + "</SBCServiceTax>" +
    		"<KKCServiceTax>"  + outputArr[11]  + "</KKCServiceTax>" +

			"<basicServiceTaxSecondYear>"  + outputArr[12]  + "</basicServiceTaxSecondYear>" +
    		"<SBCServiceTaxSecondYear>"  + outputArr[13]  + "</SBCServiceTaxSecondYear>" +
    		"<KKCServiceTaxSecondYear>"  + outputArr[14]  + "</KKCServiceTaxSecondYear>" +
    		"<MinesOccuInterest>"  + outputArr[15]  + "</MinesOccuInterest>" +
    		"<OccuInt>"  + outputArr[15]  + "</OccuInt>" +
    	    "<KeralaCessServiceTax>"  + outputArr[16]  + "</KeralaCessServiceTax>" +
    	    "<KeralaCessServiceTaxSecondYear>"  + outputArr[17]  + "</KeralaCessServiceTaxSecondYear>" +
    		
			
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
	
	
	/********************************** Calculations starts from here **********************************************************/ 


	public String[] getOutput(String sheetName,
			NewSmartSamriddhiBean smartsamriddhibean) {
		NewSmartSamriddhiBusinessLogic smartSamriddhiBusinessLogic = new NewSmartSamriddhiBusinessLogic(smartsamriddhibean);
		bussIll = new StringBuilder();
		String MinesOccuInterest = "0" ;
		int _year_F = 0;

		int year_F = 0;

		double yearlyBasePremiumPaid = 0, GuaranteedDeathBenefit = 0,GuaranteedSurvivalBenefit=0,

		GuaranteedMaturityBenefit = 0, cummulativePremium = 0,SurvivalBenefit=0,sumoftotalpremium=0,totalpremium=0, GuaranteedAddition = 0, GuaranteedSurrenderValue = 0,SpecialSurrenderValue=0, cummulativeGuaranteedAddition = 0;
		double TotalPremium=0,GetValue=0,SumOfGetValue=0;
		// From GUI Input
//=============================================================================================
		int age = smartsamriddhibean.getAge();
		int policyterm =smartsamriddhibean.getPolicyTerm();
		int ppt =smartsamriddhibean.getPremPayingTerm();
		 premiumAmt = smartSamriddhiBusinessLogic.getPremiumAmount(smartsamriddhibean.getPremfreq(),smartsamriddhibean.getPremiumAmount());
		String premiumAmtRound = commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(premiumAmt));
		
		double premiumRoundlevel2= Double.parseDouble(commonForAllProd.getRoundOffLevel2((commonForAllProd.getStringWithout_E(premiumAmt))));
		double premiumAmtRoundfinal= Double.parseDouble(premiumAmtRound);
		
		//System.out.println("premiumRoundlevel2 " + premiumRoundlevel2);
		//System.out.println("premiumAmtRoundfinal "+premiumAmtRoundfinal);
		
		
		
		
		//
		
		
		
		/*** modified by Akshaya on 20-MAY-16 start **/
	//  Added By Saurabh Jain on 16/05/2019 Start
		
		boolean state =smartsamriddhibean.getState();
		double basicServiceTax =0;
		double SBCServiceTax =0;
		double KKCServiceTax =0;
		double KerlaServiceTax =0;
		double KeralaCessServiceTax =0;
		
		if(state){
			 basicServiceTax = smartSamriddhiBusinessLogic.getServiceTax(premiumAmt,"basic");
			 KerlaServiceTax = smartSamriddhiBusinessLogic.getServiceTax(premiumAmt,"KERALA");
			 KeralaCessServiceTax =KerlaServiceTax-basicServiceTax;
		}else{
			 basicServiceTax = smartSamriddhiBusinessLogic.getServiceTax(premiumAmt,"basic");
			 SBCServiceTax = smartSamriddhiBusinessLogic.getServiceTax(premiumAmt,"SBC");
			 KKCServiceTax = smartSamriddhiBusinessLogic.getServiceTax(premiumAmt,"KKC");
		}
		
		String fyServiceTax = commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(basicServiceTax+SBCServiceTax+KKCServiceTax+KerlaServiceTax));
		
	//  Added By Saurabh Jain on 16/05/2019 End
	
		//double fyPremiumWithST = premiumAmtRoundfinal + (Double.parseDouble(fyServiceTax));
		double fyPremiumWithST = premiumAmtRoundfinal + (premiumAmtRoundfinal*0.0225)+(premiumAmtRoundfinal*0.0225);
		
		String fyPremiumWithSTRound = commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(fyPremiumWithST));
		double fyPremiumWithSTFinal = Double.parseDouble(fyPremiumWithSTRound);
		double fyPremiumWithSTFinallevel2 = Double.parseDouble(commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(fyPremiumWithST)));
//		double fyPremiumWithST = Double.valueOf(smartSamriddhiBusinessLogic
//				.setPremiumWithST(premiumAmt, prop.fyServiceTax));
//		double fyServiceTax = Double.valueOf(smartSamriddhiBusinessLogic.setServiceTax(
//				premiumAmt, fyPremiumWithST));

	//  Added By Saurabh Jain on 16/05/2019 Start
		
		double basicServiceTaxSecondYear =0;
		double SBCServiceTaxSecondYear = 0;
		double KKCServiceTaxSecondYear =0;
		double kerlaServiceTaxSecondYear=0;
		double KeralaCessServiceTaxSecondYear =0;
		
		if(state){
			 basicServiceTaxSecondYear = smartSamriddhiBusinessLogic.getServiceTaxSecondYear(premiumAmt,"basic");
			 kerlaServiceTaxSecondYear = smartSamriddhiBusinessLogic.getServiceTaxSecondYear(premiumAmt,"KERALA");
			 KeralaCessServiceTaxSecondYear =kerlaServiceTaxSecondYear-basicServiceTaxSecondYear;
		}else{
			 basicServiceTaxSecondYear = smartSamriddhiBusinessLogic.getServiceTaxSecondYear(premiumAmt,"basic");
			 SBCServiceTaxSecondYear = smartSamriddhiBusinessLogic.getServiceTaxSecondYear(premiumAmt,"SBC");
			 KKCServiceTaxSecondYear = smartSamriddhiBusinessLogic.getServiceTaxSecondYear(premiumAmt,"KKC");
		}
		
		
		double syServiceTax = basicServiceTaxSecondYear+SBCServiceTaxSecondYear+KKCServiceTaxSecondYear+kerlaServiceTaxSecondYear;
		
	 
		
		//double syPremiumWithST = premiumAmtRoundfinal+syServiceTax;
		double syPremiumWithST = premiumAmtRoundfinal + (premiumAmtRoundfinal*0.01125)+(premiumAmtRoundfinal*0.01125);
		
		String syPremiumWithSTRound = commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(syPremiumWithST));
		double syPremiumWithSTFinal = Double.parseDouble(syPremiumWithSTRound);
		double syPremiumWithSTFinallevel2 = Double.parseDouble(commonForAllProd.getRound(commonForAllProd.getStringWithout_E(syPremiumWithST)));
		
//		double syPremiumWithST = Double.valueOf(smartSamriddhiBusinessLogic
//				.setPremiumWithST(premiumAmt, prop.syServiceTax));
//		double syServiceTax = Double.valueOf(smartSamriddhiBusinessLogic.setServiceTax(
//				premiumAmt, syPremiumWithST));

		/******************* Modified by Akshaya on 05-FEB-2015 start **********/
//		double SAMFRate = smartSamriddhiBusinessLogic.setSAMFRate(age,policyterm);
//		
//		/*double sumAssured = Double.valueOf(smartSamriddhiBusinessLogic.setSumAssured(
//				smartsamriddhibean.getPremiumAmount(), SAMFRate));*/
//		
//		double sumAssured = Double.parseDouble(commonForAllProd.getRound(commonForAllProd.getStringWithout_E(smartSamriddhiBusinessLogic.setSumAssured1(
//				smartsamriddhibean.getPremiumAmount(), age ))));
		
double SAMFRate = smartSamriddhiBusinessLogic.setSAMFRate(age,policyterm);
		
		double sumAssured = Double.valueOf(smartSamriddhiBusinessLogic.setSumAssured1(
				smartsamriddhibean.getPremiumAmount(), SAMFRate));
//System.out.println(" sumAssured " +sumAssured);
//System.out.println(" getPremiumAmount " + smartsamriddhibean.getPremiumAmount());
		/******************* Modified by Akshaya on 05-FEB-2015 end **********/

		// System.out.println(fyPremiumWithST+" "+fyServiceTax+"  "+syPremiumWithST+"  "+syServiceTax
		// + " "+sumAssured);
		int rowNumber = 0, j = 0;
		try {
//			for (int i = 0; i < prop.policyTerm; i++) {
			for (int i = 0; i < policyterm; i++) {	
				rowNumber++;

				year_F = rowNumber;
				_year_F = year_F;
				 //System.out.println("1. year_F "+year_F);
				bussIll.append("<policyYr" + _year_F + ">" + _year_F
						+ "</policyYr" + _year_F + ">");

				yearlyBasePremiumPaid = smartSamriddhiBusinessLogic
						.setYearlyBasePremiumPaid(year_F, smartsamriddhibean.getPremiumAmount(),ppt);
				 //System.out.println("2.Total Base Premium Paid "+yearlyBasePremiumPaid);
				bussIll.append("<Annulizedpremium" + _year_F + ">"
						+ yearlyBasePremiumPaid + "</Annulizedpremium" + _year_F
						+ ">");

				 
				bussIll.append("<GuaranteedSurvivalBenefit" + _year_F + ">"
						+ GuaranteedSurvivalBenefit + "</GuaranteedSurvivalBenefit" + _year_F
						+ ">");
				
				
				
				cummulativePremium = cummulativePremium + yearlyBasePremiumPaid;
				
				
			 //System.out.println("3.cummulativePremium "+cummulativePremium);
				bussIll.append("<cummulatvePrem" + _year_F + ">"
						+ SurvivalBenefit + "</cummulatvePrem" + _year_F
						+ ">");
				
				totalpremium=smartSamriddhiBusinessLogic.settotalpremium(year_F);
				sumoftotalpremium=sumoftotalpremium+totalpremium;
				
				GuaranteedAddition = smartSamriddhiBusinessLogic.setGuaranteedAddition(
						smartsamriddhibean.getPremiumAmount(), cummulativePremium);
				cummulativeGuaranteedAddition = cummulativeGuaranteedAddition
						+ GuaranteedAddition;
				
				// System.out.println("4.cummulativeGuaranteedAddition "+
				//cummulativeGuaranteedAddition);
				 
				 
				bussIll.append("<guarntdAddtn" + _year_F + ">"
						+ GuaranteedAddition + "</guarntdAddtn" + _year_F + ">");

//				GuaranteedDeathBenefit = smartSamriddhiBusinessLogic
//						.setGuaranteedDeathBenefit(sumAssured, smartsamriddhibean.getPremiumAmount(),
//								cummulativeGuaranteedAddition,
//								cummulativePremium,policyterm);
				
				GuaranteedDeathBenefit = smartSamriddhiBusinessLogic
						.setGuaranteedDeathBenefit(sumAssured, smartsamriddhibean.getPremiumAmount(),
								cummulativeGuaranteedAddition,
								cummulativePremium,year_F,policyterm);
				// System.out.println("5.Guaranteed Death Benefit "+GuaranteedDeathBenefit);
				bussIll.append("<guarntdDeathBen" + _year_F + ">"
						+ GuaranteedDeathBenefit + "</guarntdDeathBen"
						+ _year_F + ">");

				GuaranteedMaturityBenefit = smartSamriddhiBusinessLogic
						.setGuaranteedMaturityBenefit(sumAssured,
								cummulativeGuaranteedAddition, year_F,policyterm);
				// System.out.println("6.Guaranteed Survival Benefit "+GuaranteedMaturityBenefit);
				bussIll.append("<GuaranteedMaturityBenefit" + _year_F + ">"
						+ GuaranteedMaturityBenefit + "</GuaranteedMaturityBenefit"
						+ _year_F + ">");

				//GuaranteedSurrenderValue = Math.round(smartSamriddhiBusinessLogic
				//		.setGuaranteedSurrenderValue(
				//				cummulativeGuaranteedAddition,
				//				cummulativePremium, year_F,sumoftotalpremium));
				
				GetValue=smartSamriddhiBusinessLogic.getvalue(smartsamriddhibean.getPremfreq());
				
				
				TotalPremium=smartSamriddhiBusinessLogic.getTotalPremium(_year_F, premiumAmt,ppt);
				SumOfGetValue=SumOfGetValue+TotalPremium;
				
//				GuaranteedSurrenderValue = Math.round(smartSamriddhiBusinessLogic
//						.minimumguarrenteedSurrenderValue(SumOfGetValue,
//								 year_F,smartsamriddhibean.getPolicyTerm()));
				GuaranteedSurrenderValue = Math.round(smartSamriddhiBusinessLogic
						.minimumguarrenteedSurrenderValue(SumOfGetValue,
								 year_F,ppt));
				
				// System.out.println("7.Guaranteed Surrender Value "+GuaranteedSurrenderValue);
				bussIll.append("<guarntdSurrndrVal" + _year_F + ">"
						+ GuaranteedSurrenderValue + "</guarntdSurrndrVal"
						+ _year_F + ">");
				
				SpecialSurrenderValue =  Math.round(smartSamriddhiBusinessLogic.getSpecialSurrender(year_F, sumAssured, premiumAmt, cummulativeGuaranteedAddition,policyterm));
				
				//System.out.println("SpecialSurrenderValue "+SpecialSurrenderValue);
				bussIll.append("<SpecialSurrenderValue" + _year_F + ">"
						+ SpecialSurrenderValue + "</SpecialSurrenderValue"
						+ _year_F + ">");
			}

			/****************************** Added by Akshaya on 03-MAR-15 start **********************/

//			if (rb_proposerdetail_personaldetail_backdating_yes.isChecked()) {
//				// BackDateinterest=commonForAllProd.getRoundUp(""+(shubhNiveshBussinesLogic.getBackDateInterest(Double.parseDouble(premiumSingleInstBasicWithST),
//				// "16-jan-2014")));
//				BackDateinterest = commonForAllProd.getRoundUp(""
//						+ (smartSamriddhiBusinessLogic.getBackDateInterest(
//								fyPremiumWithST,
//								btn_proposerdetail_personaldetail_backdatingdate
//										.getText().toString())));
//			} else {
//				BackDateinterest = "0";
//			}
//
//			fyPremiumWithST = fyPremiumWithST
//					+ Double.parseDouble(BackDateinterest);
			/****************************** Added by Akshaya on 03-MAR-15 end **********************/
			
			/************ Added By tushar kotian on 3/8/2017 **************/
			if (Isbackdate.equals("true")) {
				// BackDateinterest=commonForAllProd.getRoundUp(""+(shubhNiveshBussinesLogic.getBackDateInterest(Double.parseDouble(premiumSingleInstBasicWithST),
				// "16-jan-2014")));
				BackDateinterest = commonForAllProd.getRoundUp(""
						+ (smartSamriddhiBusinessLogic.getBackDateInterest(
								fyPremiumWithST,
								backdate)));
			} else {
				BackDateinterest = "0";
			}

			fyPremiumWithST = fyPremiumWithST
					+ Double.parseDouble(BackDateinterest);
			
			
			
			
			/************ Added By tushar kotian on 3/8/2017 **************/

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(Ismines.equals("true"))
		{
		MinesOccuInterest = smartSamriddhiBusinessLogic.getMinesOccuInterest(sumAssured)+"";
		}
		else
		{
			MinesOccuInterest ="0" ;
		}

		/****** modified by Akshaya on 20-MAY-16 start ****/
		return new String[] {//(premiumAmtRoundfinal,premiumAmt),fyPremiumWithSTFinal,fyPremiumWithST,premiumRoundlevel2
				
						//commonForAllProd.getStringWithout_E(premiumAmtRoundfinal)),
				
				(commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E((premiumAmt)))),
				(fyServiceTax),
				commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(fyPremiumWithSTFinal)),
				commonForAllProd.getStringWithout_E(syServiceTax),
				commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(syPremiumWithSTFinal)),
				commonForAllProd.getStringWithout_E(sumAssured),
				commonForAllProd.getStringWithout_E(GuaranteedMaturityBenefit),
				commonForAllProd.getStringWithout_E(SAMFRate), BackDateinterest,
				commonForAllProd.getStringWithout_E(basicServiceTax),
				commonForAllProd.getStringWithout_E(SBCServiceTax),
				commonForAllProd.getStringWithout_E(KKCServiceTax),
				commonForAllProd.getStringWithout_E(basicServiceTaxSecondYear),
				commonForAllProd.getStringWithout_E(SBCServiceTaxSecondYear),
				commonForAllProd.getStringWithout_E(KKCServiceTaxSecondYear),MinesOccuInterest,
				commonForAllProd.getStringWithout_E(KeralaCessServiceTax),
				commonForAllProd.getStringWithout_E(KeralaCessServiceTaxSecondYear)};

	}

	
	
}
