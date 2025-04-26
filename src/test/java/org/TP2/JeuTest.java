package org.TP2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class JeuTest {
    @Mock
    private Banque banque;

    @Mock
    private Joueur joueur;

    @Mock
    private De de1;

    @Mock
    private De de2;

    private Jeu jeu;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jeu = new Jeu(banque);
    }
    @Test
    public void testJeuFerme() {
        jeu.fermer();
        assertThrows(JeuFermeException.class, () -> jeu.jouer(joueur, de1, de2));
    }
    @Test
    @ExtendWith(MockitoExtension.class)
    public void testJoueurInsolvable() throws Exception {
        when(joueur.mise()).thenReturn(100);
        doThrow(new DebitImpossibleException("Insolvable")).when(joueur).debiter(anyInt());

        assertThrows(DebitImpossibleException.class, () -> jeu.jouer(joueur, de1, de2));

        // Vérifie que les dés n'ont jamais été lancés
        verifyNoInteractions(de1);
        verifyNoInteractions(de2);
    }
    @Test
    public void testPerteNormale() throws Exception {
        when(joueur.mise()).thenReturn(50);
        when(de1.lancer()).thenReturn(2);
        when(de2.lancer()).thenReturn(3); // 2 + 3 = 5, donc pas 7
        when(banque.est_solvable()).thenReturn(true);

        jeu.jouer(joueur, de1, de2);

        verify(joueur).debiter(50);
        verify(banque).crediter(50);
        verify(banque, never()).debiter(anyInt());
        verify(joueur, never()).crediter(anyInt());
        assertTrue(jeu.estOuvert());
    }
    @Test
    public void testVictoireAvec7() throws Exception {
        when(joueur.mise()).thenReturn(100);
        when(de1.lancer()).thenReturn(3);
        when(de2.lancer()).thenReturn(4); // 3 + 4 = 7
        when(banque.est_solvable()).thenReturn(true);

        jeu.jouer(joueur, de1, de2);

        verify(joueur).debiter(100);
        verify(banque).crediter(100);
        verify(banque).debiter(200);
        verify(joueur).crediter(200);
        assertTrue(jeu.estOuvert());
    }
    @Test
    public void testBanqueInsolvableApresGain() throws Exception {
        when(joueur.mise()).thenReturn(100);
        when(de1.lancer()).thenReturn(3);
        when(de2.lancer()).thenReturn(4); // 7
        when(banque.est_solvable()).thenReturn(false); // La banque devient insolvable

        jeu.jouer(joueur, de1, de2);

        assertFalse(jeu.estOuvert());
    }
}