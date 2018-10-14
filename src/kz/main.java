package kz;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class main {

	public  static void main(String[] args) {
		
		/*
		VigenereEncryption ve=new VigenereEncryption();
		System.out.println("================== Virgere Encryption: ");
		ve.readFile("Text.txt");
		ve.readKey("key.txt");
		ve.encryptTxt();
		ve.decryptTxt();
		*/
		
		/*
		System.out.println("\n================== Encryption on Magic Table: ");
		MagicTableEncryption mte=new MagicTableEncryption();
		mte.readFile("Text.txt");
		mte.encryptText();
		mte.decryptText(mte.getEncyptedText(),0);
		System.out.println("Decrypted text: "+mte.getDecryptedText());
		*/
		
		/*System.out.println("\n================== Encryption on Magic Table: ");
		DesEncryption des=new DesEncryption();
		des.encryptAndDecrypt();
		*/
		
		/*
		RSAEncryption rsa=new RSAEncryption();
		rsa.readFile("Text.txt");
		rsa.encryptText();
		rsa.decryptText(rsa.getEncryptedText());
		*/
		System.out.println("\n\nDH Algorithm: ");
		DiffieHellmanEncryption dh=new DiffieHellmanEncryption();
		dh.generateKeysA();
		dh.generateKeysB();
	}


}
