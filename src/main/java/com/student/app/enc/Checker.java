/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.app.enc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gmartham
 */
public class Checker {
	
	
	  public static void main(String[] args) throws Exception {

//        String password = "Basic GIRISH MARTHAM";
//        String passwordEnc = AESencrp.encrypt(password);
//        String passwordDec = AESencrp.decrypt(passwordEnc);
//
//        System.out.println("Plain Text : " + password);
//        System.out.println("Encrypted Text : " + passwordEnc);
//        System.out.println("Decrypted Text : " + passwordDec);
        
        
		  ArrayList<String> al = new ArrayList();
		  for(int i=0;i<10;i++) {
		  al.add("girish"+i);
		  }
		  
		  al.forEach((String s)->{
			  System.out.println(s.equalsIgnoreCase("girish5"));
			  if(s.equalsIgnoreCase("girish5")) {
				  System.out.println(s);
			  }
			 
		  });
		  
		  al.forEach(n->{
		  if(n.equalsIgnoreCase("girish5")) {
			  System.out.println(n);
		  }
		  });
		  
		  Map<String,String> map=new HashMap();
		  for(int i=0;i<10;i++) {
			  map.put("girish"+i,"girishval"+i);
			  }
		  
		  map.forEach((k,v)->{
		  System.out.println(k+",,,,,,,,"+v);
		  if(k.equalsIgnoreCase("girish6")) {
			  System.out.println(k+",,,,,,,,"+v);
		  }
		  });
    }
}
