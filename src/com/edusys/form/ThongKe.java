package com.edusys.form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.edusys.dao.impl.AbstractDao;
import com.edusys.dao.impl.ChuyenDeDao;
import com.edusys.dao.impl.HocVienDao;
import com.edusys.dao.impl.KhoaHocDao;
import com.edusys.dao.impl.NguoiHocDao;
import com.edusys.model.ChuyenDe;
import com.edusys.model.HocVien;
import com.edusys.model.KhoaHoc;
import com.edusys.model.NguoiHoc;

@SuppressWarnings("serial")
public class ThongKe extends JFrame {

	private JPanel contentPane;
	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel model1 = new DefaultTableModel();
	DefaultTableModel model2 = new DefaultTableModel();
	DefaultTableModel model3 = new DefaultTableModel();
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;

	ArrayList<HocVien> lisHocViens = new ArrayList<HocVien>();
	ArrayList<NguoiHoc> lisNguoiHocs = new ArrayList<NguoiHoc>();
	ArrayList<KhoaHoc> listKhoaHocs = new ArrayList<KhoaHoc>();
	ArrayList<ChuyenDe> listchuyenDes = new ChuyenDeDao().fillAll();
	ResultSet year = new NguoiHocDao().fillYear();

	JComboBox<String> comboBox_1 = new JComboBox<String>();
	JComboBox<String> comboBox = new JComboBox<String>();

	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKe frame = new ThongKe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ThongKe() {
		setTitle("TỔNG HỢP THỐNG KÊ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 702, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTngHpThng = new JLabel("TỔNG HỢP THỐNG KÊ");
		lblTngHpThng.setForeground(Color.BLUE);
		lblTngHpThng.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTngHpThng.setBounds(10, 0, 257, 34);
		contentPane.add(lblTngHpThng);

		tabbedPane.setBounds(0, 33, 688, 439);
		contentPane.add(tabbedPane);

		JPanel BangDiem = new JPanel();
		tabbedPane.addTab("BẢNG ĐIẺM", null, BangDiem, null);
		BangDiem.setLayout(null);

		JLabel lblNewLabel = new JLabel("KHÓA HỌC");
		lblNewLabel.setBounds(10, 11, 85, 14);
		BangDiem.add(lblNewLabel);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadBangDiem();
			}
		});

		comboBox.setBounds(105, 7, 568, 22);
		BangDiem.add(comboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 663, 351);
		BangDiem.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		model.addColumn("MÃ NH");
		model.addColumn("HỌ VÀ TÊN");
		model.addColumn("ĐIỂM");
		model.addColumn("XẾP LOẠI");
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
//		table.getColumnModel().getColumn(2).setPreferredWidth(10);
//		table.getColumnModel().getColumn(3).setPreferredWidth(10);

		JPanel NguoiHoc = new JPanel();
		tabbedPane.addTab("NGƯỜI HỌC", null, NguoiHoc, null);
		NguoiHoc.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 663, 389);
		NguoiHoc.add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		model1.addColumn("NĂM");
		model1.addColumn("SỐ NGƯỜI HỌC");
		model1.addColumn("ĐK SỚM NHẤT");
		model1.addColumn("ĐK MUỘN NHẤT");
		table_1.setModel(model1);

		JPanel DiemChuyenDe = new JPanel();
		tabbedPane.addTab("ĐIỂM CHUYÊN ĐỀ", null, DiemChuyenDe, null);
		DiemChuyenDe.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 663, 389);
		DiemChuyenDe.add(scrollPane_2);

		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		model2.addColumn("CHUYÊN ĐỀ");
		model2.addColumn("SL HV");
		model2.addColumn("ĐIỂM TN");
		model2.addColumn("ĐIỂM CN");
		model2.addColumn("ĐIỂM TB");
		table_2.setModel(model2);

		JPanel DoanhThu = new JPanel();
		tabbedPane.addTab("DOANH THU", null, DoanhThu, null);
		DoanhThu.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("NĂM");
		lblNewLabel_1.setBounds(10, 11, 49, 14);
		DoanhThu.add(lblNewLabel_1);

		comboBox_1.setBounds(48, 7, 625, 22);
		DoanhThu.add(comboBox_1);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 49, 663, 351);
		DoanhThu.add(scrollPane_3);

		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		model3.addColumn("CHUYÊN ĐỀ");
		model3.addColumn("SỐ KH");
		model3.addColumn("SỐ HV");
		model3.addColumn("DOANH THU");
		model3.addColumn("HP TN");
		model3.addColumn("HP CN");
		model3.addColumn("HP TB");
		table_3.setModel(model3);

		loadKhoaHoc();
		loadBangDiem();
		try {
			thongKeNguoiHoc();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		fillYear();
		thongKeDiemChuyenDe();
		table_2.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_3.getColumnModel().getColumn(0).setPreferredWidth(200);
		thongKeDoanhThu();
	}

	public void loadKhoaHoc() {
		listKhoaHocs = new KhoaHocDao().fillAll();
		listKhoaHocs.forEach((cd) -> {
			comboBox.addItem(cd.getMaKh());
		});
	}

	public void loadBangDiem() {
		lisNguoiHocs.removeAll(lisNguoiHocs);
		lisHocViens.removeAll(lisHocViens);
		model.setRowCount(0);

		lisHocViens = new HocVienDao().fillAll();
		lisNguoiHocs = new NguoiHocDao().fillAll();

		lisHocViens.forEach((cd) -> {
			String hoTen = null;
			if (cd.getMaKH().equals((String) comboBox.getSelectedItem())) {
				for (int i = 0; i < lisNguoiHocs.size(); i++) {
					if (lisNguoiHocs.get(i).getMaNH().equals(cd.getMaNH())) {
						hoTen = lisNguoiHocs.get(i).getHoTen();
						model.addRow(
								new Object[] { lisNguoiHocs.get(i).getMaNH(), hoTen, cd.getDiem(), cd.getXepLoai() });
					}
				}
			}
		});
	}

	public void thongKeNguoiHoc() throws SQLException {
		model1.setRowCount(0);
		while (year.next()) {
			lisNguoiHocs.removeAll(lisNguoiHocs);
			lisNguoiHocs = new NguoiHocDao().fillByYear(year.getString(1));
			ArrayList<String> ngayDK = new NguoiHocDao().fillMinMaxYear(year.getString(1));

			model1.addRow(new Object[] { year.getString(1), lisNguoiHocs.size(), ngayDK.get(0).toString(),
					ngayDK.get(1).toString() });
		}
	}

	public void thongKeDiemChuyenDe() {
		model2.setRowCount(0);

		listchuyenDes.forEach((cd) -> {
			String slHocVien = "";
			double diemTN = 0;
			double diemCN = 0;
			ResultSet countHV = AbstractDao.fill(
					"select count(mahv) from hocvien where makh in (select makh from khoahoc where macd = ?)",
					cd.getMaCD());
			ResultSet fillDiem = AbstractDao.fill(
					"select MIN(diem), max(diem) from hocvien where makh in (select makh from khoahoc where macd = ?)",
					cd.getMaCD());
			try {
				while (countHV.next()) {
					slHocVien = countHV.getString(1);
				}
				while (fillDiem.next()) {
					try {
						diemTN = Double.parseDouble(fillDiem.getString(1));
						diemCN = Double.parseDouble(fillDiem.getString(2));
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model2.addRow(new Object[] { cd.getTenCD(), slHocVien, diemTN, diemCN, (diemTN + diemCN) / 2 });
		});
	}

	public void fillYear() {
		ResultSet year = AbstractDao.fill("select distinct YEAR(ngayKG) from khoahoc");
		try {
			while (year.next()) {
				comboBox_1.addItem(year.getString(1));
			}
		} catch (SQLException e) {
		}
	}

	public void thongKeDoanhThu() {
		model3.setRowCount(0);
		listchuyenDes.forEach((cd) -> {
			String slHocVien = "";
			String slKhoaHoc = "";
			Double minHocPhi = 0.0;
			Double maxHocPhi = 0.0;
			Double doanhThu = 0.0;
			Double doanhThu1 = 0.0;
			Double doanhThu2 = 0.0;
			ResultSet hocPhi = AbstractDao.fill("select min(hocphi), max(hocphi) from khoahoc where macd = ? and year(ngayKG) = ?", new Object[] {cd.getMaCD(), comboBox_1.getSelectedItem()});
			ResultSet count = AbstractDao.fill(
					"select count(mahv), (select count(makh) from khoahoc where macd = ? and YEAR(ngayKG) = ?) from hocvien where makh in (select makh from khoahoc where macd = ? and YEAR(ngayKG) = ?)",
					new Object[] { cd.getMaCD(), (String) comboBox_1.getSelectedItem(), cd.getMaCD(),
							(String) comboBox_1.getSelectedItem() });
			try {
				while(hocPhi.next()) {
					minHocPhi = hocPhi.getDouble(1);
					maxHocPhi = hocPhi.getDouble(2);
				}
				while (count.next()) {
					slHocVien = count.getString(1);
					slKhoaHoc = count.getString(2);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(minHocPhi - maxHocPhi == 0) {
				doanhThu = Double.parseDouble(slHocVien) * minHocPhi;
			} else {
				ResultSet slHocVienByMinHocPhi = AbstractDao.fill("select COUNT(mahv) from hocvien join khoahoc on hocvien.makh = khoahoc.makh where macd = ? and YEAR(ngayKG) = ? and hocphi = ?", new Object[] {cd.getMaCD(), comboBox_1.getSelectedItem(), String.valueOf(minHocPhi)});
				ResultSet slHocVienByMaxHocPhi = AbstractDao.fill("select COUNT(mahv) from hocvien join khoahoc on hocvien.makh = khoahoc.makh where macd = ? and YEAR(ngayKG) = ? and hocphi = ?", new Object[] {cd.getMaCD(), comboBox_1.getSelectedItem(), String.valueOf(maxHocPhi)});
				try {
					while(slHocVienByMinHocPhi.next()) {
						doanhThu1 = minHocPhi * Double.parseDouble(slHocVienByMinHocPhi.getString(1));
					}
					while(slHocVienByMaxHocPhi.next()) {
						doanhThu2 = minHocPhi * Double.parseDouble(slHocVienByMaxHocPhi.getString(1));
					}
					doanhThu = doanhThu1 + doanhThu2;
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			model3.addRow(new Object[] { cd.getTenCD(), slKhoaHoc, slHocVien, doanhThu, minHocPhi, maxHocPhi, (minHocPhi + maxHocPhi) /2 });
		});
	}

}
