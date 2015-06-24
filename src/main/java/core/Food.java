package core;


public class Food {

    private Double calorie;
    private Double amount;
    private String unit;
    private Double protein;
    private Double carbon;
    private Double fat;
    private Double iduser;
    private String name;
    private int idfood;
    
    public Food(){}
    
    public Food(String name, Double calorie) {
        setName(name);
        setCalorie(calorie);
    }
    
    
    /**
     * @return the calorie
     */
    public Double getCalorie() {
        return calorie;
    }
    
    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }
    
    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }
    
    /**
     * @return the protein
     */
    public Double getProtein() {
        return protein;
    }
    
    /**
     * @return the carbon
     */
    public Double getCarbon() {
        return carbon;
    }
    
    /**
     * @return the fat
     */
    public Double getFat() {
        return fat;
    }
    
    /**
     * @return the iduser
     */
    public Double getIduser() {
        return iduser;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the idfood
     */
    public int getIdfood() {
        return idfood;
    }

    /**
     * @param calorie the calorie to set
     */
    public void setCalorie(Double calorie) {
        this.calorie = calorie;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @param protein the protein to set
     */
    public void setProtein(Double protein) {
        this.protein = protein;
    }

    /**
     * @param carbon the carbon to set
     */
    public void setCarbon(Double carbon) {
        this.carbon = carbon;
    }

    /**
     * @param fat the fat to set
     */
    public void setFat(Double fat) {
        this.fat = fat;
    }

    /**
     * @param iduser the iduser to set
     */
    public void setIduser(Double iduser) {
        this.iduser = iduser;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param idfood the idfood to set
     */
    public void setIdfood(int idfood) {
        this.idfood = idfood;
    }
    
}
