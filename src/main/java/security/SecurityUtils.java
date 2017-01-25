package security;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtils {

	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't',
			'K', 'e', 'y' };
	
	public static String encrypt(String data) throws Exception{
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(ALGO);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedVal = cipher.doFinal(data.getBytes());
		String encryptedValue = new String(Base64.getEncoder().encode(encryptedVal));
		return encryptedValue;
	}
	
	public static String decrypt(String data) throws Exception{
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(ALGO);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = Base64.getDecoder().decode(data);
		byte[] decValue = cipher.doFinal(decodedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}
	
	public static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue,ALGO);
		return key;
	}

}
