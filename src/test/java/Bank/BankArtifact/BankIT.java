package Bank.BankArtifact;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Bank.BankArtifact.Bank;
import Bank.BankArtifact.Client;

public class BankIT {
	public final float floatTolerance = 0.0001f;
	private Bank bank;
	
	@Before
    public void setUp() {
		// this is a simple abstraction from what would be a database connection
		// testing several systems with one simple test
		
		bank = new Bank();
		Client carlos = new Client("Carlos");
		Client melo = new Client("Melo");
		Client rui = new Client("Rui");
		
		bank.addClient(carlos);	bank.addClient(melo); bank.addClient(rui);
    }
	

	@Test
	public void testDepositAmount() {
		// use the functions depositAccount(Client,float) & getClientByName(String) from Bank 
		
		Client c1 = new Client("John");
		Client c2 = new Client("Mary");
		Client c3 = new Client("Eve");
		
		Bank b = new Bank();
		
		b.addClient(c1);
		b.addClient(c2);
		b.addClient(c3);
		
		b.depositAccount(c1, 1500.f);
		b.depositAccount(c2, 3000.f);
		b.depositAccount(c3, 4500.f);
		
		assertEquals(b.getClientByName("John").getAccount().getAmount(), 1500.0f, floatTolerance);
		assertEquals(b.getClientByName("Mary").getAccount().getAmount(), 3000.0f, floatTolerance);
		assertEquals(b.getClientByName("Eve").getAccount().getAmount(), 4500.0f, floatTolerance);
	}
	
	@Test	
	public void testWithdrawAmount() {	
		// use the functions depositAccount(Client), getClientByName(String) & withdrawClientAccount(Client) from Bank
		
		Client c1 = new Client("John");
		Client c2 = new Client("Mary");
		Client c3 = new Client("Eve");
		
		Bank b = new Bank();
		
		b.addClient(c1);
		b.addClient(c2);
		b.addClient(c3);
		
		b.depositAccount(c1, 1500.f);
		b.depositAccount(c2, 3000.f);
		b.depositAccount(c3, 4500.f);
		
		b.withdrawClientAccount(b.getClientByName("John"), 500.0f);
		b.withdrawClientAccount(b.getClientByName("Mary"), 250.0f);
		b.withdrawClientAccount(b.getClientByName("Eve"), 100.0f);
		
		assertEquals(b.getClientByName("John").getAccount().getAmount(), 1000.0f, floatTolerance);
		assertEquals(b.getClientByName("Mary").getAccount().getAmount(), 2750.0f, floatTolerance);
		assertEquals(b.getClientByName("Eve").getAccount().getAmount(), 4400.0f, floatTolerance);
		
	}
	
	@Test
	public void testTransactionBetweenUsers() {
		// use the functions transfer(Client,Client,float) & getClientByName(String) from Bank
		
		Client c1 = new Client("John");
		Client c2 = new Client("Mary");
		Client c3 = new Client("Eve");
		
		Bank b = new Bank();
		
		b.addClient(c1);
		b.addClient(c2);
		b.addClient(c3);
		
		b.depositAccount(c1, 1500.f);
		b.depositAccount(c2, 3000.f);
		b.depositAccount(c3, 4500.f);
		
		assertEquals(b.transfer(b.getClientByName("John"), b.getClientByName("Mary"), 2000.0f), -1);
		
		assertEquals(b.transfer(b.getClientByName("John"), b.getClientByName("Mary"), 200.0f), 0);
		assertEquals(b.getClientByName("Mary").getAccount().getAmount(), 3200.0f, floatTolerance);
		assertEquals(b.getClientByName("John").getAccount().getAmount(), 1300.0f, floatTolerance);
		
		assertEquals(b.transfer(b.getClientByName("Mary"), b.getClientByName("Eve"), 100.0f), 0);
		assertEquals(b.getClientByName("Eve").getAccount().getAmount(), 4600.0f, floatTolerance);
		assertEquals(b.getClientByName("Mary").getAccount().getAmount(), 3100.0f, floatTolerance);
		
	}

}
