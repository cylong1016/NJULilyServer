package data.accountbilldata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Test;

import po.AccountBillPO;

/**
 * 测试get收款单和付款单的ID
 * @author cylong
 * @version 2014年12月17日 下午11:02:46
 */
public class AccountBillDataTest {

	AccountBillData accountBill;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link data.accountbilldata.AccountBillData#getExpenseID()}.
	 * @throws RemoteException
	 */
	@Test
	public void testGetExpenseID() throws RemoteException {
		accountBill = new AccountBillData();
		System.out.println(accountBill.getExpenseID());
	}

	/**
	 * Test method for {@link data.accountbilldata.AccountBillData#getPayID()}.
	 * @throws RemoteException
	 */
	@Test
	public void testGetPayID() throws RemoteException {
		accountBill = new AccountBillData();
		System.out.println(accountBill.getPayID());
	}

	/**
	 * Test method for {@link data.CommonData#show()}.
	 * @throws RemoteException
	 */
	@Test
	public void testShow() throws RemoteException {
		ArrayList<AccountBillPO> bills = new AccountBillData().show();
		System.out.println(bills.size());
		for(AccountBillPO po : bills) {
			System.out.println(po.getID());
			System.out.println(po.getState());
			System.out.println();
		}
	}

}
