package com.sbilife.smartmoneybackgold;

public class resCalci {public static void main(String[] args) {
	SmartMoneyBackGoldNew gold =new SmartMoneyBackGoldNew();
	String outout = gold.CalculatePrem("false", "false", "Option 1", "42", "Male", "15", "8",
			"Single Premium", "Yearly", "500000", "false", "", "", "false", "", "", "false", "", "", "false", "", "",
			"false", "", "false", "false");
	System.out.println(outout);
	
	
}
}
