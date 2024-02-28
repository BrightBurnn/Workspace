package com.sbilife.saralPension;

import com.sbilife.common.CommonForAllProd;

public class SaralPensionBusinessLogic {

	private CommonForAllProd cfap=null; 
	private SaralPensionBean saralPensionBean=null;
	private SaralPensionProperties prop=null;
	private SaralPensionDB objDB = null;
	
	public SaralPensionBusinessLogic(SaralPensionBean saralPensionBean) {
		// TODO Auto-generated constructor stub
		cfap=new CommonForAllProd();
		this.saralPensionBean= saralPensionBean;
		prop=new SaralPensionProperties();
		objDB=new SaralPensionDB();
	}
	public double getBasicPremium()
	{	
		double SA=saralPensionBean.getSumAssured();
//		System.out.println("SA "+SA);
//		System.out.println("rate "+getBasicPremiumRate());
		return (SA * getBasicPremiumRate())/1000;
	}
	
	public double getBasicPremiumRate()
	{
		SaralPensionDB sp_Db=new SaralPensionDB();
		int arrCount=0;
		String prStr,pr=null;
		String []prStrArr;
		double prDouble=0;
//		if(saralPensionBean.getPremiumFreq().equals("Single"))
//		{prStr=sp_Db.getPremiumRate_Arr("Single");}
//		else
//		{prStr=sp_Db.getPremiumRate_Arr("Regular");}
//		prStrArr=cfap.split(prStr, ",");
		if(saralPensionBean.getPremiumFreq().equals("Single"))
		{
			prStr=sp_Db.getPremiumRate_Arr("Single");
			prStrArr=cfap.split(prStr, ",");
			for(int i=5;i<=40;i++)
			{
				if(i==saralPensionBean.getPolicyTerm())
				{
//					System.out.println("i "+i);
//					System.out.println("term "+saralPensionBean.getPolicyTerm());
					pr=prStrArr[arrCount];
					prDouble=Double.parseDouble(pr);
					break;
				}
				arrCount++;
			}
		}
		else
		{
			prStr=sp_Db.getPremiumRate_Arr("regular");
			prStrArr=cfap.split(prStr, ",");
			for(int i=10;i<=40;i++)
			{
				if(i==saralPensionBean.getPolicyTerm())
				{
//					System.out.println("i "+i);
//					System.out.println("term "+saralPensionBean.getPolicyTerm());
					pr=prStrArr[arrCount];
					prDouble=Double.parseDouble(pr);
					break;
				}
				arrCount++;
			}
		}
//		System.out.println("prDouble "+prDouble);
		return prDouble;
		
	}
	
	public double getDiscount()
	{
		double disc=0;
		if(saralPensionBean.getStaffDisc()==true)
		{
			if(saralPensionBean.getPremiumFreq().equals("Single"))
			{disc=getBasicPremium()*0.02;}
			else
			{disc= getBasicPremium()*0.0225;}
		}
//		System.out.println("disc "+disc);
		return disc;
	}
	
	public double getModalLoading(double annualPremWithDiscount)
	{
		if(saralPensionBean.getPremiumFreq().equals("Single") || saralPensionBean.getPremiumFreq().equals("Yearly"))
		{return annualPremWithDiscount;}
		else if(saralPensionBean.getPremiumFreq().equals("Half Yearly"))
		{return annualPremWithDiscount * 50.2/100;}
		else
		{return annualPremWithDiscount*8.4/100;}
	}
	
	public double getPTRPremiumRate()
	{
		SaralPensionDB sp_Db=new SaralPensionDB();
		int arrCount=0;
		String prStr,pr=null;
		String []prStrArr;
		double prDouble=0;
		
		if(saralPensionBean.getPremiumFreq().equals("Single"))
		{prStr=sp_Db.getPTRPremiumRate_Arr("Single");}
		else
		{prStr=sp_Db.getPTRPremiumRate_Arr("Regular");}
		prStrArr=cfap.split(prStr,",");
		//Here min age is 18 & max age is 65/min term is 5 & max term is 30
		for (int i=18; i<61;i++)
		{
			for (int j = 5; j <31; j++)
			{
				
				if(i==saralPensionBean.getAge() && j==saralPensionBean.getPTRTerm())
				{
					pr=prStrArr[arrCount];
					prDouble=Double.parseDouble(pr);
					break;
				}
				arrCount++;
			}
		}
		return prDouble;
	}
	
	public double getPTRPremium()
	{	
		double SA=saralPensionBean.getPTR_sumAssured();
		double rate=0;
		if(saralPensionBean.getPremiumFreq().equals("Single"))
		{
			rate=getPTRPremiumRate()*100;
		}
		else
		{
			rate=getPTRPremiumRate();
		}
		return (SA * rate/100000);
	}
	
	public double getPTRDiscount()
	{
		double disc=0;
		if(saralPensionBean.getStaffDisc()==true)
		{
			if(saralPensionBean.getPremiumFreq().equals("Single"))
			{disc=getPTRPremium()*0.02;}
			else
			{disc= getPTRPremium()*0.0225;}
		}
		return disc;
	}
	
	public double getPTRModalLoading(double annualPTRPremWithDiscount)
	{
		//System.out.println("annualPTRPremWithDiscount " + annualPTRPremWithDiscount);
		if(saralPensionBean.getPremiumFreq().equals("Single") || saralPensionBean.getPremiumFreq().equals("Yearly"))
		
		{return annualPTRPremWithDiscount;}
		else if(saralPensionBean.getPremiumFreq().equals("Half Yearly"))
		{return annualPTRPremWithDiscount * 52/100;}
		else
		{return annualPTRPremWithDiscount*8.9/100;}
	}
	/**
	  * As per the changes from 1st Apr 2015, by Vrushali Chaudhari
	  * 
	  */
	
	/*** modified by Akshaya on 20-MAY-16 start **/
//	public double getServiceTax(double totalPremiumWithoutST)
//	{
//		if(saralPensionBean.getJkResident()==true)
//		{return totalPremiumWithoutST * prop.serviceTaxJkResident;}
//		else
//		{return totalPremiumWithoutST * prop.serviceTax;}
//	}
	//Added by sujata on 09-01-2020
	 public double getServiceTax(double modalLoading,boolean JKResident,boolean state,String type)
		{
			double basicST=0,sbcST=0,kkcST=0,basicJkST=0,keralaST=0,premST;

		
			if(type.equals("basic"))
			{
				if(state==true)
				{
//					basicST =Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*(prop.servtx))));
					basicST =Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*(prop.serviceTax))));
					//basicST = basicST-modalLoading;
				}
	//				return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*prop.serviceTaxJkResident)));
				else
				{
//					basicST =Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*(prop.serviceTax))));
					basicST =Double.parseDouble((cfap.getStringWithout_E(modalLoading*(prop.serviceTax))));
					//System.out.println("FirstYearServicetax "+ (basicST));
					//basicST=(basicST-modalLoading);
				}
				
//				basicST = basicST + (Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*(prop.serviceTax)))));
				basicST = basicST + (Double.parseDouble((cfap.getStringWithout_E(modalLoading*(prop.serviceTax)))));
//				System.out.println("FirstYearServicetax "+ (basicST));
				
				return (basicST);
			}
			
			else if (type.equals("SBC"))
			{
				if(JKResident==true)
					return 0;
				else
				{
					return 0;
			//		return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*prop.SBCServiceTax)));
				}
			}
		//  Added By Saurabh Jain on 14/05/2019 Start
			else if (type.equals("KERALA"))
			{
				return 0;
			//		return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*prop.kerlaServiceTax)));
			}
		//  Added By Saurabh Jain on 14/05/2019 End
			else //KKC
			{
				if(JKResident==true)
					return 0;
				else
				{
					return 0;
	//				return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*prop.KKCServiceTax)));
				}
			}
				
		}

		 
		 public double getServiceTaxSecondYear(double modalLoading,boolean JKResident,boolean state,String type)
			{
			 	double basicST=0,sbcST=0,kkcST=0,basicJkST=0,keralaST=0;

				if(type.equals("basic"))
				{
					if(state==true)
					{
//						basicST= Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*(prop.kerlaServiceTaxSecondYear))));
						basicST= Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*(prop.serviceTaxSecondYear))));

					}
	//					return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(totalPremiumWithoutST*prop.serviceTaxJkResident)));
					else
					{
//						basicST =Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*(prop.serviceTaxSecondYear))));
						basicST =Double.parseDouble((cfap.getStringWithout_E(modalLoading*(prop.serviceTaxSecondYear))));
	//					return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(totalPremiumWithoutST*prop.serviceTaxSecondYear)));
					}
