package com.sbilife.SaralpensionNext;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.text.StyledEditorKit.BoldAction;

import com.sbilife.common.CommonForAllProd;

public class SaralPensionNextBusinessLogic {
	
	private CommonForAllProd cfap=null; 
	private SaralPensionNextBean saralPensionBean=null;
	private SaralPensionNextProperties prop=null;
	private SaralPensionNextDB objDB = null;
	private static final int minAge=40;
	private static final int maxAge=80;
	public SaralPensionNextBusinessLogic(SaralPensionNextBean saralPensionBean) {
		// TODO Auto-generated constructor stub
		cfap = new CommonForAllProd();
		this.saralPensionBean = saralPensionBean;
		prop = new SaralPensionNextProperties();
		objDB = new SaralPensionNextDB();
	}
	
	public double getSingleAnnutyRate() {
		double b = 0;
		double[] AnnuityRatesForSingleLife = objDB.getAnnuityRatesForSingleLife();

		b = AnnuityRatesForSingleLife[saralPensionBean.getfAnnuitantAge() - 40];
		return b;
	}
	
	public double getJoinAnnunityRate() {
		double d = 0;
		int rowNo = saralPensionBean.getfAnnuitantAge() -(minAge-1);
		int colNo = saralPensionBean.getsAnnuitantAge() -(minAge-1);
		int h_min = (rowNo-1) * ((maxAge-minAge)+1);
		int h_max = rowNo * ((maxAge-minAge)+1)-1;
		double[] getAnnuityRatesForJoinLife = objDB.getAnnuityRatesForJoinLife();
		d = getAnnuityRatesForJoinLife[h_min+colNo-1];
		//System.out.println("getJoinAnnunityRate() : "+d+",rowno"+rowNo+",colNo"+colNo+",hmin"+h_min+",hmax"+h_max);
		return d;
		
	}
	//B2
	public int getFreqModeFactor() {
		if(saralPensionBean.getpayoutMode()=="Annual") 
			return 1;	
		else if(saralPensionBean.getpayoutMode()=="Half yearly") 
			return 2;	
		else if(saralPensionBean.getpayoutMode()=="Quarterly") 
			return 4;	
		else  
			return 12;	
	}
	//B3
	public double getFreqModalFactor() {
		if(saralPensionBean.getpayoutMode()=="Annual") 
			return 1.035;	
		else if(saralPensionBean.getpayoutMode()=="Half yearly") 
			return 1.0175;	
		else if(saralPensionBean.getpayoutMode()=="Quarterly") 
			return 1.0075;	
		else  
			return 0;	
	}
	//B4
	public double getPremiumRate() {
		if (saralPensionBean.getAnnuityOption()=="Option1")
			return getSingleAnnutyRate();
		else 
			return getJoinAnnunityRate();
		
	}
	//B5
	public double getpremRateModelFactor() {
		
		if(saralPensionBean.getpayoutMode()=="Monthly") {
			
		   return getPremiumRate();
		}
		else {
			//System.out.println("getPremiumRate()*getFreqModalFactor()" +getPremiumRate()*getFreqModalFactor());
			return getPremiumRate()*getFreqModalFactor();
		    
		}
		
		 
	}
	//B6
	public double getHighrPurchesPrice() {
		if (saralPensionBean.getSumAssured() >=0 && saralPensionBean.getSumAssured()<=500000)
			return 0;
		else if (saralPensionBean.getSumAssured()>500000 && saralPensionBean.getSumAssured()<=1000000)
			return 2.75;
		else if (saralPensionBean.getSumAssured()>1000000 && saralPensionBean.getSumAssured()<=2500000)
			return 3.75;
		else if (saralPensionBean.getSumAssured()>2500000)
			return 4.75;
		else 
			return 0;
		
	}
	//B15
    public double getAdditionalAmt() {
    	if(saralPensionBean.getSouceofBuss()=="New Proposal" || saralPensionBean.getSouceofBuss()=="Open Market Option (Any other Life Insurance Company pension policy)" )
    		return 0;
    	else 
    		return saralPensionBean.getAdditionalAmt();
		
	}
    //B17
    public double ServiceTax() {
    	double basicST=0;
    	
    	DecimalFormat df = new DecimalFormat("#.####");
    	DecimalFormat df1 = new DecimalFormat("#");
    	df.setRoundingMode(RoundingMode.FLOOR);
    	df1.setRoundingMode(RoundingMode.CEILING);
    	if(saralPensionBean.getState()==true) {
    		basicST = Double.parseDouble(df.format(saralPensionBean.getSumAssured() - saralPensionBean.getSumAssured()/(1+SaralPensionNextProperties.KKCServiceTax)));
    	}
    	else 
    	{
    		basicST =Double.parseDouble(df.format(saralPensionBean.getSumAssured() - saralPensionBean.getSumAssured()/(1+SaralPensionNextProperties.serviceTax)));
    	}
    	basicST = Double.parseDouble(df1.format(basicST));
    	return basicST;
    	
    }
    //B16
    public double getTotalAmt() {
    	double TotAmt=0;
    	TotAmt=(saralPensionBean.getSumAssured() + getAdditionalAmt());
    	//System.out.println("TotAmt" +TotAmt);
    	return TotAmt;
    	
    }
    //B18
    public double getAmtAfterST() {
    	double AmtafterST=0;
    	AmtafterST=(getTotalAmt()-(ServiceTax()));  
    	//System.out.println("AmtafterST" +AmtafterST);
    	return AmtafterST;
    }
    
