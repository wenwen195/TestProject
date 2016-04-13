    package getNextTimeStamp;  
      
    import java.awt.*;  
    import java.awt.event.ItemEvent;  
    import java.awt.event.ItemListener;  
    import java.awt.event.MouseAdapter;  
    import java.awt.event.MouseEvent;  
      
    import javax.swing.*;  
    import java.util.*;  
      
    public class DateUI extends MouseAdapter {  
      
        JDialog jf = new JDialog();  
      
        JPanel jp = new JPanel();  
      
        JComboBox yearBox = new JComboBox();  
      
        JComboBox monthBox = new JComboBox();  
      
        JButton[][] buttons = new JButton[7][7];  
      
        String[] weeks = { "��", "һ", "��", "��", "��", "��", "��" };  
      
        Calendar cd = Calendar.getInstance();  
      
        int curX = -1;  
      
        int curY = -1;  
      
        JTextField dateText;  
        
        NextTimeStamp timeToday;
      
        public DateUI(JTextField dateText) {  
        	//����Ķ�
        	timeToday=new NextTimeStamp();
        	
        	//����Ķ�
              
            this.dateText = dateText;  
            jf.setLocationRelativeTo(dateText);  
            jf.getContentPane().add(jp);  
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
            jp.setBorder(BorderFactory.createTitledBorder("����"));  
            jp.setLayout(new BorderLayout());  
            JPanel northPanel = new JPanel(new GridLayout(1, 0,0,0));  
            for (int i = 2000; i < 2100; i++) {  
                yearBox.addItem(i + "��");  
            }  
            yearBox.setSelectedItem("2009��");  
            for (int i = 1; i <= 12; i++) {  
                monthBox.addItem(i + "��");  
            }  
            northPanel.add(yearBox);  
            northPanel.add(monthBox);  
            JPanel centerPanel = new JPanel();  
            centerPanel.setLayout(new GridLayout(7, 7));  
            for (int i = 0; i < 7; i++) {  
                for (int j = 0; j < 7; j++) {  
                    if (i == 0) {  
                        buttons[i][j] = new JButton(weeks[j]);  
                        buttons[i][j].setEnabled(false);  
                        buttons[i][j].setBackground(Color.green);  
                    } else {  
                        buttons[i][j] = new JButton("");  
                        buttons[i][j].setBackground(Color.white);  
                        buttons[i][j].addMouseListener(this);  
                    }  
                    if (j == 0) {  
                        buttons[i][j].setForeground(Color.red);  
                    }  
                    centerPanel.add(buttons[i][j]);  
                }  
            }  
            setDate();  
            yearBox.addItemListener(new ItemListener() {  
      
                public void itemStateChanged(ItemEvent e) {  
                    if (e.getStateChange() == 2) {  
                        return;  
                    }  
                    String yy = e.getItem().toString();  
                    int yi = Integer.parseInt(yy.substring(0, yy.length() - 1));  
                    cd.set(Calendar.YEAR, yi);  
                    setDate();  
                }  
      
            });  
            monthBox.addItemListener(new ItemListener() {  
                public void itemStateChanged(ItemEvent e) {  
                    if (e.getStateChange() == 2) {  
                        return;  
                    }  
                    String yy = e.getItem().toString();  
                    int yi = Integer.parseInt(yy.substring(0, yy.length() - 1));  
                    cd.set(Calendar.MONTH, yi - 1);  
                    setDate();  
                }  
      
            });  
            jp.add(northPanel, BorderLayout.NORTH);  
            jp.add(centerPanel, BorderLayout.CENTER);  
            jf.setSize(370,220);  
            jf.setVisible(true);  
        }  
      
        public void mousePressed(MouseEvent e) {  
      
            if (e.getClickCount() == 1) {  
                buttons[curX][curY].setBackground(Color.white);  
                for (int i = 1; i < 7; i++) {  
                    for (int j = 0; j < 7; j++) {  
                        if (buttons[i][j].getText().equals("")) {  
                            continue;  
                        }  
                        if (buttons[i][j].equals(e.getSource())) {  
                            curX = i;  
                            curY = j;  
                            buttons[curX][curY].setBackground(Color.MAGENTA);  
                        }  
                    }  
                }  
                 
            } else {  
                String ds=yearBox.getSelectedItem().toString()  
                + monthBox.getSelectedItem().toString()  
                + buttons[curX][curY].getText();  
                ds=ds.replaceAll("��|��", "-");  
                dateText.setText(ds);  
                jf.dispose();  
               // JOptionPane.showMessageDialog(null,ds);
                String year=yearBox.getSelectedItem().toString();
                String getYear=year.substring(0,year.length()-1);
                String month=monthBox.getSelectedItem().toString();
                String getMonth=month.substring(0,month.length()-1);
                String day=buttons[curX][curY].getText();
               // System.out.println(getYear+";"+getMonth+";"+day+";");
                //����NextTime�ĵ���
                String[] arr= {getYear,getMonth,day};
                //��ȡ���
                String[] nextDay=timeToday.inputNextTime(arr);
                 JOptionPane.showMessageDialog(null,nextDay[0]+","+nextDay[1]+","+nextDay[2]);
            }  
            //JOptionPane.showMessageDialog(null,"��ʾ��Ϣ");
        }  
      
        public void setDate() {  
            for (int i = 1; i < 7; i++) {  
                for (int j = 0; j < 7; j++) {  
                    buttons[i][j].setText("");  
                    buttons[i][j].setBackground(Color.white);  
                    buttons[i][j].setEnabled(false);  
                }  
            }  
            int year = cd.get(Calendar.YEAR);  
            int month = cd.get(Calendar.MONTH) + 1;  
            int day = cd.get(Calendar.DAY_OF_MONTH);  
            curX = cd.get(Calendar.WEEK_OF_MONTH);  
            curY = cd.get(Calendar.DAY_OF_WEEK) - 1;  
            buttons[curX][curY].setBackground(Color.MAGENTA);  
            yearBox.setSelectedItem(year + "��");  
            monthBox.setSelectedItem(month + "��");  
            cd.set(Calendar.DAY_OF_MONTH, 1);  
            int week = cd.get(Calendar.DAY_OF_WEEK);  
            int maxDay = cd.getActualMaximum(Calendar.DAY_OF_MONTH);  
            int k = 0;  
            int dm = 1;  
            for (int i = 1; i < 7; i++) {  
                for (int j = 0; j < 7; j++) {  
                    k++;  
                    if (k >= week && k < maxDay + week) {  
                        buttons[i][j].setText(dm++ + "");  
                        buttons[i][j].setEnabled(true);  
                    }  
      
                }  
            }  
            cd.set(Calendar.DAY_OF_MONTH, day);  
        }  
    }  