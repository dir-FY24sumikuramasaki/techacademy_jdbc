package dbSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.EncryptedPrivateKeyInfo;
import javax.management.loading.PrivateClassLoader;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbConnectSample03 {

    public static void main(String[] args) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs= null;
        
        try {
            // 1. ドライバーのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. DBと接続する
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Sumimasa@3530"
                    );
            // 3. DBとやりとりする窓口（Statementオブジェクト）の作成
            stmt = con.createStatement();
  
            // 4, 5. Select文の実行と結果を格納／代入
            System.out.print("検索キーワードを入力してください > ");
            String input = keyIn();
            
            String sql = "SELECT * From country Where Name = '" + input + "'";
            rs = stmt.executeQuery(sql);
            // 6. 結果を表示する[
            while( rs.next() ) {
                String name = rs.getString("Name");
                int population =rs.getInt("Population"); 
                System.out.println(name);
                System.out.println(population);
            }
         // 6-1. データの更新を行う
            sql = "update country set Population = 105000 where Code = 'ABW'";
            int count = stmt.executeUpdate(sql);
            System.out.println(count);
            
        } catch (ClassNotFoundException e) {
            
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        }finally{
            // 7. 接続を閉じる
            if( rs != null){
                try {
                    rs.close();             
            }catch(SQLException e) {
                System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                e.printStackTrace();
            }
            }
            if( stmt != null) {
                try {
                    stmt.close();
                }catch(SQLException e) {
                    System.err.println("Statementを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if( con != null) {
                try {
                    con.close();
                }catch(SQLException e) {
                    System.err.println("データベース切断時にエラーが発生しました。");
                    e.printStackTrace();
                }
            }
        }
        
        /*
         * キーボードから入力された値をStringで返す 引数：なし 戻り値：入力された文字列
         */
        
        private static String keyIn() {
            String line = null;
            try {
                BufferedReader keyBufferedReader = new BufferedReader(new InputStreamReader(System.in));
                line = key.readline();
            }catch(IOException e) {
                
            }
            return line;
    }

    private static String keyIn() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
