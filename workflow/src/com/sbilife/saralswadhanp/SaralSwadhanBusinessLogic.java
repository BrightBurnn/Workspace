package com.sbilife.saralswadhanp;

import com.sbilife.common.CommonForAllProd;

public class SaralSwadhanBusinessLogic {
   	CommonForAllProd comm ;
   	SaralSwadhanpBean ssBean;
   	SaralSwadhanpDB ssDB;
   	SaralSwadhanpProperties  sspro;
    private String   agegroup;
            
   	
   	
   	public SaralSwadhanBusinessLogic() {
   		comm = new CommonForAllProd();
   		ssBean = new SaralSwadhanpBean();
   		ssDB = new SaralSwadhanpDB();
   		sspro = new SaralSwadhanpProperties();
   	}
   	
    public int setMaturityAge(int policyterm,int age)
    {
   	 return (policyterm+age);
    }
                   
    public String setAgeGroup(int age)
    {
   	 if(age>=18 && age<=30)
   		agegroup= "18-30";
   	 else if(age>=31 && age<=35)
   		 	agegroup="31-35";
   	 else if(age>=36 && age<=40)
   		 agegroup="36-40";
   	 else if(age>=41 && age<=45)
   		agegroup="41-45";
   	else if(age>=46 && age<=50)
   			agegroup="46-50";
   			else
   				agegroup="51-55";
   	 
   	
   	 return agegroup;
    }               
    
    public String getAgeGroup()
    {
   	 return agegroup;
    }
    
    public double setSumAssured(int policyTerm,double premium)
    {
   	 String ageGrp=getAgeGroup();
   	 if(policyTerm==10)
   	 { 	
   		 if(ageGrp.equalsIgnoreCase("18-30"))
   			 return 80*premium;
   	 	 else if(ageGrp.equalsIgnoreCase("31-35"))
   	 		return 65*premium;
   	 	 else if(ageGrp.equalsIgnoreCase("36-40"))
   	 			 return 50*premium;
   	 	 else if(ageGrp.equalsIgnoreCase("41-45"))
   	 			 return 35*premium;
   	 	 else if(ageGrp.equalsIgnoreCase("46-50"))
   	 			 return 25*premium;
   	 	 else
   	 		 return 20*premium;
   	 }
   	 else if(policyTerm ==15)
   	{
   		 if(ageGrp.equalsIgnoreCase("18-30"))
   				 return 95*premium;
   		 else if(ageGrp.equalsIgnoreCase("31-35"))
   				 return 70*premium;
   		 else if(ageGrp.equalsIgnoreCase("36-40"))
   				 return 55*premium;
   		 else if(ageGrp.equalsIgnoreCase("41-45"))
   				 return 40*premium;
   		 else if(ageGrp.equalsIgnoreCase("46-50"))
   				 return 30*premium;
   		 else
   			 return 20*premium;
   	 }
   	 else 
   	 {
   		 return 0;
   		 
   	 }
   	 
    }  
  //  "********************************************************************"
    
    public double setAnnualPrem(double premium,int year)
    {
   	 if(year<=sspro.premiumPayingTerm)
   		 return premium;
   	 else
   		 
   		 return 0;
    }
    
    public double setSumAssured(double sumAssured,int year,int policyTerm)
    {
   	 if(year<=policyTerm)
   		 return sumAssured;
   	 else
   		 return 0;
    }
    
    public double setBenefitPaybleAtDeath(double sumAssured,int year,int policyTerm)
    {
   	 if(year<=policyTerm)
   		 return sumAssured;
   	 else
   		 return 0;
    }
    

    
    public double setSAMF(int policyTerm)
    {
   	 String ageGrp=getAgeGroup();
   	 if(policyTerm==10)
   	 { 	
   		 if(ageGrp.equalsIgnoreCase("18-30"))
   			 return 80;
   	 	 else if(ageGrp.equalsIgnoreCase("31-35"))
   	 		return 65;
   	 	 else if(ageGrp.equalsIgnoreCase("36-40"))
   	 			 return 50;
   	 	 else if(ageGrp.equalsIgnoreCase("41-45"))
   	 			 return 35;
   	 	 else if(ageGrp.equalsIgnoreCase("46-50"))
   	 			 return 25;
   	 	 else
   	 		 return 20;
   	 }
   	 else if(policyTerm ==15)
   	{
   		 if(ageGrp.equalsIgnoreCase("18-30"))
   				 return 95;
   		 else if(ageGrp.equalsIgnoreCase("31-35"))
   				 return 70;
   		 else if(ageGrp.equalsIgnoreCase("36-40"))
   				 return 55;
   		 else if(ageGrp.equalsIgnoreCase("41-45"))
   				 return 40;
   		 else if(ageGrp.equalsIgnoreCase("46-50"))
   				 return 30;
   		 else
   			 return 20;
   	 }
   	 else
   	 {
   		 return 0;
   	 }
    }
    
