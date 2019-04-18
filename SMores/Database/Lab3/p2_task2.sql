#Task2: error version
/*Delimiter $$
create procedure add_order(IN onum int, IN cnum int, IN receive date)
begin
    DECLARE employee_name varchar(255);
    if (receive is null)
        insert into ORDERS values (onum, cnum, enum, CURDATE(), null);
    else then
        insert into ORDERS values (onum, cnum, enum, receive, null);
    end if;
end;
$$*/

Delimiter $$
create procedure add_order(IN onum int, IN cnum int, IN enum int, IN receive date, IN shipped date)
begin
    DECLARE employee_name varchar(255);
    if (receive is null) THEN
        insert into ORDERS values (onum, cnum, enum, CURDATE(), null);
    else
        insert into ORDERS values (onum, cnum, enum, receive, null);
    end if;
end;
$$

CALL add_order(666, 1, 3, null, null);

SELECT *
FROM ORDERS;

Delimiter $$
create procedure ship_order(IN onum int, IN ship date)
begin
    if (ship is null) THEN
        UPDATE ORDERS SET shipped = CURDATE() WHERE ono = onum;
    else
        UPDATE ORDERS SET shipped = ship WHERE ono = onum;
    end if;
end;
$$

CALL ship_order(438, null);

SELECT *
FROM ORDERS;

SET GLOBAL log_bin_trust_function_creators = 1;

Delimiter ||
CREATE FUNCTION cancel_order(onum INT) RETURNS varchar(255)
begin
    #DELETE FROM ORDERS WHERE ono = onum;
    DECLARE message varchar(255);
    IF not exists((SELECT ono FROM ORDERS where ono = onum)) THEN #no row in ORDERS
        SET message = 'Order Details Do Not Exist!  Order Does Not Exist!';
        return message;
    ELSEIF not exists((SELECT ono FROM ODETAILS where ono = onum)) THEN #no row in ODETAILS
        DELETE FROM ORDERS WHERE ono = onum;
        SET message = 'Order Details Do Not Exist!  Order Is Cancelled Successfully!';
        return message;
    ELSE
        DELETE FROM ODETAILS WHERE ono = onum;
        DELETE FROM ORDERS WHERE ono = onum;
        SET message = 'Order Details Removed Successfully!  Order Is Cancelled Successfully!';
        return message;
    END IF;
end;
||

SET @x = cancel_order(315);
SELECT @x;

SET @x = cancel_order(666);
SELECT @x;

SELECT *
FROM ORDERS;

SELECT *
FROM ODETAILS;

Delimiter ||
CREATE procedure add_order_details(IN onum int, IN pnum int, IN qty int)
begin
    SELECT qoh INTO @quantity FROM PARTS WHERE pno = pnum;
    IF qty > @quantity THEN
        INSERT into ORDERS_ERRORS VALUES (CURDATE(), onum, 'Do not have enough quantity to sell!');
        CALL cancel_order(onum);
    ELSE
        UPDATE PARTS SET qoh = qoh - qty WHERE pno = pnum;
        INSERT into ODETAILS VALUES (onum, pnum, qty);
        IF (SELECT qoh FROM PARTS WHERE pno = pnum) < (SELECT olevel FROM PARTS where pno = pnum) THEN
            INSERT into RESTOCK VALUES (CURDATE(), pnum);
            UPDATE PARTS set qoh = olevel * 2 where pno = pnum;
        end if;
    end if;
end;
||
