package com.metro.ticket.service;

import org.springframework.stereotype.Service;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.metro.ticket.model.Ticket;

@Service
public class TicketPdfService {

    public byte[] generateTicketPdf(Ticket t) {

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf);

            doc.setFontSize(10);

            doc.add(new Paragraph("PUNE METRO").setBold().setTextAlignment(TextAlignment.CENTER));

            ImageData qr = ImageDataFactory.create(t.getQrCode());
            Image qrImg = new Image(qr).scaleToFit(150, 150);
            qrImg.setHorizontalAlignment(HorizontalAlignment.CENTER);

            doc.add(qrImg);

            doc.add(new Paragraph("Ticket No: " + t.getQrToken()));
            doc.add(new Paragraph("From: " + t.getFromStation()));
            doc.add(new Paragraph("To: " + t.getToStation()));
            doc.add(new Paragraph("Fare: ₹" + t.getFare()));
            doc.add(new Paragraph("Valid Upto: " + t.getValidUpto()));

            doc.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}