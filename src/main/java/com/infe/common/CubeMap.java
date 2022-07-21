package com.infe.common;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@SuppressWarnings("rawtypes")
public class CubeMap  extends HashMap
{
	private static final long serialVersionUID = 2135268622818763181L;
	private HttpServletRequest _request = null;
	private MultipartHttpServletRequest _multirequest = null;

	public CubeMap() {}

	public CubeMap(Object obj)
	{
		this((HashMap)obj);
	}

	@SuppressWarnings("unchecked")
	public CubeMap(HashMap map)
	{
		if (map != null) { putAll(map);
		}
	}

	@SuppressWarnings("unchecked")
	public CubeMap(Map map)
	{
		if (map != null) { putAll(map);
		}
	}

	public CubeMap(Object... units)
	{
		setArgs(units);
	}

	public CubeMap(HttpServletRequest request) throws Exception
	{
		setRequest(request);
	}
	
	public CubeMap(MultipartHttpServletRequest request)
	{
		setMultiRequest(request);
	}

	public static CubeMap getInstance(Object map)
	{
		return map == null ? null : new CubeMap(map);
	}

	public HttpServletRequest getServletRequest() {
		return this._request;
	}

	public String getQueryString(String name) {
		String query = this._request.getQueryString();
		if (query == null) { return null;
		}
		if (query.startsWith(name + "=")) {
			int pos = query.indexOf("&");

			if (pos > -1) return query.substring(name.length() + 1, pos);
			return query.substring(name.length() + 1);
		}

		int pos1 = query.indexOf("&" + name + "=");

		if (pos1 > -1) {
			int pos2 = query.indexOf("&", pos1 + 1);

			if (pos2 > -1) return query.substring(pos1 + name.length() + 2, 
					pos2);
			return query.substring(pos1 + name.length() + 2);
		}

		return null;
	}

	public int getQueryStringCount(String name) {
		String query = this._request.getQueryString();
		if (query == null) { return 0;
		}
		int cnt = 0;
		int last = 0;
		for (;;) {
			int start = query.indexOf(name + "=", last);
			if (start <= -1) break;
			cnt++;
			last = start + name.length() + 1;
		}
		return cnt;
	}


	public CubeMap copy()
	{
		return (CubeMap)super.clone();
	}

	@SuppressWarnings("unchecked")
	public void set(String key, Object value)
	{
		key = key.trim();
		super.put(key, value);
	}


	@SuppressWarnings("unchecked")
   public void setArgs(Object... units)
   {
     for (int i = 0; i < units.length; i += 2) {
       super.put((String)units[i], i + 1 < units.length ? units[(i + 1)] : null);
     }
   }

	public int getInt(String key)
	{
		return getInt(key, 0);
	}

	public int getInt(String key, int defaultValue)
	{
		return _getInt(key, defaultValue, false);
	}

	private int _getInt(String key, int defaultValue, boolean upper) {
		Object o = super.get(key);

		if (o == null) {
			if (upper) {
				return defaultValue;
			}

			return _getInt(key.toUpperCase(), defaultValue, true);
		}


		if ((o instanceof Integer)) {
			return ((Integer)o).intValue();
		}
		if ((o instanceof Long)) {
			return ((Integer)o).intValue();
		}
		if ((o instanceof Float)) {
			return ((Float)o).intValue();
		}
		if ((o instanceof Double)) {
			return ((Double)o).intValue();
		}

		if (o.equals("")) {
			return defaultValue;
		}

		return (int)Double.parseDouble(o.toString());
	}


	public long getLong(String column)
	{
		return getLong(column, 0L);
	}


	public long getLong(String key, long defaultValue)
	{
		return _getLong(key, defaultValue, false);
	}

	private long _getLong(String key, long defaultValue, boolean upper) {
		Object o = super.get(key);

		if (o == null) {
			if (upper) {
				return defaultValue;
			}

			return _getLong(key.toUpperCase(), defaultValue, true);
		}


		if ((o instanceof Integer)) {
			return ((Integer)o).longValue();
		}
		if ((o instanceof Long)) {
			return ((Long)o).longValue();
		}
		if ((o instanceof Float)) {
			return ((Float)o).longValue();
		}
		if ((o instanceof Double)) {
			return ((Double)o).longValue();
		}

		if (o.equals("")) {
			return defaultValue;
		}

		return Long.parseLong(o.toString());
	}

	public float getFloat(String key)
	{
		return getFloat(key, 0.0F);
	}


	public float getFloat(String key, float defaultValue)
	{
		return _getFloat(key, defaultValue, false);
	}

