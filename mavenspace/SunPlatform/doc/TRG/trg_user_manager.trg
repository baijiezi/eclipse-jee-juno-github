create or replace trigger trg_user_manager
  before insert on frontend_user_manager
  for each row
declare
  -- local variables here
begin
  SELECT seq_user_manager.nextval INTO :new.id FROM dual;
end trg_user_manager;
