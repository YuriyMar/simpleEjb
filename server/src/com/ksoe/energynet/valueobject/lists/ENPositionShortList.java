
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
//---------------------------------------------------------

package  com.ksoe.energynet.valueobject.lists;


  /**
  * Short List for ENPosition;  	
  */

import java.io.Serializable;
import java.util.Vector;

public class ENPositionShortList implements Serializable {

  public int totalCount = 0;
  public Vector list = new Vector();

  public final int getTotalCount() {return totalCount;}
  public final void setTotalCount(int aValue) {totalCount = aValue;}

  public final Vector getList() {return list;}
  public final void setList(Vector aValue) {list = aValue;}

  public final com.ksoe.energynet.valueobject.brief.ENPositionShort get(int anIndex)
  {
    return (com.ksoe.energynet.valueobject.brief.ENPositionShort)list.get(anIndex);
  }

  public final int size()
  {
    return (list == null)?0:list.size();
  }

} // end of List for ENPosition

