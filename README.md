keywordSQL
==========
I list down all the keywords of Major Database vendors and convert then into methods in a class.
Now you can call these methods as though you are writing a series of strings in a Stringbuilder.

This is like and extension of StringBuilder which you use to form string, sql strings specifically when you are writing SQL queries for your applications.


#How much complexity of SQL statements I can write?
* As complex as you want it to get.

Example: List down products that is sold by somebody that is within 5 k.m from me.

```sql

		double latitude = 10.0000d;
		double longitude = 123.000d;
		double km = 5.0d;
		double earthRadius = 6371.0d;

		SQL sql = WITH("distanceSQL", SELECT()
				.ACOS(SIN(RADIANS(VALUE( latitude ))).MULTIPLY().SIN(RADIANS(FIELD( address.latitude ))).PLUS()
						.COS(RADIANS(VALUE( latitude ))).MULTIPLY().COS(RADIANS(FIELD( address.latitude ))).MINUS()
						.COS(RADIANS(FIELD( address.longitude )).MINUS().RADIANS(VALUE( longitude )))
						).MULTIPLY().VALUE(earthRadius)
						.AS("computed_distance")
						.FIELD("address.address_id")
						.FROM("address")
				)
				.WITH("withinKm", SELECT("computed_distance", "address_id")
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
		System.out.println(bk.getSql()+"\n"+Arrays.asList(bk.getParameters()));
```

#Supported SQL keywords

* postgresql
* sqlite
* oracle
* mysql
* mariadb


