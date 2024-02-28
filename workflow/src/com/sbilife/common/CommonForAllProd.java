package com.sbilife.common;

//Import required packages

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

public class CommonForAllProd {

    /****************** Retire Smart start************************/

    public double roundDown(double a, int scale) {
        BigDecimal value = new BigDecimal(a);
        value = value.setScale(scale, RoundingMode.DOWN);
        return value.doubleValue();
    }

    public double roundUP(double a, int scale) {
        BigDecimal value = new BigDecimal(a);
        value = value.setScale(scale, RoundingMode.UP);
        return value.doubleValue();
    }

    public double roundUpWithScale(double a, int scale) {
        BigDecimal value = new BigDecimal(a);
        value = value.setScale(scale, RoundingMode.UP);
        return value.doubleValue();
    }
    /****************** Retire Smart end************************/

    public String getRoundOffLevel2_1(String number) {
        String first = "", second = "";
        String retVal = "0";
        String dummy = "" + number;
        // System.out.println("dummy" + dummy);

        String[] dummyArr = split(dummy, ".");
        // System.out.println("" + dummyArr[0]);
        // System.out.println("" + dummyArr[1]);

        if (dummyArr[1].length() == 1) {
            retVal = dummy + "0";
        } else if (dummyArr[1].length() == 2) {
            retVal = dummy;
        } else if (dummyArr[1].length() > 2) {
            first = dummyArr[1].substring(0, 2);
            second = dummyArr[1].substring(2, 3);

            // System.out.println("first" + first);
            // System.out.println("second" + second);

            if (Integer.parseInt(second) >= 5) {
                if (Integer.parseInt(first) < 9) {
                    String temp = first.substring(1, 2);
                    // System.out.println("temp "+ temp);
                    first = "0" + String.valueOf(Integer.parseInt(temp) + 1);
                } else {
                    first = String.valueOf(Integer.parseInt(first) + 1);
                }

                // System.out.println("first changed" + first);
            }
            if (Integer.parseInt(first) > 99)
                retVal = (Long.parseLong(dummyArr[0]) + 1) + "." + 00;
            else
                retVal = dummyArr[0] + "." + first;

        }
        return retVal;
    }

    /**********************  Smart Power *******************************************/


    public String getRoundOffLevel2(String number) {
        String first = "", second = "";
        String retVal = "0";
        String dummy = "" + number;
        // System.out.println("dummy" + dummy);

        String[] dummyArr = split(dummy, ".");
        // System.out.println("" + dummyArr[0]);
        // System.out.println("" + dummyArr[1]);

        if (dummyArr[1].length() == 1) {
            retVal = dummy + "0";
        } else if (dummyArr[1].length() == 2) {
            retVal = dummy;
        } else if (dummyArr[1].length() > 2) {
            first = dummyArr[1].substring(0, 2);
            second = dummyArr[1].substring(2, 3);

            // System.out.println("first" + first);
            // System.out.println("second" + second);

            if (Integer.parseInt(second) >= 5) {
                if (Integer.parseInt(first) < 9) {
                    String temp = first.substring(1, 2);
                    // System.out.println("temp "+ temp);
                    first = "0" + String.valueOf(Integer.parseInt(temp) + 1);
                } else {
                    first = String.valueOf(Integer.parseInt(first) + 1);
                }

                // System.out.println("first changed" + first);
            }
            if (Integer.parseInt(first) > 99)
                retVal = (Integer.parseInt(dummyArr[0]) + 1) + "." + 00;
            else
                retVal = dummyArr[0] + "." + first;

        }
        return retVal;
    }


    /*************************** Smart Power ****************************************************/


    //This method is Used in Smart Power
    public String getRoundUp_Level2(String toBeRounded) {
        int index = toBeRounded.indexOf('.');
        int val = 2;

        int val_afterdecimal = toBeRounded.length() - 1 - index;

        if (val_afterdecimal < 2) {
            return getStringWithout_E(Math.round(Double.parseDouble(toBeRounded) * 100.0) / 100.0);
        } else {


            try {
                String a = toBeRounded.substring((index + 3), (toBeRounded.length() - 1));
                if (a.equals("")) {
                    val = 2;
                } else {
                    val = 3;
                }

                if (Long.parseLong(toBeRounded.substring((index + val), (toBeRounded.length() - 1))) > 0) {
                    String dummy = "" + (Double.parseDouble(toBeRounded.substring(0, (index + 3))) + 0.01);
                    try {
                        String dummy_ = dummy.substring(index + 3, dummy.length());
                        boolean status = true;
                        for (int i = 0; i < 5; i++) {
                            if (dummy_.charAt(i) != '9') {
                                status = false;
                            }
                        }
                        if (status) {
                            String returnVal = ("" + ((Double.parseDouble(toBeRounded.substring(0, (index + 3))) + 0.01) + 0.001)).substring(0, (index + 3));
                            return returnVal;
                        }
                        if (dummy_.substring(0, 6).equals("999999")) {
                            status = false;
                        }

                        if (dummy_.substring(0, 6).equals("000000")) {
                            return dummy.substring(0, index + 3);
                        }
                    } catch (Exception e) {
                        return dummy;
                    }
                    return dummy;
                } else {
                    return "" + Double.parseDouble(toBeRounded.substring(0, (index + 3)));
                }
            } catch (StringIndexOutOfBoundsException e) {
                return "" + Double.parseDouble(toBeRounded);
            }

        }

    }


    //To get age in year [Used for Rinn Raksha product Only]
    //To get age in year [Used for Rinn Raksha product Only]
    //Month Rank Mapping is done here
    public int getMappedMonth(String monthStr) {
        if (monthStr.equals("Jan")) {
            return 1;
        } else if (monthStr.equals("Feb")) {
            return 2;
        } else if (monthStr.equals("Mar")) {
            return 3;
        } else if (monthStr.equals("Apr")) {
            return 4;
        } else if (monthStr.equals("May")) {
            return 5;
        } else if (monthStr.equals("Jun")) {
            return 6;
        } else if (monthStr.equals("Jul")) {
            return 7;
        } else if (monthStr.equals("Aug")) {
            return 8;
        } else if (monthStr.equals("Sep")) {
            return 9;
        } else if (monthStr.equals("Oct")) {
            return 10;
        } else if (monthStr.equals("Nov")) {
            return 11;
        } else if (monthStr.equals("Dec")) {
            return 12;
        } else {
            return 0;
        }
    }


