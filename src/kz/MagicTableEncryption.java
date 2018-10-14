package kz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationEvent;

public class MagicTableEncryption {
	private String text;
	private String encryptedText;
	private String decryptedText;
	private int tLength;
	
	public MagicTableEncryption() {
		this.text="";
		this.encryptedText="";
		this.decryptedText="";
		this.tLength=0;
	}
	
	public void readFile(String filename){
		try{
			BufferedReader bfile=new BufferedReader(new FileReader(new File(filename)));		
			while(bfile.ready()){
				this.text=bfile.readLine();
				System.out.println("Text: "+this.text);
				this.text=this.text.replaceAll("\\s+","");
				this.tLength=this.text.length();
			}
			bfile.close();
		}catch(IOException e){
			System.err.println("Problem With File : "+filename);
			System.exit(0);
		}
	}

	public void encryptText(){
		int key=1,count=1;
		Map<Integer,Character>table=initTable();
		for(Character c: this.text.toCharArray()){
			if(key>16 ){
				for(Map.Entry<Integer, Character> m: table.entrySet()){
					
					encryptedText+=""+m.getValue();
				}
				key=1;
				table=initTable();
			}
			table.put(key,c);
			if(this.text.length()==count){
				key++;
				while(key<=16){
					table.put(key,(char)(ThreadLocalRandom.current().nextInt(0, 26 + 1)+97));
					key++;
				}
				for(Map.Entry<Integer, Character> m: table.entrySet()){
					//System.out.println(m.getKey());
					encryptedText+=""+m.getValue();
					//System.out.println(m.getKey()+" -> "+m.getValue());
				}
			}
			key++;
			count++;
		}
		System.out.println("Encrypted text: "+encryptedText);
	}
	
	public void decryptText(String eText, int count){
		int length=eText.length();
		if(length>16)
			decryptText(eText.substring(16, length), count+16);
		Map<Integer,Character>table=initTable();
		int key=0;
		
		for(Map.Entry<Integer, Character> m:table.entrySet()){
			m.setValue(eText.charAt(key));
			//System.out.println(eText.charAt(key)+" : "+m.getKey()+" -> "+m.getValue());
			key++;
		}
		eText="";
		for(int i=1; i<17; i++){
			eText+=table.get(i);
			count++;
			if(count==tLength)break;
		}
		
		this.decryptedText=eText+this.decryptedText;
		//System.out.println("Decrypted text: "+this.decryptedText);
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
	 * @return the encyptedText
	 */
	public String getEncyptedText() {
		return encryptedText;
	}
	/**
	 * @param encyptedText the encyptedText to set
	 */
	public void setEncyptedText(String encyptedText) {
		this.encryptedText = encyptedText;
	}
	/**
	 * @return the decryptedText
	 */
	public String getDecryptedText() {
		return this.decryptedText;
	}
	/**
	 * @param decryptedText the decryptedText to set
	 */
	public void setDecryptedText(String decryptedText) {
		this.decryptedText = decryptedText;
	}

	private Map<Integer, Character> initTable() 
	{
		Map<Integer, Character> m=new LinkedHashMap<Integer, Character>();
		m.put(1, null);  m.put(15, null); m.put(14, null); m.put(4, null);
		m.put(12, null); m.put(6, null);  m.put(7, null);  m.put(9, null);
		m.put(8, null);  m.put(10, null); m.put(11, null); m.put(5, null);
		m.put(13, null); m.put(3, null);  m.put(2, null);  m.put(16, null);
		return m;
	}
	

}
