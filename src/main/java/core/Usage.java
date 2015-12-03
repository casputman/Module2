package core;

import java.sql.Date;

public class Usage {

    private int idusage;
    private int iduser;
    private Activity activity;
    private double amount;
    private Date usageDate;
    
    /**
     * @return the idusage
     */
    public int getIdusage() {
        return idusage;
    }

    /**
     * @return the iduser
     */
    public int getIduser() {
        return iduser;
    }

    /**
     * @return the activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return the usageDate
     */
    public Date getUsageDate() {
        return usageDate;
    }

    /**
     * @param idusage the idusage to set
     */
    public void setIdusage(int idusage) {
        this.idusage = idusage;
    }

    /**
     * @param iduser the iduser to set
     */
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @param usageDate the usageDate to set
     */
    public void setUsageDate(Date usageDate) {
        this.usageDate = usageDate;
    }
}
