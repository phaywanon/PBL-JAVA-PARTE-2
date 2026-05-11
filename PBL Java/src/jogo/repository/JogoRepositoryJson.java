package jogo.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jogo.model.EstadoDoJogo;

import java.io.*;

public class JogoRepositoryJson implements JogoRepository {

    private static final String PASTA = "saves/";
    private final Gson gson;

    public JogoRepositoryJson() {
        // prettyPrinting = JSON formatado e legível
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        new File(PASTA).mkdirs(); // cria a pasta saves/ se não existir
    }

    @Override
    public void salvar(EstadoDoJogo estado, String slot) {
        String caminho = PASTA + slot + ".json";
        try (Writer writer = new FileWriter(caminho)) {
            gson.toJson(estado, writer); // objeto Java → JSON no arquivo
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    @Override
    public EstadoDoJogo carregar(String slot) {
        String caminho = PASTA + slot + ".json";
        File arquivo = new File(caminho);
        if (!arquivo.exists()) return null;

        try (Reader reader = new FileReader(arquivo)) {
            return gson.fromJson(reader, EstadoDoJogo.class); // JSON → objeto Java
        } catch (IOException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void deletar(String slot) {
        new File(PASTA + slot + ".json").delete();
    }

    @Override
    public boolean slotOcupado(String slot) {
        return new File(PASTA + slot + ".json").exists();
    }
}