//					basicST = basicST + (Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*(prop.serviceTaxSecondYear)))));
					basicST = basicST + (Double.parseDouble((cfap.getStringWithout_E(modalLoading*(prop.serviceTaxSecondYear)))));
					
					return basicST;
				}
				else if (type.equals("SBC"))
				{
					if(JKResident==true)
						return 0;
					else
					{
						return 0;
						//return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(totalPremiumWithoutST*prop.SBCServiceTaxSecondYear)));
					}
				}
			//  Added By Saurabh Jain on 14/05/2019 Start
				else if (type.equals("KERALA"))
				{
					return 0;
					//return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(totalPremiumWithoutST*prop.kerlaServiceTaxSecondYear)));

				}
			//  Added By Saurabh Jain on 14/05/2019 End
				else //KKC
				{
					if(JKResident==true)
						return 0;
					else
					{
						return 0;
						//return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(totalPremiumWithoutST*prop.KKCServiceTaxSecondYear)));
					}
				}
					
			}
		 
		 
		 /*************Rider service tax*********************/
		 
		 public double getServiceTaxRiderBasic(String type,boolean PTR_Status,double PTR_ModalPremiumWithoutDiscount)
		 {
			 double basicST=0;
			 if(PTR_Status==true)
			 {
				 basicST = Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(PTR_ModalPremiumWithoutDiscount)));
				 return basicST;
			 }
			 else
			 {
				 return 0;
			 }
		 }
		 
		 public double getServiceTaxRider(double premiumSingleInstBasicRider,boolean JKResident,boolean state,String type)
			{
				double basicST=0,sbcST=0,kkcST=0,basicJkST=0,keralaST=0,premST;

			
				if(type.equals("basic"))
				{
					if(state==true)
					{
//						basicST =Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(premiumSingleInstBasicRider*(prop.servtx))));
						basicST =Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(premiumSingleInstBasicRider*(prop.serviceTax))));
						//basicST = basicST-modalLoading;
					}
		//				return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*prop.serviceTaxJkResident)));
					else
					{
//						basicST =Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(premiumSingleInstBasicRider*(prop.serviceTax))));
						basicST =Double.parseDouble((cfap.getStringWithout_E(premiumSingleInstBasicRider*(prop.serviceTax))));
//						System.out.println("basicST 1 "+ (basicST));
						//basicST=(basicST-modalLoading);
					}
					
//					basicST = basicST + (Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(premiumSingleInstBasicRider*(prop.serviceTax)))));
					basicST = basicST + (Double.parseDouble((cfap.getStringWithout_E(premiumSingleInstBasicRider*(prop.serviceTax)))));
//					System.out.println("basicST "+ (basicST));
					return (basicST);
				}
				
				else if (type.equals("SBC"))
				{
					if(JKResident==true)
						return 0;
					else
					{
						return 0;
				//		return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*prop.SBCServiceTax)));
					}
				}
			//  Added By Saurabh Jain on 14/05/2019 Start
				else if (type.equals("KERALA"))
				{
					return 0;
				//		return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*prop.kerlaServiceTax)));
				}
			//  Added By Saurabh Jain on 14/05/2019 End
				else //KKC
				{
					if(JKResident==true)
						return 0;
					else
					{
						return 0;
		//				return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*prop.KKCServiceTax)));
					}
				}
					
			}

			 
//for 2nd year
		 
		 
		 public double getServiceTaxRiderSecondYear(double premiumSingleInstBasicRider,boolean JKResident,boolean state,String type)
			{
				double basicST=0,sbcST=0,kkcST=0,basicJkST=0,keralaST=0,premST;

			
				if(type.equals("basic"))
				{
					if(state==true)
					{
//						basicST =Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(premiumSingleInstBasicRider*(prop.kerlaServiceTaxSecondYear))));
						basicST =Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(premiumSingleInstBasicRider*(prop.serviceTaxSecondYear))));
						//basicST = basicST-modalLoading;
					}
		//				return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*prop.serviceTaxJkResident)));
					else
					{
//						basicST =Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(premiumSingleInstBasicRider*(prop.serviceTaxSecondYear))));
						basicST =Double.parseDouble((cfap.getStringWithout_E(premiumSingleInstBasicRider*(prop.serviceTaxSecondYear))));
//						System.out.println("basicST 1 "+ (basicST));
						//basicST=(basicST-modalLoading);
					}
					
//					basicST = basicST + (Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(premiumSingleInstBasicRider*(prop.serviceTaxSecondYear)))));
					basicST = basicST + (Double.parseDouble((cfap.getStringWithout_E(premiumSingleInstBasicRider*(prop.serviceTaxSecondYear)))));
//					System.out.println("basicST "+ (basicST));
					return (basicST);
				}
				
				else if (type.equals("SBC"))
				{
					if(JKResident==true)
						return 0;
					else
					{
						return 0;
				//		return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*prop.SBCServiceTax)));
					}
				}
			//  Added By Saurabh Jain on 14/05/2019 Start
				else if (type.equals("KERALA"))
				{
					return 0;
				//		return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*prop.kerlaServiceTax)));
				}
			//  Added By Saurabh Jain on 14/05/2019 End
				else //KKC
				{
					if(JKResident==true)
						return 0;
					else
					{
						return 0;
		//				return Double.parseDouble(cfap.getRoundUp(cfap.getStringWithout_E(modalLoading*prop.KKCServiceTax)));
					}
				}
					
			}
		 

			/*** modified by Akshaya on 20-MAY-16 end **/
	
	public double getGuaranteedVestingBenefit(double sumAssured)
	{
		double val=0;
		val=sumAssured + sumAssured*(0.025*3+0.0275*2);
		return val;
	}
	public double getVestingBenefit(int j,double guaranteedVestingBenefit)
	{
			if(j < saralPensionBean.getPolicyTerm())
			{
//				System.out.println("percent value "+getPercentValue(j));
				guaranteedVestingBenefit=getPercentValue(j)+guaranteedVestingBenefit;
//				System.out.println("guaranteedVestingBenefit in if " +guaranteedVestingBenefit);
			}
			else if(j== saralPensionBean.getPolicyTerm())
			{
//				System.out.println("guaranteedVestingBenefit "+guaranteedVestingBenefit);
				guaranteedVestingBenefit=saralPensionBean.getSumAssured()+guaranteedVestingBenefit;
			}
		return guaranteedVestingBenefit;
	}
	
	public double getVestingBenefit11(int j,double guaranteedVestingBenefit)
	{
			/*if(j < saralPensionBean.getPolicyTerm())
			{
//				System.out.println("percent value "+getPercentValue(j));
				guaranteedVestingBenefit=getPercentValue(j)+guaranteedVestingBenefit;
//				System.out.println("guaranteedVestingBenefit in if " +guaranteedVestingBenefit);
			}
			else*/
		if(j== saralPensionBean.getPolicyTerm())
			{
//				System.out.println("guaranteedVestingBenefit "+guaranteedVestingBenefit);
				guaranteedVestingBenefit= saralPensionBean.getSumAssured() +(saralPensionBean.getSumAssured() *.025 *3) + (saralPensionBean.getSumAssured() *.0275 *2);
			}
		else {
			
			guaranteedVestingBenefit =0;	
			
			}
		return guaranteedVestingBenefit;
	}
	
	public double getPercentValue(int i)
	{
		if(i==1)
		{return 0.025*saralPensionBean.getSumAssured();}
		else if(i==2)
		{return 0.025 * saralPensionBean.getSumAssured();}
		else if(i==3)
		{return 0.025 * saralPensionBean.getSumAssured();}
		else if(i==4)
		{return 0.0275 * saralPensionBean.getSumAssured();}
		else if(i==5)
		{return 0.0275 * saralPensionBean.getSumAssured();}
		else
		{return 0;}
	}
	
