package common;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopyFields;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;


public class PdfMerger {

	public static void main(String[] args) {
	    try {
	      List<InputStream> pdfs = new ArrayList<InputStream>();
	      List<String> fileList = new ArrayList<String>();
	      String uploadDir= ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_ORI+"BRT.INV.01.04_30-10-2013.pdf";
	      
	      fileList.add(ConstantParameter.MtrackInvoiceAttr.UPLOAD_DIR_INV+"BRT.INV.01.04_30-10-2013.pdf");
	      fileList.add(ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_BILL+"BillingSummary.PDF");
	      
	      PdfMerger.concatPDFFiles(fileList, uploadDir);
	      System.out.println("DONE!!!");
	      
	      /*pdfs.add(new FileInputStream(ConstantParameter.MtrackInvoiceAttr.UPLOAD_DIR_INV+"BRT.INV.01.04_30-10-2013.pdf"));
	      pdfs.add(new FileInputStream(ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_BILL+"BillingSummary.PDF"));
	      //
	      OutputStream output = new FileOutputStream(ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_ORI+"BRT.INV.01.04_30-10-2013.pdf");
	      PdfMerger.concatPDFs(pdfs, output, true);*/
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	  public static void concatPDFs(List<InputStream> streamOfPDFFiles, OutputStream outputStream, boolean paginate) {

	    Document document = new Document();
	    try {
	      List<InputStream> pdfs = streamOfPDFFiles;
	      List<PdfReader> readers = new ArrayList<PdfReader>();
	      int totalPages = 0;
	      Iterator<InputStream> iteratorPDFs = pdfs.iterator();

	      // Create Readers for the pdfs.
	      while (iteratorPDFs.hasNext()) {
	        InputStream pdf = iteratorPDFs.next();
	        PdfReader pdfReader = new PdfReader(pdf);
	        PdfReader.unethicalreading = true;
	        readers.add(pdfReader);
	        totalPages += pdfReader.getNumberOfPages();
	      }
	      // Create a writer for the outputstream
	      PdfWriter writer = PdfWriter.getInstance(document, outputStream);

	      document.open();
	      
	      //BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	      PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
	      // data

	      PdfImportedPage page;
	      int currentPageNumber = 0;
	      int pageOfCurrentReaderPDF = 0;
	      Iterator<PdfReader> iteratorPDFReader = readers.iterator();

	      // Loop through the PDF files and add to the output.
	      while (iteratorPDFReader.hasNext()) {
	        PdfReader pdfReader = iteratorPDFReader.next();

	        // Create a new page in the target for each source page.
	        while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
	          document.newPage();
	          pageOfCurrentReaderPDF++;
	          currentPageNumber++;
	          page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
	          cb.addTemplate(page, 0, 0);

	          // Code for pagination.
	          /*if (paginate) {
	            cb.beginText();
	            cb.setFontAndSize(bf, 9);
	            cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages, 520, 5, 0);
	            cb.endText();
	          }*/
	        }
	        pageOfCurrentReaderPDF = 0;
	      }
	      outputStream.flush();
	      document.close();
	      outputStream.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      if (document.isOpen())
	        document.close();
	      try {
	        if (outputStream != null)
	          outputStream.close();
	      } catch (IOException ioe) {
	        ioe.printStackTrace();
	      }
	    }
	  }
	  
	  public static boolean concatPDFFiles(List<String> listOfFiles,
		        String outputfilepath) throws FileNotFoundException, DocumentException {
		    PdfCopyFields copy = null;
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    try {
		        //copy = new PdfCopyFields(new FileOutputStream(""));
		    	copy = new PdfCopyFields(baos);
		    } catch (DocumentException ex) {
		    	System.out.println("1");
		        ex.printStackTrace();
		    }
		    try {
		        for (String fileName : listOfFiles) {
		            PdfReader reader1 = new PdfReader(fileName);
		            PdfReader.unethicalreading = true;
		            copy.addDocument(reader1);
		            System.out.println("READ");
		        }
		    } catch (IOException ex) {
		    	ex.printStackTrace();
		    	System.out.println("2");
		    } finally {
		        copy.close();
		    }
		    
		    System.out.println("file bytes = " + new String(baos.toByteArray()));
		    /*if (new File(outputfilepath).exists()) {
		        double bytes = new File(outputfilepath).length();
		        //double kilobytes = (bytes / 1024);
		        if (bytes != 0) {
		            return true;
		        } else {
		            return false;
		        }
		    } else {
		        return false;
		    }*/
		    
		    return true;
		}
	
}
