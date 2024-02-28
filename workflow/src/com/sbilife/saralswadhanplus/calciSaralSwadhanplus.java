package com.sbilife.saralswadhanplus;


public class calciSaralSwadhanplus {
	public String getPremiumSaralSwadhanPlus(String age, String gender,
			  String policyTerm, String premFreq, String premiumamount,String KFC) {
			  SaralSwadhanPlus saralSwadhanPlus = new SaralSwadhanPlus(); 
			  return saralSwadhanPlus.CalculatePrem(age, gender, policyTerm, premFreq,
			 premiumamount,KFC); }
	public static void main(String[] args) {
		calciSaralSwadhanplus obj = new calciSaralSwadhanplus();
		String output=obj.getPremiumSaralSwadhanPlus("26","male", "11", "annual","4500", "keral");
		System.out.println(output);

}
}
