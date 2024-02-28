package com.sbilife.smartmoneybackgold;

import com.sbilife.common.CommonForAllProd;

//import com.sbilife.common.CommonForAllProd;

public class SmartMoneyBackGoldNew {

	CommonForAllProd commonForAllProduct = null;
	SmartMoneyBackGoldProperties prop = null;
	String retVal = null;
	// Variable Declaration
	public int totInstPrem_inclST = 0, countEle = 0, basicCoverTIY = 0, ptaTIY = 0, adbTIY = 0, atpdbTIY = 0, age = 0,
			prempta = 0, totalST = 0;
	public double totInstPrem_exclST = 0, premPTA = 0, premBasic = 0, premADB = 0, premATPDB = 0, premCC13NonLinked = 0;
	StringBuilder bussIll = null;
	String staffRebate = "";
	String Isbackdate = "";
	String backdate = "";
	String Ismines = "";
	String cashierDate = "";
	// String ppt = ppt.toString();
	// public String CalculatePrem(String name ,String isStaff, String State ,
	// String planName,String age,String gender,String policyTerm,String
	// premiumFreq,String basicSA,String isPTARider,String ptaTerm,String
	// ptaSA,String isADBRider,String adbTerm,String adbSA,String
	// isATPDBRider,String atpdbTerm,String atpdbSA,String isCC13Rider,String
	// cc13Term,String cc13SA
	// ,String IsBackdate ,String Backdate,String IsMines,String KFC)

	// public String CalculatePrem(String name, String isStaff, String State, String
	// planName, String age,String gender,String policyTerm,String premiumFreq )
	public String CalculatePrem(String isStaff, String isJkResident, String planName, String age, String gender,
			String policyTerm, String ppt, String premPayOption, String premiumFreq, String basicSA, String isPTARider,
			String ptaTerm, String ptaSA, String isADBRider, String adbTerm, String adbSA, String isATPDBRider,
			String atpdbTerm, String atpdbSA, String isCC13Rider, String cc13Term, String cc13SA, String IsBackdate,
			String Backdate, String IsMines, String cashierDate) {
		String retVal = null;
		Isbackdate = IsBackdate;
		backdate = Backdate;
		Ismines = IsMines;
		this.cashierDate = cashierDate;

		try {
			SmartMoneyBackGoldBean smartMoneyBackGoldBean = new SmartMoneyBackGoldBean();
			smartMoneyBackGoldBean.setStaffDisc(Boolean.parseBoolean(isStaff));

			// Added By Saurabh Jain on 14/05/2019 Start

			smartMoneyBackGoldBean.setState(false);

			// Added By Saurabh Jain on 14/05/2019 End

			// smartMoneyBackGoldBean.setJkResident(Boolean.parseBoolean(isJkResident));

			smartMoneyBackGoldBean.setPlanName(planName);

			smartMoneyBackGoldBean.setAge(Integer.parseInt(age));

			smartMoneyBackGoldBean.setGender(gender);

			smartMoneyBackGoldBean.setPolicyTerm(Integer.parseInt(policyTerm));
			// added by sujata on 04/11/2019

			smartMoneyBackGoldBean.setppt(Integer.parseInt(ppt));

			smartMoneyBackGoldBean.setpremPayOption(premPayOption);

			smartMoneyBackGoldBean.setPremiumFreq(premiumFreq);

			smartMoneyBackGoldBean.setSumAssured_Basic(Integer.parseInt(basicSA));

//		System.out.println("setPremiumFreq"+smartMoneyBackGoldBean.getPremiumFreq());

			if (Boolean.parseBoolean(isPTARider)) {
				smartMoneyBackGoldBean.setPT_Status(true);
				smartMoneyBackGoldBean.setPolicyTerm_PT(Integer.parseInt(ptaTerm));
				smartMoneyBackGoldBean.setSumAssured_PT(Integer.parseInt(ptaSA));
			} else {
				smartMoneyBackGoldBean.setPT_Status(false);
			}

			if (Boolean.parseBoolean(isADBRider)) {
				smartMoneyBackGoldBean.setADB_Status(true);
				smartMoneyBackGoldBean.setPolicyTerm_ADB(Integer.parseInt(adbTerm));
				smartMoneyBackGoldBean.setSumAssured_ADB(Integer.parseInt(adbSA));
			} else {
				smartMoneyBackGoldBean.setADB_Status(false);
			}

			if (Boolean.parseBoolean(isATPDBRider)) {
				smartMoneyBackGoldBean.setATPDB_Status(true);
				smartMoneyBackGoldBean.setPolicyTerm_ATPDB(Integer.parseInt(atpdbTerm));
				smartMoneyBackGoldBean.setSumAssured_ATPDB(Integer.parseInt(atpdbSA));
			} else {
				smartMoneyBackGoldBean.setATPDB_Status(false);
			}

			if (Boolean.parseBoolean(isCC13Rider)) {
				smartMoneyBackGoldBean.setCC13NonLinked_Status(true);
				smartMoneyBackGoldBean.setPolicyTerm_CC13NonLinked(Integer.parseInt(cc13Term));
				smartMoneyBackGoldBean.setSumAssured_CC13NonLinked(Integer.parseInt(cc13SA));
			} else {
				smartMoneyBackGoldBean.setCC13NonLinked_Status(false);
			}

			// Show Output Form
			retVal = showSmartMoneyBackGoldOutputPg(smartMoneyBackGoldBean);
		} catch (Exception e) {
			// TODO: handle exception
			retVal = "<?xml version='1.0' encoding='utf-8' ?><smartMoneyBackGold>" + "<errCode>1</errCode>"
					+ "<errorMessage>" + e.getMessage() + "</errorMessage></smartMoneyBackGold>";

		}
		return retVal;

	}

