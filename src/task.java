import org.json.JSONObject;
import org.json.XML;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class task {
	 
	static String line="",str="";
	
	public static void main(String[] args) {
			
        try { 
        	
        	URL url = new URL("https://gist.githubusercontent.com/evanr76/3365397/raw/7015f47f96a254ba71e1deb1d420a87bade42767/shipwire-rate-response-example.xml");
        	BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        	//System.out.println(System.getProperty("user.dir")+File.separator+"out-{" +uniqueID+ "}.Json");

			while ((line = in.readLine()) != null) 
			{ 
				//System.out.println(line);
			    str+=line;  
			}
			
			int[] numOfThread =IntStream.range(0, 10).toArray();
		    ExecutorService executorService = Executors.newFixedThreadPool(numOfThread.length);
		    for (int  x:numOfThread) {
		    	
		        executorService.submit(() -> ConvertXMLToJSON());
		    }
		    executorService.shutdown();
            
		} catch (Exception e1 ) {
			// TODO Auto-generated catch block
			System.out.println(e1.toString());
			e1.printStackTrace();
		}    
	}
	
	 public static void ConvertXMLToJSON(){
		 JSONObject jsondata = XML.toJSONObject(str);
         String jsonString = jsondata.toString(4);  
         //System.out.println(" Was executed by " + Thread.currentThread().getId());
         System.out.println(jsonString);
         CreateJsonFiles(jsonString);
	}
	 
	 public static void CreateJsonFiles(String content){
		try {
			
			FileWriter file = new FileWriter(System.getProperty("user.dir")+File.separator+"out-{" +Thread.currentThread().getId()+ "}.Json");
			file.write(content);
		    file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
