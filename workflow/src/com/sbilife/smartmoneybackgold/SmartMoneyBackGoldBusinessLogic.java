//workflow
package com.sbilife.smartmoneybackgold;

//import com.sbilife.common.CommonForAllProd;
//import com.sbilife.smartmoneyback.SmartMoneyBackDB;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;

import com.sbilife.common.CommonForAllProd;

public class SmartMoneyBackGoldBusinessLogic {

	private SmartMoneyBackGoldBean smartMoneyBackGoldBean = null;
	private CommonForAllProd commonForAllProd = null;
	private SmartMoneyBackGoldProperties prop = null;
	SmartMoneyBackGoldDB ac = null;

	public SmartMoneyBackGoldBusinessLogic(SmartMoneyBackGoldBean smartMoneyBackGoldBean) {
		this.smartMoneyBackGoldBean = smartMoneyBackGoldBean;
		commonForAllProd = new CommonForAllProd();
		prop = new SmartMoneyBackGoldProperties();
		ac = new SmartMoneyBackGoldDB();
	}

	public double getPR_Basic_FromDB() {
		double prDouble = 0;
		String pr = null, prStr;
		int arrCount = 0;
		String[] prStrArr;
		SmartMoneyBackGoldDB ac = new SmartMoneyBackGoldDB();
		double[] prArr = null;
		// String premFreqMode=smartMoneyBackGoldBean.getPremiumFreq();
		prStr = ac.getBasic_PR_Arr(smartMoneyBackGoldBean.getpremPayOption());

		prStrArr = commonForAllProd.split(prStr, ",");

		// Match J with Basic Policy Term
		int matchFactor = 0;

		/******************
		 * added by sujata on 04/11/2019
		 ****************************************/

		if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
			matchFactor = 1;
		} else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
			matchFactor = 2;
		} else if (smartMoneyBackGoldBean.getPolicyTerm() == 25) {
			matchFactor = 3;
		}

		/*
		 * else if(smartMoneyBackGoldBean.getPolicyTerm() == 15 &&
		 * getpremPayOption()=="Regular") {matchFactor=5;} else
		 * if(smartMoneyBackGoldBean.getPolicyTerm() == 20 &&
		 * getpremPayOption()=="Regular") {matchFactor=6;} else
		 * if(smartMoneyBackGoldBean.getPolicyTerm() == 25 &&
		 * getpremPayOption()=="Regular") {matchFactor=7;} else
		 * if(smartMoneyBackGoldBean.getPolicyTerm() == 15 &&
		 * getpremPayOption()=="Single") {matchFactor=8;} else
		 * if(smartMoneyBackGoldBean.getPolicyTerm() == 20 &&
		 * getpremPayOption()=="Single") {matchFactor=9;} else
		 * if(smartMoneyBackGoldBean.getPolicyTerm() == 25 &&
		 * getpremPayOption()=="Single") {matchFactor=10;}
		 */

		// Here min age is 18 & max age is 55
		for (int i = 14; i < 56; i++) {
			for (int j = 1; j < 4; j++) {
				if (i == smartMoneyBackGoldBean.getAge() && j == matchFactor) {
					pr = prStrArr[arrCount];
					prDouble = Double.parseDouble(pr);
					break;
				}
				arrCount++;
			}
		}
		return prDouble;
	}

	public double getStaffRebate(String cover) {
		if (!cover.equals("CC13NonLinkedRider")) {
			if (smartMoneyBackGoldBean.getStaffDisc()) {
				if (smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
					return 0.02;
				} else {
					return 0.035;
				}

			} else {
				return 0.0;
			}
			// For CC13NonLinkedRider
		} else {
			if (smartMoneyBackGoldBean.getStaffDisc()) {
				return 0.015;
			} else {
				return 0.0;
			}
		}
	}

	public double getSA_Rebate_Basic() {
		// For Regular Premium Mode
		double rebate = 0;
		/*
		 * if(!smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
		 * if(smartMoneyBackGoldBean.getSumAssured_Basic() >=75000 &&
		 * smartMoneyBackGoldBean.getSumAssured_Basic() <200000) {rebate= 0;} else
		 * if(smartMoneyBackGoldBean.getSumAssured_Basic() >= 200000 &&
		 * smartMoneyBackGoldBean.getSumAssured_Basic() < 300000) {rebate= 1.0;} else
		 * if(smartMoneyBackGoldBean.getSumAssured_Basic() >=300000 &&
		 * smartMoneyBackGoldBean.getSumAssured_Basic() <600000) {rebate=2.0;}
		 * //if(smartMoneyBackBean.getSumAssured_Basic() >=600000) else{rebate=3;} }
		 */
		/***********************************
		 * added by Sujata on 04/11/2019
		 **************************************/
		if (smartMoneyBackGoldBean.getpremPayOption().equals("Regular")) {
			if (smartMoneyBackGoldBean.getSumAssured_Basic() >= 200000
					&& smartMoneyBackGoldBean.getSumAssured_Basic() < 500000) {
				rebate = 0;
			} else if (smartMoneyBackGoldBean.getSumAssured_Basic() >= 500000
					&& smartMoneyBackGoldBean.getSumAssured_Basic() < 1000000) {
				rebate = 1.5;
			} else // if(smartMoneyBackBean.getSumAssured_Basic() >=1000000)
			{
				rebate = 2.5;
			}
			return rebate;

		}

		else if (smartMoneyBackGoldBean.getpremPayOption().equals("Limited")) {
			if (smartMoneyBackGoldBean.getSumAssured_Basic() >= 200000
					&& smartMoneyBackGoldBean.getSumAssured_Basic() < 500000) {
				rebate = 0;
			} else if (smartMoneyBackGoldBean.getSumAssured_Basic() >= 500000
					&& smartMoneyBackGoldBean.getSumAssured_Basic() < 1000000) {
				rebate = 2;
			} else // if(smartMoneyBackBean.getSumAssured_Basic() >=1000000)
			{
				rebate = 3.5;
			}

			return rebate;
		}

		else {
			if (smartMoneyBackGoldBean.getSumAssured_Basic() >= 200000
					&& smartMoneyBackGoldBean.getSumAssured_Basic() < 500000) {
				rebate = 0;
			} else if (smartMoneyBackGoldBean.getSumAssured_Basic() >= 500000
					&& smartMoneyBackGoldBean.getSumAssured_Basic() < 1000000) {
				rebate = 10;
			} else // if(smartMoneyBackBean.getSumAssured_Basic() >=1000000)
			{
				rebate = 15;
			}

			return rebate;
		}

	}

	public int getNSAP() {
		return 0;
	}

	public int getEMR() {
		return 0;
	}

	/****************************
	 * added by Sujata on 04/11/2019
	 ****************************************/
	public double getLoadingForFreqOfPremiums(String cover) {
		if (cover.equals("Basic")) {
			if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
					|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
				return prop.Yearly_Model_Factor;
			} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
				return prop.HalfYearly_Modal_Factor;
			} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
				return prop.Quarterly_Modal_Factor;
			} else {
				return prop.Monthly_Modal_Factor;
			}
		} else {
			if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")) {
				return 1;
			} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
				return 1.04;
			} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
				return 1.06;
			} else {
				return 1.068;
			}
		}

	}

	/******************** end ********************************/
	public double get_Premium_Basic_WithoutST_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		}
		// For Monthly Premium Frequency Mode
		else {
			divFactor = 12;
		}

		/*
		 * System.out.println("rate " + getPR_Basic_FromDB());
		 * System.out.println("staff rebate " + getStaffRebate("basic"));
		 * System.out.println("SA rebate " + getSA_Rebate_Basic());
		 * System.out.println("NSAP " + getNSAP()); System.out.println("EMR " +
		 * getEMR()); System.out.println("SA Basic " +
		 * smartMoneyBackGoldBean.getSumAssured_Basic()); System.out.println("loading "
		 * + getLoadingForFreqOfPremiums("Basic")); System.out.println("divfact " +
		 * divFactor); System.out.println("smartMoneyBackGoldBean.getPremiumFreq() " +
		 * smartMoneyBackGoldBean.getPremiumFreq());
		 */

		return (getPR_Basic_FromDB() * (1 - getStaffRebate("basic")) - getSA_Rebate_Basic() + (getNSAP() + getEMR()))
				* smartMoneyBackGoldBean.getSumAssured_Basic() / 1000 * getLoadingForFreqOfPremiums("Basic") / 1;
