unit CNMovementController;

interface

uses InvokeRegistry, SOAPHTTPClient, Types, XSBuiltIns, EnergyProController, EnergyProController2 
   ,CNSubsystemTypeController 
   ,CNPackController 
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

  CNMovement            = class;                 { "http://ksoe.org/EnergyproControllerService/type/" }



  // ************************************************************************ //
  // Namespace : http://ksoe.org/EnergyproControllerService/type/
  // ************************************************************************ //
  CNMovementRef = class(TRemotable)
  private
    Fcode: Integer;
  published
    property code: Integer read Fcode write Fcode;
  end;


  // ************************************************************************ //
  // Namespace : http://ksoe.org/EnergyproControllerService/type/
  // ************************************************************************ //
  CNMovement = class(TRemotable)
  private
    Fid : Integer; 
    Fid_state : Integer; 
    Fstartdate : TXSDate;
    Fnote : WideString;
    Fid_parent : Integer; 
    Fid_user : Integer; 
    Frealdate : TXSDate;
    Fcanceled : Integer; 
    Fid_user_canceled : Integer; 
    Fcanceleddate : TXSDate;
    Fcancelednote : WideString;
    Fis_completed : Integer; 
    Fid_movement_status : Integer; 
    Faddnote : WideString;
    Fread_status : Integer; 
    Fid_user_read : Integer; 
    Fread_date : TXSDate;
    Fid_user_created : Integer; 
    Fmodifytime : TXSDate;
    Fpastdate : TXSDate;
//???
    FsubsystemRef : CNSubsystemTypeRef;
//???
    FcnPackRef : CNPackRef;
  public
    destructor Destroy; override;
  published
    property  id : Integer read Fid write Fid; 
    property  id_state : Integer read Fid_state write Fid_state; 
    property startdate : TXSDate read Fstartdate write Fstartdate;
    property note : WideString read Fnote write Fnote;
    property  id_parent : Integer read Fid_parent write Fid_parent; 
    property  id_user : Integer read Fid_user write Fid_user; 
    property realdate : TXSDate read Frealdate write Frealdate;
    property  canceled : Integer read Fcanceled write Fcanceled; 
    property  id_user_canceled : Integer read Fid_user_canceled write Fid_user_canceled; 
    property canceleddate : TXSDate read Fcanceleddate write Fcanceleddate;
    property cancelednote : WideString read Fcancelednote write Fcancelednote;
    property  is_completed : Integer read Fis_completed write Fis_completed; 
    property  id_movement_status : Integer read Fid_movement_status write Fid_movement_status; 
    property addnote : WideString read Faddnote write Faddnote;
    property  read_status : Integer read Fread_status write Fread_status; 
    property  id_user_read : Integer read Fid_user_read write Fid_user_read; 
    property read_date : TXSDate read Fread_date write Fread_date;
    property  id_user_created : Integer read Fid_user_created write Fid_user_created; 
    property modifytime : TXSDate read Fmodifytime write Fmodifytime;
    property pastdate : TXSDate read Fpastdate write Fpastdate;
    property subsystemRef : CNSubsystemTypeRef read FsubsystemRef write FsubsystemRef; 
    property cnPackRef : CNPackRef read FcnPackRef write FcnPackRef; 
  end;
  
