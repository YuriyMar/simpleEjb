CREATE OR REPLACE FUNCTION net.get_standard_const_entry_on_date (
  pstandard_const_code double precision,
  pdate date
)
RETURNS numeric AS
$body$
declare
result numeric;
r record;
i numeric;
begin

if pstandard_const_code is null then
	raise exception 'pstandard_const_code can''not be null';
end if;

if pdate is null then
	raise exception 'pdate can''not be null';
end if;

i := 0;

for r in (select * from enstandardconstentry as entry where entry.constrefcode = pstandard_const_code and
pdate between entry.startdate and coalesce(entry.enddate, to_date('31.12.9999', 'dd.mm.yyyy')))
loop
	i := i + 1;
	if i >= 2 then
		raise exception 'more than one entry found for enstandardconst with code = % and on date %'
        , pstandard_const_code, to_char(pdate, 'dd.mm.yyyy');
	end if;
	result := r.constentry;
end loop;

return result;
end;
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER
COST 100;

