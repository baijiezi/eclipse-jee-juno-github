create or replace trigger trg_dept
  before insert on frontend_dept
  for each row
declare
  -- local variables here
begin
  SELECT seq_dept.nextval INTO :new.id FROM dual;
end trg_dept;
