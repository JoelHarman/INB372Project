package guestbook;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Calculator class models a solar power calculator which is provided with
 * various system settings. Using these settings, the calculator is able
 * to perform a variety of calculations to provide detailed information
 * about the system.
 * 
 * Version: 1.0
 */
public class Calculator {
	
	// Calculator Constants
	private double CARBON_SAVINGS_PER_WATT = .0020358;
	private double POWER_LOSS_BY_FACING_DEGREE = 0.00167;
	private double YEARLY_PANEL_DEGREDATION = 0.93;
	private int LIFESPAN = 20;
	private double DAYS_IN_YEAR = 365.25;
	private double SYSTEM_COST_PER_WATT = 1.5;
	private double COMPARISON_INTEREST_RATE = 1.05;
	
	// The initial wattage of the panel (in watts).
	private double panelWattage;
	
	// The angle of installation (ground is 0 degrees).
	private double angleOfInstallation;
	
	// The angle of facing (north is 0 degrees).
	private double panelDegreeFacing;

	// Hours of daylight.
	private double hoursOfDaylight;
	
	// The cost of electricity (per Kilowatt).
	private double savingsPerKilowatt;
	
	// The cost, in dollars, of the specified system.
	private double systemCost;
	
	// The energy feed in tariff (per Watt).
	private double feedInTariff;
	
	// The cost of electricity (per watt).
	private double costPerWatt;
	
	// The total electricity used by the household.
	private double electricityGeneration;
	
	/*
	 * Calculator constructor. Stores all user inputs to allow for calculations
	 * to be performed.
	 */
	public Calculator(double watts, double elevationAngle, double facingAngle, double daylight, double electricityCost, double dailyUsage, double tariff) throws CalculatorException {
		// Probably needs some input checking
		if (false) throw new CalculatorException("Exception!");
		panelWattage = watts;
		angleOfInstallation = elevationAngle;
		panelDegreeFacing = facingAngle;
		hoursOfDaylight = daylight;
		systemCost = SYSTEM_COST_PER_WATT * watts;
		costPerWatt = electricityCost / 1000;
		feedInTariff = tariff / 1000;
		electricityGeneration = dailyUsage * DAYS_IN_YEAR * 1000;
	}
	
	public Calculator() {
	}
	
	/*
	 * Takes the yearly figures from a result and converts it into a single
	 * total for the lifetime of the product.
	 * 
	 * Precondition: None.
	 * Postcondition: The list will be summed and the result returned.
	 */
	public double sumResult(double[] result) throws CalculatorException {
		if (result == null) throw new CalculatorException("Provided result is null");
		double sum = 0;
		for (int i = 0; i < result.length; i++) {
			sum += result[i];
		}
		return sum;
	}
	
	/*
	 * Takes the yearly figures for a result and converts it into a daily
	 * average for the lifetime of the product.
	 * 
	 * Precondition: A list of yearly figures is given.
	 * Postcondition: The average daily figure is returned.
	 */
	public double calculateDailyAverage(double[] result) throws CalculatorException {
		if (result == null) throw new CalculatorException("Result is null");
		if (result.length <= 0) throw new CalculatorException("Result length cannot be zero: divide by zero error.");
		double total = sumResult(result);
		double dailyAverage = total / (DAYS_IN_YEAR * result.length);
		return dailyAverage;
	}
	
	/* 
	 * Returns a list of the total electricity generation over the lifetime of
	 * the product, using the power of the unit and its installation properties. 
	 * 
	 * Precondition: The unit power (in watts) is specified.
	 * Postcondition: A list of yearly expected generations is returned.
	 */
	public double[] getLifetimeElectricityGeneration(double unitPower) throws CalculatorException {
		if (false) throw new CalculatorException("Math.");
		if (panelWattage < 0) throw new CalculatorException("Wattage cannot be negative.");
		double[] electricity = new double[LIFESPAN];
		for (int i = 0; i < LIFESPAN; i++) {
			electricity[i] = Math.round(getElectricityGeneration(panelWattage, i));
		}
		return electricity;
	}
	
