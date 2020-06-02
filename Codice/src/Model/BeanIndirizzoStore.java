package Model;

public class BeanIndirizzoStore
{
	private String via_store; 
	private int numero_civico_store; 
	private BeanCity city;
	private String nome_store;
	
	public BeanIndirizzoStore()
	{
		
	}
	
	public BeanCity getCity()
	{
		return city;
	}

	public String getVia_store()
	{
		return via_store;
	}

	
	public String getNome_store()
	{
		return nome_store;
	}

	public void setNome_store(String nome_store)
	{
		this.nome_store = nome_store;
	}

	public int getNumero_civico_store()
	{
		return numero_civico_store;
	}

	public void setVia_store(String via_store)
	{
		this.via_store = via_store;
	}

	public void setNumero_civico_store(int numero_civico_store)
	{
		this.numero_civico_store = numero_civico_store;
	}

	public void setCity(BeanCity city)
	{
		this.city = city;
	}
}