	public String showSmartMoneyBackGoldOutputPg(SmartMoneyBackGoldBean smartMoneyBackGoldBean) {
		StringBuilder retVal = new StringBuilder();

		bussIll = new StringBuilder();
		try {
			SmartMoneyBackGoldBusinessLogic smartMoneyBackGoldBusinessLogic = new SmartMoneyBackGoldBusinessLogic(
					smartMoneyBackGoldBean);
			CommonForAllProd commonForAllProd = new CommonForAllProd();
			SmartMoneyBackGoldProperties prop = new SmartMoneyBackGoldProperties();

			int _year_F = 0;

			int year_F = 0;

			String MinesOccuInterest = "0", premWithoutDiscWithoutst = "0", premWithoutDiscAndSAWithoutst = "0",
					BackDateinterest = "0", BackDateinterestwithGST = "0";

			String premiumPTA = "0", premiumWithoutDiscPTA = "0", premiumWithoutDiscSAPTA = "0";
			String premiumADB = "0", premiumWithotDiscADB = "0", premiumWithotDiscSAADB = "0";
			String premiumATPDB = "0", premiumWithoutDiscATPDB = "0", premiumWithoutDiscSAATPDB = "0";
			String premiumCC13NonLinked = "0", premiumWithoutDiscCC13NonLinked = "0",
					premiumWithoutDiscSACC13NonLinked = "0";
			// added by sujata
			String guaranMatBen = "0", TotalMaturityBenefit8per = null, AnnulizedPremium = null,
					getSurvivalBenefit = null, TotalMaturityBenefit4per = null, TotalDeathBenefit8per = null,
					TotalDeathBenefit4per = null, ReversionaryBonus8Per = null, ReversionaryBonus4Per = null,
					MaturityBenefit = null, GuaranteedDeathBenefit = null, GuarateedAddition = null,
					nonGuaranMatBen_4Percent = null, nonGuaranMatBen_8Percent = null, totaBasePrem = null,
					guarntdDeathBenft = null, nongrntdDeathNenft_4Percent = null, nongrntdDeathNenft_8Percent = null,
					GSV_surrendr_val = null, NonGSV_surrndr_val_4Percent = null, NonGSV_surrndr_val_8Percent = null;

			double sumAnnualizedPrem1 = 0;
//		String premiumBasic_NotRounded=""+smartMoneyBackGoldBusinessLogic.get_Premium_Basic_WithoutST_NotRounded();
			String premiumBasic_NotRounded = "" + commonForAllProd.getRoundOffLevel2New(commonForAllProd
					.getStringWithout_E(smartMoneyBackGoldBusinessLogic.get_Premium_Basic_WithoutST_NotRounded()));
			
			String premiumBasic_NotRounded1 = commonForAllProd
                    .getStringWithout_E(smartMoneyBackGoldBusinessLogic.get_Premium_Basic_WithoutST_NotRounded());
//		System.out.println("premiumBasic_NotRounded "+premiumBasic_NotRounded);
			String premiumBasic_Rounded = commonForAllProd
					.getRoundUp(commonForAllProd.getStringWithout_E(Double.parseDouble(premiumBasic_NotRounded)));

			String premiumWithoutApplicableTax_NotRounded = ""
					+ smartMoneyBackGoldBusinessLogic.get_Premium_Without_ST_WithoutLoadingFreq();
			String premiumWithoutApplicableTax = commonForAllProd.getRoundUp(
					commonForAllProd.getStringWithout_E(Double.parseDouble(premiumWithoutApplicableTax_NotRounded)));
//		System.out.println("premiumBasic_Rounded "+premiumBasic_Rounded);
			premBasic = Integer.parseInt(premiumBasic_Rounded);

			double totBasePrem = smartMoneyBackGoldBusinessLogic.getTotBasePrem(
					Double.parseDouble(premiumBasic_NotRounded), smartMoneyBackGoldBean.getPremiumFreq());
			
			double totBasePrem1 = smartMoneyBackGoldBusinessLogic.getTotBasePrem(
                    Double.parseDouble(premiumBasic_NotRounded1), smartMoneyBackGoldBean.getPremiumFreq());
			
			double staffrbte = smartMoneyBackGoldBusinessLogic.getStaffRebate("basic");
			staffRebate = commonForAllProd.getRoundOffLevel2("" + staffrbte * 100);
			int rowNumber = 0;

			double sumGuaranteedSurvivalBen = 0;
			// added by sujata
			double sumAnnualizedPrem = 0, sumSurvivalBenefit = 0, annualizedPrem = 0;
			/*
			 * for (int k = 0; k < smartMoneyBackGoldBean.getPolicyTerm(); k++) {
			 * rowNumber++; year_F = rowNumber; _year_F = year_F;
			 */

			for (int j = 0; j < smartMoneyBackGoldBean.getPolicyTerm(); j++) {
				rowNumber++;

				year_F = rowNumber;
				_year_F = year_F;
//			System.out.println("1. year_F " + year_F);
				bussIll.append("<policyYr" + _year_F + ">" + _year_F + "</policyYr" + _year_F + ">");

//added by sujata
				/*
				 * totaBasePrem = commonForAllProd.getRoundOffLevel2(commonForAllProd
				 * .getStringWithout_E(Double.parseDouble("" + smartMoneyBackGoldBusinessLogic
				 * .get_Total_Base_Premium_Paid(year_F, totBasePrem, smartMoneyBackGoldBean
				 * .getPolicyTerm(),
				 * 
				 * smartMoneyBackGoldBean .getPremiumFreq()))));
				 */
				// added by sujata
				totaBasePrem = commonForAllProd
						.getRoundOffLevel2(commonForAllProd.getStringWithout_E(smartMoneyBackGoldBusinessLogic
								.get_Annualized_Premium(year_F, totBasePrem, smartMoneyBackGoldBean.getPolicyTerm(),
										smartMoneyBackGoldBean.getPremiumFreq(), smartMoneyBackGoldBean.getppt())));
			//System.out.println("totaBasePrem " + totaBasePrem);	
				
				String totaBasePrem1 = commonForAllProd
                        .getRoundOffLevel2(commonForAllProd.getStringWithout_E(smartMoneyBackGoldBusinessLogic
                                .get_Annualized_Premium(year_F, totBasePrem1, smartMoneyBackGoldBean.getPolicyTerm(),
                                        smartMoneyBackGoldBean.getPremiumFreq(), smartMoneyBackGoldBean.getppt())));
//System.out.println("totaBasePrem1"+totaBasePrem1);
				// added by sujata on 06-05-2019
				AnnulizedPremium = commonForAllProd.getRound(commonForAllProd.getStringWithout_E(smartMoneyBackGoldBusinessLogic
								.getannulizedPremFinal(year_F, smartMoneyBackGoldBean.getppt())));
				// added by sujata on 08-11-2019
				GuarateedAddition = commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(
						smartMoneyBackGoldBusinessLogic.get_Guarateed_add(year_F, smartMoneyBackGoldBean.getPolicyTerm()

						)));

				// added by sujata
				MaturityBenefit = commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(
						smartMoneyBackGoldBusinessLogic.Maturity_Benefit(year_F, smartMoneyBackGoldBean.getPolicyTerm()

						)));
				// added by sujata
	ReversionaryBonus4Per = commonForAllProd.getRoundOffLevel2(commonForAllProd.getStringWithout_E(smartMoneyBackGoldBusinessLogic.Reversionary_Bonus(year_F,
								smartMoneyBackGoldBean.getPolicyTerm(), smartMoneyBackGoldBean.getSumAssured_Basic(),
								"4%", smartMoneyBackGoldBean.getPlanName()

						))); // added by sujata

				ReversionaryBonus8Per = commonForAllProd.getRoundOffLevel2(commonForAllProd
						.getStringWithout_E(smartMoneyBackGoldBusinessLogic.Reversionary_Bonus8per(year_F,
								smartMoneyBackGoldBean.getPolicyTerm(), smartMoneyBackGoldBean.getSumAssured_Basic(),
								"8%", smartMoneyBackGoldBean.getPlanName()

						)));

				if (_year_F == 1) {
					annualizedPrem = Double.parseDouble(totaBasePrem);
					System.out.println("annualizedPrem 1 "+annualizedPrem);
				}

				//sumAnnualizedPrem = sumAnnualizedPrem + Double.parseDouble(totaBasePrem);
				sumAnnualizedPrem1 = sumAnnualizedPrem1 + Double.parseDouble(totaBasePrem1);
				
				
				sumAnnualizedPrem = Double.parseDouble(totaBasePrem) * year_F;

				TotalDeathBenefit4per = commonForAllProd.getRoundOffLevel2(
						commonForAllProd.getStringWithout_E(smartMoneyBackGoldBusinessLogic.TotalDeathBenefit4per(
								year_F, smartMoneyBackGoldBean.getPolicyTerm(), smartMoneyBackGoldBean.getAge(), "4%",
								smartMoneyBackGoldBean.getPlanName(), smartMoneyBackGoldBean.getSumAssured_Basic()

								, Double.parseDouble(premiumBasic_Rounded), Double.parseDouble(totaBasePrem),
								annualizedPrem, sumAnnualizedPrem

						)));
				// added by sujata 05-11-2019
				TotalDeathBenefit8per = commonForAllProd.getRoundOffLevel2(
						commonForAllProd.getStringWithout_E(smartMoneyBackGoldBusinessLogic.TotalDeathBenefit8per(
								year_F, smartMoneyBackGoldBean.getPolicyTerm(), smartMoneyBackGoldBean.getAge(), "8%",
								smartMoneyBackGoldBean.getPlanName(), smartMoneyBackGoldBean.getSumAssured_Basic()

								, Double.parseDouble(premiumBasic_Rounded), Double.parseDouble(totaBasePrem),
								annualizedPrem, sumAnnualizedPrem)));

				TotalMaturityBenefit4per = commonForAllProd.getRoundOffLevel2(commonForAllProd
						.getStringWithout_E(smartMoneyBackGoldBusinessLogic.TotalMaturityBenefit4per(year_F,
								smartMoneyBackGoldBean.getPolicyTerm(), smartMoneyBackGoldBean.getSumAssured_Basic(),
								"4%", smartMoneyBackGoldBean.getPlanName())));

				TotalMaturityBenefit8per = commonForAllProd.getRoundOffLevel2(commonForAllProd
						.getStringWithout_E(smartMoneyBackGoldBusinessLogic.TotalMaturityBenefit8per(year_F,
								smartMoneyBackGoldBean.getPolicyTerm(), smartMoneyBackGoldBean.getSumAssured_Basic(),
								"8%", smartMoneyBackGoldBean.getPlanName()

						)));

				getSurvivalBenefit = commonForAllProd.getRoundOffLevel2(commonForAllProd
						.getStringWithout_E(smartMoneyBackGoldBusinessLogic.getSurvivalBenefit(year_F)));

				bussIll.append("<totaBasePrem" + _year_F + ">"
						+ commonForAllProd
								.getRound(commonForAllProd.getStringWithout_E(Double.parseDouble(totaBasePrem)))
						+ "</totaBasePrem" + _year_F + ">");

				// added by sujata on 06-12-2019
				bussIll.append("<AnnulizedPremium" + _year_F + ">"
						+ commonForAllProd
								.getRound(commonForAllProd.getStringWithout_E(Double.parseDouble(AnnulizedPremium)))
						+ "</AnnulizedPremium" + _year_F + ">");

				bussIll.append("<getSurvivalBenefit" + _year_F + ">"
						+ commonForAllProd
								.getRound(commonForAllProd.getStringWithout_E(Double.parseDouble(getSurvivalBenefit)))
						+ "</getSurvivalBenefit" + _year_F + ">");

				/********** Modified By Tushar Kotian on 11/8/2017 ****************/
				/*
				 * guarntdDeathBenft = commonForAllProd.getRoundUp(commonForAllProd
				 * .getStringWithout_E(Double.parseDouble("" + smartMoneyBackGoldBusinessLogic
				 * .getGuarntedDeathBenefit(_year_F,
				 * smartMoneyBackGoldBean.getSumAssured_Basic(),
				 * smartMoneyBackGoldBean.getPolicyTerm(), smartMoneyBackGoldBean.getAge(),
				 * Double.parseDouble(premiumBasic_Rounded),
				 * Double.parseDouble(totaBasePrem)))));
				 */
				// added by sujata
				GuaranteedDeathBenefit = commonForAllProd.getRound(commonForAllProd.getStringWithout_E(
						Double.parseDouble("" + smartMoneyBackGoldBusinessLogic.getGuarDeathBenefitint(_year_F,
								smartMoneyBackGoldBean.getSumAssured_Basic(),
								smartMoneyBackGoldBean.getPolicyTerm()))));
//			System.out.println("guarntdDeathBenft " + guarntdDeathBenft);

				guarntdDeathBenft = commonForAllProd.getRound(commonForAllProd.getStringWithout_E(
						Double.parseDouble("" + smartMoneyBackGoldBusinessLogic.getGuarntedDeathBenefit(_year_F,
								smartMoneyBackGoldBean.getSumAssured_Basic(), smartMoneyBackGoldBean.getPolicyTerm(),
								smartMoneyBackGoldBean.getAge(), Double.parseDouble(premiumBasic_Rounded),
								Double.parseDouble(totaBasePrem), annualizedPrem))));// System.out.println("guarntdDeathBenft
																						// " + guarntdDeathBenft);
				/********** Modified By Tushar Kotian on 11/8/2017 ****************/
				// added by sujata
				bussIll.append("<GuaranteedDeathBenefit" + _year_F + ">" + guarntdDeathBenft
						+ "</GuaranteedDeathBenefit" + _year_F + ">");
				// added by sujata
				bussIll.append("<GuarateedAddition" + _year_F + ">" + GuarateedAddition + "</GuarateedAddition"
						+ _year_F + ">");

				// added by sujata
				bussIll.append(
						"<MaturityBenefit" + _year_F + ">" + MaturityBenefit + "</MaturityBenefit" + _year_F + ">");

				// added by sujata
				bussIll.append("<ReversionaryBonus4Per" + _year_F + ">" + ReversionaryBonus4Per
						+ "</ReversionaryBonus4Per" + _year_F + ">");
				// added by sujata
				bussIll.append("<ReversionaryBonus8Per" + _year_F + ">" + ReversionaryBonus8Per
						+ "</ReversionaryBonus8Per" + _year_F + ">");
				// added by sujata
				bussIll.append("<TotalDeathBenefit4per" + _year_F + ">" + TotalDeathBenefit4per
						+ "</TotalDeathBenefit4per" + _year_F + ">");
				// added by sujata
				bussIll.append("<TotalDeathBenefit8per" + _year_F + ">" + TotalDeathBenefit8per
						+ "</TotalDeathBenefit8per" + _year_F + ">");
				// added by sujata
				bussIll.append("<TotalMaturityBenefit4per" + _year_F + ">" + TotalMaturityBenefit4per
						+ "</TotalMaturityBenefit4per" + _year_F + ">");

				// added by sujata
				bussIll.append("<TotalMaturityBenefit8per" + _year_F + ">" + TotalMaturityBenefit8per
						+ "</TotalMaturityBenefit8per" + _year_F + ">");

				// end
				bussIll.append("<guarntdDeathBenft" + _year_F + ">" + guarntdDeathBenft + "</guarntdDeathBenft"
						+ _year_F + ">");

				nongrntdDeathNenft_4Percent = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(Double
						.parseDouble("" + smartMoneyBackGoldBusinessLogic.getNonGuarntedDeathBenefit(year_F, "4%"))));
				// System.out.println("nongrntdDeathNenft_4Percent "
				// + nongrntdDeathNenft_4Percent);
				bussIll.append("<nongrntdDeathNenft_4Percent" + _year_F + ">" + nongrntdDeathNenft_4Percent
						+ "</nongrntdDeathNenft_4Percent" + _year_F + ">");

