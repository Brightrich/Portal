package common;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;

public class MtrackInvoiceStyler {
	
	private static final BaseColor SILVER = new BaseColor(242,242,242);

	public static void infoCellStyle(PdfPCell cell, String type){
		 
	    // alignment
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 	    
	    // background color
	    if(type.equals("blank")){
	    	cell.setBackgroundColor(BaseColor.WHITE);
	    	cell.setBorder(0);
	    }else{
	    
	    if(type.equals("label")){
	    	cell.setBackgroundColor(SILVER);	    	
	    } else {
	    	cell.setBackgroundColor(BaseColor.WHITE);
	    }
	        // border
	        cell.setBorder(Rectangle.BOX);
	        cell.setBorderColor(SILVER);	        	 
	    }
	}
	
	public static void billCellStyle(PdfPCell cell, String type){
		 
	    // alignment
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 	    
	    // background color
	    if(type.equals("header")){
	    	cell.setBackgroundColor(SILVER);
	    	cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
	    	cell.setBorderColor(BaseColor.DARK_GRAY);
	    } else if(type.equals("subtotal")){	    
	    	cell.setBackgroundColor(BaseColor.WHITE);
	        cell.setBorder(Rectangle.TOP);	        
	        cell.setBorderColor(BaseColor.DARK_GRAY);
	    } else if(type.equals("linetotal")){
	    	cell.setBackgroundColor(SILVER);
	    	cell.setBorder(0);
	    	cell.setPaddingTop(20f);
	    } else if(type.equals("linetotalsub")){
	    	cell.setBackgroundColor(SILVER);
	    	cell.setBorder(0);
	    } else if(type.equals("valuesub")){
	    	cell.setBackgroundColor(BaseColor.WHITE);
	        cell.setBorder(0);
	    }
	    else{	    
	    	cell.setBackgroundColor(BaseColor.WHITE);
	        cell.setBorder(0);	        	        
	        cell.setPaddingTop(20f);
	    }
	}
	
	public static void footerCellStyle(PdfPCell cell, String type){
		 
	    // alignment
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setBackgroundColor(BaseColor.WHITE);
    	cell.setBorder(0);
    	
	    // indent
	    if(type.equals("padding")){
	    	cell.setPaddingLeft(20f);
	    } else if(type.equals("sign")){
	    	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    	//cell.setPaddingRight(30f);
	    } else if(type.equals("tag")){
	    	cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    }
	}
	
	
}
