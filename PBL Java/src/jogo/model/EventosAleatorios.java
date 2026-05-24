package jogo.model;

public abstract class EventosAleatorios extends Eventos {
    public EventosAleatorios(String nome){
        super(nome);
    }

    public Class<? extends Local> getLocalPermitido() {
        return null;
    }

    public abstract String getMensagem();
}

