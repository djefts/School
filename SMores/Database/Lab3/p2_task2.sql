Task2: error version

Delimiter $$
create procedure add_order
    (IN onum int,
     IN cnum int,
     IN receive date) 
  begin
  DECLARE employee_name varchar(255);
    if (receive is null) 
      insert into ORDERS values (onum,cnum,enum,CURDATE(),null);
    else then
      insert into ORDERS values (onum,cnum,enum,receive,null);
  end if;
  end;
$$

