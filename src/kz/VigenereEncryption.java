/**
 * 
 */
package kz;

import java.io.*;
import java.util.Scanner;

/**
 * @author lenevo
 *
 */
public class VigenereEncryption {

	private String text;
	private String key;
	private String encryptedTxt;
	private String decryptedTxt;
	
	public VigenereEncryption() {
		this.text="";
		this.key="";
		this.encryptedTxt="";
		this.decryptedTxt="";
	}
	
	public void readFile(String filename){
			try{
				BufferedReader bfile=new BufferedReader(new FileReader(new File(filename)));	
				while(bfile.ready()){
					this.text=bfile.readLine();
					System.out.println("Text: "+this.text);
					this.text=this.text.replaceAll("\\s+","");
				}
				bfile.close();
			}catch(IOException e){
				System.err.println("Problem With File : "+filename);
				System.exit(0);
			}
		}

	public void readKey(String file) {
		try{
			BufferedReader bfile=new BufferedReader(new FileReader(new File(file)));		
			while(bfile.ready()){
				this.key=bfile.readLine().replaceAll("\\s+","");
			}
			while(this.key.length()<this.text.length()){
				this.key+=this.key;
			}
			System.out.println("Key : "+this.key);
			bfile.close();
		}catch(IOException e){
			System.err.println("Problem With File : "+file);
			System.exit(0);
		}
	}

	public void encryptTxt(){
		//Formula encryption:  C = (P + K) mod 26
	
		if(this.text.length()<=this.key.length()){
			for(int i=0; i<text.length(); i++){
				int p=(int)text.charAt(i);
				int k=(int)key.charAt(i);
				p=(p>96)?(p-97):(p-65);
				k=(k>96)?(k-97):(k-65);
				int a=(p+k)%26;
				//System.out.println((a+65)+" : "+(char)(a+65));
				this.encryptedTxt+=""+(char)(a+65);
			}
			System.out.println("Encrypted text: "+this.encryptedTxt);
		}else{
			System.out.println("length of Text end Key is not equal. ");
		}
	}
	
	public void decryptTxt(){
		//P = (C-K+26) mod 26
		if(this.encryptedTxt.length()<=this.key.length()){
			for(int i=0; i<text.length(); i++){
				int k=(int)encryptedTxt.charAt(i);
				int p=(int)key.charAt(i);
				p=(p>96)?(p-97):(p-65);
				k=(k>96)?(k-97):(k-65);
				int a=(k-p+26)%26;
				//System.out.println((a+65)+" : "+(char)(a+65));
				this.decryptedTxt+=""+(char)(a+65);
			}
			System.out.println("Decrypted text: "+this.decryptedTxt);
		}else{
			System.out.println("length of Text end Key is not equal. ");
		}
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
}
