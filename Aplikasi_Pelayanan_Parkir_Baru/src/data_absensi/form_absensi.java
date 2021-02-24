package data_absensi;

import popup.popup_karyawan2;
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

public class form_absensi extends javax.swing.JFrame {
private Connection conn = new koneksi().connect();
private DefaultTableModel tabmode;
private DefaultTableModel tabmode1;
    public String tgl;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public form_absensi() {
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage("src/gambar/logo_frame.png");
        setIconImage(icon);
        tglabsen.setDateFormatString("yyyy-MM-dd");
        
        datatable(); 
        datatable_detail();
        cariid();
        waktu();
        autoNumber();
        autoNumber1();
        
        tnik.setEnabled(false);
        tnama.setEnabled(false);
        tid_absensi.setEnabled(false);
        tid_kehadiran.setEnabled(false);
        bsave.setEnabled(false);
    }
    
    private void autoNumber(){
        String idp = "AB00";
        int i = 0;
         String sql = "select id_absensi from data_absensi";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                idp = hasil.getString("id_absensi");
            }
                idp = idp.substring(2);
                i = Integer.parseInt(idp)+1;
                idp = "0" +i;
                idp = "AB"+ idp.substring(idp.length()-2);
                tid_absensi.setText(idp);
                tnik.setEnabled(false);
                tnama.setEnabled(false);
                tid_absensi.setEnabled(false);
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
    
    private void autoNumber1(){
        String idb = "KH00";
        int i = 0;
         String sql = "select id_absensi_baru from detail_data_absensi";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                idb = hasil.getString("id_absensi_baru");
            }
                idb = idb.substring(2);
                i = Integer.parseInt(idb)+1;
                idb = "0" +i;
                idb = "KH"+ idb.substring(idb.length()-2);
                tid_kehadiran.setText(idb);
                tnik.setEnabled(false);
                tnama.setEnabled(false);
                tid_absensi.setEnabled(false);
                tid_kehadiran.setEnabled(false);
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
    tglabsen.setDate(null);
    tlokasi.setText("");
    tcari.setText("");
   
    tnik.setText("");
    tnama.setText("");
    cbshift.setSelectedIndex(0);
    tmasuk.setText("");
    tpulang.setText("");
    cbketerangan.setSelectedIndex(0);
    tcari_id.setText("");
    
