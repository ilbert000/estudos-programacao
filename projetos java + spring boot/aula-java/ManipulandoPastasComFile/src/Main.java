import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a foçder path: ");
        String strPath = sc.nextLine();

        File psth = new File(strPath);

        File[] folders = psth.listFiles(File::isDirectory);
        System.out.println("FOLDERS: ");
        for (File folder : folders){
            System.out.println(folder);
        }

        File[] files = psth.listFiles(File::isFile);
        System.out.println("FILES: ");
        for (File file : files){
            System.out.println(file);
        }

        boolean sucess = new File(strPath + "\\subdir").mkdir();
        System.out.println("Directory created!" + sucess);

        sc.close();
    }
}