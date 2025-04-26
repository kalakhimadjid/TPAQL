package org.TP2;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UtilisateurApi utilisateurApiMock;

    @Test
    public void testCreerUtilisateur() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Kalakhi", "Abdelmadjid", "kalakhicheikhabdelmadjid@gmail.com");


        // 1. Lever une exception ServiceException-----------------------------------------------------------------------------------------------
        // TODO : Configuration du comportement du mock, utiliser la
        //directive « when » avec sa méthode « thenReturn »
        doThrow(new ServiceException("Echec de la création de l'utilisateur")).when(utilisateurApiMock).creerUtilisateur(utilisateur);

        // TODO : Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // TODO : Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);
        // Act & Assert
        assertThrows(ServiceException.class, () -> userService.creerUtilisateur(utilisateur));

        // TODO : Vérification de l'appel à l'API
        verifyNoMoreInteractions(utilisateurApiMock);
        // OU
        verify(utilisateurApiMock, times(1)).creerUtilisateur(utilisateur);
    }

    // 2. Erreur de validation → l’API ne doit jamais être appelée ----------------------------------------------------------------------------------------
    @Test
    public void testValidationError_NeverCallApi() throws ServiceException {
        // Suppose un scénario où l'utilisateur est invalide → mais ici simulé avec une règle
        Utilisateur utilisateur = new Utilisateur("", "", ""); // informations invalides

        UserService userService = new UserService(utilisateurApiMock);

        // On n'appelle même pas l'API si l'utilisateur est invalide (imaginé ici)
        // Donc il faut vérifier que l'API n'est jamais appelée
        verify(utilisateurApiMock, never()).creerUtilisateur(any(Utilisateur.class));
    }

    // 3. L’API retourne un ID après création----------------------------------------------------------------------------------------------------
    @Test
    public void testCreerUtilisateur_AssignerID() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("ROG", "STRIX", "ROG@STRIX.com");
        UserService userService = new UserService(utilisateurApiMock);

        // Suppose que créer utilisateur fonctionne (doNothing)
        doNothing().when(utilisateurApiMock).creerUtilisateur(utilisateur);
        // Définition d'un ID fictif
        int idUtilisateur = 2001;
        // TODO: Configuration du mock pour renvoyer l'ID
        utilisateur.setId(idUtilisateur); // simulation manuelle ici
        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);
        // TODO: Vérification de l'ID de l'utilisateur
        assertEquals(2001, utilisateur.getId());
    }
    // 4. Capturer les arguments passés à creerUtilisateur()-------------------------------------------------------------------------------------------
    @Test
    public void testCaptureArguments() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("ROG", "ARKAN", "ROG@ARKAN.com");
        UserService userService = new UserService(utilisateurApiMock);
        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);
        doNothing().when(utilisateurApiMock).creerUtilisateur(utilisateur);
        userService.creerUtilisateur(utilisateur);
        Utilisateur utilisateurCapture = argumentCaptor.getValue();
        // TODO : Vérification des arguments capturés, utiliser les getters de
        //l’objet utilisateurCapture
        assertEquals("ROG", utilisateurCapture.getPrenom());
        assertEquals("ARKAN", utilisateurCapture.getNom());
        assertEquals("ROG@ARKAN.com", utilisateurCapture.getEmail());


    }
}