package com.sbilife.sampoorncancersuraksha;


import com.sbilife.common.CommonForAllProd;



public class SampoornCancerSuraksha {



	public  SampoorncancersurakshaBusinesslogic sampoorncancersurakshabusinesslogic;


	CommonForAllProd commonForAllProd;
//	StringBuilder bussIll=null;
	StringBuilder retVal=null;
	String staffstatus = "";
	String OnlineDisc ="";
	public String CalculatePrem(String OnlineDiscount,String isStaff,String isJkResident,String planType,String age,String gender,String policyTerm,String premFreq,String sumAssured)
	{
		//Variable declaration


		//Class declaration
		commonForAllProd=new CommonForAllProd();
		OnlineDisc = OnlineDiscount ;
//		retVal=new StringBuilder();
		SampoorncancersurakshaBean sampoorncancersurakshaBean = new SampoorncancersurakshaBean();

		try
		{
			//Insert data entered by user in an object

			if(isStaff.equals("true"))
			{
				staffstatus = "sbi";
			}
			else
			{
				staffstatus = "none";
			}
			sampoorncancersurakshaBean.setIsForStaffOrNot(Boolean.parseBoolean(isStaff));

			sampoorncancersurakshaBean.setIsJammuResident(Boolean.parseBoolean(isJkResident));

			sampoorncancersurakshaBean.setPlanType(planType);
			sampoorncancersurakshaBean.setAge(Integer.parseInt(age));
			sampoorncancersurakshaBean.setGender(gender);
			sampoorncancersurakshaBean.setPolicyterm(Integer.parseInt(policyTerm));
			sampoorncancersurakshaBean.setPremiumFrequency(premFreq);
			sampoorncancersurakshaBean.setSumAssured(Double.parseDouble(sumAssured));

		}
		catch (Exception e) {
			// TODO: handle exception

		}
		return showSampoornCancerSurakshaOutputPg(sampoorncancersurakshaBean);
	}
	