	/*
	 * Returns a list of the total yearly cost savings for the lifetime of the
	 * system, using the power of the system and the cost of power.
	 * 
	 * Precondition: A list of yearly power generations, and the cost of power is given.
	 * Postcondition: A list representing total yearly expected savings is returned.
	 */
	public double[] getLifetimeCostSavings(double[] powerGeneration) throws CalculatorException {
		if (powerGeneration == null)
			throw new CalculatorException("powerGeneration is null");
		
		if (costPerWatt < 0)
			throw new CalculatorException("Power cost must be non-negative.");
		double[] costSavings = new double[LIFESPAN];

		if (powerGeneration.length != costSavings.length)
			throw new CalculatorException("powerGeneration and costSavings arrays are of different lengths");

		for (int i = 0; i < LIFESPAN; i++) {
			costSavings[i] = powerGeneration[i] * costPerWatt;
		}
		return costSavings;
	}
	
	/*
	 * Returns a list of total yearly carbon savings over the lifetime of the
	 * system, using the list of power estimates of the system.
	 * 
	 * Precondition: A list of yearly power savings is given.
	 * Postcondition: A list of yearly carbon savings is returned.
	 */
	public double[] getLifetimeCarbonSavings(double[] powerGeneration) throws CalculatorException {
		if (powerGeneration == null)
			throw new CalculatorException("powerGeneration is null");
		
		double[] carbonSavings = new double[LIFESPAN];

		if (powerGeneration.length != carbonSavings.length)
			throw new CalculatorException("powerGeneration and carbonSavings arrays are of different lengths");

		for (int i = 0; i < LIFESPAN; i++) {
			carbonSavings[i] = powerGeneration[i] * CARBON_SAVINGS_PER_WATT;
		}
		return carbonSavings;
	}

	/*
	 * Returns the estimated return on investment from a specified interest rate.
	 * 
	 * Precondition: Takes an interest rate (E.g. 1.05 is 5%).
	 * Postcondition: Returns the new total money from the investment.
	 */
	public double getInvestmentReturnFromBank(double interestRate) throws CalculatorException {
		if (interestRate < 0) throw new CalculatorException("Investment rate must be positive.");
		return systemCost * Math.pow(interestRate, LIFESPAN);
	}
	
	public double[] getYearlyInvestmentFromBank(double interestRate) throws CalculatorException {
		if (interestRate < 0) throw new CalculatorException("Investment rate must be positive.");
		double[] returns = new double[LIFESPAN];
		returns[0] = systemCost;
		for (int i = 1; i < LIFESPAN; i++) {
			returns[i] = returns[i - 1] * interestRate;
		}
		return returns;
	}
	
	/*
	 * Returns the total return on investment from the solar panels, also
	 * considering the cost of the install.
	 * 
	 * Precondition: A list of yearly panel savings is given.
	 * Postcondition: A value representing the total investment return (subtracting
	 * the cost of the panels) is returned.
	 */
	public double getIvestmentReturnFromPanels(double[] savings) throws CalculatorException {
		if (savings == null)
			throw new CalculatorException("savings array is null");
		
		double[] yearlyReturns = getYearlyInvestmentFromPanels(savings);
		if (yearlyReturns == null)
			throw new CalculatorException("yearlyReturns is null");
		
		return yearlyReturns[LIFESPAN - 1];
	}
	
	public double[] getYearlyInvestmentFromPanels(double[] savings) throws CalculatorException {
		if (savings == null)
			throw new CalculatorException("savings array is null");

		if (savings.length < 1)
			throw new CalculatorException("savings array is empty");

		double[] returns = new double[LIFESPAN];

		if (returns.length != savings.length)
			throw new CalculatorException("savings and returns arrays are of different lengths");

		returns[0] = -systemCost + savings[0];
		
		for (int i = 1; i < LIFESPAN; i++) {
			returns[i] = returns[i - 1] + savings[i];
			if (returns[i] > 0) returns[i] *= COMPARISON_INTEREST_RATE;
		}
		return returns;
	}
	
	/*
	 * Calculates the break-even time (in years), for the product to pay
	 * itself off.
	 * 
	 * Precondition: A list of yearly savings is given.
	 * Postcondition: Returns a value representing the number of years needed
	 * for the product to break even from setup costs.
	 */
	public double calculateBreakEven(double[] savings) throws CalculatorException {
		if (savings == null)
			throw new CalculatorException("savings is null");
		
		if (savings.length == 0) throw new CalculatorException("Savings list must be populated.");
		double remainingCost = systemCost;
		for (int i = 0; i < LIFESPAN; i++) {
			if (remainingCost <= 0) return i;
			remainingCost -= savings[i];
		}
		return -1;
	}
	
