create or replace trigger trg_terminal_manager
  before insert on frontend_terminal_manager
  for each row
declare
  -- local variables here
begin
  SELECT seq_terminal_manager.nextval INTO :new.id FROM dual;
end trg_terminal_manager;
