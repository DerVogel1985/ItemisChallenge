//@Frank Schmitteckert
package itemis_challenge;
import java.math.*;
import java.util.ArrayList;



public class Order_Double{
	
	private ArrayList<Order_Pos> order = new ArrayList<Order_Pos>();
	private double tax_amount;
	private double tax_total;
	private double cost_total;
	
	
	public void setTax_Amount(double tax_amount) {
		this.tax_amount = tax_amount;
	}
	
	public double getTax_Amount() {
		return tax_amount;
	}
	
	public void setTax_totalt(double tax_total) {
		this.tax_total = tax_total;
	}
	
	public double getTax_total() {
		return tax_total;
	}
	
	public void setCost_total(double cost_total) {
		this.cost_total = cost_total;
	}
	
	public double getCost_total() {
		return cost_total;
	}
	
	
	public void sales_tax() {
		
		String[]books = {"book", "books"};
		String[]food = {"chocolate bar", "box of chocolates"};
		String[]medical = {"packet of headache pills"};
		

		
		for(int i=0;i<order.size();i++) {
			int tax_rate=0;
			if(order.get(i).imported) {
				tax_rate+=5;
			}
			if(contains(books, order.get(i).category) || contains(food, order.get(i).category) || contains(medical, order.get(i).category)) {
				tax_rate += 0;
			}
			else {
				tax_rate += 10;
			}
			
			//tax_total +=  Math.round(((order.get(i).price/100) * tax_rate))/interval*interval;
			//tax_total += (((order.get(i).price/100) * tax_rate)/interval)*interval;
			System.out.println( Math.ceil(((order.get(i).price*tax_rate) / 100)*20.0)/20.0);
			tax_total += Math.round((Math.ceil(((order.get(i).price*tax_rate) / 100)*20.0)/20.0)*100.0)/100.0;
			//order.get(i).price = Math.round((order.get(i).price*(100 + tax_rate) / 100)/interval)*interval;
			//order.get(i).price = (order.get(i).price + Math.ceil(((order.get(i).price/100) * tax_rate)/interval)*interval);
			order.get(i).price = Math.round((order.get(i).price + Math.ceil(((order.get(i).price*tax_rate) / 100)*20.0)/20.0)*100.0)/100.0;
			cost_total += order.get(i).price;
		}
	}
	
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
	
	public void print_reciept() {
		for(int i =0; i<order.size();i++) {
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
	
	public void close_order() {
		sales_tax();
		print_reciept();
	}
	
	
	public static void main(String[] args) {

		Order_Double one = new Order_Double();
		one.order.add(one.new Order_Pos(1, "book", false, 12.49));
		one.order.add(one.new Order_Pos(1, "music CD", false, 14.99));
		one.order.add(one.new Order_Pos(1, "chocolate bar", false, 0.85));
		Order_Double two = new Order_Double();
		two.order.add(two.new Order_Pos(1, "box of chocolates", true, 10.00));
		two.order.add(two.new Order_Pos(1, "bottle of perfume", true, 47.50));
		Order_Double three = new Order_Double();
		three.order.add(three.new Order_Pos(1, "bottle of perfume", true, 27.99));
		three.order.add(three.new Order_Pos(1, "bottle of perfume", false, 18.99));
		three.order.add(three.new Order_Pos(1, "packet of headache pills", false, 9.75));
		three.order.add(three.new Order_Pos(1, "box of chocolates", true, 11.25));
		one.close_order();
		two.close_order();
		three.close_order();
		

}

 class Order_Pos{
 
	private int amount;
	private String category;
	private boolean imported;
	private double price;
	
	public Order_Pos(int amount, String category, boolean imported, double price) {
		this.amount = amount;
		this.category = category;
		this.imported = imported;
		this.price = price;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
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
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	}

}
