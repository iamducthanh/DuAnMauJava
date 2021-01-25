package com.edusys.form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.edusys.dao.impl.AbstractDao;
import com.edusys.dao.impl.NguoiHocDao;
import com.edusys.helper.FormHelper;
import com.edusys.helper.Validate;
import com.edusys.model.NguoiHoc;

@SuppressWarnings("serial")
public class QuanLyNguoiHoc extends JFrame {

	private JPanel contentPane;
	private JTextField textMaNH;
	private JTextField textHoTen;
	private JTextField textDT;
	private JTextField textEmail;
	private JTextField textNgaySinh;
	DefaultTableModel model = new DefaultTableModel();
	private JTextField textField;
	private JTable table;
	JButton btnSua = new JButton("Sửa");
	JButton btnMoi = new JButton("Mới");
	JButton btnXoa = new JButton("Xóa");
	JButton btnThem = new JButton("Thêm");

	JButton btnHuy = new JButton("Hủy");
	ArrayList<NguoiHoc> listNguoiHocs = new ArrayList<NguoiHoc>();
	ArrayList<JTextField> list = new ArrayList<JTextField>();
	SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
	int current = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyNguoiHoc frame = new QuanLyNguoiHoc();
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
	JRadioButton rdbtnNam = new JRadioButton("Nam");
	JRadioButton rdbtnNu = new JRadioButton("Nữ");
	JTextArea textArea = new JTextArea();