	private float _getFloat(String key, float defaultValue, boolean upper) {
		Object o = super.get(key);

		if (o == null) {
			if (upper) {
				return defaultValue;
			}

			return _getFloat(key.toUpperCase(), defaultValue, true);
		}


		if ((o instanceof Integer)) {
			return ((Integer)o).floatValue();
		}
		if ((o instanceof Long)) {
			return ((Long)o).floatValue();
		}
		if ((o instanceof Float)) {
			return ((Float)o).floatValue();
		}
		if ((o instanceof Double)) {
			return ((Double)o).floatValue();
		}

		if (o.equals("")) {
			return defaultValue;
		}

		return (float)Double.parseDouble(o.toString());
	}

	public double getDouble(String key)
	{
		return getDouble(key, 0.0D);
	}

	public double getDouble(String key, double defaultValue)
	{
		return _getDouble(key, defaultValue, false);
	}

	private double _getDouble(String key, double defaultValue, boolean upper) {
		Object o = super.get(key);

		if (o == null) {
			if (upper) {
				return defaultValue;
			}

			return _getDouble(key.toUpperCase(), defaultValue, true);
		}


		if ((o instanceof Integer)) {
			return ((Integer)o).doubleValue();
		}
		if ((o instanceof Long)) {
			return ((Long)o).doubleValue();
		}
		if ((o instanceof Float)) {
			return ((Float)o).doubleValue();
		}
		if ((o instanceof Double)) {
			return ((Double)o).doubleValue();
		}

		if (o.equals("")) {
			return defaultValue;
		}

		return Double.parseDouble(o.toString());
	}


	public String getString(String column)
	{
		return get(column, "");
	}

	public String getString(String column, String defaultValue)
	{
		return get(column, defaultValue);
	}


	public String get(String key)
	{
		return CubeUtils.nvl(get(key, ""));
	}


	public String get(String key, String defaultValue)
	{
		return _get(key, defaultValue, false);
	}

	private String _get(String key, String defaultValue, boolean upper) {
		Object o = super.get(key);

		if (o == null) {
			if (upper) {
				return defaultValue;
			}

			return _get(key.toUpperCase(), defaultValue, true);
		}
		if ((o instanceof String)) {
			return o.equals("") ? defaultValue : (String)o;
		}
		if ((o instanceof Integer)) {
			return o.toString();
		}
		if ((o instanceof Long)) {
			return o.toString();
		}
		if ((o instanceof Float)) {
			return o.toString();
		}
		if ((o instanceof Double)) {
			return o.toString();
		}
		if ((o instanceof BigDecimal)) {
			return ((BigDecimal)o).toString();
		}
//		if ((o instanceof Date)) {
//			return DateFormatUtils.format((Date)o, "yyyy-MM-dd");
//		}

		return (String)o;
	}
	
	public void remove(String key)
	{
		super.remove(key);
	}

	public String toQueryString()
	{
		StringBuffer sb = new StringBuffer();

		Set set = entrySet();
		Iterator keys = set.iterator();

		boolean isFirst = true;
		while (keys.hasNext()) {
			String sKey = keys.next().toString();
			if (!isFirst) sb.append("&");
			sb.append(sKey);
			isFirst = false;
		}

		return sb.toString();
	}

	public String toQueryString(String url)
	{
		CubeMap param = copy();
		param.removePaging();

		if (param.toQueryString().equals("")) {
			return url;
		}

		if (url.indexOf("?") > -1) return url + "&" + param.toQueryString();
		return url + "?" + param.toQueryString();
	}


	public String toString()
	{
		String s = "";
		Set set = keySet();
		for (Iterator it = set.iterator(); it.hasNext();) {
			Object key = it.next();

			if ((super.get(key) instanceof String[])) {
				s = s + "[" + key + "={";

				String[] str = (String[])super.get(key);
				for (int i = 0; i < str.length; i++) {
					s = s + (i == 0 ? "" : ", ") + str[i];
				}

				s = s + "}]";
			}
			else {
				s = s + "[" + key + "=" + get(key) + "]";
			}
		}

		return s;
	}

	private static String nvl(Object v)
	{
		if (v == null) return "";
		return String.valueOf(v);
	}

	@SuppressWarnings("unchecked")
	public void setRequest(HttpServletRequest request) throws Exception
	{
		this._request = request;

		Enumeration _enum = this._request.getParameterNames();
		while (_enum.hasMoreElements())
		{
			String key = (String)_enum.nextElement();
			
			if ( key.contains("_ck") ) {
				super.put(key, nvl(this._request.getParameter(key)));
			} else {
				super.put(key, CubeUtils.getStrParameter(nvl(this._request.getParameter(key))));
			}
			
			String[] pv = null;
			if (this._request.getParameterValues(key) != null) {
				pv = this._request.getParameterValues(key);
				if (pv.length > 1) { super.put(key, pv);
				}
			}
		}
		
		super.put("mbauth", nvl(request.getSession().getAttribute("auth")));
		//     Utils.debug(request.getRequestURI() + " : " + this);
	}
	
