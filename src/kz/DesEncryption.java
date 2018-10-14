/**
 * 
 */
package kz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * @author Shukhratzhan Nizamov
 *
 */
public class DesEncryption {

	public DesEncryption() {
		// TODO Auto-generated constructor stub
	}
	
	public void encryptAndDecrypt(){
	    try{
	    	KeyGenerator kGenerator = KeyGenerator.getInstance("DES");
		    SecretKey sKey = kGenerator.generateKey();
		    Cipher cipher=Cipher.getInstance("DES/ECB/PKCS5Padding");
		    cipher.init(Cipher.ENCRYPT_MODE, sKey);
		    byte[]text=readFile("text.txt").getBytes();
		    System.out.println("Text: "+new String(text));
		    System.out.println("Text in byte: "+text);
		    byte[] eText=cipher.doFinal(text);
		    System.out.println("Encrypted text: "+eText);

		    cipher.init(cipher.DECRYPT_MODE, sKey);
		    byte[] dText=cipher.doFinal(eText);
		    System.out.println("dText in byte: "+dText);
		    System.out.println("Decrypted text: "+new String(dText));
		    
		    
	    }catch(NoSuchAlgorithmException e){
	    	e.printStackTrace();
	    }catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	    catch (BadPaddingException e){
	    	e.printStackTrace();
	    }catch(IllegalBlockSizeException e){
	    	e.printStackTrace();
	    }
	}
	
	public static String readFile(String filename){
		String text="";
		try{
			BufferedReader bfile=new BufferedReader(new FileReader(new File(filename)));		
			while(bfile.ready()){
				text+=bfile.readLine()+"\n";
				//System.out.println("Text: "+text);
			}
			bfile.close();
		}catch(IOException e){
			System.err.println("Problem With File : "+filename);
			System.exit(0);
		}
		return text;
	}

}
