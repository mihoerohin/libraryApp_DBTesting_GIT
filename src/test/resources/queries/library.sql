select count(*) from book_borrow
where is_returned = 0;

select name from book_categories;

select * from books
where name = 'Clean Code';

select count(*) from users;

select count(*) from book_borrow
                where is_returned=0;


select bc.name,count(*) from book_borrow bb
                                 inner join books b on bb.book_id = b.id
                                 inner join book_categories bc on b.book_category_id=bc.id
group by name
order by 2 desc;


select id,name,author from books
where name = 'Clean Code B26B6' and author='Robert C.Martin'
order by id desc;


select full_name,b.name,bb.borrowed_date from users u
                                                  inner join book_borrow bb on u.id = bb.user_id
                                                  inner join books b on bb.book_id = b.id
where full_name='Test Student 55' and name='Head First Java'
order by 3 desc;

select * from books;





