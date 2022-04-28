unit CNPack2SiteController;

interface

uses InvokeRegistry, SOAPHTTPClient, Types, XSBuiltIns
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

  CNPack2Site            = class;                 { "http://ksoe.org/EnergyproControllerService/type/" }



  // ************************************************************************ //
  // Namespace : http://ksoe.org/EnergyproControllerService/type/
  // ************************************************************************ //
  CNPack2SiteRef = class(TRemotable)
  private
    Fcode: Integer;
  published
    property code: Integer read Fcode write Fcode;
  end;


  // ************************************************************************ //
  // Namespace : http://ksoe.org/EnergyproControllerService/type/
  // ************************************************************************ //
  CNPack2Site = class(TRemotable)
  private
    Fcode : Integer;
    Fis_reg : Integer;
    Fcustomeremail : WideString;
    Fphone : WideString;
    Fcustomertype : Integer;
//???
    FsubsystemRef : CNSubsystemTypeRef;
//???
    FcnPackRef : CNPackRef;
  public
    destructor Destroy; override;
  published
    property  code : Integer read Fcode write Fcode;
    property  is_reg : Integer read Fis_reg write Fis_reg;
    property customeremail : WideString read Fcustomeremail write Fcustomeremail;
    property phone : WideString read Fphone write Fphone;
    property  customertype : Integer read Fcustomertype write Fcustomertype;
    property subsystemRef : CNSubsystemTypeRef read FsubsystemRef write FsubsystemRef;
    property cnPackRef : CNPackRef read FcnPackRef write FcnPackRef;
  end;

