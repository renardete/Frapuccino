/*
 * Generated by: org.ow2.frascati.tinfi.opt.oo.ClientInterfaceClassGenerator
 * on: Fri Sep 28 21:27:38 COT 2018
 */

package co.edu.icesi.shellsort.interfaces;


public class sortFcOutItf<E>
extends co.edu.icesi.shellsort.interfaces.sortFcInItf<E>
implements co.edu.icesi.shellsort.interfaces.sort<E>,org.ow2.frascati.tinfi.TinfiComponentOutInterface<co.edu.icesi.shellsort.interfaces.sort> {

  public sortFcOutItf()  {
  }

  public sortFcOutItf(org.objectweb.fractal.api.Component component,String s,org.objectweb.fractal.api.Type type,boolean flag,Object obj)  {
    super(component,s,type,flag,obj);
  }

  public org.oasisopen.sca.ServiceReference<co.edu.icesi.shellsort.interfaces.sort> getServiceReference()  {
    return new co.edu.icesi.shellsort.interfaces.sortFcSR(co.edu.icesi.shellsort.interfaces.sort.class,this);
  }

}