    //Returns RoundUp value[Used to RoundUp a value which is in String format][This Method is Overloaded]
    public String getRoundUp(String dummy) {
        String[] dummyArr = split(dummy, ".");
        try {
            if (Long.parseLong(dummyArr[1]) > 0) {
                return "" + (Long.parseLong(dummyArr[0]) + 1);
            } else {
                return dummyArr[0];
            }
        } catch (Exception e) {
            return dummyArr[0];
        }
    }

    //Returns
    public String getIntegerVal(String dummy) {
        String[] dummyArr = split(dummy, ".");
        try {
            if (Long.parseLong(dummyArr[1]) > 0) {
                return "" + (Long.parseLong(dummyArr[0]));
            } else {
                return dummyArr[0];
            }
        } catch (Exception e) {
            return dummyArr[0];
        }
    }

    //Convert & return Product Related Constant Data which is in String form into an array format
    public String[] split(String original, String separator) {
        Vector<String> nodes = new Vector<String>();
        int index = original.indexOf(separator);
        while (index >= 0) {
            nodes.addElement(original.substring(0, index));
            original = original.substring(index + separator.length());
            index = original.indexOf(separator);
        }
        nodes.addElement(original);
        String[] result = new String[nodes.size()];
        if (nodes.size() > 0) {
            for (int loop = 0; loop < nodes.size(); loop++)
                result[loop] = (String) nodes.elementAt(loop);
        }
        return result;
    }


    public String roundUp_Level2_FS(String strToBeRounded) {
        String LongStrToBeRounded = strToBeRounded + "00000000000000";
        int decIndex = LongStrToBeRounded.indexOf('.');
        String strToReturn = null;
        if ((LongStrToBeRounded.charAt(decIndex + 3) != '9') && (LongStrToBeRounded.substring(decIndex + 4, decIndex + 8).equals("9999")) && (Integer.parseInt("" + LongStrToBeRounded.charAt(decIndex + 3)) + 1 >= 5)) {
            try {
                strToReturn = ((getStringWithout_E(Double.parseDouble(LongStrToBeRounded) + 0.01))).substring(0, (decIndex + 3));
            } catch (Exception e) {
                strToReturn = (("" + (Double.parseDouble(LongStrToBeRounded) + 0.01)));
            }
        } else if (Integer.parseInt("" + LongStrToBeRounded.charAt(decIndex + 3)) >= 5) {
            try {
                if (LongStrToBeRounded.substring(decIndex + 3, decIndex + 7).equals("9999")) {
                    strToReturn = ("" + (Double.parseDouble(LongStrToBeRounded) + 0.001)).substring(0, decIndex + 3);
                } else {
                    strToReturn = ((getStringWithout_E(Double.parseDouble(LongStrToBeRounded) + 0.01))).substring(0, (decIndex + 3));
                }
            } catch (Exception e) {
                strToReturn = (("" + (Double.parseDouble(LongStrToBeRounded) + 0.01)));
            }
        } else {
            strToReturn = LongStrToBeRounded.substring(0, decIndex + 3);
        }
        return strToReturn;
    }

    public String roundUp_Level2(String strToBeRounded) {
        String LongStrToBeRounded = strToBeRounded + "0000";
//    	String LongStrToBeRounded=strToBeRounded+"000000";
        int decIndex = LongStrToBeRounded.indexOf('.');
        String strToReturn = null;
        if (Integer.parseInt("" + LongStrToBeRounded.charAt(decIndex + 3)) >= 5) {
            try {
                if (LongStrToBeRounded.substring(decIndex + 3, decIndex + 7).equals("9999")) {
                    strToReturn = ("" + (Double.parseDouble(LongStrToBeRounded) + 0.001)).substring(0, decIndex + 3);
                } else {
                    strToReturn = ((getStringWithout_E(Double.parseDouble(LongStrToBeRounded) + 0.01))).substring(0, (decIndex + 3));
                }
            } catch (Exception e) {
                strToReturn = (("" + (Double.parseDouble(LongStrToBeRounded) + 0.01)));
            }
        } else {
            strToReturn = LongStrToBeRounded.substring(0, decIndex + 3);
        }
        return strToReturn;
    }

    public String roundUp_Level2_Ceil(String strToBeRounded) {
        String LongStrToBeRounded = strToBeRounded + "0000";
        int decIndex = LongStrToBeRounded.indexOf('.');
        String strToReturn = null;
        if (Integer.parseInt("" + LongStrToBeRounded.charAt(decIndex + 3)) > 0) {
            try {
                if (LongStrToBeRounded.substring(decIndex + 3, decIndex + 7).equals("9999")) {
                    strToReturn = ("" + (Double.parseDouble(LongStrToBeRounded) + 0.001)).substring(0, decIndex + 3);
                } else {
                    strToReturn = ((getStringWithout_E(Double.parseDouble(LongStrToBeRounded) + 0.01))).substring(0, (decIndex + 3));
                }
            } catch (Exception e) {
                strToReturn = (("" + (Double.parseDouble(LongStrToBeRounded) + 0.01)));
            }
        } else {
            strToReturn = LongStrToBeRounded.substring(0, decIndex + 3);
        }
        return strToReturn;
    }


    //Returns RoundUp value[Used to RoundUp a value which is in String format][This Method is Overloaded]
    //Used in setPremiumAllocationCharge_K() method
    public String getRoundUp(String dummy, String roundUpII) {
        //System.out.println("_Received String = "+ dummy);
        String[] dummyArr = split(dummy, ".");
        try {
            if (Long.parseLong(dummyArr[1].substring(0, 8)) > 0) {
                return "" + (Integer.parseInt(dummyArr[0]) + 1);
            } else {
                return dummyArr[0];
            }
        } catch (Exception e) {
            return dummyArr[0];
        }
    }


    public int getFloorVal_Significance12(double number) {
        //System.out.println("IN -> getFloorVal_Significance12");
        for (int i = 12; i < 1200; i = i + 12) {
            //System.out.println("Outside IF -> i = "+i);
            if (number == i) {
                return i;
            } else if (number <= i) {
                return i - 12;
            }
        }
        return 0;
    }


