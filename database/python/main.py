import pymysql


db = pymysql.connect("localhost","root","123456","test" )
cursor = db.cursor()
cursor.execute("drop table if exists EMPLOYEE")
sql = """CREATE TABLE EMPLOYEE (
         name  CHAR(20) NOT NULL,
         age INT,  
         sex CHAR(1))"""
cursor.execute(sql)
db.commit()
data=[{'name':'\'mac\'','age':10,'sex':'\'m\''},{'name':'\'jack\'','age':12,'sex':'\'f\''}]
sql = """INSERT INTO EMPLOYEE(name,age,sex)
         VALUES ({0},{1},{2})"""
for d in data:
    cursor.execute(sql.format(d['name'],d['age'],d['sex']))
    db.commit()
sql = "select * from EMPLOYEE"
cursor.execute(sql)
result = cursor.fetchall()
for row in result:
    print(row)
db.close()
