package com.fshows.commons.yq;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目：fs-fubei-shop
 * 包名：com.fshows.fubei.shop.common.fbshop
 * 功能：
 * 时间：2016-10-14
 * 作者：呱牛
 */
public class ClientDemo extends JFrame {

    private static final long serialVersionUID = -4171589383520209054L;

    private static final String SERVER_LOCAL = "127.0.0.1";
    private static final int SERVER_PORT = 7070;
    private int iPort = SERVER_PORT;

    private static final String FILE_ENCODING = "GBK";

    //顶部元数据
    private JPanel panServerIp;
    private JLabel labServerIp;
    private JTextField txtServerIp;

    private JPanel panPort;
    private JLabel labPort;
    private JTextField txtPort;

    private JCheckBox chkAutoHead;

    private Map<String,String> templateMap;
    private JComboBox comboTmpl;

    private JPanel panYqdm;
    private JLabel labYqdm;
    private JComboBox comboYqdm;

    private JPanel panTranCode;
    private JLabel labTranCode;
    private JTextField txtTranCode;

    private JCheckBox chkAttach;

    private JPanel panAttachName;
    private JLabel labAttachName;
    private JTextField txtAttachName;

    //中部 发送、接收报文
    private JPanel panSend;
    private JLabel labSend;
    private JPanel spSend;
    private JTextArea taSend;
    private JButton btnSend;

    private JPanel panReceive;
    private JLabel labReceive;
    private JPanel spReceive;
    private JTextArea taReceive;
    private JButton btnClear;

    private JPanel panButtons;

    //底部声明
    private JLabel labCopyrightCN;
    private JLabel labCopyrightEN;

    private static final String strFmt = "yyyyMMdd";
    private String fmtDate;

    public ClientDemo() {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateSDF = new SimpleDateFormat(strFmt);
        fmtDate = dateSDF.format(cal.getTime());

        Container con = this.getContentPane();
        con.setLayout(new FlowLayout());

        labServerIp = new JLabel("服务器IP");
        txtServerIp = new JTextField(8);
        txtServerIp.setText(SERVER_LOCAL);
        panServerIp = new JPanel();
        panServerIp.add(labServerIp);
        panServerIp.add(txtServerIp);
        con.add(panServerIp);

        labPort = new JLabel("端口");
        txtPort = new JTextField(4);
        txtPort.setText(String.valueOf(SERVER_PORT));

        panPort = new JPanel();
        panPort.add(labPort);
        panPort.add(txtPort);
        con.add(panPort);

        chkAutoHead = new JCheckBox("自动生成报文头");
        chkAutoHead.setSelected(true);
        con.add(chkAutoHead);

        comboTmpl = new JComboBox();
        templateMap = new HashMap<String,String>();
        initTemplates();
        con.add(comboTmpl);

        panYqdm = new JPanel();
        labYqdm = new JLabel("银企代码");
        comboYqdm = new JComboBox();
        comboYqdm.setEditable(true);
        comboYqdm.setSize(50, 0);

        initializeYqdm();
        panYqdm.add(labYqdm);
        panYqdm.add(comboYqdm);
        panYqdm.add(placeholder(20));
        con.add(panYqdm);

        labTranCode = new JLabel("交易码");
        txtTranCode = new JTextField(4);
        panTranCode = new JPanel();
        panTranCode.add(labTranCode);
        panTranCode.add(txtTranCode);
        panTranCode.add(placeholder(22));
        con.add(panTranCode);

        chkAttach = new JCheckBox("附件");
        chkAttach.setVisible(false);

        labAttachName = new JLabel("附件名称");
        txtAttachName = new JTextField(10);
        panAttachName = new JPanel();
        panAttachName.add(labAttachName);
        panAttachName.add(txtAttachName);

        //中间部分
        panSend = new JPanel();
        panSend.setLayout(new BorderLayout());

        labSend = new JLabel("发送报文：");
        taSend = new JTextArea(8, 50);
        taSend.setLineWrap(true);
        spSend = new JPanel();
        spSend.add(new JScrollPane(taSend));
        panSend.add(labSend, BorderLayout.NORTH);
        panSend.add(spSend, BorderLayout.CENTER);
        con.add(panSend);

        panReceive = new JPanel();
        panReceive.setLayout(new BorderLayout());

        labReceive = new JLabel("接收报文：");
        taReceive = new JTextArea(8, 50);
        taReceive.setLineWrap(true);
        spReceive = new JPanel();
        spReceive.add(new JScrollPane(taReceive));

        panReceive.add(labReceive, BorderLayout.NORTH);
        panReceive.add(spReceive, BorderLayout.CENTER);

        con.add(panReceive);

        panButtons = new JPanel();
        btnSend = new JButton("发送");
        btnClear = new JButton("清空结果");
        panButtons.add(btnSend);
        panButtons.add(btnClear);
        initilizeButtons();

        con.add(panButtons);

        //底部声明
        labCopyrightCN = new JLabel("版权所有 \u00A9 中国平安保险（集团）股份有限公司 未经许可不得复制、转载或摘编，违者必究!");
        labCopyrightEN = new JLabel("Copyright \u00A9 PING AN INSURANCE (GROUP) COMPANY OF CHINA ,LTD. All Rights Reserved");
        con.add(labCopyrightCN);
        con.add(labCopyrightEN);
    }