    public int getMonthDiff(Date MembershipFormDate, Date dateOfBirth) {
        //System.out.println("----------------IN getMonthDiff()");
        String strToSplit = "" + Math.floor(((Double.parseDouble("" + (MembershipFormDate.getTime() - dateOfBirth.getTime())) / Double.parseDouble("2629800000"))));
        //System.out.println("strToSplit -> "+strToSplit);
        String[] arr = split(strToSplit, ".");
        //System.out.println("arr[0] -> "+arr[0]);
        return Integer.parseInt(arr[0]);
    }


    /************************************ Power Functions Start***************************************************/


    double powTaylor(double a, double b) {

        boolean gt1 = (Math.sqrt((a - 1) * (a - 1)) <= 1) ? false : true;
        int oc = -1, iter = 30;
        double p = a, x, x2, sumX, sumY;
        if ((b - Math.floor(b)) == 0) {
            for (int i = 1; i < b; i++) p *= a;
            return p;
        }
        x = (gt1) ? (a / (a - 1)) : (a - 1);
        sumX = (gt1) ? (1 / x) : x;
        for (int i = 2; i < iter; i++) {
            p = x;
            for (int j = 1; j < i; j++) p *= x;
            double xTemp = (gt1) ? (1 / (i * p)) : (p / i);
            sumX = (gt1) ? (sumX + xTemp) : (sumX + (xTemp * oc));
            oc *= -1;
        }
        x2 = b * sumX;
        sumY = 1 + x2;
        for (int i = 2; i <= iter; i++) {
            p = x2;
            for (int j = 1; j < i; j++) p *= x2;
            int yTemp = 2;
            for (int j = i; j > 2; j--) yTemp *= j;
            sumY += p / yTemp;
        }
        return sumY;
    }

    public double pow(double x, double y) {
        return powTaylor(x, y);
    }

/************************************ Power Functions End***************************************************/

    /**********************************Annuity start ********************************************/
    /*
     * Only for Annuity Plus
     */
    public String roundUp_Level4(String strToBeRounded) {
        //System.out.println("Required strToBeRounded -> "+ strToBeRounded);

        String LongStrToBeRounded = strToBeRounded + "0000";
        int decIndex = LongStrToBeRounded.indexOf('.');
        String strToReturn = null;
        if (Integer.parseInt("" + LongStrToBeRounded.charAt(decIndex + 5)) >= 5) {
            try {
                if (LongStrToBeRounded.substring(decIndex + 5, decIndex + 9).equals("9999")) {
                    strToReturn = ("" + (Double.parseDouble(LongStrToBeRounded) + 0.00001)).substring(0, decIndex + 5);
                } else {
                    strToReturn = ((getStringWithout_E(Double.parseDouble(LongStrToBeRounded) + 0.0001))).substring(0, (decIndex + 5));
                }
            } catch (Exception e) {
                strToReturn = (("" + (Double.parseDouble(LongStrToBeRounded) + 0.0001)));
            }
        } else {
            strToReturn = LongStrToBeRounded.substring(0, decIndex + 5);
        }
        return strToReturn;
    }

    public long getRounDownMinus3(double toBeRoundDown) {
        long longToBeRoundDown = (long) toBeRoundDown;
        return longToBeRoundDown - (longToBeRoundDown % 1000);
    }

    //Returns RoundUp value[Used to RoundUp a value which is in String format][This Method is Overloaded]
    public String getRound(String dummy) {
        String[] dummyArr = split(dummy, ".");
        try {
            if (Integer.parseInt("" + dummyArr[1].charAt(0)) >= 5) {
                return "" + (Integer.parseInt(dummyArr[0]) + 1);
            } else {
                return dummyArr[0];
            }
        } catch (Exception e) {
            return dummyArr[0];
        }
    }

    public long getDaysDiff(Date MembershipFormDate, Date dateOfBirth) {
//		System.out.println("cfap "+(MembershipFormDate.getTime()-dateOfBirth.getTime()));
//		System.out.println("cfap 1 "+(MembershipFormDate.getTime()-dateOfBirth.getTime())/86400000);
        return (MembershipFormDate.getTime() - dateOfBirth.getTime()) / 86400000;
    }


    public String getMessage(Date proposalDate, Date annuityPayoutDate, String modeOfAnnuityPayout, long noOfDaysForIntCalculation) {

        int maxAllowedDays = 0;
        if (modeOfAnnuityPayout.equals("Yearly")) {
            maxAllowedDays = 12;
        } else if (modeOfAnnuityPayout.equals("Half Yearly")) {
            maxAllowedDays = 6;
        }
        //System.out.println("$$$ -> maxAllowedDays [Input-BT95] -> "+maxAllowedDays);
        //getMonthDifference(DateField oldDate,DateField recentDate)

        //Calculate Difference in Months
        //int year=getMonthDifference(proposalDate,annuityPayoutDate)[0];
        //System.out.println("$$$ -> Year Diff -> "+year);
        //int month=getMonthDifference(proposalDate,annuityPayoutDate)[1];
        //System.out.println("$$$ -> Month Diff -> "+month);
        int totalMonths = getMonthDifference(proposalDate, annuityPayoutDate);
        //         System.out.println("$$$ -> Total Month Diff [Input [j3]]-> "+totalMonths);
        //         System.out.println("noOfDaysForIntCalculation  [BT96]->>  "+noOfDaysForIntCalculation);

        if (totalMonths >= 3 && totalMonths <= maxAllowedDays && noOfDaysForIntCalculation >= 0) {
            return "doNotDisplayMsg";
        } else {
            return "displayMsg";
        }

    }

