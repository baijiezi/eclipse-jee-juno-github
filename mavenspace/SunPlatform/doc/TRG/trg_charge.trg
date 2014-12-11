create or replace trigger trg_charge
  before insert on frontend_charge
  for each row
declare
  -- local variables here
begin
  SELECT seq_charge.nextval INTO :new.id FROM dual;
end trg_charge;
