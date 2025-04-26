package org.TP2;

//import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CalculatriceTest {
    @Mock
    private Calculatrice calculatrice;
    @Test
    public void testAdditionner() {
        // Définition du comportement de la méthode "additionner"
        when(calculatrice.additionner(2, 3)).thenReturn(5);
        //TODO : Appel de la méthode à tester
        int resultat = calculatrice.additionner(2, 3);

        //TODO : Vérification du résultat
        assertEquals(5, resultat);


        //TODO : Vérification que la méthode "additionner" a été appelée
        // avec les arguments 2 et 3, utiliser la directive « verify »
        verify(calculatrice).additionner(2, 3);

        //TODO : Vérification qu'aucune autre méthode n'a été appelée sur
        //l'objet après l'appel de la méthode "additionner", utiliser la
        // méthode « verifyNoMoreInteractions »
        verifyNoMoreInteractions(calculatrice);

        // TODO : Vérification de l'état de l'objet après l'appel de la
        //méthode "additionner", penser à utiliser la méthode
        //« getState() » de la directive « verify » : // exemple :
        verify(calculatrice).getState();
    }
}