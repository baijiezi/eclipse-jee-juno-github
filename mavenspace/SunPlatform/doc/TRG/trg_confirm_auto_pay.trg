create or replace trigger trg_confirm_auto_pay
  before insert on frontend_confirm_auto_pay
  for each row
declare
  -- local variables here
begin
  SELECT seq_confirm_auto_pay.nextval INTO :new.id FROM dual;
end trg_confirm_auto_pay;
