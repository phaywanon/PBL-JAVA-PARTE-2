package jogo.model;

public class Mapa {
    private LocalCantina cantina;
    private LocalCasa casa;
    private LocalColegiado colegiado;
    private LocalLaboratorio laboratorio;
    private LocalSalaDeAula salaDeAula;
    private LocalPontoDeOnibus pontoDeOnibus;
    private LocalDA da;

    public Mapa(){
        this.cantina = new LocalCantina();
        this.casa = new LocalCasa();
        this.colegiado = new LocalColegiado();
        this.laboratorio = new LocalLaboratorio();
        this.salaDeAula = new LocalSalaDeAula();
        this.pontoDeOnibus = new LocalPontoDeOnibus();
        this.da = new LocalDA();
    }

    public LocalCantina getCantina() { return cantina; }
    public LocalCasa getCasa() { return casa; }
    public LocalColegiado getColegiado() { return colegiado; }
    public LocalLaboratorio getLaboratorio() { return laboratorio; }
    public LocalSalaDeAula getSalaDeAula() { return salaDeAula; }
    public LocalPontoDeOnibus getPontoDeOnibus() { return pontoDeOnibus; }
    public LocalDA getDa() { return da; }

}
