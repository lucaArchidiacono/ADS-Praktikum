package Praktikum_01_Code;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KgvServerTest {
	KGVServer server;

	@Before
	public void setUp() throws Exception {
		server = new KGVServer();
	}

	@Test
	public void testKgv() {
		assertEquals(server.kgv(3, 4), 12);
		assertEquals(server.kgv(2, 4), 4);
		assertEquals(server.kgv(5, 7), 35);
		assertEquals(server.kgv(4, 6), 12);
	}
}