//        return ( getPR_Basic_FromDB() *(1- getStaffRebate("basic"))- getSA_Rebate_Basic() +( getNSAP() + getEMR()))* smartMoneyBackGoldBean.getSumAssured_Basic() /1000;

	}

	/******************
	 * Added By Tushar Kotian on 19/9/2017 START
	 **********************/

	public double get_Premium_Without_ST_WithoutLoadingFreq() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		}
		// For Monthly Premium Frequency Mode
		else {
			divFactor = 12;
		}

		/*
		 * System.out.println("rate " + getPR_Basic_FromDB());
		 * System.out.println("staff rebate " + getStaffRebate("basic"));
		 * System.out.println("SA rebate " + getSA_Rebate_Basic());
		 * System.out.println("NSAP " + getNSAP()); System.out.println("EMR " +
		 * getEMR()); System.out.println("SA Basic " +
		 * smartMoneyBackGoldBean.getSumAssured_Basic()); System.out.println("loading "
		 * + getLoadingForFreqOfPremiums("Basic")); System.out.println("divfact " +
		 * divFactor);
		 */
		return (getPR_Basic_FromDB() * (1 - getStaffRebate("basic")) - getSA_Rebate_Basic() + (getNSAP() + getEMR()))
				* smartMoneyBackGoldBean.getSumAssured_Basic() / 1000;
//        return ( getPR_Basic_FromDB() *(1- getStaffRebate("basic"))- getSA_Rebate_Basic() +( getNSAP() + getEMR()))* smartMoneyBackGoldBean.getSumAssured_Basic() /1000;

	}

	/******************
	 * Added By Tushar Kotian on 19/9/2017 END
	 **********************/

	public double get_Premium_PTA_WithoutST_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 0;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		} else {
			divFactor = 12;
		}
		/*
		 * System.out.println("pta rate " + getPR_PTA_FromDB());
		 * System.out.println("staff rebate " + getStaffRebate("PT"));
		 * System.out.println("SA rebate " + getSA_Rebate_Rider());
		 * System.out.println("NSAP " + getNSAP()); System.out.println("EMR " +
		 * getEMR()); System.out.println("SA Basic " +
		 * smartMoneyBackGoldBean.getSumAssured_PT()); System.out.println("loading " +
		 * getLoadingForFreqOfPremiums("Rider")); System.out.println("divfact " +
		 * divFactor);
		 */
		return (getPR_PTA_FromDB() * (1 - getStaffRebate("PT")) - getSA_Rebate_Rider() + (getNSAP() + getEMR()))
				* smartMoneyBackGoldBean.getSumAssured_PT() / 1000 * getLoadingForFreqOfPremiums("Rider") / divFactor;
	}

	public double getPR_PTA_FromDB() {
		double prDouble = 0;
		String pr = null, prStr;
		int arrCount = 0;
		String[] prStrArr;
		SmartMoneyBackGoldDB ac = new SmartMoneyBackGoldDB();

		prStr = ac.getPTA_PremiumRate_Arr();
		prStrArr = commonForAllProd.split(prStr, ",");
		// Here min age is 18 & max age is 65/min term is 5 & max term is 30
		for (int i = 18; i < 61; i++) {
			for (int j = 5; j < 31; j++) {
				if (i == smartMoneyBackGoldBean.getAge() && j == smartMoneyBackGoldBean.getPolicyTerm_PT()) {
					pr = prStrArr[arrCount];
					prDouble = Double.parseDouble(pr);
					break;
				}
				arrCount++;
			}
		}
		return prDouble;
	}

	public int getSA_Rebate_Rider() {
		return 0;
	}

	// ADB Start
	public double get_Premium_ADB_WithoutST_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		} else {
			divFactor = 12;
		}
		/*
		 * System.out.println("pta rate " + getPR_Rider_FromDB("ADB"));
		 * System.out.println("staff rebate " + getStaffRebate("ADB"));
		 * System.out.println("SA rebate " + getSA_Rebate_Rider());
		 * System.out.println("NSAP " + getNSAP()); System.out.println("EMR " +
		 * getEMR()); System.out.println("SA Basic " +
		 * smartMoneyBackGoldBean.getSumAssured_ADB()); System.out.println("loading " +
		 * getLoadingForFreqOfPremiums("Rider")); System.out.println("divfact " +
		 * divFactor);
		 */
		return (getPR_Rider_FromDB("ADB") * (1 - getStaffRebate("ADB")) - getSA_Rebate_Rider() + (getNSAP() + getEMR()))
				* smartMoneyBackGoldBean.getSumAssured_ADB() / 1000 * getLoadingForFreqOfPremiums("Rider") / divFactor;
	}

	public double getPR_Rider_FromDB(String whichRider) {
		// For Regular Mode of Premium Frequency
		if (!smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			if (whichRider.equals("ADB")) {
				return 0.5;
			} else {
				return 0.4;
			}
		}
		// For Single Mode of Premium Frequency
		else {
			double pr = 0;
			SmartMoneyBackGoldDB ac = new SmartMoneyBackGoldDB();
			int[] prArr = null;
			if (whichRider.equals("ADB")) {
				prArr = ac.getADB_PremiumRate_Arr();
			} else {
				prArr = ac.getATPDB_PremiumRate_Arr();
			}
			for (int i = 5; i < 31; i++) {
				if (i == smartMoneyBackGoldBean.getPolicyTerm_ADB()) {
					pr = prArr[i - 5];
					break;
				}
			}
			double rate = pr / 100;
			return rate;
		}
	}

	// ATPDB Start
	public double get_Premium_ATPDB_WithoutST_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		} else {
			divFactor = 12;
		}

		/*
		 * System.out.println("pta rate " + getPR_Rider_FromDB("ATPDB"));
		 * System.out.println("staff rebate " + getStaffRebate("ATPDB"));
		 * System.out.println("SA rebate " + getSA_Rebate_Rider());
		 * System.out.println("NSAP " + getNSAP()); System.out.println("EMR " +
		 * getEMR()); System.out.println("SA Basic " +
		 * smartMoneyBackGoldBean.getSumAssured_ATPDB()); System.out.println("loading "
		 * + getLoadingForFreqOfPremiums("Rider")); System.out.println("divfact " +
		 * divFactor);
		 */
		return (getPR_Rider_FromDB("ATPDB") * (1 - getStaffRebate("ATPDB")) - getSA_Rebate_Rider()
				+ (getNSAP() + getEMR())) * smartMoneyBackGoldBean.getSumAssured_ATPDB() / 1000
				* getLoadingForFreqOfPremiums("Rider") / divFactor;
	}

	public double get_Premium_CC13NonLinked_WithoutST_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		} else {
			divFactor = 12;
		}

		/*
		 * System.out.println("@ getPR_CC13NonLinked_FromDB() -> "
		 * +getPR_CC13NonLinked_FromDB());
		 * System.out.println("@ getStaffRebate('CC13NonLinkedRider') ->"+
		 * getStaffRebate("CC13NonLinkedRider"));
		 * System.out.println("@ getSA_Rebate_Rider() -> "+getSA_Rebate_Rider());
		 * System.out.println("@ getNSAP() -> "+getNSAP());
		 * System.out.println("@ getEMR() -> "+getEMR());
		 * System.out.println("@ smartMoneyBackBean.getSumAssured_PT() -> "
		 * +smartMoneyBackGoldBean.getSumAssured_PT());
		 * System.out.println("@ getLoadingForFreqOfPremiums('CC13NonLinked') -> "
		 * +getLoadingForFreqOfPremiums("CC13NonLinked"));
		 */

		return (getPR_CC13NonLinked_FromDB() * (1 - getStaffRebate("CC13NonLinkedRider")) - getSA_Rebate_Rider()
				+ (getNSAP() + getEMR())) * smartMoneyBackGoldBean.getSumAssured_CC13NonLinked() / 1000
				* getLoadingForFreqOfPremiums("Rider") / divFactor;
	}

	// This method is used to select Basic PR for CC 13 Non Linked Rider from the
	// Smart Money Back Constant Data
	public double getPR_CC13NonLinked_FromDB() {
		double pr = 0;
		SmartMoneyBackGoldDB ac = new SmartMoneyBackGoldDB();
		double[] prArr = null;
		String premFreqMode = smartMoneyBackGoldBean.getPremiumFreq();
		if (premFreqMode.equals("Single")) {
			prArr = ac.getCC13NonLinked_PremiumRate_Arr("Single");
		} else {
			prArr = ac.getCC13NonLinked_PremiumRate_Arr("Regular");
		}
		for (int i = 18; i < 65; i++) {
			if (i == smartMoneyBackGoldBean.getAge()) {
				pr = prArr[i - 18];
				break;
			}
		}
		double rate = pr;
		return rate;
	}

	/*** modified by Akshaya on 20-MAY-16 start **/
	/*
	 * Modified by Vrushali Chaudhari on 07/04/2015
	 */