				nongrntdDeathNenft_8Percent = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(Double
						.parseDouble("" + smartMoneyBackGoldBusinessLogic.getNonGuarntedDeathBenefit(year_F, "8%"))));
				/*
				 * System.out.println("nongrntdDeathNenft_8Percent  " +
				 * nongrntdDeathNenft_8Percent);
				 */
				bussIll.append("<nongrntdDeathNenft_8Percent" + _year_F + ">" + nongrntdDeathNenft_8Percent
						+ "</nongrntdDeathNenft_8Percent" + _year_F + ">");

				sumSurvivalBenefit = Double.parseDouble(guaranMatBen) + sumSurvivalBenefit;
				guaranMatBen = "" + (smartMoneyBackGoldBusinessLogic.getGuaranMatBen(year_F));
				/* System.out.println("guaranMatBen " + guaranMatBen); */
				bussIll.append("<guaranMatBen" + _year_F + ">" + guaranMatBen + "</guaranMatBen" + _year_F + ">");

				nonGuaranMatBen_4Percent = commonForAllProd
						.getRoundUp(commonForAllProd.getStringWithout_E(smartMoneyBackGoldBusinessLogic
								.getNonGuaranMatBen(year_F, Double.parseDouble(nongrntdDeathNenft_4Percent))));
