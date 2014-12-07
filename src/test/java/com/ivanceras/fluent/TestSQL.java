package com.ivanceras.fluent;


import com.ivanceras.keyword.sql.Breakdown;
import com.ivanceras.keyword.sql.SQL;

import static com.ivanceras.keyword.sql.SQLStatics.*;
public class TestSQL {
	
	public static void main(String[] args) {
		SQL sql = WITH().RECURSIVE().SELECT().FIELD("address");
		
		Breakdown bk = sql.build();
		System.out.println(bk.getSql());
	}

}
