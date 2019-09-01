import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Toolkit;
import java.util.Scanner;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Dimension;

import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JTree;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.AbstractListModel;

import java.awt.ComponentOrientation;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;



public class GuiApplication extends JFrame implements Serializable {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private ArrayList<PatientModel> patientList= new ArrayList<PatientModel>();
	private JComboBox<ProcedureModel> comboBox;
	private File procedureFile = new File("Procedures.txt");
	private File patientFile = new File("patient.ser");
	private ProcedureModel[] procedureArrays;
	private DefaultListModel patientListModel=new DefaultListModel(); 
	private DefaultListModel paymentListModel=new DefaultListModel();
	private JList list = new JList(patientListModel);
	private DefaultListModel addProcedureListModel=new DefaultListModel();
	private DefaultListModel reportList=new DefaultListModel();
	private DefaultListModel procedureListModel=new DefaultListModel();
	private DefaultListModel tempPatientListModel =new DefaultListModel();
	JCheckBox chckbxNewCheckBox;
	private JList list_1;
	private JList list_3;
	private JList list_7;
	private JList list_8;
	private JList list_9;
	private JList list_5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiApplication frame = new GuiApplication();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public GuiApplication( ) throws IOException {
	       
	        
	   
		readPatientInputFile();
		procedureListModel.clear();	
		
		
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setFont(new Font("Tekton Pro", Font.PLAIN, 11));
		setTitle("Dentist Surgery");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit frameSize = Toolkit.getDefaultToolkit();
		int hSize = ((int) frameSize.getScreenSize().getWidth());
		int vSize = ((int) frameSize.getScreenSize().getHeight());
		setSize(1378,768);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1352, 729);
		contentPane.add(tabbedPane);
		
		//Start of patient Panel
		
		
		JPanel patientPanel = new JPanel();
		patientPanel.setToolTipText("");
		tabbedPane.addTab("Patient", null, patientPanel, null);
		patientPanel.setLayout(null);
		
		
		
		list.setName("");
		list.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list.setBackground(new Color(255, 255, 255));
		list.setBounds(333, 115, 681, 273);
		patientPanel.add(list);
		
		
		