//	public double getNonGuaranVestingBenefit(double percent,double vestingBenefit,int j,double nonGuaranVestingBenefit)
//	{
//		if(j<=5)
//		{
//			nonGuaranVestingBenefit=vestingBenefit;
//		}
//		else if(j>5 && j< saralPensionBean.getPolicyTerm())
//		{
//			nonGuaranVestingBenefit=percent * saralPensionBean.getSumAssured() +nonGuaranVestingBenefit;
//		}
//		else if(j==saralPensionBean.getPolicyTerm())
//		{
//			nonGuaranVestingBenefit=percent* saralPensionBean.getSumAssured() +nonGuaranVestingBenefit+saralPensionBean.getSumAssured();
//		}
//		return nonGuaranVestingBenefit;
//	}
	
	public double getNonGuaranVestingBenefit(String percent, double bonus_rate,double vestingBenefit,int j,double nonGuaranVestingBenefit)
	{
		if(j<=3)
		{
			if(percent.equals("4%"))
			nonGuaranVestingBenefit=Math.max(0.025,bonus_rate)*saralPensionBean.getSumAssured()+nonGuaranVestingBenefit;
			else
//			nonGuaranVestingBenefit=Math.max(0.025,bonus_rate)*saralPensionBean.getSumAssured()*j+nonGuaranVestingBenefit;
				nonGuaranVestingBenefit=Math.max(0.025,bonus_rate)*saralPensionBean.getSumAssured()*j;
		}
		else if(j<=5)
		{
			
			 if(saralPensionBean.getPolicyTerm()==j)
//				 nonGuaranVestingBenefit=((Math.max(0.0275,bonus_rate)*saralPensionBean.getSumAssured()+nonGuaranVestingBenefit)*1.15)+saralPensionBean.getSumAssured();
				 nonGuaranVestingBenefit=(Math.max(0.0275,bonus_rate)*saralPensionBean.getSumAssured()+nonGuaranVestingBenefit);
			 else if(j==4)
			 {
				 if(percent.equals("4%"))
						nonGuaranVestingBenefit=Math.max(0.0275,bonus_rate)*saralPensionBean.getSumAssured()+nonGuaranVestingBenefit;
						else
//						nonGuaranVestingBenefit=Math.max(0.0275,bonus_rate)*saralPensionBean.getSumAssured()*j+nonGuaranVestingBenefit;
							nonGuaranVestingBenefit=Math.max(0.0275,bonus_rate)*saralPensionBean.getSumAssured()*j;

			 }
			 else
				 nonGuaranVestingBenefit=Math.max(0.0275,bonus_rate)*saralPensionBean.getSumAssured()+nonGuaranVestingBenefit;
			
		}
		else if(j>5 && j< saralPensionBean.getPolicyTerm())
		{
			nonGuaranVestingBenefit=bonus_rate*saralPensionBean.getSumAssured()+nonGuaranVestingBenefit;
		}
		else if(j==saralPensionBean.getPolicyTerm())
		{
			 if(percent.equals("4%"))
			//nonGuaranVestingBenefit=((bonus_rate*saralPensionBean.getSumAssured()+nonGuaranVestingBenefit)*1.15) +saralPensionBean.getSumAssured();
			nonGuaranVestingBenefit=((bonus_rate*saralPensionBean.getSumAssured())+nonGuaranVestingBenefit);
			 else
			nonGuaranVestingBenefit=((bonus_rate*saralPensionBean.getSumAssured())+nonGuaranVestingBenefit);
//			System.out.println("bonus_rate " + bonus_rate);
//			System.out.println("nonGuaranVestingBenefit " + nonGuaranVestingBenefit);
		}
		return nonGuaranVestingBenefit;
	}
	
	/********* Added by Priyanka Warekar - 14-01-2015 ********/
	
	/************Added by sujata on 17-01-2020************/
//	public double BaseNewPrem(double sumassured)
//	{
//		if (saralPensionBean.getPremiumFreq().equals("Yearly") || saralPensionBean.getPremiumFreq().equals("Half Yearly") || saralPensionBean.getPremiumFreq().equals("Half Yearly"))
//		{
//			
//		}
//	}

	//17-01-2020
	public double getTotalBasePremPaid(/*double totalPremiumWithoutST,*/double totalPremiumWithoutservicetax, int year_F) {
		if (saralPensionBean.getPremiumFreq().equals("Single")) {
			if (year_F == 1)
				return  Double.parseDouble(cfap.getRoundOffLevel2(cfap
						.getStringWithout_E(totalPremiumWithoutservicetax)));
			else
				return 0;

		} else if (saralPensionBean.getPremiumFreq().equals("Yearly"))
			return  Double.parseDouble(cfap.getRoundOffLevel2(cfap
					.getStringWithout_E(totalPremiumWithoutservicetax)));
		else if (saralPensionBean.getPremiumFreq().equals("Half Yearly"))
			return Double.parseDouble(cfap.getRoundOffLevel2(cfap
					.getStringWithout_E(totalPremiumWithoutservicetax * 2)));
		else
//			System.out.println("totalPremiumWithoutST............... " +( totalPremiumWithoutservicetax * 12));
			return Double.parseDouble(cfap.getRoundOffLevel2(cfap
					.getStringWithout_E(totalPremiumWithoutservicetax * 12)));
	}
	public double getValueOfK(double _totalBasePremiumPaid, double k) {
		return ((_totalBasePremiumPaid + k) * 1.0025);
	}
	
	public double getGuarnteedDeathBenefit(int year_F,
			double cummulativTotalBasePremiumPaid, double guarnVestingBenefit,
			double _cal_of_colm_k) {
		if (year_F < saralPensionBean.getPolicyTerm())

			return Math.max((_cal_of_colm_k + guarnVestingBenefit),
					(1.05 * cummulativTotalBasePremiumPaid));
		else if (year_F == saralPensionBean.getPolicyTerm())
			return Math.max(
					(_cal_of_colm_k + guarnVestingBenefit - saralPensionBean
							.getSumAssured()),
					(1.05 * cummulativTotalBasePremiumPaid));
		else
			return 0;
	}
	
	public double getGuarnteedDeathBenefit11(int year_F,
			double cummulativTotalBasePremiumPaid, double guarnVestingBenefit,
			double _cal_of_colm_k,String _getGuarDeathBenForMat) {
		
		double a=Double.parseDouble(_getGuarDeathBenForMat);
		if (year_F <= saralPensionBean.getPolicyTerm()){
			
			return (guarnVestingBenefit +a);
			
		}else{
			
			return 0;
		}
	}
	
	public double getPUV(int year_F, double nonGuaranVestingBenefit) {
		if (year_F < saralPensionBean.getPolicyTerm()) {
			if (saralPensionBean.getPremiumFreq().endsWith("Single"))
				return (saralPensionBean.getSumAssured() + nonGuaranVestingBenefit);
			else
				return ((saralPensionBean.getSumAssured() * year_F / saralPensionBean
						.getPolicyTerm()) + nonGuaranVestingBenefit);

		} else if (year_F == saralPensionBean.getPolicyTerm()) {
			if (saralPensionBean.getPremiumFreq().endsWith("Single"))
				return (saralPensionBean.getSumAssured() + nonGuaranVestingBenefit - saralPensionBean
						.getSumAssured());
			else
				return ((saralPensionBean.getSumAssured() * year_F / saralPensionBean
						.getPolicyTerm()) + nonGuaranVestingBenefit - saralPensionBean
							.getSumAssured());

		} else
			return 0;
	}
	
