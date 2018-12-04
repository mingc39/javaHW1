import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSV {
	public static void Read(String FN) {
		List<List<String>> ret = new ArrayList<List<String>>();
		BufferedReader br = null;
		
		try {
			br = Files.newBufferedReader(Paths.get("C:\\Users\\PC\\Desktop\\" + FN +".csv"));
			Charset.forName("UTF-8");
			String line = "";
			
			while((line = br.readLine())!= null) {
				List<String> tmpList = new ArrayList<String>();
				// array[0]~[9]까지 데이터 저장
				String array[] = line.split(",");
				
				tmpList = Arrays.asList(array);
				System.out.println(tmpList);
				ret.add(tmpList);
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(br != null) {
					br.close();
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void Write(String FN, Student stu[]) {
		BufferedWriter bufWriter =null;
		try {
			bufWriter = Files.newBufferedWriter(Paths.get("C:\\Users\\PC\\Desktop\\"+ FN +".csv"),Charset.forName("UTF-8"));
			List<List<String>> allData = readCSV(stu);
			
			for(List<String> newLine : allData) {
				List<String> list = newLine;
				for(String data : list) {
					bufWriter.write(data);
					bufWriter.write(",");
				}
				bufWriter.newLine();
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(bufWriter != null) {
					bufWriter.close();
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static List<List<String>> readCSV(Student stu[]){
		List<List<String>> ret = new ArrayList<List<String>>();
		//BufferedReader br = null;
		for(Student stus : stu) {
			try {
				String line = "";
				line += stus.getStudentID() + ",";
				line += stus.getName() + ",";
				line += stus.getAttendance() + ",";
				line += stus.getMidTest() + ",";
				line += stus.getFinalTest() + ",";
				line += stus.getHomework() + ",";
				line += stus.getQuiz() + ",";
				line += stus.getPt() + ",";
				line += stus.getReport() + ",";
				line += stus.getOthers() + ",";
					
				List<String> tmpList = new ArrayList<String>();
				String array[] = line.split(",");
				tmpList = Arrays.asList(array);
				ret.add(tmpList);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

		/*try {
			br = Files.newBufferedReader(Paths.get("C:\\Users\\PC\\Desktop\\DDD.csv"));
			Charset.forName("UTF-8");
			String line = "";
			
			while((line = br.readLine()) != null) {
				List<String> tmpList = new ArrayList<String>();
				String array[] = line.split(",");
				
				tmpList = Arrays.asList(array);
				System.out.println(tmpList);
				ret.add(tmpList);
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(br != null) {
					br.close();
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}*/
		return ret;
	}
}
