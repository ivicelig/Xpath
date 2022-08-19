import java.io.File;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;

public class Main
{

    public static void main(String[] args)
    {
        JFileChooser fileChooser = new JFileChooser();
        SearchObjectFactory searchObjectFactory = new SearchObjectFactory();
        fileChooser.setMultiSelectionEnabled(true);

        JFrame jFrame = new JFrame();
        int result = fileChooser.showOpenDialog(jFrame);

        File[] selectedFiles = null;

        if (result == JFileChooser.APPROVE_OPTION)
        {
            selectedFiles = fileChooser.getSelectedFiles();
        }

        if (selectedFiles == null)
        {
            System.exit(0);
        }

        SearchObject searchObject = searchObjectFactory.getSearchObject();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (File selectedFile : selectedFiles)
        {
            executorService.submit(new FileProcessor(selectedFile, searchObject));
        }
        executorService.shutdown();
    }
}