    public double setSumAssured(double SAMF,double premium)
    {
   	 
   	 return SAMF*premium;
   	 

    } 
    //+=======================================================+
    public double getServiceTax(double premiumWithoutST,String type)
	{
		if(type.equals("basic"))
		{
//			System.out.println("nnn "+ (premiumWithoutST*prop.serviceTax));
				return Double.parseDouble(comm.getRoundOffLevel2(comm.getRoundOffLevel2(comm.getStringWithout_E(premiumWithoutST*sspro.serviceTaxFirstYear))));
		}
		else if (type.equals("SBC"))
		{
				return Double.parseDouble(comm.getRoundOffLevel2(comm.getStringWithout_E(premiumWithoutST*sspro.SBCServiceTaxFirstYear)));
		}
	//  Added By Saurabh Jain on 15/05/2019 Start
		else if (type.equals("KERALA"))
		{
			
				return Double.parseDouble(comm.getRoundOffLevel2(comm.getStringWithout_E(premiumWithoutST*sspro.kerlaServiceTaxFirstYear)));

		}
	//  Added By Saurabh Jain on 15/05/2019 End
		else //KKC
		{
				return Double.parseDouble(comm.getRoundOffLevel2(comm.getStringWithout_E(premiumWithoutST*sspro.KKCServiceTaxFirstYear)));
		}
			
	}

	 
	 public double getServiceTaxSecondYear(double premiumWithoutST,String type)
		{
			if(type.equals("basic"))
			{
					return Double.parseDouble(comm.getRoundOffLevel2New(comm.getStringWithout_E(premiumWithoutST*sspro.serviceTaxSecondYear)));
			}
			else if (type.equals("SBC"))
			{
					return Double.parseDouble(comm.getRoundOffLevel2New(comm.getStringWithout_E(premiumWithoutST*sspro.SBCServiceTaxSecondYear)));
			}
		//  Added By Saurabh Jain on 15/05/2019 Start
			else if (type.equals("KERALA"))
			{
					return Double.parseDouble(comm.getRoundOffLevel2New(comm.getStringWithout_E(premiumWithoutST*sspro.kerlaServiceTaxSecondYear)));

			}
		//  Added By Saurabh Jain on 
			
			else //KKC
			{
					return Double.parseDouble(comm.getRoundOffLevel2New(comm.getStringWithout_E(premiumWithoutST*sspro.KKCServiceTaxSecondYear)));
			}
				
		}
	 //=================================================================================
    
   
	 
	public double setGuaranteedSurrenderValue(double sumcummulativePremiumPaid,
			int year,int policyTerm) {
		
		String[] prStrArr = null;
		double prDouble = 0;
	

		if (policyTerm ==10) {


			prStrArr = comm.split(ssDB.getGsvratefor10(), ",");
			
		}else if(policyTerm==15){
			
			prStrArr = comm.split(ssDB.getGsvratefor15(), ",");
		}
		else {
			return 0;
		}
			
			 prDouble=Double.parseDouble(prStrArr[year-1]);
			

		return sumcummulativePremiumPaid * prDouble;
		
	}
	
	public double setNonGuaranteedSurrenderValue(int year,double sumcummulativePremiumPaid,int policyTerm){
		
		String[] prStrArr;
		double prDouble = 0;

		if (policyTerm ==10) {


			prStrArr = comm.split(ssDB.getSsvratefor10(), ",");
			prDouble=Double.parseDouble(prStrArr[year-1]);
			return Double.parseDouble(comm.getRoundUp(comm.getStringWithout_E(sumcummulativePremiumPaid *1* prDouble)));
			
		}else if(policyTerm ==15){
			
			prStrArr = comm.split(ssDB.getSsvratefor15(), ",");
			prDouble=Double.parseDouble(prStrArr[year-1]);
			return Double.parseDouble(comm.getRoundUp(comm.getStringWithout_E(sumcummulativePremiumPaid *1.15* prDouble)));
		}
		else {
			return 0;
		}
			
	}

	public double setbenefitPaybleAtMaturity(double premium, int year_F, int policyTerm) {
		// TODO Auto-generated method stub
		return 0;
	}
   	
   	

}
