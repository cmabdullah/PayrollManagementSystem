package com.abdullah.PayrollManagementSystem.report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.abdullah.PayrollManagementSystem.dao.Attendance;
import com.abdullah.PayrollManagementSystem.dao.Salary;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class SalaryPdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Salary> salaryList = (List<Salary>) model.get("salaryList");

		//String currentMonthString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
		
		Table table = new Table(5);
		table.addCell("Fullname");
		//table.addCell("email");
		table.addCell("Salary Payment Date");
		table.addCell("Total Salary");
		//table.addCell("Loan Id");
		//table.addCell("Basic");
		table.addCell("Loan Amount");
		table.addCell("Loan Status");
		
		
		
		for (Salary salary : salaryList) {
		//	table.addCell(salary.getFullname());
			table.addCell(salary.getEmail());
			table.addCell(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(salary.getDatemonthyear()));
			table.addCell(String.valueOf(salary.getTotalsalary()));
//			table.addCell(String.valueOf(salary.getLoan_id()));
//			table.addCell(String.valueOf(salary.getBasic()));
			table.addCell(String.valueOf(salary.getAmount()));
			table.addCell(String.valueOf(salary.getStatus()));
			
		}
		document.add(table);	
	}
}