//			System.out.println("nonGuaranMatBen_4Percent "
//					+ nonGuaranMatBen_4Percent);
				bussIll.append("<nonGuaranMatBen_4Percent" + _year_F + ">" + nonGuaranMatBen_4Percent
						+ "</nonGuaranMatBen_4Percent" + _year_F + ">");

				nonGuaranMatBen_8Percent = commonForAllProd
						.getRoundUp(commonForAllProd.getStringWithout_E(smartMoneyBackGoldBusinessLogic
								.getNonGuaranMatBen(year_F, Double.parseDouble(nongrntdDeathNenft_8Percent))));
//			System.out.println("nonGuaranMatBen_8Percent "
//					+ nonGuaranMatBen_8Percent);
				bussIll.append("<nonGuaranMatBen_8Percent" + _year_F + ">" + nonGuaranMatBen_8Percent
						+ "</nonGuaranMatBen_8Percent" + _year_F + ">");

				/*
				 * GSV_surrendr_val = commonForAllProd.getRound(commonForAllProd
				 * .getStringWithout_E((smartMoneyBackGoldBusinessLogic
				 * .getGSV_SurrenderValue(year_F, Double.parseDouble(totaBasePrem),
				 * sumGuaranteedSurvivalBen))));
				 */
				sumAnnualizedPrem = sumAnnualizedPrem + Double.parseDouble(totaBasePrem);
				GSV_surrendr_val = commonForAllProd.getRound(commonForAllProd
						.getStringWithout_E((smartMoneyBackGoldBusinessLogic.getGSV_SurrenderValue(year_F,
								sumAnnualizedPrem1, sumSurvivalBenefit, smartMoneyBackGoldBean.getPlanName(),
								smartMoneyBackGoldBean.getpremPayOption(), smartMoneyBackGoldBean.getPolicyTerm()))));
//			System.out.println("GSV_surrendr_val " + GSV_surrendr_val);
				bussIll.append(
						"<GSV_surrendr_val" + _year_F + ">" + GSV_surrendr_val + "</GSV_surrendr_val" + _year_F + ">");

				/********** Modified By Tushar Kotian on 11/8/2017 ****************/

				NonGSV_surrndr_val_4Percent = commonForAllProd
						.getRound("" + (smartMoneyBackGoldBusinessLogic.getNonGSV_surrndr_val_SSV_4(year_F,
								smartMoneyBackGoldBean.getPolicyTerm(), smartMoneyBackGoldBean.getpremPayOption(),
								smartMoneyBackGoldBean.getSumAssured_Basic(), smartMoneyBackGoldBean.getppt(),
								smartMoneyBackGoldBean.getAge(), sumSurvivalBenefit, "4%",
								smartMoneyBackGoldBean.getPlanName())));

//			System.out.println("NonGSV_surrndr_val_4Percent " + NonGSV_surrndr_val_4Percent);
				/********** Modified By Tushar Kotian on 11/8/2017 ****************/

//			NonGSV_surrndr_val_4Percent = ""
//					+ (smartMoneyBackGoldBusinessLogic.getNonGSV_surrndr_val(
//							year_F,
//							Double.parseDouble(nongrntdDeathNenft_4Percent)));
//			System.out.println("NonGSV_surrndr_val_4Percent "
//					+ NonGSV_surrndr_val_4Percent);
				bussIll.append("<NonGSV_surrndr_val_4Percent" + _year_F + ">" + NonGSV_surrndr_val_4Percent
						+ "</NonGSV_surrndr_val_4Percent" + _year_F + ">");

				NonGSV_surrndr_val_8Percent = commonForAllProd
						.getRound("" + (smartMoneyBackGoldBusinessLogic.getNonGSV_surrndr_val_SSV_8(year_F,
								smartMoneyBackGoldBean.getPolicyTerm(), smartMoneyBackGoldBean.getpremPayOption(),
								smartMoneyBackGoldBean.getSumAssured_Basic(), smartMoneyBackGoldBean.getppt(),
								smartMoneyBackGoldBean.getAge(), sumSurvivalBenefit,"8%", smartMoneyBackGoldBean.getPlanName())));
//			System.out.println("NonGSV_surrndr_val_8Percent "
//					+ NonGSV_surrndr_val_8Percent);
				bussIll.append("<NonGSV_surrndr_val_8Percent" + _year_F + ">" + NonGSV_surrndr_val_8Percent
						+ "</NonGSV_surrndr_val_8Percent" + _year_F + ">");
				sumGuaranteedSurvivalBen = sumGuaranteedSurvivalBen + Double.parseDouble(guaranMatBen);
			}

			String guaranMatBen1 = commonForAllProd.getRoundUp(
					commonForAllProd.getStringWithout_E(smartMoneyBackGoldBusinessLogic.getGuaranMatBenMultiplyFactor()
							* smartMoneyBackGoldBean.getSumAssured_Basic()));

//		System.out.println("guaranMatBen "+guaranMatBen);

			String nonGuaranMatBen_4Percent1 = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(
					smartMoneyBackGoldBusinessLogic.getNonGuaranMatBen("4%", smartMoneyBackGoldBean.getPolicyTerm())));

//		System.out.println("nonGuaranMatBen_4Percent "+nonGuaranMatBen_4Percent);
			String nonGuaranMatBen_8Percent1 = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(
					smartMoneyBackGoldBusinessLogic.getNonGuaranMatBen("8%", smartMoneyBackGoldBean.getPolicyTerm())));
