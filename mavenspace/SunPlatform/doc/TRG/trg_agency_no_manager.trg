create or replace trigger trg_agency_no_manager
  before insert on frontend_agency_no_manager  
  for each row
declare
  -- local variables here
begin
  SELECT seq_agency_no_manager.nextval INTO :new.id FROM dual;
end trg_agency_no_manager;
/
