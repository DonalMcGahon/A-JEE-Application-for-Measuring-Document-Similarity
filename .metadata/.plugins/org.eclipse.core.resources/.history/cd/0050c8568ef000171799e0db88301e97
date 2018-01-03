package ie.gmit.sw;

import java.util.List;

import com.db4o.ObjectContainer; 
 

import ie.gmit.sw.DocumentDetails;
import ie.gmit.sw.ComparisionResult;

public class DBUpdate {
	/**
	 * Save DoumentDetails object in database
	 * @param dbConnection ObjectContainer db4o
	 * @param doumentDetails DoumentDetails object 
	 */
	public void SaveDoucmentInDB(ObjectContainer dbConnection,DocumentDetails doumentDetails){
		try {
			dbConnection.store(doumentDetails);	
			dbConnection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 //dbConnection.close();
		}				
	}
	
	/**
	 * Retrieve DoumentDetails object in database
	 * @param dbConnection ObjectContainer db4ot 
	 * @return  list DoumentDetails
	 */
	
	public List<DocumentDetails> getDoucmentFromDB(ObjectContainer dbConnection){
	try{
		List<DocumentDetails> list= dbConnection.queryByExample(new DocumentDetails(null, null, null));
		dbConnection.commit();
		return list;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}finally{
		//dbConnection.close();
	}				
	}	
	
	/**
	 * Save ComparisonResult object in database
	 * @param dbConnection ObjectContainer db4o
	 * @param comparisonResult ComparisonResult object 
	 */
	public void SaveDoucmentComparisonResultInDB(ObjectContainer dbConnection,ComparisionResult comparisonResult){
	try{
		dbConnection.store(comparisonResult);
		dbConnection.commit();
		
	} catch (Exception e) {
		e.printStackTrace();
 
	}finally{
		 //dbConnection.close();
	}
				
	}
	
	/**
	 * Retrieve ComparisonResult object in database
	 * @param dbConnection ObjectContainer db4ot 
	 * @return  list ComparisionResult
	 */
	
	public List<ComparisionResult> getComparisonResultFromDB(ObjectContainer dbConnection){
		try{
			List<ComparisionResult> list= dbConnection.queryByExample(new ComparisionResult(null, null, 0));
			dbConnection.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			 //dbConnection.close();
		}
	

	}
}
