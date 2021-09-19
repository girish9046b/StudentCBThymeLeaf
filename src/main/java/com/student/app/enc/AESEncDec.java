package com.student.app.enc;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.util.Constants;

//import sun.misc.BASE64Decoder;
import java.util.Base64;

/**
 *
 * @author girish
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AESEncDec {

	private static final String ALGORITHM = Constants.ALGORITHM;
	private static final String ENC_DEC_KEY = Constants.ENC_DEC_KEY;
	private static final String UNICODE_FORMAT = Constants.UNICODE_FORMAT;

	public static void main(String[] args) throws Exception {

		int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
		System.out.println("MaxAllowedKeyLength=[" + maxKeyLen + "].");
		
		String value = "17";//"gmartham@sanservices.in";
		String valueEnc = new AESEncDec().encrypt(value);
		String valueDec = new AESEncDec().decrypt(valueEnc);

		System.out.println("Plain Text : " + value);
		System.out.println("Encrypted  : " + valueEnc);
		System.out.println("Decrypted  : " + valueDec);
	}

	public String encrypt(String valueToEnc) throws Exception {
		Key key = generateKey();
		System.out.println("...EEEEkey....."+key);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		//byte[] encValue = c.doFinal(valueToEnc.getBytes(UNICODE_FORMAT));
		byte[] encValue = c.doFinal(valueToEnc.getBytes());
		String encryptedValue = new Hex().encodeHexString(encValue);
		return encryptedValue;
	}

	public String decrypt(String encryptedValue) throws Exception {
		Key key = generateKey();
		System.out.println("..DDD.key....."+key);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new Hex().decode(encryptedValue.getBytes(UNICODE_FORMAT));
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private Key generateKey() throws Exception {
		byte[] keyValue;
		//keyValue = new BASE64Decoder().decodeBuffer(ENC_DEC_KEY);
		keyValue = Base64.getMimeDecoder().decode(ENC_DEC_KEY);
		System.out.println("keyValue Text : " + keyValue);
		Key key = new SecretKeySpec(keyValue, ALGORITHM);

		return key;
	}

}
