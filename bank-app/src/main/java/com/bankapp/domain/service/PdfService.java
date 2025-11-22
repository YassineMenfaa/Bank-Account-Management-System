package com.bankapp.domain.service;

import com.bankapp.domain.model.BankAccount;
import com.bankapp.domain.model.User;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    public byte[] generateAccountStatement(User user, List<BankAccount> accounts) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);

            document.open();

            // En-tête
            document.add(new Paragraph("BankApp - Account Statement"));
            document.add(new Paragraph("User: " + user.getName()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph(" "));

            // Tableau des comptes
            PdfPTable table = new PdfPTable(3); // IBAN, solde, devise
            table.setWidthPercentage(100);
            table.addCell("IBAN");
            table.addCell("Balance");
            table.addCell("Currency");

            for (BankAccount account : accounts) {
                table.addCell(account.getIban());
                table.addCell(account.getBalance().toString());
                table.addCell(account.getCurrency().name());
            }

            document.add(table);
            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
