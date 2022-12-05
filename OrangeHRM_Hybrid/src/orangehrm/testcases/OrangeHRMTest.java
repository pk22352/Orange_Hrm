package orangehrm.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import orangehrm.library.Employee;
import orangehrm.library.LoginPage;
import orangehrm.library.User;
import utils.AppUtils;
import utils.XLUtils;

public class OrangeHRMTest extends AppUtils 
{

	String keywordfile = "C:\\Users\\DELL\\OneDrive\\Desktop\\Automaton\\OrangeHRM_Hybrid\\keywordfiles\\OrangeHRMKeywords.xlsx";
	String tcsheet = "TestCases";
	String tssheet = "TestSteps";
	
	@Test
	public void checkOrangeHRM() throws IOException, InterruptedException
	{
	
		int tccount = XLUtils.getRowCount(keywordfile, tcsheet);
		int tscount = XLUtils.getRowCount(keywordfile, tssheet);
		
		String tcexeflag,tcid,tstcid,keyword;
		String stepres,tcres;
		
		String adminuid,adminpwd;
		String empuid,emppwd;
		String fname,lname;
		String role,empname,uname,pword;
		
		boolean res = false;
		
		LoginPage lp = new LoginPage();
		Employee emp = new Employee();
		User us = new User();
		
		
		
		for(int i=1;i<=tccount;i++)
		{
			tcexeflag = XLUtils.getStringCellData(keywordfile, tcsheet, i, 2);
			if(tcexeflag.equalsIgnoreCase("y"))
			{
				tcid = XLUtils.getStringCellData(keywordfile, tcsheet, i, 0);
				for(int j=1;j<=tscount;j++)
				{
					tstcid = XLUtils.getStringCellData(keywordfile, tssheet, j, 0);
					if(tstcid.equalsIgnoreCase(tcid))
					{
						keyword = XLUtils.getStringCellData(keywordfile, tssheet, j, 4);
					
						switch (keyword.toLowerCase()) 
						{
						case "adminlogin":
							adminuid = XLUtils.getStringCellData(keywordfile, tssheet, j, 5);
							adminpwd = XLUtils.getStringCellData(keywordfile, tssheet, j, 6);
							lp.login(adminuid, adminpwd);
							res = lp.isAdminModuleDisplayed();
							break;
						case "logout":
							res = lp.logout();
							break;							
						case "newempreg":
							fname = XLUtils.getStringCellData(keywordfile, tssheet, j, 5);
							lname = XLUtils.getStringCellData(keywordfile, tssheet, j, 6);
							res = emp.addEmployee(fname, lname);
							break;
						case "newuserreg":
							role = XLUtils.getStringCellData(keywordfile, tssheet, j, 5);
							empname=XLUtils.getStringCellData(keywordfile, tssheet, j, 6);
							uname=XLUtils.getStringCellData(keywordfile, tssheet, j, 7);
							pword = XLUtils.getStringCellData(keywordfile, tssheet, j, 8);
							res = us.addUser(role, empname, uname, pword);
							break;
						case "emplogin":
							empuid = XLUtils.getStringCellData(keywordfile, tssheet, j, 5);
							emppwd = XLUtils.getStringCellData(keywordfile, tssheet, j, 6);
							lp.login(empuid, emppwd);
							res =lp.isEmpModuleDisplayed();
							break;
							
						case "invalidlogin":
							uname = XLUtils.getStringCellData(keywordfile, tssheet, j, 5);
							pword = XLUtils.getStringCellData(keywordfile, tssheet, j, 6);
							lp.login(uname, pword);
							res = lp.isErrMsgDisplayed();
							
							break;
						}
						
						// code to update Step Result
						if(res==true)
						{
							stepres = "Pass";
							XLUtils.setCellData(keywordfile, tssheet, j, 3, stepres);
							XLUtils.fillGreenColor(keywordfile, tssheet, j, 3);	
//							XLUtils.setCellData(keywordfile, tcsheet, i, 3, stepres);
						}
						else
						{
							stepres = "Fail";
							XLUtils.setCellData(keywordfile, tssheet, j, 3, stepres);
							XLUtils.fillRedColor(keywordfile, tssheet, j, 3);
//							XLUtils.setCellData(keywordfile, tcsheet, i, 3, stepres);
						}
						
//			 code to update TestCase Result
						tcres = XLUtils.getStringCellData(keywordfile, tcsheet, i, 3);
					if(!tcres.equalsIgnoreCase("fail"))
						{
							XLUtils.setCellData(keywordfile, tcsheet, i, 3, stepres);
					}
						
						// code to fill TCResult Colors
						tcres = XLUtils.getStringCellData(keywordfile, tcsheet, i, 3);
						if(tcres.equalsIgnoreCase("pass"))
						{
							XLUtils.fillGreenColor(keywordfile, tcsheet, i, 3);
						}else
						{
							XLUtils.fillRedColor(keywordfile, tcsheet, i, 3);
						}
					}
				}
				
			}
			else
			{
				XLUtils.setCellData(keywordfile, tcsheet, i, 3, "blocked");
				XLUtils.fillRedColor(keywordfile, tcsheet, i, 3);
			}
		}
		
		
		
	}
	
}
