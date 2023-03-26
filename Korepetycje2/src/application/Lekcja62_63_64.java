package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

class DialogsUtils{
	
	private static Stage primaryStage;
	private static Event event;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}

	public static void dialogAboutResult(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initModality(Modality.APPLICATION_MODAL);
		
		alert.showAndWait();
	}
	
	public static void dialogAboutWarning(String title, String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}
	
	private Label lblConfirmation;
	private Button buttonTak;
	private Button buttonNie;
	
	public static void dialogConfirm(String title, String message) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(primaryStage);
		
        Button exitButton = (Button) alert.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Tak");
        
        Button cancelButton = (Button) alert.getDialogPane().lookupButton(
                ButtonType.CANCEL
        );
        cancelButton.setText("Nie");

		Optional<ButtonType> result = alert.showAndWait();
        if (!ButtonType.OK.equals(result.get())) {
            event.consume();
        }
        else {
        	new Lekcja62_63_64().disposeThreads();
        }
	}
	
}

public class Lekcja62_63_64 extends Application{
	
	private Label lblQuestion1;
	private Label lblQuestion2;
	private TextField jtxtQuestion1;
	private TextField jtxtQuestion2;
	private Label lblSearch;
	private static Service[] services = new Service[5];
	private static MyTask[] myTask = new MyTask[5];
	private String check1, check2;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
//		Scanner scanner = new Scanner(new File("D:\\Users\\kszym\\eclipse-workspace\\Korepetycje2\\DaneWejscie.txt"));
//		while(scanner.hasNextLine()) {
//			String linia = scanner.nextLine();
//		}
//		scanner.close();
		
