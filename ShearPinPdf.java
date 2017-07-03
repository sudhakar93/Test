import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.delta.dcc.webportal.beans.ShearPinBean;
import com.delta.dcc.webportal.beans.ShearPinEmailBean;
import com.delta.dcc.webportal.exceptions.DccException;
import com.delta.dcc.webportal.utils.Messages;
import com.delta.dcc.webportal.utils.SendMail;
import com.delta.dcc.webportal.utils.Validation;

/*import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bean.ShearPinQuestBean;*/

public class ShearPinPdf {

/*	public ActionForward viewBlankAdhocFlights( final ActionMapping inMapping,
            final ActionForm inForm,
            final HttpServletRequest inRequest,
            final HttpServletResponse inResponse )
{
List<ShearPinQuestBean> shearPinQuest = new ArrayList<ShearPinQuestBean>();

	String name =inRequest.getParameter("employeeName");
	System.out.println(name);

return null; 


}*//*
	private long shearPinId = 0;
	
	private String station = "MSP";
	private String aircraftNumber = "7645";
	private String flightNumber = "7410";
	private String towbarType = "Hydro";
	private String localDateTime = "24/12/2016";
	private String gateNumber = "C5";
	private String tugAssetNumber = "A345";
	private String towbarAssetNumber = "af45456";
	private String weather = "35f";
	private String employeeName = "Sudhakar";
	private String employeeNumber = "587917";
	private String seniority = "Senior";
	private String latestTrainingDate = "20/11/2016";
	private String pushbackFrequency = "weekly";
	
	private String shearPinAns1 = "a";
	private String shearPinAns2 = "b";
	private String shearPinAns3 = "c";
	private String shearPinAns4 = "d";
	private String shearPinAns5 = "e";
	private String shearPinAns6 = "f";
	private String shearPinAns7 = "g";
	private String shearPinAns8 = "h";
	private String shearPinAns9 = "i";
	private String shearPinAns10 = "j";
	
	public static void main(String[] args) {
		
		Timestamp ts = Timestamp.valueOf("yyyy-MM-dd HH:mm:ss");
		String text = "2011-10-02 18:48:05";
		ts = Timestamp.valueOf(text);
		System.out.println(ts);
		
		
		String text = "2011-10-02 18:48:05.123456";
        Timestamp ts = Timestamp.valueOf(text);
        System.out.println(ts);
	
		

	}
*/
	private static final Logger LOGGER   = Logger.getLogger( ShearPinPdf.class );

    private String testWarning = "";

    private ShearPinPdf()
    {
        String deploymentLevel = System.getProperty( "APP_DEPLOYMENT_LEVEL" );
        if ( "DVL".equals( deploymentLevel ) || "INT".equals( deploymentLevel ) )
        {
            this.testWarning = "Test Mail in " + deploymentLevel
                    + ", please ignore-";
        }
    }
	
