package data_tiket;

import popup.popup_karyawan;
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

public class form_tiket extends javax.swing.JFrame {
private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    public String tgl;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public form_tiket() {
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage("src/gambar/logo_frame.png");
        setIconImage(icon);
        datatable();      
        autoNumber();
        tglkerja.setDateFormatString("yyyy-MM-dd");
        waktu();
        txt_id_tiket.setEnabled(false);
        txt_nik.setEnabled(false);
        txt_nama.setEnabled(false);
        txt_total_tes.setEnabled(false);
        txt_terpakai.setEnabled(false);
        
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
    txt_id_tiket.setText("");
    tglkerja.setDate(null);
    txt_nik.setText("");
    txt_nama.setText("");
    cbshift.setSelectedIndex(0);
    cbpos.setSelectedIndex(0);
    cbjenis.setSelectedIndex(0);
    txt_cari.setText("");
    txt_tes_awal.setText("");
    txt_tes_akhir.setText("");;
    txt_terjual.setText("");
    txt_total_tes.setText("");
    txt_terpakai.setText("");
    txt_batal.setText("");
   
    txt_id_tiket.requestFocus();
    }
    
    protected void datatable() {
        Object[] Baris = {"Id","Tanggal","NIK","Nama Petugas","Shift","Pos","Jenis Tiket",
            "No Awal", "No Akhir","Tiket Terjual","Total Tes","Tiket Batal","Tiket Terpakai"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel_petugas_tiket.setModel(tabmode);
        String sql = "select * from data_tiket ORDER BY tgl_tiket DESC";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_tiket");
                String b = hasil.getString("tgl_tiket");
                String c = hasil.getString("nik");
                String d = hasil.getString("nama");
                String e = hasil.getString("shift");
                String f = hasil.getString("pos");
                
                String g = hasil.getString("jenis");
                String h = hasil.getString("tes_awal");
                String i = hasil.getString("tes_akhir");
                String j = hasil.getString("terjual");
                String k = hasil.getString("total");
                String l = hasil.getString("batal");
                String m = hasil.getString("terpakai");
               
                String [] data = {a,b,c,d,e,f,g,h,i,j,k,l,m};
                tabmode.addRow(data);
            }
        }catch (Exception e) {
        }
    }
    
     private void autoNumber(){
        String idp = "TK00";
        int i = 0;
         String sql = "select id_tiket from data_tiket";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                idp = hasil.getString("id_tiket");
            }
                idp = idp.substring(2);
                i = Integer.parseInt(idp)+1;
                idp = "0" +i;
                idp = "TK"+ idp.substring(idp.length()-2);
                txt_id_tiket.setText(idp);
                txt_id_tiket.setEditable(false);
            }catch (SQLException e) {
                System.out.println(e.getMessage());
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
     
    popup_karyawan fd_karyawan = new popup_karyawan();
    
      public String get_nik_karyawan() {
        return str_nik;
    }

    public String get_nama_karyawan() {
        return str_nama;
    }
    public void close_popup() {
        fd_karyawan.setVisible(false);
    }
    
    public void itemTerpilih_karyawan() {
        fd_karyawan.fa_karyawan = this;
        txt_nik.setText(str_nik);
        txt_nama.setText(str_nama);
    
        enable_mainframe();
    }
    
     public void itemTidakTerpilih() {
        fd_karyawan.fa_karyawan = this;

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
        jPanel2 = new javax.swing.JPanel();
        txt_tes_awal = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_batal = new javax.swing.JTextField();
        txt_terjual = new javax.swing.JTextField();
        txt_tes_akhir = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_total_tes = new javax.swing.JTextField();
        txt_terpakai = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        bcari1 = new javax.swing.JButton();
        txt_id_tiket = new javax.swing.JTextField();
        txt_nik = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        bclear = new javax.swing.JButton();
        txt_nama = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tglkerja = new com.toedter.calendar.JDateChooser();
        bedit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbshift = new javax.swing.JComboBox<>();
        bdelete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        bsave = new javax.swing.JButton();
        cbjenis = new javax.swing.JComboBox<>();
        cbpos = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        bcari = new javax.swing.JButton();
        bclear3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_petugas_tiket = new javax.swing.JTable();
        bclear2 = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PT Securindo Packatama Indonesia - Karyawan");
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
        jLabel1.setText("Tiket Manual");

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Keterangan Tiket Manual", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Tiket Terjual");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Nomor Awal Tiket");

        txt_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_batalActionPerformed(evt);
            }
        });

        txt_terjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_terjualActionPerformed(evt);
            }
        });

        txt_tes_akhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tes_akhirActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Total Tes Tiket");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Total Tiket Terpakai");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Nomor Akhir Tiket");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Tiket Batal");

        txt_total_tes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_total_tesMouseClicked(evt);
            }
        });
        txt_total_tes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_total_tesActionPerformed(evt);
            }
        });

        txt_terpakai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_terpakaiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tes_awal)
                    .addComponent(txt_tes_akhir)
                    .addComponent(txt_total_tes)
                    .addComponent(txt_terjual)
                    .addComponent(txt_batal)
                    .addComponent(txt_terpakai))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tes_awal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tes_akhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_total_tes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_terjual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_batal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_terpakai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Pos");

        bcari1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bcari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_search.png"))); // NOI18N
        bcari1.setText("Cari");
        bcari1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bcari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Nama Petugas");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Jenis Tiket");

        bclear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_cancel.png"))); // NOI18N
        bclear.setText("Clear");
        bclear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bclearActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Shift");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("NIK");

        tglkerja.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglkerjaPropertyChange(evt);
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Tanggal");

        cbshift.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbshift.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pagi", "Middle", "Siang", "Malam" }));
        cbshift.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        bdelete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bdelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_delete.png"))); // NOI18N
        bdelete.setText("Delete");
        bdelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdeleteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("ID Tiket");

        bsave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/btn_save.png"))); // NOI18N
        bsave.setText("Save");
        bsave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsaveActionPerformed(evt);
            }
        });

        cbjenis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbjenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Casual Mobil", "Pass Mobil", "Casual Box", "Casual Motor", "Pass Motor" }));
        cbjenis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cbpos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbpos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PM1", "PM2", "PM3", "PM4", "PM5" }));
        cbpos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(bsave)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(bedit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bdelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bclear))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(64, 64, 64)
                                        .addComponent(txt_id_tiket, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbshift, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbpos, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbjenis, javax.swing.GroupLayout.Alignment.TRAILING, 0, 234, Short.MAX_VALUE))
                                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(54, 54, 54)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(txt_nik)
                                        .addGap(18, 18, 18)
                                        .addComponent(bcari1))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tglkerja, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(34, 34, 34))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bsave)
                    .addComponent(bdelete)
                    .addComponent(bedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bclear))
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id_tiket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tglkerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbshift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbpos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbjenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Petugas Tiket Manual", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tabel_petugas_tiket.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabel_petugas_tiket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_petugas_tiket.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabel_petugas_tiket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_petugas_tiketMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_petugas_tiket);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addContainerGap())
        );

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(bclear2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cari)
                    .addComponent(bcari)
                    .addComponent(bclear3)
                    .addComponent(bclear2))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        setSize(new java.awt.Dimension(1280, 690));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    setExtendedState(form_tiket.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bclear3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bclear3ActionPerformed
        datatable();
        kosong();
        autoNumber();
    }//GEN-LAST:event_bclear3ActionPerformed

    private void bclear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bclear2ActionPerformed
        try{
            String namaFile="src/laporan/laporan_tiket.jasper";
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

    private void tabel_petugas_tiketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_petugas_tiketMouseClicked
          try{
            int bar = tabel_petugas_tiket.getSelectedRow();
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
           
            txt_id_tiket.setText(a);
            tglkerja.setDate(dt);
            txt_nik.setText(c);
            txt_nama.setText(d);
            cbshift.setSelectedItem(e);
            cbpos.setSelectedItem(f);
            cbjenis.setSelectedItem(g);
            txt_tes_awal.setText(h);
            txt_tes_akhir.setText(i);
            txt_total_tes.setText(j);
            txt_terjual.setText(k);
            txt_batal.setText(l);
            txt_terpakai.setText(m);
             
        } catch (Exception exc) {}
    }//GEN-LAST:event_tabel_petugas_tiketMouseClicked

    private void bclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bclearActionPerformed
    datatable();
    kosong();
    autoNumber();
    }//GEN-LAST:event_bclearActionPerformed

    private void bdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdeleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus Data?", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok == 0){
            String sql = "delete from data_tiket where id_tiket='"+ txt_id_tiket.getText() +"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
                txt_id_tiket.requestFocus();
                datatable();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus " + e);
            }
        }
        autoNumber();
    }//GEN-LAST:event_bdeleteActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        try {
            String sql = "update data_tiket set tgl_tiket=? , nik=?, nama=?, shift=? , pos=?, jenis=?, tes_awal=?,tes_akhir=?, terjual=?, total=?, terpakai=?, batal=? where id_tiket=?";
            PreparedStatement stat = conn.prepareStatement (sql);
            stat.setString (1, tgl);
            stat.setString (2, txt_nik.getText());
            stat.setString (3, txt_nama.getText());
            stat.setString (4, cbshift.getSelectedItem().toString());
            stat.setString (5, cbpos.getSelectedItem().toString());
            stat.setString (6, cbjenis.getSelectedItem().toString());
            stat.setString (7, txt_tes_awal.getText());
            stat.setString (8, txt_tes_akhir.getText());
            stat.setString (9, txt_total_tes.getText());
            stat.setString (10, txt_terjual.getText());
            stat.setString (11, txt_batal.getText());
            stat.setString (12, txt_terpakai.getText());

            stat.setString (13, txt_id_tiket.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog (null, "Data Berhasil Diubah");
            kosong();
            txt_id_tiket.requestFocus();
            datatable();
            txt_total_tes.setEditable(false);
            txt_terpakai.setEditable(false);
           
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah " +e);
        }
        autoNumber();
    }//GEN-LAST:event_beditActionPerformed

    private void bsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsaveActionPerformed
    String sql = "insert into data_tiket values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txt_id_tiket.getText());
            stat.setString(2, tgl);
            stat.setString(3, txt_nik.getText());
            stat.setString(4, txt_nama.getText());
            stat.setString(5, cbshift.getSelectedItem().toString());
            stat.setString(6, cbpos.getSelectedItem().toString());
            
            stat.setString(7, cbjenis.getSelectedItem().toString());
            stat.setString(8, txt_tes_awal.getText());
            stat.setString(9, txt_tes_akhir.getText());
            stat.setString(10, txt_terjual.getText());
            stat.setString(11, txt_total_tes.getText());
            stat.setString(12, txt_terpakai.getText());
            stat.setString(13, txt_batal.getText());
           
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong();
            txt_id_tiket.requestFocus();
            datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
        autoNumber();
    }//GEN-LAST:event_bsaveActionPerformed

    private void tglkerjaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglkerjaPropertyChange
        if (tglkerja.getDate() !=null){
            tgl=format.format(tglkerja.getDate());
        }
    }//GEN-LAST:event_tglkerjaPropertyChange

    private void txt_terjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_terjualActionPerformed
        //ttotal1.setEnabled(false);
        txt_terjual.setVisible(false);
    }//GEN-LAST:event_txt_terjualActionPerformed

    private void bcari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari1ActionPerformed
     close_popup();
        //disable_cari();
        fd_karyawan.fa_karyawan = this;
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
    }//GEN-LAST:event_bcari1ActionPerformed

    private void txt_tes_akhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tes_akhirActionPerformed
    int xqty, xhrg, xjml;
    xhrg = Integer.parseInt(txt_tes_awal.getText());
    xqty = Integer.parseInt(txt_tes_akhir.getText());
    xjml = xqty-xhrg+1;
    String xsubtotal = Integer.toString(xjml);
    txt_total_tes.setText(xsubtotal);
    }//GEN-LAST:event_txt_tes_akhirActionPerformed

    private void txt_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_batalActionPerformed
    int xtotal_tes, xterjual, xbatal, xjml;
    xtotal_tes = Integer.parseInt(txt_total_tes.getText());
    xterjual = Integer.parseInt(txt_terjual.getText());
    xbatal = Integer.parseInt(txt_batal.getText());
    xjml = xtotal_tes+xterjual+xbatal;
    String xsubtotal = Integer.toString(xjml);
    txt_terpakai.setText(xsubtotal);
    }//GEN-LAST:event_txt_batalActionPerformed

    private void txt_total_tesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_total_tesActionPerformed
    
    }//GEN-LAST:event_txt_total_tesActionPerformed

    private void txt_terpakaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_terpakaiMouseClicked
    txt_terpakai.setEditable(false);
    }//GEN-LAST:event_txt_terpakaiMouseClicked

    private void txt_total_tesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_total_tesMouseClicked
    txt_total_tes.setEditable(false);
    }//GEN-LAST:event_txt_total_tesMouseClicked

    private void txt_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyPressed

    }//GEN-LAST:event_txt_cariKeyPressed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        Object[] Baris = {"Id","Tanggal","NIK","Nama Petugas","Shift","Pos","Jenis Tiket",
            "No Awal", "No Akhir","Tiket Terjual","Total Tes","Tiket Batal","Tiket Terpakai"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel_petugas_tiket.setModel(tabmode);
        String sql = "select * from data_tiket where nama like '%"+txt_cari.getText()+"%'";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_tiket");
                String b = hasil.getString("tgl_tiket");
                String c = hasil.getString("nik");
                String d = hasil.getString("nama");
                String e = hasil.getString("shift");
                String f = hasil.getString("pos");

                String g = hasil.getString("jenis");
                String h = hasil.getString("tes_awal");
                String i = hasil.getString("tes_akhir");
                String j = hasil.getString("terjual");
                String k = hasil.getString("total");
                String l = hasil.getString("batal");
                String m = hasil.getString("terpakai");

                String [] data = {a,b,c,d,e,f,g,h,i,j,k,l,m};
                tabmode.addRow(data);
            }
        }catch (Exception e) {
        }
        autoNumber();
    }//GEN-LAST:event_bcariActionPerformed

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
            java.util.logging.Logger.getLogger(form_tiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_tiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_tiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_tiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_tiket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bcari;
    private javax.swing.JButton bcari1;
    private javax.swing.JButton bclear;
    private javax.swing.JButton bclear2;
    private javax.swing.JButton bclear3;
    private javax.swing.JButton bdelete;
    private javax.swing.JButton bedit;
    private javax.swing.JButton bsave;
    private javax.swing.JComboBox<String> cbjenis;
    private javax.swing.JComboBox<String> cbpos;
    private javax.swing.JComboBox<String> cbshift;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_tanggal;
    private javax.swing.JLabel label_waktu;
    private javax.swing.JTable tabel_petugas_tiket;
    private com.toedter.calendar.JDateChooser tglkerja;
    private javax.swing.JTextField txt_batal;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_id_tiket;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nik;
    private javax.swing.JTextField txt_terjual;
    private javax.swing.JTextField txt_terpakai;
    private javax.swing.JTextField txt_tes_akhir;
    private javax.swing.JTextField txt_tes_awal;
    private javax.swing.JTextField txt_total_tes;
    // End of variables declaration//GEN-END:variables
}
