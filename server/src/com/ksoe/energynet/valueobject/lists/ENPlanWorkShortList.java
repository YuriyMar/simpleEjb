
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------

package  com.ksoe.energynet.valueobject.lists;


  /**
  * Short List for ENPlanWork;  	
  */

import java.util.Vector;

import com.ksoe.energynet.valueobject.brief.ENPlanWorkShort;

import java.io.Serializable;

public class ENPlanWorkShortList implements Serializable {

  public int totalCount = 0;
  public Vector<ENPlanWorkShort> list = new Vector();

  public final int getTotalCount() {return totalCount;}
  public final void setTotalCount(int aValue) {totalCount = aValue;}

  public final Vector<ENPlanWorkShort> getList() {return list;}
  public final void setList(Vector<ENPlanWorkShort> aValue) {list = aValue;}

  public final ENPlanWorkShort get(int anIndex)
  {
    return list.get(anIndex);
  }

  public final int size()
  {
    return (list == null)?0:list.size();
  }

} // end of List for ENPlanWork

