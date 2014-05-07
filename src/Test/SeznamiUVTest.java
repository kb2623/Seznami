package Test;

import Source.SeznamiUV;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class SeznamiUVTest {

	private SeznamiUV uv;

	@Before
	public void setUp() {
		this.uv = new SeznamiUV();
	}

	@Test
	public void testUseSklad() {
		assertEquals("OK", uv.processInput("use sk"));
		reset(); testAdd();
		reset(); testAddNothing();
		reset(); testRemoveFirst();
		reset(); testRemoveFirstNothing("sk");
		reset(); testGet();
		reset(); testGetFirstNothing("sk");
		reset(); testExistsNothing("sk");
		reset(); testRemoveNothing("sk");
		reset(); testResetOnEmpty();
		reset(); testResetOnFull("sk");
		reset(); testCountOnEmpty();
		reset(); testCountOne();
		reset(); testCountTwo();
		reset(); testDepthOnEmpty();
		reset(); testDepthOne(false);
		reset(); testDepthTwo(false);
		reset(); testSklad(true);
		reset(); testIsEmpty();
		reset(); testExists();
		reset(); testRemove();
	}

	@Test
	public void testUsePrioritetnaVrsta() {
		assertEquals("OK", uv.processInput("use pv"));
		reset(); testAdd();
		reset(); testAddNothing();
		reset(); testRemoveFirst();
		reset(); testRemoveFirstNothing("pv");
		reset(); testGet();
		reset(); testGetFirstNothing("pv");
		reset(); testExistsNothing("pv");
		reset(); testRemoveNothing("pv");
		reset(); testResetOnEmpty();
		reset(); testResetOnFull("pv");
		reset(); testCountOnEmpty();
		reset(); testCountOne();
		reset(); testCountTwo();
		reset(); testDepthOnEmpty();
		reset(); testDepthOne(false);
		reset(); testDepthTwo(false);
		reset(); testPrioritetnaVrsta(true);
		reset(); testIsEmpty();
		reset(); testExists();
		reset(); testRemove();
	}
	
	@Test
	public void testUseBST() {
		assertEquals("OK", uv.processInput("use bst"));
		reset(); testAdd();
		reset(); testAddNothing();
		reset(); testRemoveFirst();
		reset(); testRemoveFirstNothing("bst");
		reset(); testGet();
		reset(); testGetFirstNothing("bst");
		reset(); testExistsNothing("bst");
		reset(); testRemoveNothing("bst");
		reset(); testResetOnEmpty();
		reset(); testResetOnFull("bst");
		reset(); testCountOnEmpty();
		reset(); testCountOne();
		reset(); testCountTwo();
		reset(); testDepthOnEmpty();
		reset(); testDepthOne(false);
		reset(); testDepthTwo(false);
		reset(); testBst(true);
		reset(); testIsEmpty();
		reset(); testExists();
		reset(); testRemove();
	}
	
	@Test
	public void testUseBinarnaKopica() {
		assertEquals("OK", uv.processInput("use bk"));
		reset(); testAdd();
		reset(); testAddNothing();
		reset(); testRemoveFirst();
		reset(); testRemoveFirstNothing("bk");
		reset(); testGet();
		reset(); testGetFirstNothing("bk");
		reset(); testExistsNothing("bk");
		reset(); testRemoveNothing("bk");
		reset(); testResetOnEmpty();
		reset(); testResetOnFull("bk");
		reset(); testCountOnEmpty();
		reset(); testCountOne();
		reset(); testCountTwo();
		reset(); testDepthOnEmpty();
		reset(); testDepthOne(true);
		reset(); testDepthTwo(true);
		reset(); testBinarnaKopica(true);
		reset(); testIsEmpty();
		reset(); testExists();
		reset(); testRemove();
	}

	@Test
	public void testUseAll() {
		testUseSklad();
		testUseBST();
		testUsePrioritetnaVrsta();
	}

	public void reset() {
		uv.processInput("reset");
	}

	public void testAdd() {
		assertEquals("OK", uv.processInput("add Test1"));
		assertEquals("OK", uv.processInput("add Test2"));
	}

	public void testAddNothing() {
		assertEquals("Error: please specify a string", uv.processInput("add"));
	}

	public void testRemoveFirst() {
		assertEquals("OK", uv.processInput("add Test"));
		assertEquals("Test", uv.processInput("removefirst"));
	}

	public void testRemoveFirstNothing(String struct) {
		switch (struct) {
			case "sk": assertEquals("Error: stack is empty", uv.processInput("removefirst")); break;
			case "pv": assertEquals("Error: priority queue is empty", uv.processInput("removefirst")); break;
			case "bst": assertEquals("Error: bst tree is empty", uv.processInput("removefirst")); break;
			default: assertEquals("Error: bk queue is empty", uv.processInput("removefirst")); break;
		}
		assertEquals("Error: please specify a string", uv.processInput("add"));
		switch (struct) {
			case "sk": assertEquals("Error: stack is empty", uv.processInput("removefirst")); break;
			case "pv": assertEquals("Error: priority queue is empty", uv.processInput("removefirst")); break;
			case "bst": assertEquals("Error: bst tree is empty", uv.processInput("removefirst")); break;
			default: assertEquals("Error: bk queue is empty", uv.processInput("removefirst")); break;
		}
	}

	public void testGetFirstNothing(String struct) {
		switch (struct) {
			case "sk": assertEquals("Error: stack is empty", uv.processInput("getfirst")); break;
			case "pv": assertEquals("Error: priority queue is empty", uv.processInput("getfirst")); break;
			case "bst": assertEquals("Error: bst tree is empty", uv.processInput("getfirst")); break;
			default: assertEquals("Error: bk queue is empty", uv.processInput("getfirst")); break;
		}
		assertEquals("Error: please specify a string", uv.processInput("add"));
		switch (struct) {
			case "sk": assertEquals("Error: stack is empty", uv.processInput("getfirst")); break;
			case "pv": assertEquals("Error: priority queue is empty", uv.processInput("getfirst")); break;
			case "bst": assertEquals("Error: bst tree is empty", uv.processInput("getfirst")); break;
			default: assertEquals("Error: bk queue is empty", uv.processInput("getfirst")); break;
		}
	}
	
	public void testExistsNothing(String struct) {
		switch (struct) {
			case "sk": assertEquals("Error: stack is empty", uv.processInput("exists test")); break;
			case "pv": assertEquals("Error: priority queue is empty", uv.processInput("exists test")); break;
			case "bst": assertEquals("Error: bst tree is empty", uv.processInput("exists test")); break;
			default: assertEquals("Error: bk queue is empty", uv.processInput("exists test")); break;
		}
		assertEquals("Error: please specify a string", uv.processInput("add"));
		switch (struct) {
			case "sk": assertEquals("Error: stack is empty", uv.processInput("exists test")); break;
			case "pv": assertEquals("Error: priority queue is empty", uv.processInput("exists test")); break;
			case "bst": assertEquals("Error: bst tree is empty", uv.processInput("exists test")); break;
			default: assertEquals("Error: bk queue is empty", uv.processInput("exists test")); break;
		}
	}
	
	public void testRemoveNothing(String struct) {
		switch (struct) {
			case "sk": assertEquals("Error: stack is empty", uv.processInput("remove test")); break;
			case "pv": assertEquals("Error: priority queue is empty", uv.processInput("remove test")); break;
			case "bst": assertEquals("Error: bst tree is empty", uv.processInput("remove test")); break;
			default: assertEquals("Error: bk queue is empty", uv.processInput("remove test")); break;
		}
		assertEquals("Error: please specify a string", uv.processInput("add"));
		switch (struct) {
			case "sk": assertEquals("Error: stack is empty", uv.processInput("remove test")); break;
			case "pv": assertEquals("Error: priority queue is empty", uv.processInput("remove test")); break;
			case "bst": assertEquals("Error: bst tree is empty", uv.processInput("remove test")); break;
			default: assertEquals("Error: bk queue is empty", uv.processInput("remove test")); break;
		}
	}
	
	public void testIsEmpty() {
		assertEquals("Yes", uv.processInput("isempty"));
		assertEquals("OK", uv.processInput("add test"));
		assertEquals("Not", uv.processInput("isempty"));
	}
	
	public void testExists() {
		testAddTestSequence();
		assertEquals("Not", uv.processInput("exists test"));
		assertEquals("Not", uv.processInput("exists \"hello world\""));
		assertEquals("Yes", uv.processInput("exists Test4"));
		assertEquals("Not", uv.processInput("exists test4"));
		assertEquals("Yes", uv.processInput("exists Test2"));
		assertEquals("Yes", uv.processInput("exists Test1"));
		assertEquals("Yes", uv.processInput("exists Test3"));
		assertEquals("Yes", uv.processInput("exists Test5"));		
	}
	
	public void testRemove() {
		testAddTestSequence();
		assertEquals("Error: element does not exists", uv.processInput("remove \"Hello world\""));
		assertEquals("Test1", uv.processInput("remove Test1"));
		assertEquals("Error: element does not exists", uv.processInput("remove test1"));
		assertEquals("Test5", uv.processInput("remove Test5"));
		assertEquals("Test3", uv.processInput("remove Test3"));
		assertEquals("Test2", uv.processInput("remove Test2"));
		assertEquals("Test4", uv.processInput("remove Test4"));
	}

	public void testGet() {
		assertEquals("OK", uv.processInput("add Test"));
		assertEquals("Test", uv.processInput("getfirst"));
	}

	public void testResetOnEmpty() {
		assertEquals("OK", uv.processInput("reset"));
	}

	public void testResetOnFull(String struct) {
		assertEquals("OK", uv.processInput("add Test"));
		assertEquals("OK", uv.processInput("reset"));
		switch (struct) {
			case "sk": assertEquals("Error: stack is empty", uv.processInput("removefirst")); break;
			case "pv": assertEquals("Error: priority queue is empty", uv.processInput("removefirst")); break;
			case "bst": assertEquals("Error: bst tree is empty", uv.processInput("removefirst")); break;
			default: assertEquals("Error: bk queue is empty", uv.processInput("removefirst")); break;
		}
		assertEquals("0", uv.processInput("count"));
	}

	public void testCountOnEmpty() {
		assertEquals("0", uv.processInput("count"));
	}

	public void testCountOne() {
		assertEquals("OK", uv.processInput("add Test"));
		assertEquals("1", uv.processInput("count"));
	}

	public void testCountTwo() {
		assertEquals("OK", uv.processInput("add Test1"));
		assertEquals("OK", uv.processInput("add Test2"));
		assertEquals("2", uv.processInput("count"));
	}

	public void testDepthOnEmpty() {
		assertEquals("0", uv.processInput("depth"));
	}

	public void testDepthOne(boolean bk) {
		assertEquals("OK", uv.processInput("add Test"));
		if(bk) {
			assertEquals("0", uv.processInput("depth"));
		} else {
			assertEquals("1", uv.processInput("depth"));
		}
	}

	public void testDepthTwo(boolean bk) {
		assertEquals("OK", uv.processInput("add Test1"));
		assertEquals("OK", uv.processInput("add Test2"));
		if(bk) {
			assertEquals("1", uv.processInput("depth"));
		} else {
			assertEquals("2", uv.processInput("depth"));
		}
	}

	public void testAddTestSequence() {
		assertEquals("OK", uv.processInput("add Test4"));
		assertEquals("OK", uv.processInput("add Test2"));
		assertEquals("OK", uv.processInput("add Test3"));
		assertEquals("OK", uv.processInput("add Test1"));
		assertEquals("OK", uv.processInput("add Test5"));
	}

	public void testSklad(boolean add) {
		if(add) testAddTestSequence();
		assertEquals("Test5", uv.processInput("removefirst"));
		assertEquals("Test1", uv.processInput("removefirst"));
		assertEquals("Test3", uv.processInput("removefirst"));
		assertEquals("Test2", uv.processInput("removefirst"));
		assertEquals("Test4", uv.processInput("removefirst"));
	}

	public void testPrioritetnaVrsta(boolean add) {
		if(add) testAddTestSequence();
		assertEquals("Test5", uv.processInput("removefirst"));
		assertEquals("Test4", uv.processInput("removefirst"));
		assertEquals("Test3", uv.processInput("removefirst")); 
		assertEquals("Test2", uv.processInput("removefirst"));
		assertEquals("Test1", uv.processInput("removefirst"));        
	}

	public void testBst(boolean add) {
		if(add) testAddTestSequence();
		assertEquals("Test4", uv.processInput("removefirst"));
		assertEquals("Test5", uv.processInput("removefirst"));
		assertEquals("Test2", uv.processInput("removefirst"));
		assertEquals("Test3", uv.processInput("removefirst"));
		assertEquals("Test1", uv.processInput("removefirst"));
	}
	
	public void testBinarnaKopica(boolean add) {
		if(add) testAddTestSequence();
		assertEquals("Test5", uv.processInput("removefirst"));
		assertEquals("Test4", uv.processInput("removefirst"));
		assertEquals("Test3", uv.processInput("removefirst"));
		assertEquals("Test2", uv.processInput("removefirst"));
		assertEquals("Test1", uv.processInput("removefirst"));
	}
	
	@Test
	public void testProcesInput_Use_NoAdditionalString() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("use"));
	}
	
	@Test
	public void testProcesInput_Use_BadAdditionalString() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("use asdf"));
	}
	
	@Test
	public void testProcesInput_Add_SeznamNull() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("add"));
	}

	@Test
	public void testProcessInput_Add_Duplicate() {
		assertEquals("OK", uv.processInput("use bst"));
		assertEquals("OK", uv.processInput("add test"));
		assertEquals("Error: element exists", uv.processInput("add test"));
	}

	@Test
	public void testProcesInput_RemoveFirst_SeznamNull() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("removefirst"));
	}

	@Test
	public void testProcesInput_GetFirst_SeznamNull() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("getfirst"));
	}

	@Test
	public void testProcesInput_Count_SeznamNull() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("count"));
	}

	@Test
	public void testProcesInput_Depth_SeznamNull() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("depth"));
	}

	@Test
	public void testProcesInput_IsEmpty_SeznamNull() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("isempty"));
	}

	@Test
	public void testProcesInput_Exists_SeznamNull() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("exists"));
	}

	@Test
	public void testProcesInput_Remove_SeznamNull() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("remove test"));
	}

	@Test
	public void testProcesInput_Reset_SeznamNull() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("reset"));
	}
	
	@Test
	public void testProcesInput_Print_SeznamNull() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("print"));
	}

	@Test
	public void testProcesInput_Save_SeznamNull() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("save"));
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("save test.bin"));
		assertEquals("OK", uv.processInput("use sk"));
		assertEquals("Error: please specify a file name", uv.processInput("save"));
	}
	
	@Test
	public void testProcesInput_Restore_SeznamNull() {
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("restore"));
		assertEquals("Error: please specify a data structure type (pv, sk, bst, bk)", uv.processInput("restore test.bin"));
		assertEquals("OK", uv.processInput("use sk"));
		assertEquals("Error: please specify a file name", uv.processInput("restore"));
	}

	@Test
	public void testProcesInput_BadCommand() {
		assertEquals("Error: bad operation", uv.processInput("asdf"));
		assertEquals("Error: bad operation", uv.processInput("asdf dela"));
		assertEquals("Error: bad operation", uv.processInput("asdf \"hello man\""));
		assertEquals("Error: bad operation", uv.processInput("\"hello\" add"));
		assertEquals("Error: No arguments", uv.processInput(""));
		assertEquals("Error: Bad arguments", uv.processInput(null));
	}
	
	@Test
	public void testExists_BadAdditionalString() {
		assertEquals("OK", uv.processInput("use sk"));
		assertEquals("Error: please specify a string", uv.processInput("exists"));
		assertEquals("Error: please specify a string", uv.processInput("exists \"hellll"));
	}
	
	@Test
	public void testRemove_BadAdditionalString() {
		assertEquals("OK", uv.processInput("use sk"));
		assertEquals("Error: please specify a string", uv.processInput("remove"));
		assertEquals("Error: please specify a string", uv.processInput("remove \"hellll"));
	}
	
	@Test
	public void testGetNextString_AddtionalStringTest() {
		assertEquals("OK", uv.processInput("use sk\"Kako si kaj\""));
		assertEquals("OK", uv.processInput("add \"asdf\""));
		assertEquals("OK", uv.processInput("add \"Hello my friend\""));
	}
	
	public void testPrint(boolean add) {
		if(add) testAddTestSequence();
		assertEquals("OK", uv.processInput("print"));
		if(add) assertEquals("5", uv.processInput("count"));
		else 	assertEquals("0", uv.processInput("count"));
	}
	
	public void testSaveRestore(boolean add) {
		if(add) testAddTestSequence();
		assertEquals("Error: please specify a file name", uv.processInput("save"));
		assertEquals("OK", uv.processInput("save test.bin"));
		assertEquals("OK", uv.processInput("reset"));
		assertEquals("0", uv.processInput("count"));
		assertEquals("Error: please specify a file name", uv.processInput("restore"));
		if(add) {
			assertEquals("OK", uv.processInput("restore test.bin"));
			assertEquals("5", uv.processInput("count"));
		} else {
			assertEquals("Error: IO error test.test (No such file or directory)", uv.processInput("restore test.test"));
			assertEquals("Error: IO error null", uv.processInput("restore test.bin"));
			assertEquals("0", uv.processInput("count"));
		}
	}
	
	public void testSaveRestore_Diff(String struct) {
		if(struct.equals("sk")) {
			assertEquals("OK", uv.processInput("use pv"));
			testAddTestSequence();
			assertEquals("OK", uv.processInput("save test.bin"));
		} else {
			assertEquals("OK", uv.processInput("use sk"));
			testAddTestSequence();
			assertEquals("OK", uv.processInput("save test.bin"));
		}
		assertEquals("OK", uv.processInput("use "+struct));
		assertEquals("OK", uv.processInput("restore test.bin"));
		assertEquals("OK", uv.processInput("print"));
	}
	
	@Test
	public void testSK_PrintSaveRestore() {
		assertEquals("OK", uv.processInput("use sk"));
		reset(); testPrint(false);
		reset(); testPrint(true);
		reset(); testSaveRestore(true);
		reset(); testSaveRestore(false);
		reset(); testSaveRestore_Diff("sk");
	}
	
	@Test
	public void testPV_PrintSaveRestore() {
		assertEquals("OK", uv.processInput("use pv"));
		reset(); testPrint(false);
		reset(); testPrint(true);
		reset(); testSaveRestore(true);
		reset(); testSaveRestore(false);
		reset(); testSaveRestore_Diff("pv");
	}
	
	@Test
	public void testBst_PrintSaveRestore() {
		assertEquals("OK", uv.processInput("use bst"));
		reset(); testPrint(false);
		reset(); testPrint(true);
		reset(); testSaveRestore(true);
		reset(); testSaveRestore(false);
		reset(); testSaveRestore_Diff("bst");
	}
	
	@Test
	public void testSaveForError() {
		assertEquals("OK", uv.processInput("use sk"));
		assertEquals("Error: IO error /hello (Permission denied)", uv.processInput("save /hello/"));
	}
}