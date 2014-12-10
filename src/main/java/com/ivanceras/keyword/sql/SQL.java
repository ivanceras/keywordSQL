package com.ivanceras.keyword.sql;

import java.util.ArrayList;
import java.util.List;

import com.ivanceras.keyword.sql.Keywords;
import static com.ivanceras.keyword.sql.BaseSQL.Type.*;

public class SQL extends Keywords{
	
	
	/**
	 * Wraps with open and close parenthesis the SQL
	 * @param sql
	 * @return
	 */
	public SQL FIELD(SQL sql){
		smartCommaFnField();
		openParen();
		field(sql);
		closeParen();
		lastCall = FIELD;
		return this;
	}

	public SQL FIELD(String keyword){
		smartCommaFnField();
		keyword(keyword);
		lastCall = FIELD;
		return this;
	}
	

	public SQL FIELD(String... columns){
		for(String col : columns){
			FIELD(col);
		}
		return this;
	}


	public SQL ALTER_TABLE(String table){
		return instance().ALTER().TABLE(table);
	}
	public SQL AVG(SQL sql){
		return instance().FUNCTION("AVG", sql);
	}
	public SQL AVG(String column){
		return instance().FUNCTION("AVG", column);
	}
	public SQL COUNT(SQL sql){
		return instance().FUNCTION("COUNT", sql);
	}

	public SQL COUNT(String column){
		return instance().FUNCTION("COUNT", column);
	}
	public SQL CREATE_TABLE(String table){
		return CREATE().TABLE(table);
	}
	public SQL DELETE_FROM(String table){
		return DELETE().FROM(table);
	}

	public SQL DROP_TABLE() {
		return DROP().keyword("TABLE");
	}

	public SQL DROP_TABLE(String table) {
		return DROP().TABLE(table);
	}

	public static SQL instance(){
		return new SQL();
	}
	public SQL LOWER(SQL sql){
		return FUNCTION("LOWER", sql);
	}
	public SQL LOWER(String column){
		return FUNCTION("LOWER", column);
	}

	public SQL MAX(SQL sql){
		return FUNCTION("MAX", sql);
	}

	public SQL MAX(String column){
		return FUNCTION("MAX", column);
	}

	public SQL MIN(SQL sql){
		return FUNCTION("MIN", sql);
	}

	public SQL MIN(String column){
		return FUNCTION("MIN", column);
	}

	public SQL SELECT(SQL arg){
		return ln().SELECT().field(arg);
	}

	public SQL SELECT(String... columns){
		return ln().SELECT().FIELD(columns);
	}

	public SQL SUM(SQL sql){
		return FUNCTION("SUM", sql);
	}


	public SQL SUM(String column){
		return FUNCTION("SUM", column);
	}

	public SQL TRUNCATE_TABLE(String table){
		return keyword("TRUNCATE").TABLE(table);
	}

	public SQL UPDATE(String table){
		return keyword("UPDATE").FIELD(table);
	}

	public SQL UPPER(SQL sql){
		return FUNCTION("UPPER", sql);
	}

	public SQL UPPER(String column){
		return FUNCTION("UPPER", column);
	}


	public SQL AND(SQL sql){
		return keyword("AND").FN(sql);
	}


	public SQL AND(String column){
		return ln().AND().FIELD(column);
	}

	public SQL AND_ON(String column1, String column2) {
		return ln().AND().FIELD(column1).EQUAL_TO_FIELD(column2);
	}

	public SQL AS(SQL sql){
		return AS().ln().FIELD(sql);
	}
	public SQL AS(String asColumn){
		return AS().FIELD(asColumn);
	}
	
	public Breakdown build(){
		Breakdown bk = new Breakdown(); 
		build(bk, (SQL)this);
		return bk;
	}
	
	public SQL CONSTRAINT(String constraintName){
		return CONSTRAINT().keyword(constraintName);
	}
	public SQL CROSS_JOIN(String table){
		return CROSS().JOIN().FIELD(table);
	}
	
