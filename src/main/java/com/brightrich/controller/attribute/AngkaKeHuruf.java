package com.brightrich.controller.attribute;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class AngkaKeHuruf {
	private static final String[] tensNames = {
	    "",
	    " sepuluh",
	    " dua puluh",
	    " tiga puluh",
	    " empat puluh",
	    " lima puluh",
	    " enam puluh",
	    " tujuh puluh",
	    " delapan puluh",
	    " sembilan puluh"
	  };

	  private static final String[] numNames = {
	    "",
	    " satu",
	    " dua",
	    " tiga",
	    " empat",
	    " lima",
	    " enam",
	    " tujuh",
	    " delapan",
	    " sembilan",
	    " sepuluh",
	    " sebelas",
	    " dua belas",
	    " tiga belas",
	    " empat belas",
	    " lima belas",
	    " enam belas",
	    " tujuh belas",
	    " delapan belas",
	    " sembilan belas"
	  };

	  private static String convertLessThanOneThousand(int number) {
	    String soFar;

	    if (number % 100 < 20){
	      soFar = numNames[number % 100];
	      number /= 100;
	    }
	    else {
	      soFar = numNames[number % 10];
	      number /= 10;

	      soFar = tensNames[number % 10] + soFar;
	      number /= 10;
	    }
	    if (number == 0) return soFar;
	    if(number == 1) return "seratus"+soFar;
	    else return numNames[number] + " ratus" + soFar;
	  }
	  
	

	  public static String convert(String number) {
	    // 0 to 999 999 999 999
		  String belakangkoma = null;
		  Boolean adakoma = false;
	    if (number.equals("0")) { return "nol"; }
	    if (number.contains(".")){
	    	adakoma = true;
	    	int temp = number.lastIndexOf(".");
	    	String blkgkoma = number.substring(temp+1,number.length());
	    	belakangkoma=convertLessThanOneThousand(Integer.valueOf(blkgkoma));
	    	number = number.substring(0,temp);
	    }
	    String snumber = number;
	    Long lnumber = Long.parseLong(number);

	    // pad with "0"
	    String mask = "000000000000";
	    DecimalFormat df = new DecimalFormat(mask);
	    snumber = df.format(lnumber);

	    // XXXnnnnnnnnn 
	    int billions = Integer.parseInt(snumber.substring(0,3));
	    // nnnXXXnnnnnn
	    int millions  = Integer.parseInt(snumber.substring(3,6)); 
	    // nnnnnnXXXnnn
	    int hundredThousands = Integer.parseInt(snumber.substring(6,9)); 
	    // nnnnnnnnnXXX
	    int thousands = Integer.parseInt(snumber.substring(9,12));    

	    String tradBillions;
	    switch (billions) {
	    case 0:
	      tradBillions = "";
	      break;
	    case 1 :
	      tradBillions = convertLessThanOneThousand(billions) 
	      + " miliar ";
	      break;
	    default :
	      tradBillions = convertLessThanOneThousand(billions) 
	      + " miliar ";
	    }
	    String result =  tradBillions;

	    String tradMillions;
	    switch (millions) {
	    case 0:
	      tradMillions = "";
	      break;
	    case 1 :
	      tradMillions = convertLessThanOneThousand(millions) 
	      + " juta ";
	      break;
	    default :
	      tradMillions = convertLessThanOneThousand(millions) 
	      + " juta ";
	    }
	    result =  result + tradMillions;

	    String tradHundredThousands;
	    switch (hundredThousands) {
	    case 0:
	      tradHundredThousands = "";
	      break;
	    case 1 :
	      tradHundredThousands = "seribu ";
	      break;
	    default :
	      tradHundredThousands = convertLessThanOneThousand(hundredThousands) 
	      + " ribu ";
	    }
	    result =  result + tradHundredThousands;

	    String tradThousand;
	    tradThousand = convertLessThanOneThousand(thousands);
	    result =  result + tradThousand;

	    if(adakoma){
	    	return result+" koma"+belakangkoma+"".replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	    }else
	    return result+" koma nol nol"+"".replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	  }
	  
	  public static String formatrupiah(String nilai){
		  DecimalFormat df = new DecimalFormat();
		  DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		  dfs.setCurrencySymbol("");
		  dfs.setMonetaryDecimalSeparator('.');
		  dfs.setGroupingSeparator(',');
		  df.setDecimalFormatSymbols(dfs);
		  String hsl=null;
		  if(nilai.contains(".")){
			  hsl = "Rp. " + String.format("%15s",df.format(Double.valueOf(nilai)));
		  }else{
			  hsl = "Rp. " + String.format("%15s",df.format(Double.valueOf(nilai))+".00");
		  }
		  return hsl;
	  }


	  /**
	   * testing
	   * @param args
	   */
	  public static void main(String[] args) {
//		System.out.println("ASTAGA ="+formatrupiah("12.12"));
		System.out.println("ASTAGA ="+formatrupiah("1"));
		System.out.println("ASTAGA ="+formatrupiah("10"));
		System.out.println("ASTAGA ="+formatrupiah("100"));
		System.out.println("ASTAGA ="+formatrupiah("1000"));
		System.out.println("ASTAGA ="+formatrupiah("10000"));
		System.out.println("ASTAGA ="+formatrupiah("100000"));
		System.out.println("ASTAGA ="+formatrupiah("1000000"));
		System.out.println("ASTAGA ="+formatrupiah("10000000"));
		System.out.println("ASTAGA ="+formatrupiah("900000000"));
		System.out.println("ASTAGA ="+formatrupiah("123123.12"));
	    System.out.println("*** " + AngkaKeHuruf.convert("16.12"));
//	    System.out.println("*** " + AngkaKeHuruf.convert(16));
//	    System.out.println("*** " + AngkaKeHuruf.convert(100));
//	    System.out.println("*** " + AngkaKeHuruf.convert(118));
//	    System.out.println("*** " + AngkaKeHuruf.convert(200));
//	    System.out.println("*** " + AngkaKeHuruf.convert(219));
//	    System.out.println("*** " + AngkaKeHuruf.convert(800));
//	    System.out.println("*** " + AngkaKeHuruf.convert(801));
//	    System.out.println("*** " + AngkaKeHuruf.convert(1316));
//	    System.out.println("*** " + AngkaKeHuruf.convert("1000000"));
//	    System.out.println("*** " + AngkaKeHuruf.convert(2000000));
//	    System.out.println("*** " + AngkaKeHuruf.convert(3000200));
//	    System.out.println("*** " + AngkaKeHuruf.convert(700000));
//	    System.out.println("*** " + AngkaKeHuruf.convert(9000000));
//	    System.out.println("*** " + AngkaKeHuruf.convert(9001100));
//	    System.out.println("*** " + AngkaKeHuruf.convert(123456789));
//	    System.out.println("*** " + AngkaKeHuruf.convert("2147483647"));
//	    System.out.println("*** " + AngkaKeHuruf.convert(3000000010L));
	  }
}