    public int getMonthDifference(Date oldDate, Date recentDate) {
        String selDate = oldDate.toString();
        String[] selDateArr = split(selDate, " ");
        //Method Variables Declaration
        int year = 0, months = 0;
        //Dld Date
        int dd1 = Integer.parseInt(selDateArr[2]);
        int mm1 = getMappedMonth(selDateArr[1]);
        int yy1 = Integer.parseInt(selDateArr[5]);
        String[] currDateArr = split(recentDate.toString(), " ");
        //Recent Date
        int dd2 = Integer.parseInt(currDateArr[2]);
        int mm2 = getMappedMonth(currDateArr[1]);
        int yy2 = Integer.parseInt(currDateArr[5]);

        //System.out.println("DOB DD"+dd1); //12
        //System.out.println("DOB MM"+mm1); //Sep OR 9
        //System.out.println("DOB YY"+yy1); //1988

        //System.out.println("MFD DD"+dd2); // 5
        //System.out.println("MFD MM"+mm2); //Oct OR 10
        //System.out.println("MFD YY"+yy2); //2011

        //Age Calculation
        if (mm1 == mm2 && dd2 == dd1) {
            year = yy2 - yy1;
        } else if (mm1 > mm2) {
            year = yy2 - yy1 - 1;
        } else if (mm1 < mm2) {
            year = yy2 - yy1;
        } else if (mm1 == mm2 && dd2 > dd1) {
            year = yy2 - yy1;
        } else if (mm1 == mm2 && dd2 < dd1) {
            year = yy2 - yy1 - 1;
        }
        //System.out.println("Membership Form Date mm2  ->  "+mm2);
        //System.out.println("DOB  mm1 ->  "+mm1);

        if (year == 0 && mm1 == mm2 && dd2 < dd1) {
            months = 11;
        } else if (mm2 >= mm1) {
            months = months + (year * 12) + (mm2 - mm1);
        } else {
            months = months + Math.abs((year * 12) - (12 - (mm1 - mm2)));
        }
        //System.out.println("months -> "+months);
        //yearMonthArr[0]=year;
        //yearMonthArr[1]=months;

        //System.out.println("->> year => "+year);
        //System.out.println("->> mm1 => "+mm1);
        //System.out.println("->> mm1 => "+mm2);

        if (year == 0 && mm1 == mm2 && dd2 < dd1) {
            return months;
        }
        if (dd1 > dd2 && months >= 0) {
            return months - 1;
        } else {
            return months;
        }
    }

    /******************************Annuity end **************************************************/

    /**************************************Rinnraksha start *********************************************/

    /**
     * Only for RinnRaksha
     */

    //To get age in year [Used for Rinn Raksha product Only]
    public int getAgeRinnRaksha(String DOB_Borrower, String membershipDate) {
//			System.out.println("inside getage");
        String selDate = DOB_Borrower;
        String[] selDateArr = split(selDate, "/");
        // Method Variables Declaration
        int year = 0, months = 0;
        int[] yearMonthArr = new int[]{0, 0, 0};
        // Old Date
        int dd1 = Integer.parseInt(selDateArr[1]);
        int mm1 = Integer.parseInt(selDateArr[0]);

        int yy1 = Integer.parseInt(selDateArr[2].trim());
//		        System.out.println("DOB YY" + yy1); // 1988
        String[] currDateArr = split(membershipDate, "/");
        // Recent Date
        int dd2 = Integer.parseInt(currDateArr[1]);
        int mm2 = Integer.parseInt(currDateArr[0]);
        int yy2 = Integer.parseInt(currDateArr[2].trim());


        // Age Calculation
        if (mm1 == mm2 && dd2 == dd1) {
            year = yy2 - yy1;
        } else if (mm1 > mm2) {
            year = yy2 - yy1 - 1;
        } else if (mm1 < mm2) {
            year = yy2 - yy1;
        } else if (mm1 == mm2 && dd2 > dd1) {
            year = yy2 - yy1;
        } else if (mm1 == mm2 && dd2 < dd1) {
            year = yy2 - yy1 - 1;
        }

        months = (year * 12) + (mm2 - mm1);
        // System.out.println("months -> "+months);
        // System.out.println("year -> "+year);

        yearMonthArr[0] = year;
        yearMonthArr[1] = months;

        // return yearMonthArr;
        return yearMonthArr[0];

    }


    /************************************************** Rinnraksha end *********************************/


    /****Smart Power Functions******/

    public int getPowerOutput(int num, int power) {
        int output = num;
        for (int i = 1; i < power; i++) {
            output = output * num;
        }
        return output;
    }

    /********* Modified by Akshaya on 1-July-16 start **************/

