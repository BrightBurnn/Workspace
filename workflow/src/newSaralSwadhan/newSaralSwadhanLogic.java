 package newSaralSwadhan;

import com.sbilife.common.CommonForAllProd;
import com.sbilife.saralswadhanp.SaralSwadhanpBean;

public class newSaralSwadhanLogic {
	 newSaralSwadhanDb db = new newSaralSwadhanDb();
 newSaralSwadhanProperties prop = null;
	
	public newSaralSwadhanLogic(newSaralSwadhanDb db )
	{
	   this .db = db;
		prop =  new newSaralSwadhanProperties();
	}

	
	 public String getAgeGroup()
	    { int age = db.getAge();
	    String agegroup= null;
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
	 
	 public int getMaturityAge()
	    {
	   	 return (db.getPolicyTerm()+db.getAge());
	    }
	 //+============================================================+
	  public double getSumAssured()
	    {
	   	 String ageGrp=getAgeGroup();
	   	 if(db.getPolicyTerm()==10)
	   	 { 	
	   		double premium = db.getPremium();
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
	   	 else if(db.getPolicyTerm() ==15)
	   		 
	   	{
	   		double premium = db.getPremium();
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
	  //=======================================================
	 
	  public double getAnnualPrem(int year)
	    {
	   	 if(year<=prop.premiumPayingTerm)
	   		 return db.getPremium();
	   	 else
	   		 return 0;
	    }
	  public double  getmaturity()
	    {
	   	 if(db.getPolicyTerm()==10 )
	   		return  db.getPremium()*10*1;
	   	 else if(db.getPolicyTerm()==15 )
	   		 return db.getPremium()*10*1.15;
	   	 else
	   		 return 0;
	    }
	  
	  //====================================================================
	  int l=0;
	  int k =1;
	  public double getMaturitySurrenderValueFor10()
	  {    
	    
		 if(l<=10 && db.getPolicyTerm()==10) {
			
			  double h=((db.getPremium()*k++) *prop.getGsvratefor10[l++]);
			  return (Math.round(h));
			  	 }	  
		 else
		 {    
			 double h =(db.getPremium()*k++) *prop.getGsvratefor15[l++];
			 return (Math.round(h));
		 }
	  }
	  
	 
	  public double  getMaturiySurrenderValueFor15() {
		  double h =0;
		  if (l<=15)
		  {
			 h =(db.getPremium()*--k) *prop.getGsvratefor15[l++];
			 
		  }
		  k++;
		  return (Math.round(h));
	  }
	  
	  //=======================================================
	  int m =0;
	  int n=1;
	  public double getSpecialSurrenderValueFor10()
	  {    
	    
		 if(m<=10 && db.getPolicyTerm()==10) {
			
			double h=  (db.getPremium()*n++)*1 *prop.getSsvratefor10[m++];
			return (Math.round(h));
			  	 }	  
		 else
		 {
			 double h =((db.getPremium()*n++)*1.15*prop.getSsvratefor15[m++]);
			 return (Math.round(h));
		 }
	  }
	  
	 
	  public double getSpecialSurrenderValueFor15() {
		  double h=0;
		  if (m<=15)
		  {
			 h =((db.getPremium()*--n)*1.15*prop.getSsvratefor15[m++]);
			
		  }
		  n++;
		  return Math.round(h);
	  }
	  //==========================================================================
	  
	  
	  public double premiumWithTaxesfirstyear()
	  {
		 return db.getPremium()+(db.getPremium()*prop.serviceTaxFirstYear)+( db.getPremium()*prop.SBCServiceTaxFirstYear);
	  }
	  public double premiumWithTaxesSecondYearOnward()
	  {
		  return db.getPremium()+(db.getPremium()*prop.serviceTaxSecondYear)+( db.getPremium()*prop.SBCServiceTaxSecondYear);
	  }
	  
	  
	 
	
	
	  
	  //+==========================================================================+
	/*public void display()
	{
		int year=1;
		int k =1;
		if((db.getPolicyTerm() == 10 || db.getPolicyTerm() ==15) && age>=18 && age<=55) {
	
		
		for(int i =1; i<=db.getPolicyTerm();i++)
		{
			if(year==1 ) {
			
		    System.out.println(k++ + "  year policy");
			System.out.println("Annual premium is " +getAnnualPrem(year));
			System.out.println("Annual premium with Gst "+premiumWithTaxesfirstyear() );
			System.out.println( "death benifits is " +getSumAssured());
			   if(year++ ==db.getPolicyTerm()) {
			    System.out.println("Maturity benifit is  " +Math.round(getmaturity()));
			    
			  }
			
			 else {
				System.out.println("Maturity benifit is  "+ 0);
			   }
	 System.out.print("maturity In Surrender value is  "); getMaturitySurrenderValuefor10();
			 System.out.print("Special In Surrender value is  "); getSpecialSurrenderValuefor10();
	       
			 System.out.println("**************************************");
			}
			
			else if(year<=10 ) {
				
			    System.out.println(k++ + "  year policy");
				System.out.println("Annual premium is " +getAnnualPrem(year));
				System.out.println("Annual premium with Gst "+premiumWithTaxesSecondYearOnward() );
				System.out.println( "death benifits is " +getSumAssured());
				if(year++ ==db.getPolicyTerm()) {
				    System.out.println("Maturity benifit is  " +Math.round(getmaturity()));
				    
				}
				
				else {
					System.out.println("Maturity benifit is  "+ 0);
				   }
				 System.out.print("maturity In Surrender value is  "); getMaturitySurrenderValuefor10();
				 System.out.print("Special In Surrender value is  "); getSpecialSurrenderValuefor10();
		       
				 System.out.println("**************************************");
				}
			
			// for 15
			else {
				 System.out.println(k++ + "  year policy");
				System.out.println("Annual premium is " + 0);	
				System.out.println( "death benifits is " +getSumAssured());
				
				if(year++ ==db.getPolicyTerm()) {
				    System.out.println("Maturity benifit is  " + Math.round(getmaturity()));
				}
				else {
					System.out.println("Maturity benifit is  "+0);
				   }
				 System.out.print("maturity In Surrender value is  "); getMaturiySurrenderValueFor15();
				 System.out.print("Special  Surrender value is  "); getSpecialSurrenderValueFor15();
				
			System.out.println("***************************************");
		     }	
			}
		
		}
		else {
			System.out.println("Age is between 18 to 55  And \n policyTerm is either 10 or 15" );
		
		}
			
		}
	
	
	*/
	
	
	
	
}