		Pane r = new Pane();
		Font font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 14);
		
		lblQuestion1 = new Label("Podaj Nazwe watku");
		lblQuestion1.setFont(font);
		lblQuestion1.setLayoutX(23);
		lblQuestion1.setLayoutY(15);
		lblQuestion1.setTextFill(Color.BLACK);
		r.getChildren().add(lblQuestion1);
		
		jtxtQuestion1 = new TextField();
		jtxtQuestion1.setFont(font);
		jtxtQuestion1.setLayoutX(23);
		jtxtQuestion1.setLayoutY(40);
		jtxtQuestion1.setStyle("-fx-text-fill: black;");
		r.getChildren().add(jtxtQuestion1);
		
		lblQuestion2 = new Label("Podaj Nazwe owoców");
		lblQuestion2.setFont(font);
		lblQuestion2.setLayoutX(23);
		lblQuestion2.setLayoutY(70);
		lblQuestion2.setTextFill(Color.BLACK);
		r.getChildren().add(lblQuestion2);
		
		jtxtQuestion2 = new TextField();
		jtxtQuestion2.setFont(font);
		jtxtQuestion2.setLayoutX(23);
		jtxtQuestion2.setLayoutY(95);
		jtxtQuestion2.setStyle("-fx-text-fill: black;");
		r.getChildren().add(jtxtQuestion2);
		
		lblSearch = new Label("Szukaj");
		lblSearch.setFont(font);
		lblSearch.setLayoutX(23);
		lblSearch.setLayoutY(125);
		lblSearch.setTextFill(Color.BLACK);
		r.getChildren().add(lblSearch);
		
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
		@Override 
			public void handle(MouseEvent e) { 
				
				check1 = jtxtQuestion1.getText();
				check2 = jtxtQuestion2.getText();
				if (check1.equals("ThreadA")) {
					myTask[0].setStr(check2);
					myTask[0].setThreadArray(myTask);
					myTask[0].setCheckOtherThreads(true);
					myTask[0].setCheckThisThread(true);
					
				}
				else if (check1.equals("ThreadB")) {
					
					myTask[1].setStr(check2);
					myTask[1].setThreadArray(myTask);
					myTask[1].setCheckOtherThreads(true);
					myTask[1].setCheckThisThread(true);
					
				}
				else if (check1.equals("ThreadC")) {
					
					myTask[2].setStr(check2);
					myTask[2].setThreadArray(myTask);
					myTask[2].setCheckOtherThreads(true);
					myTask[2].setCheckThisThread(true);
					
				}
				else if (check1.equals("ThreadD")) {
					
					myTask[3].setStr(check2);
					myTask[3].setThreadArray(myTask);
					myTask[3].setCheckOtherThreads(true);
					myTask[3].setCheckThisThread(true);
					
				}
				else if (check1.equals("ThreadE")) {
					
					myTask[4].setStr(check2);
					myTask[4].setThreadArray(myTask);
					myTask[4].setCheckOtherThreads(true);
					myTask[4].setCheckThisThread(true);
					
				}
				else {
					
					DialogsUtils.dialogAboutWarning("Uwaga", "Nie ma wątku o nazwie " + check1);
					
				}
			} 
		};  
		
		lblSearch.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		
		myTask[0] = new MyTask(true, "ThreadA");
		myTask[1] = new MyTask(true, "ThreadB");
		myTask[2] = new MyTask(true, "ThreadC");
		myTask[3] = new MyTask(true, "ThreadD");
		myTask[4] = new MyTask(true, "ThreadE");
		
		myTask[0].start();
		myTask[1].start();
		myTask[2].start();
		myTask[3].start();
		myTask[4].start();
		
		primaryStage.setWidth(300);
		primaryStage.setHeight(200);
		primaryStage.setResizable(false);
		
		Scene sc = new Scene(r, 200, 200);
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				new DialogsUtils().setPrimaryStage(primaryStage);
				new DialogsUtils().setEvent(event);
				DialogsUtils.dialogConfirm("Potwierdź Zamknięcie", "Czy na pewno chcesz zamknąć aplikację?");
			}
			
		});
		
		primaryStage.setScene(sc);
		
		primaryStage.setTitle("Threads"); 
		primaryStage.show(); 

	}
	
	public void disposeThreads() {
		myTask[0].setKeepThread(false);
		myTask[1].setKeepThread(false);
		myTask[2].setKeepThread(false);
		myTask[3].setKeepThread(false);
		myTask[4].setKeepThread(false);
	}
	
	public static class MyTask extends Service<Void> {
	
		private boolean keepThread;
		private String threadName;
		private String str;
		private boolean checkOtherThreads;
		private boolean checkThisThread;
		private MyTask[] threadArray;
		private String fileContent;
		
		
		public MyTask(boolean keepThread, String threadName) {
			this.keepThread = keepThread;
			this.threadName = threadName;
			this.str = null;
			this.checkOtherThreads = true;
			this.checkThisThread = false;
			this.threadArray = null;
		}
		
		public String getThreadName() {
			return threadName;
		}
	
		public void setThreadName(String threadName) {
			this.threadName = threadName;
		}
		
		public boolean getKeepThread() {
			return keepThread;
		}
	
		public void setKeepThread(boolean keepThread) {
			this.keepThread = keepThread;
		}
		
		public String getStr() {
			return str;
		}
	
		public void setStr(String str) {
			this.str = str;
		}
		
		public boolean getCheckOtherThreads() {
			return checkOtherThreads;
		}
	
		public void setCheckOtherThreads(boolean checkOtherThreads) {
			this.checkOtherThreads = checkOtherThreads;
		}
		
		public boolean getCheckThisThread() {
			return checkThisThread;
		}
	
		public void setCheckThisThread(boolean checkThisThread) {
			this.checkThisThread = checkThisThread;
		}
		
		public MyTask[] getThreadArray() {
			return threadArray;
		}
	
		public void setThreadArray(MyTask[] threadArray) {
			this.threadArray = threadArray;
		}
		
		public String getFileContent() {
			
			String threadName = getThreadName();
			Scanner scanner;
			try {
				scanner = new Scanner(new File("D:\\Users\\kszym\\eclipse-workspace\\Korepetycje2\\Lekcja62_" + threadName + ".txt"));
				fileContent = scanner.nextLine();
				scanner.close();
				return fileContent;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			return "";
		}
	
		public void setFileContent(String fileContent) {
			
			String threadName = getThreadName();
			PrintWriter pw1;
			try {
				pw1 = new PrintWriter(new File("D:\\Users\\kszym\\eclipse-workspace\\Korepetycje2\\Lekcja62_" + threadName + ".txt"));
				pw1.print(fileContent);
				pw1.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
		@Override
		protected Task createTask() {
			
			return new Task<Void>(){
				
	            @Override
	            protected Void call() throws Exception {
	            	while(keepThread) {
	            		
	            		try { Thread.sleep(1000); } catch(InterruptedException ie) {ie.printStackTrace();}
	            		
		                Platform.runLater(() -> {
		
		                		if (checkThisThread) {
		                			DialogsUtils.dialogAboutResult("Wynik", sprawdz(str, checkOtherThreads));
		                		}
		
		                });
	            	}
	                return null;
	            }
			};
			
	
		}
	    
	    public String sprawdz(String str, boolean checkOtherThreads) {
			
	    	String threadName = getThreadName();
	    	Scanner scanner;
			String linia1 = getFileContent();
			if (linia1.split(" ")[0].equals(str+":")) {
    			checkOtherThreads=false;
    			checkThisThread=false;
    			System.out.println(threadName);
				return threadName + " " + getFileContent();
			}
			else if (threadArray!=null && checkOtherThreads==true) {
				for (MyTask thread : threadArray) {
					
					if (!thread.getThreadName().equals(threadName)) {
						
						String linia2 = thread.getFileContent();
						System.out.println(linia2.split(" ")[0]);
						if (linia2.split(" ")[0].equals(str+":")) {
							setFileContent(linia2);
							thread.setFileContent(linia1);
                			checkOtherThreads=false;
                			checkThisThread=false;
							return threadName + " " + linia2;
						}
					}
				}
			}
	    	
			checkOtherThreads=false;
			checkThisThread=false;
			return ("Nie ma takich owoców nazywających się "+str);
			
	    }

	}

	public static void main(String[] args) {
		
		launch(args);
		
	}

	
			
}
//Uruchamiamy dowolną ilość wątków, kaady watek ma swoj plik z zasobami w postaci nazwa zasobu: ilosc.
//Kazdy watek ma informacje o kilku innych watkach dzialajacych.
//Klient odpytuje dowolny watek o jego zasob. Jesli watek taka informacje posiada odpowiada nazwa zasobu i jego iloscia
//Jezli dany watek nie posiada informacji o zasobie wowczas odpytuje te watki o ktorych informacje ma o mozliwosc wymiany.
//Jezeli ktorys z watkow ma posidany zasob wowczas nastepuje wymiana (pomiedzy plikami) o zasob ktory watek nie ma informacji.