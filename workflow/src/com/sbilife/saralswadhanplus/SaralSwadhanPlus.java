package com.sbilife.saralswadhanplus;



import com.sbilife.common.CommonForAllProd;

public class SaralSwadhanPlus {
	
	
	//Variable declaration
	public int countEle=0,basicCoverTIY=0,ptaTIY=0,adbTIY=0,atpdbTIY=0,age=0,prempta=0,totalST=0;
	public String totInstPrem_exclST=null,SYServiceTax=null,FYServiceTax=null;
	public String FYtotInstPrem_inclST = "", SYtotInstPrem_inclST = "";
	StringBuilder bussIll = null;
	StringBuilder retVal = null;
	/**
	 * added by vrushali on 10-Dec-2015
	 */
	String totInstPrem_exclST_exclDisc = null,
			totInstPrem_exclST_exclDisc_exclFreqLoading = null;
	String staffStatus="", staffRebate="";
	String MinesOccuInterest="0";
	String BackDateinterest="0";
	
	public String CalculatePrem(String age,String gender,String policyTerm,String premFreq,String sumAssured,String KFC)
	{
		String retVal="";
		
		try
		{
		SaralSwadhanPlusBean saralSwadhanPlusBean=new SaralSwadhanPlusBean();

		saralSwadhanPlusBean.setState(Boolean.parseBoolean(KFC));

		saralSwadhanPlusBean.setAge(Integer.parseInt(age));
		//        System.out.println("* 1.1 *");
		saralSwadhanPlusBean.setGender(gender);
		//        System.out.println("* 1.1 *"+selGender.getSelectedItem().toString());
//		saralSwadhanPlusBean.setPlanType(plan);
		saralSwadhanPlusBean.setPremFreqMode(premFreq);
		saralSwadhanPlusBean.setPolicy_Term(Integer.parseInt(policyTerm));
		//        System.out.println("* 1.2 *"+Integer.parseInt(selBasicTerm.getSelectedItem().toString()));
		
		//        System.out.println("* 1.3 *");
		saralSwadhanPlusBean.setPremiumAmt(Integer.parseInt(sumAssured));
		//        System.out.println("* 1.3 *"+Integer.parseInt(basicSA.getText().toString()));


		//Show Output Form
		
		retVal = showSaralSwadhanOutputPg(saralSwadhanPlusBean);
		
		}
		catch(Exception e)
		{
			retVal = "<SmartSwadhanPlus>" +
					"<errCode>1</errCode>" +
					"<errorMessage>" + e.getMessage() + "</errorMessage>"+
					"</SmartSwadhanPlus>";
		}
		return retVal;
	}

	
	/********************************** Output starts here **********************************************************/ 
	
	
	//Display Saral Swadhan Output Screen
	public String showSaralSwadhanOutputPg(SaralSwadhanPlusBean saralswadhanbean)
	{
		
		retVal = new StringBuilder();
		CommonForAllProd commonForAllProd=new CommonForAllProd();

		//ArrayList<String[]> arrList=new ArrayList<String[]>();
		String[] outputArr=getOutput("BI_of_Saral_Swadhan_Plus",saralswadhanbean);
		  
//		Intent i = new Intent(getApplicationContext(),SaralSwadhanPlusSuccess.class);
		
		  try
   			{		
		        retVal.append("<?xml version='1.0' encoding='utf-8' ?><SaralSwadhanPlus>");
		        retVal.append("<errCode>0</errCode>");
		        
		     	 				  
						
				
		        retVal.append("<sumAssured>"+ outputArr[0]  + "</sumAssured>" + 
		        		"<benefitPaybleAtDeath>" + outputArr[1] + "</benefitPaybleAtDeath>" +
		        		"<benefitPaybleAtMaturity>" + outputArr[2] + "</benefitPaybleAtMaturity>" +
		        		"<SAMF>" + outputArr[3] + "</SAMF>" +
		        		"<basicPrem>" +(commonForAllProd.getRoundOffLevel2(outputArr[4])) +"</basicPrem>"+
		    			"<fyServiceTax>" + outputArr[5] +"</fyServiceTax>"+ 
		    			"<fyPremium>" + outputArr[6] +"</fyPremium>"+
		    			"<SYServiceTax>" + outputArr[7] +"</SYServiceTax>"+
		    			"<SYtotInstPrem_inclST>" + outputArr[8] +"</SYtotInstPrem_inclST>"+
		         bussIll.toString());
		        retVal.append("</SaralSwadhanPlus>");

		String str=retVal.toString().replaceAll(bussIll.toString(), "");
//		System.out.println(" str : "+str);
			}
			catch(Exception e)
			{
				retVal.append("<?xml version='1.0' encoding='utf-8' ?><SaralSwadhanPlus>" 
							+ "<errCode>1</errCode>" +
						"<errorMessage>" + e.getMessage() + "</errorMessage></SaralSwadhanPlus>");
			}
	//  arrList.add(outputArr);
      //  arrList.add(outputReductionYield);
       
	       
				/*********************************************** xml Output *************************************/
				
		// System.out.println("Final output in xml" + retVal.toString());
						
				
					  
     
//	    	i.putExtra("op"," Sum Assured is Rs." + currencyFormat.format(Double.parseDouble(outputArr[0])));
//			i.putExtra("op1", "Benefit Paybale At Death is Rs." + currencyFormat.format(Double.parseDouble(outputArr[1])));
//			i.putExtra("op2","Benefit Paybale At Maturity is Rs." + currencyFormat.format(Double.parseDouble(outputArr[2])));	
//		
//		
//		
//		 i.putExtra("header", inputActivityHeader.getText().toString());
//		 
//		 if(progressDialog.isShowing())
//		 		progressDialog.dismiss();
//		 startActivity(i);
		 
		return retVal.toString();
	}
	
