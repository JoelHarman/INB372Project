package guestbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Test.*;
import static org.junit.Assert.*;

//----------------------------------------------------------------------------
// JUnit test class. Checks to ensure that all methods function how they are
// supposed to, with the correct outputs for all situations.
//----------------------------------------------------------------------------
public class CalculatorTests {
	
	// Basic calculator for the test calculations
	private Calculator calc;
	
	@Test
	//-------------------------------------------------------------------------
	// Simple constructor test. Checks that a non-parameterised constructor
	// initialises correctly.
	//-------------------------------------------------------------------------
	public void simpleConstructorTest() {
		calc = new Calculator();
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Regular constructor test. Checks that a parameterised constructor works
	// and initialises itself correctly.
	//-------------------------------------------------------------------------
	public void regularConstructorTest() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, 0.13, 16, 0.1);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Checks to ensure that an empty list sums correctly to a zero value.
	//-------------------------------------------------------------------------
	public void testSumResultEmptyList() throws CalculatorException {
		double[] results = new double[0];
		calc = new Calculator();
		assertEquals(0, calc.sumResult(results), 0);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Checks to ensure that a positive list sums correctly.
	//-------------------------------------------------------------------------
	public void testSumResultPositiveList() throws CalculatorException {
		double[] results = {3, 5, 9, 1, 6};
		calc = new Calculator();
		assertEquals(24, calc.sumResult(results), 0);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Checks to ensure that a list with both positive and negative numbers
	// sums correctly.
	//-------------------------------------------------------------------------
	public void testSumResultMixedList() throws CalculatorException {
		double[] results = {-6.1, 5.35, 0.13, -8.9, 16.8};
		calc = new Calculator();
		assertEquals(7.28, calc.sumResult(results), 0.1);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Checks that calculating the average of an empty list throws an
	// exception, as there is no average.
	//-------------------------------------------------------------------------
	public void testCalculateAverageEmpty() throws CalculatorException {
		double[] results = {};
		calc = new Calculator();
		double average = calc.calculateDailyAverage(results);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Checks that calculating the average of an entirely positive list works
	// correctly.
	//-------------------------------------------------------------------------
	public void testCalculateAveragePositive() throws CalculatorException {
		double[] results = {3, 8, 6, 5, 3};
		calc = new Calculator();
		assertEquals(5 / 365.25, calc.calculateDailyAverage(results), 0);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Checks that calculating the average of a list containing both positive
	// and negative numbers works correctly.
	//-------------------------------------------------------------------------
	public void testCalculateAverageMixed() throws CalculatorException {
		double[] results = {3.85, -8, 16.1, 5.5, 3.7};
		calc = new Calculator();
		assertEquals(21.15 / (365.25 * 5), calc.calculateDailyAverage(results), 0.01);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Checks that a positive system power calculates the expected return
	// based on the given inputs.
	//-------------------------------------------------------------------------
	public void testCalculatePowerPositive() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, 0.13, 16, 0.1);
		double[] expectedResults = { 25141, 23381, 21744 };
		double[] actualResults = calc.getLifetimeElectricityGeneration(5000);
		assertEquals(expectedResults[0], actualResults[0], 0.01);
		assertEquals(expectedResults[1], actualResults[1], 0.01);
		assertEquals(expectedResults[2], actualResults[2], 0.01);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Checks that a zero power system calculates the expected return based
	// on the given inputs.
	//-------------------------------------------------------------------------
	public void testCalculateZeroPower() throws CalculatorException {
		calc = new Calculator(0, 0, 10,7, 0.13, 16, 0.1);
		double[] expectedResults = { 0, 0, 0 };
		double[] actualResults = calc.getLifetimeElectricityGeneration(0);
		assertEquals(expectedResults[0], actualResults[0], 0.01);
		assertEquals(expectedResults[1], actualResults[1], 0.01);
		assertEquals(expectedResults[2], actualResults[2], 0.01);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Checks that a negative power system throws an exception, as it cannot
	// exist.
	//-------------------------------------------------------------------------
	public void testCalculateNegativePower() throws CalculatorException {
		calc = new Calculator(-10, 0, 10,7, 0.13, 16, 0.1);
		double[] actualResults = calc.getLifetimeElectricityGeneration(0);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Checks that a positive power generates the correct savings based on
	// the various inputs.
	//-------------------------------------------------------------------------
	public void testCalculateSavingsPositive() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, 0.13, 16, 0.1);
		double[] expectedResults = { 3.26833, 3.03953, 2.8267 };
		double[] power = calc.getLifetimeElectricityGeneration(5000);
		double[] actualResults = calc.getLifetimeCostSavings(power);
		assertEquals(expectedResults[0], actualResults[0], 0.01);
		assertEquals(expectedResults[1], actualResults[1], 0.01);
		assertEquals(expectedResults[2], actualResults[2], 0.01);
	}

	@Test
	//-------------------------------------------------------------------------
	// Checks that a zero power generates the correct savings based on the
	// various inputs.
	//-------------------------------------------------------------------------
	public void testCalculateSavingsZero() throws CalculatorException {
		calc = new Calculator(0, 0, 10,7, 0.13, 16, 0.1);
		double[] expectedResults = { 0, 0, 0 };
		double[] power = calc.getLifetimeElectricityGeneration(0);
		double[] actualResults = calc.getLifetimeCostSavings(power);
		assertEquals(expectedResults[0], actualResults[0], 0.01);
		assertEquals(expectedResults[1], actualResults[1], 0.01);
		assertEquals(expectedResults[2], actualResults[2], 0.01);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Checks to ensure that an exception is thrown if a negative power input
	// is given for savings.
	//-------------------------------------------------------------------------
	public void testCalculateSavingsNegativePower() throws CalculatorException {
		calc = new Calculator(-10, 0, 10,7, 0.13, 16, 0.1);
		double[] expectedResults = { 0, 0, 0 };
		double[] power = calc.getLifetimeElectricityGeneration(0);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Checks to ensure that an exception is thrown if savings calculations are
	// trying to be performed on a negative input tariff.
	//-------------------------------------------------------------------------
	public void testCalculateSavingsNegativeTariff() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, -0.13, 16, 0.1);
		double[] expectedResults = { 0, 0, 0 };
		double[] power = calc.getLifetimeElectricityGeneration(0);
		double[] actualResults = calc.getLifetimeCostSavings(power);
		assertEquals(expectedResults[0], actualResults[0], 0.01);
		assertEquals(expectedResults[1], actualResults[1], 0.01);
		assertEquals(expectedResults[2], actualResults[2], 0.01);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Checks to ensure that an exception is thrown if an empty list is parsed
	// in for cost savings calculations.
	//-------------------------------------------------------------------------
	public void testCalculateSavingsZeroList() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, -0.13, 16, 0.1);
		double[] expectedResults = {};
		double[] power = {};
		double[] actualResults = calc.getLifetimeCostSavings(power);
		assertEquals(true, actualResults.length == 0);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Checks to ensure that an exception is thrown if a null list is parsed
	// in for savings calculations.
	//-------------------------------------------------------------------------
	public void testCalculateSavingsNullList() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, -0.13, 16, 0.1);
		double[] actualResults = calc.getLifetimeCostSavings(null);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Checks to ensure that if a regular investment is entered, the results
	// are calculated correctly according to the given inputs.
	//-------------------------------------------------------------------------
	public void testBankInvestmentPositiveInvest() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, -0.13, 16, 0.1);
		double expectedResult = 39.8;
		double actualResult = calc.getInvestmentReturnFromBank(1.05);
		assertEquals(expectedResult, actualResult, 0.1);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Checks to ensure that if a flat investment is entered, the results
	// remain the same, despite the various inputs.
	//-------------------------------------------------------------------------
	public void testBankInvestmentZeroInvest() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, -0.13, 16, 0.1);
		double expectedResult = 15;
		double actualResult = calc.getInvestmentReturnFromBank(1.0);
		assertEquals(expectedResult, actualResult, 0.1);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Checks to ensure that if a zeroed investment is entered, all values
	// drop to zero accordingly.
	//-------------------------------------------------------------------------
	public void testBankInvestmentZeroTotalInvest() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, -0.13, 16, 0.1);
		double expectedResult = 0;
		double actualResult = calc.getInvestmentReturnFromBank(0.0);
		assertEquals(expectedResult, actualResult, 0.1);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a negative investment is entered, an exception
	// is thrown, as a negative investment modifier in nonsensical.
	//-------------------------------------------------------------------------
	public void testBankInvestmentNegativeInvest() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, -0.13, 16, 0.1);
		double expectedResult = 0;
		double actualResult = calc.getInvestmentReturnFromBank(-1.0);
		assertEquals(expectedResult, actualResult, 0.1);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Check to ensure that if regular values are entered for a bank investment,
	// each of the yearly snapshots are recorded correctly.
	//-------------------------------------------------------------------------
	public void testYearlyBankInvestmentPositiveInvest() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, -0.13, 16, 0.1);
		double[] expectedResults = { 15, 15.75, 16.5375 };
		double[] actualResults = calc.getYearlyInvestmentFromBank(1.05);
		assertEquals(expectedResults[0], actualResults[0], 0.01);
		assertEquals(expectedResults[1], actualResults[1], 0.01);
		assertEquals(expectedResults[2], actualResults[2], 0.01);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Check to ensure that if a zero investment is entered, each of the yearly
	// snapshots remain the same (regardless of inputs).
	//-------------------------------------------------------------------------
	public void testYearlyBankInvestmentZeroInvest() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, -0.13, 16, 0.1);
		double[] expectedResults = { 15, 15, 15 };
		double[] actualResults = calc.getYearlyInvestmentFromBank(1.0);
		assertEquals(expectedResults[0], actualResults[0], 0.01);
		assertEquals(expectedResults[1], actualResults[1], 0.01);
		assertEquals(expectedResults[2], actualResults[2], 0.01);
	}
	
	@Test 
	//-------------------------------------------------------------------------
	// Check to ensure that if a zero investment is entered, each snapshot,
	// besides from the initial, all return zeroed values.
	//-------------------------------------------------------------------------
	public void testYearlyBankInvestmentZeroTotalInvest() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, -0.13, 16, 0.1);
		double[] expectedResults = { 15, 0, 0 };
		double[] actualResults = calc.getYearlyInvestmentFromBank(0);
		assertEquals(expectedResults[0], actualResults[0], 0.01);
		assertEquals(expectedResults[1], actualResults[1], 0.01);
		assertEquals(expectedResults[2], actualResults[2], 0.01);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a negative investment modifier is given, an
	// exception is thrown as it is nonsensical.
	//-------------------------------------------------------------------------
	public void testYearlyBankInvestmentNegativeInvest() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, -0.13, 16, 0.1);
		double[] expectedResults = { 0, 0, 0 };
		double[] actualResults = calc.getYearlyInvestmentFromBank(-1.0);
		assertEquals(expectedResults[0], actualResults[0], 0.01);
		assertEquals(expectedResults[1], actualResults[1], 0.01);
		assertEquals(expectedResults[2], actualResults[2], 0.01);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Check to ensure that if a regular savings structure is entered, the
	// panel investment returns the appropriate values.
	//-------------------------------------------------------------------------
	public void testYearlyPanelInvestmentPositiveInvest() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, 0.13, 16, 0.1);
		double[] expectedResults = { -11.73, -8.69, -5.865 };
		double[] power = calc.getLifetimeElectricityGeneration(10);
		double[] savings = calc.getLifetimeCostSavings(power);
		double[] actualResults = calc.getYearlyInvestmentFromPanels(savings);
		assertEquals(expectedResults[0], actualResults[0], 0.01);
		assertEquals(expectedResults[1], actualResults[1], 0.01);
		assertEquals(expectedResults[2], actualResults[2], 0.01);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if an empty savings list is parsed for calculation,
	// an exception is thrown as there is no investment to manage.
	//-------------------------------------------------------------------------
	public void testYearlyPanelInvestmentEmptyList() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, 0.13, 16, 0.1);
		double[] savings = { };
		double[] actualResults = calc.getYearlyInvestmentFromPanels(savings);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a null list is parsed in for panel calculations,
	// an exception is thrown as there is nothing to calculate.
	//-------------------------------------------------------------------------
	public void testYearlyPanelInvestmentNullList() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, 0.13, 16, 0.1);
		double[] actualResults = calc.getYearlyInvestmentFromPanels(null);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Check to ensure that if a break-even time is going to occur, it will
	// be calculated correctly (down to the year).
	//-------------------------------------------------------------------------
	public void testBreakEvenWithPossibleBreak() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, 0.13, 16, 0.1);
		double expectedResults = 6;
		double[] power = calc.getLifetimeElectricityGeneration(10);
		double[] savings = calc.getLifetimeCostSavings(power);
		double actualResults = calc.calculateBreakEven(savings);
		assertEquals(expectedResults, actualResults, 0.01);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Check to ensure that if a break-even time is NOT going to occur, it will
	// return a token value (-1) to represent this result.
	//-------------------------------------------------------------------------
	public void testBreakEvenWithNoBreak() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, 0.013, 16, 0.1);
		double expectedResults = -1;
		double[] power = calc.getLifetimeElectricityGeneration(10);
		double[] savings = calc.getLifetimeCostSavings(power);
		double actualResults = calc.calculateBreakEven(savings);
		assertEquals(expectedResults, actualResults, 0.01);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Check to ensure that if the system will only JUST break even in the
	// system life, it will still show the appropriate break-even time.
	//-------------------------------------------------------------------------
	public void testBreakEvenWithJustBreak() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, 0.0559, 16, 0.1);
		double expectedResults = 19;
		double[] power = calc.getLifetimeElectricityGeneration(10);
		double[] savings = calc.getLifetimeCostSavings(power);
		double actualResults = calc.calculateBreakEven(savings);
		assertEquals(expectedResults, actualResults, 0.01);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if an empty savings list is parsed in for 
	// break-even calculations, an exception is thrown.
	//-------------------------------------------------------------------------
	public void testBreakEvenWithEmptyList() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, 0.0559, 16, 0.1);
		double[] savings = {};
		double actualResults = calc.calculateBreakEven(savings);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a null list is parsed in for break-even
	// calculations, an exception is thrown.
	//-------------------------------------------------------------------------
	public void testBreakEvenWithNullList() throws CalculatorException {
		calc = new Calculator(10, 0, 10,7, 0.0559, 16, 0.1);
		double actualResults = calc.calculateBreakEven(null);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Check to ensure that if excess earnings are likely to occur in the
	// product life, they will be calculated correctly.
	//-------------------------------------------------------------------------
	public void testExcessEarningsPositive() throws CalculatorException {
		calc = new Calculator(5000, 0, 10,7, 0.5, 16, 0.5);
		double[] expectedResults = {3363.13, 2923.17, 2514.01};
		double[] power = calc.getLifetimeElectricityGeneration(10);
		double[] actualResults = calc.calculateExcessPowerEarnings(power);
		assertEquals(expectedResults[0], actualResults[0], 0.01);
		assertEquals(expectedResults[1], actualResults[1], 0.01);
		assertEquals(expectedResults[2], actualResults[2], 0.01);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Check to ensure that if NO excess earnings are likely to occur in the 
	// product life, they will be zeroed correctly.
	//-------------------------------------------------------------------------
	public void testExcessEarningsZero() throws CalculatorException {
		calc = new Calculator(500, 0, 10,7, 0.5, 16, 0.1);
		double[] expectedResults = {0, 0, 0};
		double[] power = calc.getLifetimeElectricityGeneration(10);
		double[] actualResults = calc.calculateExcessPowerEarnings(power);
		assertEquals(expectedResults[0], actualResults[0], 0.01);
		assertEquals(expectedResults[1], actualResults[1], 0.01);
		assertEquals(expectedResults[2], actualResults[2], 0.01);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Check to ensure that if the system will generate money for each year
	// over its lifetime, it will still calculate correctly.
	//-------------------------------------------------------------------------
	public void testExcessEarningsComplete() throws CalculatorException {
		calc = new Calculator(10000, 0, 10,7, 0.5, 16, 500);
		double[] expectedResults = {9648261.5, 8768343, 7950019};
		double[] power = calc.getLifetimeElectricityGeneration(10);
		double[] actualResults = calc.calculateExcessPowerEarnings(power);
		assertEquals(expectedResults[0], actualResults[0], 0.01);
		assertEquals(expectedResults[1], actualResults[1], 0.01);
		assertEquals(expectedResults[2], actualResults[2], 0.01);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a null value is entered for inputs, an exception
	// will be thrown appropriately.
	//-------------------------------------------------------------------------
	public void testExcessEarningsNullInput() throws CalculatorException {
		calc = new Calculator(10000, 0, 10,7, 0.5, 16, 500);
		double[] actualResults = calc.calculateExcessPowerEarnings(null);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if an empty list is given for excess earnings,
	// an exception will be thrown accordingly.
	//-------------------------------------------------------------------------
	public void testExcessEarningsEmptyList() throws CalculatorException {
		calc = new Calculator(10000, 0, 10,7, 0.5, 16, 500);
		double[] power = {};
		double[] actualResults = calc.calculateExcessPowerEarnings(power);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Check to ensure that if a regular excess power generation is calculated,
	// the percentage of total power will be calculated as expected.
	//-------------------------------------------------------------------------
	public void testPercentageGenerationRegular() throws CalculatorException {
		calc = new Calculator(2000, 0, 10,7, 0.5, 50, 0.5);
		double expectedResults = 15.06;
		double[] power = calc.getLifetimeElectricityGeneration(10);
		double actualResults = calc.getPanelGenerationPercent(power);
		assertEquals(expectedResults, actualResults, 0.01);
	}
	
	@Test
	//-------------------------------------------------------------------------
	// Check to ensure that if the percentage of power generation is high (>100%)
	// it will still calculate correctly.
	//-------------------------------------------------------------------------
	public void testPercentageGenerationHigh() throws CalculatorException {
		calc = new Calculator(10000, 0, 10,7, 0.5, 16, 500);
		double expectedResults = 235.3;
		double[] power = calc.getLifetimeElectricityGeneration(10);
		double actualResults = calc.getPanelGenerationPercent(power);
		assertEquals(expectedResults, actualResults, 0.01);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a percentage generation is attempted to be
	// calculated on an empty list, it will throw an exception accordingly.
	//-------------------------------------------------------------------------
	public void testPercentageGenerationEmptyList() throws CalculatorException {
		calc = new Calculator(2000, 0, 10,7, 0.5, 50, 0.5);
		double[] power = {};
		double actualResults = calc.getPanelGenerationPercent(power);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a null input is provided for percentage
	// generation, it will throw an exception accordingly.
	//-------------------------------------------------------------------------
	public void testPercentageGenerationNullList() throws CalculatorException {
		calc = new Calculator(2000, 0, 10,7, 0.5, 50, 0.5);
		double actualResults = calc.getPanelGenerationPercent(null);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a null pointer is provided when trying to sum
	// a result, it will throw an exception accordingly.
	//-------------------------------------------------------------------------
	public void testSumResultNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.sumResult(null);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a break even is attempted to be calculated with
	// a null savings value, an exception will be thrown accordingly.
	//-------------------------------------------------------------------------
	public void testCalculateBreakEvenNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.calculateBreakEven(null);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a daily average is tried to be calculated with a
	// null list, an exception will be thrown accordingly.
	//-------------------------------------------------------------------------
	public void testCalculateDailyAverageNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.calculateDailyAverage(null);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a null input is provided for excess power
	// earnings, it will throw an exception accordingly.
	//-------------------------------------------------------------------------
	public void testCalculateExcessPowerEarningsNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.calculateExcessPowerEarnings(null);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a null input is provided when trying to calculate
	// excess power generation, it will throw an exception accordingly.
	//-------------------------------------------------------------------------
	public void testCalculateExcessPowerGenerationNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.calculateExcessPowerGeneration(null);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a null input is provided when trying to calculate
	// investment return, an exception will be thrown accordingly.
	//-------------------------------------------------------------------------
	public void testGetInvestmentReturnFromPanelsNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.getIvestmentReturnFromPanels(null);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a null input is provided when trying to calculate
	// total carbon savings, an exception will be thrown accordingly.
	//-------------------------------------------------------------------------
	public void testGetLifetimeCarbonSavingsNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.getLifetimeCarbonSavings(null);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a null input is provided when trying to
	// calculate lifetime savings, an exception will be thrown accordingly.
	//-------------------------------------------------------------------------
	public void testGetLifetimeCostSavingsNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.getLifetimeCostSavings(null);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a null input is provided when trying to calculate
	// panel generation percentage of total, an exception will be thrown accordingly.
	//-------------------------------------------------------------------------
	public void testGetPanelGenerationPercentNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.getPanelGenerationPercent(null);
	}
	
	@Test (expected=CalculatorException.class)
	//-------------------------------------------------------------------------
	// Check to ensure that if a null input is provided when trying to
	// calculate yearly panel investments, an exception will be thrown accordingly.
	//-------------------------------------------------------------------------
	public void testGetYearlyInvestmentFromPanelsNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.getYearlyInvestmentFromPanels(null);
	}
}
