package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ie.gmit.sw.ComparisionResult;

public class CompareDocument {
	/**
	 * 
	 * @param doc1 DoumentDetails type compare document
	 * @param doc2 DoumentDetails type compared document
	 * @return similarity Percentage 
	 */
		public static double compareTwoDocuments(DocumentDetails doc1,DocumentDetails doc2){ 
			System.out.println("compare doc "+doc1.getDocumentTitle()+ " vs "+doc2.getDocumentTitle());
			Set<String> s1 =doc1.getShingles();
			Set<String> s2 =doc2.getShingles();	
			
	 		Set<String> set1=new HashSet<>();
			Set<String> set2=new HashSet<>();
			Set<String> set3=new HashSet<>();
			set1.addAll(s1);
			set2.addAll(s1);
			set3.addAll(s2);		 
			set1.addAll(set3);
			set2.retainAll(set3); 
		    double val = ((double)set2.size()/(double)set1.size())*100;
		    return val;
	 	}
		
		/**
		 * 
		 * @param doc DoumentDetails type
		 * @param list ObjectSet- DoumentDetails from database
		 * @return ArrayList<ComparisionResult>
		 */
		public ArrayList<ComparisionResult> compareDocumentWithDocumentsSet(DocumentDetails doc, List<DocumentDetails> list){
			ArrayList<ComparisionResult> arrayListComparisionResult= new ArrayList<>();
			 for (DocumentDetails document : list) {
					//System.out.println("document  "+document.getDocumentTitle());
					//System.out.println("doc  "+doc.getDocumentTitle());
					if(!doc.getDocumentTitle().equals(document.getDocumentTitle())){
					double similarity_Percentage=compareTwoDocuments(doc, document);
					ComparisionResult comparisionResult = new ComparisionResult(doc.getDocumentTitle(), document.getDocumentTitle(), similarity_Percentage);
					arrayListComparisionResult.add(comparisionResult);
					}
			}
			 
			return arrayListComparisionResult;
			 
		}

}
