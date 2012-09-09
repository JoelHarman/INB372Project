<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="guestbook.Calculator" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	// Manage user inputs
	int panelGeneration = Integer.parseInt(request.getParameter("radiobutton"));
	double panelElevation = Double.parseDouble(request.getParameter("elevation"));
	double panelFacing = Double.parseDouble(request.getParameter("select"));
	double daylight = Double.parseDouble(request.getParameter("daylight"));
	double electricityCost = Double.parseDouble(request.getParameter("electricityCost"));
	double dailyUsage = Double.parseDouble(request.getParameter("dailyUsage"));
	double feedInTariff = Double.parseDouble(request.getParameter("feedInTariff"));

	// Store basic calculations
	Calculator t = new Calculator(panelGeneration, panelElevation, panelFacing, daylight, electricityCost, dailyUsage, feedInTariff);
	double[] power = t.getLifetimeElectricityGeneration(5000);
	double[] savings = t.getLifetimeCostSavings(power);
	double[] carbonSavings = t.getLifetimeCarbonSavings(power);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 
<html xmlns = "http://www.w3.org/1999/xhtml"> 

	<!-- Page header information. -->
	<head>
		<!--Page Title-->
		<title>Solar Calculator - Results</title>
		
		<!--Link to the formatting CSS document-->
		<link href="css/main.css" rel="stylesheet" type="text/css" />
		
		<!--Link to the rollover JavaScript code-->
		<script type="text/javascript" src="scripts/rollover.js"></script>
		
	    <style type="text/css">
			.style31 {color: #CC9900}
			.style32 {color: #FFFF82}
        .style33 {font-style: italic}
        .style34 {color: #FFFF33}
        </style>
</head>
	
	<!-- Page body information. -->
	<body onload="MM_PreloadNavigation();MM_preloadImages('images/navigationnew/ict-down.png')">
	
		<!-- Wrapper div, contains all of divs in the body -->
	<div class="wrapper"> 
			
				<!-- The heads for the page. -->
				<div class="header"><img src="images/banner.png" alt="Banner Image" width="744" height="95" /></div>
				
				<!-- Links for the page. -->
	  <div class="menu">
				<a href="home.html" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('HomeButton','','images/navigationnew/home-down.png',1)">
				<img src="images/navigationnew/home-up.png" alt="Home Button" name="HomeButton" width="152" height="30" border="0"  id="HomeButton" /></a><a href="info.html" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('ICTButton','','images/navigationnew/ict-down.png',1)"></a><a href="calculator.html" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('FAQButton','','images/navigationnew/faq-down.png',1)">
				<img src="images/navigationnew/faq-up.png" alt="FAQ Button" name="FAQButton" width="152" height="30" border="0" id="FAQButton" /></a><br />	
	  </div>
		
	  <div class="content">
		  
		  		<div class="sidebox">
				<h1>Solar Power Calculator </h1>
				<p><span class="style31">Disclaimer</span><em>:</em> <em>All figures and results generated by the solar power calculator are <u>estimates only</u>. Actual results may vary based on incorrect input, location of installation, weather patterns in your area and many other factors. This calculator should be taken as a guide of expected power generation only, rather then a complete simulation.</em></p>
				<div align="left">
<p align="center"><span class="winners">Simple Summary</span></p>
<table width="1017" border="0" cellpadding="3" cellspacing="7" class="tableborder">
                      <tr>
                        <td width="211" scope="col"><strong>Average Yearly Power Generation: </strong></td>
                        <td width="218" class="style32" scope="col"><% out.print(Math.round(t.calculateDailyAverage(power) / 1000 * 365.25) + "Kw"); %>
						(<% out.print(Math.round(t.calculateDailyAverage(power) / 1000) + "Kw"); %>a day).</td>
                        <td width="169" scope="col"><strong>Total Power Generation: </strong></td>
                        <td width="274" class="style32" scope="col"><% out.print(Math.round(t.sumResult(power) / 1000) + "Kw"); %>.</td>
                      </tr>
                      <tr>
                        <td scope="col"><strong>Average Yearly Cost Savings: </strong></td>
                        <td class="style32" scope="col"><% out.print("$" + Math.round(t.calculateDailyAverage(savings) * 365.25)); %>
						(<% out.print("$" + Math.round(t.calculateDailyAverage(savings))); %> a day). </td>
                        <td scope="col"><strong>Total Cost Savings: </strong></td>
                        <td class="style32" scope="col"><% out.print("$" + Math.round(t.sumResult(savings))); %>.</td>
                      </tr>
                      <tr>
                        <td scope="col"><strong>Average Yearly Carbon Savings: </strong></td>
                        <td class="style32" scope="col"><% out.print(Math.round(t.calculateDailyAverage(carbonSavings) * 365.25) + " tonnes"); %>
						(<% out.print(Math.round(t.calculateDailyAverage(carbonSavings)) + " tonnes"); %> a day).</td>
                        <td scope="col"><strong>Total Carbon Savings: </strong></td>
                        <td class="style32" scope="col"><% out.print(Math.round(t.sumResult(carbonSavings)) + " tonnes"); %>.</td>  
                      </tr>
                  </table>
                  </div>
                  <p>&nbsp;</p>
				</div>
				
				<div class="sidebox">
				<h1>Results Breakdown</h1>
				<p>Below is a list of year-by-year breakdowns of important information about the product. </p>
				<div align="center">
				  <table width="900" border="0" align="center" cellpadding="10" bgcolor="#000000" class="tableborder">
				  	<tr>
						<td><div align="center">
						  <p class="winners">Electricity Generation</p>
						  <p>Below is a power generation breakdown graph. </p>
						</div></td>
						<td><div align="center">
						  <p class="winners">Cost Savings</p>
						  <p>Below is a cost savings breakdown graph. </p>
						</div></td>
						<td><div align="center">
						  <p class="winners">Carbon Savings</p>
						  <p>Below is a carbon savings breakdown graph. </p>
						</div></td>
					</tr>
                    <tr>
                    	<td>
							<div align="center">
                        	<% 
								// Draw the power generation graph
								out.print("<img src=\"http://ebusiness.net78.net/inctrendoverview.php?data=");
								for (int i = 0; i < 20; i++) out.print(Math.round(power[i] / 1000) + "*");
								out.print("0\" />");
							%>
                      		</div>
					  	</td>
                      	<td>
							<div align="center">
					  		<% 
								// Draw the power generation graph
								out.print("<img src=\"http://ebusiness.net78.net/inctrendoverview.php?data=");
								for (int i = 0; i < 20; i++) out.print(Math.round(savings[i]) + "*");
								out.print("0\" />");
							%>
                      		</div>
					  	</td>
					  
					  	<td>
					  		<div align="center">
					  		<% 
								// Draw the power generation graph
								out.print("<img src=\"http://ebusiness.net78.net/inctrendoverview.php?data=");
								for (int i = 0; i < 20; i++) out.print(Math.round(carbonSavings[i]) + "*");
								out.print("0\" />");
							%>
					  		</div>
				 	  </td>	
				    </tr>
                  </table>
				</div>
				<p>&nbsp;</p>
				<div align="center">
					<p class="winners">Panel Breakdowns</p>
					<p>Below is a year by year breakdown of the panels and their outputs.  </p>
					<table align="center" cellpadding="10" class="tableborder" id="gradient-style">
					<thead>
					<tr>
						<th align="center"><strong>Year</strong></td>
						<th align="center"><strong>Power Generated</strong></td>
						<th align="center"><strong>Money Saved</strong></td>
						<th align="center"><strong>Carbon Saved</strong></td>
					</tr>
					</thead>
						<%
							// Write each of the rows for the table breakdown
							for (int i = 0; i < power.length; i++) {
								out.print("<tr>");
									out.print("<td align=\"center\">" + "Year " + (i + 1) + "</td>");
									out.print("<td align=\"center\">" + Math.round(power[i] / 1000) + "Kw" + "</td>");
									out.print("<td align=\"center\">" + "$" + Math.round(savings[i]) + "</td>");
									out.print("<td align=\"center\">" + Math.round(carbonSavings[i]) + " tonnes" + "</td>");
								out.print("</tr>");
							}
				
							// Write the totals at the end of the table
							out.print("<tr>");
							out.print("<td align=\"center\">" + "Total" + "</td>");
							out.print("<td align=\"center\">" + Math.round(t.sumResult(power) / 1000) + "Kw" + "</td>");
							out.print("<td align=\"center\">" + "$" + Math.round(t.sumResult(savings)) + "</td>");
							out.print("<td align=\"center\">" + Math.round(t.sumResult(carbonSavings)) + " tonnes" + "</td>");
							out.print("</tr>");
						%>
				  </table>
				</div>
	
				</div>
		<div class="sidebox">
				<h1>Return on Investment</h1>
				<p>The section below provides details on the potential monetary gains from the solar panel purchase. </p>
				<table width="399" border="0" cellspacing="0" cellpadding="3">
                  <tr>
                    <th width="157" scope="col"><div align="left"><strong>Break Even Time:</strong></div></th>
                    <th width="213" scope="col"><div align="left"><span class="style32">
                      <% out.print(t.calculateBreakEven(savings)); %>
Years</span>.</div></th>
                  </tr>
                  <tr>
                    <td><div align="left"><strong>Total Net Profit: </strong></div></td>
                    <td><div align="left"><strong><span class="style32">$<% out.print(Math.round(t.getIvestmentReturnFromPanels(savings))); %></span>.</strong></div></td>
                  </tr>
                </table>
          <p>&nbsp;</p>
		  <p>Below is a much more detailed breakdown of how this investment compares with other competitive forms. </p>
		  <div align="center">
            <table width="900" border="0" align="center" cellpadding="10" bgcolor="#000000" class="tableborder">
              <tr>
                <td width="426"><div align="center">
                    <p class="winners">Panel Investment </p>
                    <p><em>Below is a graph showing the money generated by the panels over the lifetime of the </em>product.</p>
                </div></td>
                <td width="426"><div align="center">
                    <p class="winners">Bank Investment </p>
                    <p><em>Below is a graph showing the money generated by a standard investment over the same period. </em></p>
                </div></td>
              </tr>
              <tr>
                <td><div align="center">
                    <p>
                      <% 
							// Draw the power generation graph
							double[] panelReturn = t.getYearlyInvestmentFromPanels(savings);
							out.print("<img src=\"http://ebusiness.net78.net/inctrendoverview.php?data=");
							for (int i = 0; i < 20; i++) out.print(Math.round(panelReturn[i]) + "*");
							out.print("0\" />");
						%>
                    </p>
                    <p><strong>Total Return: <span class="style32">$<% out.print(Math.round(t.getIvestmentReturnFromPanels(savings))); %></span>.</strong></p>
                </div></td>
                <td><div align="center">
                    <p>
                      <% 
						// Draw the power generation graph
						double[] bankReturn = t.getYearlyInvestmentFromBank(1.05);
						out.print("<img src=\"http://ebusiness.net78.net/inctrendoverview.php?data=");
						for (int i = 0; i < 20; i++) out.print(Math.round(bankReturn[i]) + "*");
						out.print("0\" />");
					%>
                    </p>
                    <p><strong>Total Return:     <span class="style32">$<% out.print(Math.round(t.getInvestmentReturnFromBank(1.05))); %>.</span></strong></p>
                </div></td>
              </tr>
            </table>
	      </div>
		  <p>&nbsp;</p>
		  <p><span class="style33"><strong>Note</strong>: These figures are estimates only. True earnings, interest rates, and other factors could influence the results of the system. </span></p>
		</div>
				
		<div class="sidebox">
				<h1>Panel Breakdown:</h1>
				<p>In this setup, panels will account for 
				  <span class="style32">
				  <% out.print(Math.round(t.getPanelGenerationPercent(power))); %>
				  %</span>
				   of total expected power generation over their lifetime. To account for full power generation with these specifications over an extended period, a 
				   <span class="style32">
			      <% out.print(Math.round(panelGeneration * (90.0 / Math.round(t.getPanelGenerationPercent(power))))); %>Kw</span> 
			      sized system would be needed. </p>
				<p>Below is a year-by-year breakdown showing exactly how much excess power (and money) is generated over the life of the product. </p>
		        <div align="center">
		          <table width="900" border="0" align="center" cellpadding="10" bgcolor="#000000" class="tableborder">
                    <tr>
                      <td><div align="center">
                          <p class="winners">Electricity Generation</p>
                        <p><em>Below is a graph showing excess power, in kilowatts, per year. </em></p>
                      </div></td>
                      <td><div align="center">
                          <p class="winners">Money Generation </p>
                        <p><em>Below is a graph showing money earned per year. </em></p>
                      </div></td>
                    </tr>
                    <tr>
                      <td><div align="center">
                          <% 
							// Draw the power generation graph
							double[] excess = t.calculateExcessPowerGeneration(power);
							out.print("<img src=\"http://ebusiness.net78.net/inctrendoverview.php?data=");
							for (int i = 0; i < 20; i++) out.print(Math.round(excess[i] / 1000) + "*");
							out.print("0\" />");
						%>
                      </div></td>
                      <td><div align="center">
                          <% 
						// Draw the power generation graph
						double[] earnings = t.calculateExcessPowerEarnings(power);
						out.print("<img src=\"http://ebusiness.net78.net/inctrendoverview.php?data=");
						for (int i = 0; i < 20; i++) out.print(Math.round(earnings[i]) + "*");
						out.print("0\" />");
					%>
                      </div></td>
                    </tr>
                  </table>
          </div>
		        <p>&nbsp;</p>
		        <p>Based on the results above, it is expected that the system will generate an excess of 
				  <span class="style32"><% out.print(Math.round(t.sumResult(t.calculateExcessPowerGeneration(power)) / 1000));%>Kw</span>
				of power which will be fed back into the grid, which will earn you <span class="style32">$<% out.print(Math.round(t.sumResult(t.calculateExcessPowerEarnings(power))));%></span>
				over its lifetime.  </p>
		</div>
	    <p>&nbsp;</p>
	  </div>
	</div> 
</body>
</html>