	private String showSampoornCancerSurakshaOutputPg(
			SampoorncancersurakshaBean sampoorncancersurakshabean) {
		// TODO Auto-generated method stub
		 retVal = new StringBuilder();
		String error = "";
		String[] outputArr=getOutput("BI_of_Sampoorn_Cancer_Suraksha",sampoorncancersurakshabean);
		String val ="" ;
		String val_standard = (valminprem(outputArr[0],sampoorncancersurakshabean.getPremiumFrequency()));
		String val_classic =(valminprem(outputArr[1],sampoorncancersurakshabean.getPremiumFrequency())); ;
		String val_enhanced =(valminprem(outputArr[2],sampoorncancersurakshabean.getPremiumFrequency())); ;
			
		if(!val_standard.equals("") && !val_classic.equals("") && !val_enhanced.equals(""))
		{
			error= val_standard;
		}
		else if(sampoorncancersurakshabean.getPlantype().equals("Standard") && !val_standard.equals(""))
		{
			error= val_standard+".So either increase the Sum Assured or change the plan type with current sum assured";
		}
		else if(sampoorncancersurakshabean.getPlantype().equals("Classic") && !val_classic.equals(""))
		{
			error=val_classic+".So either increase the Sum Assured or change the plan type with current sum assured";
		}
		else if(sampoorncancersurakshabean.getPlantype().equals("Enhanced") && !val_enhanced.equals(""))
		{
			error=val_enhanced+".So either increase the Sum Assured or change the plan type with current sum assured";
		}
		
		if(error.equals(""))
		{

			try
			{
				
				
				 if(val_standard != "")
				{
					
					

					retVal.append("<?xml version='1.0' encoding='utf-8' ?><SampoornCancerSuraksha>");
					retVal.append("<errCode>0</errCode>");
					retVal.append("<PremiumBeforeST_Standard>" + "NA"
							+ "</PremiumBeforeST_Standard>"
							+ "<PremiumBeforeST_Classic>" + outputArr[1]
							+ "</PremiumBeforeST_Classic>"
							+ "<PremiumBeforeST_Enhanced>" + outputArr[2]
							+ "</PremiumBeforeST_Enhanced>"						
							+ "<GST_Standard>"+ "NA" 
							+ "</GST_Standard>"
							+ "<GST_Classic>" + outputArr[4]
							+ "</GST_Classic>" 
							+ "<GST_Enhanced>"+ outputArr[5] 
							+ "</GST_Enhanced>"
							+ "<PremiumWithServTax_Standard>" + "NA"
							+ "</PremiumWithServTax_Standard>"
							+ "<PremiumWithServTax_Classic>" + outputArr[7]
							+ "</PremiumWithServTax_Classic>"
							+ "<PremiumWithServTax_Enhanced>" + outputArr[8]
							+ "</PremiumWithServTax_Enhanced>"
							+ "<BasicServiceTax_Standard>" + "NA"
							+ "</BasicServiceTax_Standard>"
							+ "<BasicServiceTax_Classic>" + outputArr[10]
							+ "</BasicServiceTax_Classic>"
							+ "<BasicServiceTax_Enhanced>" + outputArr[11]
							+ "</BasicServiceTax_Enhanced>"
							+ "<SBCServiceTax_Standard>" + "NA"
							+ "</SBCServiceTax_Standard>"
							+ "<SBCServiceTax_Classic>" + outputArr[13]
							+ "</SBCServiceTax_Classic>"
							+ "<SBCServiceTax_Enhanced>" + outputArr[14]
							+ "</SBCServiceTax_Enhanced>"
							+"<PremiumBeforeST_Standard_Round2>" + "NA"
							+ "</PremiumBeforeST_Standard_Round2>"
							+"<PremiumBeforeST_Classic_Round2>" + outputArr[16]
							+ "</PremiumBeforeST_Classic_Round2>"
							+"<PremiumBeforeST_Enhanced_Round2>" + outputArr[17]
							+ "</PremiumBeforeST_Enhanced_Round2>"
							);
					
					if(sampoorncancersurakshabean.getPlantype().equals("Standard"))
					{
						retVal.append("<basicPrem>"+outputArr[15]+"</basicPrem>");
					}
					else if(sampoorncancersurakshabean.getPlantype().equals("Classic"))
					{
						retVal.append("<basicPrem>"+outputArr[16]+"</basicPrem>");
					}
					else
					{
						retVal.append("<basicPrem>"+outputArr[17]+"</basicPrem>");
					}
					
					retVal.append("</SampoornCancerSuraksha>");
					// System.out.println("output " + retVal.toString());
				}
				else if(val_classic != "") 
				{
					
					

					retVal.append("<?xml version='1.0' encoding='utf-8' ?><SampoornCancerSuraksha>");
					retVal.append("<errCode>0</errCode>");
					retVal.append("<PremiumBeforeST_Standard>" + outputArr[0]
							+ "</PremiumBeforeST_Standard>"
							+ "<PremiumBeforeST_Classic>" +"NA"
							+ "</PremiumBeforeST_Classic>"
							+ "<PremiumBeforeST_Enhanced>" + outputArr[2]
							+ "</PremiumBeforeST_Enhanced>"						
							+ "<GST_Standard>"+ outputArr[3] 
							+ "</GST_Standard>"
							+ "<GST_Classic>" + "NA"
							+ "</GST_Classic>" 
							+ "<GST_Enhanced>"+ outputArr[5] 
							+ "</GST_Enhanced>"
							+ "<PremiumWithServTax_Standard>" + outputArr[6]
							+ "</PremiumWithServTax_Standard>"
							+ "<PremiumWithServTax_Classic>" + "NA"
							+ "</PremiumWithServTax_Classic>"
							+ "<PremiumWithServTax_Enhanced>" + outputArr[8]
							+ "</PremiumWithServTax_Enhanced>"
							+ "<BasicServiceTax_Standard>" + outputArr[9]
							+ "</BasicServiceTax_Standard>"
							+ "<BasicServiceTax_Classic>" + "NA"
							+ "</BasicServiceTax_Classic>"
							+ "<BasicServiceTax_Enhanced>" + outputArr[11]
							+ "</BasicServiceTax_Enhanced>"
							+ "<SBCServiceTax_Standard>" + outputArr[12]
							+ "</SBCServiceTax_Standard>"
							+ "<SBCServiceTax_Classic>" + "NA"
							+ "</SBCServiceTax_Classic>"
							+ "<SBCServiceTax_Enhanced>" + outputArr[14]
							+ "</SBCServiceTax_Enhanced>"
							+"<PremiumBeforeST_Standard_Round2>" + outputArr[15]
									+ "</PremiumBeforeST_Standard_Round2>"
									+"<PremiumBeforeST_Classic_Round2>" + "NA"
									+ "</PremiumBeforeST_Classic_Round2>"
									+"<PremiumBeforeST_Enhanced_Round2>" + outputArr[17]
									+ "</PremiumBeforeST_Enhanced_Round2>");
					if(sampoorncancersurakshabean.getPlantype().equals("Standard"))
					{
						retVal.append("<basicPrem>"+outputArr[15]+"</basicPrem>");
					}
					else if(sampoorncancersurakshabean.getPlantype().equals("Classic"))
					{
						retVal.append("<basicPrem>"+outputArr[16]+"</basicPrem>");
					}
					else
					{
						retVal.append("<basicPrem>"+outputArr[17]+"</basicPrem>");
					}
					
					retVal.append("</SampoornCancerSuraksha>");
					// System.out.println("output " + retVal.toString());
				}
				else if(val_enhanced != "") 
				{
					
					

					retVal.append("<?xml version='1.0' encoding='utf-8' ?><SampoornCancerSuraksha>");
					retVal.append("<errCode>0</errCode>");
					retVal.append("<PremiumBeforeST_Standard>" + outputArr[0]
							+ "</PremiumBeforeST_Standard>"
							+ "<PremiumBeforeST_Classic>" + outputArr[1]
							+ "</PremiumBeforeST_Classic>"
							+ "<PremiumBeforeST_Enhanced>" + "NA"
							+ "</PremiumBeforeST_Enhanced>"						
							+ "<GST_Standard>"+ outputArr[3] 
							+ "</GST_Standard>"
							+ "<GST_Classic>" + outputArr[4]
							+ "</GST_Classic>" 
							+ "<GST_Enhanced>"+ "NA" 
							+ "</GST_Enhanced>"
							+ "<PremiumWithServTax_Standard>" + outputArr[6]
							+ "</PremiumWithServTax_Standard>"
							+ "<PremiumWithServTax_Classic>" + outputArr[7]
							+ "</PremiumWithServTax_Classic>"
							+ "<PremiumWithServTax_Enhanced>" + "NA"
							+ "</PremiumWithServTax_Enhanced>"
							+ "<BasicServiceTax_Standard>" + outputArr[9]
									+ "</BasicServiceTax_Standard>"
									+ "<BasicServiceTax_Classic>" + outputArr[10]
									+ "</BasicServiceTax_Classic>"
									+ "<BasicServiceTax_Enhanced>" + "NA"
									+ "</BasicServiceTax_Enhanced>"
									+ "<SBCServiceTax_Standard>" + outputArr[12]
									+ "</SBCServiceTax_Standard>"
									+ "<SBCServiceTax_Classic>" + outputArr[13]
									+ "</SBCServiceTax_Classic>"
									+ "<SBCServiceTax_Enhanced>" + "NA"
									+ "</SBCServiceTax_Enhanced>"
									+"<PremiumBeforeST_Standard_Round2>" + outputArr[15]
											+ "</PremiumBeforeST_Standard_Round2>"
											+"<PremiumBeforeST_Classic_Round2>" + outputArr[16]
											+ "</PremiumBeforeST_Classic_Round2>"
											+"<PremiumBeforeST_Enhanced_Round2>" + "NA"
											+ "</PremiumBeforeST_Enhanced_Round2>");
					if(sampoorncancersurakshabean.getPlantype().equals("Standard"))
					{
						retVal.append("<basicPrem>"+outputArr[15]+"</basicPrem>");
					}
					else if(sampoorncancersurakshabean.getPlantype().equals("Classic"))
					{
						retVal.append("<basicPrem>"+outputArr[16]+"</basicPrem>");
					}
					else
					{
						retVal.append("<basicPrem>"+outputArr[17]+"</basicPrem>");
					}
					
					retVal.append("</SampoornCancerSuraksha>");
					// System.out.println("output " + retVal.toString());
				}
				
				else if(val_standard != "" && val_classic !="") 
				{
					
					

					retVal.append("<?xml version='1.0' encoding='utf-8' ?><SampoornCancerSuraksha>");
					retVal.append("<errCode>0</errCode>");
					retVal.append("<PremiumBeforeST_Standard>" + "NA"
							+ "</PremiumBeforeST_Standard>"
							+ "<PremiumBeforeST_Classic>" + "NA"
							+ "</PremiumBeforeST_Classic>"
							+ "<PremiumBeforeST_Enhanced>" + outputArr[2]
							+ "</PremiumBeforeST_Enhanced>"						
							+ "<GST_Standard>"+ "NA"
							+ "</GST_Standard>"
							+ "<GST_Classic>" + "NA"
							+ "</GST_Classic>" 
							+ "<GST_Enhanced>"+ outputArr[5] 
							+ "</GST_Enhanced>"
							+ "<PremiumWithServTax_Standard>" + "NA"
							+ "</PremiumWithServTax_Standard>"
							+ "<PremiumWithServTax_Classic>" + "NA"
							+ "</PremiumWithServTax_Classic>"
							+ "<PremiumWithServTax_Enhanced>" + outputArr[8]
							+ "</PremiumWithServTax_Enhanced>"
							+ "<BasicServiceTax_Standard>" + "NA"
									+ "</BasicServiceTax_Standard>"
									+ "<BasicServiceTax_Classic>" + "NA"
									+ "</BasicServiceTax_Classic>"
									+ "<BasicServiceTax_Enhanced>" +outputArr[11]
									+ "</BasicServiceTax_Enhanced>"
									+ "<SBCServiceTax_Standard>" +  "NA"
									+ "</SBCServiceTax_Standard>"
									+ "<SBCServiceTax_Classic>" +  "NA"
									+ "</SBCServiceTax_Classic>"
									+ "<SBCServiceTax_Enhanced>" +outputArr[14]
									+ "</SBCServiceTax_Enhanced>"
									+"<PremiumBeforeST_Standard_Round2>" + "NA"
											+ "</PremiumBeforeST_Standard_Round2>"
											+"<PremiumBeforeST_Classic_Round2>" + "NA"
											+ "</PremiumBeforeST_Classic_Round2>"
											+"<PremiumBeforeST_Enhanced_Round2>" + outputArr[17]
											+ "</PremiumBeforeST_Enhanced_Round2>");
					if(sampoorncancersurakshabean.getPlantype().equals("Standard"))
					{
						retVal.append("<basicPrem>"+outputArr[15]+"</basicPrem>");
					}
					else if(sampoorncancersurakshabean.getPlantype().equals("Classic"))
					{
						retVal.append("<basicPrem>"+outputArr[16]+"</basicPrem>");
					}
					else
					{
						retVal.append("<basicPrem>"+outputArr[17]+"</basicPrem>");
					}
					
					retVal.append("</SampoornCancerSuraksha>");
					// System.out.println("output " + retVal.toString());
				}
				else if(val_classic != "" && val_enhanced !="") 
				{
					
					

					retVal.append("<?xml version='1.0' encoding='utf-8' ?><SampoornCancerSuraksha>");
					retVal.append("<errCode>0</errCode>");
					retVal.append("<PremiumBeforeST_Standard>" + outputArr[0]
							+ "</PremiumBeforeST_Standard>"
							+ "<PremiumBeforeST_Classic>" + "NA"
							+ "</PremiumBeforeST_Classic>"
							+ "<PremiumBeforeST_Enhanced>" + "NA"
							+ "</PremiumBeforeST_Enhanced>"						
							+ "<GST_Standard>"+ outputArr[3] 
							+ "</GST_Standard>"
							+ "<GST_Classic>" + "NA"
							+ "</GST_Classic>" 
							+ "<GST_Enhanced>"+ "NA"
							+ "</GST_Enhanced>"
							+ "<PremiumWithServTax_Standard>" + outputArr[6]
							+ "</PremiumWithServTax_Standard>"
							+ "<PremiumWithServTax_Classic>" + "NA"
							+ "</PremiumWithServTax_Classic>"
							+ "<PremiumWithServTax_Enhanced>" + "NA"
							+ "</PremiumWithServTax_Enhanced>"
							+ "<BasicServiceTax_Standard>" + outputArr[9]
									+ "</BasicServiceTax_Standard>"
									+ "<BasicServiceTax_Classic>" + "NA"
									+ "</BasicServiceTax_Classic>"
									+ "<BasicServiceTax_Enhanced>" + "NA"
									+ "</BasicServiceTax_Enhanced>"
									+ "<SBCServiceTax_Standard>" + outputArr[12]
									+ "</SBCServiceTax_Standard>"
									+ "<SBCServiceTax_Classic>" + "NA"
									+ "</SBCServiceTax_Classic>"
									+ "<SBCServiceTax_Enhanced>" + "NA"
									+ "</SBCServiceTax_Enhanced>"
									+"<PremiumBeforeST_Standard_Round2>" + outputArr[15]
											+ "</PremiumBeforeST_Standard_Round2>"
											+"<PremiumBeforeST_Classic_Round2>" + "NA"
											+ "</PremiumBeforeST_Classic_Round2>"
											+"<PremiumBeforeST_Enhanced_Round2>" + "NA"
											+ "</PremiumBeforeST_Enhanced_Round2>");
					if(sampoorncancersurakshabean.getPlantype().equals("Standard"))
					{
						retVal.append("<basicPrem>"+outputArr[15]+"</basicPrem>");
					}
					else if(sampoorncancersurakshabean.getPlantype().equals("Classic"))
					{
						retVal.append("<basicPrem>"+outputArr[16]+"</basicPrem>");
					}
					else
					{
						retVal.append("<basicPrem>"+outputArr[17]+"</basicPrem>");
					}
					
					retVal.append("</SampoornCancerSuraksha>");
					// System.out.println("output " + retVal.toString());
				}
				else if(val_standard != "" && val_enhanced !="") 
				{
					
					

					retVal.append("<?xml version='1.0' encoding='utf-8' ?><SampoornCancerSuraksha>");
					retVal.append("<errCode>0</errCode>");
					retVal.append("<PremiumBeforeST_Standard>" + "NA"
							+ "</PremiumBeforeST_Standard>"
							+ "<PremiumBeforeST_Classic>" + outputArr[1]
							+ "</PremiumBeforeST_Classic>"
							+ "<PremiumBeforeST_Enhanced>" + "NA"
							+ "</PremiumBeforeST_Enhanced>"						
							+ "<GST_Standard>"+ "NA" 
							+ "</GST_Standard>"
							+ "<GST_Classic>" + outputArr[4]
							+ "</GST_Classic>" 
							+ "<GST_Enhanced>"+ "NA" 
							+ "</GST_Enhanced>"
							+ "<PremiumWithServTax_Standard>" + "NA"
							+ "</PremiumWithServTax_Standard>"
							+ "<PremiumWithServTax_Classic>" + outputArr[7]
							+ "</PremiumWithServTax_Classic>"
							+ "<PremiumWithServTax_Enhanced>" + "NA"
							+ "</PremiumWithServTax_Enhanced>"
							+ "<BasicServiceTax_Standard>" + "NA"
									+ "</BasicServiceTax_Standard>"
									+ "<BasicServiceTax_Classic>" + outputArr[10]
									+ "</BasicServiceTax_Classic>"
									+ "<BasicServiceTax_Enhanced>" + "NA"
									+ "</BasicServiceTax_Enhanced>"
									+ "<SBCServiceTax_Standard>" + "NA"
									+ "</SBCServiceTax_Standard>"
									+ "<SBCServiceTax_Classic>" + outputArr[13]
									+ "</SBCServiceTax_Classic>"
									+ "<SBCServiceTax_Enhanced>" +"NA"
									+ "</SBCServiceTax_Enhanced>"
									+"<PremiumBeforeST_Standard_Round2>" + "NA"
											+ "</PremiumBeforeST_Standard_Round2>"
											+"<PremiumBeforeST_Classic_Round2>" + outputArr[16]
											+ "</PremiumBeforeST_Classic_Round2>"
											+"<PremiumBeforeST_Enhanced_Round2>" + "NA"
											+ "</PremiumBeforeST_Enhanced_Round2>");
					if(sampoorncancersurakshabean.getPlantype().equals("Standard"))
					{
						retVal.append("<basicPrem>"+outputArr[15]+"</basicPrem>");
					}
					else if(sampoorncancersurakshabean.getPlantype().equals("Classic"))
					{
						retVal.append("<basicPrem>"+outputArr[16]+"</basicPrem>");
					}
					else
					{
						retVal.append("<basicPrem>"+outputArr[17]+"</basicPrem>");
					}
					
					retVal.append("</SampoornCancerSuraksha>");
					// System.out.println("output " + retVal.toString());
				}
				else
				{
				
				

						retVal.append("<?xml version='1.0' encoding='utf-8' ?><SampoornCancerSuraksha>");
						retVal.append("<errCode>0</errCode>");
						retVal.append("<PremiumBeforeST_Standard>" + outputArr[0]
								+ "</PremiumBeforeST_Standard>"
								+ "<PremiumBeforeST_Classic>" + outputArr[1]
								+ "</PremiumBeforeST_Classic>"
								+ "<PremiumBeforeST_Enhanced>" + outputArr[2]
								+ "</PremiumBeforeST_Enhanced>"						
								+ "<GST_Standard>"+ outputArr[3] 
								+ "</GST_Standard>"
								+ "<GST_Classic>" + outputArr[4]
								+ "</GST_Classic>" 
								+ "<GST_Enhanced>"+ outputArr[5] 
								+ "</GST_Enhanced>"
								+ "<PremiumWithServTax_Standard>" + outputArr[6]
								+ "</PremiumWithServTax_Standard>"
								+ "<PremiumWithServTax_Classic>" + outputArr[7]
								+ "</PremiumWithServTax_Classic>"
								+ "<PremiumWithServTax_Enhanced>" + outputArr[8]
								+ "</PremiumWithServTax_Enhanced>"
								+ "<BasicServiceTax_Standard>" + outputArr[9]
								+ "</BasicServiceTax_Standard>"
								+ "<BasicServiceTax_Classic>" + outputArr[10]
								+ "</BasicServiceTax_Classic>"
								+ "<BasicServiceTax_Enhanced>" + outputArr[11]
								+ "</BasicServiceTax_Enhanced>"
								+ "<SBCServiceTax_Standard>" + outputArr[12]
								+ "</SBCServiceTax_Standard>"
								+ "<SBCServiceTax_Classic>" + outputArr[13]
								+ "</SBCServiceTax_Classic>"
								+ "<SBCServiceTax_Enhanced>" +outputArr[14]
								+ "</SBCServiceTax_Enhanced>"
								+"<PremiumBeforeST_Standard_Round2>" + outputArr[15]
										+ "</PremiumBeforeST_Standard_Round2>"
										+"<PremiumBeforeST_Classic_Round2>" + outputArr[16]
										+ "</PremiumBeforeST_Classic_Round2>"
										+"<PremiumBeforeST_Enhanced_Round2>" + outputArr[17]
										+ "</PremiumBeforeST_Enhanced_Round2>");
						if(sampoorncancersurakshabean.getPlantype().equals("Standard"))
						{
							retVal.append("<basicPrem>"+outputArr[15]+"</basicPrem>");
						}
						else if(sampoorncancersurakshabean.getPlantype().equals("Classic"))
						{
							retVal.append("<basicPrem>"+outputArr[16]+"</basicPrem>");
						}
						else
						{
							retVal.append("<basicPrem>"+outputArr[17]+"</basicPrem>");
						}
						
						retVal.append("</SampoornCancerSuraksha>");
						// System.out.println("output " + retVal.toString());
					}
				}
			catch(Exception e)
			{
				retVal.append("<?xml version='1.0' encoding='utf-8' ?><SampoornCancerSuraksha>" 
						+ "<errCode>1</errCode>" +
						"<errorMessage>" + e.getMessage() + "</errorMessage></SampoornCancerSuraksha>");
			}
			



		}
		else
		{
			retVal.append("<?xml version='1.0' encoding='utf-8' ?><SampoornCancerSuraksha>" 
					+ "<errCode>1</errCode>" +
					"<errorMessage>" + error + "</errorMessage></SampoornCancerSuraksha>");
		}
//		System.out.print( retVal.toString());
		return retVal.toString();
	}

