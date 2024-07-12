

package customerpanel;

public class customerpanelbean 
{
	public String cname;
	String spapers;
	String mobile;
	String area;
	
	public customerpanelbean () {}
	public customerpanelbean  (String cname,String spapers)
	{
		super();
		this.cname=cname;
		this.spapers=spapers;
	}
	public customerpanelbean(String cname,String mobile,String area)
	{
		super();
		this.cname=cname;
		this.mobile=mobile;
		this.area=area;
	}	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getSpapers() {
		return spapers;
	}
	public void setSpapers(String spapers) {
		this.spapers = spapers;
	}
}