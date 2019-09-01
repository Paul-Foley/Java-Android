
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientModel implements Serializable {
    
    private int patientNo;
    private String patientName,patientAdd,patientPhone;
    ArrayList<InvoiceModel> pInvoiceList;
    

    public PatientModel()
    {
    }
    
    public PatientModel(String patientName, String patientAdd,int patientNo)
    {
        setName(patientName);
        setAddress(patientAdd);
        setPatientNo(patientNo);
        pInvoiceList=new ArrayList<InvoiceModel>();
    }
    
    public void setName(String patientName)
    {
        this.patientName=patientName;
    }
    
    public void setAddress(String patientAdd)
    {
        this.patientAdd=patientAdd;
    }
    
    public void setPatientNo(int patientNo)
    {
        this.patientNo=patientNo;
    }
    
    public String getName()
    {
        return patientName;
    }
    
    public String getAddress()
    {
        return patientAdd;
    }
    
      public int getPatientNo()
    {
        return patientNo;
    }
    
    public void addInvoice(InvoiceModel i)
    {
        pInvoiceList.add(i);
    }
    
    public ArrayList<InvoiceModel> getInvoiceList()
    {
        return pInvoiceList;
    }
    
    
    public String toString()
    {
        return "Patient#: " + getPatientNo() + " Name: " + getName() + " Address: " + getAddress();
    }
    
    public void print()
    {
        System.out.println(toString());
    }

}
