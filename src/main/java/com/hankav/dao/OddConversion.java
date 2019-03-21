package com.hankav.dao;

public class OddConversion {
	
	
	public Double fromFractionalToDecimal(String s) {
        s.trim();    
        String[] frarr=s.split("\\/");
		if(frarr.length==2) {			
		  return Double.parseDouble(frarr[0])/Double.parseDouble(frarr[1])+1;	
			
		}
		/*Double dblfmt=Double.parseDouble(s);
	    String[] arr=dblfmt.toString().split("\\.");
	    if(arr.length==2) {
	    	return Double.parseDouble(s);
	    }
	
		return null;*/
		return null;
	}
	
	
	
	
	public Double fromAmericanToDecimal(String s) {
        s.trim();
			if(s.charAt(0)=='-') {
				return (100/Double.parseDouble(s.substring(1,s.length())))+1;
				
			}
			else {
				return (Double.parseDouble(s)/100)+1;
			}
	}
	
	
	
	
	
	
	
	
	/*public String fromDecimal(Double decodds,String oddsformat) {
		if(oddsformat.equals("AMERICAN")) {
			if(decodds>2) {
				String amp="+"+String.valueOf((decodds-1.0)*100.0);
			    return amp.split("\\.")[0];
			}
			else {
				String amn=String.valueOf(-100/(decodds-1));
				return amn.split("\\.")[0];
			}
		}
		
		if(oddsformat.equals("FRACTIONAL")) {
			
			String frastring=decodds.toString();
			String[] arr=frastring.split("\\.");
			int len=arr[1].length();
			int num;
			int den=1;
			for(int i=0;i<len;i++) {
				decodds*=10;
				den*=10;
			}
			num = (int) Math.round(decodds);
			int gcd=gcf(num,den);
	        num = num/gcd;
	        den= den/gcd;
			
	        return String.valueOf(num-den) + "/" + String.valueOf(den);
		}
		
		return null;
		
		
	}
	
	
	public int gcf(int num,int den) {
		if(den==0) {
			return num;
		}
		
		return gcf(den,num%den);
		
	}
	*/


}
