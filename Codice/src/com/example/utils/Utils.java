package com.example.utils;

import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import Model.BeanProdottoFornito;
import Model.DriverManagerConnectionPool;
import Model.ProductFornitoModelDM;
import Model.UserModelDM;
import Model.BeanCategoria;
import Model.BeanSpecificaTecnica;
import Model.BeanUtente;
import Model.DriverManagerConnectionPool;
import Model.ProductFornitoModelDM;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import Model.BeanProdottoFornito;
import Model.DriverManagerConnectionPool;
import Model.ProductFornitoModelDM;
import com.example.utils.Utils;
import Model.BeanProdottoFornito.opzioniAcquisto;	



public class Utils
{
	
// 	accedere al DB
//	
//	ServletContext ctx = getServletContext();
//	DriverManagerConnectionPool driver = (DriverManagerConnectionPool)ctx.getAttribute("driver");
//	ProductFornitoModelDM interfaceDB = new ProductFornitoModelDM(driver);
		
	
	
	public static ArrayList<BeanProdottoFornito> ribassiDelGiorno(ArrayList<BeanProdottoFornito> list)
	{
		if(list.isEmpty()==true)
			return null;
		BeanProdottoFornito beanProdottocor = null;
		ArrayList<BeanProdottoFornito> listaDeiRibassiDelGiorno = new ArrayList<BeanProdottoFornito>();
		double prezzoOggi = 0;
		double prezzoIeri = 0;
		for(int i = 0; i < list.size(); i++)
		{
			//System.out.println(list.get(i).getProductName());
			beanProdottocor = list.get(i);
			prezzoOggi = beanProdottocor.getPrezzoAttuale();
			prezzoIeri = beanProdottocor.getPrezzoIeri();
			if( prezzoOggi < prezzoIeri)
				listaDeiRibassiDelGiorno.add(beanProdottocor);
			
		}
		
		return listaDeiRibassiDelGiorno;
	}
	
	
	
	public static String controlCheckDigit(String cf)
	{
		int sum = 0;
		int i;
		for(i = 0; i < 15; i++)
		{		
			//carattere pari
			if(((i+1) % 2) == 0)
			{
				switch(cf.substring(i, i+1))
				{
					case"0": sum += 0; break;
					case"1": sum += 1; break;
					case"2": sum += 2; break;
					case"3": sum += 3; break;
					case"4": sum += 4; break;
					case"5": sum += 5; break;
					case"6": sum += 6; break;
					case"7": sum += 7; break;
					case"8": sum += 8; break;
					case"9": sum += 9; break;
					case"A": sum += 0; break;
					case"B": sum += 1; break;
					case"C": sum += 2; break;
					case"D": sum += 3; break;
					case"E": sum += 4; break;
					case"F": sum += 5; break;
					case"G": sum += 6; break;
					case"H": sum += 7; break;
					case"I": sum += 8; break;
					case"J": sum += 9; break;
					case"K": sum += 10; break;
					case"L": sum += 11; break;
					case"M": sum += 12; break;
					case"N": sum += 13; break;
					case"O": sum += 14; break;
					case"P": sum += 15; break;
					case"Q": sum += 16; break;
					case"R": sum += 17; break;
					case"S": sum += 18; break;
					case"T": sum += 19; break;
					case"U": sum += 20; break;
					case"V": sum += 21; break;
					case"W": sum += 22; break;
					case"X": sum += 23; break;
					case"Y": sum += 24; break;
					case"Z": sum += 25; break;
				}
			}
			//carattere dispari
			else
			{
				switch(cf.substring(i, i+1))
				{
					case"0": sum += 1; break;
					case"1": sum += 0; break;
					case"2": sum += 5; break;
					case"3": sum += 7; break;
					case"4": sum += 9; break;
					case"5": sum += 13; break;
					case"6": sum += 15; break;
					case"7": sum += 17; break;
					case"8": sum += 19; break;
					case"9": sum += 21; break;
					case"A": sum += 1; break;
					case"B": sum += 0; break;
					case"C": sum += 5; break;
					case"D": sum += 7; break;
					case"E": sum += 9; break;
					case"F": sum += 13; break;
					case"G": sum += 15; break;
					case"H": sum += 17; break;
					case"I": sum += 19; break;
					case"J": sum += 21; break;
					case"K": sum += 2; break;
					case"L": sum += 4; break;
					case"M": sum += 18; break;
					case"N": sum += 20; break;
					case"O": sum += 11; break;
					case"P": sum += 3; break;
					case"Q": sum += 6; break;
					case"R": sum += 8; break;
					case"S": sum += 12; break;
					case"T": sum += 14; break;
					case"U": sum += 16; break;
					case"V": sum += 10; break;
					case"W": sum += 22; break;
					case"X": sum += 25; break;
					case"Y": sum += 24; break;
					case"Z": sum += 23; break;
				}
			}
		}
		
		String control = "";
		switch(sum%26)
		{
			case 0: control+="A";break;
			case 1: control+="B";break;
			case 2: control+="C";break;
			case 3: control+="D";break;
			case 4: control+="E";break;
			case 5: control+="F";break;
			case 6: control+="G";break;
			case 7: control+="H";break;
			case 8: control+="I";break;
			case 9: control+="J";break;
			case 10: control+="K";break;
			case 11: control+="L";break;
			case 12: control+="M";break;
			case 13: control+="N";break;
			case 14: control+="O";break;
			case 15: control+="P";break;
			case 16: control+="Q";break;
			case 17: control+="R";break;
			case 18: control+="S";break;
			case 19: control+="T";break;
			case 20: control+="U";break;
			case 21: control+="V";break;
			case 22: control+="W";break;
			case 23: control+="X";break;
			case 24: control+="Y";break;
			case 25: control+="Z";break;
		}
		return control;
	}

	public static boolean checkAdminSession(HttpSession session, DriverManagerConnectionPool con, Cookie[] cookies) throws SQLException
	{
		boolean trovato = false;
		if(session != null)
		{	
			UserModelDM manager = new UserModelDM(con);
			String username = "";
			//Cerco nei cookies
			if(cookies != null)
			{
				for(Cookie cookie : cookies)
				{
					if(trovato == false)
					{
						if(cookie.getName().equals("user"))
						{
							username = cookie.getValue();
							trovato = true;
						}
					}
					else break;
				}
			}
			//Cerco nella sessione
			if(trovato == false)
			{
				username = (String) session.getAttribute("username");
				if(username != null)
					trovato = true;
			}
			if(trovato == true)
			{
				BeanUtente utente = manager.doRetrieveByKey(username);
				if(utente.getType() == 0)
					return false;
				else 
					return true;
			}
		}
		return trovato;
	}
}
