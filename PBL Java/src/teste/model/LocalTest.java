//package teste.model;
//
//import jogo.model.*;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class LocalTest {
//
//    @Test
//    void cantinaDeveTerNPC() {
//        Local cantina = new LocalCantina();
//
//        assertFalse(cantina.getPersonagensPresentes().isEmpty());
//    }
//
//    @Test
//    void colegiadoDeveTerMaeli() {
//        Local colegiado = new LocalColegiado();
//
//        boolean temMaeli = colegiado.getPersonagensPresentes()
//                .stream()
//                .anyMatch(npc -> npc instanceof PersonagemMaeli);
//
//        assertTrue(temMaeli);
//    }
//
//    @Test
//    void laboratorioDeveTerProfessor() {
//        Local laboratorio = new LocalLaboratorio();
//
//        boolean temProfessor = laboratorio.getPersonagensPresentes()
//                .stream()
//                .anyMatch(npc -> npc instanceof PersonagemProfessor);
//
//        assertTrue(temProfessor);
//    }
//
//    @Test
//    void pontoDeOnibusDeveTerBichinhos() {
//        Local ponto = new LocalPontoDeOnibus();
//
//        boolean temBichinho = ponto.getPersonagensPresentes()
//                .stream()
//                .anyMatch(npc -> npc instanceof PersonagemBichinhos);
//
//        assertTrue(temBichinho);
//    }
//
//    @Test
//    void cantinaDeveTerColegas() {
//        Local cantina = new LocalCantina();
//
//        boolean temColegas = cantina.getPersonagensPresentes()
//                .stream()
//                .anyMatch(npc -> npc instanceof PersonagemColegas);
//
//        assertTrue(temColegas);
//    }
//}