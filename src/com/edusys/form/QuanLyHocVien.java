package com.edusys.form;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
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
public class QuanLyHocVien extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel model1 = new DefaultTableModel();
	private JTextField textField;
	private JTable table_1;
	ArrayList<HocVien> lisHocViens = new ArrayList<HocVien>();
	ArrayList<NguoiHoc> lisNguoiHocs = new ArrayList<NguoiHoc>();
	ArrayList<ChuyenDe> listChuyenDe = new ArrayList<ChuyenDe>();
	ArrayList<KhoaHoc> listKhoaHocs = new ArrayList<KhoaHoc>();
	JComboBox<String> cbbChuyenDe = new JComboBox<String>();
	JComboBox<String> cbbKhoaHoc = new JComboBox<String>();

	JButton btnXoa = new JButton("Xóa khỏi khóa học");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyHocVien frame = new QuanLyHocVien();
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
	public QuanLyHocVien() {
		setTitle("Quản Lý Học Viên");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CHUYÊN ĐỀ");
		lblNewLabel.setBounds(10, 11, 154, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblKhaHc = new JLabel("kHÓA HỌC");
		lblKhaHc.setBounds(352, 11, 154, 14);
		contentPane.add(lblKhaHc);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 25, 324, 41);
		contentPane.add(panel);
		panel.setLayout(null);
		loadcbbChuyenDe();
		cbbChuyenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadKhoaHoc();
				loadHocVien();
			}
		});
		
		cbbChuyenDe.setBounds(10, 11, 304, 22);
		panel.add(cbbChuyenDe);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(352, 25, 324, 41);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		cbbKhoaHoc.setBounds(10, 11, 304, 22);
		panel_1.add(cbbKhoaHoc);
		loadKhoaHoc();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 77, 666, 405);
		contentPane.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Học viên", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 661, 332);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		model.addColumn("TT");
		model.addColumn("MÃ HV");
		model.addColumn("MÃ NH");
		model.addColumn("HỌ TÊN");
		model.addColumn("ĐIỂM");
		table.setModel(model);
		
		JButton btnCapNhat = new JButton("Cập nhật điểm");
		btnCapNhat.setBounds(522, 343, 129, 23);
		panel_2.add(btnCapNhat);
		
		btnXoa.setBounds(363, 343, 149, 23);
		panel_2.add(btnXoa);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Người học", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm");
		lblNewLabel_1.setBounds(10, 5, 78, 14);
		panel_3.add(lblNewLabel_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_4.setBounds(10, 23, 641, 45);
		panel_4.setFocusable(true);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		textField = new JTextField(" Nhập mã người học cần tìm ....!");
		textField.setBounds(10, 11, 522, 23);
		panel_4.add(textField);
		textField.setColumns(10);
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals(" Nhập mã người học cần tìm ....!")) {
					textField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().equals("")) {
					textField.setText(" Nhập mã người học cần tìm ....!");
				}
			}
		});
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setBounds(542, 10, 89, 23);
		panel_4.add(btnTim);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 79, 641, 253);
		panel_3.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		model1.addColumn("MÃ NH");
		model1.addColumn("HỌ VÀ TÊN");
		model1.addColumn("GIỚI TÍNH");
		model1.addColumn("NGÀY SINH");
		model1.addColumn("ĐIỆN THOẠI");
		model1.addColumn("EMAIL");
		table_1.setModel(model1);
		
		JButton btnThem = new JButton("Thêm vào khóa học");
		btnThem.setBounds(480, 343, 171, 23);
		panel_3.add(btnThem);
		loadNguoiHoc();
		loadHocVien();
		displayHocVien(0);
		displayNguoiHoc(0);
		
		btnXoa.addActionListener(xoaHocVien);
		btnThem.addActionListener(themHocVien);
		btnCapNhat.addActionListener(capNhatDiem);
		btnTim.addActionListener(timKiem);
	}
	
	ActionListener timKiem = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int r = NguoiHocDao.findNguoiHoc(lisNguoiHocs, textField.getText());
			if(r == model1.getRowCount()) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy người học nào có mã này!");
			} else {
				displayNguoiHoc(r);
				JOptionPane.showMessageDialog(null, "Đã tìm thấy!");
			}
		}
	};
	
	ActionListener capNhatDiem = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int r = table.getSelectedRow();
			String diem = (String) table.getValueAt(r, 4);
			String maHV = (String) table.getValueAt(r, 1);
			AbstractDao.executeQuery("update hocvien set diem = ? where mahv = ?", new Object[] {diem, maHV});
			loadHocVien();
			displayHocVien(r);
			JOptionPane.showMessageDialog(null, "Cập nhật điểm thành công!");
		}
	};
	
	ActionListener themHocVien = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int[] r = table_1.getSelectedRows();
			for(int i=0;i<r.length;i++) {
				String maNH = (String) model1.getValueAt(i, 0);
				String maKH = (String) cbbKhoaHoc.getSelectedItem();
				ResultSet fillHocVien = AbstractDao.fill("select * from hocvien where manh = ? and makh = ?", maNH, maKH);
				try {
					if(fillHocVien.next()) {
						JOptionPane.showMessageDialog(null, "Người học "+ maNH +" đã có trong khóa học!");
					} else {
						AbstractDao.executeQuery("insert into hocvien(makh, manh,diem) values (?,?,?)", new Object[] {maKH,maNH,"0"});
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			loadHocVien();
			displayHocVien(0);
			JOptionPane.showMessageDialog(null, "Thêm thành công!");
		}
	};
	
	ActionListener xoaHocVien = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int r = table.getSelectedRow();
			String maHV = (String) model.getValueAt(r, 1);
			AbstractDao.executeQuery("delete from hocvien where mahv = ?", maHV);
			loadHocVien();
			displayHocVien(0);
			JOptionPane.showMessageDialog(null, "Xóa thành công!");
		}
	};
	
	public void displayHocVien(int r) {
		try {
			table.setRowSelectionInterval(r, r);
		} catch (Exception e) {
		}
	}
	
	public void displayNguoiHoc(int r) {
		try {
			table_1.setRowSelectionInterval(r, r);
		} catch (Exception e) {
		}
	}
	
	public void loadTable() {
		model.setRowCount(0);
		lisHocViens = new HocVienDao().fillAll();
		lisHocViens.forEach((cd) -> {
			model.addRow(new Object[] {lisHocViens.indexOf(cd), cd.getMaHV(), cd.getMaNH(), cd.getMaKH(), cd.getDiem()});
		});
	}
	
	public void loadcbbChuyenDe() {
		listChuyenDe.removeAll(listChuyenDe);
		listChuyenDe = new ChuyenDeDao().fillAll();
		listChuyenDe.forEach((cd) -> {
			cbbChuyenDe.addItem(cd.getTenCD());
		});
	}
	
	public void loadKhoaHoc() {
		listChuyenDe.removeAll(listChuyenDe);
		listKhoaHocs.removeAll(listKhoaHocs);
		
		listChuyenDe = new ChuyenDeDao().fillByTen((String) cbbChuyenDe.getSelectedItem());
		listKhoaHocs = new KhoaHocDao().fillByChuyenDe(listChuyenDe.get(0).getMaCD());
		cbbKhoaHoc.removeAllItems();
		
		listKhoaHocs.forEach((cd) -> {
			cbbKhoaHoc.addItem(cd.getMaKh());
		});
	}
	
	public void loadNguoiHoc() {
		lisNguoiHocs.removeAll(lisNguoiHocs);
		model1.setRowCount(0);
		
		lisNguoiHocs = new NguoiHocDao().fillAll();
		lisNguoiHocs.forEach((cd) -> {
			model1.addRow(new Object[] {cd.getMaNH(),cd.getHoTen(),cd.getGioiTinh(),cd.getNgaySinh(),cd.getSoDT(),cd.getEmail()});
		});
		
	}
	
	public void loadHocVien() {
		lisHocViens.removeAll(lisHocViens);
		model.setRowCount(0);
		
		lisHocViens = new HocVienDao().fillByKhoaHoc((String)cbbKhoaHoc.getSelectedItem());
		lisHocViens.forEach((cd) -> {
			String hoTen = null;
			for (int i = 0; i < lisNguoiHocs.size(); i++) {
				if(lisNguoiHocs.get(i).getMaNH().equals(cd.getMaNH())) {
					hoTen = lisNguoiHocs.get(i).getHoTen();
					break;
				}
			}
			model.addRow(new Object[] {lisHocViens.indexOf(cd) + 1, cd.getMaHV(), cd.getMaNH(), hoTen ,cd.getDiem()});
		});
	}

}
