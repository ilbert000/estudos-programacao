import java.io.*;

public class Main {

    public static void main(String[] args) {
        String[] lines = new String[] { "One Life... Good Day!" };

        String path = "C:\\Users\\ilber\\ProjetosP\\out.txt";

        //para nao criar um novo arquivo e sim reescrver algo que ja existe adicione o paramentro true como argumento dentro do try no pth (caminho)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}