package data.purchase;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import po.PurchasePO;
import data.purchasedata.PurchaseData;

/**
 * @author cylong
 * @version 2014年12月19日 上午12:12:45
 */
public class PurchaseDataTest {

	/**
	 * @throws java.lang.Exception
	 * @author cylong
	 * @version 2014年12月19日 上午12:12:45
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 * @author cylong
	 * @version 2014年12月19日 上午12:12:45
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link data.CommonData#show()}.
	 * @throws RemoteException
	 */
	@Test
	public void testShow() throws RemoteException {
		ArrayList<PurchasePO> pos = new PurchaseData().show();
		for(PurchasePO po : pos) {
			System.out.println(po.getID());
		}
	}

}
