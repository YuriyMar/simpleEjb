unit CNPack2PlanWorkController;

interface

uses InvokeRegistry, SOAPHTTPClient, Types, XSBuiltIns, EnergyProController, EnergyProController2 
   ,CNSubsystemTypeController 
   ,ENPlanWorkController
   //,CommonController
;

type

  // ************************************************************************ //
  // The following types, referred to in the WSDL document are not being represented
  // in this file. They are either aliases[@] of other types represented or were referred
  // to but never[!] declared in the document. The types from the latter category
  // typically map to predefined/known XML or Borland types; however, they could also 
  // indicate incorrect WSDL documents that failed to declare or import a schema type.
  // ************************************************************************ //
  // !:int             - "http://www.w3.org/2001/XMLSchema"
  // !:string          - "http://www.w3.org/2001/XMLSchema"
  // !:decimal         - "http://www.w3.org/2001/XMLSchema"
  // !:date            - "http://www.w3.org/2001/XMLSchema"
  // !:long            - "http://www.w3.org/2001/XMLSchema"
  // !:dateTime        - "http://www.w3.org/2001/XMLSchema"
  // !:EPCalculatorShortList - "http://ksoe.org/EnergyproControllerService/type/"
  // !:EPCalculatorFilter - "http://ksoe.org/EnergyproControllerService/type/"

  CNPack2PlanWork            = class;                 { "http://ksoe.org/EnergyproControllerService/type/" }



  // ************************************************************************ //
  // Namespace : http://ksoe.org/EnergyproControllerService/type/
  // ************************************************************************ //
  CNPack2PlanWorkRef = class(TRemotable)
  private
    Fcode: Integer;
  published
    property code: Integer read Fcode write Fcode;
  end;


  // ************************************************************************ //
  // Namespace : http://ksoe.org/EnergyproControllerService/type/
  // ************************************************************************ //
  CNPack2PlanWork = class(TRemotable)
  private
    Fcode : Integer; 
    FpackCode : Integer; 
    FcommentGen : WideString;
    FuserGen : WideString;
    FdateEdit : TXSDate;
    Fdomain_info : WideString;
    Fmodify_time : Int64;
//???
    FpackTypeRef : CNSubsystemTypeRef;
//???
    FplanRef : ENPlanWorkRef;
  public
    destructor Destroy; override;
  published
    property  code : Integer read Fcode write Fcode; 
    property  packCode : Integer read FpackCode write FpackCode; 
    property commentGen : WideString read FcommentGen write FcommentGen;
    property userGen : WideString read FuserGen write FuserGen;
    property dateEdit : TXSDate read FdateEdit write FdateEdit;
    property domain_info : WideString read Fdomain_info write Fdomain_info;
    property modify_time : Int64 read Fmodify_time write Fmodify_time; 
    property packTypeRef : CNSubsystemTypeRef read FpackTypeRef write FpackTypeRef; 
    property planRef : ENPlanWorkRef read FplanRef write FplanRef; 
  end;

  CNPack2PlanWorkFilter = class(TRemotable)
  private
    FconditionSQL: WideString;
    ForderBySQL: WideString;
    Fcode : Integer; 
    FpackCode : Integer; 
    FcommentGen : WideString;
    FuserGen : WideString;
    FdateEdit : TXSDate;
    Fdomain_info : WideString;
    Fmodify_time : Int64;
//???
    FpackTypeRef : CNSubsystemTypeRef;
