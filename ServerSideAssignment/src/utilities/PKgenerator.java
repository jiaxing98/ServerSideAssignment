package utilities;

import java.util.List;

public class PKgenerator {
    
	public static String getAlphaNumericString(List<String> pkString) {
		final int m = 2;
    	final int n = 6;
    	String pk;
        String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Number = "0123456789";
  
        StringBuilder alphaSB = new StringBuilder(m); 
        StringBuilder numberSB = new StringBuilder(n); 
        
        do {
        	
            for (int i = 0; i < m; i++) { 
            	   
                int index = (int)(Alphabet.length() * Math.random()); 
                alphaSB.append(Alphabet .charAt(index)); 
            }
            
            for (int i = 0; i < n; i++) { 
            	   
                int index = (int)(Number.length() * Math.random()); 
                numberSB.append(Number .charAt(index));  
            }
            
            pk =  alphaSB.toString() + numberSB.toString();
            
        }while(CheckPK(pkString, pk));

        return pk;
    }
	
	private static boolean CheckPK(List<String> pkString, String newPK) {
		for(String s : pkString) {
			if(s.equals(newPK))
				return false;
		}
		
		return true;
	}
}