	public SQL DISTINCT(String... columns){
		return DISTINCT().FIELD(columns);
	}
	public SQL DISTINCT_ON(String... columns){
		DISTINCT().ON();
		openParen();
		FIELD(columns);
		closeParen();
		return this;
	}
	public SQL EQUAL() {
		return keyword("=");
	}
	public SQL NOT_EQUAL() {
		return keyword("!=");
	}
	public SQL EQUAL(Object value){
		return EQUAL().VALUE(value);
	}
	public SQL EQUAL_TO(Object value){
		return EQUAL().VALUE(value);
	}
	public SQL EQUAL_TO_FIELD(String column) {
		return EQUAL().FIELD(column);
	}
	public SQL EXCEPT(SQL sql){
		return EXCEPT().FIELD(sql);
	}
	public SQL EXCEPT_ALL(SQL sql){
		return EXCEPT().ALL().FIELD(sql);
	}
	public SQL EXISTS(SQL sql){
		EXISTS();
		openParen();
		FIELD(sql);
		closeParen();
		return this;
	}




	/**
	 * Does not wraps the SQL statements with parenthesis, to avoid unnecessary characters on the query
	 * @param sql
	 * @return
	 */
	public SQL FN(SQL sql){
		smartCommaFnField();
		field(sql);
		lastCall = FUNCTION;
		return this;
	}

	public SQL FOREIGN_KEY(String...columns){
		return FOREIGN().KEY()
				.openParen()
				.FIELD(columns)
				.closeParen()
				.ln();
	}
	
	public SQL FROM(SQL sql){
		return ln().FROM().FIELD(sql);
	}

	public SQL FROM(String table){
		ln().FROM().keyword(table);
		lastCall = TABLE;
		return this;
	}
	
	public SQL FROM(String table, String... otherTables){
		List<String> tableList = new ArrayList<String>();
		tableList.add(table);
		for(String tbl : otherTables){
			tableList.add(tbl);
		}
		return FROM(tableList.toArray(new String[tableList.size()]));
	}



	public SQL FROM(String[] tables){
		ln().FROM();
		for(String tbl : tables){
			table(tbl);
		}
		return this;
	}
	public SQL FULL_OUTER_JOIN(String table){
		return ln().FULL().OUTER().JOIN().FIELD(table);
	}


	public SQL function(String function, SQL sql){
		keyword(function);
		lastCall = FUNCTION;
		openParen();
		field(sql);
		closeParen();
		lastCall = FUNCTION;
		return this;
	}

	public SQL function(String function, String column){
		keyword(function);
		lastCall = FUNCTION;
		openParen();
		FIELD(column);
		closeParen();
		lastCall = FUNCTION;
		return this;
	}

	public SQL FUNCTION(String function, SQL sql){
		return function(function, sql);
	}

	public SQL FUNCTION(String function, String column){
		return function(function, column);
	}

	public SQL GREATER_THAN(){
		return keyword(">");
	}
	
	public SQL GREATER_THAN(Object value){
		return GREATER_THAN().VALUE(value);
	}

	public SQL GREATER_THAN(SQL sql){
		return GREATER_THAN().FIELD(sql);
	}

	public SQL GREATER_THAN_OR_EQUAL(){
		return keyword(">=");
	}
	public SQL GREATER_THAN_OR_EQUAL(Object value){
		return GREATER_THAN_OR_EQUAL().VALUE(value);
	}
	public SQL GROUP_BY(SQL sql){
		return GROUP().BY().FN(sql);
	}

	public SQL GROUP_BY(String... column){
		return GROUP().BY().FIELD(column);
	}
	/**
	 * As much as possible, don't use this
	 * @param expression
	 * @return
	 */
	public SQL HAVING(String column1){
		return HAVING().FIELD(column1);
	}
	
	public SQL IF_EXISTS(){
		return IF().EXISTS();
	}
	public SQL IF_NOT_EXISTS(){
		return IF().NOT().EXISTS();
	}
	public SQL IN(Object... value){
		IN();
		openParen();
		boolean doComma = false;
		for(Object v : value){
			if(doComma){comma();}else{doComma=true;}
			VALUE(v);
		}
		closeParen();
		return this;
	}

