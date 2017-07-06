package com.tools.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mysql.fabric.xmlrpc.base.Array;

/** 
* @author 作者 : 
* @version 创建时间：2017年5月22日 下午5:55:28 
* 类说明 
*/
public class Constant {
	/**
	 * 执行失败
	 */
	public static final boolean EXEC_STATUS_FAILED = false;
	
	/**
	 * 执行成功
	 */
	public static final boolean EXEC_STATUS_SUCCESS = true;
	/**
	 * url缓存集合
	 */
	public static final Set<String> EXEC_URL_SET = new HashSet<>();
		
	public static final int  MINSIZE=1000;

	
}
