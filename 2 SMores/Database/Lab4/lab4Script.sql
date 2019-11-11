CREATE USER 'lab4' @'localhost' IDENTIFIED BY 'lab4pass';
GRANT SELECT ON table EMPLOYEES TO 'lab4';
GRANT SELECT ON table PARTS TO 'lab4';

DELIMITER ||
CREATE procedure insertPart(IN pname varchar(255), IN qoh int, IN price double)
begin
    SELECT max(pno) into @pno from PARTS;
    INSERT into PARTS values (@pno + 1, pname, qoh, price, 20);
end;
||

GRANT execute on procedure insertPart to 'lab4';

REVOKE select on table PARTS from 'lab4';
REVOKE select on table EMPLOYEES from 'lab4';

DROP user 'lab4';


