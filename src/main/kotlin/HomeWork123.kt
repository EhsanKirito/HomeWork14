//HW1
CREATE TABLE company (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(255),
department VARCHAR(255),
salary INT
);

INSERT INTO company (name, department, salary) VALUES ('ali','Finance',1000) ;
INSERT INTO company (name, department, salary) VALUES ('mamad','Finance',1001) ;
INSERT INTO company (name, department, salary) VALUES ('ali','Finance',1002) ;
INSERT INTO company (name, department, salary) VALUES ('saeed','IT',1003) ;
INSERT INTO company (name, department, salary) VALUES ('behnam','IT',1004) ;
INSERT INTO company (name, department, salary) VALUES ('ali','IT',1005) ;
INSERT INTO company (name, department, salary) VALUES ('jafar','HR',1006) ;
INSERT INTO company (name, department, salary) VALUES ('hadi','HR',900) ;
INSERT INTO company (name, department, salary) VALUES ('mahdi','HR',800) ;
INSERT INTO company (name, department, salary) VALUES ('hamed','HR',800) ;
INSERT INTO company (name, department, salary) VALUES ('mohsen','Research',900) ;
INSERT INTO company (name, department, salary) VALUES ('amir','Research',500) ;
INSERT INTO company (name, department, salary) VALUES ('hosein','Research',1090) ;
INSERT INTO company (name, department, salary) VALUES ('hasan','Research',1800) ;
INSERT INTO company (name, department, salary) VALUES ('ehsan','Boss',55000) ;

SELECT * FROM company WHERE name = 'ali';
SELECT * FROM company WHERE salary<1000;
SELECT AVG(salary) FROM company GROUP BY department;
//====================================================================================
//HW2
CREATE DATABASE university;

CREATE TABLE student (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(255),
department VARCHAR(255),
age INT
);

INSERT INTO student (name, department, age) VALUES ('ali','IT',22);
INSERT INTO student (name, department, age) VALUES ('ehsan','IT',23);
INSERT INTO student (name, department, age) VALUES ('behnam','IT',21);
INSERT INTO student (name, department, age) VALUES ('surush','IT',20);
INSERT INTO student (name, department, age) VALUES ('jafar','IT',20);
INSERT INTO student (name, department, age) VALUES ('ali','Mechanic',25);
INSERT INTO student (name, department, age) VALUES ('saeed','Mechanic',24);
INSERT INTO student (name, department, age) VALUES ('sina','Mechanic',28);
INSERT INTO student (name, department, age) VALUES ('hashem','Mechanic',24);
INSERT INTO student (name, department, age) VALUES ('keyvan','Mechanic',29);
INSERT INTO student (name, department, age) VALUES ('masoud','Civil',20);
INSERT INTO student (name, department, age) VALUES ('siavash','Civil',19);
INSERT INTO student (name, department, age) VALUES ('reza','Civil',18);
INSERT INTO student (name, department, age) VALUES ('mehrad','Civil',16);
INSERT INTO student (name, department, age) VALUES ('yunes','Civil',10);
INSERT INTO student (name, department, age) VALUES ('mohammad','Electric',25);
INSERT INTO student (name, department, age) VALUES ('fereydun','Electric',24);
INSERT INTO student (name, department, age) VALUES ('mahdi','Electric',22);
INSERT INTO student (name, department, age) VALUES ('hadi','Electric',22);
INSERT INTO student (name, department, age) VALUES ('mohsen','Electric',21);

SELECT name FROM student ORDER BY name;
SELECT name FROM student ORDER BY name LIMIT 3;
SELECT AVG(age) FROM student;
SELECT COUNT(name) FROM student WHERE age>20;
SELECT * FROM student WHERE name LIKE '%ali%';
SELECT COUNT(department) FROM student WHERE department = 'Mechanic';
SELECT COUNT(department) FROM student GROUP BY department;

//====================================================================================
//HW3
SELECT * FROM albums;
SELECT * FROM customers ORDER BY(LastName);
SELECT * FROM tracks ORDER BY (Bytes) DESC LIMIT 5;
SELECT COUNT(TrackId) FROM tracks;
SELECT COUNT(TrackId) FROM tracks WHERE Milliseconds>3000000;
SELECT * FROM tracks ORDER BY Milliseconds DESC;
SELECT * FROM customers WHERE City = 'Prague';
SELECT * FROM customers WHERE Company NOTNULL;
SELECT * FROM customers WHERE NOT Country  = 'India';

SELECT albums.AlbumId, albums.Title, COUNT(artists.Name)
FROM albums INNER JOIN artists ON albums.ArtistId = artists.ArtistId
GROUP BY Name ORDER BY COUNT(artists.Name) DESC;

SELECT * FROM customers WHERE Email LIKE '%@gmail.com%';
SELECT * FROM tracks GROUP BY UnitPrice;
SELECT * FROM tracks WHERE AlbumId = (SELECT AlbumId FROM albums WHERE Title = 'Big Ones');

SELECT tracks.TrackId, tracks.Name, albums.Title
FROM tracks INNER JOIN albums ON tracks.AlbumId = albums.AlbumId WHERE albums.Title = 'Big Ones'

SELECT tracks.TrackId, tracks.Name, genres.Name
FROM tracks INNER JOIN genres ON tracks.GenreId = genres.GenreId WHERE genres.Name = 'Rock';

SELECT tracks.TrackId, tracks.Name, genres.Name
FROM tracks INNER JOIN genres ON tracks.GenreId = genres.GenreId WHERE genres.Name = 'Jazz'
AND tracks.MediaTypeId = (SELECT MediaTypeId FROM media_types WHERE Name = 'AAC audio file');

SELECT * FROM tracks
INNER JOIN playlist_track ON tracks.TrackId = playlist_track.TrackId
INNER JOIN playlists ON playlist_track.PlaylistId = playlists.PlaylistId WHERE playlists.Name = '90’s Music';

SELECT genres.Name, count(genres.Name) AS genres_count FROM genres
INNER JOIN tracks ON genres.GenreId = tracks.GenreId
INNER JOIN invoice_items ON tracks.TrackId = invoice_items.TrackId
INNER JOIN invoices ON invoice_items.InvoiceId = invoices.InvoiceId
INNER JOIN customers ON invoices.CustomerId = customers.CustomerId
GROUP BY genres.Name ORDER BY genres_count DESC limit 1;

SELECT genres.Name, count(genres.Name) FROM genres
INNER JOIN tracks ON genres.GenreId = tracks.GenreId
GROUP BY genres.Name;

SELECT Name, count(invoice_items.Quantity) AS tracsCount FROM tracks
INNER JOIN invoice_items ON tracks.TrackId = invoice_items.TrackId
GROUP BY tracks.Name;

SELECT tracks.Name, tracks.Composer, tracks.Bytes, tracks.Milliseconds FROM tracks
INNER JOIN invoice_items ON tracks.TrackId = invoice_items.TrackId
GROUP BY tracks.Name ORDER BY count(invoice_items.Quantity) DESC limit 10;

SELECT artists.Name, count(invoice_items.Quantity) * tracks.UnitPrice AS sell FROM tracks
INNER JOIN invoice_items ON tracks.TrackId = invoice_items.TrackId
INNER JOIN albums ON tracks.AlbumId = albums.AlbumId
INNER JOIN artists ON albums.ArtistId = artists.ArtistId
GROUP BY artists.Name ORDER BY sell DESC LIMIT 1;
//====================================================================================
//HomeWork 4 and 5 has been added to resources folder as images