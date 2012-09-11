package guestbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Test.*;
import static org.junit.Assert.*;

// Simple JUnit class to ensure that the methods can be tested correctly.
public class CalculatorTests {
	
	private Calculator calc;
	
	@Test
	public void constructorTest() {
	}
	
	@Test
	public void testSumResultEmptyList() throws CalculatorException {
		double[] results = new double[0];
		calc = new Calculator();
		assertEquals(0, calc.sumResult(results), 0);
	}
	
	@Test
	public void testSumResultPositiveList() throws CalculatorException {
		double[] results = {3, 5, 9, 1, 6};
		calc = new Calculator();
		assertEquals(24, calc.sumResult(results), 0);
	}
	
	@Test
	public void testSumResultMixedList() throws CalculatorException {
		double[] results = {-6.1, 5.35, 0.13, -8.9, 16.8};
		calc = new Calculator();
		assertEquals(7.28, calc.sumResult(results), 0.1);
	}
	
	@Test (expected=CalculatorException.class)
	public void testCalculateAverageEmpty() throws CalculatorException {
		double[] results = {};
		calc = new Calculator();
		double average = calc.calculateDailyAverage(results);
	}
	
	@Test
	public void testCalculateAveragePositive() throws CalculatorException {
		double[] results = {3, 8, 6, 5, 3};
		calc = new Calculator();
		assertEquals(5 / 365.25, calc.calculateDailyAverage(results), 0);
	}
	
	@Test
	public void testCalculateAverageMixed() throws CalculatorException {
		double[] results = {3.85, -8, 16.1, 5.5, 3.7};
		calc = new Calculator();
		assertEquals(21.15 / (365.25 * 5), calc.calculateDailyAverage(results), 0.01);
	}
	
	
	
	
	
	@Test (expected=CalculatorException.class)
	public void testSumResultNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.sumResult(null);
	}
	
	@Test (expected=CalculatorException.class)
	public void testCalculateBreakEvenNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.calculateBreakEven(null);
	}
	
	@Test (expected=CalculatorException.class)
	public void testCalculateDailyAverageNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.calculateDailyAverage(null);
	}
	
	@Test (expected=CalculatorException.class)
	public void testCalculateExcessPowerEarningsNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.calculateExcessPowerEarnings(null);
	}
	
	@Test (expected=CalculatorException.class)
	public void testCalculateExcessPowerGenerationNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.calculateExcessPowerGeneration(null);
	}
	
	@Test (expected=CalculatorException.class)
	public void testGetInvestmentReturnFromPanelsNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.getIvestmentReturnFromPanels(null);
	}
	
	@Test (expected=CalculatorException.class)
	public void testGetLifetimeCarbonSavingsNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.getLifetimeCarbonSavings(null);
	}
	
	@Test (expected=CalculatorException.class)
	public void testGetLifetimeCostSavingsNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.getLifetimeCostSavings(null);
	}
	
	@Test (expected=CalculatorException.class)
	public void testGetPanelGenerationPercentNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.getPanelGenerationPercent(null);
	}
	
	@Test (expected=CalculatorException.class)
	public void testGetYearlyInvestmentFromPanelsNullPointer() throws CalculatorException {
		calc = new Calculator(1,1,1,1,1,1,1);
		calc.getYearlyInvestmentFromPanels(null);
	}
	
	/* @TODO
	 * Write test for divide by zero
	@Test (expected=CalculatorException.class)
	public void testCalculateDailyAverageDivideByZero() throws CalculatorException {
	}
	 */
}
