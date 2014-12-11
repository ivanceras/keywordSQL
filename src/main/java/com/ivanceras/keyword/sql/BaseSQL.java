package com.ivanceras.keyword.sql;

import java.util.LinkedList;
import java.util.List;

import static com.ivanceras.keyword.sql.BaseSQL.Type.*;

public abstract class BaseSQL {

	protected List<Object> keywords = new LinkedList<Object>();

	Type lastCall = null;

	boolean smartMode = true;//if on smart mode, adds commas, and parenthesis automatically if possible.

	public enum Type{
		FIELD,
		FUNCTION,
		KEYWORD,
		VALUE,
		TABLE,
		OPEN_PAREN,
		CLOSE_PAREN,
	}

	int tabs = 0;


	protected List<Object> values = new LinkedList<Object>();
	
	public SQL append(SQL sql){
		keywords.add(sql);
		lastCall = KEYWORD;
		return (SQL) this;
	}
	
	public SQL keyword(String keyword){
		keywords.add(keyword);
		lastCall = KEYWORD; 
		return (SQL) this;
	}
	
	public SQL chars(String keyword){
		keywords.add(keyword);
		return (SQL) this;
	}
	
	public SQL comma(){
		return chars(",");
	}
	
	protected SQL smartCommaFnField(){
		if(smartMode && lastCall != null && (lastCall.equals(FIELD) || lastCall.equals(FUNCTION))){
			comma();
		}
		return (SQL) this;
	}
	public SQL tab(){
		tabs++;
		for(int i = 0; i < tabs; i++){
			keyword("\t");
		}
		return (SQL) this;
	}
	
	protected SQL table(String tbl) {
		if(smartMode && lastCall != null && lastCall.equals(TABLE)){
			comma();
		}
		keywords.add(tbl);
		lastCall = TABLE;
		return (SQL) this;
	}
	
	public SQL ln(){
		return chars("\n");
	}
	
	public Breakdown build(Breakdown bk, SQL passed){
		List<Object> passedKeywords = passed.keywords;
		List<Object> passedValues = passed.values;
		for(Object keyword : passedKeywords){
			if(keyword != null){
				Class<?> keyClass = keyword.getClass();
				if(keyClass.equals(String.class)){
					bk.appendSp((String)keyword);
				}
				else if(keyClass.equals(SQL.class)){
					build(bk, (SQL)keyword);
				}
			}
		}
		for(Object value : passedValues){
			bk.addParameter(value);
		}
		return bk;
	}
	
	protected SQL field(SQL sql){
		keywords.add(sql);
		return (SQL) this;
	}

	public SQL openParen(){
		lastCall =OPEN_PAREN;
		return chars("(");
	}
	public SQL closeParen(){
		lastCall = CLOSE_PAREN;
		return chars(")");
	}
	
}
