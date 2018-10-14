/**
 * 
 */
package kz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Shuhratjan Nizamov
 *
 */
public class RSAEncryption {

	private String text;
	private List<Long>encryptedText;
	private String decryptedText;
	private long e;
	private long d;
	private long f;//just f=(p-1)*(q-1)
	private long n;// n=q*p;

	public RSAEncryption() {
		this.text="";
		this.encryptedText=new ArrayList<Long>();
		this.decryptedText="";
		this.e=1;
		this.d=1;
		this.f=0;
		this.n=0;
	}

	public void encryptText(){
		if(!this.text.isEmpty()){
			generateKeys();
			for (char letter : this.text.toCharArray()) {
				long m=(int)letter;
				long sum=m;
				for(int i=1; i<this.e; i++){
					sum=(sum*m)%this.n;
				}
				this.encryptedText.add(sum);
			}
		}else{
			System.err.println("-----Empty Text-----");
			System.exit(0);
		}
		System.out.println("Encrypted Text:\n "+this.encryptedText);
	}

	public void decryptText(List<Long> encryptedText){
		if(!encryptedText.isEmpty()){
			for (Long number : encryptedText) {
				long C=number;
				long sum=C;
				for(int i=1; i<this.d; i++){
					sum=(sum*C)%this.n;
				}
				this.decryptedText+=(char)sum;
			}
		}else{
			System.out.println("Encrypted Text is empty!");
			System.exit(0);
		}
		System.out.println("Decrypted Text:\n "+this.decryptedText);
	}
	
	public void generateKeys(){
		long p=generatePrimeNumber(3,1000);
		long q=generatePrimeNumber(3,1000);
		System.out.println("P: "+p);
		System.out.println("Q: "+q);
		this.n=(p*q);
		System.out.println("n = "+p+" * "+q+" = "+this.n);
		this.f=(p-1)*(q-1);
		System.out.println("f = ("+p+" - 1)*("+q+" - 1) = "+this.f);
		do{
			this.e=generatePrimeNumber(17,this.f);
		}while(this.f%this.e==0);
		System.out.println("E: "+this.e);
		generatePrivateKey();
		System.out.println("D: "+this.d);
	}

	public long generatePrimeNumber(long start, long end){
		long number=0;	 		  
	    int counter=0; 		  
	    while(counter!=2){
	    	long i=ThreadLocalRandom.current().nextLong(start, end + 1);
	    	counter=0;
	    	for(long num = i; num > 0; num--){
	    		if(i%num==0){
	    			counter++;
	        		if(counter>2){
	        		 	counter=0;
	        		 	break;
	        		 }
	 		    }
	         }
	    	if(counter==2)
	    		number=i;
	    }
	    return number;
	}

	public void generatePrivateKey() {
		int k=1;
		while(!isDoubleInt(this.d)||((this.d*this.e)%this.f!=1) || this.d==this.e){
			this.d=(k*this.f+1)/this.e;
			k++;
		}
	}
	
	public boolean isDoubleInt(double d)
	{
	    double TOLERANCE = 1E-5;
	    return Math.abs(Math.floor(d) - d) < TOLERANCE;
	}
	
	public void readFile(String filename){
		try{
			BufferedReader bfile=new BufferedReader(new FileReader(new File(filename)));	
			while(bfile.ready()){
				this.text+=bfile.readLine();
				if(bfile.ready()) this.text+="\n";
			}
			bfile.close();
		}catch(IOException e){
			System.err.println("Problem With File : "+filename);
			System.exit(0);
		}
		System.out.println("Text:\n "+this.text);
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
	 * @return the publicExponent
	 */
	public long getE() {
		return e;
	}

	/**
	 * @param publicExponent the publicExponent to set
	 */
	public void setE(long e) {
		this.e=e;
	}

	/**
	 * @return the d
	 */
	public long getD() {
		return d;
	}

	/**
	 * @param d the d to set
	 */
	public void setD(long d) {
		this.d = d;
	}

	/**
	 * @return the f
	 */
	public long getF() {
		return f;
	}

	/**
	 * @param f the f to set
	 */
	public void setF(long f) {
		this.f = f;
	}

	/**
	 * @return the n
	 */
	public long getN() {
		return n;
	}

	/**
	 * @param n the n to set
	 */
	public void setN(long n) {
		this.n = n;
	}

	/**
	 * @return the encryptedText
	 */
	public List<Long> getEncryptedText() {
		return encryptedText;
	}
	/**
	 * @param encryptedText the encryptedText to set
	 */
	public void setEncryptedText(List<Long> encryptedText) {
		this.encryptedText = encryptedText;
	}

	/**
	 * @return the decryptedText
	 */
	public String getDecryptedText() {
		return decryptedText;
	}

	/**
	 * @param decryptedText the decryptedText to set
	 */
	public void setDecryptedText(String decryptedText) {
		this.decryptedText = decryptedText;
	}

}
