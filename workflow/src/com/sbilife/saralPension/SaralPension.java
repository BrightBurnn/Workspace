package com.sbilife.saralPension;

import com.sbilife.common.CommonForAllProd;

public class SaralPension {
	
	CommonForAllProd cfap=null;
	SaralPensionProperties prop=null;
	StringBuilder bussIll=null;
	String  _getGuarDeathBenForMat,premInstBasicFirstYear,premiumSingleInstBasicWithST,premInstBasicSecondYear,premInstFYRider,premInstSYRider,premiumSingleInstBasicRider,ServiceTaxRiderBasic;
	double totalMaturityBen4per,sumGuaranteedAddition=0,sumTotalBasePrem=0;

	public String CalculatePrem(String isStaff,String isJkResident,String age,String gender,String policyTerm,String premiumFreq,String sumAssured,String PTRider,String ptrTerm,String ptrSumAssured,String KFC,String annuityOption,String annuityPlanType, String deferredPeroid)
	{
		String retVal="";
		
		try{
		
		SaralPensionBean saralPensionBean=new SaralPensionBean();
		
	//  Added By Saurabh Jain on 14/05/2019 Start
		
		saralPensionBean.setState(Boolean.parseBoolean(KFC));
		saralPensionBean.setDeferredPeroid(Integer.parseInt(deferredPeroid));
		
	//  Added By Saurabh Jain on 14/05/2019 End
		
		saralPensionBean.setStaffDisc(Boolean.parseBoolean(isStaff));
		
		saralPensionBean.setJkResident(Boolean.parseBoolean(isJkResident));
		
		saralPensionBean.setAge(Integer.parseInt(age));
		
		saralPensionBean.setGender(gender);
		
		saralPensionBean.setAnnuityOption(annuityOption);
		
		saralPensionBean.setPolicyTerm(Integer.parseInt(policyTerm));
		
		saralPensionBean.setPremiumFreq(premiumFreq);
		
		saralPensionBean.setSumAssured(Double.parseDouble(sumAssured));
		
		saralPensionBean.setVestingAge(saralPensionBean.getAge() +saralPensionBean.getPolicyTerm());
		
		if(Boolean.parseBoolean(PTRider))
		{
			saralPensionBean.setPTR_Status(true);
			saralPensionBean.setPTRTerm(Integer.parseInt(ptrTerm));
			saralPensionBean.setPTR_sumAssured(Double.parseDouble(ptrSumAssured));
		}
		else{saralPensionBean.setPTR_Status(false);}
		
		retVal=showSaralPensionOutputPg(saralPensionBean);
		
		}
		catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			retVal="<?xml version='1.0' encoding='utf-8' ?><saralPension>"+
					"<errCode>1</errCode>"+
					"<errorMessage>"+e.getMessage()+"</errorMessage></saralPension>";
			
					
		}
		return retVal.toString();
		
	}
	
	public String showSaralPensionOutputPg(SaralPensionBean saralPensionBean)
	{
		SaralPensionBusinessLogic SP_BusinessLogic=new SaralPensionBusinessLogic(saralPensionBean);
		CommonForAllProd cfap=new CommonForAllProd();
		StringBuilder retVal=new StringBuilder();
		bussIll=new StringBuilder();
		String staffStatus="";
		double discountPercentage = 0;
		int _year_F = 0;

		int year_F = 0;
		double _vestingBenefit1=0;
		double _nonGuaranVestingBenefit_4Percent1=0;
		double _nonGuaranVestingBenefit_4Percent2=0;
		double _nonGuaranVestingBenefit_8Percent1=0;
		double _nonGuaranVestingBenefit_8Percent2=0;
		double vestingBenefit1=0;
		double nonGuaranVestingBenefit_4Percent1=0;
		double nonGuaranVestingBenefit_4Percent2 =0;
		double nonGuaranVestingBenefit_8Percent1=0;
		double nonGuaranVestingBenefit_8Percent2=0;
		double getGuarDeathBenForMat=0;
		
		double _vestingBenefit = 0, _vestingBenefit_4_pr = 0, _vestingBenefit_8_pr = 0;
		double _nonGuaranVestingBenefit_4Percent = 0;
		double _nonGuaranVestingBenefit_8Percent = 0;
		double vestingBenefit = 0, vestingBenefit_4_pr = 0, vestingBenefit_8_pr = 0,vestingBenefitMin=0,vestingBenefit11=0;
		double nonGuaranVestingBenefit_4Percent = 0;
		double nonGuaranVestingBenefit_8Percent = 0, totalBasePremiumPaid = 0, cummulativTotalBasePremiumPaid = 0, _totalBasePremiumPaid = 0, _cummulativTotalBasePremiumPaid = 0, guaranDeathBenfit = 0, nonGuaranDeathBenfit_4Percent = 0, nonGuaranDeathBenfit_8Percent = 0, _guaranDeathBenfit = 0, _nonGuaranDeathBenfit_4Percent = 0, _nonGuaranDeathBenfit_8Percent = 0, cal_of_colm_k = 0, _cal_of_colm_k = 0, deathBenefit_4Percent = 0, _deathBenefit_4Percent = 0, _deathBenefit_8Percent = 0, deathBenefit_8Percent = 0, PUV_4Percent = 0, _PUV_4Percent = 0, PUV_8Percent = 0, _PUV_8Percent = 0, guaranSurrBenefit = 0, _guaranSurrBenefit = 0, nonGuaranSurrBen_4Pr = 0, _nonGuaranSurrBen_4Pr = 0, nonGuaranSurrBen_8Pr = 0, _nonGuaranSurrBen_8Pr = 0, surrenderBenefit_4Pr = 0, _surrenderBenefit_4Pr = 0, surrenderBenefit_8Pr = 0, _surrenderBenefit_8Pr = 0, _surrenderBenefit_8Pr1 = 0, annuityAmount_4Pr = 0, _annuityAmount_4Pr = 0,annuityAmount_MiniBen=0, annuityAmount_8Pr = 0,_annuityAmount_Min=0, _annuityAmount_8Pr = 0,maxVestingBenefitMin=0 ,maxVestingBenefit_4Pr = 0, maxVestingBenefit_8Pr = 0
				,totalMaturity4per = 0,totalMaturity8per =0,totDeathBen4per=0,totDeathBen8per=0;
		double guaranDeathBenfit11=0,_guaranDeathBenfit11=0;
		
//		String getGuarDeathBenForMat;
		
		double basicPremium=SP_BusinessLogic.getBasicPremium();
//		System.out.println("basicprem "+basicPremium);
		double discount=SP_BusinessLogic.getDiscount();
//		System.out.println("discount "+discount);
		double annualPremWithDiscount=(basicPremium-discount);
//		System.out.println("annualPremWithDiscount "+annualPremWithDiscount);

		double modalPremiumWithoutDiscount = SP_BusinessLogic
				.getModalLoading(basicPremium);
		// System.out.println("modalPremiumWithoutDiscount "+modalPremiumWithoutDiscount);
		
		double modalLoading=SP_BusinessLogic.getModalLoading(annualPremWithDiscount);
		String modalLoadingPrem = cfap.getRoundOffLevel2(cfap.getStringWithout_E(modalLoading));
//		System.out.println("modalLoading "+modalLoading);
			
		double PTR_Premium=SP_BusinessLogic.getPTRPremium();
//		System.out.println("PTR premium "+PTR_Premium);
		double PTR_Discount=SP_BusinessLogic.getPTRDiscount();
//		System.out.println("PTR discount "+PTR_Discount);
		double annualPTRPremiumWithDiscount=(PTR_Premium-PTR_Discount);
//		System.out.println("annualPTRPremiumWithDiscount "+annualPTRPremiumWithDiscount);
		double PTR_ModalLoading=SP_BusinessLogic.getPTRModalLoading(annualPTRPremiumWithDiscount);
//		System.out.println("PTR_ModalLoading "+PTR_ModalLoading);
		
		/** Added by Vrushali on 07-Aug-2015 start **/
		double PTR_ModalPremiumWithoutDiscount = SP_BusinessLogic
				.getPTRModalLoading(PTR_Premium);
//		System.out.println("PTR_ModalPremiumWithoutDiscount "
//				+ PTR_ModalPremiumWithoutDiscount);

		double totalPremiumWithoutDiscount = modalPremiumWithoutDiscount
				+ PTR_ModalPremiumWithoutDiscount;
//		System.out.println("totalPremiumWithoutDiscount "
//				+ totalPremiumWithoutDiscount);
		/** Added by Vrushali on 07-Aug-2015 start **/
		
		//17-01-2020
		/*double totalPremiumWithoutST=Double.parseDouble(cfap.getRoundUp(cfap
				.getStringWithout_E(modalPremiumWithoutDiscount + PTR_ModalLoading)));*/
		double totalPremiumWithoutST=Double.parseDouble(cfap.getRoundOffLevel2(cfap
				.getStringWithout_E(modalPremiumWithoutDiscount + PTR_ModalLoading)));
//		System.out.println("totalPremiumWithoutST "+totalPremiumWithoutST);
		
		/*double totalPremiumWithoutservicetax = Double.parseDouble(cfap.getRoundUp
				(cfap.getStringWithout_E(modalLoading)));*/
				double totalPremiumWithoutservicetax = Double.parseDouble(cfap.getRoundOffLevel2
				(cfap.getStringWithout_E(modalLoading)));
				
	//	System.out.println("totalPremiumWithoutservicetax " + totalPremiumWithoutservicetax);
		
		/*** modified by Akshaya on 20-MAY-16 start **/
		
	//  Added By Saurabh Jain on 14/05/2019 Start
		//17-01-2020
		boolean state =saralPensionBean.getState();
			
		double basicServiceTax =0;
		double SBCServiceTax =0;
		double KKCServiceTax =0;
		double kerlaServiceTax=0;
		double KeralaCessServiceTax=0;
		
		//added by sujata on 10-01-2020
//		if(state){
//			 basicServiceTax = SP_BusinessLogic.getServiceTax(modalLoading,saralPensionBean.getJkResident(),"basic");
//			 kerlaServiceTax = SP_BusinessLogic.getServiceTax(totalPremiumWithoutST,saralPensionBean.getJkResident(),"KERALA");
//			 KeralaCessServiceTax =kerlaServiceTax-basicServiceTax;
//		}else{
//			 basicServiceTax = SP_BusinessLogic.getServiceTax(totalPremiumWithoutST,saralPensionBean.getJkResident(),"basic");
//			 SBCServiceTax = SP_BusinessLogic.getServiceTax(totalPremiumWithoutST,saralPensionBean.getJkResident(),"SBC");
//			 KKCServiceTax = SP_BusinessLogic.getServiceTax(totalPremiumWithoutST,saralPensionBean.getJkResident(),"KKC");
//		}
		if(state){
			 basicServiceTax = SP_BusinessLogic.getServiceTax(Double.parseDouble(modalLoadingPrem),saralPensionBean.getJkResident(),saralPensionBean.getState(),"basic");
			 kerlaServiceTax = SP_BusinessLogic.getServiceTax(Double.parseDouble(modalLoadingPrem),saralPensionBean.getJkResident(),saralPensionBean.getState(),"KERALA");
			 KeralaCessServiceTax =SP_BusinessLogic.getServiceTax(Double.parseDouble(modalLoadingPrem),saralPensionBean.getJkResident(),saralPensionBean.getState(),"KKC");
		}else{
			 basicServiceTax = SP_BusinessLogic.getServiceTax(Double.parseDouble(modalLoadingPrem),saralPensionBean.getJkResident(),saralPensionBean.getState(),"basic");
			 SBCServiceTax = SP_BusinessLogic.getServiceTax(Double.parseDouble(modalLoadingPrem),saralPensionBean.getJkResident(),saralPensionBean.getState(),"SBC");
			 KKCServiceTax = SP_BusinessLogic.getServiceTax(Double.parseDouble(modalLoadingPrem),saralPensionBean.getJkResident(),saralPensionBean.getState(),"KKC");
		}
		
		double FYserviceTax = basicServiceTax+SBCServiceTax+KKCServiceTax+kerlaServiceTax;
//		System.out.println("FYserviceTax "+FYserviceTax);
		
//		premInstBasicFirstYear =cfap.getRoundUp(cfap.getStringWithout_E(Double.parseDouble(modalLoadingPrem) + (FYserviceTax)));
		premInstBasicFirstYear =cfap.getRoundOffLevel2(cfap.getStringWithout_E(Double.parseDouble(modalLoadingPrem) + (FYserviceTax)));
		
//		System.out.println("premInstBasicFirstYear "+premInstBasicFirstYear);
//		System.out.println("FYserviceTax "+FYserviceTax);
		
//		String premiumBasicRider =cfap.getRound(cfap.getStringWithout_E(PTR_ModalLoading));
		String premiumBasicRider =cfap.getRoundOffLevel2(cfap.getStringWithout_E(PTR_ModalLoading));
//		premiumSingleInstBasicRider =cfap.getRound(cfap.getStringWithout_E(Double.parseDouble(premiumBasicRider)));
		premiumSingleInstBasicRider =cfap.getRoundOffLevel2(cfap.getStringWithout_E(Double.parseDouble(premiumBasicRider)));
		
	//  Added By Saurabh Jain on 14/05/2019 End
		
	//  Added By Saurabh Jain on 18/01/2020 End
		
		/*double FYtotalPremiumWithST = Double.parseDouble(cfap.getRoundUp(cfap
				.getStringWithout_E(totalPremiumWithoutST)))
				+ Double.parseDouble(cfap.getRoundUp(cfap
						.getStringWithout_E(FYserviceTax)));*/
		
		double FYtotalPremiumWithST = Double.parseDouble(cfap.getRoundUp_Level2(cfap
				.getStringWithout_E(modalLoading)))
				+ Double.parseDouble(cfap.getRoundUp_Level2(premiumSingleInstBasicRider));
		
		/*double FYtotalPremiumWithST = Double.parseDouble(cfap.getRoundOffLevel2(cfap
				.getStringWithout_E(modalLoading)))
				+ Double.parseDouble(cfap.getRoundOffLevel2(premiumSingleInstBasicRider));*/
		
		
	//  Added By Saurabh Jain on 18/01/2020 End
		
		/************ Added By Tushar Kotian 10/8/2017 ******************/
		
		/************ Added By Tushar Kotian 10/8/2017 ******************/
		
	//  Added By Saurabh Jain on 14/05/2019 Start
		
		double basicServiceTaxSecondYear=0,SBCServiceTaxSecondYear=0,KKCServiceTaxSecondYear=0,SYserviceTax=0,SYtotalPremiumWithST = 0;
		double kerlaServiceTaxSecondYear=0;
		double KeralaCessServiceTaxSecondYear =0;
		if(!saralPensionBean.getPremiumFreq().equals("Single"))
		{
//			if(state){
//		 basicServiceTaxSecondYear = SP_BusinessLogic.getServiceTaxSecondYear(totalPremiumWithoutST,saralPensionBean.getJkResident(),"basic");
//		 kerlaServiceTaxSecondYear = SP_BusinessLogic.getServiceTaxSecondYear(totalPremiumWithoutST,saralPensionBean.getJkResident(),"KERALA");
//		 KeralaCessServiceTaxSecondYear =kerlaServiceTaxSecondYear-basicServiceTaxSecondYear;
			
			if(state){
				 basicServiceTaxSecondYear = SP_BusinessLogic.getServiceTaxSecondYear(Double.parseDouble(modalLoadingPrem),saralPensionBean.getJkResident(),saralPensionBean.getState(),"basic");
				 kerlaServiceTaxSecondYear = SP_BusinessLogic.getServiceTaxSecondYear(Double.parseDouble(modalLoadingPrem),saralPensionBean.getJkResident(),saralPensionBean.getState(),"KERALA");
				 KeralaCessServiceTaxSecondYear =SP_BusinessLogic.getServiceTaxSecondYear(Double.parseDouble(modalLoadingPrem),saralPensionBean.getJkResident(),saralPensionBean.getState(),"KKC");;
		
		
		 SYserviceTax = basicServiceTaxSecondYear+kerlaServiceTaxSecondYear;
//		 System.out.println("SYserviceTax " + SYserviceTax);
		
		/* SYtotalPremiumWithST = Double.parseDouble(cfap.getRoundUp(cfap
				.getStringWithout_E(Double.parseDouble(modalLoadingPrem))))
				+ Double.parseDouble(cfap.getRoundUp(cfap
						.getStringWithout_E(SYserviceTax)));*/
		 SYtotalPremiumWithST = Double.parseDouble(cfap.getRoundOffLevel2(cfap
					.getStringWithout_E(Double.parseDouble(modalLoadingPrem))))
					+ Double.parseDouble(cfap.getRoundOffLevel2(cfap
							.getStringWithout_E(SYserviceTax)));
			}else{
				basicServiceTaxSecondYear = SP_BusinessLogic.getServiceTaxSecondYear(Double.parseDouble(modalLoadingPrem),saralPensionBean.getJkResident(),saralPensionBean.getState(),"basic");
				 SBCServiceTaxSecondYear = SP_BusinessLogic.getServiceTaxSecondYear(Double.parseDouble(modalLoadingPrem),saralPensionBean.getJkResident(),saralPensionBean.getState(),"SBC");
				 KKCServiceTaxSecondYear = SP_BusinessLogic.getServiceTaxSecondYear(Double.parseDouble(modalLoadingPrem),saralPensionBean.getJkResident(),saralPensionBean.getState(),"KKC");
				
				
				 SYserviceTax = basicServiceTaxSecondYear+SBCServiceTaxSecondYear+KKCServiceTaxSecondYear;
				 
//				System.out.println("basicServiceTaxSecondYear " + basicServiceTaxSecondYear);
				
				 /*SYtotalPremiumWithST = Double.parseDouble(cfap.getRoundUp(cfap
						.getStringWithout_E(modalLoading)))
						+ Double.parseDouble(cfap.getRoundUp(cfap
								.getStringWithout_E(SYserviceTax)));*/
				 SYtotalPremiumWithST = Double.parseDouble(cfap.getRoundOffLevel2(cfap
							.getStringWithout_E(modalLoading)))
							+ Double.parseDouble(cfap.getRoundOffLevel2(cfap
									.getStringWithout_E(SYserviceTax)));
			}
		}
		
		
		if(saralPensionBean.getPremiumFreq().equals("Single"))
		{
			premInstBasicSecondYear = "0";
		}
		else
		{
//			premInstBasicSecondYear = cfap.getRoundUp(cfap.getStringWithout_E(Double.parseDouble(modalLoadingPrem)+SYserviceTax));
			premInstBasicSecondYear = cfap.getRoundOffLevel2(cfap.getStringWithout_E(Double.parseDouble(modalLoadingPrem)+SYserviceTax));
		}
		
		/************Rider Service*******************/
		double basicServiceTaxRider =0;
		double SBCServiceTaxRider =0;
		double KKCServiceTaxRider =0;
		double kerlaServiceTaxRider=0;
		double KeralaCessServiceTaxRider=0;
		if(state){
			basicServiceTaxRider = SP_BusinessLogic.getServiceTaxRider(Double.parseDouble(premiumSingleInstBasicRider),saralPensionBean.getJkResident(),saralPensionBean.getState(),"basic");
			kerlaServiceTaxRider = SP_BusinessLogic.getServiceTaxRider(Double.parseDouble(premiumSingleInstBasicRider),saralPensionBean.getJkResident(),saralPensionBean.getState(),"KERALA");
			KeralaCessServiceTaxRider = SP_BusinessLogic.getServiceTaxRider(Double.parseDouble(premiumSingleInstBasicRider),saralPensionBean.getJkResident(),saralPensionBean.getState(),"KKC");;
		}else{
			basicServiceTaxRider = SP_BusinessLogic.getServiceTaxRider(Double.parseDouble(premiumSingleInstBasicRider),saralPensionBean.getJkResident(),saralPensionBean.getState(),"basic");
			SBCServiceTaxRider = SP_BusinessLogic.getServiceTaxRider(Double.parseDouble(premiumSingleInstBasicRider),saralPensionBean.getJkResident(),saralPensionBean.getState(),"SBC");
			KKCServiceTaxRider = SP_BusinessLogic.getServiceTaxRider(Double.parseDouble(premiumSingleInstBasicRider),saralPensionBean.getJkResident(),saralPensionBean.getState(),"KKC");
		}
		String serviceTaxRiderFY =  cfap.getStringWithout_E(basicServiceTaxRider+SBCServiceTaxRider+KKCServiceTax+kerlaServiceTaxRider);
//		System.out.println("serviceTaxRider 1 "+serviceTaxRiderFY);
		
		/**************************rider 2nd year*************************/
		
		double basicServiceTaxRiderSecondYear =0;
		double SBCServiceTaxRiderSecondYear =0;
		double KKCServiceTaxRiderSecondYear =0;
		double kerlaServiceTaxRiderSecondYear=0;
		double KeralaCessServiceTaxRiderSecondYear=0;
		if(state){
			basicServiceTaxRiderSecondYear = SP_BusinessLogic.getServiceTaxRiderSecondYear(Double.parseDouble(premiumSingleInstBasicRider),saralPensionBean.getJkResident(),saralPensionBean.getState(),"basic");
			kerlaServiceTaxRiderSecondYear = SP_BusinessLogic.getServiceTaxRiderSecondYear(Double.parseDouble(premiumSingleInstBasicRider),saralPensionBean.getJkResident(),saralPensionBean.getState(),"KERALA");
			KeralaCessServiceTaxRiderSecondYear =SP_BusinessLogic.getServiceTaxRider(Double.parseDouble(premiumSingleInstBasicRider),saralPensionBean.getJkResident(),saralPensionBean.getState(),"KKC");;
		}else{
			basicServiceTaxRiderSecondYear = SP_BusinessLogic.getServiceTaxRiderSecondYear(Double.parseDouble(premiumSingleInstBasicRider),saralPensionBean.getJkResident(),saralPensionBean.getState(),"basic");
			SBCServiceTaxRiderSecondYear = SP_BusinessLogic.getServiceTaxRiderSecondYear(Double.parseDouble(premiumSingleInstBasicRider),saralPensionBean.getJkResident(),saralPensionBean.getState(),"SBC");
			KKCServiceTaxRiderSecondYear = SP_BusinessLogic.getServiceTaxRiderSecondYear(Double.parseDouble(premiumSingleInstBasicRider),saralPensionBean.getJkResident(),saralPensionBean.getState(),"KKC");
		}
		
		String serviceTaxRiderSecondYear =  cfap.getStringWithout_E(basicServiceTaxRiderSecondYear+SBCServiceTaxRiderSecondYear+KKCServiceTaxRiderSecondYear+kerlaServiceTaxRiderSecondYear);
	
		premInstFYRider = cfap.getRoundOffLevel2(cfap.getStringWithout_E(Double.parseDouble(premiumSingleInstBasicRider)+Double.parseDouble(serviceTaxRiderFY)));
//		System.out.println("premiumSingleInstBasicRider " + premiumSingleInstBasicRider);
//		System.out.println("serviceTaxRiderFY " + serviceTaxRiderFY);
//		System.out.println("premInstFYRider " + premInstFYRider);
		premInstSYRider = cfap.getRoundOffLevel2(cfap.getStringWithout_E(Double.parseDouble(premiumSingleInstBasicRider)+Double.parseDouble(serviceTaxRiderSecondYear)));
		
//		System.out.println("premInstSYRider "+premInstSYRider);
		
		
		
//		double FYtotalPremiumWithST = Double.parseDouble(cfap.getRoundOffLevel2New(cfap
//				.getStringWithout_E(totalPremiumWithoutST)))
//				+ Double.parseDouble(cfap.getRound(cfap
//						.getStringWithout_E(FYserviceTax)));
//		
		
		
//		System.out.println("totalPremiumWithoutST "+totalPremiumWithoutST);
		
		
//		String premiumSingleInstBasicWithST=cfap.getRound(cfap.getStringWithout_E(Double.parseDouble(premInstBasicFirstYear)+Double.parseDouble(premInstFYRider)));
		String premiumSingleInstBasicWithST=cfap.getRoundUp(cfap.getStringWithout_E(Double.parseDouble(premInstBasicFirstYear)+Double.parseDouble(premInstFYRider)));
//		premiumSingleInstBasicWithST= (cfap.getRoundOffLevel2(cfap.getStringWithout_E(modalLoading) + (FYserviceTax)) +
//				(cfap.getRoundOffLevel2(cfap.getStringWithout_E(PTR_ModalLoading)) + (Double.parseDouble(serviceTaxRiderFY))));
		
//		System.out.println("premInstBasicFirstYear " + premInstBasicFirstYear);
//		
//		System.out.println("premiumSingleInstBasicWithST " + premiumSingleInstBasicWithST);
		String premiumSingleInstBasicWithSTSecondYear=cfap.getStringWithout_E(Double.parseDouble(premInstBasicSecondYear)+Double.parseDouble(premInstSYRider));

		//	System.out.println("serviceTaxRiderSecondYear "+serviceTaxRiderSecondYear);
		
//		System.out.println("premInstBasicSecondYear "+premInstBasicSecondYear);

	//  Added By Saurabh Jain on 14/05/2019 End
		
//		double serviceTax=SP_BusinessLogic.getServiceTax(Double.parseDouble(cfap.getRoundUp(""+totalPremiumWithoutST)));
//		System.out.println("service tax "+serviceTax);
		
//		double totalPremiumWithST=Double.parseDouble(cfap.getRoundUp(""+totalPremiumWithoutST))+Double.parseDouble(cfap.getRoundUp(""+serviceTax));
//		System.out.println("totalPremiumWithST "+totalPremiumWithST);
		
		/*** modified by Akshaya on 20-MAY-16 end **/
		
		double guaranteenVestingBenefit=SP_BusinessLogic.getGuaranteedVestingBenefit(saralPensionBean.getSumAssured());
		
		//extra output
		
//		for(int j=1;j<=saralPensionBean.getPolicyTerm();j++)
//		{
//			
//			vestingBenefit1=SP_BusinessLogic.getVestingBenefit(j,_vestingBenefit1);
//			_vestingBenefit1=vestingBenefit1;
////			System.out.println("vestingBenefit "+vestingBenefit1);
//			
//			nonGuaranVestingBenefit_4Percent1=SP_BusinessLogic.getNonGuaranVestingBenefit("4%",SP_BusinessLogic.getbonusrate4(saralPensionBean.getPremiumFreq(), saralPensionBean.getPolicyTerm()), _vestingBenefit1, j, _nonGuaranVestingBenefit_4Percent1);
//			_nonGuaranVestingBenefit_4Percent1=nonGuaranVestingBenefit_4Percent1;
////			System.out.println("nonGuaranVestingBenefit_4Percent "+_nonGuaranVestingBenefit_4Percent1);
//			
//			nonGuaranVestingBenefit_4Percent2 =	_vestingBenefit1 + (SP_BusinessLogic.getNonGuarnteedDeathBenefit4per( year_F, saralPensionBean.getPolicyTerm(), saralPensionBean.getSumAssured(),saralPensionBean.getPremiumFreq()));
//
////					saralPensionBean.getPolicyTerm(), saralPensionBean.getSumAssured(), _vestingBenefit1, saralPensionBean.getPremiumFreq());
//			_nonGuaranVestingBenefit_4Percent2 =nonGuaranVestingBenefit_4Percent2;
//			System.out.println("nonGuaranVestingBenefit_4Percent2 "+_nonGuaranVestingBenefit_4Percent2);
//			
//			nonGuaranVestingBenefit_8Percent1=SP_BusinessLogic.getNonGuaranVestingBenefit("8%",SP_BusinessLogic.getbonusrate8(saralPensionBean.getPremiumFreq(), saralPensionBean.getPolicyTerm()), _vestingBenefit1, j, _nonGuaranVestingBenefit_8Percent1);
//			_nonGuaranVestingBenefit_8Percent1=nonGuaranVestingBenefit_8Percent1;
////			System.out.println("nonGuaranVestingBenefit_8Percent "+nonGuaranVestingBenefit_8Percent);
//			
//			nonGuaranVestingBenefit_8Percent2=SP_BusinessLogic.getTotalDeathBen8per(_year_F, saralPensionBean.getPolicyTerm(), saralPensionBean.getSumAssured(), vestingBenefit1, saralPensionBean.getPremiumFreq());
//			_nonGuaranVestingBenefit_8Percent2=nonGuaranVestingBenefit_8Percent2;
////			System.out.println("nonGuaranVestingBenefit_8Percent2 "+nonGuaranVestingBenefit_8Percent2);
//		}
		double val=0,a=0,val1=0;
		int rowNumber = 0;
		for (int j = 1; j <= saralPensionBean.getPolicyTerm(); j++) {
			rowNumber++;

			year_F = rowNumber;
			_year_F = year_F;
//			System.out.println("1. year_F " + year_F);
			bussIll.append("<policyYr" + _year_F + ">" + _year_F + "</policyYr"
					+ _year_F + ">");

			bussIll.append("<age" + _year_F + ">"
					+ (saralPensionBean.getAge() + j - 1) + "</age" + _year_F
					+ ">");
			
			//17-01-2020
			/*totalBasePremiumPaid = SP_BusinessLogic.getTotalBasePremPaid(Double
					.parseDouble(cfap.getRoundOffLevel2(cfap
							.getStringWithout_E(totalPremiumWithoutservicetax))), year_F);*/
			totalBasePremiumPaid = SP_BusinessLogic.getTotalBasePremPaid(Double
					.parseDouble(cfap.getRoundUp(cfap
							.getStringWithout_E(totalPremiumWithoutservicetax))), year_F);
			_totalBasePremiumPaid = totalBasePremiumPaid;
//			System.out.println("2. _totalBasePremiumPaid "
//					+ _totalBasePremiumPaid);
			
			sumTotalBasePrem = sumTotalBasePrem + totalBasePremiumPaid;
			
			bussIll.append("<totalBasePremPaid" + _year_F + ">"
					+ Math.round(_totalBasePremiumPaid) + "</totalBasePremPaid"
					+ _year_F + ">");
			
			//17-01-2020
			String AnnulizedPremium = cfap.getRoundUp(cfap.getStringWithout_E(SP_BusinessLogic
					.getannulizedPremFinal(_year_F, saralPensionBean.getPolicyTerm(),saralPensionBean.getPremiumFreq(),annualPremWithDiscount)));
			
			if(year_F==1)
			{
				double annualizedPrem=Double.parseDouble(AnnulizedPremium);
			}
			
			bussIll.append("<AnnulizedPremium" + _year_F + ">" + cfap.getRound(cfap
					.getStringWithout_E(Double.parseDouble(AnnulizedPremium)))
					+ "</AnnulizedPremium" + _year_F + ">");
			
			
			
//			String GuaranteedAddition = cfap.getRound(cfap.getStringWithout_E(SP_BusinessLogic.getGuaranteedAddition(_year_F, saralPensionBean.getPTR_sumAssured(),sumGuaranteedAddition,saralPensionBean.getPolicyTerm())));
			String GuaranteedAddition = cfap.getRound(cfap.getStringWithout_E(SP_BusinessLogic.getGuaranteedAddition(_year_F, saralPensionBean.getSumAssured(),sumGuaranteedAddition,saralPensionBean.getPolicyTerm())));
			double GuranAdd=0;
			
//			System.out.println("sumGuaranteedAddition " + sumGuaranteedAddition);

			/*double val=0,a=0,val1=0;*/
			
			double sumassured=saralPensionBean.getSumAssured();
			if(year_F == 1)
			{
		    val= (sumassured * 0.025);
		    val1 =val;
		    bussIll.append("<Guaranteedadd" + _year_F + ">" + (val) + "</Guaranteedadd" + _year_F + ">");
			}
			else if(year_F <=3)
			{
			val = (val1 + sumassured * 0.025);
			val1=val;
			bussIll.append("<Guaranteedadd" + _year_F + ">" + (val) + "</Guaranteedadd" + _year_F + ">");
				
			}else if(year_F <=5){
				
				val = (val1 + sumassured * 0.0275);
				val1=val;
				bussIll.append("<Guaranteedadd" + _year_F + ">" + (val) + "</Guaranteedadd" + _year_F + ">");
				
			}else if(year_F <= saralPensionBean.getPolicyTerm()){
				
				bussIll.append("<Guaranteedadd" + _year_F + ">" + (val) + "</Guaranteedadd" + _year_F + ">");
			}
//			bussIll.append("<Guaranteedadd" + _year_F + ">" + Double.parseDouble(GuaranteedAddition) + "</Guaranteedadd" + _year_F + ">");
			
//			String SurvivalBenefit =  cfap.getRound(cfap.getStringWithout_E(SP_BusinessLogic.getSurvivalBenefit(_year_F, saralPensionBean.getPolicyTerm(), saralPensionBean.getSumAssured())));
			
			String SurvivalBenefit = "0";
			
			bussIll.append("<SurvivalBenefit" + _year_F + ">" + (cfap
					.getStringWithout_E(Double.parseDouble(SurvivalBenefit)))
					+ "</SurvivalBenefit" + _year_F + ">");
			
			cummulativTotalBasePremiumPaid += totalBasePremiumPaid;
			_cummulativTotalBasePremiumPaid = cummulativTotalBasePremiumPaid;
//			System.out.println("3. _cummulativTotalBasePremiumPaid "
//					+ _cummulativTotalBasePremiumPaid);
			bussIll.append("<cummTotBasePrem" + _year_F + ">"
					+ Math.round(_cummulativTotalBasePremiumPaid) + "</cummTotBasePrem"
					+ _year_F + ">");

			vestingBenefit = SP_BusinessLogic.getVestingBenefit(j,
					_vestingBenefit);
			
			vestingBenefit11 = SP_BusinessLogic.getVestingBenefit11(j,
					_vestingBenefit);
			
			_vestingBenefit = vestingBenefit;
//			System.out.println("4. vestingBenefit " + vestingBenefit);
			bussIll.append("<vestingBenefit" + _year_F + ">" + vestingBenefit11
					+ "</vestingBenefit" + _year_F + ">");

			nonGuaranVestingBenefit_4Percent = SP_BusinessLogic
					.getNonGuaranVestingBenefit("4%",SP_BusinessLogic.getbonusrate4(saralPensionBean.getPremiumFreq(), saralPensionBean.getPolicyTerm()), val, j,
							_nonGuaranVestingBenefit_4Percent);
			_nonGuaranVestingBenefit_4Percent = nonGuaranVestingBenefit_4Percent;
//			System.out.println("5. nonGuaranVestingBenefit_4Percent "
//					+ nonGuaranVestingBenefit_4Percent);
			bussIll.append("<nonGuaranVestingBenefit_4Percent" + _year_F + ">"
					+ _nonGuaranVestingBenefit_4Percent
					+ "</nonGuaranVestingBenefit_4Percent" + _year_F + ">");

			nonGuaranVestingBenefit_8Percent = SP_BusinessLogic
					.getNonGuaranVestingBenefit("8%",SP_BusinessLogic.getbonusrate8(saralPensionBean.getPremiumFreq(), saralPensionBean.getPolicyTerm()), val, j,
							_nonGuaranVestingBenefit_8Percent);
			_nonGuaranVestingBenefit_8Percent = nonGuaranVestingBenefit_8Percent;
//			System.out.println("6. nonGuaranVestingBenefit_8Percent "
//					+ nonGuaranVestingBenefit_8Percent);
			bussIll.append("<nonGuaranVestingBenefit_8Percent" + _year_F + ">"
					+ _nonGuaranVestingBenefit_8Percent
					+ "</nonGuaranVestingBenefit_8Percent" + _year_F + ">");

			vestingBenefit_4_pr = Math.max(vestingBenefit,
					nonGuaranVestingBenefit_4Percent);
			_vestingBenefit_4_pr = vestingBenefit_4_pr;
//			System.out.println("7. vestingBenefit_4_pr " + vestingBenefit_4_pr);
			bussIll.append("<vestingBenefit_4_pr" + _year_F + ">"
					+ _vestingBenefit_4_pr + "</vestingBenefit_4_pr" + _year_F
					+ ">");
			
//			System.out.println("_vestingBenefit_4_pr " + _vestingBenefit_4_pr);
			vestingBenefit_8_pr = Math.max(vestingBenefit,
					nonGuaranVestingBenefit_8Percent);
			_vestingBenefit_8_pr = vestingBenefit_8_pr;
//			System.out.println("8. vestingBenefit_8_pr " + vestingBenefit_8_pr);
			bussIll.append("<vestingBenefit_8_pr" + _year_F + ">"
					+ _vestingBenefit_8_pr + "</vestingBenefit_8_pr" + _year_F
					+ ">");

			cal_of_colm_k = SP_BusinessLogic.getValueOfK(_totalBasePremiumPaid,
					_cal_of_colm_k);
			_cal_of_colm_k = cal_of_colm_k;
//			System.out.println("9. K " + _cal_of_colm_k);

			guaranDeathBenfit = SP_BusinessLogic.getGuarnteedDeathBenefit(
					year_F, cummulativTotalBasePremiumPaid, vestingBenefit,
					_cal_of_colm_k);
			_guaranDeathBenfit = guaranDeathBenfit;
//			System.out.println("10. guaranDeathBenfit " + guaranDeathBenfit);
			/*bussIll.append("<guaranDeathBenfit" + _year_F + ">"
					+ Math.round(_guaranDeathBenfit) + "</guaranDeathBenfit"
					+ _year_F + ">");*/

//			nonGuaranDeathBenfit_4Percent = SP_BusinessLogic
//					.getGuarnteedDeathBenefit(year_F,
//							cummulativTotalBasePremiumPaid,
//							nonGuaranVestingBenefit_4Percent, _cal_of_colm_k);
//			_nonGuaranDeathBenfit_4Percent = nonGuaranDeathBenfit_4Percent;
			
			nonGuaranDeathBenfit_4Percent = SP_BusinessLogic.getNonGuarnteedDeathBenefit4per(_year_F, saralPensionBean.getPolicyTerm(), saralPensionBean.getSumAssured(), saralPensionBean.getPremiumFreq());
			
			
//			System.out.println("11. nonGuaranDeathBenfit_4Percent "
//					+ nonGuaranDeathBenfit_4Percent);
			bussIll.append("<nonGuaranDeathBenfit_4Percent" + _year_F + ">"
					+ Math.round(nonGuaranDeathBenfit_4Percent)
					+ "</nonGuaranDeathBenfit_4Percent" + _year_F + ">");
			
			
			bussIll.append("<cashBonus" + _year_F + ">" + 0 + "</cashBonus" + _year_F + ">");
//			System.out.println("nonGuaranDeathBenfit_4Percent "+nonGuaranDeathBenfit_4Percent);

//			nonGuaranDeathBenfit_8Percent = SP_BusinessLogic
//					.getGuarnteedDeathBenefit(year_F,
//							cummulativTotalBasePremiumPaid,
//							nonGuaranVestingBenefit_8Percent, _cal_of_colm_k);
//			_nonGuaranDeathBenfit_8Percent = nonGuaranDeathBenfit_8Percent;
			
			nonGuaranDeathBenfit_8Percent = SP_BusinessLogic.getNonGuarnteedDeathBenefit8per(_year_F, saralPensionBean.getPolicyTerm(), saralPensionBean.getSumAssured(), saralPensionBean.getPremiumFreq());
//			System.out.println("12. nonGuaranDeathBenfit_4Percent "
//					+ nonGuaranDeathBenfit_8Percent);
			bussIll.append("<nonGuaranDeathBenfit_8Percent" + _year_F + ">"
					+ Math.round(nonGuaranDeathBenfit_8Percent)
					+ "</nonGuaranDeathBenfit_8Percent" + _year_F + ">");

			deathBenefit_4Percent = Math.max(guaranDeathBenfit,
					nonGuaranDeathBenfit_4Percent);
			_deathBenefit_4Percent = deathBenefit_4Percent;
//			System.out.println("13. deathBenefit_4Percent "
//					+ deathBenefit_4Percent);
			bussIll.append("<deathBenefit_4Percent" + _year_F + ">"
					+ Math.round(_deathBenefit_4Percent)
					+ "</deathBenefit_4Percent" + _year_F + ">");

			deathBenefit_8Percent = Math.max(guaranDeathBenfit,
					nonGuaranDeathBenfit_8Percent);
			_deathBenefit_8Percent = deathBenefit_8Percent;
//			System.out.println("14. deathBenefit_8Percent "
//					+ deathBenefit_8Percent);
			bussIll.append("<deathBenefit_8Percent" + _year_F + ">"
					+ Math.round(_deathBenefit_8Percent)
					+ "</deathBenefit_8Percent" + _year_F + ">");

			PUV_4Percent = SP_BusinessLogic.getPUV(year_F,
					nonGuaranVestingBenefit_4Percent);
			_PUV_4Percent = PUV_4Percent;
//			System.out.println("15. PUV_4Percent " + PUV_4Percent);

			PUV_8Percent = SP_BusinessLogic.getPUV(year_F,
					nonGuaranVestingBenefit_8Percent);
			_PUV_8Percent = PUV_8Percent;
//			System.out.println("16. PUV_8Percent " + PUV_8Percent);

			
			//17-01-2020
			nonGuaranSurrBen_4Pr = SP_BusinessLogic
					.getNonGuarnteedSurrenderBenefit(year_F,saralPensionBean.getPolicyTerm(),saralPensionBean.getSumAssured(),_vestingBenefit_4_pr);
			_nonGuaranSurrBen_4Pr = nonGuaranSurrBen_4Pr;
			/*System.out.println("year_F"+year_F);
			System.out.println("17. nonGuaranSurrBen_4Pr "
					+ nonGuaranSurrBen_4Pr);*/
			
			bussIll.append("<nonGuaranSurrBen_4Pr" + _year_F + ">"
					+ Math.round(_nonGuaranSurrBen_4Pr)
					+ "</nonGuaranSurrBen_4Pr" + _year_F + ">");

			
			//17-01-2020
			nonGuaranSurrBen_8Pr = SP_BusinessLogic
					.getNonGuarnteedSurrenderBenefit8per(year_F,saralPensionBean.getPolicyTerm(),saralPensionBean.getSumAssured(),_vestingBenefit_4_pr);
			_nonGuaranSurrBen_8Pr = nonGuaranSurrBen_8Pr;
//			System.out.println("18. nonGuaranSurrBen_8Pr "
//					+ nonGuaranSurrBen_8Pr);
			bussIll.append("<nonGuaranSurrBen_8Pr" + _year_F + ">"
					+ Math.round(_nonGuaranSurrBen_8Pr)
					+ "</nonGuaranSurrBen_8Pr" + _year_F + ">");

			//17-01-2020
			guaranSurrBenefit = SP_BusinessLogic.getGuarnteedSurrenderBenefit(
					year_F, cummulativTotalBasePremiumPaid, vestingBenefit,sumTotalBasePrem);
			_guaranSurrBenefit = guaranSurrBenefit;
//			System.out.println("18. guaranSurrBenefit " + _guaranSurrBenefit);
			bussIll.append("<guaranSurrBenefit" + _year_F + ">"
					+ cfap.getRoundUp(cfap.getStringWithout_E(_guaranSurrBenefit)) + "</guaranSurrBenefit"
					+ _year_F + ">");

			/*surrenderBenefit_4Pr = Math.max(guaranSurrBenefit,
					nonGuaranSurrBen_4Pr);*/
			
			surrenderBenefit_4Pr = SP_BusinessLogic.getSurrenderBenefit_4Pr(year_F,_nonGuaranVestingBenefit_4Percent);
			
			_surrenderBenefit_4Pr = surrenderBenefit_4Pr;
//			System.out.println("19. surrenderBenefit_4Pr "
//					+ surrenderBenefit_4Pr);
			bussIll.append("<surrenderBenefit_4Pr" + _year_F + ">"
					+ cfap.getRoundUp(cfap.getStringWithout_E(_surrenderBenefit_4Pr))
					+ "</surrenderBenefit_4Pr" + _year_F + ">");

			/*surrenderBenefit_8Pr = Math.max(guaranSurrBenefit,
					nonGuaranSurrBen_8Pr);*/
			
			surrenderBenefit_8Pr = SP_BusinessLogic.getSurrenderBenefit_8Pr(year_F,_nonGuaranVestingBenefit_8Percent);
					
			_surrenderBenefit_8Pr = surrenderBenefit_8Pr;
			
			_surrenderBenefit_8Pr1 = Double.parseDouble(cfap.getRoundOffLevel2(cfap.getStringWithout_E
                    (_surrenderBenefit_8Pr)));
			System.out.println("20. surrenderBenefit_8Pr "
					+ (_surrenderBenefit_8Pr1));
//			System.out.println("20. surrenderBenefit_8Pr "
//					+ surrenderBenefit_8Pr);
			bussIll.append("<surrenderBenefit_8Pr" + _year_F + ">"
					+ cfap.getRoundUp(cfap.getStringWithout_E(_surrenderBenefit_8Pr1))
					+ "</surrenderBenefit_8Pr" + _year_F + ">");
			
			//17-01-2020
			getGuarDeathBenForMat = (SP_BusinessLogic.getGuarDeathBenForMat(_year_F, saralPensionBean.getPolicyTerm(), _totalBasePremiumPaid,getGuarDeathBenForMat));
//			_getGuarDeathBenForMat =  cfap.getRound(cfap.getStringWithout_E(getGuarDeathBenForMat));
			_getGuarDeathBenForMat =  cfap.getRoundOffLevel2(cfap.getStringWithout_E(getGuarDeathBenForMat));
//			System.out.println("getGuarDeathBenForMat " + _getGuarDeathBenForMat);
			
			//17-01-2020
			totalMaturity4per = SP_BusinessLogic.getTotalMaturityBenefit4per(_year_F, 
					saralPensionBean.getPolicyTerm(), saralPensionBean.getSumAssured(), _nonGuaranVestingBenefit_4Percent,Double.parseDouble(_getGuarDeathBenForMat), saralPensionBean.getPremiumFreq());
			
			guaranDeathBenfit11 = SP_BusinessLogic.getGuarnteedDeathBenefit11(
					year_F, cummulativTotalBasePremiumPaid, val,
					_cal_of_colm_k,_getGuarDeathBenForMat);
			_guaranDeathBenfit11 = guaranDeathBenfit11;
//			System.out.println("10. guaranDeathBenfit " + guaranDeathBenfit);
			bussIll.append("<guaranDeathBenfit" + _year_F + ">"
					+ Math.round(_guaranDeathBenfit11) + "</guaranDeathBenfit"
					+ _year_F + ">");
			
			bussIll.append("<totalMaturityBen4per" + _year_F + ">"
					+ Math.round(totalMaturity4per)
					+ "</totalMaturityBen4per" + _year_F + ">");
			
			totalMaturity8per = SP_BusinessLogic.getTotalMaturityBenefit8per(_year_F, 
					saralPensionBean.getPolicyTerm(), saralPensionBean.getSumAssured(), _nonGuaranVestingBenefit_8Percent,Double.parseDouble(_getGuarDeathBenForMat), saralPensionBean.getPremiumFreq());
			
			bussIll.append("<totalMaturityBen8per" + _year_F + ">"
					+ Math.round(totalMaturity8per)
					+ "</totalMaturityBen8per" + _year_F + ">");

			totDeathBen4per = SP_BusinessLogic.getTotalDeathBen4per(_year_F, saralPensionBean.getPolicyTerm(), Double.parseDouble(_getGuarDeathBenForMat), _nonGuaranVestingBenefit_4Percent,sumTotalBasePrem);
			
			bussIll.append("<totDeathBen4per" + _year_F + ">"
					+ (cfap.getRoundUp(cfap.getStringWithout_E(totDeathBen4per)))
					+ "</totDeathBen4per" + _year_F + ">");
			
			totDeathBen8per = SP_BusinessLogic.getTotalDeathBen8per(_year_F, saralPensionBean.getPolicyTerm(),  Double.parseDouble(_getGuarDeathBenForMat), _nonGuaranVestingBenefit_8Percent,sumTotalBasePrem);

			bussIll.append("<totDeathBen8per" + _year_F + ">"
					+ (cfap.getRoundUp(cfap.getStringWithout_E(totDeathBen8per)))
					+ "</totDeathBen8per" + _year_F + ">");
			

			if (maxVestingBenefit_4Pr < vestingBenefit_4_pr)
				maxVestingBenefit_4Pr = vestingBenefit_4_pr;
			if (maxVestingBenefit_8Pr < vestingBenefit_8_pr)
				maxVestingBenefit_8Pr = vestingBenefit_8_pr;
			if(maxVestingBenefitMin < vestingBenefit)
				maxVestingBenefitMin = vestingBenefit;
		}
		
		annuityAmount_4Pr = SP_BusinessLogic.getAnnuityAmount(totalMaturity4per);
		_annuityAmount_4Pr = annuityAmount_4Pr;
//		System.out.println(" annuityAmount_4Pr " + _annuityAmount_4Pr);

		annuityAmount_8Pr = SP_BusinessLogic.getAnnuityAmount(totalMaturity8per);
		_annuityAmount_8Pr = annuityAmount_8Pr;
//		System.out.println(" annuityAmount_8Pr " + _annuityAmount_8Pr);
		
		annuityAmount_MiniBen = SP_BusinessLogic.getAnnuityAmount(vestingBenefit11);
		_annuityAmount_Min = annuityAmount_MiniBen;
//		System.out.println("_annuityAmount_Min " + _annuityAmount_Min);
		
		String valPremiumError=valBasicPremium(modalLoading,saralPensionBean.getPremiumFreq());
		String valRiderPremiumError=valRiderPremium(basicPremium,PTR_Premium );
		
		//added by sujata on 13-01-2020
		for(int j=1;j<=saralPensionBean.getPolicyTerm();j++)
		{
			vestingBenefit1=SP_BusinessLogic.getVestingBenefit(j,_vestingBenefit1);
			_vestingBenefit1=vestingBenefit1;
//			System.out.println("vestingBenefit "+vestingBenefit1);
			
			nonGuaranVestingBenefit_4Percent1=SP_BusinessLogic.getNonGuaranVestingBenefit("4%",SP_BusinessLogic.getbonusrate4(saralPensionBean.getPremiumFreq(), saralPensionBean.getPolicyTerm()), _vestingBenefit1, j, _nonGuaranVestingBenefit_4Percent1);
			_nonGuaranVestingBenefit_4Percent1=nonGuaranVestingBenefit_4Percent1;
//			System.out.println("nonGuaranVestingBenefit_4Percent "+_nonGuaranVestingBenefit_4Percent1);
			
			nonGuaranVestingBenefit_4Percent2 =	_vestingBenefit1 + (SP_BusinessLogic.getNonGuarnteedDeathBenefit4per( year_F, saralPensionBean.getPolicyTerm(), saralPensionBean.getSumAssured(),saralPensionBean.getPremiumFreq()));

//			saralPensionBean.getPolicyTerm(), saralPensionBean.getSumAssured(), _vestingBenefit1, saralPensionBean.getPremiumFreq());
			_nonGuaranVestingBenefit_4Percent2 =nonGuaranVestingBenefit_4Percent2;
//			System.out.println("nonGuaranVestingBenefit_4Percent2 "+_nonGuaranVestingBenefit_4Percent2);
			
			nonGuaranVestingBenefit_8Percent1=SP_BusinessLogic.getNonGuaranVestingBenefit("8%",SP_BusinessLogic.getbonusrate8(saralPensionBean.getPremiumFreq(), saralPensionBean.getPolicyTerm()), _vestingBenefit1, j, _nonGuaranVestingBenefit_8Percent1);
			_nonGuaranVestingBenefit_8Percent1=nonGuaranVestingBenefit_8Percent1;
//			System.out.println("nonGuaranVestingBenefit_8Percent "+nonGuaranVestingBenefit_8Percent);
			// double _getGuarDeathBenForMat ,double _nonGuaranVestingBenefit_8Percent, double sumTotalBasePrem
			nonGuaranVestingBenefit_8Percent2=SP_BusinessLogic.getTotalDeathBen8per(_year_F, saralPensionBean.getPolicyTerm(),Double.parseDouble(_getGuarDeathBenForMat), _nonGuaranVestingBenefit_8Percent, sumTotalBasePrem);
			_nonGuaranVestingBenefit_8Percent2=nonGuaranVestingBenefit_8Percent2;
//			System.out.println("nonGuaranVestingBenefit_8Percent2 "+_nonGuaranVestingBenefit_8Percent2);
			
//			getGuarDeathBenForMat = getGuarDeathBenForMat + (SP_BusinessLogic.getGuarDeathBenForMat(_year_F, saralPensionBean.getPolicyTerm(), _totalBasePremiumPaid));
//			_getGuarDeathBenForMat =  cfap.getRound(cfap.getStringWithout_E(getGuarDeathBenForMat));
//			System.out.println("getGuarDeathBenForMat " + _getGuarDeathBenForMat);
		}
		
		/************************************* output Starts here *******************************************************/
		
		if (saralPensionBean.getStaffDisc()) {
			staffStatus = "sbi";
			// disc_Basic_SelFreq
		} else
			staffStatus = "none";

		discountPercentage = SP_BusinessLogic.getStaffRebate();
		
		retVal.append("<?xml version='1.0' encoding='utf-8' ?><saralPension>");
		if(valPremiumError.equals("") && valRiderPremiumError.equals(""))
		{
			retVal.append("<intallmentPremWithoutST>"+cfap.getRoundUp(cfap.getStringWithout_E(totalPremiumWithoutST))+"</intallmentPremWithoutST>");
			
			retVal.append("<installmntPrem>"+cfap.getRoundUp(cfap.getStringWithout_E(totalPremiumWithoutST))+"</installmntPrem>");// Parivatran
			
			retVal.append("<basePremExclST>"+cfap.getRoundOffLevel2(cfap.getStringWithout_E(modalLoading))+"</basePremExclST>");
			
			retVal.append("<basePremExcludngST>"+cfap.getRoundOffLevel2(cfap.getStringWithout_E(modalLoading))+"</basePremExcludngST>");//Parivartan

			retVal.append("<serviceTax>"+cfap.getRoundUp(cfap.getStringWithout_E(FYserviceTax))+"</serviceTax>");
			
			retVal.append("<servceTax>"+cfap.getRoundUp(cfap.getStringWithout_E(FYserviceTax))+"</servceTax>");// Parivatran

			retVal.append("<intallmentPremWithST>"+cfap.getRoundUp(cfap.getStringWithout_E(FYtotalPremiumWithST))+"</intallmentPremWithST>");		
			
			retVal.append("<instalmntPremWTServcTax>"+cfap.getStringWithout_E(Double.parseDouble(premiumSingleInstBasicWithST))+"</instalmntPremWTServcTax>");		
			
			retVal.append("<VestingMinAssBen>"+cfap.getStringWithout_E(vestingBenefit11)+"</VestingMinAssBen>");
			
//			retVal.append("<guarnVestngBen>"+cfap.getStringWithout_E(guaranteenVestingBenefit)+"</guarnVestngBen>");//Parivartan
			
			ServiceTaxRiderBasic = cfap.getRoundUp(cfap.getStringWithout_E(SP_BusinessLogic.getServiceTaxRiderBasic("Basic",saralPensionBean.getPTR_Status(),PTR_ModalPremiumWithoutDiscount)));
			retVal.append("<ServiceTaxRiderBasic>"
					+ (ServiceTaxRiderBasic)
					+ "</ServiceTaxRiderBasic>");
			

			if(saralPensionBean.getPTR_Status())
			{
				retVal.append("<riderPremExclST>"+cfap.getRoundUp_Level2(cfap.getStringWithout_E(PTR_ModalLoading))+"</riderPremExclST>");
				retVal.append("<riderPrem>"+cfap.getRoundUp_Level2(cfap.getStringWithout_E(PTR_ModalLoading))+"</riderPrem>"); //Parivartan
				
			}
			else
				
				retVal.append("<riderPremExclST>0</riderPremExclST>");
			retVal.append("<riderPrem>0</riderPrem>"); //Parivartan
			//retVal.append("<nonGuaranVestingBen4>"+cfap.getStringWithout_E(nonGuaranVestingBenefit_4Percent1)+"</nonGuaranVestingBen4>");
			retVal.append("<FundValueAtVesting4>"+cfap.getStringWithout_E(totalMaturity4per)+"</FundValueAtVesting4>");
	//		System.out.println("nonGuaranVestingBen4 " + nonGuaranVestingBenefit_4Percent2 );
			retVal.append("<FundValueAtVesting8>"+cfap.getStringWithout_E(totalMaturity8per)+"</FundValueAtVesting8>");
			
			retVal.append("<errCode>0</errCode>");
			retVal.append("<staffStatus>" + staffStatus + "</staffStatus>");
			retVal.append("<staffRebate>"
					+ cfap.getStringWithout_E(discountPercentage)
					+ "</staffRebate>");
			retVal.append("<InstmntPrem>"
					+ cfap.getRoundUp(cfap
							.getStringWithout_E(totalPremiumWithoutDiscount))
					+ "</InstmntPrem>");// Parivatran
			retVal.append("<basicPremWithoutDisc>"
					+ cfap.getRoundOffLevel2(cfap
							.getStringWithout_E(modalPremiumWithoutDiscount))
					+ "</basicPremWithoutDisc>");
			retVal.append("<basicPremWithoutDiscSA>"
					+ cfap.getRoundOffLevel2(cfap
							.getStringWithout_E(basicPremium))
					+ "</basicPremWithoutDiscSA>");
			retVal.append("<premPTRWithoutDisc>"
					+ cfap.getRoundOffLevel2(cfap
							.getStringWithout_E(PTR_ModalPremiumWithoutDiscount))
							+ "</premPTRWithoutDisc>");
			//added by sujata on 10-01-2020
			retVal.append("<premBaiscRider>"
					+ cfap.getRoundUp(cfap
							.getStringWithout_E(PTR_ModalPremiumWithoutDiscount))			
					+ "</premBaiscRider>");
			
			
			retVal.append("<premiumSingleInstBasicRider>" + premiumSingleInstBasicRider + "</premiumSingleInstBasicRider>");
			retVal.append("<premInstBasicFirstYear>" + premInstBasicFirstYear + "</premInstBasicFirstYear>");
			retVal.append("<premInstBasicSecondYear>" + premInstBasicSecondYear + "</premInstBasicSecondYear>");
			retVal.append("<premInstFYRider>" + premInstFYRider + "</premInstFYRider>");
			retVal.append("<premInstSYRider>" + premInstSYRider + "</premInstSYRider>");
			
			//end
			
			retVal.append("<premPTRWithoutDiscSA>"
					+ cfap.getRoundOffLevel2(cfap
							.getStringWithout_E(PTR_Premium))
					+ "</premPTRWithoutDiscSA>");
			retVal.append("<SYservceTax>"
					+ Math.round(SYserviceTax)
					+ "</SYservceTax>");
			retVal.append("<SYinstalmntPremWTServcTax>"
					+ cfap.getRoundUp(cfap
							.getStringWithout_E(Double.parseDouble(premiumSingleInstBasicWithSTSecondYear)))
					+ "</SYinstalmntPremWTServcTax>");
			
			retVal.append("<vestingAge>"
					+ (saralPensionBean.getAge() + saralPensionBean
							.getPolicyTerm()) + "</vestingAge>");
			
			retVal.append("<annuityPayout_4_Pr>"
					+ cfap.getStringWithout_E(annuityAmount_4Pr)
					+ "</annuityPayout_4_Pr>");
			retVal.append("<annuityPayout_8_Pr>"
					+ cfap.getStringWithout_E(annuityAmount_8Pr)
					+ "</annuityPayout_8_Pr>");
			//added by sujata on 13-01-2020
			retVal.append("<annuityAmount_MiniBen>"
					+ cfap.getStringWithout_E(annuityAmount_MiniBen)
					+ "</annuityAmount_MiniBen>");
			
			retVal.append("<premiumRate>"
					+ SP_BusinessLogic.getPremiumRate() + "</premiumRate>");
			retVal.append("<basicServiceTax>"  + cfap.getStringWithout_E(basicServiceTax)  + "</basicServiceTax>" +
    		"<SBCServiceTax>"  + cfap.getStringWithout_E(SBCServiceTax)  + "</SBCServiceTax>" +
    		"<KKCServiceTax>"  + cfap.getStringWithout_E(KKCServiceTax)  + "</KKCServiceTax>"+
					"<basicServiceTaxSecondYear>"  + cfap.getStringWithout_E(basicServiceTaxSecondYear)  + "</basicServiceTaxSecondYear>" +
    		"<SBCServiceTaxSecondYear>"  + cfap.getStringWithout_E(SBCServiceTaxSecondYear)  + "</SBCServiceTaxSecondYear>" +
    		"<KKCServiceTaxSecondYear>"  + cfap.getStringWithout_E(KKCServiceTaxSecondYear)  + "</KKCServiceTaxSecondYear>" +
    		"<KeralaCessServiceTax>"+cfap.getStringWithout_E(KeralaCessServiceTax)+"</KeralaCessServiceTax>"  +
    		"<KeralaCessServiceTaxSecondYear>"+cfap.getStringWithout_E(KeralaCessServiceTaxSecondYear)+"</KeralaCessServiceTaxSecondYear>"
					/*** modified by Akshaya on 20-MAY-16 end **/
    		
					+ bussIll.toString());
			//added by sujata on 08-01-2020	
		}
		else
		{
			retVal.append("<errCode>"+1 +"</errCode>");
			if(!valPremiumError.equals(""))
			{retVal.append("<minPremError>"+valPremiumError+"</minPremError>");}
			if(!valRiderPremiumError.equals(""))
			{retVal.append("<minRiderPremError>"+valRiderPremiumError+"</minRiderPremError>");}
		}
		retVal.append("</saralPension>");
		/*********************************** output Ends here ************************************************/
		
		return retVal.toString();
	}
	public String valBasicPremium(double modalLoading,String premiumFreq)
	{
		String error="";
		if(premiumFreq.equals("Yearly") && modalLoading < 7500)
		{
			error="Minimum Premium for Yearly mode under this product is Rs.7,500";
		}
		else if(premiumFreq.equals("Half Yearly") && modalLoading < 3800)
		{
			error="Minimum Premium for Half-Yearly mode under this product is Rs.3,800";
		}
		else if(premiumFreq.equals("Monthly") && modalLoading < 700)
		{
			error="Minimum Premium for Monthly mode under this product is Rs.700";
			
		}
		return error;
	}
	
	public String valRiderPremium(double premBasic,double sumOfRiders)
	{
		String error="";
		/*if((premBasic * 0.15) < sumOfRiders)
		{
			error="Total of Rider Premium should not be greater than 15% of the Base Premium";
			
		}*/
		
		if((premBasic * 0.30) < sumOfRiders)
		{
			error="Total of Rider Premium should not be greater than 30% of the Base Premium";
			
		}
		return error;
	}
}
