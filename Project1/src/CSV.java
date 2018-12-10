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

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CSV {
	public static Student[] Read() {
		BufferedReader br = null;
		String FN = readC(false);
		if(FN == "") {
			
		}
		else {
			try {
				br = Files.newBufferedReader(Paths.get(FN));
				Charset.forName("UTF-8");
				String line = "";
				Student stu[] = new Student[100];
				int num = 0;
				while((line = br.readLine())!= null) {
					// array[0]~[9]까지 데이터 저장
					String array[] = line.replace(" ", "").split(",");
					int[][] attendance = new int[16][];
					for(int i = 0; i < 16; i++) attendance[i] = new int[] { Integer.parseInt(array[10 + 2 * i]), Integer.parseInt(array[11 + 2 * i]) };
					stu[num] = new Student(Integer.parseInt(array[0]), array[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]), Integer.parseInt(array[7]),Integer.parseInt(array[8]),Integer.parseInt(array[9]));
					stu[num].setAttendance(attendance);
					num++;
				}
				Student Stu[] = new Student[num];
				for(int n = 0 ; n< num ; n++) {
					Stu[n] = stu[n];
				}
				return Stu;
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
		return null;
	}
	
	public static void Write(Student stu[]) {
		BufferedWriter bufWriter =null;
		String FN = readC(true);
		if(FN != "") {
			try {
				bufWriter = Files.newBufferedWriter(Paths.get(FN + ".csv"),Charset.forName("UTF-8"));
				//List<List<String>> allData = readCSV(stu);
				
				/*for(List<String> newLine : allData) {
					List<String> list = newLine;
					for(String data : list) {
						bufWriter.write(data);
						bufWriter.write(",");
					}
					bufWriter.newLine();
				}*/
				for(Student student : stu) {
					int[][] attendance = student.getAttendance();
					if(attendance == null) attendance = new int[16][];
					for(int i = 0; i < attendance.length; i++)
						if(attendance[i] == null) attendance[i] = new int[]{2, 2};
					student.setAttendance(attendance);
					bufWriter.write(student.toString().replace(" ", ""));
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
	}
	
	public static List<List<String>> readCSV(Student stu[]){
		List<List<String>> ret = new ArrayList<List<String>>();
		//BufferedReader br = null;
		for(Student stus : stu) {
			try {
				String line = "";
				line += stus.getStudentID() + ",";
				line += stus.getName() + ",";
				line += stus.getAttendanceScore() + ",";
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
	
	public static String readC(boolean isSave) {
		JFrame window = new JFrame();
		String Folder = "";
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("csv파일", "csv");
		fileChooser.addChoosableFileFilter(filter);
		int result;
		if(isSave) result = fileChooser.showSaveDialog(window);
		else result = fileChooser.showOpenDialog(window);
		if(result == JFileChooser.APPROVE_OPTION) {
			Folder = fileChooser.getSelectedFile().toString();
		}
		else if(result == JFileChooser.CANCEL_OPTION) {
			//System.out.println("");
		}
		return Folder;
	}
}