    public String getStringWithout_E(double doubleVal) {

        String dStr = "" + doubleVal;

        // System.out.println("******************String with e***"+dStr );
        int indexOf_E = dStr.indexOf('E');
        int indexOf_e = dStr.indexOf('e');
        int finalIndexOfE = 0;
        String seperator = null;
        String[] strPartsByE = new String[2];
        String[] strPartsByDecimal = new String[2];
        String rawStrWithoutDecimal = null;
        if ((indexOf_E > 0) || (indexOf_e > 0)) {
            if (indexOf_E > 0) {
                seperator = "E";
                finalIndexOfE = indexOf_E;
            } else if (indexOf_e > 0) {
                seperator = "e";
                finalIndexOfE = indexOf_e;
            }
            strPartsByE = split(dStr, seperator);
            strPartsByDecimal = split(strPartsByE[0], ".");


            if (Integer.parseInt(strPartsByE[1]) < 0) {
                rawStrWithoutDecimal = strPartsByDecimal[0] + strPartsByDecimal[1];


                String zeroAfterDecimal = "";
                for (int i = 1; i < Math.abs(Integer.parseInt(strPartsByE[1])); i++) {
                    zeroAfterDecimal = zeroAfterDecimal + "0";
                }

                if (strPartsByDecimal[0].contains("-")) {

                    return "-0." + zeroAfterDecimal + String.valueOf(Math.abs(Long.parseLong(rawStrWithoutDecimal)));
                } else {
                    return "0." + zeroAfterDecimal + String.valueOf(Math.abs(Long.parseLong(rawStrWithoutDecimal)));
                }


            } else {

                rawStrWithoutDecimal = strPartsByDecimal[0] + strPartsByDecimal[1]
                        + "00000000000000000000";

                if (strPartsByDecimal[0].contains("-")) {
                    return (rawStrWithoutDecimal.substring(0,
                            2 + Integer.parseInt(strPartsByE[1])))
                            + "."
                            + rawStrWithoutDecimal.substring(
                            2 + Integer.parseInt(strPartsByE[1]),
                            5 + Integer.parseInt(strPartsByE[1]));

                } else {
                    return (rawStrWithoutDecimal.substring(0,
                            1 + Integer.parseInt(strPartsByE[1])))
                            + "."
                            + rawStrWithoutDecimal.substring(
                            1 + Integer.parseInt(strPartsByE[1]),
                            4 + Integer.parseInt(strPartsByE[1]));

                }

            }

        } else {
            return dStr;
        }
    }


//public int getAge(String DOB_Borrower,String Today)
//{
//      String selDate=DOB_Borrower;
//      String[] selDateArr=split(selDate,"/");
//      //Method Variables Declaration
//      int year=0,months=0;
//      int[] yearMonthArr=new int[]{0,0,0};
////      System.out.println("array size " + selDateArr.length);
////      System.out.println("array element0  " + selDateArr[0]);
////      System.out.println("array element1  " + selDateArr[1]);
////      System.out.println("array element2**" + selDateArr[2]+"**");
//      //Old Date
//      int dd1=Integer.parseInt(selDateArr[1]);
//      int mm1=Integer.parseInt(selDateArr[0]);
//      
//      int yy1=Integer.parseInt(selDateArr[2].trim());
////      System.out.println("DOB YY"+yy1); //1988
//      String[] currDateArr=split(Today,"/");
//      //Recent Date
//      int dd2=Integer.parseInt(currDateArr[1]);
//      int mm2=Integer.parseInt(currDateArr[0]);
//      int yy2=Integer.parseInt(currDateArr[2].trim());
//
////      System.out.println("DOB DD"+dd1); //12
////      System.out.println("DOB MM"+mm1); //Sep OR 9
////      System.out.println("DOB YY"+yy1); //1988
////      System.out.println("MFD DD"+dd2); // 5
////      System.out.println("MFD MM"+mm2); //Oct OR 10
////      System.out.println("MFD YY"+yy2); //2011
//
//      //Age Calculation
//      if(mm1==mm2&& dd2==dd1)
//      {year=yy2-yy1;}
//      else if(mm1>mm2)
//      {year=yy2-yy1-1;}
//      else if(mm1<mm2)
//      {year=yy2-yy1;}
//      else if(mm1==mm2&&dd2>dd1)
//      {year=yy2-yy1;}
//      else if(mm1==mm2&&dd2<dd1)
//      {year=yy2-yy1-1;}
//
//      months=(year*12)+(mm2-mm1);
////      System.out.println("months -> "+months);
////      System.out.println("year -> "+year);
//
//      yearMonthArr[0]=year;
//      yearMonthArr[1]=months;
//
//      return yearMonthArr[0];
//}


//public long getRoundUp1(double dummy)
//{
//	String dummy1=Double.toString(dummy);
//	String[] dummyArr=split(dummy1,".");
//	try
//	{
//		if(Long.parseLong(dummyArr[1])>0)
//		{return (Integer.parseInt(dummyArr[0])+1);}
//		else
//		{return Integer.parseInt(dummyArr[0]);}
//	}
//	catch (Exception e)
//	{return Integer.parseInt(dummyArr[0]);}
//}


/*******************************************************************************/
    /*
     * Used only in ULIP
     */
//public String getRoundUpLevelZero(String dummy)
//{
//    //System.out.println("Received String = "+ dummy);
//    String[] dummyArr=split(dummy,".");
//    try
//    {
//        if(Long.parseLong(dummyArr[1])>=5)
//        {return ""+(Integer.parseInt(dummyArr[0])+1);}
//        else
//        {return dummyArr[0];}
//    }
//    catch (Exception e)
//    {return dummyArr[0];}
//}
/*****************************************************************************/


//Returns RoundUp value[Used to RoundUp a value which is in double format]
//public String getRoundUp(double val)
//{
//    String dummy=""+val;
//    String[] dummyArr=split(dummy,".");
//    if(dummyArr[1].charAt(0)=='0')
//    {return dummyArr[0];}
//    else{return ""+((Integer.parseInt(dummyArr[0]))+1);}
//}

/************************* Smart Wealth Builder start****************************************/
//public  String getStringWithout_SmartWealthBuilder_E(double doubleVal) {
//	
//	/**Modified by Akshaya,Vrushali 06-MAY-2016 **/
//
//	 String dStr = "" + doubleVal;
//
//	 // System.out.println("******************String with e***"+dStr );
//	 int indexOf_E = dStr.indexOf('E');
//	 int indexOf_e = dStr.indexOf('e');
//	 int finalIndexOfE = 0;
//	 String seperator = null;
//	 String[] strPartsByE = new String[2];
//	 String[] strPartsByDecimal = new String[2];
//	 String rawStrWithoutDecimal = null;
//	 if ((indexOf_E > 0) || (indexOf_e > 0)) {
//	 		 if (indexOf_E > 0) {
//	 		 		 seperator = "E";
//	 		 		 finalIndexOfE = indexOf_E;
//	 		 } else if (indexOf_e > 0) {
//	 		 		 seperator = "e";
//	 		 		 finalIndexOfE = indexOf_e;
//	 		 }
//	 		 strPartsByE = split(dStr, seperator);
//	 		 strPartsByDecimal = split(strPartsByE[0], ".");
//	 		
//	 		 
//	 		 
//	 		 if(Integer.parseInt(strPartsByE[1])<0)
//	 		 {
//	 			 rawStrWithoutDecimal = strPartsByDecimal[0] + strPartsByDecimal[1];
//	 			 
//	 			 
//	 	
//	 			 String zeroAfterDecimal="";
//	 			 for(int i=1;i<Math.abs(Integer.parseInt(strPartsByE[1]));i++)
//	 			 {
//	 				zeroAfterDecimal = zeroAfterDecimal+"0";
//	 			 }
//	 			 
//	 			if(strPartsByDecimal[0].contains("-"))
//	 			{
//	 			 
//	 			 return "-0." +  zeroAfterDecimal +  String.valueOf(Math.abs(Long.parseLong(rawStrWithoutDecimal)));
//	 			}
//	 			else
//	 			{
//	 				return "0." +  zeroAfterDecimal +  String.valueOf(Math.abs(Long.parseLong(rawStrWithoutDecimal)));
//	 			}
//	 				 
//	 			 
//	 		 }
//	 		 else
//	 		 {
//	 			 
//	 			 rawStrWithoutDecimal = strPartsByDecimal[0] + strPartsByDecimal[1]
// 		 		 		 + "00000000000000000000";
//	 			 
//	 			if(strPartsByDecimal[0].contains("-"))
//		 		 {
//		 		 		 return (rawStrWithoutDecimal.substring(0,
//		 		 		 		 		 2 + Integer.parseInt(strPartsByE[1])))
//		 		 		 		 		 + "."
//		 		 		 		 		 + rawStrWithoutDecimal.substring(
//		 		 		 		 		 		 		 2 + Integer.parseInt(strPartsByE[1]),
//		 		 		 		 		 		 		 5 + Integer.parseInt(strPartsByE[1]));
//		 		 		 
//		 		 }
//		 		 else
//		 		 {
//		 		 		 return (rawStrWithoutDecimal.substring(0,
//		 		 		 		 		 1 + Integer.parseInt(strPartsByE[1])))
//		 		 		 		 		 + "."
//		 		 		 		 		 + rawStrWithoutDecimal.substring(
//		 		 		 		 		 		 		 1 + Integer.parseInt(strPartsByE[1]),
//		 		 		 		 		 		 		 4 + Integer.parseInt(strPartsByE[1]));
//		 		 		 
//		 		 }
//		 		 
//	 		 }
//
//	 } else {
//	 		 return dStr;
//	 }
//}

/************************ Smart Wealth Builder end****************************************/


/********************** Smart Wealth Assure start**************************************/
//
//public String stringWithoutE(String value) 
//{
//	return new BigDecimal(value).toPlainString();	    
//}

/*************************Smart wealth Assure end **************************************/


//public String getStringWithout_E_(double doubleVal)
//{
//  String dStr=""+doubleVal;
//  int indexOf_E=dStr.indexOf('E');
//  int indexOf_e=dStr.indexOf('e');
////  int finalIndexOfE=0;
//  String seperator=null;
//  String[] strPartsByE=new String[2];
//  String[] strPartsByDecimal=new String[2];
//  String rawStrWithoutDecimal=null;
//  if((indexOf_E>0) || (indexOf_e>0))
//  {
//      if(indexOf_E > 0)
//      {
//          seperator="E";
////          finalIndexOfE=indexOf_E;
//      }
//      else if(indexOf_e > 0)
//      {
//          seperator="e";
////          finalIndexOfE=indexOf_e;
//      }
//      strPartsByE=split(dStr, seperator);
//      strPartsByDecimal=split(strPartsByE[0], ".");
//      rawStrWithoutDecimal=strPartsByDecimal[0]+strPartsByDecimal[1];
//      return (rawStrWithoutDecimal.substring(0, 1+Integer.parseInt(strPartsByE[1])))+"."  +   rawStrWithoutDecimal.substring(1+Integer.parseInt(strPartsByE[1]), 4+Integer.parseInt(strPartsByE[1]));
//  }
//  else
//  {return dStr;}
//}


//    public String getStringWithout_E(double doubleVal)
//    {
//        String dStr=""+doubleVal;
//        int indexOf_E=dStr.indexOf('E');
//        int indexOf_e=dStr.indexOf('e');
////        int finalIndexOfE=0;
//        String seperator=null;
//        String[] strPartsByE=new String[2];
//        String[] strPartsByDecimal=new String[2];
//        String rawStrWithoutDecimal=null;
//        if((indexOf_E>0) || (indexOf_e>0))
//        {
//            if(indexOf_E > 0)
//            {
//                seperator="E";
////                finalIndexOfE=indexOf_E;
//            }
//            else if(indexOf_e > 0)
//            {
//                seperator="e";
////                finalIndexOfE=indexOf_e;
//            }
//            strPartsByE=split(dStr, seperator);
//            strPartsByDecimal=split(strPartsByE[0], ".");
//
//            rawStrWithoutDecimal=strPartsByDecimal[0]+strPartsByDecimal[1]+"00000000000000000000";
//            
////            System.out.println("rawStrWithoutDecimal"+rawStrWithoutDecimal);
////            System.out.println("val before ." + rawStrWithoutDecimal.substring(0, 1+Integer.parseInt(strPartsByE[1])));
////            System.out.println("" + rawStrWithoutDecimal.substring(1+Integer.parseInt(strPartsByE[1]), 4+Integer.parseInt(strPartsByE[1])));
////            
////            
////            
////            System.out.println("return"+ (rawStrWithoutDecimal.substring(0, 1+Integer.parseInt(strPartsByE[1])))+"."  +   rawStrWithoutDecimal.substring(1+Integer.parseInt(strPartsByE[1]), 4+Integer.parseInt(strPartsByE[1])));
//            
//            /** Changed by Akshaya om 25-MAR-14 start **/
//            if(strPartsByDecimal[0].contains("-"))
//            {
//            	return (rawStrWithoutDecimal.substring(0, 2+Integer.parseInt(strPartsByE[1])))+"."  +   rawStrWithoutDecimal.substring(1+Integer.parseInt(strPartsByE[1]), 4+Integer.parseInt(strPartsByE[1]));
//            }
//            else
//            {
//            	return (rawStrWithoutDecimal.substring(0, 1+Integer.parseInt(strPartsByE[1])))+"."  +   rawStrWithoutDecimal.substring(1+Integer.parseInt(strPartsByE[1]), 4+Integer.parseInt(strPartsByE[1]));
//            }
//            
//            /** Changed by Akshaya om 25-MAR-14 end **/
//            
////            return (rawStrWithoutDecimal.substring(0, 1+Integer.parseInt(strPartsByE[1])))+"."  +   rawStrWithoutDecimal.substring(1+Integer.parseInt(strPartsByE[1]), 4+Integer.parseInt(strPartsByE[1]));
//        }
//        else
//        {return dStr;}
//    }


//public double powSqrt(double x, double y)
//{
//        int den = 1024, num = (int)(y*den), iterations = 10;
//        double n = Double.MAX_VALUE;
//        while( n >= Double.MAX_VALUE && iterations > 1)
//        {
//            n = x;
//            for( int i=1; i < num; i++ )n*=x;
//            if( n >= Double.MAX_VALUE )
//            {
//                iterations--;
//                den = (int)(den / 2);
//                num = (int)(y*den);
//            }
//        }
//        for( int i = 0; i <iterations; i++ )n = Math.sqrt(n);
//        return n;
//}

//public double powDecay(double x, double y)
//{
//        int num, den = 1001, s = 0;
//        double n = x, z = Double.MAX_VALUE;
//        for( int i = 1; i < s; i++)n *= x;
//        while( z >= Double.MAX_VALUE )
//        {
//                den -=1;
//                num = (int)(y*den);
//                s = (num/den)+1;
//                z = x;
//                for( int i = 1; i < num; i++ )z *= x;
//        }
//        while( n > 0 )
//        {
//                double a = n;
//                for( int i = 1; i < den; i++ )a *= n;
//                if( (a-z) < .00001 || (z-a) > .00001 ) return n;
//                n *= .9999;
//        }
//        return -1.0;
//}


//public int getMonthDiff(String DOB_Borrower,String membershipDate)
//{
//	String selDate=DOB_Borrower;
//    String[] selDateArr=split(selDate,"/");
//    //Method Variables Declaration
//    int year=0,months=0;
//    int[] yearMonthArr=new int[]{0,0,0};
//    
//    //Old Date
//    int dd1=Integer.parseInt(selDateArr[1]);
//    int mm1=Integer.parseInt(selDateArr[0]);
//    
//    int yy1=Integer.parseInt(selDateArr[2].trim());
////    System.out.println("DOB YY"+yy1); //1988
//    String[] currDateArr=split(membershipDate,"/");
//    //Recent Date
//    int dd2=Integer.parseInt(currDateArr[1]);
//    int mm2=Integer.parseInt(currDateArr[0]);
//    int yy2=Integer.parseInt(currDateArr[2].trim());
//    
//    return mm1-mm2;
//}

