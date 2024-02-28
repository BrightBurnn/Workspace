package newSaralSwadhan;

import com.sbilife.common.CommonForAllProd;

public class newSaralSwadhanJava  {
	
	
	
	public String Calculate(String name, String age, String gender, String policyTerm, String preFreq, String premium)
	{ 
		String retValue="";
		newSaralSwadhanDb  db = new newSaralSwadhanDb();
		try {
		db.setName(name);
		db.setAge(Integer.parseInt(age));
		db.setGender(gender);
		db.setPolicyTerm(Integer.parseInt(policyTerm));
		db.setPreFreq(preFreq);
		db.setPremium(Double.parseDouble(premium));
		
		retValue=showSaralSwadhanOutputPg(db);
		}
		catch(Exception e)
		
		{
			e.printStackTrace();
			retValue="<?xml version='1.0' encoding='utf-8' ?><saralSwadhan>"+
					"<errCode>1</errCode>"+
					"<errorMessage>"+e.getMessage()+"</errorMessage></saralSwadhan>";	
			
		}
		
		return retValue.toString();
		
	}
	
	public String showSaralSwadhanOutputPg(newSaralSwadhanDb db)
	{  CommonForAllProd comm = new CommonForAllProd();
		newSaralSwadhanLogic log =  new newSaralSwadhanLogic(db);
		StringBuilder retVal = new StringBuilder();
		if((db.getPolicyTerm() == 10 || db.getPolicyTerm() ==15) && db.getAge()>=18 && db.getAge()<=55) {
			 
		
		String sumassure = comm.getRoundUp(comm.getStringWithout_E(log.getSumAssured()));
		
		retVal.append("<?xml version='1.0' encoding='utf-8' ?><saralSwadhan>");
		retVal.append("<maturityAge>" + log.getMaturityAge() +"</maturityAge>");
		retVal.append("<agegroup>" + log.getAgeGroup() + "</agegroup>");
		retVal.append("<sumAssured>" + sumassure +"</sumAssured>");
		retVal.append("<basicPremium>"+ db.getPremium()+"</basicPremium>");
		 
		int year=1;
		int k =1;
		
		
		for(int i =1; i<=db.getPolicyTerm();i++)
		{
			if(year==1 ) {
			
		    retVal.append ("<policyYear>"+(k++ + "year policy")+"</policyYear>");
			retVal.append("<Annualpremium>" +log.getAnnualPrem(year)+"</Annualpremium>");
			retVal.append("<AnnualpremiumwithGst>"+log.premiumWithTaxesfirstyear()+ "</AnnualpremiumwithGst>" );
			retVal.append( "<deathbenifits>" + sumassure+ "</deathbenifits>");
			if(year++ ==db.getPolicyTerm()) {
			    retVal.append("<Maturitybenifit>" +Math.round(log.getmaturity())+ "</Maturitybenifit>");
			    
			}
			
			else {
				retVal.append("<Maturitybenifit>"+ 0+ "</Maturitybenifit>");
			   }
			 retVal.append("<maturityInSurrendervalue>" +comm.getRoundUp(comm.getStringWithout_E(log. getMaturitySurrenderValueFor10())) + "</maturityInSurrendervalue>");
			 retVal.append("<SpecialInSurrendervalue>"+ comm.getRoundUp(comm.getStringWithout_E(log. getSpecialSurrenderValueFor10()))+ "</SpecialInSurrendervalue>");
	       
			
			}
			
			else if(year<=10 ) {
				
				 retVal.append ("<policyYear>"+(k++ + "yearpolicy")+"</policyYear>");
				retVal.append("<Annualpremium>" +log.getAnnualPrem(year) + "</Annualpremium>");
				retVal.append("<AnnualpremiumwithGst> "+log.premiumWithTaxesSecondYearOnward() +"</AnnualpremiumwithGst> ");
				retVal.append( "<deathbenifits>" +sumassure +  "</deathbenifits>");
				if(year++ ==db.getPolicyTerm()) {
				    retVal.append("<Maturitybenifit>" +Math.round(log.getmaturity())+ "</Maturitybenifit>");
				    
				}
				
				else {
					retVal.append("<Maturitybenifit>"+ 0+ "</Maturitybenifit>");
				   }
				 retVal.append("<maturityInSurrendervalue>"+comm.getRoundUp(comm.getStringWithout_E(log. getMaturitySurrenderValueFor10()))+"</maturityInSurrendervalue>" );
				 retVal.append("<SpecialInSurrendervalue>" + comm.getRoundUp(comm.getStringWithout_E(log. getSpecialSurrenderValueFor10()))+ "</SpecialInSurrendervalue>");
		       
				
				}
			
			// for 15
			else {
				 retVal.append("<policyyear>"+(k++ + "  yearpolicy")+ "</policyyear>");
				retVal.append("<Annualpremium > " + 0+"</Annualpremium>"  );	
				retVal.append( "<deathbenifits>" +sumassure+ "</deathbenifits>");
				
				if(year++ ==db.getPolicyTerm()) {
				    retVal.append("<Maturitybenifit>" + Math.round(log.getmaturity())+ "</Maturitybenifit>" );
				}
				else {
					retVal.append("<Maturitybenifit>"+0 +"</Maturitybenifit>" );
				   }
				 retVal.append("<maturityInSurrendervalue>"+ comm.getRoundUp(comm.getStringWithout_E(log. getMaturiySurrenderValueFor15())) + "</maturityInSurrendervalue>");
				 retVal.append("<SpecialSurrendervalue>"+comm.getRoundUp(comm.getStringWithout_E(log. getSpecialSurrenderValueFor15()))+ "</SpecialSurrendervalue>");
				
		     }	
			}
		
		
		
		retVal.append("</saralSwadhan>");
		
		

			
		}
		else {
			retVal.append("<credential>" + " Age is between 18 to 55 And policy Term is either 10 or 15 "  + "</credential>");
		
		}
		
		
		
		return retVal.toString();
	}
	
	
	
	
	
	

}
