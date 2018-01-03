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
@WebServlet("/poll")
public class ServicePollHandler extends HttpServlet {
	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html"); 
		PrintWriter out = resp.getWriter(); 
		
		String title = req.getParameter("txtTitle");
		String taskNumber = req.getParameter("frmTaskNumber");
		String file_content = req.getParameter("file_content");
		int counter = 1;
		if (req.getParameter("counter") != null){
			counter = Integer.parseInt(req.getParameter("counter"));
			counter++;
		}

		
		
		out.print("<html><head><title>A JEE Application for Measuring Document Similarity</title>");		
		out.print("<link rel=\"stylesheet\" href=\"includes/basic.css\">");
		out.print("</head>");		
		out.print("<body>");
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<H3>Document Title: " + title + "</H3>");
		
		out.print("</font> <font color=\"D15722\"><br/><br/>");	
		CompareDocumentFacade objCompareDocumentFacade=new CompareDocumentFacade();
		List<ComparisionResult> arrayListComparisionResult=objCompareDocumentFacade.comapareAndSaveDoucument(title, file_content);
		out.print("<div align=\"center\" class=\"tbl-header\"><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><thead><tr><th>Upload Docuemt Title</th><th>Compared Docuemt Title</th><th>Similarity Percentage</th></tr></thead></table></div><div align=\"center\" class=\"tbl-content\"> "
				+ "<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"> <tbody>");
		for (ComparisionResult comparisionResult : arrayListComparisionResult) {
			out.print("<tr><td>"+title+"</td><td>"+comparisionResult.getComapredDocumentTitle()+"</td><td>"+df.format(comparisionResult.getResult() ) +"</td></tr>");
			 
		}
		out.print("<tbody></table></div>");
		
		out.print("<b><font color=\"ff0000\">A total of " + counter + " polls have been made for this request.</font></b> ");
		 
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"txtTitle\" type=\"hidden\" value=\"" + title + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("<input name=\"counter\" type=\"hidden\" value=\"" + counter + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");	
		
//		out.print("<script>");
//		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 5000);"); //Refresh every 5 seconds
//		out.print("</script>");
	}
	
		DecimalFormat df = new DecimalFormat("0.00");
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
 	}
}