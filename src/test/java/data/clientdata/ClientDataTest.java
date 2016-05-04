package data.clientdata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import po.ClientPO;


/**
 * 
 * @author cylong
 * @version 2014年12月31日  上午5:39:56
 */
public class ClientDataTest {

	/**
	 * @throws java.lang.Exception
	 * @author cylong
	 * @version 2014年12月31日  上午5:39:56
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 * @author cylong
	 * @version 2014年12月31日  上午5:39:56
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link data.clientdata.ClientData#find(java.lang.String, dataenum.FindTypeClient)}.
	 * @throws RemoteException 
	 */
	@Test
	public void testFindStringFindTypeClient() throws RemoteException {
		ClientData data = new ClientData();
		ArrayList<ClientPO> clients = data.find("一", null);
		for(ClientPO po : clients) {
			System.out.println(po.toString());
		}
	}

}