    tlokasi.requestFocus();
    }
   
    protected void datatable() {
        Object[] Baris = {"ID Absensi","Tanggal Absensi","Lokasi"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel_absensi.setModel(tabmode);
        String sql = "select * from data_absensi ORDER BY id_absensi DESC";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String aa = hasil.getString("id_absensi");
                String bb = hasil.getString("tanggal_absensi");
                String cc = hasil.getString("lokasi");
                                               
                String [] data = {aa,bb,cc};
                tabmode.addRow(data);
            }
        }catch (Exception e) {
        }
    }
    
     protected void datatable_detail() {
        Object[] Baris = {"ID Absensi","Tanggal Absensi","Lokasi","ID Kehadiran","NIK","Nama Petugas","Shift","Jam Masuk",
            "Jam Pulang", "Keterangan"};
        tabmode1 = new DefaultTableModel(null, Baris);
        tabel_detail_absensi.setModel(tabmode1);
        String sql = "select * from detail_data_absensi ORDER BY detail_tanggal_absensi DESC";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("detail_id_absensi");
                String b = hasil.getString("detail_tanggal_absensi");
                String c = hasil.getString("detail_lokasi");
                String d = hasil.getString("id_absensi_baru");
                String e = hasil.getString("detail_nik");
                String f = hasil.getString("detail_nama");
                String g = hasil.getString("detail_shift");
                String h = hasil.getString("detail_jam_masuk");
                String i = hasil.getString("detail_jam_pulang");
                String j = hasil.getString("detail_keterangan");
                               
                String [] data = {a,b,c,d,e,f,g,h,i,j};
                tabmode1.addRow(data);
            }
        }catch (Exception e) {
        }
    }
    
     protected void cariid(){
     Object[] Baris = {"ID Absensi","Tanggal Absensi","Lokasi","ID Kehadiran","NIK","Nama Petugas","Shift","Jam Masuk",
            "Jam Pulang", "Keterangan"};
        tabmode1 = new DefaultTableModel(null, Baris);
        tabel_detail_absensi.setModel(tabmode1);
        String sql = "select * from detail_data_absensi where detail_id_absensi like '%"+tcari_id.getText()+"%'";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("detail_id_absensi");
                String b = hasil.getString("detail_tanggal_absensi");
                String c = hasil.getString("detail_lokasi");
                String d = hasil.getString("id_absensi_baru");
                String e = hasil.getString("detail_nik");
                String f = hasil.getString("detail_nama");
                String g = hasil.getString("detail_shift");
                String h = hasil.getString("detail_jam_masuk");
                String i = hasil.getString("detail_jam_pulang");
                String j = hasil.getString("detail_keterangan");
                               
                String [] data = {a,b,c,d,e,f,g,h,i,j};
                tabmode1.addRow(data);
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
    
     public String str_nik, str_nama;
     
    popup_karyawan2 fe_karyawan = new popup_karyawan2();
    
      public String get_nik_karyawan() {
        return str_nik;
    }

    public String get_nama_karyawan() {
        return str_nama;
    }
    public void close_popup() {
        fe_karyawan.setVisible(false);
    }
    
    public void itemTerpilih_karyawan() {
        fe_karyawan.fb_karyawan = this;
        tnik.setText(str_nik);
        tnama.setText(str_nama);
      
        enable_mainframe();
    }
    
     public void itemTidakTerpilih() {
        fe_karyawan.fb_karyawan = this;

        enable_mainframe();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        tnik = new javax.swing.JTextField();
        tnama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tmasuk = new javax.swing.JTextField();
        badd = new javax.swing.JButton();
        bcari1 = new javax.swing.JButton();
        cbshift = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbketerangan = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        tpulang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tid_kehadiran = new javax.swing.JTextField();
        bclear1 = new javax.swing.JButton();
        panel_id = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tlokasi = new javax.swing.JTextField();
        tid_absensi = new javax.swing.JTextField();
        tglabsen = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        bsave = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_absensi = new javax.swing.JTable();
        bedit = new javax.swing.JButton();
        bdelete = new javax.swing.JButton();
        tcari = new javax.swing.JTextField();
        bcari = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        bclear = new javax.swing.JButton();
        bclear3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_detail_absensi = new javax.swing.JTable();
        bedit1 = new javax.swing.JButton();
        bdelete1 = new javax.swing.JButton();
        bprint1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        tcari_id = new javax.swing.JTextField();
        bcari2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PT Securindo Packatama Indonesia - Absensi");
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
        jLabel1.setText("Absensi");

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
                .addGap(18, 18, 18)
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detail Data Absensi", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Jam Pulang");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Jam Masuk");

        badd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        badd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/add - icon.png"))); // NOI18N
        badd.setText("Add");
        badd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        badd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baddActionPerformed(evt);
            }
        });

        bcari1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bcari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_search.png"))); // NOI18N
        bcari1.setText("Cari");
        bcari1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bcari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari1ActionPerformed(evt);
            }
        });

        cbshift.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbshift.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pagi", "Middle", "Siang", "Malam", "Tidak Ada" }));
        cbshift.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Keterangan");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("NIK");

        cbketerangan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbketerangan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hadir", "Sakit", "Alpa", "Izin", "Cuti" }));
        cbketerangan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Shift");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Nama Petugas");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("ID Kehadiran");

        bclear1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bclear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_cancel.png"))); // NOI18N
        bclear1.setText("Clear");
        bclear1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bclear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bclear1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)))
                    .addComponent(jLabel12)
                    .addComponent(jLabel15))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpulang)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tnik, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bcari1))
                    .addComponent(cbketerangan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tnama, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbshift, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bclear1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(badd))
                    .addComponent(tmasuk)
                    .addComponent(tid_kehadiran))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tid_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari1))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbshift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpulang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbketerangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(badd)
                    .addComponent(bclear1))
                .addContainerGap())
        );

        panel_id.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input Data Absensi", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Tanggal");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Lokasi");

        tglabsen.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglabsenPropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("ID Absensi");

        bsave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_save.png"))); // NOI18N
        bsave.setText("Save");
        bsave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_idLayout = new javax.swing.GroupLayout(panel_id);
        panel_id.setLayout(panel_idLayout);
        panel_idLayout.setHorizontalGroup(
            panel_idLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_idLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_idLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(panel_idLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tid_absensi)
                    .addComponent(tglabsen, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addComponent(tlokasi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_idLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bsave)
                .addContainerGap())
        );
        panel_idLayout.setVerticalGroup(
            panel_idLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_idLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_idLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tid_absensi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_idLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tglabsen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_idLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tlokasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bsave)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Master Absensi", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tabel_absensi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabel_absensi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_absensi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabel_absensi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_absensiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_absensi);

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

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tcariKeyPressed(evt);
            }
        });

        bcari.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_search.png"))); // NOI18N
        bcari.setText("Cari");
        bcari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Input ID Absensi");

        bclear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_cancel.png"))); // NOI18N
        bclear.setText("Clear All");
        bclear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bclearActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bdelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bclear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bclear3)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bcari)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bdelete)
                    .addComponent(tcari)
                    .addComponent(bcari)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bclear)
                    .addComponent(bclear3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Absensi", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tabel_detail_absensi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabel_detail_absensi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_detail_absensi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabel_detail_absensi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_detail_absensiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_detail_absensi);

        bedit1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bedit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_edit3.png"))); // NOI18N
        bedit1.setText("Edit");
        bedit1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bedit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bedit1ActionPerformed(evt);
            }
        });

        bdelete1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bdelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_delete.png"))); // NOI18N
        bdelete1.setText("Delete");
        bdelete1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bdelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdelete1ActionPerformed(evt);
            }
        });

        bprint1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bprint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_print.png"))); // NOI18N
        bprint1.setText("Print All");
        bprint1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bprint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bprint1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Input ID Absensi");

        bcari2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bcari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_search.png"))); // NOI18N
        bcari2.setText("Cari");
        bcari2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bcari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(bedit1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bdelete1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bprint1)
                        .addGap(173, 173, 173)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tcari_id, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bcari2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bedit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bdelete1)
                    .addComponent(bprint1)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tcari_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

        setSize(new java.awt.Dimension(1280, 702));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    setExtendedState(form_absensi.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bclear3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bclear3ActionPerformed
        datatable();
        datatable_detail();
        kosong();
        autoNumber();
        autoNumber1();
    }//GEN-LAST:event_bclear3ActionPerformed

    private void tabel_absensiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_absensiMouseClicked
   try{
            int bar = tabel_absensi.getSelectedRow();
            String aa = tabmode.getValueAt(bar, 0).toString();
            String bb = tabmode.getValueAt(bar, 1).toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dt1 = sdf.parse(bb);
            String cc = tabmode.getValueAt(bar, 2).toString();
                      
            tid_absensi.setText(aa);
            tglabsen.setDate(dt1);
            tlokasi.setText(cc);
                         
        } catch (Exception exc) {}
    }//GEN-LAST:event_tabel_absensiMouseClicked

    private void bclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bclearActionPerformed
    datatable();
    datatable_detail();
    kosong();
    autoNumber();
    autoNumber1();
    
    tid_absensi.setEnabled(false);
    tglabsen.setEnabled(true);
    tlokasi.setEnabled(true);
    tid_kehadiran.setEnabled(false);
    tnik.setEnabled(false);
    tnama.setEnabled(false);    
    }//GEN-LAST:event_bclearActionPerformed

    private void bdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdeleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus Data?", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok == 0){
            String sql = "DELETE data_absensi, detail_data_absensi FROM data_absensi INNER JOIN detail_data_absensi ON detail_data_absensi.detail_id_absensi = data_absensi.id_absensi WHERE data_absensi.id_absensi= '"+ tid_absensi.getText() +"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
                tlokasi.requestFocus();
                datatable();
                datatable_detail();
                autoNumber();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus " + e);
            }
        }
    }//GEN-LAST:event_bdeleteActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        try {
            String sql = "update data_absensi set tanggal_absensi=? , lokasi=? where id_absensi=?";
            PreparedStatement stat = conn.prepareStatement (sql);
            stat.setString (1, tgl);
            stat.setString (2, tlokasi.getText());
            stat.setString (3, tid_absensi.getText());
           
            stat.executeUpdate();
            JOptionPane.showMessageDialog (null, "Data Berhasil Diubah");
            kosong();
            tlokasi.requestFocus();
            datatable();
            autoNumber();           
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah " +e);
        }
    }//GEN-LAST:event_beditActionPerformed

    private void bsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsaveActionPerformed
    String sql = "insert into data_absensi values (?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tid_absensi.getText());
            stat.setString(2, tgl);
            stat.setString(3, tlokasi.getText());
                       
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong();
            tlokasi.requestFocus();
            datatable();
            autoNumber();
            autoNumber1();
            
            tid_absensi.setEnabled(false);
            tglabsen.setEnabled(true);
            tlokasi.setEnabled(true);
             bsave.setEnabled(false);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
    }//GEN-LAST:event_bsaveActionPerformed

    private void tglabsenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglabsenPropertyChange
        if (tglabsen.getDate() !=null){
            tgl=format.format(tglabsen.getDate());
        }
    }//GEN-LAST:event_tglabsenPropertyChange

    private void bcari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari1ActionPerformed
     close_popup();
        fe_karyawan.fb_karyawan = this;
        fe_karyawan.setVisible(true);
        fe_karyawan.setResizable(false);

        // setting popup ditengah layar
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
        int x = layar.width / 2 - fe_karyawan.getSize().width / 2;
        int y = layar.height / 2 - fe_karyawan.getSize().height / 2;
        fe_karyawan.setLocation(x, y);

        disable_mainframe();
    }//GEN-LAST:event_bcari1ActionPerformed

    private void tcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyPressed

    }//GEN-LAST:event_tcariKeyPressed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        Object[] Baris = {"ID Absensi","Tanggal Absensi","Lokasi"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel_absensi.setModel(tabmode);
        String sql = "select * from data_absensi where id_absensi like '%"+tcari.getText()+"%'";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_absensi");
                String b = hasil.getString("tanggal_absensi");
                String c = hasil.getString("lokasi");
            
                String [] data = {a,b,c};
                tabmode.addRow(data);
            }
        }catch (Exception e) {}
    }//GEN-LAST:event_bcariActionPerformed

    private void baddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baddActionPerformed
    String sql = "insert into detail_data_absensi values (?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tid_absensi.getText());
            stat.setString(2, tgl);
            stat.setString(3, tlokasi.getText());
            stat.setString(4, tid_kehadiran.getText());
            stat.setString(5, tnik.getText());
            stat.setString(6, tnama.getText());
            
            stat.setString(7, cbshift.getSelectedItem().toString());
            stat.setString(8, tmasuk.getText());
            stat.setString(9, tpulang.getText());
            stat.setString(10, cbketerangan.getSelectedItem().toString());
                      
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            bcari1.requestFocus();
            datatable_detail();
            autoNumber1();
            
            tid_absensi.setEnabled(false);
            tglabsen.setEnabled(false);
            tlokasi.setEnabled(false);
             bsave.setEnabled(true);
            
            tnik.setText("");
            tnama.setText("");
            cbshift.setSelectedIndex(0);
            tmasuk.setText("");
            tpulang.setText("");
            cbketerangan.setSelectedIndex(0);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
    }//GEN-LAST:event_baddActionPerformed

    private void tabel_detail_absensiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_detail_absensiMouseClicked
    try{
            int bar = tabel_detail_absensi.getSelectedRow();
            String a = tabmode1.getValueAt(bar, 0).toString();
            String b = tabmode1.getValueAt(bar, 1).toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dt = sdf.parse(b);
            String c = tabmode1.getValueAt(bar, 2).toString();
            String d = tabmode1.getValueAt(bar, 3).toString();
            String e = tabmode1.getValueAt(bar, 4).toString();
            String f = tabmode1.getValueAt(bar, 5).toString();
            String g = tabmode1.getValueAt(bar, 6).toString();
            String h = tabmode1.getValueAt(bar, 7).toString();
            String i = tabmode1.getValueAt(bar, 8).toString();
            String j = tabmode1.getValueAt(bar, 9).toString();
            
            tid_absensi.setText(a);
            tglabsen.setDate(dt);
            tlokasi.setText(c);
            tid_kehadiran.setText(d);
            tnik.setText(e);
            tnama.setText(f);
            cbshift.setSelectedItem(g);
            tmasuk.setText(h);
            tpulang.setText(i);
            cbketerangan.setSelectedItem(j);
           
        } catch (Exception exc) {}
    }//GEN-LAST:event_tabel_detail_absensiMouseClicked

    private void bcari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari2ActionPerformed
    cariid();
    }//GEN-LAST:event_bcari2ActionPerformed

    private void bdelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdelete1ActionPerformed
    int ok = JOptionPane.showConfirmDialog(null, "Hapus Data?", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok == 0){
            String sql = "delete from detail_data_absensi where id_absensi_baru='"+ tid_kehadiran.getText() +"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                tid_kehadiran.requestFocus();
                kosong();
                datatable_detail();
                autoNumber1();
                autoNumber();
            
                bcari1.requestFocus();
                tid_absensi.setEnabled(false);
                tglabsen.setEnabled(true);
                tlokasi.setEnabled(true);
                tid_kehadiran.setEnabled(false);
                tnik.setEnabled(false);
                tnama.setEnabled(false);
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus " + e);
            }
        }
    }//GEN-LAST:event_bdelete1ActionPerformed

    private void bedit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bedit1ActionPerformed
    try {
            String sql = "update detail_data_absensi set detail_id_absensi=?, detail_tanggal_absensi=?, detail_lokasi=?, detail_nik=? , detail_nama=?, detail_shift=?, detail_jam_masuk=?, detail_jam_pulang=?, detail_keterangan=? where id_absensi_baru=?";
            PreparedStatement stat = conn.prepareStatement (sql);
            stat.setString (1, tid_absensi.getText());
            stat.setString (2, tgl);
            stat.setString (3, tlokasi.getText());
            stat.setString (4, tnik.getText());
            stat.setString (5, tnama.getText());
            stat.setString (6, cbshift.getSelectedItem().toString());
            stat.setString (7, tmasuk.getText());
            stat.setString (8, tpulang.getText());
            stat.setString (9, cbketerangan.getSelectedItem().toString());
            stat.setString (10, tid_kehadiran.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog (null, "Data Berhasil Diubah");
            kosong();
            datatable_detail();
            autoNumber1();
            autoNumber();
            
            bcari1.requestFocus();
            tid_absensi.setEnabled(false);
            tglabsen.setEnabled(true);
            tlokasi.setEnabled(true);
            tid_kehadiran.setEnabled(false);
            tnik.setEnabled(false);
            tnama.setEnabled(false);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah " +e);
        }
    }//GEN-LAST:event_bedit1ActionPerformed

    private void bprint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bprint1ActionPerformed
    try{
        String namaFile="src/laporan/laporan_absensi.jasper";
        Connection conn=new koneksi().connect();
        HashMap parameter=new HashMap();
        File report_file=new File(namaFile);
        JasperPrint jasperPrint=JasperFillManager.fillReport(namaFile, parameter, conn);
        JasperViewer.viewReport(jasperPrint, false);
        JasperViewer.setDefaultLookAndFeelDecorated(true);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    }//GEN-LAST:event_bprint1ActionPerformed

    private void bclear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bclear1ActionPerformed
    //tglabsen.setDate(null); 
    //tlokasi.setText(""); 
    tnik.setText("");   
    tnama.setText("");
    cbshift.setSelectedIndex(0);
    tmasuk.setText("");
    tpulang.setText("");
    cbketerangan.setSelectedIndex(0);   
    
  //  tid_absensi.setEnabled(false);
    tid_kehadiran.setEnabled(false);
    tnik.setEnabled(false);
    tnama.setEnabled(false);
    
   // autoNumber();
    autoNumber1();
    
    }//GEN-LAST:event_bclear1ActionPerformed

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
            java.util.logging.Logger.getLogger(form_absensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_absensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_absensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_absensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new form_absensi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton badd;
    private javax.swing.JButton bcari;
    private javax.swing.JButton bcari1;
    private javax.swing.JButton bcari2;
    private javax.swing.JButton bclear;
    private javax.swing.JButton bclear1;
    private javax.swing.JButton bclear3;
    private javax.swing.JButton bdelete;
    private javax.swing.JButton bdelete1;
    private javax.swing.JButton bedit;
    private javax.swing.JButton bedit1;
    private javax.swing.JButton bprint1;
    private javax.swing.JButton bsave;
    private javax.swing.JComboBox<String> cbketerangan;
    private javax.swing.JComboBox<String> cbshift;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_tanggal;
    private javax.swing.JLabel label_waktu;
    private javax.swing.JPanel panel_id;
    private javax.swing.JTable tabel_absensi;
    private javax.swing.JTable tabel_detail_absensi;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tcari_id;
    private com.toedter.calendar.JDateChooser tglabsen;
    private javax.swing.JTextField tid_absensi;
    private javax.swing.JTextField tid_kehadiran;
    private javax.swing.JTextField tlokasi;
    private javax.swing.JTextField tmasuk;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tnik;
    private javax.swing.JTextField tpulang;
    // End of variables declaration//GEN-END:variables
}