    //初始化模板
    private void initTemplates() {

        templateMap = new HashMap<String,String>();
        String fileName = "template.txt";
        BufferedReader freader = null;

        try {
            freader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), FILE_ENCODING));
            String strTemp;
            while ((strTemp = freader.readLine()) != null) {
                int keyIdx = strTemp.indexOf("=");
                if (keyIdx > 0) {
                    String tranCode = strTemp.substring(0, keyIdx);
                    String telegram = strTemp.substring(keyIdx + 1);

                    templateMap.put(tranCode, telegram.replace(strFmt, fmtDate));
                    comboTmpl.addItem(tranCode);
                }
            }

            comboTmpl.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String key = (String) comboTmpl.getSelectedItem();
                        taSend.setText(templateMap.get(key));
                        txtTranCode.setText(key.substring(0, 4));
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (freader != null) {
                    freader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //初始化银企代码
    private void initializeYqdm() {
        String fileName = "yqdm.txt";
        BufferedReader freader = null;

        try {
            freader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), FILE_ENCODING));
            String yqdm;
            while ((yqdm = freader.readLine()) != null) {
                comboYqdm.addItem(yqdm.trim());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (freader != null) {
                    freader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //初始化发送按钮点击事件
    private void initilizeButtons() {

        btnSend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                //首先清空接收报文
                taReceive.setText("");

                String errMsg = null;

                String serverIp = txtServerIp.getText().trim();
                String port = txtPort.getText().trim();

                if(serverIp.length() == 0 || port.length() == 0) {
                    errMsg = "服务器或端口不能为空！";
                }

                try{
                    iPort = Integer.parseInt(port);
                } catch(NumberFormatException nfe) {
                    errMsg = "端口必须为整数";
                }

                String txtSend = taSend.getText().trim();
                String packets = null;

                if(txtSend == "") {
                    errMsg = "发送报文不能为空！";
                    taSend.setFocusable(true);
                }

                boolean autoHead = chkAutoHead.isSelected();
                if(autoHead) {
                    String txtYqdm = ((JTextField)comboYqdm.getEditor().getEditorComponent()).getText();
                    String yqdm = txtYqdm;

                    if(yqdm == null || yqdm.length() < 20) {
                        errMsg = "银企代码不少于20位！";
                        comboYqdm.setFocusable(true);
                    }
                    if(txtYqdm.length() > 20) {
                        yqdm = txtYqdm.substring(0, 20);
                    }

                    String tranCode = txtTranCode.getText().trim();
                    if(tranCode == "") {
                        errMsg = "交易码不能为空！";
                        txtTranCode.setFocusable(true);
                    }

                    packets = YQUtil.asemblyPackets(yqdm, tranCode, "", txtSend);
                } else {
                    packets = txtSend;
                }

                if(errMsg != null) {
                    taReceive.setText(errMsg);
                    return;
                }

                try
                {
                    long stime = System.currentTimeMillis();
                    //Packets packetsRP = YQUtil.send2server(serverIp, iPort, packets); //socket
                    Packets packetsRP = YQUtil.send2httpServer(serverIp, iPort, packets); //http
                    byte[] headRP = packetsRP.getHead();
                    int bodyRpLen = packetsRP.getLen();
                    byte[] bodyRP = packetsRP.getBody();

                    StringBuilder rcvMsg = new StringBuilder();
                    if(headRP != null) {
                        rcvMsg.append(new String(headRP, YQUtil.CHARSET_DEFAULT));
                    }
                    if(bodyRpLen > 0 && bodyRP != null) {
                        rcvMsg.append(new String(bodyRP, YQUtil.CHARSET_DEFAULT));
                    }

                    taReceive.setText(rcvMsg.toString());
                    taReceive.setCaretPosition(0);

                    long etime = System.currentTimeMillis();
                    System.out.println((new StringBuilder("total time=")).append(etime - stime).toString());
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    taReceive.setText(ex.getMessage());
                }
            }

        });

        //结果清空按钮
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // TODO Auto-generated method stub
                //taSend.setText("");
                taReceive.setText("");
            }

        });
    }

    //保证布局demo好看一些
    private JLabel placeholder(int num) {
        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < num; i++) {
            buf.append(" ");
        }
        return new JLabel(buf.toString());
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ClientDemo clientDemo = new ClientDemo();
        clientDemo.setTitle("平安银行银企直连客户端DEMO V0.1");
        clientDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //netool.pack();
        clientDemo.setSize(600, 580);
        clientDemo.setLocationRelativeTo(null); //居中显示
        clientDemo.setVisible(true);
    }
}