	public SQL IN(SQL sql){
		return ln().IN().FIELD(sql);
	}

	public SQL INDEX(String indexName, String columns){
		return INDEX().keyword(indexName).FIELD(columns);
	}
	public SQL INHERITS(String table){
		return INHERITS().openParen().FIELD(table).closeParen();
	}

	public SQL INNER_JOIN(String table){
		return ln().INNER().JOIN().FIELD(table);
	}

	public SQL INTERSECT(SQL sql){
		return INTERSECT().field(sql);
	}

	public SQL INTERSECT_ALL(SQL sql){
		return INTERSECT().ALL().FIELD(sql);
	}

	public SQL INTO(String table){
		INTO().FIELD(table);
		lastCall = TABLE;
		return this;
	}

	public SQL IS_NOT_NULL(){
		return IS().NOT().NULL();
	}
	public SQL IS_NULL(){
		return IS().NULL();
	}

	public SQL LEFT_JOIN(String table){
		return ln().LEFT().JOIN().FIELD(table);
	}

	public SQL LEFT_OUTER_JOIN(String table){
		return ln().LEFT().OUTER().JOIN().FIELD(table);
	}
	
	public SQL LESS_THAN(){
		return keyword("<");
	}

	public SQL LESS_THAN(Object value){
		return LESS_THAN().VALUE(value);
	}

	public SQL LESS_THAN(SQL sql){
		return LESS_THAN().FIELD(sql);
	}
	
	public SQL LESS_THAN_OR_EQUAL(){
		return keyword("<=");
	}
	public SQL LESS_THAN_OR_EQUAL(Object value){
		return LESS_THAN_OR_EQUAL().VALUE(value);
	}
	public SQL LIMIT(int limit){
		return LIMIT().keyword(limit+"");
	}

	public SQL MATCH_FULL(){
		return MATCH().FULL();
	}

	public SQL MATCH_SIMPLE(){
		return MATCH().SIMPLE();
	}


	public SQL NOT_EQUAL_TO(Object value){
		return keyword("!=").VALUE(value);
	}

	public SQL NOT_EQUAL_TO_FIELD(String column){
		return keyword("!=").FIELD(column);
	}

	public SQL NOT_EXISTS(SQL sql){
		NOT().EXISTS();
		FIELD(sql);
		return this;
	}

	public SQL NOT_IN(Object... value){
		NOT().IN();
		openParen();
		boolean doComma = false;
		for(Object v : value){
			if(doComma){comma();}else{doComma=true;}
			VALUE(v);
		}
		closeParen();
		return this;
	}
	public SQL NOT_IN(SQL sql){
		NOT().IN();
		FIELD(sql);
		return this;
	}

	public SQL NOT_NULL() {
		return NOT().NULL();
	}

	public SQL OFFSET(int offset){
		return OFFSET().keyword(offset+"");
	}

	public SQL ON(String column1){
		return ON().FIELD(column1);
	}

	public SQL ON(String column1, String column2){
		return ON().FIELD(column1).EQUAL().FIELD(column2);
	}
	public SQL ON_DELETE(){
		return ON().DELETE();
	}
	
	public SQL ON_UPDATE(){
		return ON().UPDATE();
	}

	public SQL OR(String column){
		return OR().FIELD(column);
	}
	public SQL ORDER_BY(){
		return ORDER().BY();
	}
	public SQL ORDER_BY(String... field){
		ORDER_BY();
		for(String f : field){
			FIELD(f);
		}
		return this;
	}
	public SQL PARTITION_BY(String... columns){
		return PARTITION().BY().FIELD(columns);
	}
	public SQL PRIMARY_KEY(){
		return PRIMARY().KEY();
	}
	public SQL PRIMARY_KEY(String...columns){
		return PRIMARY_KEY().openParen().FIELD(columns).closeParen();
	}
	public SQL REFERENCES(String table, String column){
		return REFERENCES().ln()
				.FIELD(table)
				.openParen().FIELD(column).ln().closeParen();
	}

	public SQL RENAME_TO(String table){
		return RENAME().TO(table);
	}

