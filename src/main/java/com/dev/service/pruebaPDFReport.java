package com.dev.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

import com.dev.model.Answer;
import com.dev.model.Difficulties;
import com.dev.model.Test;
import com.dev.model.TestItem;
import com.dev.model.User;
import com.dev.repository.TestRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class pruebaPDFReport {

	Difficulties difficulties;
	Answer answer = new Answer();
	TestItem item = new TestItem();
	Document doc = new Document();
	private static Font keyFont = new Font(FontFamily.HELVETICA, 18, Font.BOLD);
	private static Font textfont_H = new Font(FontFamily.HELVETICA, 12, Font.NORMAL);
	private static Font textfont_B = new Font(FontFamily.HELVETICA, 12, Font.UNDERLINE);
	
	/**
	 * Creates a cell for a table
	 * @param value
	 * @param font
	 * @param align_v
	 * @param align_h
	 * @param colspan
	 * @param rowspan
	 * @param cellBorder
	 * @param height
	 * @param correct 
	 * @return
	 */
	private PdfPCell createCell(String value,Font font,int align_v,int align_h,int colspan,int rowspan, int cellBorder, float height,boolean correct){
		PdfPCell cell = new PdfPCell();
		cell.setBorder(cellBorder);
		cell.setVerticalAlignment(align_v);
		cell.setHorizontalAlignment(align_h);
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);
		cell.setFixedHeight(height);
		cell.setPhrase(new Phrase(value,font));
		if(correct) {
			cell.setBackgroundColor(new BaseColor(230,244,234,255));
		}else {
			cell.setBackgroundColor(new BaseColor(252,232,230,255));
		}
		return cell;
	}
	
	/**
	 * Creates cell for a table
	 * @param value
	 * @param font
	 * @param align_v
	 * @param align_h
	 * @param colspan
	 * @param rowspan
	 * @param cellBorder
	 * @return
	 */
	private PdfPCell createCell(String value,Font font,int align_v,int align_h,int colspan,int rowspan, int cellBorder){
		PdfPCell cell = new PdfPCell();
		cell.setBorder(cellBorder);
		cell.setVerticalAlignment(align_v);
		cell.setHorizontalAlignment(align_h);
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);
		cell.setPhrase(new Phrase(value,font));
		return cell;
	}
	
	/**
	 * Creates paragraph
	 * @param value
	 * @param font
	 * @param alignment
	 * @return
	 */
	private Paragraph createParagraph(String value,Font font,int alignment){
		Paragraph paragraph = new Paragraph();
		paragraph.add(new Phrase(value,font));
		paragraph.setAlignment(alignment);
		return paragraph;
	}
	
	/**
     * Establecer propiedades de página
* @param file
* @return 
*/
public pruebaPDFReport(File file) {

  try {
      PdfWriter.getInstance(doc, new FileOutputStream(file));
      doc.open();
  } catch (Exception e) {
      e.printStackTrace();
  }
}

public void generatePDF(Test test) throws DocumentException, MalformedURLException, IOException{

	PdfPTable headerTable = new PdfPTable(2);
	headerTable.setWidthPercentage(100);
	headerTable.setWidths(new float[] {70f, 350f});

	Image logo = Image.getInstance("logo_horizontal.png");
	logo.scalePercent(35);
	PdfPCell pictureCell = new PdfPCell(logo);
	pictureCell.setBorder(0);
	headerTable.addCell(pictureCell);

	headerTable.addCell(createCell ("Resultado: " + test.getGrade() + "/" + test.getItemsCount() , keyFont, Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT, 0, 0, 0));

	doc.add(headerTable);

	PdfPTable informationTable = new PdfPTable(2);
	informationTable.setWidthPercentage(95);
	informationTable.setWidths(new float[] {50f, 350f});
	informationTable.setSpacingBefore(20);

	informationTable.addCell(createCell("Nombre: ", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 0, 0, 0));
	informationTable.addCell(createCell(test.getUser().getName() , textfont_B, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 0, 0, 0));

	doc.add(informationTable);

	PdfPTable testTable = new PdfPTable(4);
	testTable.setWidthPercentage(95);
	testTable.setWidths(new float[] {60f,300f, 60f, 70f});

	testTable.addCell(createCell("Usuario: ", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 0, 0, 0));
	testTable.addCell(createCell(test.getUser().getNickName(), textfont_B, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 0, 0, 0));
	testTable.addCell(createCell("Fecha: ", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 0, 0, 0));
	testTable.addCell(createCell(test.getDate().toString(), textfont_B, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 0, 0, 0));
	doc.add(testTable);

	testTable.addCell(createCell("Categoria: ", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 0, 0, 0));
	testTable.addCell(createCell(test.getItems().get(0).getQuestion().getSubCategory().getCategory().getCategory() , textfont_B, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 0, 0, 0));
	testTable.addCell(createCell("Dificultad: ", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 0, 0, 0));
	testTable.addCell(createCell(Difficulties.getDifficultie(test.getItems().get(0).getQuestion().getDifficultyLevel()).toString(), textfont_B, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 0, 0, 0));
	testTable.setSpacingAfter(20);
	doc.add(testTable);

	doc.add(createParagraph("Resultados", keyFont, Element.ALIGN_CENTER));

	doc.add(Chunk.NEWLINE);

	PdfPTable resultsTable = new PdfPTable(2);
	resultsTable.setWidthPercentage(95);
	resultsTable.setWidths(new float[] {245f, 245f});

	for(int i = 1; i <= test.getItemsCount(); i++) {
		resultsTable.addCell(createCell(test.getItems().get(i).getQuestion().getQuestion(), textfont_B, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 2, 0, 0));
		doc.add(resultsTable);
		
		for(int j = 1; j <= test.getItems().get(i).getQuestion().getAnswers().size(); j++) {
			
			if(test.getItems().get(i).isSolvedCorrectly()) {

				resultsTable.addCell(createCell(test.getItems().get(i).getQuestion().getAnswers().get(j).getAnswer(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 2, 0, 0, textfont_H.getSize() + 10,true));
				doc.add(resultsTable);
				
			} else if (!test.getItems().get(i).isSolvedCorrectly()) {
				
				resultsTable.addCell(createCell(test.getItems().get(i).getQuestion().getAnswers().get(j).getAnswer(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 2, 0, 0, textfont_H.getSize() + 10,false));
				doc.add(resultsTable);
				
			} else {

				resultsTable.addCell(createCell(test.getItems().get(i).getQuestion().getAnswers().get(j).getAnswer(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 2, 0, 0));
				doc.add(resultsTable);
				
			}

			
			
		}

		doc.add(Chunk.NEWLINE);
	}


    doc.close();
    System.out.println("DONE!!!");
}
	
	public static void main(String[] args) throws IOException, DocumentException {
		File file = new File("test.pdf");
		
		TestRepository tr = null;
		Optional<Test> t = tr.findById(1);
		
        file.createNewFile();
        new pruebaPDFReport(file).generatePDF(t.get());
	}

}
