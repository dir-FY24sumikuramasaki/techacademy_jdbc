package dbSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import dbSample.dao.CountryDAO;
import dbSample.entity.Country;

public class DbConnectSample {

    public static void main(String[] args) {

        CountryDAO dao = new CountryDAO();

        System.out.print("検索キーワードを入力してください > ");
        String name = keyIn();

        List<Country> list = dao.getCountryFromName(name);

        for(Country item : list){
            System.out.println(item.getName());
            System.out.println(item.getPopulation());
        }
    }

 
    private static String keyIn() {
        String line = null;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            line = key.readLine();
        } catch (IOException e) {

        }
        return line;
    }
}