{
  CNMovementFilter = class(TRemotable)
  private
    FconditionSQL: WideString;
    ForderBySQL: WideString;
    Fid : Integer; 
    Fid_state : Integer; 
    Fstartdate : TXSDate;
    Fnote : WideString;
    Fid_parent : Integer; 
    Fid_user : Integer; 
    Frealdate : TXSDate;
    Fcanceled : Integer; 
    Fid_user_canceled : Integer; 
    Fcanceleddate : TXSDate;
    Fcancelednote : WideString;
    Fis_completed : Integer; 
    Fid_movement_status : Integer; 
    Faddnote : WideString;
    Fread_status : Integer; 
    Fid_user_read : Integer; 
    Fread_date : TXSDate;
    Fid_user_created : Integer; 
    Fmodifytime : TXSDate;
    Fpastdate : TXSDate;
//???
    FsubsystemRef : CNSubsystemTypeRef;
//???
    FcnPackRef : CNPackRef;
  public
    destructor Destroy; override;
  published
    property conditionSQL: WideString read FconditionSQL write FconditionSQL;
    property orderBySQL: WideString read ForderBySQL write ForderBySQL;
    property  id : Integer read Fid write Fid; 
    property  id_state : Integer read Fid_state write Fid_state; 
    property startdate : TXSDate read Fstartdate write Fstartdate;
    property note : WideString read Fnote write Fnote;
    property  id_parent : Integer read Fid_parent write Fid_parent; 
    property  id_user : Integer read Fid_user write Fid_user; 
    property realdate : TXSDate read Frealdate write Frealdate;
    property  canceled : Integer read Fcanceled write Fcanceled; 
    property  id_user_canceled : Integer read Fid_user_canceled write Fid_user_canceled; 
    property canceleddate : TXSDate read Fcanceleddate write Fcanceleddate;
    property cancelednote : WideString read Fcancelednote write Fcancelednote;
    property  is_completed : Integer read Fis_completed write Fis_completed; 
    property  id_movement_status : Integer read Fid_movement_status write Fid_movement_status; 
    property addnote : WideString read Faddnote write Faddnote;
    property  read_status : Integer read Fread_status write Fread_status; 
    property  id_user_read : Integer read Fid_user_read write Fid_user_read; 
    property read_date : TXSDate read Fread_date write Fread_date;
    property  id_user_created : Integer read Fid_user_created write Fid_user_created; 
    property modifytime : TXSDate read Fmodifytime write Fmodifytime;
    property pastdate : TXSDate read Fpastdate write Fpastdate;
    property subsystemRef : CNSubsystemTypeRef read FsubsystemRef write FsubsystemRef; 
    property cnPackRef : CNPackRef read FcnPackRef write FcnPackRef; 
  end;
}

  CNMovementFilter = class(CNMovement)
  private
    FconditionSQL: WideString;
    ForderBySQL: WideString;
  public
    destructor Destroy; override;
  published
    property conditionSQL: WideString read FconditionSQL write FconditionSQL;
    property orderBySQL: WideString read ForderBySQL write ForderBySQL;
  end;
  
  CNMovementShort = class(TRemotable)
  private
    Fid : Integer; 
    Fid_state : Integer; 
    Fstartdate : TXSDate;	
    Fnote : WideString;
    Fid_parent : Integer; 
    Fid_user : Integer; 
    Frealdate : TXSDate;	
    Fcanceled : Integer; 
    Fid_user_canceled : Integer; 
    Fcanceleddate : TXSDate;	
    Fcancelednote : WideString;
    Fis_completed : Integer; 
    Fid_movement_status : Integer; 
    Faddnote : WideString;
    Fread_status : Integer; 
    Fid_user_read : Integer; 
    Fread_date : TXSDate;	
    Fid_user_created : Integer; 
    Fmodifytime : TXSDate;	
    Fpastdate : TXSDate;	
    FsubsystemRefCode : Integer; 
    FsubsystemRefName : WideString;
    FcnPackRefCode : Integer; 
    FcnPackRefPackCode : Integer; 
    FcnPackRefName : WideString;
    FcnPackRefId_ren : Integer; 
    FcnPackRefRenName : WideString;
    FcnPackRefId_district : Integer; 
    FcnPackRefDistrictName : WideString;
    FcnPackRefId_pack_status : Integer; 
    FcnPackRefStatusName : WideString;
    FcnPackRefDescription : WideString;
    FcnPackRefPower : TXSDecimal;
    FcnPackRefAddress : WideString;
    FcnPackRefAddress_jur : WideString;
    FcnPackRefReg_num_cn_contract : WideString;
    FcnPackRefDate_cn_contract : TXSDate;
    FcnPackRefReg_num_spl_pp_contract : WideString;
    FcnPackRefDate_spl_pp_contract : TXSDate;
    FcnPackRefReg_num_tu_contract : WideString;
    FcnPackRefDate_tu_contract : TXSDate;
    FcnPackRefReg_num_tu_cr_contract : WideString;
    FcnPackRefDate_tu_cr_contract : TXSDate;
    FcnPackRefProject_num : WideString;
    FcnPackRefProject_date : TXSDate;
    FcnPackRefOver5 : Integer; 
    FcnPackRefStatus : Integer; 
    FcnPackRefLetter_num_customer : WideString;
    FcnPackRefDate_letter_customer : TXSDate;
    FcnPackRefLetter_num : WideString;
    FcnPackRefDate_letter : TXSDate;
    FcnPackRefReliability_class : WideString;
    FcnPackRefId_waiting_status : Integer; 
    FcnPackRefWaitingStatus : WideString;
    FcnPackRefIs_payable : Integer; 
    FcnPackRefWorksize : WideString;
    FcnPackRefWork_inc_net : WideString;
    FcnPackRefBusiness_type : WideString;
    FcnPackRefEstimateterm : Integer; 
    FcnPackRefEstimatedate : TXSDate;
    FcnPackRefBuildingterm : Integer; 
    FcnPackRefBuildingdate : TXSDate;
    FcnPackRefBuyingterm : Integer; 
    FcnPackRefBuyingdate : TXSDate;
    FcnPackRefEstimate_num : WideString;
    FcnPackRefEstimate_contract_date : TXSDate;
    FcnPackRefIs_reserv : Integer; 
  public
    destructor Destroy; override;
  published
    property  id : Integer read Fid write Fid; 
    property  id_state : Integer read Fid_state write Fid_state; 
    property startdate : TXSDate read Fstartdate write Fstartdate;
    property note : WideString read Fnote write Fnote;
    property  id_parent : Integer read Fid_parent write Fid_parent; 
    property  id_user : Integer read Fid_user write Fid_user; 
    property realdate : TXSDate read Frealdate write Frealdate;
    property  canceled : Integer read Fcanceled write Fcanceled; 
    property  id_user_canceled : Integer read Fid_user_canceled write Fid_user_canceled; 
    property canceleddate : TXSDate read Fcanceleddate write Fcanceleddate;
    property cancelednote : WideString read Fcancelednote write Fcancelednote;
    property  is_completed : Integer read Fis_completed write Fis_completed; 
    property  id_movement_status : Integer read Fid_movement_status write Fid_movement_status; 
    property addnote : WideString read Faddnote write Faddnote;
    property  read_status : Integer read Fread_status write Fread_status; 
    property  id_user_read : Integer read Fid_user_read write Fid_user_read; 
    property read_date : TXSDate read Fread_date write Fread_date;
    property  id_user_created : Integer read Fid_user_created write Fid_user_created; 
    property modifytime : TXSDate read Fmodifytime write Fmodifytime;
    property pastdate : TXSDate read Fpastdate write Fpastdate;

    property subsystemRefCode : Integer read FsubsystemRefCode write FsubsystemRefCode; 
    property subsystemRefName : WideString read FsubsystemRefName write FsubsystemRefName; 
    property cnPackRefCode : Integer read FcnPackRefCode write FcnPackRefCode; 
    property cnPackRefPackCode : Integer read FcnPackRefPackCode write FcnPackRefPackCode; 
    property cnPackRefName : WideString read FcnPackRefName write FcnPackRefName; 
    property cnPackRefId_ren : Integer read FcnPackRefId_ren write FcnPackRefId_ren; 
    property cnPackRefRenName : WideString read FcnPackRefRenName write FcnPackRefRenName; 
    property cnPackRefId_district : Integer read FcnPackRefId_district write FcnPackRefId_district; 
    property cnPackRefDistrictName : WideString read FcnPackRefDistrictName write FcnPackRefDistrictName; 
    property cnPackRefId_pack_status : Integer read FcnPackRefId_pack_status write FcnPackRefId_pack_status; 
    property cnPackRefStatusName : WideString read FcnPackRefStatusName write FcnPackRefStatusName; 
    property cnPackRefDescription : WideString read FcnPackRefDescription write FcnPackRefDescription; 
    property cnPackRefPower : TXSDecimal read FcnPackRefPower write FcnPackRefPower; 
    property cnPackRefAddress : WideString read FcnPackRefAddress write FcnPackRefAddress; 
    property cnPackRefAddress_jur : WideString read FcnPackRefAddress_jur write FcnPackRefAddress_jur; 
    property cnPackRefReg_num_cn_contract : WideString read FcnPackRefReg_num_cn_contract write FcnPackRefReg_num_cn_contract; 
    property cnPackRefDate_cn_contract : TXSDate read FcnPackRefDate_cn_contract write FcnPackRefDate_cn_contract; 
    property cnPackRefReg_num_spl_pp_contract : WideString read FcnPackRefReg_num_spl_pp_contract write FcnPackRefReg_num_spl_pp_contract; 
    property cnPackRefDate_spl_pp_contract : TXSDate read FcnPackRefDate_spl_pp_contract write FcnPackRefDate_spl_pp_contract; 
    property cnPackRefReg_num_tu_contract : WideString read FcnPackRefReg_num_tu_contract write FcnPackRefReg_num_tu_contract; 
    property cnPackRefDate_tu_contract : TXSDate read FcnPackRefDate_tu_contract write FcnPackRefDate_tu_contract; 
    property cnPackRefReg_num_tu_cr_contract : WideString read FcnPackRefReg_num_tu_cr_contract write FcnPackRefReg_num_tu_cr_contract; 
    property cnPackRefDate_tu_cr_contract : TXSDate read FcnPackRefDate_tu_cr_contract write FcnPackRefDate_tu_cr_contract; 
    property cnPackRefProject_num : WideString read FcnPackRefProject_num write FcnPackRefProject_num; 
    property cnPackRefProject_date : TXSDate read FcnPackRefProject_date write FcnPackRefProject_date; 
    property cnPackRefOver5 : Integer read FcnPackRefOver5 write FcnPackRefOver5; 
    property cnPackRefStatus : Integer read FcnPackRefStatus write FcnPackRefStatus; 
    property cnPackRefLetter_num_customer : WideString read FcnPackRefLetter_num_customer write FcnPackRefLetter_num_customer; 
    property cnPackRefDate_letter_customer : TXSDate read FcnPackRefDate_letter_customer write FcnPackRefDate_letter_customer; 
    property cnPackRefLetter_num : WideString read FcnPackRefLetter_num write FcnPackRefLetter_num; 
    property cnPackRefDate_letter : TXSDate read FcnPackRefDate_letter write FcnPackRefDate_letter; 
    property cnPackRefReliability_class : WideString read FcnPackRefReliability_class write FcnPackRefReliability_class; 
    property cnPackRefId_waiting_status : Integer read FcnPackRefId_waiting_status write FcnPackRefId_waiting_status; 
    property cnPackRefWaitingStatus : WideString read FcnPackRefWaitingStatus write FcnPackRefWaitingStatus; 
    property cnPackRefIs_payable : Integer read FcnPackRefIs_payable write FcnPackRefIs_payable; 
    property cnPackRefWorksize : WideString read FcnPackRefWorksize write FcnPackRefWorksize; 
    property cnPackRefWork_inc_net : WideString read FcnPackRefWork_inc_net write FcnPackRefWork_inc_net; 
    property cnPackRefBusiness_type : WideString read FcnPackRefBusiness_type write FcnPackRefBusiness_type; 
    property cnPackRefEstimateterm : Integer read FcnPackRefEstimateterm write FcnPackRefEstimateterm; 
    property cnPackRefEstimatedate : TXSDate read FcnPackRefEstimatedate write FcnPackRefEstimatedate; 
    property cnPackRefBuildingterm : Integer read FcnPackRefBuildingterm write FcnPackRefBuildingterm; 
    property cnPackRefBuildingdate : TXSDate read FcnPackRefBuildingdate write FcnPackRefBuildingdate; 
    property cnPackRefBuyingterm : Integer read FcnPackRefBuyingterm write FcnPackRefBuyingterm; 
    property cnPackRefBuyingdate : TXSDate read FcnPackRefBuyingdate write FcnPackRefBuyingdate; 
    property cnPackRefEstimate_num : WideString read FcnPackRefEstimate_num write FcnPackRefEstimate_num; 
    property cnPackRefEstimate_contract_date : TXSDate read FcnPackRefEstimate_contract_date write FcnPackRefEstimate_contract_date; 
    property cnPackRefIs_reserv : Integer read FcnPackRefIs_reserv write FcnPackRefIs_reserv; 
  end;

  ArrayOfCNMovementShort = array of CNMovementShort;  // { "http://ksoe.org/EnergyproControllerService/type/" }

  CNMovementShortList = class(TRemotable)
  private
    FtotalCount: Integer;
    Flist: ArrayOfCNMovementShort;
  public
  destructor Destroy; override;
  published
    property totalCount: Integer read FtotalCount write FtotalCount;
    property list: ArrayOfCNMovementShort read Flist write Flist;
  end;

  ArrayOfInteger = array of Integer;

  // ************************************************************************ //
  // Namespace : http://ksoe.org/CNMovementController/message/
  // soapAction: http://ksoe.org/CNMovementController/action/CNMovementController.%operationName%
  // transport : http://schemas.xmlsoap.org/soap/http
  // style     : rpc
  // binding   : CNMovementControllerSoapBinding
  // service   : EnergyproControllerService
  // port      : CNMovementController
  // URL       : http://soap.ksoe.com.ua/energypro
  // ************************************************************************ //


  CNMovementControllerSoapPort = interface(IInvokable)
  ['{3a2d3a2d-3a2d-3a2d-3a2d-3a2d3a2d3a2d}']
    function  add(const aCNMovement: CNMovement): Integer; stdcall;
    procedure remove(const anObjectCode: Integer); stdcall;
    procedure save(const aCNMovement: CNMovement); stdcall;
    function  getObject(const anObjectCode: Integer): CNMovement; stdcall;
    function  getList: CNMovementShortList; stdcall;
    function  getFilteredList(const aCNMovementFilter: CNMovementFilter): CNMovementShortList; stdcall;
    function  getScrollableList(const aFromPosition: Integer; const aQuantity: Integer): CNMovementShortList; stdcall;
    function  getScrollableFilteredList(const aCNMovementFilter: CNMovementFilter; const aFromPosition: Integer; const aQuantity: Integer): CNMovementShortList; stdcall;
    function  getScrollableListByCondition(const aCondition: WideString; const aFromPosition: Integer; const aQuantity: Integer): CNMovementShortList; stdcall;
    function  getScrollableFilteredCodeArray(const aCNMovementFilter: CNMovementFilter; const aFromPosition: Integer; const aQuantity: Integer): ArrayOfInteger; stdcall;

  end; 


