/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.app.couchbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.PersistTo;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.error.CASMismatchException;
import com.couchbase.client.java.error.DocumentDoesNotExistException;
import com.couchbase.client.java.view.DefaultView;
import com.couchbase.client.java.view.DesignDocument;
import com.couchbase.client.java.view.Stale;
import com.couchbase.client.java.view.View;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;
//import javax.faces.model.SelectItem;
import com.google.gson.Gson;
//import com.sanservices.oee.converter.ViewDesign;
import com.student.app.config.CouchbaseBucketManager;

/**
 *
 * @author gmartham
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CBOperations {
	@Autowired
	Gson _gson;
//	@Autowired
//	CouchbaseBucketManager couchbaseBucketManager;
	@Autowired
	@Qualifier("springbucket")
	private  Bucket _bucket;

	public CBOperations() {
		// System.out.println("-----------------_bucket333--------------"+couchbaseBucketManager);
		//_bucket = CouchbaseBucketManager.getCouchbaseBucket(); //CHECK OTHER WAYS
		 
		//_bucket = couchbaseBucketManager.getInstance(); 
          //      System.out.println(_bucket+"-----------------_bucket333--------------");
		//_gson = new Gson();
	}
	
	
 

	public Bucket get_bucket() {
		return _bucket;
	}




	public void set_bucket(Bucket _bucket) {
		this._bucket = _bucket;
	}




	//Code to get document if exists 
	public <T> T get(String docID, Class entityclass) throws Exception {
		T returnObject = null;
		try {
			String entityJson = _bucket.get(docID).content().toString();
			//System.out.println("entityJson :" + entityJson);
			returnObject = (T) _gson.fromJson(entityJson, entityclass);
		} catch (NullPointerException e) {
			//System.err.println("CBOperations::get - NullPointerException : docID : " + docID + "..." + e.getMessage());
			//e.printStackTrace();
		}
		return returnObject;
	}

	//Code to get document if exists 
	public <T> T get(String docID) throws Exception {
		T returnObject = null;
		try {
			String entityJson = _bucket.get(docID).content().toString();
			//System.out.println("entityJson :" + entityJson);
			returnObject = (T) _gson.fromJson(entityJson, Object.class);
		} catch (NullPointerException e) {
			//System.err.println("CBOperations::get - NullPointerException : docID : " + docID + "..." + e.getMessage());
			//e.printStackTrace();
		}
		return returnObject;
	}

	//Code to get document if exists 
	public Object getDocument(String docID) throws Exception {
		Object object = null;
		try {
			object = _bucket.get(docID).content().toString();
		} catch (NullPointerException e) {
			//System.err.println("CBOperations::getDocument - NullPointerException : docID : " + docID + "..." + e.getMessage());
			//e.printStackTrace();
		}
		return object;
	}
	
	//Code to get counter if exists 
	public String getCounter(String docID) throws Exception {
		String counter = null;
		try {
			counter = _bucket.counter(docID,0).content().toString();
		} catch (Exception e) {
			System.err.println("CBOperations::getCounter - NullPointerException : docID : " + docID + "..." + e.getMessage());
			e.printStackTrace();
		}
		return counter;
	}
	
	//Code to increment counter OR create a new counter from 1
	public String setCounter(String docID,long increment , long initial) throws Exception {
		String counter = null;
		try {
			counter = _bucket.counter(docID,increment,initial).content().toString();
		} catch (Exception e) {
			System.err.println("CBOperations::setCounter - NullPointerException : docID : " + docID + "..." + e.getMessage());
			e.printStackTrace();
		}
		return counter;
	}

	//Code to create  document if not exists  (if exists DocumentAlreadyExistsException)
	public void insert(String docID, Object entityobj) throws Exception {
		RawJsonDocument rJsonDoc = convertToRawJsonDoc(docID, entityobj);
		_bucket.insert(rJsonDoc);
	}

	//Code to create  document if not exists  (if exists DocumentAlreadyExistsException)
	public void insertToMaster(String docID, Object entityobj) throws Exception {
		RawJsonDocument rJsonDoc = convertToRawJsonDoc(docID, entityobj);
		_bucket.insert(rJsonDoc, PersistTo.MASTER);
	}
	
	

	//Code to create or update document if exists 
	public void update(String docID, Object entityobj) throws Exception {
            System.out.println(entityobj+"-----------------TEST--------------"+docID);
		RawJsonDocument rJsonDoc = convertToRawJsonDoc(docID, entityobj);
                System.out.println(_bucket+"-----------------TEST555--------------"+rJsonDoc);
		_bucket.upsert(rJsonDoc);
	}

	//Code to create or update document if exists till it persist in MASTER
	public void updateToMaster(String docID, Object entityobj) throws Exception {
		RawJsonDocument rJsonDoc = convertToRawJsonDoc(docID, entityobj);
		_bucket.upsert(rJsonDoc, PersistTo.MASTER);
	}

	//Code to create or update document if exists till it persist in MASTER
	public void updateToMasterAndGet(String docID, Object entityobj) throws Exception {
		RawJsonDocument rJsonDoc = convertToRawJsonDoc(docID, entityobj);
		_bucket.upsert(rJsonDoc, PersistTo.MASTER);
		_bucket.get(docID);
	}

	//Code to create or update document if exists till it persist in MASTER
	public String replaceInMasterAndGet(String docID, Object entityobj) throws Exception {
		String response = "OK";
		try {
			System.out.println(".old...."+_bucket.get(docID).cas());
			RawJsonDocument rJsonDoc = convertToRawJsonDoc(docID, entityobj);
			_bucket.replace(rJsonDoc, PersistTo.MASTER);
			System.out.println("..new..."+_bucket.get(docID).cas());
		} catch (DocumentDoesNotExistException dne) {
			response ="NOT_FOUND";
			//SSLogger.getInstance().log(docID + "STATUSCHANGE AbstractDocumentManager::updateDoc  ", " * * * *  -- Value is not found - should create new order", org.apache.log4j.Level.INFO);
		} catch (CASMismatchException cme) {
			response ="EXISTS";
			//SSLogger.getInstance().log(docID + "STATUSCHANGE AbstractDocumentManager::updateDoc  ", " * * * *  -- Value is not found - should create new order", org.apache.log4j.Level.INFO);
		}
		catch (Exception e) {
			response ="FAIL";
			//SSLogger.getInstance().log(docID + "STATUSCHANGE AbstractDocumentManager::updateDoc  ", " * * * *  -- Value is not found - should create new order", org.apache.log4j.Level.INFO);
		}
		System.out.println("responseresponseresponse : "+response);
		return response;
	}

	//Code to delete a document
	public void EraseFromCB(String docID) throws Exception {
		try {
			_bucket.remove(docID);
		} catch (Exception e) {
			Logger.getLogger(CBOperations.class.getName()).log(Level.SEVERE, "Coucbhase Error: Erasing Data from Couchbase", e);
		}
	}

	//Code to delete a document
	public void EraseFromCBMaster(String docID) throws Exception {
		try {
			_bucket.remove(docID, PersistTo.MASTER);
		} catch (Exception e) {
			Logger.getLogger(CouchbaseBucketManager.class.getName()).log(Level.SEVERE, "Coucbhase Error: EraseFromCBMaster from Couchbase", e);
		}
	}

	//Code to get CAS of  a document
	public long getCAS(String docID){
		long cas = 0;
		try {
			cas = _bucket.get(docID).cas();
		} catch (Exception e) {
			//Logger.getLogger(CouchbaseBucketManager.class.getName()).log(Level.SEVERE, "Coucbhase Error: getCAS from Couchbase", e);
		}
		return cas;
	}

	//Code to read results form a view
	public ViewResult doQuery(String designDoc, String viewName) throws Exception {
		ViewQuery viewQuery = null;
		ViewResult result = null;
		viewQuery = ViewQuery.from(designDoc, viewName);
		viewQuery.stale(Stale.FALSE);
		viewQuery.includeDocs(true);
		//System.out.println(".._bucket..." + _bucket);
		result = _bucket.query(viewQuery);
		return result;
	}
	
	public void createDesignDoc(String designDocName, String viewName,HashMap<String,String> Keys) throws Exception {
		//String designDocName = "users";
		 //viewName = "getAllDocsKey";
		//Map<String, String> map = new HashMap<>();
		 String condition2="";
		 for (Entry<String, String> entry : Keys.entrySet()) {
	            System.out.println("[Key] : " + entry.getKey() + " [Value] : " + entry.getValue());
	            condition2 = condition2+"doc."+entry.getKey()+"==\""+entry.getValue()+"\" && ";
	        }
		 
		 condition2 = condition2.substring(0, condition2.length()-3);
		 System.out.println("conditiomnn................."+condition2);
	    
		String mapFunction = "function (doc, meta) {\n"
				+ "  if("+condition2+") {\n"
				+ "    emit(meta.id,doc);\n"
				+ "  }\n"
				+ "}";
		
		createSingleDesignDocument(designDocName, viewName,mapFunction);
	}
	
	//code to create the single view for given designDocName and viewName, mapfunctionString
		public void createSingleDesignDocument(String designDocName, String viewName, String mapfunctionString) {
			try {
				System.out.println("...._albumSizeList...." + designDocName + "...." + viewName + "..." + mapfunctionString);
				List<View> viewList = new ArrayList();
				View vd = DefaultView.create(viewName, mapfunctionString);
				viewList.add(vd);
				DesignDocument designDoc = DesignDocument.create(designDocName, viewList);
				System.out.println("...._albumSizeList...." + designDocName + "...." + viewName + "..." + designDoc);
				_bucket.bucketManager().upsertDesignDocument(designDoc);
			} catch (Exception e) {
				System.err.println("[ CBOperations ] Exception createSingleDesignDocument : " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		//code to create the single view for given designDocName and viewName, mapfunctionString
		public void removeSingleDesignDocument(String designDocName) {
			try {
				_bucket.bucketManager().removeDesignDocument(designDocName);
			} catch (Exception e) {
				System.err.println("[ CBOperations ] Exception removeSingleDesignDocument : " + e.getMessage());
				e.printStackTrace();
			}
		}
	
	//Code to read results form a view
		public ViewResult doQueryDynamicView(HashMap<String,String> Keys) throws Exception {
			UUID uid = UUID.randomUUID();
			String designDocName = uid.toString();
			String viewName = uid.toString();
			createDesignDoc(designDocName, viewName, Keys);
			ViewQuery viewQuery = null;
			ViewResult result = null;
			viewQuery = ViewQuery.from(designDocName, viewName);
			viewQuery.stale(Stale.FALSE);
			viewQuery.includeDocs(true);
			//viewQuery.key(Key);  
//			viewQuery.key(key);
			//System.out.println(".._bucket..." + _bucket);
			result = _bucket.query(viewQuery);
			removeSingleDesignDocument(designDocName);
			return result;
		}
	
	//Code to read results form a view
	public ViewResult doQuery(String designDoc, String viewName,String Key) throws Exception {
		ViewQuery viewQuery = null;
		ViewResult result = null;
		viewQuery = ViewQuery.from(designDoc, viewName);
		viewQuery.stale(Stale.FALSE);
		viewQuery.includeDocs(true);
		viewQuery.key(Key);  
//		viewQuery.key(key);
		//System.out.println(".._bucket..." + _bucket);
		result = _bucket.query(viewQuery);
		return result;
	}

	//Code to read doc ids from a view and then get the bulk document values based on doc ids
	public LinkedHashMap<String, Object> getBulkData(String designDoc, String viewName) throws Exception {
		ViewResult result = doQuery(designDoc, viewName);
		LinkedHashMap<String, Object> lmap = new LinkedHashMap<>();
		ArrayList<String> al = new ArrayList<>();
		for (ViewRow row : result) {
			al.add(row.key().toString());
		}
		for (String key : al) {
			lmap.put(key, _bucket.get(key).content().toString());
		}

		return lmap;
	}

	//Code to read doc ids from a list and then get the bulk document values based on doc ids
	public LinkedHashMap<String, Object> getBulk(ArrayList<String> keys) throws Exception {
		LinkedHashMap<String, Object> lmap = new LinkedHashMap<>();
		for (String key : keys) {
			lmap.put(key, _bucket.get(key).content().toString());
		}
		return lmap;
	}

//Code to get the list of documents values from a view.
//	public List<SelectItem> fillList(String designDoc, String viewName) throws Exception {
//		List<SelectItem> _docs = new ArrayList();
//		ViewResult result = doQuery(designDoc, viewName);
//		for (ViewRow row : result) {
//			_docs.add(new SelectItem(row.document(), row.key().toString()));
//		}
//		return _docs;
//	}

	//Code to get the Map of documents key,values from a view.
	public Map<String, Object> FillMap(String designDoc, String viewName) throws Exception {
		Map valueMap;
		ViewResult result = doQuery(designDoc, viewName);
		valueMap = new LinkedHashMap();

		for (ViewRow row : result) {
			valueMap.put(row.key().toString(), row.id());
		}
		return valueMap;
	}

	//Code to convert the Object to RawJsonDoc
	public RawJsonDocument convertToRawJsonDoc(String docid, Object entityobj) throws Exception {
		RawJsonDocument returnobj = null;
                try{
                     System.out.println(entityobj+"-----------------TEST33--------------"+docid);
		Gson gson = new Gson();
		String docId = docid;
		String jsonData;
		jsonData = gson.toJson(entityobj);
		returnobj = RawJsonDocument.create(docId, jsonData);
                }catch(Exception e){
                        e.printStackTrace();
                        }
                         System.out.println(returnobj+"-----------------TEST44--------------"+docid);
		return returnobj;
	}

	

	//code to create the views for given designDocName and viewToMapFunctions<viewName,mapfunctionString>
	public void createDesignDocument(String designDocName, HashMap<String, String> viewToMapFunctions) {
		try {
			List<View> viewList = new ArrayList();
			HashMap<String, String> views = viewToMapFunctions;
			Iterator it = views.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
				System.out.println("------------------------------");
				System.out.println(pairs.getKey().toString());
				System.out.println(pairs.getValue().toString());
				View vd = DefaultView.create(pairs.getKey().toString(), pairs.getValue().toString());
				viewList.add(vd);
				it.remove();
			}
			DesignDocument designDoc = DesignDocument.create(designDocName, viewList);
			_bucket.bucketManager().upsertDesignDocument(designDoc);
		} catch (Exception e) {
			System.err.println("[ CBOperations ] Exception createDesignDocument : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
