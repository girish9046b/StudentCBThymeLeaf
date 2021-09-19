package com.student.app.rest.resttemplate;

import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.student.app.enc.AESEncDec;
import com.student.app.model.Student;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RestTemplateStudent {
	
//	private final String baseUrl="https://192.168.43.240:8443/api/student";
//	private final String userName="admin";
//	private final String password="password2";
	
	@Value("${spring.security.user.name}")
	private  String userName;
	@Value("${spring.security.user.password}")
	private  String password;
	@Value("${server.app.port}")
	private String port ;
	@Value("${server.app.protocall}")
	private String protocall;
	@Value("${spring.security.basicauth}")
	private String basicauth;
	
	
	
	public static void main(String args[]) {
		new RestTemplateStudent().loadStudentDetails(); 
	}
	
	private String getBaseUrl() {
		
		String hostname="";
		String serverPath ="";
		try {
			hostname = Inet4Address.getLocalHost().getHostAddress();
			 serverPath = protocall+"://" + hostname +":"+port+"/api/student";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverPath;
	}
	public  void loadStudentDetails()
	{
		RestTemplate restTemplate = getSSLRestTemplate();
		try {
			AESEncDec aESEncDec = new AESEncDec();
		Student student = null;
	    final String uri = getBaseUrl()+"/loadStudentDetails/{id}";
	    Map<String, String> params = new HashMap<String, String>();
		params.put("id",aESEncDec.encrypt("6"));
	    Object claimResponse = restTemplate.getForObject(uri,Object.class, params);
		String decString=aESEncDec.decrypt(claimResponse.toString());
		 student = new Gson().fromJson(decString, Student.class);
		System.out.println("loadStudentDetailsloadStudentDetailsttt......................"+student.getName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public  void getAllStudentss()
	{
		RestTemplate restTemplate = getSSLRestTemplate();
		try {
			AESEncDec aESEncDec = new AESEncDec();
		ArrayList<Student> studentslist = null;
		final String uri = getBaseUrl()+"/allstudentss";
	    Object claimResponse = restTemplate.getForObject(uri,Object.class);
	    String decString=aESEncDec.decrypt(claimResponse.toString());
	    studentslist = new Gson().fromJson(decString, new TypeToken<ArrayList<Student>>() {}.getType());
	    System.err.println("studentsliststudentslist ----------"+studentslist.size());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public  void getAllStudents()
	{
		RestTemplate restTemplate = getSSLRestTemplate();
		try {
		ArrayList<Student> studentslist = null;
		final String uri = getBaseUrl()+"/allstudents";
	    //to get the response as a Arraylist of Objects
	   ResponseEntity<ArrayList<Student>> claimResponse = restTemplate.exchange(
				uri, 
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<ArrayList<Student>>() {});
	   System.out.println("claimResponseclaimResponseclaimResponse .................."+claimResponse.getBody().getClass());
		if(claimResponse != null && claimResponse.hasBody()){
			studentslist = claimResponse.getBody();
		}
		
	   System.err.println("studentsliststudentslist ----------"+studentslist);
	   for(Student student:studentslist) {
		   System.err.println("studentsliststudentslist ----------"+student.getName());
		   
	   }
	   
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public  void addStudent()
	{
		RestTemplate restTemplate = getSSLRestTemplate();
		try {
		ArrayList<Student> studentslist = null;
		final String uri = getBaseUrl()+"/addStudentDetails";
	    //to get the response as a Arraylist of Objects
	    Student student = new Student();
	    student.setId(8);
	    student.setName("girishbb888");
	    student.setAge("33");
	    student.setPhone("900000");
	    student.setCountry("ind");
	    student.setStandard("3");
	    student.setAddress("add test");
	    Student student1= restTemplate.postForObject(uri, student, Student.class);
	   System.err.println("student1student1 ----------"+student1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public  void updateStudent()
	{
		RestTemplate restTemplate = getSSLRestTemplate();
		try {
			final String uri = getBaseUrl()+"/updateStudentDetails/{id}";
	    Map<String, String> params = new HashMap<String, String>();
		params.put("id","8");
	    //to get the response as a Arraylist of Objects
	    Student student = new Student();
	    student.setName("girishbb88888888");
	    student.setAge("77");
	    student.setPhone("900000");
	    student.setCountry("ind");
	    student.setStandard("3");
	    student.setAddress("add test");
	    restTemplate.put ( uri, student, params);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public  void deleteStudent()
	{
		RestTemplate restTemplate = getSSLRestTemplate();
		try {
			final String uri = getBaseUrl()+"/deleteStudentDetails/{id}"; 
	    		 Map<String, String> params = new HashMap<String, String>();
	    		params.put("id","8");
	    		   
	     restTemplate.delete(uri,params);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @param configFilePath
	 * @param ipAddress
	 * @param userId
	 * @param password
	 * @throws MalformedURLException
	 */
	
	
	public RestTemplate getSSLRestTemplate() {
	   
	    RestTemplate restTemplate = new RestTemplate(createSecureTransport(userName,password));
	  //  restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
	    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
	    return restTemplate;
	 }

	ClientHttpRequestFactory createSecureTransport(String username,
	        String password) {
	    HostnameVerifier nullHostnameVerifier = new HostnameVerifier() {
	        public boolean verify(String hostname, SSLSession session) {
	            return true;
	        }
	    };
	    //code for basic authentication
	    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
	    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
	    credentialsProvider.setCredentials(
	            new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM), credentials);

	    HttpClient client = HttpClientBuilder.create()
	            .setSSLHostnameVerifier(nullHostnameVerifier)
	            .setSSLContext(createContext())
	            .setDefaultCredentialsProvider(credentialsProvider).build();

	    HttpComponentsClientHttpRequestFactory requestFactory = 
	            new HttpComponentsClientHttpRequestFactory(client);

	    return requestFactory;
	}
	//code to handle the SSL for restful 
	private SSLContext createContext() {
	    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
	        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	            return null;
	        }

	        public void checkClientTrusted(
	                java.security.cert.X509Certificate[] certs, String authType) {
	        }

	        public void checkServerTrusted(
	                java.security.cert.X509Certificate[] certs, String authType) {
	        }
	    } };

	    try {
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, null);
	        SSLContext.setDefault(sc);
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
	            public boolean verify(String hostname, SSLSession session) {
	                    return true;
	                }
	            });
	        return sc;

	    } catch (Exception e) {
	    }
	    return null;
	}
	
	
//	//@Value("${server.ssl.key-store-password}")
//	private String keyStorePassword ="martham";
//	//@Value("${server.ssl.key-store-type}")
//	private String keyStoreType="JKS";
//	//@Value("${server.ssl.key-store}")
//	private Resource resource= new FileSystemResource("D:/JOB_2020/LOCAL/tomcat_ssl_key/keystore_pkcs12.p12");
//
//	private RestTemplate getRestTemplate() throws Exception {
//	    return new RestTemplate(clientHttpRequestFactory());
//	}
//
//	private ClientHttpRequestFactory clientHttpRequestFactory() throws Exception {
//	    return new HttpComponentsClientHttpRequestFactory(httpClient());
//	}
//
//	private HttpClient httpClient() throws Exception {
//
//	    KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
//	    KeyStore trustStore = KeyStore.getInstance(keyStoreType);
//
//	    if (resource.exists()) {
//	        InputStream inputStream = resource.getInputStream();
//
//	        try {
//	            if (inputStream != null) {
//	                trustStore.load(inputStream, keyStorePassword.toCharArray());
//	                keyManagerFactory.init(trustStore, keyStorePassword.toCharArray());
//	            }
//	        } finally {
//	            if (inputStream != null) {
//	                inputStream.close();
//	            }
//	        }
//	    } else {
//	        throw new RuntimeException("Cannot find resource: " + resource.getFilename());
//	    }
//
//	    SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
//	    sslcontext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
//	    SSLConnectionSocketFactory sslConnectionSocketFactory =
//	            new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
//
//	    return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
//	}
	
	

//	public RestTemplate restTemplate1() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//		
//		
//	    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//
//	    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
//	                    .loadTrustMaterial(null, acceptingTrustStrategy)
//	                    .build();
//
//	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//
//	    CloseableHttpClient httpClient = HttpClients.custom()
//	                    .setSSLSocketFactory(csf)
//	                    .build();
//
//	    HttpComponentsClientHttpRequestFactory requestFactory =
//	                    new HttpComponentsClientHttpRequestFactory();
//
//	    requestFactory.setHttpClient(httpClient);
//	   // requestFactory.setHttpClient(httpClient());
//	    RestTemplate restTemplate = new RestTemplate(requestFactory);
//	    return restTemplate;
//	 }
	
	//code to handle the SSL 
//		private TrustManager[ ] get_trust_mgr() {
//		     TrustManager[ ] certs = new TrustManager[ ] {
//		        new X509TrustManager() {
//		           public X509Certificate[ ] getAcceptedIssuers() { return null; }
//		           public void checkClientTrusted(X509Certificate[ ] certs, String t) { }
//		           public void checkServerTrusted(X509Certificate[ ] certs, String t) { }
//		         }
//		      };
//		      return certs;
//		  }
//		
//		
//		 public RestTemplate getRestTemplate(String proxyHost, int proxyPort)
//			        throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//
//			    TrustStrategy acceptingTrustStrategy = new TrustSelfSignedStrategy();
//
//			    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
//			            .build();
//
//			    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
//
//			    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
//
//			    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//			    if (null != proxyHost && proxyPort > 0) {
//			        System.out.println("PROXY CONFIGURED | proxyHost=" + proxyHost + " | proxyPort=" + proxyPort);
//			        HttpHost proxy = new HttpHost(proxyHost, proxyPort, Proxy.Type.HTTP.name());
//			        httpClient = HttpClients.custom().setSSLSocketFactory(csf)
//			                .setRoutePlanner(new DefaultProxyRoutePlanner(proxy)).build();
//			    }
//			    requestFactory.setHttpClient(httpClient);
//			    RestTemplate restTemplate = new RestTemplate(requestFactory);
//			    return restTemplate;
//			}
//		 
//		 
//		private RestTemplate getRestTemplateClientAuthentication()
//                throws IOException, UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException,
//                KeyStoreException, KeyManagementException {
//    final String allPassword = "martham";
//    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//    SSLContext sslContext = SSLContextBuilder
//                    .create()
//                    .loadKeyMaterial(ResourceUtils.getFile("D:/JOB_2020/LOCAL/tomcat_ssl_key/keystore_pkcs12.p12"),
//                                        allPassword.toCharArray(), allPassword.toCharArray())
////.loadTrustMaterial(ResourceUtils.getFile("classpath:truststore.jks"), allPassword.toCharArray())
//                    .loadTrustMaterial(null, acceptingTrustStrategy)
//                    .build();
//    HttpClient client = HttpClients.custom()
//                                    .setSSLContext(sslContext)
//                                    .build();
//    HttpComponentsClientHttpRequestFactory requestFactory =
//                    new HttpComponentsClientHttpRequestFactory();
//    requestFactory.setHttpClient(client);
//    RestTemplate restTemplate = new RestTemplate(requestFactory);
//    return restTemplate;
//}
//		
//		
//		public static RestTemplate initRestTemplateForPdfAsByteArrayAndSelfSignedHttps() {
//			RestTemplate restTemplate = new RestTemplate(useApacheHttpClientWithSelfSignedSupport());
//			restTemplate.getMessageConverters().add(generateByteArrayHttpMessageConverter());
//			return restTemplate;
//		}
//
//		private static HttpComponentsClientHttpRequestFactory useApacheHttpClientWithSelfSignedSupport() {
//			CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
//			HttpComponentsClientHttpRequestFactory useApacheHttpClient = new HttpComponentsClientHttpRequestFactory();
//			useApacheHttpClient.setHttpClient(httpClient);
//			return useApacheHttpClient;
//		}
//
//		private static ByteArrayHttpMessageConverter generateByteArrayHttpMessageConverter() {
//			ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
//
//			List<MediaType> supportedApplicationTypes = new ArrayList<MediaType>();
//			supportedApplicationTypes.add(new MediaType("application","pdf"));
//			byteArrayHttpMessageConverter.setSupportedMediaTypes(supportedApplicationTypes);
//			return byteArrayHttpMessageConverter;
//		}
//		
//		public RestTemplate restTemplate() {
//			HttpComponentsClientHttpRequestFactory requestFactory =null;
//			try {
//		    KeyStore clientStore = KeyStore.getInstance("PKCS12");
//		    clientStore.load(new FileInputStream("D:/JOB_2020/LOCAL/tomcat_ssl_key/keystore_pkcs12.p12"), "martham".toCharArray());
//		 
//		    SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
//		    sslContextBuilder.useProtocol("TLS");
//		    sslContextBuilder.loadKeyMaterial(clientStore, "martham".toCharArray());
//		    sslContextBuilder.loadTrustMaterial(new TrustSelfSignedStrategy());
//		 
//		    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build());
//		    CloseableHttpClient httpClient = HttpClients.custom()
//		            .setSSLSocketFactory(sslConnectionSocketFactory)
//		            .build();
//		     requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//		    requestFactory.setConnectTimeout(10000); // 10 seconds
//		    requestFactory.setReadTimeout(10000); // 10 seconds
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		    return new RestTemplate(requestFactory);
//		}
//	
//	public RestTemplate getRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//		
//		
//		//Start of code to handle the SSL 
//		 // Create a context that doesn't check certificates.
//       SSLContext ssl_ctx = SSLContext.getInstance("TLS");
//       TrustManager[ ] trust_mgr = get_trust_mgr();
//       ssl_ctx.init(null,                // key manager
//                    trust_mgr,           // trust manager
//                    new SecureRandom()); // random number generator
//       
//       SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(ssl_ctx);
//
//	    CloseableHttpClient httpClient = HttpClients.custom()
//	                    .setSSLSocketFactory(csf).setSSLHostnameVerifier(new HostnameVerifier() {
//	                        public boolean verify(String host, SSLSession sess) {
//	                            if (host.equals("127.0.0.1")) return true;
//	                            else return false;
//	                        }
//	                    }).build();
//	    
//	   
//
//	    HttpComponentsClientHttpRequestFactory requestFactory =
//	                    new HttpComponentsClientHttpRequestFactory();
//
//	    requestFactory.setHttpClient(httpClient);
//	    requestFactory.setHttpClient(httpClient());
//	    RestTemplate restTemplate = new RestTemplate(requestFactory);
//	    return restTemplate;
//	
//	
//       HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
//       HttpsURLConnection urlConn = (HttpsURLConnection)url.openConnection();
//       // Guard against "bad hostname" errors during handshake.
//       urlConn.setHostnameVerifier(new HostnameVerifier() {
//           public boolean verify(String host, SSLSession sess) {
//               if (host.equals("127.0.0.1")) return true;
//               else return false;
//           }
//       });
//     //End of code to handle the SSL 
//       
//       
//	    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//
//	    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
//	                    .loadTrustMaterial(null, acceptingTrustStrategy)
//	                    .build();
//
//	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//
//	    CloseableHttpClient httpClient = HttpClients.custom()
//	                    .setSSLSocketFactory(csf)
//	                    .build();
//
//	    HttpComponentsClientHttpRequestFactory requestFactory =
//	                    new HttpComponentsClientHttpRequestFactory();
//
//	    requestFactory.setHttpClient(httpClient);
//	    requestFactory.setHttpClient(httpClient());
//	    RestTemplate restTemplate = new RestTemplate(requestFactory);
//	    return restTemplate;
//	 }

	
	
	private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() 
    {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                          = new HttpComponentsClientHttpRequestFactory();
         
        clientHttpRequestFactory.setHttpClient(httpClientBasicAuth());
              
        return clientHttpRequestFactory;
    }
     
    private HttpClient httpClientBasicAuth() 
    {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
 
        credentialsProvider.setCredentials(AuthScope.ANY, 
                        new UsernamePasswordCredentials("admin", "password2"));
 
        HttpClient client = HttpClientBuilder
                                .create()
                                .setDefaultCredentialsProvider(credentialsProvider)
                                .build();
        return client;
    }
}