//		System.out.println("nonGuaranMatBen_8Percent "+nonGuaranMatBen_8Percent);

			// String premiumPTA="0";
			if (smartMoneyBackGoldBean.getPT_Status()) {
				premiumPTA = "" + smartMoneyBackGoldBusinessLogic.get_Premium_PTA_WithoutST_NotRounded();
//			System.out.println("premiumPTA "+premiumPTA);
				premiumWithoutDiscPTA = ""
						+ smartMoneyBackGoldBusinessLogic.get_Premium_PTA_withoutDisc_WithoutST_NotRounded();
				premiumWithoutDiscSAPTA = ""
						+ smartMoneyBackGoldBusinessLogic.get_Premium_PTA_withoutDiscAndSA_WithoutST_NotRounded();
			} else {
				premiumPTA = "0";
				premiumWithoutDiscPTA = "0";
				premiumWithoutDiscSAPTA = "0";
			}

			// String premiumADB="0";
			if (smartMoneyBackGoldBean.getADB_Status()) {
				premiumADB = "" + smartMoneyBackGoldBusinessLogic.get_Premium_ADB_WithoutST_NotRounded();
//			System.out.println("premiumADB "+premiumADB);

				premiumWithotDiscADB = ""
						+ smartMoneyBackGoldBusinessLogic.get_Premium_WithoutADB_withoutDisc_WithoutST_NotRounded();
				premiumWithotDiscSAADB = "" + smartMoneyBackGoldBusinessLogic
						.get_Premium_WithoutADB_withoutDiscAndSA_WithoutST_NotRounded();
				// System.out.println("premiumADB "+premiumADB);
			} else {
				premiumADB = "0";
				premiumWithotDiscADB = "0";
				premiumWithotDiscSAADB = "0";

			}

			// String premiumATPDB="0";
			if (smartMoneyBackGoldBean.getATPDB_Status()) {
				premiumATPDB = "" + smartMoneyBackGoldBusinessLogic.get_Premium_ATPDB_WithoutST_NotRounded();
//			System.out.println("premiumATPDB "+premiumATPDB);
				/*** modified by Akshaya on 20-MAY-16 end **/
				premiumWithoutDiscATPDB = ""
						+ smartMoneyBackGoldBusinessLogic.get_Premium_ATPDB_WithoutDisc_WithoutST_NotRounded();
				premiumWithoutDiscSAATPDB = ""
						+ smartMoneyBackGoldBusinessLogic.get_Premium_ATPDB_WithoutDiscAndSA_WithoutST_NotRounded();
//			System.out.println("premiumATPDB " + premiumATPDB);
				// System.out.println("premiumATPDB "+premiumATPDB);
			} else {
				premiumATPDB = "0";
				premiumWithoutDiscATPDB = "0";
				premiumWithoutDiscSAATPDB = "0";
			}

			// String premiumCC13NonLinked="0";
			if (smartMoneyBackGoldBean.getCC13NonLinked_Status()) {
				premiumCC13NonLinked = ""
						+ smartMoneyBackGoldBusinessLogic.get_Premium_CC13NonLinked_WithoutST_NotRounded();
//			System.out.println("premiumCC13NonLinked "+premiumCC13NonLinked);
				/*** modified by Akshaya on 20-MAY-16 end **/
				premiumWithoutDiscCC13NonLinked = ""
						+ smartMoneyBackGoldBusinessLogic.get_Premium_CC13NonLinked_WithoutDisc_WithoutST_NotRounded();
				premiumWithoutDiscSACC13NonLinked = "" + smartMoneyBackGoldBusinessLogic
						.get_Premium_CC13NonLinked_WithoutDiscAndSA_WithoutST_NotRounded();
				// System.out.println("premiumCC13NonLinked "+premiumCC13NonLinked);
			} else {
				premiumCC13NonLinked = "0";
				premiumWithoutDiscCC13NonLinked = "0";
				premiumWithoutDiscSACC13NonLinked = "0";
			}

			/********** Modified By Tushar Kotian on 11/9/2017 ****************/
			String premiumInst = commonForAllProd
					.getRoundUp(commonForAllProd.getStringWithout_E(Double.parseDouble(premiumBasic_NotRounded)
							+ Double.parseDouble(premiumPTA) + Double.parseDouble(premiumADB)
							+ Double.parseDouble(premiumATPDB) + Double.parseDouble(premiumCC13NonLinked)));
			/********** Modified By Tushar Kotian on 11/9/2017 ****************/
			// System.out.println("premiumInst "+premiumInst);

			/*** modified by Akshaya on 20-MAY-16 start **/
//		String premiumInstWithST=commonForAllProd.getRoundUp(""+(Integer.parseInt(premiumInst)*(smartMoneyBackGoldBusinessLogic.getServiceTax()+1)));
//		System.out.println("premiumInstWithST "+premiumInstWithST);

//		String serviceTax=""+(Integer.parseInt(premiumInstWithST)-Integer.parseInt(premiumInst));
//		System.out.println("serviceTax "+serviceTax);

//		MinesOccuInterest = ""
//				+ smartMoneyBackGoldBusinessLogic
//						.getMinesOccuInterest(smartMoneyBackGoldBean
//								.getSumAssured_Basic());

			// premiumInst = commonForAllProd.getStringWithout_E(Double
			// .parseDouble(premiumInst)
			// + Double.parseDouble(MinesOccuInterest));

			String premiumBasicWithoutDisc_NotRounded = ""
					+ smartMoneyBackGoldBusinessLogic.get_Premium_Basic_WithoutST_WithoutDisc_NotRounded();

			premWithoutDiscWithoutst = commonForAllProd.getRoundUp(commonForAllProd
					.getStringWithout_E(Double.parseDouble("" + (Double.parseDouble(premiumBasicWithoutDisc_NotRounded)
							+ Double.parseDouble(premiumWithoutDiscPTA) + Double.parseDouble(premiumWithotDiscADB)
							+ Double.parseDouble(premiumWithoutDiscATPDB)
							+ Double.parseDouble(premiumWithoutDiscCC13NonLinked)))));

			premWithoutDiscAndSAWithoutst = ""
					+ smartMoneyBackGoldBusinessLogic.get_Premium_Basic_WithoutST_withoutStaffAndSARebate_NotRounded();
