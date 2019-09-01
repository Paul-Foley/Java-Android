import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;
public class InvoiceModel implements Serializable
{
    private ArrayList<ProcedureModel> pProcList;
    private ArrayList<PaymentModel> pPaymentList;
    private int invoiceNo=0;
    private double invoiceAmount=0.00;
    private static int invoiceNumber=0;
    Date rDate = new Date();
    private boolean isPaid;
    String sDate;
    
    
    public InvoiceModel(String sDate)
    {
        pProcList= new ArrayList<ProcedureModel>();
        pPaymentList=new ArrayList<PaymentModel>();
        invoiceNo=invoiceNumber+1;
        invoiceNumber++;
        setDate(sDate);
    }
    
    public void setDate(String sDate)
    {
        this.sDate=sDate;
    }
    
     public String getDate()
    {
        return sDate;
    }
    
    public void addProcedure(ProcedureModel proc)
    {
        pProcList.add(proc);
    }
    
    public void addPayment(PaymentModel pay)
    {
        pPaymentList.add(pay);
    }
    
    public int getInvoiceNumber()
    {
        return invoiceNo; 
    }
    
    public ArrayList<ProcedureModel> getProcedureList()
    {
        return pProcList;
    }
    
    public ArrayList<PaymentModel> getPaymentList()
    {
        return pPaymentList;
    }
     
    public String getAllProcedure() //returns string of all procedures
    {
        String proc = "";
        for(int i =0; i<pProcList.size(); i++)
        {
            proc =  proc+ pProcList.get(i).toString() +" ";
        }
        return proc;
    }
    
    public String getAllPayment()//return String of all information about payments
    {
        String payment="";
      
        for(int i =0; i<pPaymentList.size();i++)
        {
            payment=payment + pPaymentList.get(i).toString()+" ";
    
        }
        
        return payment;
    }
    
     public double getTotalAmountOwed() //gets the amount owed as double
    {
        double owed=0.00;
    
        System.out.println(pProcList.size());
        for(int i =0; i<pProcList.size();i++)
        {
            owed=owed + pProcList.get(i).getProcedureCost();
    
        }
        
        return owed;
    }
    
    public double getTotalPaid()
    {
        double paid=0.00;
    
        for(int i =0; i<pPaymentList.size();i++)
        {
            paid=paid + pPaymentList.get(i).getPaymentAmount();
    
        }
        
        return paid;
        
    }
    
    public double getBalance()
    {
    	double balance=0;
        double paid=0.00;
        double owed=0.00;
        
        for(int i =0; i<pPaymentList.size();i++)
        {
            paid=paid + pPaymentList.get(i).getPaymentAmount();
    
        }
        
        for(int i =0; i<pProcList.size();i++)
        {
            owed=owed + pProcList.get(i).getProcedureCost();
    
        }
        
        balance = owed-paid;
        return balance;
        
    }
     
    public boolean isPaid()
    {
    	double paid=0;
    	double cost=0;
        boolean valid=false;
        
           for(int i=0;i<pPaymentList.size();i++)
            { 
                paid=paid+pPaymentList.get(i).getPaymentAmount();     
           }
           for(int x=0;x<pProcList.size();x++)
           { 
           cost=cost+pProcList.get(x).getProcedureCost();
           }
           if (paid==cost)
           {
               valid=true;
           }
        return valid;
    }
    
    public String toString()
    {
        return "Amount Owed:£" +  getTotalAmountOwed() + "\n Total Paid:£ " + getTotalPaid();
    }
    
    public void print()
    {
        System.out.println(toString());
    }
}
