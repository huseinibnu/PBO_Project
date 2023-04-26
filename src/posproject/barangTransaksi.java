/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package posproject;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import static posproject.DBConnector.connection;

/**
 *
 * @author ASUS
 */
public class barangTransaksi {
    private String kode, nama, hargaSatuan, jumlah, total;
    
    public barangTransaksi(String kodeS, String namaS, String hargaSatuanS, String jumlahS, String totalS) {
        this.kode = kodeS;
        this.nama = namaS;
        this.hargaSatuan = hargaSatuanS;
        this.jumlah = jumlahS;
        this.total = totalS;
    }
    
    public String getKode() {
        return kode;
    }
    
    public String getNama() {
        return nama;
    }
    
    public String getHargaSatuan() {
        return hargaSatuan;
    }
    
    public String getJumlah() {
        return jumlah;
    }
    
    public String getTotal() {
        return total;
    }
    
    public static void insertDetailDataTransaksi(int idTransaksi, String kode, String nama, String jumlah, String total){
        try{
            
            String sql = "INSERT INTO transaksi_barang (id_transaksi,id_barang,nama_barang,jumlah_barang,total) VALUES (?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idTransaksi);
            statement.setObject(2, kode);
            statement.setObject(3, nama);
            statement.setString(4, jumlah);
            statement.setObject(5, total);
            
            int rowInserted = statement.executeUpdate();
            if(rowInserted > 0){
                System.out.println("succesfully adding new transaction");
            }
            
            statement.close();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
}
