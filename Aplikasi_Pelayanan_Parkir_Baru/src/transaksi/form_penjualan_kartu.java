package transaksi;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import popup.popup_kartu;
import popup.popup_karyawan1;
import popup.popup_pelanggan;
import popup.popup_perusahaan;

public class form_penjualan_kartu extends javax.swing.JFrame {
private Connection conn = new koneksi().connect();
private Connection conn1 = new koneksi().connect();
    private DefaultTableModel tabmode;
    public String tgl;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public form_penjualan_kartu() {
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage("src/gambar/logo_frame.png");
        setIconImage(icon);
        datatable();      
        tgl_penjualan.setDateFormatString("yyyy-MM-dd");
        waktu();
        autoNumber();
        tidak_aktip();
        
    }
    
    private void autoNumber(){
        String idp = "TR00";
        int i = 0;
         String sql = "select id_penjualan from data_penjualan_kartu";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                idp = hasil.getString("id_penjualan");
            }
                idp = idp.substring(2);
                i = Integer.parseInt(idp)+1;
                idp = "0" +i;
                idp = "TR"+ idp.substring(idp.length()-2);
                tid_penjualan.setText(idp);
                tid_penjualan.setEditable(false);
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
    
    
    private void waktu(){
        new Thread(){
            int waktumulai;
            @Override
            public void run(){
                while (waktumulai==0){
                    int detik, menit, jam, hari, bulan, tahun;
                    GregorianCalendar date = new GregorianCalendar();
                    Date ys=new Date();
                    SimpleDateFormat s=new SimpleDateFormat("dd - MMMM - yyyy");
                    label_tanggal.setText(s.format(ys));
                    detik = date.get(Calendar.SECOND);
                    menit = date.get(Calendar.MINUTE);
                    jam =  date.get(Calendar.HOUR_OF_DAY);
                    String waktu_sekarang = jam + " : " + menit + " : " + detik;
                    label_waktu.setText(waktu_sekarang);
                }
            }
        }.start();
    }
    
    protected void kosong(){
    tid_penjualan.setText("");
    tgl_penjualan.setDate(null);
    tkasir.setText("");
    tid_perusahaan.setText("");
    tnama_perusahaan.setText("");
    tktp.setText("");
    tnama_pelanggan.setText("");
    tnopol.setText("");
    tno_kartu.setText("");
    tjenis.setText("");
    tharga.setText("");
    tdurasi.setText("");
    tbayar.setText("");
    tperiode.setText("");
    txt_cari.setText("");
      
    bcarikasir.requestFocus();
    }
    
    protected void tidak_aktip(){
    tid_penjualan.setEnabled(false);
    tkasir.setEnabled(false);
    tid_perusahaan.setEnabled(false);
    tnama_perusahaan.setEnabled(false);
    tktp.setEnabled(false);
    tnama_pelanggan.setEnabled(false);
    tnopol.setEnabled(false);
    tno_kartu.setEnabled(false);
    tjenis.setEnabled(false);
    tharga.setEnabled(false);
    tbayar.setEnabled(false);
    }
    
