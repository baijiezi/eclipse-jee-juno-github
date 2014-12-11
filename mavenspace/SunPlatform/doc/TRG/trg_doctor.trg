create or replace trigger trg_doctor
  before insert on frontend_doctor
  for each row
declare
  -- local variables here
begin
  SELECT seq_doctor.nextval INTO :new.id FROM dual;
end trg_doctor;