	/********************************** Output ends here **********************************************************/ 

	/********************************** Calculations starts from here **********************************************************/ 


	public String[] getOutput(String sheetName,SaralSwadhanPlusBean saralswadhanbean)
	{
		bussIll = new StringBuilder();

		int _year_F = 0;

		int year_F = 0;
		// from GUI input
		CommonForAllProd commonForAllProd=new CommonForAllProd();

		int age = saralswadhanbean.getAge();
		int policyTerm = saralswadhanbean.getPolicy_Term();
		String premFreqMode = saralswadhanbean.getPremFreqMode();
		double premium =saralswadhanbean.getPremiumAmt();
		double sumAssured = 0, _sumAssured = 0, _annualPrem = 0, benefitPaybleAtDeath = 0, benefitPaybleAtMaturity = 0, SAMF = 0;

		SaralSwadhanPlusBusinessLogic BIMAST = new SaralSwadhanPlusBusinessLogic();

		int maturityAge = BIMAST.setMaturityAge(policyTerm, age);
		BIMAST.setAgeGroup(age);

		/******************* Modified by Akshaya on 05-FEB-2015 Start **********/
		SAMF = BIMAST.setSAMF(policyTerm);
		sumAssured = BIMAST.setSumAssured(SAMF, premium);

		/******************* Modified by Akshaya on 05-FEB-2015 End **********/
		

		boolean state =saralswadhanbean.getState();
		
		double survival_Benefits =0, other_Benefits_if_any =0;
		
		double basicServiceTaxSecondYear=0,SBCServiceTaxSecondYear=0,KKCServiceTaxSecondYear=0,sumcummulativePremiumPaid=0,
				guaranSurrenderValue=0,nonGuaranSurrenderValue=0;
		
		double kerlaServiceTaxSecondYear=0;
		double KeralaCessServiceTaxSecondYear =0;
		
		double basicServiceTax =0;
		double SBCServiceTax =0;
		double KKCServiceTax =0;
		double kerlaServiceTax=0;
		double KeralaCessServiceTax=0;
		
		if(state){
			 basicServiceTax = BIMAST.getServiceTax(premium,"basic");
			 kerlaServiceTax = BIMAST.getServiceTax(premium,"KERALA");
			 KeralaCessServiceTax =kerlaServiceTax-basicServiceTax;
		}else{
			 basicServiceTax = BIMAST.getServiceTax(premium,"basic");
			 SBCServiceTax = BIMAST.getServiceTax(premium,"SBC");
			 KKCServiceTax = BIMAST.getServiceTax(premium,"KKC");
		}
		
		
		
		FYServiceTax =commonForAllProd.getStringWithout_E(basicServiceTax+SBCServiceTax+KKCServiceTax+kerlaServiceTax);
		
	//  Added By Saurabh Jain on 15/05/2019 End
		
		FYtotInstPrem_inclST = commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(premium
				+ Double.parseDouble(FYServiceTax)));
		
		
		//  Added By Saurabh Jain on 15/05/2019 Start
			if(state){
			
			basicServiceTaxSecondYear = BIMAST.getServiceTaxSecondYear(premium,"basic");
			kerlaServiceTaxSecondYear = BIMAST.getServiceTaxSecondYear(premium,"KERALA");
			KeralaCessServiceTaxSecondYear =kerlaServiceTaxSecondYear-basicServiceTaxSecondYear;
			
			SYServiceTax = commonForAllProd.getStringWithout_E(basicServiceTaxSecondYear+kerlaServiceTaxSecondYear);
		
		}else{
			basicServiceTaxSecondYear = BIMAST.getServiceTaxSecondYear(premium,"basic");
			SBCServiceTaxSecondYear = BIMAST.getServiceTaxSecondYear(premium,"SBC");
			KKCServiceTaxSecondYear = BIMAST.getServiceTaxSecondYear(premium,"KKC");
			
			SYServiceTax = commonForAllProd.getStringWithout_E(basicServiceTaxSecondYear+SBCServiceTaxSecondYear+KKCServiceTaxSecondYear);
			
		}
			
