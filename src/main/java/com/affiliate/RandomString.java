package com.affiliate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//Java program generate a random AlphaNumeric String
//using Math.random() method

public class RandomString {

	//function to generate a random string of length n
	static String getAlphaNumericString(int n) throws InterruptedException {

			//chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

			//create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < 20; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());
			// System.out.println(index);
			// Thread.sleep(2000);

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}
	
	public static String affilated_id() {
		List<String> al=new ArrayList<String>();
		for(int k=0;k<8000;k++) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSSS");
			LocalDateTime now = LocalDateTime.now();
			//System.out.println("now: "+now);
			String time = dtf.format(now);
			//System.out.println("time: "+time);
			String otp = time.split("\\.")[1];
			//System.out.println("otp: "+otp);
			Random rand = new Random();
			
			 int num = rand.nextInt(10000);
	        String formatted = String.format("%04d", num);

			String random = formatted+otp;
			
			
			al.add(random);
			System.out.println(random);
		}
		
        //String[] my_array = {"bcd", "abd", "jude", "bcd", "oiu", "gzw", "oiu"};
       
        for (int i = 0; i < al.size()-1; i++)
        {
            for (int j = i+1; j < al.size(); j++)
            {
                if( (al.get(i).equals(al.get(j))) && (i != j) )
                {
                    System.out.println("Duplicate Element is : "+al.get(j));
                }
            }
        }
        
		System.out.println("forloop end:");
		
		return null;
	}
	
	
	public static int switchExample() {
	    //for loop 
		int k=0;
	    for(int i=1;i<=10;i++){  
	        if(i==5){  
	            //using continue statement
	        	k=i;
	        	System.out.println("inside if: "+i);
	            continue;//it will skip the rest statement  
	        } else {
	        	k=i;
	        	System.out.println("else condition: "+i);  
	        	break;
	        }
	    }
		return k; 
	    
	}
	
	//generate 5 digit random numbers
	public static void randomDigitGenerate() {
		//List<String> al=new ArrayList<String>();
		for(int i=0;i<10000;i++) {
			Random random = new Random();
			 int num = random.nextInt(10000);
	        String formatted = String.format("%04d", num);
	        System.out.println(formatted);
	        //al.add(formatted);
		}
		
		
		/*
        for (int i = 0; i < al.size()-1; i++)
        {
            for (int j = i+1; j < al.size(); j++)
            {
                if( (al.get(i).equals(al.get(j))) && (i != j) )
                {
                    System.out.println("Duplicate Element is : "+al.get(j));
                }
            }
        }
		
*/
	}
	
	
	

	//public static void main(String[] args) throws Exception {

		//Get the size n
		//int n = 20;

		//Get and display the alphanumeric string
		//System.out.println("final output: "+affilated_id());
		//System.out.println(RandomString.getAlphaNumericString(n));
		//switchExample();
		
		//generate 5 digit random numbers
		//randomDigitGenerate();
	

	//}

	

}
