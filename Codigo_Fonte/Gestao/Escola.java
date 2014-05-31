package Gestao;

import Competicao.Campo;
import DAO.DAOCampo;
import DAO.DAOEquipa;
import java.util.ArrayList;
import java.util.Collection;

public class Escola {
        //private static int codigo = 0;
        private final String cod_escola;
        private String nome;
	private String endereco;
	private String localidade;
	public DAOEquipa DAOEquipa;
	public DAOCampo DAOCampo;
        
        public Escola(String codigo,String nome,String endereco,String localidade){
            this.cod_escola= codigo;
            this.nome=nome;
            this.endereco=endereco;
            this.localidade=localidade;
            this.DAOEquipa = new DAOEquipa();
            this.DAOCampo = new DAOCampo();
        }
/*
        public Escola(String nome,String endereco,String localidade){
            this.cod_escola= Integer.toString(codigo);
            Escola.codigo++;
            this.nome=nome;
            this.endereco=endereco;
            this.localidade=localidade;
            this.DAOEquipa = new DAOEquipa();
            this.DAOCampo = new DAOCampo();
        }
*/
        /*Get & Set*/
        public String getCod_Escola(){
            return cod_escola;
        }
        
	public String getNome(){
            return this.nome;
	}

	public void setNome(String aNome){
            this.nome = aNome;
	}

	public String getEndereco(){
            return this.endereco;
	}

	public void setEndereco(String aEndereco){
            this.endereco = aEndereco;
	}

	public String getLocalidade(){
		return this.localidade;
	}

	public void setLocalidade(String aLocalidade){
		this.localidade = aLocalidade;
	}
        
        /*Metodos*/
        
        public Collection<Equipa> getEquipas (String escalao){
            return DAOEquipa.values(escalao);
        }
        
        public Collection<Equipa> getEquipas (String escola,String escalao){
            return DAOEquipa.values(escalao,escola);
        }
         public Collection<Equipa> getEquipas (Escola escola){
            return DAOEquipa.values(escola);
        }
           public Collection<Equipa> getEquipas (Object escola){
            return DAOEquipa.values(escola);
        }
        
        public Equipa getEquipa (String cod_equipa){
            return DAOEquipa.get(cod_equipa);
        }
        
        public boolean validaEquipa (String nome){
            return DAOEquipa.containsKey(nome);
        }
        
        public void removeEquipa (String equipa){
            Equipa e = DAOEquipa.get(equipa);
            e.removeJogadores();
            Equipa remove;
            remove = DAOEquipa.remove(equipa);
        }
        
        public void registaAlteracao(Equipa e){
            Equipa put = DAOEquipa.put(e.getCodEquipa(),e);
        }
        
        public Collection<Jogador> getJogadores(String codEquipa){
            Equipa e = DAOEquipa.get(codEquipa);
            Collection<Jogador>jog=new ArrayList<Jogador>();
            jog=e.getJogadores();
            return jog;
        }
        
        public void removeJogador(String jogador,String equipa){
           Equipa e=DAOEquipa.get(equipa);
           e.removeJogador(jogador);
        }
        public boolean existeCampo (String codigoCampo){
            return DAOCampo.containsKey(codigoCampo);
        }
        public void NovoCampo (String cod,String nome,String localidade,int quadrantes){
            Campo c = new Campo(cod,nome,localidade,quadrantes,this.cod_escola);
            DAOCampo.put(c.getCodigo(),c);
        }
        public void removeCampo(String codCampo){
            DAOCampo.remove(codCampo);
        }
        public Campo getCampo (String codCampo){
            return DAOCampo.get(codCampo);
        }
        
        public DAOCampo getDAOCampo(){
            return this.DAOCampo;
        }
}
