package com.sbilife.smartmoneyplannerv3;

import com.sbilife.common.CommonForAllProd;
import com.sbilife.smartmoneyplannerv3.*;

//import com.sbilife.smartchamp.SmartChampDB;
//import com.sbilife.smartchamp.SmartChampProperties;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SmartMoneyPlannerBusinessLogic {

	double TabularPremiumRate = 0, staffRebate = 0, SARebate = 0, NSAP_Rate = 0, EMR = 0, LoadngFreqOfPremiums = 0,
			PF = 0;

	double premiumWithoutSTwithoutRoundUP = 0, premiumFrequencyLoading = 0, yearlyPremiumWithoutST = 0,
			premiumWithoutST = 0, premiumWithST = 0, serviceTax = 0, BasePremiumWithoutSTwithRoundUP = 0,
			basePremiumWithST = 0, baseServiceTax = 0, yearlyPremiumWithST = 0, totalBasePremiumPaid = 0,
			guaranteedDeathBenefit = 0, nonGuarateedDeathBenefitAt_4_Percent = 0,
			nonGuarateedDeathBenefitAt_8_Percent = 0, guaranteedSurvivalBenefit = 0, GuaranteedMaturityBenefit=0,
			nonGuarateedSurvivalBenefitAt_4_Percent = 0, nonGuarateedSurvivalBenefitAt_8_Percent = 0,
			GSV_surrendr_val = 0, NonGSV_surrndr_val_4Percent = 0, NonGSV_surrndr_val_8Percent = 0,
			singleTotalBasePremPaid = 0,totalBasePremiumPaidPPT=0;

	;
	/** Added by Akshaya on 04-AUG-15 Start ********/
	String premiumWithoutSTwithoutStaffwithoutRoundUP, premiumWithoutSTwithoutStaffwithRoundUP;
	/** Added by Akshaya on 04-AUG-15 End ********/

	int growthPeriod = 0;
	public CommonForAllProd cfap = null;
	public SmartMoneyPlannerProperties smpProp = null;
	SmartMoneyPlannerDB dbObj = null;
	SmartMoneyPlannerProperties prop = null;
	SmartMoneyPlannerBean smartMoneyPlannerBean = null;

	public SmartMoneyPlannerBusinessLogic(SmartMoneyPlannerBean smartMoneyPlannerBean) {
		// TODO Auto-generated constructor stub
		this.smartMoneyPlannerBean = smartMoneyPlannerBean;
		dbObj = new SmartMoneyPlannerDB();
		prop = new SmartMoneyPlannerProperties();
		cfap = new CommonForAllProd();
		smpProp = new SmartMoneyPlannerProperties();
	}

	// Set Premium without ST and roundup
	public void setPremiumWithoutSTwithoutRoundUP(int age, int plan, double sumAssured, boolean staffDisc,
			String premFreqMode) {
//				SmartChampDB smartchampDB=new  SmartChampDB();
		double SA_Rebate = 0;
		double tabularPremiumRate = 0;

		staffRebate = getStaffRebate(premFreqMode, staffDisc);
		SA_Rebate = getSARebate(premFreqMode, sumAssured);
		LoadngFreqOfPremiums = getLoadingFrqOfPremium(premFreqMode, "Basic");

		tabularPremiumRate = getTabularPremiumRate(age, premFreqMode, plan);

		/**** Added By Priyanka Warekar - 08-08-2016 - Start ****/
//				PF = getPF(premFreqMode);
		PF = 1;
		/**** Added By Priyanka Warekar - 08-08-2016 - Start ****/

		System.out.println("tabularPremiumRate " + tabularPremiumRate);
		double temp = ((tabularPremiumRate * (1.0 - staffRebate) - SARebate + (NSAP_Rate + EMR)) * sumAssured / 1000.0
				* LoadngFreqOfPremiums / PF);
		double ans = Double.valueOf(cfap.getRoundOffLevel2(String.valueOf(temp)));

		this.premiumWithoutSTwithoutRoundUP = ((tabularPremiumRate * (1.0 - staffRebate) - SARebate + (NSAP_Rate + EMR))
				* sumAssured / 1000.0 * LoadngFreqOfPremiums / PF);

		// return ans;
	}

	public double getPremiumWithoutSTwithoutRoundUP() {
		// System.out.println("premiumWithoutSTwithoutRoundUP
		// "+premiumWithoutSTwithoutRoundUP);
		return premiumWithoutSTwithoutRoundUP;
	}

	public double getSARebate(String premFreqMode, double sumAssured) {
		if (premFreqMode.equalsIgnoreCase("Single")) {
			if (sumAssured >= 100000 && sumAssured < 200000)
				SARebate = 0;
			else if (sumAssured >= 200000 && sumAssured < 300000)
				SARebate = 20;
			else if (sumAssured >= 300000 && sumAssured < 500000)
				SARebate = 25;
			else
				SARebate = 30;
		} else {
			if (sumAssured >= 100000 && sumAssured < 200000)
				SARebate = 0;
			else if (sumAssured >= 200000 && sumAssured < 300000)
				SARebate = 2;
			else if (sumAssured >= 300000 && sumAssured < 500000)
				SARebate = 3;
			else
				SARebate = 5;
		}
		return SARebate;
	}

	public double getStaffRebate(String premFreqMode, boolean staff) {
		double staff_Rebate;
		// staffRebate
		if (staff == true) {
			if (premFreqMode.equalsIgnoreCase("Single"))
				staff_Rebate = 0.02;
			else
				staff_Rebate = 0.07;
		} else
			staff_Rebate = 0;
		return staff_Rebate;

	}

	public double getPF(String premFreqMode) {
		double pf = 0;
		if (premFreqMode.equalsIgnoreCase("Yearly") || premFreqMode.equalsIgnoreCase("Single"))
			pf = 1.0;
		else if (premFreqMode.equalsIgnoreCase("Half Yearly"))
			pf = 2.0;
		else if (premFreqMode.equalsIgnoreCase("Quarterly"))
			pf = 4.0;
		else
			pf = 12.0;
		return pf;
	}

	public double getLoadingFrqOfPremium(String premFreqMode, String cover) {

		double loadngFreqOfPremiums = 0;
		// Loading for Frequency Of Premiums
		if (cover.equals("Basic")) {
			if (premFreqMode.equalsIgnoreCase("Yearly") || premFreqMode.equalsIgnoreCase("Single"))
				loadngFreqOfPremiums = 1;
			else if (premFreqMode.equalsIgnoreCase("Half Yearly"))
				loadngFreqOfPremiums = prop.HalfYearly_Modal_Factor;
			else if (premFreqMode.equalsIgnoreCase("Quarterly"))
				loadngFreqOfPremiums = prop.Quarterly_Modal_Factor;
			else
				loadngFreqOfPremiums = prop.Monthly_Modal_Factor;
		} else {
			if (premFreqMode.equalsIgnoreCase("Yearly") || premFreqMode.equalsIgnoreCase("Single"))
				loadngFreqOfPremiums = 1;
			else if (premFreqMode.equalsIgnoreCase("Half Yearly"))
				loadngFreqOfPremiums = 1.04;
			else if (premFreqMode.equalsIgnoreCase("Quarterly"))
				loadngFreqOfPremiums = 1.06;
			else
				loadngFreqOfPremiums = 1.068;
		}
		return loadngFreqOfPremiums;
	}

	public double getTabularPremiumRate(int age, String premFreqMode, int plan) {
		SmartMoneyPlannerDB smartmoneyplanneDB = new SmartMoneyPlannerDB();
		if (age == -1)
			TabularPremiumRate = 0;
		else {
			if (premFreqMode.equalsIgnoreCase("Single")) {
				double[] premiumArr = smartmoneyplanneDB.getSinglePremiumRates();
				int position = 0;
				TabularPremiumRate = 0;
				for (int ageCount = 18; ageCount <= 60; ageCount++) {
					for (int plancount = 1; plancount <= 4; plancount++) {
						if (ageCount == age && plancount == plan) {
							TabularPremiumRate = premiumArr[position];
							break;
						}
						position++;
					}
				}
			} else {
				double[] premiumArr = smartmoneyplanneDB.getRegularPremiumRates();
				int position = 0;
				TabularPremiumRate = 0;
				for (int ageCount = 18; ageCount <= 60; ageCount++) {
					for (int plancount = 1; plancount <= 4; plancount++) {
						if (ageCount == age && plancount == plan) {
							TabularPremiumRate = premiumArr[position];
							break;
						}
						position++;
					}
				}
			}
		}

		return TabularPremiumRate;
	}

	public int setBenefitPayingTerm(int plan) {
		if (plan == 1)
			return 5;
		else if (plan == 2)
			return 10;
		else if (plan == 3)
			return 5;
		else
			return 10;
	}

	public int getGrowthPeriod(int age, String premFreqMode, int plan) {
		SmartMoneyPlannerDB smartmoneyplanneDB = new SmartMoneyPlannerDB();
		if (age == -1)
			growthPeriod = 0;
		else {
			if (premFreqMode.equalsIgnoreCase("Single")) {
				int[] growthArr = smartmoneyplanneDB.getGrowthPeriod();
				int position = 0;
				growthPeriod = 0;

				for (int plancount = 1; plancount <= 4; plancount++) {
					if (plancount == plan) {
						growthPeriod = growthArr[position + 4];
					}
					position++;
				}
			} else {
				int[] growthArr = smartmoneyplanneDB.getGrowthPeriod();
				int position = 0;
				growthPeriod = 0;
				for (int plancount = 1; plancount <= 4; plancount++) {
					if (plancount == plan) {
						growthPeriod = growthArr[position];
					}
					position++;
				}

			}
		}
		if (premFreqMode.equalsIgnoreCase("Single"))
			return growthPeriod;
		else
			return growthPeriod + smartMoneyPlannerBean.getPremiumPayingTerm();
	}

	public void setPremiumWithoutSTwithRoundUP(double premium, double PTARiderPremium, double ADBRiderPremium,
			double ATPDRiderPremium, double criticarePremium) {
		double riderPremiumWithoutST = 0;

		this.premiumWithoutST = (Double.parseDouble(cfap.getRoundOffLevel2New(cfap.getStringWithout_E(
				premium + PTARiderPremium + ADBRiderPremium + ATPDRiderPremium + criticarePremium))));
	}

	public double getPremiumWithoutSTwithRoundUP() {
		// System.out.println("premiumWithoutST "+premiumWithoutST);
		return premiumWithoutST;
	}