	/*
	 * Calculates the excess power earnings based on the user's power consumption
	 * and power generated over the lifetime of the product.
	 * 
	 * Precondition: A list representing yearly power generation is given.
	 * Postcondition: A list representing yearly earnings from excess power is returned.
	 */
	public double[] calculateExcessPowerEarnings(double[] power) throws CalculatorException {
		if (power == null)
			throw new CalculatorException("power is null");
		
		if (power.length == 0) throw new CalculatorException("Power list must be populated.");
		
		double[] excess = new double[power.length];
		for (int i = 0; i < LIFESPAN; i++) {
			excess[i] = (power[i] - electricityGeneration) * feedInTariff;
			if (excess[i] < 0) excess[i] = 0;
		}
		return excess;
	}
	
	/*
	 * Calculates the excess power generation based on the user's power consumption
	 * and power generated over the lifetime of the poduct.
	 * 
	 * Precondition: A list representing yearly power generation is given.
	 * Postcondition: A list representing yearly excess power generation is returned.
	 */
	public double[] calculateExcessPowerGeneration(double[] power) throws CalculatorException {
		if (power == null)
			throw new CalculatorException("power is null");
		
		double[] excess = new double[power.length];
		for (int i = 0; i < LIFESPAN; i++) {
			excess[i] = (power[i] - electricityGeneration);
			if (excess[i] < 0) excess[i] = 0;
		}
		return excess;
	}
	
	/*
	 * Calculates the amount of power which will be generated by the system,
	 * in respect to the amount of power being used.
	 * 
	 * Precondition: A list representing yearly power generation is given.
	 * Postcondition: Returns a percentage representing the amount of power
	 * generated in respect to the amount used (over the lifetime of the product).
	 */
	public double getPanelGenerationPercent(double[] power) throws CalculatorException {
		
		if (power == null)
			throw new CalculatorException("power is null");

		if (electricityGeneration == 0)
			throw new CalculatorException("electricityGeneration is 0");
		
		if (power.length == 0) throw new CalculatorException("Power list must be populated.");

		double totalPercent = 0;
		for (int i = 0; i < LIFESPAN; i++) {
			totalPercent += (power[i] / electricityGeneration);
		}
		totalPercent /= LIFESPAN;
		return (totalPercent * 100);
	}
	
	/*
	 * Retrieves the electricity generation for the year, given the power of the
	 * unit and the time since it was installed.
	 * 
	 * Precondition: The unit power (in watts) and the years since installation is given.
	 * Postcondition: The expected electricity generation over the year is returned.
	 */
	private double getElectricityGeneration(double unitPower, int yearsSinceInstall) {
		double u = unitPower;
		u *= getPanelDegradation(YEARLY_PANEL_DEGREDATION, yearsSinceInstall);
		u *= getElectricityModifierByAngle(angleOfInstallation);
		u *= getElectricityModifierByFacing(panelDegreeFacing);
		u *= hoursOfDaylight;
		u *= DAYS_IN_YEAR;
		return u;
	}
	
	/*
	 * Determines the electricity modifier based on how the system is installed
	 * off the ground.
	 * 
	 * Precondition: The angle of installation (from the ground) is given.
	 * Postcondition: The electricity modifier (in range 0-1) is returned.
	 */
	private double getElectricityModifierByAngle(double angle) {
		double maxAngle = 90;
		angle = Math.max(0, angle);
		angle = Math.min(maxAngle, angle);
		double efficiency = 1 - (angle / (maxAngle * 2));
		return efficiency;
	}
	
	/*
	 * Determines the electricity modifier based on the install direction.
	 * 
	 * Precondition: An angle in the range (-180 to 180) is specified.
	 * Postcondition: an electricity modifier (in range 0-1) is returned. 
	 */
	private double getElectricityModifierByFacing(double angle) {
		double modifier = 1 - (angle * POWER_LOSS_BY_FACING_DEGREE);
		return modifier;
	}
	
	/*
	 * Determines the panel degradation based on the rate of decay and the 
	 * time since installation.
	 * 
	 * Precondition: The yearly degradation and years since installation are given.
	 * Postcondition: An electricity modifier (in range 0-1) is returned.
	 */
	private double getPanelDegradation(double yearlyDeg, int yearsSinceInstall) {
		return Math.pow(yearlyDeg, yearsSinceInstall);
	}
}