//    public double getServiceTax()
//    {
//    	if(smartMoneyBackGoldBean.getJkResident())
//    		return smartMoneyBackGoldProperties.serviceTaxJkResident;
//    	else
//    		return smartMoneyBackGoldProperties.serviceTax;
//    }

	public double getServiceTax(double premiumWithoutST, boolean JKResident, String type) {
		if (type.equals("basic")) {
//			if(JKResident==true)
//				return Double.parseDouble(commonForAllProd.getRoundOffLevel2New(String.valueOf(premiumWithoutST*prop.serviceTaxJkResident)));
//			else
//			{
			return Double.parseDouble(
					commonForAllProd.getRoundOffLevel2New(String.valueOf(premiumWithoutST * prop.serviceTax)));
//			}
		} else if (type.equals("SBC")) {
//			if(JKResident==true)
//				return 0;
//			else
//			{
			return Double.parseDouble(
					commonForAllProd.getRoundOffLevel2New(String.valueOf(premiumWithoutST * prop.SBCServiceTax)));
//			}
		} else // KKC
		{
//			if(JKResident==true)
//				return 0;
//			else
//			{
			return Double.parseDouble(
					commonForAllProd.getRoundOffLevel2New(String.valueOf(premiumWithoutST * prop.KKCServiceTax)));
//			}
		}

	}

	public double getServiceTaxSecondYear(double premiumWithoutST, boolean JKResident, String type) {
		if (type.equals("basic")) {
//				if(JKResident==true)
//					return Double.parseDouble(commonForAllProd.getRoundOffLevel2New(String.valueOf(premiumWithoutST*prop.serviceTaxJkResident)));
//				else
//				{
			return Double.parseDouble(commonForAllProd
					.getRoundOffLevel2New(String.valueOf(premiumWithoutST * prop.serviceTaxSecondYear)));
//				}
		} else if (type.equals("SBC")) {
//				if(JKResident==true)
//					return 0;
//				else
//				{
			return Double.parseDouble(commonForAllProd
					.getRoundOffLevel2New(String.valueOf(premiumWithoutST * prop.SBCServiceTaxSecondYear)));
//				}
		} else // KKC
		{
//				if(JKResident==true)
//					return 0;
//				else
//				{
			return Double.parseDouble(commonForAllProd
					.getRoundOffLevel2New(String.valueOf(premiumWithoutST * prop.KKCServiceTaxSecondYear)));
//				}
		}

	}

	/***
	 * modified by Akshaya on 20-MAY-16 end
	 * 
	 * @param planname
	 **/

	public double getBonusRate(String returnOnInvestment, String planname) {
		// For Single Premium
//        if(smartMoneyBackGoldBean.getPremiumFreq().equals("Single"))
//        {return 0;}
//        //For Regular Premium
//        else
//        {
		double bonusrate = 0;
		/********************* added by sujata on 04/11/2019 *************************/
		String premFreqMode = smartMoneyBackGoldBean.getpremPayOption();
		if (planname.equals("Option 1")) {

			if (returnOnInvestment.equals("4%") && (premFreqMode.equals("Regular")))

				if (smartMoneyBackGoldBean.getPolicyTerm() == 15)
				// if(policyTerm == 15 && premPayOption == "Regular")
				// {bonusrate = 0.017;}
				{
					 bonusrate = 0.016; 
					//bonusrate = 0.015;
				}
				// For 10%
				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20)

				// bonusrate = 0.031;
				{
					 bonusrate = 0.015; 
					//bonusrate = 0.014;
				}

				else {
					 bonusrate = 0.014;
					//bonusrate = 0.013;
				}

			else if (returnOnInvestment.equals("8%") && (premFreqMode.equals("Regular")))

				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					 bonusrate = 0.034;
					//bonusrate = 0.035;
				}

				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.036; 
					//bonusrate = 0.035;
				}

				else {
					 bonusrate = 0.038; 
					//bonusrate = 0.035;
				}

			if (returnOnInvestment.equals("4%") && (premFreqMode.equals("Limited")))
				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					bonusrate = 0.014;
				}
				// For 10%
				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.011; 
					//bonusrate = 0.012;
				}

				else {
					 bonusrate = 0.01; 
					//bonusrate = 0.012;
				}

			else if (returnOnInvestment.equals("8%") && (premFreqMode.equals("Limited")))

				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					bonusrate = 0.038; 
					//bonusrate = 0.035;
				}

				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.04; 
					//bonusrate = 0.035;
				}

				else {
				 bonusrate = 0.043; 
					//bonusrate = 0.035;
				}

			if (returnOnInvestment.equals("4%") && (premFreqMode.equals("Single")))
				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					 bonusrate =0.006; 
					//bonusrate = 0.007;
				}
				// For 10%
				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.005;
					//bonusrate = 0.006;
				}

				else {
					 bonusrate = 0.003;
					//bonusrate = 0.005;
				}

			else if (returnOnInvestment.equals("8%") && (premFreqMode.equals("Single")))

				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					 bonusrate =0.04; 
					//bonusrate = 0.0425;
				}

				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
				 bonusrate = 0.043;
					//bonusrate = 0.0425;
				}

				else {
					 bonusrate = 0.045; 
					//bonusrate = 0.0425;
				}

		} else if (planname.equals("Option 2")) {
			if (returnOnInvestment.equals("4%") && (premFreqMode.equals("Limited")))
				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					 bonusrate =0.014; 
					//bonusrate = 0.014;
				}
				// For 10%
				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.011;
					//bonusrate = 0.012;
				}

				else {
					bonusrate = 0.01;
					//bonusrate = 0.012;
				}

			else if (returnOnInvestment.equals("8%") && (premFreqMode.equals("Limited")))

				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					 bonusrate =0.038; 
					//bonusrate = 0.035;
				}

				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.04; 
					//bonusrate = 0.035;
				}

				else {
					 bonusrate = 0.043;
					//bonusrate = 0.035;
				}

			if (returnOnInvestment.equals("4%") && (premFreqMode.equals("Single")))
				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					 bonusrate =0.006; 
					//bonusrate = 0.007;
				}
				// For 10%
				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.005; 
					//bonusrate = 0.006;
				}

				else {
					 bonusrate = 0.003; 
					//bonusrate = 0.005;
				}

			else if (returnOnInvestment.equals("8%") && (premFreqMode.equals("Single")))

				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					bonusrate =0.04; 
					//bonusrate = 0.0425;
				}

				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.043; 
					//bonusrate = 0.0425;
				}

				else {
					 bonusrate = 0.045; 
					//bonusrate = 0.0425;
				}

			if (returnOnInvestment.equals("4%") && (premFreqMode.equals("Regular")))

				if (smartMoneyBackGoldBean.getPolicyTerm() == 15)
				// if(policyTerm == 15 && premPayOption == "Regular")
				// {bonusrate = 0.017;}
				{
					 bonusrate = 0.016; 
					//bonusrate = 0.015;
				}
				// For 10%
				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20)

				// bonusrate = 0.031;
				{
					bonusrate = 0.015;
					//bonusrate = 0.014;
				}

				else {
					 bonusrate = 0.014; 
					//bonusrate = 0.013;
				}

			else if (returnOnInvestment.equals("8%") && (premFreqMode.equals("Regular")))

				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					 bonusrate = 0.034; 
					//bonusrate = 0.035;
				}

				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.036; 
					//bonusrate = 0.035;
				}

				else {
					bonusrate = 0.038; 
					//bonusrate = 0.035;
				}

		} else if (planname.equals("Option 3")) {
			if (returnOnInvestment.equals("4%") && (premFreqMode.equals("Single")))
				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					 bonusrate =0.006;
					//bonusrate = 0.007;
				}
				// For 10%
				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.005;
					//bonusrate = 0.006;

				}

				else {
					 bonusrate = 0.003; 
					//bonusrate = 0.005;
				}

			else if (returnOnInvestment.equals("8%") && (premFreqMode.equals("Single")))

				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					 bonusrate =0.04;
					//bonusrate = 0.0425;
				}

				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.043;
					//bonusrate = 0.0425;
				}

				else {
					 bonusrate = 0.045;
					//bonusrate = 0.0425;
				}

			if (returnOnInvestment.equals("4%") && (premFreqMode.equals("Regular")))

				if (smartMoneyBackGoldBean.getPolicyTerm() == 15)
				// if(policyTerm == 15 && premPayOption == "Regular")
				// {bonusrate = 0.017;}
				{
					bonusrate = 0.016; 
					//bonusrate = 0.015;

				}
				// For 10%
				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20)

				// bonusrate = 0.031;
				{
					 bonusrate = 0.015; 
					//bonusrate = 0.014;
				}

				else {
					 bonusrate = 0.014; 
					//bonusrate = 0.013;
				}

			else if (returnOnInvestment.equals("8%") && (premFreqMode.equals("Regular")))

				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
				 bonusrate = 0.034; 
					//bonusrate = 0.035;
				}

				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.036; 
					//bonusrate = 0.035;
				}

				else {
					 bonusrate = 0.038; 
					//bonusrate = 0.035;
				}

			if (returnOnInvestment.equals("4%") && (premFreqMode.equals("Limited")))
				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					 bonusrate =0.014;
					//bonusrate = 0.014;
				}
				// For 10%
				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.011;
					//bonusrate = 0.012;
				}

				else {
					 bonusrate = 0.01; 
					//bonusrate = 0.012;
				}

			else if (returnOnInvestment.equals("8%") && (premFreqMode.equals("Limited")))

				if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
					 bonusrate =0.038; 
					//bonusrate = 0.035;
				}

				else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
					 bonusrate = 0.04;
					//bonusrate = 0.035;
				}

				else {
					 bonusrate = 0.043; 
					//bonusrate = 0.035;
				}

		}

		/*
		 * else if(planname.equals("Option 4")) { if(returnOnInvestment.equals("4%"))
		 * {bonusrate = 0.014;} //For 10% else { bonusrate = 0.033; } }
		 */
