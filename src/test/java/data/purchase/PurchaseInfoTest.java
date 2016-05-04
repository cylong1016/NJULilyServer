package data.purchase;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.purchasedata.PurchaseInfo;
import dataenum.BillType;


/**
 * 
 * @author cylong
 * @version 2014年12月29日  下午4:19:35
 */
public class PurchaseInfoTest {

	/**
	 * @throws java.lang.Exception
	 * @author cylong
	 * @version 2014年12月29日  下午4:19:35
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 * @author cylong
	 * @version 2014年12月29日  下午4:19:35
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link data.purchasedata.PurchaseInfo#getAllID(dataenum.BillType)}.
	 * @throws RemoteException 
	 */
	@Test
	public void testGetAllID() throws RemoteException {
		PurchaseInfo info = new PurchaseInfo();
		ArrayList<String> IDs = info.getAllID(BillType.PURCHASE);
		for(String ID : IDs) {
			System.out.println(ID);
		}
	}

}