//			//added by sujata on 28-02-2020
//			public double get_Premium_Basic_WithoutST_NotRounded_withoutFreqLoading() {
//				int divFact = 0;
//				if (smartMoneyPlannerBean.getPremFreqMode().equals("Yearly")) {
//					divFact = 1;
//				} else if (smartMoneyPlannerBean.getPremFreqMode()
//						.equals("Half Yearly")) {
//					divFact = 2;
//				} else if (smartMoneyPlannerBean.getPremFreqMode().equals("Quarterly")) {
//					divFact = 4;
//				} else {
//					divFact = 12;
//				}
//
//			
//				return (getPR_Basic_FromDB() * (1 - getStaffRebate("Basic"))
//						- getSA_Rebate_Basic() + (getNSAP() + getEMR()))
//						* smartIncomeProtectBean.getSumAssured_Basic()
//						/ 1000;
//				/**** Added By sujata on  - 28-02-2020 - Start ****/
//			}

	/****************/

	public void set_Premium_Basic_WithoutST_NotRounded_withoutFreqLoading(int age, int plan, double sumAssured,
			boolean staffDisc, String premFreqMode) {
//				SmartChampDB smartchampDB=new  SmartChampDB();
		double SA_Rebate = 0;
		double tabularPremiumRate = 0;

		staffRebate = getStaffRebate(premFreqMode, staffDisc);
		SA_Rebate = getSARebate(premFreqMode, sumAssured);
		LoadngFreqOfPremiums = getLoadingFrqOfPremium(premFreqMode, "Basic");

		tabularPremiumRate = getTabularPremiumRate(age, premFreqMode, plan);

		/**** Added By Priyanka Warekar - 08-08-2016 - Start ****/
//				PF = getPF(premFreqMode);
		PF = 1;
		/**** Added By Priyanka Warekar - 08-08-2016 - Start ****/

		System.out.println("tabularPremiumRate " + tabularPremiumRate);
		double temp = ((tabularPremiumRate * (1.0 - staffRebate) - SARebate + (NSAP_Rate + EMR)) * sumAssured / 1000.0);
		double ans = Double.valueOf(cfap.getRoundOffLevel2(String.valueOf(temp)));

		this.premiumFrequencyLoading = ((tabularPremiumRate * (1.0 - staffRebate) - SARebate + (NSAP_Rate + EMR))
				* sumAssured / 1000.0);

		// return ans;
	}

	public double get_Premium_Basic_WithoutST_NotRounded_withoutFreqLoading() {
		// System.out.println("premiumFrequencyLoading "+premiumFrequencyLoading);
		return premiumFrequencyLoading;
	}
	/***************/
