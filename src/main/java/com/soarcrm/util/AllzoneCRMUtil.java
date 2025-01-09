package com.soarcrm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllzoneCRMUtil 
{
	
	public static String getCurrentDate(String format){
		java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat(format);
	    String currentTime = sdf.format(dt);
	    return currentTime;
	}
	/**
	 * This method is used to check null and return empty values
	 * 
	 * @author Karthika Chinnadhurai
	 * @since 2023-08-27
	 */
	public static String parseNull(Object object) {
		return (object == null || object.toString().equals("")) ? "" : object.toString().trim();
	}

	/**
	 * This method is used to change database date to front end date
	 *
	 * @author Karthika Chinnadhurai
	 * @since 2023-08-27
	 */
	public static String changeFEDateFormat(String date) throws Exception {
		DateFormat targetFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDt = "";
		if (date != null && !date.equals("")) {
			Date dordate = originalFormat.parse(date);
			formattedDt = targetFormat.format(dordate);
		}
		return formattedDt;
	}
	
	public static String unMaskNumber(String phoneNumber) throws Exception {
		if(phoneNumber!= null && !phoneNumber.equals("")){
			phoneNumber = phoneNumber.replaceAll("-", "");
		}
		return phoneNumber;
	}
	/**
	 * TThis method is used to change front end date to Database date format
	 *
	 * @author Karthika Chinnadhurai
	 * @since 2023-08-27
	 */
	public static String changeBEDateFormat(String date) throws Exception {
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDt = "";
		if (date != null && !date.equals("")) {
			Date dordate = originalFormat.parse(date);
			formattedDt = targetFormat.format(dordate);
		}
		return formattedDt;
	}

	/**
	 * This method is used to get current to save in database *
	 * @author Karthika Chinnadhurai
	 * @since 2023-08-27
	 */
	public static String getCurrentDBDate() throws Exception {
		String currentDate = getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		return currentDate;
	}

	public static String getcurrentDatetime(String format)
	{
		java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat(format);
	    String currentTime = sdf.format(dt);
	    return currentTime;
	}
	
	public static String getDate7DaysBefore(String format)
	{
		 java.text.SimpleDateFormat sdf = 
				  new java.text.SimpleDateFormat(format);
		 Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			
		  String weekdate = sdf.format(cal.getTime());
		  
		  return weekdate;
	}
	
	public static String getmonthDatetime(String format)
	{
		Calendar c = Calendar.getInstance();  
	    c.set(Calendar.DAY_OF_MONTH, 1);
	    DateFormat df = new java.text.SimpleDateFormat(format);
	       String startDate=df.format(c.getTime());
	    return startDate;
	}
	
	 public static String subtractDate(String inputdate,int subtractDay) throws ParseException 
	 {
         SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
         Calendar c = Calendar.getInstance();
         c.setTime(new Date(inputdate)); // Now use today date.
         c.add(Calendar.DATE, subtractDay); // Adding 5 days
         String output = sdf.format(c.getTime());
         //System.out.println(output);
         return output;

    }
	 
	 public static boolean isNumeric(String strNum) throws Exception 
	 {
		 Pattern p = Pattern.compile("[0-9]+");
	     Matcher m = p.matcher(strNum);
	     boolean b = m.matches();
	    	
	    	if(b == false)
	    	{
	    		return false;
	    	}
	    	else
	    	{
	    		 return true;
	    	}
		   
		}
	 
	 public static boolean isPhoneNo(String strNum) throws Exception 
	 {
		 Pattern p = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
	     Matcher m = p.matcher(strNum);
	     boolean b = m.matches();
	    	
	    	if(b == false)
	    	{
	    		return false;
	    	}
	    	else
	    	{
	    		 return true;
	    	}
		   
		}
	 
	 public static boolean IsEmail(String email) throws Exception 
	 {
		 Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	     Matcher m = p.matcher(email);
	     boolean b = m.matches();
	     
	    	if(b == false)
	    	{
	    		return false;
	    	}
	    	else
	    	{
	    		 return true;
	    	}
		   
		}
	 
	 public static boolean isString(String Stringvalue, int currentcell) throws Exception 
	{
		    try 
		    {
		    	Pattern p = Pattern.compile("^[ A-Za-z]+$");
		    	Matcher m = p.matcher(Stringvalue);
		    	boolean b = m.matches();
		    } 
		    catch (Exception e) 
		    {
		        throw  new Exception("column " +currentcell + " " + "contains Numbers");
		    }
		    return true;
	}
	 
	 public static List<String> getYears(int currentyear)
	{
			List<String> yearlist = new ArrayList<String>();
			
			int iYearback = currentyear;
			int count = 10;
			for(int i=1; i<=10; i++)
			{
				
				yearlist.add(String.valueOf(iYearback-count));
				count--;
			}
			
			yearlist.add(String.valueOf(currentyear));
			
			for(int i=1; i<23; i++)
			{
				yearlist.add(String.valueOf(iYearback+i));
			}
			
			return yearlist;
			
		}
	 
	 public static long getNoofweekforHalfYearly(int monthValue, int year) throws ParseException 
	 {
		 long weeksBetweenDates = 0 ;
		 if(monthValue > 6)
		 {
			LocalDate firstMondayOfMonth = LocalDate.of(year, 7, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

		    LocalDate lastDayOfMonth  = LocalDate.of(year, 12, 31).with(TemporalAdjusters.lastDayOfMonth());
		    LocalDate lastSundayOfMonth = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

		    weeksBetweenDates = ChronoUnit.WEEKS.between(firstMondayOfMonth, lastSundayOfMonth.plusDays(1));
		    //System.out.println("weeksBetweenDates in 1st half "+weeksBetweenDates);
		 }
		 else
		 {
			 LocalDate firstMondayOfMonth = LocalDate.of(year, 1, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

			 LocalDate lastDayOfMonth  = LocalDate.of(year, 6, 30).with(TemporalAdjusters.lastDayOfMonth());
			 LocalDate lastSundayOfMonth = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

			 weeksBetweenDates = ChronoUnit.WEEKS.between(firstMondayOfMonth, lastSundayOfMonth.plusDays(1));
			 //System.out.println("weeksBetweenDates in 2nd half "+weeksBetweenDates);
		 }
		 
		 return weeksBetweenDates;
	 }
	 
	 public static long getNoofweekforQuarter(int monthValue, int year) throws ParseException 
	 {
		 long weeksBetweenDates = 0 ;
		 if((monthValue == 1) || (monthValue == 2) || (monthValue == 3))
		 {
			LocalDate firstMondayOfMonth = LocalDate.of(year, 1, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

		    LocalDate lastDayOfMonth  = LocalDate.of(year, 3, 31).with(TemporalAdjusters.lastDayOfMonth());
		    LocalDate lastSundayOfMonth = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

		    weeksBetweenDates = ChronoUnit.WEEKS.between(firstMondayOfMonth, lastSundayOfMonth.plusDays(1));
		    //System.out.println("weeksBetweenDates in 1st quater "+weeksBetweenDates);
		 }
		 else if((monthValue == 4) || (monthValue == 5) || (monthValue == 6))
		 {
			 LocalDate firstMondayOfMonth = LocalDate.of(year, 4, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

			 LocalDate lastDayOfMonth  = LocalDate.of(year, 6, 30).with(TemporalAdjusters.lastDayOfMonth());
			 LocalDate lastSundayOfMonth = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

			 weeksBetweenDates = ChronoUnit.WEEKS.between(firstMondayOfMonth, lastSundayOfMonth.plusDays(1));
			 //System.out.println("weeksBetweenDates in 2nd quarter "+weeksBetweenDates);
		 }
		 else if((monthValue == 7) || (monthValue == 8 )|| (monthValue == 9))
		 {
			 LocalDate firstMondayOfMonth = LocalDate.of(year, 7, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

			 LocalDate lastDayOfMonth  = LocalDate.of(year, 9, 30).with(TemporalAdjusters.lastDayOfMonth());
			 LocalDate lastSundayOfMonth = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

			 weeksBetweenDates = ChronoUnit.WEEKS.between(firstMondayOfMonth, lastSundayOfMonth.plusDays(1));
			// System.out.println("weeksBetweenDates in 3rd quarter "+weeksBetweenDates);
		 }
		 else if((monthValue == 10) || (monthValue == 11) || (monthValue == 12))
		 {
			 LocalDate firstMondayOfMonth = LocalDate.of(year, 10, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

			 LocalDate lastDayOfMonth  = LocalDate.of(year, 12, 31).with(TemporalAdjusters.lastDayOfMonth());
			 LocalDate lastSundayOfMonth = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

			 weeksBetweenDates = ChronoUnit.WEEKS.between(firstMondayOfMonth, lastSundayOfMonth.plusDays(1));
			 //System.out.println("weeksBetweenDates in 4th quarter "+weeksBetweenDates);
		 }
		 
		 return weeksBetweenDates;
	 }
	 
	 public static long getNoofweekbetweenMonths(int fromyear, int toyear, int frommonth, int tomonth,  int fromdate , int todate) throws ParseException 
	 {
		 long weeksBetweenDates = 0 ;
		
			LocalDate firstMondayOfMonth = LocalDate.of(fromyear, frommonth, fromdate).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

		    LocalDate lastDayOfMonth  = LocalDate.of(toyear, tomonth, todate).with(TemporalAdjusters.lastDayOfMonth());
		    LocalDate lastSundayOfMonth = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

		    weeksBetweenDates = ChronoUnit.WEEKS.between(firstMondayOfMonth, lastSundayOfMonth.plusDays(1));
		    //System.out.println("weeksBetweenDates in 1st half "+weeksBetweenDates);
		    return weeksBetweenDates;
	 }
	 
	 public static long getNoofweekforMonth(int monthValue, int year) throws ParseException 
	 {
		 long weeksBetweenDates = 0 ;
		 if((monthValue == 1) || (monthValue == 3) || (monthValue == 5) || (monthValue == 7) || (monthValue == 8) || (monthValue == 10) || (monthValue == 12))
		 {
			LocalDate firstMondayOfMonth = LocalDate.of(year, monthValue, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

		    LocalDate lastDayOfMonth  = LocalDate.of(year, monthValue, 31).with(TemporalAdjusters.lastDayOfMonth());
		    LocalDate lastSundayOfMonth = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

		    weeksBetweenDates = ChronoUnit.WEEKS.between(firstMondayOfMonth, lastSundayOfMonth.plusDays(1));
		    //System.out.println("weeksBetweenDates in 1st quater "+weeksBetweenDates);
		 }
		 else if((monthValue == 4) || (monthValue == 6) || (monthValue == 9) || (monthValue == 11))
		 {
			 LocalDate firstMondayOfMonth = LocalDate.of(year, monthValue, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

			 LocalDate lastDayOfMonth  = LocalDate.of(year, monthValue, 30).with(TemporalAdjusters.lastDayOfMonth());
			 LocalDate lastSundayOfMonth = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

			 weeksBetweenDates = ChronoUnit.WEEKS.between(firstMondayOfMonth, lastSundayOfMonth.plusDays(1));
			 //System.out.println("weeksBetweenDates in 2nd quarter "+weeksBetweenDates);
		 }
		 else if(monthValue == 2)
		 {
			 LocalDate firstMondayOfMonth = LocalDate.of(year, monthValue, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

			 LocalDate lastDayOfMonth  = LocalDate.of(year, monthValue, 28).with(TemporalAdjusters.lastDayOfMonth());
			 LocalDate lastSundayOfMonth = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

			 weeksBetweenDates = ChronoUnit.WEEKS.between(firstMondayOfMonth, lastSundayOfMonth.plusDays(1));
		 }
		return weeksBetweenDates;
	 }
	 
	 public static boolean IsCommonDomain(String domainname) throws Exception
	 {
		// System.out.println("domainname "+ domainname);
		 	boolean isValid = false;
	       List validemails = new ArrayList();
	       // valid email addresses
	       validemails.add("gmail.com");
	       validemails.add("yahoo.com");
	       validemails.add("hotmail.com");
	       validemails.add("aol.com");
	       validemails.add("hotmail.co.uk");
	       validemails.add("hotmail.fr");
	       
	       validemails.add("msn.com");
	       validemails.add("yahoo.fr");
	       validemails.add("yahoo.co.uk");
	       validemails.add("yahoo.co.in");
	       validemails.add("rediffmail.com");
	       validemails.add("outlook.com");
	       
	       validemails.add("googlemail.com");
	       validemails.add("comcast.net");
	       validemails.add("yahoo.com.br");
	       validemails.add("live.com");

	       for(int i=0; i<validemails.size(); i++)
	       {
	    	   String value = (String) validemails.get(i);
	    	   if(value.equals(domainname))
	    	   {
	    		   isValid = true;
	    		   break;
	    	   }
	       }
	      // System.out.println("isValid "+ isValid);
	       return isValid;
	 }
	 

}
