package common;

import java.util.HashMap;

import com.brightrich.model.MtrackInvoice;
import com.brightrich.model.MtrackItem;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class TaxInvoiceBuilder {

	public static PdfPTable createTaxHeader(MtrackInvoice invoice) throws Exception{
		PdfPTable table = new PdfPTable(2);

		table.setWidthPercentage(100);

		table.setWidths(new float[] { 4f, 4f });
		
		Font fontHeader = new Font(FontFamily.HELVETICA, 26, Font.BOLD, BaseColor.BLACK);
		Font fontText = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
		Font fontText1 = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
		
		PdfPCell cell = createCell("FAKTUR PAJAK", fontHeader);
		cell.setRowspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		
		PdfPCell cell1 = createCell("Lembar ke-1: Untuk Pembeli", fontText);
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		table.addCell(cell);
		table.addCell(cell1);
		
		PdfPCell r1cell = createCell("BKP/ Penerima JKP", fontText);
		r1cell.setBorder(Rectangle.NO_BORDER);
		r1cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		table.addCell(r1cell);
		
		PdfPCell r2cell = createCell("Sebagai bukti Pajak", fontText);
		r2cell.setBorder(Rectangle.NO_BORDER);
		r2cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		table.addCell(r2cell);
		
		PdfPCell r3cell = createCell("Kode dan Nomor Seri Faktur Pajak : " + invoice.getTaxInvNumber(), fontText1);
		r3cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
		
		//INVOICE NUMBER
		PdfPCell r3cell1 = createCell("No. Invoice: " + invoice.getInvoiceNo(), fontText1);
		r3cell1.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
		r3cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		
		table.addCell(r3cell);
		table.addCell(r3cell1);
		//table.setSpacingAfter(10f);
		return table;
	}
	
	public static PdfPTable createTaxBody(HashMap<String,String> map, MtrackInvoice invoice) throws Exception{
		PdfPTable table = new PdfPTable(3);

		table.setWidthPercentage(100);

		table.setWidths(new float[] { 2f,0.2f, 5.8f});
		
		Font fontText = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
		
		//row1
		PdfPCell cell = createCell("Pengusaha Kena Pajak", fontText);
		cell.setColspan(3);
		cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
		table.addCell(cell);
		
		//row2
		PdfPCell r2cell = createCell("Nama", fontText);
		r2cell.setBorder(Rectangle.LEFT);
		r2cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell r2cell1 = createCell(":", fontText);
		r2cell1.setBorder(Rectangle.NO_BORDER);
		r2cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		//company name to uppercase
		PdfPCell r2cell2 = createCell(map.get(ConstantParameter.MtrackInvoiceAttr.BR_NAME).toUpperCase(), fontText);
		r2cell2.setBorder(Rectangle.RIGHT);
		r2cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		table.addCell(r2cell);
		table.addCell(r2cell1);
		table.addCell(r2cell2);
		
		//row3
		PdfPCell r3cell = createCell("Alamat", fontText);
		r3cell.setBorder(Rectangle.LEFT);
		r3cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell r3cell1 = createCell(":", fontText);
		r3cell1.setBorder(Rectangle.NO_BORDER);
		r3cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		//ADDRESS
		PdfPCell r3cell2 = createCell(map.get(ConstantParameter.MtrackInvoiceAttr.BR_ADDRESS), fontText);
		r3cell2.setBorder(Rectangle.RIGHT);
		r3cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		
		table.addCell(r3cell);
		table.addCell(r3cell1);
		table.addCell(r3cell2);
		
		/*//row4
		PdfPCell r4cell = createCell(" ", fontText));
		r4cell.setBorder(Rectangle.LEFT);
		r4cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell r4cell1 = createCell(" ", fontText));
		r4cell1.setBorder(Rectangle.NO_BORDER);
		r4cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell r4cell2 = createCell("KUNINGAN TIMUR - SETIABUDI, JAKARTA - SELATAN", fontText));
		r4cell2.setBorder(Rectangle.RIGHT);
		r4cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		table.addCell(r4cell);
		table.addCell(r4cell1);
		table.addCell(r4cell2);*/
		
		//row5
		PdfPCell r5cell = createCell("NPWP", fontText);
		r5cell.setBorder(Rectangle.LEFT);
		r5cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell r5cell1 = createCell(":", fontText);
		r5cell1.setBorder(Rectangle.NO_BORDER);
		r5cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		// BR NPWP
		PdfPCell r5cell2 = createCell(map.get(ConstantParameter.MtrackInvoiceAttr.BR_NPWP), fontText);
		r5cell2.setBorder(Rectangle.RIGHT);
		r5cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		table.addCell(r5cell);
		table.addCell(r5cell1);
		table.addCell(r5cell2);
		
		//----------------------------------------------------- BUYER TAX----------------------------------------------------------------------//
		//row6 -- BUYER
		PdfPCell r6cell = createCell("Pembeli Barang Kena Pajak/Penerima Jasa Kena Pajak", fontText);
		r6cell.setColspan(3);
		r6cell.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT);
		table.addCell(r6cell);
		
		//row7
				PdfPCell r7cell = createCell("Nama", fontText);
				r7cell.setBorder(Rectangle.LEFT );
				r7cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				PdfPCell r7cell1 = createCell(":", fontText);
				r7cell1.setBorder(Rectangle.NO_BORDER);
				r7cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				// CLIENT NAME
				PdfPCell r7cell2 = createCell(invoice.getCompanyId().getName(), fontText);
				r7cell2.setBorder(Rectangle.RIGHT);
				r7cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				table.addCell(r7cell);
				table.addCell(r7cell1);
				table.addCell(r7cell2);
				
				//row8
				PdfPCell r8cell = createCell("Alamat", fontText);
				r8cell.setBorder(Rectangle.LEFT );
				r8cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				PdfPCell r8cell1 = createCell(":", fontText);
				r8cell1.setBorder(Rectangle.NO_BORDER);
				r8cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				
				//CLIENT ADDRESS
				PdfPCell r8cell2 = createCell(invoice.getCompanyId().getAddress(), fontText);
				r8cell2.setBorder(Rectangle.RIGHT);
				r8cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				table.addCell(r8cell);
				table.addCell(r8cell1);
				table.addCell(r8cell2);
				
				//row9
				PdfPCell r9cell = createCell("NPWP", fontText);
				r9cell.setBorder(Rectangle.LEFT);
				r9cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				PdfPCell r9cell1 = createCell(":", fontText);
				r9cell1.setBorder(Rectangle.NO_BORDER);
				r9cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				
				//CLIENT NPWP
				PdfPCell r9cell2 = createCell(invoice.getCompanyId().getNPWP(), fontText);
				r9cell2.setBorder(Rectangle.RIGHT);
				r9cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				table.addCell(r9cell);
				table.addCell(r9cell1);
				table.addCell(r9cell2);
		
		//table.setSpacingAfter(10f);
		return table;
	}
	
	public static PdfPTable createTaxDetail(MtrackItem item, MtrackInvoice invoice, HashMap<String,String> map) throws Exception{
		PdfPTable table = new PdfPTable(4);

		table.setWidthPercentage(100);

		table.setWidths(new float[] { 0.5f,3.5f, 2f, 2f});
		
		Font fontText = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
		
		//rowheader
		PdfPCell cell = createCell("No. Urut", fontText);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.BOX);
		cell.setRowspan(2);
		
		PdfPCell cell1 = createCell("Nama Barang Kena Pajak / Jasa Kena Pajak", fontText);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBorder(Rectangle.BOX);
		cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell1.setRowspan(2);
		
		PdfPCell cell2 = createCell("Harga Jual / Penggantian /  Uang Muka / Termin", fontText);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setBorder(Rectangle.BOX);
		cell2.setColspan(2);
		
		table.addCell(cell);
		table.addCell(cell1);
		table.addCell(cell2);
		
		
		//row header2
		PdfPCell h2cell = createCell("Valas", fontText);
		h2cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		h2cell.setBorder(Rectangle.BOX);
		
		PdfPCell h2cell1 = createCell("Rp.", fontText);
		h2cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		h2cell1.setBorder(Rectangle.BOX);
		
		table.addCell(h2cell);
		table.addCell(h2cell1);
		
		//======================================================= DETAIL VALUE============================================================//
		
		//row1
		PdfPCell r1cell = createCell("1.", fontText);
		r1cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		r1cell.setBorder(Rectangle.BOX);
		
		//ITEM TYPE
		PdfPCell r1cell1 = createCell(item.getDescription(), fontText);
		r1cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		r1cell1.setBorder(Rectangle.BOX);
		
		PdfPCell r1cell2 = createCell(" ", fontText);
		r1cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		r1cell2.setBorder(Rectangle.BOX);
		
		// TOTAL INVOICE
		PdfPCell r1cell3 = createCell(Tools.formatrupiah(map.get(ConstantParameter.MtrackInvoiceAttr.SUBTOTAL)), fontText);
		r1cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		r1cell3.setBorder(Rectangle.BOX);
		
		table.addCell(r1cell);
		table.addCell(r1cell1);
		table.addCell(r1cell2);
		table.addCell(r1cell3);
		
		
		//============================================================== TOTAL =============================================================//
		
		//row1
		PdfPCell t1cell = createCell("Harga Jual / Penggantian / Uang Muka / Termin**)", fontText);
		t1cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		t1cell.setColspan(2);
		t1cell.setBorder(Rectangle.BOX);
		
		PdfPCell t1cell1 = createCell("-", fontText);
		t1cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		t1cell1.setBorder(Rectangle.BOX);
		
		//TOTAL INVOICE
		PdfPCell t1cell2 = createCell(Tools.formatrupiah(map.get(ConstantParameter.MtrackInvoiceAttr.SUBTOTAL)), fontText);
		t1cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		t1cell2.setBorder(Rectangle.BOX);
		
		table.addCell(t1cell);
		table.addCell(t1cell1);
		table.addCell(t1cell2);
		
		//row2
		PdfPCell t2cell = createCell("Dikurangi Potongan Harga", fontText);
		t2cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		t2cell.setColspan(2);
		t2cell.setBorder(Rectangle.BOX);
		
		PdfPCell t2cell1 = createCell("-", fontText);
		t2cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		t2cell1.setBorder(Rectangle.BOX);
		
		PdfPCell t2cell2 = createCell(" ", fontText);
		t2cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		t2cell2.setBorder(Rectangle.BOX);
		
		table.addCell(t2cell);
		table.addCell(t2cell1);
		table.addCell(t2cell2);
		
		//row3
		PdfPCell t3cell = createCell("Dikurangi Uang Muka Yang Telah Diterima", fontText);
		t3cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		t3cell.setColspan(2);
		t3cell.setBorder(Rectangle.BOX);
		
		PdfPCell t3cell1 = createCell("-", fontText);
		t3cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		t3cell1.setBorder(Rectangle.BOX);
		
		PdfPCell t3cell2 = createCell(" ", fontText);
		t3cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		t3cell2.setBorder(Rectangle.BOX);
		
		table.addCell(t3cell);
		table.addCell(t3cell1);
		table.addCell(t3cell2);
		
		//row4
		PdfPCell t4cell = createCell("Dasar Pengenaan Pajak", fontText);
		t4cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		t4cell.setColspan(2);
		t4cell.setBorder(Rectangle.BOX);
		
		PdfPCell t4cell1 = createCell(" ", fontText);
		t4cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		t4cell1.setBorder(Rectangle.BOX);
		
		//TOTAL INVOICE
		PdfPCell t4cell2 = createCell(Tools.formatrupiah(map.get(ConstantParameter.MtrackInvoiceAttr.SUBTOTAL)), fontText);
		t4cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		t4cell2.setBorder(Rectangle.BOX);
		
		table.addCell(t4cell);
		table.addCell(t4cell1);
		table.addCell(t4cell2);
		
		//row5
		PdfPCell t5cell = createCell("PPN = 10% x Dasar Penggenaan Pajak ", fontText);
		t5cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		t5cell.setColspan(2);
		t5cell.setBorder(Rectangle.BOX);
		
		PdfPCell t5cell1 = createCell(" ", fontText);
		t5cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		t5cell1.setBorder(Rectangle.BOX);
		
		//VAT
		PdfPCell t5cell2 = createCell(Tools.formatrupiah(map.get(ConstantParameter.MtrackInvoiceAttr.VAT)), fontText);
		t5cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		t5cell2.setBorder(Rectangle.BOX);
		
		table.addCell(t5cell);
		table.addCell(t5cell1);
		table.addCell(t5cell2);
		
		return table;
	}
	
	public static PdfPTable createTaxFooter(MtrackInvoice invoice, HashMap<String, String> map) throws Exception{
		PdfPTable table = new PdfPTable(5);

		table.setWidthPercentage(100);

		table.setWidths(new float[] { 0.5f,0.5f, 1.5f, 1.5f, 4f});
		
		Font fontText = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
		Font fontSign = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);
		Font fontLabel = new Font(FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);
		
		//row1
		PdfPCell cell = createCell(" ", fontText);
		cell.setBorder(Rectangle.LEFT);
		cell.setRowspan(9);
		
		PdfPCell cell1 = createCell("Pajak Penjualan Atas Barang Mewah", fontText);
		cell1.setBorder(Rectangle.RIGHT);
		cell1.setColspan(4);
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		table.addCell(cell);
		table.addCell(cell1);
		
		//row2
		PdfPCell r2cell = createCell(" ", fontText);
		r2cell.setBorder(Rectangle.RIGHT);
		r2cell.setColspan(4);
		
		table.addCell(r2cell);
		
		//row3 ------------------------------------- TABLE INSIDE---------------------------------------------------------------------------//
		PdfPCell r3cell = createCell("TARIF", fontLabel);
		r3cell.setBorder(Rectangle.BOX);
		r3cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		PdfPCell r3cell1 = createCell("DPP", fontLabel);
		r3cell1.setBorder(Rectangle.BOX);
		r3cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		PdfPCell r3cell2 = createCell("PPn BM", fontLabel);
		r3cell2.setBorder(Rectangle.BOX);
		r3cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		
		//Invoice DATE
		PdfPCell r3cell3 = createCell("Jakarta, " + Tools.monddyyyy.format(invoice.getInvoiceDate()), fontText);
		r3cell3.setPaddingRight(30f);
		r3cell3.setBorder(Rectangle.RIGHT);
		r3cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		table.addCell(r3cell);
		table.addCell(r3cell1);
		table.addCell(r3cell2);
		table.addCell(r3cell3);
		
		//row4
		PdfPCell r4cell = createCell("... %", fontText);
		r4cell.setBorder(Rectangle.BOX);
		
		PdfPCell r4cell1 = createCell("Rp ...............", fontText);
		r4cell1.setBorder(Rectangle.BOX);
		
		PdfPCell r4cell2 = createCell("Rp ...............", fontText);
		r4cell2.setBorder(Rectangle.BOX);
		
		PdfPCell r4cell3 = createCell(" ", fontText);
		r4cell3.setBorder(Rectangle.RIGHT);
		r4cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		
		table.addCell(r4cell);
		table.addCell(r4cell1);
		table.addCell(r4cell2);
		table.addCell(r4cell3);
		
		
		//row5
		PdfPCell r5cell = createCell("... %", fontText);
		r5cell.setBorder(Rectangle.BOX);
		
		PdfPCell r5cell1 = createCell("Rp ...............", fontText);
		r5cell1.setBorder(Rectangle.BOX);
		
		PdfPCell r5cell2 = createCell("Rp ...............", fontText);
		r5cell2.setBorder(Rectangle.BOX);
		
		PdfPCell r5cell3 = createCell(" ", fontText);
		r5cell3.setBorder(Rectangle.RIGHT);
		r5cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		
		table.addCell(r5cell);
		table.addCell(r5cell1);
		table.addCell(r5cell2);
		table.addCell(r5cell3);
		
		
		//row6
		PdfPCell r6cell = createCell("... %", fontText);
		r6cell.setBorder(Rectangle.BOX);
		
		PdfPCell r6cell1 = createCell("Rp ...............", fontText);
		r6cell1.setBorder(Rectangle.BOX);
		
		PdfPCell r6cell2 = createCell("Rp ...............", fontText);
		r6cell2.setBorder(Rectangle.BOX);
		
		PdfPCell r6cell3 = createCell(" ", fontText);
		r6cell3.setBorder(Rectangle.RIGHT);
		r6cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
			
		table.addCell(r6cell);
		table.addCell(r6cell1);
		table.addCell(r6cell2);
		table.addCell(r6cell3);
		
		
		//row7
		PdfPCell r7cell = createCell("... %", fontText);
		r7cell.setBorder(Rectangle.BOX);
		
		PdfPCell r7cell1 = createCell("Rp ...............", fontText);
		r6cell1.setBorder(Rectangle.BOX);
		
		PdfPCell r7cell2 = createCell("Rp ...............", fontText);
		r7cell2.setBorder(Rectangle.BOX);
		
		PdfPCell r7cell3 = createCell(" ", fontText);
		r7cell3.setBorder(Rectangle.RIGHT);
		r7cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		
		table.addCell(r7cell);
		table.addCell(r7cell1);
		table.addCell(r7cell2);
		table.addCell(r7cell3);
		
		//row 8
		PdfPCell r8cell = createCell("Jumlah", fontText);
		r8cell.setColspan(2);
		r8cell.setRowspan(2);
		r8cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
		
		PdfPCell r8cell1 = createCell("Rp ...............", fontText);
		r8cell1.setRowspan(2);
		r8cell1.setBorder(Rectangle.RIGHT  | Rectangle.BOTTOM);
		
		PdfPCell r8cell2 = createCell(" ", fontText);
		r8cell2.setRowspan(2);
		r8cell2.setBorder(Rectangle.RIGHT);
		
		table.addCell(r8cell);
		table.addCell(r8cell1);
		table.addCell(r8cell2);				
		
		//row10
		PdfPCell r10cell = createCell(" ", fontText);
		r10cell.setColspan(4);
		r10cell.setBorder(Rectangle.LEFT);
		
		//BR DIRECTOR
		PdfPCell r10cell1 = createCell(map.get(ConstantParameter.MtrackInvoiceAttr.BR_DIRECTOR_NAME), fontSign);
		r10cell1.setBorder(Rectangle.RIGHT);
		r10cell1.setPaddingRight(40f);
		r10cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		table.addCell(r10cell);
		table.addCell(r10cell1);
		
		
		//row11
		PdfPCell r11cell = createCell("Catatan: Kurs Pajak Rp. 0 / 1 USD", fontText);
		r11cell.setColspan(4);
		r11cell.setPaddingLeft(10f);
		r11cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
		
		PdfPCell r11cell1 = createCell("Director", fontText);
		r11cell1.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
		r11cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		r11cell1.setPaddingRight(50f);
		
		table.addCell(r11cell);
		table.addCell(r11cell1);
		
		/*PdfPCell r2cell = createCell(" ", fontText));
		r2cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
		r2cell.setColspan(4);
		*/
		return table;
		
	}
	
	private static PdfPCell createCell(String text, Font font) {
		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		// set style
		cell.setMinimumHeight(20f);
		return cell;
	}
	
}
