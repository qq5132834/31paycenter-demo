import com.baidu.translate.demo.TranslateMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class CodeAntMain {


    public static void main(String[] args) {
        CodeAntMain codeAntMain = new CodeAntMain();
        codeAntMain.select();
    }

    private void update(java.sql.Connection con, Integer id, String name) throws Exception {
        String res = TranslateMain.translate("en", "zh", name);
        System.out.println(res);

        java.sql.Statement st = con.createStatement();
        String sql = "update rules set name_cn='"+ res +"' where id = " + id;
        int num = st.executeUpdate(sql);
        System.out.println(num);
        System.out.println("==================================");
        //con.commit();
        Thread.sleep(2000);
    }

    private void select(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.32.149:3306/sonar?user=root&password=root&useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String sql = "select * from rules order by id asc ";
        ResultSet rs=null;
        java.sql.Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            if (!con.isClosed()) {
                System.out.println("已成功链接数据库！");
                java.sql.Statement st = con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    try {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        System.out.println(id + "\t" + name);
                        this.update(con, id, name);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                rs.close();
                con.close();
            }catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

}
