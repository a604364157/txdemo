package com.jjx.demoa.config;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class MainFrame extends JFrame {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel panel = new JPanel(new BorderLayout());
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel(new GridLayout(7, 7));
    JPanel panel3 = new JPanel();
    JLabel[] label = new JLabel[49];
    JLabel y_label = new JLabel("年份");
    JLabel m_label = new JLabel("月份");
    JComboBox com1 = new JComboBox();
    JComboBox com2 = new JComboBox();

    JButton but1 = new JButton("上个月");
    JButton but2 = new JButton("下个月");

    int re_year, re_month;
    int x_size, y_size;
    String year_num;
    Calendar now = Calendar.getInstance(); // 实例化Calendar

    MainFrame() {
        super("万年历");
        setSize(600, 700);
        x_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        y_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        setLocation((x_size - 300) / 2, (y_size - 350) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel1.add(but1);

        panel1.add(y_label);
        panel1.add(com1);
        panel1.add(m_label);
        panel1.add(com2);

        panel1.add(but2);

        for (int i = 0; i < 49; i++) {
            label[i] = new JLabel("", JLabel.CENTER);// 将显示的字符设置为居中
            panel2.add(label[i]);
        }
        panel3.add(new Clock(this));
        panel.add(panel1, BorderLayout.NORTH);
        panel.add(panel2, BorderLayout.CENTER);
        panel.add(panel3, BorderLayout.SOUTH);
        panel.setBackground(Color.white);
        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        panel3.setBackground(Color.gray);
        Init();
        but1.addActionListener(new AnAction());
        but2.addActionListener(new AnAction());
        com1.addActionListener(new ClockAction());
        com2.addActionListener(new ClockAction());


        setContentPane(panel);
        setVisible(true);
        setResizable(false);
    }


    class AnAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int c_year, c_month, c_week;

            c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // 得到当前月份，并减1,计算机中的月为0－11
            c_year = Integer.parseInt(com1.getSelectedItem().toString()) - 1; // 得到当前所选年份
            if (e.getSource() == but1) {
                if (c_month == 0) {
                    c_year = c_year - 1;
                    c_month = 11;
                } else {
                    c_month = c_month - 1;
                }

            }
            if (e.getSource() == but2) {
                if (c_month == 11) {
                    c_year = c_year + 1;
                    c_month = 0;
                } else {
                    c_month = c_month + 1;
                }
            }
            com1.setSelectedIndex(c_year);
            com2.setSelectedIndex(c_month);
            c_year = Integer.parseInt(com1.getSelectedItem().toString()); // 得到当前所选年份
            c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // 得到当前月份，并减1,计算机中的月为0－11
            c_week = use(c_year, c_month); // 调用函数use，得到星期几
            resetDay(c_week, c_year, c_month); // 调用函数Resetday

        }

    }

   /* class NextAction implements ActionListener
    {
   	 public void actionPerformed(ActionEvent arg0)
   	 {
   		 int c_year, c_month,c_week;

            c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // 得到当前月份，并减1,计算机中的月为0－11
            c_year = Integer.parseInt(com1.getSelectedItem().toString()); // 得到当前所选年份
            if(c_month==11)
            {
           	 c_year=c_year+1;
           	 c_month=0;
            }
            c_week = use(c_year, c_month); // 调用函数use，得到星期几
            Resetday(c_week, c_year, c_month); // 调用函数Resetday

   	 }

    }

     */

    class ClockAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            int c_year, c_month, c_week;
            c_year = Integer.parseInt(com1.getSelectedItem().toString()); // 得到当前所选年份
            c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // 得到当前月份，并减1,计算机中的月为0－11
            c_week = use(c_year, c_month); // 调用函数use，得到星期几
            resetDay(c_week, c_year, c_month); // 调用函数Resetday
        }
    }

    public void Init() {
        int year, month_num, first_day_num;
        String log[] = {"日", "一", "二", "三", "四", "五", "六"};
        for (int i = 0; i < 7; i++) {
            label[i].setText(log[i]);
        }
        for (int i = 0; i < 49; i = i + 7) {
            // 将星期日的日期设置为红色
            label[i].setForeground(Color.blue);
        }
        for (int i = 6; i < 49; i = i + 7) {
            // 将星期六的日期设置为hong色
            label[i].setForeground(Color.blue);
        }
        for (int i = 1; i < 10000; i++) {
            com1.addItem("" + i);
        }
        for (int i = 1; i < 13; i++) {
            com2.addItem("" + i);
        }
        // 得到当前时间的月份
        month_num = (int) (now.get(Calendar.MONTH));
        // 得到当前时间的年份
        year = (int) (now.get(Calendar.YEAR));
        // 设置下拉列表显示为当前年???????????
        com1.setSelectedIndex(year - 1);
        // 设置下拉列表显示为当前月
        com2.setSelectedIndex(month_num);
        first_day_num = use(year, month_num);
        resetDay(first_day_num, year, month_num);
    }

    public int use(int reyear, int remonth) {
        int weekNum;
        // 设置时间为所要查询的年月的第一天
        now.set(reyear, remonth, 1);
        // 得到第一天的星期
        weekNum = (int) (now.get(Calendar.DAY_OF_WEEK));
        return weekNum;
    }

    public void resetDay(int weekLog, int yearLog, int monthLog) {
        // 存储月份的天数
        int monthDayScore;
        int count;
        Lunar lunar;
        int monthDay;
        String[] lunarDate = new String[49];
        monthDayScore = 0;
        count = 1;
        for (int i = 1; i < 49; i++) {
            for (int j = 0; j < 49; j = j + 7) {
                if (i != j && i != j + 6) {
                    label[i].setForeground(Color.black);
                }
            }
        }
        // now MONTH是从0开始的, 对于一月第几天来说，DAY_OF_MONTH第一天就是1. 对于一年第几个月来说，MONTH一月份是0，二月份是1...
        Date date = new Date(yearLog, monthLog + 1, 1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 前个月
        cal.add(Calendar.MONTH, -1);
        // 最后一天
        monthDayScore = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthDay = monthDayScore;
        // 初始化标签
        for (int i = 7; i < 49; i++) {
            label[i].setText("");
        }
        // 将星期数加6，使显示正确
        weekLog = weekLog + 6;
        monthDayScore = monthDayScore + weekLog;
        lunar = new Lunar();
        for (int i = 0; i < monthDay; i++) {
            lunarDate[i] = lunar.getLunarDate(yearLog, monthLog + 1, i + 1);
        }
        for (int i = weekLog; i < monthDayScore; i++, count++) {
            if (monthLog == 9 && count == 1) {
                label[i].setText(count + "国庆");
                label[i].setForeground(Color.red);
            } else if (monthLog == 0 && count == 1) {
                label[i].setText(count + "元旦");
                label[i].setForeground(Color.red);
            } else if (monthLog == 11 && count == 24) {
                label[i].setText(count + "平安夜");
                label[i].setForeground(Color.red);
            } else if (monthLog == 11 && count == 25) {
                label[i].setText(count + "圣诞");
                label[i].setForeground(Color.red);
            } else if (monthLog == 1 && count == 14) {
                label[i].setText(count + "情人节");
                label[i].setForeground(Color.red);
            } else if (monthLog == 4 && count == 1) {
                label[i].setText(count + "劳动节");
                label[i].setForeground(Color.red);
            } else if ("春节".equals(lunarDate[i - weekLog]) || "元宵".equals(lunarDate[i - weekLog]) || "端午".equals(lunarDate[i - weekLog]) || "中秋".equals(lunarDate[i - weekLog])) {
                label[i].setText(count + lunarDate[i - weekLog]);
                label[i].setForeground(Color.red);
            } else {
                label[i].setText(count + lunarDate[i - weekLog]);
            }
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new MainFrame();
    }

}
