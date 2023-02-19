package présentation;

import dao.IDaoImpl;
import metier.IMetierImpl;

public class Application1 {
    //injection de dépendances par instanciation statique
    public static void main(String[] args) {
        IDaoImpl dao=new IDaoImpl();
        IMetierImpl metier=new IMetierImpl();
        metier.setDao(dao);
        System.out.println("Résultat = "+metier.calcul());
    }
}
