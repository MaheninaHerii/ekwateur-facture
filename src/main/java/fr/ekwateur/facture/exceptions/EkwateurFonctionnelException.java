package fr.ekwateur.facture.exceptions;

public class EkwateurFonctionnelException extends RuntimeException {
    public EkwateurFonctionnelException(String message) {
        super("Ekw Erreur Fonctionnel : " + message);
    }
}
