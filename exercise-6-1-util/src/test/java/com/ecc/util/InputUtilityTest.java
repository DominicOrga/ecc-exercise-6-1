package com.ecc.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.After;

import java.util.Optional;
import java.io.ByteArrayInputStream;

public class InputUtilityTest {

	@Test
	public void givenAValidStringInputWhenRequestStringThenReturnString() {
		String stringInput = "My Input String";

		ByteArrayInputStream in = new ByteArrayInputStream(stringInput.getBytes());
		System.setIn(in);

		Optional<String> str = InputUtility.nextString("message");
	
		assertThat(str.get()).isEqualTo(stringInput);
	}

	@Test
	public void givenAnInvalidStringInputWhenRequestStringThenReturnEmpty() {
		String stringInput = "My Input String";
		String[] invalidSubstring = { "Input" };

		ByteArrayInputStream in = new ByteArrayInputStream(stringInput.getBytes());
		System.setIn(in);

		Optional<String> str = InputUtility.nextString("message", invalidSubstring);

		assertThat(str.isPresent()).isFalse();
	}

	@Test
	public void givenAValidIntegerInputWhenRequestIntegerThenReturnInteger() {
		String intInput = "4";

		ByteArrayInputStream in = new ByteArrayInputStream(intInput.getBytes());
		System.setIn(in);

		Optional<Integer> input = InputUtility.nextInt("message");

		assertThat(input.get()).isEqualTo(Integer.parseInt(intInput));
	}

	@Test
	public void givenMaxIsLessThanMinWhenRequestIntegerThenReturnEmpty() {
		String intInput = "4";
		int min = 6;
		int max = 4;

		ByteArrayInputStream in = new ByteArrayInputStream(intInput.getBytes());
		System.setIn(in);

		Optional<Integer> input = InputUtility.nextInt("message", min, max);

		assertThat(input.isPresent()).isFalse();	
	}

	@Test
	public void givenANonIntegerInputWhenRequestIntegerThenReturnEmpty() {
		String intInput = "Non-String Input";

		ByteArrayInputStream in = new ByteArrayInputStream(intInput.getBytes());
		System.setIn(in);

		Optional<Integer> input = InputUtility.nextInt("message");

		assertThat(input.isPresent()).isFalse();		
	}

	@Test
	public void givenTrueSymbolWhenRequestBoolThenReturnTrue() {
		String trueSymbol = "y";
		String falseSymbol = "n";

		ByteArrayInputStream in = new ByteArrayInputStream(trueSymbol.getBytes());
		System.setIn(in);

		Optional<Boolean> input = InputUtility.nextBool("message", trueSymbol, falseSymbol);

		assertThat(input.get()).isTrue();
	}

	@Test
	public void givenFalseSymbolWhenRequestBoolThenReturnFalse() {
		String trueSymbol = "y";
		String falseSymbol = "n";

		ByteArrayInputStream in = new ByteArrayInputStream(falseSymbol.getBytes());
		System.setIn(in);

		Optional<Boolean> input = InputUtility.nextBool("message", trueSymbol, falseSymbol);

		assertThat(input.get()).isFalse();	
	}

	@Test
	public void givenInvalidSymbolWhenRequestBoolThenReturnEmpty() {
		String trueSymbol = "y";
		String falseSymbol = "n";

		ByteArrayInputStream in = new ByteArrayInputStream("a".getBytes());
		System.setIn(in);

		Optional<Boolean> input = InputUtility.nextBool("message", trueSymbol, falseSymbol);

		assertThat(input.isPresent()).isFalse();
	}

	@After
	public void resetSystemIn() {
		System.setIn(System.in);
	}
}