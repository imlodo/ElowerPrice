package Model;

public class BeanSpecificaTecnica
{
	private String nomeSpecifica;
	private String descrizioneSpecifica;
	
	public BeanSpecificaTecnica()
	{
		
	}

	public String getNomeSpecifica()
	{
		return nomeSpecifica;
	}

	public String getDescrizioneSpecifica()
	{
		return descrizioneSpecifica;
	}

	public void setNomeSpecifica(String nomeSpecifica)
	{
		this.nomeSpecifica = nomeSpecifica;
	}

	public void setDescrizioneSpecifica(String descrizioneSpecifica)
	{
		this.descrizioneSpecifica = descrizioneSpecifica;
	}
	
	public String toString()
	{
		return 
		getClass().getSimpleName() +
		"[NomeSpecifica = " +
		nomeSpecifica +
		", DescrizioneSpecifica = " +
		descrizioneSpecifica +
		"]";
	}
}