{
  CNPack2SiteFilter = class(TRemotable)
  private
    FconditionSQL: WideString;
    ForderBySQL: WideString;
    Fcode : Integer;
    Fis_reg : Integer;
    Fcustomeremail : WideString;
    Fphone : WideString;
    Fcustomertype : Integer;
//???
    FsubsystemRef : CNSubsystemTypeRef;
//???
    FcnPackRef : CNPackRef;
  public
    destructor Destroy; override;
  published
    property conditionSQL: WideString read FconditionSQL write FconditionSQL;
    property orderBySQL: WideString read ForderBySQL write ForderBySQL;
    property  code : Integer read Fcode write Fcode;
    property  is_reg : Integer read Fis_reg write Fis_reg;
    property customeremail : WideString read Fcustomeremail write Fcustomeremail;
    property phone : WideString read Fphone write Fphone;
    property  customertype : Integer read Fcustomertype write Fcustomertype;
    property subsystemRef : CNSubsystemTypeRef read FsubsystemRef write FsubsystemRef;
    property cnPackRef : CNPackRef read FcnPackRef write FcnPackRef;
  end;
}

  CNPack2SiteFilter = class(CNPack2Site)
  private
    FconditionSQL: WideString;
    ForderBySQL: WideString;
  public
    destructor Destroy; override;
  published
    property conditionSQL: WideString read FconditionSQL write FconditionSQL;
    property orderBySQL: WideString read ForderBySQL write ForderBySQL;
  end;

  CNPack2SiteShort = class(TRemotable)
  private
    Fcode : Integer;
    Fis_reg : Integer;
    Fcustomeremail : WideString;
    Fphone : WideString;
    Fcustomertype : Integer;
    FsubsystemRefCode : Integer;
    FsubsystemRefName : WideString;
    FcnPackRefCode : Integer;
    FcnPackRefPackCode : Integer;
    FcnPackRefName : WideString;
    FcnPackRefId_ren : Integer;
    FcnPackRefRenName : WideString;
    FcnPackRefId_district : Integer;
    FcnPackRefDistrictName : WideString;
    FcnPackRefId_child_district : Integer;
    FcnPackRefChildDistrictName : WideString;
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
    FcnPackRefPurpose : WideString;
    FcnPackRefIs_ksoe : Integer;
    FcnPackRefOver150 : Integer;
    FcnPackRefIs_new : Integer;
    FcnPackRefIs3phases : Integer;
    FcnPackRefAgree_changes : Integer;
    FcnPackRefDate_end_order_spl : TXSDate;
    FcnPackRefCopmany_protocol : Integer;
    FcnPackRefId_feature : Integer;
    FcnPackRefId_state : Integer;
    FcnPackRefId_bp : Integer;
    FcnPackRefId_parent : Integer;
  public
    destructor Destroy; override;
  published
    property  code : Integer read Fcode write Fcode;
    property  is_reg : Integer read Fis_reg write Fis_reg;
    property customeremail : WideString read Fcustomeremail write Fcustomeremail;
    property phone : WideString read Fphone write Fphone;
    property  customertype : Integer read Fcustomertype write Fcustomertype;

    property subsystemRefCode : Integer read FsubsystemRefCode write FsubsystemRefCode;
    property subsystemRefName : WideString read FsubsystemRefName write FsubsystemRefName;
    property cnPackRefCode : Integer read FcnPackRefCode write FcnPackRefCode;
    property cnPackRefPackCode : Integer read FcnPackRefPackCode write FcnPackRefPackCode;
    property cnPackRefName : WideString read FcnPackRefName write FcnPackRefName;
    property cnPackRefId_ren : Integer read FcnPackRefId_ren write FcnPackRefId_ren;
    property cnPackRefRenName : WideString read FcnPackRefRenName write FcnPackRefRenName;
    property cnPackRefId_district : Integer read FcnPackRefId_district write FcnPackRefId_district;
    property cnPackRefDistrictName : WideString read FcnPackRefDistrictName write FcnPackRefDistrictName;
    property cnPackRefId_child_district : Integer read FcnPackRefId_child_district write FcnPackRefId_child_district;
    property cnPackRefChildDistrictName : WideString read FcnPackRefChildDistrictName write FcnPackRefChildDistrictName;
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
    property cnPackRefPurpose : WideString read FcnPackRefPurpose write FcnPackRefPurpose;
    property cnPackRefIs_ksoe : Integer read FcnPackRefIs_ksoe write FcnPackRefIs_ksoe;
    property cnPackRefOver150 : Integer read FcnPackRefOver150 write FcnPackRefOver150;
    property cnPackRefIs_new : Integer read FcnPackRefIs_new write FcnPackRefIs_new;
    property cnPackRefIs3phases : Integer read FcnPackRefIs3phases write FcnPackRefIs3phases;
    property cnPackRefAgree_changes : Integer read FcnPackRefAgree_changes write FcnPackRefAgree_changes;
    property cnPackRefDate_end_order_spl : TXSDate read FcnPackRefDate_end_order_spl write FcnPackRefDate_end_order_spl;
    property cnPackRefCopmany_protocol : Integer read FcnPackRefCopmany_protocol write FcnPackRefCopmany_protocol;
    property cnPackRefId_feature : Integer read FcnPackRefId_feature write FcnPackRefId_feature;
    property cnPackRefId_state : Integer read FcnPackRefId_state write FcnPackRefId_state;
    property cnPackRefId_bp : Integer read FcnPackRefId_bp write FcnPackRefId_bp;
    property cnPackRefId_parent : Integer read FcnPackRefId_parent write FcnPackRefId_parent;
  end;

  ArrayOfCNPack2SiteShort = array of CNPack2SiteShort;  // { "http://ksoe.org/EnergyproControllerService/type/" }

  CNPack2SiteShortList = class(TRemotable)
  private
    FtotalCount: Integer;
    Flist: ArrayOfCNPack2SiteShort;
  public
  destructor Destroy; override;
  published
    property totalCount: Integer read FtotalCount write FtotalCount;
    property list: ArrayOfCNPack2SiteShort read Flist write Flist;
  end;

  ArrayOfInteger = array of Integer;

  // ************************************************************************ //
  // Namespace : http://ksoe.org/CNPack2SiteController/message/
  // soapAction: http://ksoe.org/CNPack2SiteController/action/CNPack2SiteController.%operationName%
  // transport : http://schemas.xmlsoap.org/soap/http
  // style     : rpc
  // binding   : CNPack2SiteControllerSoapBinding
  // service   : EnergyproControllerService
  // port      : CNPack2SiteController
  // URL       : http://soap.ksoe.com.ua/energypro
  // ************************************************************************ //


  CNPack2SiteControllerSoapPort = interface(IInvokable)
  ['{49DD2580-2D32-4A0D-9D96-B57776F47750}']
    function add(const aCNPack2Site: CNPack2Site): Integer; stdcall;
    procedure remove(const anObjectCode: Integer); stdcall;
    procedure save(const aCNPack2Site: CNPack2Site); stdcall;
    function getObject(const anObjectCode: Integer): CNPack2Site; stdcall;
    function getList: CNPack2SiteShortList; stdcall;
    function getFilteredList(const aCNPack2SiteFilter: CNPack2SiteFilter): CNPack2SiteShortList; stdcall;
    function getScrollableList(const aFromPosition: Integer; const aQuantity: Integer): CNPack2SiteShortList; stdcall;
    function getScrollableFilteredList(const aCNPack2SiteFilter: CNPack2SiteFilter; const aFromPosition: Integer; const aQuantity: Integer): CNPack2SiteShortList; stdcall;
    function getScrollableListByCondition(const aCondition: WideString; const aFromPosition: Integer; const aQuantity: Integer): CNPack2SiteShortList; stdcall;
    function getScrollableFilteredCodeArray(const aCNPack2SiteFilter: CNPack2SiteFilter; const aFromPosition: Integer; const aQuantity: Integer): ArrayOfInteger; stdcall;
    function getShortObject(const anObjectCode: Integer): CNPack2SiteShort; stdcall;
  end;


