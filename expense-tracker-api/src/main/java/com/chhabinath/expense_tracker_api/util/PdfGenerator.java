package com.chhabinath.expense_tracker_api.util;

import java.util.List;

import com.chhabinath.expense_tracker_api.entity.Expense;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.element.Paragraph;

import java.io.OutputStream;


public class PdfGenerator {

    public static void generateExpenseReport(OutputStream outputStream, List<Expense> expenses) throws Exception {
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Expense Report").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                .setFontSize(18).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("\n"));

        Table table = new Table(6); // 6 columns

        table.addHeaderCell("ID");
        table.addHeaderCell("Title");
        table.addHeaderCell("Category");
        table.addHeaderCell("Amount");
        table.addHeaderCell("Date");
        table.addHeaderCell("Description");

        for (Expense expense : expenses) {
            table.addCell(expense.getId().toString());
            table.addCell(expense.getTitle());
            table.addCell(expense.getCategory());
            table.addCell(String.format("%.2f", expense.getAmount()));
            table.addCell(expense.getDate().toString());
            table.addCell(expense.getDescription());
        }

        document.add(table);
        document.close();
    }
}