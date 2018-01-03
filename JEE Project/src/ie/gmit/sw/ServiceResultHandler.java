package ie.gmit.sw;

import java.io.*;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ie.gmit.sw.CompareDocumentFacade;
import ie.gmit.sw.ComparisionResult;

@SuppressWarnings("serial")
@WebServlet("/doGetResult")

public class ServiceResultHandler extends HttpServlet {
	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html"); 
		PrintWriter out = resp.getWriter(); 
	 		out.print("<html><head><title>A JEE Application for Measuring Document Similarity</title>");		
		out.print("<link rel=\"stylesheet\" href=\"includes/basic.css\">");
		out.print("</head>");		
		out.print("<body>");
		out.print("<H1>Summary of All Comparision Results</H1>");
	 
		out.print("</font> <font color=\"ff00ff\"><br/><br/>");	
		CompareDocumentFacade objCompareDocumentFacade=new CompareDocumentFacade();
		List<ComparisionResult> arrayListComparisionResult=objCompareDocumentFacade.getAllCompasionResult();
 
		out.print("<div align=\"center\" class=\"tbl-header\"><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><thead><tr><th>Upload Docuemt Title</th><th>Compared Docuemt Title</th><th>Similarity Percentage</th></tr></thead></table></div><div align=\"center\" class=\"tbl-content\"> "
				+ "<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"> <tbody>");
		for (ComparisionResult comparisionResult : arrayListComparisionResult) {
			out.print("<tr><td>"+comparisionResult.getDocumentTitle()+"</td><td>"+comparisionResult.getComapredDocumentTitle()+"</td><td>"+df.format(comparisionResult.getResult() ) +"</td></tr>");
			 
		}
		out.print("<tbody></table></div>");
		 
	 							
		out.print("</body>");	
		out.print("</html>");
		
	}
		DecimalFormat df = new DecimalFormat("0.00");
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
 	}
	
	

}
