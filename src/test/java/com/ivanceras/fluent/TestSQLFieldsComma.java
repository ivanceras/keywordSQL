package com.ivanceras.fluent;

import org.junit.Test;

import static com.ivanceras.keyword.sql.SQLStatics.*;

public class TestSQLFieldsComma {
	
	@Test
	public void testFieldsAutoComma() {
		String expected = "" +
				" SELECT Customers.CustomerName , Orders.OrderID "+
				" FROM Customers ";
		
		String actual = SELECT()
					.FIELD("Customers.CustomerName")
					.FIELD("Orders.OrderID")
					.FROM("Customers").build().getSql();
		
		CTest.cassertEquals(expected, actual);
	}
	
	
	@Test
	public void testFieldsAutoCommaGroupBy() {
		String expected = "" +
				" SELECT Customers.CustomerName , Orders.OrderID "+
				" FROM Customers "
				+ " GROUP BY CustomerName , Date";
		
		String actual = SELECT()
					.FIELD("Customers.CustomerName")
					.FIELD("Orders.OrderID")
					.FROM("Customers")
					.GROUP_BY("CustomerName", "Date")
					.build().getSql();
		
		CTest.cassertEquals(expected, actual);
	}

}
