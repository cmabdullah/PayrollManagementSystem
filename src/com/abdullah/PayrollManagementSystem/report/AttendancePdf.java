package com.abdullah.PayrollManagementSystem.report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.abdullah.PayrollManagementSystem.dao.Attendance;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class AttendancePdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Attendance> attendanceList = (List<Attendance>) model.get("attendanceList");

		//String currentMonthString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
		
		Table table = new Table(5);
		table.addCell("User ID");
		table.addCell("Login time");
		table.addCell("Logout time");
		table.addCell("IP Address");
		table.addCell("Working Hours");
		
		
		for (Attendance attendance : attendanceList) {
			table.addCell(String.valueOf(attendance.getUserinfo_id()));
			table.addCell(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(attendance.getLogintime()));
			table.addCell(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(attendance.getLogouttime()));
			table.addCell(String.valueOf(attendance.getIpaddress()));
			table.addCell(String.valueOf(attendance.getWorkinghours()));
		}
		document.add(table);	
	}
}
