package com.abdullah.PayrollManagementSystem.report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.abdullah.PayrollManagementSystem.dao.Attendance;
import com.abdullah.PayrollManagementSystem.dao.Loan;
import com.abdullah.PayrollManagementSystem.dao.Salary;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class LoanPdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Loan> loanList = (List<Loan>) model.get("loanList");

		//String currentMonthString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
		
		Table table = new Table(4);
		table.addCell("Fullname");
		table.addCell("Loan Place Date");
		table.addCell("Total Loan amount");
		table.addCell("Loan Status");
		
		for (Loan loan : loanList) {
			table.addCell(loan.getFullname());
			table.addCell(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(loan.getPlacedate()));
			table.addCell(String.valueOf(loan.getAmount()));
			table.addCell(String.valueOf(loan.getStatus()));
		}
		document.add(table);	
	}
}
