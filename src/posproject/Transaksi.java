/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package posproject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import static posproject.DBConnector.connection;


/**
 *
 * @author ASUS
 */
public class Transaksi {
    private ArrayList<barangTransaksi> daftarBelanja;
    private String totalBelanja, bayar, kembalian, detail = "", kode, nama, jumlah, total;
    private LocalDateTime waktuTransaksi;
    private int idTransaksi = 1;

    public Transaksi(ArrayList<barangTransaksi> daftarBelanja, String totalBelanja, String bayar, String kembalian) {
        this.daftarBelanja = daftarBelanja;
        this.totalBelanja = totalBelanja;
        this.bayar = bayar;
        this.kembalian = kembalian;
        this.waktuTransaksi = LocalDateTime.now();
        
        for (int i = 0; i < daftarBelanja.size(); i++) {
            detail = "";
            if (i > 0) {
                detail += ", ";
            }
            detail += daftarBelanja.get(i).getKode() + "-" + 
                    daftarBelanja.get(i).getNama() + "-" + 
                    daftarBelanja.get(i).getHargaSatuan()+ "-" + 
                    daftarBelanja.get(i).getJumlah()+ "-" + 
                    daftarBelanja.get(i).getTotal();
            
            kode = daftarBelanja.get(i).getKode();
            nama = daftarBelanja.get(i).getNama();
            jumlah = daftarBelanja.get(i).getJumlah();
            total = daftarBelanja.get(i).getTotal();
            barangTransaksi.insertDetailDataTransaksi(idTransaksi, kode, nama, jumlah, total);
        }
        insertDataTransaksi();
        idTransaksi++;
    }

    public ArrayList<barangTransaksi> getDaftarBelanja() {
        return daftarBelanja;
    }

    public String getTotalBelanja() {
        return totalBelanja;
    }
    
    public String getBayar() {
        return bayar;
    }
    
    public String getKembalian() {
        return kembalian;
    }

    public LocalDateTime getWaktuTransaksi() {
        return waktuTransaksi;
    }
    
public void insertDataTransaksi(){
        try{
            
            String sql = "INSERT INTO transaksi (id_transaksi,waktu_transaksi,total_belanja,jumlah_dibayar,kembalian,detail_barang) VALUES (?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idTransaksi);
            statement.setObject(2, waktuTransaksi);
            statement.setString(3, totalBelanja);
            statement.setString(4,bayar);
            statement.setString(5, kembalian);
            statement.setString(6, detail);
            
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