//???
    FplanRef : ENPlanWorkRef;
  public
    destructor Destroy; override;
  published
    property conditionSQL: WideString read FconditionSQL write FconditionSQL;
    property orderBySQL: WideString read ForderBySQL write ForderBySQL;
    property  code : Integer read Fcode write Fcode; 
    property  packCode : Integer read FpackCode write FpackCode; 
    property commentGen : WideString read FcommentGen write FcommentGen;
    property userGen : WideString read FuserGen write FuserGen;
    property dateEdit : TXSDate read FdateEdit write FdateEdit;
    property domain_info : WideString read Fdomain_info write Fdomain_info;
    property modify_time : Int64 read Fmodify_time write Fmodify_time; 
    property packTypeRef : CNSubsystemTypeRef read FpackTypeRef write FpackTypeRef; 
    property planRef : ENPlanWorkRef read FplanRef write FplanRef; 
  end;


  CNPack2PlanWorkShort = class(TRemotable)
  private
    Fcode : Integer; 
    FpackCode : Integer; 
    FcommentGen : WideString;
    FuserGen : WideString;
    FdateEdit : TXSDate;
    FpackTypeRefCode : Integer; 
    FpackTypeRefName : WideString;
    FplanRefCode : Integer; 
    FplanRefDateGen : TXSDate;
    FplanRefDateStart : TXSDate;
    FplanRefDateFinal : TXSDate;
    FplanRefYearGen : Integer; 
    FplanRefMonthGen : Integer; 
    FplanRefUserGen : WideString;
    FplanRefDateEdit : TXSDate;
  public
    destructor Destroy; override;
  published
    property  code : Integer read Fcode write Fcode; 
    property  packCode : Integer read FpackCode write FpackCode; 
    property commentGen : WideString read FcommentGen write FcommentGen;
    property userGen : WideString read FuserGen write FuserGen;
    property dateEdit : TXSDate read FdateEdit write FdateEdit;

    property packTypeRefCode : Integer read FpackTypeRefCode write FpackTypeRefCode; 
    property packTypeRefName : WideString read FpackTypeRefName write FpackTypeRefName; 
    property planRefCode : Integer read FplanRefCode write FplanRefCode; 
    property planRefDateGen : TXSDate read FplanRefDateGen write FplanRefDateGen; 
    property planRefDateStart : TXSDate read FplanRefDateStart write FplanRefDateStart; 
    property planRefDateFinal : TXSDate read FplanRefDateFinal write FplanRefDateFinal; 
    property planRefYearGen : Integer read FplanRefYearGen write FplanRefYearGen; 
    property planRefMonthGen : Integer read FplanRefMonthGen write FplanRefMonthGen; 
    property planRefUserGen : WideString read FplanRefUserGen write FplanRefUserGen; 
    property planRefDateEdit : TXSDate read FplanRefDateEdit write FplanRefDateEdit; 
  end;

  ArrayOfCNPack2PlanWorkShort = array of CNPack2PlanWorkShort;  // { "http://ksoe.org/EnergyproControllerService/type/" }

  CNPack2PlanWorkShortList = class(TRemotable)
  private
    FtotalCount: Integer;
    Flist: ArrayOfCNPack2PlanWorkShort;
  public
  destructor Destroy; override;
  published
    property totalCount: Integer read FtotalCount write FtotalCount;
    property list: ArrayOfCNPack2PlanWorkShort read Flist write Flist;
  end;



  // ************************************************************************ //
  // Namespace : http://ksoe.org/CNPack2PlanWorkController/message/
  // soapAction: http://ksoe.org/CNPack2PlanWorkController/action/CNPack2PlanWorkController.%operationName%
  // transport : http://schemas.xmlsoap.org/soap/http
  // style     : rpc
  // binding   : CNPack2PlanWorkControllerSoapBinding
  // service   : EnergyproControllerService
  // port      : CNPack2PlanWorkController
  // URL       : http://soap.ksoe.com.ua/energypro
  // ************************************************************************ //


  CNPack2PlanWorkControllerSoapPort = interface(IInvokable)
  ['{6E1440AC-0A6E-4A68-9F36-C34857A6E3A4}']
    function  add(const aCNPack2PlanWork: CNPack2PlanWork): Integer; stdcall;
    procedure remove(const anObjectCode: Integer); stdcall;
    procedure save(const aCNPack2PlanWork: CNPack2PlanWork); stdcall;
    function  getObject(const anObjectCode: Integer): CNPack2PlanWork; stdcall;
    function  getList: CNPack2PlanWorkShortList; stdcall;
    function  getFilteredList(const aCNPack2PlanWorkFilter: CNPack2PlanWorkFilter): CNPack2PlanWorkShortList; stdcall;
    function  getScrollableList(const aFromPosition: Integer; const aQuantity: Integer): CNPack2PlanWorkShortList; stdcall;
    function  getScrollableFilteredList(const aCNPack2PlanWorkFilter: CNPack2PlanWorkFilter; const aFromPosition: Integer; const aQuantity: Integer): CNPack2PlanWorkShortList; stdcall;
    function  getScrollableListByCondition(const aCondition: WideString; const aFromPosition: Integer; const aQuantity: Integer): CNPack2PlanWorkShortList; stdcall;

    function  createPack(const aObject: CNPack2PlanWork): Integer; stdcall;    
  end; 