    /********* Modified by Akshaya on 1-July-16 end **************/


    public String getRoundOffLevel2New(String number) {
        String first = "", second = "", third = "";
        String retVal = "0";
        String dummy = "" + number;
        // System.out.println("dummy" + dummy);

        String[] dummyArr = split(dummy, ".");
        // System.out.println("" + dummyArr[0]);
        // System.out.println("" + dummyArr[1]);

        if (dummyArr[1].length() == 1) {
            retVal = dummy + "0";
        } else if (dummyArr[1].length() == 2) {
            retVal = dummy;
        } else if (dummyArr[1].length() > 8) {
            first = dummyArr[1].substring(0, 2);
            second = dummyArr[1].substring(2, 3);
            third = dummyArr[1].substring(7, 8);
            if (Integer.parseInt(third) >= 5) {
                second = "" + (Integer.parseInt(second) + 1);
            }

            if (Integer.parseInt(second) >= 5) {
                if (Integer.parseInt(first) < 9) {
                    String temp = first.substring(1, 2);
                    // System.out.println("temp "+ temp);
                    first = "0" + String.valueOf(Integer.parseInt(temp) + 1);
                } else {
                    first = String.valueOf(Integer.parseInt(first) + 1);
                }

                // System.out.println("first changed" + first);
            }
            if (Integer.parseInt(first) > 99)
                retVal = (Long.parseLong(dummyArr[0]) + 1) + "." + 00;
            else
                retVal = dummyArr[0] + "." + first;


        } else if (dummyArr[1].length() > 2) {
            first = dummyArr[1].substring(0, 2);
            second = dummyArr[1].substring(2, 3);


            if (Integer.parseInt(second) >= 5) {
                if (Integer.parseInt(first) < 9) {
                    String temp = first.substring(1, 2);
                    // System.out.println("temp "+ temp);
                    first = "0" + String.valueOf(Integer.parseInt(temp) + 1);
                } else {
                    first = String.valueOf(Integer.parseInt(first) + 1);
                }

                // System.out.println("first changed" + first);
            }
            if (Integer.parseInt(first) > 99)
                retVal = (Long.parseLong(dummyArr[0]) + 1) + "." + 00;
            else
                retVal = dummyArr[0] + "." + first;


        }
        // System.out.println("first" + first);
        // System.out.println("second" + second);


        return retVal;
    }