    protected void datatable() {
        Object[] Baris = {"ID Penjualan","Tanggal","Kasir","ID Perusahaan","Nama Perusahaan","No KTP","Nama Pelanggan","No Kendaraan",
            "No Kartu","Jenis Kartu","Harga","Durasi","Total Bayar","Periode"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel_penjualan.setModel(tabmode);
        String sql = "select * from data_penjualan_kartu ORDER BY id_penjualan DESC";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_penjualan");
                String b = hasil.getString("tanggal_penjualan");
                String c = hasil.getString("nama_karyawan");
                String d = hasil.getString("id_perusahaan");
                String e = hasil.getString("nama_perusahaan");
                String f = hasil.getString("no_ktp");
                String g = hasil.getString("nama_pelanggan");
                String h = hasil.getString("no_kendaraan");
                String i = hasil.getString("no_kartu");
                String j = hasil.getString("jenis");
                String k = hasil.getString("harga");
                String l = hasil.getString("durasi");
                String m = hasil.getString("total");
                String n = hasil.getString("periode");
                             
                String [] data = {a,b,c,d,e,f,g,h,i,j,k,l,m,n};
                tabmode.addRow(data);
            }
        }catch (Exception e) {
        }
    }
    
    public void disable_mainframe(){
        this.setEnabled(false);
    }
    
    public void enable_mainframe(){
        this.toFront();
        this.setEnabled(true);
    }
    
     public String str_id_perusahaan, str_nama_perusahaan, no_ktp, nama_pelanggan, nopol,
             pop_no_kartu,pop_jenis, pop_harga, str_nama;
     
    popup_perusahaan fd_perusahaan = new popup_perusahaan();
    popup_pelanggan fd_pelanggan = new popup_pelanggan();
    popup_kartu fd_kartu = new popup_kartu();
    popup_karyawan1 fd_karyawan = new popup_karyawan1();
    
    public String get_id_perusahaan() {
        return str_id_perusahaan;
    }

    public String get_nama_perusahaan() {
        return str_nama_perusahaan;
    }
    
    public String get_id_pelanggan() {
        return no_ktp;
    }

    public String get_nama_pelanggan() {
        return nama_pelanggan;
    }
    
    public String get_nopol() {
        return nopol;
    }
    
    public String get_no_kartu() {
        return pop_no_kartu;
    }

    public String get_jenis() {
        return pop_jenis;
    }
    
    public String get_harga() {
        return pop_harga;
    }
    
    public String get_nama_karyawan() {
        return str_nama;
    }

    public void close_popup() {
        fd_perusahaan.setVisible(false);
        fd_pelanggan.setVisible(false);
        fd_kartu.setVisible(false);
        fd_karyawan.setVisible(false);
    }
    
    public void itemTerpilih_perusahaan() {
        fd_perusahaan.fa_perusahaan = this;
        tid_perusahaan.setText(str_id_perusahaan);
        tnama_perusahaan.setText(str_nama_perusahaan);     
           
        enable_mainframe();
    }
    
    public void itemTerpilih_pelanggan() {  
        fd_pelanggan.fa_pelanggan = this;
        tktp.setText(no_ktp);
        tnama_pelanggan.setText(nama_pelanggan);
        tnopol.setText(nopol);
        
        
        enable_mainframe();
    }
    
    public void itemTerpilih_kartu() {  
        fd_kartu.fa_kartu = this;
        tno_kartu.setText(pop_no_kartu);
        tjenis.setText(pop_jenis);
        tharga.setText(pop_harga);
        
        enable_mainframe();
    }
    
    public void itemTerpilih_karyawan() {  
        fd_karyawan.fb_karyawan = this;
        tkasir.setText(str_nama);
        
        enable_mainframe();
    }
    
     public void itemTidakTerpilih() {
        fd_perusahaan.fa_perusahaan = this;
        fd_pelanggan.fa_pelanggan = this;
        fd_kartu.fa_kartu = this;
        enable_mainframe();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        label_tanggal = new javax.swing.JLabel();
        label_waktu = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        bcari = new javax.swing.JButton();
        bclear3 = new javax.swing.JButton();
        bclear2 = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_penjualan = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        bclear = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        bdelete = new javax.swing.JButton();
        bsave = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        tnama_perusahaan = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tktp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        bcari_perusahaan = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tnopol = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        bcari_kartu = new javax.swing.JButton();
        bcari_ktp = new javax.swing.JButton();
        tjenis = new javax.swing.JTextField();
        tnama_pelanggan = new javax.swing.JTextField();
        tdurasi = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tperiode = new javax.swing.JTextField();
        tno_kartu = new javax.swing.JTextField();
        tid_perusahaan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tbayar = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tharga = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tid_penjualan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bcarikasir = new javax.swing.JButton();
        tgl_penjualan = new com.toedter.calendar.JDateChooser();
        tkasir = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PT Securindo Packatama Indonesia - Penjualan Kartu Berlangganan");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1020, 664));
        setName(""); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setFocusable(false);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_back.png"))); // NOI18N
        jButton1.setText("Back");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/staff - icon.png"))); // NOI18N
        jLabel1.setText("Penjualan Kartu Berlangganan");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tanggal :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Waktu   :");

        label_tanggal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label_tanggal.setForeground(new java.awt.Color(255, 255, 255));
        label_tanggal.setText("jLabel11");

        label_waktu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label_waktu.setForeground(new java.awt.Color(255, 255, 255));
        label_waktu.setText("jLabel11");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_waktu, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addComponent(label_tanggal)
                    .addComponent(jLabel10)
                    .addComponent(label_waktu))
                .addContainerGap())
        );

        bcari.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_search.png"))); // NOI18N
        bcari.setText("Cari");
        bcari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });

        bclear3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bclear3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_refresh.png"))); // NOI18N
        bclear3.setText("Refresh");
        bclear3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bclear3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bclear3ActionPerformed(evt);
            }
        });

        bclear2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bclear2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_print.png"))); // NOI18N
        bclear2.setText("Print");
        bclear2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bclear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bclear2ActionPerformed(evt);
            }
        });

        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cariKeyPressed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Penjualan Kartu", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tabel_penjualan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabel_penjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_penjualan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabel_penjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_penjualanMouseClicked(evt);
            }
        });
        tabel_penjualan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tabel_penjualanPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_penjualan);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(bclear2)
                        .addGap(6, 6, 6)
                        .addComponent(bclear3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bcari)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bclear2)
                    .addComponent(bclear3)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bcari)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bclear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_cancel.png"))); // NOI18N
        bclear.setText("Clear");
        bclear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bclearActionPerformed(evt);
            }
        });

        bedit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_edit3.png"))); // NOI18N
        bedit.setText("Edit");
        bedit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });

        bdelete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bdelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_delete.png"))); // NOI18N
        bdelete.setText("Delete");
        bdelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdeleteActionPerformed(evt);
            }
        });

        bsave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_save.png"))); // NOI18N
        bsave.setText("Save");
        bsave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsaveActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("bulan");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Nama Perusahaan");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("No Kartu");

        bcari_perusahaan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bcari_perusahaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_search.png"))); // NOI18N
        bcari_perusahaan.setText("Cari");
        bcari_perusahaan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bcari_perusahaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari_perusahaanActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("No KTP");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Total Bayar");

        tnopol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnopolActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Durasi");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("ID Perusahaan");

        bcari_kartu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bcari_kartu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_search.png"))); // NOI18N
        bcari_kartu.setText("Cari");
        bcari_kartu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bcari_kartu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari_kartuActionPerformed(evt);
            }
        });

        bcari_ktp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bcari_ktp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_search.png"))); // NOI18N
        bcari_ktp.setText("Cari");
        bcari_ktp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bcari_ktp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari_ktpActionPerformed(evt);
            }
        });

        tdurasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tdurasiActionPerformed(evt);
            }
        });
        tdurasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tdurasiKeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Keterangan Periode");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("No Kendaraan");

        tperiode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Nama Pelanggan");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Jenis Kartu");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Harga");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tdurasi, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tharga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tbayar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tperiode, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tnama_pelanggan, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tnopol)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tktp)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bcari_ktp))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tid_perusahaan)
                                        .addGap(10, 10, 10)
                                        .addComponent(bcari_perusahaan))
                                    .addComponent(tnama_perusahaan))
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tjenis)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tno_kartu)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bcari_kartu)))
                                .addGap(23, 23, 23))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tid_perusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari_perusahaan))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tnama_perusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bcari_ktp)
                    .addComponent(tktp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tnama_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tnopol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bcari_kartu)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tno_kartu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tjenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tdurasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tperiode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Penjualan", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("ID Penjualan");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Kasir");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Tanggal");

        bcarikasir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bcarikasir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_search.png"))); // NOI18N
        bcarikasir.setText("Cari");
        bcarikasir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bcarikasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcarikasirActionPerformed(evt);
            }
        });

        tgl_penjualan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tgl_penjualanPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tid_penjualan)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(tkasir, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bcarikasir))
                            .addComponent(tgl_penjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1)))
                .addGap(21, 21, 21))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tid_penjualan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tgl_penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bcarikasir)
                    .addComponent(tkasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(bsave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bedit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bdelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bclear))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bdelete)
                    .addComponent(bclear)
                    .addComponent(bedit)
                    .addComponent(bsave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1126, 702));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    setExtendedState(form_penjualan_kartu.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bclear3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bclear3ActionPerformed
        datatable();
        kosong();
        autoNumber();
        tidak_aktip();
    }//GEN-LAST:event_bclear3ActionPerformed

    private void bclear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bclear2ActionPerformed
        try{
            String namaFile="src/laporan/laporan_penjualan.jasper";
            Connection conn=new koneksi().connect();
            HashMap parameter=new HashMap();
            File report_file=new File(namaFile);
            JasperPrint jasperPrint=JasperFillManager.fillReport(namaFile, parameter, conn);
            JasperViewer.viewReport(jasperPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_bclear2ActionPerformed

    private void tabel_penjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_penjualanMouseClicked
          try{
            int bar = tabel_penjualan.getSelectedRow();
            String a = tabmode.getValueAt(bar, 0).toString();
            String b = tabmode.getValueAt(bar, 1).toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dt = sdf.parse(b);
            String c = tabmode.getValueAt(bar, 2).toString();
            String d = tabmode.getValueAt(bar, 3).toString();
            String e = tabmode.getValueAt(bar, 4).toString();
            String f = tabmode.getValueAt(bar, 5).toString();
            String g = tabmode.getValueAt(bar, 6).toString();
            String h = tabmode.getValueAt(bar, 7).toString();
            String i = tabmode.getValueAt(bar, 8).toString();
            String j = tabmode.getValueAt(bar, 9).toString();
            String k = tabmode.getValueAt(bar, 10).toString();
            String l = tabmode.getValueAt(bar, 11).toString();
            String m = tabmode.getValueAt(bar, 12).toString();
            String n = tabmode.getValueAt(bar, 13).toString();
           
            tid_penjualan.setText(a);
            tgl_penjualan.setDate(dt);
            tkasir.setText(c);
            tid_perusahaan.setText(d);
            tnama_perusahaan.setText(e);
            tktp.setText(f);
            tnama_pelanggan.setText(g);
            tnopol.setText(h);
            tno_kartu.setText(i);
            tjenis.setText(j);
            tharga.setText(k);
            tdurasi.setText(l);
            tbayar.setText(m);
            tperiode.setText(n);
                                     
        } catch (Exception exc) {}
    }//GEN-LAST:event_tabel_penjualanMouseClicked

    private void bclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bclearActionPerformed
    datatable();
    kosong();
    autoNumber();
    tidak_aktip();
    }//GEN-LAST:event_bclearActionPerformed

    private void bdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdeleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus Data?", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok == 0){
            String sql = "delete from data_penjualan_kartu where id_penjualan='"+ tid_penjualan.getText() +"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
                tid_penjualan.requestFocus();
                datatable();
                autoNumber();
                tidak_aktip();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus " + e);
            }
        }
        tidak_aktip();
    }//GEN-LAST:event_bdeleteActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        try {
            String sql = "update data_penjualan_kartu set tanggal_penjualan=?, nama_karyawan=?, id_perusahaan=?, nama_perusahaan=?,"
                    + "no_ktp=? , nama_pelanggan=?, no_kendaraan=?, no_kartu=?,"
                    + "jenis=?, harga=?, durasi=?, total=?, periode=? where id_penjualan=?";
            PreparedStatement stat = conn.prepareStatement (sql);
            stat.setString (1, tgl);
            stat.setString (2, tkasir.getText());
            stat.setString (3, tid_perusahaan.getText());
            stat.setString (4, tnama_perusahaan.getText());
            stat.setString (5, tktp.getText());
            stat.setString (6, tnama_pelanggan.getText());
            stat.setString (7, tnopol.getText());
            stat.setString (8, tno_kartu.getText());
            stat.setString (9, tjenis.getText());
            stat.setString (10, tharga.getText());
            stat.setString (11, tdurasi.getText());
            stat.setString (12, tbayar.getText());
            stat.setString (13, tperiode.getText());

            stat.setString (14, tid_penjualan.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog (null, "Data Berhasil Diubah");
            kosong();
            tid_penjualan.requestFocus();
            datatable();
            autoNumber();                
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah " +e);
        }
        tidak_aktip();      
    }//GEN-LAST:event_beditActionPerformed

    private void bsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsaveActionPerformed
         String sbl = "delete from data_kartu where no_kartu='"+ tno_kartu.getText() +"'";
         try{
                PreparedStatement hasil = conn1.prepareStatement(sbl);
                hasil.executeUpdate();
                } catch (SQLException e) {}
                
                
        String sql= "insert into data_penjualan_kartu values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tid_penjualan.getText());
            stat.setString(2, tgl);
            stat.setString(3, tkasir.getText());
            stat.setString(4, tid_perusahaan.getText());
            stat.setString(5, tnama_perusahaan.getText());
            stat.setString(6, tktp.getText());
            stat.setString(7, tnama_pelanggan.getText());
            stat.setString(8, tnopol.getText());
            stat.setString(9, tno_kartu.getText());
            stat.setString(10, tjenis.getText());
            stat.setString(11, tharga.getText());
            stat.setString(12, tdurasi.getText());
            stat.setString(13, tbayar.getText());
            stat.setString(14, tperiode.getText());
           
            stat.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong();
            tid_penjualan.requestFocus();
            datatable();
            autoNumber();
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
        tidak_aktip();
    }//GEN-LAST:event_bsaveActionPerformed

    private void tgl_penjualanPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tgl_penjualanPropertyChange
        if (tgl_penjualan.getDate() !=null){
            tgl=format.format(tgl_penjualan.getDate());
        }
    }//GEN-LAST:event_tgl_penjualanPropertyChange

    private void bcari_ktpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari_ktpActionPerformed
     close_popup();
        //disable_cari();
        fd_pelanggan.fa_pelanggan = this;
        fd_pelanggan.setVisible(true);
        fd_pelanggan.setResizable(false);

        // setting popup ditengah layar
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
        int x = layar.width / 2 - fd_pelanggan.getSize().width / 2;
        int y = layar.height / 2 - fd_pelanggan.getSize().height / 2;
        fd_pelanggan.setLocation(x, y);

        /*btn_cancel.setEnabled(false);
        btn_save.setEnabled(false);
        btn_clear.setEnabled(false);
        */
        disable_mainframe();
    }//GEN-LAST:event_bcari_ktpActionPerformed

    private void txt_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyPressed

    }//GEN-LAST:event_txt_cariKeyPressed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        Object[] Baris = {"ID Penjualan","Tanggal","Kasir","ID Perusahaan","Nama Perusahaan","No KTP","Nama Pelanggan","No Kendaraan",
            "No Kartu","Jenis Kartu","Harga","Durasi","Total Bayar","Periode"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel_penjualan.setModel(tabmode);
        String sql = "select * from data_penjualan_kartu where id_penjualan like '%"+txt_cari.getText()+"%'";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_penjualan");
                String b = hasil.getString("tanggal_penjualan");
                String c = hasil.getString("nama_karyawan");
                String d = hasil.getString("id_perusahaan");
                String e = hasil.getString("nama_perusahaan");
                String f = hasil.getString("no_ktp");
                String g = hasil.getString("nama_pelanggan");
                String h = hasil.getString("no_kendaraan");
                String i = hasil.getString("no_kartu");
                String j = hasil.getString("jenis");
                String k = hasil.getString("durasi");
                String l = hasil.getString("harga");
                String m = hasil.getString("total");
                String n = hasil.getString("periode");

                String [] data = {a,b,c,d,e,f,g,h,i,j,k,l,m,n};
                tabmode.addRow(data);
            }
        }catch (Exception e) {
        }
        tidak_aktip();
    }//GEN-LAST:event_bcariActionPerformed

    private void bcari_perusahaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari_perusahaanActionPerformed
     close_popup();
        //disable_cari();
        fd_perusahaan.fa_perusahaan = this;
        fd_perusahaan.setVisible(true);
        fd_perusahaan.setResizable(false);

        // setting popup ditengah layar
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
        int x = layar.width / 2 - fd_perusahaan.getSize().width / 2;
        int y = layar.height / 2 - fd_perusahaan.getSize().height / 2;
        fd_perusahaan.setLocation(x, y);

        /*btn_cancel.setEnabled(false);
        btn_save.setEnabled(false);
        btn_clear.setEnabled(false);
        */
        disable_mainframe();
    }//GEN-LAST:event_bcari_perusahaanActionPerformed

    private void bcari_kartuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari_kartuActionPerformed
    close_popup();
        //disable_cari();
        fd_kartu.fa_kartu = this;
        fd_kartu.setVisible(true);
        fd_kartu.setResizable(false);

        // setting popup ditengah layar
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
        int x = layar.width / 2 - fd_kartu.getSize().width / 2;
        int y = layar.height / 2 - fd_kartu.getSize().height / 2;
        fd_kartu.setLocation(x, y);

        /*btn_cancel.setEnabled(false);
        btn_save.setEnabled(false);
        btn_clear.setEnabled(false);
        */
        disable_mainframe();
    }//GEN-LAST:event_bcari_kartuActionPerformed

    private void tdurasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tdurasiActionPerformed
    int xharga, xdurasi, xbayar;
    xharga = Integer.parseInt(tharga.getText());
    xdurasi = Integer.parseInt(tdurasi.getText());
    xbayar = xharga*xdurasi;
    String xsubtotal = Integer.toString(xbayar);
    tbayar.setText(xsubtotal);
    }//GEN-LAST:event_tdurasiActionPerformed

    private void tabel_penjualanPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tabel_penjualanPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_penjualanPropertyChange

    private void bcarikasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcarikasirActionPerformed
   close_popup();
        //disable_cari();
        fd_karyawan.fb_karyawan = this;
        fd_karyawan.setVisible(true);
        fd_karyawan.setResizable(false);

        // setting popup ditengah layar
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
        int x = layar.width / 2 - fd_karyawan.getSize().width / 2;
        int y = layar.height / 2 - fd_karyawan.getSize().height / 2;
        fd_karyawan.setLocation(x, y);

        /*btn_cancel.setEnabled(false);
        btn_save.setEnabled(false);
        btn_clear.setEnabled(false);
        */
        disable_mainframe();
    }//GEN-LAST:event_bcarikasirActionPerformed

    private void tnopolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnopolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnopolActionPerformed

    private void tdurasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tdurasiKeyPressed
    int xharga, xdurasi, xbayar;
    xharga = Integer.parseInt(tharga.getText());
    xdurasi = Integer.parseInt(tdurasi.getText());
    xbayar = xharga*xdurasi;
    String xsubtotal = Integer.toString(xbayar);
    tbayar.setText(xsubtotal);
    }//GEN-LAST:event_tdurasiKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form_penjualan_kartu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_penjualan_kartu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_penjualan_kartu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_penjualan_kartu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_penjualan_kartu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bcari;
    private javax.swing.JButton bcari_kartu;
    private javax.swing.JButton bcari_ktp;
    private javax.swing.JButton bcari_perusahaan;
    private javax.swing.JButton bcarikasir;
    private javax.swing.JButton bclear;
    private javax.swing.JButton bclear2;
    private javax.swing.JButton bclear3;
    private javax.swing.JButton bdelete;
    private javax.swing.JButton bedit;
    private javax.swing.JButton bsave;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_tanggal;
    private javax.swing.JLabel label_waktu;
    private javax.swing.JTable tabel_penjualan;
    private javax.swing.JTextField tbayar;
    private javax.swing.JTextField tdurasi;
    private com.toedter.calendar.JDateChooser tgl_penjualan;
    private javax.swing.JTextField tharga;
    private javax.swing.JTextField tid_penjualan;
    private javax.swing.JTextField tid_perusahaan;
    private javax.swing.JTextField tjenis;
    private javax.swing.JTextField tkasir;
    private javax.swing.JTextField tktp;
    private javax.swing.JTextField tnama_pelanggan;
    private javax.swing.JTextField tnama_perusahaan;
    private javax.swing.JTextField tno_kartu;
    private javax.swing.JTextField tnopol;
    private javax.swing.JTextField tperiode;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
}
