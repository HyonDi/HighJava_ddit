package vo;

public class ProdVO {
	private String lprod_gu;
	private String lprod_nm;
	private String prod_id;
	private String prod_name;
	private String prod_lgu;
	private String prod_buyer;
	private int prod_cost;
	private int prod_price;
	private int prod_sale;
	private String outline;
	private String detail;
	
	
	public ProdVO() {
		super();
	}

	public ProdVO(String lprod_gu, String lprod_nm, String prod_id, String prod_name, String prod_lgu,
			String prod_buyer, int prod_cost, int prod_price, int prod_sale, String outline, String detail) {
		this.lprod_gu = lprod_gu;
		this.lprod_nm = lprod_nm;
		this.prod_id = prod_id;
		this.prod_name = prod_name;
		this.prod_lgu = prod_lgu;
		this.prod_buyer = prod_buyer;
		this.prod_cost = prod_cost;
		this.prod_price = prod_price;
		this.prod_sale = prod_sale;
		this.outline = outline;
		this.detail = detail;
	}

	public String getLprod_gu() {
		return lprod_gu;
	}

	public void setLprod_gu(String lprod_gu) {
		this.lprod_gu = lprod_gu;
	}

	public String getLprod_nm() {
		return lprod_nm;
	}

	public void setLprod_nm(String lprod_nm) {
		this.lprod_nm = lprod_nm;
	}

	public String getProd_id() {
		return prod_id;
	}

	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getProd_lgu() {
		return prod_lgu;
	}

	public void setProd_lgu(String prod_lgu) {
		this.prod_lgu = prod_lgu;
	}

	public String getProd_buyer() {
		return prod_buyer;
	}

	public void setProd_buyer(String prod_buyer) {
		this.prod_buyer = prod_buyer;
	}

	public int getProd_cost() {
		return prod_cost;
	}

	public void setProd_cost(int prod_cost) {
		this.prod_cost = prod_cost;
	}

	public int getProd_price() {
		return prod_price;
	}

	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}

	public int getProd_sale() {
		return prod_sale;
	}

	public void setProd_sale(int prod_sale) {
		this.prod_sale = prod_sale;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
	
	

}
