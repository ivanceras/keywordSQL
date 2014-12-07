package com.ivanceras.fluent;


import com.ivanceras.fluent.Breakdown;
import com.ivanceras.fluent.SQL;

import static com.ivanceras.fluent.SQLStatics.*;
public class TestSQL {
	
	public static void main(String[] args) {
		SQL sql = WITH().RECURSIVE().SELECT().FIELD("address");
		
		Breakdown bk = sql.build();
		System.out.println(bk.getSql());
	}

}
