CREATE OR REPLACE FUNCTION net.get_productionexpencespercent (
  codeact numeric
)
RETURNS numeric AS
$body$
DECLARE    
  productionexpencespercent numeric;
  actDate date;
  operativeDate date;
  minDate date;
  
BEGIN

select 
ac_super.dateact
, (select
	distinct oo.contractdate 
    from enoperativeobject as oo
	where oo.elementcode = ac_super.code)
into actDate, operativeDate 
from
enact as ac_super where code = codeact;

minDate = actDate;
if operativeDate is not null and operativeDate < minDate then 
	minDate = operativeDate;
end if;

select get_standard_const_entry_on_date(5, minDate)
into productionexpencespercent; 
  
return productionexpencespercent;

END;
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER
COST 100;
