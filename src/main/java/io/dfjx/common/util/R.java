/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjx.common.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark mazong@gmail.com
 */
public class R extends HashMap<String, Object> implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int HTTP_500 = 500;
	
	public R() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static R error() {
		return error(HTTP_500, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HTTP_500, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(Object data){
		return put("data", data);
	}
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public boolean isok(){
		if(!this.containsKey("code")) return false;
		String code = this.get("code").toString();
		return code.equals("0");
	}
}
