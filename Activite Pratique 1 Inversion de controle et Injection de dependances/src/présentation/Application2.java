package présentation;

import dao.IDao;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(new File("config.txt"));
        String daoClassName=scanner.nextLine();
        //instatioation dynamique
        Class cDao=Class.forName(daoClassName);
        IDao dao=(IDao) cDao.newInstance();
        System.out.println("Résultat = "+dao.getData());
    }
}