//        }
		return bonusrate;
		/******************* end **************************/
	}

	public double getNonGuaranMatBen(String returnOnInvestment, int term) {
		double terminalBonus = 0;
		if (term == smartMoneyBackGoldBean.getPolicyTerm()) {
			terminalBonus = 1 + 0.15;
		} else {
			terminalBonus = 1;
		}

		return smartMoneyBackGoldBean.getSumAssured_Basic()
				* getBonusRate(returnOnInvestment, smartMoneyBackGoldBean.getPlanName())
				* smartMoneyBackGoldBean.getPolicyTerm() * terminalBonus;
	}

	public double getGuaranMatBenMultiplyFactor() {
		if (smartMoneyBackGoldBean.getPolicyTerm() == 12) {
			if (smartMoneyBackGoldBean.getPlanName().equals("Option 1")) {
				return (double) 0.50;
			} else if (smartMoneyBackGoldBean.getPlanName().equals("Option 2")) {
				return (double) 0.15;
			} else if (smartMoneyBackGoldBean.getPlanName().equals("Option 3")) {
				return (double) 0.15;
			} else {
				return 0;
			}
		} else if (smartMoneyBackGoldBean.getPolicyTerm() == 15) {
			if (smartMoneyBackGoldBean.getPlanName().equals("Option 2")) {
				return (double) 0.50;
			} else if (smartMoneyBackGoldBean.getPlanName().equals("Option 4")) {
				return (double) 0.15;
			} else {
				return 0;
			}
		} else if (smartMoneyBackGoldBean.getPolicyTerm() == 20) {
			if (smartMoneyBackGoldBean.getPlanName().equals("Option 3")) {
				return (double) 0.50;
			} else if (smartMoneyBackGoldBean.getPlanName().equals("Option 4")) {
				return (double) 0.15;
			} else {
				return 0;
			}
		} else if (smartMoneyBackGoldBean.getPolicyTerm() == 25) {
			if (smartMoneyBackGoldBean.getPlanName().equals("Option 4")) {
				return (double) 0.50;
			} else {
				return 0;
			}
		} else {
			return 0;
		}

	}
	/*
	 * public double getGuarMaturityBenefit(int year_F, int PolicyTerm) { if (year_F
	 * == smartMoneyBackGoldBean.getPolicyTerm()) { return
	 * smartMoneyBackGoldBean.getSumAssured_Basic(); } else if() return 0;
	 * 
	 * 
	 * }
	 */

	public double getTotBasePrem(double premBasic, String premFreq) {
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")) {
			return premBasic * 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			return premBasic * 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			return premBasic * 4;
		} else {
			return premBasic * 12;
		}
	}

	public double get_Total_Base_Premium_Paid(int year_F, double premBasic, int PolicyTerm, String PremiumFreq) {
		if (year_F != 1 && PremiumFreq.equalsIgnoreCase("Single")) {
			return 0;
		} else {
			if (year_F <= PolicyTerm)
				return premBasic;
			else
				return 0;

		}
	}

	// added by sujata
	public double get_Annualized_Premium(int year_F, double premBasic, int PolicyTerm, String PremiumFreq, int ppt) {
		if (year_F <= ppt) {
			return get_Total_Base_Premium_Paid(year_F, premBasic, PolicyTerm, PremiumFreq);
		} else
			return 0;
	}

	public double getannulizedPremFinal(int year_F, int ppt) {
		if (year_F <= ppt) {
			return get_Premium_Without_ST_WithoutLoadingFreq();
		} else {
			return 0;
		}
	}

	// added by sujata
	public double get_Guarateed_add(int year_F, int PolicyTerm) {
		if (year_F > PolicyTerm)
			return 0;
		else
			return 0;

	}

	// added by sujata
	public double Maturity_Benefit(int year_F, int PolicyTerm) {
		if (year_F == PolicyTerm)
			return getGuaranMatBen(year_F);
		else
			return 0;
	}
	
	

	// 25-11-2019
	public double Reversionary_Bonus(int year_F, int PolicyTerm, double SumAssured_Basic, String returnOnInvestment,
			String planname) 
	{
		double ReversionaryBonusValue;
		if (year_F > PolicyTerm)
			return 0;
		else
			ReversionaryBonusValue = (smartMoneyBackGoldBean.getSumAssured_Basic()
					* getBonusRate(returnOnInvestment, planname)) * year_F;
		return ReversionaryBonusValue;

	}

	// added by sujata
	/*
	 * public double Reversionary_Bonus8per(int year_F, int PolicyTerm, double
	 * SumAssured_Basic, String returnOnInvestment, String planName) { double
	 * ReversionaryBonus8PerValue; double bonus ; if (year_F > PolicyTerm) return 0;
	 * else // return smartMoneyBackGoldBean.getSumAssured_Basic()* 0.036 * year_F;
	 * // 10/02/2023 bonus = getBonusRate(returnOnInvestment, planName);
	 * ReversionaryBonus8PerValue = (smartMoneyBackGoldBean.getSumAssured_Basic()
	 * getBonusRate(returnOnInvestment, planName)) * year_F; // return
	 * smartMoneyBackGoldBean.getSumAssured_Basic() * //
	 * getBonusRate(returnOnInvestment, planName) * year_F;rever return
	 * ReversionaryBonus8PerValue;
	 * 
	 * }
	 */

	public double Reversionary_Bonus8per(int year_F, int PolicyTerm, double SumAssured_Basic, String returnOnInvestment,
			String planName) {
		double bonus;

		if (year_F > PolicyTerm)
			return 0;
		else

			bonus = getBonusRate(returnOnInvestment, planName);
		return smartMoneyBackGoldBean.getSumAssured_Basic() * getBonusRate(returnOnInvestment, planName) * year_F;

	}

	// 06-12-2019
	public double TotalDeathBenefit4per(int year_F, int PolicyTerm, int age, String returnOnInvestment, String planname,
			double SumAssured_Basic, double premiumBasic_Rounded, double totaBasePrem, double annualizedPrem,
			double sumAnnualizedPrem) {
		double max1, max2, totalDeathBenefit4;
		if (year_F > PolicyTerm)
			return 0;
		else
			max1 = (getGuarntedDeathBenefit(year_F, SumAssured_Basic, PolicyTerm, age, premiumBasic_Rounded,
					totaBasePrem, annualizedPrem)
					+ ((Reversionary_Bonus(year_F, PolicyTerm, SumAssured_Basic, returnOnInvestment, planname) * 0.15))
					+ (Reversionary_Bonus(year_F, PolicyTerm, SumAssured_Basic, returnOnInvestment, planname)));

		max2 = (1.05 * (sumAnnualizedPrem));

		totalDeathBenefit4 = Math.max(max1, max2);

		return totalDeathBenefit4;

	}

	// added by sujata 05-12-2019
	public double TotalDeathBenefit8per(int year_F, int PolicyTerm, int age, String returnOnInvestment, String planname,
			double SumAssured_Basic, double premiumBasic_Rounded, double totaBasePrem, double annualizedPrem,
			double sumAnnualizedPrem) {
		double max1, max2, totalDeathBenefit8;
		double guranteddeathbenefit, reversionarybonus8per1, reversionarybonus8per2;
		if (year_F > PolicyTerm)
			return 0;
		else

			max1 = (getGuarntedDeathBenefit(year_F, SumAssured_Basic, PolicyTerm, age, premiumBasic_Rounded,
					totaBasePrem, annualizedPrem)
					+ (Reversionary_Bonus8per(year_F, PolicyTerm, SumAssured_Basic, returnOnInvestment, planname)
							* 0.15)
					+ Reversionary_Bonus8per(year_F, PolicyTerm, SumAssured_Basic, returnOnInvestment, planname));


		max2 = (1.05 * (sumAnnualizedPrem));

		totalDeathBenefit8 = Math.max(max1, max2);

		return totalDeathBenefit8;

	}// added by sujata

	public double TotalMaturityBenefit4per(int year_F, int PolicyTerm, double SumAssured_Basic,
			String returnOnInvestment, String planname) {
		if (year_F == PolicyTerm)
			return (Maturity_Benefit(year_F, PolicyTerm)
					+ ((Reversionary_Bonus(year_F, PolicyTerm, SumAssured_Basic, returnOnInvestment, planname) * 0.15))
					+ (Reversionary_Bonus(year_F, PolicyTerm, SumAssured_Basic, returnOnInvestment, planname)));
		else
			return 0;
	}

	public double TotalMaturityBenefit8per(int year_F, int PolicyTerm, double SumAssured_Basic,
			String returnOnInvestment, String planName) {
		if (year_F == PolicyTerm) {
			return (Maturity_Benefit(year_F, PolicyTerm)
					+ ((Reversionary_Bonus8per(year_F, PolicyTerm, SumAssured_Basic, returnOnInvestment, planName)
							* 0.15))
					+ (Reversionary_Bonus8per(year_F, PolicyTerm, SumAssured_Basic, returnOnInvestment, planName)));
		} else {
			return 0;
		}
	}