	private String[] getOutput(String string,
			SampoorncancersurakshaBean sampoorncancersurakshabean) {
		

//		String BasePremiumRate = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getBasePremiumRate(sampoorncancersurakshabean.getGender(), sampoorncancersurakshabean.getPlantype(), sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm()));
		
		sampoorncancersurakshabusinesslogic=new SampoorncancersurakshaBusinesslogic();
		String PremiumBeforeST_Standard = "";
		String PremiumBeforeST_Classic = "";
		String PremiumBeforeST_Enhanced = "";
		String PremiumBeforeST_Standard_Round2 = "";
		String PremiumBeforeST_Classic_Round2 = "";
		String PremiumBeforeST_Enhanced_Round2 = "";
		
		
		
	/*	System.out.println("staff "+sampoorncancersurakshabean.getIsForStaffOrNot());
		System.out.println("staff "+sampoorncancersurakshabean.getIsJammuResident());
		System.out.println("staff "+sampoorncancersurakshabean.getGender());
		System.out.println("staff "+sampoorncancersurakshabean.getAge());
		System.out.println("staff "+sampoorncancersurakshabean.getPlantype());
		System.out.println("staff "+sampoorncancersurakshabean.getPolicyterm());
		System.out.println("staff "+sampoorncancersurakshabean.getPremiumFrequency());
		System.out.println("staff "+sampoorncancersurakshabean.getSumAssured());*/
		

		String BasePremiumRate_Standard =commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getBasePremiumRate(sampoorncancersurakshabean.getGender(),"Standard", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm()));
		String BasePremiumRate_Classic =commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getBasePremiumRate(sampoorncancersurakshabean.getGender(),"Classic", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm()));
		String BasePremiumRate_Enhanced =commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getBasePremiumRate(sampoorncancersurakshabean.getGender(),"Enhanced", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm()));
			

		
		
		
		String StaffRebate = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getStaffRebate(sampoorncancersurakshabean.getIsForStaffOrNot()));
		
		String SARebate = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getSARebate(sampoorncancersurakshabean.getSumAssured()));
		