	@SuppressWarnings("unchecked")
	public void setMultiRequest(MultipartHttpServletRequest request)
	{
		this._multirequest = request;
		
		Enumeration _enum = this._multirequest.getParameterNames();
		while (_enum.hasMoreElements())
		{
			String key = (String)_enum.nextElement();
			super.put(key, nvl(this._multirequest.getParameter(key)));
			String[] pv = null;
			if (this._multirequest.getParameterValues(key) != null) {
				pv = this._multirequest.getParameterValues(key);
				if (pv.length > 1) { super.put(key, pv);
				}
			}
		}
		super.put("mbauth", nvl(request.getSession().getAttribute("auth")));
	}

	public boolean isNull(String key)
	{
		if (this._request == null) return super.get(key) == null;
		return this._request.getParameter(key) == null;
	}

	public void addPaging(int page)
	{
		addPaging(page, 10);
	}

	public void addPaging(int page, int pageSize)
	{
		int nStart = (page - 1) * pageSize;
		int nEnd = page * pageSize;

		set("numstart", Integer.valueOf(nStart));
		set("numend", Integer.valueOf(nEnd));
		set("pp", Integer.valueOf(pageSize));
	}
	public void removePaging()
	{
		remove("numstart");
		remove("numend");
		remove("pp");
	}

	private String getDefaultQueryId() {
		String id = this._request.getRequestURI();
		id = id.substring(0, id.lastIndexOf("/"));
		id = CubeUtils.replace(id, "/", ".");
		return id;
	}

	public void keepQuery(HttpServletResponse response)
			throws Exception
	{
		keepQuery(response, null);
	}

	public void keepQuery(HttpServletResponse response, String id)
			throws Exception
	{
		String url = this._request.getRequestURI();
		String query = this._request.getQueryString();

		String sId = getDefaultQueryId();
		sId = sId + (id == null ? "" : new StringBuilder(".").append(id).toString());

		CubeUtils.setCookie(response, "q" + sId + ".url", url);
		CubeUtils.setCookie(response, "q" + sId + ".query", query);
	}

	public String backQuery() throws Exception {
		return backQuery(null);
	}


	public String backQuery(String id)
			throws Exception
	{
		String sId = getDefaultQueryId();
		sId = sId + (id == null ? "" : new StringBuilder(".").append(id).toString());

		String url = CubeUtils.getCookie(this._request, "q" + sId + ".url");
		String query = CubeUtils.getCookie(this._request, "q" + sId + ".query");

		url.equals("");
		return url + (query.equals("") ? "" : new StringBuilder("?").append(query).toString());
	}

	public CubeMap toLowerCaseKey() {
		CubeMap param = new CubeMap();
		Iterator it = keySet().iterator();
		try {
			while (it.hasNext()) {
				String key = ((String)it.next()).toLowerCase();
				param.set(key, get(key));
			}
		} catch(Exception e) {}
		return param;
	}

	public Object get(Object obj) {
		if (obj == null) {
			return "";
		}
		try
		{
			return get(obj.toString());
		} catch (ClassCastException cce) {}
		return super.get(obj);
	}
	
	
	/**
	 * @Method Name : getObject
	 * @작성일 : 2020. 2. 6.
	 * @작성자 : chan
	 * @변경이력 :
	 * @Method 설명 : map에 담긴 Object 꺼낼때,
	 * @param key
	 * @return
	 */
	public Object getObject(String key) {
		Object object = super.get(key);
		
		if(object == null) object = new Object();
		
		return object;
	}
	
	/**
	 * @Method Name : getKvstr
	 * @작성일 : 2020. 2. 4.
	 * @작성자 : chan
	 * @변경이력 :
	 * @Method 설명 : map에 담긴 key, value 반환
	 * @return
	 */
	public String getKvstr() {
		String rtnString = "";
		
		int rowcount = 0;
		Iterator<String> iterator = this.keySet().iterator();
		while(iterator.hasNext()) {
			String key 		= iterator.next();
			String value 	= this.get(key, "");
			
			if(rowcount == 0) 	rtnString = rtnString + key + ":" + value;
			else 				rtnString = rtnString + ", " + key + ":" + value;
			rowcount ++;
		}
		
		if(rtnString.length() != 0) {
			rtnString = "{" + rtnString;
			rtnString = rtnString + "}";
		}
		
		return rtnString;
	}
}