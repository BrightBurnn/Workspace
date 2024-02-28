package com.sbilife.smartmoneyplannerv3;

import com.sbilife.common.CommonForAllProd;

public class SmartMoneyPlannerVersion3 {

	StringBuilder bussIll = null;
	StringBuilder retVal = null;
	CommonForAllProd commonForAllProd = null;
	SmartMoneyPlannerProperties prop = null;
	String Isbackdate = "";
	String backdate = "";
	String Ismines = "";
	String cashierDate = "";
	String premiumWithST_WithoutBkdate = "";

	public String CalculatePrem(String isStaff, String isJKResident, String age, String gender, String plan,
			String premFreq, String policyTerm, String premPayingTerm, String sumAssured, String IsBackdate,
			String Backdate, String IsMines, String cashierDate) {

		String retVal = null;
		commonForAllProd = new CommonForAllProd();
		Isbackdate = IsBackdate;
		backdate = Backdate;
		Ismines = IsMines;
		this.cashierDate = cashierDate;

		// Insert data entered by user in an object
		SmartMoneyPlannerBean smartmoneyplannerbean = new SmartMoneyPlannerBean();

		smartmoneyplannerbean.setIsForStaffOrNot(Boolean.parseBoolean(isStaff));

		smartmoneyplannerbean.setIsJKResidentDiscOrNot(Boolean.parseBoolean(isJKResident));

		smartmoneyplannerbean.setAge(Integer.parseInt(age));

		smartmoneyplannerbean.setGender(gender);

		/** Commented by Akshaya on 19-Jun-15 **/
//		if(plan.equals("Plan 1"))
//			smartmoneyplannerbean.setPlan(1);
//		else if(plan.equals("Plan 2"))
//			smartmoneyplannerbean.setPlan(2);
//		else if(plan.equals("Plan 3"))
//			smartmoneyplannerbean.setPlan(3);
//		else
//			smartmoneyplannerbean.setPlan(4);

		smartmoneyplannerbean.setPlan(Integer.parseInt(plan));

		smartmoneyplannerbean.setPremFreqMode(premFreq);
		smartmoneyplannerbean.setPolicyTerm_Basic(Integer.parseInt(policyTerm));

		smartmoneyplannerbean.setPremiumPayingTerm(Integer.parseInt(premPayingTerm));

		smartmoneyplannerbean.setsumAssured(Double.parseDouble(sumAssured));

		// Show Smart Scholar Output Screen
		retVal = showSmartMoneyPlannerOutputPg(smartmoneyplannerbean);

		return retVal;
	}