//	public double getNonGuarnteedSurrenderBenefit(int year_F, double _PUV) {
//		int a = 0, arrCount = 0;
//		double ab = 0, b = 0;
//
//		double[] regularGSV = objDB.getDiscountFactor_Arr();
//
//		if (year_F > saralPensionBean.getPolicyTerm())
//			return 0;
//		else {
//			if (saralPensionBean.getPremiumFreq().equalsIgnoreCase("Single")) {
//				if (year_F > 1)
//					a = 1;
//				else
//					a = 0;
//			} else {
//				if (year_F > 2)
//					a = 1;
//				else
//					a = 0;
//			}
//
//			for (int i = 1; i <= 40; i++) {
//				for (int j = 5; j <= 40; j++) {
//					if (i == year_F && saralPensionBean.getPolicyTerm() == j) {
////						System.out.println(i + " " + j + "  " + arrCount);
//						b = regularGSV[arrCount];
//						break;
//					}
//					arrCount++;
//					// System.out.println(i +" "+ j +" = "+prStrArr[arrCount]);
//				}
//
//			}
////			System.out.println("GSV : " + b + "   " + a + "   " + _PUV);
//			double val  =  Math.round(_PUV * b * a);
//			return Math.round(_PUV * b * a);
//		}
//	}
	
	
	//17-01-2020
	public double getNonGuarnteedSurrenderBenefit(int year_F,double PolicyTerm, double sumassured,double _vestingBenefit_4_pr) {
		int a = 0, arrCount = 0;
		double ab = 0, b = 0;
		double t;

		double[] SSVFactor = objDB.getSSVFactor_Arr(saralPensionBean.getPremiumFreq());

		if (year_F > saralPensionBean.getPolicyTerm())
			return 0;
		else {
			if (saralPensionBean.getPremiumFreq().equalsIgnoreCase("Single")) {	
				t = 1;
			} else {
				t = year_F/PolicyTerm;
//				System.out.println("t "+t);
			}
			
		double Val = (t * sumassured + _vestingBenefit_4_pr);
	//	System.out.println("_vestingBenefit_4_pr " + _vestingBenefit_4_pr);
	//	System.out.println("Val "+Val);
		
		if (saralPensionBean.getPremiumFreq().equalsIgnoreCase("Single")) 
		{
			for (int i = 1; i <= 40; i++) 
			{
				for (int j = 5; j <= 40; j++)
				{
					if (i == year_F && saralPensionBean.getPolicyTerm() == j) 
					{
//						System.out.println(i + " " + j + "  " + arrCount);
						b = SSVFactor[arrCount];
						break;
					}
					arrCount++;
					// System.out.println(i +" "+ j +" = "+prStrArr[arrCount]);
				}

			}
		}
		else
			for (int i = 1; i <= 40; i++) 
			{
				for (int j =10; j <= 40; j++)
				{
					if (i == year_F && saralPensionBean.getPolicyTerm() == j) 
					{
//						System.out.println(i + " " + j + "  " + arrCount);
						b = SSVFactor[arrCount];
//						System.out.println("b " + b);
						break;
					}
					arrCount++;
//				System.out.println("b "+ b);
				}

			}
//			System.out.println("GSV : " + b + "   " + a + "   " + _PUV);
			double Surr4per  =  Val * b;
			
		//	System.out.println("Surr4per " + Surr4per);
			return Surr4per;
		}
	}
	
	
	//17-01-2020
	public double getNonGuarnteedSurrenderBenefit8per(int year_F,double PolicyTerm, double sumassured,double _vestingBenefit_8_pr) {
		int a = 0, arrCount = 0;
		double ab = 0, b = 0;
		double t;

		double[] SSVFactor = objDB.getSSVFactor_Arr(saralPensionBean.getPremiumFreq());

		if (year_F > saralPensionBean.getPolicyTerm())
			return 0;
		else {
			if (saralPensionBean.getPremiumFreq().equalsIgnoreCase("Single")) {	
				t = 1;
			} else {
				t = year_F/PolicyTerm;
//				System.out.println("t "+t);
			}
			
		double Val = (t * sumassured + _vestingBenefit_8_pr);
	//	System.out.println("_vestingBenefit_4_pr " + _vestingBenefit_4_pr);
	//	System.out.println("Val "+Val);
		
		if (saralPensionBean.getPremiumFreq().equalsIgnoreCase("Single")) 
		{
			for (int i = 1; i <= 40; i++) 
			{
				for (int j = 5; j <= 40; j++)
				{
					if (i == year_F && saralPensionBean.getPolicyTerm() == j) 
					{
//						System.out.println(i + " " + j + "  " + arrCount);
						b = SSVFactor[arrCount];
						break;
					}
					arrCount++;
					// System.out.println(i +" "+ j +" = "+prStrArr[arrCount]);
				}

			}
		}
		else
			for (int i = 1; i <= 40; i++) 
			{
				for (int j =10; j <= 40; j++)
				{
					if (i == year_F && saralPensionBean.getPolicyTerm() == j) 
					{
//						System.out.println(i + " " + j + "  " + arrCount);
						b = SSVFactor[arrCount];
//						System.out.println("b " + b);
						break;
					}
					arrCount++;
//				System.out.println("b "+ b);
				}

			}
//			System.out.println("GSV : " + b + "   " + a + "   " + _PUV);
			double Surr4per  =  Val * b;
			
		//	System.out.println("Surr4per " + Surr4per);
			return Surr4per;
		}
	}
	
//	public double getGuarnteedSurrenderBenefit(int year_F,
//			double cummulativTotalBasePremiumPaid, double vestingBenefit) {
//		int a = 0;
//		double bonus = 0, b = 0;
//
//		double[] regularGSV;
//		double[] bonusSurrValueFact;
//
//		if (year_F < saralPensionBean.getPolicyTerm()) {
//
//			regularGSV = objDB.getGSVFactor_Arr(saralPensionBean.getPremiumFreq());
//			b = regularGSV[year_F - 1];
//
//			bonusSurrValueFact = objDB.getBonusSurrenderValueFactor_Arr();
//			bonus = bonusSurrValueFact[saralPensionBean.getPolicyTerm() - year_F
//					+ 1 - 1];
//
//			if (saralPensionBean.getPremiumFreq().equalsIgnoreCase("Single")) {
//				if (year_F > 1)
//					a = 1;
//				else
//					a = 0;
//			} else {
//				if (year_F > 2)
//					a = 1;
//				else
//					a = 0;
//			}
//
//			return Math.round(b * cummulativTotalBasePremiumPaid
//					+ vestingBenefit * bonus * a);
//		} else {
//			if (year_F == saralPensionBean.getPolicyTerm()) {
//
//				regularGSV = objDB.getGSVFactor_Arr(saralPensionBean
//						.getPremiumFreq());
//				b = regularGSV[year_F - 1];
//
//				bonusSurrValueFact = objDB.getBonusSurrenderValueFactor_Arr();
//				bonus = bonusSurrValueFact[saralPensionBean.getPolicyTerm()
//						- year_F + 1 - 1];
//
//				if (saralPensionBean.getPremiumFreq().equalsIgnoreCase("Single")) {
//					if (year_F > 1)
//						a = 1;
//					else
//						a = 0;
//				} else {
//					if (year_F > 2)
//						a = 1;
//					else
//						a = 0;
//				}
//
//				return Math.round(b * cummulativTotalBasePremiumPaid
//						+ (vestingBenefit - saralPensionBean.getSumAssured())
//						* bonus * a);
//			} else
//				return 0;
//		}
//
//	}
	
	
	//17-01-2020
	public double getGuarnteedSurrenderBenefit(int year_F,
			double cummulativTotalBasePremiumPaid, double vestingBenefit,double sumTotalBasePrem) 
	{
		int a = 0;
		double bonus = 0, b = 0,pr;
		int arrCount=0;
		String prStr;
		double SurrValue;
		double prDouble=0;
		double[] regularGSV;
		double[] bonusSurrValueFact;

		if (year_F > saralPensionBean.getPolicyTerm()) 
		{
			return 0;
		}
		else
		{
			regularGSV = objDB.getGSVFactor_Arr(saralPensionBean.getPremiumFreq());
			b = regularGSV[year_F - 1];

//			bonusSurrValueFact = objDB.getBonusSurrenderValueFactor_Arr();
//			bonus = bonusSurrValueFact[saralPensionBean.getPolicyTerm() - year_F
//					+ 1 - 1];

			if (saralPensionBean.getPremiumFreq().equalsIgnoreCase("Single")) 
			{
				for (int i=1; i<41;i++)
				{
						
						if(i==year_F)
						{
							pr= regularGSV[arrCount];
							prDouble=(pr);
							break;
						}
						arrCount++;
					}
				
				SurrValue = prDouble * sumTotalBasePrem;
			}
			else
			{
				for (int i=1; i<41;i++)
				{
					for(int j =10; j< 41;j++)
						{
						if(i==year_F && j==saralPensionBean.getPolicyTerm())
							{
							pr= regularGSV[arrCount];
							prDouble=(pr);
							//System.out.println("prDouble " + prDouble);
							break;
							}
						arrCount++;
						}
						
				}
				SurrValue = prDouble * sumTotalBasePrem;
		//		System.out.println("SurrValue " + SurrValue);
				
			}
		}
		return SurrValue;
	}
			

	
	public double getSurrenderBenefit_4Pr(int year_F, double _nonGuaranVestingBenefit_4Percent){
		
		double a=0,b=0;
		double[] regularGSV;
		double prDouble=0, pr=0;
		int arrCount=0;
		double c=saralPensionBean.getPolicyTerm();
		double d =saralPensionBean.getSumAssured();
		if (saralPensionBean.getPremiumFreq().equals("Single")){
			
			a= 1;
			
		}else{
			
			a =(year_F/c);
		}
		
		b = ((a* d) + _nonGuaranVestingBenefit_4Percent);
		
		regularGSV = objDB.getSSVFactor_Arr(saralPensionBean.getPremiumFreq());

		if (saralPensionBean.getPremiumFreq().equalsIgnoreCase("Single")) 
		{
			for (int i=1; i<41;i++)
			{
			for (int j=5; j<41;j++)
			{
					
				if(i==year_F && j==saralPensionBean.getPolicyTerm())
					{
						pr= regularGSV[arrCount];
						prDouble=(pr);
//						System.out.println("prDouble " + prDouble);
						break;
					}
					arrCount++;
				}
		}
		}
		else
		{
			for (int i=1; i<41;i++)
			{
				for(int j =10; j< 41;j++)
					{
					if(i==year_F && j==saralPensionBean.getPolicyTerm())
						{
						pr= regularGSV[arrCount];
						prDouble=(pr);
						//System.out.println("prDouble " + prDouble);
						break;
						}
					arrCount++;
					}
					
			}
	}
		return (b * prDouble);
	}
	