//		String BasePremiumRateWithoutSARebate = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getBasePremiumRateWithoutSARebate(sampoorncancersurakshabean.getGender(), sampoorncancersurakshabean.getPlantype(), sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())) ;
		
		String BasePremiumRateWithoutSARebate_Standard = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getBasePremiumRateWithoutSARebate(sampoorncancersurakshabean.getGender(),"Standard", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())) ;
		String BasePremiumRateWithoutSARebate_Classic = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getBasePremiumRateWithoutSARebate(sampoorncancersurakshabean.getGender(),"Classic", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())) ;
		String BasePremiumRateWithoutSARebate_Enhanced = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getBasePremiumRateWithoutSARebate(sampoorncancersurakshabean.getGender(), "Enhanced", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())) ;
		
		
//		String StaffDiscountRate = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getStaffDiscountRate(sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(), sampoorncancersurakshabean.getPlantype(), sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()));
		String StaffDiscountRate_Standard = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getStaffDiscountRate(sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Standard", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()));
		String StaffDiscountRate_Classic = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getStaffDiscountRate(sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Classic", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()));
		String StaffDiscountRate_Enhanced = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getStaffDiscountRate(sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(), "Enhanced", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()));
		
		
		
		
		
//		String FinalBasePremiumRate = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getFinalBasePremiumRate(sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(), sampoorncancersurakshabean.getPlantype(), sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()));
		String FinalBasePremiumRate_Standard = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getFinalBasePremiumRate(sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Standard", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()));
		String FinalBasePremiumRate_Classic = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getFinalBasePremiumRate(sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Classic", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()));
		String FinalBasePremiumRate_Enhanced = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getFinalBasePremiumRate(sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Enhanced", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()));
		
		
//		String AnnualPremium = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getAnnualPremium(sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(), sampoorncancersurakshabean.getPlantype(), sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()));
		String AnnualPremium_Standard = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getAnnualPremium(sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Standard", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()));
		String AnnualPremium_Classic = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getAnnualPremium(sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Classic", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()));
		String AnnualPremium_Enhanced = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getAnnualPremium(sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Enhanced", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()));
		
		
//		String PremiumBeforeST = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremium(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(), sampoorncancersurakshabean.getPlantype(), sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()));
		
		if(OnlineDisc.equals("true"))
		{
		 PremiumBeforeST_Standard = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremiumOnline(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Standard", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())));
		 PremiumBeforeST_Classic = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremiumOnline(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Classic", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())));
		 PremiumBeforeST_Enhanced = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremiumOnline(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Enhanced", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())));
			
		 
		 PremiumBeforeST_Standard_Round2 = commonForAllProd.getRoundOffLevel2New(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremiumOnline(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Standard", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())));
		 PremiumBeforeST_Classic_Round2 = commonForAllProd.getRoundOffLevel2New(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremiumOnline(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Classic", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())));
		 PremiumBeforeST_Enhanced_Round2 = commonForAllProd.getRoundOffLevel2New(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremiumOnline(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Enhanced", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())));
			
		}
		else
		{
		
//		 PremiumBeforeST_Standard = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremium(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Standard", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())));
		 PremiumBeforeST_Standard = commonForAllProd.getRoundUp(commonForAllProd.getRoundOffLevel2New(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremium(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Standard", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()))));
//		 PremiumBeforeST_Classic = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremium(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Classic", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())));
		 PremiumBeforeST_Classic = commonForAllProd.getRoundUp(commonForAllProd.getRoundOffLevel2New(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremium(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Classic", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()))));
//		 PremiumBeforeST_Enhanced = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremium(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Enhanced", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())));
		 PremiumBeforeST_Enhanced = commonForAllProd.getRoundUp(commonForAllProd.getRoundOffLevel2New(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremium(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Enhanced", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured()))));
		
		 PremiumBeforeST_Standard_Round2 = commonForAllProd.getRoundOffLevel2New(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremium(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Standard", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())));
		 PremiumBeforeST_Classic_Round2 = commonForAllProd.getRoundOffLevel2New(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremium(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Classic", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())));
		 PremiumBeforeST_Enhanced_Round2 = commonForAllProd.getRoundOffLevel2New(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremium(sampoorncancersurakshabean.getPremiumFrequency(),sampoorncancersurakshabean.getIsForStaffOrNot(),sampoorncancersurakshabean.getGender(),"Enhanced", sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(),sampoorncancersurakshabean.getSumAssured())));
		
		}
//		String loadingfreq = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getLoadingFrequencyPremium(sampoorncancersurakshabean.getPremiumFrequency()));
		
//		String PremiumBeforeST = commonForAllProd.getRoundOffLevel2New(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getPremiumbeforeST(sampoorncancersurakshabean.getGender(), sampoorncancersurakshabean.getPlantype(), sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(), sampoorncancersurakshabean.getSumAssured(), sampoorncancersurakshabean.getIsForStaffOrNot(), sampoorncancersurakshabean.getPremiumFrequency())));
		
//		String PremiumWithoutServTax = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getMonthlyPremium_1stYear(sampoorncancersurakshabean.getGender(), sampoorncancersurakshabean.getPlantype(), sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(), sampoorncancersurakshabean.getSumAssured(), sampoorncancersurakshabean.getIsForStaffOrNot(), sampoorncancersurakshabean.getPremiumFrequency()));
		
		
		
//		String basicServiceTax = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getBasicServiceTax(sampoorncancersurakshabean.getIsJammuResident(),sampoorncancersurakshabean.getGender(), sampoorncancersurakshabean.getPlantype(), sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(), sampoorncancersurakshabean.getSumAssured(), sampoorncancersurakshabean.getIsForStaffOrNot(), sampoorncancersurakshabean.getPremiumFrequency()));
		
		String basicServiceTax_Standard = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getBasicServiceTax(sampoorncancersurakshabean.getIsJammuResident(),PremiumBeforeST_Standard)));
		String basicServiceTax_Classic = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getBasicServiceTax(sampoorncancersurakshabean.getIsJammuResident(),PremiumBeforeST_Classic)));
		String basicServiceTax_Enhanced = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getBasicServiceTax(sampoorncancersurakshabean.getIsJammuResident(),PremiumBeforeST_Enhanced)));
		
		
//		String SBCServiceTax = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getSBC(sampoorncancersurakshabean.getIsJammuResident(),sampoorncancersurakshabean.getGender(), sampoorncancersurakshabean.getPlantype(), sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(), sampoorncancersurakshabean.getSumAssured(), sampoorncancersurakshabean.getIsForStaffOrNot(), sampoorncancersurakshabean.getPremiumFrequency()));
		String SBCServiceTax_Standard = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getSBC(sampoorncancersurakshabean.getIsJammuResident(),PremiumBeforeST_Standard)));
		String SBCServiceTax_Classic = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getSBC(sampoorncancersurakshabean.getIsJammuResident(),PremiumBeforeST_Classic)));
		String SBCServiceTax_Enhanced =commonForAllProd.getRoundUp( commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getSBC(sampoorncancersurakshabean.getIsJammuResident(),PremiumBeforeST_Enhanced)));
		
//		String KKCServiceTax = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getKKC(sampoorncancersurakshabean.getIsJammuResident(),sampoorncancersurakshabean.getGender(), sampoorncancersurakshabean.getPlantype(), sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(), sampoorncancersurakshabean.getSumAssured(), sampoorncancersurakshabean.getIsForStaffOrNot(), sampoorncancersurakshabean.getPremiumFrequency()));
		String KKCServiceTax_Standard = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getKKC(sampoorncancersurakshabean.getIsJammuResident(),PremiumBeforeST_Standard)));
		String KKCServiceTax_Classic = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getKKC(sampoorncancersurakshabean.getIsJammuResident(),PremiumBeforeST_Classic)));
		String KKCServiceTax_Enhanced =commonForAllProd.getRoundUp( commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getKKC(sampoorncancersurakshabean.getIsJammuResident(),PremiumBeforeST_Enhanced)));
		
//		String TotalTaxes = commonForAllProd.getStringWithout_E(sampoorncancersurakshabusinesslogic.getTotalTaxes(sampoorncancersurakshabean.getIsJammuResident(),sampoorncancersurakshabean.getGender(), sampoorncancersurakshabean.getPlantype(), sampoorncancersurakshabean.getAge(), sampoorncancersurakshabean.getPolicyterm(), sampoorncancersurakshabean.getSumAssured(), sampoorncancersurakshabean.getIsForStaffOrNot(), sampoorncancersurakshabean.getPremiumFrequency()));
		String TotalTaxes_Standard =commonForAllProd.getStringWithout_E(Double.parseDouble(basicServiceTax_Standard) + Double.parseDouble(SBCServiceTax_Standard) + Double.parseDouble(KKCServiceTax_Standard)) ;
		String TotalTaxes_Classic = commonForAllProd.getStringWithout_E(Double.parseDouble(basicServiceTax_Classic) + Double.parseDouble(SBCServiceTax_Classic) + Double.parseDouble(KKCServiceTax_Classic)) ;
		String TotalTaxes_Enhanced = commonForAllProd.getStringWithout_E(Double.parseDouble(basicServiceTax_Enhanced) + Double.parseDouble(SBCServiceTax_Enhanced) + Double.parseDouble(KKCServiceTax_Enhanced)) ;
		
		String PremiumWithServTax_Standard = commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(Double.parseDouble(PremiumBeforeST_Standard)+Double.parseDouble(TotalTaxes_Standard)));
		String PremiumWithServTax_Classic =commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(Double.parseDouble(PremiumBeforeST_Classic)+Double.parseDouble(TotalTaxes_Classic)));
		String PremiumWithServTax_Enhanced =commonForAllProd.getRoundUp(commonForAllProd.getStringWithout_E(Double.parseDouble(PremiumBeforeST_Enhanced)+Double.parseDouble(TotalTaxes_Enhanced)));
		
//		System.out.print("StaffDiscountRate_Standard = "+StaffDiscountRate_Standard+",StaffDiscountRate_Classic = "+StaffDiscountRate_Classic+",StaffDiscountRate_Enhanced="+StaffDiscountRate_Enhanced);
		
		return new String []
				{PremiumBeforeST_Standard,				
				PremiumBeforeST_Classic,
				PremiumBeforeST_Enhanced,				
				TotalTaxes_Standard,
				TotalTaxes_Classic,
				TotalTaxes_Enhanced,
				PremiumWithServTax_Standard,
				PremiumWithServTax_Classic,
				PremiumWithServTax_Enhanced,
				basicServiceTax_Standard,
				basicServiceTax_Classic,
				basicServiceTax_Enhanced,
				SBCServiceTax_Standard,
				SBCServiceTax_Classic,
				SBCServiceTax_Enhanced,
				 PremiumBeforeST_Standard_Round2,
				 PremiumBeforeST_Classic_Round2,
				 PremiumBeforeST_Enhanced_Round2};
		


	}

	private String valminprem(String val,String premFreq)
	{
		boolean valminprem = true ;
		String error="";
		if(Double.parseDouble(val)<600 && premFreq.equals("Yearly"))
		{
			valminprem = false ;
			error="Minimum Premium for Yearly Frequency mode must be greater than 600";
			
		}
		else if(Double.parseDouble(val)<300 && premFreq.equals("Half-Yearly"))
		{
				valminprem = false ;
				error="Minimum Premium for Half - Yearly Frequency mode must be greater than 300";

		}
		else if(Double.parseDouble(val)<150 && premFreq.equals("Quarterly"))
		{
			valminprem = false ;
			error="Minimum Premium for Quarterly Frequency mode must be greater than 150";
		}
		else if(Double.parseDouble(val)<50 && premFreq.equals("Monthly"))
		{
			valminprem = false ;
			error="Minimum Premium for Monthly Frequency mode must be greater than 50";
		}
		else
		{
			valminprem = true ;
		}
		return error;
	}

}
