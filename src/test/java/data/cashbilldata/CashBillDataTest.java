package data.cashbilldata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import po.CashBillPO;


/**
 * 
 * @author cylong
 * @version 2014年12月18日  上午12:06:41
 */
public class CashBillDataTest {

	/**
	 * @throws java.lang.Exception
	 * @author cylong
	 * @version 2014年12月18日  上午12:06:41
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 * @author cylong
	 * @version 2014年12月18日  上午12:06:41
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws RemoteException {
		System.out.println(new CashBillData().getID());
	}
	
	/**
	 * Test method for {@link data.CommonData#show()}.
	 * @throws RemoteException 
	 */
	@Test
	public void testShow() throws RemoteException {
		ArrayList<CashBillPO> cashBills = new CashBillData().show();
		for(CashBillPO po : cashBills) {
			System.out.println(po.getID());
		}
	}

}
