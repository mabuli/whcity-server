/**
 * Copyright 2018 东方金信
 *  URL：bs-development/query/表名
 *  说明：表数据查询
 *  参数：s-显示字段,o-排列字段,
 *        w-查询条件:w=name!aa;val!3,4,5;code!lkcong;等于 name='aa' and val in ('3','4','5') and code like '%cong%'
 *  1) 查询所有数据：http://localhost:9005/bs-development/query/表名
 *  2）带条件查询：http://localhost:9005/bs-development/query/表名?s=name,val&w=val!3
 *            sql：select name,val from 表名 where val=3
 */

package io.dfjx.module.sys.base;

import io.dfjx.common.util.R;
import io.dfjx.common.util.SpringContextUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统页面API
 *
 * @author mazong
 * @email gitname@github.com
 * @date 2019年11月31日 下午11:05:27
 */
@RestController
public class SysApiController {

	private static Logger logger = LoggerFactory.getLogger(SysApiController.class);

	@RequestMapping("api/{serviceName}/{methodName}")
	public R api(@PathVariable String serviceName, @PathVariable String methodName, HttpServletRequest request, HttpServletResponse response){
		Object servcieObj = SpringContextUtil.getBean(serviceName);
		Object result = R.ok();
		try {
			Method method = servcieObj.getClass().getDeclaredMethod(methodName, Map.class);
			result = method.invoke(servcieObj, request.getParameterMap());
		} catch (Exception ex) {
			checkException(ex, serviceName, methodName, response);
		}
		return (R) result;
	}

	/**
	 * 表数据查询
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	//@RequestMapping("query/{tableName}")
	public R query(@PathVariable String tableName, HttpServletRequest request, HttpServletResponse response){
		String where = mstr(request, "w", "");
		String column = mstr(request, "s", "");
		String orderBy = mstr(request, "o", "");
		if(column.length()==0) column = "*";
		if(orderBy.length()>0) orderBy = " order by " + orderBy;
		String sql = "select "+column+" from "+tableName+" " + doWhere(where,request, response) + orderBy;
		return doSql(sql, request, response);
	}


	private void checkException(Exception ex, String serviceName, @PathVariable String methodName, HttpServletResponse response){
		if (ex.getCause() instanceof NullPointerException){
			response.setStatus(450);
		}
		if (ex.getCause() instanceof ArrayIndexOutOfBoundsException){
			response.setStatus(450);
		}
		logger.info("调用失败 servicename：{}，methodname：{}", serviceName, methodName);
		logger.error("异常信息 ",ex.getCause());
		logger.error(ex.getMessage());
	}

	private String doWhere(String value, HttpServletRequest request, HttpServletResponse response){
		StringBuilder sb = new StringBuilder();
		sb.append(" where 1=1 ");
		String[] cnds = StringUtils.split(value, ";");
		for(String cnd : cnds){
			String[] kvp = StringUtils.split(cnd, "!");
			if(kvp.length <2)
				continue;
			String key = kvp[0];
			String fn = "eq";
			String val = kvp[1];
			String tp = "=";
			if(kvp.length >2){
				fn = kvp[1];
				val = kvp[2];
			}
			if("lk".equals(fn)){
				tp = "like";
				val = "'%" + val + "%'";
			} else if("gq".equals(fn)){
				tp = ">=";
			}  else if("lq".equals(fn)){
				tp = "<=";
			} else{
				if(val.indexOf(',')>0){
					String[] arr1 = StringUtils.split(val, ",");
					String tmp1 = "";
					int i = 0;
					for(String str : arr1){
						if(i>0){
							tmp1 += ",";
						}
						tmp1 += "'" + str + "'";
						i++;
					}
					val = "(" + tmp1 + ")";
					tp = "in";
				}else{
					val = "'" + val + "'";
				}
			}
			sb.append(" and ").append(key).append(" "+tp+" ").append(val);
		}
		return sb.toString();
	}

	private R doSql(String sql, HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = new HashMap<>();
		param.put("sql", sql);
		return doQuery(param, "queryService", "querySql", request, response);
	}

	private R doQuery(Map<String, Object> param, String serviceName, String methodName, HttpServletRequest request, HttpServletResponse response){
		Object servcieObj = SpringContextUtil.getBean(serviceName);
		Object result = R.ok();
		try {
			Method method = servcieObj.getClass().getDeclaredMethod(methodName, Map.class);
			result = method.invoke(servcieObj, param);
		} catch (Exception ex) {
			checkException(ex, serviceName, methodName, response);
			return R.error("请求异常，" + ex.getMessage());
		}
		return (R) result;
	}

	private String mstr(HttpServletRequest request, String key, String dft){
		String val = request.getParameter(key);
		if(val == null || "".equals(val.toString())) return dft;
		return val;
	}
}