implementation

  destructor CNPack2Site.Destroy;
  begin
    if Assigned(FsubsystemRef) then
      subsystemRef.Free;
    if Assigned(FcnPackRef) then
      cnPackRef.Free;
    inherited Destroy;
  end;

{
  destructor CNPack2SiteFilter.Destroy;
  begin
    if Assigned(FsubsystemRef) then
      subsystemRef.Free;
    if Assigned(FcnPackRef) then
      cnPackRef.Free;
    inherited Destroy;
  end;
}

  destructor CNPack2SiteFilter.Destroy;
  begin
    inherited Destroy;
  end;

  destructor CNPack2SiteShort.Destroy;
  begin
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
    if Assigned(FcnPackRefDate_end_order_spl) then
      cnPackRefDate_end_order_spl.Free;
    inherited Destroy;
  end;

  destructor CNPack2SiteShortList.Destroy;
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

  RemClassRegistry.RegisterXSClass(CNPack2Site, 'http://ksoe.org/EnergyproControllerService/type/', 'CNPack2Site');
  RemClassRegistry.RegisterXSClass(CNPack2SiteRef, 'http://ksoe.org/EnergyproControllerService/type/', 'CNPack2SiteRef');
  RemClassRegistry.RegisterXSClass(CNPack2SiteFilter, 'http://ksoe.org/EnergyproControllerService/type/', 'CNPack2SiteFilter');
  RemClassRegistry.RegisterXSClass(CNPack2SiteShort, 'http://ksoe.org/EnergyproControllerService/type/', 'CNPack2SiteShort');
  RemClassRegistry.RegisterXSClass(CNPack2SiteShortList, 'http://ksoe.org/EnergyproControllerService/type/', 'CNPack2SiteShortList');
  RemClassRegistry.RegisterXSInfo(TypeInfo(ArrayOfCNPack2SiteShort), 'http://ksoe.org/EnergyproControllerService/type/', 'ArrayOfCNPack2SiteShort');

  InvRegistry.RegisterInterface(TypeInfo(CNPack2SiteControllerSoapPort), 'http://ksoe.org/CNPack2SiteController/message/', 'UTF-8');
  InvRegistry.RegisterDefaultSOAPAction(TypeInfo(CNPack2SiteControllerSoapPort), 'http://ksoe.org/CNPack2SiteController/action/CNPack2SiteController.%operationName%');


end.
