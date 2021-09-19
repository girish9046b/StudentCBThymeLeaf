package com.student.app.couchbase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CounterManager {


	@Autowired
	CBOperations cboperations;

	public int getCounter(Counter type) {
		System.out.println("[CounterManager]:  getCounter " + type);
		String docCounterName = "counter_new_" + type;
		int counterI = 0;
		try {
			System.out.println("-----------------_bucket333--------------");
			String counter = cboperations.setCounter(docCounterName,1,1);
			counterI = Integer.parseInt(counter);
		} catch (Exception e) {
			System.err.println("[CounterManager]: Exception getCounter " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("[CounterManager]:  getCounter  - docCounterName : " + docCounterName+" ,counterI :  "+counterI);
		return counterI;
	}
}

//package com.blitzkriegdevelopment.sasbase.couchbase;
//
//import com.blitzkriegdevelopment.sasbase.documents.enums.Counter;
//import com.couchbase.client.CouchbaseClient;
//
//public class CounterManager {
//	
//	private static CouchbaseClient _client = CouchbaseManager.getInstance();
//	
//	public static int getCounter(Counter type) {
//		String docCounterName = "counter_"+type;
//		Object cur = _client.get(docCounterName);
//		System.out.println(docCounterName+"..curcurcur...."+cur);
//		int res = 0;
//		if(cur == null) {
//			_client.add(docCounterName,1);
//			res = 1;
//		} else {
//			res = Integer.parseInt(cur.toString()) + 1;
//			System.out.println(docCounterName+"..resresres...."+res);
//			_client.set(docCounterName,res);
//		}
//		
//		return res;
//	}
//	
//}

//	public static void main(String args[]){
//		getCountertest(Counter.USER);
//	}
//	public static int getCountertest(Counter type) {
//		String docCounterName = "counter_new_"+"USERRR";
//		int res = 0;
//		long i=1;
//		try{
//			Bucket b = new CouchbaseBucketManager().getInstance();
//			System.out.println(docCounterName+"..bbbbbb...."+b);
//			JsonLongDocument cur = b.counter(docCounterName, 1,1);
//		//Object cur = _client.get(docCounterName);
//		System.out.println(docCounterName+"..curcurcur...."+cur.content());
//		
////		if(cur == null) {
////			cBOperations.insert(docCounterName, 1);
////			//_client.add(docCounterName,1);
////			res = 1;
////		} else {
////			res = Integer.parseInt(cur.toString()) + 1;
////			System.out.println(docCounterName+"..resresres...."+res);
////			cBOperations.update(docCounterName, res);
////			//_client.set(docCounterName,res);
////		}
//		}catch(Exception e){
//			//System.err.println("[CounterManager]: Exception getCounter "+e.getMessage());
//			e.printStackTrace();
//		}
//		System.err.println("[CounterManager]: resresresresresres getCounter "+res);
//		return res;
//	}
//}