	public void main() 
    {
    	StringBuffer contentbuff = new StringBuffer();
        Properties props = new Properties();
        InternetAddress[] msgAddrTo = null;
        String subject = shearPinBean.getSubject();
        if ( "YES".equals( System.getProperty( "OFFSHORE" ) ) )
        {
        	props.put( "mail.smtp.host", Messages.getString( "SMTP_HOST_ONSITE" ) );
        }
        else
        {
        	props.put( "mail.smtp.host", Messages.getString( "SMTP_HOST_ONSITE" ) );
        }
        Session session = Session.getDefaultInstance( props, null );
        session.setDebug( false );
        this.checkConnection( session );
        LOGGER.info( "Method Entrance SendMail/sendShearPinFormMail() ..." );
        try
        {
        	String deploymentLevel = System.getProperty( "APP_DEPLOYMENT_LEVEL" );
        	//String deploymentLevel = "PRD";
            Message msg = new MimeMessage( session );
            String emailIdAll =null;
            String senderMail = "dcpadmin@delta.com";
            List<ShearPinEmailBean> emailList = shearPinBean.getEmailList();
            for(ShearPinEmailBean sbean : emailList )
            {
            	if(deploymentLevel.equalsIgnoreCase("PRD"))
            	{
            		if(sbean.getShearEmailCode().equalsIgnoreCase(shearPinBean.getEmailPrd()))
            		{
            			emailIdAll=sbean.getShearEmailVal();
            		}
            		if(sbean.getShearEmailCode().equalsIgnoreCase("PRD_SHEAR_EMAIL_ADDR_TO"))
            		{
            			emailIdAll=emailIdAll.concat("," +sbean.getShearEmailVal());
            		}
            	}
            	else if(sbean.getShearEmailCode().equalsIgnoreCase(shearPinBean.getEmailInt()))
            	{
            		emailIdAll=sbean.getShearEmailVal();
            	}
            }
            InternetAddress addressFrom = new InternetAddress( senderMail );
            String[] emailIdPrd = emailIdAll.split( "," );
            msgAddrTo = new InternetAddress[emailIdPrd.length];
            for ( int i = 0; i < emailIdPrd.length; i++ )
            {
                msgAddrTo[i] = new InternetAddress( emailIdPrd[i] );
                LOGGER.info( "msgAddrTo[" + i + "]:" + msgAddrTo[i] );
            }
            msg.setRecipients( Message.RecipientType.TO, msgAddrTo );
            msg.setFrom( addressFrom );
            LOGGER.info( "From:" + addressFrom );
            msg.setSubject( subject );
            contentbuff = createMailFormat( );
            BodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            messageBodyPart.setText( contentbuff.toString() );
            messageBodyPart.setHeader( "content-type", "text/html" );
            
            msg.setContent( multipart );
            LOGGER.info( "Sending message...... " );
            Transport.send( msg );
        }
        catch ( Exception exc )
        {
            LOGGER.error( "Error in SendMail.sendShearPinFormMail()" + exc.getMessage(), exc );
        }
    }
    
    private StringBuffer createMailFormat(  )
    {
        StringBuffer contentbuff = new StringBuffer();
        if(deploymentLevel.equalsIgnoreCase("INT"))
        {
        	contentbuff.append( "<font color='red'><b>" + testWarning + "</b></font><br/><br/>" );
        	
        }
        contentbuff.append( "<html style='font'><body><font color='000000' face='calibri' size='3'> Hi,<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" );
        contentbuff.append( "Shear Pin Incident Questionnaire has been submitted by " +shearPinBean.getEmployeeName() + " (" + shearPinBean.getEmployeeNumber() + ") for the below flight," );
        contentbuff.append( "<br/><br/><span style='font-weight:bold;'>Flight Number: </span>" );
        contentbuff.append( "<span style=''>" + shearPinBean.getFlightNumber() + "</span><br/>" );
        contentbuff.append( "<span style='font-weight:bold;'>Station: </span>" );
        contentbuff.append( "<span style=''>" + shearPinBean.getStation() + "</span><br/>" );
        contentbuff.append( "<span style='font-weight:bold;'>Date/Time: </span>" );
        contentbuff.append( "<span style=''>" + shearPinBean.getLocalDateTime() + "</span><br/>" );
        contentbuff.append( "<br/><br/><span style='font-weight:bold;'>Attachments: </span><br/>" );
        if ( ( Validation.validate( shearPinBean.getAttch1() ) &&( shearPinBean.getAttch1().getFileSize() > 0 ) ) || 
        	 ( Validation.validate( shearPinBean.getAttach2() ) && ( shearPinBean.getAttach2().length > 0 ) ) )
        {
            //contentbuff.append( "<span style=''>Attached with this mail</span><br/>" );
        	contentbuff.append( "<span style=''>" +shearPinBean.getAttch1().getFileName() +"</span><br/>" );
        	contentbuff.append( "<span style=''>Shear Pin Incident Questionnaire(" +shearPinBean.getStation()+"-" +shearPinBean.getFlightNumber()+ ").pdf</span><br/>" );
        }
        else
        {
            contentbuff.append( "<span style=''>No attachments</span><br/>" );
        }
        contentbuff.append( "<br/><span style='font-weight:bold;font-size:12px;'>Note:</span> <span style='font-size:12px;'>This is an autogenerated mail. Please do not reply to this.</span>" );
        contentbuff.append( "</font></body></html>" );
        
        return contentbuff;
    }
	
	
}
