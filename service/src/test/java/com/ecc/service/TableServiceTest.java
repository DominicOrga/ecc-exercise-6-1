package com.ecc.service;

import static org.assertj.core.api.Assertions.*;

import com.ecc.util.Utility;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Optional;
import java.io.IOException;
import java.io.FileNotFoundException;

public class TableServiceTest {

	@Test
	public void givenANullTableWhenGetTableAsStringThenValuesAreNULL() 
		throws IOException, FileNotFoundException {
		
		Optional<String> resourcePath = Utility.getResourcePath("pure_null_table.txt");
		TableService tableService = new TableServiceImpl(resourcePath.get());

		String tableString = tableService.getTableAsString();
		String expectedValue = "NULL/NULL/NULL/NULL\n" +
							   "NULL/NULL/NULL/NULL\n" +
							   "NULL/NULL/NULL/NULL";

		assertThat(tableString).isEqualTo(expectedValue);
	}

	@Test
	public void givenANonNullTableWhenGetTableAsStringThenValuesArePersisted() 
		throws IOException, FileNotFoundException {

		Optional<String> resourcePath = Utility.getResourcePath("non_null_table.txt");
		TableService tableService = new TableServiceImpl(resourcePath.get());

		String tableString = tableService.getTableAsString();
		String expectedValue = "a,a/aa,aa/aaa,aaa\n" +
							   "b,b/bb,bb/bbb,bbb\n" +
							   "c,c/cc,cc/ccc,ccc";

		assertThat(tableString).isEqualTo(expectedValue);
	}

	@Test
	@Ignore
	public void givenAnEmptyFileWhenGetRowCountThenReturnOne() {}

	@Test
	@Ignore
	public void givenAnEmptyFileWhenGetColCountThenReturnOne() {}


}