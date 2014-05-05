package common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class PdfCracker {
	
public static String billSumHeader = "\\bkartuHALO BILLING STATEMENT\\b";
public static String callDetailHeader = "\\bDetail Usage Statement\\b";
public static String billSumInvDate = "(?<=Invoice Date ).*";
public static String billSumAmount = "(?<=Jumlah yang harus dibayarkan \nRp. ).*|(?<=Jumlah yang harus dibayarkan\nRp. ).*";
public static String msisdnPattern = "(?<=kartuHALO Number ).*";

public static Pattern patternDate = Pattern.compile(billSumInvDate);
public static Pattern patternAmount = Pattern.compile(billSumAmount);
public static Pattern patternSumHeader = Pattern.compile(billSumHeader);
public static Pattern patternDetailHeader = Pattern.compile(callDetailHeader);
public static Pattern patternMsisdn = Pattern.compile(msisdnPattern);


public static Matcher matcherDate, matcherAmount, matcherMsisdn, matcherHeaderSum, matcherHeaderDetail;
public static boolean foundDate = false, foundAmount = false, foundMsisdn = false, foundHeaderSum = false, foundHeaderDetail = false;
	
public static String convertDateToEnglish(String inputDate){
		
		String[] arrDate = inputDate.split("-"); 
		
		if(arrDate[1].toLowerCase().contains("januari") || arrDate[1].toLowerCase().contains("jan")){
			inputDate = inputDate.replace(arrDate[1], "January");
		} else if (arrDate[1].toLowerCase().contains("pebruari") || arrDate[1].toLowerCase().contains("februari") || arrDate[1].toLowerCase().contains("feb") || arrDate[1].toLowerCase().contains("peb")){
			inputDate = inputDate.replace(arrDate[1], "February");
		} else if (arrDate[1].toLowerCase().contains("maret") || arrDate[1].toLowerCase().contains("mar")){
			inputDate = inputDate.replace(arrDate[1], "March");
		} else if (arrDate[1].toLowerCase().contains("april") || arrDate[1].toLowerCase().contains("apr")){
			inputDate = inputDate.replace(arrDate[1], "April");
		} else if (arrDate[1].toLowerCase().contains("mei")){
			inputDate = inputDate.replace(arrDate[1], "May");
		} else if (arrDate[1].toLowerCase().contains("juni") || arrDate[1].toLowerCase().contains("jun")){
			inputDate = inputDate.replace(arrDate[1], "June");
		} else if (arrDate[1].toLowerCase().contains("juli") || arrDate[1].toLowerCase().contains("jul")){
			inputDate = inputDate.replace(arrDate[1], "July");
		} else if (arrDate[1].toLowerCase().contains("agustus") || arrDate[1].toLowerCase().contains("aug")){
			inputDate = inputDate.replace(arrDate[1], "August");
		} else if (arrDate[1].toLowerCase().contains("sept") || arrDate[1].toLowerCase().contains("sep")){
			inputDate = inputDate.replace(arrDate[1], "September");
		} else if (arrDate[1].toLowerCase().contains("oktober") || arrDate[1].toLowerCase().contains("okt") || arrDate[1].toLowerCase().contains("oct")){
			inputDate = inputDate.replace(arrDate[1], "October");
		} else if (arrDate[1].toLowerCase().contains("nopember") || arrDate[1].toLowerCase().contains("nov") || arrDate[1].toLowerCase().contains("nop")){
			inputDate = inputDate.replace(arrDate[1], "November");
		} else if (arrDate[1].toLowerCase().contains("desember") || arrDate[1].toLowerCase().contains("dec") || arrDate[1].toLowerCase().contains("des")){
			inputDate = inputDate.replace(arrDate[1], "Desember");
		}
				
		return inputDate;
	}	
	public static void readPdfFile() throws IOException, DocumentException{
		String dirBill = "C:/Users/Arga/Documents/TSEL/brighten.pdf";
		//String dirBill = "C:/Users/Arga/Documents/TSEL/628111022211_201308.pdf";
		//String dirBill = "C:/Users/Arga/Documents/TSEL/BillingSummary_0811136940_15-October-2013.pdf";
		String outDir = "C:/Users/Arga/Documents/TSEL/new/";
		PdfReader reader = new PdfReader(dirBill);
		//PdfReader reader = new PdfReader(dirBill,"999999".getBytes());
		//reader.unethicalreading = true;
		
		String prevMsisdnCD = "";
		final String RESULT_CALL_DETAIL = "CallDetail_";
		final String RESULT_BILL_SUM = "BillingSummary_";
		boolean flag = false;		
		
		// We'll create as many new PDFs as there are pages
		Document sumDoc, detailDoc = null;
		PdfCopy copySum, copyDetail = null;
		String msisdn ="", date ="", amount="";
		
		// loop over all the pages in the original PDF
		int n = reader.getNumberOfPages();
		for (int i = 0; i < n; i++) {
		    // step 1
		   /* if(flag){
		    	document.close();
		    	copy.close();
		    	flag = false;
		    }*/
		    
		    // step 2 get page
		    String page =  PdfTextExtractor.getTextFromPage(reader, i+1);
		    //System.out.println("PAGE = " + i);
		    //System.out.println("PAGE = " + page);
		    
		    //checked bill sum or call details
		    matcherHeaderSum = patternSumHeader.matcher(page);
		    matcherHeaderDetail = patternDetailHeader.matcher(page);
		    if(matcherHeaderSum.find()){		    	
		    	// BILLING SUMMARY PAGE
		    	//System.out.println("I found the Billing Summary: " + matcherHeaderSum.group().toString());
		    	
		        matcherMsisdn = patternMsisdn.matcher(page);
		        if(matcherMsisdn.find()){
		        	msisdn = matcherMsisdn.group().toString();
		        	if(msisdn.startsWith("62")){
		        		msisdn = "0"+msisdn.substring(2);
		        	}
		        }
		        
		        matcherAmount = patternAmount.matcher(page);
		        if(matcherAmount.find()){
		        	amount = matcherAmount.group().toString();
		        	amount = amount.replace(",", "");
		        }
		        
		        matcherDate = patternDate.matcher(page);
		        while(matcherDate.find()){		        	
		        	date = matcherDate.group().toString();
		        	date = convertDateToEnglish(date);
		        	
		        	matcherDate = patternDate.matcher(date);
		        }
		        
		        //System.out.println("EXTRACTED BILL SUMMARY DATA : \n MSISDN = " + msisdn + "\n" + "AMOUNT = " + amount + "\n" + "DATE = " + date );
		        System.out.println("PAGE = " + i);
		        System.out.println(msisdn);
		        
		        //WRITE PDF
		        sumDoc = new Document();
		        copySum = new PdfCopy(sumDoc, new FileOutputStream(outDir+RESULT_BILL_SUM+msisdn+".pdf"));
		        sumDoc.open();
		        copySum.addPage(copySum.getImportedPage(reader, i+1));
		        sumDoc.close();
		        copySum.close();
		        
		    } else if (matcherHeaderDetail.find()){
		    	//System.out.println("I found the CALL DETAILS: " + matcherHeaderDetail.group().toString());
		    	matcherMsisdn = patternMsisdn.matcher(page);
		        if(matcherMsisdn.find()){
		        	msisdn = matcherMsisdn.group().toString();
		        	if(msisdn.startsWith("62")){
		        		msisdn = "0"+msisdn.substring(2);
		        	}
		        	
		        	//System.out.println("EXTRACTED CALL DETAILS DATA : \n MSISDN = " + msisdn);
		        	
		        	if(prevMsisdnCD.equals("")){
		        		detailDoc = new Document();
				        copyDetail = new PdfCopy(detailDoc, new FileOutputStream(outDir+RESULT_CALL_DETAIL+msisdn+".pdf"));
				        detailDoc.open();
				        copyDetail.addPage(copyDetail.getImportedPage(reader, i+1));
				        
		        	} else if(prevMsisdnCD.equals(msisdn)){
		        		//ADD to DOCUMENT BEFORE
		        		
		        		//System.out.println("SAME DOCUMENT");
		        		copyDetail.addPage(copyDetail.getImportedPage(reader, i+1));
		        		flag = true;
		        	} else {
		        		// CREATE NEW DOCUMENT
		        		detailDoc.close();
		        		copyDetail.close();
		        		
		        		detailDoc = new Document();
				        copyDetail = new PdfCopy(detailDoc, new FileOutputStream(outDir+RESULT_CALL_DETAIL+msisdn+".pdf"));
				        detailDoc.open();
				        copyDetail.addPage(copyDetail.getImportedPage(reader, i+1));				        				        
		        	}
		        	
		        	prevMsisdnCD=msisdn;
		        	
		        	if(i+1 >= n){
		        		detailDoc.close();
		        		copyDetail.close();
		        	}
		        	
		        	
		        	
		        }
		        
		        matcherDate = patternDate.matcher(page);
		        while(matcherDate.find()){		        	
		        	date = matcherDate.group().toString();
		        	date = convertDateToEnglish(date);
		        	
		        	matcherDate = patternDate.matcher(date);
		        }
		        
		    }
		    
		    
		    
		    
		}
		
		System.out.println("DONE!!!");
		reader.close(); 
	}
	
	
	
	
	public static void main(String[] args){
		try {
			
			PdfReader read = PdfTools.createPdfReaderFromDirectory("C:/Users/Arga/Documents/Lync/Productivity – Flexible Workplace Solutions – Customer Ready_pptx.pdf", "","");
			splitPdf(read);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void splitPdf(PdfReader reader) throws IOException, DocumentException{
		PdfCopy copySum = null;
		Document sumDoc = null;
		String fname = "Productivity – Flexible Workplace Solutions – Customer Ready_pptx"+ 3+".pdf";
		sumDoc = new Document();
		copySum = new PdfCopy(sumDoc, new FileOutputStream("C:/Users/Arga/Documents/Lync/"+fname));
		sumDoc.open();
		
		int n = reader.getNumberOfPages();
		System.out.println("NUMBER OF PAGES = " + n);		
		for (int i = 10; i < 15; i++) {
			System.out.println("READ PAGE NUMBER = " + i);
			
		    //String page =  PdfTextExtractor.getTextFromPage(reader, i+1);
		    
		    
		    
	        
	        
	        
	        copySum.addPage(copySum.getImportedPage(reader, i+1));
	        

	}
		sumDoc.close();
        copySum.close();
		
	}
	
	
}