//    public double getGuarntedDeathBenefit(int year_F, double SumAssured_Basic,
//			int PolicyTerm) {
//		if (year_F <= PolicyTerm)
//			return SumAssured_Basic;
//		else
//			return 0;
//	}

	// added by sujata 05-12-2019
	public double getGuarntedDeathBenefit(int year_F, double SumAssured_Basic, int PolicyTerm, int age,
			double premiumBasic_Rounded, double totaBasePrem, double annualizedPrem) {

		double GuarntedDeathBenefit = 0;
		int multiple = 0;
		if (age < 45) {
			multiple = 10;
		} else {
			multiple = 7;
		}

		if (year_F <= PolicyTerm) {
			if (smartMoneyBackGoldBean.premPayOption.equalsIgnoreCase("Single")) {

				// double val1 = multiple * get_Premium_Without_ST_WithoutLoadingFreq();
				double val1 = annualizedPrem * 1.25;
				// double val2 =1.05 * totaBasePrem ;
				GuarntedDeathBenefit = Math.max(SumAssured_Basic, val1);
			} else {
				double val3 = annualizedPrem * 11;

				GuarntedDeathBenefit = Math.max(SumAssured_Basic, val3);
			}
			return GuarntedDeathBenefit;
		}
		// GuarntedDeathBenefit = Math.max(val3, val2);
		else {
			return 0;
		}
	}

	// added by sujata on 08-11-2019
	public double getGuarDeathBenefitint(int year_F, double SumAssured_Basic, int PolicyTerm) {
		if (year_F <= PolicyTerm) {
			return SumAssured_Basic;
		}
		return 0;
	}

	public double getNonGuarntedDeathBenefit(int year_F, String returnOnInvestment) {
		double terminalBonus = 0;
		if (year_F == smartMoneyBackGoldBean.getPolicyTerm()) {
			terminalBonus = 1 + 0.15;
		} else {
			terminalBonus = 1;
		}
		return smartMoneyBackGoldBean.getSumAssured_Basic()
				* getBonusRate(returnOnInvestment, smartMoneyBackGoldBean.getPlanName()) * year_F * terminalBonus;
	}

	// added by sujata on 06/11/2019
	public double getGuaranMatBen(int year_F) {
		/*
		 * if (smartMoneyBackGoldBean.getPlanName().equals("Option 0") && (year_F % 3)
		 * == 0) { if (year_F == smartMoneyBackGoldBean.getPolicyTerm()) { return 0.50
		 * smartMoneyBackGoldBean.getSumAssured_Basic(); } else { return 0.20
		 * smartMoneyBackGoldBean.getSumAssured_Basic(); } } else
		 */if (smartMoneyBackGoldBean.getPlanName().equals("Option 1") && (year_F % 3) == 0) {
			if (year_F == smartMoneyBackGoldBean.getPolicyTerm()) {
				return 0.50 * smartMoneyBackGoldBean.getSumAssured_Basic();
			} else {
				return 0.15 * smartMoneyBackGoldBean.getSumAssured_Basic();
			}
		} else if (smartMoneyBackGoldBean.getPlanName().equals("Option 2") && (year_F % 4) == 0) {
			if (year_F == smartMoneyBackGoldBean.getPolicyTerm()) {
				return 0.50 * smartMoneyBackGoldBean.getSumAssured_Basic();
			} else {
				return 0.15 * smartMoneyBackGoldBean.getSumAssured_Basic();
			}
		} else if (smartMoneyBackGoldBean.getPlanName().equals("Option 3") && (year_F % 5) == 0) {
			if (year_F == smartMoneyBackGoldBean.getPolicyTerm()) {
				return 0.50 * smartMoneyBackGoldBean.getSumAssured_Basic();
			} else {
				return 0.15 * smartMoneyBackGoldBean.getSumAssured_Basic();
			}

		} else {
			if (year_F == smartMoneyBackGoldBean.getPolicyTerm()) {
				return smartMoneyBackGoldBean.getSumAssured_Basic();
			} else
				return 0;
		}

	}

	public double getNonGuaranMatBen(int year_F, double nonGuarantedDeathBenefit) {
		if (year_F == smartMoneyBackGoldBean.getPolicyTerm())
			return nonGuarantedDeathBenefit;
		else
			return 0;
	}

	/*
	 * public double getGSV_SurrenderValue(int year_F,double totaBasePrem,double
	 * sumGuaranteedSurvivalBen) { int a=0; double ab=0,b=0,c=0;
	 * 
	 * 
	 * double[] regularGSV=ac.getGSV_RP_Arr(); b=regularGSV[year_F-1]/100;
	 * 
	 * if(smartMoneyBackGoldBean.getPolicyTerm()>9) { if(year_F>2) a=1; else a=0; }
	 * else { if(year_F>1) a=1; else a=0; }
	 * if(year_F<=smartMoneyBackGoldBean.getPolicyTerm()) {
	 * c=sumGuaranteedSurvivalBen;
	 * 
	 * 
	 * } else c=0; //
	 * System.out.println("  totaBasePrem  "+totaBasePrem+"    "+b+"   "+a+"   "+c);
	 * return Math.max(0,(totaBasePrem*b*a)-c); }
	 */

	// added by sujata

	public double getSurvivalBenefit(int year_F) {
		if (getGuaranMatBen(year_F) == 0) {
			return 0;
		} else {
			if (year_F > smartMoneyBackGoldBean.getPolicyTerm()) {
				return 0;
			}
			if (year_F == smartMoneyBackGoldBean.getPolicyTerm()) {
				return 0;
			} else {
				return getGuaranMatBen(year_F);
			}

		}
	}

	public double getGSV_SurrenderValue(int year_F, double sumAnnualizedPrem, double sumSurvivalBenefit,
			String PlanName, String premPayOption, int policyterm) {
		int a = 0;
		double ab = 0, b = 0, c = 0, p = 0, q = 0, s = 0, SurrenderVal = 0;

		if (year_F <= smartMoneyBackGoldBean.getPolicyTerm()) {

			q = Math.max(0, sumAnnualizedPrem);
			b = sumSurvivalBenefit;

			if (smartMoneyBackGoldBean.getPolicyTerm() > 9) {
				if (year_F > 2)
					a = 1;
				else
					a = 0;
			}

			else {
				if (year_F > 1)
					a = 1;
				else
					a = 0;
			}

//                            if(year_F > smartMoneyBackGoldBean.getPolicyTerm())
//                            {
//                            	c=0 ;
//                            }
//                            else
//                            {
//                            	 c=sumGuaranteedSurvivalBen; 
//                            }
//                            
//                           // p=a-c;
//                            
//                            double[]  t = ac.getPUV(PlanName,premPayOption, policyterm);
			// b=t[year_F-1]/100;

			if (year_F <= smartMoneyBackGoldBean.getPolicyTerm()) {

				q = Math.max(0, sumAnnualizedPrem);
				b = sumSurvivalBenefit;
			}
			double[] PUV_factor_array = ac.getPUV(PlanName, premPayOption, policyterm);
			double PUV_Factor = PUV_factor_array[year_F - 1];
			/*
			 * System.out.println("year_F "+year_F); System.out.println("q:  "+q);
			 * System.out.println("PUV_Factor "+PUV_Factor); System.out.println("a: "+a);
			 */

//                          System.out.println("  totaBasePrem  "+totaBasePrem+"    "+b+"   "+a+"   "+c); 
			if (year_F > 2) {
				SurrenderVal = q * PUV_Factor * a - b;
			} else {
				SurrenderVal = q * PUV_Factor;
			}

			if (SurrenderVal < 0) {
				SurrenderVal = 0;
			}
		} else {
			SurrenderVal = 0;

		}
		return SurrenderVal;
	}

