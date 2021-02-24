package koneksi;
import java.sql.*;
public class koneksi {
    private Connection koneksi;
    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil Koneksi");
        }
        catch (ClassNotFoundException ex){
            System.out.println ("Gagal Koneksi" + ex);
        }
        String url = "jdbc:mysql://localhost/aplikasi_pelayanan_parkir";
        try {
            koneksi = DriverManager.getConnection(url, "root","");
        }
        catch (SQLException ex) {
            System.out.println("Gagal Koneksi Database"+ex);
        }
        return koneksi;
    }
    
}
