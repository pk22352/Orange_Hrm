package orangehrm.library;

import org.openqa.selenium.By;

import utils.AppUtils;

public class LoginPage extends AppUtils 
{

	
	public void login(String uid,String pwd)
	{
		driver.findElement(By.id("txtUsername")).sendKeys(uid);
		driver.findElement(By.id("txtPassword")).sendKeys(pwd);
		driver.findElement(By.id("btnLogin")).click();	
	}
	
	public boolean isAdminModuleDisplayed()
	{
		
		try 
		{
			driver.findElement(By.linkText("Admin")).isDisplayed();
			return true;
		} catch (Exception e) 
		{
			return false;
		}
		
	}
	
	public boolean logout()
	{
		driver.findElement(By.partialLinkText("Welcome")).click();
		driver.findElement(By.linkText("Logout")).click();
		if(driver.findElement(By.id("btnLogin")).isDisplayed())
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean isErrMsgDisplayed()
	{
		String errmsg = driver.findElement(By.id("spanMessage")).getText().toLowerCase();
		if(errmsg.contains("invalid"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isEmpModuleDisplayed()
	{

		try 
		{
			driver.findElement(By.linkText("Welcome")).isDisplayed();
			return true;
		} catch (Exception e) 
		{
			return false;
		}
		
	}
	
}