//    public double getNonGSV_surrndr_val(int year_F, double nongrntdDeathNenft) {
//		double a = 0, b = 0, temp = 0;
//
//		if (smartMoneyBackGoldBean.getPremiumFreq().equalsIgnoreCase("Single"))
//			return 0;
//		else {
//			double terminalBonus = 0;
//			if (year_F == smartMoneyBackGoldBean.getPolicyTerm()) {
//				terminalBonus = 1 + 0.15;
//			} else {
//				terminalBonus = 1;
//			}
//
//			double[] regularSSV = ac.getSSV_RP_Arr();
//			int position = 0;
//			double regularSSVFactor = 0;
//
//			for (int policyYear = 1; policyYear <= 30; policyYear++) {
//				for (int duration = 0; duration <= 3; duration++) {
//					if (policyYear == year_F && duration == 0
//							&& smartMoneyBackGoldBean.getPolicyTerm() == 12) {
//						regularSSVFactor = regularSSV[position];
//						break;
//					} else if (policyYear == year_F && duration == 1
//							&& smartMoneyBackGoldBean.getPolicyTerm() == 15) {
//						regularSSVFactor = regularSSV[position];
//						break;
//					} else if (policyYear == year_F && duration == 2
//							&& smartMoneyBackGoldBean.getPolicyTerm() == 20) {
//						regularSSVFactor = regularSSV[position];
//						break;
//					} else if (policyYear == year_F && duration == 3
//							&& smartMoneyBackGoldBean.getPolicyTerm() == 25) {
//						regularSSVFactor = regularSSV[position];
//						break;
//					}
//					position++;
//				}
//			}
//			b = regularSSVFactor;
//			double temp2 = Double.parseDouble(String
//					.valueOf(smartMoneyBackGoldBean.getPolicyTerm()));
//			double temp1 = Double.parseDouble(String
//					.valueOf(smartMoneyBackGoldBean.getSumAssured_Basic()));
//			a = year_F / temp2 * temp1;
//
//			// return Math.round((a+nongrntdDeathNenft/terminalBonus)*b);
//			return (Math.round(Double.parseDouble(commonForAllProd
//					.getRoundOffLevel2(commonForAllProd
//							.getStringWithout_E((a + nongrntdDeathNenft
//									/ terminalBonus)
//									* b)))));
//		}
//	}
	/*********** Added by tushar Kotian On 19/9/2017 ************/

	// added by sujata
