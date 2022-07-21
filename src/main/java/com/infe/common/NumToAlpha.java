package com.infe.common;

public class NumToAlpha {
	 public static int getExcelColumnNumber(String column) {
	        int result = 0;
	        column = column.replaceAll("[0-9]", "");
	        for (int i = 0; i < column.length(); i++) {
	            result *= 26;
	            result += column.charAt(i) - 'A' + 1;
	        }
	        return result;
	    }

	    public static String getExcelColumnName(int number) {
	        final StringBuilder sb = new StringBuilder();

	        int num = number - 1;
	        while (num >=  0) {
	            int numChar = (num % 26)  + 65;
	            sb.append((char)numChar);
	            num = (num  / 26) - 1;
	        }
	        return sb.reverse().toString();
	    }

}