public double getSurrenderBenefit_8Pr(int year_F, double _nonGuaranVestingBenefit_8Percent){
		
		double a=0,b=0;
		double[] regularGSV;
		double prDouble=0, pr=0;
		int arrCount=0;
		double c=saralPensionBean.getPolicyTerm();
		double d =saralPensionBean.getSumAssured();
		if (saralPensionBean.getPremiumFreq().equals("Single")){
			
			a= 1;
			
		}else{
			
			a =(year_F/c);
		}
		
		b = ((a* d) + _nonGuaranVestingBenefit_8Percent);
		
		regularGSV = objDB.getSSVFactor_Arr(saralPensionBean.getPremiumFreq());

		if (saralPensionBean.getPremiumFreq().equalsIgnoreCase("Single")) 
		{
			for (int i=1; i<41;i++)
			{
			for (int j=5; j<41;j++)
			{
					
				if(i==year_F && j==saralPensionBean.getPolicyTerm())
					{
						pr= regularGSV[arrCount];
						prDouble=(pr);
						break;
					}
					arrCount++;
				}
		}
		}
		else
		{
			for (int i=1; i<41;i++)
			{
				for(int j =10; j< 41;j++)
					{
					if(i==year_F && j==saralPensionBean.getPolicyTerm())
						{
						pr= regularGSV[arrCount];
						prDouble=(pr);
						//System.out.println("prDouble " + prDouble);
						break;
						}
					arrCount++;
					}
					
			}
	}
		return (b * prDouble);
	}
	
	/** Added by Vrushali on 06 Aug 2015 Start **/
	public double getStaffRebate() {
		double staff_Rebate;
		// staffRebate
		if (saralPensionBean.getStaffDisc() == true) {
			if (saralPensionBean.getPremiumFreq().equals("Single"))
				staff_Rebate = 0.02;
			else
				staff_Rebate = 0.0225;
		} else
			staff_Rebate = 0;
		return staff_Rebate;

	}
	/** Added by Vrushali on 06 Aug 2015 End **/
	
	public double getPremiumRate() {
		double b = 0;
		double[] lifetimeIncome_Arr = objDB.getLifeTimeIncome_Arr();

		b = lifetimeIncome_Arr[saralPensionBean.getPolicyTerm()
				+ saralPensionBean.getAge() - 40];
//
//		System.out.println((saralPensionBean.getBasicTerm()
//				+ saralPensionBean.getAge() - 1)
//				+ "    Premium rAte: " + b);
		return b;
	}
	
	public double getAnnuityAmount(double maxVestingBenefit) {/*	

		int val1 = saralPensionBean.getAge() + saralPensionBean.getPolicyTerm();
		double aa =0;
		if(saralPensionBean.getState()){
			
			aa= 0.019;
		}else{
			
			aa =0.018;
		}
		double val2 = Math.round(maxVestingBenefit/(1+aa)) ;
//		System.out.println(" val2: " +val2);
//        double [] PWBarr=objDB.getAnnuityRatesForLifetimeIncome();
        
        double pr =getpremiumRate_Monthly();
        
        double a =0;
        if(val2 <1000000){
        	 a=0;
        }
        else if(val2 >=1000000 && val2 <=1499999){
        	 a=0.5;
        }
        else if(val2 >=1500000){
        	a=1;
        }
        
        double val3 = (((pr * 1.035)+a)/(1-0.02));
//        System.out.println(" val3: " +val3);
        return Math.round((val2*val3)/1000);
        
		*//**************************Modified by SAURABH JAIN on 07/03/2019*******End*************************//*
	*/
		int val1 = saralPensionBean.getAge() + saralPensionBean.getPolicyTerm();
		double aa =0;
		/*if(saralPensionBean.getState()){
			
			aa= 0.019;
		}else{*/
			
			aa =0.018;
//		}
		double val2 = Math.round(maxVestingBenefit/(1+aa)) ;
//		System.out.println(" val2: " +val2);
//        double [] PWBarr=objDB.getAnnuityRatesForLifetimeIncome();
        
        double pr =getpremiumRate_Monthly();
        String annuityOption=saralPensionBean.getAnnuityOption();
        
        double a =0;
        if(annuityOption.equalsIgnoreCase("Life Annuity with Annual Simple Increase of 3%") || annuityOption.equalsIgnoreCase("Life Annuity with Annual Simple Increase of 5%")){
			if(val2 >= 1000000 && val2 <= 2499999)
			{a= 0.2;}
			else if(val2>=2500000  &&  val2<=4999999)
				{a= 0.5;}
			else if (val2>=5000000  &&  val2<=9999999)
			{a= 0.6;}
			else if (val2>=10000000)
			{a= 0.7;}
			else{
				a= 0;
			}
		}else if(annuityOption.equalsIgnoreCase("Life Annuity with Annual Compound Increase of 3%") || annuityOption.equalsIgnoreCase("Life Annuity with Annual Compound Increase of 5%")){
			if(val2 >= 1000000 && val2 <= 2499999)
			{a= 0.15;}
			else if(val2>=2500000  &&  val2<=4999999)
				{a= 0.3;}
			else if (val2>=5000000  &&  val2<=9999999)
			{a= 0.4;}
			else if (val2>=10000000)
			{a= 0.5;}
			else{
				a= 0;
			}
		}else{
			if(val2 >= 1000000 && val2 <= 2499999)
			{a= 0.25;}
			else if(val2>=2500000  &&  val2<=4999999)
				{a= 0.75;}
			else if (val2>=5000000  &&  val2<=9999999)
			{a= 0.95;}
			else if (val2>=10000000)
			{a= 1.05;}
			else{
				a= 0;
			}
		}
        /*if(val2 <1000000){
        	 a=0;
        }
        else if(val2 >=1000000 && val2 <=1499999){
        	 a=0.5;
        }
        else if(val2 >=1500000){
        	a=1;
        }*/
        
//        double val3 = (((pr * 1.035)+a)/(1-0.02));
        double val3 = (((pr * 1.0275)+a));
//        System.out.println(" val3: " +val3);
//        this.annuityPay4pa = "" + Math.round((val2*val3)/1000/0.98);	
        return Math.round((val2*val3)/1000/0.98);
	}
	
	
	public double getbonusrate4(String premFreqMode,int policyTerm)
	{
		double bonusrate4 = 0 ;
		
		if(premFreqMode.equals("Single"))
		{
//			if(policyTerm>=25 )
//			{
//				bonusrate4 = 0.01 ;
//			}
//			else
//			{
//				bonusrate4 = 0.0 ;
//			}
			
			
			if(policyTerm>=5 && policyTerm<=9 )
			{
				bonusrate4 = 0.0 ;
			}
			else if(policyTerm>=10 && policyTerm<=14 )
			{
				bonusrate4 = 0.0 ;
			}
			else if(policyTerm>=15 && policyTerm<=19 )
			{
				bonusrate4 = 0.0 ;
			}
			else if(policyTerm>=20 && policyTerm<=24 )
			{
				bonusrate4 = 0.0 ;
			}
			else if(policyTerm>=25  )
			{
				bonusrate4 = 0.01 ;
			}
		}
		else
		{

			if(policyTerm>=5 && policyTerm<=9 )
			{
				bonusrate4 = 0.0 ;
			}
			else if(policyTerm>=10 && policyTerm<=14 )
			{
				bonusrate4 = 0.01 ;
			}
			else if(policyTerm>=15 && policyTerm<=19 )
			{
				bonusrate4 = 0.01 ;
			}
			else if(policyTerm>=20 && policyTerm<=24 )
			{
				bonusrate4 = 0.013 ;
			}
			else if(policyTerm>=25  )
			{
				bonusrate4 = 0.013 ;
			}
		}
		return bonusrate4;
		
	}
	
	public double getbonusrate8(String premFreqMode,int policyTerm)
	{
		double bonusrate8 = 0 ;
		
		if(premFreqMode.equals("Single"))
		{

			if(policyTerm>=5 && policyTerm<=9 )
			{
				bonusrate8 = 0.037 ;
			}
			else if(policyTerm>=10 && policyTerm<=14 )
			{
				bonusrate8 = 0.044 ;
			}
			else if(policyTerm>=15 && policyTerm<=19 )
			{
				bonusrate8 = 0.049 ;
			}
			else if(policyTerm>=20 && policyTerm<=24 )
			{
				bonusrate8 = 0.053 ;
			}
			else if(policyTerm>=25  )
			{
				bonusrate8 = 0.056 ;
			}
		}
		else
		{

			if(policyTerm>=5 && policyTerm<=9 )
			{
				bonusrate8 = 0.0 ;
			}
			else if(policyTerm>=10 && policyTerm<=14 )
			{
				bonusrate8 = 0.035 ;
			}
			else if(policyTerm>=15 && policyTerm<=19 )
			{
				bonusrate8 = 0.038 ;
			}
			else if(policyTerm>=20 && policyTerm<=24 )
			{
				bonusrate8 = 0.043 ;
			}
			else if(policyTerm>=25  )
			{
				bonusrate8 = 0.045 ;
			}
		}
		return bonusrate8;
		
	}
	
	//added by sujata on 13-01-2020
	//17-01-2020
	public double getannulizedPremFinal(int year_F,int ppt,String premfreq,double annualPremWithDiscount)
    {
		if(premfreq.equals("Single"))
		{
			ppt =1;
			if (year_F <= ppt)
			{
    		//System.out.println("Annulized " + get_Premium_WithoutST_WithoutFreqLoading() );
    		return annualPremWithDiscount;
    		}
			else
			{
				return 0;
			}
		}
    	else
    	{
    		if (year_F <= ppt)
			{
    	//	System.out.println("Annulized " + annualPremWithDiscount );
    		return annualPremWithDiscount;
    		}
			else
			{
				return 0;
			}
    	}
    }
	
	public double getSurvivalBenefit(int year_F, double policyterm,double sumassured)
	{
		if(year_F == policyterm)
		{
			return sumassured;
		}
		else
		{
			return 0;
		}
	}

	public double getNonGuarnteedDeathBenefit4per(int year_F, int policyTerm,double sumassured , String premFreqMode )
	{
		if(year_F> policyTerm)
		{
			return 0;
		}
		else
		{
			double val =sumassured * getbonusrate4(premFreqMode,policyTerm) * year_F;
	//		System.out.println("val " + val);
			return val;
		}
		
	}
	
	public double getNonGuarnteedDeathBenefit8per(int year_F, int policyTerm,double sumassured , String premFreqMode )
	{
		if(year_F> policyTerm)
		{
			return 0;
		}
		else
		{
			double val =sumassured * getbonusrate8(premFreqMode,policyTerm) * year_F;
	//		System.out.println("val " + val);
			return val;
		}
		
	}
	
	public double getGuarDeathBenForMat(int year_F,int policyTerm, double _totalBasePremiumPaid,double getGuarDeathBenForMat)
	{	
		double val;
		if(year_F > policyTerm)
		{
			return 0;
		}
		else
		{
		  val = (getGuarDeathBenForMat + _totalBasePremiumPaid) * 1.0025;
		}
		return val;
	}
	
	public double getTotalMaturityBenefit4per(int year_F,int policyTerm, double sumassured ,double _nonGuaranVestingBenefit_4Percent,double _getGuarDeathBenForMat, String premFreqMode)
	{
		double MaxVal,a;
		if(year_F == policyTerm)
		{
			MaxVal = Math.max(sumassured, _getGuarDeathBenForMat);
			a = MaxVal + _nonGuaranVestingBenefit_4Percent + 0.15 * _nonGuaranVestingBenefit_4Percent;
//			System.out.println("a " + a);
//			System.out.println("_vestingBenefit_4_pr "+_vestingBenefit_4_pr);
		}
		else
		{
			return 0;
		}
		return a;		
	}
	
	//17-01-2020
	public double getTotalMaturityBenefit8per(int year_F,int policyTerm, double sumassured ,double _nonGuaranVestingBenefit_8Percent,double _getGuarDeathBenForMat, String premFreqMode)
	{

		double MaxVal,a;
		if(year_F == policyTerm)
		{
			MaxVal = Math.max(sumassured, _getGuarDeathBenForMat);
			a = MaxVal + _nonGuaranVestingBenefit_8Percent + 0.15 * _nonGuaranVestingBenefit_8Percent;
//			System.out.println("a " + a);
//			System.out.println("_vestingBenefit_4_pr "+_vestingBenefit_4_pr);
		}
		else
		{
			return 0;
		}
		return a;
	}
	public double getTotalDeathBen4per(int year_F,int policyTerm, double _getGuarDeathBenForMat ,double _nonGuaranVestingBenefit_4Percent, double sumTotalBasePrem)
	{
		double totDeath4pr,a,b,MaxVal;
		if(year_F <= policyTerm)
		{
			a = (_getGuarDeathBenForMat + _nonGuaranVestingBenefit_4Percent + 0.15 * _nonGuaranVestingBenefit_4Percent);
			b = (1.05 * sumTotalBasePrem);
			MaxVal = Math.max(a,b);
		}
		else
		{
			return 0;
		}
		return MaxVal;
	}
	
	public double getTotalDeathBen8per(int year_F,int policyTerm,  double _getGuarDeathBenForMat ,double _nonGuaranVestingBenefit_8Percent, double sumTotalBasePrem)
	{

		double totDeath8pr,a,b,MaxVal;
		if(year_F <= policyTerm)
		{
			a = (_getGuarDeathBenForMat + _nonGuaranVestingBenefit_8Percent + 0.15 * _nonGuaranVestingBenefit_8Percent);
			b = (1.05 * sumTotalBasePrem);
			MaxVal = Math.max(a,b);
		}
		else
		{
			return 0;
		}
		return MaxVal;
	}
	public double getGuaranteedAddition(int year_F,double sumassured,double sumGuaranteedAddition,int policyTerm)
	{
		double val,a;
		if(year_F == 1)
		{
	    val= (sumassured * prop.servtx);
		}
		else
		{
		val = sumGuaranteedAddition;
			
		}
		return val;
	}
	
	public int getSheetGroup()
	{
		String annuityOption=saralPensionBean.getAnnuityOption();
		/*if(annuityOption.equals("Lifetime Income")|| annuityOption.equals("Lifetime Income with Capital Refund")|| annuityOption.equals("Lifetime Income with Capital Refund in Parts")|| annuityOption.equals("Lifetime Income with Balance Capital Refund")|| annuityOption.equals("Lifetime Income with Annual Increase of 3%")||annuityOption.equals("Lifetime Income with Annual Increase of 5%")||annuityOption.equals("Lifetime Income with Certain Period of 5 Years")||annuityOption.equals("Lifetime Income with Certain Period of 10 Years")||annuityOption.equals("Lifetime Income with Certain Period of 15 Years")||annuityOption.equals("Lifetime Income with Certain Period of 20 Years") )
		{return 1;}
		else if(annuityOption.equals("Life and Last Survivor - 50% Income"))
		{return 2;}
		else if(annuityOption.equals("Life and Last Survivor - 100% Income"))
		{return 3;}
		else if(annuityOption.equals("Life and Last Survivor with Capital Refund - 50% Income"))
		{return 4;}
		else if(annuityOption.equals("Life and Last Survivor with Capital Refund - 100% Income"))
		{return 5;}
		else if(annuityOption.equals("Income for a period with Balance Capital Refund for Period of 15 years"))
		{return 6;}
		else
		{return 0;}*/
		
		if(annuityOption.equals("Life Annuity")|| annuityOption.equals("Life Annuity with Return of Purchase Price")|| annuityOption.equals("NPS - Family Income")|| annuityOption.equals("Life Annuity with Return of Balance Purchase Price")|| annuityOption.equals("Lifetime Income with Balance Capital Refund")|| annuityOption.equals("Life Annuity with Annual Simple Increase of 3%")||annuityOption.equals("Life Annuity with Annual Simple Increase of 5%")||annuityOption.equals("Life Annuity with Certain Period of 10 Years")||annuityOption.equals("Life Annuity with Certain Period of 20 Years")||annuityOption.equals("Life Annuity with Annual Compound Increase of 3%")||annuityOption.equals("Life Annuity with Annual Compound Increase of 5%")||annuityOption.equals("Life & Last Survivor  - 100% Annuity") ||annuityOption.equals("Life and Last Survivor  - 100% Annuity")||annuityOption.equals("Life and Last Survivor  - 100% Annuity with Return of Purchase Price")||annuityOption.equals("Life & Last Survivor  - 100% Annuity with Return of Purchase Price")||annuityOption.equals("NPS - Family Income"))
		{return 1;}
		else if(annuityOption.equals("Deferred Life Annuity with Return of Purchase Price"))
		{return 2;}
		else if(annuityOption.equals("Deferred Life and Last Survivor  - 100% Annuity with Return of Purchase Price"))
		{return 3;}
		else if(annuityOption.equals("Single Life Deferred Annuity with Refund of Purchase Price"))
		{return 4;}
		else if(annuityOption.equals("Joint Life Deferred Annuity with Refund of Purchase Price"))
		{return 5;}
		else if(annuityOption.equals("Income for a period with Balance Capital Refund for Period of 15 years"))
		{return 6;}
		else
		{return 0;}
	}
	
	public double getpremiumRate_Monthly()
	{/*
		int sheetGroup=getSheetGroup();

//		System.out.println("Sheet Group : "+sheetGroup);
		if(sheetGroup==1)
		{
			double prDouble=0;

				String[] prStrArr=cfap.split(objDB.getPRArr_SingleLifeAnnuity(),",");
				String annuityOption=null;
				int optionRank=0;
				String pr=null;
				int arrCount=0;
				annuityOption=saralPensionBean.getAnnuityOption();
				//double prDouble=0;

				if(annuityOption.equals("Lifetime Income"))
				{optionRank=1;}
				else if(annuityOption.equals("Lifetime Income with Capital Refund"))
				{optionRank=2;}
				else if(annuityOption.equals("Lifetime Income with Capital Refund in Parts"))
				{optionRank=3;}
				else if(annuityOption.equals("Lifetime Income with Balance Capital Refund"))
				{optionRank=4;}
				else if(annuityOption.equals("Lifetime Income with Annual Increase of 3%"))
				{optionRank=5;}
				else if(annuityOption.equals("Lifetime Income with Annual Increase of 5%"))
				{optionRank=6;}
				else if(annuityOption.equals("Lifetime Income with Certain Period of 5 Years"))
				{optionRank=7;}
				else if(annuityOption.equals("Lifetime Income with Certain Period of 10 Years"))
				{optionRank=8;}
				else if(annuityOption.equals("Lifetime Income with Certain Period of 15 Years"))
				{optionRank=9;}
				else if(annuityOption.equals("Lifetime Income with Certain Period of 20 Years"))
				{optionRank=10;}

				//for (int i=40; i<=80;i++)
				for (int i=0; i<=80;i++)
				{
					for (int j = 1; j <=10; j++)
					{
						if(i==saralPensionBean.getVestingAge() && j ==optionRank)
						{
							pr=prStrArr[arrCount];
							prDouble=Double.parseDouble(pr);
							break;
						}
						arrCount++;
					}
				}
//				                System.out.println("*****************Premium Rate:=> "+prDouble);
			//}
			double a = prDouble ;
			return prDouble;
		}

		else if(sheetGroup==2)
		{
			//VLOOKUP(Input!B26,'Joint Life 50%'!B13:AQ54,MATCH(Input!B22,'Joint Life 	50%'!B13:AQ13,0),FALSE)
			String[] prStrArr=cfap.split(objDB.getPRArr_JointLife50Percent(),",");
			String pr=null;
			int arrCount=0;
			double prDouble=0;

			int ageOfSecondAnnuitant=saralPensionBean.getVestingAge();
//			if(ageOfSecondAnnuitant==0)
//			{ageOfSecondAnnuitant=40;}

			//for (int i=40; i<=80;i++)
			for (int i=40; i<=80;i++)
			{
				//for (int j = 40; j <=80; j++)
				for (int j =40; j <=80; j++)
				{
					if(i==ageOfSecondAnnuitant && j ==saralPensionBean.getVestingAge())
					{
						pr=prStrArr[arrCount];
						prDouble=Double.parseDouble(pr);
						break;
					}
					arrCount++;
				}
			}
//			   System.out.println("Premium Rate:=> "+prDouble);
			return prDouble;
		}
		else if(sheetGroup==3)
		{
			//VLOOKUP(Input!B26,'Joint Life 50%'!B13:AQ54,MATCH(Input!B22,'Joint Life 	50%'!B13:AQ13,0),FALSE)
			String[] prStrArr=cfap.split(objDB.getPRArr_JointLife100Percent(),",");
			String pr=null;
			int arrCount=0;
			double prDouble=0;
			int ageOfSecondAnnuitant=saralPensionBean.getVestingAge();
//			if(ageOfSecondAnnuitant==0)
//			{ageOfSecondAnnuitant=40;}

			//for (int i=40; i<=80;i++)
			for (int i=40; i<=80;i++)
			{
				//for (int j = 40; j <=80; j++)
				for (int j =40; j <=80; j++)
				{
					if(i==ageOfSecondAnnuitant && j ==saralPensionBean.getVestingAge())
					{
						pr=prStrArr[arrCount];
						prDouble=Double.parseDouble(pr);
						break;
					}
					arrCount++;
				}
			}
//			   System.out.println("Premium Rate:=> "+prDouble);
			return prDouble;
		}
		else if(sheetGroup==4)
		{
			//VLOOKUP(Input!B26,'Joint Life 50%_ROC'!B13:AQ54,MATCH(Input!B22,'Joint Life 	50%_ROC'!B13:AQ13,0),FALSE)
			String[] prStrArr=cfap.split(objDB.getPRArr_JointLife50Percent_ROC(),",");
			String pr=null;
			int arrCount=0;
			double prDouble=0;

			int ageOfSecondAnnuitant=saralPensionBean.getVestingAge();
//			if(ageOfSecondAnnuitant==0)
//			{ageOfSecondAnnuitant=40;}

			//for (int i=40; i<=80;i++)
			for (int i=40; i<=80;i++)
			{
				//for (int j = 40; j <=80; j++)
				for (int j =40; j <=80; j++)
				{
					//System.out.println("Age of First Annuitant(i) => "+i+"   Age of Second Annuitant(j) => "+j+"   PR=> "+prStrArr[arrCount]);
					if(i==ageOfSecondAnnuitant && j ==saralPensionBean.getVestingAge())
					{
						pr=prStrArr[arrCount];
						prDouble=Double.parseDouble(pr);
						//System.out.println("PR Returned=> "+prDouble);
						break;
					}
					arrCount++;
				}
			}
//			   System.out.println("Premium Rate:=> "+prDouble);
			return prDouble;
		}
		else if(sheetGroup==5)
		{
			//VLOOKUP(Input!B26,'Joint_Life_100%_ROC'!B13:AQ54,MATCH(Input!B22,'Joint_Life_100%_RO	C'!B13:AQ13,0),FALSE)
			String[] prStrArr=cfap.split(objDB.getPRArr_JointLife100Percent_ROC(),",");
			String pr=null;
			int arrCount=0;
			double prDouble=0;
			int ageOfSecondAnnuitant=saralPensionBean.getVestingAge();
//			if(ageOfSecondAnnuitant==0)
//			{ageOfSecondAnnuitant=40;}

			//for (int i=40; i<=80;i++)
			for (int i=40; i<=80;i++)
			{
				//for (int j = 40; j <=80; j++)
				for (int j =40; j <=80; j++)
				{
					if(i==ageOfSecondAnnuitant && j ==saralPensionBean.getVestingAge())
					{
						pr=prStrArr[arrCount];
						prDouble=Double.parseDouble(pr);
						break;
					}
					arrCount++;
				}
			}
//			   System.out.println("Premium Rate:=> "+prDouble);
			return prDouble;
		}
		else if(sheetGroup==6)
		{
			//VLOOKUP(Input!B22,Temporary_Annuity!B13:D77,MATCH(F8,Temporary_Annuity!B14:D14,0),FALSE)
			//VLOOKUP( Age ,Temporary_Annuity!B13:D77,MATCH(F8,Temporary_Annuity!B14:D14,0),FALSE)
			//NOT COMPLETE
			return 0;
		}
		else
		{return 0;}   
	*/
		int sheetGroup=getSheetGroup();

//		System.out.println("Sheet Group : "+sheetGroup);
		if(sheetGroup==1)
		{
			double prDouble=0;

				String[] prStrArr=cfap.split(objDB.getPRArr_SingleLifeAnnuity(),",");
				String annuityOption=null;
				int optionRank=0;
				String pr=null;
				int arrCount=0;
				annuityOption=saralPensionBean.getAnnuityOption();
				//double prDouble=0;

				/*if(annuityOption.equals("Lifetime Income"))
				{optionRank=1;}
				else if(annuityOption.equals("Lifetime Income with Capital Refund"))
				{optionRank=2;}
				else if(annuityOption.equals("Lifetime Income with Capital Refund in Parts"))
				{optionRank=3;}
				else if(annuityOption.equals("Lifetime Income with Balance Capital Refund"))
				{optionRank=4;}
				else if(annuityOption.equals("Lifetime Income with Annual Increase of 3%"))
				{optionRank=5;}
				else if(annuityOption.equals("Lifetime Income with Annual Increase of 5%"))
				{optionRank=6;}
				else if(annuityOption.equals("Lifetime Income with Certain Period of 5 Years"))
				{optionRank=7;}
				else if(annuityOption.equals("Lifetime Income with Certain Period of 10 Years"))
				{optionRank=8;}
				else if(annuityOption.equals("Lifetime Income with Certain Period of 15 Years"))
				{optionRank=9;}
				else if(annuityOption.equals("Lifetime Income with Certain Period of 20 Years"))
				{optionRank=10;}*/
				if(annuityOption.equals("Life Annuity"))
				{optionRank=1;}
				else if(annuityOption.equals("Life Annuity with Return of Purchase Price"))
				{optionRank=2;}
				else if(annuityOption.equals("Life Annuity with Return of Balance Purchase Price"))
				{optionRank=3;}
				else if(annuityOption.equals("Life Annuity with Annual Simple Increase of 3%"))
				{optionRank=4;}
				else if(annuityOption.equals("Life Annuity with Annual Simple Increase of 5%"))
				{optionRank=5;}
				else if(annuityOption.equals("Life Annuity with Certain Period of 10 Years"))
				{optionRank=6;}
				else if(annuityOption.equals("Life Annuity with Certain Period of 20 Years"))
				{optionRank=7;}
				else if(annuityOption.equals("Life Annuity with Annual Compound Increase of 3%"))
				{optionRank=8;}
				else if(annuityOption.equals("Life Annuity with Annual Compound Increase of 5%"))
				{optionRank=9;}
				/*else if(annuityOption.equals("Lifetime Income with Certain Period of 20 Years"))
				{optionRank=10;}*/
				else if(annuityOption.equals("Life & Last Survivor  - 100% Annuity") ||annuityOption.equals("Life and Last Survivor  - 100% Annuity"))
				{optionRank=10;}
				else if(annuityOption.equals("Life and Last Survivor  - 100% Annuity with Return of Purchase Price")||annuityOption.equals("Life & Last Survivor  - 100% Annuity with Return of Purchase Price"))
				{optionRank=11;}
				else if(annuityOption.equals("NPS - Family Income"))
				{optionRank=12;}
				
				//for (int i=40; i<=80;i++)
				for (int i=0; i<=100;i++)
				{
//					for (int j = 1; j <=12; j++)
					for (int j = 1; j <=9; j++)
					{
						if(i==saralPensionBean.getVestingAge() && j ==optionRank)
						{
							pr=prStrArr[arrCount];
							prDouble=Double.parseDouble(pr);
							break;
						}
						arrCount++;
					}
				}
//				                System.out.println("*****************Premium Rate:=> "+prDouble);
			//}
			double a = prDouble ;
			return prDouble;
		}

		else if(sheetGroup==2)
		{
			//VLOOKUP(Input!B26,'Joint Life 50%'!B13:AQ54,MATCH(Input!B22,'Joint Life 	50%'!B13:AQ13,0),FALSE)
			String[] prStrArr=cfap.split(objDB.getPRArr_JointLife50Percent(),",");
			String pr=null;
			int arrCount=0;
			double prDouble=0;

			int ageOfSecondAnnuitant=saralPensionBean.getDeferredPeroid();
//			if(ageOfSecondAnnuitant==0)
//			{ageOfSecondAnnuitant=40;}

			//for (int i=40; i<=80;i++)
			/*for (int i=40; i<=80;i++)
			{
				//for (int j = 40; j <=80; j++)
				for (int j =40; j <=80; j++)
				{*/
			for (int i=45; i<=75;i++)
			{
				//for (int j = 40; j <=80; j++)
				for (int j = 1; j <=10; j++)
				{
					if(j==ageOfSecondAnnuitant && i ==saralPensionBean.getVestingAge())
					{
						pr=prStrArr[arrCount];
						prDouble=Double.parseDouble(pr);
						break;
					}
					arrCount++;
				}
			}
//			   System.out.println("Premium Rate:=> "+prDouble);
			return prDouble;
		}
		else if(sheetGroup==3)
		{
			//VLOOKUP(Input!B26,'Joint Life 50%'!B13:AQ54,MATCH(Input!B22,'Joint Life 	50%'!B13:AQ13,0),FALSE)
			String[] prStrArr=cfap.split(objDB.getPRArr_JointLife100Percent(),",");
			String pr=null;
			int arrCount=0;
			double prDouble=0;
			int ageOfSecondAnnuitant=saralPensionBean.getVestingAge();
			int deferred =saralPensionBean.getDeferredPeroid();
//			if(ageOfSecondAnnuitant==0)
//			{ageOfSecondAnnuitant=40;}

			//for (int i=40; i<=80;i++)
			/*for (int i=40; i<=80;i++)
			{
				//for (int j = 40; j <=80; j++)
				for (int j =40; j <=80; j++)
				{
					if(i==ageOfSecondAnnuitant && j ==saralPensionBean.getVestingAge())
					{*/
			for (int i=45; i<=85;i++)
			{
				for (int j = 1; j <=10; j++){
					
				for (int k = 45; k <=85; k++)
				{
					if(i==saralPensionBean.getVestingAge() && k ==ageOfSecondAnnuitant && j==deferred){
						pr=prStrArr[arrCount];
						prDouble=Double.parseDouble(pr);
						break;
					}
					arrCount++;
					}
//					arrCount++;
				}
			}
			   System.out.println("Premium Rate:=> "+prDouble);
			return prDouble;
		}
		else if(sheetGroup==4)
		{
			//VLOOKUP(Input!B26,'Joint Life 50%_ROC'!B13:AQ54,MATCH(Input!B22,'Joint Life 	50%_ROC'!B13:AQ13,0),FALSE)
			String[] prStrArr=cfap.split(objDB.getPRArr_JointLife50Percent_ROC(),",");
			String pr=null;
			int arrCount=0;
			double prDouble=0;

			int ageOfSecondAnnuitant=saralPensionBean.getVestingAge();
//			if(ageOfSecondAnnuitant==0)
//			{ageOfSecondAnnuitant=40;}

			//for (int i=40; i<=80;i++)
			for (int i=40; i<=80;i++)
			{
				//for (int j = 40; j <=80; j++)
				for (int j =40; j <=80; j++)
				{
					//System.out.println("Age of First Annuitant(i) => "+i+"   Age of Second Annuitant(j) => "+j+"   PR=> "+prStrArr[arrCount]);
					if(i==ageOfSecondAnnuitant && j ==saralPensionBean.getVestingAge())
					{
						pr=prStrArr[arrCount];
						prDouble=Double.parseDouble(pr);
						//System.out.println("PR Returned=> "+prDouble);
						break;
					}
					arrCount++;
				}
			}
//			   System.out.println("Premium Rate:=> "+prDouble);
			return prDouble;
		}
		else if(sheetGroup==5)
		{
			//VLOOKUP(Input!B26,'Joint_Life_100%_ROC'!B13:AQ54,MATCH(Input!B22,'Joint_Life_100%_RO	C'!B13:AQ13,0),FALSE)
			String[] prStrArr=cfap.split(objDB.getPRArr_JointLife100Percent_ROC(),",");
			String pr=null;
			int arrCount=0;
			double prDouble=0;
			int ageOfSecondAnnuitant=saralPensionBean.getVestingAge();
//			if(ageOfSecondAnnuitant==0)
//			{ageOfSecondAnnuitant=40;}

			//for (int i=40; i<=80;i++)
			for (int i=40; i<=80;i++)
			{
				//for (int j = 40; j <=80; j++)
				for (int j =40; j <=80; j++)
				{
					if(i==ageOfSecondAnnuitant && j ==saralPensionBean.getVestingAge())
					{
						pr=prStrArr[arrCount];
						prDouble=Double.parseDouble(pr);
						break;
					}
					arrCount++;
				}
			}
//			   System.out.println("Premium Rate:=> "+prDouble);
			return prDouble;
		}
		else if(sheetGroup==6)
		{
			//VLOOKUP(Input!B22,Temporary_Annuity!B13:D77,MATCH(F8,Temporary_Annuity!B14:D14,0),FALSE)
			//VLOOKUP( Age ,Temporary_Annuity!B13:D77,MATCH(F8,Temporary_Annuity!B14:D14,0),FALSE)
			//NOT COMPLETE
			return 0;
		}
		else
		{return 0;}   
		
		
	}
}
