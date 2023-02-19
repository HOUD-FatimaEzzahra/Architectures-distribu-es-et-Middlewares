package extension;

import dao.IDao;

public class IDaoImpl2 implements IDao {
    @Override
    public double getData() {
        System.out.println("-----Depuis les capteurs-----");
        double data=100.5;
        return data;
    }
}
