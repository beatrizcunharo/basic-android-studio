package ufjf.br.beatriz.exemplo07;

public class Avistamento {
    private String nome;
    private String especie;
    private Integer avistamentos;

    public Avistamento (String nome, String especie, Integer avistamentos)
    {
        this.setNome(nome);
        this.setEspecie(especie);
        this.setAvistamentos(avistamentos);
    }

    public Avistamento (String nome, String especie){
        this(nome,especie, 0);
    }

    public Avistamento(String string){
        this(null,null,0);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Integer getAvistamentos() {
        return avistamentos;
    }

    public void setAvistamentos(Integer avistamentos) {
        this.avistamentos = avistamentos;
    }

}
