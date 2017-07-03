
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ShearPdf {
	private static String USER_PASSWORD = "password";
	private static String OWNER_PASSWORD = "sudhakar";

	public static void main(String[] args) {
		
		//http://developers.itextpdf.com/examples/tables-itext5
		
		String station = "MSP";
		String aircraftNumb = "748";
		String flightnumb = "7401";
		String towbarType = "hydro";
		String other = "NA";
		String localDate = "12-24-2016 23:15:00";
		String gateNumb = "7A";
		String pushbacktugAsstNumb = "AF32";
		String towbarAsstNumb = "3456";
		String weather = "32c";
		
		String name = "Sudhakar";
		String empNumb = "587917";
		String seniority = "senior";
		String pushbackTraining = "12-24-2016";
		String freq = "weekly";
		
		
		String ans1 = "Yes";
		String ans2 = "Yes";
		String ans3 = "Yes";
		String ans4 = "Yes";
		String ans5 = "Yes";
		String ans6 = "Yes";
		String ans7 = "Yes";
		String ans8 = "Yes";
		String ans9 = "Yes";
		String ans10 = "Yes";
		
		String description ="Description";
		
		/*List<String> field1 = new ArrayList<String>();
		List<String> value1 = new ArrayList<String>();
		field1.add("Station");
		field1.add("Local Date/Time");
		field1.add("Aircraft Number");
		field1.add("Gate Number");
		field1.add("Flight Number");
		field1.add("Pushback Tug Asset Number");
		field1.add("Towbar Type");
		field1.add("Towbar Asset Number");
		field1.add("Other Towbar");
		field1.add("Weather at time of event");
		
		value1.add(station);
		value1.add(aircraftNumb);
		value1.add(flightnumb);
		value1.add(towbarType);
		value1.add(other);
		value1.add(localDate);
		value1.add(gateNumb);
		value1.add(pushbacktugAsstNumb);
		value1.add(towbarAsstNumb);
		value1.add(weather);*/
		
		List<FieldBean> list = new ArrayList<FieldBean>();
		FieldBean bean = new FieldBean();
		
		bean.setField1("Station");
		bean.setValue1(station);
		list.add(bean);
		
		bean.setField1("Local Date/Time");
		bean.setValue1(aircraftNumb);
		list.add(bean);
		
		bean.setField1("Aircraft Number");
		bean.setValue1(aircraftNumb);
		list.add(bean);
		
		
		bean.setField1("Gate Number");
		bean.setValue1(gateNumb);
		list.add(bean);
		
		bean.setField1("Flight Number");
		bean.setValue1(flightnumb);
		list.add(bean);
		
		bean.setField1("Pushback Tug Asset Number");
		bean.setValue1(pushbacktugAsstNumb);
		list.add(bean);
		
		bean.setField1("Towbar Type");
		bean.setValue1(towbarType);
		list.add(bean);
		
		bean.setField1("Towbar Asset Number");
		bean.setValue1(towbarAsstNumb);
		list.add(bean);
		
		bean.setField1("Other Towbar");
		bean.setValue1(other);
		list.add(bean);
		
		bean.setField1("Weather at time of event");
		bean.setValue1(weather);
		list.add(bean);
		
		
		
		
	
		Document document = new Document();
		try{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/587917/Downloads/test.pdf"));
			Font subtitleFont = FontFactory.getFont("Times Roman", 8, BaseColor.BLACK);
			Font ansFont = FontFactory.getFont("Times Roman", 8, BaseColor.BLACK);
			Font TitleFont = FontFactory.getFont("Times Roman", 10, Font.BOLD, BaseColor.BLACK);
			

			
			 PdfPTable table = new PdfPTable(4);
			 table.setWidths(new int[]{ 2, 2, 3, 2});
			 
			 PdfPTable table1 = new PdfPTable(4);
			 table1.setWidths(new int[]{ 2, 2, 3, 2});
			 
			 PdfPTable table2 = new PdfPTable(4);
			 table2.setWidths(new int[]{ 2, 2, 3, 2});
			 
			 PdfPTable table3 = new PdfPTable(1);
			 
			 
		        PdfPCell cell;
		        document.open();
		        
		        cell = new PdfPCell(new Phrase("Shear Pin Incident Questionnaire - Version 6.0",TitleFont));
		        cell.setColspan(4);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        table.addCell(cell);
		        
		        
		      
		        for(FieldBean temp : list){
		        
		        cell = new PdfPCell(new Phrase(temp.getField1(),subtitleFont));
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase(temp.getValue1(),ansFont));
		        table.addCell(cell);
		        }
		        
		        /*cell = new PdfPCell(new Phrase("Station:",ansFont));
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase(station,ansFont));
		        table.addCell(cell);
		        
		        
		        cell = new PdfPCell(new Phrase("Local Date/Time:",subtitleFont));
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase(localDate,ansFont));
		        table.addCell(cell);
		        
		        
		        cell = new PdfPCell(new Phrase("Aircraft Number",subtitleFont));
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase(aircraftNumb,ansFont));
		        table.addCell(cell);
		        
		        
		        cell = new PdfPCell(new Phrase("Gate Number:",subtitleFont));
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase(gateNumb,ansFont));
		        table.addCell(cell);
		       
		        
		        cell = new PdfPCell(new Phrase("Flight Number:",subtitleFont));
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase(flightnumb,ansFont));
		        table.addCell(cell);
		       
		        
		        cell = new PdfPCell(new Phrase("Pushback Tug Asset Number:",subtitleFont));
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase(pushbacktugAsstNumb,ansFont));
		        table.addCell(cell);
		        
		        
		        cell = new PdfPCell(new Phrase("Towbar Type:",subtitleFont));
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase(towbarType,ansFont));
		        table.addCell(cell);
		        
		        
		        cell = new PdfPCell(new Phrase("Towbar Asset Number:",subtitleFont));
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase(towbarAsstNumb,ansFont));
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("Other Towbar:",subtitleFont ));
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase(other,ansFont));
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("Weather at time of event",subtitleFont));
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase(weather,ansFont));
		        table.addCell(cell);*/
		        
		        
		        
		        document.add(table);
		        
		        document.add(new Paragraph(" "));
		       
		        //employee information
		        cell = new PdfPCell(new Phrase("Employee Information (Push Back Operator)",TitleFont));
		        cell.setColspan(4);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        table1.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("1. Name:",subtitleFont));
		        cell.setColspan(3);
		        table1.addCell(cell);
		        cell = new PdfPCell(new Phrase(name,ansFont));
		        table1.addCell(cell);
		        
		        
		        cell = new PdfPCell(new Phrase("2. Employee Number:",subtitleFont));
		        cell.setColspan(3);
		        table1.addCell(cell);
		        cell = new PdfPCell(new Phrase(empNumb,ansFont));
		        table1.addCell(cell);
		        
		        
		        cell = new PdfPCell(new Phrase("3. Seniority",subtitleFont));
		        cell.setColspan(3);
		        table1.addCell(cell);
		        cell = new PdfPCell(new Phrase(seniority,ansFont));
		        table1.addCell(cell);
		        
		        
		        cell = new PdfPCell(new Phrase("4. Latest pushback training date:",subtitleFont));
		        cell.setColspan(3);
		        table1.addCell(cell);
		        cell = new PdfPCell(new Phrase(pushbackTraining,ansFont));
		        table1.addCell(cell);
		       
		        
		        cell = new PdfPCell(new Phrase("5. Pushback Frequency:",subtitleFont));
		        cell.setColspan(3);
		        table1.addCell(cell);
		        cell = new PdfPCell(new Phrase(freq,ansFont));
		        table1.addCell(cell);
		        
		        
		        document.add(table1);
		        document.add(new Paragraph(" "));
		        
		        cell = new PdfPCell(new Phrase("Operational Conditions",TitleFont));
		        cell.setColspan(4);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        table2.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("1. Were the brakes released/cleared to push authorization received from the Captain?",subtitleFont));
		        cell.setColspan(3);
		        table2.addCell(cell);
		        cell = new PdfPCell(new Phrase(ans1,ansFont));
		        table2.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("2. Was the Pushback conducted at a walking pace?",subtitleFont));
		        cell.setColspan(3);
		        table2.addCell(cell);
		        cell = new PdfPCell(new Phrase(ans1,ansFont));
		        table2.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("3. Was the push conducted in a 'non-shifting' gear?",subtitleFont));
		        cell.setColspan(3);
		        table2.addCell(cell);
		        cell = new PdfPCell(new Phrase(ans1,ansFont));
		        table2.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("4. Where any ground obstacles contacted during push back?",subtitleFont));
		        cell.setColspan(3);
		        table2.addCell(cell);
		        cell = new PdfPCell(new Phrase(ans1,ansFont));
		        table2.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("5. Was aircraft going in a straight line, flat level surface?",subtitleFont));
		        cell.setColspan(3);
		        table2.addCell(cell);
		        cell = new PdfPCell(new Phrase(ans1,ansFont));
		        table2.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("6. A/C Brakes applied during any phase of towing/pushback operation?",subtitleFont));
		        cell.setColspan(3);
		        table2.addCell(cell);
		        cell = new PdfPCell(new Phrase(ans1,ansFont));
		        table2.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("7. Any sudden stops or movements during pushback?",subtitleFont));
		        cell.setColspan(3);
		        table2.addCell(cell);
		        cell = new PdfPCell(new Phrase(ans1,ansFont));
		        table2.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("8. POIs conducted on the tractor and tow bar prior to use?",subtitleFont));
		        cell.setColspan(3);
		        table2.addCell(cell);
		        cell = new PdfPCell(new Phrase(ans1,ansFont));
		        table2.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("9. Which light on the nose gear was lit at Pushback?",subtitleFont));
		        cell.setColspan(3);
		        table2.addCell(cell);
		        cell = new PdfPCell(new Phrase(ans1,ansFont));
		        table2.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("10. Phase of pushback when pin break occurred?",subtitleFont));
		        cell.setColspan(3);
		        table2.addCell(cell);
		        cell = new PdfPCell(new Phrase(ans1,ansFont));
		        table2.addCell(cell);
		        
		        document.add(table2);
		        document.add(new Paragraph(" "));
		        
		        //description
		        cell = new PdfPCell(new Phrase("Description",TitleFont));
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        table3.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph(description,ansFont));
		        table3.addCell(cell);
		        
		        document.add(table3);
	        
	        document.close();
	        
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
	}


