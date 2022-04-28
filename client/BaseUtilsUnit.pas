{
���� ��� ����� �������� ������ ��������� � ������� ��� ������ � �������
}
unit BaseUtilsUnit;

interface

uses SysUtils, StdCtrls, Classes, EnergyproController, AdvObj, AdvGrid, Generics.Collections
, DMReportsUnit, ENConsts, Math;

{��������� �� ������� ��� ������������� �����}
 type
  StringTransformator = reference to function(param: String): String;

type
  BaseUtils = class
  public
    {��������� ������ ��������� ���������� � ������ ����������� divider}
    class function array2String(arr : ArrayOfString; divider : String): String; stdcall; static;
  	{��������� ������ ����� ����� � ������ ����������� divider}
  class function arrayOfIntegers2String(arr : array of Integer; divider : String): String; stdcall; static;
	{��������� ���� ����� ����� � ������ ����������� divider}
	class function listOfIntegers2String(arr : TList<Integer>; divider : String): String; stdcall; static;
        {��������� ���� ��������� ���������� � ������ ����������� divider}
    class function stringList2String(arr : TList<String>; divider : String): String; overload; stdcall; static;
	class function stringList2String(arr : TStringList; divider : String): String; overload; stdcall; static;
    {�������� ������ ����� �������� �������� ������� ��� ������������� �����}
    class function transformArrayOfStrings(arr : ArrayOfString; transformator : StringTransformator): ArrayOfString; overload; stdcall; static;
    class function transformArrayOfStrings(arr : TStringList; transformator : StringTransformator): ArrayOfString; overload; stdcall; static;
    {�������� ������ �����: ���������� ��������� ������� � ������� ��������}
    class function transformArrayOfStringsMakeSingleQuotes(arr : ArrayOfString): ArrayOfString; stdcall; static;
    {�������� ���� �����: ���������� ��������� ������� � ������� ��������}
    class function transformStringListMakeSingleQuotes(arr : TStringList): ArrayOfString; stdcall; static;
    {������������ ������ � ������� "� ��� n ���������"

      ���������� ������������  ����� ��������� list ����������� ������������ divider.
      ������, ���� ���-�� ��������� � ����� ��������� �������� allowedQuantity, ��
      � �������������� ������ ������������ �������������� "� ���" � ���-�� ���������
      ����� ������� �� ������ � ��� �������������� ������.
      ���� �� ���-�� � ����� �� ��������� ��������� �����, �� ������������ ������
      ������������.
    }
    class function getAndAlsoString(list : TStringList; allowedQuantity : Integer; divider : string; stringIfOne : String; stringIfMany : String) : String; static;
    {�������� ���� �������� ����� ����� grid,
    � ������� ���������� ������ � ������� columnIndex}
    class function getCheckedIndexes(grid : TAdvStringGrid; columnIndex : Integer) : TList<Integer>; static;
    {�������� ����� ����� grid �� �������� �� ����� list}
    class procedure removeRowsInGridFromList(grid : TAdvStringGrid; list : TList<Integer>); static;

  end;


implementation

class function BaseUtils.arrayOfIntegers2String(arr : array of Integer; divider : String): String; stdcall;
var
  res : String;
  item : Integer;
begin
    res := '';
    for item in arr do begin
      if Length(res) > 0 then begin
        res := res + divider + IntToStr(item);
      end else begin
        res := IntToStr(item);
      end;
    end;
    Result := res;
end;

class function BaseUtils.listOfIntegers2String(arr : TList<Integer>; divider : String): String; stdcall;
var
  res : String;
  item : Integer;
begin
  if arr = nil then begin
    Result := ''
  end else begin
    res := '';
    for item in arr do begin
      if Length(res) > 0 then begin
        res := res + divider + IntToStr(item);
      end else begin
        res := IntToStr(item);
      end;
    end;
    Result := res;
  end;
end;

class function BaseUtils.array2String(arr : ArrayOfString; divider : String): String;
var
  res, item : String;
begin
  if arr = nil then begin
    Result := ''
  end else begin
    res := '';
    for item in arr do begin
      if Length(res) > 0 then begin
        res := res + divider + item;
      end else begin
        res := item;
      end;
    end;
    Result := res;
  end;
end;