//added by sujata khandekar on 04/11/2019
			// boolean premPayOption = smartMoneyBackGoldBean.getpremPayOption();

			// if(premPayOption){

			// }

			// Added By Saurabh Jain on 14/05/2019 Start
			boolean state = smartMoneyBackGoldBean.getState();

			double basicServiceTax = 0;
			double SBCServiceTax = 0;
			double KKCServiceTax = 0;
			double kerlaServiceTax = 0;
			double KeralaCessServiceTax = 0;

			if (state) {
				basicServiceTax = smartMoneyBackGoldBusinessLogic.getServiceTax(Double.parseDouble(premiumInst),
						smartMoneyBackGoldBean.getJkResident(), "basic");
				kerlaServiceTax = smartMoneyBackGoldBusinessLogic.getServiceTax(Double.parseDouble(premiumInst),
						smartMoneyBackGoldBean.getJkResident(), "KERALA");
				KeralaCessServiceTax = kerlaServiceTax - basicServiceTax;
			} else {
				basicServiceTax = smartMoneyBackGoldBusinessLogic.getServiceTax(Double.parseDouble(premiumInst),
						smartMoneyBackGoldBean.getJkResident(), "basic");
				SBCServiceTax = smartMoneyBackGoldBusinessLogic.getServiceTax(Double.parseDouble(premiumInst),
						smartMoneyBackGoldBean.getJkResident(), "SBC");
				// kerlaServiceTax =
				// smartMoneyBackGoldBusinessLogic.getServiceTax(Double.parseDouble(premiumInst),smartMoneyBackGoldBean.getJkResident(),"KERALA");
				KKCServiceTax = smartMoneyBackGoldBusinessLogic.getServiceTax(Double.parseDouble(premiumInst),
						smartMoneyBackGoldBean.getJkResident(), "KKC");
			}

			String serviceTax = String.valueOf(basicServiceTax + SBCServiceTax + KKCServiceTax + kerlaServiceTax);
			// Added By Saurabh Jain on 14/05/2019 End

			String premiumInstWithST = commonForAllProd
					.getStringWithout_E(Double.parseDouble(premiumInst) + Double.parseDouble(serviceTax));
			/*
			 * System.out.println("premiumInst"+premiumInst);
			 * System.out.println("serviceTax"+serviceTax);
			 */
			// Added By Saurabh Jain on 14/05/2019 Start
			double basicServiceTaxSecondYear = 0;
			double SBCServiceTaxSecondYear = 0;
			double KKCServiceTaxSecondYear = 0;
			double kerlaServiceTaxSecondYear = 0;
			double KeralaCessServiceTaxSecondYear = 0;

			if (state) {
				basicServiceTaxSecondYear = smartMoneyBackGoldBusinessLogic.getServiceTaxSecondYear(
						Double.parseDouble(premiumInst), smartMoneyBackGoldBean.getJkResident(), "basic");
				kerlaServiceTaxSecondYear = smartMoneyBackGoldBusinessLogic.getServiceTaxSecondYear(
						Double.parseDouble(premiumInst), smartMoneyBackGoldBean.getJkResident(), "KERALA");
				KeralaCessServiceTaxSecondYear = kerlaServiceTaxSecondYear - basicServiceTaxSecondYear;
			} else {
				basicServiceTaxSecondYear = smartMoneyBackGoldBusinessLogic.getServiceTaxSecondYear(
						Double.parseDouble(premiumInst), smartMoneyBackGoldBean.getJkResident(), "basic");
				SBCServiceTaxSecondYear = smartMoneyBackGoldBusinessLogic.getServiceTaxSecondYear(
						Double.parseDouble(premiumInst), smartMoneyBackGoldBean.getJkResident(), "SBC");
				KKCServiceTaxSecondYear = smartMoneyBackGoldBusinessLogic.getServiceTaxSecondYear(
						Double.parseDouble(premiumInst), smartMoneyBackGoldBean.getJkResident(), "KKC");
			}

			String serviceTaxSecondYear = String.valueOf(basicServiceTaxSecondYear + SBCServiceTaxSecondYear
					+ KKCServiceTaxSecondYear + kerlaServiceTaxSecondYear);
			// Added By Saurabh Jain on 14/05/2019 End

			String premiumInstWithSTSecondYear = commonForAllProd
					.getStringWithout_E(Double.parseDouble(premiumInst) + Double.parseDouble(serviceTaxSecondYear));

			totInstPrem_exclST = premBasic;

			/******************************
			 * Added by Tushar Kotian On 3/8/2017 start
			 **********************/

			if (Isbackdate.equals("true")) {
				// BackDateinterest=commonForAllProd.getRoundUp(""+(shubhNiveshBussinesLogic.getBackDateInterest(Double.parseDouble(premiumSingleInstBasicWithST),
				// "16-jan-2014")));
				BackDateinterest = commonForAllProd
						.getRoundUp(commonForAllProd.getStringWithout_E(smartMoneyBackGoldBusinessLogic
								.getBackDateInterest(Double.parseDouble(premiumInstWithST), backdate, cashierDate)));
				BackDateinterestwithGST = commonForAllProd
						.getRound(commonForAllProd.getStringWithout_E(Double.parseDouble(BackDateinterest)
								+ (Double.parseDouble(BackDateinterest) * prop.GSTforbackdate)));

			} else {
				BackDateinterest = "0";
			}

			String premiumInstWithST_WithoutBkdate = premiumInstWithST;

			premiumInstWithST = commonForAllProd.getRoundUp(commonForAllProd
					.getStringWithout_E(Double.parseDouble(premiumInstWithST) + Double.parseDouble(BackDateinterest)));

			MinesOccuInterest = "" + smartMoneyBackGoldBusinessLogic
					.getMinesOccuInterest(smartMoneyBackGoldBean.getSumAssured_Basic());

			/******************************
			 * Tushar Kotian On 3/8/2017 end ********************
			 * 
			 * /************************* output starts here
			 ********************************************/

			// PTA
			premPTA = Double.parseDouble(commonForAllProd
					.roundUp_Level2(commonForAllProd.getStringWithout_E(Double.parseDouble(premiumPTA))));
			// ADB
			premADB = Double.parseDouble(commonForAllProd
					.roundUp_Level2(commonForAllProd.getStringWithout_E(Double.parseDouble(premiumADB))));
			// ATPDB
			premATPDB = Double.parseDouble(commonForAllProd
					.roundUp_Level2(commonForAllProd.getStringWithout_E(Double.parseDouble(premiumATPDB))));
			// CC 13 NonLinked
			premCC13NonLinked = Double.parseDouble(commonForAllProd
					.roundUp_Level2(commonForAllProd.getStringWithout_E(Double.parseDouble(premiumCC13NonLinked))));

			// Total Result

			totInstPrem_exclST = premBasic;

			double sumOfRiders = premPTA + premATPDB + premADB + premCC13NonLinked;
			String valPremiumError = valInstPremium(premiumBasic_Rounded, smartMoneyBackGoldBean.getPremiumFreq());
			String valRiderPremiumError = valRiderPremium(Double.parseDouble(premiumInst), sumOfRiders);

			/***********************************
			 * output starts here
			 *****************************************************/

			retVal.append("<?xml version='1.0' encoding='utf-8' ?><smartMoneyBackGold>");

			if (valPremiumError.equals("")) {
				retVal.append("<errCode>0</errCode>");

				if (smartMoneyBackGoldBean.getStaffDisc()) {
					retVal.append("<staffStatus>sbi</staffStatus>");
				} else {
					retVal.append("<staffStatus>none</staffStatus>");
				}
				retVal.append("<staffRebate>" + staffRebate + "</staffRebate>");
				retVal.append("<basicPremNotRounded>"
						+ commonForAllProd.getRoundOffLevel2(
								commonForAllProd.getStringWithout_E(Double.parseDouble(premiumBasic_NotRounded)))
						+ "</basicPremNotRounded>");
				retVal.append("<PremiumTax_WithoutFreqLoading>"
						+ commonForAllProd.getRoundOffLevel2(commonForAllProd
								.getStringWithout_E(Double.parseDouble(premiumWithoutApplicableTax_NotRounded)))
						+ "</PremiumTax_WithoutFreqLoading>");
				retVal.append("<basicPrem>"
						+ commonForAllProd.getRoundOffLevel2(
								commonForAllProd.getStringWithout_E(Double.parseDouble(premiumBasic_NotRounded)))
						+ "</basicPrem>");

				retVal.append("<PremiumWithoutApplicableTax>"
						+ commonForAllProd.getRoundOffLevel2(
								commonForAllProd.getStringWithout_E(Double.parseDouble(premiumWithoutApplicableTax)))
						+ "</PremiumWithoutApplicableTax>");
				retVal.append(
						"<yearlyPrem>" + commonForAllProd.getStringWithout_E(Double.parseDouble(premiumBasic_Rounded))
								+ "</yearlyPrem>");// Parivartan
				retVal.append("<installmentPrem>" + commonForAllProd.getStringWithout_E(Double.parseDouble(premiumInst))
						+ "</installmentPrem>");
				retVal.append("<premInst>" + commonForAllProd.getStringWithout_E(Double.parseDouble(premiumInst))
						+ "</premInst>");// Parivartan

				retVal.append("<serviceTax>" + commonForAllProd.getStringWithout_E(Double.parseDouble(serviceTax))
						+ "</serviceTax>");
				retVal.append("<servcTax>" + commonForAllProd.getStringWithout_E(Double.parseDouble(serviceTax))
						+ "</servcTax>");
				retVal.append("<installmentPremWithST>"
						+ commonForAllProd.getStringWithout_E(Double.parseDouble(premiumInstWithST))
						+ "</installmentPremWithST>");

				retVal.append("<premWthST>" + commonForAllProd.getStringWithout_E(Double.parseDouble(premiumInstWithST))
						+ "</premWthST>");// Parivartan
				retVal.append("<guaranMatBen>" + commonForAllProd.getStringWithout_E(Double.parseDouble(guaranMatBen1))
						+ "</guaranMatBen>");

				retVal.append("<nonGuaranMatBen4>"
						+ commonForAllProd.getStringWithout_E(Double.parseDouble(nonGuaranMatBen_4Percent1))
						+ "</nonGuaranMatBen4>");

				retVal.append("<nonGuaranMatBen8>"
						+ commonForAllProd.getStringWithout_E(Double.parseDouble(nonGuaranMatBen_8Percent1))
						+ "</nonGuaranMatBen8>");

				retVal.append("<basicPremWithoutDisc>"
						+ commonForAllProd.getRoundUp(commonForAllProd
								.getStringWithout_E(Double.parseDouble(premiumBasicWithoutDisc_NotRounded)))
						+ "</basicPremWithoutDisc>");

				// added by sujata
				retVal.append("<GuarateedAddition>"
						+ commonForAllProd.getStringWithout_E(Double.parseDouble(GuarateedAddition))
						+ "</GuarateedAddition>");
				retVal.append(
						"<MaturityBenefit>" + commonForAllProd.getStringWithout_E(Double.parseDouble(MaturityBenefit))
								+ "</MaturityBenefit>");
				retVal.append("<ReversionaryBonus4Per>"
						+ commonForAllProd.getStringWithout_E(Double.parseDouble(ReversionaryBonus4Per))
						+ "</ReversionaryBonus4Per>");
				retVal.append("<ReversionaryBonus8Per>"
						+ commonForAllProd.getStringWithout_E(Double.parseDouble(ReversionaryBonus8Per))
						+ "</ReversionaryBonus8Per>");
				retVal.append("<TotalDeathBenefit4per>"
						+ commonForAllProd.getStringWithout_E(Double.parseDouble(TotalDeathBenefit4per))
						+ "</TotalDeathBenefit4per>");
				retVal.append("<TotalDeathBenefit8per>"
						+ commonForAllProd.getStringWithout_E(Double.parseDouble(TotalDeathBenefit8per))
						+ "</TotalDeathBenefit8per>");
				retVal.append("<TotalMaturityBenefit4per>"
						+ commonForAllProd.getStringWithout_E(Double.parseDouble(TotalMaturityBenefit4per))
						+ "</TotalMaturityBenefit4per>");
				retVal.append("<TotalMaturityBenefit8per>"
						+ commonForAllProd.getStringWithout_E(Double.parseDouble(TotalMaturityBenefit8per))
						+ "</TotalMaturityBenefit8per>");

				retVal.append("<basicPremWithoutDiscSA>"
						+ commonForAllProd.getRoundUp(
								commonForAllProd.getStringWithout_E(Double.parseDouble(premWithoutDiscAndSAWithoutst)))
						+ "</basicPremWithoutDiscSA>");

				retVal.append("<servcTaxSecondYear>" + serviceTaxSecondYear + "</servcTaxSecondYear>");

				retVal.append("<premWthSTSecondYear>" + premiumInstWithSTSecondYear + "</premWthSTSecondYear>");

				retVal.append("<sumOfRiders>" + sumOfRiders + "</sumOfRiders>");

				retVal.append("<BackDateinterest>" + BackDateinterestwithGST + "</BackDateinterest>");
				retVal.append("<BackDateinterestwithoutGST>" + BackDateinterest + "</BackDateinterestwithoutGST>");

				retVal.append("<PremiumWithST_ExcBackDate>" + premiumInstWithST_WithoutBkdate
						+ "</PremiumWithST_ExcBackDate>");

				retVal.append("<MinesOccuInterest>" + MinesOccuInterest + "</MinesOccuInterest>");

				retVal.append("<OccuInt>" + MinesOccuInterest + "</OccuInt>");// Parivartan

				retVal.append("<basicServiceTax>" + commonForAllProd.getStringWithout_E(basicServiceTax)
						+ "</basicServiceTax>" + "<SBCServiceTax>" + commonForAllProd.getStringWithout_E(SBCServiceTax)
						+ "</SBCServiceTax>" + "<KKCServiceTax>" + commonForAllProd.getStringWithout_E(KKCServiceTax)
						+ "</KKCServiceTax>" +

						"<basicServiceTaxSecondYear>" + commonForAllProd.getStringWithout_E(basicServiceTaxSecondYear)
						+ "</basicServiceTaxSecondYear>" + "<SBCServiceTaxSecondYear>"
						+ commonForAllProd.getStringWithout_E(SBCServiceTaxSecondYear) + "</SBCServiceTaxSecondYear>"
						+ "<KKCServiceTaxSecondYear>" + commonForAllProd.getStringWithout_E(KKCServiceTaxSecondYear)
						+ "</KKCServiceTaxSecondYear>" + "<KeralaCessServiceTax>"
						+ commonForAllProd.getStringWithout_E(KeralaCessServiceTax) + "</KeralaCessServiceTax>"
						+ "<KeralaCessServiceTaxSecondYear>"
						+ commonForAllProd.getStringWithout_E(KeralaCessServiceTaxSecondYear)
						+ "</KeralaCessServiceTaxSecondYear>");

				/*** modified by Akshaya on 20-MAY-16 end **/

				if (smartMoneyBackGoldBean.getPT_Status()) {
					retVal.append("<ptaRiderPrem>" + (premPTA) + "</ptaRiderPrem>");
					retVal.append("<premPTA>" + (premPTA) + "</premPTA>");// Parivartan
					retVal.append("<premPTAWithoutDisc>" + commonForAllProd.roundUp_Level2(premiumWithoutDiscPTA)
							+ "</premPTAWithoutDisc>");
					retVal.append("<premPTAWithoutDiscSA>" + commonForAllProd.roundUp_Level2(premiumWithoutDiscSAPTA)
							+ "</premPTAWithoutDiscSA>");
				} else {
					retVal.append("<ptaRiderPrem>0</ptaRiderPrem>");
					retVal.append("<premPTA>0</premPTA>");// Parivartan
					retVal.append("<premPTAWithoutDisc>" + "0" + "</premPTAWithoutDisc>");
					retVal.append("<premPTAWithoutDiscSA>" + "0" + "</premPTAWithoutDiscSA>");
				}

				if (smartMoneyBackGoldBean.getADB_Status()) {
					retVal.append("<adbRiderPrem>" + (premADB) + "</adbRiderPrem>");
					retVal.append("<premADB>" + (premADB) + "</premADB>");// Parivartan
					retVal.append("<premADBWithoutDisc>" + commonForAllProd.roundUp_Level2(premiumWithotDiscADB)
							+ "</premADBWithoutDisc>");
					retVal.append("<premADBWithoutDiscSA>" + commonForAllProd.roundUp_Level2(premiumWithotDiscSAADB)
							+ "</premADBWithoutDiscSA>");
				} else {
					retVal.append("<adbRiderPrem>0</adbRiderPrem>");
					retVal.append("<premADB>0</premADB>");// Parivartan
					retVal.append("<premADBWithoutDisc>" + "0" + "</premADBWithoutDisc>");
					retVal.append("<premADBWithoutDiscSA>" + "0" + "</premADBWithoutDiscSA>");
				}

				if (smartMoneyBackGoldBean.getATPDB_Status()) {
					retVal.append("<atpdbRiderPrem>" + (premATPDB) + "</atpdbRiderPrem>");
					retVal.append("<premATPDB>" + (premATPDB) + "</premATPDB>");// Parivartan

					retVal.append("<premATPDBWithoutDisc>" + commonForAllProd.roundUp_Level2(premiumWithoutDiscATPDB)
							+ "</premATPDBWithoutDisc>");
					retVal.append("<premATPDBWithoutDiscSA>"
							+ commonForAllProd.roundUp_Level2(premiumWithoutDiscSAATPDB) + "</premATPDBWithoutDiscSA>");
				} else {
					retVal.append("<atpdbRiderPrem>0</atpdbRiderPrem>");
					retVal.append("<premATPDBWithoutDisc>" + "0" + "</premATPDBWithoutDisc>");
					retVal.append("<premATPDBWithoutDiscSA>" + "0" + "</premATPDBWithoutDiscSA>");
					retVal.append("<premATPDB>0</premATPDB>");// Parivartan
				}

				if (smartMoneyBackGoldBean.getCC13NonLinked_Status()) {
					retVal.append("<cc13RiderPrem>" + (premCC13NonLinked) + "</cc13RiderPrem>");
					retVal.append("<premCC13>" + (premCC13NonLinked) + "</premCC13>");// Parivartan

					retVal.append(
							"<premCC13WithoutDisc>" + commonForAllProd.roundUp_Level2(premiumWithoutDiscCC13NonLinked)
									+ "</premCC13WithoutDisc>");
					retVal.append("<premCC13WithoutDiscSA>"
							+ commonForAllProd.roundUp_Level2(premiumWithoutDiscSACC13NonLinked)
							+ "</premCC13WithoutDiscSA>");
				} else {
					retVal.append("<cc13RiderPrem>0</cc13RiderPrem>");
					retVal.append("<premCC13>0</premCC13>");// Parivartan
					retVal.append("<premCC13WithoutDisc>" + "0" + "</premCC13WithoutDisc>");
					retVal.append("<premCC13WithoutDiscSA>" + "0" + "</premCC13WithoutDiscSA>");
				}

				retVal.append(bussIll.toString());

			} else {
				retVal.append("<errCode>" + 1 + "</errCode>");
				if (!valPremiumError.equals("")) {
					retVal.append("<minPremError>" + valPremiumError + "</minPremError>");
				}
				/*
				 * if(!valRiderPremiumError.equals("")) {
				 * retVal.append("<minRiderPremError>"+valRiderPremiumError+
				 * "</minRiderPremError>"); }
				 */
			}
			retVal.append("</smartMoneyBackGold>");
			/***************************************
			 * output ends here
			 *********************************************************/

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal.toString();
	}

	// Validate Minimum Premium
	public String valInstPremium(String premiumBasic_Rounded,
			String premFreq) {/*
								 * String error="";
								 * 
								 * if(premFreq.equals("Yearly") &&
								 * (Integer.parseInt(premiumBasic_Rounded)<9500)) {
								 * error="Minimum premium for Yearly Mode under this product is Rs.9500";
								 * 
								 * } else if(premFreq.equals(" Half Yearly") &&
								 * (Integer.parseInt(premiumBasic_Rounded)<5000)) {
								 * error="Minimum premium for Half Yearly Mode under this product is Rs.5000"; }
								 * else if(premFreq.equals("Quarterly") &&
								 * (Integer.parseInt(premiumBasic_Rounded)<1200)) {
								 * error="Minimum premium for Quarterly Mode under this product is Rs.2500";
								 * 
								 * } else
								 * if(premFreq.equals("Monthly")&&(Integer.parseInt(premiumBasic_Rounded)<800))
								 * { error="Minimum premium for Monthly Mode under this product is Rs.800"; }
								 * else
								 * if(premFreq.equals("Single")&&(Integer.parseInt(premiumBasic_Rounded)<125000)
								 * ) { error="Minimum premium for Monthly Mode under this product is Rs.125000";
								 * } return error;
								 */

		String error = "";

		if (premFreq.equals("Yearly") && (Double.parseDouble(premiumBasic_Rounded) < 4500)) {
			error = "Minimum premium for Yearly Mode under this product is Rs.4500";

		} else if (premFreq.equals("Half Yearly") && (Double.parseDouble(premiumBasic_Rounded) < 2400)) {
			error = "Minimum premium for Half Yearly Mode under this product is Rs.2400";
		} else if (premFreq.equals("Quarterly") && (Double.parseDouble(premiumBasic_Rounded) < 1200)) {
			error = "Minimum premium for Quarterly Mode under this product is Rs.1200";

		} else if (premFreq.equals("Monthly") && (Double.parseDouble(premiumBasic_Rounded) < 400)) {
			error = "Minimum premium for Monthly Mode under this product is Rs.400";
		}
		return error;

	}

	// Validate Rider Premium
	public String valRiderPremium(double premBasic, double sumOfRiders) {
		String error = "";
		if ((premBasic * 0.3) < sumOfRiders) {
			error = "Total of Rider Premium should not be greater than 30% of the Base Premium";
		}
		return error;
	}
}
