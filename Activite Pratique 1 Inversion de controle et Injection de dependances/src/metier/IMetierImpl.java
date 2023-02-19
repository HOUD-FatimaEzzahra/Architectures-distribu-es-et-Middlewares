package metier;

import dao.IDao;

public class IMetierImpl implements IMetier{
    //couplage faible
    private IDao dao;
    @Override
    public double calcul() {
        double data=dao.getData();
        return data*5-Math.PI;
    }
    //injecter dans la variable dao un objet d'une classe qui implémente l'interface dao
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
