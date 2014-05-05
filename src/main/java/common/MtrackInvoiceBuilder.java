package common;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.util.HashMap;

import com.brightrich.model.MtrackBilling;
import com.brightrich.model.MtrackCompany;
import com.brightrich.model.MtrackInvoice;
import com.brightrich.model.MtrackItem;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class MtrackInvoiceBuilder {
	
	public static PdfPTable createLineSeparator() throws Exception{
		PdfPTable table = new PdfPTable(1);

		table.setWidthPercentage(100);

		table.setWidths(new float[] { 8f });
		
		Font font = new Font(FontFamily.TIMES_ROMAN, 20, Font.BOLD,
				BaseColor.BLACK);
		
		PdfPCell cell = new PdfPCell(new Phrase(" ", font));
		cell.setMinimumHeight(1f);
		cell.setFixedHeight(2f);
		cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
		cell.setBorderWidth(1f);
		cell.setBorderColor(BaseColor.BLACK);
		
		table.addCell(cell);
		table.setSpacingAfter(10f);
		return table;
	}

	public static PdfPTable createHeaderTable() throws DocumentException,
			Exception {

		PdfPTable table = new PdfPTable(2);

		table.setWidthPercentage(100);

		table.setWidths(new float[] { 4f, 4f });

		Font font = new Font(FontFamily.TIMES_ROMAN, 20, Font.BOLD,
				BaseColor.GRAY);

		PdfPCell cell = new PdfPCell(new Phrase("INVOICE", font));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		String filename = ConstantParameter.MtrackInvoiceAttr.BR_LOGO_FILE;
		Image image;
		image = Image.getInstance(filename);
		PdfPCell cell2 = new PdfPCell(image, false);
		cell2.setBorder(Rectangle.NO_BORDER);
		cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

		table.setSpacingAfter(10f);
		table.addCell(cell);
		table.addCell(cell2);		
		return table;
	}

	public static PdfPTable createTableInfo(HashMap<String, String> map, MtrackInvoice invoice) throws Exception {
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 0.5f, 3.25f, 0.5f, 1.625f, 2.125f });
		Font fontAddress = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD,
				BaseColor.BLACK);
		Font fontValue = new Font(FontFamily.TIMES_ROMAN, 8, Font.NORMAL,
				BaseColor.BLACK);

		// 1st Row
		PdfPCell cell = createInfoCell("To", "label", fontAddress);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setRowspan(5);
		
		//COMPANY NAME
		PdfPCell cell1 = createInfoCell(invoice.getCompanyId().getName(), "value",fontAddress);
		cell1.setBorderColorBottom(BaseColor.WHITE);
		cell1.setBorderWidthBottom(0f);
		
		PdfPCell cell2 = createInfoCell("", "blank", fontAddress);
		cell2.setRowspan(5);
		
		//INVOICE NUMBER
		PdfPCell cell3 = createInfoCell("INVOICE NO.", "label", fontValue);
		PdfPCell cell4 = createInfoCell(invoice.getInvoiceNo(), "label",fontValue);

		table.addCell(cell);
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);

		// 2nd row
		// ADDRESS
		PdfPCell r2cell = createInfoCell(
				invoice.getCompanyId().getAddress(),
				"value", fontAddress);
		r2cell.setBorderColorBottom(BaseColor.WHITE);
		r2cell.setBorderColorTop(BaseColor.WHITE);
		r2cell.setBorderWidthBottom(0f);
		r2cell.setBorderWidthTop(0f);
		
		//INVOICE DATE
		PdfPCell r2cell1 = createInfoCell("INVOICE DATE", "label", fontValue);
		PdfPCell r2cell2 = createInfoCell(Tools.monddyyyy.format(invoice.getInvoiceDate()), "label", fontValue);

		table.addCell(r2cell);
		table.addCell(r2cell1);
		table.addCell(r2cell2);

		// 3rd row
		PdfPCell r3cell = createInfoCell("", "value", fontValue);
		r3cell.setBorderColorBottom(BaseColor.WHITE);
		r3cell.setBorderColorTop(BaseColor.WHITE);
		r3cell.setBorderWidthBottom(0f);
		r3cell.setBorderWidthTop(0f);
		
		PdfPCell r3cell1 = createInfoCell("DUE DATE", "label", fontValue);
		PdfPCell r3cell2 = createInfoCell(
				"7(seven) days from the invoice date", "label", fontValue);

		table.addCell(r3cell);
		table.addCell(r3cell1);
		table.addCell(r3cell2);

		// 4th row
		PdfPCell r4cell = createInfoCell("", "value", fontValue);
		r4cell.setBorderColorBottom(BaseColor.WHITE);
		r4cell.setBorderColorTop(BaseColor.WHITE);
		r4cell.setBorderWidthBottom(0f);
		r4cell.setBorderWidthTop(0f);
		
		PdfPCell r4cell1 = createInfoCell("Our Tax No. (NPWP)", "value",
				fontValue);
		
		//B&R NPWP
		PdfPCell r4cell2 = createInfoCell(map.get(ConstantParameter.MtrackInvoiceAttr.BR_NPWP), "value",
				fontValue);

		table.addCell(r4cell);
		table.addCell(r4cell1);
		table.addCell(r4cell2);

		// 5th row
		//PIC
		PdfPCell r5cell = createInfoCell(invoice.getCompanyId().getPIC(), "value", fontAddress);
		r5cell.setBorderColorTop(BaseColor.WHITE);
		r5cell.setBorderWidthTop(0f);
		PdfPCell r5cell1 = createInfoCell("", "value", fontValue);
		PdfPCell r5cell2 = createInfoCell("", "value", fontValue);

		table.addCell(r5cell);
		table.addCell(r5cell1);
		table.addCell(r5cell2);

		table.setSpacingAfter(20f);
		return table;
	}
	
	public static PdfPTable createTableBilling(HashMap<String, String> map, MtrackInvoice invoice, MtrackItem item) throws Exception {
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 0.5f, 1.875f, 1.875f, 1.875f, 1.875f });
		Font fontHeader = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK);
		Font fontValue = new Font(FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);
		
		// header Row
		PdfPCell cell = createBillingCell("No.", "header", fontHeader);
		PdfPCell cell1 = createBillingCell("Item", "header", fontHeader);
		PdfPCell cell2 = createBillingCell("Description", "header", fontHeader);
		PdfPCell cell3 = createBillingCell("Qty", "header", fontHeader);
		PdfPCell cell4 = createBillingCell("Line Total", "header", fontHeader);
		
		table.addCell(cell);
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		
		// value Row1
				
				
				PdfPCell r1cell = createBillingCell("1", "value", fontValue);
				PdfPCell r1cell1 = createBillingCell(map.get(ConstantParameter.MtrackInvoiceAttr.MTRACK_ITEM_ID), "value", fontValue);
				PdfPCell r1cell2 = createBillingCell("M-Track", "value", fontValue);
				PdfPCell r1cell3 = createBillingCell(map.get(ConstantParameter.MtrackInvoiceAttr.NUM_OF_UNIT), "value", fontValue);
				System.out.println("BSE PRICE = " + map.get(ConstantParameter.MtrackInvoiceAttr.MTRACK_BASE_PRICE));
				System.out.println("BSE PRICE = " + Tools.formatrupiah(map.get(ConstantParameter.MtrackInvoiceAttr.MTRACK_BASE_PRICE)));
				PdfPCell r1cell4 = createBillingCell(Tools.formatrupiah(map.get(ConstantParameter.MtrackInvoiceAttr.MTRACK_BASE_PRICE)), "linetotal", fontValue);
				
				table.addCell(r1cell);
				table.addCell(r1cell1);
				table.addCell(r1cell2);
				table.addCell(r1cell3);
				table.addCell(r1cell4);
				
				// value Row2
				PdfPCell r2cell = createBillingCell("2", "value", fontValue);
				//ITEM ID
				PdfPCell r2cell1 = createBillingCell(item.getItemNumber(), "value", fontValue);
				PdfPCell r2cell2 = createBillingCell(item.getDescription(), "value", fontValue);
				PdfPCell r2cell3 = createBillingCell(map.get(ConstantParameter.MtrackInvoiceAttr.NUM_OF_UNIT), "value", fontValue);
				PdfPCell r2cell4 = createBillingCell(Tools.formatrupiah(invoice.getTotalInvoice().toString()), "linetotal", fontValue);
				
				//last row before subtotal
				r2cell4.setBorder(Rectangle.BOTTOM);	        
		        r2cell4.setBorderColor(BaseColor.DARK_GRAY);
				
				table.addCell(r2cell);
				table.addCell(r2cell1);
				table.addCell(r2cell2);
				table.addCell(r2cell3);
				table.addCell(r2cell4);
				
				// value subtotal
				PdfPCell r3cell = createBillingCell("", "subtotal", fontValue);
				PdfPCell r3cell1 = createBillingCell("Subtotal", "subtotal", fontValue);
				PdfPCell r3cell2 = createBillingCell("", "subtotal", fontValue);
				PdfPCell r3cell3 = createBillingCell("", "subtotal", fontValue);
				
				//SUBTOTAL
				PdfPCell r3cell4 = createBillingCell(Tools.formatrupiah(map.get(ConstantParameter.MtrackInvoiceAttr.SUBTOTAL)), "linetotalsub", fontValue);
				
				table.addCell(r3cell);
				table.addCell(r3cell1);
				table.addCell(r3cell2);
				table.addCell(r3cell3);
				table.addCell(r3cell4);
				
				// value VAT
				PdfPCell r4cell = createBillingCell("", "valuesub", fontValue);
				r4cell.setColspan(3);
				PdfPCell r4cell1 = createBillingCell("VAT", "valuesub", fontValue);
				r4cell1.setPaddingTop(0f);
				PdfPCell r4cell2 = createBillingCell(Tools.formatrupiah(map.get(ConstantParameter.MtrackInvoiceAttr.VAT)), "linetotalsub", fontValue);
				
				table.addCell(r4cell);
				table.addCell(r4cell1);
				table.addCell(r4cell2);
				
				// value TOTAL
				PdfPCell r5cell = createBillingCell("", "valuesub", fontValue);				
				PdfPCell r5cell1 = createBillingCell("Total", "valuesub", fontHeader);
				
				PdfPCell r5cell2 = createBillingCell("", "valuesub", fontHeader);
				r5cell2.setColspan(2);
				PdfPCell r5cell3 = createBillingCell(Tools.formatrupiah(map.get(ConstantParameter.MtrackInvoiceAttr.TOTAL)), "linetotalsub", fontHeader);
				
				table.addCell(r5cell);
				table.addCell(r5cell1);
				table.addCell(r5cell2);
				table.addCell(r5cell3);
				
				//blank
				PdfPCell r6cell = createBillingCell(" ", "value", fontHeader);
				r6cell.setColspan(5);
				table.addCell(r6cell);
				
				//amount in words
				PdfPCell r7cell = createBillingCell("Amount in words: " + Tools.convert(map.get(ConstantParameter.MtrackInvoiceAttr.TOTAL)), "header", fontHeader);				
				r7cell.setColspan(5);
				r7cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(r7cell);
				
				table.setSpacingAfter(20f);		
		return table;
	}
	
	public static PdfPTable createTableFooter(HashMap<String, String> map) throws Exception {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setSpacingBefore(20f);
		table.setWidths(new float[] { 4f,4f });
		Font fontHeader = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK);
		Font fontValue = new Font(FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);				
		
		// footer row 1
		PdfPCell cell = createFooterCell("Bank Account:", "value", fontValue);
		
		//Company name
		PdfPCell cell1 = createFooterCell(map.get(ConstantParameter.MtrackInvoiceAttr.BR_NAME), "sign", fontHeader);
				
		table.addCell(cell);
		table.addCell(cell1);
		
		// footer row 2
				//company name to uppercase
				PdfPCell r1cell = createFooterCell(map.get(ConstantParameter.MtrackInvoiceAttr.BR_NAME).toUpperCase(), "padding", fontHeader);
				PdfPCell r1cell1 = createFooterCell(" ", "header", fontValue);
						
				table.addCell(r1cell);
				table.addCell(r1cell1);
				
				// footer row 3
				//BANK ACCOUNT
				PdfPCell r2cell = createFooterCell(map.get(ConstantParameter.MtrackInvoiceAttr.BR_ACCOUNT_BRANCH), "padding", fontValue);
				r2cell.setColspan(2);
				
				table.addCell(r2cell);
				
				// footer row 4
				PdfPCell r3cell = createFooterCell(map.get(ConstantParameter.MtrackInvoiceAttr.BR_ACCOUNT_BRANCH_ADDRESS), "padding", fontValue);
				r3cell.setColspan(2);
				
				table.addCell(r3cell);
				
				// footer row 5
				PdfPCell r4cell = createFooterCell("Account Number : " + map.get(ConstantParameter.MtrackInvoiceAttr.BR_ACCOUNT_NUMBER_IDR), "padding", fontValue);
				r4cell.setColspan(2);
				
				table.addCell(r4cell);
				
				// footer row 6
				PdfPCell r5cell = createFooterCell("Account Number : " + map.get(ConstantParameter.MtrackInvoiceAttr.BR_ACCOUNT_NUMBER_USD), "padding", fontValue);
				r5cell.setColspan(2);
				
				table.addCell(r5cell);
				
				// footer row 7
				PdfPCell r6cell = createFooterCell("SWIFT Code         : " + map.get(ConstantParameter.MtrackInvoiceAttr.BR_SWIFT_CODE), "padding", fontValue);
				r6cell.setColspan(2);
				
				table.addCell(r6cell);
				
				// footer row 8
				PdfPCell r7blank = createFooterCell(" ", "padding", fontValue);
				PdfPCell r7cell = createFooterCell(map.get(ConstantParameter.MtrackInvoiceAttr.BR_DIRECTOR_NAME), "sign", fontHeader);
				r7cell.setColspan(2);
				
				table.addCell(r7blank);
				table.addCell(r7cell);
				
				// footer row 9
				PdfPCell r8blank = createFooterCell(" ", "padding", fontValue);
				PdfPCell r8cell = createFooterCell("Director", "sign", fontValue);
				r8cell.setColspan(2);
				
				table.addCell(r8blank);
				table.addCell(r8cell);
				
						
				table.setSpacingAfter(100f);		
		return table;
	}
	
	public static PdfPTable createTablePageFooter(HashMap<String, String> map) throws Exception {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 8f });
		Font fontText = new Font(FontFamily.HELVETICA, 5, Font.ITALIC, BaseColor.GRAY);	

		PdfPCell cellSeparator = new PdfPCell(new Phrase(" ", fontText));
		cellSeparator.setMinimumHeight(1f);
		cellSeparator.setFixedHeight(2f);
		cellSeparator.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
		cellSeparator.setBorderWidth(1f);
		cellSeparator.setBorderColor(BaseColor.BLACK);
		cellSeparator.setPaddingBottom(10f);
		table.addCell(cellSeparator);		
		
		
		
		PdfPCell row1 = new PdfPCell(new Phrase(map.get(ConstantParameter.MtrackInvoiceAttr.BR_NAME_FOOTER), fontText));
		row1.setHorizontalAlignment(Element.ALIGN_CENTER);
		row1.setBorder(Rectangle.NO_BORDER);
		PdfPCell row2 = new PdfPCell(new Phrase(map.get(ConstantParameter.MtrackInvoiceAttr.BR_ADDRESS_FOOTER), fontText));
		row2.setHorizontalAlignment(Element.ALIGN_CENTER);
		row2.setBorder(Rectangle.NO_BORDER);
		PdfPCell row3 = new PdfPCell(new Phrase(map.get(ConstantParameter.MtrackInvoiceAttr.BR_FINANCE_CONTACT), fontText));
		row3.setHorizontalAlignment(Element.ALIGN_CENTER);
		row3.setBorder(Rectangle.NO_BORDER);
		
		
		table.addCell(row1);
		table.addCell(row2);
		table.addCell(row3);
		//table.isExtendLastRow(true);
		return table;
	}

	private static PdfPCell createInfoCell(String text, String part, Font font) {
		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		// set style
		MtrackInvoiceStyler.infoCellStyle(cell, part);
		return cell;
	}
	
	private static PdfPCell createBillingCell(String text, String part, Font font) {
		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		// set style
		MtrackInvoiceStyler.billCellStyle(cell, part);
		return cell;
	}
	
	private static PdfPCell createFooterCell(String text, String part, Font font) {
		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		// set style
		MtrackInvoiceStyler.footerCellStyle(cell, part);
		return cell;
	}

}
