// This is sample class not needed in the package

package com.bookstore.entity;

import java.io.*; 

public class Test 
{ 
	public static void main(String[] args) 
	{ 
		String imagePath  = "/⁨Users⁩/⁨pradeepsm⁩/⁨Desktop⁩/⁨Web_Projects⁩/⁨books⁩/EffectiveJava.jpg";
		String imagePath1 = imagePath.replaceAll("[\u2068\u2069]", "");
		File file = new File(imagePath1); 
		
		// check if the file exists 
		boolean exists = file.exists(); 
		if(exists == true) 
		{ 
			// printing the permissions associated with the file 
			System.out.println("Executable: " + file.canExecute()); 
			System.out.println("Readable: " + file.canRead()); 
			System.out.println("Writable: "+ file.canWrite()); 
		} 
		else
		{ 
			System.out.println("File not found."); 
		} 
	} 
} 