class function BaseUtils.stringList2String(arr : TList<String>; divider : String): String;
var
  res, item : String;
begin
  if arr = nil then begin
    Result := ''
  end else begin
    res := '';
    for item in arr do begin
      if Length(res) > 0 then begin
        res := res + divider + item;
      end else begin
        res := item;
      end;
    end;
    Result := res;
  end;
end;

class function BaseUtils.stringList2String(arr : TStringList; divider : String): String;
var
  res, item : String;
begin
  if arr = nil then begin
    Result := ''
  end else begin
    res := '';
    for item in arr do begin
      if Length(res) > 0 then begin
        res := res + divider + item;
      end else begin
        res := item;
      end;
    end;
    Result := res;
  end;
end;

class function BaseUtils.transformArrayOfStrings(arr : ArrayOfString; transformator : StringTransformator): ArrayOfString;
var
  item : String;
  outputArray : ArrayOfString;
  i : Integer;
begin
  if arr = nil then begin
    Result := nil;
  end else begin
    i := 0;
    SetLength(outputArray, Length(arr));
    for item in arr do begin
      outputArray[i] := transformator(item);
      i := i + 1;
    end;
    Result := outputArray;
  end;
end;

class function BaseUtils.transformArrayOfStrings(arr : TStringList; transformator : StringTransformator): ArrayOfString;
var
  item : String;
  outputArray : ArrayOfString;
  i : Integer;
begin
  if arr = nil then begin
    Result := nil;
  end else begin
    i := 0;
    SetLength(outputArray, arr.Count);
    for item in arr do begin
      outputArray[i] := transformator(item);
      i := i + 1;
    end;
    Result := outputArray;
  end;
end;

class function BaseUtils.transformArrayOfStringsMakeSingleQuotes(arr : ArrayOfString): ArrayOfString;
begin
  Result := BaseUtils.transformArrayOfStrings(arr, function(item: String): String
begin
  Result := '''' + item + '''';
end)
end;

class function BaseUtils.transformStringListMakeSingleQuotes(arr : TStringList): ArrayOfString;
begin
  Result := BaseUtils.transformArrayOfStrings(arr, function(item: String): String
begin
  Result := '''' + item + '''';
end)
end;

class function BaseUtils.getAndAlsoString(list : TStringList; allowedQuantity : Integer; divider : String; stringIfOne : String; stringIfMany : String) : string;
var strOut, item : string;
count, remained : Integer;
begin
	strOut := '';
	count := 0;
	if allowedQuantity <= 0 then begin
		raise Exception.Create('allowedQuantity ������� ���� ����� 0');
	end;

	if((list <> nil) And (list.Count > 0)) then begin
		for item in list do begin
			count := count + 1;
			if(count > allowedQuantity) then begin
				Break;
			end;
			if Length(strOut) > 0 then strOut := strOut + divider;
			strOut := strOut + item;
		end;
		remained := list.Count - allowedQuantity;
    if remained > 0 then begin
      strOut := strOut + ' �� �� ' + IntToStr(remained);
      if(remained = 1) then begin
        strOut := strOut + ' ' + stringIfOne;
      end else begin
        strOut := strOut + ' ' + stringIfMany;
      end;
    end;


	end else begin
		Result := strOut;
	end;
  Result := strOut;
end;

class function BaseUtils.getCheckedIndexes(grid : TAdvStringGrid; columnIndex : Integer) : TList<Integer>;
var
arrTemp : TList<Integer>;
i : Integer;
state : Boolean;
begin
  arrTemp := TList<Integer>.Create;
	state := false;
	for i := 1 to grid.RowCount - 1 do begin
		grid.GetCheckBoxState(1,i,state);
		if state then begin
			arrTemp.Add(i);
		end;
	end;
  Result := arrTemp;
end;

class procedure BaseUtils.removeRowsInGridFromList(grid : TAdvStringGrid; list : TList<Integer>);
var
tempList : TList<Integer>;
item : Integer;
begin
  if not Assigned(list) then Exit;
  tempList := TList<Integer>.Create(list);
  /// ������� ���� �����������
  tempList.Sort;
  // ����� "����������������", ����� ��������� �� ������� ������� ��������� �������
  tempList.Reverse;
  for item in tempList do begin
    grid.RemoveRows(item, 1);
  end;
end;

end.