	/**********************************
	 * Output starts here
	 **********************************************************/
	// Display Smart Scholar Output Screen
	public String showSmartMoneyPlannerOutputPg(SmartMoneyPlannerBean smartmoneyplannerbean) {
		prop = new SmartMoneyPlannerProperties();
		// Variable declaration
		String staffStatus = "";
		StringBuilder retVal = new StringBuilder();
		String[] outputArr = getOutput("BI_of_Smart_Money_Planner", smartmoneyplannerbean);
		/*** Added by Akshaya on 04-AUG-15 start ***/
		if (smartmoneyplannerbean.getIsForStaffOrNot()) {
			staffStatus = "sbi";
			// disc_Basic_SelFreq
		} else
			staffStatus = "none";
		/*** Added by Akshaya on 04-AUG-15 end ***/
		try {
			// Display output on next page
			retVal.append("<?xml version='1.0' encoding='utf-8' ?><smartMoneyPlanner>");
			retVal.append("<errCode>0</errCode>");

			/*** Added by Akshaya on 04-AUG-15 start ***/
			retVal.append("<staffStatus>" + staffStatus + "</staffStatus>");
			retVal.append("<staffRebate>" + outputArr[6] + "</staffRebate>");
			retVal.append("<basicPremWithoutDisc>" + outputArr[8] + "</basicPremWithoutDisc>");
			retVal.append("<InstmntPrem>" + outputArr[8] + "</InstmntPrem>");
			retVal.append("<OccuInt>" + outputArr[9] + "</OccuInt>");
			retVal.append("<backdateInt>" + outputArr[22] + "</backdateInt>");
			retVal.append("<basicPrem>" + commonForAllProd.getRoundUp(outputArr[0]) + "</basicPrem>");
			retVal.append("<basicPremWithoutRound>"
					+ commonForAllProd
							.getRoundOffLevel2New(commonForAllProd.getStringWithout_E(Double.parseDouble(outputArr[0])))
					+ "</basicPremWithoutRound>");
			retVal.append("<basicPremWithoutSTWithoutLoading>"
					+ commonForAllProd
							.getRoundOffLevel2(commonForAllProd.getStringWithout_E(Double.parseDouble(outputArr[23])))
					+ "</basicPremWithoutSTWithoutLoading>");

			// basicPremWithoutSTWithoutLoading

			retVal.append("<servTax>" + outputArr[1] + "</servTax>");
			retVal.append("<installmntPremWithSerTx>" + outputArr[2] + "</installmntPremWithSerTx>" + "<premWthST>"
					+ outputArr[2] + "</premWthST>");
			retVal.append("<guarMatBenefit>" + outputArr[3] + "</guarMatBenefit>");
			retVal.append("<nonGuarMatBenefit4>" + outputArr[4] + "</nonGuarMatBenefit4>");
			retVal.append("<nonGuarMatBenefit8>" + outputArr[5] + "</nonGuarMatBenefit8>");
			/**** Added By - Priyanka Warekar - 26-08-2015 - Start ****/
			retVal.append("<premInstSecondYear>" + outputArr[0] + "</premInstSecondYear>" + "<servcTaxSecondYear>"
					+ outputArr[11] + "</servcTaxSecondYear>" + "<premWthSTSecondYear>" + outputArr[12]
					+ "</premWthSTSecondYear>" +
					/**** Added By - Priyanka Warekar - 26-08-2015 - End ****/

					/*** modified by Akshaya on 20-MAY-16 start **/

					"<basicServiceTax>" + outputArr[13] + "</basicServiceTax>" + "<SBCServiceTax>" + outputArr[14]
					+ "</SBCServiceTax>" + "<KKCServiceTax>" + outputArr[15] + "</KKCServiceTax>" +

					"<basicServiceTaxSecondYear>" + outputArr[16] + "</basicServiceTaxSecondYear>"
					+ "<SBCServiceTaxSecondYear>" + outputArr[17] + "</SBCServiceTaxSecondYear>"
					+ "<KKCServiceTaxSecondYear>" + outputArr[18] + "</KKCServiceTaxSecondYear>" + "<MinesOccuInterest>"
					+ outputArr[19] + "</MinesOccuInterest>" + "<BackDateinterest>" + outputArr[22]
					+ "</BackDateinterest>" + "<BackDateinterestwithoutGST>" + outputArr[10]
					+ "</BackDateinterestwithoutGST>" + "<PremiumWithST_ExcBackDate>" + outputArr[21]
					+ "</PremiumWithST_ExcBackDate>"
					/*** modified by Akshaya on 20-MAY-16 end **/

					+ bussIll.toString());
			retVal.append("</smartMoneyPlanner>");
		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			retVal.append("<?xml version='1.0' encoding='utf-8' ?><smartMoneyPlanner>" + "<errorMessage>"
					+ e.getMessage() + "</errorMessage></smartMoneyPlanner>");

		}
		return retVal.toString();

	}

	/**********************************
	 * Output ends here
	 **********************************************************/

	/**********************************
	 * Calculations starts from here
	 **********************************************************/

