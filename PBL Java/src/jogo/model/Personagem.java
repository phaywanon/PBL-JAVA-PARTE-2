package jogo.model;

public abstract class Personagem {
    private String nome;
    private int energia = 100;
    private int saude = 100;

    public Personagem (String nome) {
        this.nome = nome;
    }

    public void mostrarStatus() {
        System.out.println("Nome: " + nome);
        System.out.println("Energia: " + energia);
        System.out.println("Saúde: " + saude);
    }


    public String getNome(){
        return nome;
    }

    public int getEnergia(){
        return energia;
    }

    public int getSaude(){
        return saude;
    }

    public void setEnergia(int energia) {
        if (energia < 0) {
            this.energia = 0;
        } else if (energia > 110) {
            this.energia = 110;
        } else {
            this.energia = energia;
        }
    }

    public void setSaude(int saude) {
        if (saude < 0){
            this.saude = 0;
        } else if (saude > 120) {
            this.saude = 120;
        } else {
            this.saude = saude;
        }
    }
}


