/**
 * 
 */
package kz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Shuhratjan Nizamov
 *
 */
public class DiffieHellmanEncryption {
	private String text;
	private List<Long>encryptedText;
	private String decryptedTxt;
	private long p;
	private long g;
	private long A;
	private long B;
	private long a;
	private long b;
	private long s;
	
	/**
	 * 
	 */
	public DiffieHellmanEncryption() {
		this.text="";
		this.g=generatePrimeNumber(3, 1000);
		this.p=generatePrimeNumber(3, 1000);
		System.out.println("g: "+g);
		System.out.println("p: "+p);
		
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

	public void generateKeysA(){
		this.a=ThreadLocalRandom.current().nextLong(0, 1000+ 1);
		System.out.println("a: "+a);
		long sum=this.g;
		for(int i=0; i<a; i++){
			sum=(sum*this.g)%this.p;
		}
		
		this.A=sum;
		System.out.println("A: "+this.A);
		
	}
	public void generateKeysB(){
		this.b=ThreadLocalRandom.current().nextLong(0, 1000+ 1);
		System.out.println("b: "+a);
		long sum=this.g;
		for(int i=0; i<b; i++){
			sum=(sum*this.g)%this.p;
		}
		
		this.B=sum;
		System.out.println("B: "+this.B);
	}

	public void K(){
		long sum=this.B;
		for(int i=0; i<this.a; i++){
			sum=(sum*this.B)%this.p;
		}
		System.out.println("K : "+sum);
	}
	
}
