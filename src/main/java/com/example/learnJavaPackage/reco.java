package com.example.learnJavaPackage;



import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

import java.time.Month;
import java.time.LocalDate; //importing awt packages
   							//importing awt event packages
import javax.swing.*;     //importing swing packages
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;         //importing sql packages
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.FileWriter;


public class reco {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private static String neededMacAddress = "6C-72-20-01-FC-B3";
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";    //defining jdbc driver
	static final String DB_URL = "jdbc:mysql://localhost:3306"     //defining database url
	   		+ "/reco";
	
	static final String USER = "root";                            //specifying username of the database
	static final String PASS = "induction@21";                        //specifying the password of the database
	
	Integer fabricCountGlobal=0;
	String fabricCode1Global="";
	String fabricCode2Global="";
	String fabricCode3Global="";
	String fabricCode4Global="";
	Integer challanVal=0;
	
	public reco() {
		prepareGUI("Hanuman",532,450);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
				
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		reco Reco = new reco();
		Reco.launch();
		
		
	}
	
	private void prepareGUI(String name, int width , int height) {
		
		mainFrame = new JFrame(name);
		mainFrame.setResizable(false);
		mainFrame.setFont(new Font("Centaur", Font.PLAIN, 12));
		mainFrame.setForeground(SystemColor.activeCaption);
		mainFrame.setBackground(SystemColor.controlDkShadow);
		//mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Popnetic\\Dropbox\\YO\\kisspng-hanuman-mace-computer-icons-clip-art-hanuman-5ab8f018bda120.2206426315220695287767.png"));
		mainFrame.setBounds(200, 100, width, height);
		
		mainFrame.setLayout(new GridLayout(3,1));
		
		
		
		
		headerLabel = new JLabel("");
		headerLabel.setForeground(SystemColor.controlDkShadow);
		headerLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		statusLabel = new JLabel("");
		
		headerLabel.setForeground(SystemColor.controlDkShadow);
		statusLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//statusLabel.setSize(350,100);
		
		controlPanel = new JPanel();
		controlPanel.setForeground(new Color(0, 0, 0));
		controlPanel.setBackground(new Color(255, 153, 153));
		controlPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		controlPanel.setLayout(null);
		
		controlPanel.add(headerLabel);
		mainFrame.setContentPane(controlPanel);
		controlPanel.add(statusLabel);
		mainFrame.setVisible(true);
		
	}
	
	private void launch() {
		
		JButton fabricInward = new JButton("Fabric Inward");
		JButton order = new JButton("Order");
		JButton fabricOutward = new JButton("Fabric Outward");
		JButton returnOrder = new JButton("Return");
		JButton payment = new JButton("Payment");
		JButton inward = new JButton("Inward");
		JButton outward = new JButton("Outward");
		JButton challanOutward = new JButton("Challan Outward");
		JButton challanInward = new JButton("Challan Inward");
		
		
		JButton data = new JButton("Data");
		JButton barcode = new JButton("Barcode Bulk");
		JButton barcode2 = new JButton("Barcode Single");
		JButton barcodeAjio = new JButton("Ajio Barcode Bulk");
		JButton barcodeAjioSingle = new JButton("Ajio Barcode Single");
		
		JButton ajioReturn = new JButton("AJIO Return Scan");
		
		fabricInward.setForeground(SystemColor.desktop);
		fabricInward.setBackground(SystemColor.activeCaption);
		fabricInward.setBounds(10, 90,150, 31);
		
		order.setForeground(SystemColor.desktop);
		order.setBackground(SystemColor.activeCaption);
		order.setBounds(10, 140, 94, 31);
		
		inward.setForeground(SystemColor.desktop);
		inward.setBackground(SystemColor.activeCaption);
		inward.setBounds(10, 180, 94, 31);
		
		
		
		barcodeAjio.setForeground(SystemColor.desktop);
		barcodeAjio.setBackground(SystemColor.activeCaption);
		barcodeAjio.setBounds(190, 100, 150, 31);
		
		barcodeAjioSingle.setForeground(SystemColor.desktop);
		barcodeAjioSingle.setBackground(SystemColor.activeCaption);
		barcodeAjioSingle.setBounds(190, 60, 150, 31);
		
		barcode.setForeground(SystemColor.desktop);
		barcode.setBackground(SystemColor.activeCaption);
		barcode.setBounds(190, 140, 150, 31);
		
		barcode2.setForeground(SystemColor.desktop);
		barcode2.setBackground(SystemColor.activeCaption);
		barcode2.setBounds(190, 180, 150, 31);
		
		fabricOutward.setForeground(SystemColor.desktop);
		fabricOutward.setBackground(SystemColor.activeCaption);
		fabricOutward.setBounds(355, 90, 150, 31);
		
		challanInward.setForeground(SystemColor.desktop);
		challanInward.setBackground(SystemColor.activeCaption);
		challanInward.setBounds(10, 220,150, 31);
		
		challanOutward.setForeground(SystemColor.desktop);
		challanOutward.setBackground(SystemColor.activeCaption);
		challanOutward.setBounds(355, 220, 150, 31);
		
		returnOrder.setForeground(SystemColor.desktop);
		returnOrder.setBackground(SystemColor.activeCaption);
		returnOrder.setBounds(410, 140, 94, 31);
		
		outward.setForeground(SystemColor.desktop);
		outward.setBackground(SystemColor.activeCaption);
		outward.setBounds(410, 180, 94, 31);
		
		payment.setForeground(SystemColor.desktop);
		payment.setBackground(SystemColor.activeCaption);
		payment.setBounds(210, 240, 94, 31);
		
		data.setForeground(SystemColor.desktop);
		data.setBackground(SystemColor.activeCaption);
		data.setBounds(210, 300, 94, 31);
		
		ajioReturn.setForeground(SystemColor.desktop);
		ajioReturn.setBackground(SystemColor.activeCaption);
		ajioReturn.setBounds(190, 350, 150, 31);
	
		
		headerLabel.setBounds(80, 11, 400, 31);
		statusLabel.setBounds(100, 300, 312, 31);
		
		barcodeAjio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent AjioorderEvent) {
				mainFrame.dispose();
				barcodeAjioLaunch();
				
			}
		});
	
		
		
		barcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent orderEvent) {
				mainFrame.dispose();
				barcodeLaunch();
				
			}
		});
		
		barcode2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent orderEvent) {
				mainFrame.dispose();
				barcode2Launch();
				
			}
		});
		
		data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent orderEvent) {
				mainFrame.dispose();
				dataLaunch();
				
			}
		});
		
		fabricInward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent orderEvent) {
				mainFrame.dispose();
				fabricInwardLaunch();
				
				
			}
		});
		
		
		fabricOutward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent orderEvent) {
				mainFrame.dispose();
				fabricOutwardLaunch();
				
			}
		});
		
		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent orderEvent) {
				mainFrame.dispose();
				orderLaunch();
				
			}
		});
		
		returnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent returnOrderEvent) {
				
				mainFrame.dispose();
				returnOrderLaunch();
			}
		});
		
		payment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paymentEvent) {
				
				mainFrame.dispose();
				paymentLaunch();
			}
		});
		
		inward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent inwardEvent) {
				
				mainFrame.dispose();
				inwardLaunch();
			}
		});
		
		outward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent outwardEvent) {
				
				mainFrame.dispose();
				outwardLaunch();
			}
		});
		
		challanOutward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent challanOutwardEvent) {
				
				mainFrame.dispose();
				challanOutwardLaunch();
			}
		});
		
		challanInward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent challanOutwardEvent) {
				
				mainFrame.dispose();
				challanInwardLaunch();
			}
		});
		
		ajioReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent challanOutwardEvent) {
				
				mainFrame.dispose();
				ajioReturnLaunch();
			}
		});
		
		headerLabel.setText("Welcome to Popnetic Warehousing System!");
		controlPanel.add(returnOrder);
		controlPanel.add(order);
		controlPanel.add(payment);
		controlPanel.add(data);
		controlPanel.add(barcode);
		controlPanel.add(barcode2);
		controlPanel.add(inward);
		controlPanel.add(outward);
		controlPanel.add(barcodeAjio);
		controlPanel.add(barcodeAjioSingle);
		controlPanel.add(fabricInward);
		controlPanel.add(fabricOutward);
		controlPanel.add(challanInward);
		controlPanel.add(challanOutward);
		controlPanel.add(ajioReturn);
		
		mainFrame.setVisible(true);
	}
	
	
	
	//Challan Inward Launch
	
	private void challanInwardLaunch() {
		prepareGUI("Challan Inward",700,500);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosingInwardLaunch(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		
		
		JLabel challanId = new JLabel("Challan ID");
		challanId.setForeground(new Color(70, 130, 180));
		challanId.setFont(new Font("Times New Roman", Font.BOLD, 15));
		challanId.setBounds(180, 145, 124, 20);
		challanId.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		
		
		JTextField challanIdText= new JTextField();
		challanIdText.setForeground(SystemColor.desktop);
		challanIdText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		challanIdText.setBounds(280, 140, 200, 30);
		challanIdText.setColumns(10);
		
		
		JLabel styleCode = new JLabel("Style Code");
		styleCode.setForeground(new Color(70, 130, 180));
		styleCode.setFont(new Font("Times New Roman", Font.BOLD, 15));
		styleCode.setBounds(10, 200, 124, 20);
		styleCode.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField styleCodeText = new JTextField();
		styleCodeText.setForeground(SystemColor.desktop);
		styleCodeText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		styleCodeText.setBounds(100, 195, 100, 30);
		styleCodeText.setColumns(10);
		
		JLabel size = new JLabel("Size");
		size.setForeground(new Color(70, 130, 180));
		size.setFont(new Font("Times New Roman", Font.BOLD, 15));
		size.setBounds(210, 200, 124, 20);
		size.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField sizeText = new JTextField();
		sizeText.setForeground(SystemColor.desktop);
		sizeText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sizeText.setBounds(250, 195, 100, 30);
		sizeText.setColumns(10);
		
		JLabel qty = new JLabel("Quantity");
		qty.setForeground(new Color(70, 130, 180));
		qty.setFont(new Font("Times New Roman", Font.BOLD, 15));
		qty.setBounds(370, 200, 124, 20);
		qty.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField qtyText = new JTextField();
		qtyText.setForeground(SystemColor.desktop);
		qtyText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		qtyText.setBounds(450, 195, 100, 30);
		qtyText.setColumns(10);
		
		
		JButton add = new JButton("Inward +");
		
		add.setForeground(SystemColor.desktop);
		add.setBackground(SystemColor.activeCaption);
		add.setBounds(560, 195, 100, 30);
		
		JButton back = new JButton("Back");
		back.setBounds(300, 300, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent barcodeLaunchEvent) {
				mainFrame.dispose();
				prepareGUI("Hanuman",532,450);
				
				
				mainFrame.addWindowListener(new WindowAdapter() {
					public void windowClosingOrder(WindowEvent windowEvent) {
						System.exit(0);
					}
				});
				
				launch();
			}
		});
		JLabel path = new JLabel("Add Style Ids");
		path.setBounds(250, 500, 300, 31);
		
		
		
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent transferEvent) {
				
				Connection conn = null;
				PreparedStatement preparedstmt1;
				   Statement stmt = null;
				  
				   Date currentDatetime = new Date(System.currentTimeMillis());
				   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
				   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
				   DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
				    String formattedDate=dateFormat.format(timestamp);
				   try{
					 //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");

					      //STEP 3: Open a connection
					      System.out.println("Connecting to a selected database...");
					      conn = DriverManager.getConnection(DB_URL, USER, PASS);
					      System.out.println("Connected database successfully...");
					     // PreparedStatement pstm = null ;
					      
					      
					      //Reading the selected excel File
					     
					     /* FileInputStream input = new FileInputStream(path.getText());
					      System.out.println(input);
					      POIFSFileSystem fs = new POIFSFileSystem( input );
					      HSSFWorkbook wb = new HSSFWorkbook(fs);
					      HSSFSheet sheet = wb.getSheetAt(0);
					      Row row; */
					      
					    
					      
					      					      					      					      					      					  
					      
				           DateFormat df = new SimpleDateFormat("yyyyMMdd");
				           String strDate = df.format(sqlDate);  
				          
				               
				               //System.out.println(reportDate);
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
				               
				              
				               
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
				            //   Date orderedOn = Date.valueOf(orderedOnString);
				                //System.out.println(orderOn);
				           		
				              Integer challanid=Integer.parseInt(challanIdText.getText().trim());
				              System.out.println(challanid);
				              String styleCode="POP"+styleCodeText.getText().toUpperCase().trim();
				              String size=sizeText.getText().toUpperCase().trim();
				              Double quantity =Double.parseDouble(qtyText.getText());
				              Double leftQuantity=0.0;
				              Double outwardQuantity=0.0;
				             
				              
				              
				           
				              
				           
				           		
				           			String sql11= "select styleCode, size,leftQuantity,quantity from challan_outward where challanId=? and styleCode=? and size=? ";
					                
					                PreparedStatement preparedstmt51 = conn.prepareStatement(sql11);
					                preparedstmt51.setInt(1, challanid );
								      
					                preparedstmt51.setString(2, styleCode );
					                preparedstmt51.setString(3, size );
					                
						               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
						               while(rscheckkey51.next()) {
						            	   leftQuantity  = rscheckkey51.getDouble("leftQuantity");
						            	   outwardQuantity=rscheckkey51.getDouble("quantity");
						            	   }
						               if(outwardQuantity==0.0) {
						            	   System.out.println("Style Code , size and challan ID doesnot Match");
						            	   challanIdText.setText("");
										      challanIdText.requestFocus();
										      styleCodeText.setText("");
										      qtyText.setText("");
										      sizeText.setText("");
						               }else if(leftQuantity==0.0) {
						            	   System.out.println("All Goods Received back from vendor");
						            	   challanIdText.setText("");
										      challanIdText.requestFocus();
										      styleCodeText.setText("");
										      qtyText.setText("");
										      sizeText.setText("");
						               }else if(quantity>=leftQuantity) {
						            	   String sql = " INSERT ignore INTO challan_inward Values(?,?,?,?,?,?)";
										      PreparedStatement preparedstmt = conn.prepareStatement(sql);
										      
										      preparedstmt.setInt(1, challanid );
										      
										     
										      preparedstmt.setString(2, styleCode );
										      preparedstmt.setString(3, size );
										      preparedstmt.setDouble(4, leftQuantity );
										      preparedstmt.setDate(5, sqlDate );
										      preparedstmt.setString(6, formattedDate );
										      
										      preparedstmt.execute();
										      
										      String sql2="update ignore challan_outward set leftQuantity=? where challanId=? and styleCode=? and size=?";
										      PreparedStatement preparedstmt2 = conn.prepareStatement(sql2);
										      
										      preparedstmt2.setDouble(1, 0.0 );
										      preparedstmt2.setInt(2, challanid );
										      
										     
										      preparedstmt2.setString(3, styleCode );
										      preparedstmt2.setString(4, size );
										      preparedstmt2.execute();
										      challanIdText.setText("");
										      challanIdText.requestFocus();
										      styleCodeText.setText("");
										      qtyText.setText("");
										      sizeText.setText("");
						               }else {
						            	   String sql = " INSERT ignore INTO challan_inward Values(?,?,?,?,?,?)";
										      PreparedStatement preparedstmt = conn.prepareStatement(sql);
										      
										      preparedstmt.setInt(1, challanid );
										      
										     
										      preparedstmt.setString(2, styleCode );
										      preparedstmt.setString(3, size );
										      preparedstmt.setDouble(4, quantity );
										      preparedstmt.setDate(5, sqlDate );
										      preparedstmt.setString(6, formattedDate );
										      
										      preparedstmt.execute();
										      
										      String sql2="update ignore challan_outward set leftQuantity=leftQuantity-? where challanId=? and styleCode=? and size=?";
										      PreparedStatement preparedstmt2 = conn.prepareStatement(sql2);
										      
										      preparedstmt2.setDouble(1, quantity );
										      preparedstmt2.setInt(2, challanid );
										      
										      preparedstmt2.setString(3, styleCode );
										      preparedstmt2.setString(4, size );
										      preparedstmt2.execute();
										      challanIdText.setText("");
										      challanIdText.requestFocus();
										      styleCodeText.setText("");
										      qtyText.setText("");
										      sizeText.setText("");
						               }
				           
					               
									     
									      
				           	
					               
					        
		               
				      
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e1){
			      //Handle errors for Class.forName
			      e1.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			   path.setText("Transfer Complete");
			}
		});
		
								
		
		headerLabel.setBounds(150, 11, 400, 31);
		statusLabel.setBounds(180, 340, 312, 31);
		headerLabel.setText("Popnetic Warehousing System");
		
		controlPanel.add(path);
		controlPanel.add(add);
		controlPanel.add(sizeText);
		controlPanel.add(size);
		controlPanel.add(back);
		controlPanel.add(styleCodeText);
		controlPanel.add(styleCode);
		controlPanel.add(challanIdText);
		controlPanel.add(challanId);
		
		controlPanel.add(qty);
		controlPanel.add(qtyText);
		

	}
	
	
	
	//Challan Outward Launch
	
	private void challanOutwardLaunch() {
		prepareGUI("Challan Outward",700,500);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosingInwardLaunch(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		JCheckBox Challan = new JCheckBox("Challan");
		Challan.setBounds(150, 70, 70, 23);
		Challan.setBackground(SystemColor.CYAN);
		Challan.setEnabled(true);
		
		JCheckBox PO = new JCheckBox("Purchase Order");
		PO.setBounds(300, 70, 120, 23);
		PO.setBackground(SystemColor.CYAN);
		PO.setEnabled(true);
		
		JLabel vendorName = new JLabel("Vendor Name");
		vendorName.setForeground(new Color(70, 130, 180));
		vendorName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		vendorName.setBounds(180, 145, 124, 20);
		vendorName.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		
		
		JComboBox vendorNameText= new JComboBox();
		vendorNameText.setBounds(280, 140, 200, 30);
		
		Connection cn = null;
		try{
			Class.forName(JDBC_DRIVER);
			cn = DriverManager.getConnection(DB_URL, USER, PASS);
		}catch(ClassNotFoundException e)  {
				System.err.println("Failed to load driver");
				e.printStackTrace();
			}
			catch(SQLException e){
				System.err.println("Unable to connect");
				e.printStackTrace();
			}
		
		try{           
            Statement stmt = cn.createStatement();
            String queryFabric = "SELECT vendorName FROM vendor_list";
           
            ResultSet rsFabric = stmt.executeQuery(queryFabric); 
            vendorNameText.addItem("NULL");
           
            while (rsFabric.next())
            {
            	vendorNameText.addItem(rsFabric.getString("vendorName")); 

            }	
            	stmt.close();
            	
      }  
    catch(Exception ex)
      {	
      	}
		
		JLabel styleCode = new JLabel("Style Code");
		styleCode.setForeground(new Color(70, 130, 180));
		styleCode.setFont(new Font("Times New Roman", Font.BOLD, 15));
		styleCode.setBounds(10, 200, 124, 20);
		styleCode.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField styleCodeText = new JTextField();
		styleCodeText.setForeground(SystemColor.desktop);
		styleCodeText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		styleCodeText.setBounds(100, 195, 100, 30);
		styleCodeText.setColumns(10);
		
		JLabel size = new JLabel("Size");
		size.setForeground(new Color(70, 130, 180));
		size.setFont(new Font("Times New Roman", Font.BOLD, 15));
		size.setBounds(210, 200, 124, 20);
		size.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField sizeText = new JTextField();
		sizeText.setForeground(SystemColor.desktop);
		sizeText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sizeText.setBounds(250, 195, 100, 30);
		sizeText.setColumns(10);
		
		JLabel qty = new JLabel("Quantity");
		qty.setForeground(new Color(70, 130, 180));
		qty.setFont(new Font("Times New Roman", Font.BOLD, 15));
		qty.setBounds(370, 200, 124, 20);
		qty.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField qtyText = new JTextField();
		qtyText.setForeground(SystemColor.desktop);
		qtyText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		qtyText.setBounds(450, 195, 100, 30);
		qtyText.setColumns(10);
		
		
		JButton add = new JButton("ADD +");
		
		add.setForeground(SystemColor.desktop);
		add.setBackground(SystemColor.activeCaption);
		add.setBounds(560, 195, 80, 30);
		
		JButton generate = new JButton("Challan Outward");
		
		generate.setForeground(SystemColor.desktop);
		generate.setBackground(SystemColor.activeCaption);
		generate.setBounds(500, 140, 150, 31);
		
		
		
		
		JButton back = new JButton("Back");
		back.setBounds(300, 400, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent barcodeLaunchEvent) {
				mainFrame.dispose();
				prepareGUI("Hanuman",532,450);
				
				
				mainFrame.addWindowListener(new WindowAdapter() {
					public void windowClosingOrder(WindowEvent windowEvent) {
						System.exit(0);
					}
				});
				
				launch();
			}
		});
		JLabel path = new JLabel("Add Style Ids");
		path.setBounds(250, 500, 300, 31);
		
		
		
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent transferEvent) {
				
				Connection conn = null;
				PreparedStatement preparedstmt1;
				   Statement stmt = null;
				  
				   Date currentDatetime = new Date(System.currentTimeMillis());
				   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
				   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
				   DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
				    String formattedDate=dateFormat.format(timestamp);
				   try{
					 //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");

					      //STEP 3: Open a connection
					      System.out.println("Connecting to a selected database...");
					      conn = DriverManager.getConnection(DB_URL, USER, PASS);
					      System.out.println("Connected database successfully...");
					     // PreparedStatement pstm = null ;
					      
					      
					      //Reading the selected excel File
					     
					     /* FileInputStream input = new FileInputStream(path.getText());
					      System.out.println(input);
					      POIFSFileSystem fs = new POIFSFileSystem( input );
					      HSSFWorkbook wb = new HSSFWorkbook(fs);
					      HSSFSheet sheet = wb.getSheetAt(0);
					      Row row; */
					      
					    
					      
					      					      					      					      					      					  
					      
				           DateFormat df = new SimpleDateFormat("yyyyMMdd");
				           String strDate = df.format(sqlDate);  
				          
				               
				               //System.out.println(reportDate);
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
				               
				              
				               
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
				            //   Date orderedOn = Date.valueOf(orderedOnString);
				                //System.out.println(orderOn);
				           		String vendorName=vendorNameText.getSelectedItem().toString();
				              String vendorCode="";
				              String styleCode="POP"+styleCodeText.getText().toUpperCase().trim();
				              String size=sizeText.getText().toUpperCase().trim();
				              Double quantity =Double.parseDouble(qtyText.getText());
				              Double unitPrice=0.0;
				              Integer lastChallanId=0;
				              Integer i=0;
				              
				           
				              
				           
				           		if(challanVal==0) {
				           			String sql11= "select vendorCode,gstNumber,gstState,contactNumber,address from vendor_list where vendorName=?";
					                
					                PreparedStatement preparedstmt51 = conn.prepareStatement(sql11);
						               
					                preparedstmt51.setString(1, vendorName);
						               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
						               while(rscheckkey51.next()) {
						            	   vendorCode = rscheckkey51.getString("vendorCode");
						            	   
						                }
						               
						               String sql112= "select mrp from barcode_style_info where articleNumber=?";
						                
						                PreparedStatement preparedstmt512 = conn.prepareStatement(sql112);
							               
						                preparedstmt512.setString(1, styleCode);
							               ResultSet rscheckkey512 = preparedstmt512.executeQuery();
							               while(rscheckkey512.next()) {
							            	   unitPrice=(rscheckkey512.getDouble("mrp")*0.05)/1.05;
							            	
							                }
							               Double taxableValue= quantity*unitPrice;
						               
				           
					               String sql1= "select challanId from challan_outward ORDER BY challanId DESC limit 1";
					                
					                PreparedStatement preparedstmt5 = conn.prepareStatement(sql1);
						               
						               
						               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
						               while(rscheckkey5.next()) {
						            	   lastChallanId = rscheckkey5.getInt("challanId");
						                }
						               lastChallanId=lastChallanId+1;
					               
						               String sql = " INSERT ignore INTO challan_outward Values(?,?,?,?,?,?,?,?,?,?)";
									      PreparedStatement preparedstmt = conn.prepareStatement(sql);
									      
									      preparedstmt.setInt(1, lastChallanId );
									      preparedstmt.setString(2, vendorName );
									      preparedstmt.setString(3, vendorCode );
									      preparedstmt.setString(4, styleCode );
									      preparedstmt.setString(5, size );
									      preparedstmt.setDouble(6, quantity );
									      preparedstmt.setDate(7, sqlDate );
									      preparedstmt.setString(8, formattedDate );
									      preparedstmt.setDouble(9, quantity );
									      preparedstmt.setDouble(10, taxableValue );
									      preparedstmt.execute();
									      challanVal++;
									      styleCodeText.setText("");
									      qtyText.setText("");
									      sizeText.setText("");
									      vendorNameText.setEnabled(false);
									      
				           		}else {
				           			String sql11= "select vendorCode,gstNumber,gstState,contactNumber,address from vendor_list where vendorName=?";
					                
					                PreparedStatement preparedstmt51 = conn.prepareStatement(sql11);
						               
					                preparedstmt51.setString(1, vendorName);
						               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
						               while(rscheckkey51.next()) {
						            	   vendorCode = rscheckkey51.getString("vendorCode");
						            	 
						                }
						               
						               String sql1= "select challanId from challan_outward ORDER BY challanId DESC limit 1";
						                
						                PreparedStatement preparedstmt5 = conn.prepareStatement(sql1);
							               
							               
							               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							               while(rscheckkey5.next()) {
							            	   lastChallanId = rscheckkey5.getInt("challanId");
							                }
							               String sql112= "select mrp from barcode_style_info where articleNumber=?";
							                
							                PreparedStatement preparedstmt512 = conn.prepareStatement(sql112);
								               
							                preparedstmt512.setString(1, styleCode);
								               ResultSet rscheckkey512 = preparedstmt512.executeQuery();
								               while(rscheckkey512.next()) {
								            	   unitPrice=(rscheckkey512.getDouble("mrp")*0.05)/1.05;
								            	
								                }
								               Double taxableValue= quantity*unitPrice;
							               
							               String sql2="select challanId,styleCode,size from challan_outward where challanId=? and styleCode=? and size=? and vendorCode=?";
							               PreparedStatement preparedstmt52 = conn.prepareStatement(sql2);
							               preparedstmt52.setInt(1, lastChallanId);
							               preparedstmt52.setString(2, styleCode);
							               preparedstmt52.setString(3, size);
							               preparedstmt52.setString(4, vendorCode);
							               ResultSet rscheckkey52 = preparedstmt52.executeQuery();
							               while(rscheckkey52.next()) {
							            	   i=1;
							                }
							               if(i==1) {
							            	   String sql = " update ignore challan_outward set quantity=quantity+?,leftQuantity=leftQuantity+?,taxableValue=taxableValue+? where challanId=? and styleCode=? and size=? and vendorCode=?";
											      PreparedStatement preparedstmt = conn.prepareStatement(sql);
											      
											      preparedstmt.setDouble(1, quantity );
											      preparedstmt.setDouble(2, quantity );
											      preparedstmt.setDouble(3, taxableValue );
											      preparedstmt.setInt(4, lastChallanId );
											      preparedstmt.setString(5, styleCode);
									               preparedstmt.setString(6, size);
									               preparedstmt.setString(7, vendorCode);
									               preparedstmt.execute();
									               challanVal++;
												      styleCodeText.setText("");
												      qtyText.setText("");
												      sizeText.setText("");
							               }else {
							               
				           			String sql = " INSERT ignore INTO challan_outward Values(?,?,?,?,?,?,?,?,?,?)";
								      PreparedStatement preparedstmt = conn.prepareStatement(sql);
								      
								      preparedstmt.setInt(1, lastChallanId );
								      preparedstmt.setString(2, vendorName );
								      preparedstmt.setString(3, vendorCode );
								      preparedstmt.setString(4, styleCode );
								      preparedstmt.setString(5, size );
								      preparedstmt.setDouble(6, quantity );
								      preparedstmt.setDate(7, sqlDate );
								      preparedstmt.setString(8, formattedDate );
								      preparedstmt.setDouble(9, quantity);
								      preparedstmt.setDouble(10, taxableValue);
								      preparedstmt.execute();
								      challanVal++;
								      styleCodeText.setText("");
								      qtyText.setText("");
								      sizeText.setText("");
							               }
				           		}
					               
					        
		               
				      
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e1){
			      //Handle errors for Class.forName
			      e1.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			   path.setText("Transfer Complete");
			}
		});
		
								
		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent transferEvent) {
				
				Connection conn = null;
				PreparedStatement preparedstmt1;
				   Statement stmt = null;
				  
				   Date currentDatetime = new Date(System.currentTimeMillis());
				   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
				   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
				   DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
				    String formattedDate=dateFormat.format(timestamp);
				   try{
					 //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");

					      //STEP 3: Open a connection
					      System.out.println("Connecting to a selected database...");
					      conn = DriverManager.getConnection(DB_URL, USER, PASS);
					      System.out.println("Connected database successfully...");
					     // PreparedStatement pstm = null ;
					      
					      
					      //Reading the selected excel File
					     
					     /* FileInputStream input = new FileInputStream(path.getText());
					      System.out.println(input);
					      POIFSFileSystem fs = new POIFSFileSystem( input );
					      HSSFWorkbook wb = new HSSFWorkbook(fs);
					      HSSFSheet sheet = wb.getSheetAt(0);
					      Row row; */
					      if(challanVal==0) {
					    	  System.out.println("No Challan to generate");
					      }else {
					      String vendorName=vendorNameText.getSelectedItem().toString();
			              String vendorCode="";
			              
			              String gst="";
			              String add="";
			              Integer lastChallanId=0;
			              
			              String contactNumber="";
			              String state="";
					      Double unitPrice=47.61;
					      	Double TotaltaxableValue=0.0;				      					      					      					      					  
					      Double TotalQuantity=0.0;
				           DateFormat df = new SimpleDateFormat("yyyyMMdd");
				           String strDate = df.format(sqlDate);  
				           DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
				           String pdfDate = df1.format(sqlDate);
				           
				           DecimalFormat df2 = new DecimalFormat("0.00");
				               //System.out.println(reportDate);
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
				               
				             
				               
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
				            //   Date orderedOn = Date.valueOf(orderedOnString);
				                //System.out.println(orderOn);
				                
				           String sql11= "select vendorCode,gstNumber,gstState,contactNumber,address from vendor_list where vendorName=?";
			                
			                PreparedStatement preparedstmt51 = conn.prepareStatement(sql11);
				               
			                preparedstmt51.setString(1, vendorName);
				               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
				               while(rscheckkey51.next()) {
				            	   vendorCode = rscheckkey51.getString("vendorCode");
				            	   gst=rscheckkey51.getString("gstNumber");
				            	   state=rscheckkey51.getString("gstState");
				            	   add=rscheckkey51.getString("address");
				            	   contactNumber=rscheckkey51.getString("contactNumber");
				            	
				                }
				               
				               
				               
				               String sql1= "select challanId from challan_outward ORDER BY challanId DESC limit 1";
				                
				                PreparedStatement preparedstmt5 = conn.prepareStatement(sql1);
					               
					               
					               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
					               while(rscheckkey5.next()) {
					            	   lastChallanId = rscheckkey5.getInt("challanId");
					                }
					               
					               Barcode128 barcode128 = new Barcode128();
					               barcode128.setCode(lastChallanId.toString());
					               barcode128.setCodeType(Barcode.CODE128);
					              
					              
				              
				              
				              Document document = new Document();
				              com.itextpdf.text.Font blueFont = FontFactory.getFont(FontFactory.COURIER, 30, Font.BOLD);
				              com.itextpdf.text.Font blackFont = FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD);
				              com.itextpdf.text.Font underlineFont = FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD);
				              PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Abhay\\Desktop\\"+"Challan"+sqlDate+""+lastChallanId+".pdf"));
				             
				              document.open();
				              Paragraph para5 = new Paragraph("Delivery Challan(Original For Recipients)",blackFont);
				              para5.setAlignment(Element.ALIGN_CENTER);
				              document.add(para5);
				              Paragraph para6 = new Paragraph("Issue For Job Work",blackFont);
				              para6.setAlignment(Element.ALIGN_CENTER);
				              document.add(para6);
				              PdfContentByte pdfContentByte = writer.getDirectContent();
				              Image code128Image = barcode128.createImageWithBarcode(pdfContentByte, null, null);
				              Paragraph para3 = new Paragraph("Challan ID:"+" "+lastChallanId,blackFont);
				              para3.setAlignment(Element.ALIGN_LEFT);
				              document.add(code128Image);
				              document.add(para3);
				              
				              Paragraph para1 = new Paragraph("Date:"+" "+pdfDate,blackFont);
				              para1.setAlignment(Element.ALIGN_RIGHT);
				              document.add(para1);
				              Image image1 = com.itextpdf.text.Image.getInstance("C:\\Users\\ABHAY\\Dropbox\\YO\\Do Not Touch\\popnetic.png");
				              image1.setAbsolutePosition(470f, 750f);
				              image1.scaleAbsolute(80, 80);
				              document.add(image1);
				              Paragraph para = new Paragraph("Popnetic Fashions",blueFont);
				              para.setAlignment(Element.ALIGN_CENTER);
				              
				              document.add(para);
				              
				              Paragraph para2 = new Paragraph("\nD-355, Sector-10, Noida, U.P.(201301)  G.S.T : 09AGQPP9092D2ZP",blackFont);
				              para2.setAlignment(Element.ALIGN_CENTER);
				              
				              document.add(para2);
				              
				              Chunk underline = new Chunk("\nVendor Code:" + vendorCode+""+"||State:"+state);
				             // underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
				              underline.setFont(blackFont);
				              document.add(underline);
				             
				              Chunk underline2 =new Chunk("\n\nVendor Name :" +vendorNameText.getSelectedItem().toString()+"||Contact No:"+contactNumber);
				              //underline2.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
				              underline2.setFont(blackFont);
				              document.add(underline2);
				              
				              Chunk underline3 =new Chunk("\n\nVendor Address :" +add + "||GST Number:" + gst);
				             // underline3.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
				              underline3.setFont(blackFont);
				              document.add(underline3);
				              
				              
				              
				              PdfPTable table = new PdfPTable(6);
				              table.addCell(new PdfPCell(new Paragraph("Style Code",underlineFont)));
				              table.addCell(new PdfPCell(new Paragraph("Size",underlineFont)));
				              table.addCell(new PdfPCell(new Paragraph("Quantity",underlineFont)));
				              table.addCell(new PdfPCell(new Paragraph("HSN",underlineFont)));
				              table.addCell(new PdfPCell(new Paragraph("Unit Price",underlineFont)));
				              
				              table.addCell(new PdfPCell(new Paragraph("Taxable Value",underlineFont)));
				              
				              String sql12= "select styleCode,size,quantity from challan_outward where challanId=?";
				                
				                PreparedStatement preparedstmt52 = conn.prepareStatement(sql12);
					               
				                preparedstmt52.setInt(1, lastChallanId);
					               ResultSet rscheckkey52 = preparedstmt52.executeQuery();
					               while(rscheckkey52.next()) {
					            	  
					            	   table.addCell(new PdfPCell(new Paragraph(rscheckkey52.getString("styleCode"),underlineFont)));
					            	   table.addCell(new PdfPCell(new Paragraph(rscheckkey52.getString("size"),underlineFont)));
					            	   table.addCell(new PdfPCell(new Paragraph(rscheckkey52.getString("quantity")+"Pcs",underlineFont)));
					            	   table.addCell(new PdfPCell(new Paragraph("6211",underlineFont)));
					            	   String sql112= "select mrp from barcode_style_info where articleNumber=?";
						                
						                PreparedStatement preparedstmt512 = conn.prepareStatement(sql112);
							               
						                preparedstmt512.setString(1, rscheckkey52.getString("styleCode"));
							               ResultSet rscheckkey512 = preparedstmt512.executeQuery();
							               while(rscheckkey512.next()) {
							            	   unitPrice=(rscheckkey512.getDouble("mrp")*0.05)/1.05;
							            	
							                }
							               TotalQuantity=Double.parseDouble(rscheckkey52.getString("quantity"))+TotalQuantity;
							               Double taxableValue= Double.parseDouble(rscheckkey52.getString("quantity"))*unitPrice;
							               table.addCell(new PdfPCell(new Paragraph(df2.format(unitPrice),underlineFont)));
					            	   
							               table.addCell(new PdfPCell(new Paragraph(df2.format(taxableValue),underlineFont)));
					            	   TotaltaxableValue=taxableValue+TotaltaxableValue;
					                }
					               
					            document.add(table);   
					               
					            PdfPTable table4 = new PdfPTable(2);
					              table4.addCell(new PdfPCell(new Paragraph("Total Quantity",underlineFont)));
					              
					              table4.addCell(new PdfPCell(new Paragraph(df2.format(TotalQuantity),underlineFont)));
					              document.add(table4);
				              
				              PdfPTable table1 = new PdfPTable(2);
				              table1.addCell(new PdfPCell(new Paragraph("Total Taxable Value",underlineFont)));
				              
				              table1.addCell(new PdfPCell(new Paragraph(df2.format(TotaltaxableValue),underlineFont)));
				              document.add(table1);
				              
				              PdfPTable table2 = new PdfPTable(2);
				              table2.addCell(new PdfPCell(new Paragraph("GST",underlineFont)));
				              String IGST = df2.format(TotaltaxableValue*0.05);
				              table2.addCell(new PdfPCell(new Paragraph(IGST,underlineFont)));
				              document.add(table2);
				              
				              String TotalValue= df2.format(TotaltaxableValue*1.05);
				              
				              PdfPTable table3 = new PdfPTable(2);
				              table3.addCell(new PdfPCell(new Paragraph("Total Value",underlineFont)));
				              
				              table3.addCell(new PdfPCell(new Paragraph(TotalValue,underlineFont)));
				              document.add(table3);
				              
				              Image image2 = com.itextpdf.text.Image.getInstance("C:\\Users\\ABHAY\\Dropbox\\YO\\Do Not Touch\\signature.png");
				              
				              image2.scaleAbsolute(100, 100);
				              document.add(image2);
				              
				              Paragraph para4 = new Paragraph("\nAuthorized Signatory",blackFont);
				              para4.setAlignment(Element.ALIGN_LEFT);
				              document.add(para4);
				              
				              Paragraph para7 = new Paragraph("\n\n\n\nRemarks: Being goods are sent for job work and not for sale.\n Same will be returned back after completion of job work.",blackFont);
				              para7.setAlignment(Element.ALIGN_CENTER);
				              document.add(para7);
				              
				              
				              
				              
				              
				              document.close();
				              writer.close();
				                challanVal=0;
				                vendorNameText.setEnabled(true);
				                vendorNameText.setSelectedIndex(0);
				                
				               
					      
					      }    
					      
		               
				      
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e1){
			      //Handle errors for Class.forName
			      e1.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			   path.setText("Transfer Complete");
			}
		});
		headerLabel.setBounds(150, 11, 400, 31);
		statusLabel.setBounds(180, 340, 312, 31);
		headerLabel.setText("Popnetic Warehousing System");
		
		
		controlPanel.add(path);
		controlPanel.add(add);
		controlPanel.add(sizeText);
		controlPanel.add(size);
		controlPanel.add(back);
		controlPanel.add(styleCodeText);
		controlPanel.add(styleCode);
		controlPanel.add(vendorNameText);
		controlPanel.add(vendorName);
		controlPanel.add(generate);
		controlPanel.add(qty);
		controlPanel.add(qtyText);
		

	}
	
	
	//Fabric Outward Launch
	
	
	private void fabricOutwardLaunch() {
		prepareGUI("Fabric Outward",800,600);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosingInwardLaunch(WindowEvent windowEvent) {
				System.exit(0);
				
			}
		});
		
		
		
		JLabel styleCode = new JLabel("Style Code");
		styleCode.setForeground(new Color(70, 130, 180));
		styleCode.setFont(new Font("Times New Roman", Font.BOLD, 15));
		styleCode.setBounds(180, 145, 124, 20);
		styleCode.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField styleCodeText = new JTextField();
		styleCodeText.setForeground(SystemColor.desktop);
		styleCodeText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		styleCodeText.setBounds(280, 140, 150, 30);
		styleCodeText.setColumns(10);
		
		JCheckBox XSCheckBox = new JCheckBox("XS");
		XSCheckBox.setBounds(70, 200, 50, 23);
		XSCheckBox.setBackground(SystemColor.CYAN);
		XSCheckBox.setEnabled(false);
	
		JTextField XSText = new JTextField();
		XSText.setForeground(SystemColor.desktop);
		XSText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		XSText.setBounds(70, 230, 50, 23);
		XSText.setColumns(10);
		XSText.setVisible(false);
		
		XSCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ieproductionCheckBoxTrue) {
				if(XSCheckBox.isSelected()) {
					XSText.setVisible(true);
					XSText.setEnabled(true);
					XSText.setText("0");
					XSText.requestFocus();
					
				}else {
					XSText.setVisible(false);
					XSText.setEnabled(false);
					XSText.setText("");
				}
			}
		});
		
		JCheckBox SCheckBox = new JCheckBox("S");
		SCheckBox.setBounds(160, 200, 50, 23);
		SCheckBox.setBackground(SystemColor.CYAN);
		SCheckBox.setEnabled(false);
	
		JTextField SText = new JTextField();
		SText.setForeground(SystemColor.desktop);
		SText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		SText.setBounds(160, 230, 50, 23);
		SText.setColumns(10);
		SText.setVisible(false);
		
		SCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent iSCheckBoxTrue) {
				if(SCheckBox.isSelected()) {
					SText.setVisible(true);
					SText.setEnabled(true);
					SText.setText("0");
					SText.requestFocus();
					
				}else {
					SText.setVisible(false);
					SText.setEnabled(false);
					SText.setText("");
				}
			}
		});
		
		
		JCheckBox MCheckBox = new JCheckBox("M");
		MCheckBox.setBounds(250, 200, 50, 23);
		MCheckBox.setBackground(SystemColor.CYAN);
		MCheckBox.setEnabled(false);
	
		JTextField MText = new JTextField();
		MText.setForeground(SystemColor.desktop);
		MText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		MText.setBounds(250, 230, 50, 23);
		MText.setColumns(10);
		MText.setVisible(false);
		
		MCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent iMCheckBoxTrue) {
				if(MCheckBox.isSelected()) {
					MText.setVisible(true);
					MText.setEnabled(true);
					MText.setText("0");
					MText.requestFocus();
					
				}
				else {
					MText.setVisible(false);
					MText.setEnabled(false);
					MText.setText("");
					
				}
			}
		});
		
		
		JCheckBox LCheckBox = new JCheckBox("L");
		LCheckBox.setBounds(340, 200, 50, 23);
		LCheckBox.setBackground(SystemColor.CYAN);
		LCheckBox.setEnabled(false);
	
		JTextField LText = new JTextField();
		LText.setForeground(SystemColor.desktop);
		LText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		LText.setBounds(340, 230, 50, 23);
		LText.setColumns(10);
		LText.setVisible(false);
		
		LCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent iLCheckBoxTrue) {
				if(LCheckBox.isSelected()) {
					LText.setVisible(true);
					LText.setEnabled(true);
					LText.setText("0");
					LText.requestFocus();
					
				}
				else {
					LText.setVisible(false);
					LText.setEnabled(false);
					LText.setText("");
					
				}
			}
		});
		
		
		JCheckBox XLCheckBox = new JCheckBox("XL");
		XLCheckBox.setBounds(430, 200, 50, 23);
		XLCheckBox.setBackground(SystemColor.CYAN);
		XLCheckBox.setEnabled(false);
	
		JTextField XLText = new JTextField();
		XLText.setForeground(SystemColor.desktop);
		XLText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		XLText.setBounds(430, 230, 50, 23);
		XLText.setColumns(10);
		XLText.setVisible(false);
		
		XLCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent iXLCheckBoxTrue) {
				if(XLCheckBox.isSelected()) {
					XLText.setVisible(true);
					XLText.setEnabled(true);
					XLText.setText("0");
					XLText.requestFocus();
					
				}
				else {
					XLText.setVisible(false);
					XLText.setEnabled(false);
					XLText.setText("");
					
				}
			}
		});
		
		JCheckBox XXLCheckBox = new JCheckBox("XXL");
		XXLCheckBox.setBounds(520, 200, 50, 23);
		XXLCheckBox.setBackground(SystemColor.CYAN);
		XXLCheckBox.setEnabled(false);
	
		JTextField XXLText = new JTextField();
		XXLText.setForeground(SystemColor.desktop);
		XXLText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		XXLText.setBounds(520, 230, 50, 23);
		XXLText.setColumns(10);
		XXLText.setVisible(false);
		
		XXLCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent iXXLCheckBoxTrue) {
				if(XXLCheckBox.isSelected()) {
					XXLText.setVisible(true);
					XXLText.setEnabled(true);
					XXLText.setText("0");
					XXLText.requestFocus();
					
					
				}
				else {
					XXLText.setVisible(false);
					XXLText.setEnabled(false);
					XXLText.setText("");
					
				}
			}
		});
		
		JCheckBox XXXLCheckBox = new JCheckBox("3XL");
		XXXLCheckBox.setBounds(600, 200, 50, 23);
		XXXLCheckBox.setBackground(SystemColor.CYAN);
		XXXLCheckBox.setEnabled(false);
	
		JTextField XXXLText = new JTextField();
		XXXLText.setForeground(SystemColor.desktop);
		XXXLText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		XXXLText.setBounds(600, 230, 50, 23);
		XXXLText.setColumns(10);
		XXXLText.setVisible(false);
		
		XXLCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent iXXXLCheckBoxTrue) {
				if(XXXLCheckBox.isSelected()) {
					XXXLText.setVisible(true);
					XXXLText.setEnabled(true);
					XXXLText.setText("0");
					XXXLText.requestFocus();
					
					
				}
				else {
					XXXLText.setVisible(false);
					XXXLText.setEnabled(false);
					XXXLText.setText("");
					
				}
			}
		});
		
		
		JLabel labelfabricCode1 = new JLabel("Fabric Code");
		labelfabricCode1.setForeground(new Color(70, 130, 180));
		labelfabricCode1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		labelfabricCode1.setBounds(180, 300, 124, 20);
		labelfabricCode1.setHorizontalAlignment(SwingConstants.LEFT);
		labelfabricCode1.setVisible(false);
		
		
			
			JTextField fabricCode1Text = new JTextField();
			fabricCode1Text.setForeground(SystemColor.desktop);
			fabricCode1Text.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			fabricCode1Text.setBounds(280, 300, 150, 30);
			fabricCode1Text.setColumns(10);
			fabricCode1Text.setVisible(false);
			
			
			
			JLabel labelfabricCode2 = new JLabel("Fabric Code");
			labelfabricCode2.setForeground(new Color(70, 130, 180));
			labelfabricCode2.setFont(new Font("Times New Roman", Font.BOLD, 15));
			labelfabricCode2.setBounds(180, 340, 124, 20);
			labelfabricCode2.setHorizontalAlignment(SwingConstants.LEFT);
			labelfabricCode2.setVisible(false);
		
		
			
			JTextField fabricCode2Text = new JTextField();
			fabricCode2Text.setForeground(SystemColor.desktop);
			fabricCode2Text.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			fabricCode2Text.setBounds(280, 340, 150, 30);
			fabricCode2Text.setColumns(10);
			fabricCode2Text.setVisible(false);
		
			
			JLabel labelfabricCode3 = new JLabel("Fabric Code");
			labelfabricCode3.setForeground(new Color(70, 130, 180));
			labelfabricCode3.setFont(new Font("Times New Roman", Font.BOLD, 15));
			labelfabricCode3.setBounds(180, 380, 124, 20);
			labelfabricCode3.setHorizontalAlignment(SwingConstants.LEFT);
			labelfabricCode3.setVisible(false);
		
		
			
			JTextField fabricCode3Text = new JTextField();
			fabricCode3Text.setForeground(SystemColor.desktop);
			fabricCode3Text.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			fabricCode3Text.setBounds(280, 380, 150, 30);
			fabricCode3Text.setColumns(10);
			fabricCode3Text.setVisible(false);
			
			
			JLabel labelfabricCode4 = new JLabel("Fabric Code");
			labelfabricCode4.setForeground(new Color(70, 130, 180));
			labelfabricCode4.setFont(new Font("Times New Roman", Font.BOLD, 15));
			labelfabricCode4.setBounds(180, 420, 124, 20);
			labelfabricCode4.setHorizontalAlignment(SwingConstants.LEFT);
			labelfabricCode4.setVisible(false);
		
		
			
			JTextField fabricCode4Text = new JTextField();
			fabricCode4Text.setForeground(SystemColor.desktop);
			fabricCode3Text.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			fabricCode3Text.setBounds(280, 420, 150, 30);
			fabricCode3Text.setColumns(10);
			fabricCode3Text.setVisible(false);
		
		JButton inward = new JButton("Outward");
		inward.setBounds(600, 140, 150, 30);
		inward.setForeground(SystemColor.desktop);
		inward.setBackground(SystemColor.RED);
		inward.setEnabled(false);
		
		JButton back = new JButton("Back");
		back.setBounds(300, 500, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent barcodeLaunchEvent) {
				mainFrame.dispose();
				prepareGUI("Hanuman",532,450);
				
				
				mainFrame.addWindowListener(new WindowAdapter() {
					public void windowClosingOrder(WindowEvent windowEvent) {
						System.exit(0);
					}
				});
				
				launch();
			}
		});
		JLabel path = new JLabel("NO FILES Selected");
		path.setBounds(250, 260, 300, 31);
		
		styleCodeText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent transferEvent) {
				
				Connection conn = null;
				PreparedStatement preparedstmt1;
				   Statement stmt = null;
				  
				   Date currentDatetime = new Date(System.currentTimeMillis());
				   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
				   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
				   DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
				    String formattedDate=dateFormat.format(timestamp);
				   try{
					 //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");

					      //STEP 3: Open a connection
					      System.out.println("Connecting to a selected database...");
					      conn = DriverManager.getConnection(DB_URL, USER, PASS);
					      System.out.println("Connected database successfully...");
					     // PreparedStatement pstm = null ;
					      
					      
					      //Reading the selected excel File
					     
					     /* FileInputStream input = new FileInputStream(path.getText());
					      System.out.println(input);
					      POIFSFileSystem fs = new POIFSFileSystem( input );
					      HSSFWorkbook wb = new HSSFWorkbook(fs);
					      HSSFSheet sheet = wb.getSheetAt(0);
					      Row row; */
					      
					    
					      
					      					      					      					      					      					  
					      
				           DateFormat df = new SimpleDateFormat("yyyyMMdd");
				           String strDate = df.format(sqlDate);  
				          
				               
				               //System.out.println(reportDate);
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
				               
				              String barcode="POP"+styleCodeText.getText().trim();
				              System.out.println(barcode);
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
				            //   Date orderedOn = Date.valueOf(orderedOnString);
				                //System.out.println(orderOn);
				              int fabricCount=0;
				              String fabricCode1="";
				              String fabricCode2="";
				             String fabricCode3="";
				              String fabricCode4="";
				              String uniqueID="";
				             // String[] uniqueID1=new String[3];
				              ArrayList<String> sizes = new ArrayList<String>();
				              
				              
				              ArrayList<String> fabricCodeString = new ArrayList<String>();
				              String sql11= "select articleNumber,fabricCount,fabricCode1,fabricCode2,fabricCode3,fabricCode4 from barcode_style_info where articleNumber=?";
				                
				                PreparedStatement preparedstmt51 = conn.prepareStatement(sql11);
					               
				                preparedstmt51.setString(1, barcode);
					               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
					               while(rscheckkey51.next()) {
					            	   uniqueID=rscheckkey51.getString("articleNumber");
					            	   fabricCount=rscheckkey51.getInt("fabricCount");
					            	   fabricCode1 = rscheckkey51.getString("fabricCode1");
					            	   fabricCode2=rscheckkey51.getString("fabricCode2");
					            	   fabricCode3=rscheckkey51.getString("fabricCode3");
					            	   fabricCode4=rscheckkey51.getString("fabricCode4");
					            	
					                }
					               fabricCodeString.add(fabricCode1);
					               fabricCodeString.add(fabricCode2);
					               fabricCodeString.add(fabricCode3);
					               fabricCodeString.add(fabricCode4);
				              
				             
				              
				              String sql11211="select size from myntra_sku_level where vendorArticleNumber=?";
				              PreparedStatement preparedstmt51211 = conn.prepareStatement(sql11211);
				              preparedstmt51211.setString(1, barcode);
				              ResultSet rscheckkey51211 = preparedstmt51211.executeQuery();
				              int f=0;
				              
				              
				              while(rscheckkey51211.next()) {
				            	   sizes.add(rscheckkey51211.getString("size"));
				            	
				                }
				             
				              
				           
				            	if(sizes.contains("XS")) {
				            		XSCheckBox.setEnabled(true);
				            		SCheckBox.setEnabled(true);
				            		MCheckBox.setEnabled(true);
				            		LCheckBox.setEnabled(true);
				            		XLCheckBox.setEnabled(true);
				            	}
				        
				             
				           
				            	if(sizes.contains("XXL")||sizes.contains("34")) {
				            		
				            		SCheckBox.setEnabled(true);
				            		MCheckBox.setEnabled(true);
				            		LCheckBox.setEnabled(true);
				            		XLCheckBox.setEnabled(true);
				            		XXLCheckBox.setEnabled(true);
				            	}
				            	
				            	if(sizes.contains("3XL")||sizes.contains("36")) {
				            		
				            		SCheckBox.setEnabled(true);
				            		MCheckBox.setEnabled(true);
				            		LCheckBox.setEnabled(true);
				            		XLCheckBox.setEnabled(true);
				            		XXLCheckBox.setEnabled(true);
				            		XXXLCheckBox.setEnabled(true);
				            	}
				            	
				          
				            if(!sizes.contains("XS")&&(!sizes.contains("XXL"))) {
				            	SCheckBox.setEnabled(true);
			            		MCheckBox.setEnabled(true);
			            		LCheckBox.setEnabled(true);
			            		XLCheckBox.setEnabled(true);
				            }
				             
				           
					               fabricCountGlobal=fabricCount;
					              
					               
					               if(barcode.equalsIgnoreCase(uniqueID)) {
					               
					             
					               if(fabricCount==1) {
					            	   
					            	 
							              
					            	   System.out.println(fabricCount);
					            	   labelfabricCode1.setText(fabricCode1);
					            	   labelfabricCode1.setVisible(true);
					            	   fabricCode1Text.setVisible(true);
					            	   fabricCode1Text.requestFocus();
					            	   fabricCode1Text.setText("0");
					            	   styleCodeText.setEnabled(false);
					            	   inward.setEnabled(true);
					            	   inward.setBackground(SystemColor.blue);
					            	   
							             
					            	   
					               } else if(fabricCount==2){
					            	  
					            	   
					            	   System.out.println(fabricCount);
					            	   labelfabricCode1.setText(fabricCode1);
					            	   labelfabricCode1.setVisible(true);
					            	   fabricCode1Text.setVisible(true);
					            	   fabricCode1Text.setEnabled(true);
					            	   fabricCode1Text.setText("0");
					            	   labelfabricCode2.setText(fabricCode2);
					            	   labelfabricCode2.setVisible(true);
					            	   fabricCode2Text.setVisible(true);
					            	   fabricCode2Text.setEnabled(true);
					            	   fabricCode2Text.setText("0");
					            	   styleCodeText.setEnabled(false);
					            	   fabricCode1Text.requestFocus();
					            	   inward.setEnabled(true);
					            	   inward.setBackground(SystemColor.blue);
							              
					               }else if (fabricCount==3){ 
					            	   System.out.println(fabricCount);
					            	   labelfabricCode1.setText(fabricCode1);
					            	   labelfabricCode1.setVisible(true);
					            	   fabricCode1Text.setVisible(true);
					            	   fabricCode1Text.setText("0");
					            	   labelfabricCode2.setText(fabricCode2);
					            	   labelfabricCode2.setVisible(true);
					            	   fabricCode2Text.setVisible(true);
					            	   fabricCode2Text.setText("0");
					            	   labelfabricCode3.setText(fabricCode3);
					            	   labelfabricCode3.setVisible(true);
					            	   fabricCode3Text.setVisible(true);
					            	   fabricCode3Text.setText("0");
					            	   styleCodeText.setEnabled(false);
					            	   fabricCode1Text.requestFocus();
					            	   inward.setEnabled(true);
					            	   inward.setBackground(SystemColor.blue);
					            	   
					               }else {
					            	   System.out.println(fabricCount);
					            	   labelfabricCode1.setText(fabricCode1);
					            	   labelfabricCode1.setVisible(true);
					            	   fabricCode1Text.setVisible(true);
					            	   fabricCode1Text.setText("0");
					            	   labelfabricCode2.setText(fabricCode2);
					            	   labelfabricCode2.setVisible(true);
					            	   fabricCode2Text.setVisible(true);
					            	   fabricCode2Text.setText("0");
					            	   labelfabricCode3.setText(fabricCode3);
					            	   labelfabricCode3.setVisible(true);
					            	   fabricCode3Text.setVisible(true);
					            	   fabricCode3Text.setText("0");
					            	   labelfabricCode4.setText(fabricCode4);
					            	   labelfabricCode4.setVisible(true);
					            	   fabricCode4Text.setVisible(true);
					            	   fabricCode4Text.setText("0");
					            	   styleCodeText.setEnabled(false);
					            
					            	   fabricCode1Text.requestFocus();
					            	   inward.setEnabled(true);
					            	   inward.setBackground(SystemColor.blue);
					            	   System.out.println("Nothing to do");
					               }
					               }else {
					            	   System.out.println("Style ID not present");
					            	   styleCodeText.setText("");
					            	   styleCodeText.requestFocus();
					               }
					               
				   }catch(SQLException se){
					      //Handle errors for JDBC
					      se.printStackTrace();
					   }catch(Exception e1){
					      //Handle errors for Class.forName
					      e1.printStackTrace();
					   }finally{
					      //finally block used to close resources
					      try{
					         if(stmt!=null)
					            conn.close();
					      }catch(SQLException se){
					      }// do nothing
					      try{
					         if(conn!=null)
					            conn.close();
					      }catch(SQLException se){
					         se.printStackTrace();
					      }//end finally try
					   }//end try
					   System.out.println("Goodbye!");
					   path.setText("Transfer Complete");
					}
				});
				
					               
					               //Inward Method Definition//
					               
					               inward.addActionListener(new ActionListener() {
					       				public void actionPerformed(ActionEvent transferEvent) {
					       					
					       					Connection conn = null;
					       					PreparedStatement preparedstmt1;
					       					   Statement stmt = null;
					       					  
					       					   Date currentDatetime = new Date(System.currentTimeMillis());
					       					   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
					       					   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
					       					   DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
					       					    String formattedDate=dateFormat.format(timestamp);
					       					   try{
					       						 //STEP 2: Register JDBC driver
					       						      Class.forName("com.mysql.jdbc.Driver");

					       						      //STEP 3: Open a connection
					       						      System.out.println("Connecting to a selected database...");
					       						      conn = DriverManager.getConnection(DB_URL, USER, PASS);
					       						      System.out.println("Connected database successfully...");
					       						     // PreparedStatement pstm = null ;
					       						      
					       						      
					       						      //Reading the selected excel File
					       						     
					       						     /* FileInputStream input = new FileInputStream(path.getText());
					       						      System.out.println(input);
					       						      POIFSFileSystem fs = new POIFSFileSystem( input );
					       						      HSSFWorkbook wb = new HSSFWorkbook(fs);
					       						      HSSFSheet sheet = wb.getSheetAt(0);
					       						      Row row; */
					       						      
					       						    
					       						      
					       						      					      					      					      					      					  
					       						      
					       					           DateFormat df = new SimpleDateFormat("yyyyMMdd");
					       					           String strDate = df.format(sqlDate);  
					       					          
					       					               
					       					               //System.out.println(reportDate);
					       					               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
					       					               
					       					              String barcode="POP"+styleCodeText.getText().trim();
					       					           
					       					               
					       					               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
					       					            //   Date orderedOn = Date.valueOf(orderedOnString);
					       					                //System.out.println(orderOn);
					       					            if(fabricCountGlobal==1) {
					       					            	ArrayList<String> uniqueID = new ArrayList<String>();
					       					                System.out.println(uniqueID);
					       					                Double fabricCode1TextValue=Double.valueOf(fabricCode1Text.getText().trim()) ;
					       					             
					       					             ArrayList<String> uniqueIDFabricOutward = new ArrayList<String>();
					       					             ArrayList<String> sizes = new ArrayList<String>();
					       					          ArrayList<Integer> sizeqty = new ArrayList<Integer>();
					       					             Integer TotalCount=0;
					       					             
					       					            	if(XSCheckBox.isSelected()) {
					       					            		
					       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
						       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
						       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"XS");
						       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
						       						              while(rscheckkey5.next()) {
						       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
						       						              }
						       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"XS")) {
						       					            			System.out.println("XS Already updated");
						       					            		}else {
					       					            		sizeqty.add(Integer.valueOf(XSText.getText().trim())) ;
					       					            		sizes.add("XS");
					       					            		TotalCount=TotalCount+Integer.valueOf(XSText.getText().trim());
					       					            		System.out.println("XS");
					       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"XS");
						       					            		}
					       					            		
					       					            	}
					       					               
					       					               
					       					            	if(SCheckBox.isSelected()) {
					       					            		
					       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
						       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
						       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"S");
						       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
						       						              while(rscheckkey5.next()) {
						       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
						       						              }
						       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"S")) {
						       					            			System.out.println("S Already updated");
						       					            		}else {
					       					            		sizeqty.add(Integer.valueOf(SText.getText().trim())) ;
					       					            		sizes.add("S");
					       					            		TotalCount=TotalCount+Integer.valueOf(SText.getText().trim());
					       					            		System.out.println("S");
					       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"S");
						       					            		}
					       					            	}
					       					            	
					       					            	if(MCheckBox.isSelected()) {
					       					            		
					       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
						       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
						       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"M");
						       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
						       						              while(rscheckkey5.next()) {
						       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
						       						              }
						       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"M")) {
						       					            			System.out.println("M Already updated");
						       					            		}else {
					       					            		sizeqty.add(Integer.valueOf(MText.getText().trim())) ;
					       					            		sizes.add("M");
					       					            		TotalCount=TotalCount+Integer.valueOf(MText.getText().trim());
					       					            		System.out.println("M");
					       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"M");
						       					            		}
					       					            	}
					       					            	
					       					            	if(LCheckBox.isSelected()) {
					       					            		
					       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
						       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
						       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"L");
						       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
						       						              while(rscheckkey5.next()) {
						       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
						       						              }
						       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"L")) {
						       					            			System.out.println("L Already updated");
						       					            		}else {
					       					            		sizeqty.add(Integer.valueOf(LText.getText().trim())) ;
					       					            		sizes.add("L");
					       					            		TotalCount=TotalCount+Integer.valueOf(LText.getText().trim());
					       					            		System.out.println("L");
					       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"L");
						       					            		}
					       					            	}
					       					            	
					       					            	if(XLCheckBox.isSelected()) {
					       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
						       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
						       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"XL");
						       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
						       						              while(rscheckkey5.next()) {
						       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
						       						              }
						       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"XL")) {
						       					            			System.out.println("XL Already updated");
						       					            		}else {
					       					            		sizeqty.add(Integer.valueOf(XLText.getText().trim())) ;
					       					            		sizes.add("XL");
					       					            		TotalCount=TotalCount+Integer.valueOf(XLText.getText().trim());
					       					            		System.out.println("XL");
					       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"XL");
						       					            		}
					       					            	}
					       					            	
					       					            	if(XXLCheckBox.isSelected()) {
					       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
						       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
						       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"XXL");
						       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
						       						              while(rscheckkey5.next()) {
						       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
						       						              }
						       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"XXL")) {
						       					            			System.out.println("XXL Already updated");
						       					            		}else {
					       					            		sizeqty.add(Integer.valueOf(XXLText.getText().trim())) ;
					       					            		sizes.add("XXL");
					       					            		TotalCount=TotalCount+Integer.valueOf(XXLText.getText().trim());
					       					            		System.out.println("XXL");
					       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"XXL");
						       					            		}
					       					            	}
					       					            	
					       					            	if(XXXLCheckBox.isSelected()) {
					       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
						       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
						       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"XXXL");
						       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
						       						              while(rscheckkey5.next()) {
						       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
						       						              }
						       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"XXXL")) {
						       					            			System.out.println("XXXL Already updated");
						       					            		}else {
					       					            		sizeqty.add(Integer.valueOf(XXXLText.getText().trim())) ;
					       					            		sizes.add("XXXL");
					       					            		TotalCount=TotalCount+Integer.valueOf(XXXLText.getText().trim());
					       					            		System.out.println("XXXL");
					       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"XXXL");
						       					            		}
					       					            	}
					       					            	
					       					            	System.out.println(sizes.size());
					       					                
					       					                
					       						             if(fabricCode1TextValue==0.0) {
					       						            	System.out.println("Nothing to update as Value is 0");
					       						             }else {
					       						            	 
					       						            	 for(int i=0;i<sizes.size();i++) {
					       						            	String sql = " INSERT ignore INTO fabric_outward Values(?,?,?,?,?,?,?,?,?)";
		       												      PreparedStatement preparedstmt = conn.prepareStatement(sql);
		       												      
		       												      preparedstmt.setString(1, uniqueID.get(i) );
		       												      preparedstmt.setString(2, labelfabricCode1.getText() );
		       												      preparedstmt.setString(3, barcode );
		       												   preparedstmt.setString(4, sizes.get(i));
		       												preparedstmt.setInt(5, sizeqty.get(i));
		       												      preparedstmt.setDouble(6, (fabricCode1TextValue/TotalCount)*sizeqty.get(i) );
		       												   preparedstmt.setDouble(7, (fabricCode1TextValue/TotalCount) );
		       												      preparedstmt.setDate(8, sqlDate );
		       												      preparedstmt.setString(9, formattedDate );
		       												   preparedstmt.execute();
					       						            	 }
					       						            	 if(sizes.size()==0) {
					       						            		System.out.println("Selected Sizes Already updated Today-No Need to update Inventory");
					       						            	 }else {
       												   String sql12 = " update ignore fabric_inventory set stockInMeter=stockInMeter-?,lastUpdateDate=?,lastUpdateTime=? where fabricCode=?";
       												      PreparedStatement preparedstmt12 = conn.prepareStatement(sql12);
       												      
       												   preparedstmt12.setDouble(1, fabricCode1TextValue);
       												      preparedstmt12.setDate(2, sqlDate);
       												      preparedstmt12.setString(3, formattedDate);
       												      preparedstmt12.setString(4, labelfabricCode1.getText());
       												      
       												      preparedstmt12.execute(); 
					       						            	 }
       												      
					       						             }
					       					            }else if(fabricCountGlobal==2) {
					       					            	
					       					            Double  fabricCode1TextValue=Double.valueOf(fabricCode1Text.getText().trim()) ;
					       					          Double fabricCode2TextValue=Double.valueOf(fabricCode2Text.getText().trim()) ;
					       					          
					       					            	if(fabricCode2TextValue==0) {
					       					            		ArrayList<String> uniqueID = new ArrayList<String>();
						       					                System.out.println(uniqueID);
						       					                
						       					             ArrayList<String> uniqueIDFabricOutward = new ArrayList<String>();
						       					             ArrayList<String> sizes = new ArrayList<String>();
						       					          ArrayList<Integer> sizeqty = new ArrayList<Integer>();
						       					             Integer TotalCount=0;
						       					             
						       					            	if(XSCheckBox.isSelected()) {
						       					            		
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"XS");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"XS")) {
							       					            			System.out.println("XS Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(XSText.getText().trim())) ;
						       					            		sizes.add("XS");
						       					            		TotalCount=TotalCount+Integer.valueOf(XSText.getText().trim());
						       					            		System.out.println("XS");
						       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"XS");
							       					            		}
						       					            		
						       					            	}
						       					               
						       					               
						       					            	if(SCheckBox.isSelected()) {
						       					            		
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"S");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"S")) {
							       					            			System.out.println("S Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(SText.getText().trim())) ;
						       					            		sizes.add("S");
						       					            		TotalCount=TotalCount+Integer.valueOf(SText.getText().trim());
						       					            		System.out.println("S");
						       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"S");
							       					            		}
						       					            	}
						       					            	
						       					            	if(MCheckBox.isSelected()) {
						       					            		
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"M");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"M")) {
							       					            			System.out.println("M Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(MText.getText().trim())) ;
						       					            		sizes.add("M");
						       					            		TotalCount=TotalCount+Integer.valueOf(MText.getText().trim());
						       					            		System.out.println("M");
						       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"M");
							       					            		}
						       					            	}
						       					            	
						       					            	if(LCheckBox.isSelected()) {
						       					            		
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"L");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"L")) {
							       					            			System.out.println("L Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(LText.getText().trim())) ;
						       					            		sizes.add("L");
						       					            		TotalCount=TotalCount+Integer.valueOf(LText.getText().trim());
						       					            		System.out.println("L");
						       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"L");
							       					            		}
						       					            	}
						       					            	
						       					            	if(XLCheckBox.isSelected()) {
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"XL");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"XL")) {
							       					            			System.out.println("XL Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(XLText.getText().trim())) ;
						       					            		sizes.add("XL");
						       					            		TotalCount=TotalCount+Integer.valueOf(XLText.getText().trim());
						       					            		System.out.println("XL");
						       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"XL");
							       					            		}
						       					            	}
						       					            	
						       					            	if(XXLCheckBox.isSelected()) {
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"XXL");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"XXL")) {
							       					            			System.out.println("XXL Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(XXLText.getText().trim())) ;
						       					            		sizes.add("XXL");
						       					            		TotalCount=TotalCount+Integer.valueOf(XXLText.getText().trim());
						       					            		System.out.println("XXL");
						       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"XXL");
							       					            		}
						       					            	}
						       					            	
						       					            	if(XXXLCheckBox.isSelected()) {
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode1.getText()+"-"+strDate+"-"+"XXXL");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode1.getText()+"-"+strDate+"-"+"XXXL")) {
							       					            			System.out.println("XXXL Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(XXXLText.getText().trim())) ;
						       					            		sizes.add("XXXL");
						       					            		TotalCount=TotalCount+Integer.valueOf(XXXLText.getText().trim());
						       					            		System.out.println("XXXL");
						       					            		uniqueID.add(labelfabricCode1.getText()+"-"+strDate+"-"+"XXXL");
							       					            		}
						       					            	}
						       					            	
						       					            	System.out.println(sizes.size());
						       					            	
						       					             if(fabricCode1TextValue==0.0) {
						       						            	System.out.println("Nothing to update as Value is 0");
						       						             }else {
						       						            	 
						       						            	 for(int i=0;i<sizes.size();i++) {
						       						            	String sql = " INSERT ignore INTO fabric_outward Values(?,?,?,?,?,?,?,?,?)";
			       												      PreparedStatement preparedstmt = conn.prepareStatement(sql);
			       												      
			       												      preparedstmt.setString(1, uniqueID.get(i) );
			       												      preparedstmt.setString(2, labelfabricCode1.getText() );
			       												      preparedstmt.setString(3, barcode );
			       												   preparedstmt.setString(4, sizes.get(i));
			       												preparedstmt.setInt(5, sizeqty.get(i));
			       												      preparedstmt.setDouble(6, (fabricCode1TextValue/TotalCount)*sizeqty.get(i) );
			       												   preparedstmt.setDouble(7, (fabricCode1TextValue/TotalCount) );
			       												      preparedstmt.setDate(8, sqlDate );
			       												      preparedstmt.setString(9, formattedDate );
			       												   preparedstmt.execute();
						       						            	 }
						       						            	 if(sizes.size()==0) {
						       						            		System.out.println("Selected Sizes Already updated Today-No Need to update Inventory");
						       						            	 }else {
	       												   String sql12 = " update ignore fabric_inventory set stockInMeter=stockInMeter-?,lastUpdateDate=?,lastUpdateTime=? where fabricCode=?";
	       												      PreparedStatement preparedstmt12 = conn.prepareStatement(sql12);
	       												      
	       												   preparedstmt12.setDouble(1, fabricCode1TextValue);
	       												      preparedstmt12.setDate(2, sqlDate);
	       												      preparedstmt12.setString(3, formattedDate);
	       												      preparedstmt12.setString(4, labelfabricCode1.getText());
	       												      
	       												      preparedstmt12.execute(); 
						       						            	 }
	       												      
						       						             }
						       					                
					       					            	}else if(fabricCode1TextValue==0) {
					       					            		ArrayList<String> uniqueID = new ArrayList<String>();
						       					                System.out.println(uniqueID);
						       					                
						       					             ArrayList<String> uniqueIDFabricOutward = new ArrayList<String>();
						       					             ArrayList<String> sizes = new ArrayList<String>();
						       					          ArrayList<Integer> sizeqty = new ArrayList<Integer>();
						       					             Integer TotalCount=0;
						       					             
						       					            	if(XSCheckBox.isSelected()) {
						       					            		
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode2.getText()+"-"+strDate+"-"+"XS");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode2.getText()+"-"+strDate+"-"+"XS")) {
							       					            			System.out.println("XS Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(XSText.getText().trim())) ;
						       					            		sizes.add("XS");
						       					            		TotalCount=TotalCount+Integer.valueOf(XSText.getText().trim());
						       					            		System.out.println("XS");
						       					            		uniqueID.add(labelfabricCode2.getText()+"-"+strDate+"-"+"XS");
							       					            		}
						       					            		
						       					            	}
						       					               
						       					               
						       					            	if(SCheckBox.isSelected()) {
						       					            		
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode2.getText()+"-"+strDate+"-"+"S");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode2.getText()+"-"+strDate+"-"+"S")) {
							       					            			System.out.println("S Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(SText.getText().trim())) ;
						       					            		sizes.add("S");
						       					            		TotalCount=TotalCount+Integer.valueOf(SText.getText().trim());
						       					            		System.out.println("S");
						       					            		uniqueID.add(labelfabricCode2.getText()+"-"+strDate+"-"+"S");
							       					            		}
						       					            	}
						       					            	
						       					            	if(MCheckBox.isSelected()) {
						       					            		
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode2.getText()+"-"+strDate+"-"+"M");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode2.getText()+"-"+strDate+"-"+"M")) {
							       					            			System.out.println("M Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(MText.getText().trim())) ;
						       					            		sizes.add("M");
						       					            		TotalCount=TotalCount+Integer.valueOf(MText.getText().trim());
						       					            		System.out.println("M");
						       					            		uniqueID.add(labelfabricCode2.getText()+"-"+strDate+"-"+"M");
							       					            		}
						       					            	}
						       					            	
						       					            	if(LCheckBox.isSelected()) {
						       					            		
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode2.getText()+"-"+strDate+"-"+"L");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode2.getText()+"-"+strDate+"-"+"L")) {
							       					            			System.out.println("L Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(LText.getText().trim())) ;
						       					            		sizes.add("L");
						       					            		TotalCount=TotalCount+Integer.valueOf(LText.getText().trim());
						       					            		System.out.println("L");
						       					            		uniqueID.add(labelfabricCode2.getText()+"-"+strDate+"-"+"L");
							       					            		}
						       					            	}
						       					            	
						       					            	if(XLCheckBox.isSelected()) {
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode2.getText()+"-"+strDate+"-"+"XL");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode2.getText()+"-"+strDate+"-"+"XL")) {
							       					            			System.out.println("XL Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(XLText.getText().trim())) ;
						       					            		sizes.add("XL");
						       					            		TotalCount=TotalCount+Integer.valueOf(XLText.getText().trim());
						       					            		System.out.println("XL");
						       					            		uniqueID.add(labelfabricCode2.getText()+"-"+strDate+"-"+"XL");
							       					            		}
						       					            	}
						       					            	
						       					            	if(XXLCheckBox.isSelected()) {
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode2.getText()+"-"+strDate+"-"+"XXL");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode2.getText()+"-"+strDate+"-"+"XXL")) {
							       					            			System.out.println("XXL Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(XXLText.getText().trim())) ;
						       					            		sizes.add("XXL");
						       					            		TotalCount=TotalCount+Integer.valueOf(XXLText.getText().trim());
						       					            		System.out.println("XXL");
						       					            		uniqueID.add(labelfabricCode2.getText()+"-"+strDate+"-"+"XXL");
							       					            		}
						       					            	}
						       					            	
						       					            	if(XXXLCheckBox.isSelected()) {
						       					            		String sql5="select uniqueID from fabric_outward where uniqueID=?";
							       						              PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
							       						              preparedstmt5.setString(1, labelfabricCode2.getText()+"-"+strDate+"-"+"XXXL");
							       						              ResultSet rscheckkey5 = preparedstmt5.executeQuery();
							       						              while(rscheckkey5.next()) {
							       						            	  uniqueIDFabricOutward.add(rscheckkey5.getString("uniqueID"));
							       						              }
							       					            		if(uniqueIDFabricOutward.contains(labelfabricCode2.getText()+"-"+strDate+"-"+"XXXL")) {
							       					            			System.out.println("XXXL Already updated");
							       					            		}else {
						       					            		sizeqty.add(Integer.valueOf(XXXLText.getText().trim())) ;
						       					            		sizes.add("XXXL");
						       					            		TotalCount=TotalCount+Integer.valueOf(XXXLText.getText().trim());
						       					            		System.out.println("XXXL");
						       					            		uniqueID.add(labelfabricCode2.getText()+"-"+strDate+"-"+"XXXL");
							       					            		}
						       					            	}
						       					            	
						       					            	System.out.println(sizes.size());
						       					            	
						       					             if(fabricCode2TextValue==0.0) {
						       						            	System.out.println("Nothing to update as Value is 0");
						       						             }else {
						       						            	 
						       						            	 for(int i=0;i<sizes.size();i++) {
						       						            	String sql = " INSERT ignore INTO fabric_outward Values(?,?,?,?,?,?,?,?,?)";
			       												      PreparedStatement preparedstmt = conn.prepareStatement(sql);
			       												      
			       												      preparedstmt.setString(1, uniqueID.get(i) );
			       												      preparedstmt.setString(2, labelfabricCode2.getText() );
			       												      preparedstmt.setString(3, barcode );
			       												   preparedstmt.setString(4, sizes.get(i));
			       												preparedstmt.setInt(5, sizeqty.get(i));
			       												      preparedstmt.setDouble(6, (fabricCode2TextValue/TotalCount)*sizeqty.get(i) );
			       												   preparedstmt.setDouble(7, (fabricCode2TextValue/TotalCount) );
			       												      preparedstmt.setDate(8, sqlDate );
			       												      preparedstmt.setString(9, formattedDate );
			       												   preparedstmt.execute();
						       						            	 }
						       						            	 if(sizes.size()==0) {
						       						            		System.out.println("Selected Sizes Already updated Today-No Need to update Inventory");
						       						            	 }else {
	       												   String sql12 = " update ignore fabric_inventory set stockInMeter=stockInMeter-?,lastUpdateDate=?,lastUpdateTime=? where fabricCode=?";
	       												      PreparedStatement preparedstmt12 = conn.prepareStatement(sql12);
	       												      
	       												   preparedstmt12.setDouble(1, fabricCode2TextValue);
	       												      preparedstmt12.setDate(2, sqlDate);
	       												      preparedstmt12.setString(3, formattedDate);
	       												      preparedstmt12.setString(4, labelfabricCode2.getText());
	       												      
	       												      preparedstmt12.execute(); 
						       						            	 }
						       						             }
					       					            	
					       					            	}else {
					       					            		System.out.println("Please enter the Values");
					       					            		}
					       					            
					       					            }else {
					       					            	System.out.println("Cant'Inward the Value");
					       					            	labelfabricCode1.setVisible(false);
		       												labelfabricCode1.setText("");
							       						      fabricCode1Text.setVisible(false);
							       						   fabricCode1Text.setText("");
							       						   	styleCodeText.setText("");
							       						    styleCodeText.setEnabled(true);
							       						    styleCodeText.requestFocus();
							       						      inward.setEnabled(false);
							       						      inward.setBackground(SystemColor.red);
					       					            }
					       					            labelfabricCode1.setVisible(false);
					       					            labelfabricCode1.setText("");
					       					            fabricCode1Text.setVisible(false);
					       					            fabricCode1Text.setText("");
					       						   
					       					            labelfabricCode2.setVisible(false);
					       					            labelfabricCode2.setText("");
					       					            fabricCode2Text.setVisible(false);
					       					            fabricCode2Text.setText("");
				       						   
					       					            labelfabricCode3.setVisible(false);
					       					            labelfabricCode3.setText("");
					       					            fabricCode3Text.setVisible(false);
					       					            fabricCode3Text.setText("");
					       					            
					       					            labelfabricCode4.setVisible(false);
					       					            labelfabricCode4.setText("");
					       					            fabricCode4Text.setVisible(false);
					       					            fabricCode4Text.setText("");
					       					            
					       					            XSCheckBox.setEnabled(false);
					       					            XSCheckBox.setSelected(false);
					       					            XSText.setText("");
					       					            XSText.setVisible(false);
					       					            
					       					            SCheckBox.setEnabled(false);
					       					            SCheckBox.setSelected(false);
					       					            SText.setText("");
					       					            SText.setVisible(false);
					       					            
					       					            MCheckBox.setEnabled(false);
					       					            MCheckBox.setSelected(false);
					       					            MText.setText("");
					       					            MText.setVisible(false);
					       					            
					       					            LCheckBox.setEnabled(false);
					       					            LCheckBox.setSelected(false);
					       					            LText.setText("");
					       					            LText.setVisible(false);
					       					            
					       					            XLCheckBox.setEnabled(false);
					       					            XLCheckBox.setSelected(false);
					       					            XLText.setText("");
					       					            XLText.setVisible(false);
					       					            
					       					            XXLCheckBox.setEnabled(false);
					       					            XXLCheckBox.setSelected(false);
					       					            XXLText.setText("");
					       					            XXLText.setVisible(false);
					       					            
					       					         XXXLCheckBox.setEnabled(false);
					       					            XXXLCheckBox.setSelected(false);
					       					            XXXLText.setText("");
					       					            XXXLText.setVisible(false);
				       						      
					       					            styleCodeText.setText("");
					       					            styleCodeText.setEnabled(true);
					       					            styleCodeText.requestFocus();
					       					            inward.setEnabled(false);
					       					            inward.setBackground(SystemColor.red);
					       						
					       						
					       					      
					       				}catch(SQLException se){
					       				 
					       				      //Handle errors for JDBC
					       				      se.printStackTrace();
					       				   }catch(Exception e1){
					       				      //Handle errors for Class.forName
					       				      e1.printStackTrace();
					       				      labelfabricCode1.setVisible(false);
					       				      labelfabricCode1.setText("");
			       						      fabricCode1Text.setVisible(false);
			       						      fabricCode1Text.setText("");
			       						   
			       						      labelfabricCode2.setVisible(false);
			       						      labelfabricCode2.setText("");
			       						      fabricCode2Text.setVisible(false);
			       						      fabricCode2Text.setText("");
		       						   
			       						      labelfabricCode3.setVisible(false);
			       						      labelfabricCode3.setText("");
			       						      fabricCode3Text.setVisible(false);
			       						      fabricCode3Text.setText("");
		       						   
			       						      labelfabricCode4.setVisible(false);
			       						      labelfabricCode4.setText("");
			       						      fabricCode4Text.setVisible(false);
			       						      fabricCode4Text.setText("");
			       						      
			       						     XSCheckBox.setEnabled(false);
		       					            XSCheckBox.setSelected(false);
		       					            XSText.setText("");
		       					            XSText.setVisible(false);
		       					            
		       					            SCheckBox.setEnabled(false);
		       					            SCheckBox.setSelected(false);
		       					            SText.setText("");
		       					            SText.setVisible(false);
		       					            
		       					            MCheckBox.setEnabled(false);
		       					            MCheckBox.setSelected(false);
		       					            MText.setText("");
		       					            MText.setVisible(false);
		       					            
		       					            LCheckBox.setEnabled(false);
		       					            LCheckBox.setSelected(false);
		       					            LText.setText("");
		       					            LText.setVisible(false);
		       					            
		       					            XLCheckBox.setEnabled(false);
		       					            XLCheckBox.setSelected(false);
		       					            XLText.setText("");
		       					            XLText.setVisible(false);
		       					            
		       					            XXLCheckBox.setEnabled(false);
		       					            XXLCheckBox.setSelected(false);
		       					            XXLText.setText("");
		       					            XXLText.setVisible(false);
		       					            
		       					         XXXLCheckBox.setEnabled(false);
		       					            XXXLCheckBox.setSelected(false);
		       					            XXXLText.setText("");
		       					            XXXLText.setVisible(false);
		       						      
			       						      styleCodeText.setText("");
			       						      styleCodeText.setEnabled(true);
			       						      styleCodeText.requestFocus();
			       						      inward.setEnabled(false);
			       						      inward.setBackground(SystemColor.red);
			       						
					       				   }finally{
					       					
					       				      //finally block used to close resources
					       				      try{
					       				         if(stmt!=null)
					       				            conn.close();
					       				      }catch(SQLException se){
					       				      }// do nothing
					       				      try{
					       				         if(conn!=null)
					       				            conn.close();
					       				      }catch(SQLException se){
					       				         se.printStackTrace();
					       				      }//end finally try
					       				   }//end try
					       				   System.out.println("Goodbye!");
					       				   path.setText("Transfer Complete");
					       				   
					       				}
					       				
					       			});
					            	   
		               
				      
			
		
		
								
		
		headerLabel.setBounds(150, 11, 400, 31);
		statusLabel.setBounds(180, 340, 312, 31);
		headerLabel.setText("Popnetic Warehousing System");
		controlPanel.add(XXXLText);
		controlPanel.add(XXXLCheckBox);
		controlPanel.add(fabricCode4Text);
		controlPanel.add(labelfabricCode4);
		controlPanel.add(fabricCode3Text);
		controlPanel.add(labelfabricCode3);
		controlPanel.add(fabricCode1Text);
		controlPanel.add(labelfabricCode1);
		controlPanel.add(fabricCode2Text);
		controlPanel.add(labelfabricCode2);
		controlPanel.add(back);
		controlPanel.add(inward);
		controlPanel.add(styleCodeText);
		controlPanel.add(styleCode);
		controlPanel.add(XSCheckBox);
		controlPanel.add(XSText);
		controlPanel.add(SCheckBox);
		controlPanel.add(SText);
		controlPanel.add(MText);
		controlPanel.add(MCheckBox);
		controlPanel.add(XXLText);
		controlPanel.add(XXLCheckBox);
		controlPanel.add(XLText);
		controlPanel.add(XLCheckBox);
		controlPanel.add(LText);
		controlPanel.add(LCheckBox);

	}
	
	//Fabric Inward Tab Launch
		private void fabricInwardLaunch() {
			prepareGUI("Fabric Inward",700,500);
			
			mainFrame.addWindowListener(new WindowAdapter() {
				public void windowClosingInwardLaunch(WindowEvent windowEvent) {
					System.exit(0);
					
				}
			});
			
			
			
			JLabel styleCode = new JLabel("Style Code");
			styleCode.setForeground(new Color(70, 130, 180));
			styleCode.setFont(new Font("Times New Roman", Font.BOLD, 15));
			styleCode.setBounds(180, 145, 124, 20);
			styleCode.setHorizontalAlignment(SwingConstants.LEFT);
			
			JTextField styleCodeText = new JTextField();
			styleCodeText.setForeground(SystemColor.desktop);
			styleCodeText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			styleCodeText.setBounds(280, 140, 150, 30);
			styleCodeText.setColumns(10);
			
			
			JLabel labelfabricCode1 = new JLabel("Fabric Code");
			labelfabricCode1.setForeground(new Color(70, 130, 180));
			labelfabricCode1.setFont(new Font("Times New Roman", Font.BOLD, 15));
			labelfabricCode1.setBounds(180, 180, 124, 20);
			labelfabricCode1.setHorizontalAlignment(SwingConstants.LEFT);
			labelfabricCode1.setVisible(false);
			
			
   			
   			JTextField fabricCode1Text = new JTextField();
   			fabricCode1Text.setForeground(SystemColor.desktop);
   			fabricCode1Text.setFont(new Font("Times New Roman", Font.PLAIN, 15));
   			fabricCode1Text.setBounds(280, 180, 150, 30);
   			fabricCode1Text.setColumns(10);
   			fabricCode1Text.setVisible(false);
   			
   			
   			
   			JLabel labelfabricCode2 = new JLabel("Fabric Code");
   			labelfabricCode2.setForeground(new Color(70, 130, 180));
   			labelfabricCode2.setFont(new Font("Times New Roman", Font.BOLD, 15));
   			labelfabricCode2.setBounds(180, 220, 124, 20);
   			labelfabricCode2.setHorizontalAlignment(SwingConstants.LEFT);
   			labelfabricCode2.setVisible(false);
			
			
   			
   			JTextField fabricCode2Text = new JTextField();
   			fabricCode2Text.setForeground(SystemColor.desktop);
   			fabricCode2Text.setFont(new Font("Times New Roman", Font.PLAIN, 15));
   			fabricCode2Text.setBounds(280, 220, 150, 30);
   			fabricCode2Text.setColumns(10);
   			fabricCode2Text.setVisible(false);
			
   			
   			JLabel labelfabricCode3 = new JLabel("Fabric Code");
   			labelfabricCode3.setForeground(new Color(70, 130, 180));
   			labelfabricCode3.setFont(new Font("Times New Roman", Font.BOLD, 15));
   			labelfabricCode3.setBounds(180, 260, 124, 20);
   			labelfabricCode3.setHorizontalAlignment(SwingConstants.LEFT);
   			labelfabricCode3.setVisible(false);
			
			
   			
   			JTextField fabricCode3Text = new JTextField();
   			fabricCode3Text.setForeground(SystemColor.desktop);
   			fabricCode3Text.setFont(new Font("Times New Roman", Font.PLAIN, 15));
   			fabricCode3Text.setBounds(280, 260, 150, 30);
   			fabricCode3Text.setColumns(10);
   			fabricCode3Text.setVisible(false);
   			
   			
   			JLabel labelfabricCode4 = new JLabel("Fabric Code");
   			labelfabricCode4.setForeground(new Color(70, 130, 180));
   			labelfabricCode4.setFont(new Font("Times New Roman", Font.BOLD, 15));
   			labelfabricCode4.setBounds(180, 300, 124, 20);
   			labelfabricCode4.setHorizontalAlignment(SwingConstants.LEFT);
   			labelfabricCode4.setVisible(false);
			
			
   			
   			JTextField fabricCode4Text = new JTextField();
   			fabricCode4Text.setForeground(SystemColor.desktop);
   			fabricCode3Text.setFont(new Font("Times New Roman", Font.PLAIN, 15));
   			fabricCode3Text.setBounds(280, 300, 150, 30);
   			fabricCode3Text.setColumns(10);
   			fabricCode3Text.setVisible(false);
			
			JButton inward = new JButton("Inward");
			inward.setBounds(500, 140, 150, 30);
			inward.setForeground(SystemColor.desktop);
			inward.setBackground(SystemColor.RED);
			inward.setEnabled(false);
			
			JButton back = new JButton("Back");
			back.setBounds(300, 400, 94, 31);
			back.setForeground(SystemColor.desktop);
			back.setBackground(SystemColor.controlDkShadow);
			
			
			
			
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent barcodeLaunchEvent) {
					mainFrame.dispose();
					prepareGUI("Hanuman",532,450);
					
					
					mainFrame.addWindowListener(new WindowAdapter() {
						public void windowClosingOrder(WindowEvent windowEvent) {
							System.exit(0);
						}
					});
					
					launch();
				}
			});
			JLabel path = new JLabel("NO FILES Selected");
			path.setBounds(250, 260, 300, 31);
			
			styleCodeText.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent transferEvent) {
					
					Connection conn = null;
					PreparedStatement preparedstmt1;
					   Statement stmt = null;
					  
					   Date currentDatetime = new Date(System.currentTimeMillis());
					   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
					   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
					   DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
					    String formattedDate=dateFormat.format(timestamp);
					   try{
						 //STEP 2: Register JDBC driver
						      Class.forName("com.mysql.jdbc.Driver");

						      //STEP 3: Open a connection
						      System.out.println("Connecting to a selected database...");
						      conn = DriverManager.getConnection(DB_URL, USER, PASS);
						      System.out.println("Connected database successfully...");
						     // PreparedStatement pstm = null ;
						      
						      
						      //Reading the selected excel File
						     
						     /* FileInputStream input = new FileInputStream(path.getText());
						      System.out.println(input);
						      POIFSFileSystem fs = new POIFSFileSystem( input );
						      HSSFWorkbook wb = new HSSFWorkbook(fs);
						      HSSFSheet sheet = wb.getSheetAt(0);
						      Row row; */
						      
						    
						      
						      					      					      					      					      					  
						      
					           DateFormat df = new SimpleDateFormat("yyyyMMdd");
					           String strDate = df.format(sqlDate);  
					          
					               
					               //System.out.println(reportDate);
					               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
					               
					              String barcode="POP"+styleCodeText.getText().trim();
					              System.out.println(barcode);
					               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
					            //   Date orderedOn = Date.valueOf(orderedOnString);
					                //System.out.println(orderOn);
					              int fabricCount=0;
					              String fabricCode1="";
					              String fabricCode2="";
					             String fabricCode3="";
					              String fabricCode4="";
					              String uniqueID="";
					              String[] uniqueID1=new String[3];
					              
					              String sql11= "select articleNumber,fabricCount,fabricCode1,fabricCode2,fabricCode3,fabricCode4 from barcode_style_info where articleNumber=?";
					                
					                PreparedStatement preparedstmt51 = conn.prepareStatement(sql11);
						               
					                preparedstmt51.setString(1, barcode);
						               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
						               while(rscheckkey51.next()) {
						            	   uniqueID=rscheckkey51.getString("articleNumber");
						            	   fabricCount=rscheckkey51.getInt("fabricCount");
						            	   fabricCode1 = rscheckkey51.getString("fabricCode1");
						            	   fabricCode2=rscheckkey51.getString("fabricCode2");
						            	   fabricCode3=rscheckkey51.getString("fabricCode3");
						            	   fabricCode4=rscheckkey51.getString("fabricCode4");
						            	  
						                }
					               
						               fabricCountGlobal=fabricCount;
						              
						               
						               if(barcode.equalsIgnoreCase(uniqueID)) {
						               
						             
						               if(fabricCount==1) {
						            	   
						            	   
						            	   String uniqueID2=fabricCode1+"-"+ strDate;
							                
							                String sql112= "select uniqueID from fabric_inward where uniqueID=?";
							                
							                PreparedStatement preparedstmt512 = conn.prepareStatement(sql112);
								               
							                preparedstmt512.setString(1, uniqueID2);
								               ResultSet rscheckkey512 = preparedstmt512.executeQuery();
								               while(rscheckkey512.next()) {
								            	   uniqueID1[0]=rscheckkey512.getString("uniqueID");
								            	   
								            	  
								                }
								               if(uniqueID2.equalsIgnoreCase(uniqueID1[0])) {
								            	   JFrame jFrame = new JFrame();
								                   JOptionPane.showMessageDialog(jFrame, "Already Done");
								                   styleCodeText.setText("");
								                   styleCodeText.requestFocus();
								               }else {
						            	   System.out.println(fabricCount);
						            	   labelfabricCode1.setText(fabricCode1);
						            	   labelfabricCode1.setVisible(true);
						            	   fabricCode1Text.setVisible(true);
						            	   fabricCode1Text.requestFocus();
						            	   styleCodeText.setEnabled(false);
						            	   inward.setEnabled(true);
						            	   inward.setBackground(SystemColor.blue);
						            	   
								               } 
						            	   
						               } else if(fabricCount==2){
						            	   String uniqueID2=fabricCode1+"-"+ strDate;
						            	   String uniqueID3=fabricCode2+"-"+strDate;
						            	   
						            	   String sql112= "select uniqueID from fabric_inward where uniqueID=?";
							                
							                PreparedStatement preparedstmt512 = conn.prepareStatement(sql112);
								               
							                preparedstmt512.setString(1, uniqueID2);
							                
								               ResultSet rscheckkey512 = preparedstmt512.executeQuery();
								               while(rscheckkey512.next()) {
								            	  uniqueID1[0]=rscheckkey512.getString("uniqueID");
								            	 
								                }
								               String sql1121= "select uniqueID from fabric_inward where uniqueID=?";
								                
								                PreparedStatement preparedstmt5121 = conn.prepareStatement(sql1121);
									               
								                preparedstmt5121.setString(1, uniqueID3);
								                
									               ResultSet rscheckkey5121 = preparedstmt5121.executeQuery();
									               while(rscheckkey5121.next()) {
									            	  uniqueID1[1]=rscheckkey5121.getString("uniqueID");
									            	 
									                }
								               
								               
								               if((uniqueID2.equalsIgnoreCase(uniqueID1[0]))&&(uniqueID3.equalsIgnoreCase(uniqueID1[1]))) {
								            	   JFrame jFrame = new JFrame();
								                   JOptionPane.showMessageDialog(jFrame, "Already Done");
								                   styleCodeText.setText("");
								                   styleCodeText.requestFocus();
								               }else if(uniqueID2.equalsIgnoreCase(uniqueID1[0])) {
								            	   labelfabricCode1.setText(fabricCode1);
								            	   labelfabricCode1.setVisible(true);
								            	   fabricCode1Text.setVisible(true);
								            	   fabricCode1Text.setText("0");
								            	   fabricCode1Text.setEnabled(false);
								            	   labelfabricCode2.setText(fabricCode2);
								            	   labelfabricCode2.setVisible(true);
								            	   fabricCode2Text.setVisible(true);
								            	   styleCodeText.setEnabled(false);
								            	   fabricCode2Text.requestFocus();
								            	   inward.setEnabled(true);
								            	   inward.setBackground(SystemColor.blue);
								               }else if(uniqueID3.equalsIgnoreCase(uniqueID1[1])) {
								            	   labelfabricCode1.setText(fabricCode1);
								            	   labelfabricCode1.setVisible(true);
								            	   fabricCode1Text.setVisible(true);
								                   fabricCode1Text.setEnabled(true);
								            	   labelfabricCode2.setText(fabricCode2);
								            	   labelfabricCode2.setVisible(true);
								            	   fabricCode2Text.setVisible(true);
								            	   fabricCode2Text.setText("0");
								            	   fabricCode2Text.setEnabled(false);
								            	   styleCodeText.setEnabled(false);
								            	   fabricCode1Text.requestFocus();
								            	   inward.setEnabled(true);
								            	   inward.setBackground(SystemColor.blue);
								               }else {
						            	   
						            	   System.out.println(fabricCount);
						            	   labelfabricCode1.setText(fabricCode1);
						            	   labelfabricCode1.setVisible(true);
						            	   fabricCode1Text.setVisible(true);
						            	   fabricCode1Text.setEnabled(true);
						            	   fabricCode1Text.setText("0");
						            	   labelfabricCode2.setText(fabricCode2);
						            	   labelfabricCode2.setVisible(true);
						            	   fabricCode2Text.setVisible(true);
						            	   fabricCode2Text.setEnabled(true);
						            	   fabricCode2Text.setText("0");
						            	   styleCodeText.setEnabled(false);
						            	   fabricCode1Text.requestFocus();
						            	   inward.setEnabled(true);
						            	   inward.setBackground(SystemColor.blue);
								               }
						               }else if (fabricCount==3){ 
						            	   System.out.println(fabricCount);
						            	   labelfabricCode1.setText(fabricCode1);
						            	   labelfabricCode1.setVisible(true);
						            	   fabricCode1Text.setVisible(true);
						            	   labelfabricCode2.setText(fabricCode2);
						            	   labelfabricCode2.setVisible(true);
						            	   fabricCode2Text.setVisible(true);
						            	   labelfabricCode3.setText(fabricCode3);
						            	   labelfabricCode3.setVisible(true);
						            	   fabricCode3Text.setVisible(true);
						            	   styleCodeText.setEnabled(false);
						            	   fabricCode1Text.requestFocus();
						            	   
						            	   
						               }else {
						            	   System.out.println(fabricCount);
						            	   labelfabricCode1.setText(fabricCode1);
						            	   labelfabricCode1.setVisible(true);
						            	   fabricCode1Text.setVisible(true);
						            	   labelfabricCode2.setText(fabricCode2);
						            	   labelfabricCode2.setVisible(true);
						            	   fabricCode2Text.setVisible(true);
						            	   labelfabricCode3.setText(fabricCode3);
						            	   labelfabricCode3.setVisible(true);
						            	   fabricCode3Text.setVisible(true);
						            	   labelfabricCode4.setText(fabricCode4);
						            	   labelfabricCode4.setVisible(true);
						            	   fabricCode4Text.setVisible(true);
						            	   styleCodeText.setEnabled(false);
						            	   
						            	   fabricCode1Text.requestFocus();
						            	   
						            	   System.out.println("Nothing to do");
						               }
						               }else {
						            	   System.out.println("Style ID not present");
						            	   styleCodeText.setText("");
						            	   styleCodeText.requestFocus();
						               }
						               
					   }catch(SQLException se){
						      //Handle errors for JDBC
						      se.printStackTrace();
						   }catch(Exception e1){
						      //Handle errors for Class.forName
						      e1.printStackTrace();
						   }finally{
						      //finally block used to close resources
						      try{
						         if(stmt!=null)
						            conn.close();
						      }catch(SQLException se){
						      }// do nothing
						      try{
						         if(conn!=null)
						            conn.close();
						      }catch(SQLException se){
						         se.printStackTrace();
						      }//end finally try
						   }//end try
						   System.out.println("Goodbye!");
						   path.setText("Transfer Complete");
						}
					});
					
						               
						               //Inward Method Definition//
						               
						               inward.addActionListener(new ActionListener() {
						       				public void actionPerformed(ActionEvent transferEvent) {
						       					
						       					Connection conn = null;
						       					PreparedStatement preparedstmt1;
						       					   Statement stmt = null;
						       					  
						       					   Date currentDatetime = new Date(System.currentTimeMillis());
						       					   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
						       					   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
						       					   DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
						       					    String formattedDate=dateFormat.format(timestamp);
						       					   try{
						       						 //STEP 2: Register JDBC driver
						       						      Class.forName("com.mysql.jdbc.Driver");

						       						      //STEP 3: Open a connection
						       						      System.out.println("Connecting to a selected database...");
						       						      conn = DriverManager.getConnection(DB_URL, USER, PASS);
						       						      System.out.println("Connected database successfully...");
						       						     // PreparedStatement pstm = null ;
						       						      
						       						      
						       						      //Reading the selected excel File
						       						     
						       						     /* FileInputStream input = new FileInputStream(path.getText());
						       						      System.out.println(input);
						       						      POIFSFileSystem fs = new POIFSFileSystem( input );
						       						      HSSFWorkbook wb = new HSSFWorkbook(fs);
						       						      HSSFSheet sheet = wb.getSheetAt(0);
						       						      Row row; */
						       						      
						       						    
						       						      
						       						      					      					      					      					      					  
						       						      
						       					           DateFormat df = new SimpleDateFormat("yyyyMMdd");
						       					           String strDate = df.format(sqlDate);  
						       					          
						       					               
						       					               //System.out.println(reportDate);
						       					               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
						       					               
						       					              String barcode="POP"+styleCodeText.getText().trim();
						       					              
						       					              
						       					               
						       					               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
						       					            //   Date orderedOn = Date.valueOf(orderedOnString);
						       					                //System.out.println(orderOn);
						       					            if(fabricCountGlobal==1) {
						       					               
						       					                String uniqueID= labelfabricCode1.getText()+"-"+strDate;
						       					                System.out.println(uniqueID);
						       					                Double fabricCode1TextValue=Double.valueOf(fabricCode1Text.getText().trim()) ;
						       					                
						       						             if(fabricCode1TextValue==0.0) {
						       						            	System.out.println("Nothing to update as Value is 0");
						       						             }else {
						       						              
						       						             
						       					             String sql = " INSERT ignore INTO fabric_inward Values(?,?,?,?,?,?)";
	       												      PreparedStatement preparedstmt = conn.prepareStatement(sql);
	       												      
	       												      preparedstmt.setString(1, uniqueID );
	       												      preparedstmt.setString(2, labelfabricCode1.getText() );
	       												      preparedstmt.setString(3, barcode );
	       												      preparedstmt.setDouble(4, fabricCode1TextValue );
	       												      preparedstmt.setDate(5, sqlDate );
	       												      preparedstmt.setString(6, formattedDate );
	       												     
	       												      
	       								              
	       												   String sql12 = " update ignore fabric_inventory set stockInMeter=stockInMeter+?,lastUpdateDate=?,lastUpdateTime=? where fabricCode=?";
	       												      PreparedStatement preparedstmt12 = conn.prepareStatement(sql12);
	       												      
	       												   preparedstmt12.setDouble(1, fabricCode1TextValue);
	       												      preparedstmt12.setDate(2, sqlDate);
	       												      preparedstmt12.setString(3, formattedDate);
	       												      preparedstmt12.setString(4, labelfabricCode1.getText());
	       												      
	       												      preparedstmt12.execute();  	   
						       						            	   
	       												   preparedstmt.execute();
						       						             }
						       					            }else if(fabricCountGlobal==2) {
						       					             Double fabricCode1TextValue=Double.valueOf(fabricCode1Text.getText().trim()) ;
						       					          Double fabricCode2TextValue=Double.valueOf(fabricCode2Text.getText().trim()) ;
						       					          
						       					            	if(fabricCode2TextValue==0) {
						       					            		String uniqueID1= labelfabricCode1.getText()+"-"+strDate;
						       					            		String sql = " INSERT ignore INTO fabric_inward Values(?,?,?,?,?,?)";
			       												      PreparedStatement preparedstmt = conn.prepareStatement(sql);
			       												      
			       												      preparedstmt.setString(1, uniqueID1 );
			       												      preparedstmt.setString(2, labelfabricCode1.getText() );
			       												      preparedstmt.setString(3, barcode );
			       												      preparedstmt.setDouble(4, fabricCode1TextValue );
			       												      preparedstmt.setDate(5, sqlDate );
			       												      preparedstmt.setString(6, formattedDate );
			       											
			       												   String sql12 = " update ignore fabric_inventory set stockInMeter=stockInMeter+?,lastUpdateDate=?,lastUpdateTime=? where fabricCode=?";
			       												      PreparedStatement preparedstmt12 = conn.prepareStatement(sql12);
			       												      
			       												      preparedstmt12.setDouble(1, fabricCode1TextValue);
			       												      preparedstmt12.setDate(2, sqlDate);
			       												      preparedstmt12.setString(3, formattedDate);
			       												      preparedstmt12.setString(4, labelfabricCode1.getText());
			       												      preparedstmt12.execute();  	   
								       						            	   
			       												   preparedstmt.execute();
						       					            	}else if(fabricCode1TextValue==0) {
						       					            		String uniqueID2= labelfabricCode2.getText()+"-"+strDate;
						       					            		String sql = " INSERT ignore INTO fabric_inward Values(?,?,?,?,?,?)";
			       												      PreparedStatement preparedstmt = conn.prepareStatement(sql);
			       												      
			       												      preparedstmt.setString(1, uniqueID2 );
			       												      preparedstmt.setString(2, labelfabricCode2.getText() );
			       												      preparedstmt.setString(3, barcode );
			       												      preparedstmt.setDouble(4, fabricCode2TextValue );
			       												      preparedstmt.setDate(5, sqlDate );
			       												      preparedstmt.setString(6, formattedDate );
			       											
			       												   String sql12 = " update ignore fabric_inventory set stockInMeter=stockInMeter+?,lastUpdateDate=?,lastUpdateTime=? where fabricCode=?";
			       												      PreparedStatement preparedstmt12 = conn.prepareStatement(sql12);
			       												      
			       												      preparedstmt12.setDouble(1, fabricCode2TextValue);
			       												      preparedstmt12.setDate(2, sqlDate);
			       												      preparedstmt12.setString(3, formattedDate);
			       												      preparedstmt12.setString(4, labelfabricCode2.getText());
			       												      preparedstmt12.execute();  	   
								       						            	   
			       												   preparedstmt.execute();
						       					            	}else if(fabricCode1TextValue>0&&fabricCode2TextValue>0) {
						       					            		String uniqueID1= labelfabricCode1.getText()+"-"+strDate;
						       					            		String sql = " INSERT ignore INTO fabric_inward Values(?,?,?,?,?,?)";
			       												      PreparedStatement preparedstmt = conn.prepareStatement(sql);
			       												      
			       												      preparedstmt.setString(1, uniqueID1 );
			       												      preparedstmt.setString(2, labelfabricCode1.getText() );
			       												      preparedstmt.setString(3, barcode );
			       												      preparedstmt.setDouble(4, fabricCode1TextValue );
			       												      preparedstmt.setDate(5, sqlDate );
			       												      preparedstmt.setString(6, formattedDate );
			       											
			       												   String sql12 = " update ignore fabric_inventory set stockInMeter=stockInMeter+?,lastUpdateDate=?,lastUpdateTime=? where fabricCode=?";
			       												      PreparedStatement preparedstmt12 = conn.prepareStatement(sql12);
			       												      
			       												      preparedstmt12.setDouble(1, fabricCode1TextValue);
			       												      preparedstmt12.setDate(2, sqlDate);
			       												      preparedstmt12.setString(3, formattedDate);
			       												      preparedstmt12.setString(4, labelfabricCode1.getText());
			       												      
			       												      preparedstmt12.execute();  	   
								       						            	   
			       												   preparedstmt.execute();
			       												   
			       												String uniqueID2= labelfabricCode2.getText()+"-"+strDate;
					       					            		String sql1 = " INSERT ignore INTO fabric_inward Values(?,?,?,?,?,?)";
					       					            		
		       												      PreparedStatement preparedstmt14  = conn.prepareStatement(sql1);
		       												      
		       												   preparedstmt14.setString(1, uniqueID2 );
		       												preparedstmt14.setString(2, labelfabricCode2.getText() );
		       												preparedstmt14.setString(3, barcode );
		       												preparedstmt14.setDouble(4, fabricCode2TextValue );
		       												preparedstmt14.setDate(5, sqlDate );
		       												preparedstmt14.setString(6, formattedDate );
		       											
		       												   String sql121 = " update ignore fabric_inventory set stockInMeter=stockInMeter+?,lastUpdateDate=?,lastUpdateTime=? where fabricCode=?";
		       												      PreparedStatement preparedstmt121 = conn.prepareStatement(sql121);
		       												      
		       												      preparedstmt121.setDouble(1, fabricCode2TextValue);
		       												      preparedstmt121.setDate(2, sqlDate);
		       												      preparedstmt121.setString(3, formattedDate);
		       												      preparedstmt121.setString(4, labelfabricCode2.getText());
		       												      
		       												      preparedstmt121.execute();  	   
							       						            	   
		       												   preparedstmt14.execute();
						       					            		
						       					            	}
						       					            	else {
						       					            		System.out.println("Please enter the Values");
						       					            	}
						       					            }
						       					            else {
						       					            	System.out.println("Cant'Inward the Value");
						       					            	labelfabricCode1.setVisible(false);
			       												labelfabricCode1.setText("");
								       						      fabricCode1Text.setVisible(false);
								       						   fabricCode1Text.setText("");
								       						   	styleCodeText.setText("");
								       						    styleCodeText.setEnabled(true);
								       						    styleCodeText.requestFocus();
								       						      inward.setEnabled(false);
								       						      inward.setBackground(SystemColor.red);
						       					            }
						       					            labelfabricCode1.setVisible(false);
						       					            labelfabricCode1.setText("");
						       					            fabricCode1Text.setVisible(false);
						       					            fabricCode1Text.setText("");
						       						   
						       					            labelfabricCode2.setVisible(false);
						       					            labelfabricCode2.setText("");
						       					            fabricCode2Text.setVisible(false);
						       					            fabricCode2Text.setText("");
					       						   
						       					            labelfabricCode3.setVisible(false);
						       					            labelfabricCode3.setText("");
						       					            fabricCode3Text.setVisible(false);
						       					            fabricCode3Text.setText("");
						       					            
						       					            labelfabricCode4.setVisible(false);
						       					            labelfabricCode4.setText("");
						       					            fabricCode4Text.setVisible(false);
						       					            fabricCode4Text.setText("");
					       						      
						       					            styleCodeText.setText("");
						       					            styleCodeText.setEnabled(true);
						       					            styleCodeText.requestFocus();
						       					            inward.setEnabled(false);
						       					            inward.setBackground(SystemColor.red);
						       						
						       						
						       					      
						       				}catch(SQLException se){
						       					
						       				      //Handle errors for JDBC
						       				      se.printStackTrace();
						       				   }catch(Exception e1){
						       				      //Handle errors for Class.forName
						       				      e1.printStackTrace();
						       				      labelfabricCode1.setVisible(false);
						       				      labelfabricCode1.setText("");
				       						      fabricCode1Text.setVisible(false);
				       						      fabricCode1Text.setText("");
				       						   
				       						      labelfabricCode2.setVisible(false);
				       						      labelfabricCode2.setText("");
				       						      fabricCode2Text.setVisible(false);
				       						      fabricCode2Text.setText("");
			       						   
				       						      labelfabricCode3.setVisible(false);
				       						      labelfabricCode3.setText("");
				       						      fabricCode3Text.setVisible(false);
				       						      fabricCode3Text.setText("");
			       						   
				       						      labelfabricCode4.setVisible(false);
				       						      labelfabricCode4.setText("");
				       						      fabricCode4Text.setVisible(false);
				       						      fabricCode4Text.setText("");
			       						      
				       						      styleCodeText.setText("");
				       						      styleCodeText.setEnabled(true);
				       						      styleCodeText.requestFocus();
				       						      inward.setEnabled(false);
				       						      inward.setBackground(SystemColor.red);
				       						
						       				   }finally{
						       					
						       				      //finally block used to close resources
						       				      try{
						       				         if(stmt!=null)
						       				            conn.close();
						       				      }catch(SQLException se){
						       				      }// do nothing
						       				      try{
						       				         if(conn!=null)
						       				            conn.close();
						       				      }catch(SQLException se){
						       				         se.printStackTrace();
						       				      }//end finally try
						       				   }//end try
						       				   System.out.println("Goodbye!");
						       				   path.setText("Transfer Complete");
						       				   
						       				}
						       				
						       			});
						            	   
			               
					      
				
			
			
									
			
			headerLabel.setBounds(150, 11, 400, 31);
			statusLabel.setBounds(180, 340, 312, 31);
			headerLabel.setText("Popnetic Warehousing System");
			controlPanel.add(fabricCode4Text);
			controlPanel.add(labelfabricCode4);
			controlPanel.add(fabricCode3Text);
			controlPanel.add(labelfabricCode3);
			controlPanel.add(fabricCode1Text);
			controlPanel.add(labelfabricCode1);
			controlPanel.add(fabricCode2Text);
			controlPanel.add(labelfabricCode2);
			controlPanel.add(back);
			controlPanel.add(inward);
			controlPanel.add(styleCodeText);
			controlPanel.add(styleCode);
			

		}
		
		
		
		//Ajio Return Scan launch
		
		private void ajioReturnLaunch() {
			prepareGUI("Ajio Return Scan",700,398);
			
			mainFrame.addWindowListener(new WindowAdapter() {
				public void windowClosingInwardLaunch(WindowEvent windowEvent) {
					System.exit(0);
				}
			});
			
			
			
			JLabel barcode = new JLabel("Scan AWB");
			barcode.setForeground(new Color(70, 130, 180));
			barcode.setFont(new Font("Times New Roman", Font.BOLD, 15));
			barcode.setBounds(20, 145, 124, 20);
			barcode.setHorizontalAlignment(SwingConstants.LEFT);
			
			JTextField barcodeText = new JTextField("");
			barcodeText.setForeground(SystemColor.desktop);
			barcodeText.setFont(new Font("Times New Roman", Font.BOLD, 80));
			barcodeText.setBounds(120, 100, 550, 160);
			barcodeText.setColumns(10);
			
			
			JButton back = new JButton("Back");
			back.setBounds(300, 300, 94, 31);
			back.setForeground(SystemColor.desktop);
			back.setBackground(SystemColor.controlDkShadow);
			
			
			
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent barcodeLaunchEvent) {
					mainFrame.dispose();
					prepareGUI("Hanuman",532,450);
					
					
					mainFrame.addWindowListener(new WindowAdapter() {
						public void windowClosingOrder(WindowEvent windowEvent) {
							System.exit(0);
						}
					});
					
					launch();
				}
			});
			JLabel path = new JLabel("NO FILES Selected");
			path.setBounds(250, 260, 300, 31);
			
			
			
									
			barcodeText.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent transferEvent) {
					
					Connection conn = null;
					PreparedStatement preparedstmt1;
					   Statement stmt = null;
					  
					   Date currentDatetime = new Date(System.currentTimeMillis());
					   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
					   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
					   DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
					    String formattedDate=dateFormat.format(timestamp);
					   try{
						 //STEP 2: Register JDBC driver
						      Class.forName("com.mysql.jdbc.Driver");

						      //STEP 3: Open a connection
						      System.out.println("Connecting to a selected database...");
						      conn = DriverManager.getConnection(DB_URL, USER, PASS);
						      System.out.println("Connected database successfully...");
						     // PreparedStatement pstm = null ;
						      
						      
						      //Reading the selected excel File
						     
						     /* FileInputStream input = new FileInputStream(path.getText());
						      System.out.println(input);
						      POIFSFileSystem fs = new POIFSFileSystem( input );
						      HSSFWorkbook wb = new HSSFWorkbook(fs);
						      HSSFSheet sheet = wb.getSheetAt(0);
						      Row row; */
						      
					               //System.out.println(reportDate);
					               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
					               
					              String barcode=barcodeText.getText().trim();
					              if(barcode.equals("")) {
					            	  				            	  
					            	  Toolkit.getDefaultToolkit().beep();
					              }
					               
					               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
					            //   Date orderedOn = Date.valueOf(orderedOnString);
					                //System.out.println(orderOn);
					                
					             
					               
					                String uniqueID= barcode;
					                System.out.println(uniqueID);
					                
					               
								            	   String sql = " INSERT ignore INTO ajio_return_scan Values(?,?)";
												      PreparedStatement preparedstmt = conn.prepareStatement(sql);
												      
												      preparedstmt.setString(1, uniqueID );
												      
												      preparedstmt.setDate(2, sqlDate );
												     
												     
												      preparedstmt.execute();
								              
						            	   
						        
						      
						      barcodeText.setText("");
						      
						      barcodeText.requestFocus();
						
					      
				}catch(SQLException se){
				      //Handle errors for JDBC
				      se.printStackTrace();
				   }catch(Exception e1){
				      //Handle errors for Class.forName
				      e1.printStackTrace();
				   }finally{
				      //finally block used to close resources
				      try{
				         if(stmt!=null)
				            conn.close();
				      }catch(SQLException se){
				      }// do nothing
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
				   }//end try
				   System.out.println("Goodbye!");
				   path.setText("Transfer Complete");
				}
			});
			headerLabel.setBounds(150, 11, 400, 31);
			statusLabel.setBounds(180, 340, 312, 31);
			headerLabel.setText("Popnetic Warehousing System");
			
			controlPanel.add(path);
			
			controlPanel.add(back);
			
			controlPanel.add(barcodeText);
			controlPanel.add(barcode);
			

		}
		

	
	//Inward Tab Launch
	private void inwardLaunch() {
		prepareGUI("Inventory Inward",700,398);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosingInwardLaunch(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		
		
		JLabel barcode = new JLabel("Scan Barcode");
		barcode.setForeground(new Color(70, 130, 180));
		barcode.setFont(new Font("Times New Roman", Font.BOLD, 15));
		barcode.setBounds(20, 145, 124, 20);
		barcode.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField barcodeText = new JTextField("");
		barcodeText.setForeground(SystemColor.desktop);
		barcodeText.setFont(new Font("Times New Roman", Font.BOLD, 80));
		barcodeText.setBounds(120, 100, 550, 160);
		barcodeText.setColumns(10);
		
		
		
		
		
		JButton back = new JButton("Back");
		back.setBounds(300, 300, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent barcodeLaunchEvent) {
				mainFrame.dispose();
				prepareGUI("Hanuman",532,450);
				
				
				mainFrame.addWindowListener(new WindowAdapter() {
					public void windowClosingOrder(WindowEvent windowEvent) {
						System.exit(0);
					}
				});
				
				launch();
			}
		});
		JLabel path = new JLabel("NO FILES Selected");
		path.setBounds(250, 260, 300, 31);
		
		
		
								
		barcodeText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent transferEvent) {
				
				Connection conn = null;
				PreparedStatement preparedstmt1;
				   Statement stmt = null;
				  
				   Date currentDatetime = new Date(System.currentTimeMillis());
				   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
				   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
				   DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
				    String formattedDate=dateFormat.format(timestamp);
				   try{
					 //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");

					      //STEP 3: Open a connection
					      System.out.println("Connecting to a selected database...");
					      conn = DriverManager.getConnection(DB_URL, USER, PASS);
					      System.out.println("Connected database successfully...");
					     // PreparedStatement pstm = null ;
					      
					      
					      //Reading the selected excel File
					     
					     /* FileInputStream input = new FileInputStream(path.getText());
					      System.out.println(input);
					      POIFSFileSystem fs = new POIFSFileSystem( input );
					      HSSFWorkbook wb = new HSSFWorkbook(fs);
					      HSSFSheet sheet = wb.getSheetAt(0);
					      Row row; */
					      
					    
					      
					      					      					      					      					      					  
					      
				           DateFormat df = new SimpleDateFormat("yyyyMMdd");
				           String strDate = df.format(sqlDate);  
				          
				               
				               //System.out.println(reportDate);
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
				               
				              String barcode=barcodeText.getText().trim();
				              if(barcode.equals("")) {
				            	  				            	  
				            	  Toolkit.getDefaultToolkit().beep();
				              }
				               
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
				            //   Date orderedOn = Date.valueOf(orderedOnString);
				                //System.out.println(orderOn);
				                
				              String sku="";
				              String size="";
				              String isscanned="";
				              String uniqueIDTest="";
				               
				                String uniqueID= barcode+"-"+strDate;
				                System.out.println(uniqueID);
				                
				                String sql11= "select sellerSkuCode,size,isscanned from barcode_style where barcode=?";
				                
				                PreparedStatement preparedstmt51 = conn.prepareStatement(sql11);
					               
				                preparedstmt51.setString(1, barcode);
					               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
					               while(rscheckkey51.next()) {
					            	   sku = rscheckkey51.getString("sellerSkuCode");
					            	   size=rscheckkey51.getString("size");
					            	   isscanned=rscheckkey51.getString("isscanned");
					            	  
					                }
					               
					               String sellerSku=sku+"_"+size;
					             
					               if(isscanned.equalsIgnoreCase("No")) {
					            	   
					            	   String sql113= "select uniqueID from inward_inventory where uniqueID=?";
						                
						                PreparedStatement preparedstmt513 = conn.prepareStatement(sql113);
							               
						                preparedstmt513.setString(1, uniqueID);
							               ResultSet rscheckkey513 = preparedstmt513.executeQuery();
							               while(rscheckkey513.next()) {
							            	   uniqueIDTest = rscheckkey513.getString("uniqueID");
							       
							                }
							               if(uniqueID.equalsIgnoreCase(uniqueIDTest)) {
							            	   String sql12 = " update ignore inward_inventory set scannedDate=?,scannedTime=? where uniqueID=?";
											      PreparedStatement preparedstmt12 = conn.prepareStatement(sql12);
											      
											      
											      preparedstmt12.setDate(1, sqlDate);
											      preparedstmt12.setString(2, formattedDate);
											      preparedstmt12.setString(3, uniqueID);
											      preparedstmt12.execute();
											      
							               }else {
							            	   String sql = " INSERT ignore INTO inward_inventory Values(?,?,?,?,?,?)";
											      PreparedStatement preparedstmt = conn.prepareStatement(sql);
											      
											      preparedstmt.setString(1, uniqueID );
											      preparedstmt.setString(2, barcode );
											      preparedstmt.setString(3, sku );
											      preparedstmt.setString(4, size );
											      preparedstmt.setDate(5, sqlDate );
											      preparedstmt.setString(6, formattedDate );
											     
											      preparedstmt.execute();
							               }
					            	   
					            	   String sql12 = " update ignore inventory set inventory=inventory+?,lastUpdate=?,lastUpdateTime=? where sku=?";
									      PreparedStatement preparedstmt12 = conn.prepareStatement(sql12);
									      
									      
									      preparedstmt12.setInt(1, 1);
									      preparedstmt12.setString(4, sellerSku);
									      preparedstmt12.setDate(2, sqlDate);
									      preparedstmt12.setString(3, formattedDate);
				              
					       
									    								      
									      String sql = " update ignore  barcode_style set isscanned=?,scanDate=?,scanTime=? where barcode=?";
									      PreparedStatement preparedstmt = conn.prepareStatement(sql);
									      
									      
									      preparedstmt.setString(1, "Yes");
									      preparedstmt.setDate(2, sqlDate);
									      preparedstmt.setString(3, formattedDate);
									      preparedstmt.setString(4, barcode);
								
									      preparedstmt12.execute(); 
									      preparedstmt.execute(); 
							   
									      
					               }else {
					            	   System.out.println("Nothing to do");
					               }
					               
					           
				            
					      
					      barcodeText.setText("");
					      
					      barcodeText.requestFocus();
					
				      
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e1){
			      //Handle errors for Class.forName
			      e1.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			   path.setText("Transfer Complete");
			}
		});
		headerLabel.setBounds(150, 11, 400, 31);
		statusLabel.setBounds(180, 340, 312, 31);
		headerLabel.setText("Popnetic Warehousing System");
		
		controlPanel.add(path);
		
		controlPanel.add(back);
		
		controlPanel.add(barcodeText);
		controlPanel.add(barcode);
		

	}
	//Outward Launch Tab
	
	
		private void outwardLaunch() {
			prepareGUI("Inventory Outward",700,398);
			
			mainFrame.addWindowListener(new WindowAdapter() {
				public void windowClosingbarcodeLaunch(WindowEvent windowEvent) {
					System.exit(0);
				}
			});
			
			
			
			JLabel barcode = new JLabel("Scan Barcode");
			barcode.setForeground(new Color(70, 130, 180));
			barcode.setFont(new Font("Times New Roman", Font.BOLD, 15));
			barcode.setBounds(20, 145, 124, 20);
			barcode.setHorizontalAlignment(SwingConstants.LEFT);
			
			JTextField barcodeText = new JTextField();
			barcodeText.setForeground(SystemColor.desktop);
			barcodeText.setFont(new Font("Times New Roman", Font.BOLD, 80));
			barcodeText.setBounds(120, 100, 550, 160);
			barcodeText.setColumns(10);
			
			
			
			
			
			JButton back = new JButton("Back");
			back.setBounds(300, 300, 94, 31);
			back.setForeground(SystemColor.desktop);
			back.setBackground(SystemColor.controlDkShadow);
			
			
			
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent barcodeLaunchEvent) {
					mainFrame.dispose();
					prepareGUI("Hanuman",532,450);
					
					
					mainFrame.addWindowListener(new WindowAdapter() {
						public void windowClosingOrder(WindowEvent windowEvent) {
							System.exit(0);
						}
					});
					
					launch();
				}
			});
			JLabel path = new JLabel("NO FILES Selected");
			path.setBounds(250, 260, 300, 31);
			
									
			barcodeText.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent transferEvent) {
					
					Connection conn = null;
					PreparedStatement preparedstmt1;
					   Statement stmt = null;
					  
					   Date currentDatetime = new Date(System.currentTimeMillis());
					   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
					   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
					   DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
					    String formattedDate=dateFormat.format(timestamp);
					   try{
						 //STEP 2: Register JDBC driver
						      Class.forName("com.mysql.jdbc.Driver");

						      //STEP 3: Open a connection
						      System.out.println("Connecting to a selected database...");
						      conn = DriverManager.getConnection(DB_URL, USER, PASS);
						      System.out.println("Connected database successfully...");
						     // PreparedStatement pstm = null ;
						      
						      
						      //Reading the selected excel File
						     
						     /* FileInputStream input = new FileInputStream(path.getText());
						      System.out.println(input);
						      POIFSFileSystem fs = new POIFSFileSystem( input );
						      HSSFWorkbook wb = new HSSFWorkbook(fs);
						      HSSFSheet sheet = wb.getSheetAt(0);
						      Row row; */
						      
						    
						      
						      					      					      					      					      					  
						      
						      DateFormat df = new SimpleDateFormat("yyyyMMdd");
					           String strDate = df.format(sqlDate);  
					          
					               
					               //System.out.println(reportDate);
					               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
					               
					              String barcode=barcodeText.getText().trim();
					              if(barcode.equals("")) {
	  				            	  
					            	  Toolkit.getDefaultToolkit().beep();
					              }
					               
					               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
					            //   Date orderedOn = Date.valueOf(orderedOnString);
					                //System.out.println(orderOn);
					                
					              String sku="";
					              String size="";
					              String isscanned="";
					              String uniqueIDTest="";
					               
					                String uniqueID= barcode+"-"+strDate;
					                System.out.println(uniqueID);
					                
					                String sql11= "select sellerSkuCode,size,isscanned from barcode_style where barcode=?";
					                
					                PreparedStatement preparedstmt51 = conn.prepareStatement(sql11);
						               
					                preparedstmt51.setString(1, barcode);
						               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
						               while(rscheckkey51.next()) {
						            	   sku = rscheckkey51.getString("sellerSkuCode");
						            	   size=rscheckkey51.getString("size");
						            	   isscanned=rscheckkey51.getString("isscanned");
						            	  
						                }
						               
						               String sellerSku=sku+"_"+size;
						             
						               if(isscanned.equalsIgnoreCase("Yes")) {
						            	   
						            	   String sql113= "select uniqueID from outward_inventory where uniqueID=?";
							                
							                PreparedStatement preparedstmt513 = conn.prepareStatement(sql113);
								               
							                preparedstmt513.setString(1, uniqueID);
								               ResultSet rscheckkey513 = preparedstmt513.executeQuery();
								               while(rscheckkey513.next()) {
								            	   uniqueIDTest = rscheckkey513.getString("uniqueID");
								       
								                }
								               if(uniqueID.equalsIgnoreCase(uniqueIDTest)) {
								            	   String sql12 = " update ignore outward_inventory set scannedDate=?,scannedTime=? where uniqueID=?";
												      PreparedStatement preparedstmt12 = conn.prepareStatement(sql12);
												      
												      
												      preparedstmt12.setDate(1, sqlDate);
												      preparedstmt12.setString(2, formattedDate);
												      preparedstmt12.setString(3, uniqueID);
												      preparedstmt12.execute();
												      
								               }else {
								            	   String sql = " INSERT ignore INTO outward_inventory Values(?,?,?,?,?,?)";
												      PreparedStatement preparedstmt = conn.prepareStatement(sql);
												      
												      preparedstmt.setString(1, uniqueID );
												      preparedstmt.setString(2, barcode );
												      preparedstmt.setString(3, sku );
												      preparedstmt.setString(4, size );
												      preparedstmt.setDate(5, sqlDate );
												      preparedstmt.setString(6, formattedDate );
												     
												      preparedstmt.execute();
								               }
						            	   
						            	   String sql12 = " update ignore inventory set inventory=inventory-?,lastUpdate=?,lastUpdateTime=? where sku=?";
										      PreparedStatement preparedstmt12 = conn.prepareStatement(sql12);
										      
										      
										      preparedstmt12.setInt(1, 1);
										      preparedstmt12.setString(4, sellerSku);
										      preparedstmt12.setDate(2, sqlDate);
										      preparedstmt12.setString(3, formattedDate);
					              
						       
										    								      
										      String sql = " update ignore  barcode_style set isscanned=?,scanDate=?,scanTime=? where barcode=?";
										      PreparedStatement preparedstmt = conn.prepareStatement(sql);
										      
										      
										      preparedstmt.setString(1, "No");
										      preparedstmt.setDate(2, sqlDate);
										      preparedstmt.setString(3, formattedDate);
										      preparedstmt.setString(4, barcode);
									
										      preparedstmt12.execute(); 
										      preparedstmt.execute(); 
								   
										      
						               }else {
						            	   System.out.println("Nothing to do");
						               }
						               
						               
					                
					         
						              
						               
					            
						      
						      barcodeText.setText("");
						      
						      barcodeText.requestFocus();
						      
			               
					      
				}catch(SQLException se){
				      //Handle errors for JDBC
				      se.printStackTrace();
				   }catch(Exception e1){
				      //Handle errors for Class.forName
				      e1.printStackTrace();
				   }finally{
				      //finally block used to close resources
				      try{
				         if(stmt!=null)
				            conn.close();
				      }catch(SQLException se){
				      }// do nothing
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
				   }//end try
				   System.out.println("Goodbye!");
				   path.setText("Transfer Complete");
				}
			});
			headerLabel.setBounds(150, 11, 400, 31);
			statusLabel.setBounds(180, 340, 312, 31);
			headerLabel.setText("Popnetic Warehousing System");
			
			controlPanel.add(path);
			
			controlPanel.add(back);
			
			controlPanel.add(barcodeText);
			controlPanel.add(barcode);
			

		}
	
		//Barcode Ajio Bulk
		
		private void barcodeAjioLaunch() {
			prepareGUI("Ajio Bulk Barcode Generator",700,398);
			
			mainFrame.addWindowListener(new WindowAdapter() {
				public void windowClosingbarcodeLaunch(WindowEvent windowEvent) {
					System.exit(0);
				}
			});
			
			
			
			
			
			
			
			
			JButton openfile = new JButton("Upload");
			openfile.setForeground(SystemColor.desktop);
			openfile.setBackground(SystemColor.activeCaption);
			openfile.setBounds(150, 140, 94, 31);
			openfile.setHorizontalAlignment(SwingConstants.LEFT);
			
			
			JButton transfer = new JButton("Generate");
			transfer.setForeground(SystemColor.desktop);
			transfer.setBackground(SystemColor.activeCaption);
			transfer.setBounds(450, 140, 94, 31);
			
			
			JButton back = new JButton("Back");
			back.setBounds(300, 300, 94, 31);
			back.setForeground(SystemColor.desktop);
			back.setBackground(SystemColor.controlDkShadow);
			
			
			
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent barcodeLaunchEvent) {
					mainFrame.dispose();
					prepareGUI("Hanuman",532,450);
					
					
					mainFrame.addWindowListener(new WindowAdapter() {
						public void windowClosingOrder(WindowEvent windowEvent) {
							System.exit(0);
						}
					});
					
					launch();
				}
			});
			JLabel path = new JLabel("NO FILES Selected");
			path.setBounds(250, 260, 300, 31);
			
			
			
			openfile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent openfileEvent) {
					JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
					 int r = j.showOpenDialog(null); 
					 if (r == JFileChooser.APPROVE_OPTION) 
						  
			            { 
			                // set the label to the path of the selected file 
			                path.setText(j.getSelectedFile().getAbsolutePath()); 
			            } 
			            // if the user cancelled the operation 
			            else
			                path.setText("the user cancelled the operation");
					
				}
				
				
				
			});
			
			transfer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent transferEvent) {
					
					Connection conn = null;
					PreparedStatement preparedstmt1;
					   Statement stmt = null;
					  
					   Date currentDatetime = new Date(System.currentTimeMillis());
					   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
					   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
					   DateFormat dateFormat = new SimpleDateFormat("HHmmss");
					    String formattedDate=dateFormat.format(timestamp);
					   try{
						 //STEP 2: Register JDBC driver
						      Class.forName("com.mysql.jdbc.Driver");

						      //STEP 3: Open a connection
						      System.out.println("Connecting to a selected database...");
						      conn = DriverManager.getConnection(DB_URL, USER, PASS);
						      System.out.println("Connected database successfully...");
						     // PreparedStatement pstm = null ;
						      
						      
						      //Reading the selected excel File
						     
						      FileInputStream input = new FileInputStream(path.getText());
						      System.out.println(input);
						      POIFSFileSystem fs = new POIFSFileSystem( input );
						      HSSFWorkbook wb = new HSSFWorkbook(fs);
						      HSSFSheet sheet = wb.getSheetAt(0);
						      Row row;
						      
						      XSSFWorkbook workbook = new XSSFWorkbook(); 
							     
						      XSSFSheet spreadsheet = workbook.createSheet("itemlabels");
						      XSSFRow row1 = spreadsheet.createRow(0);
						      XSSFCell cell;
						      FileWriter writer = null;
						      writer = new FileWriter("C:\\Users\\ABHAY\\Desktop\\itemlabels_"+sqlDate+""+formattedDate+".csv");
						      
						     
						      
						      
						      int j=1;
						      
						      writer.append("ItemCode");
						      writer.append(',');
						      writer.append("MfgBy");
						      writer.append(',');
						      writer.append("DOM");
						      writer.append(',');
						      writer.append("VendorSkuCode");
						      writer.append(',');
						      writer.append("ArticleType");
						      writer.append(',');
						      writer.append("MRP");
						      writer.append(',');
						      writer.append("ItemSize");
						      writer.append(',');
						      writer.append("ArticleColor");
						      writer.append(',');
						      writer.append("MFGAdd");
						      writer.append(',');
						      writer.append("BrandName");
						      writer.append('\n');
						      
						      
						      for(int i=1; i<=sheet.getLastRowNum(); i++){
					                row = sheet.getRow(i);
					                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					              String date= df.format(sqlDate);
					               DataFormatter formatter = new DataFormatter();
					               
					               //System.out.println(reportDate);
					               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
					               
					              
					               
					               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
					            //   Date orderedOn = Date.valueOf(orderedOnString);
					                //System.out.println(orderOn);
					                String sellerSkuCode = row.getCell(5).getStringCellValue();
					                
					                String sellerOrderId = row.getCell(1).getStringCellValue();
					                
					                
						           
						            
					                String ajioCode = row.getCell(4).getStringCellValue();
					                String hsn = row.getCell(6).getStringCellValue();
					                
					                int qty =(int) row.getCell(9).getNumericCellValue();
					                
					                String ean = row.getCell(7).getStringCellValue();
					                
					                
					                
					                
					                int mrp=(int) row.getCell(10).getNumericCellValue();
					                
					                int t=0;
					                
					                LocalDate currentDate
					                = LocalDate.parse(date);
					                
					                
					                
						               String mrpstring= String.valueOf(mrp);
						           
						               
						                
						          for(int k=1; k<=qty; k++) {
						        	  
								      writer.append(ean);
								      writer.append(',');
								      writer.append("Brand : Popnetic");
								      writer.append(',');
								      writer.append("HSN Code:"+hsn );
								      
								      writer.append(',');
								      writer.append("SKU ID :"+sellerSkuCode );
								      writer.append(',');
								      writer.append("MKT BY:"+" "+ "Popnetic Fashions");
								      writer.append(',');
								      writer.append(mrpstring);
								      writer.append(',');
								      writer.append("AjioCode :" + ajioCode);
								      writer.append(',');
								      writer.append("OrderID:" +sellerOrderId );
								      writer.append(',');
								      writer.append("MFG By: Popnetic Fashions");
								      writer.append(',');
								      writer.append("D-355 Sector-10 Noida-201301");
								      writer.append('\n');
								      
								      j++;
										      
										      
										      
										      
										      
						               }
						               
						               
					                
					                
					              /* String sql = " INSERT ignore INTO mdirect_daily_order_upload Values(?,?,?,?)";
								      PreparedStatement preparedstmt = conn.prepareStatement(sql);
								      
								      preparedstmt.setString(1, sellerSkuCode);
								      preparedstmt.setString(2, color);
								      preparedstmt.setString(3, size);
								      
								      preparedstmt.setInt(4, qty);
								     
								      System.out.println("Imported Rows"+i); */
						          
								      
					            }
						      
						      
						      writer.flush();
						      writer.close();
						      
						      Path temp = Files.move(Paths.get("C:\\Users\\ABHAY\\Desktop\\itemlabels_"+sqlDate+""+formattedDate+".csv"), Paths.get("C:\\Users\\ABHAY\\Downloads\\itemlabels_"+sqlDate+""+formattedDate+".csv"));
					      
				}catch(SQLException se){
				      //Handle errors for JDBC
				      se.printStackTrace();
				   }catch(Exception e1){
				      //Handle errors for Class.forName
				      e1.printStackTrace();
				   }finally{
				      //finally block used to close resources
				      try{
				         if(stmt!=null)
				            conn.close();
				      }catch(SQLException se){
				      }// do nothing
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
				   }//end try
				   System.out.println("Goodbye!");
				   path.setText("Transfer Complete");
				}
			});
			headerLabel.setBounds(150, 11, 400, 31);
			statusLabel.setBounds(180, 340, 312, 31);
			headerLabel.setText("Popnetic Warehousing System");
			controlPanel.add(openfile);
			controlPanel.add(path);
			controlPanel.add(transfer);
			controlPanel.add(back);
			

		}
	
	//Barcode 2 Tab Launch
		
	private void barcode2Launch() {
		prepareGUI("Single Barcode Generator",700,398);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosingbarcodeLaunch(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		
		
		JLabel styleCode = new JLabel("Style Code");
		styleCode.setForeground(new Color(70, 130, 180));
		styleCode.setFont(new Font("Times New Roman", Font.BOLD, 15));
		styleCode.setBounds(80, 140, 124, 20);
		styleCode.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField inputText = new JTextField();
		inputText.setForeground(SystemColor.desktop);
		inputText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		inputText.setBounds(160, 140, 120, 20);
		inputText.setColumns(10);
		
		
		JLabel size = new JLabel("Size");
		size.setForeground(new Color(70, 130, 180));
		size.setFont(new Font("Times New Roman", Font.BOLD, 15));
		size.setBounds(300, 140, 80, 20);
		size.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField sizeText = new JTextField();
		sizeText.setForeground(SystemColor.desktop);
		sizeText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sizeText.setBounds(350, 140, 50, 20);
		sizeText.setColumns(10);
		
		JLabel quantity = new JLabel("QTY");
		quantity.setForeground(new Color(70, 130, 180));
		quantity.setFont(new Font("Times New Roman", Font.BOLD, 15));
		quantity.setBounds(430, 140, 80, 20);
		quantity.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField qtyText = new JTextField();
		qtyText.setForeground(SystemColor.desktop);
		qtyText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		qtyText.setBounds(480, 140, 50, 20);
		qtyText.setColumns(10);
		
		
		
		JButton transfer = new JButton("Generate");
		transfer.setForeground(SystemColor.desktop);
		transfer.setBackground(SystemColor.activeCaption);
		transfer.setBounds(300, 200, 94, 31);
		
		
		JButton back = new JButton("Back");
		back.setBounds(300, 300, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent barcodeLaunchEvent) {
				mainFrame.dispose();
				prepareGUI("Hanuman",532,450);
				
				
				mainFrame.addWindowListener(new WindowAdapter() {
					public void windowClosingOrder(WindowEvent windowEvent) {
						System.exit(0);
					}
				});
				
				launch();
			}
		});
		JLabel path = new JLabel("NO FILES Selected");
		path.setBounds(250, 260, 300, 31);
		
								
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent transferEvent) {
				
				Connection conn = null;
				PreparedStatement preparedstmt1;
				   Statement stmt = null;
				  
				   Date currentDatetime = new Date(System.currentTimeMillis());
				   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
				   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
				   DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
				    String formattedDate=dateFormat.format(timestamp);
				   try{
					 //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");

					      //STEP 3: Open a connection
					      System.out.println("Connecting to a selected database...");
					      conn = DriverManager.getConnection(DB_URL, USER, PASS);
					      System.out.println("Connected database successfully...");
					     // PreparedStatement pstm = null ;
					      
					      
					      //Reading the selected excel File
					     
					     /* FileInputStream input = new FileInputStream(path.getText());
					      System.out.println(input);
					      POIFSFileSystem fs = new POIFSFileSystem( input );
					      HSSFWorkbook wb = new HSSFWorkbook(fs);
					      HSSFSheet sheet = wb.getSheetAt(0);
					      Row row; */
					      
					    
					      FileWriter writer = null;
					      
					      writer = new FileWriter("C:\\Users\\ABHAY\\Desktop\\itemlabels_"+sqlDate+""+formattedDate+".csv");
					     
					      
					      
					      int j=1;
					      
					      writer.append("ItemCode");
					      writer.append(',');
					      writer.append("MfgBy");
					      writer.append(',');
					      writer.append("DOM");
					      writer.append(',');
					      writer.append("VendorSkuCode");
					      writer.append(',');
					      writer.append("ArticleType");
					      writer.append(',');
					      writer.append("MRP");
					      writer.append(',');
					      writer.append("ItemSize");
					      writer.append(',');
					      writer.append("ArticleColor");
					      writer.append(',');
					      writer.append("MFGAdd");
					      writer.append(',');
					      writer.append("BrandName");
					      writer.append('\n');
					      
					      					      					      					      					      					  
					      
				           DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				              
				           String date= df.format(sqlDate);
				               
				               //System.out.println(reportDate);
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
				               
				              String sellerSkuCode1=inputText.getText().trim();
				              String sellerSkuCode = "POP"+sellerSkuCode1;
				               String size = sizeText.getText().toUpperCase().trim();
				               String qty1= qtyText.getText().trim();
				               int qty=Integer.parseInt(qty1);  
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
				            //   Date orderedOn = Date.valueOf(orderedOnString);
				                //System.out.println(orderOn);
				                
				                int lastbarcode=0;
				                int mrp=0 ;
				                Double unit=0.0;
				                String color="";
				                int test=0;
				                String articleType="";
				                int monthNumber=1;
				                
				                LocalDate currentDate
				                = LocalDate.parse(date);
				                Month month = currentDate.getMonth();
				                System.out.println(month);
				                int year=currentDate.getYear();
				                
				                String sql11= "select mrp,color,articleType from barcode_style_info where articleNumber=?";
				                
				                PreparedStatement preparedstmt51 = conn.prepareStatement(sql11);
					               
				                preparedstmt51.setString(1, sellerSkuCode);
					               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
					               while(rscheckkey51.next()) {
					            	   mrp = rscheckkey51.getInt("mrp");
					            	   color=rscheckkey51.getString("color");
					            	   articleType=rscheckkey51.getString("articleType");
					            	   test=1;
					                }
					               System.out.println(articleType);
					               String mrpstring= String.valueOf(mrp);
					               String monthString=String.valueOf(month);
					               String yearString=String.valueOf(year);
					             
					               if(test==1) {
					            	   if((articleType.equalsIgnoreCase("Trousers"))||(articleType.equalsIgnoreCase("Shorts"))||(articleType.equalsIgnoreCase("Palazzos"))) {
					            	   if(size.equalsIgnoreCase("S")) {
						                	unit=66.04;
						                }else if(size.equalsIgnoreCase("M")) {
						                	unit=71.12;
						                }else if(size.equalsIgnoreCase("L")) {
						                	unit=76.20;
						                }else if(size.equalsIgnoreCase("XL")) {
						                	unit=81.28;
						                }else if(size.equalsIgnoreCase("XXL")){
						                	unit=86.36;
						                }else {
						                	unit=91.44;
						                }
					            	   
					            	   }else {
					            		   if(size.equalsIgnoreCase("S")) {
							                	unit=86.36;
							                }else if(size.equalsIgnoreCase("M")) {
							                	unit=91.44;
							                }else if(size.equalsIgnoreCase("L")) {
							                	unit=96.52;
							                }else if(size.equalsIgnoreCase("XL")) {
							                	unit=101.6;
							                }else {
							                	unit=106.68;
							                }
					            	   }
					            		   
					            		   if(monthString.equalsIgnoreCase("January")) {
					            			   monthNumber=01;
					            		   }else if(monthString.equalsIgnoreCase("February")) {
					            			   monthNumber=02;
					            		   }else if(monthString.equalsIgnoreCase("March")) {
					            			   monthNumber=03;
					            		   }else if(monthString.equalsIgnoreCase("April")) {
					            			   monthNumber=04;
					            		   }else if(monthString.equalsIgnoreCase("May")) {
					            			   monthNumber=05;
					            		   }else if(monthString.equalsIgnoreCase("June")) {
					            			   monthNumber=06;
					            		   }else if(monthString.equalsIgnoreCase("July")) {
					            			   System.out.println("Iam Here");
					            			   monthNumber=07;
					            		   }else if(monthString.equalsIgnoreCase("August")) {
					            			   monthNumber=8;
					            		   }else if(monthString.equalsIgnoreCase("September")) {
					            			   monthNumber=9;
					            		   }else if(monthString.equalsIgnoreCase("October")) {
					            			   monthNumber=10;
					            		   }else if(monthString.equalsIgnoreCase("November")) {
					            			   monthNumber=11;
					            		   }else {
					            			   monthNumber=12;
					            		   }
					            	   
					            	   String monthNumberString=String.valueOf(monthNumber);
					            	   System.out.println(monthNumberString);
					            	   String unitString=String.valueOf(unit);
				               String sql1= "select barcode from barcode_style ORDER BY barcode DESC limit 1";
				                
				                PreparedStatement preparedstmt5 = conn.prepareStatement(sql1);
					               
					               
					               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
					               while(rscheckkey5.next()) {
					            	   lastbarcode = rscheckkey5.getInt("barcode");
					                }
					               lastbarcode=lastbarcode+1;
					               
					               
					               for(int k=1; k<=qty; k++) {
					            	   
									     
									      String lastbarcodeString = String.valueOf(lastbarcode) ;
									      writer.append(lastbarcodeString);
									      writer.append(',');
									      writer.append("Brand : Popnetic");
									      writer.append(',');
									      writer.append("Month Of MFG. :"+" "+monthNumberString+"/"+yearString);
									      writer.append(',');
									      writer.append("SKU ID :"+sellerSkuCode );
									      writer.append(',');
									      writer.append("Article Type:"+" "+ articleType);
									      writer.append(',');
									      writer.append(mrpstring);
									      writer.append(',');
									      writer.append("Size :" + size +"("+unitString+"cm)");
									      writer.append(',');
									      writer.append("Article Color :" +color);
									      writer.append(',');
									      writer.append("MFG By: Popnetic Fashions");
									      writer.append(',');
									      writer.append("D-355 Sector-10 Noida-201301");
									      writer.append('\n');
									      
									      j++;
									      
									      
									      String sql = " INSERT ignore INTO barcode_style Values(?,?,?,?,?,?)";
									      PreparedStatement preparedstmt = conn.prepareStatement(sql);
									      
									      preparedstmt.setInt(1, lastbarcode);
									      preparedstmt.setString(2, sellerSkuCode);
									      preparedstmt.setString(3, size);
									      
									      preparedstmt.setString(4, "No");
									      preparedstmt.setString(5, "NA");
									      preparedstmt.setString(6, "NA");
									     
									      System.out.println("Imported Rows"+k);
									      
									      preparedstmt.execute(); 
									      lastbarcode++;
									      
					               }     
									      
					               }else {
					            	   System.out.println("Nothing to do");
					               }
					               
					               
				                
				                
				              /* String sql = " INSERT ignore INTO mdirect_daily_order_upload Values(?,?,?,?)";
							      PreparedStatement preparedstmt = conn.prepareStatement(sql);
							      
							      preparedstmt.setString(1, sellerSkuCode);
							      preparedstmt.setString(2, color);
							      preparedstmt.setString(3, size);
							      
							      preparedstmt.setInt(4, qty);
							     
							      System.out.println("Imported Rows"+i); */
					              
					               writer.flush();
								      writer.close();
				            
				            Path temp = Files.move(Paths.get("C:\\Users\\ABHAY\\Desktop\\itemlabels_"+sqlDate+""+formattedDate+".csv"), Paths.get("C:\\Users\\ABHAY\\Downloads\\itemlabels_"+sqlDate+""+formattedDate+".csv"));
					      
				            
					      
					      inputText.setText("");
					      sizeText.setText("");
					      qtyText.setText("");
					      inputText.requestFocus();
					      
		               
				      
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e1){
			      //Handle errors for Class.forName
			      e1.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			   path.setText("Transfer Complete");
			}
		});
		headerLabel.setBounds(150, 11, 400, 31);
		statusLabel.setBounds(180, 340, 312, 31);
		headerLabel.setText("Popnetic Warehousing System");
		
		controlPanel.add(path);
		controlPanel.add(transfer);
		controlPanel.add(back);
		
		controlPanel.add(inputText);
		controlPanel.add(styleCode);
		controlPanel.add(size);
		controlPanel.add(sizeText);
		controlPanel.add(qtyText);
		controlPanel.add(quantity);

	}
	
	
	//Barcode Tab Launch
	
	private void barcodeLaunch() {
		prepareGUI("Bulk Barcode Generator",700,398);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosingbarcodeLaunch(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		
		
		
		
		
		
		
		JButton openfile = new JButton("Upload");
		openfile.setForeground(SystemColor.desktop);
		openfile.setBackground(SystemColor.activeCaption);
		openfile.setBounds(150, 140, 94, 31);
		openfile.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		JButton transfer = new JButton("Generate");
		transfer.setForeground(SystemColor.desktop);
		transfer.setBackground(SystemColor.activeCaption);
		transfer.setBounds(450, 140, 94, 31);
		
		
		JButton back = new JButton("Back");
		back.setBounds(300, 300, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent barcodeLaunchEvent) {
				mainFrame.dispose();
				prepareGUI("Hanuman",532,450);
				
				
				mainFrame.addWindowListener(new WindowAdapter() {
					public void windowClosingOrder(WindowEvent windowEvent) {
						System.exit(0);
					}
				});
				
				launch();
			}
		});
		JLabel path = new JLabel("NO FILES Selected");
		path.setBounds(250, 260, 300, 31);
		
		
		
		openfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent openfileEvent) {
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
				 int r = j.showOpenDialog(null); 
				 if (r == JFileChooser.APPROVE_OPTION) 
					  
		            { 
		                // set the label to the path of the selected file 
		                path.setText(j.getSelectedFile().getAbsolutePath()); 
		            } 
		            // if the user cancelled the operation 
		            else
		                path.setText("the user cancelled the operation");
				
			}
			
			
			
		});
		
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent transferEvent) {
				
				Connection conn = null;
				PreparedStatement preparedstmt1;
				   Statement stmt = null;
				  
				   Date currentDatetime = new Date(System.currentTimeMillis());
				   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
				   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
				   DateFormat dateFormat = new SimpleDateFormat("HHmmss");
				    String formattedDate=dateFormat.format(timestamp);
				   try{
					 //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");

					      //STEP 3: Open a connection
					      System.out.println("Connecting to a selected database...");
					      conn = DriverManager.getConnection(DB_URL, USER, PASS);
					      System.out.println("Connected database successfully...");
					     // PreparedStatement pstm = null ;
					      
					      
					      //Reading the selected excel File
					     
					      FileInputStream input = new FileInputStream(path.getText());
					      System.out.println(input);
					      POIFSFileSystem fs = new POIFSFileSystem( input );
					      HSSFWorkbook wb = new HSSFWorkbook(fs);
					      HSSFSheet sheet = wb.getSheetAt(0);
					      Row row;
					      
					      XSSFWorkbook workbook = new XSSFWorkbook(); 
						     
					      XSSFSheet spreadsheet = workbook.createSheet("itemlabels");
					      XSSFRow row1 = spreadsheet.createRow(0);
					      XSSFCell cell;
					      FileWriter writer = null;
					      writer = new FileWriter("C:\\Users\\ABHAY\\Desktop\\itemlabels_"+sqlDate+""+formattedDate+".csv");
					      
					     
					      
					      
					      int j=1;
					      
					      writer.append("ItemCode");
					      writer.append(',');
					      writer.append("MfgBy");
					      writer.append(',');
					      writer.append("DOM");
					      writer.append(',');
					      writer.append("VendorSkuCode");
					      writer.append(',');
					      writer.append("ArticleType");
					      writer.append(',');
					      writer.append("MRP");
					      writer.append(',');
					      writer.append("ItemSize");
					      writer.append(',');
					      writer.append("ArticleColor");
					      writer.append(',');
					      writer.append("MFGAdd");
					      writer.append(',');
					      writer.append("BrandName");
					      writer.append('\n');
					      
					      
					      for(int i=1; i<=sheet.getLastRowNum(); i++){
				                row = sheet.getRow(i);
				                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				              String date= df.format(sqlDate);
				               DataFormatter formatter = new DataFormatter();
				               
				               //System.out.println(reportDate);
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
				               
				              
				               
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
				            //   Date orderedOn = Date.valueOf(orderedOnString);
				                //System.out.println(orderOn);
				                String sellerSkuCode = row.getCell(0).getStringCellValue();
				                String color = row.getCell(1).getStringCellValue();
				                String size = row.getCell(2).getStringCellValue();
				                int qty =(int) row.getCell(3).getNumericCellValue();
				                int lastbarcode=0;
				                Double unit=0.0;
				                int monthNumber=1;
				                int mrp=0 ;
				                int t=0;
				                String articleType="";
				                LocalDate currentDate
				                = LocalDate.parse(date);
				                Month month = currentDate.getMonth();
				                int year=currentDate.getYear();
				                
				                String sql11= "select mrp,articleType from barcode_style_info where articleNumber=?";
				                
				                PreparedStatement preparedstmt51 = conn.prepareStatement(sql11);
					               
				                preparedstmt51.setString(1, sellerSkuCode);
					               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
					               while(rscheckkey51.next()) {
					            	   mrp = rscheckkey51.getInt("mrp");
					            	   articleType=rscheckkey51.getString("articleType");
					            	   t=1;
					                }
					               String mrpstring= String.valueOf(mrp);
					               String monthString=String.valueOf(month);
					               String yearString=String.valueOf(year);
					               if((articleType.equalsIgnoreCase("Trousers"))||(articleType.equalsIgnoreCase("Shorts"))) {
					            	   if(size.equalsIgnoreCase("S")) {
						                	unit=66.04;
						                }else if(size.equalsIgnoreCase("M")) {
						                	unit=71.12;
						                }else if(size.equalsIgnoreCase("L")) {
						                	unit=76.20;
						                }else if(size.equalsIgnoreCase("XL")) {
						                	unit=81.28;
						                }else {
						                	unit=86.36;
						                }
					            	   }else {
					            		   if(size.equalsIgnoreCase("S")) {
							                	unit=86.36;
							                }else if(size.equalsIgnoreCase("M")) {
							                	unit=91.44;
							                }else if(size.equalsIgnoreCase("L")) {
							                	unit=96.52;
							                }else if(size.equalsIgnoreCase("XL")) {
							                	unit=101.6;
							                }else {
							                	unit=106.68;
							                }
					            	   }
					               
					               if(monthString.equalsIgnoreCase("January")) {
			            			   monthNumber=01;
			            		   }else if(monthString.equalsIgnoreCase("February")) {
			            			   monthNumber=02;
			            		   }else if(monthString.equalsIgnoreCase("March")) {
			            			   monthNumber=03;
			            		   }else if(monthString.equalsIgnoreCase("April")) {
			            			   monthNumber=04;
			            		   }else if(monthString.equalsIgnoreCase("May")) {
			            			   monthNumber=05;
			            		   }else if(monthString.equalsIgnoreCase("June")) {
			            			   monthNumber=06;
			            		   }else if(monthString.equalsIgnoreCase("July")) {
			            			   monthNumber=07;
			            		   }else if(monthString.equalsIgnoreCase("August")) {
			            			   monthNumber=8;
			            		   }else if(monthString.equalsIgnoreCase("September")) {
			            			   monthNumber=9;
			            		   }else if(monthString.equalsIgnoreCase("October")) {
			            			   monthNumber=10;
			            		   }else if(monthString.equalsIgnoreCase("November")) {
			            			   monthNumber=11;
			            		   }else {
			            			   monthNumber=12;
			            		   }
			            	   
			            	   String monthNumberString=String.valueOf(monthNumber);
					               
					               String unitString=String.valueOf(unit);
				               String sql1= "select barcode from barcode_style ORDER BY barcode DESC limit 1";
				                
				                PreparedStatement preparedstmt5 = conn.prepareStatement(sql1);
					               
					               
					               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
					               while(rscheckkey5.next()) {
					            	   lastbarcode = rscheckkey5.getInt("barcode");
					                }
					               lastbarcode=lastbarcode+1;
					               
					                
					          for(int k=1; k<=qty; k++) {
					        	  String lastbarcodeString = String.valueOf(lastbarcode) ;
							      writer.append(lastbarcodeString);
							      writer.append(',');
							      writer.append("Brand : Popnetic");
							      writer.append(',');
							      writer.append("Month Of MFG. :"+" "+monthNumberString+"/"+yearString);
							      writer.append(',');
							      writer.append("SKU ID :"+sellerSkuCode );
							      writer.append(',');
							      writer.append("Article Type:"+" "+ articleType);
							      writer.append(',');
							      writer.append(mrpstring);
							      writer.append(',');
							      writer.append("Size :" + size +"("+unitString+"cm)");
							      writer.append(',');
							      writer.append("Article Color :" +color);
							      writer.append(',');
							      writer.append("MFG By: Popnetic Fashions");
							      writer.append(',');
							      writer.append("D-355 Sector-10 Noida-201301");
							      writer.append('\n');
							      
							      j++;
									      
									      String sql = " INSERT ignore INTO barcode_style Values(?,?,?,?,?,?)";
									      PreparedStatement preparedstmt = conn.prepareStatement(sql);
									      
									      preparedstmt.setInt(1, lastbarcode);
									      preparedstmt.setString(2, sellerSkuCode);
									      preparedstmt.setString(3, size);
									      
									      preparedstmt.setString(4, "No");
									      preparedstmt.setString(5, "NA");
									      preparedstmt.setString(6, "NA");
									     
									      System.out.println("Imported Rows"+i);
									      
									      preparedstmt.execute(); 
									      lastbarcode++;
									      
									      
									      
					               }
					               
					               
				                
				                
				              /* String sql = " INSERT ignore INTO mdirect_daily_order_upload Values(?,?,?,?)";
							      PreparedStatement preparedstmt = conn.prepareStatement(sql);
							      
							      preparedstmt.setString(1, sellerSkuCode);
							      preparedstmt.setString(2, color);
							      preparedstmt.setString(3, size);
							      
							      preparedstmt.setInt(4, qty);
							     
							      System.out.println("Imported Rows"+i); */
					          
							      
				            }
					      
					      
					      writer.flush();
					      writer.close();
					      
					      Path temp = Files.move(Paths.get("C:\\Users\\ABHAY\\Desktop\\itemlabels_"+sqlDate+""+formattedDate+".csv"), Paths.get("C:\\Users\\ABHAY\\Downloads\\itemlabels_"+sqlDate+""+formattedDate+".csv"));
				      
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e1){
			      //Handle errors for Class.forName
			      e1.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			   path.setText("Transfer Complete");
			}
		});
		headerLabel.setBounds(150, 11, 400, 31);
		statusLabel.setBounds(180, 340, 312, 31);
		headerLabel.setText("Popnetic Warehousing System");
		controlPanel.add(openfile);
		controlPanel.add(path);
		controlPanel.add(transfer);
		controlPanel.add(back);
		

	}

	//Data Tab Launch
	
	private void dataLaunch() {
		prepareGUI("Data",700,398);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosinglimeroadLaunch(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		JCheckBox productionCheckBox = new JCheckBox("Commission");
		productionCheckBox.setBounds(70, 80, 110, 23);
		
		JCheckBox limeroadcodeCheckBox = new JCheckBox("State_City_Zone");
		limeroadcodeCheckBox.setBounds(230, 80, 120, 23);
		
		JCheckBox itemcodeCheckBox = new JCheckBox("SKU_Category");
		itemcodeCheckBox.setBounds(370, 80, 120, 23);
		
		JCheckBox myntraDiscount = new JCheckBox("Myntra_Discount");
		myntraDiscount.setBounds(520, 80, 140, 23);
		
		JCheckBox myntraInvoice = new JCheckBox("Myntra_Invoice");
		myntraInvoice.setBounds(100, 120, 140, 23);
		
		JCheckBox myntraSharing = new JCheckBox("Myntra_Discount_Sharing");
		myntraSharing.setBounds(250, 120, 175, 23);
		
		JCheckBox myntraSPF = new JCheckBox("Myntra SPF");
		myntraSPF.setBounds(440, 120, 95, 23);
		
		JCheckBox flipkartSPF = new JCheckBox("Flipkart SPF");
		flipkartSPF.setBounds(560, 120, 95, 23);
		
		JCheckBox inventoryUpload = new JCheckBox("Style Info");
		inventoryUpload.setBounds(270, 160, 120, 23);
		
		JCheckBox ajioInfo = new JCheckBox("AJio Info");
		ajioInfo.setBounds(400, 160, 120, 23);
		
		JCheckBox vendorlist = new JCheckBox("Vendor List");
		vendorlist.setBounds(550, 160, 120, 23);
		
		
		vendorlist.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ievendorCheckBoxTrue) {
				if(vendorlist.isSelected()) {
					itemcodeCheckBox.setEnabled(false);
					limeroadcodeCheckBox.setEnabled(false);
					myntraDiscount.setEnabled(false);
					myntraInvoice.setEnabled(false);
					myntraSharing.setEnabled(false);
					myntraSPF.setEnabled(false);
					flipkartSPF.setEnabled(false);
					inventoryUpload.setEnabled(false);
					productionCheckBox.setEnabled(false);
					ajioInfo.setEnabled(false);
					
				}else {
					limeroadcodeCheckBox.setEnabled(true);
					itemcodeCheckBox.setEnabled(true);
					myntraDiscount.setEnabled(true);
					myntraInvoice.setEnabled(true);
					myntraSharing.setEnabled(true);
					myntraSPF.setEnabled(true);
					flipkartSPF.setEnabled(true);
					inventoryUpload.setEnabled(true);
					productionCheckBox.setEnabled(true);
					ajioInfo.setEnabled(true);
				}
			}
		});
		
		ajioInfo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ieproductionCheckBoxTrue) {
				if(ajioInfo.isSelected()) {
					itemcodeCheckBox.setEnabled(false);
					limeroadcodeCheckBox.setEnabled(false);
					myntraDiscount.setEnabled(false);
					myntraInvoice.setEnabled(false);
					myntraSharing.setEnabled(false);
					myntraSPF.setEnabled(false);
					flipkartSPF.setEnabled(false);
					inventoryUpload.setEnabled(false);
					productionCheckBox.setEnabled(false);
					
				}else {
					limeroadcodeCheckBox.setEnabled(true);
					itemcodeCheckBox.setEnabled(true);
					myntraDiscount.setEnabled(true);
					myntraInvoice.setEnabled(true);
					myntraSharing.setEnabled(true);
					myntraSPF.setEnabled(true);
					flipkartSPF.setEnabled(true);
					inventoryUpload.setEnabled(true);
					productionCheckBox.setEnabled(true);
				}
			}
		});
		
		productionCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ieproductionCheckBoxTrue) {
				if(productionCheckBox.isSelected()) {
					itemcodeCheckBox.setEnabled(false);
					limeroadcodeCheckBox.setEnabled(false);
					myntraDiscount.setEnabled(false);
					myntraInvoice.setEnabled(false);
					myntraSharing.setEnabled(false);
					myntraSPF.setEnabled(false);
					flipkartSPF.setEnabled(false);
					inventoryUpload.setEnabled(false);
					ajioInfo.setEnabled(false);
					
				}else {
					limeroadcodeCheckBox.setEnabled(true);
					itemcodeCheckBox.setEnabled(true);
					myntraDiscount.setEnabled(true);
					myntraInvoice.setEnabled(true);
					myntraSharing.setEnabled(true);
					myntraSPF.setEnabled(true);
					flipkartSPF.setEnabled(true);
					inventoryUpload.setEnabled(true);
					ajioInfo.setEnabled(true);
				}
			}
		});
		
		itemcodeCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ieitemCheckBoxTrue) {
				if(itemcodeCheckBox.isSelected()) {
					productionCheckBox.setEnabled(false);
					limeroadcodeCheckBox.setEnabled(false);
					myntraDiscount.setEnabled(false);
					myntraInvoice.setEnabled(false);
					myntraSharing.setEnabled(false);
					myntraSPF.setEnabled(false);
					flipkartSPF.setEnabled(false);
					inventoryUpload.setEnabled(false);
					ajioInfo.setEnabled(false);
					
				}else {
					limeroadcodeCheckBox.setEnabled(true);
					productionCheckBox.setEnabled(true);
					myntraDiscount.setEnabled(true);
					myntraInvoice.setEnabled(true);
					myntraSharing.setEnabled(true);
					myntraSPF.setEnabled(true);
					flipkartSPF.setEnabled(true);
					inventoryUpload.setEnabled(true);
					ajioInfo.setEnabled(true);
				}
			}
		});
		
		limeroadcodeCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ielimeroadCheckBoxTrue) {
				if(limeroadcodeCheckBox.isSelected()) {
					productionCheckBox.setEnabled(false);
					itemcodeCheckBox.setEnabled(false);
					myntraDiscount.setEnabled(false);
					myntraInvoice.setEnabled(false);
					myntraSharing.setEnabled(false);
					myntraSPF.setEnabled(false);
					flipkartSPF.setEnabled(false);
					inventoryUpload.setEnabled(false);
					ajioInfo.setEnabled(false);
					
				}else {
					productionCheckBox.setEnabled(true);
					itemcodeCheckBox.setEnabled(true);
					myntraDiscount.setEnabled(true);
					myntraInvoice.setEnabled(true);
					myntraSharing.setEnabled(true);
					myntraSPF.setEnabled(true);
					flipkartSPF.setEnabled(true);
					inventoryUpload.setEnabled(true);
					ajioInfo.setEnabled(true);
				}
			}
		});
		
		myntraDiscount.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent iemyntraCheckBoxTrue) {
				if(myntraDiscount.isSelected()) {
					productionCheckBox.setEnabled(false);
					itemcodeCheckBox.setEnabled(false);
					limeroadcodeCheckBox.setEnabled(false);
					myntraInvoice.setEnabled(false);
					myntraSharing.setEnabled(false);
					myntraSPF.setEnabled(false);
					flipkartSPF.setEnabled(false);
					inventoryUpload.setEnabled(false);
					ajioInfo.setEnabled(false);
					
				}else {
					productionCheckBox.setEnabled(true);
					itemcodeCheckBox.setEnabled(true);
					limeroadcodeCheckBox.setEnabled(true);
					myntraInvoice.setEnabled(true);
					myntraSharing.setEnabled(true);
					myntraSPF.setEnabled(true);
					flipkartSPF.setEnabled(true);
					inventoryUpload.setEnabled(true);
					ajioInfo.setEnabled(true);
				}
			}
		});
		
		myntraInvoice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent iemyntraCheckBoxTrue) {
				if(myntraInvoice.isSelected()) {
					productionCheckBox.setEnabled(false);
					itemcodeCheckBox.setEnabled(false);
					limeroadcodeCheckBox.setEnabled(false);
					myntraDiscount.setEnabled(false);
					myntraSharing.setEnabled(false);
					myntraSPF.setEnabled(false);
					flipkartSPF.setEnabled(false);
					inventoryUpload.setEnabled(false);
					ajioInfo.setEnabled(false);
					
				}else {
					productionCheckBox.setEnabled(true);
					itemcodeCheckBox.setEnabled(true);
					limeroadcodeCheckBox.setEnabled(true);
					myntraDiscount.setEnabled(true);
					myntraSharing.setEnabled(true);
					myntraSPF.setEnabled(true);
					flipkartSPF.setEnabled(true);
					inventoryUpload.setEnabled(true);
					ajioInfo.setEnabled(true);
				}
			}
		});
		
		myntraSharing.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent iemyntraCheckBoxTrue) {
				if(myntraSharing.isSelected()) {
					productionCheckBox.setEnabled(false);
					itemcodeCheckBox.setEnabled(false);
					limeroadcodeCheckBox.setEnabled(false);
					myntraDiscount.setEnabled(false);
					myntraInvoice.setEnabled(false);
					myntraSPF.setEnabled(false);
					flipkartSPF.setEnabled(false);
					inventoryUpload.setEnabled(false);
					ajioInfo.setEnabled(false);
					
				}else {
					productionCheckBox.setEnabled(true);
					itemcodeCheckBox.setEnabled(true);
					limeroadcodeCheckBox.setEnabled(true);
					myntraDiscount.setEnabled(true);
					myntraInvoice.setEnabled(true);
					myntraSPF.setEnabled(true);
					flipkartSPF.setEnabled(true);
					inventoryUpload.setEnabled(true);
					ajioInfo.setEnabled(true);
				}
			}
		});
		
		
			myntraSPF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent iemyntraSPFCheckBoxTrue) {
				if(myntraSPF.isSelected()) {
					productionCheckBox.setEnabled(false);
					itemcodeCheckBox.setEnabled(false);
					limeroadcodeCheckBox.setEnabled(false);
					myntraDiscount.setEnabled(false);
					myntraInvoice.setEnabled(false);
					myntraSharing.setEnabled(false);
					flipkartSPF.setEnabled(false);
					inventoryUpload.setEnabled(false);
					ajioInfo.setEnabled(false);
					
				}else {
					productionCheckBox.setEnabled(true);
					itemcodeCheckBox.setEnabled(true);
					limeroadcodeCheckBox.setEnabled(true);
					myntraDiscount.setEnabled(true);
					myntraInvoice.setEnabled(true);
					
					myntraSharing.setEnabled(true);
					flipkartSPF.setEnabled(true);
					inventoryUpload.setEnabled(true);
					ajioInfo.setEnabled(true);
				}
			}
		});
		
			
			flipkartSPF.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ieflipkartSPFCheckBoxTrue) {
					if(flipkartSPF.isSelected()) {
						productionCheckBox.setEnabled(false);
						itemcodeCheckBox.setEnabled(false);
						limeroadcodeCheckBox.setEnabled(false);
						myntraDiscount.setEnabled(false);
						myntraInvoice.setEnabled(false);
						myntraSharing.setEnabled(false);
						myntraSPF.setEnabled(false);
						inventoryUpload.setEnabled(false);
						ajioInfo.setEnabled(false);
						
					}else {
						productionCheckBox.setEnabled(true);
						itemcodeCheckBox.setEnabled(true);
						limeroadcodeCheckBox.setEnabled(true);
						myntraDiscount.setEnabled(true);
						myntraInvoice.setEnabled(true);
						ajioInfo.setEnabled(true);
						myntraSharing.setEnabled(true);
						myntraSPF.setEnabled(true);
						inventoryUpload.setEnabled(true);
					}
				}
			});
			
			inventoryUpload.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ieinventoryUploadCheckBoxTrue) {
					if(inventoryUpload.isSelected()) {
						productionCheckBox.setEnabled(false);
						itemcodeCheckBox.setEnabled(false);
						limeroadcodeCheckBox.setEnabled(false);
						myntraDiscount.setEnabled(false);
						myntraInvoice.setEnabled(false);
						myntraSharing.setEnabled(false);
						myntraSPF.setEnabled(false);
						ajioInfo.setEnabled(false);
						flipkartSPF.setEnabled(false);
						
					}else {
						productionCheckBox.setEnabled(true);
						itemcodeCheckBox.setEnabled(true);
						limeroadcodeCheckBox.setEnabled(true);
						myntraDiscount.setEnabled(true);
						myntraInvoice.setEnabled(true);
						ajioInfo.setEnabled(true);
						myntraSharing.setEnabled(true);
						myntraSPF.setEnabled(true);
						flipkartSPF.setEnabled(true);
					}
				}
			});
			
		JButton openfile = new JButton("Open");
		openfile.setForeground(SystemColor.desktop);
		openfile.setBackground(SystemColor.activeCaption);
		openfile.setBounds(150, 210, 94, 31);
		openfile.setHorizontalAlignment(SwingConstants.LEFT);
		
		JButton transfer = new JButton("Transfer");
		transfer.setForeground(SystemColor.desktop);
		transfer.setBackground(SystemColor.activeCaption);
		transfer.setBounds(450, 210, 94, 31);
		
		
		JButton back = new JButton("Back");
		back.setBounds(300, 300, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent backOrderLaunchEvent) {
				mainFrame.dispose();
				prepareGUI("Hanuman",532,450);
				
				
				mainFrame.addWindowListener(new WindowAdapter() {
					public void windowClosingOrder(WindowEvent windowEvent) {
						System.exit(0);
					}
				});
				
				launch();
			}
		});
		JLabel path = new JLabel("NO FILES Selected");
		path.setBounds(250, 260, 300, 31);
		
		openfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent openfileEvent) {
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
				 int r = j.showOpenDialog(null); 
				 if (r == JFileChooser.APPROVE_OPTION) 
					  
		            { 
		                // set the label to the path of the selected file 
		                path.setText(j.getSelectedFile().getAbsolutePath()); 
		            } 
		            // if the user cancelled the operation 
		            else
		                path.setText("the user cancelled the operation");
				
			}
			
			
			
		});
		
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent transferEvent) {
				
				Connection conn = null;
				
				   Statement stmt = null;
				   Date currentDatetime = new Date(System.currentTimeMillis());
				   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
				   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
				   DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
				    String formattedDate=dateFormat.format(timestamp);
				   try{
					 //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");

					      //STEP 3: Open a connection
					      System.out.println("Connecting to a selected database...");
					      conn = DriverManager.getConnection(DB_URL, USER, PASS);
					      System.out.println("Connected database successfully...");
					     // PreparedStatement pstm = null ;
					      
					      
					      //Reading the selected excel File
					     
					      FileInputStream input = new FileInputStream(path.getText());
					      System.out.println(input);
					      POIFSFileSystem fs = new POIFSFileSystem( input );
					      HSSFWorkbook wb = new HSSFWorkbook(fs);
					      HSSFSheet sheet = wb.getSheetAt(0);
					      Row row;
					      for(int i=1; i<=sheet.getLastRowNum(); i++){
				                row = sheet.getRow(i);
				               DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				                DataFormatter formatter = new DataFormatter();
				               
				               //System.out.println(reportDate);
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
				               
				              
				               
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
				            //   Date orderedOn = Date.valueOf(orderedOnString);
				                //System.out.println(orderOn);
				                if(productionCheckBox.isSelected()) {
					                
						            String level   = formatter.formatCellValue(row.getCell(0));
						            
						            String category    = formatter.formatCellValue(row.getCell(1));
						            
						            Double commission       = (Double) row.getCell(2).getNumericCellValue();
						            
						           String uniqueKey = level+category;
						          
						           	String sql2 ="select commission_rate from flipkart_commission where uniqueKey=?";
						           	PreparedStatement preparedstmt2 = conn.prepareStatement(sql2);
						               preparedstmt2.setString(1, uniqueKey);
						               
						               ResultSet rscheckkey = preparedstmt2.executeQuery();
						               while(rscheckkey.next()) {
							            	String sql = "update ignore flipkart_commission set commission_rate = ? where uniqueKey=?";
							            	PreparedStatement preparedstmt = conn.prepareStatement(sql);
										      
										      
										      preparedstmt.setDouble(1, commission );
										      preparedstmt.setString(2, uniqueKey);
										      preparedstmt.execute(); 
							            }
					        			                
						            String sql = " INSERT ignore INTO flipkart_commission values(?,?,?,?)";
								      PreparedStatement preparedstmt = conn.prepareStatement(sql);
								      
								      preparedstmt.setString(1, uniqueKey);
								      preparedstmt.setString(2, level );
								      preparedstmt.setString(3, category );
								      preparedstmt.setDouble(4, commission );
								      
								      preparedstmt.execute(); 
								      
					              
					                
					               
					                System.out.println("Import rows "+i);
					                }else if(limeroadcodeCheckBox.isSelected()) {
					                	 String city   = formatter.formatCellValue(row.getCell(0));
								            
								            String state    = formatter.formatCellValue(row.getCell(1));
								            
								            String shippingZone     = formatter.formatCellValue(row.getCell(2));
								            
								           
							        			                
								            String sql = " INSERT ignore INTO shippingZone values(?,?,?)";
										      PreparedStatement preparedstmt = conn.prepareStatement(sql);
										      
										      
										      preparedstmt.setString(1, city );
										      preparedstmt.setString(2, state );
										      preparedstmt.setString(3, shippingZone );
										      
										      preparedstmt.execute(); 
										      
							              
							                
							               
							                System.out.println("Import rows "+i);
					                }else if(itemcodeCheckBox.isSelected()) {
					                	 String sku   = formatter.formatCellValue(row.getCell(0));
								            
								            String category    = formatter.formatCellValue(row.getCell(1));
								            					           
								            		          
							        		               
								            String sql = " INSERT ignore INTO sku_category_mapping values(?,?)";
										      PreparedStatement preparedstmt = conn.prepareStatement(sql);
										      
										      preparedstmt.setString(1, sku);
										  
										      preparedstmt.setString(2, category );
										      
										      preparedstmt.execute(); 
										      						              				                
							                  System.out.println("Import rows "+i);
					                }else if(myntraDiscount.isSelected()){
					                	
				                
				                
					            String styleID   = formatter.formatCellValue(row.getCell(0));
					            System.out.println(styleID);
					            Double discount       = (Double) row.getCell(1).getNumericCellValue();
					            System.out.println(discount);
					            String sku    = formatter.formatCellValue(row.getCell(2));
					            System.out.println(sku);
					            
					            java.util.Date fromDateOn  =  row.getCell(3).getDateCellValue();
					            String fromDate  = df.format(fromDateOn);
					            System.out.println(fromDate);
					            java.util.Date toDateOn  =  row.getCell(4).getDateCellValue();
					            String toDate  = df.format(toDateOn);
					           
					            System.out.println(toDate);
					           
					            String sql2 = "select discount from myntra_current_discount where sku= ?";
					            PreparedStatement preparedstmt2 = conn.prepareStatement(sql2);
					               preparedstmt2.setString(1, sku);
					               
					               ResultSet rscheckkey = preparedstmt2.executeQuery();
					               if(rscheckkey.next()) {
					            	   String sql ="update ignore myntra_current_discount set discount=? where sku=?";
					            	   PreparedStatement preparedstmt = conn.prepareStatement(sql);
					            	   preparedstmt.setDouble(1, discount );
									      preparedstmt.setString(2, sku);
									      preparedstmt.execute(); 
					               }else {
				        			                
					            String sql = " INSERT ignore INTO myntra_current_discount values(?,?,?)";
							      PreparedStatement preparedstmt = conn.prepareStatement(sql);
							      
							      preparedstmt.setString(1, styleID);
							      preparedstmt.setString(2, sku );
							      preparedstmt.setDouble(3, discount ); 
							      preparedstmt.execute(); 
					               }
							      
							      String sql1= " Insert ignore into myntra_historical_discount values(?,?,?,?,?,?,?)";
							      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
							      preparedstmt1.setString(1, styleID);
							      preparedstmt1.setString(2, sku);
							      preparedstmt1.setDouble(3, discount);
							      preparedstmt1.setString(4, fromDate);
							      preparedstmt1.setString(5, toDate);
							      preparedstmt1.setDate(6, sqlDate);
							      preparedstmt1.setTimestamp(7, timestamp);
							      
							     
							      preparedstmt1.execute(); 
				                
				               
				                System.out.println("Import rows "+i);
				            }else if(myntraInvoice.isSelected()) {
				            	 String myntraSku   = formatter.formatCellValue(row.getCell(1));
				            	 
				            	 String brandStyle   = formatter.formatCellValue(row.getCell(2));
						            
				            	 int hsnCode =(int) row.getCell(3).getNumericCellValue();  
				            	 
				            	 String color   = formatter.formatCellValue(row.getCell(4));
				            	 
				            	 String size   = formatter.formatCellValue(row.getCell(5));
				            	 
				            	 int qty =(int) row.getCell(6).getNumericCellValue(); 
				            	 
				            	 Double mrp       = (Double) row.getCell(7).getNumericCellValue();
						            
				            	 Double unitPrice       = (Double) row.getCell(8).getNumericCellValue();
				            	 
				            	 Double igst       = (Double) row.getCell(9).getNumericCellValue();
				            	 
				            	 Double total       = (Double) row.getCell(10).getNumericCellValue();
				            	 
				            	 String invoiceNumber   = formatter.formatCellValue(row.getCell(11));
				            	 
				            	 java.util.Date invoiceDateOn  =  row.getCell(12).getDateCellValue();
						            String invoiceDate  = df.format(invoiceDateOn);
						            
						            String sql = " INSERT ignore INTO tally_myntra_invoice values(?,?,?,?,?,?,?,?,?,?,?,?)";
								      PreparedStatement preparedstmt = conn.prepareStatement(sql);
								      
								      preparedstmt.setString(1, myntraSku);
								      preparedstmt.setString(2, brandStyle );
								      preparedstmt.setInt(3, hsnCode ); 
								      preparedstmt.setString(4, color);
								      preparedstmt.setString(5, size );
								      preparedstmt.setInt(6, qty ); 
								      preparedstmt.setDouble(7, mrp ); 
								      preparedstmt.setDouble(8, unitPrice );
								      preparedstmt.setDouble(9, igst );
								      preparedstmt.setDouble(10, total );
								      preparedstmt.setString(11, invoiceNumber );
								      preparedstmt.setString(12, invoiceDate );
								      
								      preparedstmt.execute(); 
								      System.out.println("Import rows "+i);
						            
				            }else if(myntraSharing.isSelected()) {
				            	
				            	
					            
					            
				            	
				            	String coreItemId   = formatter.formatCellValue(row.getCell(4));
				            	String status   = formatter.formatCellValue(row.getCell(26));
				            	String uniqueKey="";
				            	uniqueKey=coreItemId+status;
				            	String sql5= "select uniqueKey from tally_discount_sharing where uniqueKey=?";
					            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
					               preparedstmt5.setString(1, uniqueKey);
					               String uniqueKey2="";
					               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
					               while(rscheckkey5.next()) {
					            	   uniqueKey2 = rscheckkey5.getString("uniqueKey");
					               }
					               if(uniqueKey2.equalsIgnoreCase(uniqueKey)) {
					            	   System.out.println("No Need of Operations");
					               }else {
				            	
					               java.util.Date monthOfYearOn  =  row.getCell(54).getDateCellValue();
						            String monthOfYear  = df.format(monthOfYearOn);
					               
				            	String styleID   = formatter.formatCellValue(row.getCell(9));
				            	
				            	String saleOrderCode   = formatter.formatCellValue(row.getCell(11));
				            	
				            	String articleType   = formatter.formatCellValue(row.getCell(12));
				            	
				            	String vendorArticleNumber   = formatter.formatCellValue(row.getCell(13));
				            	
				            	String discountID   = formatter.formatCellValue(row.getCell(17));
				            	
				            	String poCode   = formatter.formatCellValue(row.getCell(18));
				            	
				            	String invoiceNumber   = formatter.formatCellValue(row.getCell(19));
				            	
				            	String skuCode   = formatter.formatCellValue(row.getCell(20));
				            	
				            	java.util.Date orderPackedOn  =  row.getCell(23).getDateCellValue();
					            String orderPackedDate  = df.format(orderPackedOn);
				            	
					            java.util.Date orderCreatedDateOn  =  row.getCell(24).getDateCellValue();
					            String orderCreatedDate  = df.format(orderCreatedDateOn);
				            	 
					            java.util.Date transactionDateOn  =  row.getCell(25).getDateCellValue();
					            String transactionDate  = df.format(transactionDateOn);
					            
					            
					            
					            
				            	int qty =(int) row.getCell(27).getNumericCellValue();  
				            	 
				            	Double poMrp       = (Double) row.getCell(28).getNumericCellValue();
				            	 
				            	Double poListPrice       = (Double) row.getCell(31).getNumericCellValue();
				            	 
				            	Double poCostPrice       = (Double) row.getCell(32).getNumericCellValue();
				            	
				            	Double vendorFunding       = (Double) row.getCell(33).getNumericCellValue();
				            	
				            	Double vendorDiscount       = (Double) row.getCell(34).getNumericCellValue();
				            	 
				            	Double netSales       = (Double) row.getCell(35).getNumericCellValue();
				            	
				            	Double gstOnNetSales       = (Double) row.getCell(36).getNumericCellValue();
				            	
				            	Double gstTax       = (Double) row.getCell(37).getNumericCellValue();
				            	
				            	Double listprice       = (Double) row.getCell(38).getNumericCellValue();
				            	
				            	Double newLandingPrice       = (Double) row.getCell(39).getNumericCellValue();
				            	
				            	Double margin       = (Double) row.getCell(40).getNumericCellValue();
				            	
				            	Double marginAmount       = (Double) row.getCell(41).getNumericCellValue();
				            	
				            	Double minMargin       = (Double) row.getCell(42).getNumericCellValue();
				            	
				            	Double minMarginDebit       = (Double) row.getCell(43).getNumericCellValue();
				            	
				            	Double gstOnMinMargin       = (Double) row.getCell(44).getNumericCellValue();
				            	
				            	Double totalMinMargin       = (Double) row.getCell(45).getNumericCellValue();
				            	
				            	Double discountDebitWithoutTax       = (Double) row.getCell(46).getNumericCellValue();
				            	
				            	Double igstTaxRate       = (Double) row.getCell(47).getNumericCellValue();
				            	
				            	Double igstTaxInput       = (Double) row.getCell(50).getNumericCellValue();
				            	
				            	Double totalDiscountDebit       = (Double) row.getCell(53).getNumericCellValue();
				            	
				            	
				            	
				            	
						            String sql = " INSERT ignore INTO tally_discount_sharing values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
								      PreparedStatement preparedstmt = conn.prepareStatement(sql);
								      
								      preparedstmt.setString(1, uniqueKey);
								      preparedstmt.setString(2, monthOfYear );
								      preparedstmt.setString(3, coreItemId );
								      
								      preparedstmt.setString(4, styleID);
								      preparedstmt.setString(5, saleOrderCode );
								      preparedstmt.setString(6, articleType ); 
								      preparedstmt.setString(7, vendorArticleNumber ); 
								      preparedstmt.setString(8, discountID );
								      preparedstmt.setString(9, poCode );
								      preparedstmt.setString(10, invoiceNumber );
								      preparedstmt.setString(11, skuCode );
								      preparedstmt.setString(12, orderPackedDate );
								      
								      preparedstmt.setString(13, orderCreatedDate ); 
								      preparedstmt.setString(14, transactionDate );
								      preparedstmt.setString(15, status );
								      preparedstmt.setInt(16, qty );
								      preparedstmt.setDouble(17, poMrp );
								      preparedstmt.setDouble(18, poListPrice );
								      
								      preparedstmt.setDouble(19, poCostPrice );
								      preparedstmt.setDouble(20, vendorFunding );
								      
								      preparedstmt.setDouble(21, vendorDiscount );
								      preparedstmt.setDouble(22, netSales );
								      
								      preparedstmt.setDouble(23, gstOnNetSales );
								      preparedstmt.setDouble(24, gstTax );
								      
								      preparedstmt.setDouble(25, listprice );
								      preparedstmt.setDouble(26, newLandingPrice );
								      
								      preparedstmt.setDouble(27, margin );
								      preparedstmt.setDouble(28, marginAmount );
								      
								      preparedstmt.setDouble(29, minMargin );
								      preparedstmt.setDouble(30, minMarginDebit );
								      
								      preparedstmt.setDouble(31, gstOnMinMargin );
								      preparedstmt.setDouble(32, totalMinMargin );
								      
								      preparedstmt.setDouble(33, discountDebitWithoutTax );
								      preparedstmt.setDouble(34, igstTaxRate );
								      
								      preparedstmt.setDouble(35, igstTaxInput );
								      preparedstmt.setDouble(36, totalDiscountDebit );
								      
								      
								      preparedstmt.execute(); 
								      
								      
								      if(status.equalsIgnoreCase("Sales")) {
									      String sql1 = "UPDATE IGNORE myntra_order set fwdPaymentStatus=?,fwdSettleValue=fwdSettleValue+? ,settleValue=settleValue+?,reconcillationStatus=? where itemCode=?";
									      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
									      preparedstmt1.setString(1, "Yes");
									      preparedstmt1.setDouble(2, poCostPrice-totalDiscountDebit);
									      preparedstmt1.setDouble(3, poCostPrice-totalDiscountDebit);
									      preparedstmt1.setString(4,"No" );
									      preparedstmt1.setString(5,coreItemId );
									      
									      preparedstmt1.execute();
									     
									      }else if(status.equalsIgnoreCase("Return")) {
									    	  String sql1 = "UPDATE IGNORE myntra_order set revPaymentStatus=?,revSettleValue=revSettleValue+? ,settleValue=settleValue+?,reconcillationStatus=? where itemCode=?";
										      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
										      preparedstmt1.setString(1, "Yes");
										      preparedstmt1.setDouble(2, poCostPrice-totalDiscountDebit);
										      preparedstmt1.setDouble(3, poCostPrice-totalDiscountDebit);
										      preparedstmt1.setString(4,"No" );
										      preparedstmt1.setString(5,coreItemId );
										      preparedstmt1.execute();
										      System.out.println("Import rows "+i);
									      }else {
									    	  System.out.println("Neither Sale nor Return");
									      }
						            
				            }
				            }else if(myntraSPF.isSelected()) {
				            	
				            	/*DelayedReturn------DEL
				            	UndeliveredReturn------UND
				            	IncorrectPayment-------INC
				            	UnpaidOrders-----------UNP
				            	Rejected Orders-------REJ
				            	UTR Received--------UTR*/
				            	
				            	String coreItemId   = formatter.formatCellValue(row.getCell(0));
				            	String status   = formatter.formatCellValue(row.getCell(1));
				            	
				            	
				            	String uniqueKey="UTR";
				            	
				            	
				            	String sql5= "select SPFStatus from myntra_ppmp_order where orderReleaseId=?";
					            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
					               preparedstmt5.setString(1, coreItemId);
					               String uniqueKey2="";
					               String uniqueKey3="Rej";
					              
					               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
					               while(rscheckkey5.next()) {
					            	   uniqueKey2 = rscheckkey5.getString("SPFStatus");
					               }
					               if(uniqueKey2.equalsIgnoreCase(uniqueKey)) {
					            	   System.out.println("No Need of Operations,UTR alreaady received");
					               } else if(uniqueKey2.equalsIgnoreCase(uniqueKey3)){
					            	   System.out.println("Claim Rejected");
					               }else if(uniqueKey2.equalsIgnoreCase(status)) {
					            	   System.out.println("Already Updated with same Status");
					               }
					               else if(uniqueKey2.equalsIgnoreCase("NA")) {
					            	   String ticketID   = formatter.formatCellValue(row.getCell(2));
					            	   String sql1 = "UPDATE IGNORE myntra_ppmp_order set SPFStatus=?,reconcillationStatus=?,ticketID=? where orderReleaseId=?";
									      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
									      
									      preparedstmt1.setString(1,status );
									      preparedstmt1.setString(2,"Yes" );
									      preparedstmt1.setString(3,ticketID );
									      preparedstmt1.setString(4,coreItemId );
									      preparedstmt1.execute();
									      
					               }else {
					            	   Double amount       = (Double) row.getCell(3).getNumericCellValue();
					            	   System.out.println(amount);
					            	   String ticketID   = formatter.formatCellValue(row.getCell(2));
					            	   String sql1 = "UPDATE IGNORE myntra_ppmp_order set fwdSettleValue=fwdSettleValue+?,settleValue=settleValue+?,SPFStatus=?,ticketId=? where orderReleaseId=?";
									      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
									      preparedstmt1.setDouble(1, amount);
									      preparedstmt1.setDouble(2, amount);
									     
									      preparedstmt1.setString(3,status );
									      preparedstmt1.setString(4,ticketID );
									      preparedstmt1.setString(5,coreItemId );
									      preparedstmt1.execute();
					               }
				            	
				            }else if(inventoryUpload.isSelected()) {
				            	
				            	String brandName   = row.getCell(0).getStringCellValue();
				            	String masterCategory   = row.getCell(1).getStringCellValue();
				            	String subCategory   = row.getCell(2).getStringCellValue();
				            	String articleType   = row.getCell(3).getStringCellValue();
				            	String articleNumber   = row.getCell(4).getStringCellValue();
				            	String color   = row.getCell(5).getStringCellValue();
				            	int styleID =(int) row.getCell(6).getNumericCellValue();
				            	String websiteLink =row.getCell(7).getStringCellValue();
				            	int fabricCount =(int) row.getCell(14).getNumericCellValue();
				            	int mrp =(int) row.getCell(8).getNumericCellValue();
				            	String fabricType =row.getCell(15).getStringCellValue();
				            	if(fabricCount==1) {
				            	String FabricCode1=row.getCell(16).getStringCellValue();
				            	
				            	String sql1 = "insert IGNORE barcode_style_info values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
							      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
							      
							      preparedstmt1.setString(1, brandName);
							      preparedstmt1.setString(2, masterCategory);
							      
							      preparedstmt1.setString(3,subCategory );
							      preparedstmt1.setString(4,articleType );
							      preparedstmt1.setString(5,articleNumber );
							      preparedstmt1.setString(6,color );
							      preparedstmt1.setInt(7,styleID );
							      preparedstmt1.setString(8,websiteLink );
								  preparedstmt1.setInt(9,mrp );
								  preparedstmt1.setInt(10, fabricCount);
								  preparedstmt1.setString(11, fabricType);
								  preparedstmt1.setString(12, FabricCode1);
								  preparedstmt1.setString(13, "No");
								  preparedstmt1.setString(14, "No");
								  preparedstmt1.setString(15, "No");
								  
								  
								  String sql11 = "insert IGNORE fabric_inventory values(?,?,?,?) ";
								  PreparedStatement preparedstmt11 = conn.prepareStatement(sql11);
								  preparedstmt11.setString(1, FabricCode1);
							      preparedstmt11.setInt(2, 0);
							      
							      preparedstmt11.setDate(3,sqlDate );
							      preparedstmt11.setString(4,formattedDate );
							      
							      preparedstmt11.execute();
								 
							      preparedstmt1.execute();
				            	
				            	}else if(fabricCount==2) {
				            		String FabricCode1=row.getCell(16).getStringCellValue();
				            		String FabricCode2=row.getCell(17).getStringCellValue();
				            		
				            		String sql1 = "insert IGNORE barcode_style_info values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
								      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
								      
								      preparedstmt1.setString(1, brandName);
								      preparedstmt1.setString(2, masterCategory);
								      
								      preparedstmt1.setString(3,subCategory );
								      preparedstmt1.setString(4,articleType );
								      preparedstmt1.setString(5,articleNumber );
								      preparedstmt1.setString(6,color );
								      preparedstmt1.setInt(7,styleID );
								      preparedstmt1.setString(8,websiteLink );
									  preparedstmt1.setInt(9,mrp );
									  preparedstmt1.setInt(10, fabricCount);
									  preparedstmt1.setString(11, fabricType);
									  preparedstmt1.setString(12, FabricCode1);
									  preparedstmt1.setString(13, FabricCode2);
									  preparedstmt1.setString(14, "No");
									  preparedstmt1.setString(15, "No");
									  
									  
									  String sql11 = "insert IGNORE fabric_inventory values(?,?,?,?) ";
									  PreparedStatement preparedstmt11 = conn.prepareStatement(sql11);
									  preparedstmt11.setString(1, FabricCode1);
								      preparedstmt11.setInt(2, 0);
								      
								      preparedstmt11.setDate(3,sqlDate );
								      preparedstmt11.setString(4,formattedDate );
								      
								      preparedstmt11.execute();
								      
								      String sql112 = "insert IGNORE fabric_inventory values(?,?,?,?) ";
									  PreparedStatement preparedstmt112 = conn.prepareStatement(sql112);
									  preparedstmt112.setString(1, FabricCode2);
								      preparedstmt112.setInt(2, 0);
								      
								      preparedstmt112.setDate(3,sqlDate );
								      preparedstmt112.setString(4,formattedDate );
								      
								      preparedstmt112.execute();
									 
								      preparedstmt1.execute();
				            	}else if(fabricCount==3) {
				            		String FabricCode1=row.getCell(16).getStringCellValue();
				            		String FabricCode2=row.getCell(17).getStringCellValue();
				            		String FabricCode3=row.getCell(18).getStringCellValue();
				            		
				            		String sql1 = "insert IGNORE barcode_style_info values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
								      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
								      
								      preparedstmt1.setString(1, brandName);
								      preparedstmt1.setString(2, masterCategory);
								      
								      preparedstmt1.setString(3,subCategory );
								      preparedstmt1.setString(4,articleType );
								      preparedstmt1.setString(5,articleNumber );
								      preparedstmt1.setString(6,color );
								      preparedstmt1.setInt(7,styleID );
								      preparedstmt1.setString(8,websiteLink );
									  preparedstmt1.setInt(9,mrp );
									  preparedstmt1.setInt(10, fabricCount);
									  preparedstmt1.setString(11, fabricType);
									  preparedstmt1.setString(12, FabricCode1);
									  preparedstmt1.setString(13, FabricCode2);
									  preparedstmt1.setString(14, FabricCode3);
									  preparedstmt1.setString(15, "No");
									  
									  
									  String sql11 = "insert IGNORE fabric_inventory values(?,?,?,?) ";
									  PreparedStatement preparedstmt11 = conn.prepareStatement(sql11);
									  preparedstmt11.setString(1, FabricCode1);
								      preparedstmt11.setInt(2, 0);
								      
								      preparedstmt11.setDate(3,sqlDate );
								      preparedstmt11.setString(4,formattedDate );
								      
								      preparedstmt11.execute();
								      
								      String sql112 = "insert IGNORE fabric_inventory values(?,?,?,?) ";
									  PreparedStatement preparedstmt112 = conn.prepareStatement(sql112);
									  preparedstmt112.setString(1, FabricCode2);
								      preparedstmt112.setInt(2, 0);
								      
								      preparedstmt112.setDate(3,sqlDate );
								      preparedstmt112.setString(4,formattedDate );
								      
								      preparedstmt112.execute();
								      
								      String sql113 = "insert IGNORE fabric_inventory values(?,?,?,?) ";
									  PreparedStatement preparedstmt113 = conn.prepareStatement(sql113);
									  preparedstmt113.setString(1, FabricCode3);
								      preparedstmt113.setInt(2, 0);
								      
								      preparedstmt113.setDate(3,sqlDate );
								      preparedstmt113.setString(4,formattedDate );
								      
								      preparedstmt113.execute();
									 
								      preparedstmt1.execute();
				            	}else {
				            		String FabricCode1=row.getCell(16).getStringCellValue();
				            		String FabricCode2=row.getCell(17).getStringCellValue();
				            		String FabricCode3=row.getCell(18).getStringCellValue();
				            		String FabricCode4=row.getCell(19).getStringCellValue();
				            		
				            		String sql1 = "insert IGNORE barcode_style_info values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
								      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
								      
								      preparedstmt1.setString(1, brandName);
								      preparedstmt1.setString(2, masterCategory);
								      
								      preparedstmt1.setString(3,subCategory );
								      preparedstmt1.setString(4,articleType );
								      preparedstmt1.setString(5,articleNumber );
								      preparedstmt1.setString(6,color );
								      preparedstmt1.setInt(7,styleID );
								      preparedstmt1.setString(8,websiteLink );
									  preparedstmt1.setInt(9,mrp );
									  preparedstmt1.setInt(10, fabricCount);
									  preparedstmt1.setString(11, fabricType);
									  preparedstmt1.setString(12, FabricCode1);
									  preparedstmt1.setString(13, FabricCode2);
									  preparedstmt1.setString(14, FabricCode3);
									  preparedstmt1.setString(15, FabricCode4);
									  
									  
									  String sql11 = "insert IGNORE fabric_inventory values(?,?,?,?) ";
									  PreparedStatement preparedstmt11 = conn.prepareStatement(sql11);
									  preparedstmt11.setString(1, FabricCode1);
								      preparedstmt11.setInt(2, 0);
								      
								      preparedstmt11.setDate(3,sqlDate );
								      preparedstmt11.setString(4,formattedDate );
								      
								      preparedstmt11.execute();
								      
								      String sql112 = "insert IGNORE fabric_inventory values(?,?,?,?) ";
									  PreparedStatement preparedstmt112 = conn.prepareStatement(sql112);
									  preparedstmt112.setString(1, FabricCode2);
								      preparedstmt112.setInt(2, 0);
								      
								      preparedstmt112.setDate(3,sqlDate );
								      preparedstmt112.setString(4,formattedDate );
								      
								      preparedstmt112.execute();
								      
								      String sql113 = "insert IGNORE fabric_inventory values(?,?,?,?) ";
									  PreparedStatement preparedstmt113 = conn.prepareStatement(sql113);
									  preparedstmt113.setString(1, FabricCode3);
								      preparedstmt113.setInt(2, 0);
								      
								      preparedstmt113.setDate(3,sqlDate );
								      preparedstmt113.setString(4,formattedDate );
								      
								      preparedstmt113.execute();
								      
								      String sql114 = "insert IGNORE fabric_inventory values(?,?,?,?) ";
									  PreparedStatement preparedstmt114 = conn.prepareStatement(sql114);
									  preparedstmt114.setString(1, FabricCode4);
								      preparedstmt114.setInt(2, 0);
								      
								      preparedstmt114.setDate(3,sqlDate );
								      preparedstmt114.setString(4,formattedDate );
								      
								      preparedstmt114.execute();
									 
								      preparedstmt1.execute();
				            	}
				            	
				            	
				          
				            	
							      
							      
				            }else if(ajioInfo.isSelected()) {
				            	String jioCode   = row.getCell(0).getStringCellValue();
				            	String ean   = row.getCell(1).getStringCellValue();
				            	String sellerStyleCode   = row.getCell(2).getStringCellValue();
				            	String sellerSKuCode   = row.getCell(3).getStringCellValue();
				            	String size   = row.getCell(4).getStringCellValue();
				            	String color   = row.getCell(5).getStringCellValue();
				            	String hsn   = row.getCell(6).getStringCellValue();
				            	
				            	
				            	String mrp =row.getCell(7).getStringCellValue();
				            	 
				            	
				            	
				            	String sql1 = "insert IGNORE ajioLookUp values(?,?,?,?,?,?,?,?) ";
							      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
							      preparedstmt1.setString(1, ean);
							      preparedstmt1.setString(2, jioCode);
							      
							      preparedstmt1.setString(3,sellerSKuCode );
							      preparedstmt1.setString(4,sellerStyleCode );
							      preparedstmt1.setString(5,size );
							      preparedstmt1.setString(6,color );
							      preparedstmt1.setString(7,hsn );
							      preparedstmt1.setString(8,mrp );
							      
							      
							      					    
							      
							      preparedstmt1.execute();
				            }else if(vendorlist.isSelected()){
				            	String vendorCode   = row.getCell(0).getStringCellValue().toUpperCase();
				            	String vendorName   = row.getCell(1).getStringCellValue().toUpperCase();
				            	String gstNumber   = row.getCell(2).getStringCellValue().toUpperCase();
				            	String gstState   = row.getCell(3).getStringCellValue().toUpperCase();
				            	String contactNumber   = row.getCell(4).getStringCellValue().toUpperCase();
				            	System.out.println(contactNumber);
				            	String contactPerson   = row.getCell(5).getStringCellValue().toUpperCase();
				            	String add   = row.getCell(6).getStringCellValue();
				            	
				            	
				            	
				            	 
				            	
				            	
				            	String sql1 = "insert IGNORE vendor_list values(?,?,?,?,?,?,?) ";
							      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
							      preparedstmt1.setString(1, vendorCode);
							      preparedstmt1.setString(2, vendorName);
							      
							      preparedstmt1.setString(3,gstNumber );
							      preparedstmt1.setString(4,gstState );
							      preparedstmt1.setString(5,contactNumber );
							      preparedstmt1.setString(6,contactPerson );
							      preparedstmt1.setString(7,add );
							     				    
							      
							      preparedstmt1.execute();
				            }
				                
					                else {
				            	path.setText("Select a CheckBox");
				            }
				                System.out.println("Import rows "+i);
					      }
				      
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e1){
			      //Handle errors for Class.forName
			      e1.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			   path.setText("Transfer Complete");
			
			}
		});
		headerLabel.setBounds(150, 11, 400, 31);
		statusLabel.setBounds(180, 340, 312, 31);
		headerLabel.setText("Popnetic Warehousing System");
		controlPanel.add(inventoryUpload);
		controlPanel.add(openfile);
		controlPanel.add(path);
		controlPanel.add(transfer);
		controlPanel.add(back);
		controlPanel.add(myntraDiscount);
		controlPanel.add(itemcodeCheckBox);
		controlPanel.add(limeroadcodeCheckBox);
		controlPanel.add(productionCheckBox);
		controlPanel.add(myntraInvoice);
		controlPanel.add(myntraSharing);
		controlPanel.add(myntraSPF);
		controlPanel.add(flipkartSPF);
		controlPanel.add(ajioInfo);
		controlPanel.add(vendorlist);
		

	}	
	
	
	//Payment Launch
private void paymentLaunch() {
		
		prepareGUI("Payment",560,398);
		
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosingOrder(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		JButton limeroad = new JButton("Limeroad");
		JButton flipkart = new JButton("Flipkart");
		JButton myntra = new JButton("Myntra Daily");
		JButton myntra_order = new JButton("Myntra Order Flow");
		JButton myntra_restock = new JButton("Myntra Restock");
		
		
		
		limeroad.setForeground(SystemColor.desktop);
		limeroad.setBackground(SystemColor.activeCaption);
		limeroad.setBounds(10, 140, 94, 31);
		
		flipkart.setForeground(SystemColor.desktop);
		flipkart.setBackground(SystemColor.activeCaption);
		flipkart.setBounds(220, 140, 94, 31);
		
		myntra_order.setForeground(SystemColor.desktop);
		myntra_order.setBackground(SystemColor.activeCaption);
		myntra_order.setBounds(90, 200, 160, 31);
		
		myntra_restock.setForeground(SystemColor.desktop);
		myntra_restock.setBackground(SystemColor.activeCaption);
		myntra_restock.setBounds(270, 200, 160, 31);
		
		myntra.setForeground(SystemColor.desktop);
		myntra.setBackground(SystemColor.activeCaption);
		myntra.setBounds(415, 140,124, 31);
		
		JButton back = new JButton("Back");
		back.setBounds(220, 300, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent backOrderLaunchEvent) {
				mainFrame.dispose();
				prepareGUI("Hanuman",532,450);
				
				
				mainFrame.addWindowListener(new WindowAdapter() {
					public void windowClosingOrder(WindowEvent windowEvent) {
						System.exit(0);
					}
				});
				
				launch();
			}
		});
		
		
		headerLabel.setBounds(80, 11, 400, 31);
		statusLabel.setBounds(100, 300, 312, 31);
		
		
		
		limeroad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent limeroadEvent) {
				mainFrame.dispose();
				limeroadLaunch();
				
			}
		});
		
		
		
		flipkart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent flipkartEvent) {
				mainFrame.dispose();
				flipkartPaymentLaunch();
				
			}
		});
		
		myntra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent myntraEvent) {
				mainFrame.dispose();
				myntraPaymentLaunch();
				
			}
		});
		
		myntra_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent myntraOrderEvent) {
				mainFrame.dispose();
				myntraOrderLaunch();
				
			}
		});
		
		myntra_restock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent myntraReStockEvent) {
				mainFrame.dispose();
				myntraRestockLaunch();
				
			}
		});
		
		headerLabel.setText("Welcome to Popnetic Warehousing System!");
		
		controlPanel.add(limeroad);
		controlPanel.add(flipkart);
		controlPanel.add(myntra);
		controlPanel.add(myntra_order);
		controlPanel.add(back);
		controlPanel.add(myntra_restock);
		mainFrame.setVisible(true);
		
		
		
		
	}


//Myntra Restock Flow Launch

private void myntraRestockLaunch() {
	prepareGUI("Myntra Restock",700,398);
	
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosinglimeroadLaunch(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	
	JButton openfile = new JButton("Open");
	openfile.setForeground(SystemColor.desktop);
	openfile.setBackground(SystemColor.activeCaption);
	openfile.setBounds(150, 140, 94, 31);
	openfile.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton transfer = new JButton("Transfer");
	transfer.setForeground(SystemColor.desktop);
	transfer.setBackground(SystemColor.activeCaption);
	transfer.setBounds(450, 140, 94, 31);
	
	
	JButton back = new JButton("Back");
	back.setBounds(300, 300, 94, 31);
	back.setForeground(SystemColor.desktop);
	back.setBackground(SystemColor.controlDkShadow);
	
	
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent backmyntraOrderFlowEvent) {
			
			mainFrame.dispose();
			paymentLaunch();
			
		}
	});
	JLabel path = new JLabel("NO FILES Selected");
	path.setBounds(250, 260, 300, 31);
	
	openfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent openfileEvent) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			 int r = j.showOpenDialog(null); 
			 if (r == JFileChooser.APPROVE_OPTION) 
				  
	            { 
	                // set the label to the path of the selected file 
	                path.setText(j.getSelectedFile().getAbsolutePath()); 
	            } 
	            // if the user cancelled the operation 
	            else
	                path.setText("the user cancelled the operation");
			
		}
		
		
		
	});
	
	transfer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent transferEvent) {
			
			Connection conn = null;
			
			   Statement stmt = null;
			   
			   try{
				 //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");

				      //STEP 3: Open a connection
				      System.out.println("Connecting to a selected database...");
				      conn = DriverManager.getConnection(DB_URL, USER, PASS);
				      System.out.println("Connected database successfully...");
				     // PreparedStatement pstm = null ;
				      
				      
				      //Reading the selected excel File
				     
				      FileInputStream input = new FileInputStream(path.getText());
				      System.out.println(input);
				      POIFSFileSystem fs = new POIFSFileSystem( input );
				      HSSFWorkbook wb = new HSSFWorkbook(fs);
				      HSSFSheet sheet = wb.getSheetAt(0);
				      Row row;
				      for(int i=1; i<=sheet.getLastRowNum(); i++){
			                row = sheet.getRow(i);
			                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			                DataFormatter formatter = new DataFormatter();
			               
			               //System.out.println(reportDate);
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
			               
			              String uniqueKey1="";
			               
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
			            //   Date orderedOn = Date.valueOf(orderedOnString);
			                //System.out.println(orderOn);
			                String saleOrderCode  = formatter.formatCellValue(row.getCell(0));
			                System.out.println(saleOrderCode);
			                
			                String sql5= "select saleOrderCode from myntra_restock where saleOrderCode=?";
				            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
				               preparedstmt5.setString(1, saleOrderCode);
				               
				               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
				               while(rscheckkey5.next()) {
				            	   uniqueKey1 = rscheckkey5.getString("saleOrderCode");
				               }
			                if(saleOrderCode.equalsIgnoreCase(uniqueKey1)) {
			                	System.out.println("Already updated");
			                }else {
			                
			                String orderCode = formatter.formatCellValue(row.getCell(1));
			                System.out.println(orderCode);
			                
				            
				            String restockType = formatter.formatCellValue(row.getCell(2));
				            
				            String returnID = formatter.formatCellValue(row.getCell(3));
				            
				            java.util.Date restockDateOn  =  row.getCell(4).getDateCellValue();
				            String restockDate  = df.format(restockDateOn);
			                
				            java.util.Date packingDateOn  =  row.getCell(6).getDateCellValue();
				            String packingDate  = df.format(packingDateOn);
				            
				            System.out.println(packingDate);
				            
				            
				            
				            String invoiceNumber = formatter.formatCellValue(row.getCell(7));
				            
				            String portalName = formatter.formatCellValue(row.getCell(8));
				            
				            String skuCode = formatter.formatCellValue(row.getCell(9));
				            
				            String hsn = formatter.formatCellValue(row.getCell(10));
				            
				            String productTaxCategory = formatter.formatCellValue(row.getCell(11));
				            
				            String paymentMethod = formatter.formatCellValue(row.getCell(12));
				            
				            Double totalAmount      = (Double) row.getCell(14).getNumericCellValue();
			                
				            Double postpaid      = (Double) row.getCell(15).getNumericCellValue();
				            
				            Double prepaid      = (Double) row.getCell(16).getNumericCellValue();
				            
				            Double discountAmount      = (Double) row.getCell(18).getNumericCellValue();
			               
				            Double additionalAmount      = (Double) row.getCell(19).getNumericCellValue();
				            
				            String shippingCase = formatter.formatCellValue(row.getCell(20));
				            
				            Double taxRate      = (Double) row.getCell(21).getNumericCellValue();
				            
				            Double igstAmount      = (Double) row.getCell(22).getNumericCellValue();
				            
				            Double cgstAmount      = (Double) row.getCell(23).getNumericCellValue();
				            
				            Double sgstAmount      = (Double) row.getCell(24).getNumericCellValue();
				            
				            Double tcsIgstAmount      = (Double) row.getCell(25).getNumericCellValue();
				            
				            Double tcsCgstAmount      = (Double) row.getCell(26).getNumericCellValue();
				            
				            Double tcsSgstAmount      = (Double) row.getCell(27).getNumericCellValue();
				            
				            String brand = formatter.formatCellValue(row.getCell(28));
				            
				            String gender = formatter.formatCellValue(row.getCell(29));
				            
				            String articleType = formatter.formatCellValue(row.getCell(30));
				            
				            String isTryAndBuy = formatter.formatCellValue(row.getCell(32));
				            
				            String trackingNo = formatter.formatCellValue(row.getCell(33));
				            
				            String courierCode = formatter.formatCellValue(row.getCell(34));
				            
				            
				            
				            String customerState = formatter.formatCellValue(row.getCell(37));
				            
				            
				            
				            Double taxableAmount      = (Double) row.getCell(41).getNumericCellValue();
				            
				            Double shippingAmount      = (Double) row.getCell(47).getNumericCellValue();
				            
				            
				            
				            String vendorSkuCode="";
				            String levels="";
				            Double fwdShipping=0.0;
				            Double revShipping=0.0;
				           Double paymentFee=0.0;
				           Double pickandpackFee=0.0;
				            Double taxonLogistics=0.0;
				            
				            
				            String zone="";
				            
				            String sql2 ="select vendorSKU from myntra_vendor_sku_mapping where myntraSKU=?";
				               PreparedStatement preparedstmt3 = conn.prepareStatement(sql2);
				               preparedstmt3.setString(1, skuCode);
				               ResultSet rscheckkey1 = preparedstmt3.executeQuery();
				               while(rscheckkey1.next()) {
				            	   vendorSkuCode = rscheckkey1.getString("vendorSKU");
				               }
				            
				            
				            
				               
				               String sql6 ="select zone from myntra_state_zone where code=?";
				               PreparedStatement preparedstmt6 = conn.prepareStatement(sql6);
				               preparedstmt6.setString(1, customerState);
				               ResultSet rscheckkey4 = preparedstmt6.executeQuery();
				               while(rscheckkey4.next()) {
				            	   zone = rscheckkey4.getString("zone");
				               }
				               
				            
				               
				            
					              
					               
				            
				            String sql = " INSERT ignore INTO myntra_restock values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						      PreparedStatement preparedstmt = conn.prepareStatement(sql);
						      
						     
						      preparedstmt.setString(1, saleOrderCode);
						      preparedstmt.setString(2, orderCode);
						      preparedstmt.setString(3, restockType);
						      preparedstmt.setString(4, returnID);
						      preparedstmt.setString(5, restockDate);
						      preparedstmt.setString(6, packingDate);
						      preparedstmt.setString(7, invoiceNumber);
						      preparedstmt.setString(8, portalName);
						      preparedstmt.setString(9, skuCode);
						      preparedstmt.setString(10, vendorSkuCode);
						      preparedstmt.setString(11, hsn);
						      preparedstmt.setString(12, productTaxCategory);
						      
						      preparedstmt.setString(13, paymentMethod);
						      preparedstmt.setDouble(14, totalAmount);
						      preparedstmt.setDouble(15, postpaid);
						      preparedstmt.setDouble(16, prepaid);
						      preparedstmt.setDouble(17, discountAmount);
						      preparedstmt.setDouble(18, additionalAmount);
						      preparedstmt.setString(19, shippingCase);
						      preparedstmt.setDouble(20, taxRate);
						      preparedstmt.setDouble(21, igstAmount);
						      preparedstmt.setDouble(22, cgstAmount);
						      preparedstmt.setDouble(23, sgstAmount);
						      preparedstmt.setDouble(24, tcsIgstAmount);
						      preparedstmt.setDouble(25, tcsCgstAmount);
						     
						      preparedstmt.setDouble(26, tcsSgstAmount);
						      preparedstmt.setString(27, brand);
						      preparedstmt.setString(28, gender);
						      preparedstmt.setString(29, articleType);
						      preparedstmt.setString(30, isTryAndBuy);
						      preparedstmt.setString(31, trackingNo);
						      preparedstmt.setString(32, courierCode);
						      preparedstmt.setString(33, customerState);
						      preparedstmt.setString(34, zone);
						      preparedstmt.setDouble(35, taxableAmount);
						      preparedstmt.setDouble(36, shippingAmount);
						     
						      
						      
						     preparedstmt.execute(); 
						     if(restockType.equalsIgnoreCase("RTO-Restocked")) {
						    	 System.out.println("No need to update");
						     }else {
						     String sql3 ="select levels from myntra_sku_level where sku=?";
				               PreparedStatement preparedstmt4 = conn.prepareStatement(sql3);
				               preparedstmt4.setString(1, vendorSkuCode);
				               ResultSet rscheckkey2 = preparedstmt4.executeQuery();
				               while(rscheckkey2.next()) {
				            	   levels = rscheckkey2.getString("levels");
				               }
				               
						     String uniqueKey = levels+zone;
				              System.out.println(uniqueKey);
				              String sql4 =" select logisticCost from myntra_reverse_logistics where uniqueKey=?";
				              PreparedStatement preparedstmt10 = conn.prepareStatement(sql4);
				               preparedstmt10.setString(1, uniqueKey);
				               
				               ResultSet rscheckkey3 = preparedstmt10.executeQuery();
				               while(rscheckkey3.next()) {
				            	   revShipping = rscheckkey3.getDouble("logisticCost");
				               }
				               
				               String sql8 =" select logisticCost from myntra_forward_logistics where uniqueKey=?";
					              PreparedStatement preparedstmt11 = conn.prepareStatement(sql8);
					               preparedstmt11.setString(1, uniqueKey);
					               
					               ResultSet rscheckkey6 = preparedstmt11.executeQuery();
					               while(rscheckkey6.next()) {
					            	   fwdShipping = rscheckkey6.getDouble("logisticCost");
					               }
						     
				               if(levels.equalsIgnoreCase("Level 1")) {
				            	   pickandpackFee =15.0;
				               }else if(levels.equalsIgnoreCase("Level 2")) {
				            	   pickandpackFee =20.0;
				               }else if(levels.equalsIgnoreCase("Level 3")) {
				            	   pickandpackFee =25.0;
				               }else if(levels.equalsIgnoreCase("Level 4")) {
				            	   pickandpackFee =35.0;
				               }else {
				            	   pickandpackFee =35.0;
				               }
				               
				               if((totalAmount*0.02)<20) {
				            	   paymentFee=20.0;
				               }else {
				            	   paymentFee=totalAmount*0.02;
				               }
				               
				             
				               
				               taxonLogistics=-(paymentFee+pickandpackFee+fwdShipping+revShipping)*1.18;
						     
						     
				               
					             
					               String sql7="update ignore myntra_order set revLogistics=?,estimatedRevCharges=? where saleOrderCode=?";
					               PreparedStatement preparedstmt8 = conn.prepareStatement(sql7);
					               preparedstmt8.setDouble(1,(-revShipping) );
					               preparedstmt8.setDouble(2,taxonLogistics );
					               
					               preparedstmt8.setString(3, saleOrderCode);
						     
						     preparedstmt8.execute(); 
						     }
			              				               				               
						     System.out.println("Imported Rows"+i);
			                } 
				      }
			      
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e1){
		      //Handle errors for Class.forName
		      e1.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   path.setText("Transfer Complete");
		
		}
	});
	headerLabel.setBounds(150, 11, 400, 31);
	statusLabel.setBounds(180, 340, 312, 31);
	headerLabel.setText("Popnetic Warehousing System");
	controlPanel.add(openfile);
	controlPanel.add(path);
	controlPanel.add(transfer);
	controlPanel.add(back);
}

	

//Myntra Order Flow Launch

private void myntraOrderLaunch() {
	prepareGUI("Myntra order Flow",700,398);
	
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosinglimeroadLaunch(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	
	JButton openfile = new JButton("Open");
	openfile.setForeground(SystemColor.desktop);
	openfile.setBackground(SystemColor.activeCaption);
	openfile.setBounds(150, 140, 94, 31);
	openfile.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton transfer = new JButton("Transfer");
	transfer.setForeground(SystemColor.desktop);
	transfer.setBackground(SystemColor.activeCaption);
	transfer.setBounds(450, 140, 94, 31);
	
	
	JButton back = new JButton("Back");
	back.setBounds(300, 300, 94, 31);
	back.setForeground(SystemColor.desktop);
	back.setBackground(SystemColor.controlDkShadow);
	
	
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent backmyntraOrderFlowEvent) {
			
			mainFrame.dispose();
			paymentLaunch();
			
		}
	});
	JLabel path = new JLabel("NO FILES Selected");
	path.setBounds(250, 260, 300, 31);
	
	openfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent openfileEvent) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			 int r = j.showOpenDialog(null); 
			 if (r == JFileChooser.APPROVE_OPTION) 
				  
	            { 
	                // set the label to the path of the selected file 
	                path.setText(j.getSelectedFile().getAbsolutePath()); 
	            } 
	            // if the user cancelled the operation 
	            else
	                path.setText("the user cancelled the operation");
			
		}
		
		
		
	});
	
	transfer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent transferEvent) {
			
			Connection conn = null;
			
			   Statement stmt = null;
			   
			   try{
				 //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");

				      //STEP 3: Open a connection
				      System.out.println("Connecting to a selected database...");
				      conn = DriverManager.getConnection(DB_URL, USER, PASS);
				      System.out.println("Connected database successfully...");
				     // PreparedStatement pstm = null ;
				      
				      
				      //Reading the selected excel File
				     
				      FileInputStream input = new FileInputStream(path.getText());
				      System.out.println(input);
				      POIFSFileSystem fs = new POIFSFileSystem( input );
				      HSSFWorkbook wb = new HSSFWorkbook(fs);
				      HSSFSheet sheet = wb.getSheetAt(0);
				      Row row;
				      for(int i=1; i<=sheet.getLastRowNum(); i++){
			                row = sheet.getRow(i);
			                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			                DataFormatter formatter = new DataFormatter();
			               
			               //System.out.println(reportDate);
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
			               
			              String uniqueKey1="";
			               
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
			            //   Date orderedOn = Date.valueOf(orderedOnString);
			                //System.out.println(orderOn);
			                String saleOrderCode  = formatter.formatCellValue(row.getCell(0));
			                System.out.println(saleOrderCode);
			                
			                String sql5= "select saleOrderCode from myntra_order_flow where saleOrderCode=?";
				            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
				               preparedstmt5.setString(1, saleOrderCode);
				               
				               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
				               while(rscheckkey5.next()) {
				            	   uniqueKey1 = rscheckkey5.getString("saleOrderCode");
				               }
			                if(saleOrderCode.equalsIgnoreCase(uniqueKey1)) {
			                	System.out.println("Already updated");
			                }else {
			                
			                String orderCode = formatter.formatCellValue(row.getCell(11));
			                System.out.println(orderCode);
			                java.util.Date orderDateOn  =  row.getCell(22).getDateCellValue();
				            String orderDate  = df.format(orderDateOn);
				            System.out.println(orderDate);
			                
				            java.util.Date packingDateOn  =  row.getCell(33).getDateCellValue();
				            String packingDate  = df.format(packingDateOn);
				            System.out.println(packingDate);
				            String sellerOrderID = formatter.formatCellValue(row.getCell(44));
				            
				            String invoiceNumber = formatter.formatCellValue(row.getCell(53));
				            
				            String portalName = formatter.formatCellValue(row.getCell(54));
				            
				            String skuCode = formatter.formatCellValue(row.getCell(55));
				            
				            String hsn = formatter.formatCellValue(row.getCell(56));
				            
				            String productTaxCategory = formatter.formatCellValue(row.getCell(1));
				            
				          //  String paymentMethod = formatter.formatCellValue(row.getCell(10));
				            
				            Double totalAmount      = (Double) row.getCell(4).getNumericCellValue();
			                
				            Double postpaid      = (Double) row.getCell(5).getNumericCellValue();
				            
				            Double prepaid      = (Double) row.getCell(6).getNumericCellValue();
				            
				            Double discountAmount      = (Double) row.getCell(8).getNumericCellValue();
			               
				            Double additionalAmount      = (Double) row.getCell(9).getNumericCellValue();
				            
				            String shippingCase = formatter.formatCellValue(row.getCell(10));
				            
				            Double taxRate      = (Double) row.getCell(12).getNumericCellValue();
				            
				            Double igstAmount      = (Double) row.getCell(13).getNumericCellValue();
				            
				            Double cgstAmount      = (Double) row.getCell(14).getNumericCellValue();
				            
				            Double sgstAmount      = (Double) row.getCell(15).getNumericCellValue();
				            
				            Double tcsIgstAmount      = (Double) row.getCell(16).getNumericCellValue();
				            
				            Double tcsCgstAmount      = (Double) row.getCell(17).getNumericCellValue();
				            
				            Double tcsSgstAmount      = (Double) row.getCell(18).getNumericCellValue();
				            
				            String brand = formatter.formatCellValue(row.getCell(19));
				            
				            String gender = formatter.formatCellValue(row.getCell(20));
				            
				            String articleType = formatter.formatCellValue(row.getCell(21));
				            
				            String isTryAndBuy = formatter.formatCellValue(row.getCell(24));
				            
				            String trackingNo = formatter.formatCellValue(row.getCell(25));
				            
				            String courierCode = formatter.formatCellValue(row.getCell(26));
				            
				            String customerName = formatter.formatCellValue(row.getCell(27));
				            
				            String customerPincode = formatter.formatCellValue(row.getCell(28));
				            
				            String customerState = formatter.formatCellValue(row.getCell(29));
				            
				            String customerAddress = "NA";
				            
				            Double taxableAmount      = (Double) row.getCell(35).getNumericCellValue();
				            
				            Double shippingAmount      = (Double) row.getCell(41).getNumericCellValue();
				            
				            
				            
				            Double tds = (Double) row.getCell(43).getNumericCellValue();
				            
				            Double couponDiscount      = (Double) row.getCell(45).getNumericCellValue();
				            
				            String myntraGSTN = formatter.formatCellValue(row.getCell(52));
				            
				            String vendorSkuCode="";
				            
				            String zone="";
				            
				            String sql2 ="select vendorArticleNumber from myntra_sku_level where myntraSku=?";
				               PreparedStatement preparedstmt3 = conn.prepareStatement(sql2);
				               preparedstmt3.setString(1, skuCode);
				               ResultSet rscheckkey1 = preparedstmt3.executeQuery();
				               while(rscheckkey1.next()) {
				            	   vendorSkuCode = rscheckkey1.getString("vendorArticleNumber");
				               }
				            
				            
				            
				               
				               String sql6 ="select zone from ppmp_pincode where pincode=?";
				               PreparedStatement preparedstmt6 = conn.prepareStatement(sql6);
				               preparedstmt6.setString(1, customerPincode);
				               ResultSet rscheckkey4 = preparedstmt6.executeQuery();
				               while(rscheckkey4.next()) {
				            	   zone = rscheckkey4.getString("zone");
				               }
				               
				            
				               
				            
					              
					               
				            
				            String sql = " INSERT ignore INTO myntra_order_flow values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						      PreparedStatement preparedstmt = conn.prepareStatement(sql);
						      
						     
						      preparedstmt.setString(1, saleOrderCode);
						      preparedstmt.setString(2, orderCode);
						      preparedstmt.setString(3, orderDate);
						      preparedstmt.setString(4, packingDate);
						      preparedstmt.setString(5, sellerOrderID);
						      preparedstmt.setString(6, invoiceNumber);
						      preparedstmt.setString(7, portalName);
						      preparedstmt.setString(8, skuCode);
						      preparedstmt.setString(9, vendorSkuCode);
						      preparedstmt.setString(10, hsn);
						      preparedstmt.setString(11, productTaxCategory);
						      //preparedstmt.setString(12, paymentMethod);
						      
						      preparedstmt.setDouble(13, totalAmount);
						      preparedstmt.setDouble(14, postpaid);
						      preparedstmt.setDouble(15, prepaid);
						      
						      if(prepaid>postpaid) {
						    	  preparedstmt.setString(12, "on");
						    	  
						      }else {
						    	  preparedstmt.setString(12, "cod");
						      }
						      preparedstmt.setDouble(16, discountAmount);
						      preparedstmt.setDouble(17, additionalAmount);
						      preparedstmt.setString(18, shippingCase);
						      preparedstmt.setDouble(19, taxRate);
						      preparedstmt.setDouble(20, igstAmount);
						      preparedstmt.setDouble(21, cgstAmount);
						      preparedstmt.setDouble(22, sgstAmount);
						      preparedstmt.setDouble(23, tcsIgstAmount);
						      preparedstmt.setDouble(24, tcsCgstAmount);
						      preparedstmt.setDouble(25, tcsSgstAmount);
						     
						      preparedstmt.setString(26, brand);
						      preparedstmt.setString(27, gender);
						      preparedstmt.setString(28, articleType);
						      preparedstmt.setString(29, isTryAndBuy);
						      preparedstmt.setString(30, trackingNo);
						      preparedstmt.setString(31, courierCode);
						      preparedstmt.setString(32, customerName);
						      preparedstmt.setString(33, customerPincode);
						      preparedstmt.setString(34, customerState);
						      preparedstmt.setString(35, zone);
						      preparedstmt.setString(36, customerAddress);
						     
						      preparedstmt.setDouble(37, taxableAmount);
						      preparedstmt.setDouble(38, shippingAmount);
						      preparedstmt.setDouble(39, tds);
						      preparedstmt.setDouble(40, couponDiscount);
						      
						      preparedstmt.setString(41, myntraGSTN);
						      
						     preparedstmt.execute(); 
						     
						     
						     if(prepaid>postpaid) {
				               Double paymentFee1=(prepaid+postpaid)*0.02;
					             
				               String sql1 = "UPDATE IGNORE myntra_ppmp_order set paymentMethod=?,estimatedSettleValue=estimatedSettleValue+paymentFee-?,paymentFee=?,isPresentInOrderFlow=? where orderReleaseID=?";
							      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
							      preparedstmt12.setString(1, "on");
							      
							      preparedstmt12.setDouble(2, paymentFee1);
							      preparedstmt12.setDouble(3, paymentFee1);
							      preparedstmt12.setString(4, "Yes");
							      
							      preparedstmt12.setString(5,saleOrderCode );
							      
							      preparedstmt12.execute();
						     }else {
						    	 String sql7="update ignore myntra_ppmp_order set isPresentInOrderFlow=? where orderReleaseId=?";
					               PreparedStatement preparedstmt8 = conn.prepareStatement(sql7);
					               preparedstmt8.setString(1,"Yes" );
					               
					               preparedstmt8.setString(2, saleOrderCode);
						     
						     preparedstmt8.execute();
						     }
						     
						     String sql71="update ignore myntra_ppmp_order_report set isPresentInOrderFlow=? where orderReleaseID=?";
				               PreparedStatement preparedstmt81 = conn.prepareStatement(sql71);
				               preparedstmt81.setString(1,"Yes" );
				               
				               preparedstmt81.setString(2, saleOrderCode);
					     
					     preparedstmt81.execute(); 
						     
			              				               				               
						     System.out.println("Imported Rows"+i);
			                } 
				      }
			      
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e1){
		      //Handle errors for Class.forName
		      e1.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   path.setText("Transfer Complete");
		
		}
	});
	headerLabel.setBounds(150, 11, 400, 31);
	statusLabel.setBounds(180, 340, 312, 31);
	headerLabel.setText("Popnetic Warehousing System");
	controlPanel.add(openfile);
	controlPanel.add(path);
	controlPanel.add(transfer);
	controlPanel.add(back);
}

	
	
	//Order launch Window Code
	private void orderLaunch() {
		
		prepareGUI("Order",532,398);
		
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosingOrder(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		JButton limeroad = new JButton("Limeroad");
		JButton ppmp = new JButton("Myntra PPMP Order");
		JButton flipkart = new JButton("Flipkart");
		JButton myntra = new JButton("Myntra");
		JButton ajioPO = new JButton("AJIO PO");
		JButton ppmpreport = new JButton("Myntra PPMP Report");
		JButton ajioGRN = new JButton("AJIO GRN");
		
		
		limeroad.setForeground(SystemColor.desktop);
		limeroad.setBackground(SystemColor.activeCaption);
		limeroad.setBounds(10, 140, 94, 31);
		
		ppmp.setForeground(SystemColor.desktop);
		ppmp.setBackground(SystemColor.activeCaption);
		ppmp.setBounds(10, 200, 150, 31);
		
		ajioPO.setForeground(SystemColor.desktop);
		ajioPO.setBackground(SystemColor.activeCaption);
		ajioPO.setBounds(10, 240, 150, 31);
		
		flipkart.setForeground(SystemColor.desktop);
		flipkart.setBackground(SystemColor.activeCaption);
		flipkart.setBounds(220, 140, 94, 31);
		
		myntra.setForeground(SystemColor.desktop);
		myntra.setBackground(SystemColor.activeCaption);
		myntra.setBounds(415, 140, 94, 31);
		
		
		
		ppmpreport.setForeground(SystemColor.desktop);
		ppmpreport.setBackground(SystemColor.activeCaption);
		ppmpreport.setBounds(350, 200, 155, 31);
		
		ajioGRN.setForeground(SystemColor.desktop);
		ajioGRN.setBackground(SystemColor.activeCaption);
		ajioGRN.setBounds(350, 240, 155, 31);
		
		JButton back = new JButton("Back");
		back.setBounds(220, 300, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		JButton scan = new JButton("Scan Upload");
		scan.setBounds(205, 200, 124, 31);
		scan.setForeground(SystemColor.desktop);
		scan.setBackground(SystemColor.green);
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent backOrderLaunchEvent) {
				mainFrame.dispose();
				prepareGUI("Hanuman",532,450);
				
				
				mainFrame.addWindowListener(new WindowAdapter() {
					public void windowClosingOrder(WindowEvent windowEvent) {
						System.exit(0);
					}
				});
				
				launch();
			}
		});
		
		
		headerLabel.setBounds(80, 11, 400, 31);
		statusLabel.setBounds(100, 300, 312, 31);
		
		
		
		limeroad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent limeroadEvent) {
				mainFrame.dispose();
				limeroadLaunch();
				
			}
		});
		
		scan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent scanEvent) {
				mainFrame.dispose();
				scanLaunch();
				
			}
		});
		
		flipkart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent flipkartEvent) {
				mainFrame.dispose();
				flipkartLaunch();
				
			}
		});
		
		myntra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent myntraEvent) {
				mainFrame.dispose();
				myntraLaunch();
				
			}
		});
		
		ppmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ppmpEvent) {
				mainFrame.dispose();
				ppmpLaunch();
				
			}
		});
		
		ajioPO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ajioPOEvent) {
				mainFrame.dispose();
				ajioPOLaunch();
				
			}
		});
		
		ppmpreport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ppmpreportEvent) {
				mainFrame.dispose();
				ppmpreportLaunch();
				
			}
		});
		
		ajioGRN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ajioGRNEvent) {
				mainFrame.dispose();
				ajioGRNLaunch();
				
			}
		});
		
		headerLabel.setText("Welcome to Popnetic Warehousing System!");
		controlPanel.add(scan);
		controlPanel.add(limeroad);
		controlPanel.add(flipkart);
		controlPanel.add(myntra);
		controlPanel.add(ppmp);
		controlPanel.add(ppmpreport);
		controlPanel.add(ajioPO);
		controlPanel.add(ajioGRN);
		
		controlPanel.add(back);
		mainFrame.setVisible(true);
		
		
		
		
	}
	
	
	
	
	
	//Scan Launch Code
	private void scanLaunch() {
		prepareGUI("Scan",700,398);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosingscanLaunch(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		
		
		JLabel scanID = new JLabel("Scan ID");
		scanID.setForeground(new Color(70, 130, 180));
		scanID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		scanID.setBounds(160, 160, 200, 20);
		scanID.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField scanIDText = new JTextField();
		scanIDText.setForeground(SystemColor.desktop);
		scanIDText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		scanIDText.setBounds(350, 160, 200, 20);
		scanIDText.setColumns(10);
		
		JLabel link = new JLabel("Link");
		link.setForeground(new Color(70, 130, 180));
		link.setFont(new Font("Times New Roman", Font.BOLD, 15));
		link.setBounds(160, 200, 200, 20);
		link.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField linkText = new JTextField();
		linkText.setForeground(SystemColor.desktop);
		linkText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		linkText.setBounds(350, 200, 200, 20);
		linkText.setColumns(10);
		
		JButton back = new JButton("Back");
		back.setBounds(300, 300, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent backmyntrareturnOrderEvent) {
				mainFrame.dispose();
				orderLaunch();
			}
		});
		
		
		linkText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent orderNumberAddEvent) {
				
				String datascanID = scanIDText.getText();
				String datalink =linkText.getText();
				
				Connection conn = null;
				   Statement stmt1 = null;
				   Date currentDatetime = new Date(System.currentTimeMillis());
				   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
				   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
				   try{
						 //STEP 2: Register JDBC driver
						      Class.forName("com.mysql.jdbc.Driver");

						      //STEP 3: Open a connection
						      System.out.println("Connecting to a selected database...");
						      conn = DriverManager.getConnection(DB_URL, USER, PASS);
						      System.out.println("Connected database successfully...");
					      
					      //STEP 4: Execute a query
					      System.out.println("Inserting records into the table...");
					      
					      String sql = " INSERT INTO scan(ID,link,date,time) Values(?,?,?,?)";
					      PreparedStatement preparedstmt = conn.prepareStatement(sql);
					      preparedstmt.setString(1, datascanID);
					      preparedstmt.setString(2, datalink);
					      
					      
					      preparedstmt.setDate(3, sqlDate);
					      preparedstmt.setTimestamp(4, timestamp);
					      preparedstmt.executeUpdate();
					      System.out.println("Inserted records into the table...");
					      
					      scanIDText.setText("");
					      linkText.setText("");
					      scanIDText.requestFocus();
					      
					     
					      
				}catch(SQLException se){
				      //Handle errors for JDBC
				      se.printStackTrace();
				   }catch(Exception e1){
				      //Handle errors for Class.forName
				      e1.printStackTrace();
				   }finally{
				      //finally block used to close resources
				      try{
				         if(stmt1!=null)
				            conn.close();
				      }catch(SQLException se){
				      }// do nothing
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
				   }//end try
				   System.out.println("Goodbye!");
				
			}
		});
		
		
		
		headerLabel.setBounds(150, 11, 400, 31);
		statusLabel.setBounds(180, 340, 312, 31);
		headerLabel.setText("Popnetic Warehousing System");
			
		controlPanel.add(linkText);
		controlPanel.add(scanIDText);
		controlPanel.add(link);
		controlPanel.add(scanID);
		controlPanel.add(back);
		
		
		

	}
	
	//ReturnOrder Launch Code
	
	private void returnOrderLaunch() {
		
		prepareGUI("Return",532,398);
		
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosingReturn(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		JButton limeroad = new JButton("Limeroad");
		JButton flipkart = new JButton("Flipkart");
		JButton myntra = new JButton("Myntra");
		JButton myntraReturnReport = new JButton("Myntra Return Report");
		JButton myntraReturnAlert = new JButton("Myntra Return Alert");
		JButton ajioRTV = new JButton("AJIO RTV");
		JButton myntraReturn = new JButton("Myntra Daily Return");
		
		
		limeroad.setForeground(SystemColor.desktop);
		limeroad.setBackground(SystemColor.activeCaption);
		limeroad.setBounds(10, 140, 94, 31);
		
		myntraReturnReport.setForeground(SystemColor.desktop);
		myntraReturnReport.setBackground(SystemColor.activeCaption);
		myntraReturnReport.setBounds(10, 200, 160, 31);
		
		flipkart.setForeground(SystemColor.desktop);
		flipkart.setBackground(SystemColor.activeCaption);
		flipkart.setBounds(220, 140, 94, 31);
		
		myntraReturnAlert.setForeground(SystemColor.desktop);
		myntraReturnAlert.setBackground(SystemColor.activeCaption);
		myntraReturnAlert.setBounds(190, 200, 160, 31);
		
		ajioRTV.setForeground(SystemColor.desktop);
		ajioRTV.setBackground(SystemColor.activeCaption);
		ajioRTV.setBounds(190, 240, 160, 31);
		
		myntra.setForeground(SystemColor.desktop);
		myntra.setBackground(SystemColor.activeCaption);
		myntra.setBounds(415, 140, 94, 31);
		myntraReturn.setForeground(SystemColor.desktop);
		myntraReturn.setBackground(SystemColor.activeCaption);
		myntraReturn.setBounds(365, 200, 150, 31);
		
		
		JButton back = new JButton("Back");
		back.setBounds(220, 300, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent backreturnOrderLaunchEvent) {
				mainFrame.dispose();
				
				prepareGUI("Hanuman",532,450);
				
				
				mainFrame.addWindowListener(new WindowAdapter() {
					public void windowClosingOrder(WindowEvent windowEvent) {
						System.exit(0);
					}
				});
				
				launch();
						
			}
		});
		
		
		headerLabel.setBounds(80, 11, 400, 31);
		statusLabel.setBounds(100, 300, 312, 31);
		
		
		
		
		limeroad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent limeroadreturnEvent) {
				mainFrame.dispose();
				limeroadreturnLaunch();
				
			}
		});
		
		flipkart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent flipkartreturnEvent) {
				mainFrame.dispose();
				flipkartreturnLaunch();
			}
		});
		
		myntra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent myntrareturnEvent) {
				mainFrame.dispose();
				myntrareturnLaunch();
			}
		});
		
		myntraReturnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent myntrareturnReportEvent) {
				mainFrame.dispose();
				myntrareturnReportLaunch();
			}
		});
		
		
		myntraReturnAlert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent myntrareturnAlertEvent) {
				mainFrame.dispose();
				myntrareturnAlertLaunch();
			}
		});
		
		ajioRTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ajioRTVtEvent) {
				mainFrame.dispose();
				ajioRTVLaunch();
			}
		});
		
		myntraReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent myntrareturnEvent) {
				mainFrame.dispose();
				myntrareturnDLaunch();
			}
		});
		
		headerLabel.setText("Welcome to Popnetic Warehousing System!");
		controlPanel.add(limeroad);
		controlPanel.add(flipkart);
		controlPanel.add(myntra);
		controlPanel.add(myntraReturn);
		controlPanel.add(myntraReturnAlert);
		controlPanel.add(myntraReturnReport);
		controlPanel.add(back);
		controlPanel.add(ajioRTV);
		
		mainFrame.setVisible(true);
	}
	
	
	
	//Myntra Payment Windows Code

private void myntraPaymentLaunch() {
        prepareGUI("Myntra Payment", 700, 398);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        JButton openfile = new JButton("Open");
        openfile.setForeground(SystemColor.desktop);
        openfile.setBackground(SystemColor.activeCaption);
        openfile.setBounds(150, 140, 94, 31);
        openfile.setHorizontalAlignment(SwingConstants.LEFT);

        JButton transfer = new JButton("Transfer");
        transfer.setForeground(SystemColor.desktop);
        transfer.setBackground(SystemColor.activeCaption);
        transfer.setBounds(450, 140, 94, 31);

        JButton back = new JButton("Back");
        back.setBounds(300, 300, 94, 31);
        back.setForeground(SystemColor.desktop);
        back.setBackground(SystemColor.controlDkShadow);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent backflipkartOrderEvent) {
                mainFrame.dispose();
                // Assuming paymentLaunch() is another method that navigates back
                //paymentLaunch();
            }
        });

        JLabel path = new JLabel("NO FILES Selected");
        path.setBounds(250, 260, 300, 31);

        openfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent openfileEvent) {
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                j.setMultiSelectionEnabled(true);

                FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel and CSV Files", "csv", "xlsx", "xls");
                j.setFileFilter(filter);

                int r = j.showOpenDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    File[] selectedFiles = j.getSelectedFiles();
                    StringBuilder filePaths = new StringBuilder();
                    for (File file : selectedFiles) {
                        filePaths.append(file.getAbsolutePath()).append("\n");
                    }
                    path.setText(filePaths.toString());
                } else {
                    path.setText("The user cancelled the operation");
                }
            }
        });

        transfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent transferEvent) {
                Connection conn = null;
                Statement stmt = null;

                try {
                    // Register JDBC driver
                    Class.forName("com.mysql.jdbc.Driver");

                    // Open a connection
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);

                    String[] filePaths = path.getText().split("\n");
                    for (String filePath : filePaths) {
                        if (filePath.trim().isEmpty()) continue;

                        FileInputStream input = new FileInputStream(filePath.trim());
                        Workbook workbook = null;

                        // Determine if the file is .xlsx or .xls
                        if (filePath.toLowerCase().endsWith(".xlsx")) {
                            workbook = new XSSFWorkbook(input);  // Use XSSFWorkbook for .xlsx files
                        } else if (filePath.toLowerCase().endsWith(".xls")) {
                            workbook = new HSSFWorkbook(input);  // Use HSSFWorkbook for .xls files
                        }

                        if (workbook != null) {
                            Sheet sheet = workbook.getSheetAt(0);
                            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                                Row row = sheet.getRow(i);
                                DataFormatter formatter = new DataFormatter();
                                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                                String neftRef = formatter.formatCellValue(row.getCell(0));
                                String paymentType = formatter.formatCellValue(row.getCell(10));
                                
                                
                                
					            String payDate        =  formatter.formatCellValue(row.getCell(20));
					            
                                String orderType = formatter.formatCellValue(row.getCell(24));
                                String releaseID = formatter.formatCellValue(row.getCell(25));
                                String packetID = formatter.formatCellValue(row.getCell(26));
                                Double settlementValue = (Double) row.getCell(17).getNumericCellValue();
                                Double customerPaid = (Double) row.getCell(5).getNumericCellValue();
                                Double commission = (Double) row.getCell(6).getNumericCellValue();
                                Double tcsigst = (Double) row.getCell(7).getNumericCellValue();
                                Double tcscgst = (Double) row.getCell(8).getNumericCellValue();
                                Double tcssgst = (Double) row.getCell(9).getNumericCellValue();
                                Double tds = (Double) row.getCell(11).getNumericCellValue();

                                Double commissionWithTcs = commission + tcsigst + tcscgst + tcssgst;
                                Double logisticCommission = (Double) row.getCell(16).getNumericCellValue();

                                String uniqueKey = neftRef + releaseID + orderType;

                                // Database operations
                                String sql5 = "SELECT uniqueKey FROM myntra_payment_daily WHERE uniqueKey=?";
                                PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
                                preparedstmt5.setString(1, uniqueKey);
                                ResultSet rscheckkey5 = preparedstmt5.executeQuery();

                                if (rscheckkey5.next()) {
                                    System.out.println("No Need of Operations for uniqueKey: " + uniqueKey);
                                } else {
                                    String sql = "INSERT IGNORE INTO myntra_payment_daily VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                                    PreparedStatement preparedstmt = conn.prepareStatement(sql);
                                    preparedstmt.setString(1, uniqueKey);
                                    preparedstmt.setString(2, neftRef);
                                    preparedstmt.setString(3, paymentType);
                                    preparedstmt.setString(4, payDate);
                                    preparedstmt.setString(5, orderType);
                                    preparedstmt.setString(6, releaseID);
                                    preparedstmt.setString(7, packetID);
                                    preparedstmt.setDouble(8, customerPaid);
                                    preparedstmt.setDouble(9, commissionWithTcs);
                                    preparedstmt.setDouble(10, logisticCommission);
                                    preparedstmt.setDouble(11, tds);
                                    preparedstmt.setDouble(12, settlementValue);
                                    preparedstmt.execute();

                                    if (orderType.equalsIgnoreCase("Forward")) {
                                        String sql1 = "UPDATE IGNORE myntra_ppmp_order SET fwdPaymentStatus=?, fwdSettleValue=fwdSettleValue+?, settleValue=settleValue+?, reconcillationStatus=? WHERE orderReleaseId=?";
                                        PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
                                        preparedstmt1.setString(1, "Yes");
                                        preparedstmt1.setDouble(2, settlementValue);
                                        preparedstmt1.setDouble(3, settlementValue);
                                        preparedstmt1.setString(4, "No");
                                        preparedstmt1.setString(5, releaseID);
                                        preparedstmt1.execute();
                                    } else if (orderType.equalsIgnoreCase("Reverse")) {
                                        String sql1 = "UPDATE IGNORE myntra_ppmp_order SET revPaymentStatus=?, revSettleValue=revSettleValue+?, settleValue=settleValue+?, reconcillationStatus=? WHERE orderReleaseId=?";
                                        PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
                                        preparedstmt1.setString(1, "Yes");
                                        preparedstmt1.setDouble(2, settlementValue);
                                        preparedstmt1.setDouble(3, settlementValue);
                                        preparedstmt1.setString(4, "No");
                                        preparedstmt1.setString(5, releaseID);
                                        preparedstmt1.execute();
                                    }
                                }
                            }
                            workbook.close();
                        }
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (stmt != null) conn.close();
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                }
                path.setText("Transfer Complete");
            }
        });

	    headerLabel.setBounds(150, 11, 400, 31);
	    statusLabel.setBounds(180, 340, 312, 31);
	    headerLabel.setText("Popnetic Warehousing System");
	    controlPanel.add(openfile);
	    controlPanel.add(path);
	    controlPanel.add(transfer);
	    controlPanel.add(back);
	}

	
	//Myntra Return Launch Code
	
	private void myntrareturnLaunch() {
		prepareGUI("Myntra Return",700,398);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosinglimeroadLaunch(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		
		JButton openfile = new JButton("Open");
		openfile.setForeground(SystemColor.desktop);
		openfile.setBackground(SystemColor.activeCaption);
		openfile.setBounds(150, 140, 94, 31);
		openfile.setHorizontalAlignment(SwingConstants.LEFT);
		
		JButton transfer = new JButton("Transfer");
		transfer.setForeground(SystemColor.desktop);
		transfer.setBackground(SystemColor.activeCaption);
		transfer.setBounds(450, 140, 94, 31);
		
		
		JButton back = new JButton("Back");
		back.setBounds(300, 300, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent backflipkartOrderEvent) {
				
				mainFrame.dispose();
				returnOrderLaunch();
				
			}
		});
		JLabel path = new JLabel("NO FILES Selected");
		path.setBounds(10, 260, 500, 31);
		
		
		openfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent openfileEvent) {
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
				 int r = j.showOpenDialog(null); 
				 if (r == JFileChooser.APPROVE_OPTION) 
					  
		            { 
		                // set the label to the path of the selected file 
		                path.setText(j.getSelectedFile().getAbsolutePath()); 
		            } 
		            // if the user cancelled the operation 
		            else
		                path.setText("the user cancelled the operation");
				
			}
			
			
			
		});
		
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent transferEvent) {
				
				Connection conn = null;
				
				   Statement stmt = null;
				   
				   try{
					 //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");

					      //STEP 3: Open a connection
					      System.out.println("Connecting to a selected database...");
					      conn = DriverManager.getConnection(DB_URL, USER, PASS);
					      System.out.println("Connected database successfully...");
					     // PreparedStatement pstm = null ;
					      
					      
					      //Reading the selected excel File
					     
					      FileInputStream input = new FileInputStream(path.getText());
					      System.out.println(input);
					      POIFSFileSystem fs = new POIFSFileSystem( input );
					      HSSFWorkbook wb = new HSSFWorkbook(fs);
					      HSSFSheet sheet = wb.getSheetAt(0);
					      Row row;
					      for(int i=1; i<=sheet.getLastRowNum(); i++){
				                row = sheet.getRow(i);
				                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				                DataFormatter formatter = new DataFormatter();
				              
				               //System.out.println(reportDate);
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
				               
				              
				               
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
				            //   Date orderedOn = Date.valueOf(orderedOnString);
				                //System.out.println(orderOn);
				                String uniqueKey1="";
				                String itemCode         = formatter.formatCellValue(row.getCell(14));
				                
				                String sql5= "select itemCode from myntra_gatepass where itemCode=?";
					            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
					               preparedstmt5.setString(1, itemCode);
					               
					               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
					               while(rscheckkey5.next()) {
					            	   uniqueKey1 = rscheckkey5.getString("itemCode");
					               }
				                if(itemCode.equalsIgnoreCase(uniqueKey1)) {
				                	System.out.println("No Need for operations");
				                }else {
				                
				                String gatepassNo  = formatter.formatCellValue(row.getCell(0));
				                
				                String brand  = formatter.formatCellValue(row.getCell(1));
				                
				                String po  = formatter.formatCellValue(row.getCell(2));
				                
				                String myntraSKU   = formatter.formatCellValue(row.getCell(3));
				                
				                String vendorInvoice    = formatter.formatCellValue(row.getCell(4));
				                
				                String vendorStyleCode     = formatter.formatCellValue(row.getCell(5));
				                
				                String color      = formatter.formatCellValue(row.getCell(6));
				                
				                String size       = formatter.formatCellValue(row.getCell(7));
				                
				                int qty  =(int) row.getCell(8).getNumericCellValue();
				                
				                Double rateWoTax     = (Double) row.getCell(9).getNumericCellValue();
				                
				                Double landedPrice      = (Double) row.getCell(11).getNumericCellValue();
				                
				                Double total       = (Double) row.getCell(12).getNumericCellValue();
				                
				                String reason        = formatter.formatCellValue(row.getCell(13));
				                
				                
				                
				                String saleOrderItemCode          =formatter.formatCellValue(row.getCell(15));
				                System.out.println(saleOrderItemCode);
				                String saleOrderCode           = formatter.formatCellValue(row.getCell(16));
				                
				                System.out.println(saleOrderCode);
				                
				                java.util.Date invoiceDateOn  =  row.getCell(17).getDateCellValue();
					            String invoiceDate  = df.format(invoiceDateOn);
					            System.out.println(invoiceDate);
					            String poType            = formatter.formatCellValue(row.getCell(18));
					            java.util.Date gatepassDateOn  =  row.getCell(19).getDateCellValue();
					            String gatepassDate  = df.format(gatepassDateOn);
					            
					            System.out.println(poType);
					            
					          /*  String sql2 ="select levels from myntra_sku_level where sku=?";
					               PreparedStatement preparedstmt3 = conn.prepareStatement(sql2);
					               preparedstmt3.setString(1, vendorStyleCode);
					               ResultSet rscheckkey1 = preparedstmt3.executeQuery();
					               while(rscheckkey1.next()) {
					            	   levels = rscheckkey1.getString("levels");
					               }
					               String uniqueKey = levels+"National";
						              System.out.println(uniqueKey);
						              
						              String sql3 =" select logisticCost from myntra_reverse_logistics where uniqueKey=?";
						              PreparedStatement preparedstmt4 = conn.prepareStatement(sql3);
						               preparedstmt4.setString(1, uniqueKey);
						               
						               ResultSet rscheckkey2 = preparedstmt4.executeQuery();
						               while(rscheckkey2.next()) {
						            	   revlogistics = rscheckkey2.getDouble("logisticCost");
						               }
						               
						               System.out.println(revlogistics);
						               
					               
						               String sql4 ="select fwdLogistics,pickandpackFee,paymentFee from myntra_order where itemCode=?";
						               PreparedStatement preparedstmt5 = conn.prepareStatement(sql4);
						               preparedstmt5.setString(1, itemCode);
						               
						               ResultSet rscheckkey3 = preparedstmt5.executeQuery();
						               while(rscheckkey3.next()) {
						            	   fwdlogistics = rscheckkey3.getDouble("fwdLogistics");
						            	   pickandpackFee =rscheckkey3.getDouble("pickandpackFee");
						            	   paymentFee =rscheckkey3.getDouble("paymentFee");
						               }
						               
						               estimatedRevCharges =(fwdlogistics+revlogistics+pickandpackFee+paymentFee)*1.18;       */
					            
					            
					            String sql = " INSERT ignore INTO myntra_gatepass values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							      PreparedStatement preparedstmt = conn.prepareStatement(sql);
							     
							      
							      preparedstmt.setString(1, gatepassNo);
							      preparedstmt.setString(2, brand);
							      preparedstmt.setString(3, po);
							      preparedstmt.setString(4, myntraSKU);
							      preparedstmt.setString(5, vendorInvoice);
							      preparedstmt.setString(6, vendorStyleCode);
							      preparedstmt.setString(7, color);
							      preparedstmt.setString(8, size);
							      preparedstmt.setInt(9, qty);
							      preparedstmt.setDouble(10, rateWoTax);
							      preparedstmt.setDouble(11, landedPrice);
							      preparedstmt.setDouble(12, total);
							      preparedstmt.setString(13, reason);
							      preparedstmt.setString(14, itemCode);
							      
							      preparedstmt.setString(15, saleOrderItemCode);
							      preparedstmt.setString(16, saleOrderCode);
							      preparedstmt.setString(17, invoiceDate);
							      
							      preparedstmt.setString(18, poType);
							      preparedstmt.setString(19, gatepassDate);
							      
							      
							      preparedstmt.execute(); 
							    
							      String sql1 = "UPDATE IGNORE myntra_order set returnStatus=?,returnReason=? where itemCode=?";
							      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
							      preparedstmt1.setString(1, "Yes");
							      preparedstmt1.setString(2, reason);
							      preparedstmt1.setString(3, itemCode);
							      preparedstmt1.execute();
							      
							      String sql6 = "UPDATE IGNORE myntra_order_sales_order_code_na set returnStatus=?,returnReason=? where itemCode=?";
							      PreparedStatement preparedstmt6 = conn.prepareStatement(sql6);
							      preparedstmt6.setString(1, "Yes");
							      preparedstmt6.setString(2, reason);
							      preparedstmt6.setString(3, itemCode);
							      preparedstmt6.execute();
								    
								  					      				                
				                  System.out.println("Imported Rows"+i);
				                }
				            }
				     
				      
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e1){
			      //Handle errors for Class.forName
			      e1.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			   path.setText("Transfer Complete");
			
			}
		});
		headerLabel.setBounds(150, 11, 400, 31);
		statusLabel.setBounds(180, 340, 312, 31);
		headerLabel.setText("Popnetic Warehousing System");
		controlPanel.add(openfile);
		controlPanel.add(path);
		controlPanel.add(transfer);
		controlPanel.add(back);
	}
		



	
	//Limeroad return Launch code
	
	private void limeroadreturnLaunch() {
		prepareGUI("Limeroad",700,398);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosinglimeroadLaunch(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		
		
		JLabel orderNumber = new JLabel("Order Number");
		orderNumber.setForeground(new Color(70, 130, 180));
		orderNumber.setFont(new Font("Times New Roman", Font.BOLD, 15));
		orderNumber.setBounds(160, 160, 200, 20);
		orderNumber.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField orderNumberText = new JTextField();
		orderNumberText.setForeground(SystemColor.desktop);
		orderNumberText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		orderNumberText.setBounds(350, 160, 200, 20);
		orderNumberText.setColumns(10);
		
		JButton back = new JButton("Back");
		back.setBounds(300, 300, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent backlimeroadreturnOrderEvent) {
				mainFrame.dispose();
				returnOrderLaunch();
			}
		});
		
		
		orderNumberText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent orderNumberAddEvent) {
				
				String dataorderNumber = orderNumberText.getText();
				
				Connection conn = null;
				   Statement stmt1 = null;
				   Date currentDatetime = new Date(System.currentTimeMillis());
				   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
				   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
				   try{
						 //STEP 2: Register JDBC driver
						      Class.forName("com.mysql.jdbc.Driver");

						      //STEP 3: Open a connection
						      System.out.println("Connecting to a selected database...");
						      conn = DriverManager.getConnection(DB_URL, USER, PASS);
						      System.out.println("Connected database successfully...");
					      
					      //STEP 4: Execute a query
					      System.out.println("Inserting records into the table...");
					      
					      String sql = " INSERT INTO lr_return(orderNumber,date,time) Values(?,?,?)";
					      PreparedStatement preparedstmt = conn.prepareStatement(sql);
					      preparedstmt.setString(1, dataorderNumber);
					      
					      
					      preparedstmt.setDate(2, sqlDate);
					      preparedstmt.setTimestamp(3, timestamp);
					      preparedstmt.executeUpdate();
					      System.out.println("Inserted records into the table...");
					      
					      orderNumberText.setText("");
					      orderNumberText.requestFocus();
					      
					     
					      
				}catch(SQLException se){
				      //Handle errors for JDBC
				      se.printStackTrace();
				   }catch(Exception e1){
				      //Handle errors for Class.forName
				      e1.printStackTrace();
				   }finally{
				      //finally block used to close resources
				      try{
				         if(stmt1!=null)
				            conn.close();
				      }catch(SQLException se){
				      }// do nothing
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
				   }//end try
				   System.out.println("Goodbye!");
				
			}
		});
		
		
		
		headerLabel.setBounds(150, 11, 400, 31);
		statusLabel.setBounds(180, 340, 312, 31);
		headerLabel.setText("Popnetic Warehousing System");
			
		controlPanel.add(orderNumberText);
		controlPanel.add(orderNumber);
	   
		controlPanel.add(back);
		
		
		

	}

	
	//Flipkart Return launch code
	
	private void flipkartreturnLaunch() {
		prepareGUI("Flipkart Return",700,398);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosinglimeroadLaunch(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		
		
		JButton openfile = new JButton("Open");
		openfile.setForeground(SystemColor.desktop);
		openfile.setBackground(SystemColor.activeCaption);
		openfile.setBounds(150, 140, 94, 31);
		openfile.setHorizontalAlignment(SwingConstants.LEFT);
		
		JButton transfer = new JButton("Transfer");
		transfer.setForeground(SystemColor.desktop);
		transfer.setBackground(SystemColor.activeCaption);
		transfer.setBounds(450, 140, 94, 31);
		
		
		JButton back = new JButton("Back");
		back.setBounds(300, 300, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent backflipkartOrderEvent) {
				
				mainFrame.dispose();
				returnOrderLaunch();
				
			}
		});
		JLabel path = new JLabel("NO FILES Selected");
		path.setBounds(250, 260, 300, 31);
		
		openfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent openfileEvent) {
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
				 int r = j.showOpenDialog(null); 
				 if (r == JFileChooser.APPROVE_OPTION) 
					  
		            { 
		                // set the label to the path of the selected file 
		                path.setText(j.getSelectedFile().getAbsolutePath()); 
		            } 
		            // if the user cancelled the operation 
		            else
		                path.setText("the user cancelled the operation");
				
			}
			
			
			
		});
		
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent transferEvent) {
				
				Connection conn = null;
				
				   Statement stmt = null;
				   
				   try{
					 //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");

					      //STEP 3: Open a connection
					      System.out.println("Connecting to a selected database...");
					      conn = DriverManager.getConnection(DB_URL, USER, PASS);
					      System.out.println("Connected database successfully...");
					     // PreparedStatement pstm = null ;
					      
					      
					      //Reading the selected excel File
					     
					      FileInputStream input = new FileInputStream(path.getText());
					      System.out.println(input);
					      POIFSFileSystem fs = new POIFSFileSystem( input );
					      HSSFWorkbook wb = new HSSFWorkbook(fs);
					      HSSFSheet sheet = wb.getSheetAt(0);
					      Row row;
					      for(int i=1; i<=sheet.getLastRowNum(); i++){
				                row = sheet.getRow(i);
				                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				                DataFormatter formatter = new DataFormatter();
				               
				               //System.out.println(reportDate);
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
				               
				              
				               
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
				            //   Date orderedOn = Date.valueOf(orderedOnString);
				                //System.out.println(orderOn);
				                
				                java.util.Date returnApprovalDateOn  =  row.getCell(0).getDateCellValue();
					            String returnApprovalDate  = df.format(returnApprovalDateOn);
					            
					            java.util.Date returnRequestedDateOn  =  row.getCell(1).getDateCellValue();
					            String returnRequestedDate   = df.format(returnRequestedDateOn);
				                
					            String returnID   = formatter.formatCellValue(row.getCell(2));
					            
					            String trackingID    = formatter.formatCellValue(row.getCell(3));
					            
					            String orderID     = formatter.formatCellValue(row.getCell(4));
					            
					            String orderItemID      = formatter.formatCellValue(row.getCell(5));
					            
					            String returnType       = formatter.formatCellValue(row.getCell(6));
					            
					            String returnSubType        = formatter.formatCellValue(row.getCell(7));
					            
					            String returnStatus         = formatter.formatCellValue(row.getCell(9));
					            
					            String sku          = formatter.formatCellValue(row.getCell(10));
					            
					            String fsn          = formatter.formatCellValue(row.getCell(11));
					            
					            String ffType	          = formatter.formatCellValue(row.getCell(13));
					            
					            java.util.Date completedDateOn  =  row.getCell(17).getDateCellValue();
					            String completedDate    = df.format(completedDateOn);
					            
					            String returnSubReason 	          = formatter.formatCellValue(row.getCell(19));
					            
					            String buyerName  	          = formatter.formatCellValue(row.getCell(21));
					            
					            String buyerAddress   	          = formatter.formatCellValue(row.getCell(22));
					            
					            Double totalPrice      = (Double) row.getCell(25).getNumericCellValue();
					            
					            int quantity   =(int) row.getCell(26).getNumericCellValue();
					            
					            String vendorName    	          = formatter.formatCellValue(row.getCell(29));
					            
					            String locationName     	          = formatter.formatCellValue(row.getCell(30));
					              
					           String uniqueKey = trackingID + orderItemID;
					           
					           double collectionFee =0;
					           double fwdShippingFee=0;
					           double revShippingFee=0;
					           double estimatedReturnCharges =0;
					           
				        			                
					            String sql = " INSERT ignore INTO flipkart_return values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							      PreparedStatement preparedstmt = conn.prepareStatement(sql);
							      String sql1 = "UPDATE IGNORE flipkart_order set returnStatus=?,returnReason=? where orderItemID =?";
							      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
							      preparedstmt1.setString(1, "Yes");
							      preparedstmt1.setString(2, returnType );
							      preparedstmt1.setString(3, orderItemID );
							      
							      
							      
							      String sql2 ="update ignore flipkart_return set completedDate=? where uniqueKey= ?";
							      PreparedStatement preparedstmt2 = conn.prepareStatement(sql2);
							      preparedstmt2.setString(1, completedDate);
							      preparedstmt2.setString(2, uniqueKey);
							      
							      
							      			if(returnType.equals("Customer Return")) {
							      				
							      				String sql3 ="select collectionFee,fwdShippingFee from flipkart_order where orderItemID=?";
									               PreparedStatement preparedstmt3 = conn.prepareStatement(sql3);
									               preparedstmt3.setString(1, orderItemID);
									               
									               ResultSet rscheckkey = preparedstmt3.executeQuery();
												      
										            while(rscheckkey.next()) {
										            	collectionFee = rscheckkey.getDouble("collectionFee");
				 				            	        fwdShippingFee =rscheckkey.getDouble("fwdShippingFee");
										            }
										            
										            if(fwdShippingFee==36) {
										            	revShippingFee=35;
										            }else if(fwdShippingFee==42.3) {
										            	revShippingFee=55;
										            }else {
										            	revShippingFee=75;
										            }
										            
										            estimatedReturnCharges = collectionFee + revShippingFee+fwdShippingFee;   
							      			}else {
							      				estimatedReturnCharges = collectionFee + revShippingFee+fwdShippingFee;
							      			}
							      			
							      			
							     						     
							      preparedstmt.setString(1, uniqueKey );
							      preparedstmt.setString(2, returnApprovalDate );
							      preparedstmt.setString(3, returnRequestedDate );
							      preparedstmt.setString(4, returnID );
							      preparedstmt.setString(5, trackingID );
							      preparedstmt.setString(6, orderID );
							      preparedstmt.setString(7, orderItemID );
							      preparedstmt.setString(8, returnType );
							      preparedstmt.setString(9, returnSubType );
							      preparedstmt.setString(10, returnStatus );
							      preparedstmt.setString(11, sku );
							      preparedstmt.setString(12, fsn );
							      preparedstmt.setString(13, ffType	);
							      preparedstmt.setString(14, completedDate );
							      preparedstmt.setString(15, returnSubReason );
							      
							      preparedstmt.setString(16, buyerName );
							      preparedstmt.setString(17, buyerAddress );
							      preparedstmt.setDouble(18, totalPrice );
							      
							      preparedstmt.setInt(19, quantity );
							      preparedstmt.setString(20, vendorName  );
							      preparedstmt.setString(21, locationName  );
							      preparedstmt.setDouble(22, collectionFee );
							      preparedstmt.setDouble(23, fwdShippingFee );
							      preparedstmt.setDouble(24, revShippingFee );
							      preparedstmt.setDouble(25, estimatedReturnCharges );
							      
							      preparedstmt.execute(); 
							      preparedstmt1.execute();
							      preparedstmt2.execute();
				              
				                
				               
							      System.out.println("Imported Rows"+i);
				            }
				     
				      
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e1){
			      //Handle errors for Class.forName
			      e1.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			   path.setText("Transfer Complete");
			
			}
		});
		headerLabel.setBounds(150, 11, 400, 31);
		statusLabel.setBounds(180, 340, 312, 31);
		headerLabel.setText("Popnetic Warehousing System");
		controlPanel.add(openfile);
		controlPanel.add(path);
		controlPanel.add(transfer);
		controlPanel.add(back);
		
		

	}
	
	
	//Flipkart Payment Code

	private void flipkartPaymentLaunch() {
		prepareGUI("Flipkart",700,398);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosinglimeroadLaunch(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		
		
		JButton openfile = new JButton("Open");
		openfile.setForeground(SystemColor.desktop);
		openfile.setBackground(SystemColor.activeCaption);
		openfile.setBounds(150, 140, 94, 31);
		openfile.setHorizontalAlignment(SwingConstants.LEFT);
		
		JButton transfer = new JButton("Transfer");
		transfer.setForeground(SystemColor.desktop);
		transfer.setBackground(SystemColor.activeCaption);
		transfer.setBounds(450, 140, 94, 31);
		
		
		JButton back = new JButton("Back");
		back.setBounds(300, 300, 94, 31);
		back.setForeground(SystemColor.desktop);
		back.setBackground(SystemColor.controlDkShadow);
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent backflipkartOrderEvent) {
				
				mainFrame.dispose();
				paymentLaunch();
				
			}
		});
		JLabel path = new JLabel("NO FILES Selected");
		path.setBounds(250, 260, 300, 31);
		
		openfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent openfileEvent) {
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
				 int r = j.showOpenDialog(null); 
				 if (r == JFileChooser.APPROVE_OPTION) 
					  
		            { 
		                // set the label to the path of the selected file 
		                path.setText(j.getSelectedFile().getAbsolutePath()); 
		            } 
		            // if the user cancelled the operation 
		            else
		                path.setText("the user cancelled the operation");
				
			}
			
			
			
		});
		
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent transferEvent) {
				
				Connection conn = null;
				
				   Statement stmt = null;
				   
				   try{
					 //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");

					      //STEP 3: Open a connection
					      System.out.println("Connecting to a selected database...");
					      conn = DriverManager.getConnection(DB_URL, USER, PASS);
					      System.out.println("Connected database successfully...");
					     // PreparedStatement pstm = null ;
					      
					      
					      //Reading the selected excel File
					     
					      FileInputStream input = new FileInputStream(path.getText());
					      System.out.println(input);
					      POIFSFileSystem fs = new POIFSFileSystem( input );
					      HSSFWorkbook wb = new HSSFWorkbook(fs);
					      HSSFSheet sheet = wb.getSheetAt(1);
					      Row row;
					      for(int i=2; i<=sheet.getLastRowNum(); i++){
				                row = sheet.getRow(i);
				                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				                DataFormatter formatter = new DataFormatter();
				                String uniqueKeyCheck = "NULL";
				               
				               //System.out.println(reportDate);
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
				               
				              
				               
				               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
				            //   Date orderedOn = Date.valueOf(orderedOnString);
				                //System.out.println(orderOn);
				                
				                
				                
				                String neftID    = formatter.formatCellValue(row.getCell(0));
					            System.out.println(neftID);
					            String payDate      = formatter.formatCellValue(row.getCell(1));
					            
					            //String payDate     = df.format(payDateOn);
					            System.out.println(payDate);
					            Double settlementValue       = (Double) row.getCell(2).getNumericCellValue();
					            System.out.println(settlementValue);
					            String orderID     = formatter.formatCellValue(row.getCell(4));
					            System.out.println(orderID);
					            String orderItemID      = formatter.formatCellValue(row.getCell(5));
					            System.out.println(orderItemID);
					            Double saleAmount        = (Double) row.getCell(6).getNumericCellValue();
					            System.out.println(saleAmount);
					            Double totalOfferAmount         = (Double) row.getCell(7).getNumericCellValue();
					            System.out.println(totalOfferAmount);
					            Double myshare          = (Double) row.getCell(8).getNumericCellValue();
					            System.out.println(myshare);
					            Double customerShippingAmt           = (Double) row.getCell(9).getNumericCellValue();
					            System.out.println(customerShippingAmt);
					            Double marketplaceFee            = (Double) row.getCell(10).getNumericCellValue();
					            System.out.println(marketplaceFee);
					            Double tcs            = (Double) row.getCell(11).getNumericCellValue();
					            System.out.println(tcs);
					            Double taxes             = (Double) row.getCell(13).getNumericCellValue();
					            System.out.println(taxes);
					            Double refund             = (Double) row.getCell(15).getNumericCellValue();
					            System.out.println(refund);
					            //java.util.Date orderDateOn  =  row.getCell(16).getDateCellValue();
					            //String orderDate      = df.format(orderDateOn);
					            String orderDate      = formatter.formatCellValue(row.getCell(17));
					            System.out.println(orderDate);
					            //java.util.Date dispatchDateOn  =  row.getCell(17).getDateCellValue();
					            //String dispatchDate       = df.format(dispatchDateOn );
					            String dispatchDate      = formatter.formatCellValue(row.getCell(18));
					            System.out.println(dispatchDate);
					            String orderType      = formatter.formatCellValue(row.getCell(19));
					            System.out.println(orderType);
					            String fullfilmentType       = formatter.formatCellValue(row.getCell(20));
					            System.out.println(fullfilmentType);
					            String sellerSku       = formatter.formatCellValue(row.getCell(21));
					            System.out.println(sellerSku);
					            int qty   =(int) row.getCell(22).getNumericCellValue();
					            System.out.println(qty);
					            String returnType        = formatter.formatCellValue(row.getCell(25));
					            System.out.println(returnType);
					            Double commissionRate              = (Double) row.getCell(33).getNumericCellValue();
					            System.out.println(commissionRate);
					            Double commission              = (Double) row.getCell(34).getNumericCellValue();
					            System.out.println(commission);
					            Double commissionWaiver               = (Double) row.getCell(35).getNumericCellValue();
					            System.out.println(commissionWaiver);
					            Double collectionFee                = (Double) row.getCell(36).getNumericCellValue();
					            System.out.println(collectionFee);
					            Double collectionWaiver                 = (Double) row.getCell(37).getNumericCellValue();
					            System.out.println(collectionWaiver);
					            Double fixedFee                  = (Double) row.getCell(38).getNumericCellValue();
					            System.out.println(fixedFee);
					            Double fixedwaiver                   = (Double) row.getCell(39).getNumericCellValue();
					            System.out.println(fixedwaiver);
					            Double customerShippingFee                    = (Double) row.getCell(48).getNumericCellValue();
					            System.out.println(customerShippingFee);
					            Double shippingFee                     = (Double) row.getCell(49).getNumericCellValue();
					            System.out.println(shippingFee);
					            Double reverseShipping                      = (Double) row.getCell(50).getNumericCellValue();
					            System.out.println(reverseShipping);
					            //Double sellerDeadWeight                       = (Double) row.getCell(59).getNumericCellValue();
					            String sellerDeadWeight        = formatter.formatCellValue(row.getCell(60));
					            System.out.println(sellerDeadWeight);
					           // Double volumetricWeight                        = (Double) row.getCell(61).getNumericCellValue();
					            String volumetricWeight        = formatter.formatCellValue(row.getCell(62));
					            System.out.println(volumetricWeight);
					            
					            String chargeableType        = formatter.formatCellValue(row.getCell(63));
					            
					            String chargeableSlab         = formatter.formatCellValue(row.getCell(64));
					            
					            String shippingZone          = formatter.formatCellValue(row.getCell(65));
					            
					            String invoiceID           = formatter.formatCellValue(row.getCell(67));
					            System.out.println(invoiceID);
					            Double tds                  = (Double) row.getCell(12).getNumericCellValue();
					            System.out.println(tds);
					            
					            //java.util.Date InvoiceDateOn  =  row.getCell(67).getDateCellValue();
					            //String InvoiceDate        = df.format(InvoiceDateOn );
					            String InvoiceDate           = formatter.formatCellValue(row.getCell(68));
					            
					            Double InvoiceAmount                         = (Double) row.getCell(69).getNumericCellValue();
					            	
					            String uniqueKey1 = neftID + orderItemID;
					            
					           
					            
					            PreparedStatement stmtcheckUniqueKey = conn.prepareStatement("Select uniqueKey from flipkart_payment where orderItemID=?");
					            stmtcheckUniqueKey.setString(1, "'"+orderItemID);
							      ResultSet rscheckkey = stmtcheckUniqueKey.executeQuery();
							      
					            while(rscheckkey.next()) {
					            	uniqueKeyCheck = rscheckkey.getString("uniqueKey");
					            }
							      if(uniqueKey1.equals(uniqueKeyCheck)) {
							    	  System.out.println("No need to Update the Value");
							      }else {
					            String sql1 = "UPDATE IGNORE flipkart_order set paymentStatus=?,reconcillationStatus=?,settledValue= settledValue +? where orderItemID =?";
							      PreparedStatement preparedstmt1 = conn.prepareStatement(sql1);
							      preparedstmt1.setString(1, "Yes");
							      preparedstmt1.setString(2, "No");
							      preparedstmt1.setDouble(3, settlementValue  );
							      preparedstmt1.setString(4, "'"+orderItemID  );
							      preparedstmt1.execute();
							      }
				            				        			                
					            String sql = " INSERT ignore INTO flipkart_payment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							      PreparedStatement preparedstmt = conn.prepareStatement(sql);
							      
							      preparedstmt.setString(1, uniqueKey1);
							      preparedstmt.setString(2, neftID  );
							      preparedstmt.setString(3, payDate  );
							      preparedstmt.setDouble(4, settlementValue  );
							      preparedstmt.setString(5, orderID  );
							      preparedstmt.setString(6, "'"+orderItemID  );
							      preparedstmt.setDouble(7, saleAmount  );
							      preparedstmt.setDouble(8, totalOfferAmount  );
							      preparedstmt.setDouble(9, myshare  );
							      preparedstmt.setDouble(10, customerShippingAmt  );
							      preparedstmt.setDouble(11, marketplaceFee  );
							      preparedstmt.setDouble(12, tcs  );
							      preparedstmt.setDouble(13, taxes 	);
							      preparedstmt.setDouble(14, refund  );
							      preparedstmt.setString(15, orderDate  );
							      
							      preparedstmt.setString(16, dispatchDate  );
							      preparedstmt.setString(17, orderType  );
							      preparedstmt.setString(18, fullfilmentType  );
							      
							      preparedstmt.setString(19, sellerSku  );
							      preparedstmt.setInt(20, qty   );
							      preparedstmt.setString(21, returnType   );
							      
							      preparedstmt.setDouble(22, commissionRate   );
							      preparedstmt.setDouble(23, commission    );
							      preparedstmt.setDouble(24, commissionWaiver    );
							      preparedstmt.setDouble(25, collectionFee    );
							      
							      preparedstmt.setDouble(26, collectionWaiver    );
							      
							      preparedstmt.setDouble(27, fixedFee    );
							      preparedstmt.setDouble(28, fixedwaiver    );
							      preparedstmt.setDouble(29, customerShippingFee    );
							      
							      preparedstmt.setDouble(30, shippingFee    );
							      preparedstmt.setDouble(31, reverseShipping    );
							      preparedstmt.setString(32, sellerDeadWeight    );
							      preparedstmt.setString(33, volumetricWeight    );
							      
							      preparedstmt.setString(34, chargeableType    );
							      preparedstmt.setString(35, chargeableSlab    );
							      preparedstmt.setString(36, shippingZone    );
							      preparedstmt.setString(37, invoiceID    );
							      preparedstmt.setString(38, InvoiceDate    );
							      preparedstmt.setDouble(39, InvoiceAmount    );
							      preparedstmt.setDouble(40, tds   );
							      						      							      
							      preparedstmt.execute(); 
							      				              				                				               
							      System.out.println("Imported Rows"+i);
				            }
				     
				      
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e1){
			      //Handle errors for Class.forName
			      e1.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			   path.setText("Transfer Complete");
			
			}
		});
		headerLabel.setBounds(150, 11, 400, 31);
		statusLabel.setBounds(180, 340, 312, 31);
		headerLabel.setText("Popnetic Warehousing System");
		controlPanel.add(openfile);
		controlPanel.add(path);
		controlPanel.add(transfer);
		controlPanel.add(back);
		
		

	}

	
	
// Limeroad Launch Window Code

private void limeroadLaunch() {
	prepareGUI("Limeroad",700,398);
	
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosinglimeroadLaunch(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	JLabel manifestID = new JLabel("Manifest ID");
	manifestID.setForeground(new Color(70, 130, 180));
	manifestID.setFont(new Font("Times New Roman", Font.BOLD, 15));
	manifestID.setBounds(160, 80, 120, 20);
	manifestID.setHorizontalAlignment(SwingConstants.LEFT);
	
	JTextField manifestIDText = new JTextField("");
	manifestIDText.setForeground(SystemColor.desktop);
	manifestIDText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	manifestIDText.setBounds(350, 80, 200, 20);
	manifestIDText.setColumns(10);
	
	JLabel courierName = new JLabel("Courier Carrier");
	courierName.setForeground(new Color(70, 130, 180));
	courierName.setFont(new Font("Times New Roman", Font.BOLD, 15));
	courierName.setBounds(160, 120, 200, 20);
	courierName.setHorizontalAlignment(SwingConstants.LEFT);
	
	JComboBox courierNameComboBox= new JComboBox();
	courierNameComboBox.setBounds(350, 120, 200, 20);
	courierNameComboBox.addItem("Bluedart Surface");
	courierNameComboBox.addItem("Ecomexpress");
	courierNameComboBox.addItem("Bluedart Premium Surface");
	courierNameComboBox.addItem("Delivery Surface");
	courierNameComboBox.addItem("Wowexpress Surface");
	courierNameComboBox.addItem("Xpressbees");
	
	JLabel orderNumber = new JLabel("Order Number");
	orderNumber.setForeground(new Color(70, 130, 180));
	orderNumber.setFont(new Font("Times New Roman", Font.BOLD, 15));
	orderNumber.setBounds(160, 160, 200, 20);
	orderNumber.setHorizontalAlignment(SwingConstants.LEFT);
	
	JTextField orderNumberText = new JTextField();
	orderNumberText.setForeground(SystemColor.desktop);
	orderNumberText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	orderNumberText.setBounds(350, 160, 200, 20);
	orderNumberText.setColumns(10);
	
	JButton back = new JButton("Back");
	back.setBounds(300, 300, 94, 31);
	back.setForeground(SystemColor.desktop);
	back.setBackground(SystemColor.controlDkShadow);
	
	
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent backlrOrderEvent) {
			
			mainFrame.dispose();
			orderLaunch();
			
		}
	});
	
	
	orderNumberText.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent orderNumberAddEvent) {
		
			String datamanifestID = manifestIDText.getText();
			String datacourierName = ""+courierNameComboBox.getItemAt(courierNameComboBox.getSelectedIndex());
			String dataorderNumber = orderNumberText.getText();
			
			Connection conn = null;
			   Statement stmt1 = null;
			   Date currentDatetime = new Date(System.currentTimeMillis());
			   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
			   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
			   try{
					 //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");

					      //STEP 3: Open a connection
					      System.out.println("Connecting to a selected database...");
					      conn = DriverManager.getConnection(DB_URL, USER, PASS);
					      System.out.println("Connected database successfully...");
				      
				      //STEP 4: Execute a query
				      System.out.println("Inserting records into the table...");
				      
				      String sql = " INSERT INTO lr_order(orderNumber,manifestID,courierName,date,time) Values(?,?,?,?,?)";
				      PreparedStatement preparedstmt = conn.prepareStatement(sql);
				      preparedstmt.setString(1, dataorderNumber);
				      preparedstmt.setString(2, datamanifestID);
				      preparedstmt.setString(3, datacourierName);
				      
				      preparedstmt.setDate(4, sqlDate);
				      preparedstmt.setTimestamp(5, timestamp);
				      preparedstmt.executeUpdate();
				      System.out.println("Inserted records into the table...");
				      
				      orderNumberText.setText("");
				      orderNumberText.requestFocus();
				      
				     
				      
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e1){
			      //Handle errors for Class.forName
			      e1.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt1!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			
		}
	});
	
	
	
	headerLabel.setBounds(150, 11, 400, 31);
	statusLabel.setBounds(180, 340, 312, 31);
	headerLabel.setText("Popnetic Warehousing System");
	
	controlPanel.add(orderNumberText);
	controlPanel.add(orderNumber);
    controlPanel.add(courierNameComboBox);
    controlPanel.add(courierName);
    controlPanel.add(manifestID);
    controlPanel.add(manifestIDText);
	controlPanel.add(back);
	
	
	

}


//Flipkart Order Launch Code
private void flipkartLaunch() {
	prepareGUI("Flipkart Order",700,398);
	
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosinglimeroadLaunch(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	
	
	JButton openfile = new JButton("Open");
	openfile.setForeground(SystemColor.desktop);
	openfile.setBackground(SystemColor.activeCaption);
	openfile.setBounds(150, 140, 94, 31);
	openfile.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton transfer = new JButton("Transfer");
	transfer.setForeground(SystemColor.desktop);
	transfer.setBackground(SystemColor.activeCaption);
	transfer.setBounds(450, 140, 94, 31);
	
	
	JButton back = new JButton("Back");
	back.setBounds(300, 300, 94, 31);
	back.setForeground(SystemColor.desktop);
	back.setBackground(SystemColor.controlDkShadow);
	
	
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent backflipkartOrderEvent) {
			
			mainFrame.dispose();
			orderLaunch();
			
		}
	});
	JLabel path = new JLabel("NO FILES Selected");
	path.setBounds(250, 260, 300, 31);
	
	openfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent openfileEvent) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			 int r = j.showOpenDialog(null); 
			 if (r == JFileChooser.APPROVE_OPTION) 
				  
	            { 
	                // set the label to the path of the selected file 
	                path.setText(j.getSelectedFile().getAbsolutePath()); 
	            } 
	            // if the user cancelled the operation 
	            else
	                path.setText("the user cancelled the operation");
			
		}
		
		
		
	});
	
	transfer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent transferEvent) {
			
			Connection conn = null;
			PreparedStatement preparedstmt1;
			   Statement stmt = null;
			   
			   try{
				 //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");

				      //STEP 3: Open a connection
				      System.out.println("Connecting to a selected database...");
				      conn = DriverManager.getConnection(DB_URL, USER, PASS);
				      System.out.println("Connected database successfully...");
				     // PreparedStatement pstm = null ;
				      
				      
				      //Reading the selected excel File
				     
				      FileInputStream input = new FileInputStream(path.getText());
				      System.out.println(input);
				      POIFSFileSystem fs = new POIFSFileSystem( input );
				      HSSFWorkbook wb = new HSSFWorkbook(fs);
				      HSSFSheet sheet = wb.getSheetAt(0);
				      Row row;
				      for(int i=1; i<=sheet.getLastRowNum(); i++){
			                row = sheet.getRow(i);
			                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			               java.util.Date orderedOnDate =  row.getCell(0).getDateCellValue();
			               String orderOnDate = df.format(orderedOnDate);
			               DataFormatter formatter = new DataFormatter();
			               
			               //System.out.println(reportDate);
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
			               System.out.println(orderOnDate);
			              
			               
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
			            //   Date orderedOn = Date.valueOf(orderedOnString);
			                //System.out.println(orderOn);
			                String shipmentID = row.getCell(1).getStringCellValue();
			                System.out.println(shipmentID);
			                String orderItemID = row.getCell(2).getStringCellValue();
			                System.out.println(orderItemID);
			                String orderId = row.getCell(3).getStringCellValue();
			                System.out.println(orderId);
			                int hsnCode =(int) row.getCell(4).getNumericCellValue();
			                System.out.println(hsnCode);
			                String orderState = row.getCell(5).getStringCellValue();
			                System.out.println(orderState);
			                String orderType = row.getCell(6).getStringCellValue();
			                System.out.println(orderType);
			                String fsn = row.getCell(7).getStringCellValue();
			                System.out.println(fsn);
			                String sku = row.getCell(8).getStringCellValue();
			                System.out.println(sku);
			                String product  = row.getCell(9).getStringCellValue();
			                System.out.println(product);
			                String invoiceNo  = row.getCell(10).getStringCellValue();
			                System.out.println(invoiceNo);
			                String cgst   = formatter.formatCellValue(row.getCell(11));
			                System.out.println(cgst);
			                String igst   = formatter.formatCellValue(row.getCell(12));
			                System.out.println(igst);
			                String sgst   = formatter.formatCellValue(row.getCell(13));
			                System.out.println(sgst);
			                
			               
			                
			                
			                
			                
			               // String invoiceDateString  = row.getCell(12).getStringCellValue();
			                //Date invoiceDate = Date.valueOf(invoiceDateString);
			                //java.util.Date invoiceDateJava =  row.getCell(14).getDateCellValue();
			                //String invoiceDate = df.format(invoiceDateJava);
			               // System.out.println(invoiceDate);
			                
			                Double invoiceAmount  = (Double) row.getCell(15).getNumericCellValue();
			                System.out.println(invoiceAmount);
			                Double sellingPricePerItem   = (Double) row.getCell(16).getNumericCellValue();
			                System.out.println(sellingPricePerItem);
			                Double shippingChargePerItem    = (Double) row.getCell(17).getNumericCellValue();
			                System.out.println(shippingChargePerItem);
			                int quantity  = (int) row.getCell(18).getNumericCellValue();
			                System.out.println(quantity);
			                Double priceIncFKMPContribution     = (Double) row.getCell(19).getNumericCellValue();
			                System.out.println(priceIncFKMPContribution);
			                String buyerName    = row.getCell(20).getStringCellValue();
			                System.out.println(buyerName);
			                String shipToName     = row.getCell(21).getStringCellValue();
			                System.out.println(shipToName);
			                
			               
			                
			                String addressLine1      = formatter.formatCellValue(row.getCell(22));
			                
			                System.out.println(addressLine1);
			                String addressLine2      = formatter.formatCellValue(row.getCell(23));
			                System.out.println(addressLine2);
			                String city       = row.getCell(24).getStringCellValue();
			                System.out.println(city);
			                String state       = row.getCell(25).getStringCellValue();
			                System.out.println(state);
			                int pincode       = (int) row.getCell(26).getNumericCellValue();
			                System.out.println(pincode);
			                
			               // String dispatchAfterDateString  = row.getCell(25).getStringCellValue();
			               // Date dispatchAfterDate = Date.valueOf(dispatchAfterDateString);
			                java.util.Date dispatchAfterDateJava =  row.getCell(27).getDateCellValue();
			                String dispatchAfterDate = df.format(dispatchAfterDateJava);
			                System.out.println(dispatchAfterDate);
			                //String dispatchByDateString  = row.getCell(26).getStringCellValue();
			                //Date dispatchByDate = Date.valueOf(dispatchByDateString);
			                java.util.Date dispatchByDateJava =  row.getCell(28).getDateCellValue();
			                String dispatchByDate = df.format(dispatchByDateJava);
			                System.out.println(dispatchByDate);
			                
			                String formReq        = row.getCell(29).getStringCellValue();
			                System.out.println(formReq);
			                String trackingID	        = formatter.formatCellValue(row.getCell(30));
			                System.out.println(trackingID);
			                Double packageLength    = (Double) row.getCell(31).getNumericCellValue();
			                System.out.println(packageLength);
			                Double packageBreath     = (Double) row.getCell(32).getNumericCellValue();
			                System.out.println(packageBreath);
			                
			                Double packageHeight     = (Double) row.getCell(33).getNumericCellValue();
			                System.out.println(packageHeight);
			                Double packageWeight     = (Double) row.getCell(34).getNumericCellValue();
			                System.out.println(packageWeight);
			                String readyToMake 	        = row.getCell(35).getStringCellValue();
			                System.out.println(readyToMake);
			               String withAttachment  = row.getCell(36).getStringCellValue();
			               System.out.println(withAttachment);
			               
			               String levelValue="";
			               String category="";
			               double commission_rate=0.0;
			               double commission=0.0;
			                double fixedFee =0.0;
			                double collectionFee=0.0;
			                double shippingFee=0.0;
			                double tcsTax =0.0;
			                double tax =0.0;
			                double extimatedSettledValue=0.0;
			                String zone ="";
			               
			               double taxableValue = invoiceAmount/1.05;
			               
			               if(taxableValue<1000) {
			            	   tcsTax = taxableValue*0.01;
			               }else {
			            	   taxableValue = invoiceAmount/1.12;
			            	   tcsTax =taxableValue*0.01;
			               }
			               System.out.println(taxableValue);
			               System.out.println(tcsTax);
			               
			               if(invoiceAmount<300) {
			            	   levelValue = "Level 1";
			            	  
			               }else if((invoiceAmount>300)&&(invoiceAmount<500)){
			            	   levelValue ="Level 2";
			               }else if((invoiceAmount>500)&&(invoiceAmount<1000)) {
			            	   levelValue="Level 3";
			               }else {
			            	   levelValue="Level 4";
			               }
			               
			               System.out.println(levelValue);
			               
			               String sql2 ="select category from sku_category_mapping where sku=?";
			               PreparedStatement preparedstmt2 = conn.prepareStatement(sql2);
			               preparedstmt2.setString(1, sku);
			               
			               ResultSet rscheckkey = preparedstmt2.executeQuery();
						      
				            while(rscheckkey.next()) {
				            	category = rscheckkey.getString("category");
				            }
				            
				            System.out.println(category);
				            //String uniqueKeyTest =levelValue+category;
				           // System.out.println(uniqueKeyTest);
				            
				            PreparedStatement preparedstmt3 = conn.prepareStatement("select commission_rate from flipkart_commission where levelCategory=? and styleType=?" );
				            preparedstmt3.setString(1, levelValue);
				            preparedstmt3.setString(2, category);
				            ResultSet rscheckkey2 = preparedstmt3.executeQuery();
				            
				            
				            while(rscheckkey2.next()) {
				            	commission_rate = rscheckkey2.getDouble("commission_rate");
				           
				            System.out.println(commission_rate);
				            
				            commission=(commission_rate*invoiceAmount)/100;
				            }
				            System.out.println(commission);
				            if(invoiceAmount<300) {
				            	fixedFee=13;
				            	
				            }else if((invoiceAmount>300)&&(invoiceAmount<500)) {
				            	fixedFee=11;
				            }else if((invoiceAmount>500)&&(invoiceAmount<1000)) {
				            	fixedFee=19;
				            }
				            else {
				            	fixedFee=37;
				            }
				            System.out.println(fixedFee);
				            
				            if(invoiceAmount<750) {
				            	collectionFee = 15;
				            }else {
				            	collectionFee= (invoiceAmount)*0.02;
				            }
				            
				            System.out.println(collectionFee);
				            
				            if(state.equals("Delhi")) {
				            	zone= "local";
				            }else if(city.equals("Greater Noida")||(city.equals("Gurugram"))||(city.equals("Ghaziabad"))||(city.equals("Faridabad"))||(city.equals("Noida"))||(city.equals("Gurgaon"))||(city.equals("Pataudi"))) {
				            	zone="local";
				            }
				            
				            else if((state.equals("Uttar Pradesh")) ||(state.equals("Madhya Pradesh"))||(state.equals("Himachal Pradesh"))||(state.equals("Rajasthan"))||(state.equals("Uttarakhand"))||(state.equals("Punjab"))||(state.equals("Madhya Pradesh"))||(state.equals("Jammu & Kashmir"))||(state.equals("Haryana"))) {
				            	zone="zonal";
				            	
				            }else {
				            	zone="National";
				            }
				            
				            System.out.println(zone);
				            
				           if((zone.equals("local"))) {
				        	   shippingFee = 37.8;
				           }else if((zone.equals("zonal"))) {
				        	   shippingFee = 44.1;
				           }else {
				        	   shippingFee=55.8;
				           }
				           
				           System.out.println(shippingFee);
				            	tax= (commission + shippingFee +fixedFee +collectionFee) *0.18;
				            	
				            	 System.out.println(tax);
				            	
				            	extimatedSettledValue = invoiceAmount -(commission+fixedFee+shippingFee+collectionFee+tcsTax+tax);
				            	 System.out.println(extimatedSettledValue);
			            				               
			               
			               String uniqueKey = trackingID + orderItemID;
			                
			               String sql = " INSERT ignore INTO flipkart_order Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						      PreparedStatement preparedstmt = conn.prepareStatement(sql);
						      
						      preparedstmt.setString(1, uniqueKey);
						      preparedstmt.setString(2, orderOnDate);
						      preparedstmt.setString(3, shipmentID);
						      preparedstmt.setString(4, orderItemID);
						      preparedstmt.setString(5, orderId);
						      preparedstmt.setInt(6, hsnCode);
						      preparedstmt.setString(7, orderState);
						      preparedstmt.setString(8, orderType);
						      preparedstmt.setString(9, fsn);
						      preparedstmt.setString(10, sku);
						      preparedstmt.setString(11, product);
						      preparedstmt.setString(12, invoiceNo);
						      preparedstmt.setString(13, cgst);
						      preparedstmt.setString(14, igst);
						      preparedstmt.setString(15, sgst);
						     // preparedstmt.setString(16, invoiceDate);
						      preparedstmt.setDouble(16, invoiceAmount);
						      preparedstmt.setDouble(17, sellingPricePerItem);
						      preparedstmt.setDouble(18, shippingChargePerItem);
						      preparedstmt.setInt(19, quantity);
						      preparedstmt.setDouble(20, priceIncFKMPContribution);
						      preparedstmt.setString(21, buyerName);
						      preparedstmt.setString(22, shipToName);
						      preparedstmt.setString(23, addressLine1);
						      preparedstmt.setString(24, addressLine2);
						      preparedstmt.setString(25, city);
						      preparedstmt.setString(26, state);
						      preparedstmt.setInt(27, pincode);
						      preparedstmt.setString(28, dispatchAfterDate);
						      preparedstmt.setString(29, dispatchByDate);
						      preparedstmt.setString(30, formReq);
						      preparedstmt.setString(31, trackingID);
						      preparedstmt.setDouble(32, packageLength);
						      preparedstmt.setDouble(33, packageBreath);
						      preparedstmt.setDouble(34, packageHeight);
						      preparedstmt.setDouble(35, packageWeight);
						      preparedstmt.setString(36, readyToMake);
						      preparedstmt.setString(37, withAttachment);
						      preparedstmt.setString(38, "No");
						      preparedstmt.setString(39, "NA");
						      preparedstmt.setString(40, "No");
						      preparedstmt.setDouble(41, 0);
						      preparedstmt.setDouble(42, commission);
						      preparedstmt.setDouble(43, collectionFee);
						      preparedstmt.setDouble(44, fixedFee);
						      preparedstmt.setDouble(45, tcsTax);
						      preparedstmt.setDouble(46, tax);
						      preparedstmt.setDouble(47, shippingFee);
						      preparedstmt.setDouble(48, extimatedSettledValue);
						      preparedstmt.setString(49,"No");
						      preparedstmt.setString(50,"NA");
						      preparedstmt.execute(); 
			              
			                
			               // String sql = "INSERT INTO flipkart_order VALUES('"+orderOnDate+"','"+shipmentID+"','"+orderItemID+"','"+orderId+"','"+hsnCode+"','"+orderState+"','"+orderType+"','"+fsn+"','"+sku+"','"+product+"','"+invoiceNo+"','"+cgst+"','"+igst+"','"+sgst+"','"+invoiceDate+"','"+invoiceAmount+"','"+sellingPricePerItem+"','"+shippingChargePerItem+"','"+quantity+"','"+priceIncFKMPContribution+"','"+buyerName+"','"+shipToName+"','"+addressLine1+"','"+addressLine2+"','"+city+"','"+state+"',"+pincode+",'"+dispatchAfterDate+"','"+dispatchByDate+"','"+formReq+"','"+trackingID+"','"+packageLength+"','"+packageBreath+"','"+packageHeight+"','"+packageWeight+"','"+readyToMake+"','"+withAttachment+"')";
			                		
			                //pstm = (PreparedStatement) conn.prepareStatement(sql);
			               // pstm.execute();
						      System.out.println("Imported Rows"+i);
			            }
			     
			      
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e1){
		      //Handle errors for Class.forName
		      e1.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   path.setText("Transfer Complete");
		}
	});
	headerLabel.setBounds(150, 11, 400, 31);
	statusLabel.setBounds(180, 340, 312, 31);
	headerLabel.setText("Popnetic Warehousing System");
	controlPanel.add(openfile);
	controlPanel.add(path);
	controlPanel.add(transfer);
	controlPanel.add(back);

}

//Myntra Order launch Code
private void myntraLaunch() {
	prepareGUI("Myntra Order",700,398);
	
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosinglimeroadLaunch(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	JButton openfile = new JButton("Open");
	openfile.setForeground(SystemColor.desktop);
	openfile.setBackground(SystemColor.activeCaption);
	openfile.setBounds(150, 140, 94, 31);
	openfile.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton transfer = new JButton("Transfer");
	transfer.setForeground(SystemColor.desktop);
	transfer.setBackground(SystemColor.activeCaption);
	transfer.setBounds(450, 140, 94, 31);
	
	
	JButton back = new JButton("Back");
	back.setBounds(300, 300, 94, 31);
	back.setForeground(SystemColor.desktop);
	back.setBackground(SystemColor.controlDkShadow);
	
	
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent backflipkartOrderEvent) {
			
			mainFrame.dispose();
			orderLaunch();
			
		}
	});
	JLabel path = new JLabel("NO FILES Selected");
	path.setBounds(250, 260, 300, 31);
	
	openfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent openfileEvent) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			 int r = j.showOpenDialog(null); 
			 if (r == JFileChooser.APPROVE_OPTION) 
				  
	            { 
	                // set the label to the path of the selected file 
	                path.setText(j.getSelectedFile().getAbsolutePath()); 
	            } 
	            // if the user cancelled the operation 
	            else
	                path.setText("the user cancelled the operation");
			
		}
		
		
		
	});
	
	transfer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent transferEvent) {
			
			Connection conn = null;
			PreparedStatement preparedstmt1;
			   Statement stmt = null;
			   
			   try{
				 //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");

				      //STEP 3: Open a connection
				      System.out.println("Connecting to a selected database...");
				      conn = DriverManager.getConnection(DB_URL, USER, PASS);
				      System.out.println("Connected database successfully...");
				     // PreparedStatement pstm = null ;
				      
				      
				      //Reading the selected excel File
				     
				      FileInputStream input = new FileInputStream(path.getText());
				      System.out.println(input);
				      POIFSFileSystem fs = new POIFSFileSystem( input );
				      HSSFWorkbook wb = new HSSFWorkbook(fs);
				      HSSFSheet sheet = wb.getSheetAt(0);
				      HSSFRow row;
				      for(int i=1; i<=sheet.getLastRowNum(); i++){
			                row = sheet.getRow(i);
			                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			               Double discount =0.0;
			               Double commission=0.0;
			               String levels="";
			               Double fixedFee = 0.0;
			               Double fwdShipping=0.0;
			               Double sellingPrice =0.0;
			               Double taxablePrice=0.0;
			               Double pickandpackFee=0.0;
			               Double paymentFee=0.0;
			               Double estimatedValue=0.0;
			               Double taxonCommisson =0.0;
			               Double taxonLogistics;
			               Double tcsTax=0.0;
			               //System.out.println(reportDate);
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
			               
			              String uniqueKey1="";
			               
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
			            //   Date orderedOn = Date.valueOf(orderedOnString);
			                //System.out.println(orderOn);
			                String itemCode = row.getCell(0).getStringCellValue();
			                System.out.println(itemCode);
			                
			                String sql5= "select itemCode from myntra_order where itemCode=?";
				            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
				               preparedstmt5.setString(1, itemCode);
				               
				               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
				               while(rscheckkey5.next()) {
				            	   uniqueKey1 = rscheckkey5.getString("itemCode");
				               }
			                if(itemCode.equalsIgnoreCase(uniqueKey1)) {
			                	System.out.println("No Need for operations");
			                }else {
			                java.util.Date itemCreatedOnDate =  row.getCell(1).getDateCellValue();
				            String itemCreatedOn  = df.format(itemCreatedOnDate);
				            DataFormatter formatter = new DataFormatter();
				            
				            java.util.Date itemUpdatedOnDate =  row.getCell(2).getDateCellValue();
				            String itemUpdatedOn   = df.format(itemUpdatedOnDate);
				           
				            
			                String itemstatus  = row.getCell(3).getStringCellValue();
			                
			                String grnNumber = row.getCell(4).getStringCellValue();
			                
			                java.util.Date grnCreatedOnDate =  row.getCell(5).getDateCellValue();
				            String grnCreated   = df.format(grnCreatedOnDate);
				            
				            String vendorCode  = row.getCell(6).getStringCellValue();
				            
				            String facility   = row.getCell(7).getStringCellValue();
				            
				            //8th Cell Value is excluded for entering into database
				            
				            String brand   = row.getCell(9).getStringCellValue();
				            
				            Double mrp   = (Double) row.getCell(10).getNumericCellValue();
				            
				            String itemSkuCode    = row.getCell(11).getStringCellValue();
				            
				            String vendorSku     = row.getCell(12).getStringCellValue();
				            
				            Double unitPrice    = (Double) row.getCell(13).getNumericCellValue();
				            
				            Double unitPriceWithTax     = (Double) row.getCell(14).getNumericCellValue();
				            
				            
				            int hsnCode =(int) row.getCell(20).getNumericCellValue();
				            
				            String invoiceNumber      = row.getCell(21).getStringCellValue();
				            
				            java.util.Date invoiceDateOn  =  row.getCell(22).getDateCellValue();
				            String invoiceDate    = df.format(invoiceDateOn);
				            
				            String poType       = row.getCell(23).getStringCellValue();
				            
				            String poCode        = row.getCell(24).getStringCellValue();
				            
				            java.util.Date poCreatedOn   =  row.getCell(25).getDateCellValue();
				            String poCreated    = df.format(poCreatedOn);
				            
				            String saleOrderCode    = formatter.formatCellValue(row.getCell(26));
				            String uniqueKey2="";
				            String sql6= "select saleOrderCode from myntra_order where saleOrderCode=?";
				            PreparedStatement preparedstmt6 = conn.prepareStatement(sql6);
				               preparedstmt6.setString(1, saleOrderCode);
				               
				               ResultSet rscheckkey6 = preparedstmt6.executeQuery();
				               while(rscheckkey6.next()) {
				            	   uniqueKey2 = rscheckkey6.getString("saleOrderCode");
				               }
				            
				           
				            String sql1= "select discount from myntra_current_discount where sku=?";
				            PreparedStatement preparedstmt2 = conn.prepareStatement(sql1);
				               preparedstmt2.setString(1, vendorSku);
				               
				               ResultSet rscheckkey = preparedstmt2.executeQuery();
				               while(rscheckkey.next()) {
				            	   discount = rscheckkey.getDouble("discount");
				               }
				               
				               
				               sellingPrice= mrp*(100-discount)/100;
				               commission= 0.11*sellingPrice;
				               
				               
				               String sql2 ="select levels from myntra_sku_level where sku=?";
				               PreparedStatement preparedstmt3 = conn.prepareStatement(sql2);
				               preparedstmt3.setString(1, vendorSku);
				               ResultSet rscheckkey1 = preparedstmt3.executeQuery();
				               while(rscheckkey1.next()) {
				            	   levels = rscheckkey1.getString("levels");
				               }
				               System.out.println(levels);
				              String uniqueKey = levels+"National";
				              System.out.println(uniqueKey);
				              String sql3 =" select logisticCost from myntra_forward_logistics where uniqueKey=?";
				              PreparedStatement preparedstmt4 = conn.prepareStatement(sql3);
				               preparedstmt4.setString(1, uniqueKey);
				               
				               ResultSet rscheckkey2 = preparedstmt4.executeQuery();
				               while(rscheckkey2.next()) {
				            	   fwdShipping = rscheckkey2.getDouble("logisticCost");
				               }
				               
				               System.out.println(fwdShipping);
				               if(levels.equalsIgnoreCase("Level 1")) {
				            	   pickandpackFee =15.0;
				               }else if(levels.equalsIgnoreCase("Level 2")) {
				            	   pickandpackFee =20.0;
				               }else if(levels.equalsIgnoreCase("Level 3")) {
				            	   pickandpackFee =25.0;
				               }else if(levels.equalsIgnoreCase("Level 4")) {
				            	   pickandpackFee =35.0;
				               }else {
				            	   pickandpackFee =35.0;
				               }
				               
				               if((sellingPrice*0.02)<20) {
				            	   paymentFee=20.0;
				               }else {
				            	   paymentFee=sellingPrice*0.02;
				               }
				               
				               if(sellingPrice<500) {
				            	   fixedFee=5.0;
				               }else if (sellingPrice>1000 ){
				            	   fixedFee =30.0;
				               }else {
				            	   fixedFee =15.0;
				               }
				            		  
				               
				               taxonCommisson = commission*0.18;
				               taxonLogistics = (pickandpackFee+paymentFee+fwdShipping+fixedFee)*0.18;
				               
				               taxablePrice = sellingPrice/1.05;
				               if(taxablePrice<1000) {
				            	   taxablePrice=sellingPrice/1.05;
				               }else {
				            	   taxablePrice=sellingPrice/1.12;
				               }
				               tcsTax =taxablePrice*0.01;
				               estimatedValue = sellingPrice - (commission +pickandpackFee +paymentFee +fixedFee+fwdShipping +taxonCommisson+taxonLogistics+tcsTax);
				               
				               if(uniqueKey2.equalsIgnoreCase(saleOrderCode)) {
				            	   String sql = " INSERT ignore INTO myntra_order_sales_order_code_na Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
								      PreparedStatement preparedstmt = conn.prepareStatement(sql);
								      
								      preparedstmt.setString(1, itemCode );
								      preparedstmt.setString(2, itemCreatedOn );
								      preparedstmt.setString(3, itemUpdatedOn );
								      preparedstmt.setString(4, itemstatus );
								      preparedstmt.setString(5, grnNumber );
								      preparedstmt.setString(6, grnCreated );
								      preparedstmt.setString(7, vendorCode );
								      preparedstmt.setString(8, facility );
								      preparedstmt.setString(9, brand );
								      preparedstmt.setDouble(10, mrp );
								      preparedstmt.setString(11, itemSkuCode );
								      preparedstmt.setString(12, vendorSku );
								      preparedstmt.setDouble(13, unitPrice );
								      preparedstmt.setDouble(14, unitPriceWithTax );
								      
								      preparedstmt.setInt(15, hsnCode );
								      preparedstmt.setString(16, invoiceNumber );
								      preparedstmt.setString(17, invoiceDate );
								      
								      preparedstmt.setString(18, poType );
								      preparedstmt.setString(19, poCode );
								      preparedstmt.setString(20, poCreated );
								      preparedstmt.setString(21, saleOrderCode );
								      preparedstmt.setString(22, "No" );
								      preparedstmt.setString(23, "NA" );
								      preparedstmt.setString(24, "No" );
								      preparedstmt.setString(25, "No" );
								      preparedstmt.setDouble(26, discount );
								      preparedstmt.setDouble(27, commission );
								      preparedstmt.setDouble(28, fwdShipping );
								      preparedstmt.setDouble(29,  pickandpackFee);
								      preparedstmt.setDouble(30,  fixedFee);
								      preparedstmt.setDouble(31, paymentFee );
								      preparedstmt.setDouble(32, 0 );
								      preparedstmt.setDouble(33,  taxonCommisson);
								      preparedstmt.setDouble(34,  taxonLogistics);
								      preparedstmt.setDouble(35,  tcsTax);
								      preparedstmt.setDouble(36,  estimatedValue);
								      preparedstmt.setDouble(37,  0);
								      preparedstmt.setDouble(38,  0);
								      preparedstmt.setDouble(39,0);
								      preparedstmt.setDouble(40,0);
								      preparedstmt.execute(); 
					              			                		               
								      System.out.println("Imported Rows"+i);
				               }else if(saleOrderCode.equalsIgnoreCase("NA")){
				            	   String sql = " INSERT ignore INTO myntra_order_sales_order_code_na Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
								      PreparedStatement preparedstmt = conn.prepareStatement(sql);
								      
								      preparedstmt.setString(1, itemCode );
								      preparedstmt.setString(2, itemCreatedOn );
								      preparedstmt.setString(3, itemUpdatedOn );
								      preparedstmt.setString(4, itemstatus );
								      preparedstmt.setString(5, grnNumber );
								      preparedstmt.setString(6, grnCreated );
								      preparedstmt.setString(7, vendorCode );
								      preparedstmt.setString(8, facility );
								      preparedstmt.setString(9, brand );
								      preparedstmt.setDouble(10, mrp );
								      preparedstmt.setString(11, itemSkuCode );
								      preparedstmt.setString(12, vendorSku );
								      preparedstmt.setDouble(13, unitPrice );
								      preparedstmt.setDouble(14, unitPriceWithTax );
								      
								      preparedstmt.setInt(15, hsnCode );
								      preparedstmt.setString(16, invoiceNumber );
								      preparedstmt.setString(17, invoiceDate );
								      
								      preparedstmt.setString(18, poType );
								      preparedstmt.setString(19, poCode );
								      preparedstmt.setString(20, poCreated );
								      preparedstmt.setString(21, saleOrderCode );
								      preparedstmt.setString(22, "No" );
								      preparedstmt.setString(23, "NA" );
								      preparedstmt.setString(24, "No" );
								      preparedstmt.setString(25, "No" );
								      preparedstmt.setDouble(26, discount );
								      preparedstmt.setDouble(27, commission );
								      preparedstmt.setDouble(28, fwdShipping );
								      preparedstmt.setDouble(29,  pickandpackFee);
								      preparedstmt.setDouble(30,  fixedFee);
								      preparedstmt.setDouble(31, paymentFee );
								      preparedstmt.setDouble(32, 0 );
								      preparedstmt.setDouble(33,  taxonCommisson);
								      preparedstmt.setDouble(34,  taxonLogistics);
								      preparedstmt.setDouble(35,  tcsTax);
								      preparedstmt.setDouble(36,  estimatedValue);
								      preparedstmt.setDouble(37,  0);
								      preparedstmt.setDouble(38,  0);
								      preparedstmt.setDouble(39,0);
								      preparedstmt.setDouble(40,0);
								      preparedstmt.execute(); 
					              			                		               
								      System.out.println("Imported Rows"+i);
				               }else {
				              
				               if(poType.equalsIgnoreCase("FBM")) {
			                
			               String sql = " INSERT ignore INTO myntra_order Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						      PreparedStatement preparedstmt = conn.prepareStatement(sql);
						      
						      preparedstmt.setString(1, itemCode );
						      preparedstmt.setString(2, itemCreatedOn );
						      preparedstmt.setString(3, itemUpdatedOn );
						      preparedstmt.setString(4, itemstatus );
						      preparedstmt.setString(5, grnNumber );
						      preparedstmt.setString(6, grnCreated );
						      preparedstmt.setString(7, vendorCode );
						      preparedstmt.setString(8, facility );
						      preparedstmt.setString(9, brand );
						      preparedstmt.setDouble(10, mrp );
						      preparedstmt.setString(11, itemSkuCode );
						      preparedstmt.setString(12, vendorSku );
						      preparedstmt.setDouble(13, unitPrice );
						      preparedstmt.setDouble(14, unitPriceWithTax );
						      
						      preparedstmt.setInt(15, hsnCode );
						      preparedstmt.setString(16, invoiceNumber );
						      preparedstmt.setString(17, invoiceDate );
						      
						      preparedstmt.setString(18, poType );
						      preparedstmt.setString(19, poCode );
						      preparedstmt.setString(20, poCreated );
						      preparedstmt.setString(21, saleOrderCode );
						      preparedstmt.setString(22, "No" );
						      preparedstmt.setString(23, "NA" );
						      preparedstmt.setString(24, "No" );
						      preparedstmt.setString(25, "No" );
						      preparedstmt.setDouble(26, discount );
						      preparedstmt.setDouble(27, commission );
						      preparedstmt.setDouble(28, fwdShipping );
						      preparedstmt.setDouble(29,  pickandpackFee);
						      preparedstmt.setDouble(30,  fixedFee);
						      preparedstmt.setDouble(31, paymentFee );
						      preparedstmt.setDouble(32, 0 );
						      preparedstmt.setDouble(33,  taxonCommisson);
						      preparedstmt.setDouble(34,  taxonLogistics);
						      preparedstmt.setDouble(35,  tcsTax);
						      preparedstmt.setDouble(36,  estimatedValue);
						      preparedstmt.setDouble(37,  0);
						      preparedstmt.setDouble(38,  0);
						      preparedstmt.setDouble(39,0);
						      preparedstmt.setDouble(40,0);
						      preparedstmt.setString(41, "No" );
						      preparedstmt.setString(42, "NA" );
						      preparedstmt.execute(); 
			              			                		               
						      System.out.println("Imported Rows"+i);
				               }else {
				            	   if(mrp>1000) {
				            	   estimatedValue = unitPriceWithTax*((100-discount+5)/100);
				            	   }else {
				            		   estimatedValue = unitPriceWithTax*((100-discount)/100) ;
				            	   }
				            	   String sql = " INSERT ignore INTO myntra_order Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
								      PreparedStatement preparedstmt = conn.prepareStatement(sql);
								      
								      preparedstmt.setString(1, itemCode );
								      preparedstmt.setString(2, itemCreatedOn );
								      preparedstmt.setString(3, itemUpdatedOn );
								      preparedstmt.setString(4, itemstatus );
								      preparedstmt.setString(5, grnNumber );
								      preparedstmt.setString(6, grnCreated );
								      preparedstmt.setString(7, vendorCode );
								      preparedstmt.setString(8, facility );
								      preparedstmt.setString(9, brand );
								      preparedstmt.setDouble(10, mrp );
								      preparedstmt.setString(11, itemSkuCode );
								      preparedstmt.setString(12, vendorSku );
								      preparedstmt.setDouble(13, unitPrice );
								      preparedstmt.setDouble(14, unitPriceWithTax );
								      
								      preparedstmt.setInt(15, hsnCode );
								      preparedstmt.setString(16, invoiceNumber );
								      preparedstmt.setString(17, invoiceDate );
								      
								      preparedstmt.setString(18, poType );
								      preparedstmt.setString(19, poCode );
								      preparedstmt.setString(20, poCreated );
								      preparedstmt.setString(21, saleOrderCode );
								      preparedstmt.setString(22, "No" );
								      preparedstmt.setString(23, "NA" );
								      preparedstmt.setString(24, "No" );
								      preparedstmt.setString(25, "No" );
								      preparedstmt.setDouble(26, discount );
								      preparedstmt.setDouble(27, 0 );
								      preparedstmt.setDouble(28, 0 );
								      preparedstmt.setDouble(29,  0);
								      preparedstmt.setDouble(30,  0);
								      preparedstmt.setDouble(31, 0 );
								      preparedstmt.setDouble(32, 0 );
								      preparedstmt.setDouble(33,  0);
								      preparedstmt.setDouble(34,  0);
								      preparedstmt.setDouble(35,  0);
								      preparedstmt.setDouble(36,  estimatedValue);
								      preparedstmt.setDouble(37,  0);
								      preparedstmt.setDouble(38,  0);
								      preparedstmt.setDouble(39,0);
								      preparedstmt.setDouble(40,0);
								      preparedstmt.setString(41, "No" );
								      preparedstmt.setString(42, "NA" );
								      preparedstmt.execute(); 
								      System.out.println("Imported Rows"+i);
				               }
				               }
			                } 
			            }
				               
			     
			      
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e1){
		      //Handle errors for Class.forName
		      e1.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   path.setText("Transfer Complete");
		}
	});
	headerLabel.setBounds(150, 11, 400, 31);
	statusLabel.setBounds(180, 340, 312, 31);
	headerLabel.setText("Popnetic Warehousing System");
	controlPanel.add(openfile);
	controlPanel.add(path);
	controlPanel.add(transfer);
	controlPanel.add(back);
}


//PPMP Launch Code

private void ppmpLaunch() {
	prepareGUI("Myntra PPMP Order",700,398);
	
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosingppmpLaunch(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	JButton openfile = new JButton("Open");
	openfile.setForeground(SystemColor.desktop);
	openfile.setBackground(SystemColor.activeCaption);
	openfile.setBounds(150, 140, 94, 31);
	openfile.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton transfer = new JButton("Transfer");
	transfer.setForeground(SystemColor.desktop);
	transfer.setBackground(SystemColor.activeCaption);
	transfer.setBounds(450, 140, 94, 31);
	
	
	JButton back = new JButton("Back");
	back.setBounds(300, 300, 94, 31);
	back.setForeground(SystemColor.desktop);
	back.setBackground(SystemColor.controlDkShadow);
	
	
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent backflipkartOrderEvent) {
			
			mainFrame.dispose();
			orderLaunch();
			
		}
	});
	JLabel path = new JLabel("NO FILES Selected");
	path.setBounds(250, 260, 300, 31);
	
	openfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent openfileEvent) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			 int r = j.showOpenDialog(null); 
			 if (r == JFileChooser.APPROVE_OPTION) 
				  
	            { 
	                // set the label to the path of the selected file 
	                path.setText(j.getSelectedFile().getAbsolutePath()); 
	            } 
	            // if the user cancelled the operation 
	            else
	                path.setText("the user cancelled the operation");
			
		}
		
		
		
	});
	
	transfer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent transferEvent) {
			
			Connection conn = null;
			PreparedStatement preparedstmt1;
			   Statement stmt = null;
			   
			   try{
				 //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");

				      //STEP 3: Open a connection
				      System.out.println("Connecting to a selected database...");
				      conn = DriverManager.getConnection(DB_URL, USER, PASS);
				      System.out.println("Connected database successfully...");
				     // PreparedStatement pstm = null ;
				      
				      
				      //Reading the selected excel File
				     
				      FileInputStream input = new FileInputStream(path.getText());
				      System.out.println(input);
				      POIFSFileSystem fs = new POIFSFileSystem( input );
				      HSSFWorkbook wb = new HSSFWorkbook(fs);
				      HSSFSheet sheet = wb.getSheetAt(0);
				      HSSFRow row;
				      for(int i=1; i<=sheet.getLastRowNum(); i++){
			                row = sheet.getRow(i);
			                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			               Double discount =0.0;
			               Double commission=0.0;
			               String levels="";
			               String zone="";
			               Double fixedFee = 0.0;
			               Double fwdShipping=0.0;
			               Double sellingPrice =0.0;
			               Double taxablePrice=0.0;
			               Double pickandpackFee=0.0;
			               Double paymentFee=0.0;
			               Double estimatedValue=0.0;
			               Double taxonCommisson =0.0;
			               Double taxonLogistics;
			               Double tcsTax=0.0;
			               Double tdsTax=0.0;
			               DataFormatter formatter = new DataFormatter();
			               //System.out.println(reportDate);
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
			               
			              String uniqueKey1="";
			               
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
			            //   Date orderedOn = Date.valueOf(orderedOnString);
			                //System.out.println(orderOn);
			              String orderReleaseId    = formatter.formatCellValue(row.getCell(14));
			                System.out.println(orderReleaseId);
			                
			                String sql5= "select orderReleaseId from myntra_ppmp_order where orderReleaseId=?";
				            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
				               preparedstmt5.setString(1, orderReleaseId);
				               
				               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
				               while(rscheckkey5.next()) {
				            	   uniqueKey1 = rscheckkey5.getString("orderReleaseId");
				               }
			                if(orderReleaseId.equalsIgnoreCase(uniqueKey1)) {
			                	System.out.println("No Need for operations");
			                }else {
			                java.util.Date itemCreatedOnDate =  row.getCell(15).getDateCellValue();
				            String itemCreatedOn  = df.format(itemCreatedOnDate);
				            
				            
				            java.util.Date itempackedOnDate =  row.getCell(2).getDateCellValue();
				            String itempackedOn   = df.format(itempackedOnDate);
				           
				            
			                String itemstatus  = row.getCell(5).getStringCellValue();
			                
			                
			                
			                
				            
				            
				            
				            //8th Cell Value is excluded for entering into database
				            
				           
				            
				            Double mrp   = (Double) row.getCell(6).getNumericCellValue();
				            
				            String itemSkuCode    = row.getCell(8).getStringCellValue();
				            
				            String vendorSku     = row.getCell(11).getStringCellValue();
				            
				            Double unitPrice    = (Double) row.getCell(13).getNumericCellValue();
				            
				            String [] words = vendorSku.split("_");
				            String sku=words[0];
				            
				            String state    = row.getCell(16).getStringCellValue();
				            
				            String trackingId    = formatter.formatCellValue(row.getCell(17));
				            
				            String orderId    = formatter.formatCellValue(row.getCell(3	));
				            
				            String packetId    = formatter.formatCellValue(row.getCell(12));
				            
				            String pincode    = formatter.formatCellValue(row.getCell(1));
				            
				        /*    String uniqueKey2="";
				            String sql6= "select saleOrderCode from myntra_order where saleOrderCode=?";
				            PreparedStatement preparedstmt6 = conn.prepareStatement(sql6);
				               preparedstmt6.setString(1, saleOrderCode);
				               
				               ResultSet rscheckkey6 = preparedstmt6.executeQuery();
				               while(rscheckkey6.next()) {
				            	   uniqueKey2 = rscheckkey6.getString("saleOrderCode");
				               }
				            */
				           
				            String sql1= "select discount from myntra_current_discount where sku=?";
				            PreparedStatement preparedstmt2 = conn.prepareStatement(sql1);
				               preparedstmt2.setString(1, sku);
				               
				               ResultSet rscheckkey = preparedstmt2.executeQuery();
				               while(rscheckkey.next()) {
				            	   discount = rscheckkey.getDouble("discount");
				               }
				               
				               String sql6 ="select zone from ppmp_pincode where pincode=?";
				               PreparedStatement preparedstmt6 = conn.prepareStatement(sql6);
				               preparedstmt6.setString(1, pincode);
				               ResultSet rscheckkey4 = preparedstmt6.executeQuery();
				               while(rscheckkey4.next()) {
				            	   zone = rscheckkey4.getString("zone");
				               }
				               
				               
				               sellingPrice= mrp*(100-discount)/100;
				               commission= 0.11*unitPrice;
				               
				               
				               String sql2 ="select levels from myntra_sku_level where sku=?";
				               PreparedStatement preparedstmt3 = conn.prepareStatement(sql2);
				               preparedstmt3.setString(1, vendorSku);
				               ResultSet rscheckkey1 = preparedstmt3.executeQuery();
				               while(rscheckkey1.next()) {
				            	   levels = rscheckkey1.getString("levels");
				               }
				               
				               System.out.println(levels);
				               
				               //Need to move forward to check from here
				              String uniqueKey = levels+zone;
				              System.out.println(uniqueKey);
				              String sql3 =" select logisticCost from myntra_forward_logistics where uniqueKey=?";
				              PreparedStatement preparedstmt4 = conn.prepareStatement(sql3);
				               preparedstmt4.setString(1, uniqueKey);
				               
				               ResultSet rscheckkey2 = preparedstmt4.executeQuery();
				               while(rscheckkey2.next()) {
				            	   fwdShipping = rscheckkey2.getDouble("logisticCost");
				               }
				               
				               System.out.println(fwdShipping);
				              
				            	   pickandpackFee =0.0;
				              
				               
				               if((unitPrice*0.02)<20) {
				            	   paymentFee=20.0;
				               }else {
				            	   paymentFee=unitPrice*0.02;
				               }
				               
				               if(unitPrice<500) {
				            	   fixedFee=5.0;
				               }else if (unitPrice>1000 ){
				            	   fixedFee =30.0;
				               }else {
				            	   fixedFee =15.0;
				               }
				            		  
				               
				               taxonCommisson = commission*0.18;
				               taxonLogistics = (pickandpackFee+paymentFee+fwdShipping+fixedFee)*0.18;
				               
				               taxablePrice = unitPrice/1.05;
				               if(taxablePrice<1000) {
				            	   taxablePrice=unitPrice/1.05;
				               }else {
				            	   taxablePrice=unitPrice/1.12;
				               }
				               tcsTax =taxablePrice*0.01;
				               tdsTax =taxablePrice*0.01;
				               estimatedValue = unitPrice - (commission +tdsTax+pickandpackFee +paymentFee +fixedFee+fwdShipping +taxonCommisson+taxonLogistics+tcsTax);
				               
				               
				            	   
				              
				               
			                
			               String sql = " INSERT ignore INTO myntra_ppmp_order Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						      PreparedStatement preparedstmt = conn.prepareStatement(sql);
						      
						      preparedstmt.setString(1, orderReleaseId );
						      preparedstmt.setString(2, itemCreatedOn );
						      preparedstmt.setString(3, vendorSku );
						      preparedstmt.setString(4, itemSkuCode );
						      preparedstmt.setString(5, orderId );
						      preparedstmt.setString(6, itempackedOn );
						      preparedstmt.setDouble(7, mrp );
						      preparedstmt.setDouble(8, unitPrice );
						      preparedstmt.setString(9, packetId );
						      preparedstmt.setString(10, itemstatus );
						      
						      preparedstmt.setString(11, pincode );
						      preparedstmt.setString(12, state );
						      preparedstmt.setString(13, trackingId );
						      
						     
						      
						     
						      preparedstmt.setString(14, "No" );
						      preparedstmt.setString(15, "NA" );
						      preparedstmt.setString(16, "No" );
						      preparedstmt.setString(17, "No" );
						      preparedstmt.setDouble(18, discount );
						      preparedstmt.setDouble(19, commission );
						      preparedstmt.setDouble(20, fwdShipping );
						      preparedstmt.setDouble(21,  pickandpackFee);
						      preparedstmt.setDouble(22,  fixedFee);
						      preparedstmt.setDouble(23, paymentFee );
						      preparedstmt.setDouble(24, 0 );
						      preparedstmt.setDouble(25,  taxonCommisson);
						      preparedstmt.setDouble(26,  taxonLogistics);
						      preparedstmt.setDouble(27,  tcsTax);
						      preparedstmt.setDouble(28,  tdsTax);
						      preparedstmt.setDouble(29,  estimatedValue);
						      preparedstmt.setDouble(30,  0);
						      preparedstmt.setDouble(31,  0);
						      preparedstmt.setDouble(32,  0);
						      preparedstmt.setDouble(33,  0);
						      preparedstmt.setString(34, "No" );
						      preparedstmt.setString(35, "NA" );
						      preparedstmt.setString(36, "NA");
						      preparedstmt.setString(37, "N" );
						      preparedstmt.setString(38, "N");
						      preparedstmt.setString(39, "NA");
						      preparedstmt.setString(40, "NA");
						      preparedstmt.execute(); 
			              			                		               
						      System.out.println("Imported Rows"+i);
				               
				               }
			                } 
			            }
				               
			     
			      
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e1){
		      //Handle errors for Class.forName
		      e1.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   path.setText("Transfer Complete");
		}
	});
	headerLabel.setBounds(150, 11, 400, 31);
	statusLabel.setBounds(180, 340, 312, 31);
	headerLabel.setText("Popnetic Warehousing System");
	controlPanel.add(openfile);
	controlPanel.add(path);
	controlPanel.add(transfer);
	controlPanel.add(back);
}


//AJIO RTV Launch Code

private void ajioRTVLaunch() {
	prepareGUI("AJIO Return Details",700,398);
	
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosingajioRTV(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	JButton openfile = new JButton("Open");
	openfile.setForeground(SystemColor.desktop);
	openfile.setBackground(SystemColor.activeCaption);
	openfile.setBounds(150, 140, 94, 31);
	openfile.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton transfer = new JButton("Transfer");
	transfer.setForeground(SystemColor.desktop);
	transfer.setBackground(SystemColor.activeCaption);
	transfer.setBounds(450, 140, 94, 31);
	
	
	JButton back = new JButton("Back");
	back.setBounds(300, 300, 94, 31);
	back.setForeground(SystemColor.desktop);
	back.setBackground(SystemColor.controlDkShadow);
	
	
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent backflipkartOrderEvent) {
			
			mainFrame.dispose();
			orderLaunch();
			
		}
	});
	JLabel path = new JLabel("NO FILES Selected");
	path.setBounds(250, 260, 300, 31);
	
	openfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent openfileEvent) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			 int r = j.showOpenDialog(null); 
			 if (r == JFileChooser.APPROVE_OPTION) 
				  
	            { 
	                // set the label to the path of the selected file 
	                path.setText(j.getSelectedFile().getAbsolutePath()); 
	            } 
	            // if the user cancelled the operation 
	            else
	                path.setText("the user cancelled the operation");
			
		}
		
		
		
	});
	
	transfer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent transferEvent) {
			
			Connection conn = null;
			PreparedStatement preparedstmt1;
			   Statement stmt = null;
			   
			   try{
				 //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");

				      //STEP 3: Open a connection
				      System.out.println("Connecting to a selected database...");
				      conn = DriverManager.getConnection(DB_URL, USER, PASS);
				      System.out.println("Connected database successfully...");
				     // PreparedStatement pstm = null ;
				      
				      
				      //Reading the selected excel File
				     
				      FileInputStream input = new FileInputStream(path.getText());
				      System.out.println(input);
				      POIFSFileSystem fs = new POIFSFileSystem( input );
				      HSSFWorkbook wb = new HSSFWorkbook(fs);
				      HSSFSheet sheet = wb.getSheetAt(0);
				      HSSFRow row;
				      for(int i=1; i<=sheet.getLastRowNum(); i++){
			                row = sheet.getRow(i);
			                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			               Double estimatedTotalValue=0.0;
			               DataFormatter formatter = new DataFormatter();
			               //System.out.println(reportDate);
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
			               
			              String uniqueKey1="";
			              String uniqueKeyID="";
			              String vendorSKU="";
			              String styleCode="";
			              String size="";
			              Double MRP=0.0;
			              Double revSCM=0.0;
			              Double returnQTYVal=0.0;
			             
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
			            //   Date orderedOn = Date.valueOf(orderedOnString);
			                //System.out.println(orderOn);
			             
			                
			             
			            	  
			            	  String grnNumber    = formatter.formatCellValue(row.getCell(14));
			            	  String articleCode = formatter.formatCellValue(row.getCell(10));
			            	  
			            	  uniqueKeyID= grnNumber+"-"+articleCode;
			            	  
			            	  System.out.println(uniqueKeyID);
			            	  
			                
			                String sql5= "select uniqueID from ajio_return where uniqueID=?";
				            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
				               preparedstmt5.setString(1, uniqueKeyID);
				               
				               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
				               while(rscheckkey5.next()) {
				            	   uniqueKey1 = rscheckkey5.getString("uniqueID");
				               }
			                if(uniqueKeyID.equalsIgnoreCase(uniqueKey1)) {
			                	
			                	String quantity    = formatter.formatCellValue(row.getCell(12));
			                	
			                	String totalAmountString    = formatter.formatCellValue(row.getCell(31));
			                	String taxableAmountString    = formatter.formatCellValue(row.getCell(20));
			                	String IgstAmountString    = formatter.formatCellValue(row.getCell(30));
			                	
			                	 Double totalAmount   = Double.parseDouble(totalAmountString);
			                	 
			                	 Double taxableAmount = Double.parseDouble(taxableAmountString);
			                	 
			                	 Double IgstAmount = Double.parseDouble(IgstAmountString);
			                	 
			                	 returnQTYVal =Double.parseDouble(quantity);
			                	 
			                	 revSCM= (75*returnQTYVal)*1.18;
			                	 
			                	 String sql12 = "UPDATE IGNORE ajio_return set quantity=quantity+?,taxableAmount=taxableAmount+?,IgstAmount=IgstAmount+?,totalAmount=totalAmount+? where uniqueID=?";
							      PreparedStatement preparedstmt122 = conn.prepareStatement(sql12);
							      preparedstmt122.setString(1, quantity);
							     
							      preparedstmt122.setDouble(2,taxableAmount);
							      
							      preparedstmt122.setDouble(3,IgstAmount);
							      
							      preparedstmt122.setDouble(4,totalAmount);
							  
							      preparedstmt122.setString(5, uniqueKeyID);
							     
			                	 
			                	 String sql1 = "UPDATE IGNORE ajio_grn set returnQty=returnQty+?,estimatedSettleValue=estimatedSettleValue-?,revSCM=revSCM+? where uniqueID=?";
							      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
							      preparedstmt12.setString(1, quantity);
							     
							      preparedstmt12.setDouble(2,revSCM+totalAmount);
							      
							      preparedstmt12.setDouble(3,revSCM);
							  
							      preparedstmt12.setString(4, uniqueKeyID);
							      
							      preparedstmt122.execute();
							      preparedstmt12.execute();
			                	
			                	System.out.println("Return qty updated against the concerned article Type in both Tables");
			                }else {
			                	String quantity    = formatter.formatCellValue(row.getCell(12));
			                	
			                	String totalAmountString    = formatter.formatCellValue(row.getCell(31));
			                	String taxableAmountString    = formatter.formatCellValue(row.getCell(20));
			                	String IgstAmountString    = formatter.formatCellValue(row.getCell(30));
			                	
			                	 Double totalAmount   = Double.parseDouble(totalAmountString);
			                	 
			                	 Double taxableAmount = Double.parseDouble(taxableAmountString);
			                	 
			                	 Double IgstAmount = Double.parseDouble(IgstAmountString);
			                	 
			                	 returnQTYVal =Double.parseDouble(quantity);
			                	 
			                	 revSCM= (75*returnQTYVal)*1.18;
			                	
			                	 
			                	String vendorCode  = row.getCell(1).getStringCellValue();
			              
			                	
			                	String purchaseOrderCode  = row.getCell(2).getStringCellValue();
			              
			                	String returnPurchaseOrderCode = row.getCell(5).getStringCellValue();
			                
			                	
				           
				            
				            String rtvdateget    = row.getCell(6).getStringCellValue();
				            
				            String rtvdateD=rtvdateget.substring(0, 2);
				            String rtvmonth=rtvdateget.substring(3, 5);
				            String rtvyear=rtvdateget.substring(6, 10);
				            
				            String rtvdate=rtvyear+"-"+rtvmonth+"-"+rtvdateD;
				            
				            String rtvNumber     = row.getCell(7).getStringCellValue();
				            
				            String rtvYear  = row.getCell(8).getStringCellValue();
				            String deliveryChallanRtvNumber  = row.getCell(9).getStringCellValue();
				         
				            String grnDateget    = row.getCell(15).getStringCellValue();
				            
				            String year=grnDateget.substring(6, 10);
				            String month=grnDateget.substring(3, 5);
				            String date=grnDateget.substring(0, 2);
				            
				            String grnDate=year+"-"+month+"-"+date;
				            
				            String invoiceNumber    = row.getCell(16).getStringCellValue();
				            
				            String invoiceDate    = row.getCell(17).getStringCellValue();
				            
				            String family    = row.getCell(18).getStringCellValue();
				            
				            
			                
			               String sql = " INSERT ignore INTO ajio_return Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						      PreparedStatement preparedstmt = conn.prepareStatement(sql);
						      
						      preparedstmt.setString(1, uniqueKeyID );
						      preparedstmt.setString(2, vendorCode );
						      preparedstmt.setString(3, returnPurchaseOrderCode );
						      preparedstmt.setString(4, rtvdate );
						      preparedstmt.setString(5, rtvNumber );
						      preparedstmt.setString(6, rtvYear );
						      preparedstmt.setString(7, deliveryChallanRtvNumber );
						      preparedstmt.setString(8, articleCode );
						      preparedstmt.setString(9, quantity );
						      preparedstmt.setString(10, grnNumber );
						      preparedstmt.setString(11, grnDate );
						      preparedstmt.setString(12, invoiceNumber );
						      preparedstmt.setString(13, invoiceDate );
						      preparedstmt.setString(14, family );
						      preparedstmt.setDouble(15, taxableAmount );
						      preparedstmt.setDouble(16, IgstAmount );
						      preparedstmt.setDouble(17, totalAmount );
						      preparedstmt.setString(18, "Yes" );
						     
						      String sql1 = "UPDATE IGNORE ajio_grn set returnQty=returnQty+?,estimatedSettleValue=estimatedSettleValue-?,revSCM=revSCM+? where uniqueID=?";
						      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
						      preparedstmt12.setString(1, quantity);
						     
						      preparedstmt12.setDouble(2,totalAmount+revSCM);
						      
						      preparedstmt12.setDouble(3,revSCM);
						  
						      preparedstmt12.setString(4, uniqueKeyID);
						     
						      preparedstmt12.execute(); 
						      preparedstmt.execute(); 
			              			                		               
						      System.out.println("Imported Rows"+i);
			                }
				               
				               
			                } 
			            }
				               
			     
			      
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e1){
		      //Handle errors for Class.forName
		      e1.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   path.setText("Transfer Complete");
		}
	});
	headerLabel.setBounds(150, 11, 400, 31);
	statusLabel.setBounds(180, 340, 312, 31);
	headerLabel.setText("Popnetic Warehousing System");
	controlPanel.add(openfile);
	controlPanel.add(path);
	controlPanel.add(transfer);
	controlPanel.add(back);
}



//AJIO GRN Launch Code

private void ajioGRNLaunch() {
	prepareGUI("AJIO GRN Details",700,398);
	
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosingppmpLaunch(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	JButton openfile = new JButton("Open");
	openfile.setForeground(SystemColor.desktop);
	openfile.setBackground(SystemColor.activeCaption);
	openfile.setBounds(150, 140, 94, 31);
	openfile.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton transfer = new JButton("Transfer");
	transfer.setForeground(SystemColor.desktop);
	transfer.setBackground(SystemColor.activeCaption);
	transfer.setBounds(450, 140, 94, 31);
	
	
	JButton back = new JButton("Back");
	back.setBounds(300, 300, 94, 31);
	back.setForeground(SystemColor.desktop);
	back.setBackground(SystemColor.controlDkShadow);
	
	
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent backflipkartOrderEvent) {
			
			mainFrame.dispose();
			orderLaunch();
			
		}
	});
	JLabel path = new JLabel("NO FILES Selected");
	path.setBounds(250, 260, 300, 31);
	
	openfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent openfileEvent) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			 int r = j.showOpenDialog(null); 
			 if (r == JFileChooser.APPROVE_OPTION) 
				  
	            { 
	                // set the label to the path of the selected file 
	                path.setText(j.getSelectedFile().getAbsolutePath()); 
	            } 
	            // if the user cancelled the operation 
	            else
	                path.setText("the user cancelled the operation");
			
		}
		
		
		
	});
	
	transfer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent transferEvent) {
			
			Connection conn = null;
			PreparedStatement preparedstmt1;
			   Statement stmt = null;
			   
			   try{
				 //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");

				      //STEP 3: Open a connection
				      System.out.println("Connecting to a selected database...");
				      conn = DriverManager.getConnection(DB_URL, USER, PASS);
				      System.out.println("Connected database successfully...");
				     // PreparedStatement pstm = null ;
				      
				      
				      //Reading the selected excel File
				     
				      FileInputStream input = new FileInputStream(path.getText());
				      System.out.println(input);
				      POIFSFileSystem fs = new POIFSFileSystem( input );
				      HSSFWorkbook wb = new HSSFWorkbook(fs);
				      HSSFSheet sheet = wb.getSheetAt(0);
				      HSSFRow row;
				      for(int i=1; i<=sheet.getLastRowNum(); i++){
			                row = sheet.getRow(i);
			                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			               Double estimatedTotalValue=0.0;
			               DataFormatter formatter = new DataFormatter();
			               //System.out.println(reportDate);
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
			               String defaultMVTNumber= "101";
			              String uniqueKey1="";
			              String uniqueKeyID="";
			              String vendorSKU="";
			              String styleCode="";
			              String size="";
			              Double MRP=0.0;
			              Double fwdSCM =0.0;
			              Double grnQTYVal=0.0;
			              Double grnTotalValue=0.0;
			             
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
			            //   Date orderedOn = Date.valueOf(orderedOnString);
			                //System.out.println(orderOn);
			              String mvtNumber    = formatter.formatCellValue(row.getCell(33));
			                
			              if(mvtNumber.equalsIgnoreCase(defaultMVTNumber)) {
			            	  
			            	  String grnNumber    = formatter.formatCellValue(row.getCell(30));
			            	  String articleCode = formatter.formatCellValue(row.getCell(4));
			            	  
			            	  uniqueKeyID= grnNumber+"-"+articleCode;
			            	  
			            	  System.out.println(uniqueKeyID);
			            	  
			                
			                String sql5= "select uniqueID from ajio_grn where uniqueID=?";
				            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
				               preparedstmt5.setString(1, uniqueKeyID);
				               
				               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
				               while(rscheckkey5.next()) {
				            	   uniqueKey1 = rscheckkey5.getString("uniqueID");
				               }
			                if(uniqueKeyID.equalsIgnoreCase(uniqueKey1)) {
			                	
			                	String grnQty    = formatter.formatCellValue(row.getCell(37));
			                	
			                	 Double grnTotalValueGot   = (Double) row.getCell(43).getNumericCellValue();
			                	 
			                	 if(grnTotalValueGot<1000) {
			                		 grnTotalValue=grnTotalValueGot*1.05;
			                	 }else {
			                		 grnTotalValue=grnTotalValueGot*1.12;
			                	 }
			                	 
			                	 grnQTYVal =Double.parseDouble(grnQty);
			                	 
			                	 fwdSCM= (95*grnQTYVal)*1.18;
			                	 
			                	 String sql1 = "UPDATE IGNORE ajio_grn set grnQty=grnQty+?,grnTotalValue=grnTotalValue+?,estimatedSettleValue=estimatedSettleValue+?,fwdSCM=fwdSCM+? where uniqueID=?";
							      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
							      preparedstmt12.setString(1, grnQty);
							     
							      preparedstmt12.setDouble(2,(grnTotalValue));
							      
							      preparedstmt12.setDouble(3,grnTotalValue-fwdSCM);
							      
							      preparedstmt12.setDouble(4,fwdSCM);
							      
							      preparedstmt12.setString(5, uniqueKeyID);
							      
							      preparedstmt12.execute();
			                	
			                	System.out.println("grn qty updated against the concerned article Type");
			                }else {
			                	
			                	String grnQty    = formatter.formatCellValue(row.getCell(37));
			                	
			                	Double grnTotalValueGot   = (Double) row.getCell(43).getNumericCellValue();
			                	 
			                	 if(grnTotalValueGot<1000) {
			                		 grnTotalValue=grnTotalValueGot*1.05;
			                	 }else {
			                		 grnTotalValue=grnTotalValueGot*1.12;
			                	 }
			                	 
			                	 grnQTYVal =Double.parseDouble(grnQty);
			                	 
			                	 fwdSCM= (95*grnQTYVal)*1.18;
			                	 
			                	String poDateget  = row.getCell(1).getStringCellValue();
			                	System.out.println(poDateget);
			                	String poDatenext= poDateget;
			                	 
			                	 
			                	String Date=poDatenext.substring(0, 2);
			                	System.out.println(Date);
			                	
			                	String month=poDatenext.substring(3, 5);
			                	System.out.println(month);
			                	
			                	String year =poDatenext.substring(6, 10);
			                	System.out.println(year);
			                	String poDate=year+"-"+month+"-"+Date;
			              
			                	
			                	String purchaseOrderCode  = row.getCell(2).getStringCellValue();
			              
			                	String eanNumber = row.getCell(6).getStringCellValue();
			                
			                	String sql51= "select sellerSkuCode,sellerStyleCode,size,MRP from ajiolookup where ean=?";
					            PreparedStatement preparedstmt51 = conn.prepareStatement(sql51);
					               preparedstmt51.setString(1, eanNumber);
					               
					               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
					               while(rscheckkey51.next()) {
					            	   vendorSKU = rscheckkey51.getString("sellerSkuCode");
					            	   styleCode = rscheckkey51.getString("sellerStyleCode");
					            	   size = rscheckkey51.getString("size");
					            	   MRP= rscheckkey51.getDouble("MRP");
					            	   
					               }
				           
				            
				            String vendorCode    = row.getCell(7).getStringCellValue();
				            
				            String parkedInvoiceNumber     = row.getCell(16).getStringCellValue();
				            
				            String asnDateget  = row.getCell(17).getStringCellValue();
				            
				            String asnyear =asnDateget.substring(6, 10);
		                	String asnmonth=asnDateget.substring(3, 5);
		                	String asnDateD=asnDateget.substring(0, 2);
		                	String asnDate=asnyear+"-"+asnmonth+"-"+asnDateD;
				            
				            
				            String site  = row.getCell(9).getStringCellValue();
				         
				            String asnNumber    = row.getCell(18).getStringCellValue();
				            
				            String asnItem    = row.getCell(19).getStringCellValue();
				            
				            String vendorInvoiceNumber    = row.getCell(20).getStringCellValue();
				            
				            String grnDateget  = row.getCell(29).getStringCellValue();
				           
				            String grnyear =grnDateget.substring(6, 10);
		                	String grnmonth=grnDateget.substring(3, 5);
		                	String grnDateD=grnDateget.substring(0, 2);
		                	String grnDate=grnyear+"-"+grnmonth+"-"+grnDateD;
				            
				            String docYear  = row.getCell(31).getStringCellValue();
				            
				            String grnItem  = row.getCell(32).getStringCellValue();
			
			                
			               String sql = " INSERT ignore INTO ajio_grn Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						      PreparedStatement preparedstmt = conn.prepareStatement(sql);
						      
						      preparedstmt.setString(1, uniqueKeyID );
						      preparedstmt.setString(2, poDate );
						      preparedstmt.setString(3, purchaseOrderCode );
						      preparedstmt.setString(4, articleCode );
						      preparedstmt.setString(5, eanNumber );
						      preparedstmt.setString(6, vendorSKU );
						      preparedstmt.setString(7, styleCode );
						      preparedstmt.setString(8, size );
						      preparedstmt.setDouble(9, MRP );
						      preparedstmt.setString(10, site );
						      preparedstmt.setString(11, vendorCode );
						      preparedstmt.setString(12, parkedInvoiceNumber );
						      preparedstmt.setString(13, asnDate );
						      preparedstmt.setString(14, asnNumber );
						      preparedstmt.setString(15, asnItem );
						      preparedstmt.setString(16, vendorInvoiceNumber );
						      preparedstmt.setString(17, grnDate );
						      preparedstmt.setString(18, grnNumber );
						      preparedstmt.setString(19, docYear );
						      preparedstmt.setString(20, grnItem );
						      preparedstmt.setString(21, mvtNumber );
						      preparedstmt.setString(22, grnQty );
						      preparedstmt.setDouble(23, grnTotalValue );
						      preparedstmt.setDouble(24, fwdSCM );
						      preparedstmt.setDouble(25, 0 );
						      preparedstmt.setDouble(26, 0 );
						      preparedstmt.setDouble(27, grnTotalValue-fwdSCM);
						     
						      preparedstmt.execute(); 
			              			                		               
						      System.out.println("Imported Rows"+i);
			                }
				               
				               }else {
				            	   System.out.println("MVT Value is not for Inward Articles");
				               }
			                } 
			            }
				               
			     
			      
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e1){
		      //Handle errors for Class.forName
		      e1.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   path.setText("Transfer Complete");
		}
	});
	headerLabel.setBounds(150, 11, 400, 31);
	statusLabel.setBounds(180, 340, 312, 31);
	headerLabel.setText("Popnetic Warehousing System");
	controlPanel.add(openfile);
	controlPanel.add(path);
	controlPanel.add(transfer);
	controlPanel.add(back);
}



//AJIO PO launch Code

private void ajioPOLaunch() {
	prepareGUI("AJIO Purchase Order",700,398);
	
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosingppmpLaunch(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	JButton openfile = new JButton("Open");
	openfile.setForeground(SystemColor.desktop);
	openfile.setBackground(SystemColor.activeCaption);
	openfile.setBounds(150, 140, 94, 31);
	openfile.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton transfer = new JButton("Transfer");
	transfer.setForeground(SystemColor.desktop);
	transfer.setBackground(SystemColor.activeCaption);
	transfer.setBounds(450, 140, 94, 31);
	
	
	JButton back = new JButton("Back");
	back.setBounds(300, 300, 94, 31);
	back.setForeground(SystemColor.desktop);
	back.setBackground(SystemColor.controlDkShadow);
	
	
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent backflipkartOrderEvent) {
			
			mainFrame.dispose();
			orderLaunch();
			
		}
	});
	JLabel path = new JLabel("NO FILES Selected");
	path.setBounds(250, 260, 300, 31);
	
	openfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent openfileEvent) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			 int r = j.showOpenDialog(null); 
			 if (r == JFileChooser.APPROVE_OPTION) 
				  
	            { 
	                // set the label to the path of the selected file 
	                path.setText(j.getSelectedFile().getAbsolutePath()); 
	            } 
	            // if the user cancelled the operation 
	            else
	                path.setText("the user cancelled the operation");
			
		}
		
		
		
	});
	
	transfer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent transferEvent) {
			
			Connection conn = null;
			PreparedStatement preparedstmt1;
			   Statement stmt = null;
			   
			   try{
				 //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");

				      //STEP 3: Open a connection
				      System.out.println("Connecting to a selected database...");
				      conn = DriverManager.getConnection(DB_URL, USER, PASS);
				      System.out.println("Connected database successfully...");
				     // PreparedStatement pstm = null ;
				      
				      
				      //Reading the selected excel File
				     
				      FileInputStream input = new FileInputStream(path.getText());
				      System.out.println(input);
				      POIFSFileSystem fs = new POIFSFileSystem( input );
				      HSSFWorkbook wb = new HSSFWorkbook(fs);
				      HSSFSheet sheet = wb.getSheetAt(0);
				      HSSFRow row;
				      for(int i=1; i<=sheet.getLastRowNum(); i++){
			                row = sheet.getRow(i);
			                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			               Double estimatedTotalValue=0.0;
			               DataFormatter formatter = new DataFormatter();
			               //System.out.println(reportDate);
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
			               
			              String uniqueKey1="";
			               
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
			            //   Date orderedOn = Date.valueOf(orderedOnString);
			                //System.out.println(orderOn);
			              String purchaseOrderCode    = formatter.formatCellValue(row.getCell(1));
			                System.out.println(purchaseOrderCode);
			                
			                String sql5= "select purchaseOrderCode from ajio_po where purchaseOrderCode=?";
				            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
				               preparedstmt5.setString(1, purchaseOrderCode);
				               
				               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
				               while(rscheckkey5.next()) {
				            	   uniqueKey1 = rscheckkey5.getString("purchaseOrderCode");
				               }
			                if(purchaseOrderCode.equalsIgnoreCase(uniqueKey1)) {
			                	System.out.println("No Need for operations");
			                }else {
			                	String purchaseOrderDateOn  = row.getCell(5).getStringCellValue();
			              
			                	
			                String purchaserGroupDescrition  = row.getCell(4).getStringCellValue();
			                
			                Double totalValue   = (Double) row.getCell(7).getNumericCellValue();
			                
			                String returnPO = row.getCell(14).getStringCellValue();
			                
			                if(returnPO.equalsIgnoreCase("Yes")) {
			                	estimatedTotalValue = -totalValue;
			                }else {
			                	estimatedTotalValue=totalValue;
			                }
				           
				            
				            String vendorCode    = row.getCell(8).getStringCellValue();
				            
				            String companyCode     = row.getCell(10).getStringCellValue();
				         
				            String companyName    = row.getCell(11).getStringCellValue();
				            
				            String warehouseCode    = formatter.formatCellValue(row.getCell(12));
			
			                
			               String sql = " INSERT ignore INTO ajio_po Values(?,?,?,?,?,?,?,?,?,?)";
						      PreparedStatement preparedstmt = conn.prepareStatement(sql);
						      
						      preparedstmt.setString(1, purchaseOrderCode );
						      preparedstmt.setString(2, purchaserGroupDescrition );
						      preparedstmt.setString(3, purchaseOrderDateOn );
						      preparedstmt.setDouble(4, totalValue );
						      preparedstmt.setString(5, vendorCode );
						      preparedstmt.setString(6, companyCode );
						      preparedstmt.setString(7, companyName );
						      preparedstmt.setString(8, warehouseCode );
						      preparedstmt.setString(9, returnPO );
						      preparedstmt.setDouble(10, estimatedTotalValue );
						     
						      preparedstmt.execute(); 
			              			                		               
						      System.out.println("Imported Rows"+i);
				               
				               }
			                } 
			            }
				               
			     
			      
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e1){
		      //Handle errors for Class.forName
		      e1.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   path.setText("Transfer Complete");
		}
	});
	headerLabel.setBounds(150, 11, 400, 31);
	statusLabel.setBounds(180, 340, 312, 31);
	headerLabel.setText("Popnetic Warehousing System");
	controlPanel.add(openfile);
	controlPanel.add(path);
	controlPanel.add(transfer);
	controlPanel.add(back);
}



//PPMP Order Report Launch

private void ppmpreportLaunch() {
	prepareGUI("Myntra PPMP Order Report",700,398);
	
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosingppmpLaunch(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	JButton openfile = new JButton("Open");
	openfile.setForeground(SystemColor.desktop);
	openfile.setBackground(SystemColor.activeCaption);
	openfile.setBounds(150, 140, 94, 31);
	openfile.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton transfer = new JButton("Transfer");
	transfer.setForeground(SystemColor.desktop);
	transfer.setBackground(SystemColor.activeCaption);
	transfer.setBounds(450, 140, 94, 31);
	
	
	JButton back = new JButton("Back");
	back.setBounds(300, 300, 94, 31);
	back.setForeground(SystemColor.desktop);
	back.setBackground(SystemColor.controlDkShadow);
	
	
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent backflipkartOrderEvent) {
			
			mainFrame.dispose();
			orderLaunch();
			
		}
	});
	JLabel path = new JLabel("NO FILES Selected");
	path.setBounds(250, 260, 300, 31);
	
	openfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent openfileEvent) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			 int r = j.showOpenDialog(null); 
			 if (r == JFileChooser.APPROVE_OPTION) 
				  
	            { 
	                // set the label to the path of the selected file 
	                path.setText(j.getSelectedFile().getAbsolutePath()); 
	            } 
	            // if the user cancelled the operation 
	            else
	                path.setText("the user cancelled the operation");
			
		}
		
		
		
	});
	
	transfer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent transferEvent) {
			
			Connection conn = null;
			PreparedStatement preparedstmt1;
			   Statement stmt = null;
			   
			   try{
				 //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");

				      //STEP 3: Open a connection
				      System.out.println("Connecting to a selected database...");
				      conn = DriverManager.getConnection(DB_URL, USER, PASS);
				      System.out.println("Connected database successfully...");
				     // PreparedStatement pstm = null ;
				      
				      
				      //Reading the selected excel File
				     
				      FileInputStream input = new FileInputStream(path.getText());
				      System.out.println(input);
				      POIFSFileSystem fs = new POIFSFileSystem( input );
				      HSSFWorkbook wb = new HSSFWorkbook(fs);
				      HSSFSheet sheet = wb.getSheetAt(0);
				      HSSFRow row;
				      for(int i=1; i<=sheet.getLastRowNum(); i++){
			                row = sheet.getRow(i);
			                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			               Double discount =0.0;
			               Double paymentFee=0.0;
			               
			               DataFormatter formatter = new DataFormatter();
			               //System.out.println(reportDate);
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
			               Double discount1 =0.0;
			               Double commission=0.0;
			               String levels="";
			               String zone="";
			               Double fixedFee = 0.0;
			               Double fwdShipping=0.0;
			               Double sellingPrice =0.0;
			               Double taxablePrice=0.0;
			               Double pickandpackFee=0.0;
			               
			               Double paymentFee1=0.0;
			               Double estimatedValue=0.0;
			               Double taxonCommisson =0.0;
			               Double taxonLogistics;
			               Double tcsTax=0.0;
			               Double tdsTax=0.0;
			              String uniqueKey1="";
			              String uniqueKey11="";
			              String uniqueKeyorderStatus="";
			              String isPresentinppmpOrder="";
			               
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
			            //   Date orderedOn = Date.valueOf(orderedOnString);
			                //System.out.println(orderOn);
			              String orderReleaseId    = formatter.formatCellValue(row.getCell(3));
			                System.out.println(orderReleaseId);
			                String packetID   = formatter.formatCellValue(row.getCell(19));
				            
				            String sellerPacketId   = formatter.formatCellValue(row.getCell(20));
			                String orderStatus    = row.getCell(18).getStringCellValue();
			                System.out.println(orderStatus);
			                String sql5= "select orderReleaseId,orderStatus from myntra_ppmp_order_report where orderReleaseId=?";
				            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
				               preparedstmt5.setString(1, orderReleaseId);
				               
				               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
				               while(rscheckkey5.next()) {
				            	   uniqueKey1 = rscheckkey5.getString("orderReleaseId");
				            	   uniqueKeyorderStatus=rscheckkey5.getString("orderStatus");
				            	   System.out.println(uniqueKey1);
				            	   System.out.println(uniqueKeyorderStatus);
				            	   
				               }
			                if(orderReleaseId.equalsIgnoreCase(uniqueKey1)&&orderStatus.equalsIgnoreCase(uniqueKeyorderStatus)) {
			                	System.out.println("No Need for operations");
			                }else {
			                	
			                	if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&((orderStatus.equalsIgnoreCase("C"))||((orderStatus.equalsIgnoreCase("DL"))))&&((uniqueKeyorderStatus.equalsIgnoreCase("PK"))||(uniqueKeyorderStatus.equalsIgnoreCase("SH")))) {
			                		 java.util.Date itemdeliveredOn =  row.getCell(30).getDateCellValue();
							            String deliveredOn	  = df.format(itemdeliveredOn);
							            
							            java.util.Date itemshippedOn	 =  row.getCell(29).getDateCellValue();
							            String shippedOn		  = df.format(itemshippedOn);
							            
							            java.util.Date itemscanOn	 =  row.getCell(28).getDateCellValue();
							            String scanOn		  = df.format(itemscanOn);
							            
							            
							            
							            String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=?,shippedOn=?,deliveredOn=?,scanOn=? where orderReleaseID=?";
									      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
									      preparedstmt12.setString(1, orderStatus);
									      preparedstmt12.setString(2, shippedOn);
									     
									      preparedstmt12.setString(3,deliveredOn );
									      preparedstmt12.setString(4,scanOn );
									      preparedstmt12.setString(5,orderReleaseId );
									      System.out.println("1");
									      preparedstmt12.execute();
							            
							           
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("RTO"))&&(uniqueKeyorderStatus.equalsIgnoreCase("SH"))) {
			                		 String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								     
								      preparedstmt12.setString(2,orderReleaseId );
								      
								      preparedstmt12.execute();
								      System.out.println("2");
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("L"))&&(uniqueKeyorderStatus.equalsIgnoreCase("PK"))) {
			                		String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								     
								      preparedstmt12.setString(2,orderReleaseId );
								      
								      preparedstmt12.execute();
								      System.out.println("3");
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("SH"))&&(uniqueKeyorderStatus.equalsIgnoreCase("PK"))) {
			                		 java.util.Date itemshippedOn	 =  row.getCell(29).getDateCellValue();
							            String shippedOn		  = df.format(itemshippedOn);
							            java.util.Date scanonDate	 =  row.getCell(28).getDateCellValue();
							            String scanon		  = df.format(scanonDate);
							            String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=?,shippedOn=?,scanOn=? where orderReleaseID=?";
									      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
									      preparedstmt12.setString(1, orderStatus);
									      preparedstmt12.setString(2, shippedOn);
									      preparedstmt12.setString(3, scanon);
									      preparedstmt12.setString(4,orderReleaseId );
									      
									      preparedstmt12.execute();
									      System.out.println("4");
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("F"))&&(uniqueKeyorderStatus.equalsIgnoreCase("SH"))) {
			                		String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								     
								      preparedstmt12.setString(2,orderReleaseId );
								      
								      preparedstmt12.execute();
								      System.out.println("5");
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("F"))&&(uniqueKeyorderStatus.equalsIgnoreCase("PK"))) {
			                		String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								     
								      preparedstmt12.setString(2,orderReleaseId );
								      
								      preparedstmt12.execute();
								      System.out.println("6");
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("SH"))&&(uniqueKeyorderStatus.equalsIgnoreCase("L"))) {
			                		 java.util.Date itemshippedOn	 =  row.getCell(29).getDateCellValue();
							            String shippedOn		  = df.format(itemshippedOn);
							            
							            String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=?,shippedOn=?,packetID=?,sellerPacketId=? where orderReleaseID=?";
									      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
									      preparedstmt12.setString(1, orderStatus);
									      preparedstmt12.setString(2, shippedOn);
									      preparedstmt12.setString(3, packetID);
									      preparedstmt12.setString(4,sellerPacketId );
									      
									      
									      preparedstmt12.setString(5,orderReleaseId );
									      
									      preparedstmt12.execute();
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("RTO"))&&(uniqueKeyorderStatus.equalsIgnoreCase("SH"))) {
			                		String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								     
								      preparedstmt12.setString(2,orderReleaseId );
								      
								      preparedstmt12.execute();
								      System.out.println("6");
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("RTO"))&&(uniqueKeyorderStatus.equalsIgnoreCase("PK"))) {
			                		String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								     
								      preparedstmt12.setString(2,orderReleaseId );
								      
								      preparedstmt12.execute();
								      System.out.println("6");
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("RTO"))&&(uniqueKeyorderStatus.equalsIgnoreCase("F"))) {
			                		String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								     
								      preparedstmt12.setString(2,orderReleaseId );
								      
								      preparedstmt12.execute();
								      System.out.println("6");
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("C"))&&(uniqueKeyorderStatus.equalsIgnoreCase("DL"))) {
			                		String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								     
								      preparedstmt12.setString(2,orderReleaseId );
								      
								      preparedstmt12.execute();
								      System.out.println("6");
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("DL"))&&(uniqueKeyorderStatus.equalsIgnoreCase("C"))) {
			                		String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								     
								      preparedstmt12.setString(2,orderReleaseId );
								      
								      preparedstmt12.execute();
								      System.out.println("6");
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("PK"))&&(uniqueKeyorderStatus.equalsIgnoreCase("WP"))) {
			                		java.util.Date itemdeliveredOn =  row.getCell(26).getDateCellValue();
						            String packedOn	  = df.format(itemdeliveredOn);
						            
						            java.util.Date itemshippedOn	 =  row.getCell(28).getDateCellValue();
						            String scanOn		  = df.format(itemshippedOn);
						            
						            String FwdTrackingID   = formatter.formatCellValue(row.getCell(22));
			                		
			                		String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=?,packedOn=?,scanOn=?,packetID=?,sellerPacketId=?,FwdTrackingID=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								      preparedstmt12.setString(2, packedOn);
								      preparedstmt12.setString(3, scanOn);
								      preparedstmt12.setString(4, packetID);
								      preparedstmt12.setString(5,sellerPacketId );
								      preparedstmt12.setString(6, FwdTrackingID);
								     
								      preparedstmt12.setString(7,orderReleaseId );
								      
								      String sql12 = "UPDATE IGNORE myntra_ppmp_order set orderStatus=?,packedOn=?,storePacketId=? ,trackingId =? where orderReleaseId=?";
								      PreparedStatement preparedstmt122 = conn.prepareStatement(sql12);
								      preparedstmt122.setString(1, orderStatus);
								      preparedstmt122.setString(2, packedOn);
								      
								      preparedstmt122.setString(3, packetID);
								      preparedstmt122.setString(4, FwdTrackingID);
								     
								      preparedstmt122.setString(5,orderReleaseId );
								      
								      
								      preparedstmt12.execute();
								      preparedstmt122.execute();
								      System.out.println("6");
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("SH"))&&(uniqueKeyorderStatus.equalsIgnoreCase("WP"))) {
			                		
			                		java.util.Date itemdeliveredOn =  row.getCell(26).getDateCellValue();
						            String packedOn	  = df.format(itemdeliveredOn);
						            
						            java.util.Date itemshippedOn	 =  row.getCell(28).getDateCellValue();
						            String scanOn		  = df.format(itemshippedOn);
						            
						            java.util.Date itemshippedOn1	 =  row.getCell(29).getDateCellValue();
						            String shippedOn		  = df.format(itemshippedOn1);
						            
						            String FwdTrackingID   = formatter.formatCellValue(row.getCell(22));
			                		
			                		String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=?,packedOn=?,scanOn=?,shippedOn=?,packetID=?,sellerPacketId=?,FwdTrackingID=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								      preparedstmt12.setString(2, packedOn);
								      preparedstmt12.setString(3, scanOn);
								      preparedstmt12.setString(4, shippedOn);
								      preparedstmt12.setString(5, packetID);
								      preparedstmt12.setString(6,sellerPacketId );
								      preparedstmt12.setString(7,FwdTrackingID );
								     
								      preparedstmt12.setString(8,orderReleaseId );
								      
								      String sql12 = "UPDATE IGNORE myntra_ppmp_order set orderStatus=?,packedOn=?,storePacketId=?,trackingId =? where orderReleaseId=?";
								      PreparedStatement preparedstmt122 = conn.prepareStatement(sql12);
								      preparedstmt122.setString(1, orderStatus);
								      preparedstmt122.setString(2, packedOn);
								      
								      preparedstmt122.setString(3, packetID);
								      preparedstmt122.setString(4, FwdTrackingID);
								     
								      preparedstmt122.setString(5,orderReleaseId );
								      
								      
								      preparedstmt12.execute();
								      preparedstmt122.execute();
								      
								      
								      System.out.println("6");
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("F"))&&(uniqueKeyorderStatus.equalsIgnoreCase("WP"))) {
			                		String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								     
								      preparedstmt12.setString(2,orderReleaseId );
								      
								      String sql12 = "UPDATE IGNORE myntra_ppmp_order set orderStatus=?,storePacketId=? where orderReleaseId=?";
								      PreparedStatement preparedstmt122 = conn.prepareStatement(sql12);
								      preparedstmt122.setString(1, orderStatus);
								      
								      
								      preparedstmt122.setString(2, packetID);
								      
								     
								      preparedstmt122.setString(3,orderReleaseId );
								      
								      
								      preparedstmt12.execute();
								      preparedstmt122.execute();
								      System.out.println("6");
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("DL"))&&(uniqueKeyorderStatus.equalsIgnoreCase("WP"))) {
			                		java.util.Date itemdeliveredOn =  row.getCell(26).getDateCellValue();
						            String packedOn	  = df.format(itemdeliveredOn);
						            
						            java.util.Date itemshippedOn	 =  row.getCell(28).getDateCellValue();
						            String scanOn		  = df.format(itemshippedOn);
						            
						            java.util.Date itemshippedOn1	 =  row.getCell(29).getDateCellValue();
						            String shippedOn		  = df.format(itemshippedOn1);
						            
						            java.util.Date itemshippedOn12	 =  row.getCell(30).getDateCellValue();
						            String deliveredOn		  = df.format(itemshippedOn12);
			                		
						            String FwdTrackingID   = formatter.formatCellValue(row.getCell(22));
			                		
			                		
			                		String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=?,packedOn=?,scanOn=?,shippedOn=?,deliveredOn=?,packetID=?,sellerPacketId=?,FwdTrackingID=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								      preparedstmt12.setString(2, packedOn);
								      preparedstmt12.setString(3, scanOn);
								      preparedstmt12.setString(4, shippedOn);
								      preparedstmt12.setString(5, deliveredOn);
								      preparedstmt12.setString(6, packetID);
								      preparedstmt12.setString(7,sellerPacketId );
								      preparedstmt12.setString(8,FwdTrackingID );
								      preparedstmt12.setString(9,orderReleaseId );
								      
								      String sql12 = "UPDATE IGNORE myntra_ppmp_order set orderStatus=?,packedOn=?,storePacketId=?,trackingId=? where orderReleaseId=?";
								      PreparedStatement preparedstmt122 = conn.prepareStatement(sql12);
								      preparedstmt122.setString(1, orderStatus);
								      preparedstmt122.setString(2, packedOn);
								      
								      preparedstmt122.setString(3, packetID);
								      preparedstmt122.setString(4, FwdTrackingID);
								     
								      preparedstmt122.setString(5,orderReleaseId );
								      
								      
								      preparedstmt12.execute();
								      preparedstmt122.execute();
								      
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("C"))&&(uniqueKeyorderStatus.equalsIgnoreCase("WP"))) {
			                		java.util.Date itemdeliveredOn =  row.getCell(26).getDateCellValue();
						            String packedOn	  = df.format(itemdeliveredOn);
						            
						            java.util.Date itemshippedOn	 =  row.getCell(28).getDateCellValue();
						            String scanOn		  = df.format(itemshippedOn);
						            
						            java.util.Date itemshippedOn1	 =  row.getCell(29).getDateCellValue();
						            String shippedOn		  = df.format(itemshippedOn1);
						            
						            java.util.Date itemshippedOn12	 =  row.getCell(30).getDateCellValue();
						            String deliveredOn		  = df.format(itemshippedOn12);
			                		
						            String FwdTrackingID   = formatter.formatCellValue(row.getCell(22));
			                		
			                		
						            String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=?,packedOn=?,scanOn=?,shippedOn=?,deliveredOn=?,packetID=?,sellerPacketId=?,FwdTrackingID=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								      preparedstmt12.setString(2, packedOn);
								      preparedstmt12.setString(3, scanOn);
								      preparedstmt12.setString(4, shippedOn);
								      preparedstmt12.setString(5, deliveredOn);
								      preparedstmt12.setString(6, packetID);
								      preparedstmt12.setString(7,sellerPacketId );
								      preparedstmt12.setString(8,FwdTrackingID );
								      preparedstmt12.setString(9,orderReleaseId );
								      
								      String sql12 = "UPDATE IGNORE myntra_ppmp_order set orderStatus=?,packedOn=?,storePacketId=?,trackingId=? where orderReleaseId=?";
								      PreparedStatement preparedstmt122 = conn.prepareStatement(sql12);
								      preparedstmt122.setString(1, orderStatus);
								      preparedstmt122.setString(2, packedOn);
								      
								      preparedstmt122.setString(3, packetID);
								      preparedstmt122.setString(4, FwdTrackingID);
								     
								      preparedstmt122.setString(5,orderReleaseId );
								      
								      
								      preparedstmt12.execute();
								      preparedstmt122.execute();
			                	}else if((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("RTO"))&&(uniqueKeyorderStatus.equalsIgnoreCase("WP"))) {
			                		String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set orderStatus=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								     
								      preparedstmt12.setString(2,orderReleaseId );
								      
								      String sql12 = "UPDATE IGNORE myntra_ppmp_order set orderStatus=?,storePacketId=? where orderReleaseId=?";
								      PreparedStatement preparedstmt122 = conn.prepareStatement(sql12);
								      preparedstmt122.setString(1, orderStatus);
								      
								      
								      preparedstmt122.setString(2, packetID);
								      
								     
								      preparedstmt122.setString(3,orderReleaseId );
								      
								      
								      preparedstmt12.execute();
								      preparedstmt122.execute();
								      
			                	}
			                	else {
			                		String sellerID	  = formatter.formatCellValue(row.getCell(0));
			                		String warehouseID	 = formatter.formatCellValue(row.getCell(1));
			                		String storeOrderID	 = formatter.formatCellValue(row.getCell(2));
			                		String orderLineID	  = formatter.formatCellValue(row.getCell(4));
			                		String sellerOrderID   = formatter.formatCellValue(row.getCell(5));
			                		String orderIDFK   = formatter.formatCellValue(row.getCell(6));
			                		
			                		 java.util.Date itemcreatedOn	 =  row.getCell(7).getDateCellValue();
							            String createdOn		  = df.format(itemcreatedOn);
							        
							            String styleID   = formatter.formatCellValue(row.getCell(8));
							            
							            String sellerSKUCode  = row.getCell(9).getStringCellValue();
							            
							            String skuID   = formatter.formatCellValue(row.getCell(10));
							            
							            String myntraSKU  = row.getCell(11).getStringCellValue();
							            
							            String size   = formatter.formatCellValue(row.getCell(12));
							            
							            String vendorArticleNumber   = formatter.formatCellValue(row.getCell(13));
							            
							            String brand   = formatter.formatCellValue(row.getCell(14));
							            
							            String articleType  = row.getCell(16).getStringCellValue();
							            
							            String artilceTypeId   = formatter.formatCellValue(row.getCell(17));
							            
							            
							            
							            String courierCode   = formatter.formatCellValue(row.getCell(21));
							            
							            String FwdTrackingID   = formatter.formatCellValue(row.getCell(22));
							            
							            java.util.Date itempackedOn	 =  row.getCell(26).getDateCellValue();
							            String packedOn		  = df.format(itempackedOn);
							            
							            
							            java.util.Date itemscanOn	 =  row.getCell(28).getDateCellValue();
							            String scanOn		  = df.format(itemscanOn);
							            
							            java.util.Date itemshippedOn	 =  row.getCell(29).getDateCellValue();
							            String shippedOn		  = df.format(itemshippedOn);
							            
							            java.util.Date itemdeliveredOn	 =  row.getCell(30).getDateCellValue();
							            String deliveredOn		  = df.format(itemdeliveredOn);
							            
							            String paymentMethod	   = "cod";
							            
							            Double finalAmount   = (Double) row.getCell(35).getNumericCellValue();
							            
							            Double mrp   = (Double) row.getCell(36).getNumericCellValue();
							            
							             discount   = (Double) row.getCell(37).getNumericCellValue();
							            
							            String city	   = formatter.formatCellValue(row.getCell(42));
							            
							            String state	   = formatter.formatCellValue(row.getCell(43));
							            
							            String pincode	   = formatter.formatCellValue(row.getCell(44));
							            
							            String sql51= "select orderReleaseId from myntra_ppmp_order where orderReleaseID=?";
							            PreparedStatement preparedstmt51 = conn.prepareStatement(sql51);
							               preparedstmt51.setString(1, orderReleaseId);
							               
							               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
							               while(rscheckkey51.next()) {
							            	   uniqueKey11 = rscheckkey51.getString("orderReleaseId");
							            	  
							               }      
							               
							               if(orderReleaseId.equalsIgnoreCase(uniqueKey11)) {
							            	   isPresentinppmpOrder ="Yes";
							               }else {  
									            
									        /*    String uniqueKey2="";
									            String sql6= "select saleOrderCode from myntra_order where saleOrderCode=?";
									            PreparedStatement preparedstmt6 = conn.prepareStatement(sql6);
									               preparedstmt6.setString(1, saleOrderCode);
									               
									               ResultSet rscheckkey6 = preparedstmt6.executeQuery();
									               while(rscheckkey6.next()) {
									            	   uniqueKey2 = rscheckkey6.getString("saleOrderCode");
									               }
									            */
									           
									            String sql1= "select discount from myntra_current_discount where sku=?";
									            PreparedStatement preparedstmt2 = conn.prepareStatement(sql1);
									               preparedstmt2.setString(1, vendorArticleNumber);
									               
									               ResultSet rscheckkey = preparedstmt2.executeQuery();
									               while(rscheckkey.next()) {
									            	   discount1 = rscheckkey.getDouble("discount");
									               }
									               
									               String sql6 ="select zone from ppmp_pincode where pincode=?";
									               PreparedStatement preparedstmt6 = conn.prepareStatement(sql6);
									               preparedstmt6.setString(1, pincode);
									               ResultSet rscheckkey4 = preparedstmt6.executeQuery();
									               while(rscheckkey4.next()) {
									            	   zone = rscheckkey4.getString("zone");
									               }
									               
									               
									               sellingPrice= mrp*(100-discount1)/100;
									               if(articleType.equalsIgnoreCase("Kurtas")||articleType.equalsIgnoreCase("Kurta Sets")||articleType.equalsIgnoreCase("Ethnic Dresses")) {
									            	   commission=0.23*finalAmount;
									               }else if(articleType.equalsIgnoreCase("Night suits")){
									               commission= 0.19*finalAmount;
									               }else {
									            	   commission= 0.19*finalAmount; 
									               }
									               
									               String sql2 ="select levels from myntra_sku_level where sku=?";
									               PreparedStatement preparedstmt3 = conn.prepareStatement(sql2);
									               preparedstmt3.setString(1, sellerSKUCode);
									               ResultSet rscheckkey1 = preparedstmt3.executeQuery();
									               while(rscheckkey1.next()) {
									            	   levels = rscheckkey1.getString("levels");
									               }
									               
									               System.out.println(levels);
									               
									               //Need to move forward to check from here
									              String uniqueKey = levels+zone;
									              System.out.println(uniqueKey);
									              String sql3 =" select logisticCost from myntra_forward_logistics where uniqueKey=?";
									              PreparedStatement preparedstmt4 = conn.prepareStatement(sql3);
									               preparedstmt4.setString(1, uniqueKey);
									               
									               ResultSet rscheckkey2 = preparedstmt4.executeQuery();
									               while(rscheckkey2.next()) {
									            	   fwdShipping = rscheckkey2.getDouble("logisticCost");
									               }
									               
									               System.out.println(fwdShipping);
									              
									            	   pickandpackFee =0.0;
									              
									               if(paymentMethod.equalsIgnoreCase("cod")) {
									               if((finalAmount*0.02)<20) {
									            	   paymentFee=20.0;
									               }else {
									            	   paymentFee=finalAmount*0.02;
									               }
									               }else {
									            	   paymentFee=finalAmount*0.02;
									               }
									               if(finalAmount<500) {
									            	   fixedFee=7.0;
									               }else if (finalAmount>1000 ){
									            	   fixedFee =38.0;
									               }else {
									            	   fixedFee =15.0;
									               }
									            		  
									               
									               taxonCommisson = commission*0.18;
									               taxonLogistics = (pickandpackFee+paymentFee+fwdShipping+fixedFee)*0.18;
									               
									               taxablePrice = finalAmount/1.05;
									               if(taxablePrice<1000) {
									            	   taxablePrice=finalAmount/1.05;
									               }else {
									            	   taxablePrice=finalAmount/1.12;
									               }
									               tcsTax =taxablePrice*0.01;
									               tdsTax =taxablePrice*0.01;
									               estimatedValue = finalAmount - (commission +tdsTax+pickandpackFee +paymentFee +fixedFee+fwdShipping +taxonCommisson+taxonLogistics+tcsTax);
									               
									               
									            	   
									              
									               
								                
								               String sql = " INSERT ignore INTO myntra_ppmp_order Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
											      PreparedStatement preparedstmt = conn.prepareStatement(sql);
											      
											      preparedstmt.setString(1, orderReleaseId );
											      preparedstmt.setString(2, createdOn );
											      preparedstmt.setString(3, sellerSKUCode );
											      preparedstmt.setString(4, myntraSKU );
											      preparedstmt.setString(5, orderIDFK );
											      preparedstmt.setString(6, packedOn );
											      preparedstmt.setDouble(7, mrp );
											      preparedstmt.setDouble(8, finalAmount );
											      preparedstmt.setString(9, packetID );
											      preparedstmt.setString(10, orderStatus );
											      
											      preparedstmt.setString(11, pincode );
											      preparedstmt.setString(12, state );
											      preparedstmt.setString(13, FwdTrackingID );
											     
											      preparedstmt.setString(14, "No" );
											      preparedstmt.setString(15, "NA" );
											      preparedstmt.setString(16, "No" );
											      preparedstmt.setString(17, "No" );
											      preparedstmt.setDouble(18, discount1 );
											      preparedstmt.setDouble(19, commission );
											      preparedstmt.setDouble(20, fwdShipping );
											      preparedstmt.setDouble(21,  pickandpackFee);
											      preparedstmt.setDouble(22,  fixedFee);
											      preparedstmt.setDouble(23, paymentFee );
											      preparedstmt.setDouble(24, 0 );
											      preparedstmt.setDouble(25,  taxonCommisson);
											      preparedstmt.setDouble(26,  taxonLogistics);
											      preparedstmt.setDouble(27,  tcsTax);
											      preparedstmt.setDouble(28,  tdsTax);
											      preparedstmt.setDouble(29,  estimatedValue);
											      preparedstmt.setDouble(30,  0);
											      preparedstmt.setDouble(31,  0);
											      preparedstmt.setDouble(32,  0);
											      preparedstmt.setDouble(33,  0);
											      preparedstmt.setString(34, "No" );
											      preparedstmt.setString(35, "NA" );
											      preparedstmt.setString(36, "NA");
											      preparedstmt.setString(37, "N" );
											      preparedstmt.setString(38, "N");
											      preparedstmt.setString(39, "NA");
											      preparedstmt.setString(40, "NA");
											      preparedstmt.execute(); 
											      isPresentinppmpOrder ="Yes";
							               }
				              				               
			                
			               String sql = " INSERT ignore INTO myntra_ppmp_order_report Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						      PreparedStatement preparedstmt = conn.prepareStatement(sql);
						      
						      preparedstmt.setString(1, sellerID );
						      preparedstmt.setString(2, warehouseID );
						      preparedstmt.setString(3, storeOrderID );
						      preparedstmt.setString(4, orderReleaseId );
						      preparedstmt.setString(5, orderLineID );
						      preparedstmt.setString(6, sellerOrderID );
						      preparedstmt.setString(7, orderIDFK );
						      preparedstmt.setString(8, createdOn );
						      preparedstmt.setString(9, styleID );
						      preparedstmt.setString(10, sellerSKUCode );
						      
						      preparedstmt.setString(11, skuID );
						      preparedstmt.setString(12, myntraSKU );
						      preparedstmt.setString(13, size );
						     
						     		      
						      preparedstmt.setString(14, vendorArticleNumber);
						      preparedstmt.setString(15, brand);
						      preparedstmt.setString(16, articleType );
						      preparedstmt.setString(17, artilceTypeId);
						      preparedstmt.setString(18, orderStatus );
						      preparedstmt.setString(19, packetID );
						      preparedstmt.setString(20, sellerPacketId );
						      preparedstmt.setString(21,  courierCode);
						      preparedstmt.setString(22,  FwdTrackingID);
						      preparedstmt.setString(23, packedOn );
						      preparedstmt.setString(24, scanOn );
						      preparedstmt.setString(25,  shippedOn);
						      preparedstmt.setString(26,  deliveredOn);
						      preparedstmt.setString(27,  paymentMethod);
						      preparedstmt.setDouble(28,  finalAmount);
						      preparedstmt.setDouble(29,  mrp);
						      preparedstmt.setDouble(30,  discount);
						      preparedstmt.setString(31,  city);
						      preparedstmt.setString(32,  state);
						      preparedstmt.setString(33,  pincode);
						      preparedstmt.setString(34, isPresentinppmpOrder);
						      preparedstmt.setString(35, "N");
						      preparedstmt.setString(36, "N");
						      preparedstmt.setString(37, "NA");
						      preparedstmt.setString(38, "N");
						      preparedstmt.setString(39, "NA");
						     
						      
						      preparedstmt.execute(); 
						      
						      if(paymentMethod.equalsIgnoreCase("cod")) {
						       
						            String sql1 = "UPDATE IGNORE myntra_ppmp_order set paymentMethod=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, paymentMethod);
								     
								      preparedstmt12.setString(2,orderReleaseId );
								      
								      preparedstmt12.execute(); 
						            } else {
						            	paymentFee1=finalAmount*0.02;
						            	String sql1 = "UPDATE IGNORE myntra_ppmp_order set paymentMethod=?,estimatedSettleValue=estimatedSettleValue+paymentFee-?,paymentFee=? where orderReleaseID=?";
									      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
									      preparedstmt12.setString(1, paymentMethod);
									      
									      preparedstmt12.setDouble(2, paymentFee1);
									      preparedstmt12.setDouble(3, paymentFee1);
									      preparedstmt12.setString(4,orderReleaseId );
									      
									      preparedstmt12.execute();
						            }
			              			                		               
						      System.out.println("Imported Rows"+i);
						      
			                	}
				               
				               }
			                } 
			            }
				               
			     
			      
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e1){
		      //Handle errors for Class.forName
		      e1.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   path.setText("Transfer Complete");
		}
	});
	headerLabel.setBounds(150, 11, 400, 31);
	statusLabel.setBounds(180, 340, 312, 31);
	headerLabel.setText("Popnetic Warehousing System");
	controlPanel.add(openfile);
	controlPanel.add(path);
	controlPanel.add(transfer);
	controlPanel.add(back);
}


//PPMP Return Report Launch

private void myntrareturnReportLaunch() {
	prepareGUI("Myntra PPMP Return Report",700,398);
	
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosingppmpreturnreportLaunch(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	JButton openfile = new JButton("Open");
	openfile.setForeground(SystemColor.desktop);
	openfile.setBackground(SystemColor.activeCaption);
	openfile.setBounds(150, 140, 94, 31);
	openfile.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton transfer = new JButton("Transfer");
	transfer.setForeground(SystemColor.desktop);
	transfer.setBackground(SystemColor.activeCaption);
	transfer.setBounds(450, 140, 94, 31);
	
	
	JButton back = new JButton("Back");
	back.setBounds(300, 300, 94, 31);
	back.setForeground(SystemColor.desktop);
	back.setBackground(SystemColor.controlDkShadow);
	
	
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent backflipkartOrderEvent) {
			
			mainFrame.dispose();
			returnOrderLaunch();
			
		}
	});
	JLabel path = new JLabel("NO FILES Selected");
	path.setBounds(250, 260, 300, 31);
	
	openfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent openfileEvent) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			 int r = j.showOpenDialog(null); 
			 if (r == JFileChooser.APPROVE_OPTION) 
				  
	            { 
	                // set the label to the path of the selected file 
	                path.setText(j.getSelectedFile().getAbsolutePath()); 
	            } 
	            // if the user cancelled the operation 
	            else
	                path.setText("the user cancelled the operation");
			
		}
		
		
		
	});
	
	transfer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent transferEvent) {
			
			Connection conn = null;
			PreparedStatement preparedstmt1;
			   Statement stmt = null;
			   
			   try{
				 //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");

				      //STEP 3: Open a connection
				      System.out.println("Connecting to a selected database...");
				      conn = DriverManager.getConnection(DB_URL, USER, PASS);
				      System.out.println("Connected database successfully...");
				     // PreparedStatement pstm = null ;
				      
				      
				      //Reading the selected excel File
				     
				      FileInputStream input = new FileInputStream(path.getText());
				      System.out.println(input);
				      POIFSFileSystem fs = new POIFSFileSystem( input );
				      HSSFWorkbook wb = new HSSFWorkbook(fs);
				      HSSFSheet sheet = wb.getSheetAt(0);
				      HSSFRow row;
				      for(int i=1; i<=sheet.getLastRowNum(); i++){
			                row = sheet.getRow(i);
			                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			               
			               
			               DataFormatter formatter = new DataFormatter();
			               //System.out.println(reportDate);
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
			               
			              String uniqueKey1="";
			              String uniqueKeyorderStatus="";
			              
			              String returnTrackingId="";
			             String returnId="";
			              Double revLogistics=0.0;
			              String pincode="";
			              String levels="";
			              String zone="";
			               
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
			            //   Date orderedOn = Date.valueOf(orderedOnString);
			                //System.out.println(orderOn);
			              String orderReleaseId    = formatter.formatCellValue(row.getCell(14));
			                System.out.println(orderReleaseId);
			                
			                String orderStatus    = row.getCell(16).getStringCellValue();
			                System.out.println(orderStatus);
			                String sql5= "select orderReleaseId,orderStatus from ppmp_return_report where orderReleaseId=?";
				            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
				               preparedstmt5.setString(1, orderReleaseId);
				               
				               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
				               while(rscheckkey5.next()) {
				            	   uniqueKey1 = rscheckkey5.getString("orderReleaseId");
				            	   uniqueKeyorderStatus=rscheckkey5.getString("orderStatus");
				               }
				               
				               
			                if(orderReleaseId.equalsIgnoreCase(uniqueKey1)) {
			                	System.out.println("No Need for operations");
			                }else {
			                	
			                	if(orderStatus.equalsIgnoreCase("Return")) {
			                		 
			                		String sellerID	  = formatter.formatCellValue(row.getCell(0));
			                		String warehouseID	 = formatter.formatCellValue(row.getCell(1));
			                		String sellerSkuCode	 = formatter.formatCellValue(row.getCell(3));
			                		String styleId	 = formatter.formatCellValue(row.getCell(4));
			                		String skuId	 = formatter.formatCellValue(row.getCell(5));
			                		String brand	 = formatter.formatCellValue(row.getCell(6));
			                		
			                		String [] words = sellerSkuCode.split("_");
						            String sku=words[0];
			                		
			                		
			                		java.util.Date itemCreatedOn =  row.getCell(7).getDateCellValue();
							            String orderCreatedOn	  = df.format(itemCreatedOn);
							            
							            java.util.Date itemshippedOn	 =  row.getCell(8).getDateCellValue();
							            String deliveredOn		  = df.format(itemshippedOn);
							            
							            java.util.Date itemreturnOn	 =  row.getCell(9).getDateCellValue();
							            String returnOn		  = df.format(itemreturnOn);
							            
							            java.util.Date itemrefundedOn	 =  row.getCell(10).getDateCellValue();
							            String refundedOn		  = df.format(itemrefundedOn);
							            
							            
							            String cancelOn		  = "1990-01-01";
							            
							            String orderGroupId	 = formatter.formatCellValue(row.getCell(15));
							            
							            String storePacketId	 = formatter.formatCellValue(row.getCell(17));
							            
							            String sellerPacketId	 = formatter.formatCellValue(row.getCell(18));
							            
							            if((orderStatus.equalsIgnoreCase("Return"))){
							            	
							            	returnId=formatter.formatCellValue(row.getCell(20));
							            	returnTrackingId=formatter.formatCellValue(row.getCell(25));
		
							            }else {
							            	returnId=formatter.formatCellValue(row.getCell(24));
							            	returnTrackingId=formatter.formatCellValue(row.getCell(24));
							            }
							            
							            String reason=formatter.formatCellValue(row.getCell(22));
							            
							            String fwdTrackingId	 = formatter.formatCellValue(row.getCell(24));
							            
							            String sql51= "select destinationPincode from myntra_ppmp_order where orderReleaseID=?";
							            PreparedStatement preparedstmt51 = conn.prepareStatement(sql51);
							               preparedstmt51.setString(1, orderReleaseId);
							               
							               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
							               while(rscheckkey51.next()) {
							            	   pincode = rscheckkey51.getString("destinationPincode");
							            	   
							               }
							               
							               String sql6 ="select zone from ppmp_pincode where pincode=?";
							               PreparedStatement preparedstmt6 = conn.prepareStatement(sql6);
							               preparedstmt6.setString(1, pincode);
							               ResultSet rscheckkey4 = preparedstmt6.executeQuery();
							               while(rscheckkey4.next()) {
							            	   zone = rscheckkey4.getString("zone");
							               }
							               
							               
							               
							               
							               
							               String sql2 ="select levels from myntra_sku_level where sku=?";
							               PreparedStatement preparedstmt3 = conn.prepareStatement(sql2);
							               preparedstmt3.setString(1, sellerSkuCode);
							               ResultSet rscheckkey1 = preparedstmt3.executeQuery();
							               while(rscheckkey1.next()) {
							            	   levels = rscheckkey1.getString("levels");
							               }
							               
							               System.out.println(levels);
							               
							               //Need to move forward to check from here
							              String uniqueKey = levels+zone;
							              System.out.println(uniqueKey);
							              String sql3 =" select logisticCost from myntra_reverse_logistics where uniqueKey=?";
							              PreparedStatement preparedstmt4 = conn.prepareStatement(sql3);
							               preparedstmt4.setString(1, uniqueKey);
							               
							               ResultSet rscheckkey2 = preparedstmt4.executeQuery();
							               while(rscheckkey2.next()) {
							            	   revLogistics = rscheckkey2.getDouble("logisticCost");
							               }
							            
							            String sql = " INSERT ignore INTO ppmp_return_report Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
									      PreparedStatement preparedstmt = conn.prepareStatement(sql);
									      
									      preparedstmt.setString(1, sellerID );
									      preparedstmt.setString(2, warehouseID );
									      preparedstmt.setString(3, sellerSkuCode	 );
									      preparedstmt.setString(4, styleId	 );
									      preparedstmt.setString(5, skuId	 );
									      preparedstmt.setString(6, brand	 );
									      preparedstmt.setString(7, orderCreatedOn	 );
									      preparedstmt.setString(8, deliveredOn	 );
									      preparedstmt.setString(9, returnOn	 );
									      preparedstmt.setString(10, refundedOn	 );
									      
									      preparedstmt.setString(11, cancelOn	 );
									      preparedstmt.setString(12, orderReleaseId	 );
									      preparedstmt.setString(13, orderGroupId	 );
									     
									     		      
									      preparedstmt.setString(14, orderStatus	);
									      preparedstmt.setString(15, storePacketId	);
									      preparedstmt.setString(16, sellerPacketId	 );
									      preparedstmt.setString(17, fwdTrackingId);
									      preparedstmt.setString(18, returnTrackingId);
									      preparedstmt.setString(19, returnId);
									      preparedstmt.setString(20, reason);
									     
									      
									      preparedstmt.execute(); 
							            
							            
							            
							            
							            String sql1 = "UPDATE IGNORE myntra_ppmp_order set isPresentInReturnReport=?,returnReason=?,revLogistics=?,estimatedSettleValue=?,estimatedRevCharges=(-fwdLogistics-paymentFee-?)*1.18 where orderReleaseID=?";
									      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
									      preparedstmt12.setString(1, "Yes");
									      preparedstmt12.setString(2, "CR");
									      preparedstmt12.setDouble(3, revLogistics);
									      preparedstmt12.setDouble(4, 0);
									      preparedstmt12.setDouble(5, revLogistics);
									      preparedstmt12.setString(6,orderReleaseId );
									      
									      preparedstmt12.execute();
									      
									      String sql13 = "UPDATE IGNORE myntra_ppmp_order_report set isPresentInReturnReport=?,returnReason=? where orderReleaseID=?";
									      PreparedStatement preparedstmt123 = conn.prepareStatement(sql13);
									      preparedstmt123.setString(1, "Yes");
									      preparedstmt123.setString(2, "CR");
									      preparedstmt123.setString(3,orderReleaseId );
									      
									      preparedstmt123.execute();
									      
									      System.out.println("Imported Rows"+i);
							            
							           
			                	}else {
			                		String sellerID	  = formatter.formatCellValue(row.getCell(0));
			                		String warehouseID	 = formatter.formatCellValue(row.getCell(1));
			                		String sellerSkuCode	 = formatter.formatCellValue(row.getCell(3));
			                		String styleId	 = formatter.formatCellValue(row.getCell(4));
			                		String skuId	 = formatter.formatCellValue(row.getCell(5));
			                		String brand	 = formatter.formatCellValue(row.getCell(6));
			                		
			                		
			                		
			                		
			                		java.util.Date itemCreatedOn =  row.getCell(7).getDateCellValue();
							            String orderCreatedOn	  = df.format(itemCreatedOn);
							            
							            
							            String deliveredOn		  = "1990-01-01";
							            
							            
							            String returnOn		  = "1990-01-01";
							            
							            
							            String refundedOn		  = "1990-01-01";
							            
							            java.util.Date itemcancelOn	 =  row.getCell(11).getDateCellValue();
							            String cancelOn		  = df.format(itemcancelOn);
							            
							            
							            
							            String orderGroupId	 = formatter.formatCellValue(row.getCell(15));
							            
							            String storePacketId	 = formatter.formatCellValue(row.getCell(17));
							            
							            String sellerPacketId	 = formatter.formatCellValue(row.getCell(18));
							            
							            String fwdTrackingId	 = formatter.formatCellValue(row.getCell(24));
							            
							            if((orderStatus.equalsIgnoreCase("Return"))){
							            	
							            	returnId=formatter.formatCellValue(row.getCell(20));
							            	returnTrackingId=formatter.formatCellValue(row.getCell(25));
		
							            }else {
							            	returnId=formatter.formatCellValue(row.getCell(24));
							            			returnTrackingId=formatter.formatCellValue(row.getCell(24));
							            }
							            
							            String reason=formatter.formatCellValue(row.getCell(22));
							            
							              String sql = " INSERT ignore INTO ppmp_return_report Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
									      PreparedStatement preparedstmt = conn.prepareStatement(sql);
									      
									      preparedstmt.setString(1, sellerID );
									      preparedstmt.setString(2, warehouseID );
									      preparedstmt.setString(3, sellerSkuCode	 );
									      preparedstmt.setString(4, styleId	 );
									      preparedstmt.setString(5, skuId	 );
									      preparedstmt.setString(6, brand	 );
									      preparedstmt.setString(7, orderCreatedOn	 );
									      preparedstmt.setString(8, deliveredOn	 );
									      preparedstmt.setString(9, returnOn	 );
									      preparedstmt.setString(10, refundedOn	 );
									      
									      preparedstmt.setString(11, cancelOn	 );
									      preparedstmt.setString(12, orderReleaseId	 );
									      preparedstmt.setString(13, orderGroupId	 );
									     
									     		      
									      preparedstmt.setString(14, orderStatus	);
									      preparedstmt.setString(15, storePacketId	);
									      preparedstmt.setString(16, sellerPacketId	 );
									      preparedstmt.setString(17, fwdTrackingId);
									      preparedstmt.setString(18, returnTrackingId);
									      preparedstmt.setString(19, returnId);
									      preparedstmt.setString(20, reason);
									     
									      
									      preparedstmt.execute(); 
							            
							            
							            
							            
							            String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set isPresentInReturnReport=?,returnReason=? where orderReleaseID=?";
									      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
									      preparedstmt12.setString(1, "Yes");
									      preparedstmt12.setString(2, "RTO");
									      preparedstmt12.setString(3,orderReleaseId );
									      
									      preparedstmt12.execute();
									      
									      String sql13 = "UPDATE IGNORE myntra_ppmp_order set isPresentInReturnReport=?,returnReason=? where orderReleaseID=?";
									      PreparedStatement preparedstmt123 = conn.prepareStatement(sql13);
									      preparedstmt123.setString(1, "Yes");
									      preparedstmt123.setString(2, "RTO");
									      preparedstmt123.setString(3,orderReleaseId );
									      
									      preparedstmt123.execute();
									      
									      System.out.println("Imported Rows"+i);
			                	}
						      
			                	}
				               
			                }
				      }
			 
			                
			            
				               
			     
			      
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e1){
		      //Handle errors for Class.forName
		      e1.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   path.setText("Transfer Complete");
		}
	});
	headerLabel.setBounds(150, 11, 400, 31);
	statusLabel.setBounds(180, 340, 312, 31);
	headerLabel.setText("Popnetic Warehousing System");
	controlPanel.add(openfile);
	controlPanel.add(path);
	controlPanel.add(transfer);
	controlPanel.add(back);
}

//PPMP Return Alert Report

private void myntrareturnAlertLaunch() {
	prepareGUI("Myntra PPMP Return Alert",700,398);
	
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosingppmpreturnreportLaunch(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	JButton openfile = new JButton("Open");
	openfile.setForeground(SystemColor.desktop);
	openfile.setBackground(SystemColor.activeCaption);
	openfile.setBounds(150, 140, 94, 31);
	openfile.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton transfer = new JButton("Transfer");
	transfer.setForeground(SystemColor.desktop);
	transfer.setBackground(SystemColor.activeCaption);
	transfer.setBounds(450, 140, 94, 31);
	
	
	JButton back = new JButton("Back");
	back.setBounds(300, 300, 94, 31);
	back.setForeground(SystemColor.desktop);
	back.setBackground(SystemColor.controlDkShadow);
	
	
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent backflipkartOrderEvent) {
			
			mainFrame.dispose();
			returnOrderLaunch();
			
		}
	});
	JLabel path = new JLabel("NO FILES Selected");
	path.setBounds(250, 260, 300, 31);
	
	openfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent openfileEvent) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			 int r = j.showOpenDialog(null); 
			 if (r == JFileChooser.APPROVE_OPTION) 
				  
	            { 
	                // set the label to the path of the selected file 
	                path.setText(j.getSelectedFile().getAbsolutePath()); 
	            } 
	            // if the user cancelled the operation 
	            else
	                path.setText("the user cancelled the operation");
			
		}
		
		
		
	});
	
	transfer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent transferEvent) {
			
			Connection conn = null;
			PreparedStatement preparedstmt1;
			   Statement stmt = null;
			   
			   try{
				 //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");

				      //STEP 3: Open a connection
				      System.out.println("Connecting to a selected database...");
				      conn = DriverManager.getConnection(DB_URL, USER, PASS);
				      System.out.println("Connected database successfully...");
				     // PreparedStatement pstm = null ;
				      
				      
				      //Reading the selected excel File
				     
				      FileInputStream input = new FileInputStream(path.getText());
				      System.out.println(input);
				      POIFSFileSystem fs = new POIFSFileSystem( input );
				      HSSFWorkbook wb = new HSSFWorkbook(fs);
				      HSSFSheet sheet = wb.getSheetAt(0);
				      HSSFRow row;
				      for(int i=1; i<=sheet.getLastRowNum(); i++){
			                row = sheet.getRow(i);
			                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			               
			               
			               DataFormatter formatter = new DataFormatter();
			               //System.out.println(reportDate);
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
			               
			              String uniqueKey1="";
			              String uniqueKeyorderStatus="";
			              
			              String returnTrackingId="";
			             String returnId="";
			              Double revLogistics=0.0;
			              String pincode="";
			              String levels="";
			              String zone="";
			               
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
			            //   Date orderedOn = Date.valueOf(orderedOnString);
			                //System.out.println(orderOn);
			              String orderReleaseId    = formatter.formatCellValue(row.getCell(8));
			                System.out.println(orderReleaseId);
			                String orderStatus= row.getCell(16).getStringCellValue();
			                String returnType    = row.getCell(4).getStringCellValue();
			                System.out.println(returnType);
			                String sql5= "select orderReleaseId,returnStatus from ppmp_return_alert where orderReleaseId=?";
				            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
				               preparedstmt5.setString(1, orderReleaseId);
				               
				               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
				               while(rscheckkey5.next()) {
				            	   uniqueKey1 = rscheckkey5.getString("orderReleaseId");
				            	   uniqueKeyorderStatus=rscheckkey5.getString("returnStatus");
				               }
				               
				               
			                if(orderReleaseId.equalsIgnoreCase(uniqueKey1)&&(uniqueKeyorderStatus.equalsIgnoreCase(orderStatus))) {
			                	System.out.println("No Need for operations");
			                }else {
			                	
			                	if(((orderReleaseId.equalsIgnoreCase(uniqueKey1))&&(orderStatus.equalsIgnoreCase("DELIVERED"))&&((uniqueKeyorderStatus.equalsIgnoreCase("CLOSED"))))) {
			                		
			                		String sql1 = "UPDATE IGNORE ppmp_return_alert set returnStatus=? where orderReleaseID=?";
								      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
								      preparedstmt12.setString(1, orderStatus);
								     
								      preparedstmt12.setString(2,orderReleaseId );
								      
								      preparedstmt12.execute();
			                		 
			                		
							            
							            
							            
							           
			                	}else {
			                		String indexId	  = formatter.formatCellValue(row.getCell(1));
			                		String masterBagId	 = formatter.formatCellValue(row.getCell(3));
			                		String trackingId	 = formatter.formatCellValue(row.getCell(6));
			                		String scanId	 = formatter.formatCellValue(row.getCell(7));
			                		
			                		String packetId	 = formatter.formatCellValue(row.getCell(9));
			                		
			                		
			                		
			                		
			                		java.util.Date itemgpCreatedOn =  row.getCell(10).getDateCellValue();
							            String gpCreatedOn	  = df.format(itemgpCreatedOn);
							            
							            
							            
							            
							            
							            
							            String vendorArticleNo	 = formatter.formatCellValue(row.getCell(13));
							            
							            String skuId	 = formatter.formatCellValue(row.getCell(14));
							            
							            String gpCreatedBy	 = formatter.formatCellValue(row.getCell(23));
							            
							            String orderLineId	 = formatter.formatCellValue(row.getCell(33));
							            String sellerOrderId	 = formatter.formatCellValue(row.getCell(34));
							            
							            
							            
							            if((returnType.equalsIgnoreCase("RTO"))){
							            	
							            	returnId=formatter.formatCellValue(row.getCell(6));
							            	
		
							            }else {
							            	returnId=formatter.formatCellValue(row.getCell(5));
							            			
							            }
							            
							            String sql = " INSERT ignore INTO ppmp_return_alert Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
									      PreparedStatement preparedstmt = conn.prepareStatement(sql);
									      
									      preparedstmt.setString(1, indexId );
									      preparedstmt.setString(2, masterBagId );
									      preparedstmt.setString(3, returnType	 );
									      preparedstmt.setString(4, returnId	 );
									      preparedstmt.setString(5, trackingId	 );
									      preparedstmt.setString(6, scanId	 );
									      preparedstmt.setString(7, orderReleaseId	 );
									      preparedstmt.setString(8, packetId	 );
									      preparedstmt.setString(9, gpCreatedOn	 );
									      preparedstmt.setString(10, vendorArticleNo	 );
									      
									      preparedstmt.setString(11, skuId	 );
									      preparedstmt.setString(12, orderStatus	 );
									      preparedstmt.setString(13, gpCreatedBy	 );
									     
									     		      
									      preparedstmt.setString(14, orderLineId	);
									      preparedstmt.setString(15, sellerOrderId	);
									      									     									     									      
									      preparedstmt.execute(); 
							            
							            
							            
							            
							            String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set isPresentInReturnAlert=? where orderReleaseID=?";
									      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
									      preparedstmt12.setString(1, "Yes");
									      
									      preparedstmt12.setString(2,orderReleaseId );
									      
									      preparedstmt12.execute();
									      
									      String sql13 = "UPDATE IGNORE myntra_ppmp_order set isPresentInReturnAlert=? where orderReleaseId=?";
									      PreparedStatement preparedstmt123 = conn.prepareStatement(sql13);
									      preparedstmt123.setString(1, "Yes");
									      
									      preparedstmt123.setString(2,orderReleaseId );
									      
									      preparedstmt123.execute();
									      
									      System.out.println("Imported Rows"+i);
			                	}
						      
			                	}
				               
			                }
				      }
			 
			                
			            
				               
			     
			      
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e1){
		      //Handle errors for Class.forName
		      e1.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   path.setText("Transfer Complete");
		}
	});
	headerLabel.setBounds(150, 11, 400, 31);
	statusLabel.setBounds(180, 340, 312, 31);
	headerLabel.setText("Popnetic Warehousing System");
	controlPanel.add(openfile);
	controlPanel.add(path);
	controlPanel.add(transfer);
	controlPanel.add(back);
}


//PPMP Return Delivered Tab

private void myntrareturnDLaunch() {
	prepareGUI("Myntra PPMP Return Delivered",700,398);
	
	mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosingppmpreturnreportLaunch(WindowEvent windowEvent) {
			System.exit(0);
		}
	});
	
	JButton openfile = new JButton("Open");
	openfile.setForeground(SystemColor.desktop);
	openfile.setBackground(SystemColor.activeCaption);
	openfile.setBounds(150, 140, 94, 31);
	openfile.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton transfer = new JButton("Transfer");
	transfer.setForeground(SystemColor.desktop);
	transfer.setBackground(SystemColor.activeCaption);
	transfer.setBounds(450, 140, 94, 31);
	
	
	JButton back = new JButton("Back");
	back.setBounds(300, 300, 94, 31);
	back.setForeground(SystemColor.desktop);
	back.setBackground(SystemColor.controlDkShadow);
	
	
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent backflipkartOrderEvent) {
			
			mainFrame.dispose();
			returnOrderLaunch();
			
		}
	});
	JLabel path = new JLabel("NO FILES Selected");
	path.setBounds(250, 260, 300, 31);
	
	openfile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent openfileEvent) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			 int r = j.showOpenDialog(null); 
			 if (r == JFileChooser.APPROVE_OPTION) 
				  
	            { 
	                // set the label to the path of the selected file 
	                path.setText(j.getSelectedFile().getAbsolutePath()); 
	                
	            } 
	            // if the user cancelled the operation 
	            else
	                path.setText("the user cancelled the operation");
			
		}
		
		
		
	});
	
	transfer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent transferEvent) {
			
			Connection conn = null;
			PreparedStatement preparedstmt1;
			   Statement stmt = null;
			   
			   try{
				 //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");

				      //STEP 3: Open a connection
				      System.out.println("Connecting to a selected database...");
				      conn = DriverManager.getConnection(DB_URL, USER, PASS);
				      System.out.println("Connected database successfully...");
				     // PreparedStatement pstm = null ;
				      
					   Date currentDatetime = new Date(System.currentTimeMillis());
					   java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
					   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
					   
					   System.out.println(path);
					   Path p = Paths.get(path.getText());
					   Path filename1=p.getFileName();
					   System.out.println(filename1);
					  String filename2= filename1.toString();
					   
					   String [] words = filename2.split("_");
			            String fileDate=words[2];
			            System.out.println(fileDate);
			            String fileDate2=fileDate.substring(0,10);
			            
					   
				      //Reading the selected excel File
				     
				      FileInputStream input = new FileInputStream(path.getText());
				      System.out.println(input);
				      POIFSFileSystem fs = new POIFSFileSystem( input );
				      HSSFWorkbook wb = new HSSFWorkbook(fs);
				      HSSFSheet sheet = wb.getSheetAt(0);
				      HSSFRow row;
				      for(int i=1; i<=sheet.getLastRowNum(); i++){
			                row = sheet.getRow(i);
			                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			               
			               
			               DataFormatter formatter = new DataFormatter();
			               //System.out.println(reportDate);
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnDate);
			               
			               Date FinalFileDate= df.parse(fileDate2);
			               
			              String uniqueKey1="";
			              String uniqueKeyorderStatus="";
			              String orderReleaseId="";
			              String returnTrackingId="";
			             String returnId="";
			              Double revLogistics=0.0;
			              String pincode="";
			              String levels="";
			              String zone="";
			               
			               //java.sql.Date orderedOn = new java.sql.Date(orderedOnjava);
			            //   Date orderedOn = Date.valueOf(orderedOnString);
			                //System.out.println(orderOn);
			              String opsTrackingNumber    = formatter.formatCellValue(row.getCell(2));
			                System.out.println(opsTrackingNumber);
			                
			               
			                String sql5= "select opsTrackingNumber from ppmp_return where opsTrackingNumber=?";
				            PreparedStatement preparedstmt5 = conn.prepareStatement(sql5);
				               preparedstmt5.setString(1, opsTrackingNumber);
				               
				               ResultSet rscheckkey5 = preparedstmt5.executeQuery();
				               while(rscheckkey5.next()) {
				            	   uniqueKey1 = rscheckkey5.getString("opsTrackingNumber");
				            	   
				               }
				               
				               
			                if(opsTrackingNumber.equalsIgnoreCase(uniqueKey1)) {
			                	System.out.println("No Need for operations");
			                }else {
			                	
			                	String check=opsTrackingNumber.substring(0, 3);
			                	if(check.equalsIgnoreCase("MYS")) {
			                		String sql51= "select orderReleaseId from ppmp_return_report where returnTrackingId=?";
						            PreparedStatement preparedstmt51 = conn.prepareStatement(sql51);
						               preparedstmt51.setString(1, opsTrackingNumber);
						               
						               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
						               while(rscheckkey51.next()) {
						            	   orderReleaseId = rscheckkey51.getString("orderReleaseId");
						            	   String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set returnStatus=? where orderReleaseID=?";
										      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
										      preparedstmt12.setString(1, "Yes");
										      
										      preparedstmt12.setString(2,orderReleaseId );
										      
										      preparedstmt12.execute();
										      
										      String sql13 = "UPDATE IGNORE myntra_ppmp_order set returnStatus=? where orderReleaseId=?";
										      PreparedStatement preparedstmt123 = conn.prepareStatement(sql13);
										      preparedstmt123.setString(1, "Yes");
										      
										      preparedstmt123.setString(2,orderReleaseId );
										      
										      preparedstmt123.execute();
						               }
			                	}else {
			                		
			                	String sql51= "select orderReleaseId from ppmp_return_report where returnId=?";
					            PreparedStatement preparedstmt51 = conn.prepareStatement(sql51);
					               preparedstmt51.setString(1, opsTrackingNumber);
					               
					               ResultSet rscheckkey51 = preparedstmt51.executeQuery();
					               while(rscheckkey51.next()) {
					            	   orderReleaseId = rscheckkey51.getString("orderReleaseId");
					            	   String sql1 = "UPDATE IGNORE myntra_ppmp_order_report set returnStatus=? where orderReleaseID=?";
									      PreparedStatement preparedstmt12 = conn.prepareStatement(sql1);
									      preparedstmt12.setString(1, "Yes");
									      
									      preparedstmt12.setString(2,orderReleaseId );
									      
									      preparedstmt12.execute();
									      
									      String sql13 = "UPDATE IGNORE myntra_ppmp_order set returnStatus=? where orderReleaseId=?";
									      PreparedStatement preparedstmt123 = conn.prepareStatement(sql13);
									      preparedstmt123.setString(1, "Yes");
									      
									      preparedstmt123.setString(2,orderReleaseId );
									      
									      preparedstmt123.execute();
					            	   
					               }   
					               }    
			                	
							            
							            
									      
									      System.out.println("Imported Rows"+i);
					               }
			                		 
			                		
					               String masterBagId	 = formatter.formatCellValue(row.getCell(0));
			                		
			                		
			                		String sql = " INSERT ignore INTO ppmp_return Values(?,?,?)";
								      PreparedStatement preparedstmt = conn.prepareStatement(sql);
								      
								      preparedstmt.setString(1, masterBagId );
								      preparedstmt.setString(2, opsTrackingNumber );
								      preparedstmt.setString(3, fileDate2	 );
								      
								      									     									     									      
								      preparedstmt.execute(); 
							            
							            
							           
			                	
				      }
			                		
			                		
			                		
			                		
			                		
			                		
							            
							            
			                
						      
			                
				               
			                }
				
			 
			                
			            
				               
			     
			      
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e1){
		      //Handle errors for Class.forName
		      e1.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   path.setText("Transfer Complete");
		}
	});
	headerLabel.setBounds(150, 11, 400, 31);
	statusLabel.setBounds(180, 340, 312, 31);
	headerLabel.setText("Popnetic Warehousing System");
	controlPanel.add(openfile);
	controlPanel.add(path);
	controlPanel.add(transfer);
	controlPanel.add(back);
}
}


