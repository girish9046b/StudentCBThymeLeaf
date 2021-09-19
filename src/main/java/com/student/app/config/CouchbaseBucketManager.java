package com.student.app.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import com.blitzkriegdevelopment.sasbase.manager.UtilityClass;
import com.couchbase.client.core.tracing.ThresholdLogReporter;
import com.couchbase.client.core.tracing.ThresholdLogTracer;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;

import io.opentracing.Tracer;

@Configuration
public class CouchbaseBucketManager {

	private static String serverip;
//	private static String serverdns;
	public static String slashValue;
	private static String CBServerip_1;
	private static String CBServerip_2;
	private static String CBServerip_3;
	private static int sessionTimeout = 1800; //30minutes
	//private final int QUERY_TIMEOUT = 25000; //25 seconds
	private static final int TIMEOUT = 25000; //25 seconds
	private  Bucket _bucket;
	private  Cluster _cluster;
	private static final ServerAddress CBSERVER =ServerAddress.LOCAL;
	private final static String BUCKETNAME = "SPRING";
	private final static String BUCKETPASS = "SPRING";

	public enum ServerAddress {
		LOCAL, TESTING, AMAZON, MARIO, ALLAN
	}

	
	 @Bean(name = "springbucket")
	    public Bucket getCouchbaseBucket() 
	    {
		 System.out.println( "Connecting to Couchbase Cluster...");
			serverConnection(CBSERVER);
			List<String> nodes = new ArrayList<>();
			nodes.add(CBServerip_1);
			if (isInputValid(CBServerip_2)) {
				nodes.add(CBServerip_2);
			}
			if (isInputValid(CBServerip_3)) {
				nodes.add(CBServerip_3);
			}

			try {
				Tracer tracer = ThresholdLogTracer.create(ThresholdLogReporter.builder().logInterval(10, TimeUnit.SECONDS).build());
				CouchbaseEnvironment env; 
				env = DefaultCouchbaseEnvironment.builder().connectTimeout(TIMEOUT).queryTimeout(TIMEOUT).viewTimeout(TIMEOUT).socketConnectTimeout(TIMEOUT).tracer(tracer).build();  
				_cluster = CouchbaseCluster.create(env , nodes);    
				_cluster.authenticate(BUCKETNAME, BUCKETPASS);
				_bucket = _cluster.openBucket(BUCKETNAME);
	            System.out.println(_bucket+"-----------------_bucket11111construct--------------");
			} catch (Exception e) {
				System.err.println("Coucbhase Error: Initializing Couchbase"+e.getMessage());
			}
			return _bucket;
	    }
	 
	public static void serverConnection(ServerAddress connection) {
		switch (connection) {
			case LOCAL:
				slashValue = "/SASbase/";
				serverip = "127.0.0.1";
				CBServerip_1 ="127.0.0.1";// "10.3.39.168";//"10.3.34.66";//"10.3.39.168";//"127.0.0.1";//10.3.34.63
				CBServerip_2 = "";
				CBServerip_3 = "";
				break;

			case AMAZON:
				slashValue = "/";
				serverip = "127.0.0.1";
//				serverdns = "snapshots.sandals.com";
//				CBServerip_1 = "54.225.73.36";
//				CBServerip_2 = "23.21.208.146";
				CBServerip_3 = "";//"18.234.104.215";
//                CBServerip_1 = "54.81.33.73";
//                CBServerip_2 = "107.20.18.88";
//                CBServerip_3 = "";//"184.72.250.199";//"54.224.183.51";
				break;

			case TESTING:
				slashValue = "/";
				serverip = "127.0.0.1";
				CBServerip_1 = "127.0.0.1";
				CBServerip_2 = "";
				CBServerip_3 = "";
				break;
		default:
			slashValue = "/SASbase/";
			serverip = "127.0.0.1";
			CBServerip_1 ="127.0.0.1";// "10.3.39.168";//"10.3.34.66";//"10.3.39.168";//"127.0.0.1";//10.3.34.63
			CBServerip_2 = "";
			CBServerip_3 = "";
			break;
		}
//        CBServerip_1 = "http://" + CBServerip_1 + ":8091/pools";
//        if (UtilityClass.isInputValid(CBServerip_2)) {
//            CBServerip_2 = "http://" + CBServerip_2 + ":8091/pools";
//        }
//        if (UtilityClass.isInputValid(CBServerip_3)) {
//            CBServerip_3 = "http://" + CBServerip_3 + ":8091/pools";
//        }
	}

        public static boolean isInputValid(String value) {
        return value != null && !value.trim().isEmpty();
        }
	

	public static final com.student.app.config.CouchbaseBucketManager.ServerAddress getCBSERVER() {
		return CBSERVER;
	}

	public static String getServerip() {
		return serverip;
	}

	public String getSlashValue() {
		return slashValue;
	}

//	public String getServerDns() {
//		return serverdns;
//	}

	public static int getSessionTimeout() {
		return sessionTimeout;
	}

	public static void setSessionTimeout(int sessionTimeout) {
		CouchbaseBucketManager.sessionTimeout = sessionTimeout;
	}
}