	public String[] getOutput(String sheetName, SmartMoneyPlannerBean smartMoneyPlannerBean) {

		bussIll = new StringBuilder();
		retVal = new StringBuilder();
		commonForAllProd = new CommonForAllProd();

		int _year_F = 0;

		int year_F = 0;
		String MinesOccuInterest = "0", BackDateinterest = "0", BackDateinterestwithGST = "0";
		double totalBasePremiumPaid = 0, GuaranteedDeathBenefit = 0, NonGuarateedDeathBenefitAt_4_Percent = 0,
				NonGuarateedDeathBenefitAt_8_Percent = 0, GuaranteedSurvivalBenefit = 0, GuaranteedMaturityBenefit = 0,
				NonGuarateedSurvivalBenefitAt_4_Percent = 0, NonGuarateedSurvivalBenefitAt_8_Percent = 0,totalBasePremiumPaidPPT = 0,
				TotalSurvivalBenefitAt_4_Percent = 0, TotalSurvivalBenefitAt_8_Percent = 0, yearlyPremiumWithOutST = 0,SurvivalBenefit = 0,
				premiumwithRoundUP, premiumWithST = 0, /****
														 * 
														 * 
														 * 
														 * 
														 * 
														 * 
														 * 
														 * 
														 * 
														 * Modified By - Priyanka Warekar - 26-08-2015 - Start
														 ****/

				FirstYear_ServiceTax = 0, premiumWithSTSecondYear = 0, SecondYear_ServiceTax = 0,
				/**** Modified By - Priyanka Warekar - 26-08-2015 - End ****/
				basePremiumwithRoundUP = 0, sumGuarnSurvBen = 0;

		double basePremiumWithST = 0, yearlyPremiumWithST = 0, baseST = 0;

		String GSV_surrendr_val = null, NonGSV_surrndr_val_4Percent = null, NonGSV_surrndr_val_8Percent = null;
		double maxNonGuarateedDeathBenefitAt_4_Percent = 0, maxNonGuarateedDeathBenefitAt_8_Percent = 0;
		int rowNumber = 0, j = 0;
		/** added by Akshaya on 04-AUG-15 start */
		double discountPercentage = 0;
		/** added by Akshaya on 04-AUG-15 end */
		// From GUI Input
		boolean staffDisc = smartMoneyPlannerBean.getIsForStaffOrNot();
		boolean JKResidentDisc = smartMoneyPlannerBean.getIsJKResidentDiscOrNot();
		String premFreqMode = smartMoneyPlannerBean.getPremFreqMode();
		int premiumPayingTerm = smartMoneyPlannerBean.getPremiumPayingTerm();
		int age = smartMoneyPlannerBean.getAge();
		int plan = smartMoneyPlannerBean.getPlan();
		int policyTerm = smartMoneyPlannerBean.getPolicyTerm_Basic();
		double sumAssured = smartMoneyPlannerBean.getsumAssured();
		// System.out.println(staffDisc+" "+JKResidentDisc+" "+premFreqMode+"
		// "+premiumPayingTerm+" "+age+" "+plan+" "+policyTerm+" "+sumAssured);
		// showAlert(staffDisc+" "+JKResidentDisc+" "+premFreqMode+"
		// "+premiumPayingTerm+" "+ageOfChild+" "+ageOfProposer+" "+policyTerm);
		double[] deathbenefitArr = new double[policyTerm * 5];
		SmartMoneyPlannerBusinessLogic smpBusinessLogic = new SmartMoneyPlannerBusinessLogic(smartMoneyPlannerBean);

		/** added by Akshaya on 04-AUG-15 start */
		discountPercentage = smpBusinessLogic.getStaffRebate(premFreqMode, staffDisc);
		/** added by Akshaya on 04-AUG-15 end */

		smpBusinessLogic.setPremiumWithoutSTwithoutRoundUP(age, plan, sumAssured, staffDisc, premFreqMode);

		double premiumWithoutST = Double.valueOf(smpBusinessLogic.getPremiumWithoutSTwithoutRoundUP());

		/** added by sujata on 28-02-2020 ***/
		// sujata
		smpBusinessLogic.set_Premium_Basic_WithoutST_NotRounded_withoutFreqLoading(age, plan, sumAssured, staffDisc,
				premFreqMode);

		String basicPremWithoutSTWithoutLoading = String
				.valueOf((smpBusinessLogic.get_Premium_Basic_WithoutST_NotRounded_withoutFreqLoading()));

		// System.out.println("basicPremWithoutSTWithoutLoading
		// "+basicPremWithoutSTWithoutLoading);
		// System.out.println("basicPremWithoutSTWithoutLoading" +
		// basicPremWithoutSTWithoutLoading);

		/*** end ***/

		/** added by Akshaya on 04-AUG-15 start */

		smpBusinessLogic.setPremiumWithoutSTwithoutStaffwithoutRoundUP(age, plan, sumAssured, staffDisc, premFreqMode);
		String premiumWithoutSTwithoutStaffWithoutRound = smpBusinessLogic
				.getPremiumWithoutSTwithoutStaffwithoutRoundUP();

		smpBusinessLogic.setPremiumWithoutSTwithoutStaffwithRoundUP();
		String premiumWithoutSTwithoutStaffwithRoundUP = smpBusinessLogic.getPremiumWithoutSTwithoutStaffwithRoundUP();

		/** added by Akshaya on 04-AUG-15 end */

		double PTARiderPremium = smpBusinessLogic.preferredTARiderPremium();
		double ATPDRiderPremium = smpBusinessLogic.ATPDRiderPremium();
		double criticarePremium = smpBusinessLogic.critiCareRiderPremium();
		double ADBRiderPremium = smpBusinessLogic.ADBRiderPremium();

		smpBusinessLogic.setPremiumWithoutSTwithRoundUP(premiumWithoutST, PTARiderPremium, ADBRiderPremium,
				ATPDRiderPremium, criticarePremium);

		// System.out.println("premiumWithoutST "+premiumWithoutST);
		premiumwithRoundUP = smpBusinessLogic.getPremiumWithoutSTwithRoundUP();

		// smpBusinessLogic.set_Premium_Basic_WithoutST_NotRounded_withoutFreqLoading

		// added by sujata on 28-02-2020
		// String
		// basicPremWithoutSTWithoutLoading=commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(smartIncomeProtectBusinessLogic.get_Premium_Basic_WithoutST_NotRounded_withoutFreqLoading()));

		/******************************
		 * Added by Akshaya on 03-MAR-15 start
		 **********************/

		// if (selOccupationMines.isChecked()) {
//		MinesOccuInterest = ""  
//				+ smpBusinessLogic.getMinesOccuInterest(smartMoneyPlannerBean
//						.getBasicSA());
		// } else {
		// MinesOccuInterest = "0";
		// }

		// premiumwithRoundUP = premiumwithRoundUP
		// + Double.parseDouble(MinesOccuInterest);

		/******************************
		 * Added by Akshaya on 03-MAR-15 end
		 **********************/

		/**** Modified By - Priyanka Warekar - 26-08-2015 - Start ****/

		double basicServiceTax = smpBusinessLogic.getServiceTax(premiumwithRoundUP, JKResidentDisc, "basic");
		double SBCServiceTax = smpBusinessLogic.getServiceTax(premiumwithRoundUP, JKResidentDisc, "SBC");
		double KKCServiceTax = smpBusinessLogic.getServiceTax(premiumwithRoundUP, JKResidentDisc, "KKC");

		FirstYear_ServiceTax = basicServiceTax + SBCServiceTax + KKCServiceTax;

		premiumWithST = premiumwithRoundUP + FirstYear_ServiceTax;

		// smpBusinessLogic.setPremiumWithSTFirstYear(premiumwithRoundUP,
		// JKResidentDisc);
		// premiumWithST = smpBusinessLogic.getPremiumWithSTFirstYear();

		double basicServiceTaxSecondYear = smpBusinessLogic.getServiceTaxSecondYear(premiumwithRoundUP, JKResidentDisc,
				"basic");
		double SBCServiceTaxSecondYear = smpBusinessLogic.getServiceTaxSecondYear(premiumwithRoundUP, JKResidentDisc,
				"SBC");
		double KKCServiceTaxSecondYear = smpBusinessLogic.getServiceTaxSecondYear(premiumwithRoundUP, JKResidentDisc,
				"KKC");

		SecondYear_ServiceTax = basicServiceTaxSecondYear + SBCServiceTaxSecondYear + KKCServiceTaxSecondYear;

		premiumWithSTSecondYear = premiumwithRoundUP + SecondYear_ServiceTax;

		// smpBusinessLogic.setPremiumWithSTSecondYear(premiumwithRoundUP,
		// JKResidentDisc);
		// premiumWithSTSecondYear =
		// smpBusinessLogic.getPremiumWithSTSecondYear();

//		System.out.println(" premiumwithRoundUP : " + premiumwithRoundUP + " "
//				+ premiumWithST + "  " + premiumWithSTSecondYear);

		/**** Modified By - Priyanka Warekar - 26-08-2015 - End ****/

		/**** Modified By - Priyanka Warekar - 26-08-2015 - Start ****/
		// smpBusinessLogic.setServiceTaxFirstYear(premiumWithST,
		// premiumwithRoundUP);
		// FirstYear_ServiceTax = smpBusinessLogic.getServiceTaxFirstYear();
		// smpBusinessLogic.setServiceTaxSecondYear(premiumWithSTSecondYear,
		// premiumwithRoundUP);
		// SecondYear_ServiceTax = smpBusinessLogic.getServiceTaxSecondYear();

		/**** Modified By - Priyanka Warekar - 26-08-2015 - End ****/
		/******************************
		 * Added by Akshaya on 03-MAR-15 start
		 **********************/
//		if (rb_smart_money_planner_backdating_yes.isChecked()) {
//			// BackDateinterest=commonForAllProd.getRoundUp(""+(shubhNiveshBussinesLogic.getBackDateInterest(Double.parseDouble(premiumSingleInstBasicWithST),
//			// "16-jan-2014")));
//			BackDateinterest = commonForAllProd.getRoundUp(""
//					+ (smpBusinessLogic.getBackDateInterest(premiumWithST,
//							btn_smart_money_planner_backdatingdate.getText()
//							.toString())));
//		} else {
//			BackDateinterest = "0";
//		}
//
//		premiumWithST = premiumWithST + Double.parseDouble(BackDateinterest);
		/******************************
		 * Added by Akshaya on 03-MAR-15 end
		 **********************/

		// System.out.println(premiumWithoutST+" "+premiumWithST+" "+ ST+" " +
		// premiumwithRoundUP);

		/***************** Added By tushar Kotian on 2/8/2017 ********************/

		if (Isbackdate.equals("true")) {
			// BackDateinterest=commonForAllProd.getRoundUp(""+(shubhNiveshBussinesLogic.getBackDateInterest(Double.parseDouble(premiumSingleInstBasicWithST),
			// "16-jan-2014")));
			BackDateinterest = commonForAllProd.getRoundUp(commonForAllProd
					.getStringWithout_E(smpBusinessLogic.getBackDateInterest(premiumWithST, backdate, cashierDate)));

			BackDateinterestwithGST = commonForAllProd
					.getRoundUp(commonForAllProd.getStringWithout_E(Double.parseDouble(BackDateinterest)
							+ (Double.parseDouble(BackDateinterest) * prop.GSTforbackdate)));

		} else {
			BackDateinterest = "0";
		}

		premiumWithST_WithoutBkdate = "" + premiumWithST;

		premiumWithST = premiumWithST + Double.parseDouble(BackDateinterest);

		if (Ismines.equals("true")) {
			MinesOccuInterest = smpBusinessLogic.getMinesOccuInterest(sumAssured) + "";
		} else {
			MinesOccuInterest = "0";
		}

		/***************** Added By tushar Kotian on 2/8/2017 ********************/

		smpBusinessLogic.setBasePremiumWithoutSTwithRoundUP(premiumWithoutST);
		basePremiumwithRoundUP = smpBusinessLogic.getBasePremiumWithoutSTwithRoundUP();

		smpBusinessLogic.setBasePremiumWithST(basePremiumwithRoundUP, JKResidentDisc);
		basePremiumWithST = smpBusinessLogic.getBasePremiumWithST();

		smpBusinessLogic.setBaseServiceTax(premiumWithST, premiumwithRoundUP);
		baseST = smpBusinessLogic.getBaseServiceTax();

		smpBusinessLogic.setYearlyPremiumWithST(basePremiumWithST, premFreqMode);
		yearlyPremiumWithST = smpBusinessLogic.getYearlyPremiumWithST();

		int growthPeriod = smpBusinessLogic.getGrowthPeriod(age, premFreqMode, plan);
		int BPP = smpBusinessLogic.setBenefitPayingTerm(plan);
//		System.out.println("growthPeriod : " + growthPeriod);
		smpBusinessLogic.setYearlyPremiumWithoutST(premiumWithoutST, premFreqMode);
		yearlyPremiumWithOutST = Double.parseDouble(commonForAllProd.getStringWithout_E(smpBusinessLogic.getYearlyPremiumWithoutST()));

		// System.out.println(yearlyPremiumWithST+" "+
		// yearlyPremiumWithOutST+" "+ BPP+" "+ growthPeriod);
		double sumSurvivalbenefit = 0,
		firstYearoftotalbasePrem = 0,
		sumtotalBasePremiumPaidforSurr = 0;
		try {
			for (int i = 0; i < policyTerm; i++)
			// for(int i=0;i<1;i++)
			{
				rowNumber++;
				smpBusinessLogic.settotalbaseofppt(year_F, premiumPayingTerm, yearlyPremiumWithOutST);
				totalBasePremiumPaidPPT = smpBusinessLogic.gettotalbaseofppt();
				year_F = rowNumber;
				_year_F = year_F;
				// System.out.println("1. year_F "+year_F);
				bussIll.append("<policyYr" + _year_F + ">" + _year_F + "</policyYr" + _year_F + ">");

				smpBusinessLogic.setTotalBasePremiumPaid(yearlyPremiumWithOutST, year_F, premiumPayingTerm,
						premFreqMode, policyTerm);
				totalBasePremiumPaid = smpBusinessLogic.getTotalBasePremiumPaid();
				// System.out.println("2.Total Base Premium Paid "+totalBasePremiumPaid);
				bussIll.append(
						"<totaBasePrem" + _year_F + ">" + totalBasePremiumPaid + "</totaBasePrem" + _year_F + ">");

				smpBusinessLogic.setGuaranteedDeathBenefit(sumAssured, year_F, policyTerm);
				GuaranteedDeathBenefit = smpBusinessLogic.getGuaranteedDeathBenefit();
				// System.out.println("3.Guaranteed Death Benefit "+GuaranteedDeathBenefit);
				bussIll.append("<guarntdDeathBenft" + _year_F + ">" + GuaranteedDeathBenefit + "</guarntdDeathBenft"
						+ _year_F + ">");

				smpBusinessLogic.setNonGuarateedDeathBenefitAt_4_Percent(sumAssured, year_F, policyTerm);
				NonGuarateedDeathBenefitAt_4_Percent = smpBusinessLogic.getNonGuarateedDeathBenefitAt_4_Percent();
				// System.out.println("4.Non Guarateed Death Benefit At_4_Percent
				// "+NonGuarateedDeathBenefitAt_4_Percent);
				bussIll.append("<nongrntdDeathNenft_4Percent" + _year_F + ">" + NonGuarateedDeathBenefitAt_4_Percent
						+ "</nongrntdDeathNenft_4Percent" + _year_F + ">");

				smpBusinessLogic.setNonGuarateedDeathBenefitAt_8_Percent(sumAssured, year_F, policyTerm);
				NonGuarateedDeathBenefitAt_8_Percent = smpBusinessLogic.getNonGuarateedDeathBenefitAt_8_Percent();
				// System.out.println("5.Non Guarateed Death Benefit At_8_Percent
				// "+NonGuarateedDeathBenefitAt_8_Percent);
				bussIll.append("<nongrntdDeathNenft_8Percent" + _year_F + ">" + NonGuarateedDeathBenefitAt_8_Percent
						+ "</nongrntdDeathNenft_8Percent" + _year_F + ">");

				sumGuarnSurvBen += GuaranteedSurvivalBenefit;
				smpBusinessLogic.setGuaranteedSurvivalBenefit(sumAssured, growthPeriod, policyTerm, year_F, BPP);
				GuaranteedSurvivalBenefit = smpBusinessLogic.getGuaranteedSurvivalBenefit();
				// System.out.println("6.Guaranteed Survival Benefit
				// "+GuaranteedSurvivalBenefit);
				
				smpBusinessLogic.setGuaranteedMaturityBenefit(year_F, policyTerm, sumAssured, BPP);
				GuaranteedMaturityBenefit = smpBusinessLogic.getGuaranteedMaturityBenefit();
				bussIll.append(
						"<guaranMatBen" + _year_F + ">" + GuaranteedMaturityBenefit + "</guaranMatBen" + _year_F + ">");

				smpBusinessLogic.setNonGuarateedSurvivalBenefitAt_4_Percent(NonGuarateedDeathBenefitAt_4_Percent,
						policyTerm, year_F);
				NonGuarateedSurvivalBenefitAt_4_Percent = smpBusinessLogic.getNonGuarateedSurvivalBenefitAt_4_Percent();// System.out.println("7.Non
																														// Guarateed
																														// Survival
																														// Benefit
																														// At_4_Percent
																														// "+NonGuarateedSurvivalBenefitAt_4_Percent);
				double nonGuaranMatBen_4Percent = smpBusinessLogic.getTotalMaturityBenefit4percent(year_F, policyTerm,
						GuaranteedMaturityBenefit, NonGuarateedDeathBenefitAt_4_Percent);
				bussIll.append("<nonGuaranMatBen_4Percent" + _year_F + ">" + nonGuaranMatBen_4Percent
						+ "</nonGuaranMatBen_4Percent" + _year_F + ">");

				smpBusinessLogic.setNonGuarateedSurvivalBenefitAt_8_Percent(NonGuarateedDeathBenefitAt_8_Percent,
						policyTerm, year_F);
				NonGuarateedSurvivalBenefitAt_8_Percent = smpBusinessLogic.getNonGuarateedSurvivalBenefitAt_8_Percent();
				// System.out.println("8.Non Guarateed Survival Benefit At_8_Percent
				// "+NonGuarateedSurvivalBenefitAt_8_Percent);
				
				double nonGuaranMatBen_8Percent = Double.parseDouble(
						commonForAllProd.getRound("" + smpBusinessLogic.getTotalMaturityBenefit8percent(year_F,
								policyTerm, GuaranteedMaturityBenefit, NonGuarateedDeathBenefitAt_8_Percent)));
				bussIll.append("<nonGuaranMatBen_8Percent" + _year_F + ">" + nonGuaranMatBen_8Percent
						+ "</nonGuaranMatBen_8Percent" + _year_F + ">");

				SurvivalBenefit = smpBusinessLogic.getGuaranteedSurvivalBenefitFinal(policyTerm, year_F);
				
				if (year_F == 1) {
					firstYearoftotalbasePrem = (totalBasePremiumPaidPPT);
				}
				
				if (year_F <= premiumPayingTerm) {
					sumtotalBasePremiumPaidforSurr = (sumtotalBasePremiumPaidforSurr + (totalBasePremiumPaidPPT));// totalBasePremiumPaidPPT
					// System.out.println("sumtotalBasePremiumPaidforSurr
					// "+sumtotalBasePremiumPaidforSurr);premiumwithRoundUP
				}
				
				smpBusinessLogic.setGSV_SurrenderValue(year_F, sumSurvivalbenefit, firstYearoftotalbasePrem,
						sumtotalBasePremiumPaidforSurr);
				GSV_surrendr_val = commonForAllProd
						.getRound(commonForAllProd.getStringWithout_E(smpBusinessLogic.getGSV_SurrenderValue()));
//				System.out.println("GSV_surrendr_val " + GSV_surrendr_val);
				bussIll.append(
						"<GSV_surrendr_val" + _year_F + ">" + GSV_surrendr_val + "</GSV_surrendr_val" + _year_F + ">");
				
				sumSurvivalbenefit = sumSurvivalbenefit + SurvivalBenefit;

				smpBusinessLogic.setNonGSV_surrndr_val_4_Percent(year_F, NonGuarateedDeathBenefitAt_4_Percent);
				NonGSV_surrndr_val_4Percent = commonForAllProd
						.getStringWithout_E((smpBusinessLogic.getNonGSV_surrndr_val_4_Percent()));
//				System.out.println("NonGSV_surrndr_val_4Percent "
//						+ NonGSV_surrndr_val_4Percent);
				bussIll.append("<NonGSV_surrndr_val_4Percent" + _year_F + ">" + NonGSV_surrndr_val_4Percent
						+ "</NonGSV_surrndr_val_4Percent" + _year_F + ">");

				smpBusinessLogic.setNonGSV_surrndr_val_8_Percent(year_F, NonGuarateedDeathBenefitAt_8_Percent);
				NonGSV_surrndr_val_8Percent = commonForAllProd.getStringWithout_E(
						Double.parseDouble("" + (smpBusinessLogic.getNonGSV_surrndr_val_8_Percent())));
//				System.out.println("NonGSV_surrndr_val_8Percent "
//						+ NonGSV_surrndr_val_8Percent);
				bussIll.append("<NonGSV_surrndr_val_8Percent" + _year_F + ">" + NonGSV_surrndr_val_8Percent
						+ "</NonGSV_surrndr_val_8Percent" + _year_F + ">");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new String[] {
				/**** Modified By - Priyanka Warekar - 26-08-2015 - Start ****/
				(commonForAllProd.getStringWithout_E(premiumwithRoundUP)),
				commonForAllProd.getStringWithout_E(FirstYear_ServiceTax),
				commonForAllProd.getStringWithout_E(premiumWithST),

				/**** Modified By - Priyanka Warekar - 26-08-2015 - End ****/
				commonForAllProd.getStringWithout_E(GuaranteedSurvivalBenefit),
				(commonForAllProd.getStringWithout_E(NonGuarateedSurvivalBenefitAt_4_Percent)),
				commonForAllProd.getStringWithout_E(NonGuarateedSurvivalBenefitAt_8_Percent),
				/** added by Akshaya on 04-AUG-15 start */
				commonForAllProd.getStringWithout_E(discountPercentage), premiumWithoutSTwithoutStaffWithoutRound,
				premiumWithoutSTwithoutStaffwithRoundUP, MinesOccuInterest, BackDateinterest,
				commonForAllProd.getStringWithout_E(SecondYear_ServiceTax),
				commonForAllProd.getStringWithout_E(premiumWithSTSecondYear),
				commonForAllProd.getStringWithout_E(basicServiceTax),
				commonForAllProd.getStringWithout_E(SBCServiceTax), commonForAllProd.getStringWithout_E(KKCServiceTax),
				commonForAllProd.getStringWithout_E(basicServiceTaxSecondYear),
				commonForAllProd.getStringWithout_E(SBCServiceTaxSecondYear),
				commonForAllProd.getStringWithout_E(KKCServiceTaxSecondYear), BackDateinterest, MinesOccuInterest,
				premiumWithST_WithoutBkdate, BackDateinterestwithGST, basicPremWithoutSTWithoutLoading

		};
		/** added by Akshaya on 04-AUG-15 end */
		// return null;

	}
	/**********************************
	 * Calculations ends from here
	 **********************************************************/

}