implementation

  destructor CNMovement.Destroy;
  begin
    if Assigned(Fstartdate) then
      startdate.Free;
    if Assigned(Frealdate) then
      realdate.Free;
    if Assigned(Fcanceleddate) then
      canceleddate.Free;
    if Assigned(Fread_date) then
      read_date.Free;
    if Assigned(Fmodifytime) then
      modifytime.Free;
    if Assigned(Fpastdate) then
      pastdate.Free;
    if Assigned(FsubsystemRef) then
      subsystemRef.Free;
    if Assigned(FcnPackRef) then
      cnPackRef.Free;
    inherited Destroy;
  end;

{  
  destructor CNMovementFilter.Destroy;
  begin
    if Assigned(Fstartdate) then
      startdate.Free;
    if Assigned(Frealdate) then
      realdate.Free;
    if Assigned(Fcanceleddate) then
      canceleddate.Free;
    if Assigned(Fread_date) then
      read_date.Free;
    if Assigned(Fmodifytime) then
      modifytime.Free;
    if Assigned(Fpastdate) then
      pastdate.Free;
    if Assigned(FsubsystemRef) then
      subsystemRef.Free;
    if Assigned(FcnPackRef) then
      cnPackRef.Free;
    inherited Destroy;
  end; 
}

  destructor CNMovementFilter.Destroy;
  begin
    inherited Destroy;
  end; 

  destructor CNMovementShort.Destroy;
  begin
    if Assigned(Fstartdate) then
      startdate.Free;
    if Assigned(Frealdate) then
      realdate.Free;
    if Assigned(Fcanceleddate) then
      canceleddate.Free;
    if Assigned(Fread_date) then
      read_date.Free;
    if Assigned(Fmodifytime) then
      modifytime.Free;
    if Assigned(Fpastdate) then
      pastdate.Free;
    if Assigned(FcnPackRefPower) then
      cnPackRefPower.Free;
    if Assigned(FcnPackRefDate_cn_contract) then
      cnPackRefDate_cn_contract.Free;
    if Assigned(FcnPackRefDate_spl_pp_contract) then
      cnPackRefDate_spl_pp_contract.Free;
    if Assigned(FcnPackRefDate_tu_contract) then
      cnPackRefDate_tu_contract.Free;
    if Assigned(FcnPackRefDate_tu_cr_contract) then
      cnPackRefDate_tu_cr_contract.Free;
    if Assigned(FcnPackRefProject_date) then
      cnPackRefProject_date.Free;
    if Assigned(FcnPackRefDate_letter_customer) then
      cnPackRefDate_letter_customer.Free;
    if Assigned(FcnPackRefDate_letter) then
      cnPackRefDate_letter.Free;
    if Assigned(FcnPackRefEstimatedate) then
      cnPackRefEstimatedate.Free;
    if Assigned(FcnPackRefBuildingdate) then
      cnPackRefBuildingdate.Free;
    if Assigned(FcnPackRefBuyingdate) then
      cnPackRefBuyingdate.Free;
    if Assigned(FcnPackRefEstimate_contract_date) then
      cnPackRefEstimate_contract_date.Free;
    inherited Destroy;
  end; 
  
  destructor CNMovementShortList.Destroy;
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

  RemClassRegistry.RegisterXSClass(CNMovement, 'http://ksoe.org/EnergyproControllerService/type/', 'CNMovement');
  RemClassRegistry.RegisterXSClass(CNMovementRef, 'http://ksoe.org/EnergyproControllerService/type/', 'CNMovementRef');
  RemClassRegistry.RegisterXSClass(CNMovementFilter, 'http://ksoe.org/EnergyproControllerService/type/', 'CNMovementFilter');
  RemClassRegistry.RegisterXSClass(CNMovementShort, 'http://ksoe.org/EnergyproControllerService/type/', 'CNMovementShort');
  RemClassRegistry.RegisterXSClass(CNMovementShortList, 'http://ksoe.org/EnergyproControllerService/type/', 'CNMovementShortList');
  RemClassRegistry.RegisterXSInfo(TypeInfo(ArrayOfCNMovementShort), 'http://ksoe.org/EnergyproControllerService/type/', 'ArrayOfCNMovementShort');

  InvRegistry.RegisterInterface(TypeInfo(CNMovementControllerSoapPort), 'http://ksoe.org/CNMovementController/message/', 'UTF-8');
  InvRegistry.RegisterDefaultSOAPAction(TypeInfo(CNMovementControllerSoapPort), 'http://ksoe.org/CNMovementController/action/CNMovementController.%operationName%');


end.
