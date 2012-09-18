//------------------------------------------------------------------------------------
// Perform basic form validation to ensure that the inputs the user enters are
// appropriate for the application calculations.
//------------------------------------------------------------------------------------
function validateForm()
{
	// Check to ensure that an elevation is entered
	if (CalculatorForm.elevation.value == "") {
		alert("Please enter a panel elevation.");
		return false;
	}
	
	// Check to ensure that an elevation is entered
	if (CalculatorForm.electricityCost.value == "") {
		alert("Please enter an electricity cost.");
		return false;
	}
	
	// Check to ensure that an elevation is entered
	if (CalculatorForm.dailyUsage.value == "") {
		alert("Please enter an estimated daily electricity usage.");
		return false;
	}
	
	// Check to ensure that an elevation is entered
	if (CalculatorForm.feedInTariff.value == "") {
		alert("Please enter a feed-in tariff.");
		return false;
	}
	
	// Check to ensure that an elevation is entered
	if (CalculatorForm.panelAge.value == "") {
		alert("Please enter a panel age (0 if new).");
		return false;
	}
	
	// Check to ensure that elevation is a number
	if (isNaN(CalculatorForm.elevation.value)) { 
		alert("The elevation value must be a number.");
		return false;
	}
	
	// Check to ensure that electricity cost is a number
	if (isNaN(CalculatorForm.electricityCost.value)) { 
		alert("The electricity cost value must be a number.");
		return false;
	}
	
	// Check to ensure that daily usage is a number
	if (isNaN(CalculatorForm.dailyUsage.value)) { 
		alert("The daily usage value must be a number.");
		return false;
	}
	
	// Check to ensure that feed in tariff is a number
	if (isNaN(CalculatorForm.feedInTariff.value)) { 
		alert("The feed-in tariff value must be a number.");
		return false;
	}
	
	// Check to ensure that feed in tariff is a number
	if (isNaN(CalculatorForm.panelage.value)) { 
		alert("The panel age must be a number.");
		return false;
	}
	
	// Check to ensure that the angle of elevation is between 0 and 90 degrees.
	var elevationVal = CalculatorForm.elevation.value;
	if (elevationVal < 0 || elevationVal > 90) {
		alert("The angle of elevation must be between 0 and 90 degrees.");
		return false;	
	}
	
	// Check to ensure the cost of electricity is positive.
	var costVal = CalculatorForm.electricityCost.value;
	if (costVal < 0) {
		alert("The cost of electricity must be non-negative.");
		return false;	
	}

	// Check to ensure the daily usage is non-negative.
	var usageVal = CalculatorForm.electricityCost.value;
	if (usageVal < 0) {
		alert("The daily electricity usage must be non-negative.");
		return false;	
	}
	
	// Check to ensure the feed in tariff is non-negative.
	var usageVal = CalculatorForm.electricityCost.value;
	if (usageVal < 0) {
		alert("The feed-in tariff must be non-negative.");
		return false;	
	}
	
	// Check to ensure the feed in tariff is non-negative.
	var usageVal = CalculatorForm.panelAge.value;
	if (usageVal < 0) {
		alert("Panel age must be non-negative.");
		return false;	
	}
	
	// Form has been validated successfully.
	return true;
}