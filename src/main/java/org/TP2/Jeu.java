package org.TP2;

public class Jeu {

    private Banque banque;
    private boolean ouvert = true;

    public Jeu(Banque banque) {
        this.banque = banque;
    }

    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException, DebitImpossibleException {
        if (!ouvert) {
            throw new JeuFermeException("Le jeu est fermé");
        }

        int mise = joueur.mise();

        joueur.debiter(mise); // Le joueur est débité
        banque.crediter(mise); // La banque encaisse la mise

        int resultat = de1.lancer() + de2.lancer();

        if (resultat == 7) {
            int gain = mise * 2;
            banque.debiter(gain);
            joueur.crediter(gain);
        }

        if (!banque.est_solvable()) {
            fermer();
        }
    }

    public void fermer() {
        ouvert = false;
    }

    public boolean estOuvert() {
        return ouvert;
    }
}
