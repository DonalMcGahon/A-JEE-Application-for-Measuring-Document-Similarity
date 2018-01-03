package ie.gmit.sw;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ie.gmit.sw.DBFacade;
import ie.gmit.sw.ComparisionResult;
import ie.gmit.sw.DocumentDetails;
import ie.gmit.sw.Shingles;

public class CompareDocumentFacade {
	DBFacade objDBFacade = new DBFacade();
	CompareDocument compareDocument = new CompareDocument();
	Shingles shingles = new Shingles();
	/**
	 * compare uploaded document with database that store documents
	 * @param documentTitle String
	 * @param content String
	 * @return ArrayList<ComparisionResult>
	 */
	public ArrayList<ComparisionResult> comapareAndSaveDoucument(String documentTitle,String content){
		DocumentDetails doumentDetails = new  DocumentDetails(documentTitle, new Date(), Shingles.parsed_constituentShingles(content));
		objDBFacade.SaveDocument(doumentDetails);
		ArrayList<ComparisionResult> arrayListComparisionResult = compareDocument.compareDocumentWithDocumentsSet(doumentDetails, objDBFacade.getDocumentList());
		for (ComparisionResult comparisionResult : arrayListComparisionResult) {
			objDBFacade.SaveComparisonResult(comparisionResult);
		}
		return arrayListComparisionResult;
	}
	
	public List<ComparisionResult> getAllCompasionResult(){
		return  objDBFacade.getComparisionResultList();
	}
	
	public static void main(String[] args) {
	
	}
	
	
	
}