    public int setDate(String proposalDate, String backDate) {
        String[] propDate = split(proposalDate, "-");

        int mon1 = getMappedMonth(propDate[1]);
        int dd1 = Integer.parseInt(propDate[0]);
        int mm1 = mon1;
        int yy1 = Integer.parseInt(propDate[2]);
//	System.out.println("day "+dd1);
//	System.out.println("month "+mm1);
//	System.out.println("year "+yy1);


        String[] bkDate = split(backDate, "-");

        int mon2 = getMappedMonth(bkDate[1]);
        int dd2 = Integer.parseInt(bkDate[0]);
        int mm2 = mon2;
        int yy2 = Integer.parseInt(bkDate[2]);
//	System.out.println("day "+dd2);
//	System.out.println("month "+mm2);
//	System.out.println("year "+yy2);

        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        cal1.set(yy1, mm1, dd1);
        cal2.set(yy2, mm2, dd2);

//    System.out.println("cal1.getTime "+cal1.getTime());
        int daysBetween = daysBetween(cal1.getTime(), cal2.getTime());
        return daysBetween;

//    Date d1=cal1.getTime();
//    Date d2=cal2.getTime();
//    return (int)( (d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
    }

    public int daysBetween(Date d1, Date d2) {
//	 System.out.println("d1.gettime "+d1.getTime());
        return (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
    }

    public String getRoundOffLevel5New(String number) {
        String first = "", second = "", third = "", fourth = "";
        String retVal = "0";
        String dummy = "" + number;
        int count = 0;
        // System.out.println("dummy" + dummy);

        String[] dummyArr = split(dummy, ".");
        // System.out.println("" + dummyArr[0]);
        // System.out.println("" + dummyArr[1]);


        if ((dummyArr[1].substring(0, 1)).equals("0")) {
            count = 1;
        }

        if ((dummyArr[1].substring(0, 2)).equals("00")) {
            count = 2;
        }

        if ((dummyArr[1].substring(0, 3)).equals("000")) {
            count = 3;
        }

        if ((dummyArr[1].substring(0, 4)).equals("0000")) {
            count = 4;
        }


        if (dummyArr[1].length() == 1) {
            retVal = dummy + "0000";
        } else if (dummyArr[1].length() == 2) {
            retVal = dummy + "000";
        } else if (dummyArr[1].length() == 3) {
            retVal = dummy + "00";
        } else if (dummyArr[1].length() == 4) {
            retVal = dummy + "0";
        } else if (dummyArr[1].length() == 5) {
            retVal = dummy;
        } else if (dummyArr[1].length() > 8) {
            first = dummyArr[1].substring(0, 5);
            second = dummyArr[1].substring(5, 6);
            third = dummyArr[1].substring(6, 7);
            //fourth=first.substring(0, 2);
            if (Integer.parseInt(third) >= 5) {
                second = "" + (Integer.parseInt(second) + 1);
            }

            if (Integer.parseInt(second) >= 5) {
                if (Integer.parseInt(first) < 9) {
                    String temp = first.substring(4, 5);
                    // System.out.println("temp "+ temp);
                    first = "0" + String.valueOf(Integer.parseInt(temp) + 1);
                } else {
                    first = String.valueOf(Integer.parseInt(first) + 1);
                }


                // System.out.println("first changed" + first);
            }
//			if (Integer.parseInt(first) > 99)
//				retVal = (Long.parseLong(dummyArr[0]) + 1) + "." + 00;
//			else()
            if (count == 1 && first.length() < 5) {
                retVal = dummyArr[0] + ".0" + first;
            } else if (count == 2 && first.length() < 5) {
                retVal = dummyArr[0] + ".00" + first;
            } else if (count == 3 && first.length() < 5) {
                retVal = dummyArr[0] + ".000" + first;
            } else if (count == 4 && first.length() < 5) {
                retVal = dummyArr[0] + ".0000" + first;
            } else {
                retVal = dummyArr[0] + "." + first;
            }


        } else if (dummyArr[1].length() > 5) {
            first = dummyArr[1].substring(0, 5);
            second = dummyArr[1].substring(5, 6);

            if (Integer.parseInt(second) >= 5) {
                if (Integer.parseInt(first) < 9) {
                    String temp = first.substring(4, 5);
                    // System.out.println("temp "+ temp);
                    first = "0" + String.valueOf(Integer.parseInt(temp) + 1);
                } else {
                    first = String.valueOf(Integer.parseInt(first) + 1);
                }


                // System.out.println("first changed" + first);
            }
//			if (Integer.parseInt(first) > 99)
//				retVal = (Long.parseLong(dummyArr[0]) + 1) + "." + 00;
//			else

            if (count == 1 && first.length() < 5) {
                retVal = dummyArr[0] + ".0" + first;
            } else if (count == 2 && first.length() < 5) {
                retVal = dummyArr[0] + ".00" + first;
            } else if (count == 3 && first.length() < 5) {
                retVal = dummyArr[0] + ".000" + first;
            } else if (count == 4 && first.length() < 5) {
                retVal = dummyArr[0] + ".0000" + first;
            } else {
                retVal = dummyArr[0] + "." + first;
            }

        }
        // System.out.println("first" + first);
        // System.out.println("second" + second);

        return retVal;
    }

    public String getRoundOffLevel5(String number) {
        String first = "", second = "", third = "", fourth = "";
        String retVal = "0";
        String dummy = "" + number;
        int count = 0;
        // System.out.println("dummy" + dummy);

        String[] dummyArr = split(dummy, ".");
        // System.out.println("" + dummyArr[0]);
        // System.out.println("" + dummyArr[1]);


        if ((dummyArr[1].substring(0, 1)).equals("0")) {
            count = 1;
        }

        if ((dummyArr[1].substring(0, 2)).equals("00")) {
            count = 2;
        }

        if ((dummyArr[1].substring(0, 3)).equals("000")) {
            count = 3;
        }

        if ((dummyArr[1].substring(0, 4)).equals("0000")) {
            count = 4;
        }

        if (Integer.parseInt(dummyArr[1].substring(2, 5)) == 999) {
            count = 1;
        }

        if (Integer.parseInt(dummyArr[1].substring(3, 5)) == 99) {
            count = 2;
        }


        if (dummyArr[1].length() == 1) {
            retVal = dummy + "0000";
        } else if (dummyArr[1].length() == 2) {
            retVal = dummy + "000";
        } else if (dummyArr[1].length() == 3) {
            retVal = dummy + "00";
        } else if (dummyArr[1].length() == 4) {
            retVal = dummy + "0";
        } else if (dummyArr[1].length() == 5) {
            retVal = dummy;
        } else if (dummyArr[1].length() > 8) {
            first = dummyArr[1].substring(0, 5);
            second = dummyArr[1].substring(5, 6);
            third = dummyArr[1].substring(6, 7);
            //fourth=first.substring(0, 2);
            if (Integer.parseInt(third) >= 5) {
                second = "" + (Integer.parseInt(second) + 1);
            }

            if (Integer.parseInt(second) >= 5) {
                if (Integer.parseInt(first) < 9) {
                    String temp = first.substring(4, 5);
                    // System.out.println("temp "+ temp);
                    first = "0" + String.valueOf(Integer.parseInt(temp) + 1);
                } else {
                    first = String.valueOf(Integer.parseInt(first) + 1);
                }

                // System.out.println("first changed" + first);
            }
            if (Integer.parseInt(first) > 99999)
                retVal = (Long.parseLong(dummyArr[0]) + 1) + "." + 00000;
            else {
                if (count == 1 && first.length() < 5) {
                    retVal = dummyArr[0] + ".0" + first;
                } else if (count == 2 && first.length() < 5) {
                    retVal = dummyArr[0] + ".00" + first;
                } else if (count == 3 && first.length() < 5) {
                    retVal = dummyArr[0] + ".000" + first;
                } else if (count == 4 && first.length() < 5) {
                    retVal = dummyArr[0] + ".0000" + first;
                } else {
                    retVal = dummyArr[0] + "." + first;
                }
            }
        } else if (dummyArr[1].length() > 5) {
            first = dummyArr[1].substring(0, 5);
            second = dummyArr[1].substring(5, 6);

            if (Integer.parseInt(second) >= 5) {
                if (Integer.parseInt(first) < 9) {
                    String temp = first.substring(4, 5);
                    // System.out.println("temp "+ temp);
                    first = "0" + String.valueOf(Integer.parseInt(temp) + 1);
                } else {
                    first = String.valueOf(Integer.parseInt(first) + 1);
                }

                // System.out.println("first changed" + first);
            }
            if (Integer.parseInt(first) > 99999)
                retVal = (Long.parseLong(dummyArr[0]) + 1) + "." + 00000;
            else {
                if (count == 1 && first.length() < 5) {
                    retVal = dummyArr[0] + ".0" + first;
                } else if (count == 2 && first.length() < 5) {
                    retVal = dummyArr[0] + ".00" + first;
                } else if (count == 3 && first.length() < 5) {
                    retVal = dummyArr[0] + ".000" + first;
                } else if (count == 4 && first.length() < 5) {
                    retVal = dummyArr[0] + ".0000" + first;
                } else {
                    retVal = dummyArr[0] + "." + first;
                }
            }
        }
        // System.out.println("first" + first);
        // System.out.println("second" + second);

        return retVal;
    }

 /*public String getStateSplit(String dummy)
 {
     String[] dummyArr=split(dummy,"|");
     
     String policyTerm =dummyArr[0];
     
     return policyTerm;
 }
 
 public String getStateSplit1(String dummy)
 {
     String[] dummyArr=split(dummy,"|");
     
     String state =dummyArr[1];
     
     return state;
 }*/

}