package Gestao;

public class Jogador {
    
    //private static int codigo = 0;
    private String codJogador;
    private String nome;
    private String dataNascimento;
    private String codEquipa;
    private String codEscola;
    
    public Jogador(String codigo,String nome,String dataNascimento,String codEquipa){
        this.codJogador = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.codEquipa = codEquipa;
    }
    
    /*
    public Jogador(String nome,String dataNascimento,String codEquipa){
        this.codJogador = Integer.toString(codigo);
        Jogador.codigo++;
        this.nome = nome;
        this.dataNascimento = Integer.parseInt(dataNascimento);
        this.codEquipa = codEquipa;
    }*/
    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String aNome) {
        this.nome = aNome;
    }

    public String getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNasicmento(String aDataNascimento) {
        this.dataNascimento = aDataNascimento;
    }

    public String getCodJogador() {
        return codJogador;
    }
    
    public String getCod_Equipa() {
        return codEquipa;
    }
    
    public void setCod_Equipa(String codEquipa) {
        this.codEquipa = codEquipa;
    }
}