    //Vesting Amount
    public String getVestingAmt() {
    	
    	if(saralPensionBean.getSouceofBuss()=="Vesting/Death/ Surrender of existing SBI Life's pension policy")
    		return  Double.toString(saralPensionBean.getSumAssured());
    	else
    		return "-";

    }
    
    //E18
    public double getridercal() {
    	double RiderAmt=0;
    	DecimalFormat df = new DecimalFormat("#");
    	df.setRoundingMode(RoundingMode.FLOOR);
    	RiderAmt=(getAmtAfterST()/1000);
    	//System.out.println("RiderAmt" +RiderAmt);
    	return Double.parseDouble(df.format(RiderAmt))*1000;
    }
    
    //B19
    public double getAnnuityAmt() {
    	double annuityAmt = 0;
    	DecimalFormat df = new DecimalFormat("#");
    	df.setRoundingMode(RoundingMode.HALF_DOWN);
    	/*System.out.println("getpremRateModelFactor() : "+getpremRateModelFactor());
    	System.out.println("getHighrPurchesPrice() : "+getHighrPurchesPrice());
    	System.out.println("getFreqModeFactor() : "+getFreqModeFactor());
    	System.out.println("getAmtAfterST() : "+getAmtAfterST());*/
    	if(saralPensionBean.getSouceofBuss()=="Vesting/Death/ Surrender of existing SBI Life's pension policy" 
    			|| saralPensionBean.getChannelD()=="Direct") 
    		annuityAmt =((getpremRateModelFactor()+getHighrPurchesPrice())/getFreqModeFactor())/980*getAmtAfterST()	;	
        else if(saralPensionBean.getisStaff()== true)     	   
        	annuityAmt= ((getpremRateModelFactor()+getHighrPurchesPrice())/getFreqModeFactor())/980*getAmtAfterST();	
    	 else 
        	annuityAmt= ((getpremRateModelFactor()+getHighrPurchesPrice())/getFreqModeFactor())/1000*getAmtAfterST();	
    	//System.out.println("ogval : "+annuityAmt+" | converted : "+df.format(annuityAmt));
        
    	return Double.parseDouble(df.format(annuityAmt));		
    }
    
  //Grid SurvivalBenefit
    public double getSurvivalBenefit( ){
    	double survivalB=0;
    	DecimalFormat df = new DecimalFormat("#");
    	df.setRoundingMode(RoundingMode.FLOOR);
    	
    	if(saralPensionBean.getpayoutMode()=="Half yearly")
    		survivalB=(getAnnuityAmt()*2);
    	
    	else if(saralPensionBean.getpayoutMode()=="Quarterly")
    		survivalB= (getAnnuityAmt()*4);
    	
    	else if(saralPensionBean.getpayoutMode()=="Monthly")
    		survivalB= (getAnnuityAmt()*12);
    	else
    		survivalB= getAnnuityAmt();
    	//System.out.println("survivalB" + survivalB);
    	return Double.parseDouble(df.format(survivalB));
    }
    //Grid OtherBenefit
    public String getOtherBenefit(int policyYear) {
    	if(policyYear<=(99-saralPensionBean.getfAnnuitantAge()))
    		return "0";
    	else
    		return "-";
    }
    //single or annualized prem
    public String getSingleOrAnnualizedPrem(int policyYear) {
    	if((policyYear-1)<=(99-saralPensionBean.getfAnnuitantAge()))
    		return "0";
    	else
    		return "-";
    }
    //Grid DeathBenefit
    public String getDeathBenefit() {
    	DecimalFormat df = new DecimalFormat("#");
    	df.setRoundingMode(RoundingMode.FLOOR);
    	
    	if(saralPensionBean.getAnnuityOption()=="Option2")
    		return "Rs. "+String.valueOf(getSurvivalBenefit())+
    				", Till death of 2nd Annuitant and Rs. "+ String.valueOf(getAmtAfterST())+
    				" on death of 2nd Annuitant";
    	else 
    		return df.format(getAmtAfterST());
    		
    }
    //Grid GuaranteedasurrenderValue
    public double getminGuaranteedSurrValue() {
    
    	DecimalFormat df = new DecimalFormat("#");
    	df.setRoundingMode(RoundingMode.FLOOR);
    	double minGuaranteedSurr=0;
    	minGuaranteedSurr=(getAmtAfterST()*0.95);
    	return Double.parseDouble(df.format(minGuaranteedSurr));
    }
    
    

}
