package Model;

import java.util.regex.Pattern;

import com.example.utils.Utils;

import java.util.regex.Matcher;

public class ValidationFormForServlet
{
	public static boolean checkUsername(String text)
	{
		String regexp = "^[a-z0-9]{5,64}$";
		// in javascript vanno inserite tra /regexrp/ in java NO

		Pattern pt = Pattern.compile(regexp);
		Matcher mt = pt.matcher(text);

		boolean resultmatch = mt.matches();

		return resultmatch;
	}

	public static boolean checkEmail(String text)
	{
		if(text.length() < 10)
			return false;
		else if(text.length() > 254)
			return false;

		String regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
		// in javascript vanno inserite tra /regexrp/ in java NO

		Pattern pt = Pattern.compile(regexp);
		Matcher mt = pt.matcher(text);

		boolean resultmatch = mt.matches();

		return resultmatch;

	}

	public static boolean checkCodiceFiscale(String text)
	{
		if(text.length() != 16)
			return false;
		text = text.toUpperCase();

		String check = Utils.controlCheckDigit(text);
		if(!check.equals(text.substring(15, 16)))
			return false;

		String regexp = "^[a-zA-Z]{6}[0-9]{2}[a-zA-Z]{1}[0-9]{2}[a-zA-Z]{1}[a-zA-Z0-9]{3}[a-zA-Z]$";
		Pattern pt = Pattern.compile(regexp);
		Matcher mt = pt.matcher(text);

		boolean resultmatch = mt.matches();

		return resultmatch;
	}
	public static boolean checkName(String text)
	{
		String regexp = "^[A-Za-z0-9]{1,20}+$";
		// in javascript vanno inserite tra /regexrp/ in java NO

		Pattern pt = Pattern.compile(regexp);
		Matcher mt = pt.matcher(text);

		boolean resultmatch = mt.matches();

		return resultmatch;

	}

	public static boolean checkSurname(String text)
	{
		String regexp = "^[A-Za-z]{1,20}+$";
		// in javascript vanno inserite tra /regexrp/ in java NO

		Pattern pt = Pattern.compile(regexp);
		Matcher mt = pt.matcher(text);

		boolean resultmatch = mt.matches();

		return resultmatch;

	}

	public static boolean checkPassword(String text)
	{
		String regexp = "([\\wA-Z\\d]*(\\w|[A-Z]|\\d)[!%]*)$"; // da vedere meglio i patter matching
		// in javascript vanno inserite tra /regexrp/ in java NO

		Pattern pt = Pattern.compile(regexp);
		Matcher mt = pt.matcher(text);

		boolean resultmatch = mt.matches();

		return resultmatch;

	}



}
