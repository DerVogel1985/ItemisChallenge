//@Frank Schmitteckert
package itemis_challenge;
import java.math.*;
import java.util.ArrayList;


//Class that handles Orders and provide methods to print a reciept, calculate tax, etc
public class Order_BigDecimal{
	
	private ArrayList<Order_Pos> order = new ArrayList<Order_Pos>();
	private BigDecimal tax_amount;
	private BigDecimal tax_total = new BigDecimal(0.0);
	private BigDecimal cost_total = new BigDecimal(0.0);
	
	//getter and setter
	public void setTax_Amount(BigDecimal tax_amount) {
		this.tax_amount = tax_amount;
	}
	
	public BigDecimal getTax_Amount() {
		return tax_amount;
	}
	
	public void setTax_totalt(BigDecimal tax_total) {
		this.tax_total = tax_total;
	}
	
	public BigDecimal getTax_total() {
		return tax_total;
	}
	
	public void setCost_total(BigDecimal cost_total) {
		this.cost_total = cost_total;
	}
	
	public BigDecimal getCost_total() {
		return cost_total;
	}
	
	//method that calculates tax and add it up to the product price and adds all tax of one order together for the reciept
	public void sales_tax() {
		
		//3 arrays standing for the 3 non tax product categories. They will be needed later when the tax per product and per order will be calculated. They easily can be expanded.
		String[]books = {"book", "books"};
		String[]food = {"chocolate bar", "box of chocolates"};
		String[]medical = {"packet of headache pills"};
		
		BigDecimal tax;
		BigDecimal tax_rounded;
		BigDecimal tax_interval = new BigDecimal(20.0);
		
		//for loop that runs as many times as Order_Pos are created for the Order
		for(int i=0;i<order.size();i++) {
			BigDecimal tax_rate = new BigDecimal(0.0);
			//checks if the order has an imported product. if so, 5% will be added to the tax_rate.
			if(order.get(i).imported) {
				tax_rate = tax_rate.add(new BigDecimal(5));
			}
			//checks if product belongs to one of the three tax-free categories. if so, no tax will be added. if not the tax rate will be raised by 10%
			if(contains(books, order.get(i).category) || contains(food, order.get(i).category) || contains(medical, order.get(i).category)) {
				tax_rate = tax_rate.add(new BigDecimal(0));
			}
			else {
				tax_rate = tax_rate.add(new BigDecimal(10));
			}
			
			//Tax per product gets calculated
			tax = (order.get(i).price.multiply(tax_rate).divide(new BigDecimal(100)));
			tax_rounded = tax;
			//cut decimal to 2 numbers after comma, then multiply with "20.0" round up and divide by "20.0" to get the 0.05 upwards steps
			tax_rounded = tax_rounded.setScale(2, RoundingMode.CEILING).multiply(tax_interval).round(new MathContext(3, RoundingMode.CEILING)).divide(tax_interval);
			//total tax is calculated by adding up the single product taxes
			tax_total = tax_total.add(tax_rounded);
			//new taxed price is saved back to variable
			order.get(i).price = order.get(i).price.setScale(2, RoundingMode.HALF_UP).add(tax_rounded);
			//adding up all new product costs to get the total price
			cost_total = cost_total.add(order.get(i).price);
		}
	}
	
	//method that checks if a given String is in an given array. if so it will return true.
	 public boolean contains(final String[] array, final String v) {

	        boolean result = false;

	        for(String i : array){
	            if(i.equals(v)){
	                result = true;
	                break;
	            }
	        }

	        return result;
	    }
	
	//method that prints the reciept in the format we like
	public void print_reciept() {
		for(int i =0; i<order.size();i++) {
			//check if an imported product is in the order. if so, it will be printed as "imported" on the reciept.
			if(order.get(i).imported){
				System.out.println(order.get(i).amount + " " + "imported " + order.get(i).category + " " + order.get(i).price);
			}
			else {
				System.out.println(order.get(i).amount + " " + order.get(i).category + " " + order.get(i).price);
			}
		}
		System.out.println("Sales Taxes: " + tax_total);
		System.out.println("Total: " + cost_total + "\n");
		
	}
	
	//simple method that closes an order and starts all other methods necessary. in this case first the sales_tax is calculated and the the reciept is printed 
	public void close_order() {
		sales_tax();
		print_reciept();
	}
	
	
	public static void main(String[] args) {
		
		//Here we create 3 different Orders and fill them up with different amount of Order_Pos. With the exact same information from the working sheet.
		Order_BigDecimal one = new Order_BigDecimal();
		one.order.add(one.new Order_Pos(new BigInteger("1"), "book", false, new BigDecimal(12.49)));
		one.order.add(one.new Order_Pos(new BigInteger("1"), "music CD", false, new BigDecimal(14.99)));
		one.order.add(one.new Order_Pos(new BigInteger("1"), "chocolate bar", false, new BigDecimal(0.85)));
		Order_BigDecimal two = new Order_BigDecimal();
		two.order.add(two.new Order_Pos(new BigInteger("1"), "box of chocolates", true, new BigDecimal(10.00)));
		two.order.add(two.new Order_Pos(new BigInteger("1"), "bottle of perfume", true, new BigDecimal(47.50)));
		Order_BigDecimal three = new Order_BigDecimal();
		three.order.add(three.new Order_Pos(new BigInteger("1"), "bottle of perfume", true, new BigDecimal(27.99)));
		three.order.add(three.new Order_Pos(new BigInteger("1"), "bottle of perfume", false, new BigDecimal(18.99)));
		three.order.add(three.new Order_Pos(new BigInteger("1"), "packet of headache pills", false, new BigDecimal(9.75)));
		three.order.add(three.new Order_Pos(new BigInteger("1"), "box of chocolates", true, new BigDecimal(11.25)));
		one.close_order();
		two.close_order();
		three.close_order();
		

}

//handels every position in an order object-oriented. 
 class Order_Pos{
 
	private BigInteger amount;
	private String category;
	private boolean imported;
	private BigDecimal price;
	
	//We offer only one constructor, that takes all 4 necessary parameter. 
	public Order_Pos(BigInteger amount, String category, boolean imported, BigDecimal price) {
		this.amount = amount;
		this.category = category;
		this.imported = imported;
		this.price = price;
	}
	
	//getter and setter 
	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}
	
	public BigInteger getAmount() {
		return amount;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setImported(boolean imported) {
		this.imported = imported;
	}
	
	public boolean getImported() {
		return imported;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	}

}
