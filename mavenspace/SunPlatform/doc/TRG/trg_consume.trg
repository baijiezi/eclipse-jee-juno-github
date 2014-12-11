create or replace trigger trg_consume
  before insert on frontend_consume
  for each row
declare
  -- local variables here
begin
  SELECT seq_consume.nextval INTO :new.id FROM dual;
end trg_consume;
