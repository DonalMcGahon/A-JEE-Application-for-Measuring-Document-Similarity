package ie.gmit.sw;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//import core.calculation.CompareDocumentFacade;
//import ie.gmit.sw.ComparisionResult;
import ie.gmit.sw.Configuration;


/* NB: You will need to add the JAR file $TOMCAT_HOME/lib/servlet-api.jar to your CLASSPATH 
 *     variable in order to compile a servlet from a command line.
 */
@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB. The file size in bytes after which the file will be temporarily stored on disk. The default size is 0 bytes.
                 maxFileSize=1024*1024*10,      // 10MB. The maximum size allowed for uploaded files, in bytes
                 maxRequestSize=1024*1024*50)   // 50MB. he maximum size allowed for a multipart/form-data request, in bytes.
public class ServiceHandler extends HttpServlet {
	/* Declare any shared objects here. For example any of the following can be handled from 
	 * this context by instantiating them at a servlet level:
	 *   1) An Asynchronous Message Facade: declare the IN and OUT queues or MessageQueue
	 *   2) An Chain of Responsibility: declare the initial handler or a full chain object
	 *   1) A Proxy: Declare a shared proxy here and a request proxy inside doGet()
	 */
	private String environmentalVariable = null; //Demo purposes only. Rename this variable to something more appropriate
	private static long jobNumber = 0;


	/* This method is only called once, when the servlet is first started (like a constructor). 
	 * It's the Template Patten in action! Any application-wide variables should be initialised 
	 * here. Note that if you set the xml element <load-on-startup>1</load-on-startup>, this
	 * method will be automatically fired by Tomcat when the web server itself is started.
	 */
	public void init() throws ServletException {
		ServletContext ctx = getServletContext(); //The servlet context is the application itself.
		
		//Reads the value from the <context-param> in web.xml. Any application scope variables 
		//defined in the web.xml can be read in as follows:
		System.out.println("BLOCKING_QUEUE_SIZE "+ ctx.getInitParameter("BLOCKING_QUEUE_SIZE"));
		System.out.println("DB_PATH "+ ctx.getInitParameter("DB_PATH"));
		System.out.println("MINHASH_NUMBER "+ ctx.getInitParameter("MINHASH_NUMBER"));
		System.out.println("SHINGLE_SIZE "+ ctx.getInitParameter("SHINGLE_SIZE"));
		
		Configuration.BLOCKING_QUEUE_SIZE =  Integer.parseInt(ctx.getInitParameter("BLOCKING_QUEUE_SIZE"));
		Configuration.DB_PATH = ctx.getInitParameter("DB_PATH");
		Configuration.MINHASH_NUMBER = Integer.parseInt(ctx.getInitParameter("MINHASH_NUMBER"));
		Configuration.SHINGLE_SIZE =  Integer.parseInt(ctx.getInitParameter("SHINGLE_SIZE")); 
	}


	/* The doGet() method handles a HTTP GET request. Please note the following very carefully:
	 *   1) The doGet() method is executed in a separate thread. If you instantiate any objects
	 *      inside this method and don't pass them around (ie. encapsulate them), they will be
	 *      thread safe.
	 *   2) Any instance variables like environmentalVariable or class fields like jobNumber will 
	 *      are shared by threads and must be handled carefully.
	 *   3) It is standard practice for doGet() to forward the method invocation to doPost() or
	 *      vice-versa.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Step 1) Write out the MIME type
		resp.setContentType("text/html"); 
		
		//Step 2) Get a handle on the PrintWriter to write out HTML
		PrintWriter out = resp.getWriter(); 
		
		//Step 3) Get any submitted form data. These variables are local to this method and thread safe...
		String title = req.getParameter("txtTitle");
		String taskNumber = req.getParameter("frmTaskNumber");
		Part part = req.getPart("txtDocument");

		
		//Step 4) Process the input and write out the response. 
		//The following string should be extracted as a context from web.xml 
		out.print("<html><head><title>JEE Application for Measuring Document Similarity</title>");		
		out.print("<link rel=\"stylesheet\" href=\"includes/basic.css\">");
		out.print("</head>");		
		out.print("<body>");
		
		//We could use the following to track asynchronous tasks. Comment it out otherwise...
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			//Add job to in-queue
		}else{
			RequestDispatcher dispatcher = req.getRequestDispatcher("/poll");
			dispatcher.forward(req,resp);
			//Check out-queue for finished job with the given taskNumber
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(part.getInputStream()));
		String line = null;
		String file_content = "";
		while ((line = br.readLine()) != null) {
			file_content+=line;
			
		}
		
		//Output some headings at the top of the generated page
		out.print("<H1><img src=\"image/processing.gif\" height =\"150px\" alt=\"processing_image\" >Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<H3>Document Title: " + title + "</H3>");
		 
	    out.print("<form name=\"frmRequestDetails\" action=\"poll\">");
		out.print("<input name=\"txtTitle\" type=\"hidden\" value=\"" + title + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("<input name=\"file_content\" type=\"hidden\" value=\"" + file_content + "\">");
		out.print("</form>");	
		
		out.print("<h3>Uploaded Document</h3>");	
		out.print("<font color=\"0000ff\">");	
		out.print(file_content);
		
		out.print("</font>");	
		
		out.print("</body>");	
		out.print("</html>");	
		
		//JavaScript to periodically poll the server for updates (this is ideal for an asynchronous operation)
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);"); //Refresh every 10 seconds
		out.print("</script>");
		
		
			
		/* File Upload: The following few lines read the multipart/form-data from an instance of the
		 * interface Part that is accessed by Part part = req.getPart("txtDocument"). We can read 
		 * bytes or arrays of bytes by calling read() on the InputStream of the Part object. In this
		 * case, we are only interested in text files, so it's as easy to buffer the bytes as characters
		 * to enable the servlet to read the uploaded file line-by-line. Note that the uplaod action
		 * can be easily completed by writing the file to disk if necessary. The following lines just
		 * read the document from memory... this might not be a good idea if the file size is large!
		 */

		/*private Deque<String> buffer = new LinkedList<>();

		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
	 	}*/
		
	}
}