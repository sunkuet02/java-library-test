
import MySQLdb

dbName = "listitems"
tableName = "items"

db = MySQLdb.connect("localhost","root","root",dbName )
cursor = db.cursor()

createTableScript = "(id int NOT NULL AUTO_INCREMENT," \
                    "name varchar(50) not null," \
                    "price decimal(12,2) not null," \
                    "description varchar(100)," \
                    "primary key(id));"

cursor.execute("drop table IF EXISTS " + tableName)
cursor.execute("create table " + tableName + createTableScript)

#insert data
cursor.execute("insert into "  + tableName + " (name,price) values('vat',100.20)")
cursor.execute("insert into " + tableName + " (name,price) values('dal',50.21)")

db.commit()
db.close()