		JButton btnNewButton = new JButton("Display Patients");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		}
				);
		
		
		btnNewButton.setBounds(1027, 233, 160, 66);
		patientPanel.add(btnNewButton);
		
		JLabel lblPatientName = new JLabel("Patient ID");
		lblPatientName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPatientName.setBounds(146, 589, 152, 34);
		patientPanel.add(lblPatientName);
		
	    textField_1 = new JTextField();
		textField_1.setBounds(229, 589, 172, 33);
		patientPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPatientName_1 = new JLabel("Patient Name");
		lblPatientName_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPatientName_1.setBounds(435, 589, 88, 34);
		patientPanel.add(lblPatientName_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(533, 591, 172, 34);
		patientPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPatientAddress = new JLabel("Patient Address");
		lblPatientAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPatientAddress.setBounds(746, 589, 108, 34);
		patientPanel.add(lblPatientAddress);
		
		textField_3 = new JTextField();
		textField_3.setBounds(865, 591, 172, 34);
		patientPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.setBounds(1098, 591, 172, 32);
		
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				savePatientData();
				writePatientList();
			}
		});
		
		patientPanel.add(btnAddPatient);
		JButton btnNewButton_1 = new JButton("Save");
		
		
		
		btnNewButton_1.setBounds(1098, 645, 172, 33);
		patientPanel.add(btnNewButton_1);
		
		//end of Patient Panel
		
		
		
		//Procedure Panel
		
		JPanel procedurePanel = new JPanel();
		procedurePanel.setName("Procedures");
		tabbedPane.addTab("Procedures", null, procedurePanel, null);
		procedurePanel.setLayout(null);
		
		list_1 = new JList(patientListModel);
		list_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list_1.setBounds(40, 97, 400, 300);
		procedurePanel.add(list_1);
		
		 
		JList<?> list_2 = new JList(procedureListModel);
		list_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_2.setBounds(877, 97, 400, 300);
		procedurePanel.add(list_2);
		
		
		comboBox= new JComboBox <ProcedureModel>();
		addComboBox();
		comboBox.setName("");
		comboBox.setBounds(575, 241, 173, 33);
		procedurePanel.add(comboBox);
		
		//displays Procedures
		JButton btnNewButton_2 = new JButton("Display Procedures");
		btnNewButton_2.setBounds(575, 175, 173, 33);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			procedureListModel.clear();
			displayProcedures();
			
			
		}});
		procedurePanel.add(btnNewButton_2);
		
		
		JButton btnNewButton_3 = new JButton("Add Procedure");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addProcedure();
			}
		});
		btnNewButton_3.setBounds(575, 310, 173, 33);
		procedurePanel.add(btnNewButton_3);
		
		JLabel lblChooseAPatient = new JLabel("Choose A Patient Below");
		lblChooseAPatient.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblChooseAPatient.setBounds(43, 53, 184, 33);
		procedurePanel.add(lblChooseAPatient);
		
		JLabel lblAfterSelectingPatient = new JLabel("After Selecting Patient, Select Display Procedures");
		lblAfterSelectingPatient.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAfterSelectingPatient.setBounds(877, 59, 367, 21);
		procedurePanel.add(lblAfterSelectingPatient);
		
		
		JButton btnNewButton_4 = new JButton("Save");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writePatientList();
			}});
		
		
		btnNewButton_4.setBounds(1098, 646, 173, 33);
		procedurePanel.add(btnNewButton_4);
		
		
		
		JPanel paymentPanel = new JPanel();
		tabbedPane.addTab("Payment", null, paymentPanel, null);
		paymentPanel.setLayout(null);
		
		
		
		list_3 = new JList(patientListModel);
		list_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_3.setBounds(40, 97, 400, 300);
		paymentPanel.add(list_3);
		
		
		JButton btnNewButton_5 = new JButton("Display Payments");
		btnNewButton_5.setBounds(604, 103, 173, 33);
		
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayPayment();
			}
		});
		paymentPanel.add(btnNewButton_5);
		
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDate.setBounds(553, 164, 77, 39);
		paymentPanel.add(lblDate);
		
		
		textField_4 = new JTextField();
		textField_4.setBounds(604, 167, 173, 39);
		paymentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		
		JLabel lblPaymentAmount = new JLabel("Payment Amount");
		lblPaymentAmount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPaymentAmount.setBounds(467, 241, 127, 33);
		paymentPanel.add(lblPaymentAmount);
		
		
		textField_5 = new JTextField();
		textField_5.setBounds(604, 241, 173, 33);
		paymentPanel.add(textField_5);
		textField_5.setColumns(10);
		
		
		JButton btnAddPayment = new JButton("Add Payment");
		btnAddPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savePaymentData();
			}
		});
		btnAddPayment.setBounds(604, 303, 173, 33);
		paymentPanel.add(btnAddPayment);
		
		
		JButton btnNewButton_6 = new JButton("Save");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writePatientList();
			}
		});
		btnNewButton_6.setBounds(1098, 646, 173, 33);
		paymentPanel.add(btnNewButton_6);
		
		JList list_4 = new JList(paymentListModel);
		list_4.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_4.setBounds(925, 97, 400, 300);
		paymentPanel.add(list_4);
		
		chckbxNewCheckBox = new JCheckBox("Use Automatic Date");
		chckbxNewCheckBox.setBounds(604, 210, 173, 23);
		paymentPanel.add(chckbxNewCheckBox);
		
		JPanel reports = new JPanel();
		tabbedPane.addTab("Reports", null, reports, null);
		reports.setLayout(null);
		
		list_7 = new JList(patientListModel);
		list_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list_7.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_7.setBounds(40, 97, 400, 300);
		reports.add(list_7);
		
		list_8 = new JList(procedureListModel);
		list_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list_8.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_8.setBounds(488, 97, 400, 300);
		reports.add(list_8);
		
		list_9= new JList(paymentListModel);
		list_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list_9.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_9.setBounds(937, 97, 400, 300);
		reports.add(list_9);
		
		JLabel lblSelectPatientTo = new JLabel("Select patient to View Report");
		lblSelectPatientTo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSelectPatientTo.setBounds(102, 35, 285, 21);
		reports.add(lblSelectPatientTo);
		
		JLabel lblProcedures = new JLabel("Procedures");
		lblProcedures.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblProcedures.setBounds(625, 72, 139, 14);
		reports.add(lblProcedures);
		
		JLabel lblPayments = new JLabel("Payments");
		lblPayments.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPayments.setBounds(1096, 68, 115, 23);
		reports.add(lblPayments);
		
		JList list_10 = new JList(reportList);
		list_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list_10.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_10.setBounds(40, 463, 400, 153);
		reports.add(list_10);
		
		JLabel lblGenerateListOf = new JLabel("Generate List of Payments owed");
		lblGenerateListOf.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblGenerateListOf.setBounds(40, 438, 390, 14);
		reports.add(lblGenerateListOf);
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.setBounds(40, 640, 400, 23);
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				generateReport();
			}
		});
		reports.add(btnGenerateReport);
		
		paymentListModel.clear();
		JButton btnNewButton_9 = new JButton("Show Patient Data");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				displayAllData();
			}
		});
		btnNewButton_9.setBounds(102, 67, 216, 23);
		reports.add(btnNewButton_9);
		
		JPanel adminPanel = new JPanel();
		tabbedPane.addTab("Admin", null, adminPanel, null);
		adminPanel.setLayout(null);
		
		list_5 = new JList(patientListModel);
		list_5.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_5.setBounds(39, 97, 400, 300);
		adminPanel.add(list_5);
		
		JButton btnNewButton_7 = new JButton("Display Patients");
		btnNewButton_7.setBounds(39, 404, 171, 33);
		adminPanel.add(btnNewButton_7);
		
		JButton btnRemovePatient = new JButton("Remove Patient");
		btnRemovePatient.setBounds(268, 404, 171, 33);
		btnRemovePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				removePatient();
			}
		});
		adminPanel.add(btnRemovePatient);
		
		
		addAdminProcedures();
		JList list_6 = new JList(addProcedureListModel);
		list_6.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_6.setBounds(925, 97, 400, 300);
		adminPanel.add(list_6);
		
		JButton btnDisplayProcedures = new JButton("Display Procedures");
		btnDisplayProcedures.setBounds(925, 408, 171, 33);
		adminPanel.add(btnDisplayProcedures);
		
		JButton button = new JButton("Remove Procedure");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(1154, 408, 171, 33);
		adminPanel.add(button);
		
		JLabel lblProcedureName = new JLabel("Procedure Name");
		lblProcedureName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblProcedureName.setBounds(925, 483, 131, 14);
		adminPanel.add(lblProcedureName);
		
		textField_6 = new JTextField();
		textField_6.setBounds(1066, 477, 259, 33);
		adminPanel.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblProcedureCost = new JLabel("Procedure Cost \u00A3");
		lblProcedureCost.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblProcedureCost.setBounds(925, 524, 134, 21);
		adminPanel.add(lblProcedureCost);
		
		textField_7 = new JTextField();
		textField_7.setBounds(1066, 521, 259, 33);
		adminPanel.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnNewButton_8 = new JButton("Add Procedure");
		
		
		
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				String procedureName= textField_6.getText();
				String procedureCost=textField_7.getText();
				double AProcedureCost=Double.parseDouble(procedureCost);
				
				ProcedureModel procedure = new ProcedureModel(procedureName,AProcedureCost);
				addProcedureListModel.addElement(procedure.getProcedureName()+"      "+procedure.getProcedureCost());
				
				try {
				    FileOutputStream outputStream=new FileOutputStream("patient.ser");
				    ObjectOutputStream objectOutputStream= new ObjectOutputStream(outputStream);
				    objectOutputStream.writeObject(patientList);
				} catch (FileNotFoundException e5) {
				    e5.printStackTrace();
				} catch (IOException e4) {
				    e4.printStackTrace();
				}
				
				
				

			}
		
		});
		btnNewButton_8.setBounds(1066, 565, 259, 33);
		adminPanel.add(btnNewButton_8);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(1112, 609, 172, 32);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				writePatientList();
			}
		});
		adminPanel.add(btnSave);
		
		JList list_11 = new JList();
		list_11.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_11.setBounds(478, 97, 400, 300);
		adminPanel.add(list_11);
		
		JButton btnDisplayProcedures_1 = new JButton("Display Procedures");
		btnDisplayProcedures_1.setBounds(488, 404, 171, 33);
		adminPanel.add(btnDisplayProcedures_1);
		
		JButton btnRemoveProcedure = new JButton("Remove Procedure");
		btnRemoveProcedure.setBounds(707, 404, 171, 33);
		adminPanel.add(btnRemoveProcedure);
	}
	
	
	public void readPatientInputFile()
	{
		
		try {
		    FileInputStream inputStream=new FileInputStream(patientFile);
		    ObjectInputStream objectInputStream =new ObjectInputStream(inputStream);
		    ArrayList<PatientModel> patientListRead=(ArrayList<PatientModel>) objectInputStream.readObject();
		    ArrayList<InvoiceModel> invoiceArrayList = new ArrayList<InvoiceModel>();
		    ArrayList<ProcedureModel> procedureArrayList = new ArrayList<ProcedureModel>();
		    objectInputStream.close();
		    patientList=patientListRead;
		    PatientModel temp=new PatientModel();
		    
		    for(int i=0; i < patientListRead.size();i++)
		    {
		    	
		    	PatientModel fill = new PatientModel();
		    	fill=patientList.get(i);
		    	System.out.println(fill.toString());
		    	patientListModel.addElement(fill);
		    	
		    	temp=patientList.get(i);
		    	invoiceArrayList=temp.getInvoiceList();
		    	
		    	
		    	for(int x=0;x<invoiceArrayList.size();x++)
		    	{
		    		procedureArrayList=invoiceArrayList.get(x).getProcedureList();
		    		
		    		for(int j=0;j<procedureArrayList.size();j++)
					{
						ProcedureModel copy = new ProcedureModel();
						copy=procedureArrayList.get(j);
						procedureListModel.addElement(copy.toAString());	
					}
		    	
		    	}
		    }
   
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}	
	}
	
	public void readProcedureInputFile()
	{
		try {
		    FileInputStream inputStream=new FileInputStream(patientFile);
		    ObjectInputStream objectInputStream =new ObjectInputStream(inputStream);
		    ArrayList<PatientModel> patientListRead=(ArrayList<PatientModel>) objectInputStream.readObject();
		    ArrayList<InvoiceModel> invoiceArrayList = new ArrayList<InvoiceModel>();
		    ArrayList<ProcedureModel> procedureArrayList = new ArrayList<ProcedureModel>();
		    objectInputStream.close();
		    patientList=patientListRead;
		    PatientModel temp=new PatientModel();
		    
		    for(int i=0; i < patientListRead.size();i++)
		    {
		    	
		    	PatientModel fill = new PatientModel();
		    	fill=patientList.get(i);
		    	System.out.println(fill.toString());
		    	patientListModel.addElement(fill);
		    	
		    	temp=patientList.get(i);
		    	invoiceArrayList=temp.getInvoiceList();
		    	
		    	
		    	for(int x=0;x<invoiceArrayList.size();x++)
		    	{
		    		procedureArrayList=invoiceArrayList.get(x).getProcedureList();
		    		
		    		for(int j=0;j<procedureArrayList.size();j++)
					{
						ProcedureModel copy = new ProcedureModel();
						copy=procedureArrayList.get(j);
						procedureListModel.addElement(copy.toAString());	
					}
		    	
		    	}
		    }
   
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}	
	}
	
	public void readPaymentInputFile()
	{
		try {
		    FileInputStream inputStream=new FileInputStream(patientFile);
		    ObjectInputStream objectInputStream =new ObjectInputStream(inputStream);
		    ArrayList<PatientModel> patientListRead=(ArrayList<PatientModel>) objectInputStream.readObject();
		    ArrayList<InvoiceModel> invoiceArrayList = new ArrayList<InvoiceModel>();
		    ArrayList<PaymentModel> paymentArrayList = new ArrayList<PaymentModel>();
		    objectInputStream.close();
		    patientList=patientListRead;
		    PatientModel temp=new PatientModel();
		    
		    for(int i=0; i < patientListRead.size();i++)
		    {
		    	
		    	PatientModel fill = new PatientModel();
		    	fill=patientList.get(i);
		    	
		    	
		    	temp=patientList.get(i);
		    	invoiceArrayList=temp.getInvoiceList();
		    	
		    	
		    	for(int x=0;x<invoiceArrayList.size();x++)
		    	{
		    		paymentArrayList=invoiceArrayList.get(x).getPaymentList();
		    		
		    		for(int j=0;j<paymentArrayList.size();j++)
					{
						PaymentModel copy = new PaymentModel();
						copy=paymentArrayList.get(j);
						procedureListModel.addElement(copy.toString());	
					}
		    		
		    	}
		    }
   
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}	
		
		
		
		
		
		
	}
	
	public void savePatientData()
{
	String Pname,address,ID;
	int number;
	
	Pname=textField_2.getText();
	System.out.println(Pname);
	address=textField_3.getText();
	ID=textField_1.getText();
	number=Integer.parseInt(ID);
	PatientModel name= new PatientModel(Pname,address,number);
	patientList.add(name);
	patientListModel.addElement(name);
	
	
	
}
	
	public void savePaymentData()
	{
		String sDate,pay;
		double payment;
		boolean valid=chckbxNewCheckBox.isSelected();
		//
		
		
		pay=textField_5.getText();
		
		
		if(valid==true)
		{
			Date date=new Date();
			System.out.println(date);
			SimpleDateFormat format = new SimpleDateFormat( "dd MM yyyy");
		    sDate=format.format(date);
			
		}
		
		else{
			
			
			sDate=textField_4.getText();
		}
		
	        
	        
		payment=Double.parseDouble(pay);
		PaymentModel temp = new PaymentModel(sDate,payment);
		Object sel = list_3.getSelectedValue();
		PatientModel selectedPatient = (PatientModel)sel;
		ArrayList<InvoiceModel> invoiceArrayList = new ArrayList<InvoiceModel>();
		ArrayList<PaymentModel> paymentArrayList = new ArrayList<PaymentModel>();
		InvoiceModel invoice;
		invoiceArrayList=selectedPatient.getInvoiceList();
		invoice=invoiceArrayList.get(0);
		invoiceArrayList.get(0).addPayment(temp);
		boolean check= invoice.isPaid();
		System.out.println(check);
		displayPayment();
		
	
		
		
	}
	
	public void writePatientList()
	{
	try {
	    FileOutputStream outputStream=new FileOutputStream(patientFile);
	    System.out.println(patientFile);
	    ObjectOutputStream objectOutputStream= new ObjectOutputStream(outputStream);
	    objectOutputStream.writeObject(patientList);
	    objectOutputStream.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

}
 	
	public void displayPatients()
	{
		for(int i=0; i < patientList.size();i++)
	    {
	    	
	    	PatientModel fill = new PatientModel();
	    	fill=patientList.get(i);
	    	System.out.println(fill.toString());
	    	patientListModel.addElement(fill);
	    }
	}
		
	
	public void displayProcedures()
	{

		double owed=0,paid=0,balance=0;;
		procedureListModel.clear();
		Object sel = list_1.getSelectedValue();
		PatientModel selectedPatient = (PatientModel)sel;
		ArrayList<InvoiceModel> invoiceArrayList = new ArrayList<InvoiceModel>();
		ArrayList<ProcedureModel> procedureArrayList = new ArrayList<ProcedureModel>();
		InvoiceModel temp;
		invoiceArrayList=selectedPatient.getInvoiceList();
		for(int i=0;i<invoiceArrayList.size();i++)
		{
		procedureArrayList=invoiceArrayList.get(i).getProcedureList();
		temp=invoiceArrayList.get(i);
		owed=owed+temp.getTotalAmountOwed();
		paid=paid+temp.getTotalPaid();
		balance=balance+temp.getBalance();
		System.out.println(invoiceArrayList.get(i).getDate());
		for(int j=0;j<procedureArrayList.size();j++)
		{
			ProcedureModel copy = new ProcedureModel();
			copy=procedureArrayList.get(j);
			procedureListModel.addElement(copy.toAString());
			temp.getInvoiceNumber();
			
		}
		
	  }	
		procedureListModel.addElement("Total Cost:£"+owed+" Paid:£"+paid+ " Owe:£"+balance);
	}
	
	public void displayPayment()
	{

		paymentListModel.clear();
		Object sel = list_3.getSelectedValue();
		PatientModel selectedPatient = (PatientModel)sel;
		ArrayList<InvoiceModel> invoiceArrayList = new ArrayList<InvoiceModel>();
		ArrayList<PaymentModel> paymentArrayList = new ArrayList<PaymentModel>();
		
		invoiceArrayList=selectedPatient.getInvoiceList();
		for(int i=0;i<invoiceArrayList.size();i++)
		{
		paymentArrayList=invoiceArrayList.get(i).getPaymentList();
		
		for(int j=0;j<paymentArrayList.size();j++)
		{
			PaymentModel copy = new PaymentModel();
			copy=paymentArrayList.get(j);
			paymentListModel.addElement(copy.toString());	
		}
	  }	
	}
	
	public void displayAllData()
	{
		procedureListModel.clear();
		Object sel = list_7.getSelectedValue();
		PatientModel selectedPatient = (PatientModel)sel;
		ArrayList<InvoiceModel> invoiceArrayList = new ArrayList<InvoiceModel>();
		ArrayList<ProcedureModel> procedureArrayList = new ArrayList<ProcedureModel>();
		
		invoiceArrayList=selectedPatient.getInvoiceList();
		for(int i=0;i<invoiceArrayList.size();i++)
		{
		procedureArrayList=invoiceArrayList.get(i).getProcedureList();
		
		for(int j=0;j<procedureArrayList.size();j++)
		{
			ProcedureModel copy = new ProcedureModel();
			PaymentModel temp=new PaymentModel();
			copy=procedureArrayList.get(j);
			procedureListModel.addElement(copy.toAString());	
		}
	  }	
		
		paymentListModel.clear();
		ArrayList<InvoiceModel> invoiceArrayList2 = new ArrayList<InvoiceModel>();
		ArrayList<PaymentModel> paymentArrayList = new ArrayList<PaymentModel>();
		
		invoiceArrayList=selectedPatient.getInvoiceList();
		for(int i=0;i<invoiceArrayList.size();i++)
		{
		paymentArrayList=invoiceArrayList.get(i).getPaymentList();
		
		for(int j=0;j<paymentArrayList.size();j++)
		{
			PaymentModel copy = new PaymentModel();
			copy=paymentArrayList.get(j);
			paymentListModel.addElement(copy.toString());	
		}
	  }
		
		
		
		
	}
	
	public void addProcedure()
	{
		
		int sel = list_1.getSelectedIndex();
		boolean exist=false;
		Object cast = comboBox.getSelectedItem();
		ProcedureModel castAfter = (ProcedureModel)cast;
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat( "dd MM yyyy");
		String sDate=format.format(d);
		
		PatientModel p= patientList.get(sel);
		ArrayList<InvoiceModel> invoiceArrayList = new ArrayList<InvoiceModel>();
		invoiceArrayList=p.getInvoiceList();
		InvoiceModel invoice =new InvoiceModel(sDate);
		invoice.addProcedure(castAfter);
		p.addInvoice(invoice);		 
		procedureListModel.addElement(castAfter+ " added");
		System.out.println("Invoice ");
	   	System.out.println(invoice.getDate());
		procedureListModel.clear();
		procedureListModel.addElement(castAfter+ " added");
		}

	
	public void removePatient()
	{
		int patientNo;
		Object obj = list_5.getSelectedValue();
		PatientModel temp = (PatientModel)obj;
		patientNo=temp.getPatientNo();
		
		for(int i=0;i<patientList.size();i++)
		{
			if(patientNo==patientList.get(i).getPatientNo())
			{
				patientList.remove(i);
				
			}	
		}
		
		patientListModel.clear();
		
		displayPatients();
	
	}
	
	public void generateReport()
	{
			reportList.clear();
		    Date date = new Date();
		    SimpleDateFormat format = new SimpleDateFormat( "dd MM yyyy");
		    String sDate=format.format(date);
		    String tDate="";
		    String procedures="";
	    	
	    	ArrayList<InvoiceModel> invoiceList=new ArrayList<InvoiceModel>();
	    	ArrayList<ProcedureModel> procList=new ArrayList<ProcedureModel>();
	    	ArrayList<PaymentModel> payList=new ArrayList<PaymentModel>();
	    	
	    	InvoiceModel invoice;
	    	ProcedureModel procedure;
	    	PaymentModel payment;
	    	PatientModel patient;
	    	double owe=0;
	    	double paid=0;
	    	double cost=0;
	    	
	    	
	    	for(int a=0;a<patientList.size();a++)
	    	{
	    	patient=patientList.get(a);	
	    	invoiceList=patient.getInvoiceList();
	    	
	    	
	    	for(int i=0;i<invoiceList.size();i++)
	    	{
	    		invoice=invoiceList.get(i);
	    		procList=invoice.getProcedureList();
	    		payList=invoice.getPaymentList();
	    		
	    		for(int x=0;x<procList.size();x++)
	    		{
	    			procedure=procList.get(x);
	    			cost=cost+procedure.getProcedureCost();
	    		}
	    		
	    		for(int y=0;y<payList.size();y++)
	    		{
	    			payment=payList.get(y);
	    			paid=paid+payment.getPaymentAmount();
	    			
	    		}	
	    	}
	    	
	    	if(cost>paid)
    		{
    			
    			reportList.addElement("Name: "+ patient.getName()+" Address: "+patient.getAddress());
    			reportList.addElement(patient.getInvoiceList().toString());
    			
    		}
	    	}
	}
	
	
	
	public void addComboBox()
	{
		ProcedureModel [] procedureA = new ProcedureModel[100];
			String pName;
			double pCost;
			int checker=0;
	
			try {
				System.out.println(procedureFile);
				Scanner sc = new Scanner(procedureFile);
				
					while(sc.hasNextLine()){
					pName=sc.nextLine();
					pCost=Double.parseDouble(sc.nextLine());
					ProcedureModel example = new ProcedureModel(pName,pCost);
					comboBox.addItem(example);
					procedureA[checker]=example;
					checker++;
					}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
		
		}
	}
	
	public void addAdminProcedures()
	{
		Scanner sc = null;
		try {
			sc = new Scanner(procedureFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(sc.hasNextLine())
		{
		String peName;
		double pCost;
		while(sc.hasNextLine()){
		peName=sc.nextLine();
		pCost=Double.parseDouble(sc.nextLine());
		ProcedureModel pName = new ProcedureModel(peName,pCost);
		addProcedureListModel.addElement(pName.getProcedureName()+"         "+pName.getProcedureCost());
		}
	}
	}
	}

