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
	
	@Test (expected=CalculatorException.class)
	public void testCalculateDailyAverageDivideByZero() throws CalculatorException {
		/* @TODO
		 * Write test for divide by zero
		 */
	}
}
