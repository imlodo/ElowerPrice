package Model;

import java.util.ArrayList;

public class Prodotto 
{
	private String productName;
	private int count_ricerche;
	private byte[] imageBytes;
	private ArrayList<BeanCategoria> categorie;
	private ArrayList<BeanSpecificaTecnica> specifiche;
	
	public Prodotto()
	{
		
	}
	
	public String getProductName()
	{
		return productName;
	}

	public int getCount_ricerche()
	{
		return count_ricerche;
	}

	public byte[] getImageBytes()
	{
		return imageBytes;
	}
	
	public ArrayList<BeanCategoria> getCategorie()
	{
		return categorie;
	}
	
	public ArrayList<BeanSpecificaTecnica> getSpecifiche()
	{
		return specifiche;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public void setCount_ricerche(int count_ricerche)
	{
		this.count_ricerche = count_ricerche;
	}

	public void setImageBytes(byte[] imageBytes)
	{
		this.imageBytes = imageBytes;
	}

	public void setCategorie(ArrayList<BeanCategoria> categorie)
	{
		this.categorie = categorie;
	}

	public void setSpecifiche(ArrayList<BeanSpecificaTecnica> specifiche)
	{
		this.specifiche = specifiche;
	}
		
	public String toString()
	{
		return this.getClass().getSimpleName() + 
				"[NomeProdotto = " + 
				productName + 
				", CountRicerche = " + 
				count_ricerche + 
				", Categorie = " + 
				categorie +
				", Specifiche = " +
				specifiche;
	}
}