//    public double getNonGSV_surrndr_val_SSV_8(String PlanName, String premPayOption ,int PolicyTerm,int policyyear,int sumassured,String nongrntdDeathNenft_8Percent)
	public double getNonGSV_surrndr_val_SSV_8(int year_F, int PolicyTerm, String premPayOption, double SumAssured_Basic,
			double ppt, int age, double sumSurvivalBenefit, String returnOnInvestment, String planname) {
		double a = 0, b, Reversionary8perValue = 0, getNonGSV_surrndr_val_SSV_8 = 0;
		if (year_F > PolicyTerm) {
			b = 0;
		} else {
			a = 1.1 * SumAssured_Basic;
			if (smartMoneyBackGoldBean.getpremPayOption().equalsIgnoreCase("Single")) {
				b = 1;
			} else {
				if (smartMoneyBackGoldBean.getpremPayOption().equalsIgnoreCase("Limited")) {
					if (year_F < ppt) {
						b = year_F / ppt;
					} else {
						b = 1;
					}
				} else {
					b = year_F / ppt;
				}

			}
		}
		Reversionary8perValue = ((Reversionary_Bonus8per(year_F, PolicyTerm, SumAssured_Basic, returnOnInvestment,
				planname)));

		double[] getRegularPremiumSSV = ac.getRegularPremiumSSV(PolicyTerm, premPayOption, age);
		double RegularPremiumSSVFactor = getRegularPremiumSSV[year_F - 1];
		// System.out.println("a "+ a);
		// System.out.println("b "+ b);

		getNonGSV_surrndr_val_SSV_8 = (((((a * b) - 0) - sumSurvivalBenefit) + Reversionary8perValue)
				* RegularPremiumSSVFactor);

		// System.out.println("getNonGSV_surrndr_val_SSV_8
		// "+getNonGSV_surrndr_val_SSV_8);
		return getNonGSV_surrndr_val_SSV_8;
	}

	/*
	 * public double getNonGSV_surrndr_val_SSV_4(String PlanName, String
	 * premPayOption, int PolicyTerm,int policyyear,int sumassured,String
	 * nongrntdDeathNenft_4Percent)
	 */
	public double getNonGSV_surrndr_val_SSV_4(int year_F, int PolicyTerm, String premPayOption, double SumAssured_Basic,
			double ppt, int age, double sumSurvivalBenefit, String returnOnInvestment,
			String planname) {/*
								 * double NonGSV_surrndr_val_SSV_4 = 0 ; double [] SSV_factor_array =
								 * ac.getSSV(PolicyTerm,premPayOption) ; double [] PUV_factor_array =
								 * ac.getPUV(PlanName,premPayOption,PolicyTerm) ;
								 * 
								 * double SSV_Factor = SSV_factor_array[policyyear-1]; double PUV_Factor =
								 * PUV_factor_array[policyyear-1];
								 * 
								 * NonGSV_surrndr_val_SSV_4 =
								 * ((sumassured*(PUV_Factor/1000))+Double.parseDouble(
								 * nongrntdDeathNenft_4Percent))*SSV_Factor ;
								 * 
								 * return NonGSV_surrndr_val_SSV_4;
								 * 
								 */

		double a = 0, b, Reversionary4perValue = 0, getNonGSV_surrndr_val_SSV_4 = 0;
		if (year_F > PolicyTerm) {
			b = 0;
		} else {
			a = 1.1 * SumAssured_Basic;
			if (smartMoneyBackGoldBean.getpremPayOption().equalsIgnoreCase("Single")) {
				b = 1;
			} else {
				if (smartMoneyBackGoldBean.getpremPayOption().equalsIgnoreCase("Limited")) {
					if (year_F < ppt) {
						b = year_F / ppt;
					} else {
						b = 1;
					}
				} else {
					b = year_F / ppt;
				}
			}
		}
		Reversionary4perValue = ((Reversionary_Bonus(year_F, PolicyTerm, SumAssured_Basic, returnOnInvestment,
				planname)));

		double[] getRegularPremiumSSV = ac.getRegularPremiumSSV(PolicyTerm, premPayOption, age);
		double RegularPremiumSSVFactor = getRegularPremiumSSV[year_F - 1];

		// getNonGSV_surrndr_val_SSV_4 =((((( a * b ) - 0 ) - sumSurvivalBenefit )+
		// Reversionary4perValue)*RegularPremiumSSVFactor);
//    	System.out.println("a "+a);
//    	System.out.println("b "+b);
//    	System.out.println("a * b" + a*b);
//    	System.out.println("sumSurvivalBenefit "+sumSurvivalBenefit);
//    	System.out.println("Reversionary4perValue "+Reversionary4perValue);
//    	System.out.println("RegularPremiumSSVFactor "+RegularPremiumSSVFactor);
//    	
		getNonGSV_surrndr_val_SSV_4 = (((((a * b) - 0) - sumSurvivalBenefit) + Reversionary4perValue)
				* RegularPremiumSSVFactor);
		// System.out.println("\n getNonGSV_surrndr_val_SSV_4
		// "+getNonGSV_surrndr_val_SSV_4);
		return getNonGSV_surrndr_val_SSV_4;

	}

	/*********** Added by tushar Kotian On 19/9/2017 ************/

	public double get_Premium_PTA_withoutDisc_WithoutST_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		} else {
			divFactor = 12;
		}
		return (getPR_PTA_FromDB() * (1 - 0) - getSA_Rebate_Rider() + (getNSAP() + getEMR()))
				* smartMoneyBackGoldBean.getSumAssured_PT() / 1000 * getLoadingForFreqOfPremiums("Rider") / divFactor;
	}

	public double get_Premium_PTA_withoutDiscAndSA_WithoutST_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		} else {
			divFactor = 12;
		}
		return (getPR_PTA_FromDB() * (1 - 0) - 0 + (getNSAP() + getEMR())) * smartMoneyBackGoldBean.getSumAssured_PT()
				/ 1000 * getLoadingForFreqOfPremiums("Rider") / divFactor;

	}

	/****************
	 * Modified by Akshaya on 12-Feb-2015 start
	 *************************/
	// ADB Start
	public double get_Premium_WithoutADB_withoutDisc_WithoutST_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		} else {
			divFactor = 12;
		}
		return (getPR_Rider_FromDB("ADB") * (1 - 0) - getSA_Rebate_Rider() + (getNSAP() + getEMR()))
				* smartMoneyBackGoldBean.getSumAssured_ADB() / 1000 * getLoadingForFreqOfPremiums("Rider") / divFactor;
	}

	public double get_Premium_WithoutADB_withoutDiscAndSA_WithoutST_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		} else {
			divFactor = 12;
		}
		return (getPR_Rider_FromDB("ADB") * (1 - 0) - 0 + (getNSAP() + getEMR()))
				* smartMoneyBackGoldBean.getSumAssured_ADB() / 1000 * getLoadingForFreqOfPremiums("Rider") / divFactor;
	}

	/****************
	 * Modified by Akshaya on 12-Feb-2015 end
	 *************************/

	/****************
	 * Modified by Akshaya on 12-Feb-2015 start
	 *************************/
	// ATPDB Start
	public double get_Premium_ATPDB_WithoutDisc_WithoutST_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		} else {
			divFactor = 12;
		}
		return (getPR_Rider_FromDB("ATPDB") * (1 - 0) - getSA_Rebate_Rider() + (getNSAP() + getEMR()))
				* smartMoneyBackGoldBean.getSumAssured_ATPDB() / 1000 * getLoadingForFreqOfPremiums("Rider")
				/ divFactor;
	}

	public double get_Premium_ATPDB_WithoutDiscAndSA_WithoutST_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		} else {
			divFactor = 12;
		}
		return (getPR_Rider_FromDB("ATPDB") * (1 - 0) - 0 + (getNSAP() + getEMR()))
				* smartMoneyBackGoldBean.getSumAssured_ATPDB() / 1000 * getLoadingForFreqOfPremiums("Rider")
				/ divFactor;
	}

	/****************
	 * Modified by Akshaya on 12-Feb-2015 end
	 *************************/

	/****************
	 * Modified by Akshaya on 12-Feb-2015 start
	 *************************/
	public double get_Premium_CC13NonLinked_WithoutDisc_WithoutST_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		} else {
			divFactor = 12;
		}

		// System.out.println("@ getPR_CC13NonLinked_FromDB() ->
		// "+getPR_CC13NonLinked_FromDB());
		// System.out.println("@ getStaffRebate('CC13NonLinkedRider') ->"+
		// getStaffRebate("CC13NonLinkedRider"));
		// System.out.println("@ getSA_Rebate_Rider() -> "+getSA_Rebate_Rider());
		// System.out.println("@ getNSAP() -> "+getNSAP());
		// System.out.println("@ getEMR() -> "+getEMR());
		// System.out.println("@ smartMoneyBackBean.getSumAssured_PT() ->
		// "+smartMoneyBackBean.getSumAssured_PT());
		// System.out.println("@ getLoadingForFreqOfPremiums('CC13NonLinked') ->
		// "+getLoadingForFreqOfPremiums("CC13NonLinked"));

		return (getPR_CC13NonLinked_FromDB() * (1 - 0) - getSA_Rebate_Rider() + (getNSAP() + getEMR()))
				* smartMoneyBackGoldBean.getSumAssured_CC13NonLinked() / 1000 * getLoadingForFreqOfPremiums("Rider")
				/ divFactor;
	}

	public double get_Premium_CC13NonLinked_WithoutDiscAndSA_WithoutST_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		} else {
			divFactor = 12;
		}

		// System.out.println("@ getPR_CC13NonLinked_FromDB() ->
		// "+getPR_CC13NonLinked_FromDB());
		// System.out.println("@ getStaffRebate('CC13NonLinkedRider') ->"+
		// getStaffRebate("CC13NonLinkedRider"));
		// System.out.println("@ getSA_Rebate_Rider() -> "+getSA_Rebate_Rider());
		// System.out.println("@ getNSAP() -> "+getNSAP());
		// System.out.println("@ getEMR() -> "+getEMR());
		// System.out.println("@ smartMoneyBackBean.getSumAssured_PT() ->
		// "+smartMoneyBackBean.getSumAssured_PT());
		// System.out.println("@ getLoadingForFreqOfPremiums('CC13NonLinked') ->
		// "+getLoadingForFreqOfPremiums("CC13NonLinked"));

		return (getPR_CC13NonLinked_FromDB() * (1 - 0) - 0 + (getNSAP() + getEMR()))
				* smartMoneyBackGoldBean.getSumAssured_CC13NonLinked() / 1000 * getLoadingForFreqOfPremiums("Rider")
				/ divFactor;
	}

	/****************
	 * Modified by Akshaya on 12-Feb-2015 end
	 *************************/

	public double getMinesOccuInterest(double SumAssured_Basic) {
		return (SumAssured_Basic / 1000) * 2;
	}

	public double get_Premium_Basic_WithoutST_WithoutDisc_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		}
		// For Monthly Premium Frequency Mode
		else {
			divFactor = 12;
		}

		return (getPR_Basic_FromDB() * (1 - 0) - getSA_Rebate_Basic() + (getNSAP() + getEMR()))
				* smartMoneyBackGoldBean.getSumAssured_Basic() / 1000 * getLoadingForFreqOfPremiums("Basic") / 1;
	}

	public double get_Premium_Basic_WithoutST_withoutStaffAndSARebate_NotRounded() {
		int divFactor = 0;
		if (smartMoneyBackGoldBean.getPremiumFreq().equals("Yearly")
				|| smartMoneyBackGoldBean.getPremiumFreq().equals("Single")) {
			divFactor = 1;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Half Yearly")) {
			divFactor = 2;
		} else if (smartMoneyBackGoldBean.getPremiumFreq().equals("Quarterly")) {
			divFactor = 4;
		}
		// For Monthly Premium Frequency Mode
		else {
			divFactor = 12;
		}
//		System.out.println("rate" + getPR_Basic_FromDB());
//		System.out.println("sa rebte" + getSA_Rebate_Basic());
//		System.out.println("divFactor" + divFactor);
//		System.out.println("return value"
//				+ (getPR_Basic_FromDB() * (1 - getStaffRebate("basic"))
//						- getSA_Rebate_Basic() + (getNSAP() + getEMR()))
//				* smartMoneyBackGoldBean.getSumAssured_Basic() / 1000
//				* getLoadingForFreqOfPremiums() / divFactor);
//		System.out.println("staffrebatebasic" + getStaffRebate("basic"));
//		System.out.println("frequency loading" + getLoadingForFreqOfPremiums());

		return (getPR_Basic_FromDB() * (1 - 0) - 0 + (getNSAP() + getEMR()))
				* smartMoneyBackGoldBean.getSumAssured_Basic() / 1000 * getLoadingForFreqOfPremiums("Basic") / 1;
	}

	/***************************
	 * Added By Tushar Kotian on 3//8/2017
	 ****************/

	public double getBackDateInterest(double grossPremium, String bkDate, String cashierDate) {

		try {

			// double indigoRate=7.5;
			/**
			 * Modified by Akshaya. Rate Change from 1-APR-2015
			 */
			double ST = 0;
			if (smartMoneyBackGoldBean.getJkResident()) {
//				ST = prop.serviceTaxJkResident;
				ST = prop.serviceTax + prop.SBCServiceTax;
			} else {
				/******************* Modified by Priyanka on 7-Nov-2017 start **********/
				ST = prop.serviceTax + prop.SBCServiceTax;
				/******************* Modified by Priyanka on 7-Nov-2017 end **********/
			}
			/******************* Modified by Akshaya on 14-APR-2015 start **********/
			/******************* Modified by Priyanka on 7-Nov-2017 start **********/
//			double indigoRate = 10;
			double indigoRate = 8.87;

			/******************* Modified by Saurabh Jain on 08-APR-2019 start **********/
			// double indigoRate = commonForAllProd.getIndigoRate(cashierDate);
			/******************* Modified by Saurabh Jain on 08-APR-2019 end **********/

			double ServiceTaxValue = (ST + 1) * 100;
			/******************* Modified by Priyanka on 7-Nov-2017 end **********/

			/******************* Modified by Akshaya on 14-APR-2015 end **********/
			int compoundFreq = 2;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat dateformat1 = new SimpleDateFormat("dd-MM-yyyy");

			Date dtBackdate = dateformat1.parse(bkDate);
			String strBackDate = dateFormat.format(dtBackdate);

			/***** Commented By Pryanka Warekar -25-09-2017 - start *******/
//			Calendar cal = Calendar.getInstance();
			// System.out.println("cal "+cal);
			// System.out.println("cal "+cal.getTime());
//			String date = dateFormat.format(cal.getTime());
			Date dtcashierdate = dateformat1.parse(cashierDate);
			String date = dateFormat.format(dtcashierdate);
			/***** Commented By Pryanka Warekar -25-09-2017 - end *******/
			// System.out.println("date "+date);

			/******************* Modified by Akshaya on 14-APR-2015 start **********/
			// double netPremWithoutST=Math.round((grossPremium*100)/103.09);
			double netPremWithoutST = Math.round((grossPremium * 100) / ServiceTaxValue);
			/******************* Modified by Akshaya on 14-APR-2015 end **********/
			// System.out.println("netPremWithoutST "+netPremWithoutST);
			int days = commonForAllProd.setDate(date, strBackDate);
			// System.out.println("no of days "+days);
			double monthsBetween = commonForAllProd.roundDown((double) days / 365 * 12, 0);
			// System.out.println("aaaaaaaaa "+(double)79/365);
			// System.out.println("month "+monthsBetween);

			double interest = getCompoundAmount(monthsBetween, netPremWithoutST, indigoRate, compoundFreq);
			// System.out.println("onterest "+interest);
			return interest;
		}

		catch (Exception e) {
			return 0;
		}
	}

	/************ Smart moneyback gold end *****************/

	public double getCompoundAmount(double monthsBetween, double netPremWithoutST, double indigoRate,
			int compoundFreq) {
		double compoundAmount = netPremWithoutST
				* Math.pow((1 + (indigoRate / (100 * compoundFreq))), (compoundFreq * (monthsBetween / 12)))
				- netPremWithoutST;
		return compoundAmount;
		// System.out.println("compoundAmount "+compoundAmount);
	}

	/***************************
	 * Added By Tushar Kotian on 3//8/2017
	 ****************/

}
