import java.io.Serializable;

public class ProcedureModel implements Serializable
{
    
    private static int procedureNo=0;
    private int procNo;
    private String procedureName;
    private double procedureCost;
    
    public ProcedureModel()
    {
    }
    
    public ProcedureModel(String procedureName, double procedureCost)
    {
        setProcedureName(procedureName);
        setProcedureCost(procedureCost);
        procNo=procedureNo+1;
        setProcedureNo(procNo);
        procedureNo++;
    }
    
    public void setProcedureName(String procedureName)
    {
        this.procedureName=procedureName;
    }
   
    public void setProcedureCost(double procedureCost)
    {
        this.procedureCost=procedureCost;
    }
    
    public void setProcedureNo(int procNo)
    {
        this.procNo=procNo;
    }
    
    public String getProcedureName()
    {
        return procedureName;
    }
    
    public double getProcedureCost()
    {
        return procedureCost;
    }
    
    public int getProcedureNo()
    {
        return procNo;
    }
    
    public String toAString()
    {
        return " Procedure Name: " + getProcedureName() + " Cost £ : " + getProcedureCost();
    }
    
    public String toString()
    {
        return   getProcedureName();
    }
    
    public void print()
    {
        System.out.println(toString());
    }
    
}