		SYtotInstPrem_inclST =commonForAllProd.getRoundOffLevel2 (commonForAllProd
				.getStringWithout_E(premium
						+ Double.parseDouble(SYServiceTax)));
		
		int rowNumber = 0, j = 0;

		for (int i = 0; i < policyTerm; i++)
		// for(int i=0;i<1;i++)
		{
			rowNumber++;

			year_F = rowNumber;
			_year_F = year_F;
			// System.out.println("1. year_F "+year_F);
			bussIll.append("<policyYr" + _year_F + ">" + _year_F + "</policyYr"
					+ _year_F + ">");

			_annualPrem = BIMAST.setAnnualPrem(premium, year_F);
			// System.out.println("2.Annual Premium"+_annualPrem);
			bussIll.append("<AnnPrem" + _year_F + ">" + commonForAllProd.getRound(""
					+ commonForAllProd
					.getStringWithout_E( _annualPrem))
					+ "</AnnPrem" + _year_F + ">");

			sumcummulativePremiumPaid = sumcummulativePremiumPaid
					+ _annualPrem;
			
			/*_sumAssured = BIMAST.setSumAssured(sumAssured, year_F, policyTerm);
	          // System.out.println("3.Sum Assured "+_sumAssured);
			bussIll.append("<sumAssured" + _year_F + ">" + _sumAssured
					+ "</sumAssured" + _year_F \+ ">");*/
			
			bussIll.append("<SurvivalBenefits" + _year_F + ">"
					+ commonForAllProd.getStringWithout_E(survival_Benefits)
					+ "</SurvivalBenefits" + _year_F + ">");
			
			bussIll.append("<OtherBenefitsifAny" + _year_F + ">"
					+ commonForAllProd.getStringWithout_E(other_Benefits_if_any)
					+ "</OtherBenefitsifAny" + _year_F + ">");

			benefitPaybleAtDeath = BIMAST.setBenefitPaybleAtDeath(sumAssured,
					year_F, policyTerm);
			// System.out.println("4.benefit Payble At Death "+benefitPaybleAtDeath);
			bussIll.append("<benefitPaybleAtDeath" + _year_F + ">"
					+ commonForAllProd.getRound(""
							+ commonForAllProd
							.getStringWithout_E(benefitPaybleAtDeath)) + "</benefitPaybleAtDeath" + _year_F
					+ ">");

			benefitPaybleAtMaturity = BIMAST.setbenefitPaybleAtMaturity(
					premium, year_F, policyTerm);
			// System.out.println("5.benefit Payble At Maturity   : "+benefitPaybleAtMaturity);
			bussIll.append("<benefitPaybleAtMaturity" + _year_F + ">"
					+commonForAllProd.getRound(""
							+ commonForAllProd
							.getStringWithout_E( benefitPaybleAtMaturity)) + "</benefitPaybleAtMaturity"
					+ _year_F + ">");
			
			guaranSurrenderValue = BIMAST.setGuaranteedSurrenderValue(sumcummulativePremiumPaid,
							year_F,policyTerm);
			
			bussIll.append("<guaranSurrenderValue"
					+ _year_F
					+ ">"
//					+ commonForAllProd.getRoundUp(""
					+ commonForAllProd.getRound(""
							+ commonForAllProd
									.getStringWithout_E(guaranSurrenderValue))
					+ "</guaranSurrenderValue" + _year_F + ">");
			
			nonGuaranSurrenderValue = BIMAST
					.setNonGuaranteedSurrenderValue(year_F, sumcummulativePremiumPaid,policyTerm);
//			System.out.println("6.nonGuaranSurrenderValue : "
//					+ nonGuaranSurrenderValue);
			bussIll.append("<nonGuaranSurrenderValue"
					+ _year_F
					+ ">"
//					+ commonForAllProd.getRoundUp(""
					+ commonForAllProd.getRound(""
							+ commonForAllProd
									.getStringWithout_E(nonGuaranSurrenderValue))
					+ "</nonGuaranSurrenderValue" + _year_F + ">");

		}

		/******************* Modified by Akshaya on 05-FEB-2015 Start **********/
		return new String[] {
				(commonForAllProd.getStringWithout_E(sumAssured)),
				commonForAllProd.getStringWithout_E(benefitPaybleAtDeath),
				commonForAllProd.getStringWithout_E(benefitPaybleAtMaturity),
				commonForAllProd.getStringWithout_E(SAMF),
				commonForAllProd.getStringWithout_E(premium),
				(FYServiceTax),
				(FYtotInstPrem_inclST),
				SYServiceTax,
				SYtotInstPrem_inclST};
		/******************* Modified by Akshaya on 05-FEB-2015 end **********/
	}
	

}
