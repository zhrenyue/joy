package com.kvc.joy.swing.datechooser;

import com.kvc.joy.commons.lang.string.I18nTool;
import com.kvc.joy.swing.datechooser.XDateChooser;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JSpinner;

/**
 * 日期时间组件
 * @author ckcs
 */
public class FromToDateTimeChooser extends javax.swing.JPanel {

    private int layoutStyle = BoxLayout.Y_AXIS;

    public XDateChooser getFromDateChooser() {
        return fromDateChooser;
    }

    public int getLayoutStyle() {
        return layoutStyle;
    }

    public void setLayoutStyle(int layoutStyle) {
        System.out.println(layoutStyle);
        if (this.layoutStyle != layoutStyle) {
            this.layoutStyle = layoutStyle;
            setLayout(new BoxLayout(this, layoutStyle));
            revalidate();
            repaint();
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        fromDateChooser.setEnabled(enabled);
        fromTimeChooser.setEnabled(enabled);
        toDateChooser.setEnabled(enabled);
        toTimeChooser.setEnabled(enabled);
    }

    public JSpinner getFromTimeChooser() {
        return fromTimeChooser;
    }

    public XDateChooser getToDateChooser() {
        return toDateChooser;
    }

    public JSpinner getToTimeChooser() {
        return toTimeChooser;
    }

    /** Creates new form FormToDateTimeChooser */
    public FromToDateTimeChooser() {
        initComponents();
    }

    /**
     * 初始日期时间选择器
     */
    public void initDateTimeChooser() {
        initDateTimeChooser(new Date(), new Date());
    }

    /**
     * 初始话日期时间选择器
     * @param fromDate
     * @param toDate
     */
    public void initDateTimeChooser(Date fromDate, Date toDate) {
        fromDateChooser.setDate(fromDate);
        if (fromDate == null) {
            fromTimeChooser.setValue(new Date(0));
        }
        toDateChooser.setDate(toDate);
        if (toDate == null) {
            toTimeChooser.setValue(new Date(0));
        }
    }

    /**
     * 清空日期时间选择器
     */
    public void clearDateTimeChooser() {
        initDateTimeChooser(null, null);
    }

    /**
     * 获得起始得时间
     * @return
     */
    public Date getFromDateTime() {
        return getDateComp(fromDateChooser.getDate(), (Date) fromTimeChooser.getValue());
    }

    /**
     * 获得结束得时间
     * @return
     */
    public Date getToDateTime() {
        return getDateComp(toDateChooser.getDate(), (Date) toTimeChooser.getValue());
    }

    /**
     * 是否开始时间为null
     * @return
     */
    public boolean isFromDateTimeNull() {
        return (fromDateChooser.getDate() == null || fromTimeChooser.getValue() == null);
    }

    /**
     * 是否结束时间为null
     * @return
     */
    public boolean isToDateTimeNull() {
        return (toDateChooser.getDate() == null || toTimeChooser.getValue() == null);
    }

    public void addFromDateChangeListener(PropertyChangeListener listner) {
        if (listner != null) {
            fromDateChooser.addPropertyChangeListener(listner);
        }
    }

    public void addToDateChangeListener(PropertyChangeListener listner) {
        if (listner != null) {
            toDateChooser.addPropertyChangeListener(listner);
        }
    }

    /**
     * 使用前个的日期, 后个的时间
     * @param useDate 使用日期
     * @param useTime 使用时间
     * @return
     */
    public Date getDateComp(Date useDate, Date useTime) {
        if (useDate == null || useTime == null) {
            return null;
        }
        //日期部分
        Calendar first = Calendar.getInstance();
        first.setTime(useDate);
        //时间部分
        Calendar second = Calendar.getInstance();
        second.setTime(useTime);
        first.set(Calendar.HOUR_OF_DAY, second.get(Calendar.HOUR_OF_DAY));
        first.set(Calendar.MINUTE, second.get(Calendar.MINUTE));
        first.set(Calendar.SECOND, second.get(Calendar.SECOND));
        return first.getTime();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        fromDateChooser = new XDateChooser();
        fromTimeChooser = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        toDateChooser = new XDateChooser();
        toTimeChooser = new javax.swing.JSpinner();

        setOpaque(false);
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));
        jPanel1.add(fromDateChooser);

        fromTimeChooser.setModel(new javax.swing.SpinnerDateModel());
        fromTimeChooser.setEditor(new javax.swing.JSpinner.DateEditor(fromTimeChooser, "HH:mm:ss"));
        jPanel1.add(fromTimeChooser);

        add(jPanel1);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText(I18nTool.getLocalStr("To"));
        jPanel2.add(jLabel1);
        jPanel2.add(toDateChooser);

        toTimeChooser.setModel(new javax.swing.SpinnerDateModel());
        toTimeChooser.setEditor(new javax.swing.JSpinner.DateEditor(toTimeChooser, "HH:mm:ss"));
        jPanel2.add(toTimeChooser);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private XDateChooser fromDateChooser;
    private javax.swing.JSpinner fromTimeChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private XDateChooser toDateChooser;
    private javax.swing.JSpinner toTimeChooser;
    // End of variables declaration//GEN-END:variables
}
