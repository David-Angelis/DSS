package Competicao;

import DAO.DAOOcupacao;

public class Campo {

    private String codCampo;
    private String nome;
    private int quadrante;
    private String localidade;
    private String cod_Escola;
    private DAOOcupacao Ocupacao;
    
    public Campo(String codigo,String nome,String localidade,int quadrante,String cod_Escola){
        this.codCampo = codigo;
        this.nome=nome;
        this.quadrante=quadrante;
        this.localidade=localidade;
        this.cod_Escola=cod_Escola;
        this.Ocupacao= new DAOOcupacao();
    }
    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String aNome) {
        this.nome = aNome;
    }

    public int getQuadrantes() {
        return this.quadrante;
    }

    public void setQuadrantes(int aDimensao) {
        this.quadrante = aDimensao;
    }

    public String getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(String aLocalidade) {
        this.localidade=aLocalidade;
    }
    
    public String getCod_Escola() {
        return cod_Escola;
    }
    
    public void setCod_Escola(String cod_Escola) {
        this.cod_Escola = cod_Escola;
    }
    
    public DAOOcupacao getOcupacao() {
        return Ocupacao;
    }
    
    public void setOcupacao(DAOOcupacao Ocupacao) {
        this.Ocupacao = Ocupacao;
    }
    
    public String getCodigo(){
        return this.codCampo;
    }
}