implementation

  destructor CNPack2PlanWork.Destroy;
  begin
    if Assigned(FdateEdit) then
      dateEdit.Free;
    if Assigned(FpackTypeRef) then
      packTypeRef.Free;
    if Assigned(FplanRef) then
      planRef.Free;
    inherited Destroy;
  end;
  
  destructor CNPack2PlanWorkFilter.Destroy;
  begin
    if Assigned(FdateEdit) then
      dateEdit.Free;
    if Assigned(FpackTypeRef) then
      packTypeRef.Free;
    if Assigned(FplanRef) then
      planRef.Free;
    inherited Destroy;
  end; 
  
  destructor CNPack2PlanWorkShort.Destroy;
  begin
    if Assigned(FdateEdit) then
      dateEdit.Free;
    if Assigned(FplanRefDateGen) then
      planRefDateGen.Free;
    if Assigned(FplanRefDateStart) then
      planRefDateStart.Free;
    if Assigned(FplanRefDateFinal) then
      planRefDateFinal.Free;
    if Assigned(FplanRefDateEdit) then
      planRefDateEdit.Free;
    inherited Destroy;
  end; 
  
  destructor CNPack2PlanWorkShortList.Destroy;
  var
    I: Integer;
  begin
    for I := 0 to Length(Flist)-1 do
     if Assigned(Flist[I]) then
       Flist[I].Free;
     SetLength(Flist, 0);
     inherited Destroy;
  end;



initialization

  RemClassRegistry.RegisterXSClass(CNPack2PlanWork, 'http://ksoe.org/EnergyproControllerService/type/', 'CNPack2PlanWork');
  RemClassRegistry.RegisterXSClass(CNPack2PlanWorkRef, 'http://ksoe.org/EnergyproControllerService/type/', 'CNPack2PlanWorkRef');
  RemClassRegistry.RegisterXSClass(CNPack2PlanWorkFilter, 'http://ksoe.org/EnergyproControllerService/type/', 'CNPack2PlanWorkFilter');
  RemClassRegistry.RegisterXSClass(CNPack2PlanWorkShort, 'http://ksoe.org/EnergyproControllerService/type/', 'CNPack2PlanWorkShort');
  RemClassRegistry.RegisterXSClass(CNPack2PlanWorkShortList, 'http://ksoe.org/EnergyproControllerService/type/', 'CNPack2PlanWorkShortList');
  RemClassRegistry.RegisterXSInfo(TypeInfo(ArrayOfCNPack2PlanWorkShort), 'http://ksoe.org/EnergyproControllerService/type/', 'ArrayOfCNPack2PlanWorkShort');

  InvRegistry.RegisterInterface(TypeInfo(CNPack2PlanWorkControllerSoapPort), 'http://ksoe.org/CNPack2PlanWorkController/message/', 'UTF-8');
  InvRegistry.RegisterDefaultSOAPAction(TypeInfo(CNPack2PlanWorkControllerSoapPort), 'http://ksoe.org/CNPack2PlanWorkController/action/CNPack2PlanWorkController.%operationName%');


end.
