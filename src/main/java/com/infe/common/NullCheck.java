package com.infe.common;


public class NullCheck  {

    public static boolean nvl(String data) { return (data==null || data.length()==0 || data.equals("null") || data.equals("NULL")); }
    public static boolean nvl(int    data) { return (data==0); }
    public static boolean nvl(double data) { return (data==0); }
    public static boolean nvl(Object data) { return (data==null); }
    public static boolean nvl(Object[] data) { return (data.length==0); }
	
}
