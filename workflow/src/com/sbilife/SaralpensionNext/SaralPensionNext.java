package com.sbilife.SaralpensionNext;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.sbilife.common.CommonForAllProd;

public class SaralPensionNext {

		CommonForAllProd cfap=null;
		SaralPensionNextProperties prop=null;
		StringBuilder bussIll=null;
		DecimalFormat df = null;
		public String CalculatePrem(String isStaff,String SouceofBuss,String ChannelD,String payoutMode,String annuityOption,String fAnnuitantAge,String sAnnuitantAge, String fAnnuitantGender,String sAnnuitantGender,String SumAssured,String AdditionalAmt,String IsBackdate,
	            String IsMines,String KFC)
		{
			String retVal="";
			
			try{
			
			SaralPensionNextBean saralPensionNextBean=new SaralPensionNextBean();
			
			saralPensionNextBean.setisStaff(Boolean.parseBoolean(isStaff));
			saralPensionNextBean.setSouceofBuss(SouceofBuss);
			saralPensionNextBean.setChannelD(ChannelD);
			saralPensionNextBean.setpayoutMode(payoutMode);
			saralPensionNextBean.setAnnuityOption(annuityOption);
			saralPensionNextBean.setfAnnuitantAge(Integer.parseInt(fAnnuitantAge));
			saralPensionNextBean.setsAnnuitantAge(sAnnuitantAge != "" ? Integer.parseInt(sAnnuitantAge) : 0);
			saralPensionNextBean.setfAnnuitantGender(fAnnuitantGender);
			saralPensionNextBean.setsAnnuitantGender(sAnnuitantGender);
			saralPensionNextBean.setSumAssured(Double.parseDouble(SumAssured));
			saralPensionNextBean.setAdditionalAmt(AdditionalAmt != "" ? Double.parseDouble(AdditionalAmt ) : 0);
			saralPensionNextBean.setState(Boolean.parseBoolean(KFC));
			
			retVal=showSaralPensionNextOutputPg(saralPensionNextBean);
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
		
		
		
	public String showSaralPensionNextOutputPg(SaralPensionNextBean saralPensionBean)
	{
		SaralPensionNextBusinessLogic SP_BusinessLogic=new SaralPensionNextBusinessLogic(saralPensionBean);
		CommonForAllProd cfap=new CommonForAllProd();
		StringBuilder retVal=new StringBuilder();
		int policyYearMin = 1,policyYearMax=25;
		retVal.append("<?xml version='1.0' encoding='utf-8' ?><saralPensionNext>");
		
		//Instalment Premium without applicable taxes
		
		double amtAfterST = SP_BusinessLogic.getAmtAfterST();
		//System.out.print(amtAfterST);
		df = new DecimalFormat("#");
    	df.setRoundingMode(RoundingMode.FLOOR);
		retVal.append("<BasicPrem>"+df.format(amtAfterST)+"</BasicPrem>");
		retVal.append("<RiderbasicPrem>"+"Not Applicable"+"</RiderbasicPrem>");
		retVal.append("<TotalInstallmentPrem>"+df.format(amtAfterST)+"</TotalInstallmentPrem>");
		
		
		//Instalment Premium with First year applicable taxes	
		
		double serviceTax = SP_BusinessLogic.ServiceTax();
		retVal.append("<FYInstalmentPremium>"+df.format(amtAfterST+serviceTax)+"</FYInstalmentPremium>");
		retVal.append("<FYRider>"+"Not Applicable"+"</FYRider>");
		retVal.append("<FYTotalInstallmentPrem>"+df.format(amtAfterST+serviceTax)+"</FYTotalInstallmentPrem>");
		
		//Instalment Premium with applicable taxes 2nd Year onwards
		
		retVal.append("<SYInstalmentPremium>"+"Not Applicable"+"</SYInstalmentPremium>");
		retVal.append("<SYRider>"+"Not Applicable"+"</SYRider>");
		retVal.append("<SYTotalInstallmentPrem>"+"Not Applicable"+"</SYTotalInstallmentPrem>");	
		
		
		//Annuity Amount or Additional Amount
		retVal.append("<AnnuityAmount>"+SP_BusinessLogic.getAnnuityAmt()+"</AnnuityAmount>");
		
		//For rider calcalution
		retVal.append("<OutputRider>"+SP_BusinessLogic.getridercal()+"</OutputRider>");
		
		//Service Tax
		retVal.append("<ServiceTax>"+df.format(SP_BusinessLogic.ServiceTax())+"</ServiceTax>");
		
		 //Vesting Amount
		//System.out.println(SP_BusinessLogic.getVestingAmt());
		retVal.append("<VestingAmount>"+SP_BusinessLogic.getVestingAmt()+"</VestingAmount>");
		
		
		//Premium Details Grid Table
		for(int policyYear= policyYearMin;policyYear<=policyYearMax;policyYear++) {
			//Policy Year
			if(policyYear == policyYearMax)
				retVal.append("<PolicyYear>Till Death</PolicyYear>");
			else
				retVal.append("<PolicyYear>"+policyYear+"</PolicyYear>");
			
			//Single / Annualized premium
			if(policyYear==policyYearMin)
				retVal.append("<SingleOrAnnualizedPrem>"+amtAfterST+"</SingleOrAnnualizedPrem>");
			else 
				retVal.append("<SingleOrAnnualizedPrem>"+SP_BusinessLogic.getSingleOrAnnualizedPrem(policyYear)+"</SingleOrAnnualizedPrem>");
			  
			//Survival Benefit / Loyalty Additions
			retVal.append("<SurvivalBenefit>"+SP_BusinessLogic.getSurvivalBenefit()+"</SurvivalBenefit>");
			
			//"Other Benefits, 
			retVal.append("<OtherBenefits>"+(policyYear < policyYearMax ? SP_BusinessLogic.getOtherBenefit(policyYear) 
					: "-") + "</OtherBenefits>");
            retVal.append("<MaturityVestingBenefit>"+"Not Applicable"+"</MaturityVestingBenefit>");
			
			//Death Benefit
			retVal.append("<DeathBenefit>"+SP_BusinessLogic.getDeathBenefit()+"</DeathBenefit>");

			//Min.Guaranteed Surrender Value	
			retVal.append("<MinGuaranteedSurrenderValue>"+SP_BusinessLogic.getminGuaranteedSurrValue()+"</MinGuaranteedSurrenderValue>");
			
			retVal.append("<SpecialSurrenderValue>"+"Not Applicable"+"</SpecialSurrenderValue>");
			
			
			
		}
		retVal.append("</saralPensionNext>");
		
		return retVal.toString();
	}

	
	}
