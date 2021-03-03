package client.database;

import java.util.ArrayList;

public class FakeDatabase {
    private ArrayList<Account> listeCompte;

    public FakeDatabase() {
        listeCompte = new ArrayList<>();
    }

    public ArrayList<Account> getListeCompte() {
        return listeCompte;
    }

    public void setListeCompte(ArrayList<Account> listeCompte) {
        this.listeCompte = listeCompte;
    }
}
