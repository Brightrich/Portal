package common;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Tools {

	public static SimpleDateFormat dateToStr = new SimpleDateFormat("dd-MM-yyyy");
	public static SimpleDateFormat strToDate = new SimpleDateFormat("MM/dd/yyyy");
	public static SimpleDateFormat monddyyyy = new SimpleDateFormat("MMMM dd, yyyy");
	public static SimpleDateFormat ddmmmyyyy = new SimpleDateFormat("dd-MMM-yyyy");
	public static SimpleDateFormat sdfLog = new SimpleDateFormat("HH:mm:ss sss");

	
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
		  Boolean adanol = false;
	    if (number.equals("0")) { return "nol"; }
	    if (number.contains(".")){
	    	adakoma = true;
	    	int temp = number.lastIndexOf(".");
	    	String blkgkoma = number.substring(temp+1,number.length());
	    	belakangkoma=convertLessThanOneThousand(Integer.valueOf(blkgkoma));
	    	String adaangkanol=Character.toString(blkgkoma.charAt(0));
	    	if(adaangkanol.equals("0")){
	    		adanol = true;
	    	}
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
	    	if(adanol){
	    		return result+" koma nol"+belakangkoma+"".replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	    	}else
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
			  int temp = nilai.lastIndexOf(".");
		      String blkgkoma = nilai.substring(temp+1,nilai.length());
		      String adaangkanol=Character.toString(blkgkoma.charAt(1));
		      String adaangkanol2=Character.toString(blkgkoma.charAt(0));
			  if(adaangkanol.equals("0")){
				  if(adaangkanol2.equals("0")){
					  hsl = "Rp. " + String.format("%15s",df.format(Double.valueOf(nilai))+".00");
				  }else{
					  hsl = "Rp. " + String.format("%15s",df.format(Double.valueOf(nilai))+"0");
				  }
			  }
			  else{
				  hsl = "Rp. " + String.format("%15s",df.format(Double.valueOf(nilai)));
			  }
		  }
		  return hsl;
	  }

	  public static void main(String[] args) throws ParseException{
		  
		  String dateStr="09/30/2013";
		  Date date = strToDate.parse(dateStr);
		  System.out.println(monddyyyy.format(date));
	  }
	  
	  
	  public static byte[] zipFiles(HashMap<String,String> mapFiles){
		  byte[] buffer = new byte[1024];
		  
	    	try{
	 
	    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    		ZipOutputStream zos = new ZipOutputStream(baos);
	    		
	    		for (Map.Entry<String, String> files : mapFiles.entrySet()) {
	            		            
	    		ZipEntry ze= new ZipEntry(files.getKey());
	    		zos.putNextEntry(ze);
	    		FileInputStream in = new FileInputStream(files.getValue());
	 
	    		int len;
	    		while ((len = in.read(buffer)) > 0) {
	    			zos.write(buffer, 0, len);
	    		}
	 
	    		in.close();
	    		}
	    		zos.closeEntry();
	 
	    		//remember close it
	    		zos.close();
	 
	    		System.out.println("Done");
	    		return baos.toByteArray();
	    	}catch(IOException ex){
	    	   ex.printStackTrace();
	    	}
	    	return null;
	    }
	  
}