//			public void setPremiumWithST(double premiumWithoutST,boolean JKResident)
//			{
//				if(JKResident==true)
//					//		    		return(Double.parseDouble(cfap.getRoundUp((cfap.getStringWithout_E(((0.0105+1)*premiumWithoutST))))));
//					this.premiumWithST=(Double.parseDouble(cfap.getRoundUp((cfap.getStringWithout_E(((prop.serviceTaxJKResident+1)*premiumWithoutST))))));
//				else
//					this.premiumWithST=(Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(((prop.serviceTax+1)*premiumWithoutST)))));
//			}
//			public double getPremiumWithST()
//			{
//				return premiumWithST;
//			}
//		
//			public void  setServiceTax(double premiumWithST,double premiumwithRoundUP)
//			{
//				this.serviceTax= (premiumWithST-premiumwithRoundUP);
//			}
//			public double  getServiceTax()
//			{
//				return serviceTax;
//			}

	/*** modified by Akshaya on 20-MAY-16 start **/

	public double getServiceTax(double premiumWithoutST, boolean JKResident, String type) {
		if (type.equals("basic")) {
//					if(JKResident==true)
//						return Double.parseDouble(cfap.getRoundUp(String.valueOf(premiumWithoutST*smpProp.serviceTaxJKResident)));
//					else
//					{
			return Double.parseDouble(cfap.getRoundUp(String.valueOf(premiumWithoutST * smpProp.serviceTax)));
//					}
		} else if (type.equals("SBC")) {
//					if(JKResident==true)
//						return 0;
//					else
//					{
			return Double.parseDouble(cfap.getRoundUp(String.valueOf(premiumWithoutST * smpProp.SBCServiceTax)));
//					}
		} else // KKC
		{
//					if(JKResident==true)
//						return 0;
//					else
//					{
			return Double.parseDouble(cfap.getRoundUp(String.valueOf(premiumWithoutST * smpProp.KKCServiceTax)));
//					}
		}

	}

	public double getServiceTaxSecondYear(double premiumWithoutST, boolean JKResident, String type) {
		if (type.equals("basic")) {
//						if(JKResident==true)
//							return Double.parseDouble(cfap.getRoundUp(String.valueOf(premiumWithoutST*smpProp.serviceTaxJKResident)));
//						else
//						{
			return Double.parseDouble(cfap.getRoundUp(String.valueOf(premiumWithoutST * smpProp.serviceTaxSecondYear)));
//						}
		} else if (type.equals("SBC")) {
//						if(JKResident==true)
//							return 0;
//						else
//						{
			return Double
					.parseDouble(cfap.getRoundUp(String.valueOf(premiumWithoutST * smpProp.SBCServiceTaxSecondYear)));
//						}
		} else // KKC
		{
//						if(JKResident==true)
//							return 0;
//						else
//						{
			return Double
					.parseDouble(cfap.getRoundUp(String.valueOf(premiumWithoutST * smpProp.KKCServiceTaxSecondYear)));
//						}
		}

	}

	/*** modified by Akshaya on 20-MAY-16 end **/

	/* For the Base Premium */
	public void setBasePremiumWithoutSTwithRoundUP(double premium) {
		double riderPremiumWithoutST = 0;
		this.BasePremiumWithoutSTwithRoundUP = (Double
				.parseDouble(cfap.getRoundUp(cfap.getRoundOffLevel2(cfap.getStringWithout_E(premium)))));
	}

	public double getBasePremiumWithoutSTwithRoundUP() {
		return BasePremiumWithoutSTwithRoundUP;
	}

	public void setBasePremiumWithST(double basePremiumWithoutST, boolean JKResident) {
		if (JKResident == true)
			// return(Double.parseDouble(cfap.getRoundUp((cfap.getStringWithout_E(((0.0105+1)*basePremiumWithoutST))))));
			this.basePremiumWithST = (Double.parseDouble(cfap
					.getRoundUp((cfap.getStringWithout_E(((prop.serviceTaxJKResident + 1) * basePremiumWithoutST))))));
		else
			this.basePremiumWithST = (Double.parseDouble(
					cfap.getRoundUp(cfap.getStringWithout_E(((prop.serviceTax + 1) * basePremiumWithoutST)))));
	}

	public double getBasePremiumWithST() {
		return basePremiumWithST;
	}

	public void setBaseServiceTax(double basePremiumWithST, double premiumwithRoundUP) {
		this.baseServiceTax = (basePremiumWithST - premiumwithRoundUP);
	}

	public double getBaseServiceTax() {
		return baseServiceTax;
	}
	/* For the Base Premium */

	public void setYearlyPremiumWithST(double premiumWithST, String premFreqMode) {
		if (premFreqMode.equalsIgnoreCase("Monthly"))
			this.yearlyPremiumWithST = premiumWithST * 12;
		else if (premFreqMode.equalsIgnoreCase("Quarterly"))
			this.yearlyPremiumWithST = premiumWithST * 4;
		else if (premFreqMode.equalsIgnoreCase("Half Yearly"))
			this.yearlyPremiumWithST = premiumWithST * 2;
		else if (premFreqMode.equalsIgnoreCase("Yearly"))
			this.yearlyPremiumWithST = premiumWithST;
		else
			this.yearlyPremiumWithST = premiumWithST;
	}

	public double getYearlyPremiumWithST() {
		return yearlyPremiumWithST;
	}

	public void setYearlyPremiumWithoutST(double premiumWithoutST, String premFreqMode) {
		this.yearlyPremiumWithoutST = Double
				.parseDouble(cfap.getRoundOffLevel2New(cfap.getStringWithout_E(premiumWithoutST)))
				* setPremiumMultiplication(premFreqMode);
	}

	public double getYearlyPremiumWithoutST() {
		return yearlyPremiumWithoutST;
	}

	public int setPremiumMultiplication(String premFreqMode) {
		if (premFreqMode.equalsIgnoreCase("Yearly") || premFreqMode.equalsIgnoreCase("Single"))
			return 1;
		else if (premFreqMode.equalsIgnoreCase("Half Yearly"))
			return 2;
		else if (premFreqMode.equalsIgnoreCase("Quarterly"))
			return 4;
		else
			return 12;
	}

	// 2. Total Base Premium Paid

	public void setTotalBasePremiumPaid(double BasePremiumPaid, int year_F, int premiumPayingTerm, String premFreqMode,
			int policyTerm) {
		if (premFreqMode.equalsIgnoreCase("Single") && year_F >= 2)
			this.totalBasePremiumPaid = 0;
		else {
			if (year_F <= premiumPayingTerm) {
				if (year_F <= policyTerm)
					this.totalBasePremiumPaid = Math.round(BasePremiumPaid * year_F);
				else
					this.totalBasePremiumPaid = 0;
			} else
				this.totalBasePremiumPaid = 0;
		}
		setSingleTotalBasePremiumPaid(year_F, totalBasePremiumPaid);
	}

	public double getTotalBasePremiumPaid() {
		return totalBasePremiumPaid;
	}
	// Guaranteed Benefit payable on death

	public void setGuaranteedDeathBenefit(double sumAssured, int year_F, int policyTerm) {
		if (year_F <= policyTerm)
			this.guaranteedDeathBenefit = sumAssured;
		else
			this.guaranteedDeathBenefit = 0;
	}

	public double getGuaranteedDeathBenefit() {
		return guaranteedDeathBenefit;
	}

	// Non-Guaranteed Benefit payable on death at 4%
	public void  setNonGuarateedDeathBenefitAt_4_Percent(double sumAssured,int year_F,int policyTerm)
	{	double a,b;
	if(year_F<=policyTerm)
		a= year_F * sumAssured * getbonusrate4(smartMoneyPlannerBean);
	else
		a=0;
//	if(year_F==policyTerm)
//		b=a*(smpProp.terminalBonus+100)/100;
//	else
//		b=a*1;
	
//	System.out.println("a4% "+a);
//	System.out.println("sumAssured "+sumAssured);
//	System.out.println("getbonusrate4(smartMoneyPlannerBean) "+(getbonusrate4(smartMoneyPlannerBean)));
	this.nonGuarateedDeathBenefitAt_4_Percent= a;
	}

	public double getNonGuarateedDeathBenefitAt_4_Percent() {
		return nonGuarateedDeathBenefitAt_4_Percent;
	}

	// Non-Guaranteed Benefit payable on death at 8%
	public void  setNonGuarateedDeathBenefitAt_8_Percent(double sumAssured,int year_F,int policyTerm)
	{	double a,b;
	if(year_F<=policyTerm)
		a= year_F* sumAssured * getbonusrate8(smartMoneyPlannerBean);
	else
		a=0;
//	if(year_F==policyTerm)
//		b=a*(smpProp.terminalBonus+100)/100;
//	else
//		b=a*1;
	this.nonGuarateedDeathBenefitAt_8_Percent= a;
	}

	public double getNonGuarateedDeathBenefitAt_8_Percent() {
		return nonGuarateedDeathBenefitAt_8_Percent;
	}

	public double getbonusrate4(SmartMoneyPlannerBean smartMoneyPlannerBean) {
		double bonusrate4 = 0 ;
		
		if(smartMoneyPlannerBean.getPremFreqMode().equals("Single") )
		{
			if(smartMoneyPlannerBean.getPlan()==1)
			{
				bonusrate4 = 0.0075;
			}
			else if(smartMoneyPlannerBean.getPlan()==2)
			{
				bonusrate4 = 0.0075;
			}
			else if(smartMoneyPlannerBean.getPlan()==3)
			{
				bonusrate4 = 0.0075;
			}
			else if(smartMoneyPlannerBean.getPlan()==4)
			{
				bonusrate4 = 0.0075;
			}
			
		}
		else
		{

			if(smartMoneyPlannerBean.getPlan()==1)
			{
				bonusrate4 = 0.015;
			}
			else if(smartMoneyPlannerBean.getPlan()==2)
			{
				bonusrate4 = 0.014;
			}
			else if(smartMoneyPlannerBean.getPlan()==3)
			{
				bonusrate4 = 0.014;
			}
			else if(smartMoneyPlannerBean.getPlan()==4)
			{
				bonusrate4 = 0.013;
			}
			
			
			
		
			
		}
		return bonusrate4;

	}

	public double getbonusrate8(SmartMoneyPlannerBean smartMoneyPlannerBean) {

		double bonusrate8 = 0 ;
		
		if(smartMoneyPlannerBean.getPremFreqMode().equals("Single"))
		{
			if(smartMoneyPlannerBean.getPlan()==1)
			{
				bonusrate8 = 0.05;
			}
			else if(smartMoneyPlannerBean.getPlan()==2)
			{
				bonusrate8 = 0.0575;
			}
			else if(smartMoneyPlannerBean.getPlan()==3)
			{
				bonusrate8 = 0.05;
			}
			else if(smartMoneyPlannerBean.getPlan()==4)
			{
				bonusrate8 = 0.055;
			}
			
			
			
		}
		else
		{

			if(smartMoneyPlannerBean.getPlan()==1)
			{
				bonusrate8 = 0.0475;
			}
			else if(smartMoneyPlannerBean.getPlan()==2)
			{
				bonusrate8 = 0.055;
			}
			else if(smartMoneyPlannerBean.getPlan()==3)
			{
				bonusrate8 = 0.0475;
			}
			else if(smartMoneyPlannerBean.getPlan()==4)
			{
				bonusrate8 = 0.0525;
			}
			
			
			
		
			
		}
		return bonusrate8; 

	}

	public void setGuaranteedSurvivalBenefit(double sumAssured, int growthPeriod, int policyTerm, int year_f, int BPP) {
		if (year_f > growthPeriod && year_f <= policyTerm) {
			this.guaranteedSurvivalBenefit = sumAssured / BPP;
		} else
			this.guaranteedSurvivalBenefit = 0;
	}

	public double getGuaranteedSurvivalBenefit() {
		return guaranteedSurvivalBenefit;
	}

	
	public void setGuaranteedMaturityBenefit(int year_f, int policyTerm,double sumAssured,double BPP)
	{	
	if(year_f == policyTerm)
	{
		if(year_f>growthPeriod && year_f <= policyTerm)
		{
			this.GuaranteedMaturityBenefit= sumAssured/BPP;
		}
		else
			this.GuaranteedMaturityBenefit= 0;			
	}
	else
	{
		this.GuaranteedMaturityBenefit= 0;
	}
	}
				
	public double getGuaranteedMaturityBenefit()
	{
		return GuaranteedMaturityBenefit;
	}
	
	public void setNonGuarateedSurvivalBenefitAt_4_Percent(double NonGuarateedDeathBenefitAt_4_Percent, int policyTerm,
			int year_f) {

		if (year_f == policyTerm) {
			this.nonGuarateedSurvivalBenefitAt_4_Percent = (Double
					.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(NonGuarateedDeathBenefitAt_4_Percent))));
		} else

			this.nonGuarateedSurvivalBenefitAt_4_Percent = 0;
	}

	public double getNonGuarateedSurvivalBenefitAt_4_Percent() {
		return nonGuarateedSurvivalBenefitAt_4_Percent;
	}

	public void setNonGuarateedSurvivalBenefitAt_8_Percent(double nonGuarateedDeathBenefitAt_8_Percent, int policyTerm,
			int year_f) {

		if (year_f == policyTerm) {
			this.nonGuarateedSurvivalBenefitAt_8_Percent = (Double
					.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(nonGuarateedDeathBenefitAt_8_Percent))));
		} else

			this.nonGuarateedSurvivalBenefitAt_8_Percent = 0;
	}

	public double getNonGuarateedSurvivalBenefitAt_8_Percent() {
		return nonGuarateedSurvivalBenefitAt_8_Percent;
	}

	/* Rider function */
	public double preferredTARiderPremium() {
		return 0;
	}

	public double ADBRiderPremium() {
		return 0;
	}

	public double ATPDRiderPremium() {
		return 0;
	}

	public double critiCareRiderPremium() {
		return 0;
	}
	/* Rider function */

	/** Added by Akshaya on 04-AUG-15 Start ********/
	// Set Premium without ST and roundup
	public void setPremiumWithoutSTwithoutStaffwithoutRoundUP(int age, int plan, double sumAssured, boolean staffDisc,
			String premFreqMode) {
//				SmartChampDB smartchampDB = new SmartChampDB();
		double SA_Rebate = 0;
		double tabularPremiumRate = 0;
		double temp1 = 0;

		staffRebate = getStaffRebate(premFreqMode, staffDisc);
		SA_Rebate = getSARebate(premFreqMode, sumAssured);
		LoadngFreqOfPremiums = getLoadingFrqOfPremium(premFreqMode, "Basic");

		tabularPremiumRate = getTabularPremiumRate(age, premFreqMode, plan);
		/**** Added By Priyanka Warekar - 08-08-2016 - Start ****/
//				PF = getPF(premFreqMode);
		PF = 1;
		/**** Added By Priyanka Warekar - 08-08-2016 - Start ****/
		double temp = ((tabularPremiumRate * (1.0 - staffRebate) - SARebate + (NSAP_Rate + EMR)) * sumAssured / 1000.0
				* LoadngFreqOfPremiums / PF);
		double ans = Double.valueOf(cfap.getRoundOffLevel2(String.valueOf(temp)));

		temp = ((tabularPremiumRate * (1.0 - 0) - SARebate + (NSAP_Rate + EMR)) * sumAssured / 1000.0
				* LoadngFreqOfPremiums / PF);

		this.premiumWithoutSTwithoutStaffwithoutRoundUP = cfap.getRoundOffLevel2New(cfap.getStringWithout_E(temp));

	}

	public String getPremiumWithoutSTwithoutStaffwithoutRoundUP() {
		return premiumWithoutSTwithoutStaffwithoutRoundUP;
	}

	public void setPremiumWithoutSTwithoutStaffwithRoundUP() {
		this.premiumWithoutSTwithoutStaffwithRoundUP = cfap.getRoundUp(premiumWithoutSTwithoutStaffwithoutRoundUP);
	}

	public String getPremiumWithoutSTwithoutStaffwithRoundUP() {
		return premiumWithoutSTwithoutStaffwithRoundUP;
	}

	public void setSingleTotalBasePremiumPaid(int year_f, double totalBasePremiumPaid) {
		if (smartMoneyPlannerBean.getPremFreqMode().equals("Single") && year_f == 1)
			this.singleTotalBasePremPaid = totalBasePremiumPaid;
		else if (smartMoneyPlannerBean.getPremFreqMode().equals("Single"))
			this.singleTotalBasePremPaid = singleTotalBasePremPaid;
		else {
			if ((smartMoneyPlannerBean.getPlan() == 1 || smartMoneyPlannerBean.getPlan() == 2) && year_f == 6)
				this.singleTotalBasePremPaid = totalBasePremiumPaid;
			else if ((smartMoneyPlannerBean.getPlan() == 3 || smartMoneyPlannerBean.getPlan() == 4) && year_f == 10)
				this.singleTotalBasePremPaid = totalBasePremiumPaid;
		}
	}

	public double getSingleTotalBasePremiumPaid() {
		return this.singleTotalBasePremPaid;
	}

	public void setGSV_SurrenderValue(int year_F,double sumSurvivalbenefit,double firstYearoftotalbasePrem,double sumtotalBasePremiumPaidforSurr) {

		double a=0,c=0, d = 0,p=0,surrenderValue=0,GSVval;
		
		SmartMoneyPlannerDB db = new SmartMoneyPlannerDB();	
		 
		double[] singleGSV = db.getSingleGSV_Factor();
		double[] RegularGSV = db.getRegularGSV_Factor(smartMoneyPlannerBean.getPlan());
		
		

		if(year_F <= smartMoneyPlannerBean.getPolicyTerm_Basic())
		{
//				a= sumtotalBasePremiumPaidforSurr;
//				System.out.println("a "+a);
		
		if(smartMoneyPlannerBean.getPremFreqMode().equals("Single"))
		{
			
			double temp = singleGSV[year_F-1];
			surrenderValue= temp;
			

		}
		else
		{
			int pos=0;
			//double regularGSV=0;
			for(int i= 1;i<=year_F;i++ )
			{
					if(i==year_F)
					{					
						surrenderValue = RegularGSV[pos];							
					}	
					pos++;
				}
		}
		
		if (smartMoneyPlannerBean.getPolicyTerm_Basic() > 9) 
		{
				if (year_F >= 2)
					d = 1;
				else
					d = 0;
			
		} else {
				if (year_F > 1)
					d = 1;
				else
					d = 0;
			}
		
		if (year_F > smartMoneyPlannerBean.getPolicyTerm_Basic())
			c = 0;
		else if (year_F > 1)
			c = sumSurvivalbenefit;
		
		if(year_F==1)
		{	
		GSVval=(firstYearoftotalbasePrem * surrenderValue);
	//	System.out.println("firstYearof " +GSVval);					
		}
		else
		{
		GSVval= (sumtotalBasePremiumPaidforSurr * surrenderValue * d - sumSurvivalbenefit);
		}
//		System.out.println("\n year_F "+year_F);
//		System.out.println("regularGSV "+surrenderValue);
//		GSVval= (sumtotalBasePremiumPaidforSurr * surrenderValue );
		
		//System.out.println("sumtotalBasePremiumPaidforSurr "+sumtotalBasePremiumPaidforSurr);

		//System.out.println("GSVval "+GSVval);
		
		//System.out.println("sumSurvivalbenefit "+sumSurvivalbenefit);
		
	//	GSVval=(sumtotalBasePremiumPaidforSurr * surrenderValue * p);
		//System.out.println("GSVval "+GSVval);
		
	//	this.GSV_surrendr_val = Math.max(0, ((a * b) - c));
//		System.out.println(year_F + "  a : " + a + " b : " + b + " c: " + c
//				+ "         " + this.GSV_surrendr_val);
		}
		else
		{
			GSVval = 0;
		}
		
		this.GSV_surrendr_val = Math.max(0, GSVval);	
}

	public double getGSV_SurrenderValue() {
		return this.GSV_surrendr_val;
	}

	public void setNonGSV_surrndr_val_4_Percent(int year_F, double nongrntdDeathNenft) {
		double a = 0, b = 0, temp = 0;
		int ptArr[] = { 7, 12, 15 };
		if (year_F <= smartMoneyPlannerBean.getPremiumPayingTerm())
			a = ((double) year_F / smartMoneyPlannerBean.getPremiumPayingTerm()) * smartMoneyPlannerBean.getsumAssured()
					+ nongrntdDeathNenft;
		else
			a = 1 * smartMoneyPlannerBean.getsumAssured() + nongrntdDeathNenft;

		if (year_F > smartMoneyPlannerBean.getPolicyTerm_Basic())
			this.NonGSV_surrndr_val_4Percent = 0;
		else {
			if (smartMoneyPlannerBean.getPremFreqMode().equalsIgnoreCase("Single")) {

				double[] singleSSV = dbObj.getSingle_SSV_Rates();
				int arrCount = 0;
				for (int i = 1; i <= 25; i++) {
					for (int j = 1; j <= 4; j++) {
						if ((i == year_F && smartMoneyPlannerBean.getPlan() == j)) {
							// System.out.println(i +" "+ j);
							b = singleSSV[arrCount];
							break;
						}
						arrCount++;
						// System.out.println(i +" "+ j +" = "+b);
					}

				}
//						System.out.println(" NON GSV b = " + b);
			} else {
				double[] regularSSV = dbObj.getRegular_SSV_Rates();
				int arrCount = 0;

				for (int i = 1; i <= 25; i++) {
					for (int j = 1; j <= 4; j++) {
						if ((i == year_F && smartMoneyPlannerBean.getPlan() == j)) {
							// System.out.println(i +" "+ j);
							b = regularSSV[arrCount];
							break;
						}
						arrCount++;
						// System.out.println(i +" "+ j +" = "+b);
					}
				}
//						System.out.println(" NON GSV b = " + b);
			}
			// System.out.println("SSV : "+b);
			// double temp2= Double.parseDouble(String.valueOf(policyTerm));
			// double temp1=Double.parseDouble(String.valueOf(sumAssured));
			// a=year_F/temp2*temp1;
//					System.out.println("SSV : " + b + "   " + a + "   "
//							+ nongrntdDeathNenft);
			// return Math.round((a+nongrntdDeathNenft/terminalBonus)*b);

			this.NonGSV_surrndr_val_4Percent = (Math
					.round(Double.parseDouble(cfap.getRoundOffLevel2(cfap.getStringWithout_E(a * b)))));

		}
	}

	public double getNonGSV_surrndr_val_4_Percent() {
		return this.NonGSV_surrndr_val_4Percent;
	}

	public void setNonGSV_surrndr_val_8_Percent(int year_F, double nongrntdDeathNenft) {
		double a = 0, b = 0, temp = 0;
		int ptArr[] = { 7, 12, 15 };
		if (year_F <= smartMoneyPlannerBean.getPremiumPayingTerm())
			a = ((double) year_F / smartMoneyPlannerBean.getPremiumPayingTerm()) * smartMoneyPlannerBean.getsumAssured()
					+ nongrntdDeathNenft;
		else
			a = 1 * smartMoneyPlannerBean.getsumAssured() + nongrntdDeathNenft;

		if (year_F > smartMoneyPlannerBean.getPolicyTerm_Basic())
			this.NonGSV_surrndr_val_8Percent = 0;
		else {
			if (smartMoneyPlannerBean.getPremFreqMode().equalsIgnoreCase("Single")) {

				double[] singleSSV = dbObj.getSingle_SSV_Rates();
				int arrCount = 0;
				for (int i = 1; i <= 25; i++) {
					for (int j = 1; j <= 4; j++) {
						if ((i == year_F && smartMoneyPlannerBean.getPlan() == j)) {
							// System.out.println(i +" "+ j);
							b = singleSSV[arrCount];
							break;
						}
						arrCount++;
						// System.out.println(i +" "+ j +" = "+b);
					}

				}
//						System.out.println(" NON GSV b = " + b);
			} else {

				double[] regularSSV = dbObj.getRegular_SSV_Rates();
				int arrCount = 0;

				for (int i = 1; i <= 25; i++) {
					for (int j = 1; j <= 4; j++) {
						if ((i == year_F && smartMoneyPlannerBean.getPlan() == j)) {
							// System.out.println(i +" "+ j);
							b = regularSSV[arrCount];
							break;
						}
						arrCount++;
						// System.out.println(i +" "+ j +" = "+b);
					}

				}
//						System.out.println(" NON GSV b = " + b);
			}
			// System.out.println("SSV : "+b);
			// double temp2= Double.parseDouble(String.valueOf(policyTerm));
			// double temp1=Double.parseDouble(String.valueOf(sumAssured));
			// a=year_F/temp2*temp1;
//					System.out.println("SSV : " + b + "   " + a + "   "
//							+ nongrntdDeathNenft);
			// return Math.round((a+nongrntdDeathNenft/terminalBonus)*b);

			this.NonGSV_surrndr_val_8Percent = (Math
					.round(Double.parseDouble(cfap.getRoundOffLevel2(cfap.getStringWithout_E(a * b)))));

		}
	}

	public double getNonGSV_surrndr_val_8_Percent() {
		return this.NonGSV_surrndr_val_8Percent;
	}

	/***************** Added By tushar Kotian on 2/8/2017 ********************/
	public double getBackDateInterest(double grossPremium, String bkDate, String cashierDate) {

		try {

			// double indigoRate=7.5;
			/**
			 * Modified by Akshaya. Rate Change from 1-APR-2015
			 */
			double ST = 0;
			if (smartMoneyPlannerBean.getIsJKResidentDiscOrNot()) {
//						ST = smpProp.serviceTaxJKResident;
				ST = smpProp.serviceTax + smpProp.SBCServiceTax + smpProp.KKCServiceTax;
			} else {
				ST = smpProp.serviceTax + smpProp.SBCServiceTax + smpProp.KKCServiceTax;
			}
			/******************* Modified by Akshaya on 14-APR-2015 start **********/
//					double indigoRate = 10;
//					double indigoRate = 8.50;

			/******************* Modified by Saurabh Jain on 08-APR-2019 start **********/
//					double indigoRate = cfap.getIndigoRate(cashierDate);
			double indigoRate = 8.87;
			/******************* Modified by Saurabh Jain on 08-APR-2019 end **********/

			double ServiceTaxValue = (ST + 1) * 100;

			/******************* Modified by Akshaya on 14-APR-2015 end **********/
			int compoundFreq = 2;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat dateformat1 = new SimpleDateFormat("dd-MM-yyyy");

			Date dtBackdate = dateformat1.parse(bkDate);
			String strBackDate = dateFormat.format(dtBackdate);

			/***** Commented By Pryanka Warekar -25-09-2017 - start *******/
//					Calendar cal = Calendar.getInstance();
			// System.out.println("cal "+cal);
			// System.out.println("cal "+cal.getTime());
//					String date = dateFormat.format(cal.getTime());
			Date dtcashierdate = dateformat1.parse(cashierDate);
			String date = dateFormat.format(dtcashierdate);
			/***** Commented By Pryanka Warekar -25-09-2017 - end *******/
			// System.out.println("date "+date);

			/******************* Modified by Akshaya on 14-APR-2015 start **********/
			// double netPremWithoutST=Math.round((grossPremium*100)/103.09);
			double netPremWithoutST = Math.round((grossPremium * 100) / ServiceTaxValue);
			/******************* Modified by Akshaya on 14-APR-2015 end **********/
			// System.out.println("netPremWithoutST "+netPremWithoutST);
			int days = cfap.setDate(date, strBackDate);
			// System.out.println("no of days "+days);
			double monthsBetween = cfap.roundDown((double) days / 365 * 12, 0);
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

	public double getCompoundAmount(double monthsBetween, double netPremWithoutST, double indigoRate,
			int compoundFreq) {
		double compoundAmount = netPremWithoutST
				* Math.pow((1 + (indigoRate / (100 * compoundFreq))), (compoundFreq * (monthsBetween / 12)))
				- netPremWithoutST;
		return compoundAmount;
		// System.out.println("compoundAmount "+compoundAmount);
	}

	public double getMinesOccuInterest(double SumAssured_Basic) {
		return (SumAssured_Basic / 1000) * 2;
	}
	/***************** Added By tushar Kotian on 2/8/2017 ********************/
	
	public double getTotalMaturityBenefit4percent(int year,int policyterm,double maturityBen,double nongrntdDeathNenft_4Percent)
	{
		double totalmat4per;
		if(year == policyterm)
		{
			totalmat4per = maturityBen + nongrntdDeathNenft_4Percent + 0.15 * nongrntdDeathNenft_4Percent ;
		}
		else
		{
			totalmat4per=0;
		}					
		return totalmat4per;
	}
	
	public double getTotalMaturityBenefit8percent(int year,int policyterm,double maturityBen,double nongrntdDeathNenft_8Percent)
	{
		double totalmat4per;
		if(year == policyterm)
		{
			totalmat4per = maturityBen + nongrntdDeathNenft_8Percent + 0.15 * nongrntdDeathNenft_8Percent ;
		}
		else
		{
			totalmat4per=0;
		}					
		return totalmat4per;
	}
	
	public void settotalbaseofppt(int year_F,int ppt,double BasePremiumPaid)
	{
		if(year_F<= ppt)
		{
			//System.out.println("BasePremiumPaid "+BasePremiumPaid);
			this.totalBasePremiumPaidPPT= (BasePremiumPaid);
		}
		else
		{
			this.totalBasePremiumPaidPPT=0;
		}
		setSingleTotalBasePremiumPaid(year_F, totalBasePremiumPaid);

	}
	
	public double gettotalbaseofppt()
	{
		return this.totalBasePremiumPaidPPT;
	}
	
	public double getGuaranteedSurvivalBenefitFinal(int policyTerm,int year_f)
	{
		if(year_f > policyTerm)
		{
			return 0;
		}
		else
		{
			if(year_f == policyTerm)
			{
				return 0;
			}
			else
			{
				//System.out.println("getGuaranteedSurvivalBenefit "+getGuaranteedSurvivalBenefit());
				return getGuaranteedSurvivalBenefit();
			}
		}
	}

}