	public QuanLyNguoiHoc() {
		setTitle("Quản lý người học");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 637, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblQunLChuyn = new JLabel("QUẢN LÝ NGƯỜI HỌC");
		lblQunLChuyn.setForeground(Color.BLUE);
		lblQunLChuyn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQunLChuyn.setBounds(10, 0, 257, 34);
		contentPane.add(lblQunLChuyn);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 29, 603, 458);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Danh sÃ¡ch", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("CHUYÊN ĐỀ");
		lblNewLabel.setBounds(10, 11, 116, 14);
		panel.add(lblNewLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(10, 25, 578, 42);
		panel.add(panel_3);
		panel_3.setLayout(null);

		textField = new JTextField(" Nhập mã người học cần tìm...!");
		textField.setBounds(10, 11, 459, 23);
		panel_3.add(textField);
		textField.setColumns(10);

		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals(" Nhập mã người học cần tìm...!")) {
					textField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().equals("")) {
					textField.setText(" Nhập mã người học cần tìm...!");
				}
			}
		});

		JButton btnTim = new JButton("Tìm");
		btnTim.setBounds(479, 10, 89, 23);
		panel_3.add(btnTim);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 578, 325);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		model.addColumn("MÃ NH");
		model.addColumn("HỌ VÀ TÊN");
		model.addColumn("GIỚI TÍNH");
		model.addColumn("NGÀY SINH");
		model.addColumn("SỐ ĐIỆN THOẠI");
		model.addColumn("EMAIL");
		model.addColumn("MÃ NV");
		model.addColumn("NGÀY ĐKÍ");
		table.setModel(model);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Cập nhật", null, panel_1, null);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 0, 598, 426);
		panel_1.add(panel_2);

		JLabel lblNewLabel_2 = new JLabel("MÃ£ ngÆ°á»�ihá»�c");
		lblNewLabel_2.setBounds(10, 11, 227, 14);
		panel_2.add(lblNewLabel_2);

		textMaNH = new JTextField();
		textMaNH.setColumns(10);
		textMaNH.setBounds(10, 36, 578, 26);
		panel_2.add(textMaNH);

		JLabel lblNewLabel_1_2 = new JLabel("Họ và tên");
		lblNewLabel_1_2.setBounds(10, 73, 227, 14);
		panel_2.add(lblNewLabel_1_2);

		textHoTen = new JTextField();
		textHoTen.setColumns(10);
		textHoTen.setBounds(10, 98, 578, 26);
		panel_2.add(textHoTen);

		JLabel lblNewLabel_1_1_2 = new JLabel("Giới tính");
		lblNewLabel_1_1_2.setBounds(10, 135, 227, 14);
		panel_2.add(lblNewLabel_1_1_2);

		rdbtnNam.setBounds(10, 156, 62, 23);
		panel_2.add(rdbtnNam);

		rdbtnNu.setBounds(74, 156, 69, 23);
		panel_2.add(rdbtnNu);

		ButtonGroup gr = new ButtonGroup();
		gr.add(rdbtnNu);
		gr.add(rdbtnNam);

		JLabel lblNewLabel_1_1_1_4 = new JLabel("Số điện thoại");
		lblNewLabel_1_1_1_4.setBounds(10, 194, 227, 14);
		panel_2.add(lblNewLabel_1_1_1_4);

		textDT = new JTextField();
		textDT.setColumns(10);
		textDT.setBounds(10, 219, 282, 26);
		panel_2.add(textDT);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(306, 219, 282, 26);
		panel_2.add(textEmail);

		textNgaySinh = new JTextField();
		textNgaySinh.setColumns(10);
		textNgaySinh.setBounds(306, 157, 282, 26);
		panel_2.add(textNgaySinh);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Ngày sinh");
		lblNewLabel_1_1_1_1_1.setBounds(306, 135, 227, 14);
		panel_2.add(lblNewLabel_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Địa chỉ email");
		lblNewLabel_1_1_1_2_1.setBounds(306, 194, 227, 14);
		panel_2.add(lblNewLabel_1_1_1_2_1);

		JLabel lblNewLabel_1_1_1_3_1 = new JLabel("Ghi chú");
		lblNewLabel_1_1_1_3_1.setBounds(10, 256, 227, 14);
		panel_2.add(lblNewLabel_1_1_1_3_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 281, 578, 51);
		panel_2.add(scrollPane_1);

		scrollPane_1.setViewportView(textArea);
		btnThem.setEnabled(false);

		btnThem.setBounds(10, 355, 73, 23);
		panel_2.add(btnThem);
		btnSua.setBounds(93, 355, 73, 23);
		panel_2.add(btnSua);
		btnXoa.setBounds(176, 355, 73, 23);
		panel_2.add(btnXoa);

		btnMoi.setBounds(262, 355, 73, 23);
		panel_2.add(btnMoi);
		
		btnHuy.setBounds(262, 355, 73, 23);
		panel_2.add(btnHuy);
		btnHuy.setVisible(false);
		

		JButton btnLast = new JButton(">|");
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnLast.setBounds(541, 356, 47, 23);
		panel_2.add(btnLast);

		JButton btnNext = new JButton(">>");
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnNext.setBounds(486, 356, 47, 23);
		panel_2.add(btnNext);

		JButton btnPre = new JButton("<<");
		btnPre.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnPre.setBounds(429, 356, 47, 23);
		panel_2.add(btnPre);

		JButton btnFirst = new JButton("|<");
		btnFirst.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnFirst.setBounds(372, 356, 47, 23);
		panel_2.add(btnFirst);
		loadNguoiHoc();
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		btnThem.addActionListener(them);
		btnSua.addActionListener(them);

		btnMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormHelper.clear(list);
				textArea.setText("");
				btnThem.setEnabled(true);
				btnSua.setEnabled(false);
				btnXoa.setEnabled(false);
				btnMoi.setVisible(false);
				btnHuy.setVisible(true);
			}
		});

		list.add(textMaNH);
		list.add(textHoTen);
		list.add(new JTextField());
		list.add(textNgaySinh);
		list.add(textDT);
		list.add(textEmail);
		list.add(new JTextField());
		list.add(new JTextField());
		btnXoa.addActionListener(them);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = table.getSelectedRow();
				FormHelper.display(list, model, r, table);
			}
		});
		rdbtnNam.setSelected(true);
		FormHelper.display(list, model, 0, table);
		btnTim.addActionListener(timKiem);
		btnFirst.addActionListener(first);
		btnLast.addActionListener(last);
		btnNext.addActionListener(next);
		btnPre.addActionListener(previous);
		btnHuy.addActionListener(huy);
	}
	
	ActionListener huy = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			display(current);
			btnThem.setEnabled(false);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
			btnMoi.setVisible(true);
			btnHuy.setVisible(false);
		}
	};

	ActionListener timKiem = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int r = NguoiHocDao.findNguoiHoc(listNguoiHocs, textField.getText());
			if (r == model.getRowCount()) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy người học nào có mã này!");
			} else {
				display(r);
				JOptionPane.showMessageDialog(null, "Đã tìm thấy!");
			}
		}
	};

	ActionListener them = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			StringBuilder error = new StringBuilder();
			if (!Validate.checkNull(textMaNH.getText())) {
				error.append("Không được để trống mã người học!\n");
			} else {
				if (e.getActionCommand().equals("Thêm")) {
					try {
						if (AbstractDao.fill("select * from nguoihoc where manh = ?", textMaNH.getText()).next()) {
							error.append("Mã người học này đã tồn tại!\n");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if (!Validate.checkNull(textHoTen.getText())) {
				error.append("Không được để trống họ tên!\n");
			}
			if (!Validate.checkNull(textDT.getText())) {
				error.append("Không được để trống số điện thoại!\n");
			} else if (!Validate.checkSDT(textDT.getText())) {
				error.append("Nhập sai định dạng số điện thoại!\n");
			}
			if (!Validate.checkNull(textNgaySinh.getText())) {
				error.append("Không được để trống ngày sinh!\n");
			} else if (!Validate.checkNgay(textNgaySinh.getText())) {
				error.append("Nhập sai định dạng ngày sinh (yyyy-mm-dd)!\n");
			}
			if (!Validate.checkNull(textEmail.getText())) {
				error.append("Không được để trống email!\n");
			} else if (!Validate.checkEmail(textEmail.getText())) {
				error.append("Nhập sai định dạng email!\n");
			}

			if (error.toString().isBlank()) {
				String maNH = textMaNH.getText();
				String hoTen = textHoTen.getText();
				String gioiTinh = rdbtnNam.isSelected() ? "1" : "0";
				String ngaySinh = textNgaySinh.getText();
				String dienThoai = textDT.getText();
				String email = textEmail.getText();
				String ghiChu = textArea.getText();
				String ngayDK = fm.format(new Date());
				String maNV = "a";
				if (e.getActionCommand().equals("Thêm")) {
					AbstractDao.executeQuery("insert into nguoihoc values (?,?,?,?,?,?,?,?,?)",
							new Object[] { maNH, hoTen, gioiTinh, ngaySinh, dienThoai, email, ghiChu, ngayDK, maNV });
					JOptionPane.showMessageDialog(null, "Thêm thành công!");
				} else if (e.getActionCommand().equals("Sửa")) {
					AbstractDao.executeQuery(
							"update NguoiHoc set hote = ?, gioitinh = ?, ngaysinh = ?, dienthoai = ?, email = ?, ghichu = ?, ngaydk = ? where MaNH = ?",
							new Object[] { hoTen, gioiTinh, ngaySinh, dienThoai, email, ghiChu, ngayDK,
									textMaNH.getText() });
					JOptionPane.showMessageDialog(null, "Sửa thành công!");
				} else if (e.getActionCommand().equals("Xóa")) {
					int a = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?");
					if (a == 0) {
						JOptionPane.showMessageDialog(null, "Xóa thành công!");
						AbstractDao.executeQuery("delete from nguoihoc where manh = ?", textMaNH.getText());
					}
				}
				btnThem.setEnabled(false);
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);

				btnHuy.setVisible(false);
				btnMoi.setVisible(true);
				loadNguoiHoc();
				FormHelper.display(list, model, 0, table);
			} else {
				JOptionPane.showMessageDialog(null, error.toString(), "Lỗi", JOptionPane.WARNING_MESSAGE);
			}
		}
	};

	public void display(int r) {
		FormHelper.display(list, model, r, table);
		textArea.setText(listNguoiHocs.get(r).getGhiChu());
		if (listNguoiHocs.get(r).getGioiTinh().equals("1")) {
			rdbtnNam.setSelected(true);
		} else {
			rdbtnNu.setSelected(true);
		}
	}

	public void loadNguoiHoc() {
		listNguoiHocs = new NguoiHocDao().fillAll();
		model.setRowCount(0);
		listNguoiHocs.forEach((cd) -> {
			model.addRow(new Object[] { cd.getMaNH(), cd.getHoTen(), cd.getGioiTinh(), cd.getNgaySinh(), cd.getSoDT(),
					cd.getEmail(), cd.getMaNV(), cd.getNgayDK() });
		});

	}

	ActionListener first = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			current = 0;
			display(current);
		}
	};

	ActionListener last = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			current = listNguoiHocs.size() - 1;
			display(current);
		}
	};

	ActionListener next = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			current += 1;
			if (current == listNguoiHocs.size()) {
				current = 0;
			}
			display(current);
		}
	};

	ActionListener previous = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			current -= 1;
			if (current == -1) {
				current = listNguoiHocs.size() - 1;
			}
			display(current);
		}
	};
}