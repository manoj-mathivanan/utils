package sapmenu9;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Servlet implementation class sapmenu9
 */
public class sapmenu9 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sapmenu9() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().print("heloooo!!!! woooorld");
		final int BUFSIZE = 4096;
	    String filePath1;
		String filePath2;
		InputStream input = null;
        OutputStream output = null;
        filePath1 = getServletContext().getRealPath("") + File.separator + "MENU.xls";
        filePath2 = getServletContext().getRealPath("") + File.separator + "MENUU.xls";
        HttpsURLConnection connection = null;
        try {
        	TrustManager[] trustAllCerts = new X509TrustManager[] { new X509TrustManager() {     
        		        public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
        		            return new java.security.cert.X509Certificate[0];
        		        } 
        		        public void checkClientTrusted( 
        		            java.security.cert.X509Certificate[] certs, String authType) {
        		            } 
        		        public void checkServerTrusted( 
        		            java.security.cert.X509Certificate[] certs, String authType) {
        		        }
        		    } 
        		}; 
        		try {
        		    SSLContext sc = SSLContext.getInstance("SSL"); 
        		    sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
        		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        		} catch (Exception e) {
        			e.printStackTrace();
        		} 
     	
            URL url = new URL("https://portal.wdf.sap.corp/irj/go/km/docs/corporate_portal/Human%20Resources%20for%20SAP/Benefits/Labs%20India/MENU.xls");
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization","Basic STA3NDY2Nzo2TVluYW1laXM=");
            connection.connect();

            int fileLength = connection.getContentLength();

            input = connection.getInputStream();
            output = new FileOutputStream(filePath1);

            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {        
                total += count;
                if (fileLength > 0)                   
                output.write(data, 0, count);
            }

            WritableCellFormat times;
            WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
		    times = new WritableCellFormat(times10pt);
		FileInputStream file = new FileInputStream(new File(filePath1));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		File file2 = new File(filePath2);
	    WorkbookSettings wbSettings = new WorkbookSettings();

	    wbSettings.setLocale(new Locale("en", "EN"));

	    WritableWorkbook workbook2 = Workbook.createWorkbook(file2, wbSettings);
	    for(int i=0;i<3;i++)
	    {
	    XSSFSheet sheet = workbook.getSheetAt(i);
	    if(i==0)
	    	workbook2.createSheet("Lunch", i);
	    else if(i==1)
	    	workbook2.createSheet("Snacks", i);
	    else if(i==2)
	    	workbook2.createSheet("Dinner", i);
	    WritableSheet excelSheet = workbook2.getSheet(i);
		//Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		int rownum=-1,colnum=0;
		while (rowIterator.hasNext()) 
		{
			rownum++;
			colnum=-1;
			Row row = rowIterator.next();
			//For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();
			
			while (cellIterator.hasNext()) 
			{
				colnum++;
				Cell cell = cellIterator.next();
				//Check the cell type and format accordingly
				
				switch (cell.getCellType()) 
				{
					case Cell.CELL_TYPE_NUMERIC:
						//System.out.print(cell.getNumericCellValue() + "\t");
						Label label;
						Double t = cell.getNumericCellValue();
					    label = new Label(colnum, rownum,t.toString(),times);
					    excelSheet.addCell(label);
						
						break;
					case Cell.CELL_TYPE_STRING:
						Label label2;
					    label2 = new Label(colnum, rownum,cell.getStringCellValue(),times);
					    excelSheet.addCell(label2);
						break;
				}
			}
			
		}
	    }
		file.close();
		workbook2.write();
	    workbook2.close();
	
        
        File file3 = new File(filePath2);
        int length   = 0;
        ServletOutputStream outStream = response.getOutputStream();
        ServletContext context  = getServletConfig().getServletContext();
        String mimetype = context.getMimeType(filePath2);
        
        // sets response content type
        if (mimetype == null) {
            mimetype = "application/octet-stream";
        }
        response.setContentType(mimetype);
        response.setContentLength((int)file3.length());
        String fileName2 = (new File(filePath2)).getName();
        
        // sets HTTP header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName2 + "\"");
        
        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(file3));
        
        // reads the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1))
        {
            outStream.write(byteBuffer,0,length);
        }
        
        in.close();
        outStream.close();
	
	}
	
    
    
    
    
 catch (Exception e) {
    e.printStackTrace();
} finally {
    try {
        if (output != null)
            output.close();
        if (input != null)
            input.close();
    } catch (IOException ignored) {
    }
}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
