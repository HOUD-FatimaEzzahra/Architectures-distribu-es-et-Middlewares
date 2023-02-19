package dao;

public class IDaoImpl implements IDao{
    @Override
    public double getData() {
        System.out.println("-----Depuis la base de données-----");
        // récupérer la donnée depuis la base de données
        double data=Math.random()*10;
        return data;
    }
}
