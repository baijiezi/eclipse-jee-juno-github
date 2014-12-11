create or replace trigger trg_booking_order
  before insert on frontend_booking_order
  for each row
declare
  -- local variables here
begin
  SELECT seq_booking_order.nextval INTO :new.id FROM dual;
end trg_booking_order;
