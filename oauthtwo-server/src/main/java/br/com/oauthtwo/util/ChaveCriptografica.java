package br.com.oauthtwo.util;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class ChaveCriptografica {
	private Cipher cipher;
	private Cipher decipher;
	
	public ChaveCriptografica(){
		try {
			byte[] decodedKey = decodeFromBase64("UwRoUgHX4dnNjbnj/8ql7A==");
			byte[] ivParams = {124, 62, 85, 88, 76, 68, 125, 15, -105, 42, 6, -35, -49, -65, 86, 44};
			SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, "AES");
			
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(ivParams));
			
			
			decipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			decipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(ivParams));
		} catch (Exception e) {
//			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public synchronized String encrypt(String plainText) {   
        try {
        	byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        	return encodeToBase64(encryptedTextBytes);
		} catch (Exception e) {
			throw new RuntimeException("it was not possible to encrypt token", e);
		}
    }
	
	public String decrypt(String encryptedText){
		final byte[] encryptedTextBytes = decodeFromBase64(encryptedText);
			try {
				byte[] doFinal = null;
				synchronized (decipher) {
					doFinal = decipher.doFinal(encryptedTextBytes);
				}
				return new String(doFinal, "UTF-8");
	        } catch (Exception e) {
	        	throw new RuntimeException("it was not possible to decrypt token content", e);
	        }	
         
    }
	
	private static String encodeToBase64(byte[] text){
		return DatatypeConverter.printBase64Binary(text);
	}
	
	private static byte[] decodeFromBase64(String base64){
		return DatatypeConverter.parseBase64Binary(base64);
	}

	public static void main(String[] args) {
		String texto="55ceb71f41c5a162c1a7e0dc2d133040";
		ChaveCriptografica cripto=new ChaveCriptografica();
		
		System.out.println(cripto.decrypt(texto));
	}
	
}
