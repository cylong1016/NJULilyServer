package data.saledate;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import po.SalesPO;
import data.saledata.SaleData;
import dataenum.BillType;
import dataenum.ResultMessage;
import dataenum.Storage;

/**
 * @author cylong
 * @version 2014年12月29日 上午12:02:19
 */
public class SaleDataTest {

	private SaleData saleData;

	public SaleDataTest() {
		try {
			saleData = new SaleData();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws java.lang.Exception
	 * @author cylong
	 * @version 2014年12月29日 上午12:02:19
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 * @author cylong
	 * @version 2014年12月29日 上午12:02:19
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("显示全部的销售单");
		for(SalesPO po : saleData.show()) {
			System.out.println(po);
		}
	}

	/**
	 * Test method for {@link data.CommonData#insert(po.PersistentObject)}.
	 * @throws RemoteException
	 */
	@Test
	public void testSale() throws RemoteException {
		String ID = "XSD-20141227-99999";
		// --------------------添加-------------------------
		SalesPO po = new SalesPO(ID, "KHTEST", "客户", "业务员", "操作员", Storage.STORAGE_ONE, null, 123, 1, 1, "测试", 121, BillType.SALE);
		assertEquals("添加失败", saleData.insert(po), ResultMessage.SUCCESS);
		System.out.println("添加销售单 " + po);
		System.out.println("显示全部的销售单");
		for(SalesPO temp : saleData.show()) {
			System.out.println(temp);
		}
		// --------------------修改-------------------------
		po = new SalesPO(ID, "KHTEST", "修改客户", "修改业务员", "修改操作员", Storage.STORAGE_TWO, null, 124, 1, 1, "测试", 122, BillType.SALE);
		assertEquals("修改失败", saleData.update(po), ResultMessage.SUCCESS);
		System.out.println("修改销售单 " + saleData.find(ID));
		// --------------------删除-------------------------
		System.out.println("删除销售单" + saleData.find(ID));
		assertEquals("删除失败", saleData.delete(ID), ResultMessage.SUCCESS);
	}

}
