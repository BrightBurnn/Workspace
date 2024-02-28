package com.sbilife.saralaPensionNextt;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.sbilife.common.CommonForAllProd;

public class nextJava {
	CommonForAllProd comm;
	nextProperties prop;
	StringBuilder bussIll;
	DecimalFormat df ;
	
	public String CalculatePrem(String isStaff,String SouceofBuss,String ChannelDetail,String payoutMode,String annuityOption,String firstAnnuitantAge,String secondAnnuitantAge, String firstAnnuitantGender,String secondAnnuitantGender,String SumAssured,String AdditionalAmt,
            String state)
	{
		String retVal="";
		nextBean bean = new nextBean();
  try {
		bean.setStaff(Boolean.parseBoolean(isStaff));
		bean.setSouceofBuss(SouceofBuss);
		bean.setChannelDetails(ChannelDetail);
		bean.setPayoutMode(payoutMode);
		bean.setAnnuityOption(annuityOption);
		bean.setFirstAnnuitantAge(Integer.parseInt(firstAnnuitantAge));
		bean.setSecondAnnuitantAge(secondAnnuitantAge != "" ? Integer.parseInt(secondAnnuitantAge) : 0);
		bean.setFirstAnnuitantGender(firstAnnuitantGender);
		bean.setSecondAnnuitantGender(secondAnnuitantGender);
		bean.setSumAssured(Double.parseDouble(SumAssured));
		bean.setAdditionalAmt(AdditionalAmt != "" ? Double.parseDouble(AdditionalAmt ) : 0);
		 bean.setState(state);
		retVal=showSaralPensionNextOutputPg(bean);
  }
	catch (Exception e) {
		
		
		e.printStackTrace();
		retVal="<?xml version='1.0' encoding='utf-8' ?><saralPension>"+
				"<errCode>1</errCode>"+
				"<errorMessage>"+e.getMessage()+"</errorMessage></saralPension>";
				
	}
	return retVal.toString();
}


	public String showSaralPensionNextOutputPg(nextBean bean) {
		nextBusinessLogic log = new nextBusinessLogic(bean);
		CommonForAllProd comm = new CommonForAllProd();
		StringBuilder retVal =new StringBuilder();
		int policyYearMin = 1,policyYearMax=25;
		
retVal.append("<?xml version='1.0' encoding='utf-8' ?><saralPensionNext>");
		
		
		
		double amtAfterST =log.getAmountAfterServiceTax();
		
		df = new DecimalFormat("#");
		retVal.append("<BasicPrem>"+df.format(amtAfterST)+"</BasicPrem>");
		
		retVal.append("<TotalInstallmentPrem>"+df.format(amtAfterST)+"</TotalInstallmentPrem>");
		double serviceTax = log.getServiceTax();
		retVal.append("<FYInstalmentPremium>"+df.format(amtAfterST+serviceTax)+"</FYInstalmentPremium>");
		retVal.append("<FYTotalInstallmentPrem>"+df.format(amtAfterST+serviceTax)+"</FYTotalInstallmentPrem>");
		retVal.append("<SYInstalmentPremium>"+"Not Applicable"+"</SYInstalmentPremium>");
		retVal.append("<SYTotalInstallmentPrem>"+"Not Applicable"+"</SYTotalInstallmentPrem>");	
		retVal.append("<AnnuityAmount>"+log.getAnnuityAmt()+"</AnnuityAmount>");
		retVal.append("<ServiceTax>"+df.format(log.getServiceTax())+"</ServiceTax>");
		retVal.append("<VestingAmount>"+log.getVestingAmt()+"</VestingAmount>");
				
			for(int policyYear= policyYearMin;policyYear<=policyYearMax;policyYear++) {
					
					if(policyYear == policyYearMax)
						retVal.append("<PolicyYear>Till Death</PolicyYear>");
					else
						retVal.append("<PolicyYear>"+policyYear+"</PolicyYear>");
					
				    if(policyYear==policyYearMin)
						retVal.append("<SingleOrAnnualizedPrem>"+amtAfterST+"</SingleOrAnnualizedPrem>");
					else 
						retVal.append("<SingleOrAnnualizedPrem>"+log.getSingleOrAnnualizedPrem(policyYear)+"</SingleOrAnnualizedPrem>");
					  
					retVal.append("<SurvivalBenefit>"+log.getSurvivalBenefit()+"</SurvivalBenefit>");
					
				retVal.append("<OtherBenefits>"+(policyYear < policyYearMax ? log.getOtherBenefit(policyYearMax)
							: "-") + "</OtherBenefits>");
		            retVal.append("<MaturityVestingBenefit>"+"Not Applicable"+"</MaturityVestingBenefit>");
					
					retVal.append("<DeathBenefit>"+log.getDeathBenefit()+"</DeathBenefit>");

					retVal.append("<MinGuaranteedSurrenderValue>"+log.getminGuaranteedSurrValue()+"</MinGuaranteedSurrenderValue>");
					
					retVal.append("<SpecialSurrenderValue>"+"Not Applicable"+"</SpecialSurrenderValue>");
					
					
			}
			retVal.append("</saralPensionNext>");
			
			return retVal.toString();

		
	
	}
		
	
	
	
}
