create or replace trigger trg_hospital
  before insert on frontend_hospital
  for each row
declare
  -- local variables here
begin
  SELECT seq_hospital.nextval INTO :new.id FROM dual;
end trg_hospital;