	public SQL RETURNING(String column){
		return ln().RETURNING().FIELD(column);
	}

	public SQL RIGHT_JOIN(String table){
		return ln().RIGHT().JOIN().FIELD(table);
	}

	public SQL RIGHT_OUTER_JOIN(String table){
		return ln().RIGHT().OUTER().JOIN().FIELD(table);
	}

	public SQL SCHEMA(String schema){
		return SCHEMA().FIELD(schema);
	}
	
	public SQL SET(String field) {
		return SET().FIELD(field);
	}
	public SQL SET(String field, Object value) {
		return SET().FIELD(field).EQUAL(value);
	}

	public SQL TABLE(String table){
		return TABLE().FIELD(table);
	}
	public SQL TABLE(String table, String...otherTables){
		TABLE();
		FIELD(table);
		lastCall = TABLE;
		for(String o : otherTables){
			FIELD(o);
		}
		return this;
	}

	public SQL TO(String table){
		return TO().TABLE(table);
	}

	public SQL UNION(SQL sql){
		return UNION().field(sql);
	}

	public SQL UNION_ALL(SQL sql){
		return UNION().ALL().FIELD(sql);
	}

	public SQL UNIQUE(String... uniqueColumns){
		return UNIQUE().openParen().FIELD(uniqueColumns).closeParen();
	}

	public SQL USING(String... column){
		return USING().FIELD(column);
	}

	public SQL VALUE(Object value){
		FIELD("?");
		values.add(value);
		lastCall = VALUE;
		return this;
	}
	public SQL VALUE(SQL sql){
		return FIELD(sql);
	}

	public SQL VALUES(Object... objValue){
		VALUES().openParen();
		for(Object val : objValue){
			VALUE(val);
		}
		return closeParen();
	}

	public SQL WHERE(SQL sql){
		return WHERE().FIELD(sql);
	}

	public SQL WHERE(String column){
		return WHERE().FIELD(column);
	}
	
	public SQL INITIALLY_DEFERRED() {
		return INITIALLY().DEFERRED();
	}
	
	public SQL WITH(String queryName){
		return WITH().keyword(queryName);
	}
	
	public SQL WITH(String queryName, SQL sql){
		return WITH(queryName).AS().FIELD(sql);
	}
	public SQL WITH_RECURSIVE(String queryName){
		return WITH().RECURSIVE().keyword(queryName);
	}
	public SQL WITH_RECURSIVE(String queryName, SQL sql){
		return WITH().RECURSIVE().keyword(queryName).AS().FIELD(sql);
	}
	
	/**
	 * Trigonometric Functions
	 * @param sql
	 * @return
	 */
	public SQL SIN(SQL sql) {
		return FUNCTION("SIN",sql);
	}
	
	public SQL COS(SQL sql) {
		return FUNCTION("COS",sql);
	}
	public SQL TAN(SQL sql) {
		return FUNCTION("TAN",sql);
	}
	public SQL SEC(SQL sql) {
		return FUNCTION("SEC",sql);
	}
	
	//arc
	public SQL ASIN(SQL sql) {
		return FUNCTION("ASIN",sql);
	}
	
	public SQL ACOS(SQL sql) {
		return FUNCTION("ACOS",sql);
	}
	public SQL ATAN(SQL sql) {
		return FUNCTION("ATAN",sql);
	}
	public SQL ASEC(SQL sql) {
		return FUNCTION("ASEC",sql);
	}
	
	public SQL DESC(){
		super.DESC();
		lastCall = FIELD;
		return this;
	}
	
	//degree, radians
	public SQL DEGREES(SQL sql) {
		return FUNCTION("DEGREES",sql);
	}
	public SQL RADIANS(SQL sql) {
		return FUNCTION("RADIANS",sql);
	}
	
	public SQL MULTIPLY(){
		return keyword("*");
	}
	public SQL PLUS(){
		return keyword("+");
	}
	
	public SQL MINUS(){
		return keyword("-");
	}
	public SQL DIVIDE(){
		return keyword("/");
	}
	
	
	
}
