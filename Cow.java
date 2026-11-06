class Cow implements FarmItem {
    double price;
    String name;
    
    public Cow(double price, String name) {
        this.price = price;
        this.name = name;
    }

    void display(double price, String name){
        this.price = price;
        this.name = name;
        System.out.println("    "+name + " $" + price);
    }
    
    @Override
    public void add(FarmItem item) {
        throw new UnsupportedOperationException("Cannot add an Item to a Cow");
    }
    
    @Override
    public void remove(FarmItem item) {
        throw new UnsupportedOperationException("Cannot remove an Item from a Cow");
    }
    

    @Override
    public double getPrice() {
        return this.price;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

} 
