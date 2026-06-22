package jogo.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jogo.model.EstadoDoJogo;

import java.io.*;

public class JogoRepositoryJson implements JogoRepository {

    // Pasta onde os saves serão armazenados
    private static final String PASTA = "saves/";

    // Responsável por converter objetos Java ↔ JSON
    private final Gson gson;

    public JogoRepositoryJson() {

        // prettyPrinting = JSON formatado e legível
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        // Garante que a pasta de saves exista
        new File(PASTA).mkdirs();
    }

    @Override
    public void salvar(EstadoDoJogo estado, String slot) {
        String caminho = PASTA + slot + ".json";
        try (Writer writer = new FileWriter(caminho)) {

            // Converte o objeto EstadoDoJogo para JSON
            gson.toJson(estado, writer); // objeto Java → JSON no arquivo
        } catch (IOException e) {

            // Repository não deve exibir mensagens na interface.
            // Apenas registra a falha através de exceção.
            throw new RuntimeException("Erro ao salvar jogo no slot " + slot, e);
        }
    }

    @Override
    public EstadoDoJogo carregar(String slot) {
        String caminho = PASTA + slot + ".json";
        File arquivo = new File(caminho);

        // Slot vazio
        if (!arquivo.exists()) {
            return null;
        }

        try (Reader reader = new FileReader(arquivo)) {

            // Converte JSON para o objeto EstadoDoJogo
            return gson.fromJson(reader, EstadoDoJogo.class); // JSON → objeto Java
        } catch (IOException e) {

            // Repository não deve exibir mensagens na interface.
            // Apenas informa que ocorreu uma falha de persistência.
            throw new RuntimeException("Erro ao carregar jogo do slot " + slot, e);
        }
    }

    @Override
    public void deletar(String slot) {
        File arquivo = new File(PASTA + slot + ".json");

        // Só tenta remover se o arquivo existir
        if (arquivo.exists()) {
            arquivo.delete();
        }
    }

    @Override
    public boolean slotOcupado(String slot) {

        // Verifica se já existe um save associado ao slot
        return new File(PASTA + slot + ".json").exists();
    }
}