package ie.gmit.sw;

public class ComparisionResult {

	String documentTitle;
	String comapredDocumentTitle;
	double result; // value between 0-100
	/**
	 * @return the documentTitle
	 */
	public String getDocumentTitle() {
		return documentTitle;
	}
	/**
	 * @return the comapredDocumentTitle
	 */
	public String getComapredDocumentTitle() {
		return comapredDocumentTitle;
	}
	/**
	 * @return the result
	 */
	public double getResult() {
		return result;
	}
	
	public ComparisionResult(String documentTitle,
		String comapredDocumentTitle, double result) {
		this.documentTitle = documentTitle;
		this.comapredDocumentTitle = comapredDocumentTitle;
		this.result = result;
	}

}
