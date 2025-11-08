import java.io.*;
class FileInputDemo {
    			public static void main(String args[]){
    				if (args.length == 1){
    	    				try{
    	   			 		FileInputStream fstream = new FileInputStream(args[0]);
    	 			   		DataInputStream in = new DataInputStream(fstream);
    	  			  		while (in.available()!=0){
    	    						System.out.println(String.valueOf(in.readDouble()));
    	    					}
    	    					in.close();
    	    				}catch(Exception e){
						System.out.println("File input error");
					}
				}else
					System.out.println("Invalid parameters");
			}
}
