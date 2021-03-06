package au.com.anz.rpncalculator.userenter.operator;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import au.com.anz.rpncalculator.fixture.RpnCalculatorTestFixture;
import au.com.anz.rpncalculator.history.record.OperationRecord;
import au.com.anz.rpncalculator.storage.DefaultStorage;
import au.com.anz.rpncalculator.storage.Storage;
import au.com.anz.rpncalculator.userenter.operator.Addition;
import au.com.anz.rpncalculator.userenter.operator.Clear;
import au.com.anz.rpncalculator.userenter.operator.Division;
import au.com.anz.rpncalculator.userenter.operator.Multiplication;
import au.com.anz.rpncalculator.userenter.operator.SquareRoot;
import au.com.anz.rpncalculator.userenter.operator.Subtraction;
import au.com.anz.rpncalculator.userenter.operator.Undo;

public class UndoTest {

	private Undo testInstance;
	
	@Before
	public void setUp() {
		this.testInstance = new Undo();
	}
	
	@After
	public void tearDown() {
		this.testInstance = null;
	}
	
	/**
	 * Given an element in history and stack
	 * When the execute method called
	 * The last digit operation should reverse
	 */
	@Test
	public void whenAnElmentProvidedIntoStorageThenUndoShouldSuccess() {
		//Given
		Storage storage = new DefaultStorage();
		BigDecimal userEntry = new BigDecimal(6);
		storage.pushDigit(userEntry);
		OperationRecord record = new OperationRecord(RpnCalculatorTestFixture.getOperationParameters(), null);
		storage.pushOperationRecord(record);
		//When the execute method called
		testInstance.execute(storage);
		//Then storage should be empty
		@SuppressWarnings("unchecked")
		Stack<BigDecimal> stack = (Stack<BigDecimal>) Whitebox.getInternalState(storage, "digitStack");
		assertNotNull(stack);
		assertTrue(stack.isEmpty());
	}
	
	/**
	 * Given an element in history and stack
	 * When the execute method called
	 * The last digit operation should reverse
	 */
	@Test
	public void whenAnOperatorElmentProvidedIntoStorageThenUndoShouldSuccess() {
		//Given
		Storage storage = new DefaultStorage();
		BigDecimal userEntry = new BigDecimal(8);
		storage.pushDigit(userEntry);
		OperationRecord record = new OperationRecord(RpnCalculatorTestFixture.get2OperationParameters(), new Addition());
		storage.pushOperationRecord(record);
		//When the execute method called
		testInstance.execute(storage);
		//Then storage should be empty
		@SuppressWarnings("unchecked")
		Stack<BigDecimal> stack = (Stack<BigDecimal>) Whitebox.getInternalState(storage, "digitStack");
		assertNotNull(stack);
		assertTrue(2 == stack.size());
		assertEquals(new BigDecimal(6), stack.get(0));
		assertEquals(new BigDecimal(2), stack.get(1));
	}
	
	/**
	 * Given an operator
	 * When the isNeedClearUpResult method called
	 * Then the flag should return
	 */
	@Test
	public void whenAnOperatorProvidedThenFlagShouldReturn() {
		assertTrue(Undo.isNeedClearUpResult().test(null));
		assertTrue(Undo.isNeedClearUpResult().test(new Addition()));
		assertTrue(Undo.isNeedClearUpResult().test(new Subtraction()));
		assertTrue(Undo.isNeedClearUpResult().test(new Multiplication()));
		assertTrue(Undo.isNeedClearUpResult().test(new Division()));
		assertTrue(Undo.isNeedClearUpResult().test(new SquareRoot()));
		assertTrue(Undo.isNeedClearUpResult().test(new Undo()));
		assertFalse(Undo.isNeedClearUpResult().test(new Clear()));
	}
	
	/**
	 * Given the testInstance
	 * When the getEmptyStackErrorMessage method called
	 * Then the correct error message should return
	 */
	@Test
	public void shouldReturnCorrectErrorMessage() {
		//Given
		int counter = 1;
		//When
		String message = this.testInstance.getEmptyStackErrorMessage(counter);
		//Then
		assertThat(message, is(notNullValue()));
		assertThat(message, is(equalTo("Operator: undo (position: 1): insufficient parameters")));
	}
}
