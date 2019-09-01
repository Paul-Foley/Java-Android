import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PaymentModel implements Serializable{
    
    private double paymentAmount;
    
    private static int PaymentNo=0;
    private int payNo;
    private String sDate;
    
    public PaymentModel()
    {
        
    }
    
    public PaymentModel(String sDate,double paymentAmount)
    {
        payNo=PaymentNo+1;
        PaymentNo++;
        //SimpleDateFormat format = new SimpleDateFormat( "MMM dd yyyy");
        //sDate=format.format(date);
        setPaymentAmount(paymentAmount);
        setDate(sDate);
    }
    
    
    
    public void setPaymentAmount(double paymentAmount)
    {
        this.paymentAmount=paymentAmount;
    }
    
      public void setDate(String sDate)
    {
        this.sDate=sDate;
    }
     
     public String getDate()
    {
        return sDate;
    }
    
    public int getPaymentNo()
    {
        return payNo;
    }
    
    public double getPaymentAmount()
    {
        return paymentAmount;
    }
    
    public String toString()
    {
        return "\nDate: " + getDate()+  " Amount Paid:£ "+ getPaymentAmount();
    }
    
}