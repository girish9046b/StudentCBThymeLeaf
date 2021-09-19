package com.student.app.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.student.app.logging.AppLogger;
import com.student.app.model.Login;

@Service("emailService")
public class EmailService 
{
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
	AppLogger appLogger;
    @Autowired
	Gson gson;
	 @Value("${server.app.port}")
		private String port ;
		@Value("${server.app.protocall}")
		private String protocall;
		@Value("${spring.security.basicauth}")
		private String basicauth;
		@Value("${spring.emai.template}")
		private String emailTemplate;
		

   //Code to send simple email
    public void sendMail(String to, String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    

	//code to handle the SSL 
	private TrustManager[ ] get_trust_mgr() {
	     TrustManager[ ] certs = new TrustManager[ ] {
	        new X509TrustManager() {
	           public X509Certificate[ ] getAcceptedIssuers() { return null; }
	           public void checkClientTrusted(X509Certificate[ ] certs, String t) { }
	           public void checkServerTrusted(X509Certificate[ ] certs, String t) { }
	         }
	      };
	      return certs;
	  }

	
	//Method to book get the email  message
	public String getMailContent(Login login) throws Exception {
		//HttpURLConnection urlConn;
		String mailBody = null;
		String hostname=Inet4Address.getLocalHost().getHostAddress();
		try {
			String serverPath = protocall+"://" + hostname + ":"+port;
			serverPath = serverPath + emailTemplate;
			appLogger.appInfoLog(" serverPath=\"" + serverPath+"\"");
			URL url = new URL(serverPath);
			
			//Start of code to handle the SSL 
			 // Create a context that doesn't check certificates.
            SSLContext ssl_ctx = SSLContext.getInstance("TLS");
            TrustManager[ ] trust_mgr = get_trust_mgr();
            ssl_ctx.init(null,                // key manager
                         trust_mgr,           // trust manager
                         new SecureRandom()); // random number generator
            HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
            HttpsURLConnection urlConn = (HttpsURLConnection)url.openConnection();
            // Guard against "bad hostname" errors during handshake.
            urlConn.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String host, SSLSession sess) {
                    if (host.equals(hostname)) return true;
                    else return false;
                }
            });
          //End of code to handle the SSL 
            
			//HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("POST");
			HttpURLConnection.setFollowRedirects(true);
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setUseCaches(true);
			urlConn.setAllowUserInteraction(false);
			urlConn.setRequestProperty("Content-Type", "application/json");
			urlConn.setRequestProperty("Content-Language", "en-US");
			//code to set the basic authentication
			String basicAuth = "Basic " + new String(basicauth);
			urlConn.setRequestProperty ("Authorization", basicAuth);
			urlConn.setRequestProperty("login", gson.toJson(login));
			//code to pass json object 
			OutputStreamWriter out = new OutputStreamWriter(urlConn.getOutputStream());
	        out.write(gson.toJson(login));
	        out.close();
	        //code to read the html 
			InputStream byteStream = urlConn.getInputStream();
			mailBody = getStringFromInputStream(byteStream);
		} catch (Exception ex) {
			appLogger.appErrorLog("Exception : ", ex);
		} 
		return mailBody;
	}

	// convert InputStream to String
	public String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException ex) {
			appLogger.appErrorLog("Exception : " , ex);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ex) {
					appLogger.appErrorLog("Exception : " , ex);
				}
			}
		}
		return sb.toString();
	}
  
    /**
     * This method will send a pre-configured message
     * */
//    public void sendPreConfiguredMail(String message) 
//    {
//        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
//        mailMessage.setText(message);
//        mailSender.send(mailMessage);
//    }
    
    
}
