keywordSQL
==========

[![Build Status](https://api.travis-ci.org/ivanceras/keywordSQL.svg)](https://travis-ci.org/ivanceras/keywordSQL)

I list down all the keywords of Major Database vendors and convert then into methods in a class.
Now you can call these methods as though you are writing a series of texts in a Stringbuilder.


#How much complexity of SQL statements I can write?
* As complex as you want it to get.

Example: List down products that is sold by somebody that is within 5 k.m from me.

```java

	@Test
	public void test1(){
		double myLatitude = 10.0000d;
		double myLongitude = 123.000d;
		double km = 5.0d;
		double earthRadius = 6371.0d;

		SQL sql = WITH("distanceSQL", SELECT()
				.ACOS(
						SIN(RADIANS(VALUE( myLatitude )))
						.MULTIPLY().SIN(RADIANS(FIELD( "latitude" )))
						.PLUS().COS(RADIANS(VALUE( myLatitude )))
						.MULTIPLY().COS(RADIANS(FIELD( "latitude" ))).MULTIPLY()
						.COS(RADIANS(FIELD( "longitude" ))
								.MINUS().RADIANS(VALUE( myLongitude )))
						).MULTIPLY().VALUE(earthRadius)
						.AS("computed_distance")
						.FIELD("address.address_id")
						.FROM("address")

						,"withinKm", SELECT("computed_distance", "address_id")
						.FROM("distanceSQL")
						.WHERE("computed_distance").LESS_THAN(km)
				)
				.SELECT("*")
				.FIELD("computed_distance")
				.FROM("distanceSQL") 
				.INNER_JOIN("user_info")
				.ON("distanceSQL.address_id", "user_info.address_id")
				.INNER_JOIN("users")
				.ON("user_info.user_id", "users.user_id")
				.INNER_JOIN("product")
				.ON("users.user_id", "product.owner_id");

		Breakdown bk  = sql.build();
		System.out.println("SQL: "+bk.getSql());
		System.out.println("\nparameters: "+Arrays.asList(bk.getParameters()));
	}
```
The output

```sql

SQL:
WITH distanceSQL AS ( 
	SELECT ACOS ( SIN ( RADIANS ( ? ) ) 
	 * SIN ( RADIANS ( latitude ) ) 
	 + COS ( RADIANS ( ? ) ) 
	 * COS ( RADIANS ( latitude ) ) 
	 * COS ( RADIANS ( longitude ) 
	 - RADIANS ( ? ) ) ) 
	 * ? AS computed_distance , address.address_id 
	 FROM address 
  ) 
,withinKm AS 
 ( 
 SELECT computed_distance , address_id 
 	FROM distanceSQL WHERE computed_distance < ? 
 ) 
 SELECT * , computed_distance 
 FROM distanceSQL 
 INNER JOIN user_info 
 	ON distanceSQL.address_id = user_info.address_id 
 INNER JOIN users 
 	ON user_info.user_id = users.user_id 
 INNER JOIN product 
 	ON users.user_id = product.owner_id 

parameters: [10.0, 10.0, 123.0, 6371.0, 5.0]

```


#Advantages
* No SQL typo anymore, since IDE checks it for you.
* SQL injection resistance while writing the values as it where supposed to be.


#Supported SQL keywords

* postgresql
* sqlite
* oracle
* mysql
* mariadb

# Roadmap

* thorough support for functions
* Make a lightweight version of Table/Column generators from ivanceras ORM, this way all text will be check by the IDE

