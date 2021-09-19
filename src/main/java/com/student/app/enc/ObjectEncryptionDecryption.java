
package com.student.app.enc;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.util.Constants;

//import sun.misc.BASE64Decoder;

/**
 *
 * @author gmartham
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ObjectEncryptionDecryption {

	private static Key key;
	private static final String ALGORITHM = Constants.ALGORITHM;
	private static final String ENC_DEC_KEY = Constants.ENC_DEC_KEY;
	private static final String UNICODE_FORMAT = Constants.UNICODE_FORMAT;
	
	/*
	 * getCipherObject method is responsible for creating Cipher object for encryption and decryption.  
	 */
	private  Cipher getCipherObject(String type ) throws Exception{
		Cipher cipher;
		cipher = Cipher.getInstance(ALGORITHM);
		key = generateKey();
		if(type.contentEquals("encryption"))
		{
			cipher.init(Cipher.ENCRYPT_MODE, key);
		}
		else if(type.contentEquals("decryption")){
			cipher.init(Cipher.DECRYPT_MODE, key);
		}
		return cipher;
		
	}
	
	private  Key generateKey() throws Exception {
		byte[] keyValue;
		//keyValue = new BASE64Decoder().decodeBuffer(ENC_DEC_KEY);
		keyValue = Base64.getMimeDecoder().decode(ENC_DEC_KEY);
		Key key = new SecretKeySpec(keyValue, ALGORITHM);

		return key;
	}
	
	/*
	 * This method is responsible for encrypt the object
	 */
	public  SealedObject encryptObject(Serializable o) throws Exception{
		
		SealedObject sealed = new SealedObject(o, getCipherObject("encryption"));
		
		System.out.println("Object Encrypted");
		return sealed;
	}
	
	/*
	 * This method is responsible for decrypt the object
	 */
	public  <T> T decryptObject(SealedObject sealed) throws Exception {
		Object encryptedObject = (Object) sealed.getObject(getCipherObject("decryption"));
		System.out.println("Object Decrypted");
		return (T) encryptedObject;
		
	}

	public static void main(String[] args) throws ClassNotFoundException, BadPaddingException {
		try {
			
//			use this in the rest api
//			try {
//				SealedObject encryptedObject =objectEncryptionDecryption.encryptObject(student);
//				System.out.println("objectEncryptionDecryption......................"+encryptedObject);
//				Student student2 = objectEncryptionDecryption.decryptObject(encryptedObject);
//				System.out.println("objectEncryptionDecryption......................"+student2.toString());
//				}
//				catch(Exception e) {
//					
//					e.printStackTrace();
//				}
			
			ObjectEncryptionDecryption objectEncryptionDecryption =new ObjectEncryptionDecryption();
			// generate secret key using DES algorithm same for encryption and decryption
			//key = KeyGenerator.getInstance("DES").generateKey();
			key= objectEncryptionDecryption.generateKey();
			System.out.println("...key....."+key);
			key= objectEncryptionDecryption.generateKey();
			System.out.println("...key....."+key);
			key= objectEncryptionDecryption.generateKey();
			System.out.println("...key....."+key);
			/// the object so we will encrypt
			EncriptThisClass so = new EncriptThisClass();
			
			/// encryptedObject this encrypted object send this object to some one or through network
			/// no one can get the real object if they do not know the  key
			SealedObject encryptedObject =objectEncryptionDecryption.encryptObject(so);
			System.out.println(".......name...."+encryptedObject);
			///use encryptedObject and get the real object that is nothing but decryption
			EncriptThisClass etcObject=objectEncryptionDecryption.decryptObject(encryptedObject);
			
			
			/// now use the etcObject object after decrypt
			
			etcObject.Test();
			System.out.println(".......name...."+etcObject.name);
			

		} catch (NoSuchAlgorithmException e) {

			System.out.println("No Algorithmn found:" + e.getMessage());

			return;

		} catch (NoSuchPaddingException e) {

			System.out.println("No Padding:" + e.getMessage());

			return;

		} catch (InvalidKeyException e) {

			System.out.println("Invalid Key:" + e.getMessage());

			return;

		} catch (IllegalBlockSizeException e) {

			System.out.println("Illegal Block:" + e.getMessage());

			return;

		} catch (IOException e) {

			System.out.println("I/O Error:" + e.getMessage());

			return;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	/*
	 * This class object we use to encrypt and decrypt
	 */

	public static class EncriptThisClass implements Serializable {

		private static final long serialVersionUID = 1L;
		String name="girish";

		public void Test() {

			System.out.println("Object encription decryption working correctly"+name);

		}

	}

}
