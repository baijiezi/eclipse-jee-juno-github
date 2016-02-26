create or replace trigger trg_refund
  before insert on frontend_refund
  for each row
declare
  -- local variables here
begin
  SELECT seq_refund.nextval INTO :new.id FROM dual;
end trg_refund;
