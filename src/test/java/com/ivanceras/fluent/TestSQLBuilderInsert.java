package com.ivanceras.fluent;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ivanceras.keyword.sql.Breakdown;

import static com.ivanceras.keyword.sql.SQLStatics.*;

public class TestSQLBuilderInsert {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String expected = "INSERT INTO films ( SELECT * FROM tmp_films WHERE date_prod < ?  )";
		Breakdown actual = INSERT().INTO("films").FIELD(SELECT("*").FROM("tmp_films").WHERE("date_prod").LESS_THAN("2004-05007")).build();
		CTest.cassertEquals(expected, actual.getSql());
		assertArrayEquals(new Object[]{"2004-05007"}, actual.getParameters());
	}

}
