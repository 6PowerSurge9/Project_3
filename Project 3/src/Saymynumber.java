
// Saymynumber.java
// CSCI 1302
// Project 3
// 12/06/2021


import java.text.DecimalFormat;
import java.util.Scanner;

public class Saymynumber {
//Setting up the numbers to be converted
	  private static final String[] doubleNums = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
              " seventy", " eighty", " ninety" };

	  private static final String[] singleNums = { "", " one", " two", " three", " four", " five", " six", " seven",
			  " eight", " nine", " ten", " eleve", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
              " seventeen", " eighteen", " nineteen" };
	  
	  
	  

	  private static String convertLessThan1K(long taskVal) {
//number a recursive method "say(n)" where say(n) retursmallNum the string corresponding to the input integer n.
      String continueOn;

      if (taskVal % 100 < 20) {
              continueOn = singleNums[(int) (taskVal % 100)];
              taskVal /= 100;
      } else {
              continueOn = singleNums[(int) (taskVal % 10)];
              taskVal /= 10;

              continueOn = doubleNums[(int) (taskVal % 10)] + continueOn;
              taskVal /= 10;
      }
      
      if (taskVal == 0)
              return continueOn;
      return singleNums[(int) taskVal] + " hundred" + continueOn;
}
	  
	  
	  //this part now handles negative numbers and gives error if the number excedse L length
	  public static String say(String number){
		  
		  try {
			  long n = Long.parseLong(number);
			  String negativeIndicator = "";

			  if(n < 0){

				  negativeIndicator = "Minus";

				  n = n * -1;

			  }
			  return negativeIndicator + say(n);
			  
		  }catch(NumberFormatException n){
			  System.out.println("An Error has occured: Please input proper number lesser than 9,223,372,036,854,775,807L thank you.");
		  }
		  
		  return "";
	  }

	  
	  
	  public static String say(long number) {
     
      if (number == 0) {
              return "zero";
      }

      String smallNum = Long.toString(number);

     
      String mask = "000000000000";
      DecimalFormat df = new DecimalFormat(mask);
      smallNum = df.format(number);
      
      int task = 0;
      long taskVal = Long.parseLong(smallNum.substring(0, 3));
      smallNum = smallNum.substring(3);
      
      while(taskVal == 0) {
              taskVal = Long.parseLong(smallNum.substring(0, 3));
              smallNum = smallNum.substring(3);
              task++;
      }
      
      String units[] = new String[] {"quadrillion", "trillion" ,"billion", "million", "thousand"};
      String result = "";
      
      if(task < 2) { 
              result = convertLessThan1K(taskVal) + " " + units[task] + " ";
      } 
      
      else if(task == 2) {
    	  
              if(taskVal == 1) {
                      result += "one thousand ";
              } 
              else {
                      result += convertLessThan1K(taskVal) + " " + units[task] + " ";
              }
      }
      
      else {
              result += convertLessThan1K(taskVal);
      }
      
      if(!smallNum.isEmpty() && Long.parseLong(smallNum) != 0) {
              result += say(Long.parseLong(smallNum));
      }
      // this removes unecessary spaces 
      return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
}
	  
	  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scnr = new Scanner(System.in);
		int n = 0;
		
		System.out.println("Hello, Please enter a positive number that you wish to see converted in word form");
		n = scnr.nextInt();
        System.out.println("Your input: " + n + " Its word form: " + Saymynumber.say(n));

	}

}