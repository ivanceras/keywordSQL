package com.ivanceras.keyword.sql;

import com.ivanceras.keyword.sql.KeywordsStatics;

public class SQLStatics extends KeywordsStatics{
	
	private static SQL instance(){
		return SQL.instance();
	}
	
	public static SQL WITH(String queryName){
		return instance().WITH(queryName);
	}

	public static SQL WITH(String queryName, SQL sql){
		return instance().WITH(queryName, sql);
	}
	
	public static SQL WITH(String queryName, SQL sql, String queryName2, SQL sql2){
		return instance().WITH(queryName).AS().FIELD(sql)
				.ln().comma().keyword(queryName2).AS(sql2);
	}
	
	public static SQL WITH(String queryName, SQL sql, String queryName2, SQL sql2, String queryName3, SQL sql3){
		return instance().WITH(queryName).AS().FIELD(sql)
				.ln().comma().keyword(queryName2).AS(sql2)
				.ln().comma().keyword(queryName3).AS(sql3);
	}
	public static SQL WITH_RECURSIVE(String queryName){
		return instance().WITH_RECURSIVE(queryName);
	}
	
	public static SQL WITH_RECURSIVE(String queryName, SQL sql){
		return instance().WITH_RECURSIVE(queryName, sql);
	}
	
	public static SQL ALTER_TABLE(String table){
		return instance().ALTER_TABLE(table);
	}
	public static SQL AVG(SQL sql){
		return instance().AVG(sql);
	}
	public static SQL AVG(String column){
		return instance().AVG(column);
	}
	public static SQL COUNT(SQL sql){
		return instance().COUNT(sql);
	}

	public static SQL COUNT(String column){
		return instance().COUNT(column);
	}
	public static SQL CREATE_TABLE(String table){
		return instance().CREATE_TABLE(table);
	}
	public static SQL DELETE_FROM(String table){
		return instance().DELETE_FROM(table);
	}

	public static SQL DROP_TABLE() {
		return instance().DROP_TABLE();
	}

	public static SQL DROP_TABLE(String table) {
		return instance().DROP_TABLE(table);
	}

	public static SQL LOWER(SQL sql){
		return instance().LOWER(sql);
	}
	public static SQL LOWER(String column){
		return instance().LOWER(column);
	}

	public static SQL MAX(SQL sql){
		return instance().MAX(sql);
	}

	public static SQL MAX(String column){
		return instance().MAX(column);
	}

	public static SQL MIN(SQL sql){
		return instance().MIN(sql);
	}

	public static SQL MIN(String column){
		return instance().MIN(column);
	}

	public static SQL SELECT(SQL arg){
		return instance().SELECT(arg);
	}

	public static SQL SELECT(String... columns){
		return instance().SELECT(columns);
	}

	public static SQL SUM(SQL sql){
		return instance().SUM(sql);
	}


	public static SQL SUM(String column){
		return instance().SUM(column);
	}

	public static SQL TRUNCATE_TABLE(String table){
		return instance().TRUNCATE_TABLE(table);
	}

	public static SQL UPDATE(String table){
		return instance().UPDATE(table);
	}

	public static SQL UPPER(SQL sql){
		return instance().UPPER(sql);
	}

	public static SQL UPPER(String column){
		return instance().UPPER(column);
	}
	
	public static SQL SIN(SQL sql){
		return instance().SIN(sql);
	}
	public static SQL COS(SQL sql){
		return instance().COS(sql);
	}
	public static SQL TAN(SQL sql){
		return instance().TAN(sql);
	}
	public static SQL ASIN(SQL sql){
		return instance().ASIN(sql);
	}
	public static SQL ACOS(SQL sql){
		return instance().ACOS(sql);
	}
	public static SQL ATAN(SQL sql){
		return instance().ATAN(sql);
	}
	public static SQL DEGREES(SQL sql){
		return instance().DEGREES(sql);
	}
	public static SQL RADIANS(SQL sql){
		return instance().RADIANS(sql);
	}
	public static SQL VALUE(Object obj){
		return instance().VALUE(obj);
	}
	public static SQL FIELD(String column){
		return instance().FIELD(column);
	}
}
